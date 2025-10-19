package bd.edu.seu.lendengo.services;

import bd.edu.seu.lendengo.interfaces.ProductInterface;
import bd.edu.seu.lendengo.models.Product;
import bd.edu.seu.lendengo.utility.ConnectionSingleton;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;

public class ProductService implements ProductInterface {

    @Override
    public int insert(Product product) {
        Connection connection = ConnectionSingleton.getConnection();
        String query = "INSERT INTO products (code, name, description, short_description, purchase_price, sale_price, category, brand, unit, image, stock) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getProductCode());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setString(3, product.getProductDescription());
            preparedStatement.setString(4, product.getShortDescription());
            preparedStatement.setDouble(5, product.getProductPurchasePrice());
            preparedStatement.setDouble(6, product.getProductSellPrice());
            preparedStatement.setString(7, product.getProductCategory());
            preparedStatement.setString(8, product.getProductBrand());
            preparedStatement.setString(9, product.getProductUnit());
            preparedStatement.setBytes(10, product.getImage());
            preparedStatement.setInt(11, product.getStock());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                showWarning("Duplicate Product", "A product with this code already exists!");
            } else {
                showError("Database Error", "Failed to add product. Please check the data and try again.", e);
            }
        }
        return 0;
    }

    @Override
    public int update(Product product) {
        Connection connection = ConnectionSingleton.getConnection();
        String query = "UPDATE products SET name = ?, description = ?, short_description = ?, purchase_price = ?, sale_price = ?, " +
                "category = ?, brand = ?, unit = ?, image = ?, stock = ? WHERE code = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setString(2, product.getProductDescription());
            preparedStatement.setString(3, product.getShortDescription());
            preparedStatement.setDouble(4, product.getProductPurchasePrice());
            preparedStatement.setDouble(5, product.getProductSellPrice());
            preparedStatement.setString(6, product.getProductCategory());
            preparedStatement.setString(7, product.getProductBrand());
            preparedStatement.setString(8, product.getProductUnit());
            preparedStatement.setBytes(9, product.getImage());
            preparedStatement.setInt(10, product.getStock());
            preparedStatement.setString(11, product.getProductCode());

            int affected = preparedStatement.executeUpdate();
            if (affected > 0) return affected;
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                showWarning("Duplicate Product", "A product with this code already exists!");
            } else {
                showError("Database Error", "Failed to update product.", e);
            }
        }
        return 0;
    }

    @Override
    public int delete(Product product) {
        Connection connection = ConnectionSingleton.getConnection();
        String query = "DELETE FROM products WHERE code = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, product.getProductCode());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            showError("Database Error", "Failed to delete product.", e);
        }
        return 0;
    }

    @Override
    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> productList = new ArrayList<>();
        Connection connection = ConnectionSingleton.getConnection();
        String query = "SELECT * FROM products";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = extractProductFromResultSet(resultSet);
                productList.add(product);
            }
            return productList;
        } catch (SQLException e) {
            showError("Database Error", "Failed to fetch product list.", e);
        }
        return null;
    }

    @Override
    public Product getProduct(String code) {
        Connection connection = ConnectionSingleton.getConnection();
        String query = "SELECT * FROM products WHERE code = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, code);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return extractProductFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            showError("Database Error", "Failed to fetch product.", e);
        }
        return null;
    }

    private Product extractProductFromResultSet(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setProductCode(resultSet.getString("code"));
        product.setProductName(resultSet.getString("name"));
        product.setProductDescription(resultSet.getString("description"));
        product.setShortDescription(resultSet.getString("short_description"));
        product.setProductPurchasePrice(resultSet.getDouble("purchase_price"));
        product.setProductSellPrice(resultSet.getDouble("sale_price"));
        product.setProductCategory(resultSet.getString("category"));
        product.setProductBrand(resultSet.getString("brand"));
        product.setProductUnit(resultSet.getString("unit"));
        product.setImage(resultSet.getBytes("image"));
        product.setStock(resultSet.getInt("stock"));
        return product;
    }


    private void showWarning(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showError(String title, String message, Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
        e.printStackTrace();
    }
}
