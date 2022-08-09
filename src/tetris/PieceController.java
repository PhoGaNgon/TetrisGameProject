package tetris;

import java.awt.*;

import static util.Constants.BoardConstants.*;
import static util.Constants.BoardConstants.PIECE_TILE_SIZE;
import static util.Constants.TetrominoConstants.*;

public class PieceController {

    private int[][] mainTetrimino;
    private int[][][] formation;
    private Board board;
    private int x, y;
    private int curRotation = 0;
    private int pieceType = Z_PIECE;

    private int fallTick = 0, fallSpeed = 150; // How long it takes for the piece to fall
    private int placeTick = 0, placeDelay = 150; // How long it takes to place the piece

    public PieceController(Board board) {
        this.board = board;
        this.formation = GetFormations(pieceType);
        initPieces();
    }

    private void initPieces() {
        mainTetrimino = new int[4][2];
        move(3, 0);
    }

    public void update() {
        fall();

        updatePosition(mainTetrimino);
    }

    public void draw(Graphics g) {
        for (int[] p : mainTetrimino) {
            int xPos = (int) (board.getX() + p[0] * TILE_SIZE) + 1;
            int yPos = (int) (board.getY() + p[1] * TILE_SIZE) + 1;
            g.setColor(new Color(66, 188, 245));
            g.fillRect(xPos, yPos, PIECE_TILE_SIZE, PIECE_TILE_SIZE);
        }
    }

    private void updatePosition(int[][] tetrimino) {
        for (int i = 0; i < tetrimino.length; i++) {
            tetrimino[i][0] = x + formation[curRotation][i][0];
            tetrimino[i][1] = y + formation[curRotation][i][1];
        }
    }

    private void fall() {
        fallTick++;

        if (canMoveAllPiecesHere(0, 1, mainTetrimino)) {
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
        curRotation = 0;
    }

    private void placePiece() {
        for (int[] p : mainTetrimino) {
            board.updateTile(p[0], p[1], 1);
        }
        board.clearLine();
    }

    // Sets the current position of the controller and all its pieces to the new position
    private void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void moveLeft() {
        if (canMoveAllPiecesHere(-1, 0, mainTetrimino)) {
            move(x - 1, y);
        }
    }

    public void moveRight() {
        if (canMoveAllPiecesHere(1, 0, mainTetrimino)) {
            move(x + 1, y);
        }
    }

    public void moveDown() {
        if (canMoveAllPiecesHere(0, 1, mainTetrimino)) {
            move(x, y + 1);
        }
    }

    public void moveUp() {
        if (canMoveAllPiecesHere(0, -1, mainTetrimino)) {
            move(x, y - 1);
        }
    }

    public void rotate(int dir) {
        int newRotation = curRotation + dir;

        if (newRotation == 4) {
            newRotation = 0;
        } else if (newRotation == -1) {
            newRotation = 3;
        }

        if (checkValidRotation(newRotation)) {
            curRotation = newRotation;
            System.out.println("Rotate successful");
        } else {
            System.out.println("Cannot rotate");
        }
    }


    public void rotateClockwise() {
        rotate(1);
    }

    // Checks if the tetrimino can be rotated at its current position
    private boolean checkValidRotation(int newRotation) {
        int[][] uncommittedTetrimino = new int[4][2];
        for (int i = 0; i < uncommittedTetrimino.length; i++) {
            uncommittedTetrimino[i][0] = x + formation[newRotation][i][0];
            uncommittedTetrimino[i][1] = y + formation[newRotation][i][1];
        }

        if (canMoveAllPiecesHere(0, 0, uncommittedTetrimino)) {
            return true;
        } else {
            return findBestNearbySpot(uncommittedTetrimino);
        }
    }

    // Tries to find any nearby spots to fit the rotated piece if the original position was not available
    private boolean findBestNearbySpot(int[][] uncommittedTetrimino) {
        if (canMoveAllPiecesHere(x, y - 1, uncommittedTetrimino)) {
            move(x, y - 1);
            return true;
        }

        for (int i = -1; i <= 1; i += 2) {
            if (canMoveAllPiecesHere(i, 0, uncommittedTetrimino)) {
                move(x + i, y);
                return true;
            }
        }

        if (pieceType == I_PIECE) {
            for (int i = -2; i <= 2; i += 4) {
                if (canMoveAllPiecesHere(i, 0, uncommittedTetrimino)) {
                    move(x + i, y);
                    return true;
                }
            }
        }

        return false;
    }

    public void rotateCounterClockwise() {
        rotate(-1);
    }

    // Checks if all pieces can be moved by dX and dY
    private boolean canMoveAllPiecesHere(int dX, int dY, int[][] tetrimino) {
        for (int[] p : tetrimino) {
            if (!canMovePieceHere(p[0], p[1], dX, dY)) {
                return false;
            }
        }
        return true;
    }

    // Checks if the piece can be moved by dX and dY positions
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
