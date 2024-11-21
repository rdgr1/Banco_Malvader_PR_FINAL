package org.swing.util;

import javax.swing.*;
import java.awt.*;

public class GradientPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();
        GradientPaint gradient = new GradientPaint(0, 0, new Color(13, 31, 47), 0, height, new Color(10, 25, 28));
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, width, height);
    }
}
