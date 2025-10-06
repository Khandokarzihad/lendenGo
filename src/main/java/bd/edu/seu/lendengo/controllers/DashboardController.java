package bd.edu.seu.lendengo.controllers;

import bd.edu.seu.lendengo.services.DashboardService;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initiateMenu();

        borderPane.prefWidthProperty().bind(scrollPane.widthProperty().subtract(10));
        scrollPane.setPannable(true);

        //Dashboard Exclusive Starts  -----------------------------------------------------------
        tableResponsive();
        invisibleTabAll();
        lineChartInit();

        recentSellVbox.setVisible(true);
        defaultTabStyleAll();
        sellsTab.getStyleClass().removeAll("tab-default");
        sellsTab.getStyleClass().add("tab-active");

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

    @FXML
    private LineChart<String, Number> incomeVsExpenseChart;


    //Tables  ------------------------------------------------

    @FXML
    public TableView<?> sellTable;

    @FXML
    public TableView<?> customerTable;

    @FXML
    public TableView<?> purchaseTable;

    @FXML
    public TableView<?> qTable;

    @FXML
    public TableView<?> supplierTable;

    @FXML
    public TableView<?> transferTable;


    // Recent Activities Vboxes -----------------------------------------------------

    @FXML
    public VBox recentSellVbox;

    @FXML
    public VBox recentSupplierVbox;

    @FXML
    public VBox recentTransferVbox;

    @FXML
    public VBox recentCustomerVbox;

    @FXML
    public VBox recentPurchaseVbox;

    @FXML
    public VBox recentQuotationVbox;

    // Recent Activities Tabs

    @FXML
    public Label sellsTab;

    @FXML
    public Label suppliersTab;

    @FXML
    public Label transfersTab;

    @FXML
    public Label purchasesTab;

    @FXML
    public Label quotationsTab;

    @FXML
    public Label customersTab;


    //Summary Card Icons ----------------------------------------------------
    @FXML
    public FontAwesomeIconView summaryIcon1;

    @FXML
    public FontAwesomeIconView summaryIcon2;

    @FXML
    public FontAwesomeIconView summaryIcon3;

    @FXML
    public FontAwesomeIconView summaryIcon4;




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
            dashboardService.expandVBox(userVbox,160,angle13);
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


    public void tableResponsive(){
        sellTable.prefWidthProperty().bind(recentSellVbox.widthProperty());
        sellTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        purchaseTable.prefWidthProperty().bind(recentPurchaseVbox.widthProperty());
        purchaseTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        qTable.prefWidthProperty().bind(recentQuotationVbox.widthProperty());
        qTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        transferTable.prefWidthProperty().bind(recentTransferVbox.widthProperty());
        transferTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        customerTable.prefWidthProperty().bind(recentCustomerVbox.widthProperty());
        customerTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        supplierTable.prefWidthProperty().bind(recentSupplierVbox.widthProperty());
        supplierTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }



    // Recent Activities Tab Events

    @FXML
    public void sellsTabEvent(MouseEvent event) {
        invisibleTabAll();
        recentSellVbox.setVisible(true);

        defaultTabStyleAll();
        sellsTab.getStyleClass().removeAll("tab-default");
        sellsTab.getStyleClass().add("tab-active");
    }

    @FXML
    public void purchasesTabEvent(MouseEvent event) {
        invisibleTabAll();
        recentPurchaseVbox.setVisible(true);

        defaultTabStyleAll();
        purchasesTab.getStyleClass().removeAll("tab-default");
        purchasesTab.getStyleClass().add("tab-active");
    }

    @FXML
    public void quotationsTabEvent(MouseEvent event) {
        invisibleTabAll();
        recentQuotationVbox.setVisible(true);

        defaultTabStyleAll();
        quotationsTab.getStyleClass().removeAll("tab-default");
        quotationsTab.getStyleClass().add("tab-active");
    }

    @FXML
    public void suppliersTabEvent(MouseEvent event) {
        invisibleTabAll();
        recentSupplierVbox.setVisible(true);

        defaultTabStyleAll();
        suppliersTab.getStyleClass().removeAll("tab-default");
        suppliersTab.getStyleClass().add("tab-active");
    }

    @FXML
    public void transfersTabEvent(MouseEvent event) {
        invisibleTabAll();
        recentTransferVbox.setVisible(true);

        defaultTabStyleAll();
        transfersTab.getStyleClass().removeAll("tab-default");
        transfersTab.getStyleClass().add("tab-active");
    }

    @FXML
    public void customersTabEvent(MouseEvent event) {
        invisibleTabAll();
        recentCustomerVbox.setVisible(true);

        defaultTabStyleAll();
        customersTab.getStyleClass().removeAll("tab-default");
        customersTab.getStyleClass().add("tab-active");
    }

    public void invisibleTabAll(){
        recentSellVbox.setVisible(false);
        recentSupplierVbox.setVisible(false);
        recentTransferVbox.setVisible(false);
        recentCustomerVbox.setVisible(false);
        recentPurchaseVbox.setVisible(false);
        recentQuotationVbox.setVisible(false);
    }

    public void defaultTabStyleAll(){
        sellsTab.getStyleClass().removeAll("tab-active");
        sellsTab.getStyleClass().add("tab-default");

        quotationsTab.getStyleClass().removeAll("tab-active");
        quotationsTab.getStyleClass().add("tab-default");

        suppliersTab.getStyleClass().removeAll("tab-active");
        suppliersTab.getStyleClass().add("tab-default");

        transfersTab.getStyleClass().removeAll("tab-active");
        transfersTab.getStyleClass().add("tab-default");

        purchasesTab.getStyleClass().removeAll("tab-active");
        purchasesTab.getStyleClass().add("tab-default");

        customersTab.getStyleClass().removeAll("tab-active");
        customersTab.getStyleClass().add("tab-default");
    }

    //Summary Card Hover Events --------------------------------------------
    @FXML
    void summaryBoxHoverIn1(MouseEvent event) {
        DashboardService dashboardService = new DashboardService();
        dashboardService.boxHoverIn(summaryIcon1);
    }

    @FXML
    void summaryBoxHoverIn2(MouseEvent event) {
        DashboardService dashboardService = new DashboardService();
        dashboardService.boxHoverIn(summaryIcon2);
    }

    @FXML
    void summaryBoxHoverIn3(MouseEvent event) {
        DashboardService dashboardService = new DashboardService();
        dashboardService.boxHoverIn(summaryIcon3);
    }

    @FXML
    void summaryBoxHoverIn4(MouseEvent event) {
        DashboardService dashboardService = new DashboardService();
        dashboardService.boxHoverIn(summaryIcon4);
    }

    @FXML
    void summaryBoxHoverOut1(MouseEvent event) {
        DashboardService dashboardService = new DashboardService();
        dashboardService.boxHoverOut(summaryIcon1);
    }

    @FXML
    void summaryBoxHoverOut2(MouseEvent event) {
        DashboardService dashboardService = new DashboardService();
        dashboardService.boxHoverOut(summaryIcon2);
    }

    @FXML
    void summaryBoxHoverOut3(MouseEvent event) {
        DashboardService dashboardService = new DashboardService();
        dashboardService.boxHoverOut(summaryIcon3);
    }

    @FXML
    void summaryBoxHoverOut4(MouseEvent event) {
        DashboardService dashboardService = new DashboardService();
        dashboardService.boxHoverOut(summaryIcon4);
    }

    //-----------------------------------------

    public void lineChartInit(){


        XYChart.Series incomeSeries = new XYChart.Series();
        incomeSeries.setName("Income");
        XYChart.Series expenseSeries = new XYChart.Series();
        expenseSeries.setName("Expense");
        for (int i = 1; i <= 31; i++){
            incomeSeries.getData().add(new XYChart.Data(String.valueOf(i), Math.random() * 1000));
            expenseSeries.getData().add(new XYChart.Data(String.valueOf(i), Math.random() * 1000));
        }

        incomeVsExpenseChart.getData().addAll(incomeSeries,  expenseSeries);


    }

}
