package net.ldm.asciirenderer.renderer;

import net.ldm.asciirenderer.Main;
import net.ldm.asciirenderer.UI;
import net.ldm.asciirenderer.Vector2;
import net.ldm.asciirenderer.core.exception.PixelOutOfBoundsException;

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

		for (int y = -screen.size().height + 1; y < screen.size().height + 1; y++) {
			for (int x = -screen.size().width; x < screen.size().width; x++) {
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
			for (int x = -screen.size().width; x < screen.size().width; x++) {
				screen.drawVerticalLine(new Vector2(x, 5), -10, Pixel.FULL);
			}

		} catch (PixelOutOfBoundsException e) {
			throw new RuntimeException(e);
		}
		UI.set(renderScreen(screen));
		Thread.sleep(1000 / Main.FRAME_RATE);
	}
}