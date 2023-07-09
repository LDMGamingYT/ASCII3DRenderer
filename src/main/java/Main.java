import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.util.Random;

public class Main {
    private static final Logger LOG = LoggerContext.getContext().getLogger(Main.class);

    // fair warning: running this WILL lag your computer A LOT. it runs VERY unoptimized code to generate random
    //               pixels. not my fault if your computer goes all like 🔥🔥🔥🔥💥💥💥 :)
    public static void main(String[] args) {
        UI.initialize();

        new Thread(() -> {
            while (true) {
                float r = new Random().nextFloat();
                LOG.info("Rendering pixel with brightness {}", r);
                UI.append(Renderer.getChar(r));
            }
        }, "Render-Thread").start();
    }
}