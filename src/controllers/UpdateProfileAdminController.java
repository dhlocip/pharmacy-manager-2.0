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
import java.util.Locale;
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
    String lFullName, lPhone, lAddress;
    String lEmail, lDateOfBirth;
    int lUserId;

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
    private Label errorOfFullName;
    @FXML
    private Label errorOfGender;
    @FXML
    private Label errorOfPhone;
    @FXML
    private Label errorOfAddress;
    @FXML
    private Label errorOfEmail;
    @FXML
    private Label errorOfDateOfBirth;
    @FXML
    private Label errorOfPosition;
    @FXML
    private Label bdFullName;
    @FXML
    private Label bdGender;
    @FXML
    private Label bdPhone;
    @FXML
    private Label bdAddress;
    @FXML
    private Label bdEmail;
    @FXML
    private Label bdDateOfBirth;
    @FXML
    private Label bdPosition;
    @FXML
    private Label bdUpdateUser;

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

        hideErrorOfFullName(false);
        hideErrorOfGender(false);
        hideErrorOfPhone(false);
        hideErrorOfAddress(false);
        hideErrorOfEmail(false);
        hideErrorOfDateOfBrith(false);
        hideErrorOfPosition(false);
        setLanguage();
    }
    
    private void setLanguage() {
        String role = HomeManagerController.gRole;
        String langManager = HomeManagerController.gLanguage;
        String langMeber = HomeMemberController.gLanguage;
        if (role != null) {
            if (langManager.equalsIgnoreCase("english")) {
                changeLanguage("en", "EN");
            } else {
                changeLanguage("vi", "VN");
            }
        } else {
            if (langMeber.equalsIgnoreCase("english")) {
                changeLanguage("en", "EN");
            } else {
                changeLanguage("vi", "VN");
            }
        }

    }

    private void changeLanguage(String language, String country) {

        Locale locale = new Locale(language, country);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/update_profile/Bundle", locale);

        bdFullName.setText(resourceBundle.getString("bdFullName"));
        bdGender.setText(resourceBundle.getString("bdGender"));
        bdPhone.setText(resourceBundle.getString("bdPhone"));
        bdAddress.setText(resourceBundle.getString("bdAddress"));
        bdEmail.setText(resourceBundle.getString("bdEmail"));
        bdDateOfBirth.setText(resourceBundle.getString("bdDateOfBirth"));
        bdPosition.setText(resourceBundle.getString("bdPosition"));
        bdUpdateUser.setText(resourceBundle.getString("bdUpdateUser"));
        
        fullnameTextField.setPromptText(resourceBundle.getString("fullnameTextField"));
        phoneTextField.setPromptText(resourceBundle.getString("phoneTextField"));
        addressTextField.setPromptText(resourceBundle.getString("addressTextField"));
        emailTextField.setPromptText(resourceBundle.getString("emailTextField"));
        dateOfBirthDatePicker.setPromptText(resourceBundle.getString("dateOfBirthDatePicker"));
        
    }

    private void hideErrorOfFullName(boolean value) {
        errorOfFullName.setVisible(value);
        errorOfFullName.managedProperty().bind(errorOfFullName.visibleProperty());
    }

    private void hideErrorOfGender(boolean value) {
        errorOfGender.setVisible(value);
        errorOfGender.managedProperty().bind(errorOfGender.visibleProperty());
    }

    private void hideErrorOfPhone(boolean value) {
        errorOfPhone.setVisible(value);
        errorOfPhone.managedProperty().bind(errorOfPhone.visibleProperty());
    }

    private void hideErrorOfAddress(boolean value) {
        errorOfAddress.setVisible(value);
        errorOfAddress.managedProperty().bind(errorOfAddress.visibleProperty());
    }

    private void hideErrorOfEmail(boolean value) {
        errorOfEmail.setVisible(value);
        errorOfEmail.managedProperty().bind(errorOfEmail.visibleProperty());
    }

    private void hideErrorOfDateOfBrith(boolean value) {
        errorOfDateOfBirth.setVisible(value);
        errorOfDateOfBirth.managedProperty().bind(errorOfDateOfBirth.visibleProperty());
    }

    private void hideErrorOfPosition(boolean value) {
        errorOfPosition.setVisible(value);
        errorOfPosition.managedProperty().bind(errorOfPosition.visibleProperty());
    }

    private void setUserInfo() throws SQLException {
        if (new HomeManagerController().gRole != null
                && new HomeMemberController().gRole == null) {
            lUserId = new HomeManagerController().gUserId;
            PositionComboBox.setDisable(true);
        } else {
            lUserId = new HomeMemberController().gUserId;
            PositionComboBox.setDisable(true);
        }
        Users user = new UserModifier().getUser(lUserId);

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
    private void genderAction(ActionEvent event) {
        lGender = genderComboBox.getValue();

    }

    @FXML
    private void positionAction(ActionEvent event) {
        lPosition = PositionComboBox.getValue();

    }

    @FXML
    private void updateUserClicked(MouseEvent event) throws SQLException {
        if (isFullNameRight() && isPhoneRight() && isAddressRight()
                && isEmailRight()) {
            Users user = new Users();
            user.setPhone(lPhone);
            user.setFullName(lFullName);
            user.setGender(lGender);
            
            LocalDate myDate = dateOfBirthDatePicker.getValue();
            user.setDateOfBirth(String.valueOf(myDate));
            
            user.setAddress(lAddress);
            user.setPosition(lPosition);
            user.setEmail(lEmail);
            user.setUserId(lUserId);

            if (new UserModifier().updateUser(user)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notification");
                alert.setHeaderText("Success");
                alert.setContentText("User is update successfully.");
                alert.showAndWait();
                setUserInfo();
            }
        }else {
            if (!isFullNameRight()) {
                hideErrorOfFullName(true);
                errorOfFullName.setText("FullName is invalid.");
            }

            if (!isPhoneRight()) {
                hideErrorOfPhone(true);
                errorOfPhone.setText("Phone is invalid.");
            }

            if (!isAddressRight()) {
                hideErrorOfAddress(true);
                errorOfAddress.setText("Address is invalid.");
            }

            if (!isEmailRight()) {
                hideErrorOfEmail(true);
                errorOfEmail.setText("Email is invalid.");
            }

        }
    }

    private boolean isFullNameRight() {
        String tmp = fullnameTextField.getText();
        lFullName = tmp;
        return tmp.matches("^[a-zA-Z]{1}[a-zA-Z\\s]{3,30}");
    }

    @FXML
    private void fullNameReleased(KeyEvent event) {
        if (isFullNameRight()) {
            hideErrorOfFullName(false);
        } else {
            hideErrorOfFullName(true);
            errorOfFullName.setText(fullnameTextField.getText() + " is invalid.");
        }
    }

    private boolean isPhoneRight() {
        String tmp = phoneTextField.getText();
        lPhone = tmp;
        return tmp.matches("^[0]{1}[\\d]{9,10}");
    }

    @FXML
    private void phoneReleased(KeyEvent event) {
        if (isPhoneRight()) {
            hideErrorOfPhone(false);
        } else {
            hideErrorOfPhone(true);
            errorOfPhone.setText(phoneTextField.getText() + " is invalid.");
        }
    }

    private boolean isAddressRight() {
        String tmp = addressTextField.getText();
        lAddress = tmp;
        return tmp.matches("^[^\\W\\d]{1}[\\w\\s,]{5,50}");
    }

    @FXML
    private void addressReleased(KeyEvent event) {
        if (isAddressRight()) {
            hideErrorOfAddress(false);
        } else {
            hideErrorOfAddress(true);
            errorOfAddress.setText(addressTextField.getText() + " is invalid.");
        }
    }

    private boolean isEmailRight() {
        String tmp = emailTextField.getText();
        lEmail = tmp;
        return tmp.matches("^[^\\W\\d]{1}[\\w]+[.]?[\\w]+[@]{1}[^\\W\\d]{4,7}[.]{1}[^\\W\\d]{3}[.]{0,1}[^\\W\\d]{0,3}[.]{0,1}[^\\W\\d]{0,2}");
    }

    @FXML
    private void emailReleased(KeyEvent event) {
        if (isEmailRight()) {
            hideErrorOfEmail(false);
        } else {
            hideErrorOfEmail(true);
            errorOfEmail.setText(emailTextField.getText() + " is invalid.");
        }
    }

    @FXML
    private void dateOfBirthAction(ActionEvent event) {
        LocalDate myDate = dateOfBirthDatePicker.getValue();
        lDateOfBirth = String.valueOf(myDate);
        hideErrorOfDateOfBrith(false);
    }

}
