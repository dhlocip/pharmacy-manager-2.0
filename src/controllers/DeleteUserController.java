/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import data.Products;
import data.Users;
import datamodifier.BillDetailModifier;
import datamodifier.BillModifier;
import datamodifier.ImportBatchDetailModifier;
import datamodifier.ProductsModifier;
import datamodifier.UserModifier;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
public class DeleteUserController implements Initializable {

    @FXML
    private TextField searchTextField;
    @FXML
    private TableView<Users> tableViewUsers;
    @FXML
    private TableColumn<Users, Integer> userIdCol;
    @FXML
    private TableColumn<Users, String> userNameCol;
    @FXML
    private TableColumn<Users, String> phoneCol;
    @FXML
    private TableColumn<Users, String> fullNameCol;
    @FXML
    private TableColumn<Users, String> genderCol;
    @FXML
    private TableColumn<Users, String> dateOfBirthCol;
    @FXML
    private TableColumn<Users, String> addressCol;
    @FXML
    private TableColumn<Users, String> positionCol;
    @FXML
    private TableColumn<Users, String> passwordCol;
    @FXML
    private TableColumn<Users, String> emailCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO

            getUsersInfo();
        } catch (SQLException ex) {
            Logger.getLogger(DeleteUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getUsersInfo() throws SQLException {
        ObservableList<Users> oList = new UserModifier().getInfo();

        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        fullNameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        dateOfBirthCol.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        positionCol.setCellValueFactory(new PropertyValueFactory<>("position"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        tableViewUsers.setItems(oList);
    }

    private void getUsersInfoAfterSearch() throws SQLException {
        ObservableList<Users> oList = new UserModifier().searchByName(searchTextField.getText());

        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("userName"));
        passwordCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        fullNameCol.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        dateOfBirthCol.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        positionCol.setCellValueFactory(new PropertyValueFactory<>("position"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));

        tableViewUsers.setItems(oList);
    }

    @FXML
    private void searchReleased(KeyEvent event) throws SQLException {
        getUsersInfoAfterSearch();
    }

    @FXML
    private void tableViewUserClicked(MouseEvent event) throws SQLException {
        Users item = tableViewUsers.getSelectionModel().getSelectedItem();

        if (item == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Notification");
            alert.setHeaderText("Error");
            alert.setContentText("Please click on the line different null");
        } else {
            List<Integer> listBills = new BillModifier().getListBills(item.getUserId());
            
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Notification");
            alert.setHeaderText("Confirm");
            alert.setContentText("Are you sure?\nClick OK to delete the line.");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {

                for (Integer listBill : listBills) {
                    new BillDetailModifier().deleteByBillId(listBill);
                }

                if (new BillModifier().deleteByUserId(item.getUserId())) {
                    new UserModifier().deleteByUserId(item.getUserId());
                    getUsersInfo();
                }
            }
        }
    }

}
