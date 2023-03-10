package bll;

import dao.ProductDAO;
import model.Product;

import java.util.List;
import java.util.NoSuchElementException;

public class ProductBLL {

    private final ProductDAO productDAO;

    public ProductBLL() {
        productDAO = new ProductDAO();
    }

    public List<Product> getAllProducts() {
        return productDAO.findAll();
    }

    public int insertProduct(Product product) {
        return productDAO.insert(product);
    }

    public void updateProduct(int idProduct, Product product) {
        if (productDAO.findById(idProduct) == null) {
            throw new NoSuchElementException("Produsul cu id " + idProduct + " nu a fost gasit!");
        }
        productDAO.update(idProduct, product);
    }

    public void deleteProduct(int id) {
        if (productDAO.findById(id) == null) {
            throw new NoSuchElementException("Produsul cu id " + id + " nu a fost gasit!");
        }
        productDAO.delete(id);
    }

    public List<String> createTableHeader() {
        return productDAO.createTableHeader();
    }

    public List<List<Object>> createTableData() {
        return productDAO.createTableData();
    }

    public Product findProductById(int id) {
        Product p = productDAO.findById(id);
        if (p == null) {
            throw new NoSuchElementException("Produsul cu id " + id + " nu a fost gasit!");
        }
        return p;
    }

}
