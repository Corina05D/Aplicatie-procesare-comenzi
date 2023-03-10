package controller;

import bll.ClientBLL;
import bll.ProductBLL;
import model.Client;
import model.Product;
import view.OrderView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ShowOrderController implements ActionListener {
    private final ClientBLL clientBLL;
    private final ProductBLL productBLL;

    public ShowOrderController() {
        clientBLL = new ClientBLL();
        productBLL = new ProductBLL();
    }

    private ArrayList<Integer> generateClientIds() {
        ArrayList<Integer> clientIds = new ArrayList<>();
        for (Client c : clientBLL.getAllClients()) {
            clientIds.add(c.getIdClient());
        }
        return clientIds;
    }

    private ArrayList<Integer> generateProductIds() {
        ArrayList<Integer> productIds = new ArrayList<>();
        for (Product p : productBLL.getAllProducts()) {
            productIds.add(p.getIdProduct());
        }
        return productIds;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        OrderView orderView = new OrderView();
        orderView.addAddListener(new AddOrderController(generateClientIds(), generateProductIds()));
        orderView.addEditListener(new EditOrderController(orderView, generateClientIds(), generateProductIds()));
        orderView.addDeleteListener(new DeleteOrderController(orderView));
        orderView.addViewListener(new ShowTableController("Comanda"));
        orderView.setVisible(true);
    }
}
