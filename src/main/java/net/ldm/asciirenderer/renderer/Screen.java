package net.ldm.asciirenderer.renderer;

import net.ldm.asciirenderer.Vector2;
import net.ldm.asciirenderer.core.exception.PixelOutOfBoundsException;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Screen {
	private final Map<Vector2, Pixel> pixels;
	private final Dimension size;

	public Screen(int width, int height) {
		pixels = new HashMap<>();
		size = new Dimension(width, height);
	}

	public void drawPixel(Vector2 pos, Pixel pixel) throws PixelOutOfBoundsException {
		if (pos.x > size.width || pos.y > size.height) {
			throw new PixelOutOfBoundsException(pos, size);
		}
		pixels.put(pos, pixel);
	}

	public Pixel getPixelAt(Vector2 pos) {
		return pixels.get(pos) == null ? new Pixel() : pixels.get(pos);
	}

	@Override
	public String toString() {
		return "Screen" + pixels;
	}

	public Dimension size() {
		return size;
	}
}