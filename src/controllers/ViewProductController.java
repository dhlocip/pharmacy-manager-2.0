/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import data.AllInfoProduct;
import datamodifier.AllInfoProductModifier;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class ViewProductController implements Initializable {

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
    private TableColumn<AllInfoProduct, Double> price;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            getProduct();
        } catch (SQLException ex) {
            Logger.getLogger(ViewProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getProduct() throws SQLException {

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
}
