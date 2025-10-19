package bd.edu.seu.lendengo.controllers;

import bd.edu.seu.lendengo.models.Notice;
import bd.edu.seu.lendengo.models.User;
import bd.edu.seu.lendengo.services.NoticeService;
import bd.edu.seu.lendengo.services.UserService;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class NoticeController extends ControllerFrame implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url,resourceBundle);    //Sidebar and header

        comboBoxOperations();
        tableOperations();
//        createdByStaticField.setText(LoginController.user.getName());

        userVbox.setPrefHeight(110);
        userVbox.setVisible(true);
        userVbox.setManaged(true);

        noticeTable.prefWidthProperty().bind(noticeListVbox.widthProperty());
        noticeTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

    }

    ObservableList<Notice> noticeList = FXCollections.observableArrayList();
    Notice selectedNotice;

    @FXML
    public TableColumn<Notice, Void> actionColumn;

    @FXML
    public TextArea contentArea;

    @FXML
    public TableColumn<Notice, String> createdByColumn;

    @FXML
    public TextField createdByStaticField;

    @FXML
    public TableColumn<Notice, LocalDateTime> createdAtColumn;

    @FXML
    public TableColumn<Notice, Number> idColumn;

    @FXML
    public VBox newNoticeVbox;

    @FXML
    public Label noticeHeaderLabel;

    @FXML
    public TableView<Notice> noticeTable;

    @FXML
    public TextField searchField;

    @FXML
    public TableColumn<Notice, String> titleColumn;

    @FXML
    public TextField titleField;

    @FXML
    public TableColumn<Notice, String> typeColumn;

    @FXML
    public ComboBox<String> typeComboBox;

    @FXML
    public TableColumn<Notice, LocalDateTime> modifiedAtColumn;

    @FXML
    public TableColumn<Notice, String> statusColumn;

    @FXML
    public ComboBox<String> statusComboBox;

    @FXML
    public VBox noticeListVbox;

    @FXML
    public Button resetButton;

    @FXML
    public Button saveButton;


    @FXML
    public void resetEvent(ActionEvent event) {
        clearFields();
        saveButton.setText("Save");
        noticeHeaderLabel.setText("Add New Notice");
    }

    @FXML
    public void saveEvent(ActionEvent event) {
        NoticeService noticeService = new NoticeService();

        String title = titleField.getText();
        String content = contentArea.getText();
        String type = typeComboBox.getValue();
        String status = statusComboBox.getValue();
        String createdBy = createdByStaticField.getText();


        if(!title.isEmpty() && !content.isEmpty() && type != null && status!=null) {
            if(content.length()<=150){
                if (selectedNotice != null) {
                    Notice notice = new Notice(selectedNotice.getId(), title, content, type, status, createdBy);
                    noticeService.updateNotice(notice);
                    selectedNotice = null;
                    saveButton.setText("Save");
                    noticeHeaderLabel.setText("Add New Notice");
                } else {
                    Notice notice = new Notice(title, content, type, status, createdBy);
                    noticeService.insertNotice(notice);
                    clearFields();
                }

                noticeListUpdate();
                updateActiveNoticeBar();
                clearFields();
            }
            else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Content Too Large !!");
                alert.setContentText("Please keep the content within 150 characters.");
                alert.showAndWait();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Fields Empty");
            alert.setContentText("Please fill all the fields");
            alert.showAndWait();
        }

    }

    @FXML
    public void searchEvent(KeyEvent event) {
        String text = searchField.getText();

        NoticeService noticeService = new NoticeService();
        List<Notice> filteredList = noticeService.getAllNotices().stream().filter(c->
                                                                                           c.getTitle().toLowerCase().contains(text.toLowerCase()) ||
                                                                                           Integer.toString(c.getId()).startsWith(text) ||
                                                                                           c.getType().toLowerCase().startsWith(text.toLowerCase()) ||
                                                                                           c.getStatus().toLowerCase().startsWith(text.toLowerCase()) ||
                                                                                           c.getCreatedAt().toString().toLowerCase().startsWith(text.toLowerCase())
                                                                                           ).toList();
        noticeList.clear();
        noticeList.addAll(filteredList);
    }

    public void comboBoxOperations(){
        typeComboBox.setItems(FXCollections.observableArrayList("Info", "Warning", "Success", "Alert"));
        statusComboBox.setItems(FXCollections.observableArrayList("Active", "Inactive"));
    }

    public void clearFields(){
        titleField.clear();
        contentArea.clear();
        typeComboBox.getSelectionModel().clearSelection();
        statusComboBox.getSelectionModel().clearSelection();
        createdByStaticField.setText(LoginController.user.getName());
    }

    public void noticeListUpdate(){
        NoticeService noticeService = new NoticeService();
        noticeList.clear();
        noticeList.addAll(noticeService.getAllNotices());
    }

    public void tableOperations(){
        NoticeService noticeService = new NoticeService();
        List<Notice> notices = noticeService.getAllNotices();

        if(notices != null){

            noticeList.addAll(notices);

            idColumn.setCellValueFactory(c-> new SimpleIntegerProperty(c.getValue().getId()));
            titleColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getTitle()));
            createdByColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getCreatedBy()));
            typeColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getType()));
            statusColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getStatus()));
            modifiedAtColumn.setCellValueFactory(c-> new SimpleObjectProperty<LocalDateTime>(c.getValue().getModifiedAt()));
            createdAtColumn.setCellValueFactory(c-> new SimpleObjectProperty<LocalDateTime>(c.getValue().getCreatedAt()));

            setupActionColumn();

            noticeTable.setItems(noticeList);
        }


    }

    public void setupActionColumn() {
        actionColumn.setCellFactory(col -> new TableCell<Notice, Void>() {
            private final Button editButton = new Button(" Edit ");
            private final Button viewButton = new Button(" View ");
            private final HBox container = new HBox(8, viewButton, editButton);

            {

                editButton.getStyleClass().add("edit-btn");
                viewButton.getStyleClass().add("report-btn");

                viewButton.setOnAction(event -> {
                    Notice selectedNotice = getTableView().getItems().get(getIndex());
                    onViewNotice(selectedNotice);
                });

                editButton.setOnAction(event -> {
                    Notice selectedNotice = getTableView().getItems().get(getIndex());
                    editNoticeSetup(selectedNotice);
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : container);
            }

        });
    }


    @FXML
    public void onViewNotice(Notice notice) {
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setTitle("Notice Details");
        dialog.setHeaderText(notice.getTitle());
        dialog.setContentText(
                "Type: " + notice.getType() + "\n\n" +
                        "Content: " + notice.getContent() + "\n\n" +
                        "Created By: " + notice.getCreatedBy() + "\n" +
                        "Created At: " + notice.getCreatedAt() + "\n" +
                        "Modified At: " + notice.getModifiedAt()
        );
        dialog.showAndWait();
    }

    public void editNoticeSetup(Notice notice) {
        this.selectedNotice = notice;
        titleField.setText(notice.getTitle());
        contentArea.setText(notice.getContent());
        typeComboBox.setValue(notice.getType());
        statusComboBox.setValue(notice.getStatus());
        createdByStaticField.setText(notice.getCreatedBy());
        saveButton.setText("Update");
        noticeHeaderLabel.setText("Edit Notice");
        scrollPane.setVvalue(0);
    }



}
