/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import data.Products;
import datamodifier.BillDetailModifier;
import datamodifier.ImportBatchDetailModifier;
import datamodifier.ProductsModifier;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class DeleteProductController implements Initializable {

    @FXML
    private TableView<Products> searchTableView;
    @FXML
    private TableColumn<Products, Integer> productTypeId;
    @FXML
    private TableColumn<Products, Integer> productId;
    @FXML
    private TableColumn<Products, String> productName;
    @FXML
    private TableColumn<Products, String> unit;
    @FXML
    private TableColumn<Products, Double> price;
    @FXML
    private TextField searchTextField;
    @FXML
    private BorderPane homeBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            getProduct();
        } catch (SQLException ex) {
            Logger.getLogger(DeleteProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void getProductByProductName(String proName) throws SQLException {

        ProductsModifier searchProductModifier = new ProductsModifier();
        ObservableList oLists = FXCollections.observableArrayList();
        oLists = searchProductModifier.getInfoByIdOrName(proName);

        productTypeId.setCellValueFactory(new PropertyValueFactory<>("productTypeId"));
        productId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        unit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        searchTableView.setItems(oLists);
    }

    public void getProduct() throws SQLException {

        ProductsModifier searchProductModifier = new ProductsModifier();
        ObservableList oLists = FXCollections.observableArrayList();
        oLists = searchProductModifier.getInfo();

        productTypeId.setCellValueFactory(new PropertyValueFactory<>("productTypeId"));
        productId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        unit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        searchTableView.setItems(oLists);
    }

    @FXML
    private void searchReleased(KeyEvent event) throws SQLException {
        getProductByProductName(searchTextField.getText());
    }

    @FXML
    private void tableViewClicked(MouseEvent event) throws SQLException {
        Products item = searchTableView.getSelectionModel().getSelectedItem();
        if(item != null){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Notification");
            alert.setHeaderText("Confirm");
            alert.setContentText("Are you sure?\nClick OK to delete the line.");
            Optional<ButtonType> result = alert.showAndWait();
            
            if(result.isPresent() && result.get() == ButtonType.OK){
                if(new BillDetailModifier().deleteByProductId(item.getProductId()) && 
                        new ImportBatchDetailModifier().deleteByProductId(item.getProductId())){
                    new ProductsModifier().deleteByProductId(item.getProductId());
                    getProduct();
                }
            }
                    
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Notification");
            alert.setHeaderText("Error");
            alert.setContentText("Please click on the line different null");
        }
    }


}
