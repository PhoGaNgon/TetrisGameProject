package tetris;

import java.awt.*;
import java.util.Arrays;

import static util.Constants.BoardConstants.*;

public class Board extends Rectangle {

    private int x, y;
    private int[][] boardContents;

    public Board(int x, int y) {
        super(x, y, BOARD_WIDTH, BOARD_HEIGHT);
        this.x = x;
        this.y = y;
        initBoardContents();
    }

    private void initBoardContents() {
        boardContents = new int[NUM_TILES_HEIGHT][NUM_TILES_WIDTH];
        for (int[] i : boardContents)
            Arrays.fill(i, 0);

        boardContents[19][9] = 1;
        boardContents[10][0] = 1;
    }

    public void drawGrid(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        for (int i = 1; i < NUM_TILES_HEIGHT; i++) {
            // Draw row lines
            int y1 = (int) (y + i * TILES_SIZE);
            g.drawLine(x, y1, x + BOARD_WIDTH, y1);

            for (int j = 1; j < NUM_TILES_WIDTH; j++) {
                // Draw column lines
                int x1 = (int) (x + j * TILES_SIZE);
                g.drawLine(x1, y, x1, y + BOARD_HEIGHT);
            }
        }
    }

    public void renderBoardContents(Graphics g) {
        g.setColor(Color.PINK);
        for (int i = 0; i < boardContents.length; i++) {
            for (int j = 0; j < boardContents[i].length; j++) {
                if (boardContents[i][j] == 0) {
                    int x1 = (int) (x + j * TILES_SIZE) + 1;
                    int y1 = (int) (y + i * TILES_SIZE) + 1;
                    g.fillRect(x1, y1, (int) TILES_SIZE, (int) TILES_SIZE);
                }
            }
        }
    }

    public void render(Graphics g) {
//        g.setColor(Color.BLACK);
//        g.fillRect(x, y, width, height);
        renderBoardContents(g);
        drawGrid(g);
    }

}
