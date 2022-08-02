package gamestates;

import java.awt.*;
import static util.Constants.GameConstants.*;

public class Playing implements GamestateMethods {

    public Playing() {
        System.out.println("Playing created");
    }

    public void update() {

    }

    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, GAME_SIZE_WIDTH, GAME_SIZE_HEIGHT);
    }

}
