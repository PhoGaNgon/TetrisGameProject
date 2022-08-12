package gamestates;

import tetris.Board;
import tetris.Piece;
import util.ImageLoader;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import static util.Constants.GameConstants.*;
import static util.Constants.BoardConstants.*;
import static util.Constants.GAME_SCALE;

public class Playing implements GamestateMethods {

    private final BufferedImage background = ImageLoader.GetImage(ImageLoader.PLAYING_BACKGROUND);
    private BufferedImage boardBorder;
    private int borderX, borderY;

    private int fallTick = 0, fallSpeed = 120;
    private int lockTick = 0, lockDelay = 60, extLockTick = 0, extLockDelay = 180;
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
        board.update();
        fallPiece();
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
        piece.lock();
        piece = new Piece(board, this, 1);
        fallTick = 0;
        lockTick = 0;
        extLockTick = 0;
        if (!piece.isValidSpawn()) {
            setGameOver();
        }
    }

    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, GAME_SIZE_WIDTH, GAME_SIZE_HEIGHT, null);
        g.drawImage(boardBorder, borderX, borderY, boardBorder.getWidth(), boardBorder.getHeight(), null);
        board.draw(g);
        piece.draw(g);
        //g.setColor(Color.lightGray);
        //g.fillRect(fieldBounds.x, fieldBounds.y, fieldBounds.width, fieldBounds.height);
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE -> Gamestates.gamestate = Gamestates.MENU;
            case KeyEvent.VK_RIGHT -> piece.moveRight();
            case KeyEvent.VK_LEFT -> piece.moveLeft();
            case KeyEvent.VK_DOWN -> piece.moveDown();
            case KeyEvent.VK_UP -> piece.rotateLeft();
            case KeyEvent.VK_Z -> piece.rotateRight();
            case KeyEvent.VK_SPACE -> placePiece();
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void resetLockDelayTick() {
        lockTick = 0;
    }

    public void setGameOver() {
        System.out.println("Game Over!");
    }

}
