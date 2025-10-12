package bd.edu.seu.lendengo.interfaces;

import bd.edu.seu.lendengo.models.User;

import java.util.ArrayList;

public interface UserInterface {
    public int insert(User user);
    public int update(User user);
    public int delete(User user);
    public ArrayList<User> getAllUsers();
    public User getUser(String email, String password, String role);
}
