/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import data.ProductChild;
import data.ProductType;
import data.Products;
import datamodifier.ProductChildModifier;
import datamodifier.ProductTypeModifier;
import datamodifier.ProductsModifier;
import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.css.CssParser;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.*;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class UpdateProductController implements Initializable {

    Integer lProductTypeId;
    int lProductId;
    String lProductName;
    String lUnit;
    double lPrice;

    @FXML
    private ComboBox<Integer> productTypeIdComboBox;
    @FXML
    private TextField productNameTextField;
    @FXML
    private Label errorOfProductName;
    @FXML
    private TextField unitTextField;
    @FXML
    private Label errorOfUnit;
    @FXML
    private TextField priceTextField;
    @FXML
    private Label errorOfPrice;
    @FXML
    private TableView<ProductType> productTypeTableView;
    @FXML
    private TableColumn<ProductType, Integer> productTypeId;
    @FXML
    private TableColumn<ProductType, String> productTypeName;
    @FXML
    private TableView<Products> productDetailTableView;
    @FXML
    private TableColumn<Products, Integer> productTypeIdDetail;
    @FXML
    private TableColumn<Products, Integer> productIdDetail;
    @FXML
    private TableColumn<Products, String> productNameDetail;
    @FXML
    private TableColumn<Products, String> unitDetail;
    @FXML
    private TableColumn<Products, Double> priceDetail;
    @FXML
    private TextField searchTextField;
    @FXML
    private Label errorOfProductTypeId;
    @FXML
    private Label bdProductTypeId;
    @FXML
    private Label bdProductName;
    @FXML
    private Label bdUnit;
    @FXML
    private Label bdPrice;
    @FXML
    private Label bdUpdateProduct;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
//            get table product type info
            getProductTypeInfo();

//            get table products info
            getProductsInfo();

//            set product type ID
            setProductTypeId();

        } catch (SQLException ex) {
            Logger.getLogger(UpdateProductController.class.getName()).log(Level.SEVERE, null, ex);
        }

        hideErrorOfPrice(false);
        hideErrorOfUnit(false);
        hideErrorOfProTypeId(false);
        hideErrorOfProName(false);
        
        setLanguage();
    }
    
    private void setLanguage() {
        String langManager = HomeManagerController.gLanguage;
        if (langManager.equalsIgnoreCase("english")) {
            changeLanguage("en", "EN");
        } else {
            changeLanguage("vi", "VN");
        }
    }

    private void changeLanguage(String language, String country) {

        Locale locale = new Locale(language, country);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/update_product/Bundle", locale);

        searchTextField.setPromptText(resourceBundle.getString("searchTextField"));
        
        bdProductTypeId.setText(resourceBundle.getString("bdProductTypeId"));
        bdProductName.setText(resourceBundle.getString("bdProductName"));
        bdUnit.setText(resourceBundle.getString("bdUnit"));
        bdPrice.setText(resourceBundle.getString("bdPrice"));
        bdUpdateProduct.setText(resourceBundle.getString("bdUpdateProduct"));
        
        productNameTextField.setPromptText(resourceBundle.getString("productNameTextField"));
        unitTextField.setPromptText(resourceBundle.getString("unitTextField"));
        priceTextField.setPromptText(resourceBundle.getString("priceTextField"));
        
    }

    private void setProductTypeId() throws SQLException {
        ObservableList<Integer> list = new ProductTypeModifier().getListProTypeId();
        productTypeIdComboBox.setItems(list);

        productTypeIdComboBox.setOnAction((t) -> {
            lProductTypeId = productTypeIdComboBox.getValue();
        });

    }

    private void getProductTypeInfo() throws SQLException {
        ObservableList<ProductType> oList = new ProductTypeModifier().getInfo();

        productTypeId.setCellValueFactory(new PropertyValueFactory<>("id"));
        productTypeName.setCellValueFactory(new PropertyValueFactory<>("name"));

        productTypeTableView.setItems(oList);
    }

    private void getProductsInfoWhenSearch(String productName) throws SQLException {
        ObservableList<Products> oList = new ProductsModifier().getInfoByIdOrName(productName);

        productTypeIdDetail.setCellValueFactory(new PropertyValueFactory<>("productTypeId"));
        productIdDetail.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productNameDetail.setCellValueFactory(new PropertyValueFactory<>("productName"));
        unitDetail.setCellValueFactory(new PropertyValueFactory<>("unit"));
        priceDetail.setCellValueFactory(new PropertyValueFactory<>("price"));

        productDetailTableView.setItems(oList);
    }

    private void getProductsInfo() throws SQLException {
        ObservableList<Products> oList = new ProductsModifier().getInfo();

        productTypeIdDetail.setCellValueFactory(new PropertyValueFactory<>("productTypeId"));
        productIdDetail.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productNameDetail.setCellValueFactory(new PropertyValueFactory<>("productName"));
        unitDetail.setCellValueFactory(new PropertyValueFactory<>("unit"));
        priceDetail.setCellValueFactory(new PropertyValueFactory<>("price"));

        productDetailTableView.setItems(oList);
    }

    private void hideErrorOfProTypeId(boolean value) {
        errorOfProductTypeId.setVisible(value);
        errorOfProductTypeId.managedProperty().bind(errorOfProductTypeId.visibleProperty());
    }

    private void hideErrorOfProName(boolean value) {
        errorOfProductName.setVisible(value);
        errorOfProductName.managedProperty().bind(errorOfProductName.visibleProperty());
    }

    private void hideErrorOfUnit(boolean value) {
        errorOfUnit.setVisible(value);
        errorOfUnit.managedProperty().bind(errorOfUnit.visibleProperty());
    }

    private void hideErrorOfPrice(boolean value) {
        errorOfPrice.setVisible(value);
        errorOfPrice.managedProperty().bind(errorOfPrice.visibleProperty());
    }

    private boolean isProNameRight() {
        String tmp = productNameTextField.getText();
        return tmp.matches("^[a-zA-Z]{1}[\\w\\s]{2,30}");
    }

    @FXML
    private void productNameReleased(KeyEvent event) {
        if (isProNameRight()) {
            lProductName = productNameTextField.getText();
            hideErrorOfProName(false);
        } else {
            hideErrorOfProName(true);
            errorOfProductName.setText(productNameTextField.getText() + " is invalid.");
        }
    }

    private boolean isUnitRight() {
        String tmp = unitTextField.getText();
        return tmp.matches("^[a-zA-Z]{1}[a-zA-Z\\s]{1,30}");
    }

    @FXML
    private void unitReleased(KeyEvent event) {
        if (isUnitRight()) {
            lUnit = unitTextField.getText();
            hideErrorOfUnit(false);
        } else {
            hideErrorOfUnit(true);
            errorOfUnit.setText(unitTextField.getText() + " is invalid.");
        }
    }

    private boolean isPriceRight() {
        String tmp = priceTextField.getText();
        return tmp.matches("^[1-9]{1}[\\d]*") || tmp.matches("^[1-9]{1}[\\d]*[.]?[\\d]+");
    }

    @FXML
    private void priceReleased(KeyEvent event) {
        if (isPriceRight()) {
            lPrice = Double.parseDouble(priceTextField.getText());
            hideErrorOfPrice(false);
        } else {
            hideErrorOfPrice(true);
            errorOfPrice.setText(priceTextField.getText() + " is invalid.");
        }
    }

    @FXML
    private void updateProductClicked(MouseEvent event) throws SQLException {

        if (lProductTypeId != null && isPriceRight() && isUnitRight() && isProNameRight()) {
            Products items = productDetailTableView.getSelectionModel().getSelectedItem();
            lPrice = Double.parseDouble(priceTextField.getText());
            lUnit = unitTextField.getText();
            lProductName = productNameTextField.getText();
            lProductTypeId = productTypeIdComboBox.getValue();

            if (items != null) {
                items.setProductTypeId(lProductTypeId);
                items.setProductName(lProductName);
                items.setUnit(lUnit);
                items.setPrice(lPrice);

                if (new ProductsModifier().updateProducts(items)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Notification");
                    alert.setHeaderText("Success");
                    alert.setContentText("update product is successfully.");
                    alert.showAndWait();

//                reload table products 
                    getProductsInfo();
                }
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Notification");
            alert.setHeaderText("Error");
            alert.setContentText("Please click to item.");
            alert.showAndWait();

            if (!isPriceRight()) {
                hideErrorOfPrice(true);
                errorOfPrice.setText("Price is invalid.");
            }

            if (!isUnitRight()) {
                hideErrorOfUnit(true);
                errorOfUnit.setText("Unit is invalid.");
            }

            if (!isProNameRight()) {
                hideErrorOfProName(true);
                errorOfProductName.setText("ProductName is invalid.");
            }

            if (lProductTypeId == null) {
                hideErrorOfProTypeId(true);
                errorOfProductTypeId.setText("ProductTypeId can't empty.");
            }
        }
    }

    @FXML
    private void searchReleased(KeyEvent event) throws SQLException {
        getProductsInfoWhenSearch(searchTextField.getText());
    }

    @FXML
    private void productTypeTableClicked(MouseEvent event) {
    }

    @FXML
    private void productTableClicked(MouseEvent event) throws SQLException {
        Products items = productDetailTableView.getSelectionModel().getSelectedItem();
        if (items != null) {
            productNameTextField.setText(items.getProductName());
            productTypeIdComboBox.setValue(items.getProductTypeId());
            unitTextField.setText(items.getUnit());
            priceTextField.setText(String.valueOf(items.getPrice()));

            hideErrorOfPrice(false);
            hideErrorOfUnit(false);
            hideErrorOfProTypeId(false);
            hideErrorOfProName(false);

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Notification");
            alert.setHeaderText("Error");
            alert.setContentText("Please click to item.");
            alert.showAndWait();
        }
    }

    @FXML
    private void proTypeIdAction(ActionEvent event) {
        if (productTypeIdComboBox.getValue() != null) {
            hideErrorOfProTypeId(false);
        }
    }

}
