package bd.edu.seu.lendengo.models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Product {
    private byte[] image;
    private String productName;
    private String productCode;
    private String productDescription;
    private double productSellPrice;
    private double productPurchasePrice;
    private String productCategory;
    private String ProductBrand;
    private String ProductUnit;

    private final IntegerProperty stock = new SimpleIntegerProperty();
    private final IntegerProperty productQuantity = new SimpleIntegerProperty();
    private final DoubleProperty subtotalPrice = new SimpleDoubleProperty();

    public Product() {
    }

    public Product(byte[] image, String productName, String productCode, String productDescription, double productSellPrice, double productPurchasePrice, String productCategory, String productBrand, String productUnit) {
        this.image = image;
        this.productName = productName;
        this.productCode = productCode;
        this.productDescription = productDescription;
        this.productSellPrice = productSellPrice;
        this.productPurchasePrice = productPurchasePrice;
        this.productCategory = productCategory;
        ProductBrand = productBrand;
        ProductUnit = productUnit;
    }

    public Product(Product other) {
        this.image = other.image; // shallow copy is fine for byte[] if immutable
        this.productName = other.productName;
        this.productCode = other.productCode;
        this.productDescription = other.productDescription;
        this.productPurchasePrice = other.productPurchasePrice;
        this.productSellPrice = other.productSellPrice;
        this.productCategory = other.productCategory;
        this.ProductBrand = other.ProductBrand;
        this.ProductUnit = other.ProductUnit;

        this.setProductQuantity(other.getProductQuantity());
        this.setSubtotalPrice(other.getSubtotalPrice());
    }



    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public double getProductSellPrice() {
        return productSellPrice;
    }

    public void setProductSellPrice(double productSellPrice) {
        this.productSellPrice = productSellPrice;
    }

    public double getProductPurchasePrice() {
        return productPurchasePrice;
    }

    public void setProductPurchasePrice(double productPurchasePrice) {
        this.productPurchasePrice = productPurchasePrice;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }


    public String getProductBrand() {
        return ProductBrand;
    }

    public void setProductBrand(String productBrand) {
        ProductBrand = productBrand;
    }

    public String getProductUnit() {
        return ProductUnit;
    }

    public void setProductUnit(String productUnit) {
        ProductUnit = productUnit;
    }

    public int getStock() {
        return stock.get();
    }

    public void setStock(int stock) {
        this.stock.set(stock);
    }

    public IntegerProperty stockProperty() { return stock; }
    public IntegerProperty productQuantityProperty() { return productQuantity; }
    public DoubleProperty subtotalPriceProperty() { return subtotalPrice; }

    public int getProductQuantity() { return productQuantity.get(); }
    public void setProductQuantity(int qty) { productQuantity.set(qty); }

    public double getSubtotalPrice() { return subtotalPrice.get(); }
    public void setSubtotalPrice(double val) {
        val = Math.round(val*100.0)/100.0;
        subtotalPrice.set(val);
    }

}
