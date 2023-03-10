package bll;

import java.util.List;
import java.util.NoSuchElementException;

import bll.validators.EmailValidator;
import bll.validators.Validator;
import dao.ClientDAO;
import model.Client;

public class ClientBLL {

    private final Validator<Client> validator;
    private final ClientDAO clientDAO;

    public ClientBLL() {
        validator = new EmailValidator();
        clientDAO = new ClientDAO();
    }

    public List<Client> getAllClients() {
        return clientDAO.findAll();
    }

    public int insertClient(Client client) {
        validator.validate(client);
        return clientDAO.insert(client);
    }

    public void updateClient(int idClient, Client client) {
        if (clientDAO.findById(idClient) == null) {
            throw new NoSuchElementException("Clientul cu id " + idClient + " nu a fost gasit!");
        }
        validator.validate(client);
        clientDAO.update(idClient, client);
    }

    public void deleteClient(int id) {
        if (clientDAO.findById(id) == null) {
            throw new NoSuchElementException("Clientul cu id " + id + " nu a fost gasit!");
        }
        clientDAO.delete(id);
    }

    public Client findClientById(int id) {
        Client c = clientDAO.findById(id);
        if (c == null) {
            throw new NoSuchElementException("Clientul cu id " + id + " nu a fost gasit!");
        }
        return c;
    }

    public List<String> createTableHeader() {
        return clientDAO.createTableHeader();
    }

    public List<List<Object>> createTableData() {
        return clientDAO.createTableData();
    }

}
