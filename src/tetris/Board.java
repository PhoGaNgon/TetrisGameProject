package tetris;

import java.awt.*;
import java.util.Arrays;

import static util.Constants.BoardConstants.*;
import static util.Constants.TetrominoConstants.*;

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
        boardContents = new int[NUM_ROWS][NUM_COLS];
        for (int[] i : boardContents)
            Arrays.fill(i, 0);

        Arrays.fill(boardContents[19], 1);
        boardContents[19][0] = 0;
    }

    public void drawGrid(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        for (int i = 0; i <= NUM_ROWS; i++) {
            // Draw row lines
            int y1 = (int) (y + i * TILE_SIZE);
            g.drawLine(x, y1, x + BOARD_WIDTH, y1);

            for (int j = 0; j <= NUM_COLS; j++) {
                // Draw column lines
                int x1 = (int) (x + j * TILE_SIZE);
                g.drawLine(x1, y, x1, y + BOARD_HEIGHT);
            }
        }
    }

    public void drawBoardContents(Graphics g) {
        for (int i = 0; i < boardContents.length; i++) {
            for (int j = 0; j < boardContents[i].length; j++) {
                if (boardContents[i][j] == I_PIECE) {
                    g.setColor(new Color(66, 188, 245));
                    int x1 = (int) (x + j * TILE_SIZE) + 1;
                    int y1 = (int) (y + i * TILE_SIZE) + 1;
                    g.fillRect(x1, y1, (int) TILE_SIZE - 1, (int) TILE_SIZE - 1);
                }
            }
        }
    }

    public void clearLine() {
        for (int i = 0; i < boardContents.length; i++) {
            boolean clearCondition = true;
            for (int j = 0; j < boardContents[i].length; j++) {
                if (boardContents[i][j] == 0) {
                    clearCondition = false;
                }
            }
            if (clearCondition) {
                Arrays.fill(boardContents[i], 0);
            }
        }
    }

    public void updateTile(int xTile, int yTile, int pieceType) {
        boardContents[yTile][xTile] = pieceType;
    }

    public void update() {
    }

    public void draw(Graphics g) {
//        g.setColor(Color.BLACK);
//        g.fillRect(x, y, width, height);
        drawBoardContents(g);
        drawGrid(g);
    }

    public int[][] getBoardContents() {
        return boardContents;
    }

}
