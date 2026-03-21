package view.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import model.Model;
import model.Song;
import view.View;

public class MusicPlayerTablePopup extends JPopupMenu {

    private Model model;
    private View view;
    private MusicPlayerTable table;

    public MusicPlayerTablePopup(MusicPlayerTable table, Model model, View view) {
        this.table = table;
        this.model = model;
        this.view = view;

        stylePopup(this);

        setBackground(new Color(245, 245, 245));
    
        setOpaque(false);
    
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleClick(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                handleClick(e);
            }

            private void handleClick(MouseEvent e) {
                if (!e.isPopupTrigger()) return;
                int row = table.rowAtPoint(e.getPoint());
                if (row >= 0 && !table.isRowSelected(row)) {
                    table.setRowSelectionInterval(row, row);
                }
                buildMenu();
                show(table, e.getX(), e.getY());
            }
        });
    }

    private void buildMenu() {
        removeAll();

        // "Add to playlist" submenu
        JMenu addToPlaylist = new JMenu("Add to playlist");
        addToPlaylist.setFont(addToPlaylist.getFont().deriveFont(14f));
        stylePopup(addToPlaylist.getPopupMenu());

        List<String> playlists = model.loadPlaylists();
        if (playlists.isEmpty()) {
            JMenuItem none = new JMenuItem("No playlists");
            none.setEnabled(false);
            none.setFont(none.getFont().deriveFont(Font.ITALIC, 13f));
            addToPlaylist.add(none);
        } else {
            for (String playlist : playlists) {
                JMenuItem item = makeMenuItem(playlist);
                item.addActionListener(e -> addSelectedSongsToPlaylist(playlist));
                addToPlaylist.add(item);
            }
        }
        add(addToPlaylist);

        // "Remove from playlist" — only show when inside a playlist
        MusicPlayerTabbedPane tabbedPane = view.getPlayerPanel().getTabbedPane();
        if (tabbedPane.getSelectedIndex() != 0) {
            addSeparator();
            JMenuItem removeItem = makeMenuItem("Remove from playlist");
            removeItem.setForeground(new Color(200, 50, 50));
            removeItem.addActionListener(e -> removeSelectedSongsFromPlaylist());
            add(removeItem);
        }
    }

    private JMenuItem makeMenuItem(String text) {
        JMenuItem item = new JMenuItem(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                if (getModel().isArmed()) {
                    g2.setColor(new Color(0, 0, 0, 30));
                    g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, 8, 8);
                }
                g2.dispose();
                super.paintComponent(g);
            }
        };
        item.setFont(item.getFont().deriveFont(14f));
        item.setOpaque(false);
        item.setBackground(new Color(0, 0, 0, 0));
        return item;
    }

    private void stylePopup(JPopupMenu popup) {
        popup.setLightWeightPopupEnabled(true);
    }

    private void addSelectedSongsToPlaylist(String playlistName) {
        int[] selectedRows = table.getSelectedRows();
        for (int viewRow : selectedRows) {
            int modelRow = table.convertRowIndexToModel(viewRow);
            Song song = model.getSongs().get(modelRow);
            model.addSongToPlaylist(playlistName, song.getPath());
        }
    }

    private void removeSelectedSongsFromPlaylist() {
        String playlist = view.getPlayerPanel().getPlaylistsPanel().getSelectedPlaylist();
        if (playlist == null) return;
        int[] selectedRows = table.getSelectedRows();
        for (int viewRow : selectedRows) {
            int modelRow = table.convertRowIndexToModel(viewRow);
            Song song = model.getSongs().get(modelRow);
            model.removeSongFromPlaylist(playlist, song.getPath());
        }
        // refresh the playlist view
        model.setSongs(model.loadSongsForPlaylist(playlist));
        view.pullSongs();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(245, 245, 245));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 21, 21);
        g2.dispose();
    }

}