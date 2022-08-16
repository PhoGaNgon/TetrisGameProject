package gamestates;

import tetris.Board;
import tetris.Piece;
import tetris.PieceController;
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

    private boolean gameOver = false;

    private final Board board;
    private Piece piece;
    private PieceController pieceController;

    public Playing() {
        loadBoardBorder();
        board = new Board(borderX + BOARD_OFFSET_FROM_BORDER_X, borderY + BOARD_OFFSET_FROM_BORDER_Y);
        piece = new Piece(board, 1);
        pieceController = new PieceController(this, board, piece);
    }

    // Loads the border image for the board
    private void loadBoardBorder() {
        boardBorder = ImageLoader.GetImage(ImageLoader.PLAYING_FIELD_OUTLINE);
        borderX = GAME_SIZE_WIDTH / 2 - boardBorder.getWidth() / 2;
        borderY = (int) (125 * GAME_SCALE);
    }

    public void update() {
        if (!gameOver) {
            pieceController.update();
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
                case KeyEvent.VK_RIGHT -> pieceController.setRight(true);
                case KeyEvent.VK_LEFT -> pieceController.setLeft(true);
                case KeyEvent.VK_DOWN -> pieceController.setDown(true);
                case KeyEvent.VK_UP -> pieceController.rotate(1);
                case KeyEvent.VK_Z -> pieceController.rotate(-1);
                case KeyEvent.VK_SPACE -> pieceController.placePiece();
                case KeyEvent.VK_SHIFT -> pieceController.holdPiece();
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE -> Gamestates.gamestate = Gamestates.MENU;
            case KeyEvent.VK_RIGHT -> pieceController.setRight(false);
            case KeyEvent.VK_LEFT -> pieceController.setLeft(false);
            case KeyEvent.VK_DOWN -> pieceController.setDown(false);
        }
    }

    public void setGameOver() {
        this.gameOver = true;
        System.out.println("Game Over!");
    }

}
