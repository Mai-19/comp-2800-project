import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class TopBarPanel extends JPanel {
    private Model model;
    public TopBarPanel(Model model) {
        super();

        this.model = model;

        createLayout();
        addComponents();
    }

    private void createLayout() {
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));
    }

    MusicPlayerButton settingsButton;
    private void addComponents() {
        try {
            settingsButton = new MusicPlayerButton(new ImageIcon((ImageIO.read(this.getClass().getResource("/icons/settings-sliders.png"))).getScaledInstance(20, 20, BufferedImage.SCALE_SMOOTH)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        settingsButton.setActionCommand("settings");

        this.add(settingsButton);
    }
}
