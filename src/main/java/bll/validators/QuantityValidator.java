package bll.validators;
import dao.ProductDAO;
import model.ClientOrder;

public class QuantityValidator implements Validator<ClientOrder> {

    private final ProductDAO productDAO = new ProductDAO();

    @Override
    public void validate(ClientOrder order) {
        int currentStock = productDAO.findById(order.getIdProduct()).getQuantity();
        if (currentStock < order.getQuantity()) {
            if (currentStock == 0) {
                throw new IllegalArgumentException("Nu sunt produse in stoc!");
            } else if (currentStock == 1) {
                throw new IllegalArgumentException("Este doar un produs in stoc!");
            } else {
                throw new IllegalArgumentException("Sunt doar " + currentStock + " produse in stoc!");
            }
        }
    }
}
