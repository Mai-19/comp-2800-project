package view.components;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class MusicPlayerTable extends JTable {

    public MusicPlayerTable(DefaultTableModel model) {
        super(model);
        setOpaque(false);
        setShowGrid(false);
        setIntercellSpacing(new Dimension(0, 0));
        setRowHeight(28);
        setFillsViewportHeight(true);
        setFont(getFont().deriveFont(13f));

        // center all columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                setHorizontalAlignment(JLabel.CENTER);
                setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));

                if (isSelected) {
                    setBackground(new Color(0, 0, 0, 40));
                    setForeground(Color.BLACK);
                } else {
                    setBackground(row % 2 == 0 ? new Color(218, 218, 218) : new Color(208, 208, 208));
                    setForeground(new Color(40, 40, 40));
                }
                setOpaque(true);
                return this;
            }
        };

        for (int i = 0; i < getColumnCount(); i++) {
            getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // style the header
        JTableHeader header = getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = new JLabel(value.toString());
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setFont(label.getFont().deriveFont(Font.BOLD, 13f));
                label.setForeground(new Color(60, 60, 60));
                label.setOpaque(true);
                label.setBackground(new Color(200, 200, 200));
                label.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(180, 180, 180)));
                return label;
            }
        });
        header.setPreferredSize(new Dimension(header.getWidth(), 30));
        header.setReorderingAllowed(false);
        header.setResizingAllowed(false);
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(190, 190, 190));
        for (int i = 0; i < getRowCount(); i++) {
            int y = (i + 1) * getRowHeight();
            g2.drawLine(0, y, getWidth(), y);
        }
        g2.dispose();
    }
}