package ui;

import java.awt.*;


public abstract class CustomButton {

    Rectangle bounds;
    int x, y, width, height;

    public CustomButton(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        createBounds(x, y, width, height);
    }

    private void createBounds(int x, int y, int width, int height) {
        bounds = new Rectangle(x, y, width, height);
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
