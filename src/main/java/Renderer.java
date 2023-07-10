import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Renderer {
    private static final Logger LOG = LoggerContext.getContext().getLogger(Renderer.class);
    private static final char[] BRIGHTNESS_SCALE = {
            ' ', '\'', '.', '`', '^', '"', ',', ':', ';', 'I', 'l', '!', 'i', '>', '<', '~', '+', '_', '-', '?', ']',
            '[', '}', '{', '1', ')', '(', '|', '\\', '/', 't', 'f', 'j', 'r', 'x', 'n', 'u', 'v', 'c', 'z', 'X', 'Y',
            'U', 'J', 'C', 'L', 'Q', '0', 'O', 'Z', 'm', 'w', 'q', 'p', 'd', 'b', 'k', 'h', 'a', 'o', '*', '#', 'M', 'W',
            '&', '8', '%', 'B', '@', '$'
    };

    // TODO: 2023-07-09 Use a better brightness scale that uses those squares so it looks more solid

    public static char getChar(float brightness) {
        int index = (int) (brightness * (BRIGHTNESS_SCALE.length - 1));
        if (index < 0) index = 0; else if (index >= BRIGHTNESS_SCALE.length) index = BRIGHTNESS_SCALE.length - 1;
        return BRIGHTNESS_SCALE[index];
    }

    public static String renderImage(File file) {
        try {
            LOG.info("Rendering image at '{}'", file);
            BufferedImage image = ImageIO.read(file);
            int width = image.getWidth();
            int height = image.getHeight();

            StringBuilder result = new StringBuilder();
            result.append("<html>");

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    Color pixel = new Color(image.getRGB(x, y));
                    float brightness = (pixel.getRed() + pixel.getGreen() + pixel.getBlue()) / 3f / 255f;

                    result.append(getChar(brightness));
                }
                result.append("<br>");
            }
            result.append("</html>");

            LOG.info("Successfully rendered '{}'", file.getName());
            return result.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static String renderScreen(Screen screen) {
        StringBuilder result = new StringBuilder();
        result.append("<html>");

        for (int y = 0; y < screen.size().height; y++) {
            for (int x = 0; x < screen.size().width; x++) {
                result.append(getChar(screen.getBrightnessAt(x, y)));
            }
            result.append("<br>");
        }

        result.append("</html>");
        return result.toString();
    }
}