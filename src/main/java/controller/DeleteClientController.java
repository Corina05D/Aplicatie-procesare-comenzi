package controller;

import bll.ClientBLL;
import view.ClientView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteClientController implements ActionListener {
    private final ClientBLL clientBLL;
    private final ClientView clientView;

    public DeleteClientController(ClientView clientView) {
        this.clientView = clientView;
        this.clientBLL = new ClientBLL();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int idClient = Integer.parseInt(clientView.getDeleteClientId());
            clientBLL.deleteClient(idClient);
            clientView.showSuccess("Clientul cu id " + idClient + " a fost sters cu succes!");
        } catch (NumberFormatException ex) {
            clientView.showError("ID Client invalid!");
        } catch (Exception ex) {
            clientView.showError(ex.getMessage());
        }
    }
}
