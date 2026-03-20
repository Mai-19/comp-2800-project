package view.components;
import javax.swing.*;

/**
 * Class for the music player slider
 */
public class MusicPlayerSlider extends JSlider {
    /**
     * creates a new slider for the music player
     * @param min
     * @param max
     * @param value
     */
    public MusicPlayerSlider(int min, int max, int value) {
        super(min, max, value);
        setOpaque(false);
        setUI(new MusicPlayerSliderUI(this));
    }
}