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
    String lUserName, lPassword, lFullName;
    String lPhone, lAddress, lEmail, lDateOfBirth;

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
    private TableColumn<Users, String> emailCol;
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
    private ComboBox<String> positionComboBox;
    @FXML
    private Label bdUserName;
    @FXML
    private Label bdPass;
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
    private Label bdDate;
    @FXML
    private Label bdPosition;
    @FXML
    private Label bdCreate;

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

        hideErrorOfUserName(false);
        hideErrorOfPassword(false);
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
        String langManager = HomeManagerController.gLanguage;
        if (langManager.equalsIgnoreCase("english")) {
            changeLanguage("en", "EN");
        } else {
            changeLanguage("vi", "VN");
        }
    }

    private void changeLanguage(String language, String country) {

        Locale locale = new Locale(language, country);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/create_user/Bundle", locale);

        bdUserName.setText(resourceBundle.getString("bdUserName"));
        bdPass.setText(resourceBundle.getString("bdPass"));
        bdFullName.setText(resourceBundle.getString("bdFullName"));
        bdGender.setText(resourceBundle.getString("bdGender"));
        bdPhone.setText(resourceBundle.getString("bdPhone"));
        bdAddress.setText(resourceBundle.getString("bdAddress"));
        bdEmail.setText(resourceBundle.getString("bdEmail"));
        bdDate.setText(resourceBundle.getString("bdDate"));
        bdPosition.setText(resourceBundle.getString("bdPosition"));
        bdCreate.setText(resourceBundle.getString("bdCreate"));
        
        userNameTextField.setPromptText(resourceBundle.getString("userNameTextField"));
        passwordTextField.setPromptText(resourceBundle.getString("passwordTextField"));
        fullnameTextField.setPromptText(resourceBundle.getString("fullnameTextField"));
        phoneTextField.setPromptText(resourceBundle.getString("phoneTextField"));
        addressTextField.setPromptText(resourceBundle.getString("addressTextField"));
        emailTextField.setPromptText(resourceBundle.getString("emailTextField"));
        dateOfBirthDatePicker.setPromptText(resourceBundle.getString("dateOfBirthDatePicker"));
        
    }

    private void hideErrorOfUserName(boolean value) {
        errorOfUserName.setVisible(value);
        errorOfUserName.managedProperty().bind(errorOfUserName.visibleProperty());
    }

    private void hideErrorOfPassword(boolean value) {
        errorOfPassword.setVisible(value);
        errorOfPassword.managedProperty().bind(errorOfPassword.visibleProperty());
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

    private void setPosition() {
        ObservableList<String> oList = FXCollections.observableArrayList();
        oList.add("Manager");
        oList.add("Member");
        positionComboBox.setItems(oList);
        positionComboBox.setValue(oList.get(1));

        lPosition = positionComboBox.getValue();
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

    private void searchReleased(KeyEvent event) throws SQLException {
        getUsersInfoAfterSearch();
    }

    @FXML
    private void genderAction(ActionEvent event) {
        lGender = genderComboBox.getValue();
    }

    @FXML
    private void positionAction(ActionEvent event) {
        lPosition = positionComboBox.getValue();
    }

    @FXML
    private void createUserClicked(MouseEvent event) throws SQLException {

        if (isUserNameRight() && isPasswordRight() && isFullNameRight()
                && isPhoneRight() && isAddressRight() && isEmailRight() && lDateOfBirth != null) {
            Users user = new Users();
            user.setUserName(lUserName);
            user.setPassword(lPassword);
            user.setPhone(lPhone);
            user.setFullName(lFullName);
            user.setGender(lGender);
            user.setDateOfBirth(lDateOfBirth);
            user.setAddress(lAddress);
            user.setPosition(lPosition);
            user.setEmail(lEmail);

            if (new UserModifier().insertInto(user)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notification");
                alert.setHeaderText("Success");
                alert.setContentText("User is update successfully.");
                alert.showAndWait();
                getUsersInfo();
            }
        } else {
            if (!isUserNameRight()) {
                hideErrorOfUserName(true);
                errorOfUserName.setText("UserName is invalid.");
            }

            if (!isPasswordRight()) {
                hideErrorOfPassword(true);
                errorOfPassword.setText("Password is invalid.");
            }

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

            if (lDateOfBirth == null) {
                hideErrorOfDateOfBrith(true);
                errorOfDateOfBirth.setText("DateOfBirth can't empty.");
            }
        }

    }

    private boolean isUserNameRight() throws SQLException {
        String tmp = userNameTextField.getText();
        lUserName = tmp;
        return tmp.matches("^[a-zA-Z]{1}[\\w]*[.]?[\\w]+") && tmp.length() >= 6;
    }

    @FXML
    private void userNameReleased(KeyEvent event) throws SQLException {
        if (isUserNameRight()) {
            boolean check = new UserModifier().matchUserName(userNameTextField.getText());
            if (!check) {
                hideErrorOfUserName(false);
            } else {
                hideErrorOfUserName(true);
                errorOfUserName.setText(userNameTextField.getText() + " already exists.");
            }
        } else {
            hideErrorOfUserName(true);
            errorOfUserName.setText(userNameTextField.getText() + " is invalid.");
        }
    }

    private boolean isPasswordRight() {
        String tmp = passwordTextField.getText();
        lPassword = tmp;
        return tmp.matches("^[a-zA-Z]{1}[\\w!@#$%^&*_.]{7,30}");
    }

    @FXML
    private void passwordReleased(KeyEvent event) {
        if (isPasswordRight()) {
            hideErrorOfPassword(false);
        } else {
            hideErrorOfPassword(true);
            errorOfPassword.setText("Password is invalid.");
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
