package bd.edu.seu.lendengo.controllers;

import bd.edu.seu.lendengo.interfaces.ProductListener;
import bd.edu.seu.lendengo.models.Product;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
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
                fxmlLoader.setLocation(getClass().getResource("/bd/edu/seu/lendengo/fxml/productCard.fxml")); // your card FXML file
                AnchorPane productPane = fxmlLoader.load();

                ProductCardController productCardController = fxmlLoader.getController();
                productCardController.setData(product, myListener);

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
