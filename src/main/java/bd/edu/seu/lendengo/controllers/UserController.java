package bd.edu.seu.lendengo.controllers;
import bd.edu.seu.lendengo.models.User;
import bd.edu.seu.lendengo.services.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class UserController extends ControllerFrame implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        super.initialize(url,resourceBundle);    //Sidebar and header

        userTable.prefWidthProperty().bind(userListVbox.widthProperty());
        userTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        comboBoxOperations();
    }


    @FXML
    public TableView<?> userTable;

    @FXML
    public VBox userListVbox;


    @FXML
    public ImageView profileImageView;

    @FXML
    public DatePicker dobDatePicker;

    @FXML
    public TextField emailField;

    @FXML
    public TextField mobileField;

    @FXML
    public TextField nameFiled;

    @FXML
    public PasswordField passwordField;

    @FXML
    public PasswordField retypePasswordField;

    @FXML
    private ComboBox<String> roleComboBox;


    @FXML
    private ComboBox<String> statusComboBox;

    File imagePath;

    @FXML
    public void imageSelectionEvent(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select an Image");
        String userHome = System.getProperty("user.home");
        fileChooser.setInitialDirectory(new File(userHome + File.separator + "Pictures"));
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png");
        fileChooser.getExtensionFilters().add(filter);
        fileChooser.setSelectedExtensionFilter(filter);

        File Path = fileChooser.showOpenDialog(new Stage());
        if(Path != null) {
            this.imagePath = Path;
            profileImageView.setImage(new Image(imagePath.toURI().toString()));
        }
        else{
            if(imagePath == null){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No Image Selected");
                alert.setContentText("Please Select an Image");
                alert.showAndWait();
            }
        }
    }


    @FXML
    public void saveEvent(ActionEvent event) {

        String name = nameFiled.getText();
        String email = emailField.getText();
        String mobile = mobileField.getText();
        String password = passwordField.getText();
        String passwordRetyped = retypePasswordField.getText();
        String role = roleComboBox.getValue();
        String status = statusComboBox.getValue();
        LocalDate dob = dobDatePicker.getValue();


        if (!name.isEmpty() &&
                !email.isEmpty() &&
                !mobile.isEmpty() &&
                !password.isEmpty() &&
                !passwordRetyped.isEmpty() &&
                role != null &&
                status != null &&
                dob != null && imagePath != null) {

            if(!password.equals(passwordRetyped)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Password Mismatch");
                alert.setContentText("Passwords do no match");
                alert.showAndWait();
            }
            else{
                try {
                    FileInputStream fis = new FileInputStream(imagePath);
                    byte[] imageData = new byte[fis.available()];
                    fis.read(imageData);

                    User user = new User(name, email, mobile, role, dob, status, password, imageData);
                    UserService userService = new UserService();
                    if(userService.insert(user)>0){
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success");
                        alert.setContentText("New User Created");
                        alert.showAndWait();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fields Empty");
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
        }


    }

    @FXML
    public void resetEvent(ActionEvent event) {
        clearFields();
    }

    public void clearFields() {
        profileImageView.setImage(new Image(getClass().getResourceAsStream("/bd/edu/seu/lendengo/images/human.png")));
        nameFiled.clear();
        mobileField.clear();
        emailField.clear();
        passwordField.clear();
        retypePasswordField.clear();
        roleComboBox.getSelectionModel().clearSelection();
        statusComboBox.getSelectionModel().clearSelection();
        dobDatePicker.setValue(null);
    }

    public void comboBoxOperations(){
        roleComboBox.setItems(FXCollections.observableArrayList("Admin", "Employee"));
        statusComboBox.setItems(FXCollections.observableArrayList("Active", "Inactive"));
    }

}
