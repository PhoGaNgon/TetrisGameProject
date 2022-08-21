package tetris;

import util.HelpMethods;
import util.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static util.Constants.GAME_SCALE;
import static util.Constants.PlayingConstants.*;

public class NextPiecesContainer {

    private BufferedImage container;
    private ArrayList<Integer> pieceQueue;
    private int x, y; // The x and y for the container
    private int offsetX, offsetY; // For drawing the actual mini tetriminos

    public NextPiecesContainer(int x, int y, ArrayList<Integer> pieceQueue) {
        this.x = x - (NEXT_PIECES_CONTAINER_WIDTH / 2);
        this.y = y;
        offsetX = (int) (this.x + 15 * GAME_SCALE);
        offsetY = (int) (this.y + 40 * GAME_SCALE);
        this.pieceQueue = pieceQueue;
        container = ImageLoader.GetImage(ImageLoader.NEXT_PIECES_CONTAINER);
    }

    public void draw(Graphics g) {
        g.drawImage(container, x, y, NEXT_PIECES_CONTAINER_WIDTH, NEXT_PIECES_CONTAINER_HEIGHT, null);
        drawNextPieces(g);
    }

    private void drawNextPieces(Graphics g) {
        // Displays the next 4 tetriminos in queue
        for (int queueIndex = 0; queueIndex < 4; queueIndex++) {
            HelpMethods.DrawMiniTetrimino(g, offsetX, (int) (offsetY + queueIndex * 25 * GAME_SCALE), pieceQueue.get(queueIndex));
        }
    }

}
