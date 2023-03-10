package controller;

import view.*;

public class Controller {

    public Controller(MainView mainView) {
        mainView.addClientListener(new ShowClientController());
        mainView.addProductListener(new ShowProductController());
        mainView.addOrderListener(new ShowOrderController());
    }
}
