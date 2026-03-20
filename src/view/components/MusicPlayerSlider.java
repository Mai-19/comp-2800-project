package view.components;
import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;

public class MusicPlayerSlider extends JSlider {
    public MusicPlayerSlider(int min, int max, int value) {
        super(min, max, value);
        setOpaque(false);
        setUI(new BasicSliderUI(this) {
            @Override
            public void paintTrack(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Rectangle t = trackRect;
                int trackHeight = 4;
                int y = t.y + t.height / 2 - trackHeight / 2;
                // background track
                g2.setColor(Color.LIGHT_GRAY);
                g2.fillRoundRect(t.x, y, t.width, trackHeight, trackHeight, trackHeight);
                // filled portion
                int fillWidth = thumbRect.x - t.x + thumbRect.width / 2;
                g2.setColor(Color.GRAY);
                g2.fillRoundRect(t.x, y, fillWidth, trackHeight, trackHeight, trackHeight);
                g2.dispose();
            }

            @Override
            public void paintThumb(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int size = 12;
                int x = thumbRect.x + thumbRect.width / 2 - size / 2;
                int y = thumbRect.y + thumbRect.height / 2 - size / 2;
                g2.setColor(Color.GRAY);
                g2.fillOval(x, y, size, size);
                g2.dispose();
            }

            @Override
            protected void calculateThumbSize() {
                super.calculateThumbSize();
                thumbRect.setSize(16, 16);
            }
        });
    }
}