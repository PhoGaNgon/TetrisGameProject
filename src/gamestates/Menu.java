package gamestates;

import main.Game;

import static util.Constants.GAME_SCALE;
import static util.Constants.MenuConstants.*;

import util.ImageLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static util.Constants.GameConstants.*;

public class Menu implements GamestateMethods {

    private Game game;
    private BufferedImage menuBackground;
    private int backgroundWidth, backgroundHeight;

    public Menu(Game game) {
        this.game = game;
        loadBackground();
    }

    private void loadBackground() {
        menuBackground = ImageLoader.GetImage(ImageLoader.MENU_BACKGROUND);
        backgroundWidth = (int) (menuBackground.getWidth() * GAME_SCALE);
        backgroundHeight = (int) (menuBackground.getHeight() * GAME_SCALE);
    }

    public void update() {

    }

    public void render(Graphics g) {
        g.fillRect(0, 0, GAME_SIZE_WIDTH, GAME_SIZE_HEIGHT);
        g.drawImage(menuBackground, GAME_SIZE_WIDTH / 2 - (backgroundWidth / 2), (int) (30 * GAME_SCALE), backgroundWidth, backgroundHeight, null);

    }

}
