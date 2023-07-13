package net.ldm.asciirenderer.core.exception;

import net.ldm.asciirenderer.core.Vector2;

import java.awt.Dimension;

public class PixelOutOfBoundsException extends Exception {
    public PixelOutOfBoundsException(Vector2 pos, Dimension bounds) {
        super(String.format("Pixel (%s, %s) is out of bounds (%s, %s)", pos.x, pos.y, bounds.width, bounds.height));
    }
}