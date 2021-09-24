package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Main;

public class HomeManagerController implements Initializable {

    static int userId;

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
    @FXML
    private HBox myprofileAdmin;
    @FXML
    private VBox supMyProfileAdmin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        hide supLabel medicine, my profile, setting when the first load 
        hideSupMedicine(false);
        hideSupMyProfile(false);
        hideSupSettings(false);
        hideSupMyProfileAdmin(false);

    }

//    set info user from UI_Login
    public void setInfoUser(String fullname, String position, int id) {
        fullnameLabel.setText(fullname);
        positionLabel.setText(position);
        userId = id;

    }

//    set center of layout
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

    private void hideSupMyProfileAdmin(boolean value) {
        supMyProfileAdmin.setVisible(value);
        supMyProfileAdmin.managedProperty().bind(supMyProfileAdmin.visibleProperty());

    }

    @FXML
    private void dashboardClicked(MouseEvent event) {
//        event clicked to label dashboard
        hideSupMedicine(false);
        hideSupMyProfile(false);
        hideSupSettings(false);
        hideSupMyProfileAdmin(false);

    }

    @FXML
    private void medicineClicked(MouseEvent event) {
//        event clicked to label medicine
        hideSupMedicine(true);
        hideSupMyProfile(false);
        hideSupSettings(false);
        hideSupMyProfileAdmin(false);

    }

    @FXML
    private void manageProfileClicked(MouseEvent event) {
//        event clicked to label manage profile
        hideSupMedicine(false);
        hideSupMyProfile(true);
        hideSupSettings(false);
        hideSupMyProfileAdmin(false);

    }

    @FXML
    private void myprofileAdminClicked(MouseEvent event) {
        hideSupMedicine(false);
        hideSupMyProfile(false);
        hideSupSettings(false);
        hideSupMyProfileAdmin(true);
    }

    @FXML
    private void settingClicked(MouseEvent event) {
//        event clicked to label setting
        hideSupMedicine(false);
        hideSupMyProfile(false);
        hideSupSettings(true);
        hideSupMyProfileAdmin(false);

    }

    @FXML
    private void logoutClicked(MouseEvent event) throws IOException {
//        event clicked to label log out
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Sign in");
        stage.show();

    }

    @FXML
    private void importCliecked(MouseEvent event) throws IOException {
        setCenterHomeBox("ImportProduct");
    }

    @FXML
    private void createProductClicked(MouseEvent event) throws IOException {
        setCenterHomeBox("AddProduct");
    }

    @FXML
    private void updateClicked(MouseEvent event) throws IOException {
        setCenterHomeBox("UpdateProduct");
    }

    @FXML
    private void viewClicked(MouseEvent event) throws IOException {
        setCenterHomeBox("ViewProduct");
    }

    @FXML
    private void deleteClicked(MouseEvent event) throws IOException {
        setCenterHomeBox("DeleteProduct");
    }

    @FXML
    private void viewProfileClicked(MouseEvent event) throws IOException {
        setCenterHomeBox("ViewUsers");
    }

    @FXML
    private void createProfileClicked(MouseEvent event) throws IOException {
        setCenterHomeBox("CreateUsers");
    }

    @FXML
    private void updateProfileClicked(MouseEvent event) throws IOException {
        setCenterHomeBox("UpdateUsers");
    }

    @FXML
    private void deleteProfileClicked(MouseEvent event) throws IOException {
        setCenterHomeBox("DeleteUser");
    }

    @FXML
    private void statisticClicked(MouseEvent event) {
    }

    @FXML
    private void updateProfileAdminClicked(MouseEvent event) {
    }

    @FXML
    private void changePasswordProfileAdminClicked(MouseEvent event) {
    }

}
