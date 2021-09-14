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
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class AddViewProductController implements Initializable {

    int userId;
    
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private TextField usernameTextField1;
    @FXML
    private PasswordField passwordTextField1;
    @FXML
    private PasswordField passwordTextField11;
    @FXML
    private PasswordField passwordTextField111;
    @FXML
    private PasswordField passwordTextField1111;
    @FXML
    private ComboBox<?> proIdComboBox;
    @FXML
    private PasswordField passwordTextField11111;
    @FXML
    private TableView<?> tableView;
    @FXML
    private TableColumn<?, ?> colProductId;
    @FXML
    private TableColumn<?, ?> colProductName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setUserId(int id){
        userId = id;
    }

    @FXML
    private void usernameReleased(KeyEvent event) {
    }

    @FXML
    private void passwordReleased(KeyEvent event) {
    }


    private void testClicked(MouseEvent event) {
        System.out.println(HomeManagerController.userId);
    }

    @FXML
    private void addProTypeClicked(MouseEvent event) {
    }

    @FXML
    private void addProDetailClicked(MouseEvent event) {
    }

    @FXML
    private void addImportClicked(MouseEvent event) {
    }

    @FXML
    private void addImportDetailClicked(MouseEvent event) {
    }
    
}
