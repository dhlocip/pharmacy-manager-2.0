/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class AddProductController implements Initializable {

    @FXML
    private PasswordField passwordTextField;
    @FXML
    private TextField usernameTextField1;
    @FXML
    private PasswordField passwordTextField1;
    @FXML
    private PasswordField passwordTextField2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void usernameReleased(KeyEvent event) {
    }

    @FXML
    private void passwordReleased(KeyEvent event) {
    }

    @FXML
    private void addProTypeClicked(MouseEvent event) {
    }
    
}
