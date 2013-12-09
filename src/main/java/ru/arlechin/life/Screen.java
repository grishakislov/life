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

    public Screen(int width, int height) {

        screen = new BufferedImage(width, height, BufferedImage.BITMASK);

        scaledScreen = new BufferedImage(width * App.SCALE, height * App.SCALE, screen.getType());

        frame = new JFrame("Life");
        frame.add("Center", new MainCanvas());
        frame.setSize(width * App.SCALE, height * App.SCALE);
        frame.setVisible(true);

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

        Graphics2D g = scaledScreen.createGraphics();
        g.scale(App.SCALE, App.SCALE);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        g.drawImage(screen, 0, 0, null);
        g.dispose();
    }

    class MainCanvas extends Canvas {
        public void paint(Graphics g) {
            g.drawImage(scaledScreen, 0, 0, Color.white, null);
            this.repaint();
        }
    }

}
