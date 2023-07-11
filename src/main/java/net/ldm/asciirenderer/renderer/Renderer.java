package net.ldm.asciirenderer.renderer;

import net.ldm.asciirenderer.Main;
import net.ldm.asciirenderer.UI;
import net.ldm.asciirenderer.Vector2;
import net.ldm.asciirenderer.core.exception.PixelOutOfBoundsException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Renderer {
    private static int frame = 0;
    protected static final char[] BRIGHTNESS_SCALE = {
            '\u00a0', '\'', '.', '`', '^', '"', ',', ':', ';', 'I', 'l', '!', 'i', '>', '<', '~', '+', '_', '-', '?', ']',
            '[', '}', '{', '1', ')', '(', '|', '\\', '/', 't', 'f', 'j', 'r', 'x', 'n', 'u', 'v', 'c', 'z', 'X', 'Y',
            'U', 'J', 'C', 'L', 'Q', '0', 'O', 'Z', 'm', 'w', 'q', 'p', 'd', 'b', 'k', 'h', 'a', 'o', '*', '#', 'M', 'W',
            '&', '8', '%', 'B', '@'
    };

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

    @SuppressWarnings("ResultOfMethodCallIgnored")
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
            out.getParentFile().mkdirs();
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

    // Update is called every frame
    public static void update(Screen screen) {
        screen.clear();
        try {
            screen.drawPixel(new Vector2(frame, 5), new Pixel(1f));
        } catch (PixelOutOfBoundsException ignored) {
            frame = 0;
        }
        UI.set(renderScreen(screen));
        frame++;
        try {
            Thread.sleep(1000 / Main.FRAME_RATE);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}