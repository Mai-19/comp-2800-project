package view.components;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.Icon;
import javax.swing.JButton;

/**
 * Class for the music player button
 */
public class MusicPlayerButton extends JButton {

    private int radius;
    /**
     * Constructor for the MusicPlayerButton class
     * @param icon
     */
    public MusicPlayerButton(Icon icon) {
        super(icon);
        radius = 10;
        setContentAreaFilled(false);
        setRolloverEnabled(true);
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
    }
    public MusicPlayerButton(String text) {
        super(text);
        radius = 10;
        setContentAreaFilled(false);
        setRolloverEnabled(true);
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
    }
    public MusicPlayerButton(Icon icon, int width, int height, int radius) {
        super(icon);
        this.radius = radius;
        this.setPreferredSize(new Dimension(width, height));
        setContentAreaFilled(false);
        setRolloverEnabled(true);
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
    }

    /**
     * paints the button
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (getModel().isRollover()) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(new Color(0,0,0, 40));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            g2.dispose();
        }
    }
}