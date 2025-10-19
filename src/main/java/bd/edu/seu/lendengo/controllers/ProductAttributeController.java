package bd.edu.seu.lendengo.controllers;

import bd.edu.seu.lendengo.models.Notice;
import bd.edu.seu.lendengo.models.ProductAttribute;
import bd.edu.seu.lendengo.models.User;
import bd.edu.seu.lendengo.services.NoticeService;
import bd.edu.seu.lendengo.services.ProductAttributeService;
import bd.edu.seu.lendengo.services.UserService;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProductAttributeController extends ControllerFrame implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        productVbox.setPrefHeight(110);
        productVbox.setVisible(true);
        productVbox.setManaged(true);

        attributeTable.prefWidthProperty().bind(productAttributeVbox.widthProperty());
        attributeTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        typeComboBox.setItems(FXCollections.observableArrayList("Category", "Brand", "Unit"));

        tableOperations();
    }

    @FXML
    public TableColumn<ProductAttribute, Void> actionColumn;

    @FXML
    public Label attributeHeaderLabel;

    @FXML
    public TableView<ProductAttribute> attributeTable;

    @FXML
    public TableColumn<ProductAttribute, Number> idColumn;

    @FXML
    public TableColumn<ProductAttribute, String> nameColumn;

    @FXML
    public VBox newAttributeBox;

    @FXML
    public VBox productAttributeVbox;

    @FXML
    public Button resetButton;

    @FXML
    public Button saveButton;

    @FXML
    public TextField searchField;

    @FXML
    public TableColumn<ProductAttribute, String> typeColumn;

    @FXML
    public ComboBox<String> typeComboBox;

    @FXML
    public TextField nameField;

    public ObservableList<ProductAttribute> productAttributeList =  FXCollections.observableArrayList();
    public ProductAttribute selectedAttribute;


    @FXML
    public void resetEvent(ActionEvent event) {
        clearFields();
    }

    @FXML
    public void saveEvent(ActionEvent event) {
        ProductAttributeService productAttributeService = new ProductAttributeService();

        String type = typeComboBox.getValue();
        String name = nameField.getText();

        if(type != null && !name.isEmpty()){
            if(selectedAttribute != null){
                ProductAttribute newAttribute = new ProductAttribute(selectedAttribute.getId(), type, name);
                int affectedRows = productAttributeService.update(newAttribute);
                if(affectedRows > 0){
                    clearFields();
                }
                selectedAttribute = null;
                saveButton.setText("Save");
                attributeHeaderLabel.setText("Add New Attribute");

            }
            else{
                ProductAttribute newAttribute = new ProductAttribute(type, name);
                int effectedRows = productAttributeService.insert(newAttribute);
                if( effectedRows>0){
                    clearFields();
                }
            }
            attributeListUpdate();

        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fields Empty");
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
        }
    }

    @FXML
    public void searchEvent(KeyEvent event) {
        String text = searchField.getText();

        ProductAttributeService productAttributeService = new ProductAttributeService();
        List<ProductAttribute> filteredList = productAttributeService.getAllAttributes().stream().filter(c->
                c.getName().toLowerCase().contains(text.toLowerCase()) ||
                        Integer.toString(c.getId()).startsWith(text) ||
                        c.getType().toLowerCase().startsWith(text.toLowerCase())
        ).toList();
        productAttributeList.clear();
        productAttributeList.addAll(filteredList);
    }

    public void clearFields() {
        nameField.clear();
        typeComboBox.getSelectionModel().clearSelection();
    }

    public void tableOperations(){
        ProductAttributeService productAttributeService = new ProductAttributeService();
        productAttributeList.addAll(productAttributeService.getAllAttributes());

        idColumn.setCellValueFactory(c-> new SimpleIntegerProperty(c.getValue().getId()));
        typeColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getType()));
        nameColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getName()));

        setupActionColumn();
        attributeTable.setItems(productAttributeList);
    }


    public void setupActionColumn() {
        actionColumn.setCellFactory(col -> new TableCell<ProductAttribute, Void>() {
            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");
            private final HBox container = new HBox(8, editButton, deleteButton);

            {

                editButton.getStyleClass().add("edit-btn");
                deleteButton.getStyleClass().add("delete-btn");
                ProductAttributeService productAttributeService = new ProductAttributeService();
                productAttributeList.addListener((ListChangeListener<ProductAttribute>) change -> {
                    deleteButton.setDisable(productAttributeService.getAllAttributes().size() <= 1);
                });


                deleteButton.setDisable(productAttributeService.getAllAttributes().size() <= 1);

                deleteButton.setOnAction(event -> {
                    selectedAttribute = getTableView().getItems().get(getIndex());
                    productAttributeService.delete(selectedAttribute);
                    attributeListUpdate();
                });

                editButton.setOnAction(event -> {
                    selectedAttribute = getTableView().getItems().get(getIndex());
                    editAttribute(selectedAttribute);
                    attributeListUpdate();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : container);
            }
        });
    }

    public void attributeListUpdate(){
        ProductAttributeService productAttributeService = new ProductAttributeService();
        productAttributeList.clear();
        productAttributeList.addAll(productAttributeService.getAllAttributes());
    }

    public void editAttribute(ProductAttribute productAttribute) {
        this.selectedAttribute = productAttribute;
        nameField.setText(productAttribute.getName());
        typeComboBox.setValue(productAttribute.getType());
        saveButton.setText("Update");
        attributeHeaderLabel.setText("Edit Attribute");
        scrollPane.setVvalue(0);
    }



    @FXML
    void infoEvent(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText("Instructions");
        alert.setContentText("Length of Attribute name : 50 characters max");
        alert.showAndWait();
    }


}
