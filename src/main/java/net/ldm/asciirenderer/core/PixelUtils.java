package net.ldm.asciirenderer.core;

import net.ldm.asciirenderer.renderer.Pixel;

public class PixelUtils {
	public static Pixel withBrightnessFromRGB(int color) {
		int red = (color >> 16) & 0xFF;
		int green = (color >> 8) & 0xFF;
		int blue = color & 0xFF;

		float normalizedRed = red / 255.0f;
		float normalizedGreen = green / 255.0f;
		float normalizedBlue = blue / 255.0f;

		return new Pixel((normalizedRed + normalizedGreen + normalizedBlue) / 3.0f);
	}
}
