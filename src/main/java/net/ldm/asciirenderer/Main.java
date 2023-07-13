package net.ldm.asciirenderer;

import net.ldm.asciirenderer.renderer.Renderer;
import net.ldm.asciirenderer.renderer.Screen;

public class Main {
    public static final int FRAME_RATE = 60;
    public static final int FOV = 60;

    public static void main(String[] args) {
        UI.initialize();

        Screen screen = new Screen(136, 32);

        new Thread(() -> {
            while (true) {
                try {
                    Renderer.update(screen);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "Render-Thread").start();
    }
}