package view.components;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.JSlider;
import javax.swing.plaf.basic.BasicSliderUI;

import view.View;

/**
 * Class for the music player slider
 */
public class MusicPlayerSlider extends JSlider {
    /**
     * creates a new slider for the music player
     * 
     * @param min
     * @param max
     * @param value
     */
    public MusicPlayerSlider(int min, int max, int value) {
        super(min, max, value);
        setOpaque(false);
        setUI(new MusicPlayerSliderUI(this));
    }

    /**
     * Class for the music player slider UI
     */
    private class MusicPlayerSliderUI extends BasicSliderUI {

        /**
         * constructor for the MusicPlayerSliderUI class
         * 
         * @param b
         */
        public MusicPlayerSliderUI(JSlider b) {
            super(b);
        }

        /**
         * paints the slider
         */
        @Override
        public void paintTrack(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Rectangle t = trackRect;
            int trackHeight = 4;
            int y = t.y + t.height / 2 - trackHeight / 2;
            // background track
            g2.setColor(View.TEXT);
            g2.fillRoundRect(t.x, y, t.width, trackHeight, trackHeight, trackHeight);
            // filled portion
            int fillWidth = thumbRect.x - t.x + thumbRect.width / 2;
            g2.setColor(View.ACCENT);
            g2.fillRoundRect(t.x, y, fillWidth, trackHeight, trackHeight, trackHeight);
            g2.dispose();
        }

        /**
         * paint the thumb
         */
        @Override
        public void paintThumb(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int size = 12;
            int x = thumbRect.x + thumbRect.width / 2 - size / 2;
            int y = thumbRect.y + thumbRect.height / 2 - size / 2;
            g2.setColor(View.ACCENT);
            g2.fillOval(x, y, size, size);
            g2.dispose();
        }

        /**
         * calculate the thumbs size
         */
        @Override
        protected void calculateThumbSize() {
            super.calculateThumbSize();
            thumbRect.setSize(16, 16);
        }
    }
}