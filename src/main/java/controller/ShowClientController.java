package controller;

import view.ClientView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowClientController implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        ClientView clientView = new ClientView();
        clientView.addAddListener(new AddClientController());
        clientView.addEditListener(new EditClientController(clientView));
        clientView.addDeleteListener(new DeleteClientController(clientView));
        clientView.addViewListener(new ShowTableController("Client"));
        clientView.setVisible(true);
    }
}