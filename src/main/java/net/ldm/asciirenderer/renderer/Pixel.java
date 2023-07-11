package net.ldm.asciirenderer.renderer;

import java.util.Objects;

public class Pixel {
    public final float brightness;

    public Pixel(float brightness) {
        this.brightness = brightness;
    }

    public Pixel() {
        this(0f);
    }

    public char toAscii() {
        int index = (int) (brightness * (Renderer.BRIGHTNESS_SCALE.length - 1));
        if (index < 0) index = 0;
        else if (index >= Renderer.BRIGHTNESS_SCALE.length) index = Renderer.BRIGHTNESS_SCALE.length - 1;
        return Renderer.BRIGHTNESS_SCALE[index];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pixel pixel = (Pixel) o;
        return Float.compare(pixel.brightness, brightness) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(brightness);
    }

    @Override
    public String toString() {
        return "(" +
                brightness + "b" +
                ')';
    }
}