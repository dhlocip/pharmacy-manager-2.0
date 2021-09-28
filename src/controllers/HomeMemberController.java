/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static controllers.HomeManagerController.gLanguage;
import static controllers.HomeManagerController.gRole;
import datamodifier.BillModifier;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
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
import javafx.scene.control.ComboBox;
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
    static String gLanguage;
    int billId;
    String color = "#FFE194";

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
    private ComboBox<String> languageLabel;
    @FXML
    private Label bdDashboard;
    @FXML
    private Label bdMedicine;
    @FXML
    private Label bdOrderMedicine;
    @FXML
    private Label bdViewMedicine;
    @FXML
    private Label bdStatisticMedicine;
    @FXML
    private Label bdMyProfile;
    @FXML
    private Label bdUpdateMyProfile;
    @FXML
    private Label bdPassword;
    @FXML
    private Label bdSetting;
    @FXML
    private Label bdLogout;
    @FXML
    private HBox cssOrder;
    @FXML
    private HBox cssView;
    @FXML
    private HBox cssStatistic;
    @FXML
    private HBox cssUpdate;
    @FXML
    private HBox cssChangePassword;
    @FXML
    private Label bdHotline;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //        hide supLabel medicine, my profile, setting when the first load
        hideSupMedicine(false);
        hideSupMyProfile(false);
        hideSupSettings(false);

        setLanguageItem();
    }

    private void changeLanguage(String language, String country) {

        Locale locale = new Locale(language, country);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/home_member/Bundle", locale);

        bdDashboard.setText(resourceBundle.getString("bdDashboard"));
        bdMedicine.setText(resourceBundle.getString("bdMedicine"));
        bdOrderMedicine.setText(resourceBundle.getString("bdOrderMedicine"));
        bdViewMedicine.setText(resourceBundle.getString("bdViewMedicine"));
        bdStatisticMedicine.setText(resourceBundle.getString("bdStatisticMedicine"));
        bdMyProfile.setText(resourceBundle.getString("bdMyProfile"));
        bdUpdateMyProfile.setText(resourceBundle.getString("bdUpdateMyProfile"));
        bdPassword.setText(resourceBundle.getString("bdPassword"));
        bdSetting.setText(resourceBundle.getString("bdSetting"));
        bdLogout.setText(resourceBundle.getString("bdLogout"));
        bdHotline.setText(resourceBundle.getString("bdHotline"));

    }

    private void setLanguageItem() {
        ObservableList<String> sList = FXCollections.observableArrayList();
        sList.add("English");
        sList.add("Vietnamese");

        languageLabel.setItems(sList);
        languageLabel.setValue(sList.get(0));
        gLanguage = languageLabel.getValue();

        if (gLanguage.equalsIgnoreCase("english")) {
            changeLanguage("en", "EN");
        } else {
            changeLanguage("vi", "VN");
        }
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
        cssOrder.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssView.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssStatistic.setStyle("-fx-cursor: hand; -fx-background-color: null");
    }

    private void hideSupMyProfile(boolean value) {
        supMyProfile.setVisible(value);
        supMyProfile.managedProperty().bind(supMyProfile.visibleProperty());
        cssUpdate.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssChangePassword.setStyle("-fx-cursor: hand; -fx-background-color: null");
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

        setCenterHomeBox("Notice");
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
//        gLanguage = null;
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
    private void viewClicked(MouseEvent event) throws IOException {
//        event clicked to view label for show product
        setCenterHomeBox("ViewProduct");
        cssOrder.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssView.setStyle("-fx-cursor: hand; -fx-background-color:" + color);
        cssStatistic.setStyle("-fx-cursor: hand; -fx-background-color: null");

    }

    @FXML
    private void updateProfileMemberClicked(MouseEvent event) throws IOException {
//        event clicked to export label for export file xlsx
        setCenterHomeBox("UpdateProfileAdmin");
        cssUpdate.setStyle("-fx-cursor: hand; -fx-background-color:" + color);
        cssChangePassword.setStyle("-fx-cursor: hand; -fx-background-color: null");
    }

    @FXML
    private void changePasswordMemberClicked(MouseEvent event) throws IOException {
        setCenterHomeBox("ChangePasswordAdmin");
        cssUpdate.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssChangePassword.setStyle("-fx-cursor: hand; -fx-background-color:" + color);
    }

    @FXML
    private void statisticClicked(MouseEvent event) throws IOException {
        setCenterHomeBox("StatisticMember");
        cssOrder.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssView.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssStatistic.setStyle("-fx-cursor: hand; -fx-background-color:" + color);
    }

    @FXML
    private void languageAction(ActionEvent event) {
        gLanguage = languageLabel.getValue();

        if (gLanguage.equalsIgnoreCase("english")) {
            changeLanguage("en", "EN");
        } else {
            changeLanguage("vi", "VN");
        }
    }

    @FXML
    private void orderClicked(MouseEvent event) throws IOException {
        setCenterHomeBox("SearchProduct");
        cssOrder.setStyle("-fx-cursor: hand; -fx-background-color:" + color);
        cssView.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssStatistic.setStyle("-fx-cursor: hand; -fx-background-color: null");

    }

}
