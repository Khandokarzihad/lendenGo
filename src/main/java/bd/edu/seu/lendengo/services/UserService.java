package bd.edu.seu.lendengo.services;

import bd.edu.seu.lendengo.interfaces.UserInterface;
import bd.edu.seu.lendengo.models.User;
import bd.edu.seu.lendengo.utility.ConnectionSingleton;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserService implements UserInterface {
    @Override
    public void insert(User user) {
        Connection connection = ConnectionSingleton.getConnection();
        String query = "INSERT INTO users(name, email, mobile, role, dob, status, password, img) VALUES(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getMobile());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setDate(5, Date.valueOf(user.getDob()));
            preparedStatement.setString(6, user.getStatus());
            preparedStatement.setString(7, user.getPassword());
            preparedStatement.setBytes(8, user.getImage());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public int update(User user) {
        Connection connection = ConnectionSingleton.getConnection();
        String query = "UPDATE users SET name = ?, email = ?, mobile = ?, role = ?, dob = ?, status = ?, password = ?, img = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getMobile());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setDate(5, Date.valueOf(user.getDob()));
            preparedStatement.setString(6, user.getStatus());
            preparedStatement.setString(7, user.getPassword());
            preparedStatement.setBytes(8, user.getImage());
            preparedStatement.setInt(9, user.getId());
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
        String query = "DELETE FROM users WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user.getId());
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
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String mobile = resultSet.getString("mobile");
                String role = resultSet.getString("role");
                LocalDate dob = resultSet.getDate("dob").toLocalDate();
                String status = resultSet.getString("status");
                String password = resultSet.getString("password");
                byte[] image = resultSet.getBytes("img");

                userList.add(new User(id, name, email, mobile, role, dob, status, password, image));
            }
            if(!userList.isEmpty()) {
                return userList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User getUser(String UserEmail, String UserPassword, String userRole) {
        Connection connection = ConnectionSingleton.getConnection();
        String query = "SELECT * FROM users WHERE email = ? AND password = ? AND role = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, UserEmail);
            preparedStatement.setString(2, UserPassword);
            preparedStatement.setString(3, userRole);

            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String mobile = resultSet.getString("mobile");
                String role = resultSet.getString("role");
                LocalDate dob = resultSet.getDate("dob").toLocalDate();
                String status = resultSet.getString("status");
                String password = resultSet.getString("password");
                byte[] image = resultSet.getBytes("img");
                return new User(id, name, email, mobile, role, dob, status, password, image);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
