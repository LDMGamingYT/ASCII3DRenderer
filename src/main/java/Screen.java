import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Screen {
	private static final Logger LOG = LoggerContext.getContext().getLogger(Screen.class);
	private final Map<Integer, Map<Integer, Float>> pixels;
	private final Dimension size;

	public Screen(int width, int height) {
		pixels = new HashMap<>();
		size = new Dimension(width, height);
		
		LOG.info("Creating screen with size ({}, {})", size.width, size.height);

		for (int y = 0; y < size.height; y++) {
			for (int x = 0; x < size.width; x++) {
				pixels.computeIfAbsent(x, k -> new HashMap<>());
				pixels.get(x).put(y, 0f);
				pixels.put(x, pixels.get(x));
			}
		}
	}

	public boolean drawPixel(int x, int y, float brightness) {
		if (x > size.width || y > size.height) {
			LOG.warn("Pixel ({}, {}) is out of bounds ({}, {})", x, y, size.width, size.height);
			return false;
		}
		//pixels.computeIfAbsent(x, k -> new HashMap<>());
		pixels.get(x).put(y, brightness);
		return true;
	}

	public float getBrightnessAt(int x, int y) {
		if (pixels.get(x) == null) return 0f;
		return pixels.get(x).get(y) == null ? 0f : pixels.get(x).get(y);
	}

	@Override
	public String toString() {
		return "Screen" + pixels;
	}

	public Dimension size() {
		return size;
	}
}
