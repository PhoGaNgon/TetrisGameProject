package gamestates;

import util.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

import static util.Constants.PlayingConstants.*;
import static util.Constants.GameConstants.*;
import static util.Constants.GAME_SCALE;

public class Playing implements GamestateMethods {

    private BufferedImage background, fieldOutline;
    private int fieldOutlineX, fieldOutlineY;
    private Rectangle fieldBounds;

    public Playing() {
        System.out.println("Playing created");
        background = ImageLoader.GetImage(ImageLoader.PLAYING_BACKGROUND);
        loadFieldOutline();
        createFieldBounds();
    }

    private void loadFieldOutline() {
        fieldOutline = ImageLoader.GetImage(ImageLoader.PLAYING_FIELD_OUTLINE);
        fieldOutlineX = (GAME_SIZE_WIDTH / 2) - (FIELD_OUTLINE_WIDTH / 2);
        fieldOutlineY = (int) (50 * GAME_SCALE);
    }

    private void createFieldBounds() {
        fieldBounds = new Rectangle(0, 0, FIELD_BOUNDS_WIDTH, FIELD_BOUNDS_HEIGHT);
        fieldBounds.x = (int) (fieldOutlineX + 6 * GAME_SCALE); // Offset btwn bounds and outline X is 6 px
        fieldBounds.y = (int) (fieldOutlineY + 8 * GAME_SCALE); // Offset btwn bounds and outline Y is 8 px
    }

    public void update() {

    }

    public void render(Graphics g) {
        g.drawImage(background, 0, 0, GAME_SIZE_WIDTH, GAME_SIZE_HEIGHT, null);
        g.drawImage(fieldOutline, fieldOutlineX, fieldOutlineY, FIELD_OUTLINE_WIDTH, FIELD_OUTLINE_HEIGHT, null);
        //g.setColor(Color.lightGray);
        //g.fillRect(fieldBounds.x, fieldBounds.y, fieldBounds.width, fieldBounds.height);
    }

}
