package listeners;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Model;
import view.components.MusicPlayerSlider;

public class VolumeSliderListener implements ChangeListener {

    private Model model;

    public VolumeSliderListener(Model model) {
        super();

        this.model = model;
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        MusicPlayerSlider slider = (MusicPlayerSlider) e.getSource();

        if (slider.getValueIsAdjusting()) {
            model.setVolume(slider.getValue());
        }
    }
    
}
