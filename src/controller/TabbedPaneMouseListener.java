package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import view.components.MusicPlayerTabbedPane;

public class TabbedPaneMouseListener implements MouseListener, MouseMotionListener{

    public TabbedPaneMouseListener() {
        super();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        MusicPlayerTabbedPane mp = (MusicPlayerTabbedPane)e.getSource();
        int tab = mp.getUI().tabForCoordinate(mp, e.getX(), e.getY());
        if (tab != mp.getHoveredTab()) {
            mp.setHoveredTab(tab);
        }
        mp.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
        MusicPlayerTabbedPane mp = (MusicPlayerTabbedPane)e.getSource();
        mp.setHoveredTab(-1);
        mp.repaint();  
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }
    
}
