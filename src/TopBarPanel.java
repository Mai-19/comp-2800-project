import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RowFilter;

public class TopBarPanel extends JPanel {
    private Model model;
    public TopBarPanel(Model model) {
        super();

        this.model = model;

        createLayout();
        addComponents();
    }

    private void createLayout() {
        this.setLayout(new BorderLayout());
    }

    private MusicPlayerButton settingsButton;
    private JTextField searchField;
    private JLabel searchIcon;
    private void addComponents() {
        try {
            settingsButton = new MusicPlayerButton(new ImageIcon((ImageIO.read(this.getClass().getResource("/icons/settings-sliders.png"))).getScaledInstance(20, 20, BufferedImage.SCALE_SMOOTH)));
            searchIcon = new JLabel(new ImageIcon((ImageIO.read(this.getClass().getResource("/icons/search.png"))).getScaledInstance(20, 20, BufferedImage.SCALE_SMOOTH)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        settingsButton.setActionCommand("settings");

        searchField = new JTextField(30);
        searchField.setBorder(new RoundedBorder(20, 7));
        searchField.setToolTipText("Search");
        searchField.setBackground(getBackground());
        searchField.addCaretListener(e -> {
            String text = searchField.getText();
            if (text.isEmpty()) {
                model.setRowFilter(null);
            } else {
                model.setRowFilter(RowFilter.regexFilter("(?i)" + text));
            }
        });

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.setOpaque(false);
        centerPanel.add(searchIcon);
        centerPanel.add(searchField);

        this.add(centerPanel, BorderLayout.CENTER);
        this.add(settingsButton, BorderLayout.EAST);
    }

    public MusicPlayerButton getSettingsButton() {
        return settingsButton;
    }
}
