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
import java.time.format.DateTimeFormatter;
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
public class UpdateProfileAdminController implements Initializable {

    String lGender, lPosition;

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
    private TextField emailTextField;
    @FXML
    private Label errorOfUnit11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO

            setUserInfo();
            setGender();
            setPosition();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(UpdateProfileAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    private void setUserInfo() throws SQLException{
        Users user = new UserModifier().getUser(new HomeManagerController().userId);
        
        fullnameTextField.setText(user.getFullName());
        genderComboBox.setValue(user.getGender());
        phoneTextField.setText(user.getPhone());
        addressTextField.setText(user.getAddress());
        
        String formatDate = user.getDateOfBirth().formatted(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        LocalDate myDate = LocalDate.parse(formatDate);
        dateOfBirthDatePicker.setValue(myDate);
        
        PositionComboBox.setValue(user.getPosition());
        emailTextField.setText(user.getEmail());
    }

    private void setPosition() {
        ObservableList<String> oList = FXCollections.observableArrayList();
        oList.add("Manager");
        oList.add("Member");
        PositionComboBox.setItems(oList);
//        PositionComboBox.setValue(oList.get(1));

        lPosition = PositionComboBox.getValue();
    }

    private void setGender() {
        ObservableList<String> oList = FXCollections.observableArrayList();
        oList.add("Male");
        oList.add("Female");
        genderComboBox.setItems(oList);
//        genderComboBox.setValue(oList.get(0));

        lGender = genderComboBox.getValue();
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
    private void updateUserClicked(MouseEvent event) throws SQLException {
            Users user = new Users();
            user.setPhone(phoneTextField.getText());
            user.setFullName(fullnameTextField.getText());
            user.setGender(lGender);
            user.setDateOfBirth(String.valueOf(dateOfBirthDatePicker.getValue()));
            user.setAddress(addressTextField.getText());
            user.setPosition(lPosition);
            user.setEmail(emailTextField.getText());
            user.setUserId(new HomeManagerController().userId);

            if (new UserModifier().updateUser(user)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notification");
                alert.setHeaderText("Success");
                alert.setContentText("User is update successfully.");
                alert.showAndWait();
                setUserInfo();
            }
    }

}
