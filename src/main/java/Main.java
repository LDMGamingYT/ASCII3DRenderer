import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final Logger LOG = LoggerContext.getContext().getLogger(Main.class);

    // fair warning: running this WILL lag your computer A LOT. it runs VERY unoptimized code to generate random
    //               pixels. not my fault if your computer goes all like 🔥🔥🔥🔥💥💥💥 :)
    public static void main(String[] args) {
        UI.initialize();
        UI.set("Awaiting path to image...");

        new Thread(() -> {
            Scanner in = new Scanner(System.in);
            System.out.print("Path to image?\n> ");
            File image = new File(in.nextLine());
            String renderedImage = Renderer.renderImage(image);

            UI.set(renderedImage);
        }, "Render-Thread").start();
    }
}