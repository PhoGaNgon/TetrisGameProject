package gamestates;

import java.awt.*;

import static util.Constants.GameConstants.*;

public class Options implements GamestateMethods{

    public Options() {
        System.out.println("Options loaded!");
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, GAME_SIZE_WIDTH, GAME_SIZE_HEIGHT);
    }
}
