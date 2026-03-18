import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.Icon;
import javax.swing.JToggleButton;

public class MusicPlayerToggleButton extends JToggleButton {
    public MusicPlayerToggleButton(Icon icon) {
        super(icon);
        setContentAreaFilled(false);
        setRolloverEnabled(true);
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (isSelected()) {
            g2.setColor(new Color(0, 0, 0, 80));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        } else if (getModel().isRollover()) {
            g2.setColor(new Color(0, 0, 0, 40));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        }
        g2.dispose();
    }
}