package bd.edu.seu.lendengo.controllers;

import bd.edu.seu.lendengo.models.Customer;
import bd.edu.seu.lendengo.services.CustomerService;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerController extends ControllerFrame implements Initializable {

    @FXML
    public TableColumn<Customer, Void> actionColumn;

    @FXML
    public Label addCustomerHeader;

    @FXML
    public VBox customerListVbox;

    @FXML
    public TableView<Customer> customerTable;

    @FXML
    public DatePicker dobDatePicker;

    @FXML
    public TextField emailField;

    @FXML
    public TableColumn<Customer, String> genderColumn;

    @FXML
    public ComboBox<String> genderComboBox;

    @FXML
    public TableColumn<Customer, Number> idColumn;

    @FXML
    public TextField mobileField;

    @FXML
    public TableColumn<Customer, String> nameColumn;

    @FXML
    public TextField nameFiled;

    @FXML
    public VBox newUserVbox;

    @FXML
    public TableColumn<Customer, String> phoneColumn;

    @FXML
    public Button resetButton;

    @FXML
    public Button saveButton;

    @FXML
    public TextField searchField;

    @FXML
    public TableColumn<Customer, String> statusColumn;

    @FXML
    public ComboBox<String> statusComboBox;

    public ObservableList<Customer> customerList = FXCollections.observableArrayList();
    public Customer selectedCustomer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        customerVbox.setPrefHeight(60);
        customerVbox.setVisible(true);
        customerVbox.setManaged(true);

        genderComboBox.setItems(FXCollections.observableArrayList("Male", "Female", "Undefined"));
        statusComboBox.setItems(FXCollections.observableArrayList("Active", "Inactive"));

        customerTable.prefWidthProperty().bind(customerListVbox.widthProperty());
        customerTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        tableOperations();
    }

    @FXML
    public void resetEvent(ActionEvent event) {
        clearFields();
    }

    @FXML
    public void saveEvent(ActionEvent event) {
        CustomerService customerService = new CustomerService();

        String name = nameFiled.getText();
        String email = emailField.getText();
        String mobile = mobileField.getText();
        String gender = genderComboBox.getValue();
        String dob = (dobDatePicker.getValue() != null) ? dobDatePicker.getValue().toString() : "";
        String status = statusComboBox.getValue();

        if (!name.isEmpty() && !email.isEmpty() && !mobile.isEmpty() && gender != null && !dob.isEmpty() && status != null) {
            if (selectedCustomer != null) {
                Customer updatedCustomer = new Customer(selectedCustomer.getId(), name, email, mobile, gender, dob, status);
                int affectedRows = customerService.updateCustomer(updatedCustomer);
                if (affectedRows > 0) {
                    clearFields();
                }
                selectedCustomer = null;
                saveButton.setText("Save");
                addCustomerHeader.setText("Add New Customer");
            } else {
                Customer newCustomer = new Customer(0, name, email, mobile, gender, dob, status);
                int effectedRows = customerService.insertCustomer(newCustomer);
                if (effectedRows > 0) {
                    clearFields();
                }
            }
            refreshCustomerList();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fields Empty");
            alert.setContentText("Please fill all the fields before saving.");
            alert.showAndWait();
        }
    }

    @FXML
    public void searchEvent(KeyEvent event) {
        String text = searchField.getText().toLowerCase();
        CustomerService customerService = new CustomerService();
        List<Customer> filteredList = customerService.getAllCustomers().stream()
                .filter(c ->
                        c.getName().toLowerCase().contains(text.toLowerCase()) ||
                                c.getEmail().toLowerCase().contains(text) ||
                                c.getMobile().startsWith(text) ||
                                c.getGender().toLowerCase().startsWith(text.toLowerCase()) ||
                                c.getStatus().toLowerCase().startsWith(text.toLowerCase()))
                .toList();

        customerList.clear();
        customerList.addAll(filteredList);
    }

    public void clearFields() {
        nameFiled.clear();
        emailField.clear();
        mobileField.clear();
        genderComboBox.getSelectionModel().clearSelection();
        dobDatePicker.setValue(null);
        statusComboBox.getSelectionModel().clearSelection();
    }

    public void tableOperations() {
        CustomerService customerService = new CustomerService();
        customerList.addAll(customerService.getAllCustomers());

        idColumn.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getId()));
        nameColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getName()));
        genderColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getGender()));
        phoneColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getMobile()));
        statusColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getStatus()));

        setupActionColumn();
        customerTable.setItems(customerList);
    }

    public void setupActionColumn() {
        actionColumn.setCellFactory(col -> new TableCell<Customer, Void>() {
            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");
            private final HBox container = new HBox(8, editButton, deleteButton);

            {
                editButton.getStyleClass().add("edit-btn");
                deleteButton.getStyleClass().add("delete-btn");

                CustomerService customerService = new CustomerService();
                customerList.addListener((ListChangeListener<Customer>) change ->
                        deleteButton.setDisable(customerService.getAllCustomers().size() <= 1)
                );

                deleteButton.setDisable(customerService.getAllCustomers().size() <= 1);

                deleteButton.setOnAction(event -> {
                    selectedCustomer = getTableView().getItems().get(getIndex());
                    customerService.deleteCustomer(selectedCustomer);
                    refreshCustomerList();
                });

                editButton.setOnAction(event -> {
                    selectedCustomer = getTableView().getItems().get(getIndex());
                    editCustomer(selectedCustomer);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : container);
            }
        });
    }

    public void refreshCustomerList() {
        CustomerService customerService = new CustomerService();
        customerList.clear();
        customerList.addAll(customerService.getAllCustomers());
    }

    public void editCustomer(Customer customer) {
        this.selectedCustomer = customer;
        nameFiled.setText(customer.getName());
        emailField.setText(customer.getEmail());
        mobileField.setText(customer.getMobile());
        genderComboBox.setValue(customer.getGender());
        statusComboBox.setValue(customer.getStatus());

        // Handle date parsing safely
        try {
            dobDatePicker.setValue(java.time.LocalDate.parse(customer.getDob()));
        } catch (Exception ignored) {
            dobDatePicker.setValue(null);
        }

        saveButton.setText("Update");
        addCustomerHeader.setText("Edit Customer");
    }
}
