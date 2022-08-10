package tetris;

import java.awt.*;

import static util.Constants.BoardConstants.*;
import static util.Constants.TetrominoConstants.*;

public class Piece {

    private Board board;
    private Point pos = new Point(3, 0);
    private int[][][] formations;
    private int[][] piece = new int[4][2];
    private int curRotation = 0;
    private int pieceType;

    public Piece(Board board, int pieceType) {
        this.board = board;
        this.pieceType = pieceType;
        formations = GetFormations(pieceType);
        updatePiecePos();
    }

    // Updates the position of each piece component relative to the board
    protected void updatePiecePos() {
        for (int i = 0; i < piece.length; i++) {
            piece[i][0] = pos.x + this.formations[curRotation][i][0];
            piece[i][1] = pos.y + this.formations[curRotation][i][1];
        }
    }

    // Checks if the piece can be moved to x, y
    public boolean canMoveHere(int[][] piece, int x, int y) {
        int dX = x - pos.x;
        int dY = y - pos.y;
        for (int[] p : piece) {
            if (!canMovePieceHere(p[0] + dX, p[1] + dY)) {
                return false;
            }
        }
        return true;
    }

    // Returns if a tile of a piece can be moved to x, y
    private boolean canMovePieceHere(int x, int y) {
        if (x >= 0 && x < NUM_COLS && y < NUM_ROWS) {
            if (y >= 0) {
                return board.getBoardContents()[y][x] == 0;
            } else {
                return true;
            }
        }
        return false;
    }

    // Locks the piece onto the board and creates a new one
    public void lock() {
        for (int[] p : piece) {
            board.getBoardContents()[p[1]][p[0]] = pieceType;
        }
    }

    private void rotate(int dir) {
        int newRotation = curRotation + dir;

        if (newRotation == 4) {
            newRotation = 0;
        } else if (newRotation == -1) {
            newRotation = 3;
        }

        Point rotationCheck = checkValidRotation(newRotation, dir);
        if (rotationCheck != null) {
            pos.x = rotationCheck.x;
            pos.y = rotationCheck.y;
            curRotation = newRotation;
            updatePiecePos();
        }
    }

    public void rotateRight() {
        rotate(1);
    }

    public void rotateLeft() {
        rotate(-1);
    }

    protected Point checkValidRotation(int newRotation, int dir) {
        int[][][] wallKickData = GetWallKickData(pieceType);
        int[][] rotatedPiece = new int[4][2];

        for (int i = 0; i < rotatedPiece.length; i++) {
            rotatedPiece[i][0] = pos.x + formations[newRotation][i][0];
            rotatedPiece[i][1] = pos.y + formations[newRotation][i][1];
        }

        if (wallKickData != null) {
            int rotationState = curRotation * 2;

            // If rotating counter-clockwise
            if (dir < 0) {
                rotationState--;
                if (rotationState < 0) {
                    rotationState = 7;
                }
            }

            for (int i = 0; i < wallKickData[rotationState].length; i++) {
                int newX = pos.x + wallKickData[rotationState][i][0];
                int newY = pos.y - wallKickData[rotationState][i][1];
                if (canMoveHere(rotatedPiece, newX, newY)) {
                    return new Point(newX, newY);
                }
            }
        }

        System.out.println("Failed");
        return null;
    }

    // Moves the piece to x and y, if possible
    private void move(int x, int y) {
        if (canMoveHere(this.piece, x, y)) {
            pos.x = x;
            pos.y = y;
            updatePiecePos();
        } else {
            System.out.println("ERROR: Cannot move piece to " + x + ", " + y);
        }
    }

    // Moves the piece left by 1
    public void moveLeft() {
        move(pos.x - 1, pos.y);
    }

    // Moves the piece right by 1
    public void moveRight() {
        move(pos.x + 1, pos.y);
    }

    // Moves the piece down by 1
    public void moveDown() {
        move(pos.x, pos.y + 1);
    }

    // Moves the piece up by 1
    public void moveUp() {
        move(pos.x, pos.y - 1);
    }

    public void draw(Graphics g) {
        for (int[] p : piece) {
            int xTile = (int) (board.getX() + (p[0] * TILE_SIZE) + 1);
            int yTile = (int) (board.getY() + (p[1] * TILE_SIZE) + 1);

            g.setColor(new Color(66, 188, 245));
            g.fillRect(xTile, yTile, PIECE_TILE_SIZE, PIECE_TILE_SIZE);
        }
    }

    public boolean canMoveDown() {
        return canMoveHere(piece, pos.x, pos.y + 1);
    }

}
