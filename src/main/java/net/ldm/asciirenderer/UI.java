package net.ldm.asciirenderer;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

import javax.swing.*;
import java.awt.*;

public class UI {
    private static final Logger LOG = LoggerContext.getContext().getLogger(UI.class);
    private static final JLabel LABEL = new JLabel();

    public static void initialize() {
        LOG.info("Initializing UI and frame");
        // TODO: 2023-07-09 Word wrap. Word. Wrap.
        LABEL.setForeground(Color.WHITE);
        LABEL.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension windowSize = new Dimension(screenSize.width / 2, screenSize.height / 2);
        LOG.info("Screen size is {}x{}, setting window size to {}x{}",
                screenSize.width, screenSize.height, windowSize.width, windowSize.height);

        JFrame frame = new JFrame("ASCII Renderer");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panel.add(LABEL);
        panel.setPreferredSize(windowSize);

        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
        LOG.info("Frame packed! Showing frame as window");
    }

    public static void set(String text) {
        LABEL.setText(text);
    }

    public static String get() {
        return LABEL.getText();
    }

    public static void append(char c) {
        set(get() + c);
    }
}