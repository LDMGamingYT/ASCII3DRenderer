package net.ldm.asciirenderer.renderer;

import net.ldm.asciirenderer.Main;
import net.ldm.asciirenderer.UI;
import net.ldm.asciirenderer.core.Vector2;
import net.ldm.asciirenderer.core.exception.PixelOutOfBoundsException;

import java.io.IOException;

public class Renderer {
	protected static final char[] BRIGHTNESS_SCALE = {
			'\u00a0', '\'', '.', '`', '^', '"', ',', ':', ';', 'I', 'l', '!', 'i', '>', '<', '~', '+', '_', '-', '?', ']',
			'[', '}', '{', '1', ')', '(', '|', '\\', '/', 't', 'f', 'j', 'r', 'x', 'n', 'u', 'v', 'c', 'z', 'X', 'Y',
			'U', 'J', 'C', 'L', 'Q', '0', 'O', 'Z', 'm', 'w', 'q', 'p', 'd', 'b', 'k', 'h', 'a', 'o', '*', '#', 'M', 'W',
			'&', '8', '%', 'B', '@'
	};

	private static String renderScreen(Screen screen) {
		StringBuilder result = new StringBuilder();
		result.append("<html>");

		for (int y = 0; y < screen.height(); y++) {
			for (int x = 0; x < screen.width(); x++) {
				result.append(screen.getPixelAt(new Vector2(x, y)).toAscii());
			}
			result.append("<br>");
		}

		result.append("</html>");
		return result.toString();
	}

	// Update is called every frame
	public static void update(Screen screen) throws InterruptedException {
		screen.clear();
		try {
			screen.drawImage(Main.class.getResource("/images/demo/apple.png"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		UI.set(renderScreen(screen));
		Thread.sleep(1000 / Main.FRAME_RATE);
	}
}