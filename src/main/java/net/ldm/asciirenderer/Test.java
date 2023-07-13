package net.ldm.asciirenderer;

import net.ldm.asciirenderer.game.GameObject;
import net.ldm.asciirenderer.game.Map;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import java.util.Scanner;

public class Test {
	private static final Logger LOG = LoggerContext.getContext().getLogger(Test.class);

	public static void main(String[] args) {
		LOG.warn("You are currently running a TEST ENVIRONMENT. If this is intended, ignore this message. This class should NOT be included in production.");

		GameObject player = new GameObject();
		Scanner in = new Scanner(System.in);

		//noinspection InfiniteLoopStatement
		while (true) {
			System.out.println(Map.MAP);
			switch (in.nextLine()) {
				case "w" -> player.position.move(0, 1);
				case "a" -> player.position.move(1, 0);
				case "s" -> player.position.move(0, -1);
				case "d" -> player.position.move(-1, 0);
				default -> System.out.println("Invalid input");
			}
		}
	}
}
