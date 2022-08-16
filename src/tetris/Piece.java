package tetris;

import java.awt.*;

import static util.Constants.BoardConstants.*;
import static util.Constants.TetrominoConstants.*;

public class Piece {

    private Board board;
    private Point pos = new Point(3, 7);
    private int[][][] formations, wallKickData;
    private int[][] piece = new int[4][2], ghostPiece = new int[4][2];
    private int pieceType, curRotation = 0;

    public Piece(Board board, int pieceType) {
        this.board = board;
        this.pieceType = pieceType;
        formations = GetFormations(pieceType);
        wallKickData = GetWallKickData(pieceType);
        updatePieces();
    }

    public void draw(Graphics g) {
        drawGhost(g);
        drawPlayingPiece(g);
    }

    private void drawGhost(Graphics g) {
        for (int[] p : ghostPiece) {
            int xTile = (int) (board.getX() + (p[0] * TILE_SIZE) + 1);
            int yTile = (int) (board.getY() + (p[1] * TILE_SIZE) + 1);

            g.setColor(new Color(100, 100, 100));
            g.fillRect(xTile, yTile, PIECE_TILE_SIZE, PIECE_TILE_SIZE);
        }
    }

    // Updates both the playing and ghost piece
    private void updatePieces() {
        updatePiece(piece, pos, curRotation);
        updateGhost();
    }

    // Repositions the piece components (minos) relative to pos and at the provided rotation
    protected void updatePiece(int[][] piece, Point pos, int rotValue) {
        for (int i = 0; i < piece.length; i++) {
            piece[i][0] = pos.x + this.formations[rotValue][i][0];
            piece[i][1] = pos.y + this.formations[rotValue][i][1];
        }
    }

    // Updates the position of the ghost piece to be at the lowest possible position of the board
    private void updateGhost() {
        Point ghostPos = new Point(pos.x, pos.y);
        updatePiece(ghostPiece, ghostPos, curRotation);

        while (canMoveHere(ghostPiece, ghostPos.x, ghostPos.y + 1)) {
            ghostPos.y++;
        }

        updatePiece(ghostPiece, ghostPos, curRotation);
    }

    // Checks if the piece can be moved to x, y
    public boolean canMoveHere(int[][] piece, int x, int y) {
        int dX = x - pos.x;
        int dY = y - pos.y;
        for (int[] p : piece) {
            if (!isAvailableTile(p[0] + dX, p[1] + dY)) {
                return false;
            }
        }
        return true;
    }

    // Returns if a tile of a piece can be moved to x, y
    private boolean isAvailableTile(int x, int y) {
        if (x >= 0 && x < NUM_COLS && y < NUM_ROWS) {
            if (y >= 0) {
                return board.getBoardContents()[y][x] == 0;
            } else {
                return true;
            }
        }
        return false;
    }

    // Rotates the playing piece by dir. -1 for CCW and 1 for CW.
    public boolean rotate(int dir) {
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
            return true;
        }
        return false;
    }

    /* Checks if the provided rotation is possible by using the wall-kick data.
        Returns the first possible Point that allows the rotation to be valid.
     */
    protected Point checkValidRotation(int newRotation, int dir) {
        int[][] rotatedPiece = new int[4][2];

        updatePiece(rotatedPiece, pos, newRotation);

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

        System.out.println("Failed to rotate");
        return null;
    }

    // Moves the piece to x and y, if possible
    public boolean move(int x, int y) {
        if (canMoveHere(this.piece, x, y)) {
            pos.x = x;
            pos.y = y;
            updatePieces();
            return true;
        } else {
            System.out.println("RESTICTION: Cannot move piece to " + x + ", " + y);
            return false;
        }
    }

    private void drawPlayingPiece(Graphics g) {
        for (int[] p : piece) {
            int xTile = (int) (board.getX() + (p[0] * TILE_SIZE) + 1);
            int yTile = (int) (board.getY() + (p[1] * TILE_SIZE) + 1);

            g.setColor(new Color(66, 188, 245));
            g.fillRect(xTile, yTile, PIECE_TILE_SIZE, PIECE_TILE_SIZE);
        }
    }

    // Returns whether the piece can move down by a tile
    public boolean canMoveDown() {
        return canMoveHere(piece, pos.x, pos.y + 1);
    }

    // Returns if the spawn spot for the piece is avaible, signaling that the game is still ongoing
    public boolean isValidSpawn() {
        return canMoveHere(piece, pos.x, pos.y);
    }

    // Locks the piece onto the board and creates a new one
    public void lock() {
        for (int[] p : ghostPiece) {
            board.getBoardContents()[p[1]][p[0]] = pieceType;
        }
    }

    public void newPiece(int pieceType) {
        this.pieceType = pieceType;
        pos = new Point(3, 7);
        formations = GetFormations(pieceType);
        wallKickData = GetWallKickData(pieceType);
        curRotation = 0;
        updatePieces();
    }

    public int getX() {
        return pos.x;
    }

    public int getY() {
        return pos.y;
    }
}
