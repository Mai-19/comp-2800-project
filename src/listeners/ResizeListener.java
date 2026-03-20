package listeners;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import model.Model;

public class ResizeListener extends ComponentAdapter{
    private Model model;
    public ResizeListener(Model model) {
        super();
        this.model = model;
    }
    @Override
    public void componentResized(ComponentEvent e) {
        model.update();
    }
}
