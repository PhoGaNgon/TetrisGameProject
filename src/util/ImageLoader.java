package util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class ImageLoader {

    public static final String MENU_BACKGROUND = "menu_background.png";
    public static final String MENU_BUTTON = "menu_button.png";
    public static final String PLAYING_FIELD_OUTLINE = "playing_field_outline.png";
    public static final String PLAYING_BACKGROUND = "playing_background.png";

    public static BufferedImage GetImage(String fileName) {
        BufferedImage img = null;
        InputStream is = ImageLoader.class.getResourceAsStream("/" + fileName);

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return img;

    }

}
