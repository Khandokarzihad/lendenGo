package bd.edu.seu.lendengo.controllers;

import bd.edu.seu.lendengo.interfaces.ProductListener;
import bd.edu.seu.lendengo.models.Product;
import bd.edu.seu.lendengo.models.User;
import bd.edu.seu.lendengo.services.UserService;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.skin.SpinnerSkin;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PosController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        productScrollPane.setFitToWidth(true);
        productGrid.prefWidthProperty().bind(productScrollPane.widthProperty());

        gridOperations();
        tableOperations();

    }

    @FXML
    public TableView<Product> cartTable;

    @FXML
    public VBox cartVbox;

    @FXML
    public ComboBox<String> categoryComboBox;

    @FXML
    public ComboBox<String> customerComboBox;

    @FXML
    public Label discountAmount;

    @FXML
    public Label dueAmount;

    @FXML
    public Label itemTypeNumber;

    @FXML
    public Label otherCharge;

    @FXML
    public TableColumn<Product, Number> priceColumn;

    @FXML
    public TableColumn<Product, String> productColumn;

    @FXML
    public TableColumn<Product, Void> actionColumn;

    @FXML
    public GridPane productGrid;

    @FXML
    public ScrollPane productScrollPane;

    @FXML
    public TableColumn<Product, Void> quantityColumn;

    @FXML
    public TextField searchField;

    @FXML
    public Label shippingCharge;

    @FXML
    public TableColumn<Product, Double> subtotalColumn;

    @FXML
    public Label taxPercentage;

    @FXML
    public Label totalAmount;

    @FXML
    public Label totalItemNumber;

    @FXML
    public Label totalPayable;

    @FXML
    public Label totalPayableLarge;

    ObservableList<Product> productList = FXCollections.observableArrayList();
    public List<Product> products = new ArrayList<>();
    public ProductListener myListener;

    // 🛒 The shopping cart
    public List<Product> cart = new ArrayList<>();



    public void tableOperations(){

        productList.addAll(cart);


        productColumn.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getProductName()));
        priceColumn.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getProductSellPrice()));
        subtotalColumn.setCellValueFactory(c -> c.getValue().subtotalPriceProperty().asObject());


        // Quantity column
        quantityColumn.setCellFactory(col -> new TableCell<Product, Void>() {
            private final Spinner<Integer> quantitySpinner = new Spinner<>(1, 100, 1);
            private final HBox container = new HBox(5, quantitySpinner);

            {
                quantitySpinner.setEditable(true);

                quantitySpinner.valueProperty().addListener((obs, oldVal, newVal) -> {
                    Product p = getTableView().getItems().get(getIndex());
                    if (p != null) {
                        p.setProductQuantity(newVal);
                        p.setSubtotalPrice(p.getProductSellPrice() * newVal);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    Product p = getTableView().getItems().get(getIndex());
                    if (p != null) quantitySpinner.getValueFactory().setValue(p.getProductQuantity());
                    setGraphic(container);
                }
            }
        });

        // Action column — delete button
        actionColumn.setCellFactory(col -> new TableCell<Product, Void>() {
            private final Button deleteBtn = new Button("❌");



            {
                deleteBtn.getStyleClass().add("delete-btn");
                deleteBtn.setOnAction(e -> {
                    Product p = getTableView().getItems().get(getIndex());
                    if (p != null) {
                        p.setProductQuantity(1);
                        getTableView().getItems().remove(p);
                        cart.remove(p);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : deleteBtn);
            }
        });

        // Set items
        cartTable.setItems(productList);
    }








    public void gridOperations(){
        // 🧱 Load data (for now, use sample data)
        products.addAll(getSampleProducts());

        // 👂 Define what happens when a product is clicked
        if (!products.isEmpty()) {
            myListener = new ProductListener() {
                @Override
                public void onClickListener(Product product) {
                    addToCart(product);
                }
            };
        }

        // 🧩 Populate the grid with product cards
        int column = 0;
        int row = 1;

        try {
            for (Product product : products) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/bd/edu/seu/lendengo/fxml/product.fxml")); // your card FXML file
                AnchorPane productPane = fxmlLoader.load();

                ProductController productController = fxmlLoader.getController();
                productController.setData(product, myListener);

                if (column == 4) { // 3 items per row
                    column = 0;
                    row++;
                }

                productGrid.add(productPane, column++, row);

                productGrid.setHgrow(productPane, Priority.ALWAYS);
                productGrid.setVgrow(productPane, Priority.ALWAYS);


                GridPane.setMargin(productPane, new Insets(10));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void addToCart(Product product) {
        Product selectedProduct = new Product(product);
        cart.add(selectedProduct);
        tableRefresh();
    }


    public List<Product> getSampleProducts() {
        List<Product> list = new ArrayList<>();

        String filePath = "C:/Users/user/Downloads/giant.jpg";
        FileInputStream fis = null;
        byte[] imageData = null;
        try {
            fis = new FileInputStream(filePath);
            imageData = new byte[fis.available()];
            fis.read(imageData);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            e.printStackTrace();
        }


        Product p1 = new Product(imageData, "Giant", "G1420", "Limited Edition", 700, 999.99, "Security", "Johirul Islam", "pcs");
        Product p2 = new Product(null, "Samsung Galaxy S24", "SGS24", "256GB, Silver",700, 899.99, "Smartphones", "Samsung", "pcs");
        Product p3 = new Product(null, "Sony WH-1000XM5", "SONY1000XM5", "Noise Cancelling Headphones",700, 399.99, "Audio", "Sony",  "pcs");
        Product p4 = new Product(null, "Asus ZenBook 14", "ASZB14", "i7, 16GB RAM", 700, 999.99, "Laptops", "Asus",  "pcs");
        Product p8 = new Product(null, "Apple iPhone 15", "IP15", "128GB, Midnight", 700, 999.99, "Smartphones", "Apple",  "pcs");
        Product p5 = new Product(null, "Samsung Galaxy S24", "SGS24", "256GB, Silver", 700, 999.99, "Smartphones", "Samsung",  "pcs");
        Product p6 = new Product(null, "Sony WH-1000XM5", "SONY1000XM5", "Noise Cancelling Headphones", 700, 999.99, "Audio", "Sony",  "pcs");
        Product p7 = new Product(null, "Asus ZenBook 14", "ASZB14", "i7, 16GB RAM", 700, 999.99, "Laptops", "Asus",  "pcs");
        Product p16 = new Product(null, "Apple iPhone 15", "IP15", "128GB, Midnight", 700, 999.99, "Smartphones", "Apple",  "pcs");
        Product p9 = new Product(null, "Samsung Galaxy S24", "SGS24", "256GB, Silver", 700, 999.99, "Smartphones", "Samsung", "pcs");
        Product p10 = new Product(null, "Sony WH-1000XM5", "SONY1000XM5", "Noise Cancelling Headphones", 700, 999.99, "Audio", "Sony",  "pcs");
        Product p11 = new Product(null, "Asus ZenBook 14", "ASZB14", "i7, 16GB RAM", 700, 999.99, "Laptops", "Asus",  "pcs");
        Product p12 = new Product(null, "Apple iPhone 15", "IP15", "128GB, Midnight", 700, 999.99, "Smartphones", "Apple",  "pcs");
        Product p13 = new Product(null, "Samsung Galaxy S24", "SGS24", "256GB, Silver", 700, 999.99, "Smartphones", "Samsung",  "pcs");
        Product p14 = new Product(null, "Sony WH-1000XM5", "SONY1000XM5", "Noise Cancelling Headphones", 700, 999.99, "Audio", "Sony",  "pcs");
        Product p15 = new Product(null, "Asus ZenBook 14", "ASZB14", "i7, 16GB RAM", 700, 999.99, "Laptops", "Asus",  "pcs");

        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);
        list.add(p6);
        list.add(p6);
        list.add(p8);
        list.add(p9);
        list.add(p10);
        list.add(p11);
        list.add(p12);
        list.add(p13);
        list.add(p14);
        list.add(p15);
        list.add(p16);

        return list;
    }

    public void tableRefresh(){
        productList.clear();
        productList.addAll(cart);
    }



    @FXML
    public void payEvent(ActionEvent event) {

    }




}
