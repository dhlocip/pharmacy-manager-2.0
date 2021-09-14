/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import data.AllInfoProduct;
import datamodifier.AllInfoProductModifier;
import datamodifier.BillModifier;
import datamodifier.CartModifier;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class SearchProductController implements Initializable {

    String item;
    int userId;
    int billId;
    int prodId;
    int totalNumCart;
    
    @FXML
    private TableView<AllInfoProduct> searchTableView;
    @FXML
    private TableColumn<AllInfoProduct, Integer> productTypeId;
    @FXML
    private TableColumn<AllInfoProduct, Integer> productId;
    @FXML
    private TableColumn<AllInfoProduct, String> productName;
    @FXML
    private TableColumn<AllInfoProduct, String> unit;
    @FXML
    private TableColumn<AllInfoProduct, Integer> quatity;
    @FXML
    private TableColumn<AllInfoProduct, String> mfgDate;
    @FXML
    private TableColumn<AllInfoProduct, String> expDate;
    @FXML
    private TextField searchTextField;
    @FXML
    private TableColumn<AllInfoProduct, Double> price;
    @FXML
    private Spinner<Integer> setQuantity;
    int currentValueQuantity;
    @FXML
    private Label prodIdLabel;
    @FXML
    private Label prodNameLabel;
    @FXML
    private Label gotocart;
    @FXML
    private Label viewTotalNumber;
    @FXML
    private BorderPane homeBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
//          the first load all product 
            getProductDefault();
        } catch (SQLException ex) {
            Logger.getLogger(SearchProductController.class.getName()).log(Level.SEVERE, null, ex);
        }

//        get value spinner of quantity product
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
        valueFactory.setValue(1);
        setQuantity.setEditable(true);
        setQuantity.setValueFactory(valueFactory);
        currentValueQuantity = setQuantity.getValue();
        setQuantity.valueProperty().addListener((o) -> {
            currentValueQuantity = setQuantity.getValue();
        });

        try {
//            the first load userId, billId, total number inside cart
            userId = HomeMemberController.userId;
            billId = new BillModifier().getBillId(userId);
            totalNumCart = new CartModifier().getNumberProduct(billId);
        } catch (SQLException ex) {
            Logger.getLogger(SearchProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        viewTotalNumber.setText(totalNumCart + "");
        
    }

//    get all product (when enter item name) with current quantity 
    public void getProduct() throws SQLException {
        AllInfoProductModifier searchProductModifier = new AllInfoProductModifier();
        ObservableList oLists = FXCollections.observableArrayList();
        oLists = searchProductModifier.findProduct(item);
        
        productTypeId.setCellValueFactory(new PropertyValueFactory<>("productTypeId"));
        productId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        unit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        quatity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        mfgDate.setCellValueFactory(new PropertyValueFactory<>("mfgDate"));
        expDate.setCellValueFactory(new PropertyValueFactory<>("expDate"));
        
        searchTableView.setItems(oLists);
        
    }

//    get all product with current quantity
    public void getProductDefault() throws SQLException {

        AllInfoProductModifier searchProductModifier = new AllInfoProductModifier();
        ObservableList oLists = FXCollections.observableArrayList();
        oLists = searchProductModifier.viewProduct();

        productTypeId.setCellValueFactory(new PropertyValueFactory<>("productTypeId"));
        productId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        unit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        quatity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        mfgDate.setCellValueFactory(new PropertyValueFactory<>("mfgDate"));
        expDate.setCellValueFactory(new PropertyValueFactory<>("expDate"));

        searchTableView.setItems(oLists);
    }

    @FXML
    private void searchReleasedTextField(KeyEvent event) throws SQLException {
//        get value item enter into
        item = searchTextField.getText();
        getProduct();
    }

    @FXML
    private void searchTableClicked(MouseEvent event) {
        AllInfoProduct item = searchTableView.getSelectionModel().getSelectedItem();
        if (item != null) {
            prodId = item.getProductId();
            prodIdLabel.setText(prodId + "");
            prodNameLabel.setText(item.getProductName());
        } else {
            System.out.println("enter search please!");
//            more notification error
        }

    }

    @FXML
    private void addToCartAction(ActionEvent event) throws SQLException, IOException {
        AllInfoProduct item = searchTableView.getSelectionModel().getSelectedItem();
        if (item != null) {
//            need to reload billId
            billId = new BillModifier().getBillId(userId);
            
            BillModifier billModifier = new BillModifier();
            if (billModifier.addToBillDetail(billId, prodId, currentValueQuantity)) {

//                from billId -> total inside cart
                totalNumCart = new CartModifier().getNumberProduct(billId);
                viewTotalNumber.setText(totalNumCart + "");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notification");
                alert.setHeaderText("Success");
                alert.setContentText("Add to cart successfully.");
                alert.showAndWait();
                getProductDefault();

            }
        } else {
            System.out.println("click dum");
        }

    }

    @FXML
    private void newOrderAction(ActionEvent event) throws SQLException {
    }

    @FXML
    private void gotocartClicked(MouseEvent event) throws IOException {
//        set center layout is ui_viewCart
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ViewCart.fxml"));
        Parent root = loader.load();
        homeBox.setCenter(root);
        homeBox.setBottom(null);
        homeBox.setTop(null);
    }
}
