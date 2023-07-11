import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.util.Arrays;

public class Main {
    private static final Logger LOG = LoggerContext.getContext().getLogger(Main.class);

    public static void main(String[] args) {
        boolean renderToFile = Arrays.asList(args).contains("--rendertofile");

        if (!renderToFile) UI.initialize();
        if (renderToFile) LOG.info("Skipping UI initialization (file render only)");

        Screen screen = new Screen(16, 16);
        for (int x = 0; x < screen.size().width; x++) {
            for (int y = 0; y < screen.size().height; y++) {
                screen.drawPixel(new Vector2(x, y), new Pixel(1f));
            }
        }
        LOG.info(screen.toString());

        new Thread(() -> {
            if (!renderToFile) {
                String render = Renderer.renderScreen(screen);
                UI.set(render);
                LOG.info("Screen has been rendered to UI");
            } else {
                Renderer.renderScreenToFile(screen);
                LOG.info("Screen has been rendered to file.");
            }
        }, "Render-Thread").start();
    }
}