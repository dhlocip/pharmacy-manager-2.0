/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import datamodifier.UserModifier;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.RotateEvent;
import javafx.scene.input.TouchEvent;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class ChangePasswordAdminController implements Initializable {

    String lCurrentPass;
    String lNewPass;
    String lRetypeNewPass;

    @FXML
    private TextField currentPasswordTF;
    @FXML
    private TextField newPasswordTF;
    @FXML
    private TextField retypeNewPasswordTF;
    @FXML
    private Label errorOfCurrentPass;
    @FXML
    private Label errorOfNewPass;
    @FXML
    private Label errorOfRetypeNewPass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void changePasswordClicked(MouseEvent event) throws SQLException {
        int userId = new HomeManagerController().userId;

        if (isNewPassRight() && isRetypeNewPassRight() && isCurrentPassRight()) {
            if (new UserModifier().updatePassword(userId, lNewPass)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Notification");
                alert.setHeaderText("Success");
                alert.setContentText("Change password is successfully.");
                alert.showAndWait();
            }
        } else {
            if (isNewPassRight()) {
                errorOfNewPass.setText("");
            } else {
                errorOfNewPass.setText("Password must be at least 8 characters");
            }

            if (isRetypeNewPassRight()) {
                if (lNewPass.equals(lRetypeNewPass)) {
                    errorOfRetypeNewPass.setText("");
                } else {
                    errorOfRetypeNewPass.setText("Retype new password must match the new password");
                }
            } else {
                errorOfRetypeNewPass.setText("Password must be at least 8 characters");
            }

            if (isCurrentPassRight()
                    && lCurrentPass.equals(new UserModifier().getUser(userId).getPassword())) {
                errorOfCurrentPass.setText("");
            } else {
                errorOfCurrentPass.setText("Current password is invalid");
            }
        }
    }

    private boolean isNewPassRight() {
        String tmp = newPasswordTF.getText();
        lNewPass = tmp;
        return tmp.matches("^[a-zA-Z]{1}[\\w!@#$%^&*_]{7,20}");
    }

    @FXML
    private void newPasswordReleased(KeyEvent event) {
        if (isNewPassRight()) {
            errorOfNewPass.setText("");
        } else {
            errorOfNewPass.setText("Password must be at least 8 characters");
        }
    }

    private boolean isRetypeNewPassRight() {
        String tmp = retypeNewPasswordTF.getText();
        lRetypeNewPass = tmp;
        return tmp.matches("^[a-zA-Z]{1}[\\w!@#$%^&*_]{7,20}");
    }

    @FXML
    private void retypeNewPasswordReleased(KeyEvent event) {
        if (isRetypeNewPassRight()) {
            if (lNewPass.equals(lRetypeNewPass)) {
                errorOfRetypeNewPass.setText("");
            } else {
                errorOfRetypeNewPass.setText("Retype new password must match the new password");
            }
        } else {
            errorOfRetypeNewPass.setText("Password must be at least 8 characters");
        }
    }

    private boolean isCurrentPassRight() {
        String tmp = currentPasswordTF.getText();
        lCurrentPass = tmp;
        return tmp.matches("^[a-zA-Z]{1}[\\w!@#$%^&*_]{7,20}");
    }

    @FXML
    private void currentPasswordReleased(KeyEvent event) throws SQLException {
        int userId = new HomeManagerController().userId;
        if (isCurrentPassRight()
                && lCurrentPass.equals(new UserModifier().getUser(userId).getPassword())) {
            errorOfCurrentPass.setText("");
        } else {
            errorOfCurrentPass.setText("Current password is invalid");
        }
    }

}
