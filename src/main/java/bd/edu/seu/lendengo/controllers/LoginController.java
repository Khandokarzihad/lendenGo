package bd.edu.seu.lendengo.controllers;

import bd.edu.seu.lendengo.HelloApplication;
import bd.edu.seu.lendengo.models.User;
import bd.edu.seu.lendengo.services.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userLoginPanel.setVisible(true);
        adminLoginPanel.setVisible(false);

        userButton.getStyleClass().add("roleBtn-active");
        adminButton.getStyleClass().add("roleBtn-default");
        role = "Employee";
    }

    public String role;
    public static User user;

    @FXML
    public Button adminButton;

    @FXML
    public TextField adminEmailField;

    @FXML
    public VBox adminLoginPanel;

    @FXML
    public PasswordField adminPasswordField;

    @FXML
    public HBox hBox;

    @FXML
    public Button userButton;

    @FXML
    public TextField userEmailField;

    @FXML
    public VBox userLoginPanel;

    @FXML
    public PasswordField userPasswordField;

    @FXML
    public Label welcomeLabel;

    @FXML
    public Pane welcomePan;

    @FXML
    public void adminEvent(ActionEvent event) {
        userLoginPanel.setVisible(false);
        adminLoginPanel.setVisible(true);

        adminButton.getStyleClass().removeAll("roleBtn-default");
        adminButton.getStyleClass().add("roleBtn-active");

        userButton.getStyleClass().removeAll("roleBtn-active");
        userButton.getStyleClass().add("roleBtn-default");
        userEmailField.clear();
        userPasswordField.clear();
        role = "Admin";
    }

    @FXML
    public void userEvent(ActionEvent event) {
        userLoginPanel.setVisible(true);
        adminLoginPanel.setVisible(false);

        userButton.getStyleClass().removeAll("roleBtn-default");
        userButton.getStyleClass().add("roleBtn-active");

        adminButton.getStyleClass().removeAll("roleBtn-active");
        adminButton.getStyleClass().add("roleBtn-default");
        adminEmailField.clear();
        adminPasswordField.clear();
        role = "Employee";
    }

    @FXML
    public void loginEvent(ActionEvent event) {
        String email, password;
        if(role.equals("Admin")) {
            email = adminEmailField.getText();
            password = adminPasswordField.getText();

        }
        else{
            email = userEmailField.getText();
            password = userPasswordField.getText();
        }

        UserService userService = new UserService();
        user = userService.getUser(email, password, role);
        if(user == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Wrong Credentials");
            alert.setContentText("Email and Password Do Not Match.");
            alert.showAndWait();
        }
        else{
            HelloApplication helloApplication = new HelloApplication();
            helloApplication.changeScene("dashboard", "Dashboard");
        }


    }


}
