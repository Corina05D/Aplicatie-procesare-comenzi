package controller;

import view.ProductView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShowProductController implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        ProductView productView = new ProductView();
        productView.addAddListener(new AddProductController());
        productView.addEditListener(new EditProductController(productView));
        productView.addDeleteListener(new DeleteProductController(productView));
        productView.addViewListener(new ShowTableController("Produs"));
        productView.setVisible(true);
    }
}
