package controller;

import bll.OrderBLL;
import model.ClientOrder;
import view.EditOrderView;
import view.OrderView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EditOrderController implements ActionListener {
    private final OrderView orderView;
    private final OrderBLL orderBLL;
    private final ArrayList<Integer> clientIds;
    private final ArrayList<Integer> productIds;

    public EditOrderController(OrderView orderView, List<Integer> clientIds, List<Integer> productIds) {
        this.orderView = orderView;
        this.orderBLL = new OrderBLL();
        this.clientIds= (ArrayList<Integer>) clientIds;
        this.productIds = (ArrayList<Integer>) productIds;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int idOrder = Integer.parseInt(orderView.getEditOrderId());
            ClientOrder o = orderBLL.findOrderById(idOrder);
            EditOrderView editOrderView = new EditOrderView(clientIds, productIds, o);
            editOrderView.addSubmitListener(e1 -> {
                try {
                    int quantity = Integer.parseInt(editOrderView.getQuantity());
                    if (quantity <= 0) {
                        throw new NumberFormatException();
                    }
                    orderBLL.updateOrder(idOrder, new ClientOrder(editOrderView.getClientId(), editOrderView.getProductId(), quantity));
                    editOrderView.showSuccess("Comanda cu id " + idOrder + " a fost modificata cu succes!");
                } catch (NumberFormatException ex) {
                    editOrderView.showError("Introduceti o cantitate valida!");
                } catch (Exception ex) {
                    editOrderView.showError(ex.getMessage());
                }
            });
            editOrderView.setVisible(true);
        } catch (NumberFormatException ex) {
            orderView.showError("ID Comanda invalid!");
        } catch (Exception ex) {
            orderView.showError(ex.getMessage());
        }
    }
}