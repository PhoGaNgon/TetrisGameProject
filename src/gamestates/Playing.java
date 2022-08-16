package gamestates;

import tetris.Board;
import tetris.Piece;
import util.ImageLoader;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import static util.Constants.GameConstants.*;
import static util.Constants.BoardConstants.*;
import static util.Constants.*;

public class Playing implements GamestateMethods {

    private final BufferedImage background = ImageLoader.GetImage(ImageLoader.PLAYING_BACKGROUND);
    private BufferedImage boardBorder;
    private int borderX, borderY;

    private int fallTick = 0, fallSpeed = UPS_CAP;
    private int lockTick = 0, lockDelay = UPS_CAP / 2;
    private int extLockTick = 0, extLockDelay = (int) (UPS_CAP * 0.5);
    private boolean left, right, down;
    private int leftTick = 0, rightTick = 0, downTick = 0, delaySpeed = 30;
    private boolean gameOver = false;

    private final Board board;
    private Piece piece;

    public Playing() {
        loadBoardBorder();
        board = new Board(borderX + BOARD_OFFSET_FROM_BORDER_X, borderY + BOARD_OFFSET_FROM_BORDER_Y);
        piece = new Piece(board, this, 1);
    }

    // Loads the border image for the board
    private void loadBoardBorder() {
        boardBorder = ImageLoader.GetImage(ImageLoader.PLAYING_FIELD_OUTLINE);
        borderX = GAME_SIZE_WIDTH / 2 - boardBorder.getWidth() / 2;
        borderY = (int) (125 * GAME_SCALE);
    }

    public void update() {
        if (!gameOver) {
            fallPiece();
            controller();
        }
    }

    private void controller() {
        if (!(left && right)) {
            leftController();
            rightController();
        }

        if (down) {
            if (downTick >= 10) {
                downTick = 0;
                piece.moveDown();
                fallTick = 0;
            }
            downTick++;
        } else {
            downTick = 10;
        }
    }

    private void leftController() {
        if (left) {
            if (leftTick == 0) {
                piece.move(piece.getX() - 1, piece.getY());
            } else if (leftTick >= delaySpeed) {
                piece.move(piece.getX() - 1, piece.getY());
                leftTick -= 5;
            }
            leftTick++;
        } else {
            leftTick = 0;
        }
    }

    private void rightController() {
        if (right) {
            if (rightTick == 0) {
                piece.move(piece.getX() + 1, piece.getY());
            } else if (rightTick >= delaySpeed) {
                piece.move(piece.getX() + 1, piece.getY());
                rightTick -= 5;
            }
            rightTick++;
        } else {
            rightTick = 0;
        }
    }

    // Pushes the piece down over time and locks it if it cannot be lowered for some time.
    private void fallPiece() {
        if (piece.canMoveDown()) {
            fallTick++;

            if (fallTick >= fallSpeed) {
                fallTick = 0;
                piece.moveDown();
            }
        } else {
            lockTick++;
            extLockTick++;
            fallTick = 0;

            if (lockTick >= lockDelay || extLockTick >= extLockDelay) {
                placePiece();
            }
        }
    }

    // Places/locks the current piece to the lowest possible position
    private void placePiece() {
        fallTick = 0;
        lockTick = 0;
        extLockTick = 0;
        piece.lock();
        board.clearRows();
        piece.newPiece(1);
        if (!piece.isValidSpawn()) {
            setGameOver();
        }
    }

    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, GAME_SIZE_WIDTH, GAME_SIZE_HEIGHT, null);
        g.drawImage(boardBorder, borderX, borderY, boardBorder.getWidth(), boardBorder.getHeight(), null);
        board.draw(g);
        piece.draw(g);
    }

    public void keyPressed(KeyEvent e) {
        if (!gameOver) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_ESCAPE -> Gamestates.gamestate = Gamestates.MENU;
                case KeyEvent.VK_RIGHT -> right = true;
                case KeyEvent.VK_LEFT -> left = true;
                case KeyEvent.VK_DOWN -> down = true;
                case KeyEvent.VK_UP -> piece.rotateLeft();
                case KeyEvent.VK_Z -> piece.rotateRight();
                case KeyEvent.VK_SPACE -> placePiece();
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE -> Gamestates.gamestate = Gamestates.MENU;
            case KeyEvent.VK_RIGHT -> right = false;
            case KeyEvent.VK_LEFT -> left = false;
            case KeyEvent.VK_DOWN -> down = false;
        }
    }

    public void resetLockDelayTick() {
        lockTick = 0;
    }

    public void setGameOver() {
        this.gameOver = true;
        System.out.println("Game Over!");
    }

}
