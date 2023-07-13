package net.ldm.asciirenderer;

import net.ldm.asciirenderer.game.GameObject;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

public class Test {
	private static final Logger LOG = LoggerContext.getContext().getLogger(Test.class);

	public static void main(String[] args) {
		LOG.warn("You are currently running a TEST ENVIRONMENT. If this is intended, ignore this message. This class should NOT be included in production.");

		GameObject player = new GameObject();
	}
}
