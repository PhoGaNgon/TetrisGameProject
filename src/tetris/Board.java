package tetris;

import java.awt.*;
import java.util.Arrays;

import static util.Constants.BoardConstants.*;
import static util.Constants.TetrominoConstants.*;

public class Board {

    private int x, y;
    private int gridY; // The actual y-value for the grid itself
    private int[][] boardContents;

    public Board(int x, int y) {
        this.x = x;
        this.gridY = y;
        this.y = (int) (y - 4 * TILE_SIZE);
        initBoardContents();
    }

    private void initBoardContents() {
        boardContents = new int[NUM_ROWS][NUM_COLS];
        for (int[] i : boardContents) {
            Arrays.fill(i, 0);
        }

        // Test
//        boardContents = new int[][]{
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {1, 0, 0, 1, 1, 0, 0, 0, 0, 0},
//                {1, 0, 0, 0, 1, 0, 0, 0, 0, 0},
//                {1, 1, 0, 1, 1, 0, 0, 0, 0, 0}
//        };
    }

    public void drawGrid(Graphics g) {
        g.setColor(Color.DARK_GRAY);
        for (int i = 0; i <= NUM_VISIBLE_ROWS; i++) {
            // Draw row lines
            int y1 = (int) (gridY + i * TILE_SIZE);
            g.drawLine(x, y1, x + BOARD_WIDTH, y1);

            for (int j = 0; j <= NUM_COLS; j++) {
                // Draw column lines
                int x1 = (int) (x + j * TILE_SIZE);
                g.drawLine(x1, gridY, x1, gridY + BOARD_HEIGHT);
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

    // Clears all filled rows in the board and returns how many were cleared.
    public int clearRows() {
        int rowsCleared = 0;

        for (int rowIndex = 0; rowIndex < boardContents.length; rowIndex++) {
            boolean clearCondition = true;
            for (int colIndex = 0; colIndex < boardContents[rowIndex].length; colIndex++) {
                if (boardContents[rowIndex][colIndex] == 0) {
                    clearCondition = false;
                }
            }
            if (clearCondition) {
                for (int j = rowIndex; j > 0; j--) { // Shift all above rows downwards
                    boardContents[j] = Arrays.copyOf(boardContents[j - 1], boardContents[j - 1].length);
                }
                Arrays.fill(boardContents[0], 0);
                rowsCleared++;
            }
        }

        return rowsCleared;
    }

    public void draw(Graphics g) {
        drawBoardContents(g);
        drawGrid(g);
    }

    public int[][] getBoardContents() {
        return boardContents;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
