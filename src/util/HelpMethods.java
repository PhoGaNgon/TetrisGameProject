package util;

import java.awt.*;

import static util.Constants.GAME_SCALE;
import static util.Constants.TetrominoConstants.*;

public class HelpMethods {

    public static void DrawMiniTetrimino(Graphics g, int x, int y, int pieceType) {
        if (pieceType != 0) {
            int[][][] formations = GetFormations(pieceType);
            int minoSize = (int) (7 * GAME_SCALE);

            if (pieceType == I_PIECE) {
                x -= minoSize / 2;
                y += minoSize / 2;
            } else if (pieceType == O_PIECE) {
                x -= minoSize / 2;
            }

            for (int minoIndex = 0; minoIndex < 4; minoIndex++) {
                int xTile = formations[0][minoIndex][0] * minoSize + x;
                int yTile = formations[0][minoIndex][1] * minoSize + y;
                g.setColor(Color.BLACK);
                g.fillRect(xTile - 1, yTile - 1, minoSize + 1, minoSize + 1);
                g.setColor(GetPieceColor(pieceType));
                g.fillRect(xTile, yTile, minoSize - 1, minoSize - 1);
            }
        }
    }

}
