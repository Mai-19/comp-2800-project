package listeners;

import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import view.View;

public class SearchListener implements CaretListener{

    private View view;

    public SearchListener(View view) {
        super();

        this.view = view;
    }

    @Override
    public void caretUpdate(CaretEvent e) {
        String text = ((JTextField)e.getSource()).getText();
        if (text.isEmpty()) {
            view.setRowFilter(null);
        } else {
            view.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }
    
}
