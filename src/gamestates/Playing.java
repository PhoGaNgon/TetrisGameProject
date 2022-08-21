package gamestates;

import tetris.*;
import util.HelpMethods;
import util.ImageLoader;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import static util.Constants.GameConstants.*;
import static util.Constants.BoardConstants.*;
import static util.Constants.PlayingConstants.*;
import static util.Constants.*;

public class Playing implements GamestateMethods {

    private final BufferedImage background = ImageLoader.GetImage(ImageLoader.PLAYING_BACKGROUND);
    private BufferedImage boardBorder;
    private BufferedImage holdPieceContainer;
    private int borderX, borderY;

    private boolean gameOver = false;

    private final Board board;
    private Piece piece;
    private PieceController pieceController;
    private NextPiecesContainer nextPiecesContainer;

    public Playing() {
        loadBoardBorder();
        board = new Board(borderX + BOARD_OFFSET_FROM_BORDER_X, borderY + BOARD_OFFSET_FROM_BORDER_Y);
        piece = new Piece(board, 1);
        pieceController = new PieceController(this, board, piece);
        nextPiecesContainer = new NextPiecesContainer((int) (225 * GAME_SCALE), (int) (60 * GAME_SCALE), pieceController.getPieceQueue());
        holdPieceContainer = ImageLoader.GetImage(ImageLoader.HOLD_PIECE_CONTAINER);
    }

    // Loads the border image for the board
    private void loadBoardBorder() {
        boardBorder = ImageLoader.GetImage(ImageLoader.BOARD_BORDER);
        borderX = GAME_SIZE_WIDTH / 2 - BOARD_BOARDER_WIDTH / 2;
        borderY = (int) (50 * GAME_SCALE);
    }

    public void update() {
        if (!gameOver) {
            pieceController.update();
        }
    }

    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, GAME_SIZE_WIDTH, GAME_SIZE_HEIGHT, null);
        g.drawImage(boardBorder, borderX, borderY, BOARD_BOARDER_WIDTH, BOARD_BOARDER_HEIGHT, null);
        drawHoldContainer(g);
        board.draw(g);
        piece.draw(g);
        nextPiecesContainer.draw(g);
    }

    private void drawHoldContainer(Graphics g) {
        int containerX = (int) (7 * GAME_SCALE);
        int containerY = (int) (60 * GAME_SCALE);
        g.drawImage(holdPieceContainer, containerX, containerY, HOLD_PIECE_CONTAINER_WIDTH, HOLD_PIECE_CONTAINER_HEIGHT, null);
        HelpMethods.DrawMiniTetrimino(g, (int) (containerX + 15 * GAME_SCALE), (int) (containerY + 35 * GAME_SCALE), pieceController.getHeldPiece());
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
