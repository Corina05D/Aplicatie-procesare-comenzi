package controller;
import bll.ProductBLL;
import model.Product;
import view.AddProductView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddProductController implements ActionListener {
    private final ProductBLL productBLL;

    public AddProductController() {
        productBLL = new ProductBLL();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AddProductView addProductView = new AddProductView();
        addProductView.addSubmitListener(e1 -> {
            try {
                int quantity = Integer.parseInt(addProductView.getQuantity());
                int price = Integer.parseInt(addProductView.getPrice());
                if (addProductView.getProductName().equals("") || quantity < 0 || price < 0) {
                    throw new NumberFormatException();
                }
                int productId = productBLL.insertProduct(new Product(addProductView.getProductName(), quantity, price));
                addProductView.showSuccess("Produsul cu id " + productId + " a fost adaugat cu succes!");

            } catch (NumberFormatException ex) {
                addProductView.showError("Verificati daca toate campurile au fost completate corect!");
            } catch (Exception ex) {
                addProductView.showError(ex.getMessage());
            }

        });
        addProductView.setVisible(true);
    }
}
