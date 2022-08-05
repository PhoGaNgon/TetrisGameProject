package gamestates;

import main.Game;

import static util.Constants.GAME_SCALE;

import ui.MenuButton;
import util.ImageLoader;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static util.Constants.GameConstants.*;

public class Menu implements GamestateMethods {

    private Game game;
    private BufferedImage menuBackground;
    private int backgroundWidth, backgroundHeight;
    private MenuButton[] buttons;

    public Menu(Game game) {
        this.game = game;
        loadBackground();
        createButtons();
    }

    private void createButtons() {
        buttons = new MenuButton[3];
        buttons[0] = new MenuButton(GAME_SIZE_WIDTH / 2, (int) (110 * GAME_SCALE), 0, Gamestates.PLAYING);
        buttons[1] = new MenuButton(GAME_SIZE_WIDTH / 2, (int) (160 * GAME_SCALE), 1, Gamestates.OPTIONS);
        buttons[2] = new MenuButton(GAME_SIZE_WIDTH / 2, (int) (210 * GAME_SCALE), 2, Gamestates.QUIT);
    }

    private void loadBackground() {
        menuBackground = ImageLoader.GetImage(ImageLoader.MENU_BACKGROUND);
        backgroundWidth = (int) (menuBackground.getWidth() * GAME_SCALE);
        backgroundHeight = (int) (menuBackground.getHeight() * GAME_SCALE);
    }

    public void update() {
        for (MenuButton b : buttons)
            b.update();
    }

    public void render(Graphics g) {
        g.fillRect(0, 0, GAME_SIZE_WIDTH, GAME_SIZE_HEIGHT);
        g.drawImage(menuBackground, GAME_SIZE_WIDTH / 2 - (backgroundWidth / 2), (int) (30 * GAME_SCALE), backgroundWidth, backgroundHeight, null);

        for (MenuButton b : buttons)
            b.render(g);
    }

    public void mouseMoved(MouseEvent e) {
        for (MenuButton b : buttons) {
            b.setMouseOver(false);
            if (b.getBounds().contains(e.getX(), e.getY())) {
                b.setMouseOver(true);
            }
        }
    }

    public void mousePressed(MouseEvent e) {
        for (MenuButton b : buttons) {
            if (b.getBounds().contains(e.getX(), e.getY()))
                b.setMousePressed(true);
        }
    }

    public void mouseReleased(MouseEvent e) {
        for (MenuButton b : buttons) {
            if (b.getBounds().contains(e.getX(), e.getY())) {
                if (b.isMousePressed()) {
                    b.applyGamestate();
                }
            }
            b.reset();
        }
    }

}
