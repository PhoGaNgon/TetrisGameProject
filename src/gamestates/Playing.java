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

    private BufferedImage background, boardBorder;
    private int borderX, borderY;

    private Board board;
    private Piece piece;

    public Playing() {
        System.out.println("Playing created");
        background = ImageLoader.GetImage(ImageLoader.PLAYING_BACKGROUND);
        loadBoardBorder();
        board = new Board((int) (borderX + 6 * GAME_SCALE), (int) (borderY + 8 * GAME_SCALE));
        piece = new Piece(board, 1);
    }

    private void loadBoardBorder() {
        boardBorder = ImageLoader.GetImage(ImageLoader.PLAYING_FIELD_OUTLINE);
        borderX = GAME_SIZE_WIDTH / 2 - BOARD_BORDER_WIDTH / 2;
        borderY = (int) (50 * GAME_SCALE);
    }

    public void update() {
        board.update();

        if (piece.isActive())
            piece.update();
        else
            piece = new Piece(board, 1);
    }

    public void draw(Graphics g) {
        g.drawImage(background, 0, 0, GAME_SIZE_WIDTH, GAME_SIZE_HEIGHT, null);
        g.drawImage(boardBorder, borderX, borderY, BOARD_BORDER_WIDTH, BOARD_BORDER_HEIGHT, null);
        board.draw(g);
        piece.draw(g);
        //g.setColor(Color.lightGray);
        //g.fillRect(fieldBounds.x, fieldBounds.y, fieldBounds.width, fieldBounds.height);
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE -> Gamestates.gamestate = Gamestates.MENU;
            case KeyEvent.VK_RIGHT -> piece.move(1);
            case KeyEvent.VK_LEFT -> piece.move(-1);
            case KeyEvent.VK_DOWN -> piece.moveDown();
        }
    }

    public void keyReleased(KeyEvent e) {
    }

}
