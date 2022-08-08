package tetris;

import java.awt.*;
import java.util.Random;

import static util.Constants.BoardConstants.*;
import static util.Constants.BoardConstants.PIECE_TILE_SIZE;
import static util.Constants.TetrominoConstants.*;

public class PieceController {

    private int[][] pieces, formation;
    private Random rand = new Random();
    private Board board;
    private int x, y;

    private int fallTick = 0, fallSpeed = 150; // How long it takes for the piece to fall
    private int placeTick = 0, placeDelay = 150; // How long it takes to place the piece


    public PieceController(Board board) {
        this.board = board;
        this.formation = I_FORMATION;
        pieces = new int[4][2];
        initPieces();
    }

    private void initPieces() {
        move(3, 0);
    }

    public void update() {
        fall();
    }

    public void draw(Graphics g) {
        for (int[] p : pieces) {
            int xPos = (int) (board.getX() + p[0] * TILE_SIZE) + 1;
            int yPos = (int) (board.getY() + p[1] * TILE_SIZE) + 1;
            g.setColor(new Color(66, 188, 245));
            g.fillRect(xPos, yPos, PIECE_TILE_SIZE, PIECE_TILE_SIZE);
        }
    }

    private void fall() {
        fallTick++;

        if (canMoveAllPiecesHere(0, 1)) {
            if (fallTick >= fallSpeed) {
                moveDown();
                fallTick = 0;
            }
        } else {
            fallTick = 0;
            placeTick++;
            if (placeTick >= placeDelay) {
                placeTick = 0;
                placePiece();
                newPiece();
            }
        }
    }

    private void newPiece() {
        move(3, 0);
    }

    private void placePiece() {
        for (int[] p : pieces) {
            board.updateTile(p[0], p[1], 1);
        }
        board.clearLine();
    }

    // Sets the current position of the controller and all its pieces to the new position
    private void move(int x, int y) {
        this.x = x;
        this.y = y;
        for (int i = 0; i < pieces.length; i++) {
            pieces[i][0] = x + formation[i][0];
            pieces[i][1] = y + formation[i][1];
        }
    }

    public void moveLeft() {
        if (canMoveAllPiecesHere(-1, 0)) {
            move(x - 1, y);
        }
    }

    public void moveRight() {
        if (canMoveAllPiecesHere(1, 0)) {
            move(x + 1, y);
        }
    }

    public void moveDown() {
        if (canMoveAllPiecesHere(0, 1)) {
            move(x, y + 1);
        }
    }

    public void moveUp() {
        if (canMoveAllPiecesHere(0, -1)) {
            move(x, y - 1);
        }
    }

    // Checks if all pieces can be moved by dX and dY
    private boolean canMoveAllPiecesHere(int dX, int dY) {
        for (int[] p : pieces) {
            if (!canMovePieceHere(p[0], p[1], dX, dY)) {
                return false;
            }
        }
        return true;
    }

    // Checks if the piece can be moved to the newX and newY positions
    private boolean canMovePieceHere(int xPos, int yPos, int dX, int dY) {
        int newX = xPos + dX;
        int newY = yPos + dY;
        if (newX >= 0 && newX < NUM_COLS && newY < NUM_ROWS) {
            if (newY < 0) { // If the piece is above the top of the board
                return true;
            } else {
                return board.getBoardContents()[newY][newX] == 0;
            }
        }
        return false;
    }
}
