/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import data.Cart;
import datamodifier.BillDetailModifier;
import datamodifier.BillModifier;
import datamodifier.CartModifier;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class ViewCartController implements Initializable {

    int lUserId, lBillId, lQuantity, lProductId;
    String lProductName, lUnit;
    double lPrice, lAmount;
    int total;
    String bdNumberInCart;
    String bdNumberInCarts;

    @FXML
    private TableView<Cart> cartTableView;
    @FXML
    private TableColumn<Cart, Integer> productId;
    @FXML
    private TableColumn<Cart, String> productName;
    @FXML
    private TableColumn<Cart, Integer> quantity;
    @FXML
    private TableColumn<Cart, Double> price;
    @FXML
    private TableColumn<Cart, Double> amount;
    @FXML
    private Label totalLabel;
    @FXML
    private Button checkOutLabel;
    @FXML
    private TableColumn<Cart, String> unit;
    @FXML
    private Label numberProductLabel;
    @FXML
    private VBox editCartVBox;
    @FXML
    private Label setProId;
    @FXML
    private Label setProName;
    @FXML
    private Spinner<Integer> setQuantity;
    @FXML
    private Label setUnit;
    @FXML
    private Label setPrice;
    @FXML
    private Label setAmount;
    @FXML
    private BorderPane homeBox;
    @FXML
    private Label bdShoppingCart;
    @FXML
    private Label bdTotal;
    @FXML
    private Label bdProductId;
    @FXML
    private Label bdProductName;
    @FXML
    private Label bdQuantity;
    @FXML
    private Label bdUnit;
    @FXML
    private Label bdPrice;
    @FXML
    private Label bdAmount;
    @FXML
    private Button bdRemove;
    @FXML
    private Button bdUpdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            lUserId = HomeMemberController.gUserId;
            lBillId = new BillModifier().getMaxBillId(lUserId);
            
            setLanguage();
            
            getTotalLabel();

            getProductAllInCart();
        } catch (SQLException ex) {
            Logger.getLogger(ViewCartController.class.getName()).log(Level.SEVERE, null, ex);
        }

        hideEditCart(false);

        
    }

    private void setLanguage() {
        String langMember = HomeMemberController.gLanguage;
        if (langMember.equalsIgnoreCase("english")) {
            changeLanguage("en", "EN");
        } else {
            changeLanguage("vi", "VN");
        }
    }

    private void changeLanguage(String language, String country) {

        Locale locale = new Locale(language, country);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/view_cart/Bundle", locale);

        bdNumberInCart = resourceBundle.getString("bdNumberInCart");
        bdNumberInCarts = resourceBundle.getString("bdNumberInCarts");

        checkOutLabel.setText(resourceBundle.getString("checkOutLabel"));
        bdShoppingCart.setText(resourceBundle.getString("bdShoppingCart"));
        bdTotal.setText(resourceBundle.getString("bdTotal"));
        bdProductId.setText(resourceBundle.getString("bdProductId"));
        bdProductName.setText(resourceBundle.getString("bdProductName"));
        bdQuantity.setText(resourceBundle.getString("bdQuantity"));
        bdUnit.setText(resourceBundle.getString("bdUnit"));
        bdPrice.setText(resourceBundle.getString("bdPrice"));
        bdAmount.setText(resourceBundle.getString("bdAmount"));
        bdRemove.setText(resourceBundle.getString("bdRemove"));
        bdUpdate.setText(resourceBundle.getString("bdUpdate"));
    }

    private void setEditCartInfo() {
        setProId.setText(lProductId + "");
        setProName.setText(lProductName);
        setUnit.setText(lUnit);
        setPrice.setText(lPrice + "");
        setAmount.setText(lAmount + "");
    }

    private void hideEditCart(boolean value) {
        editCartVBox.setVisible(value);
        editCartVBox.managedProperty().bind(editCartVBox.visibleProperty());
    }

    private void setSpiderQuantity(Integer value) {
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
        valueFactory.setValue(value);
        setQuantity.setEditable(true);
        setQuantity.setValueFactory(valueFactory);
        lQuantity = setQuantity.getValue();

        setQuantity.valueProperty().addListener((o) -> {
            lQuantity = setQuantity.getValue();
        });

    }

//    get total 
    public void getTotalLabel() throws SQLException {

        List<Cart> list = new CartModifier().viewProduct(lBillId);
        int sum = 0;
        for (Cart cart : list) {
            sum += cart.getAmount();
        }
        totalLabel.setText(sum + "");
        total = sum;
    }

//    get all product inside cart
    public void getProductAllInCart() throws SQLException {
        CartModifier cartModifier = new CartModifier();
        ObservableList oLists = FXCollections.observableArrayList();

        lBillId = new BillModifier().getMaxBillId(lUserId);
        int numberProduct = cartModifier.getNumberProduct(lBillId);

        if (numberProduct == 1) {
            numberProductLabel.setText("1 " + bdNumberInCart);
        } else {
            if (numberProduct == 0) {
                numberProductLabel.setText("0 " + bdNumberInCart);
            }
            numberProductLabel.setText(numberProduct + " " + bdNumberInCarts);
        }

        oLists = cartModifier.viewProduct(lBillId);

        productId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        unit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        cartTableView.setItems(oLists);
    }

    @FXML
    private void checkOutClicked(MouseEvent event) throws SQLException, IOException {
//        event clicked to checkout label
        if (total != 0) {
            if (new BillModifier().addBill(lUserId)) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Notification");
                alert.setHeaderText("Success");
                alert.setContentText("Successful payment");
                alert.showAndWait();

                new CartModifier().exportCartInfo(lBillId, lUserId);

//            reload quantity product inside cart
                getProductAllInCart();

//            reset value of total = 0
                totalLabel.setText("" + 0);

                hideEditCart(false);
            }
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Notification");
            alert.setHeaderText("Error");
            alert.setContentText("Cart is empty");
            alert.showAndWait();
        }
    }

    @FXML
    private void tableViewCartClicked(MouseEvent event) throws IOException {
        Cart item = cartTableView.getSelectionModel().getSelectedItem();
        if (item != null) {
            lProductId = item.getProductId();
            lProductName = item.getProductName();
            lUnit = item.getUnit();
            lPrice = item.getPrice();
            lAmount = item.getAmount();

            hideEditCart(true);
            setSpiderQuantity(item.getQuantity());
            setEditCartInfo();
        }
    }

    @FXML
    private void removeAction(ActionEvent event) throws SQLException {
        if (new BillDetailModifier().deleteOnCart(lProductId, lBillId)) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Notification");
            alert.setHeaderText("Success");
            alert.setContentText("Remove product is successfully.");
            alert.showAndWait();

            getProductAllInCart();
            hideEditCart(false);
            getTotalLabel();
        }
    }

    @FXML
    private void updateAction(ActionEvent event) throws SQLException {
        if (new BillDetailModifier().updateQuantity(lQuantity, lBillId, lProductId)) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Notification");
            alert.setHeaderText("Success");
            alert.setContentText("Update quantity is successfully.");
            alert.showAndWait();

            getProductAllInCart();
            hideEditCart(false);
            getTotalLabel();
        }
    }

}
