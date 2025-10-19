package bd.edu.seu.lendengo.services;

import bd.edu.seu.lendengo.interfaces.ProductAttributeInterface;
import bd.edu.seu.lendengo.models.ProductAttribute;
import bd.edu.seu.lendengo.models.User;
import bd.edu.seu.lendengo.utility.ConnectionSingleton;
import javafx.scene.control.Alert;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ProductAttributeService implements ProductAttributeInterface {
    @Override
    public int insert(ProductAttribute attribute) {
        Connection connection = ConnectionSingleton.getConnection();
        String query = "INSERT INTO product_attributes(type, name) VALUES(?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, attribute.getType());
            preparedStatement.setString(2, attribute.getName());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Duplicate Entry");
                alert.setHeaderText(null);
                alert.setContentText("An Entry already exists with that name!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Database Error");
                alert.setHeaderText("Failed to create user");
                alert.setContentText("An error occurred while saving the user. Please check your data and try again.");
                alert.showAndWait();
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public int update(ProductAttribute attribute) {
            Connection connection = ConnectionSingleton.getConnection();
            String query = "UPDATE product_attributes SET type = ?, name = ? WHERE id = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, attribute.getType());
                preparedStatement.setString(2, attribute.getName());
                preparedStatement.setInt(3, attribute.getId());

                return preparedStatement.executeUpdate();
            } catch (SQLException e) {
                if (e.getErrorCode() == 1062) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Duplicate Entry");
                    alert.setHeaderText(null);
                    alert.setContentText("An Entry already exists with that name!");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Database Error");
                    alert.setHeaderText("Failed to create user");
                    alert.setContentText("An error occurred while saving the user. Please check your data and try again.");
                    alert.showAndWait();
                    e.printStackTrace();
                }
            }
            return 0;
    }

    @Override
    public int delete(ProductAttribute attribute) {
        Connection connection = ConnectionSingleton.getConnection();
        String query = "DELETE FROM product_attributes WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, attribute.getId());
            int effectedLines = preparedStatement.executeUpdate();
            if (effectedLines > 0) {
                return effectedLines;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<ProductAttribute> getAllAttributes() {
        ArrayList<ProductAttribute> attributeList = new ArrayList<>();
        Connection connection = ConnectionSingleton.getConnection();
        String query = "SELECT * FROM product_attributes";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");


                attributeList.add(new ProductAttribute(id, type, name));
            }
            if(!attributeList.isEmpty()) {
                return attributeList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
