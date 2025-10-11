package bd.edu.seu.lendengo.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class User {
    private int id;
    private String name;
    private String email;
    private String mobile;
    private String role;
    private LocalDate dob;
    private String status;
    private String password;
    private byte[] image;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public User() {
    }

    public User(String name, String email, String mobile, String role, LocalDate dob, String status, String password, byte[] image) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.role = role;
        this.dob = dob;
        this.status = status;
        this.password = password;
        this.image = image;
    }

    public User(int id, String name, String email, String mobile, String role, LocalDate dob, String status, String password, byte[] image, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.role = role;
        this.dob = dob;
        this.status = status;
        this.password = password;
        this.image = image;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return "+880 " + mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}

