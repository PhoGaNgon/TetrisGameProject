package util;

import java.awt.*;

import static util.Constants.GAME_SCALE;
import static util.Constants.TetrominoConstants.*;

public class HelpMethods {

    public static void DrawMiniTetrimino(Graphics g, int x, int y, int pieceType) {
        if (pieceType != 0) {
            int[][][] formations = GetFormations(pieceType);
            g.setColor(GetPieceColor(pieceType));

            for (int minoIndex = 0; minoIndex < 4; minoIndex++) {
                int minoSize = (int) (7 * GAME_SCALE);
                int xTile = formations[0][minoIndex][0] * minoSize + x;
                int yTile = formations[0][minoIndex][1] * minoSize + y;
                g.fillRect(xTile, yTile, minoSize - 1, minoSize - 1);
            }
        }
    }

}
