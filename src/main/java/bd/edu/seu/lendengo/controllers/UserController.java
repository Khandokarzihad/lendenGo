package bd.edu.seu.lendengo.controllers;
import bd.edu.seu.lendengo.models.User;
import bd.edu.seu.lendengo.services.UserService;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class UserController extends ControllerFrame implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        super.initialize(url,resourceBundle);    //Sidebar and header

        userTable.prefWidthProperty().bind(userListVbox.widthProperty());
        userTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        comboBoxOperations();
        tableOperations();
    }


    @FXML
    public TableView<User> userTable;

    @FXML
    public TableColumn<User, Number> idColumn;

    @FXML
    public TableColumn<User, String> nameColumn;

    @FXML
    public TableColumn<User, String> phoneColumn;

    @FXML
    public TableColumn<User, String> emailColumn;

    @FXML
    public TableColumn<User, String> roleColumn;

    @FXML
    public TableColumn<User, LocalDateTime> createdColumn;

    @FXML
    public TableColumn<User, LocalDateTime> updatedColumn;

    @FXML
    public TableColumn<User, String> statusColumn;

    @FXML
    public TableColumn<User, Void> actionColumn;

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
    public ComboBox<String> roleComboBox;


    @FXML
    public ComboBox<String> statusComboBox;


    public File imagePath;
    public ObservableList<User> userList = FXCollections.observableArrayList();
    public static User selectedUser;

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

        String name = nameFiled.getText().trim();
        String email = emailField.getText().trim();
        String mobile = mobileField.getText().trim();
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

            if(mobile.length() != 10) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Invalid Mobile Number");
                alert.setContentText("Please enter a valid Mobile Number");
                alert.showAndWait();
            }

            else if(!password.equals(passwordRetyped)) {
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
                        alert.show();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                userListUpdate();
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

    public void tableOperations(){
        UserService userService = new UserService();
        List<User> users = userService.getAllUsers();
        userList.addAll(users);

        idColumn.setCellValueFactory(c-> new SimpleIntegerProperty(c.getValue().getId()));
        nameColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getName()));
        phoneColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getMobile()));
        emailColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getEmail()));
        roleColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getRole()));
        updatedColumn.setCellValueFactory(c-> new SimpleObjectProperty<LocalDateTime>(c.getValue().getUpdatedAt()));
        createdColumn.setCellValueFactory(c-> new SimpleObjectProperty<LocalDateTime>(c.getValue().getCreatedAt()));
        statusColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getStatus()));

        setupActionColumn();

        userTable.setItems(userList);
    }

    public void userListUpdate(){
        UserService userService = new UserService();
        userList.clear();
        userList.addAll(userService.getAllUsers());
    }


    private void setupActionColumn() {
        actionColumn.setCellFactory(col -> new TableCell<User, Void>() {
            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");
            private final HBox container = new HBox(8, editButton, deleteButton);

            {
                // Optional CSS class names
                editButton.getStyleClass().add("edit-btn");
                deleteButton.getStyleClass().add("delete-btn");

                editButton.setOnAction(event -> {
                    UserService userService = new UserService();
                    selectedUser = getTableView().getItems().get(getIndex());
                    userService.delete(selectedUser);
                    userListUpdate();
                });

                deleteButton.setOnAction(event -> {
                    selectedUser = getTableView().getItems().get(getIndex());

                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : container);
            }
        });
    }


}
