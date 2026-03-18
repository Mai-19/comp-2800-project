import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class RoundedPanel extends JLayeredPane {
    private int radius;

    public RoundedPanel(JPanel content, int radius) {
        this.radius = radius;
        setOpaque(false);

        content.setBounds(0, 0, content.getPreferredSize().width, content.getPreferredSize().height);
        add(content, JLayeredPane.DEFAULT_LAYER);

        // overlay that just draws the border on top
        JComponent overlay = new JComponent() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.GRAY);
                g2.setStroke(new BasicStroke(1.5f));
                g2.draw(new RoundRectangle2D.Double(1, 1, getWidth() - 2, getHeight() - 2, radius, radius));
                g2.dispose();
            }
        };
        overlay.setBounds(0, 0, content.getPreferredSize().width, content.getPreferredSize().height);
        add(overlay, JLayeredPane.PALETTE_LAYER);
    }

    @Override
    public void doLayout() {
        super.doLayout();
        for (Component c : getComponents()) {
            c.setBounds(0, 0, getWidth(), getHeight());
        }
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setClip(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), radius, radius));
        super.paint(g2);
        g2.dispose();
    }
}