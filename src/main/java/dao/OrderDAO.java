package dao;

import model.ClientOrder;
import model.Product;

public class OrderDAO extends AbstractDAO<ClientOrder> {

    private final ProductDAO productDAO = new ProductDAO();

    @Override
    public int insert(ClientOrder t) {
        int insertedID = super.insert(t);
        Product product = productDAO.findById(t.getIdProduct());
        product.setQuantity(product.getQuantity() - t.getQuantity());
        productDAO.update(product.getIdProduct(), product);
        return insertedID;
    }

    @Override
    public void update(int id, ClientOrder t) {
        ClientOrder order = this.findById(id);
        Product product = productDAO.findById(order.getIdProduct());
        product.setQuantity(product.getQuantity() + order.getQuantity());
        productDAO.update(product.getIdProduct(), product);

        super.update(id, t);

        product = productDAO.findById(t.getIdProduct());
        product.setQuantity(product.getQuantity() - t.getQuantity());
        productDAO.update(product.getIdProduct(), product);
    }

    @Override
    public void delete(int id) {
        ClientOrder order = this.findById(id);
        Product product = productDAO.findById(order.getIdProduct());
        product.setQuantity(product.getQuantity() + order.getQuantity());
        productDAO.update(product.getIdProduct(), product);

        super.delete(id);
    }

}
