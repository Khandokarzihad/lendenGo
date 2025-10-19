package bd.edu.seu.lendengo.interfaces;

import bd.edu.seu.lendengo.models.Product;
import bd.edu.seu.lendengo.models.User;

import java.util.ArrayList;

public interface ProductInterface {
    public int insert(Product product);
    public int update(Product product);
    public int delete(Product product);
    public ArrayList<Product> getAllProducts();
    public Product getProduct(String code);
}
