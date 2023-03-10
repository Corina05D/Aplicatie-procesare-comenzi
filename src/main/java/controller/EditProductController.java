package controller;

import bll.ProductBLL;
import model.Product;
import view.EditProductView;
import view.ProductView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditProductController implements ActionListener {
    private final ProductView productView;
    private final ProductBLL productBLL;

    public EditProductController(ProductView productView) {
        this.productView = productView;
        this.productBLL = new ProductBLL();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int idProduct = Integer.parseInt(productView.getEditOrderId());
            Product p = productBLL.findProductById(idProduct);
            EditProductView editProductView = new EditProductView(p);
            editProductView.addSubmitListener(e1 -> {
                try {
                    int quantity = Integer.parseInt(editProductView.getQuantity());
                    int price = Integer.parseInt(editProductView.getPrice());
                    if (editProductView.getProductName().equals("") || quantity < 0 || price < 0) {
                        throw new NumberFormatException();
                    }
                    productBLL.updateProduct(idProduct, new Product(editProductView.getProductName(), quantity, price));
                    editProductView.showSuccess("Produsul cu id " + idProduct + " a fost modificat cu succes!");

                } catch (NumberFormatException ex) {
                    editProductView.showError("Verificati daca ati completat campurile corect!");
                } catch (Exception ex) {
                    editProductView.showError(ex.getMessage());
                }
            });
            editProductView.setVisible(true);
        } catch (NumberFormatException ex) {
            productView.showError("ID Produs invalid!");
        } catch (Exception ex) {
            productView.showError(ex.getMessage());
        }
    }
}