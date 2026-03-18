public class App {
    public static void main(String[] args) throws Exception {
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");
        Model model = new Model();
        View view = new View(model);
        view.update();
    }
}
