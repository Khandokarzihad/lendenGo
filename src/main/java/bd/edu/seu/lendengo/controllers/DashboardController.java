package bd.edu.seu.lendengo.controllers;

import bd.edu.seu.lendengo.services.DashboardService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initiateMenu();

        borderPane.prefWidthProperty().bind(scrollPane.widthProperty().subtract(10));
        scrollPane.setPannable(true);

    }


    // SideBar items Start -----------------------------------------------------------
    @FXML
    public BorderPane borderPane;

    @FXML
    ScrollPane scrollPane;

    @FXML
    public FontAwesomeIconView angle1;

    @FXML
    public FontAwesomeIconView angle10;

    @FXML
    public FontAwesomeIconView angle11;

    @FXML
    public FontAwesomeIconView angle12;

    @FXML
    public FontAwesomeIconView angle13;

    @FXML
    public FontAwesomeIconView angle2;

    @FXML
    public FontAwesomeIconView angle3;

    @FXML
    public FontAwesomeIconView angle4;

    @FXML
    public FontAwesomeIconView angle5;

    @FXML
    public FontAwesomeIconView angle6;

    @FXML
    public FontAwesomeIconView angle7;

    @FXML
    public FontAwesomeIconView angle8;

    @FXML
    public FontAwesomeIconView angle9;

    @FXML
    public VBox sellVbox;

    @FXML
    public VBox accountingVbox;


    @FXML
    public VBox customerVbox;

    @FXML
    public VBox expanditureVbox;

    @FXML
    public VBox installmentVbox;

    @FXML
    public VBox loanVbox;

    @FXML
    public VBox productVbox;

    @FXML
    public VBox purchaseVbox;

    @FXML
    public VBox qVbox;

    @FXML
    public VBox reportsVbox;


    @FXML
    public VBox stockVbox;

    @FXML
    public VBox supplierVbox;

    @FXML
    public Label userName;

    @FXML
    public VBox userVbox;

    @FXML
    public VBox sideBar;

    @FXML
    public FontAwesomeIconView sideMenuButton;

    // SideBar items End -----------------------------------------------------------



    // SideBar Methods Start -----------------------------------------------------------


    @FXML
    public void sellEvent(MouseEvent event) {
        DashboardService dashboardService = new DashboardService();
        if(sellVbox.isVisible()){
            dashboardService.shrinkVBox(sellVbox,angle1);
        }
        else{
            shrinkAll();
            dashboardService.expandVBox(sellVbox,85,angle1);
        }
    }


    @FXML
    public void quoteEvent(MouseEvent event) {
        DashboardService dashboardService = new DashboardService();
        if(qVbox.isVisible()){
            dashboardService.shrinkVBox(qVbox,angle2);
        }
        else{
            shrinkAll();
            dashboardService.expandVBox(qVbox,60,angle2);
        }
    }


    @FXML
    public void installmentEvent(MouseEvent event) {
        DashboardService dashboardService = new DashboardService();
        if(installmentVbox.isVisible()){
            dashboardService.shrinkVBox(installmentVbox,angle3);
        }
        else{
            shrinkAll();
            dashboardService.expandVBox(installmentVbox,110,angle3);
        }
    }


    @FXML
    public void purchaseEvent(MouseEvent event) {
        DashboardService dashboardService = new DashboardService();
        if(purchaseVbox.isVisible()){
            dashboardService.shrinkVBox(purchaseVbox,angle4);
        }
        else{
            shrinkAll();
            dashboardService.expandVBox(purchaseVbox,110,angle4);
        }
    }


    @FXML
    public void stockEvent(MouseEvent event) {
        DashboardService dashboardService = new DashboardService();
        if(stockVbox.isVisible()){
            dashboardService.shrinkVBox(stockVbox,angle5);
        }
        else{
            shrinkAll();
            dashboardService.expandVBox(stockVbox,85,angle5);
        }
    }


    @FXML
    public void productEvent(MouseEvent event) {
        DashboardService dashboardService = new DashboardService();
        if(productVbox.isVisible()){
            dashboardService.shrinkVBox(productVbox,angle6);
        }
        else{
            shrinkAll();
            dashboardService.expandVBox(productVbox,135,angle6);
        }
    }


    @FXML
    public void customerEvent(MouseEvent event) {
        DashboardService dashboardService = new DashboardService();
        if(customerVbox.isVisible()){
            dashboardService.shrinkVBox(customerVbox,angle7);
        }
        else{
            shrinkAll();
            dashboardService.expandVBox(customerVbox,60,angle7);
        }
    }


    @FXML
    public void supplierEvent(MouseEvent event) {
        DashboardService dashboardService = new DashboardService();
        if(supplierVbox.isVisible()){
            dashboardService.shrinkVBox(supplierVbox,angle8);
        }
        else{
            shrinkAll();
            dashboardService.expandVBox(supplierVbox,60,angle8);
        }
    }


    @FXML
    public void accountingEvent(MouseEvent event) {
        DashboardService dashboardService = new DashboardService();
        if(accountingVbox.isVisible()){
            dashboardService.shrinkVBox(accountingVbox,angle9);
        }
        else{
            shrinkAll();
            dashboardService.expandVBox(accountingVbox,365,angle9);
        }
    }



    @FXML
    public void expanditureEvent(MouseEvent event) {
        DashboardService dashboardService = new DashboardService();
        if(expanditureVbox.isVisible()){
            dashboardService.shrinkVBox(expanditureVbox,angle10);
        }
        else{
            shrinkAll();
            dashboardService.expandVBox(expanditureVbox,160,angle10);
        }
    }


    @FXML
    public void loanEvent(MouseEvent event) {
        DashboardService dashboardService = new DashboardService();
        if(loanVbox.isVisible()){
            dashboardService.shrinkVBox(loanVbox,angle11);
        }
        else{
            shrinkAll();
            dashboardService.expandVBox(loanVbox,85,angle11);
        }
    }



    @FXML
    public void reportEvent(MouseEvent event) {
        DashboardService dashboardService = new DashboardService();
        if(reportsVbox.isVisible()){
            dashboardService.shrinkVBox(reportsVbox,angle12);
        }
        else{
            shrinkAll();
            dashboardService.expandVBox(reportsVbox,315,angle12);
        }
    }



    @FXML
    public void userEvent(MouseEvent event) {
        DashboardService dashboardService = new DashboardService();
        if(userVbox.isVisible()){
            dashboardService.shrinkVBox(userVbox,angle13);
        }
        else{
            shrinkAll();
            dashboardService.expandVBox(userVbox,135,angle13);
        }
    }


    @FXML
    public void sideMenuToggleEvent(MouseEvent event) {
        DashboardService dashboardService = new DashboardService();
        if(sideBar.isManaged()){
            dashboardService.slideOut(sideBar, sideMenuButton);
        }
        else{
            dashboardService.slideIn(sideBar, 199, sideMenuButton);
        }
    }




    public void initiateMenu(){
        sellVbox.setVisible(false);
        sellVbox.setManaged(false);
        sellVbox.setPrefHeight(0);

        accountingVbox.setVisible(false);
        accountingVbox.setManaged(false);
        accountingVbox.setPrefHeight(0);

        productVbox.setVisible(false);
        productVbox.setManaged(false);
        productVbox.setPrefHeight(0);

        installmentVbox.setVisible(false);
        installmentVbox.setManaged(false);
        installmentVbox.setPrefHeight(0);

        loanVbox.setVisible(false);
        loanVbox.setManaged(false);
        loanVbox.setPrefHeight(0);

        purchaseVbox.setVisible(false);
        purchaseVbox.setManaged(false);
        purchaseVbox.setPrefHeight(0);

        qVbox.setVisible(false);
        qVbox.setManaged(false);
        qVbox.setPrefHeight(0);

        customerVbox.setVisible(false);
        customerVbox.setManaged(false);
        customerVbox.setPrefHeight(0);

        expanditureVbox.setVisible(false);
        expanditureVbox.setManaged(false);
        expanditureVbox.setPrefHeight(0);

        userVbox.setVisible(false);
        userVbox.setManaged(false);
        userVbox.setPrefHeight(0);

        reportsVbox.setVisible(false);
        reportsVbox.setManaged(false);
        reportsVbox.setPrefHeight(0);

        stockVbox.setVisible(false);
        stockVbox.setManaged(false);
        stockVbox.setPrefHeight(0);

        supplierVbox.setVisible(false);
        supplierVbox.setManaged(false);
        supplierVbox.setPrefHeight(0);


    }

    public void shrinkAll(){
        DashboardService dashboardService = new DashboardService();
        if(sellVbox.isVisible()){
            dashboardService.shrinkVBox(sellVbox,angle1);
        }
        if(qVbox.isVisible()){
            dashboardService.shrinkVBox(qVbox,angle2);
        }
        if(installmentVbox.isVisible()){
            dashboardService.shrinkVBox(installmentVbox,angle3);
        }
        if(purchaseVbox.isVisible()){
            dashboardService.shrinkVBox(purchaseVbox,angle4);
        }
        if(stockVbox.isVisible()){
            dashboardService.shrinkVBox(stockVbox,angle5);
        }
        if(productVbox.isVisible()){
            dashboardService.shrinkVBox(productVbox,angle6);
        }
        if(customerVbox.isVisible()){
            dashboardService.shrinkVBox(customerVbox,angle7);
        }
        if(supplierVbox.isVisible()){
            dashboardService.shrinkVBox(supplierVbox,angle8);
        }
        if(accountingVbox.isVisible()){
            dashboardService.shrinkVBox(accountingVbox,angle9);
        }
        if(expanditureVbox.isVisible()){
            dashboardService.shrinkVBox(expanditureVbox,angle10);
        }
        if(loanVbox.isVisible()){
            dashboardService.shrinkVBox(loanVbox,angle11);
        }
        if(reportsVbox.isVisible()){
            dashboardService.shrinkVBox(reportsVbox,angle12);
        }
        if(userVbox.isVisible()){
            dashboardService.shrinkVBox(userVbox,angle13);
        }
    }

    // SideBar Methods End -----------------------------------------------------------


}
