package net.ldm.asciirenderer.core.exception;

import net.ldm.asciirenderer.renderer.Pixel;

import java.awt.*;

public class PixelOutOfBoundsException extends Exception {
    public PixelOutOfBoundsException(Pixel pixel, Dimension bounds) {
        //super(String.format("Pixel (%s, %s) is out of bounds (%s, %s)", pixel.))
    }
}