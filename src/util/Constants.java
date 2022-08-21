package util;

import java.awt.*;

public class Constants {

    public static final float GAME_SCALE = 2.5f; // Scale of game
    public static final int FPS_CAP = 100; // Max number of frames per second
    public static final int UPS_CAP = 120; // Max number of updates per second

    public static class GameConstants {
        public static final int GAME_SIZE_WIDTH = (int) (256 * GAME_SCALE);
        public static final int GAME_SIZE_HEIGHT = (int) (352 * GAME_SCALE);
    }

    // Includes score, next pieces container, and hold piece container
    public static class PlayingConstants {
        public static final int NEXT_PIECES_CONTAINER_WIDTH_DEFAULT = 38;
        public static final int NEXT_PIECES_CONTAINER_HEIGHT_DEFAULT = 98;
        public static final int NEXT_PIECES_CONTAINER_WIDTH = (int) (NEXT_PIECES_CONTAINER_WIDTH_DEFAULT * GAME_SCALE * 1.3);
        public static final int NEXT_PIECES_CONTAINER_HEIGHT = (int) (NEXT_PIECES_CONTAINER_HEIGHT_DEFAULT * GAME_SCALE * 1.3);
        public static final int HOLD_PIECE_CONTAINER_WIDTH_DEFAULT = 38;
        public static final int HOLD_PIECE_CONTAINER_HEIGHT_DEFAULT = 34;
        public static final int HOLD_PIECE_CONTAINER_WIDTH = (int) (HOLD_PIECE_CONTAINER_WIDTH_DEFAULT * GAME_SCALE * 1.3);
        public static final int HOLD_PIECE_CONTAINER_HEIGHT = (int) (HOLD_PIECE_CONTAINER_HEIGHT_DEFAULT * GAME_SCALE * 1.3);
    }

    public static class BoardConstants {
        public static final float TILE_SIZE = 12 * GAME_SCALE;
        public static final int PIECE_TILE_SIZE = (int) (TILE_SIZE - 1);
        public static final int NUM_COLS = 10;
        public static final int NUM_ROWS = 24;
        public static final int NUM_VISIBLE_ROWS = 20;
        public static final int BOARD_BOARDER_WIDTH = (int) (132 * GAME_SCALE);
        public static final int BOARD_BOARDER_HEIGHT = (int) (256 * GAME_SCALE);
        public static final int BOARD_WIDTH = (int) (NUM_COLS * TILE_SIZE);
        public static final int BOARD_HEIGHT = (int) (NUM_VISIBLE_ROWS * TILE_SIZE);
        public static final int BOARD_OFFSET_FROM_BORDER_X = (int) (6 * GAME_SCALE);
        public static final int BOARD_OFFSET_FROM_BORDER_Y = (int) (8 * GAME_SCALE);
    }

    public static class TetrominoConstants {
        public static final int I_PIECE = 1;
        public static final int J_PIECE = 2;
        public static final int L_PIECE = 3;
        public static final int O_PIECE = 4;
        public static final int S_PIECE = 5;
        public static final int T_PIECE = 6;
        public static final int Z_PIECE = 7;

        // Returns the color of each piece type
        public static Color GetPieceColor(int pieceType) {
            return switch (pieceType) {
                case I_PIECE -> new Color(66, 188, 245);
                case J_PIECE -> new Color(66, 96, 245);
                case L_PIECE -> new Color(245, 155, 66);
                case O_PIECE -> new Color(245, 224, 66);
                case S_PIECE -> new Color(49, 214, 52);
                case T_PIECE -> new Color(112, 49, 214);
                case Z_PIECE -> new Color(214, 49, 49);
                default -> Color.BLACK;
            };
        }

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
        public static final int BUTTON_WIDTH_DEFAULT = 96;
        public static final int BUTTON_HEIGHT_DEFAULT = 32;
        public static final int BUTTON_WIDTH = (int) (BUTTON_WIDTH_DEFAULT * GAME_SCALE);
        public static final int BUTTON_HEIGHT = (int) (BUTTON_HEIGHT_DEFAULT * GAME_SCALE);
    }

}
