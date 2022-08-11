package tetris;

import java.awt.*;

import static util.Constants.BoardConstants.*;
import static util.Constants.TetrominoConstants.*;

public class Piece {

    private Board board;
    private Point pos = new Point(3, -2);
    private int[][][] formations;
    private int[][] piece = new int[4][2];
    private int[][] ghostPiece = new int[4][2];
    private int curRotation = 0;
    private int pieceType;

    public Piece(Board board, int pieceType) {
        this.board = board;
        this.pieceType = pieceType;
        formations = GetFormations(pieceType);
        updatePieces();
    }

    // Updates both the playing and ghost piece
    private void updatePieces() {
        updatePiece(piece, pos);
        updateGhost();
    }

    // Updates the position of the components of the provided piece relative to the given pos
    protected void updatePiece(int[][] piece, Point pos) {
        for (int i = 0; i < piece.length; i++) {
            piece[i][0] = pos.x + this.formations[curRotation][i][0];
            piece[i][1] = pos.y + this.formations[curRotation][i][1];
        }
    }

    // Updates the position of the ghost piece to be at the lowest possible position of the board
    private void updateGhost() {
        Point ghostPos = new Point(pos.x, pos.y);
        updatePiece(ghostPiece, ghostPos);

        while (canMoveHere(ghostPiece, ghostPos.x, ghostPos.y + 1)) {
            ghostPos.y++;
        }

        updatePiece(ghostPiece, ghostPos);
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

    // Rotates the playing piece by dir. -1 for CCW and 1 for CW.
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
            updatePieces();
        }
    }

    // Rotates the playing piece clockwise.
    public void rotateRight() {
        rotate(1);
    }

    // Rotates the playing piece counter-clockwise.
    public void rotateLeft() {
        rotate(-1);
    }

    /* Checks if the provided rotation is possible by using the wall-kick data.
        Returns the first possible Point that allows the rotation to be valid.
     */
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
            updatePieces();
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

    private void drawPlayingPiece(Graphics g) {
        for (int[] p : piece) {
            int xTile = (int) (board.getX() + (p[0] * TILE_SIZE) + 1);
            int yTile = (int) (board.getY() + (p[1] * TILE_SIZE) + 1);

            g.setColor(new Color(66, 188, 245));
            g.fillRect(xTile, yTile, PIECE_TILE_SIZE, PIECE_TILE_SIZE);
        }
    }

    private void drawGhost(Graphics g) {
        for (int[] p : ghostPiece) {
            int xTile = (int) (board.getX() + (p[0] * TILE_SIZE) + 1);
            int yTile = (int) (board.getY() + (p[1] * TILE_SIZE) + 1);

            g.setColor(new Color(100, 100, 100));
            g.fillRect(xTile, yTile, PIECE_TILE_SIZE, PIECE_TILE_SIZE);
        }
    }

    public void draw(Graphics g) {
        drawGhost(g);
        drawPlayingPiece(g);
    }

    public boolean canMoveDown() {
        return canMoveHere(piece, pos.x, pos.y + 1);
    }

}
