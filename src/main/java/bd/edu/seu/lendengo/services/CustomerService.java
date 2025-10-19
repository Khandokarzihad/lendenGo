package bd.edu.seu.lendengo.services;

import bd.edu.seu.lendengo.interfaces.CustomerInterface;
import bd.edu.seu.lendengo.models.Customer;
import bd.edu.seu.lendengo.utility.ConnectionSingleton;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;

public class CustomerService implements CustomerInterface {

    @Override
    public int insertCustomer(Customer customer) {
        Connection connection = ConnectionSingleton.getConnection();
        String query = "INSERT INTO customers (name, email, mobile, gender, dob, status) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getMobile());
            preparedStatement.setString(4, customer.getGender());
            preparedStatement.setString(5, customer.getDob());
            preparedStatement.setString(6, customer.getStatus());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Customer Added");
                alert.setHeaderText(null);
                alert.setContentText("Customer has been added successfully!");
                alert.showAndWait();
                return affectedRows;
            }

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) { // Duplicate entry
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Duplicate Entry");
                alert.setHeaderText(null);
                alert.setContentText("A customer with this email or mobile already exists!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Database Error");
                alert.setHeaderText("Failed to insert customer");
                alert.setContentText("An error occurred while saving the customer. Please try again.");
                alert.showAndWait();
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public int updateCustomer(Customer customer) {
        Connection connection = ConnectionSingleton.getConnection();
        String query = "UPDATE customers SET name = ?, email = ?, mobile = ?, gender = ?, dob = ?, status = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getMobile());
            preparedStatement.setString(4, customer.getGender());
            preparedStatement.setString(5, customer.getDob());
            preparedStatement.setString(6, customer.getStatus());
            preparedStatement.setInt(7, customer.getId());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Customer Updated");
                alert.setHeaderText(null);
                alert.setContentText("Customer information has been updated successfully!");
                alert.showAndWait();
                return affectedRows;
            }

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Error");
            alert.setHeaderText("Failed to update customer");
            alert.setContentText("An error occurred while updating the customer. Please check your data and try again.");
            alert.showAndWait();
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteCustomer(Customer customer) {
        Connection connection = ConnectionSingleton.getConnection();
        String query = "DELETE FROM customers WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, customer.getId());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Customer Deleted");
                alert.setHeaderText(null);
                alert.setContentText("Customer has been deleted successfully!");
                alert.showAndWait();
                return affectedRows;
            }

        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Error");
            alert.setHeaderText("Failed to delete customer");
            alert.setContentText("An error occurred while deleting the customer. Please try again.");
            alert.showAndWait();
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<Customer> getAllCustomers() {
        ArrayList<Customer> customers = new ArrayList<>();
        Connection connection = ConnectionSingleton.getConnection();
        String query = "SELECT * FROM customers";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String mobile = resultSet.getString("mobile");
                String gender = resultSet.getString("gender");
                String dob = resultSet.getString("dob");
                String status = resultSet.getString("status");

                customers.add(new Customer(id, name, email, mobile, gender, dob, status));
            }
            return customers;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
