package net.ldm.asciirenderer.renderer;

import net.ldm.asciirenderer.core.PixelUtils;
import net.ldm.asciirenderer.core.Vector2;
import net.ldm.asciirenderer.core.exception.PixelOutOfBoundsException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Screen {
	private final Map<Vector2, Pixel> pixels;
	private final Dimension size;
	private static final Logger LOG = LoggerContext.getContext().getLogger(Screen.class);

	public Screen(int width, int height) {
		pixels = new HashMap<>();
		size = new Dimension(width, height);
		clear();
	}

	public boolean isOutOfBounds(Vector2 pos) {
		return pos.x > size.width || pos.y > size.height || pos.x < 0 || pos.y < 0;
	}

	public void drawPixel(Vector2 pos, Pixel pixel) throws PixelOutOfBoundsException {
		if (isOutOfBounds(pos)) throw new PixelOutOfBoundsException(pos, size);
		pixels.put(pos, pixel);
		LOG.debug("Drew pixel {} at {}", pixel, pos);
	}

	public void drawImage(URL imageFile) throws IOException {
		BufferedImage originalImage = ImageIO.read(imageFile);
		BufferedImage image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics = image.createGraphics();
		graphics.drawImage(originalImage, 0, 0, size.width, size.height, null);
		graphics.dispose();

		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				try {
					drawPixel(new Vector2(x, y), PixelUtils.withBrightnessFromRGB(image.getRGB(x, y)));
				} catch (PixelOutOfBoundsException e) {
					LOG.warn("Skipping pixel at ({}, {})", x, y, e);
				}
			}
		}
	}

	public void clear() {
		pixels.clear();
		for (int x = 0; x < size.width; x++) {
			for (int y = 0; y < size.height; y++) {
				try {
					drawPixel(new Vector2(x, y), new Pixel(0.1f));
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

	public int height() {
		return size.height;
	}

	public int width() {
		return size.width;
	}
}