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
		clear();
	}

	public boolean isOutOfBounds(Vector2 pos) {
		return pos.x > size.width || pos.y > size.height;
	}

	public void drawPixel(Vector2 pos, Pixel pixel) throws PixelOutOfBoundsException {
		if (isOutOfBounds(pos)) throw new PixelOutOfBoundsException(pos, size);
		pixels.put(pos, pixel);
	}

	public void drawVerticalLine(Vector2 origin, int distance, Pixel pixels) throws PixelOutOfBoundsException {
		if (isOutOfBounds(origin)) throw new PixelOutOfBoundsException(origin, size);
		Vector2 endPoint = new Vector2(origin.x, origin.y+distance);
		if (isOutOfBounds(endPoint)) throw new PixelOutOfBoundsException(endPoint, size);

		if (distance > 0)
			for (int y = origin.y; y < origin.y + distance; y++)
				drawPixel(new Vector2(origin.x, y), pixels);
		else
			for (int y = origin.y; y > origin.y + distance; y--)
				drawPixel(new Vector2(origin.x, y), pixels);
	}

	public void clear() {
		pixels.clear();
		for (int x = 0; x < size.width; x++) {
			for (int y = 0; y < size.height; y++) {
				try {
					drawPixel(new Vector2(x, y), new Pixel(0f));
				} catch (PixelOutOfBoundsException e) {
					throw new RuntimeException(e);
				}
			}
		}
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