import controller.Controller;
import view.MainView;

public class Main {

    public static void main(String[] args) {
        MainView mainView = new MainView();
        Controller controller = new Controller(mainView);
        mainView.setVisible(true);
    }
}
