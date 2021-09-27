/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import data.*;
import datamodifier.BillModifier;
import datamodifier.ImportBatchDetailModifier;
import datamodifier.ImportBatchModifier;
import datamodifier.ProductChildModifier;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class ImportProductController implements Initializable {

    int userId;
    int impId;
    int currentProId;
    int currentImpId;
    double price;
    int quantity;
    String mfgDate;
    String expDate;
    int total;

    @FXML
    private TableView<ProductChild> productTV;
    @FXML
    private TableColumn<ProductChild, Integer> proIdCol;
    @FXML
    private TableColumn<ProductChild, String> proNameCol;
    private ComboBox<Integer> impBatchIdComboBox;
    @FXML
    private ComboBox<Integer> proIdComboBox;
    @FXML
    private TextField impPriceTextField;
    private TextField quantityTextField;
    @FXML
    private DatePicker mfgDateTextField;
    @FXML
    private DatePicker expDateTextField;
    @FXML
    private HBox impBatchDetailLabel;
    @FXML
    private TableView<ImportBatch> impBatchTV;
    @FXML
    private TableColumn<ImportBatch, Integer> impBatchIdNew;
    @FXML
    private TableColumn<ImportBatch, String> impDateNew;
    @FXML
    private HBox impNewBatchLabel;
    @FXML
    private TableView<ImportBatchDetail> impBatchDetailTV;
    @FXML
    private TableColumn<ImportBatchDetail, Integer> impBatchIdDetail;
    @FXML
    private TableColumn<ImportBatchDetail, Integer> proIdDetail;
    @FXML
    private TableColumn<ImportBatchDetail, Double> priceDetail;
    @FXML
    private TableColumn<ImportBatchDetail, Integer> quantityDetail;
    @FXML
    private TableColumn<ImportBatchDetail, String> mfgDateDetail;
    @FXML
    private TableColumn<ImportBatchDetail, String> expDateDetail;
    @FXML
    private HBox impFileExcelLabel;
    @FXML
    private Label errorOfPrice;
    @FXML
    private Label errorOfQuantity;
    @FXML
    private Label errorOfMfgDate;
    private Label errorOfexpDate;
    @FXML
    private TextField impQuantityTextField;
    @FXML
    private Label errorOfExpDate;
    @FXML
    private Label errorOfProductId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
//        get product info
            getProductInfo();

//        load userId
            userId = HomeManagerController.gUserId;
            System.out.println(userId);

//        set value for impId
            impId = new ImportBatchModifier().getMaxImpBatchId(userId);

//      get importBatch info
            getImpBatchInfo(userId);

//      get importBatchDetail info
            getImpBatchDetailInfo(userId);

//        get value of combobox importbatchid
            getValueProIdListComboBox();

//        get value of combobox importbatchid
//            getValueImpIdListComboBox();
//            get total product of an importBatchId
            getTotalProductInImportDetail();
//            if (total == 0) {
//                impBatchDetailLabel.setDisable(true);
//            } else {
//                impBatchDetailLabel.setDisable(false);
//            }

        } catch (SQLException ex) {
            Logger.getLogger(ImportProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
//        impBatchDetailLabel.setDisable(true);

        hideErrorProductId(false);
        hideErrorPrice(false);
        hideErrorQuantity(false);
        hideErrorMfgDate(false);
        hideErrorExpDate(false);

    }

//    get total product inside an importId
    private void getTotalProductInImportDetail() throws SQLException {
//        reload importID
        impId = new ImportBatchModifier().getMaxImpBatchId(userId);
//        count product in importBatchDetail
        total = new ImportBatchDetailModifier().getNumberProduct(impId);
    }

    //        get value of combobox importbatchid
//    private void getValueImpIdListComboBox() throws SQLException {
//        ObservableList<Integer> impIdList = new ImportBatchModifier().getListImpId(userId);
//        impBatchIdComboBox.setItems(impIdList);
//        impBatchIdComboBox.setValue(impIdList.get(0));
//
//        impBatchIdComboBox.setOnAction((t) -> {
//            currentImpId = impBatchIdComboBox.getValue();
//        });
//    }
    //        get value of combobox importbatchid
    private void getValueProIdListComboBox() throws SQLException {
        ObservableList<Integer> proIdList = new ProductChildModifier().getListProId();
        proIdComboBox.setItems(proIdList);
        proIdComboBox.setValue(proIdList.get(0));
        currentProId = proIdComboBox.getValue();

        proIdComboBox.setOnAction((t) -> {
            currentProId = proIdComboBox.getValue();
        });
    }

    private void getImpBatchInfo(int userId) throws SQLException {
        ObservableList oList = new ImportBatchModifier().getImpBatchInfo(userId);

        impBatchIdNew.setCellValueFactory(new PropertyValueFactory<>("importId"));
        impDateNew.setCellValueFactory(new PropertyValueFactory<>("date"));

        impBatchTV.setItems(oList);

    }

    private void getImpBatchDetailInfo(int userId) throws SQLException {
        ObservableList oList = new ImportBatchDetailModifier().getImpBatchDetailInfo(userId);

        impBatchIdDetail.setCellValueFactory(new PropertyValueFactory<>("impId"));
        proIdDetail.setCellValueFactory(new PropertyValueFactory<>("proId"));
        priceDetail.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityDetail.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        mfgDateDetail.setCellValueFactory(new PropertyValueFactory<>("mfgDate"));
        expDateDetail.setCellValueFactory(new PropertyValueFactory<>("expDate"));

        impBatchDetailTV.setItems(oList);

    }

    private void getProductInfo() throws SQLException {
        ObservableList oList = new ProductChildModifier().getInfo();

        proIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        proNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        productTV.setItems(oList);
    }

    private void hideErrorProductId(boolean value) {
        errorOfProductId.setVisible(value);
        errorOfProductId.managedProperty().bind(errorOfProductId.visibleProperty());
    }
    
    private void hideErrorPrice(boolean value) {
        errorOfPrice.setVisible(value);
        errorOfPrice.managedProperty().bind(errorOfPrice.visibleProperty());
    }

    private void hideErrorQuantity(boolean value) {
        errorOfQuantity.setVisible(value);
        errorOfQuantity.managedProperty().bind(errorOfQuantity.visibleProperty());
    }

    private void hideErrorMfgDate(boolean value) {
        errorOfMfgDate.setVisible(value);
        errorOfMfgDate.managedProperty().bind(errorOfMfgDate.visibleProperty());
    }

    private void hideErrorExpDate(boolean value) {
        errorOfExpDate.setVisible(value);
        errorOfExpDate.managedProperty().bind(errorOfExpDate.visibleProperty());
    }

    @FXML
    private void impNewBatchClicked(MouseEvent event) throws SQLException {
//        total = new ImportBatchDetailModifier().getNumberProduct(impId);
//        getTotalProductInImportDetail();
        if (total > 0) {
            if (new ImportBatchModifier().addImpNewBatch(userId)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notification");
                alert.setHeaderText("Success");
                alert.setContentText("Import new batch successfully");
                alert.showAndWait();

//            reload import new batch 
                getImpBatchInfo(userId);

//        reload table importBatchDetail info
                getImpBatchDetailInfo(userId);

//                  get total product inside an importBatchId
                getTotalProductInImportDetail();
//                if (total == 0) {
//                    impBatchDetailLabel.setDisable(true);
//                } else {
//                    impBatchDetailLabel.setDisable(false);
//                }

            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Notification");
            alert.setHeaderText("Error");
            alert.setContentText("ImportBatchId " + impId + " is null. Please import detail batch!");
            alert.showAndWait();
        }

    }

    @FXML
    private void impFileExcelClicked(MouseEvent event) {
    }

    @FXML
    private void impDetailBatchClicked(MouseEvent event) throws SQLException, ParseException {

        ImportBatchDetail items = new ImportBatchDetail();
//ngu ti thuc lam tiep
//        String expDate = isExpDateRight();
        items.setImpId(impId);
        items.setProId(currentProId);
        items.setPrice(price);
        items.setQuantity(quantity);
        items.setMfgDate(mfgDate);
        items.setExpDate(expDate);

        if (isPriceRight() && isQuantityRight() && isMfgDateRight() && isExpDateRight()) {
            new ImportBatchDetailModifier().addBatchDetail(items);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notification");
            alert.setHeaderText("Success");
            alert.setContentText("Products are added to the table below.");

//        reload table importBatchDetail info
            getImpBatchDetailInfo(userId);
            
//                  get total product inside an importBatchId
            getTotalProductInImportDetail();

        } else {
            if (isPriceRight()) {
                hideErrorPrice(false);
            } else {
                hideErrorPrice(true);
                errorOfPrice.setText("\"" + impPriceTextField.getText() + "\" is invalid value.\nPlease enter value greater than 0");
            }

            if (isQuantityRight()) {
                hideErrorQuantity(false);
            } else {
                hideErrorQuantity(true);
                errorOfQuantity.setText("\"" + impQuantityTextField.getText() + "\" is invalid value.\nPlease enter integer value.");
            }

            if (isMfgDateRight()) {
                hideErrorMfgDate(false);

            } else {
                hideErrorMfgDate(true);
                errorOfMfgDate.setText("Please select \"MFG Date\" value");
            }

            if (isExpDateRight()) {
                hideErrorExpDate(false);
            } else {
                hideErrorExpDate(true);
                errorOfExpDate.setText("Please select \"EXP Date\" value");
            }
        }

    }

//    check regex price right
    private boolean isPriceRight() {
        String tmp = impPriceTextField.getText();
        if ((!tmp.isEmpty() && tmp.matches("^[1-9]{1}[\\d]*"))
                || (!tmp.isEmpty() && tmp.matches("^[1-9]{1}[\\d]*[.]{1}[\\d]+"))) {
            price = Double.parseDouble(impPriceTextField.getText());
            return true;
        } else {
            return false;
        }

    }

//    check regex quantity right
    private boolean isQuantityRight() {
        String tmp = impQuantityTextField.getText();
        if (!tmp.isEmpty() && tmp.matches("^[1-9]{1}[\\d]*")) {
            quantity = Integer.parseInt(impQuantityTextField.getText());
            return true;
        } else {
            return false;
        }
    }

//    check mfg date is right
    private boolean isMfgDateRight() {
        LocalDate myDate = mfgDateTextField.getValue();
        if (myDate != null) {
            mfgDate = String.valueOf(myDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
            return true;
        } else {
            return false;
        }
    }

    //    get exp date
    private boolean isExpDateRight() {
        LocalDate myDate = expDateTextField.getValue();
        if (myDate != null) {
            expDate = String.valueOf(myDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
            return true;
        } else {
            return false;
        }
    }

    @FXML
    private void priceReleased(KeyEvent event) {
        if (isPriceRight()) {
            hideErrorPrice(false);
        } else {
            hideErrorPrice(true);
            errorOfPrice.setText("\"" + impPriceTextField.getText() + "\" is invalid value.\nPlease enter value greater than 0");
        }
    }

    @FXML
    private void quantityReleased(KeyEvent event) {
//        if (isPriceRight() && isQuantityRight()
//                && isMfgDateRight() != null && isExpDateRight() != null) {
//            impBatchDetailLabel.setDisable(false);
//        } else {
//            impBatchDetailLabel.setDisable(true);
//        }
        if (isQuantityRight()) {
            hideErrorQuantity(false);
        } else {
            hideErrorQuantity(true);
            errorOfQuantity.setText("\"" + impQuantityTextField.getText() + "\" is invalid value.\nPlease enter integer value.");
        }
    }

//    private void mfgDateClicked(MouseEvent event) {
//        if (isMfgDateRight()) {
//            hideErrorMfgDate(false);
//        } else {
//            hideErrorMfgDate(true);
//            errorOfMfgDate.setText("Please select \"MFG Date\" value");
//        }
//    }
//    private void expDateClicked(MouseEvent event) {
//        if (isExpDateRight()) {
//            hideErrorExpDate(false);
//        } else {
//            hideErrorExpDate(true);
//            errorOfExpDate.setText("Please select \"EXP Date\" value");
//        }
//    }
    @FXML
    private void mfgDateClicked(ActionEvent event) throws ParseException {
        if (isMfgDateRight()) {
//          check mfgDate and expDate is valid
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            if (isExpDateRight()) {
                Date startDate = dateFormat.parse(mfgDate);
                Date endDate = dateFormat.parse(expDate);

                if (endDate.compareTo(startDate) < 0) {
                    hideErrorExpDate(false);
                    hideErrorMfgDate(true);
                    errorOfMfgDate.setText("\"" + mfgDate + "\" is invalid value.\n"
                            + "\"MFG Date\" must be less than \"EXP Date\". ");
                } else {
                    hideErrorExpDate(false);
                    hideErrorMfgDate(false);
                }
            } else {
                hideErrorMfgDate(false);
                hideErrorExpDate(true);
                errorOfExpDate.setText("Please select \"EXP Date\" value");
            }
        } else {
            hideErrorMfgDate(true);
            errorOfMfgDate.setText("Please select \"MFG Date\" value");
        }
    }

//    private void priceReleased(ActionEvent event) {
//        if (isPriceRight()) {
//            hideErrorPrice(false);
//        } else {
//            hideErrorPrice(true);
//            errorOfPrice.setText("\"" + impPriceTextField.getText() + "\" is invalid value.\nPlease enter value greater than 0");
//        }
//    }
    @FXML
    private void expDateClicked(ActionEvent event) throws ParseException {
        if (isExpDateRight()) {
//          check mfgDate and expDate is valid
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            if (isMfgDateRight()) {
                Date startDate = dateFormat.parse(mfgDate);
                Date endDate = dateFormat.parse(expDate);

                if (endDate.compareTo(startDate) < 0) {
                    hideErrorExpDate(true);
                    hideErrorMfgDate(false);
                    errorOfExpDate.setText("\"" + expDate + "\" is invalid value.\n"
                            + "\"EXP Date\" must be greater than \"MFG Date\". ");
                } else {
                    hideErrorExpDate(false);
                    hideErrorMfgDate(false);
                }
            } else {
                hideErrorExpDate(false);
                hideErrorMfgDate(true);
                errorOfMfgDate.setText("Please select \"MFG Date\" value");
            }
        } else {
            hideErrorExpDate(true);
            errorOfExpDate.setText("Please select \"EXP Date\" value");
        }
    }

}
