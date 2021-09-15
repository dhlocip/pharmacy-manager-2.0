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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
    @FXML
    private ComboBox<Integer> impBatchIdComboBox;
    @FXML
    private ComboBox<Integer> proIdComboBox;
    @FXML
    private TextField impPriceTextField;
    @FXML
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
            userId = HomeManagerController.userId;

//        set value for impId
            impId = new ImportBatchModifier().getMaxImpBatchId(userId);

//      get importBatch info
            getImpBatchInfo(userId);

//      get importBatchDetail info
            getImpBatchDetailInfo(userId);

            //        get value of combobox importbatchid
            getValueProIdListComboBox();

//        get value of combobox importbatchid
            getValueImpIdListComboBox();

//            get total product inside an importBatchId
            getTotalProductInImportDetail();
//            if (total == 0) {
//                impBatchDetailLabel.setDisable(true);
//            } else {
//                impBatchDetailLabel.setDisable(false);
//            }

        } catch (SQLException ex) {
            Logger.getLogger(ImportProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    get total product inside an importId
    private void getTotalProductInImportDetail() throws SQLException {
        total = new ImportBatchDetailModifier().getNumberProduct(impId);
    }

    //        get value of combobox importbatchid
    private void getValueImpIdListComboBox() throws SQLException {
        ObservableList<Integer> impIdList = new ImportBatchModifier().getListImpId(userId);
        impBatchIdComboBox.setItems(impIdList);
        impBatchIdComboBox.setValue(impIdList.get(0));

        impBatchIdComboBox.setOnAction((t) -> {
            currentImpId = impBatchIdComboBox.getValue();
        });
    }

    //        get value of combobox importbatchid
    private void getValueProIdListComboBox() throws SQLException {
        ObservableList<Integer> proIdList = new ProductChildModifier().getListProId();
        proIdComboBox.setItems(proIdList);
        proIdComboBox.setValue(proIdList.get(0));

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

    @FXML
    private void impNewBatchClicked(MouseEvent event) throws SQLException {
        total = new ImportBatchDetailModifier().getNumberProduct(impId);

        if (total > 0) {
            if (new ImportBatchModifier().addImpBatch(userId)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notification");
                alert.setHeaderText("Success");
                alert.setContentText("Import new batch successfully");
                alert.showAndWait();

//            reload import new batch 
                getImpBatchInfo(userId);

//            reload list importId combobox
                getValueImpIdListComboBox();

//                  get total product inside an importBatchId
                getTotalProductInImportDetail();
                if (total == 0) {
                    impBatchDetailLabel.setDisable(true);
                } else {
                    impBatchDetailLabel.setDisable(false);
                }

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
        if (isPriceRight() && isQuantityRight()
                && isMfgDateRight() && isExpDateRight()) {
            System.out.println("true");
            System.out.println(mfgDate);
            System.out.println(expDate);
        } else {
            System.out.println("sai");
        }
    }

    @FXML
    private void impDetailBatchClicked(MouseEvent event) throws SQLException {
        getValueImpIdListComboBox();
        getValueProIdListComboBox();
        ImportBatchDetail impBatchDetail = new ImportBatchDetail();
//ngu ti thuc lam tiep
        impBatchDetail.setImpId(currentImpId);
        impBatchDetail.setProId(currentProId);
        impBatchDetail.setPrice(price);
        impBatchDetail.setQuantity(quantity);
        impBatchDetail.setMfgDate(mfgDate);
        impBatchDetail.setExpDate(expDate);

        System.out.println(impBatchDetail.getImpId());
        System.out.println(impBatchDetail.getProId());
        System.out.println(impBatchDetail.getPrice());
        System.out.println(impBatchDetail.getQuantity());
        System.out.println(impBatchDetail.getMfgDate());
        System.out.println(impBatchDetail.getExpDate());
//
//        if (isPriceRight() && isQuantityRight()
//                && isMfgDateRight() && isExpDateRight()) {
//            System.out.println("true");
//            if (new ImportBatchDetailModifier().addBatchDetail(impBatchDetail)) {
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Notification");
//                alert.setHeaderText("Success");
//                alert.setContentText("Import detail batch successfully");
//                alert.showAndWait();
//            }
//        } else {
//            System.out.println("fail");
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Notification");
//            alert.setHeaderText("Error");
////            alert.setContentText("ImportBatchId " + impId + " is null. Please import detail batch!");
//            alert.showAndWait();
//        }

        //      get importBatchDetail info
        getImpBatchDetailInfo(userId);

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
        String tmp = quantityTextField.getText();
        if (!tmp.isEmpty() && tmp.matches("^[1-9]{1}[\\d]*")) {
            quantity = Integer.parseInt(quantityTextField.getText());
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
        if (isPriceRight() && isQuantityRight()
                && isMfgDateRight() && isExpDateRight()) {
            impBatchDetailLabel.setDisable(false);
        }
    }

    @FXML
    private void quantityReleased(KeyEvent event) {
        if (isPriceRight() && isQuantityRight()
                && isMfgDateRight() && isExpDateRight()) {
            impBatchDetailLabel.setDisable(false);
        }
    }

    @FXML
    private void mfgDateClicked(MouseEvent event) {
        if (isPriceRight() && isQuantityRight()
                && isMfgDateRight() && isExpDateRight()) {
            impBatchDetailLabel.setDisable(false);
        }
    }

    @FXML
    private void expDateClicked(MouseEvent event) {
        if (isPriceRight() && isQuantityRight()
                && isMfgDateRight() && isExpDateRight()) {
            impBatchDetailLabel.setDisable(false);
        }
    }

}
