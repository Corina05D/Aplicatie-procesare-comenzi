package controller;

import bll.ClientBLL;
import model.Client;
import view.ClientView;
import view.EditClientView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditClientController implements ActionListener {
    private final ClientBLL clientBLL;
    private final ClientView clientView;

    public EditClientController(ClientView clientView) {
        this.clientView = clientView;
        this.clientBLL = new ClientBLL();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int idClient = Integer.parseInt(clientView.getEditClientId());
            Client c = clientBLL.findClientById(idClient);
            EditClientView editClientView = new EditClientView(c);
            editClientView.addSubmitListener(e1 -> {
                try {
                    if (editClientView.getClientName().equals("") || editClientView.getAddress().equals("") || editClientView.getEmail().equals("")) {
                        throw new IllegalArgumentException("Nu ati completat anumite campuri!");
                    }
                    clientBLL.updateClient(idClient, new Client(editClientView.getClientName(), editClientView.getEmail(), editClientView.getAddress()));
                    editClientView.showSuccess("Clientul cu  id " + idClient + " a fost modificat cu succes!");
                } catch (Exception ex) {
                    editClientView.showError(ex.getMessage());
                }
            });
            editClientView.setVisible(true);
        } catch (NumberFormatException ex) {
            clientView.showError("ID Client invalid!");
        } catch (Exception ex) {
            clientView.showError(ex.getMessage());
        }
    }
}
