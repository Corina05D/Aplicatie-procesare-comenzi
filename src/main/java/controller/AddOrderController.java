package controller;

import bll.OrderBLL;
import model.ClientOrder;
import view.AddOrderView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AddOrderController implements ActionListener {
    private final OrderBLL orderBLL;
    private final ArrayList<Integer> clientIds;
    private final ArrayList<Integer> productIds;

    public AddOrderController(List<Integer> clientIds, List<Integer> productIds) {
        this.orderBLL = new OrderBLL();
        this.clientIds = (ArrayList<Integer>) clientIds;
        this.productIds = (ArrayList<Integer>) productIds;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AddOrderView addOrderView = new AddOrderView(clientIds, productIds);
        addOrderView.addSubmitListener(e1 -> {
            try {
                int quantity = Integer.parseInt(addOrderView.getQuantity());
                if (quantity <= 0) {
                    throw new NumberFormatException();
                }
                int orderId = orderBLL.insertOrder(new ClientOrder(addOrderView.getClientId(), addOrderView.getProductId(), quantity));
                addOrderView.showSuccess("Comanda cu  id " + orderId + " a fost adaugata cu succes!");
            } catch (NumberFormatException ex) {
                addOrderView.showError("Introduceti o cantitate valida!");
            } catch (Exception ex) {
                addOrderView.showError(ex.getMessage());
            }

        });
        addOrderView.setVisible(true);
    }
}
