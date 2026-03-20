package view.components;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import listeners.SearchListener;
import view.Icons;
import view.View;

public class TopBarPanel extends JPanel {
    private View view;
    public TopBarPanel(View view) {
        super();

        this.view = view;

        createLayout();
        addComponents();
        registerControllers();
    }

    private void createLayout() {
        this.setLayout(new BorderLayout());
    }

    private MusicPlayerButton settingsButton;
    private JTextField searchField;
    private JLabel searchIcon;
    private void addComponents() {
        settingsButton = new MusicPlayerButton(Icons.SETTINGS_SLIDERS);
        searchIcon = new JLabel(Icons.SEARCH);
        settingsButton.setActionCommand("settings");

        searchField = new JTextField(30);
        searchField.setBorder(new RoundedBorder(20, 7));
        searchField.setToolTipText("Search");
        searchField.setBackground(getBackground());

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.setOpaque(false);
        centerPanel.add(searchIcon);
        centerPanel.add(searchField);
        
        this.add(Box.createRigidArea(new Dimension(settingsButton.getPreferredSize().width, 0)), BorderLayout.WEST);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(settingsButton, BorderLayout.EAST);
    }

    public void registerControllers() {
        searchField.addCaretListener(new SearchListener(view));
    }

    public MusicPlayerButton getSettingsButton() {
        return settingsButton;
    }
}
