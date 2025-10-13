package bd.edu.seu.lendengo.controllers;

import bd.edu.seu.lendengo.models.User;
import bd.edu.seu.lendengo.services.UserService;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class LoginHistoryController extends ControllerFrame implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url,resourceBundle);    //Sidebar and header
        userVbox.setPrefHeight(110);
        userVbox.setVisible(true);
        userVbox.setManaged(true);

        loginHistoryTable.prefWidthProperty().bind(loginHistoryVbox.widthProperty());
        loginHistoryTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        loginHistoryLabel.getStyleClass().removeAll();
        loginHistoryLabel.getStyleClass().add("subMenuLabel-present");

        tableOperations();
    }

    ObservableList<User> loginHistory = FXCollections.observableArrayList();

    @FXML
    public TableColumn<User, String> emailColumn;

    @FXML
    public TableColumn<User, Number> idColumn;

    @FXML
    public TableView<User> loginHistoryTable;

    @FXML
    public TableColumn<User, LocalDateTime> loginTimeColumn;

    @FXML
    public TableColumn<User, String> nameColumn;

    @FXML
    public TableColumn<User, String> roleColumn;

    @FXML
    public TextField searchField;

    @FXML
    public VBox loginHistoryVbox;

    @FXML
    public void searchEvent(KeyEvent event) {
        String text = searchField.getText();

        UserService userService = new UserService();
        List<User> filteredList = userService.getLoginHistory().stream().filter(c->
                                                                                       c.getName().toLowerCase().contains(text.toLowerCase()) ||
                                                                                       Integer.toString(c.getId()).startsWith(text) ||
                                                                                       c.getEmail().toLowerCase().contains(text.toLowerCase()) ||
                                                                                       c.getRole().toLowerCase().startsWith(text.toLowerCase()) ||
                                                                                       c.getCreatedAt().toString().toLowerCase().startsWith(text.toLowerCase())
                                                                                       ).toList();
        loginHistory.clear();
        loginHistory.addAll(filteredList);
    }


    public void tableOperations(){
        UserService userService = new UserService();
        List<User> users = userService.getLoginHistory();
        loginHistory.addAll(users);

        idColumn.setCellValueFactory(c-> new SimpleIntegerProperty(c.getValue().getId()));
        nameColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getName()));
        emailColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getEmail()));
        roleColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getRole()));
        loginTimeColumn.setCellValueFactory(c-> new SimpleObjectProperty<LocalDateTime>(c.getValue().getCreatedAt()));


        loginHistoryTable.setItems(loginHistory);
    }

}
