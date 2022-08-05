package util;

public class Constants {

    public static final float GAME_SCALE = 2.8f; // Scale of game

    public static class GameConstants {
        public static final int GAME_SIZE_WIDTH_DEFAULT = 256;
        public static final int GAME_SIZE_HEIGHT_DEFAULT = 352;
        public static final int GAME_SIZE_WIDTH = (int) (GAME_SIZE_WIDTH_DEFAULT * GAME_SCALE);
        public static final int GAME_SIZE_HEIGHT = (int) (GAME_SIZE_HEIGHT_DEFAULT * GAME_SCALE);
    }

    public static class MenuConstants {
        public static final int BUTTON_WIDTH_DEFAULT = 96;
        public static final int BUTTON_HEIGHT_DEFAULT = 32;
        public static final int BUTTON_WIDTH = (int) (BUTTON_WIDTH_DEFAULT * GAME_SCALE);
        public static final int BUTTON_HEIGHT = (int) (BUTTON_HEIGHT_DEFAULT * GAME_SCALE);
    }

    public static class BoardConstants {
        public static final float TILES_SIZE = 12 * GAME_SCALE;
        public static final int NUM_TILES_WIDTH = 10;
        public static final int NUM_TILES_HEIGHT = 20;
        public static final int BOARD_BORDER_WIDTH = (int) (132 * GAME_SCALE);
        public static final int BOARD_BORDER_HEIGHT = (int) (256 * GAME_SCALE);
        public static final int BOARD_WIDTH = (int) (NUM_TILES_WIDTH * TILES_SIZE);
        public static final int BOARD_HEIGHT = (int) (NUM_TILES_HEIGHT * TILES_SIZE);
    }

}
