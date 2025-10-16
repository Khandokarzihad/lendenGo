package bd.edu.seu.lendengo.services;

import bd.edu.seu.lendengo.interfaces.NoticeInterface;
import bd.edu.seu.lendengo.models.Notice;
import bd.edu.seu.lendengo.models.User;
import bd.edu.seu.lendengo.utility.ConnectionSingleton;
import javafx.scene.control.Alert;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class NoticeService implements NoticeInterface {
    @Override
    public int insertNotice(Notice notice) {
        Connection connection = ConnectionSingleton.getConnection();
        String query = "INSERT INTO notices(title, content, type, created_by, status) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, notice.getTitle());
            preparedStatement.setString(2, notice.getContent());
            preparedStatement.setString(3, notice.getType());
            preparedStatement.setString(4, notice.getCreatedBy());
            preparedStatement.setString(5, notice.getStatus());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int updateNotice(Notice notice) {
        Connection connection = ConnectionSingleton.getConnection();
        String query = "UPDATE notices SET title = ?, content = ?, type = ?, created_by = ?, status = ? WHERE id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, notice.getTitle());
            preparedStatement.setString(2, notice.getContent());
            preparedStatement.setString(3, notice.getType());
            preparedStatement.setString(4, notice.getCreatedBy());
            preparedStatement.setString(5, notice.getStatus());
            preparedStatement.setInt(6, notice.getId());

            int effectedLines = preparedStatement.executeUpdate();
            if (effectedLines > 0) {
                return effectedLines;
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Error");
            alert.setHeaderText("Failed to update user");
            alert.setContentText("An error occurred while saving the user. Please check your data and try again.");
            alert.showAndWait();
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Notice getActiveNotice() {
        Connection connection = ConnectionSingleton.getConnection();
        String query = "SELECT * FROM notices WHERE status = 'active' ORDER BY modified_at DESC LIMIT 1";

        try (
             PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet rs = preparedStatement.executeQuery()) {

            if (rs.next()) {
                Notice notice = new Notice();
                notice.setId(rs.getInt("id"));
                notice.setTitle(rs.getString("title"));
                notice.setContent(rs.getString("content"));
                notice.setType(rs.getString("type"));
                notice.setCreatedBy(rs.getString("created_by"));
                notice.setStatus(rs.getString("status"));
                notice.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                notice.setModifiedAt(rs.getTimestamp("modified_at").toLocalDateTime());
                return notice;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // no active notice found
    }

    @Override
    public ArrayList<Notice> getAllNotices() {
        ArrayList<Notice> noticeList = new ArrayList<>();
        Connection connection = ConnectionSingleton.getConnection();
        String query = "SELECT * FROM notices";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                String type = resultSet.getString("type");
                String status = resultSet.getString("status");
                String createdBy = resultSet.getString("created_by");
                LocalDateTime createdAt = resultSet.getObject("created_at", LocalDateTime.class);
                LocalDateTime modifiedAt = resultSet.getObject("modified_at", LocalDateTime.class);


                noticeList.add(new Notice(id, title, content, type, status, createdBy, createdAt, modifiedAt));
            }
            if(!noticeList.isEmpty()) {
                return noticeList;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
