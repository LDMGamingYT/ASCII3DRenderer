import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

public class Main {
    private static final Logger LOG = LoggerContext.getContext().getLogger(Main.class);

    public static void main(String[] args) {
        UI.initialize();

        Screen screen = new Screen(16, 16);
        screen.drawPixel(new Vector2(3, 4), 1f);
        screen.drawPixel(new Vector2(0, 4), 1f);
        LOG.info(screen.toString());

        new Thread(() -> {
            String render = Renderer.renderScreen(screen);

            UI.set(render);
        }, "Render-Thread").start();
    }
}