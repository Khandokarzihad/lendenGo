package bd.edu.seu.lendengo.controllers;

import bd.edu.seu.lendengo.HelloApplication;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DashboardController extends ControllerFrame implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        super.initialize(url, resourceBundle);

        //Dashboard Exclusive Starts  -----------------------------------------------------------
        tableResponsive();
        invisibleTabAll();
        lineChartInit();

        recentSellVbox.setVisible(true);
        defaultTabStyleAll();
        sellsTab.getStyleClass().removeAll("tab-default");
        sellsTab.getStyleClass().add("tab-active");


    }


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
        recentSellVbox.toFront();

        defaultTabStyleAll();
        sellsTab.getStyleClass().removeAll("tab-default");
        sellsTab.getStyleClass().add("tab-active");
    }

    @FXML
    public void purchasesTabEvent(MouseEvent event) {
        invisibleTabAll();
        recentPurchaseVbox.setVisible(true);
        recentPurchaseVbox.toFront();

        defaultTabStyleAll();
        purchasesTab.getStyleClass().removeAll("tab-default");
        purchasesTab.getStyleClass().add("tab-active");
    }

    @FXML
    public void quotationsTabEvent(MouseEvent event) {
        invisibleTabAll();
        recentQuotationVbox.setVisible(true);
        recentQuotationVbox.toFront();


        defaultTabStyleAll();
        quotationsTab.getStyleClass().removeAll("tab-default");
        quotationsTab.getStyleClass().add("tab-active");
    }

    @FXML
    public void suppliersTabEvent(MouseEvent event) {
        invisibleTabAll();
        recentSupplierVbox.setVisible(true);
        recentSupplierVbox.toFront();


        defaultTabStyleAll();
        suppliersTab.getStyleClass().removeAll("tab-default");
        suppliersTab.getStyleClass().add("tab-active");
    }

    @FXML
    public void transfersTabEvent(MouseEvent event) {
        invisibleTabAll();
        recentTransferVbox.setVisible(true);
        recentTransferVbox.toFront();


        defaultTabStyleAll();
        transfersTab.getStyleClass().removeAll("tab-default");
        transfersTab.getStyleClass().add("tab-active");
    }

    @FXML
    public void customersTabEvent(MouseEvent event) {
        invisibleTabAll();
        recentCustomerVbox.setVisible(true);
        recentCustomerVbox.toFront();


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

        recentSellVbox.toBack();
        recentSupplierVbox.toBack();
        recentTransferVbox.toBack();
        recentCustomerVbox.toBack();
        recentPurchaseVbox.toBack();
        recentQuotationVbox.toBack();
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
