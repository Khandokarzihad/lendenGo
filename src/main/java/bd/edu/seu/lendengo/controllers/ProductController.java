package bd.edu.seu.lendengo.controllers;

import bd.edu.seu.lendengo.interfaces.ProductListener;
import bd.edu.seu.lendengo.models.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;

public class ProductController {

    @FXML
    private Label priceLabel;

    @FXML
    private Label productDescription;

    @FXML
    private ImageView productImageView;

    @FXML
    private Label productNameLabel;

    @FXML
    private Label unitLabel;

    private Product product;
    private ProductListener myListener;

    public void setData(Product product, ProductListener myListener) {
        this.product = product;
        this.myListener = myListener;

        productNameLabel.setText(product.getProductName());
        priceLabel.setText(String.valueOf(product.getProductSellPrice()));
        productDescription.setText(product.getProductDescription());
        unitLabel.setText("/" + product.getProductUnit());

        if (product.getImage() != null)
            productImageView.setImage(new Image(new ByteArrayInputStream(product.getImage())));

    }

    @FXML
    void addToCartEvent(ActionEvent event) {
        if (myListener != null && product != null) {
            myListener.onClickListener(product);
        }
    }

}
