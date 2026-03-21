package view.components;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 * rounded panel class
 */
public class RoundedPanel extends JLayeredPane {
    private int radius;
    private JPanel content;

    /**
     * constructor for the rounded panel class
     * @param content
     * @param radius
     */
    public RoundedPanel(JPanel content, int radius) {
        this.radius = radius;
        setOpaque(false);
        this.content = content;

        this.content.setBounds(0, 0, content.getPreferredSize().width, content.getPreferredSize().height);
        add(this.content, JLayeredPane.DEFAULT_LAYER);

        RoundedBorder overlayBorder = new RoundedBorder(radius, 2);

        JPanel overlay = new JPanel();
        overlay.setOpaque(false);

        overlay.setBorder(overlayBorder);

        overlay.setBounds(0, 0, content.getPreferredSize().width, content.getPreferredSize().height);
        add(overlay, JLayeredPane.PALETTE_LAYER);
    }

    /**
     * sets the layout of the panel
     */
    @Override
    public void doLayout() {
        super.doLayout();
        for (Component c : getComponents()) {
            c.setBounds(0, 0, getWidth(), getHeight());
        }
    }

    /**
     * paints the panel
     */
    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setClip(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), radius, radius));
        super.paint(g2);
        g2.dispose();
    }
}