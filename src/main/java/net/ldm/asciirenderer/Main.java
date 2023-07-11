package net.ldm.asciirenderer;

import net.ldm.asciirenderer.renderer.Renderer;
import net.ldm.asciirenderer.renderer.Screen;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.util.Arrays;

public class Main {
    private static final Logger LOG = LoggerContext.getContext().getLogger(Main.class);
    public static final int FRAME_RATE = 60;

    public static void main(String[] args) {
        boolean renderToFile = Arrays.asList(args).contains("--rendertofile");

        if (!renderToFile) UI.initialize();
        if (renderToFile) LOG.info("Skipping UI initialization (file render only)");

        // TODO: 2023-07-11 Make sure this works on all resolutions.
        //                  last tested with Screen size is 1536x864, setting window size to 768x432
        Screen screen = new Screen(64, 16);

        //screen.drawVerticalLine(new Vector2(3, 5), -3, new Pixel(1f));

        new Thread(() -> {
            if (!renderToFile) {
                while (true) Renderer.update(screen);
            } else {
                Renderer.renderScreenToFile(screen);
                LOG.info("Frame 1 of screen has been rendered to file.");
            }
        }, "Render-Thread").start();
    }
}