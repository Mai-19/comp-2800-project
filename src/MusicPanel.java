import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;

public class MusicPanel extends JPanel {

    private DefaultTableModel tableModel;
    private JTable table;
    private Model model;
    public MusicPanel(Model model) {
        super(new BorderLayout());
        setOpaque(false);
        this.model = model;

        String[] columns = {"Title", "Artist", "Album", "Year", "Length"};
        Object[][] data = {};

        tableModel = new DefaultTableModel(data, columns) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = table.rowAtPoint(e.getPoint());
                    if (row != -1) {
                        model.play (row);
                    }
                }
            }
        });
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 1));
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scroll, BorderLayout.CENTER);
    }

    public void setData(Collection<Song> songs) { 
        tableModel.setRowCount(0);
        for (Song song : songs) { 
            tableModel.addRow(song.getInfo()); 
        } 
    }

    public JTable getTable() { return table; }
    public DefaultTableModel getTableModel() { return tableModel; }
}