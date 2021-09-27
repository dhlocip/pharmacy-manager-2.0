/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import data.ProductType;
import data.Products;
import datamodifier.ProductTypeModifier;
import datamodifier.ProductsModifier;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class AddProductController implements Initializable {

    Integer proTypeId;
    String proTypeName;
    String proName;
    String proUnit;
    double proPrice;

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
    private TextField productTypeNameTextField;
    @FXML
    private Label errorOfProductTypeName;
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
    private Label bdProTypeName;
    @FXML
    private Label bdAddProType;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // get all product type info
            getInfoTableProductType();

//            get all products info
            getInfoTableProducts();

//            set value for product type id comboBox
            setValueProTypeIdComboBox();

        } catch (SQLException ex) {
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void setValueProTypeIdComboBox() throws SQLException {
        ObservableList<Integer> oList = new ProductTypeModifier().getListProTypeId();
        productTypeIdComboBox.setItems(oList);
        productTypeIdComboBox.setValue(oList.get(0));

        proTypeId = productTypeIdComboBox.getValue();

        productTypeIdComboBox.setOnAction((t) -> {
            proTypeId = productTypeIdComboBox.getValue();
        });

    }

//    get info table product type
    private void getInfoTableProductType() throws SQLException {
        ObservableList oList = new ProductTypeModifier().getInfo();

        productTypeId.setCellValueFactory(new PropertyValueFactory<>("id"));
        productTypeName.setCellValueFactory(new PropertyValueFactory<>("name"));

        productTypeTableView.setItems(oList);
    }

//    get info table products
    private void getInfoTableProducts() throws SQLException {
        ObservableList oList = new ProductsModifier().getInfo();

        productTypeIdDetail.setCellValueFactory(new PropertyValueFactory<>("productTypeId"));
        productIdDetail.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productNameDetail.setCellValueFactory(new PropertyValueFactory<>("productName"));
        unitDetail.setCellValueFactory(new PropertyValueFactory<>("unit"));
        priceDetail.setCellValueFactory(new PropertyValueFactory<>("price"));

        productDetailTableView.setItems(oList);
    }

    private void hideErrorOfProTypeName(boolean value) {
        errorOfProductTypeName.setVisible(value);
        errorOfProductTypeName.managedProperty().bind(errorOfProductTypeName.visibleProperty());
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

    @FXML
    private void addProductTypeClicked(MouseEvent event) throws SQLException {
        boolean check = new ProductTypeModifier().matchProductType(productTypeNameTextField.getText());
        if (isProTypeNameRight() && !check) {
            if (new ProductTypeModifier().addProductTypeNew(proTypeName)) {
                getInfoTableProductType();
                setValueProTypeIdComboBox();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notification");
                alert.setHeaderText("Success");
                alert.setContentText("Add product type is successfully.");
            }
        } else {
            if (!isProTypeNameRight()) {
                hideErrorOfProTypeName(true);
                errorOfProductTypeName.setText("\"" + productTypeNameTextField.getText() + "\" invalid.\n"
                        + "Product type name only get character value.");
            }
        }

    }

    @FXML
    private void addProductDetailClicked(MouseEvent event) throws SQLException {
        if (isProNameRight() && isUnitRight() && isPriceRight()) {
            Products prod = new Products();
            prod.setProductTypeId(proTypeId);
            prod.setProductName(proName);
            prod.setUnit(proUnit);
            prod.setPrice(proPrice);

            if (new ProductsModifier().insertIntoProducts(prod)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notification");
                alert.setHeaderText("Success");
                alert.setContentText("Add product detail is successfully.");
                alert.showAndWait();

                getInfoTableProducts();
            }

        } else {
            if (!isProNameRight()) {
                hideErrorOfProName(true);
                errorOfProductName.setText("\"" + productNameTextField.getText() + "\" invalid.\n"
                        + "Product type name only get character value.");
            }
            if (!isUnitRight()) {
                hideErrorOfUnit(true);
                errorOfUnit.setText("\"" + unitTextField.getText() + "\" invalid.\n"
                        + "Product type name only get character value.");
            }
            if (!isPriceRight()) {
                hideErrorOfPrice(true);
                errorOfPrice.setText("\"" + priceTextField.getText() + "\" invalid.\n"
                        + "Please enter value greater than 0.");
            }
        }

    }

    private boolean isPriceRight() {
        String tmp = String.valueOf(priceTextField.getText());
        if ((!tmp.isEmpty() && tmp.matches("^[1-9]{1}[\\d]*[.]{1}[\\d]+"))
                || (!tmp.isEmpty() && tmp.matches("^[1-9]{1}[\\d]*"))) {
            proPrice = Double.parseDouble(tmp);
            return true;
        }
        return false;
    }

    @FXML
    private void priceReleased(KeyEvent event) {
        if (isPriceRight()) {
            hideErrorOfPrice(false);
        } else {
            hideErrorOfPrice(true);
            errorOfPrice.setText("\"" + priceTextField.getText() + "\" invalid.\n"
                    + "Please enter value greater than 0.");
        }
    }

    private boolean isProTypeNameRight() {
        String tmp = String.valueOf(productTypeNameTextField.getText());
        if (!tmp.isEmpty() && tmp.matches("^[a-zA-Z]{1}[a-zA-Z\\s]{3,30}")) {
            proTypeName = tmp;
            return true;
        }
        return false;
    }

    @FXML
    private void productTypeNameReleased(KeyEvent event) throws SQLException {
        if (isProTypeNameRight()) {
            boolean check = new ProductTypeModifier().matchProductType(productTypeNameTextField.getText());
            if (check) {
                hideErrorOfProTypeName(true);
                errorOfProductTypeName.setText(productTypeNameTextField.getText() + " already exists.");
            } else {
                hideErrorOfProTypeName(false);
            }
        } else {
            hideErrorOfProTypeName(true);
            errorOfProductTypeName.setText("\"" + productTypeNameTextField.getText() + "\" invalid.\n"
                    + "Product type name only get character value.");
        }

    }

    private boolean isProNameRight() {
        String tmp = String.valueOf(productNameTextField.getText());
        if (!tmp.isEmpty() && tmp.matches("^[a-zA-Z]{1}[a-zA-Z\\s]{3,30}")) {
            proName = tmp;
            return true;
        }
        return false;
    }

    @FXML
    private void productNameReleased(KeyEvent event) {
        if (isProNameRight()) {
            hideErrorOfProName(false);
        } else {
            hideErrorOfProName(true);
            errorOfProductName.setText("\"" + productNameTextField.getText() + "\" invalid.\n"
                    + "Product type name only get character value.");
        }
    }

    private boolean isUnitRight() {
        String tmp = String.valueOf(unitTextField.getText());
        if (!tmp.isEmpty() && tmp.matches("^[a-zA-Z]{1}[a-zA-Z\\s]{1,30}")) {
            proUnit = tmp;
            return true;
        }
        return false;
    }

    @FXML
    private void unitReleased(KeyEvent event) {
        if (isUnitRight()) {
            hideErrorOfUnit(false);
        } else {
            hideErrorOfUnit(true);
            errorOfUnit.setText("\"" + unitTextField.getText() + "\" invalid.\n"
                    + "Product type name only get character value.");
        }
    }

}
