package util;

public class Constants {

    public static final float GAME_SCALE = 2.0f; // Scale of game

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

}
