package ru.arlechin.life;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.BitSet;

/**
 * @author arlechin
 */
public class Screen {

    private BufferedImage screen;
    private BufferedImage scaledScreen;
    private JFrame frame;
    private MainPanel panel;

    public Screen(int width, int height) {

        screen = new BufferedImage(width, height, BufferedImage.BITMASK);

        scaledScreen = new BufferedImage(width * App.SCALE, height * App.SCALE, screen.getType());

        frame = new JFrame("Life");
        panel = new MainPanel();
        frame.add("Center", panel);
        panel.setPreferredSize(new Dimension(width * App.SCALE, height * App.SCALE));
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void redraw(BitSet world) {

        int y;
        int x;
        int color;

        for (int i = 0; i < 10000; i++) {
            x=i % screen.getWidth();
            y=i/screen.getWidth();
            color = world.get(i) ? 0xFF000000 : 0xFFFFFFFF;
            screen.setRGB(x, y, color);
        }

    }

    class MainPanel extends JPanel {

        public void paint(Graphics g) {
            Graphics2D g2d = scaledScreen.createGraphics();
            g2d.scale(App.SCALE, App.SCALE);
            g2d.drawImage(screen, 0, 0, null);
            g2d.dispose();

            g.drawImage(scaledScreen, 0, 0, Color.white, null);
            repaint();
        }

    }

}
