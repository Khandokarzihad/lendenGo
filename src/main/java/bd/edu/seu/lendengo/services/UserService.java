package bd.edu.seu.lendengo.services;

import bd.edu.seu.lendengo.interfaces.UserInterface;
import bd.edu.seu.lendengo.models.User;
import bd.edu.seu.lendengo.utility.ConnectionSingleton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService implements UserInterface {
    @Override
    public void insert(User user) {
        Connection connection = ConnectionSingleton.getConnection();
        String query = "INSERT INTO users VALUES(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getRole());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public int update(User user) {
        Connection connection = ConnectionSingleton.getConnection();
        String query = "UPDATE users SET password = ? WHERE email = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getEmail());
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
    public int delete(User user) {
        Connection connection = ConnectionSingleton.getConnection();
        String query = "DELETE FROM users WHERE email = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getEmail());
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
    public ArrayList<User> getAllUsers() {
        ArrayList<User> userList = new ArrayList<>();
        Connection connection = ConnectionSingleton.getConnection();
        String query = "SELECT * FROM users";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                String username = resultSet.getString("name");
                String userEmail = resultSet.getString("email");
                String userPassword = resultSet.getString("password");
                String userRole = resultSet.getString("role");

                userList.add(new User(username, userEmail, userPassword, userRole));
            }
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUser(String email, String password, String role) {
        Connection connection = ConnectionSingleton.getConnection();
        String query = "SELECT * FROM users WHERE email = ? AND password = ? AND role = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, role);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                String username = resultSet.getString("name");
                String userEmail = resultSet.getString("email");
                String userPassword = resultSet.getString("password");
                String userRole = resultSet.getString("role");
                return new User(username, userEmail, userPassword, userRole);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
