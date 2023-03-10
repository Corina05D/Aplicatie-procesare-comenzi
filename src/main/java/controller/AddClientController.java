package controller;
import bll.ClientBLL;
import model.Client;
import view.AddClientView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddClientController implements ActionListener {
    private final ClientBLL clientBLL;

    public AddClientController() {
        clientBLL = new ClientBLL();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AddClientView addClientView = new AddClientView();
        addClientView.addSubmitListener(e1 -> {
            try {
                if (addClientView.getClientName().equals("") || addClientView.getAddress().equals("") || addClientView.getEmail().equals("")) {
                    throw new IllegalArgumentException("Completati toate campurile!");
                }
                int clientId = clientBLL.insertClient(new Client(addClientView.getClientName(), addClientView.getEmail(), addClientView.getAddress()));
                addClientView.showSuccess("Clientul cu id " + clientId + " a fost adaugat cu succes!");
            } catch (Exception ex) {
                addClientView.showError(ex.getMessage());
            }
        });
        addClientView.setVisible(true);
    }
}
