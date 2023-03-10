package bll;

import bll.validators.QuantityValidator;
import bll.validators.Validator;
import dao.OrderDAO;
import model.ClientOrder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

public class OrderBLL {

    private final Validator<ClientOrder> validator;
    private final OrderDAO orderDAO;

    public OrderBLL() {
        validator = new QuantityValidator();
        orderDAO = new OrderDAO();
    }

    private void writeBill(int id, ClientOrder order) {
        try {
            ClientBLL clientBLL = new ClientBLL();
            ProductBLL productBLL = new ProductBLL();
            FileWriter fileWriter = new FileWriter("Factura_" + id + ".txt");
            StringBuilder sb = new StringBuilder();
            sb.append("Id factura: ").append(id).append("\n\n");
            sb.append(clientBLL.findClientById(order.getIdClient()));
            sb.append("\n\nProdus: ").append(productBLL.findProductById(order.getIdProduct()).getName());
            sb.append("\nCantitate: ").append(order.getQuantity());
            int totalPrice = productBLL.findProductById((order.getIdProduct())).getPrice() * order.getQuantity();
            sb.append("\nPret total: ").append(totalPrice).append("$");
            fileWriter.write(sb.toString());
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteBill(int id) {
        File file = new File("Factura_" + id + ".txt");
        file.delete();
    }

    public int insertOrder(ClientOrder order) {
        validator.validate(order);
        int orderId = orderDAO.insert(order);
        writeBill(orderId, order);
        return orderId;
    }

    public void updateOrder(int idOrder, ClientOrder order) {
        if (orderDAO.findById(idOrder) == null) {
            throw new NoSuchElementException("Comanda cu id " + idOrder + " nu a fost gasita!");
        }
        validator.validate(order);
        orderDAO.update(idOrder, order);
        writeBill(idOrder, order);
    }

    public void deleteOrder(int id) {
        if (orderDAO.findById(id) == null) {
            throw new NoSuchElementException("Comanda cu id " + id + " nu a fost gasita!");
        }
        orderDAO.delete(id);
        deleteBill(id);
    }

    public ClientOrder findOrderById(int id) {
        ClientOrder o = orderDAO.findById(id);
        if (o == null) {
            throw new NoSuchElementException("Comanda cu id " + id + " nu a fost gasita!");
        }
        return o;
    }

    public List<String> createTableHeader() {
        return orderDAO.createTableHeader();
    }

    public List<List<Object>> createTableData() {
        return orderDAO.createTableData();
    }

}
