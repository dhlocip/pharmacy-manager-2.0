/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import data.Users;
import datamodifier.UserModifier;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class LoginController implements Initializable {

    @FXML
    private VBox signInVbox;
    @FXML
    private Button signInButton;
    @FXML
    private BorderPane signInBorderBox;
    @FXML
    private HBox errorBox;
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;
    
    int userId;
    String username;
    String password;
    String fullName;
    String position;
    Users user = new Users();
    UserModifier userModifier = new UserModifier();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        signInButton.setDisable(true);
        errorBox.setVisible(false);
        errorBox.managedProperty().bind(errorBox.visibleProperty());
    }
    
    public void setUserId(int id){
        userId = id;
    }

    //next to page home
    private void nextToHome(ActionEvent event, String permission) throws IOException {
        String view = "";
        if (permission.equalsIgnoreCase("Manager")) {
            view = "HomeManager";

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/" + view + ".fxml"));
            Parent root = loader.load();

            // pass info of user to view home
            HomeManagerController controller = loader.getController();
            controller.setInfoUser(fullName, position, userId, position);

            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setResizable(true);
            stage.setScene(scene);
            stage.setTitle("Home");
            stage.show();
            
        } else {
            view = "HomeMember";

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/views/" + view + ".fxml"));
            Parent root = loader.load();

            // pass info of user to view home
            HomeMemberController controller = loader.getController();
            controller.setInfoUser(fullName, position, userId, position);

            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setResizable(true);
            stage.setScene(scene);
            stage.setTitle("Home");
            stage.show();
            
        }

    }

//    regex user 
    private boolean isUserRight() {
        username = usernameTextField.getText();
        return username.matches("^[a-zA-Z]{1}[\\w]*[.]?[\\w]+") && username.length() >= 6;
    }

//    regex password 
    private boolean isPasswordRight() {
        password = String.valueOf(passwordTextField.getText());
        return password.matches("^[a-zA-Z]{1}[\\w!@#$%^&*_.]{7,30}");
    }

    @FXML
    private void clickSignIn(ActionEvent event) throws IOException, SQLException {
        if (userModifier.signIn(username, password)) {
            // get user
            user = userModifier.getUser(username, password);
            fullName = user.getFullName();
            position = user.getPosition();
            userId = user.getUserId();
            nextToHome(event, position);
        } else {
            errorBox.setVisible(true);
        }
    }

    @FXML
    private void clickCloseError(ActionEvent event) {
        errorBox.setVisible(false);
        errorBox.managedProperty().bind(errorBox.visibleProperty());
    }

    @FXML
    private void usernameReleased(KeyEvent event) {
        if (isUserRight() && isPasswordRight()) {
            signInButton.setDisable(false);
        } else {
            signInButton.setDisable(true);
        }
    }

    @FXML
    private void passwordReleased(KeyEvent event) {
        if (isUserRight() && isPasswordRight()) {
            signInButton.setDisable(false);
        } else {
            signInButton.setDisable(true);
        }
    }

}
