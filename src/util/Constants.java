package util;

public class Constants {

    public static final float GAME_SCALE = 1f; // Scale of game

    public static class GameConstants {
        public static final int GAME_SIZE_WIDTH = (int) (640 * GAME_SCALE);
        public static final int GAME_SIZE_HEIGHT = (int) (880 * GAME_SCALE);
    }

    public static class BoardConstants {
        public static final float TILE_SIZE = 30 * GAME_SCALE;
        public static final int PIECE_TILE_SIZE = (int) (TILE_SIZE - 1);
        public static final int NUM_COLS = 10;
        public static final int NUM_ROWS = 20;
        public static final int BOARD_WIDTH = (int) (NUM_COLS * TILE_SIZE);
        public static final int BOARD_HEIGHT = (int) (NUM_ROWS * TILE_SIZE);
        public static final int BOARD_OFFSET_FROM_BORDER_X = (int) (15 * GAME_SCALE);
        public static final int BOARD_OFFSET_FROM_BORDER_Y = (int) (20 * GAME_SCALE);
    }

    public static class TetrominoConstants {
        public static final int I_PIECE = 1;
        public static final int J_PIECE = 2;
        public static final int L_PIECE = 3;
        public static final int O_PIECE = 4;
        public static final int S_PIECE = 5;
        public static final int T_PIECE = 6;
        public static final int Z_PIECE = 7;

        /* Contains the formations for all tetrimino pieces and their rotations
            Based on Tetris SRS rotations.
         */
        public static int[][][] GetFormations(int pieceType) {
            return switch (pieceType) {
                case I_PIECE -> new int[][][]{{{0, -2}, {1, -2}, {2, -2}, {3, -2}},
                        {{2, -3}, {2, -2}, {2, -1}, {2, 0}},
                        {{3, -1}, {2, -1}, {1, -1}, {0, -1}},
                        {{1, 0}, {1, -1}, {1, -2}, {1, -3}}};
                case J_PIECE -> new int[][][]{{{0, -2}, {0, -1}, {1, -1}, {2, -1}},
                        {{2, -2}, {1, -2}, {1, -1}, {1, 0}},
                        {{2, 0}, {2, -1}, {1, -1}, {0, -1}},
                        {{0, 0}, {1, 0}, {1, -1}, {1, -2}}};
                case L_PIECE -> new int[][][]{{{0, -1}, {1, -1}, {2, -1}, {2, -2}},
                        {{1, -2}, {1, -1}, {1, 0}, {2, 0}},
                        {{2, -1}, {1, -1}, {0, -1}, {0, 0}},
                        {{1, 0}, {1, -1}, {1, -2}, {0, -2}}};
                case O_PIECE -> new int[][][]{{{1, -2}, {1, -1}, {2, -2}, {2, -1}},
                        {{1, -2}, {1, -1}, {2, -2}, {2, -1}},
                        {{1, -2}, {1, -1}, {2, -2}, {2, -1}},
                        {{1, -2}, {1, -1}, {2, -2}, {2, -1}}};
                case S_PIECE -> new int[][][]{{{2, -2}, {1, -2}, {1, -1}, {0, -1}},
                        {{1, -2}, {1, -1}, {2, -1}, {2, 0}},
                        {{0, 0}, {1, 0}, {1, -1}, {2, -1}},
                        {{1, 0}, {1, -1}, {0, -1}, {0, -2}}};
                case T_PIECE -> new int[][][]{{{0, -1}, {1, -1}, {2, -1}, {1, -2}},
                        {{1, -2}, {1, -1}, {1, 0}, {2, -1}},
                        {{2, -1}, {1, -1}, {0, -1}, {1, 0}},
                        {{1, 0}, {1, -1}, {1, -2}, {0, -1}}};
                case Z_PIECE -> new int[][][]{{{0, -2}, {1, -2}, {1, -1}, {2, -1}},
                        {{2, -2}, {2, -1}, {1, -1}, {1, 0}},
                        {{2, 0}, {1, 0}, {1, -1}, {0, -1}},
                        {{0, 0}, {0, -1}, {1, -1}, {1, -2}}};
                default -> throw new IllegalStateException("Unexpected piece type: " + pieceType);
            };
        }

        /* Returns the table for wall-kick data
         Based on the Tetris SRS data; it determines all the possible alternative positions
         to check to fit a rotated piece before declaring it invalid.
        */
        public static int[][][] GetWallKickData(int pieceType) {
            return switch (pieceType) {
                case 1 -> new int[][][]{
                        {{0, 0}, {-2, 0}, {1, 0}, {-2, -1}, {1, 2}},
                        {{0, 0}, {2, 0}, {-1, 0}, {2, 1}, {-1, -2}},
                        {{0, 0}, {-1, 0}, {2, 0}, {-1, 2}, {2, -1}},
                        {{0, 0}, {1, 0}, {-2, 0}, {1, -2}, {-2, 1}},
                        {{0, 0}, {2, 0}, {-1, 0}, {2, 1}, {-1, -2}},
                        {{0, 0}, {-2, 0}, {1, 0}, {-2, -1}, {1, 2}},
                        {{0, 0}, {1, 0}, {-2, 0}, {1, -2}, {-2, 1}},
                        {{0, 0}, {-1, 0}, {2, 0}, {-1, 2}, {2, -1}}
                };
                case 2, 3, 5, 6, 7 -> new int[][][]{
                        {{0, 0}, {-1, 0}, {-1, 1}, {0, -2}, {-1, -2}},
                        {{0, 0}, {1, 0}, {1, -1}, {0, 2}, {1, 2}},
                        {{0, 0}, {1, 0}, {1, -1}, {0, 2}, {1, 2}},
                        {{0, 0}, {-1, 0}, {-1, 1}, {0, -2}, {-1, -2}},
                        {{0, 0}, {1, 0}, {1, 1}, {0, -2}, {1, -2}},
                        {{0, 0}, {-1, 0}, {-1, -1}, {0, 2}, {-1, 2}},
                        {{0, 0}, {-1, 0}, {-1, -1}, {0, 2}, {-1, 2}},
                        {{0, 0}, {1, 0}, {1, 1}, {0, -2}, {1, -2}},
                };
                default -> null;
            };
        }

    }

    public static class MenuConstants {
        public static final int BUTTON_WIDTH_DEFAULT = 240;
        public static final int BUTTON_HEIGHT_DEFAULT = 80;
        public static final int BUTTON_WIDTH = (int) (BUTTON_WIDTH_DEFAULT * GAME_SCALE);
        public static final int BUTTON_HEIGHT = (int) (BUTTON_HEIGHT_DEFAULT * GAME_SCALE);
    }

}
