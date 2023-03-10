package controller;

import bll.OrderBLL;
import view.OrderView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteOrderController implements ActionListener {
    private final OrderBLL orderBLL;
    private final OrderView orderView;

    public DeleteOrderController(OrderView orderView) {
        this.orderBLL = new OrderBLL();
        this.orderView = orderView;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int idOrder = Integer.parseInt(orderView.getDeleteOrderId());
            orderBLL.deleteOrder(idOrder);
            orderView.showSuccess("Comanda cu id " + idOrder + " a fost stearsa cu succes!");
        } catch (NumberFormatException ex) {
            orderView.showError("ID Comanda invalid!");
        } catch (Exception ex) {
            orderView.showError(ex.getMessage());
        }
    }
}
