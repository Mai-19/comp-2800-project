package view;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.UIManager;

import listeners.ButtonListener;
import listeners.ClosingListener;
import listeners.PlaybackTimer;
import listeners.ResizeListener;
import model.Model;
import model.Song;
import view.components.PlayerPanel;
import view.components.SettingsPanel;

import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.HashSet;
import java.awt.CardLayout;
import java.awt.DisplayMode;

public class View {
    private Model model;
    // the screens information, like refresh rate and size
    private DisplayMode screen;

    public View(Model model) {
        super();

        this.model = model;

        PlaybackTimer playbackTimer = new PlaybackTimer(model, this);
        playbackTimer.start();
        
        createFrame();
        registerControllers();
        this.model.addGUI(this);
        update();
    }

    private JFrame frame;
    private void createFrame() {
        // getting the default screen
        screen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode();

        frame = new JFrame();
        frame.setFont(UIManager.getFont("Label.font"));

        addComponents();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // make the window half the size of the screen in each dimension
        frame.setSize(screen.getWidth()/2, screen.getHeight()/2);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // the main panel of the view
    // will handle swapping between the main view and settings view with a card layout
    private JPanel contentPane;
    private CardLayout cardLayout;

    // the different panel views
    private PlayerPanel playerPanel;
    private SettingsPanel settingsPanel;

    private void addComponents() {
        cardLayout = new CardLayout();
        contentPane = new JPanel(cardLayout);

        playerPanel = new PlayerPanel(model, this);
        settingsPanel = new SettingsPanel(model);

        contentPane.add(playerPanel, Cards.PLAYER.name());
        contentPane.add(settingsPanel, Cards.SETTINGS.name());
        
        frame.setContentPane(contentPane);
    }

    private void registerControllers() {
        ResizeListener resizeListener = new ResizeListener(model);
        ButtonListener buttonListener = new ButtonListener(model, this);
        ClosingListener closingListener = new ClosingListener(model);
        frame.addWindowListener(closingListener);
        frame.addComponentListener(resizeListener);
        playerPanel.getTopBar().getSettingsButton().addActionListener(buttonListener);
        playerPanel.getBottomBar().addActionListener(buttonListener);
        settingsPanel.getBackBtn().addActionListener(buttonListener);
        settingsPanel.getRefreshBtn().addActionListener(buttonListener);
        settingsPanel.getAddDirectoryBtn().addActionListener(buttonListener);
    }

    public void changeView(Cards settings) {
        switch (settings) {
            case Cards.SETTINGS:
                cardLayout.show(contentPane, Cards.SETTINGS.name());
                break;
            case Cards.PLAYER:
                cardLayout.show(contentPane, Cards.PLAYER.name());
                break;
            default:
                break;
        }
    }

    public void update() {
        frame.repaint();
    }

    public void addDirectories(HashSet<String> directories) {
        settingsPanel.addDirectories(directories);
    }

    public void updateSongs(ArrayList<Song> songs) {
        playerPanel.getMusicList().setData(songs);
    }

    public void updatePlayingSong(Song song, ImageIcon icon) {
        playerPanel.getBottomBar().setSongTitle((String)song.getInfo()[0]);
        playerPanel.getBottomBar().setAlbumArtist((String)song.getInfo()[1]);
        playerPanel.getBottomBar().setCurrentTime("0:00");
        playerPanel.getBottomBar().setTotalTime((String)song.getInfo()[4]);
        playerPanel.getBottomBar().setAlbumArt(icon);
    }

    public void setProgress(int value) {
        playerPanel.getBottomBar().setProgress(value);
        playerPanel.getBottomBar().setCurrentTime(
            (value/60)
            +":"+
            ((value%60)<10?"0"+(value%60) : (value%60)));
    }
    public void shiftProgress(int value) {
        playerPanel.getBottomBar().setProgress(playerPanel.getBottomBar().getProgress() + value);
    }

    public void setRowFilter(RowFilter<Object,Object> regexFilter) {
        playerPanel.getMusicList().getTableSorter().setRowFilter(regexFilter);
    }

	public void setPlaybackButtonIcon(String string) {
        playerPanel.getBottomBar().setPlaybackButtonIcon(string);
	}

    public void setVolume(float value) {
        playerPanel.getBottomBar().setVolume(value);
    }

    public void toggleMute() {
        playerPanel.getBottomBar().toggleMute();
    }

    public void addDirectory() {
        settingsPanel.openFileChooser();
    }

    public void refreshDirectoryList() {
        settingsPanel.refreshDirectoryList();
    }
}
