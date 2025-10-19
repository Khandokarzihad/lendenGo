package bd.edu.seu.lendengo.interfaces;

import bd.edu.seu.lendengo.models.Notice;
import bd.edu.seu.lendengo.models.ProductAttribute;
import bd.edu.seu.lendengo.models.User;

import java.util.ArrayList;
import java.util.jar.Attributes;

public interface ProductAttributeInterface {
    public int insert(ProductAttribute attribute);
    public int update(ProductAttribute attribute);
    public int delete(ProductAttribute attribute);
    public ArrayList<ProductAttribute> getAllAttributes();
}
