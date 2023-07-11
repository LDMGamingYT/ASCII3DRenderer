import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

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

    public char getChar(float brightness) {
        int index = (int) (brightness * (Renderer.BRIGHTNESS_SCALE.length - 1));
        if (index < 0) index = 0;
        else if (index >= Renderer.BRIGHTNESS_SCALE.length) index = Renderer.BRIGHTNESS_SCALE.length - 1;
        return Renderer.BRIGHTNESS_SCALE[index];
    }
}