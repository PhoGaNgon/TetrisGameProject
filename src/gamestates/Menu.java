package gamestates;

import main.Game;

import static util.Constants.GAME_SCALE;

import ui.CustomButton;
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
    private MenuButton startButton, quitButton;

    public Menu(Game game) {
        this.game = game;
        loadBackground();
        createButtons();
    }

    private void createButtons() {
        startButton = new MenuButton(GAME_SIZE_WIDTH / 2, (int) (220 * GAME_SCALE), 0, Gamestates.PLAYING);
        quitButton = new MenuButton(GAME_SIZE_WIDTH / 2, (int) (350 * GAME_SCALE), 1, Gamestates.QUIT);
    }

    private void loadBackground() {
        menuBackground = ImageLoader.GetImage(ImageLoader.MENU_BACKGROUND);
        backgroundWidth = (int) (menuBackground.getWidth() * GAME_SCALE);
        backgroundHeight = (int) (menuBackground.getHeight() * GAME_SCALE);
    }

    public void update() {
        startButton.update();
        quitButton.update();
    }

    public void draw(Graphics g) {
        g.fillRect(0, 0, GAME_SIZE_WIDTH, GAME_SIZE_HEIGHT);
        g.drawImage(menuBackground, GAME_SIZE_WIDTH / 2 - (backgroundWidth / 2), (int) (30 * GAME_SCALE), backgroundWidth, backgroundHeight, null);

        startButton.draw(g);
        quitButton.draw(g);
    }

    public void mouseMoved(MouseEvent e) {
        startButton.setMouseOver(false);
        quitButton.setMouseOver(false);

        if (isIn(startButton, e)) {
            startButton.setMouseOver(true);
        } else if (isIn(quitButton, e)) {
            quitButton.setMouseOver(true);
        }
    }

    private boolean isIn(CustomButton button, MouseEvent e) {
        return button.getBounds().contains(e.getX(), e.getY());
    }

    public void mousePressed(MouseEvent e) {
        if (isIn(startButton, e)) {
            startButton.setMousePressed(true);
        } else if (isIn(quitButton, e)) {
            quitButton.setMousePressed(true);
        }
    }

    public void mouseReleased(MouseEvent e) {
        if (isIn(startButton, e)) {
            if (startButton.isMousePressed()) {
                startButton.applyGamestate();
            }
        } else if (isIn(quitButton, e)) {
            if (quitButton.isMousePressed()) {
                quitButton.applyGamestate();
            }
        }
        startButton.reset();
        quitButton.reset();
    }

}
