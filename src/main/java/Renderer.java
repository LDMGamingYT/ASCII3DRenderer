import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Renderer {
    // TODO: 2023-07-09 Use a better brightness scale that uses those squares so it looks more solid
    protected static final char[] BRIGHTNESS_SCALE = {
            ' ', '\'', '.', '`', '^', '"', ',', ':', ';', 'I', 'l', '!', 'i', '>', '<', '~', '+', '_', '-', '?', ']',
            '[', '}', '{', '1', ')', '(', '|', '\\', '/', 't', 'f', 'j', 'r', 'x', 'n', 'u', 'v', 'c', 'z', 'X', 'Y',
            'U', 'J', 'C', 'L', 'Q', '0', 'O', 'Z', 'm', 'w', 'q', 'p', 'd', 'b', 'k', 'h', 'a', 'o', '*', '#', 'M', 'W',
            '&', '8', '%', 'B', '@', '$'
    };

    private static final Logger LOG = LoggerContext.getContext().getLogger(Renderer.class);
    
    public static String renderScreen(Screen screen) {
        StringBuilder result = new StringBuilder();
        result.append("<html>");

        for (int y = 0; y < screen.size().height; y++) {
            for (int x = 0; x < screen.size().width; x++) {
                result.append(screen.getPixelAt(new Vector2(x, y)).toAscii());
            }
            result.append("<br>");
        }

        result.append("</html>");
        return result.toString();
    }

    public static void renderScreenToFile(Screen screen) {
        StringBuilder result = new StringBuilder();

        for (int y = 0; y < screen.size().height; y++) {
            for (int x = 0; x < screen.size().width; x++) {
                result.append(screen.getPixelAt(new Vector2(x, y)).toAscii());
            }
            result.append('\n');
        }

        File out = new File("out/screen.txt");
        try (FileWriter writer = new FileWriter(out)) {
            out.createNewFile();

            writer.write(
                    result +
                            "-".repeat(Math.max(0, screen.size().width)) +
                            "\nOutput of rendered screen with size " +
                            "(" + screen.size().width + ", " + screen.size().height + ")"
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public char getChar(float brightness) {
        int index = (int) (brightness * (Renderer.BRIGHTNESS_SCALE.length - 1));
        if (index < 0) index = 0;
        else if (index >= Renderer.BRIGHTNESS_SCALE.length) index = Renderer.BRIGHTNESS_SCALE.length - 1;
        return Renderer.BRIGHTNESS_SCALE[index];
    }
}