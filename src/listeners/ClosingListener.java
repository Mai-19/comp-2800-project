package listeners;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import model.Model;

public class ClosingListener extends WindowAdapter{
    private Model model;

    public ClosingListener(Model model) {
        super();

        this.model = model;
    }
    @Override
    public void windowClosing(WindowEvent e) {
        model.saveDirectories();
    }
}
