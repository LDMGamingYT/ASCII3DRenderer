package net.ldm.asciirenderer.core;

import java.util.Objects;

public class Vector2 {
	public int x;
	public int y;

	public Vector2(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Moves this Vector, does NOT set position
	 * @param dx Distance to travel on x-axis
	 * @param dy Distance to travel on y-axis
	 */
	public void move(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ')';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Vector2 vector2 = (Vector2) o;
		return x == vector2.x && y == vector2.y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}
}