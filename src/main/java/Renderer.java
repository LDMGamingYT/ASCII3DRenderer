public class Renderer {
    private static final char[] BRIGHTNESS_SCALE = {'$', '@', 'B', '%', '8', '&', 'W', 'M', '#', '*', 'o', 'a', 'h', 'k', 'b', 'd', 'p', 'q', 'w', 'm', 'Z', 'O', '0', 'Q', 'L', 'C', 'J', 'U', 'Y', 'X', 'z', 'c', 'v', 'u', 'n', 'x', 'r', 'j', 'f', 't', '/', '\\', '|', '(', ')', '1', '{', '}', '[', ']', '?', '-', '_', '+', '~', '<', '>', 'i', '!', 'l', 'I', ';', ':', ',', '"', '^', '`', '\'', '.', ' '};
    // TODO: 2023-07-09 Use a better brightness scale that uses those squares so it looks more solid

    public static char getChar(float brightness) {
        int index = (int) (brightness * (BRIGHTNESS_SCALE.length - 1));
        if (index < 0) index = 0; else if (index >= BRIGHTNESS_SCALE.length) index = BRIGHTNESS_SCALE.length - 1;
        return BRIGHTNESS_SCALE[index];
    }
}