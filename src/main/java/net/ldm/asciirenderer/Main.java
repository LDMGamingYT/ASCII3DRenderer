package net.ldm.asciirenderer;

import net.ldm.asciirenderer.renderer.Renderer;
import net.ldm.asciirenderer.renderer.Screen;

public class Main {
    public static final int FRAME_RATE = 60;
    public static final int FOV = 60;

    public static void main(String[] args) {
        UI.initialize();

        // TODO: 2023-07-11 Make sure this works on all resolutions.
        //                  last tested with Screen size is 1536x864, setting window size to 768x432
        Screen screen = new Screen(136, 32);

        new Thread(() -> {
            try {
                Renderer.update(screen);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            /*while (true) {
                try {
                    Renderer.update(screen);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }*/
        }, "Render-Thread").start();
    }
}