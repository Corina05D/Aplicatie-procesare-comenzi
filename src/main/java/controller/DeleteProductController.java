package controller;

import bll.ProductBLL;
import view.ProductView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteProductController implements ActionListener {
    private final ProductView productView;
    private final ProductBLL productBLL;

    public DeleteProductController(ProductView productView) {
        this.productView = productView;
        this.productBLL = new ProductBLL();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int idProduct = Integer.parseInt(productView.getDeleteOrderId());
            productBLL.deleteProduct(idProduct);
            productView.showSuccess("Produsul cu id " + idProduct + " a fost sters cu succes!");
        } catch (NumberFormatException ex) {
            productView.showError("ID Produs invalid!");
        } catch (Exception ex) {
            productView.showError(ex.getMessage());
        }
    }
}