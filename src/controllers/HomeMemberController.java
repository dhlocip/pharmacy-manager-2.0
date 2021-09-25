/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.HomeManagerController.gRole;
import datamodifier.BillModifier;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
public class HomeMemberController implements Initializable {

    static int gUserId;
    static String gRole;
    int billId;

    @FXML
    private BorderPane homeBox;
    @FXML
    private HBox dashboardLabel;
    @FXML
    private HBox medicineLabel;
    @FXML
    private VBox supMedicine;
    @FXML
    private HBox myprofileLabel;
    @FXML
    private VBox supMyProfile;
    @FXML
    private HBox settingLabel;
    @FXML
    private HBox supSettings;
    @FXML
    private HBox logoutLabel;
    @FXML
    private Label fullnameLabel;
    @FXML
    private Label positionLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        hide supLabel medicine, my profile, setting when the first load 
        hideSupMedicine(false);
        hideSupMyProfile(false);
        hideSupSettings(false);

    }

    public void setInfoUser(String fullname, String position, int id, String role) {
        fullnameLabel.setText(fullname);
        positionLabel.setText(position);
        gUserId = id;
        gRole = role;
    }

    public void setCenterHomeBox(String view) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/" + view + ".fxml"));
        homeBox.setCenter(root);

    }

    private void hideSupMedicine(boolean value) {
        supMedicine.setVisible(value);
        supMedicine.managedProperty().bind(supMedicine.visibleProperty());

    }

    private void hideSupMyProfile(boolean value) {
        supMyProfile.setVisible(value);
        supMyProfile.managedProperty().bind(supMyProfile.visibleProperty());

    }

    private void hideSupSettings(boolean value) {
        supSettings.setVisible(value);
        supSettings.managedProperty().bind(supSettings.visibleProperty());

    }

    @FXML
    private void dashboardClicked(MouseEvent event) throws IOException {
        hideSupMedicine(false);
        hideSupMyProfile(false);
        hideSupSettings(false);
        System.out.println(gRole);

    }

    @FXML
    private void medicineClicked(MouseEvent event) {
        hideSupMedicine(true);
        hideSupMyProfile(false);
        hideSupSettings(false);

    }

    @FXML
    private void myprofileClicked(MouseEvent event) {
        hideSupMedicine(false);
        hideSupMyProfile(true);
        hideSupSettings(false);

    }

    @FXML
    private void settingClicked(MouseEvent event) {
        hideSupMedicine(false);
        hideSupMyProfile(false);
        hideSupSettings(true);

    }

    @FXML
    private void logoutClicked(MouseEvent event) throws IOException {
        gRole = null;
//        event when clicked to log out lable 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Sign in");
        stage.show();

    }

    @FXML
    private void searchClicked(MouseEvent event) throws IOException {
//        event clicked to search label for load layout searchProduct
        setCenterHomeBox("SearchProduct");
    }

    @FXML
    private void viewClicked(MouseEvent event) throws IOException {
//        event clicked to view label for show product
        setCenterHomeBox("ViewProduct");

    }

    @FXML
    private void updateProfileMemberClicked(MouseEvent event) throws IOException {
//        event clicked to export label for export file xlsx
        setCenterHomeBox("UpdateProfileAdmin");
    }

    @FXML
    private void changePasswordMemberClicked(MouseEvent event) throws IOException {
        setCenterHomeBox("ChangePasswordAdmin");
    }

}
