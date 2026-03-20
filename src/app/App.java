package app;
import model.Model;
import view.View;

/**
 * Main class for the application.
 */
public class App {

    /**
     * Main method for the application.
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // these are to stop the text from appearing "pixelated"
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");

        // init of model and view
        Model model = new Model();
        View view = new View(model);
        view.update();
    }
}
