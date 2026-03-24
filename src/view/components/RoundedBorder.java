package view.components;
import java.awt.BasicStroke;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.border.AbstractBorder;

import view.View;

/**
 * Class for the rounded border
 */
public class RoundedBorder extends AbstractBorder{
    private int radius;
    private int inset;

    /**
     * constructor for the RoundedBorder class
     * @param radius
     */
    public RoundedBorder (int radius) {
        super();

        this.radius = radius;
        this.inset = 10;
    }
    /**
     * constructor for the RoundedBorder class <br>
     * adds custom padding
     * @param radius
     * @param insets
     */
    public RoundedBorder (int radius, int insets) {
        super();

        this.radius = radius;
        this.inset = insets;
    }

    /**
     * paints the rounded border
     */
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(View.ACCENT); // border color
        g2.setStroke(new BasicStroke(2)); // border thickness
        g2.draw(new RoundRectangle2D.Double(x + 1, y + 1, width - 2, height - 2, radius, radius));
        g2.dispose();
    }

    /**
     * returns the insets of the rounded border
     */
    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(inset, inset, inset, inset); // padding inside border
    }
}
