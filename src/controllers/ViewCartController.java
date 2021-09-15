/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import data.Cart;
import datamodifier.BillModifier;
import datamodifier.CartModifier;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class ViewCartController implements Initializable {

    int userId;
    int total;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            getProductAllInCart();
        } catch (SQLException ex) {
            Logger.getLogger(ViewCartController.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            getTotalLabel();
        } catch (SQLException ex) {
            Logger.getLogger(ViewCartController.class.getName()).log(Level.SEVERE, null, ex);
        }

        userId = HomeMemberController.userId;
    }

//    get total 
    public void getTotalLabel() throws SQLException {
        int billId = new BillModifier().getMaxBillId(HomeMemberController.userId);
        List<Cart> list = new CartModifier().viewProduct(billId);
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
        int billId = new BillModifier().getMaxBillId(HomeMemberController.userId);
        int numberProduct = cartModifier.getNumberProduct(billId);

        if (numberProduct == 1) {
            numberProductLabel.setText("1 product in cart");
        } else {
            numberProductLabel.setText(numberProduct + " products in cart");
        }

        oLists = cartModifier.viewProduct(billId);

        productId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        unit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        cartTableView.setItems(oLists);
    }

    @FXML
    private void checkOutClicked(MouseEvent event) throws SQLException {
//        event clicked to checkout label
        if (total != 0) {
            if (new BillModifier().addBill(userId)) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Notification");
                alert.setHeaderText("Success");
                alert.setContentText("Successful payment");
                alert.showAndWait();

//            reload quantity product inside cart
                getProductAllInCart();

//            reset value of total = 0
                totalLabel.setText("" + 0);
            }
        }else {
            Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Notification");
                alert.setHeaderText("Error");
                alert.setContentText("Cart is empty");
                alert.showAndWait();
//                Optional<ButtonType> result = alert.showAndWait();
//                if(result.isPresent() )
        }
    }

}
