package bd.edu.seu.lendengo.controllers;

import bd.edu.seu.lendengo.models.Product;
import bd.edu.seu.lendengo.services.ProductAttributeService;
import bd.edu.seu.lendengo.services.ProductService;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ProductController extends ControllerFrame implements Initializable {

    @FXML
    private TableColumn<Product, Void> actionColumn;

    @FXML
    private Label addProductHeader;

    @FXML
    private ComboBox<String> brandComboBox;

    @FXML
    private TableColumn<Product, String> categoryColumn;

    @FXML
    private ComboBox<String> categoryComboBox;

    @FXML
    private TableColumn<Product, String> codeColumn;

    @FXML
    private TextField codeField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private TableColumn<Product, String> nameColumn;

    @FXML
    private TextField nameFiled;

    @FXML
    private VBox newUserVbox;

    @FXML
    private ImageView productImageView;

    @FXML
    private VBox productListVbox;

    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableColumn<Product, Number> purchasePriceColumn;

    @FXML
    private TextField purchasePriceField;

    @FXML
    private Button resetnButton;

    @FXML
    private Button saveButton;

    @FXML
    private TextField searchField;

    @FXML
    private TableColumn<Product, Number> sellPriceColumn;

    @FXML
    private TextField sellPriceField;

    @FXML
    private TextField shortDescriptionField;

    @FXML
    private TableColumn<Product, String> unitColumn;

    @FXML
    private ComboBox<String> unitField;

    public File imagePath;
    private final ObservableList<Product> productList = FXCollections.observableArrayList();
    private Product selectedProduct;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        productVbox.setPrefHeight(110);
        productVbox.setVisible(true);
        productVbox.setManaged(true);




        productTable.prefWidthProperty().bind(productListVbox.widthProperty());
        productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        tableOperations();
        comboBoxOperations();
    }

    @FXML
    void imageSelectionEvent(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Product Image");
        String userHome = System.getProperty("user.home");
        fileChooser.setInitialDirectory(new File(userHome + File.separator + "Pictures"));
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png"));

        File path = fileChooser.showOpenDialog(new Stage());
        if (path != null) {
            this.imagePath = path;
            productImageView.setImage(new Image(imagePath.toURI().toString()));
        } else if (imagePath == null) {
            showAlert("No Image Selected", "Please select an image before saving.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void resetEvent(ActionEvent event) {
        addProductHeader.setText("Add New Product");
        clearFields();
    }

    @FXML
    void saveEvent(ActionEvent event) {
        ProductService productService = new ProductService();

        String code = codeField.getText();
        String name = nameFiled.getText();
        String shortDesc = shortDescriptionField.getText();
        String desc = descriptionField.getText();
        String category = categoryComboBox.getValue();
        String brand = brandComboBox.getValue();
        String unit = unitField.getValue();

        double purchasePrice = parseDoubleSafe(purchasePriceField.getText());
        double sellPrice = parseDoubleSafe(sellPriceField.getText());

        if (name.isEmpty() || code.isEmpty() || category == null || brand == null || unit == null) {
            showAlert("Missing Fields", "Please fill all required fields.", Alert.AlertType.WARNING);
            return;
        }


        FileInputStream fis = null;
        byte[] imageData =  null;
        try {
            fis = new FileInputStream(imagePath);
            imageData = new byte[fis.available()];
            fis.read(imageData);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Product newProduct = new Product(imageData, name,code, shortDesc, desc, sellPrice, purchasePrice, category, brand, unit);

        if (selectedProduct != null) {
            if (imagePath != null) {
                FileInputStream fis1 = null;
                try {
                    fis1 = new FileInputStream(imagePath);
                    imageData = new byte[fis1.available()];
                    fis.read(imageData);
                    newProduct.setImage(imageData);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                catch(IOException e) {
                    e.printStackTrace();
                }

            }
            else{
                newProduct.setImage(imageData);
            }

            newProduct.setProductCode(selectedProduct.getProductCode());
            int affectedRows = productService.update(newProduct);
            if (affectedRows > 0) clearFields();
            saveButton.setText("Save");
            resetnButton.setText("Reset");
            addProductHeader.setText("Add New Product");
            selectedProduct = null;
        } else {
            int affectedRows = productService.insert(newProduct);
            if (affectedRows > 0) clearFields();
        }

        refreshProductList();
    }

    @FXML
    void searchEvent(KeyEvent event) {
        String text = searchField.getText().toLowerCase();
        ProductService productService = new ProductService();

        List<Product> filteredList = productService.getAllProducts().stream()
                .filter(p ->
                        p.getProductName().toLowerCase().contains(text) ||
                                p.getProductCategory().toLowerCase().contains(text) ||
                                p.getProductBrand().toLowerCase().contains(text) ||
                                p.getProductCode().toLowerCase().contains(text))
                .toList();

        productList.setAll(filteredList);
    }

    private void comboBoxOperations() {
        ProductAttributeService productAttributeService = new ProductAttributeService();

        categoryComboBox.setItems(FXCollections.observableArrayList(productAttributeService.getAllCategories()));
        brandComboBox.setItems(FXCollections.observableArrayList(productAttributeService.getAllBrands()));
        unitField.setItems(FXCollections.observableArrayList(productAttributeService.getAllUnits()));
    }

    private void tableOperations() {
        ProductService productService = new ProductService();
        productList.setAll(productService.getAllProducts());

        codeColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getProductCode()));
        nameColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getProductName()));
        categoryColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getProductCategory()));
        purchasePriceColumn.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getProductPurchasePrice()));
        sellPriceColumn.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getProductSellPrice()));
        unitColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getProductUnit()));

        setupActionColumn();
        productTable.setItems(productList);
    }

    private void setupActionColumn() {
        actionColumn.setCellFactory(col -> new TableCell<>() {
            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");
            private final HBox container = new HBox(8, editButton, deleteButton);

            {
                editButton.getStyleClass().add("edit-btn");
                deleteButton.getStyleClass().add("delete-btn");

                ProductService productService = new ProductService();

                productList.addListener((ListChangeListener<Product>) change ->
                        deleteButton.setDisable(productService.getAllProducts().size() <= 1));

                deleteButton.setOnAction(event -> {
                    selectedProduct = getTableView().getItems().get(getIndex());
                    productService.delete(selectedProduct);
                    refreshProductList();
                });

                editButton.setOnAction(event -> {
                    selectedProduct = getTableView().getItems().get(getIndex());
                    editProduct(selectedProduct);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : container);
            }
        });
    }

    private void refreshProductList() {
        ProductService productService = new ProductService();
        productList.setAll(productService.getAllProducts());
    }

    private void editProduct(Product product) {
        scrollPane.setVvalue(0);

        this.selectedProduct = product;

        codeField.setText(product.getProductCode());
        nameFiled.setText(product.getProductName());
        shortDescriptionField.setText(product.getShortDescription());
        descriptionField.setText(product.getProductDescription());
        categoryComboBox.setValue(product.getProductCategory());
        brandComboBox.setValue(product.getProductBrand());
        unitField.setValue(product.getProductCategory());
        purchasePriceField.setText(String.valueOf(product.getProductPurchasePrice()));
        sellPriceField.setText(String.valueOf(product.getProductSellPrice()));

        if (product.getImage() != null) {
            ByteArrayInputStream bais = new ByteArrayInputStream(product.getImage());
            productImageView.setImage(new Image(bais));
        }

        saveButton.setText("Update");
        resetnButton.setText("Cancel");
        addProductHeader.setText("Edit Product");
    }




    @FXML
    void infoEvent(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText("Instructions");
        alert.setContentText("Name : max 20 characters\n\nShortDescription : max 25 characters\n\ndescription : max 255 characters");
        alert.showAndWait();
    }



    private void clearFields() {
        codeField.clear();
        nameFiled.clear();
        shortDescriptionField.clear();
        descriptionField.clear();
        purchasePriceField.clear();
        sellPriceField.clear();
        categoryComboBox.getSelectionModel().clearSelection();
        brandComboBox.getSelectionModel().clearSelection();
        unitField.getSelectionModel().clearSelection();
        productImageView.setImage(null);
        imagePath = null;
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private double parseDoubleSafe(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
