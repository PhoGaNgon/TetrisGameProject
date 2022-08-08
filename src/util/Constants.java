package util;

public class Constants {

    public static final float GAME_SCALE = 2.75f; // Scale of game

    public static class GameConstants {
        public static final int GAME_SIZE_WIDTH_DEFAULT = 256;
        public static final int GAME_SIZE_HEIGHT_DEFAULT = 352;
        public static final int GAME_SIZE_WIDTH = (int) (GAME_SIZE_WIDTH_DEFAULT * GAME_SCALE);
        public static final int GAME_SIZE_HEIGHT = (int) (GAME_SIZE_HEIGHT_DEFAULT * GAME_SCALE);
    }

    public static class BoardConstants {
        public static final float TILE_SIZE = 12 * GAME_SCALE;
        public static final int PIECE_TILE_SIZE = (int) (TILE_SIZE - 1);
        public static final int NUM_COLS = 10;
        public static final int NUM_ROWS = 20;
        public static final int BOARD_BORDER_WIDTH = (int) (132 * GAME_SCALE);
        public static final int BOARD_BORDER_HEIGHT = (int) (256 * GAME_SCALE);
        public static final int BOARD_WIDTH = (int) (NUM_COLS * TILE_SIZE);
        public static final int BOARD_HEIGHT = (int) (NUM_ROWS * TILE_SIZE);
    }

    public static class TetrominoConstants {
        public static final int I_PIECE = 1;
        public static final int J_PIECE = 2;
        public static final int L_PIECE = 3;
        public static final int O_PIECE = 4;
        public static final int S_PIECE = 5;
        public static final int T_PIECE = 6;
        public static final int Z_PIECE = 7;

        public static final int[][] I_FORMATION = {{1, 0}, {1, -1}, {1, -2}, {1, -3}};
    }

    public static class MenuConstants {
        public static final int BUTTON_WIDTH_DEFAULT = 96;
        public static final int BUTTON_HEIGHT_DEFAULT = 32;
        public static final int BUTTON_WIDTH = (int) (BUTTON_WIDTH_DEFAULT * GAME_SCALE);
        public static final int BUTTON_HEIGHT = (int) (BUTTON_HEIGHT_DEFAULT * GAME_SCALE);
    }

}
