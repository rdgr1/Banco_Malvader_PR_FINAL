package org.swing.util;

import javax.swing.border.LineBorder;
import java.awt.*;

public class RoundedBorder extends LineBorder {
    private final int radius;

    public RoundedBorder(int radius) {
        super(new Color(46, 202, 226), 2, true);
        this.radius = radius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(lineColor);
        g2d.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
    }
}