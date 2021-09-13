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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class SearchProductController implements Initializable {

    @FXML
    private TableView<?> searchTableView;
    @FXML
    private TableColumn<?, ?> productTypeId;
    @FXML
    private TableColumn<?, ?> productId;
    @FXML
    private TableColumn<?, ?> productName;
    @FXML
    private TableColumn<?, ?> unit;
    @FXML
    private TableColumn<?, ?> quatity;
    @FXML
    private TableColumn<?, ?> mfgDate;
    @FXML
    private TableColumn<?, ?> expDate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void searchReleasedTextField(KeyEvent event) {
    }
    
}
