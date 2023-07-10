import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Screen {
	private static final Logger LOG = LoggerContext.getContext().getLogger(Screen.class);
	private final Map<Vector2, Float> pixels;
	private final Dimension size;

	public Screen(int width, int height) {
		pixels = new HashMap<>();
		size = new Dimension(width, height);
	}

	public boolean drawPixel(Vector2 pos, float brightness) {
		if (pos.x > size.width || pos.y > size.height) {
			LOG.warn("Pixel ({}, {}) is out of bounds ({}, {})", pos.x, pos.y, size.width, size.height);
			return false;
		}
		pixels.put(pos, brightness);
		return true;
	}

	public float getBrightnessAt(Vector2 pos) {
		return pixels.get(pos) == null ? 0f : pixels.get(pos);
	}

	@Override
	public String toString() {
		return "Screen" + pixels;
	}

	public Dimension size() {
		return size;
	}
}
