package ui;

import gamestates.Gamestates;
import util.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

import static util.Constants.MenuConstants.*;

public class MenuButton extends CustomButton {

    private Rectangle bounds;
    private BufferedImage[] imgs;
    private boolean mouseOver, mousePressed;
    private Gamestates state;
    private int bIndex; // For which state of the button to display

    public MenuButton(int x, int y, int rowIndex, Gamestates state) {
        super(x, y, BUTTON_WIDTH, BUTTON_HEIGHT);
        bounds = new Rectangle(x - BUTTON_WIDTH / 2, y, BUTTON_WIDTH, BUTTON_HEIGHT);
        this.state = state;
        loadImgs(rowIndex);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    private void loadImgs(int rowIndex) {
        imgs = new BufferedImage[3];
        BufferedImage temp = ImageLoader.GetImage(ImageLoader.MENU_BUTTON);

        for (int i = 0; i < imgs.length; i++) {
            imgs[i] = temp.getSubimage(i * BUTTON_WIDTH_DEFAULT, rowIndex * BUTTON_HEIGHT_DEFAULT, BUTTON_WIDTH_DEFAULT, BUTTON_HEIGHT_DEFAULT);
        }
    }

    public void update() {
        bIndex = 0;
        if (mouseOver) {
            bIndex = 1;
        }
        if (mousePressed)
            bIndex = 2;
    }

    public void draw(Graphics g) {
        g.drawImage(imgs[bIndex], bounds.x, bounds.y, BUTTON_WIDTH, BUTTON_HEIGHT, null);
    }

    public void applyGamestate() {
        Gamestates.gamestate = state;
        if (state == Gamestates.QUIT)
            System.exit(0);
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public void reset() {
        mouseOver = false;
        mousePressed = false;
    }
}
