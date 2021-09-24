/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import data.Users;
import datamodifier.UserModifier;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
public class CreateUsersController implements Initializable {

    String lGender, lPosition;

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
    private Label errorOfUnit;
    @FXML
    private Label errorOfPrice;
    @FXML
    private Label errorOfPrice2;
    @FXML
    private Label errorOfUnit1;
    @FXML
    private Label errorOfPrice1;
    @FXML
    private Label errorOfPrice21;
    @FXML
    private TextField fullnameTextField;
    @FXML
    private ComboBox<String> genderComboBox;
    @FXML
    private TextField phoneTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private DatePicker dateOfBirthDatePicker;
    @FXML
    private ComboBox<String> PositionComboBox;
    @FXML
    private TextField userNameTextField;
    @FXML
    private Label errorOfUserName;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Label errorOfPassword;
    @FXML
    private TextField emailTextField;
    @FXML
    private Label errorOfUnit11;
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

            setGender();

            setPosition();

        } catch (SQLException ex) {
            Logger.getLogger(CreateUsersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setPosition() {
        ObservableList<String> oList = FXCollections.observableArrayList();
        oList.add("Manager");
        oList.add("Member");
        PositionComboBox.setItems(oList);
        PositionComboBox.setValue(oList.get(1));

        lPosition = PositionComboBox.getValue();
    }

    private void setGender() {
        ObservableList<String> oList = FXCollections.observableArrayList();
        oList.add("Male");
        oList.add("Female");
        genderComboBox.setItems(oList);
        genderComboBox.setValue(oList.get(0));

        lGender = genderComboBox.getValue();
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

    @FXML
    private void searchReleased(KeyEvent event) {
    }

    @FXML
    private void unitReleased(KeyEvent event) {
    }

    @FXML
    private void priceReleased(KeyEvent event) {
    }

    @FXML
    private void genderAction(ActionEvent event) {
        lGender = genderComboBox.getValue();
    }

    @FXML
    private void positionAction(ActionEvent event) {
        lPosition = PositionComboBox.getValue();
    }

    @FXML
    private void createUserClicked(MouseEvent event) throws SQLException {
        Users user = new Users();
        user.setUserName(userNameTextField.getText());
        user.setPassword(passwordTextField.getText());
        user.setPhone(phoneTextField.getText());
        user.setFullName(fullnameTextField.getText());
        user.setGender(lGender);
        user.setDateOfBirth(String.valueOf(dateOfBirthDatePicker.getValue()));
        user.setAddress(addressTextField.getText());
        user.setPosition(lPosition);
        user.setEmail(emailTextField.getText());

        if (new UserModifier().insertInto(user)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Notification");
            alert.setHeaderText("Success");
            alert.setContentText("User is update successfully.");
            alert.showAndWait();
            getUsersInfo();
        }
    }

}
