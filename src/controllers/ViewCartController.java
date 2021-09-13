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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
            getCart();
        } catch (SQLException ex) {
            Logger.getLogger(ViewCartController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getCart() throws SQLException {
        CartModifier cartModifier = new CartModifier();
        ObservableList oLists = FXCollections.observableArrayList();
        int billId = new BillModifier().getBillId();
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
    private void cartTableViewClicked(MouseEvent event) {
    }

}
