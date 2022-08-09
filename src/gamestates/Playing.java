package gamestates;

import tetris.Board;
import tetris.PieceController;
import util.ImageLoader;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import static util.Constants.GameConstants.*;
import static util.Constants.BoardConstants.*;
import static util.Constants.GAME_SCALE;

public class Playing implements GamestateMethods {

    private BufferedImage background, boardBorder;
    private int borderX, borderY;

    private Board board;
    private PieceController pieceController;

    public Playing() {
        System.out.println("Playing created");
        background = ImageLoader.GetImage(ImageLoader.PLAYING_BACKGROUND);
        loadBoardBorder();
        board = new Board((int) (borderX + 6 * GAME_SCALE), (int) (borderY + 8 * GAME_SCALE));
        pieceController = new PieceController(board);
    }

    private void loadBoardBorder() {
        boardBorder = ImageLoader.GetImage(ImageLoader.PLAYING_FIELD_OUTLINE);
        borderX = GAME_SIZE_WIDTH / 2 - BOARD_BORDER_WIDTH / 2;
        borderY = (int) (50 * GAME_SCALE);
    }

    public void update() {
        board.update();
        pieceController.update();
    }

    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, GAME_SIZE_WIDTH, GAME_SIZE_HEIGHT, null);
        g.drawImage(boardBorder, borderX, borderY, BOARD_BORDER_WIDTH, BOARD_BORDER_HEIGHT, null);
        board.draw(g);
        pieceController.draw(g);
        //g.setColor(Color.lightGray);
        //g.fillRect(fieldBounds.x, fieldBounds.y, fieldBounds.width, fieldBounds.height);
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE -> Gamestates.gamestate = Gamestates.MENU;
            case KeyEvent.VK_RIGHT -> pieceController.moveRight();
            case KeyEvent.VK_LEFT -> pieceController.moveLeft();
            case KeyEvent.VK_DOWN -> pieceController.moveDown();
            case KeyEvent.VK_UP -> pieceController.rotateClockwise();
            case KeyEvent.VK_Z -> pieceController.rotateCounterClockwise();
        }
    }

    public void keyReleased(KeyEvent e) {
    }

}
