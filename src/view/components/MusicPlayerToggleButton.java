package view.components;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.Icon;
import javax.swing.JToggleButton;

import view.View;

/**
 * Class for the music player toggle button
 */
public class MusicPlayerToggleButton extends JToggleButton {
    /**
     * constructor for the MusicPlayerToggleButton class
     * @param icon
     */
    public MusicPlayerToggleButton(Icon icon) {
        super(icon);
        setContentAreaFilled(false);
        setRolloverEnabled(true);
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
    }

    /**
     * paints the component
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (isSelected()) {
            g2.setColor(new Color(View.TEXT.getRGB()+0x22000000, true));
            g2.setStroke(new BasicStroke(0f));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
            g2.setColor(new Color(View.TEXT.getRGB()+0x66000000, true));
            g2.setStroke(new BasicStroke(2f));
            g2.drawRoundRect(2, 2, getWidth()-4, getHeight()-4, 10, 10);
        } else if (getModel().isRollover()) {
            g2.setColor(new Color(View.TEXT.getRGB()+0x22000000, true));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        }
        g2.dispose();
    }
}