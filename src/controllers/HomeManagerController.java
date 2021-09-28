package controllers;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

    static int gUserId;
    static String gRole;
    static String gLanguage;
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
    private HBox myprofileAdmin;
    @FXML
    private VBox supMyProfileAdmin;
    @FXML
    private ComboBox<String> languageLabel;
    @FXML
    private Label bdDashboard;
    @FXML
    private Label bdManageMedicine;
    @FXML
    private Label bdImport;
    private Label bdDelete;
    @FXML
    private Label bdStatistic;
    @FXML
    private Label bdManageProfile;
    @FXML
    private Label bdMyProfile;
    @FXML
    private Label bdChangePassword;
    @FXML
    private Label bdSetting;
    @FXML
    private Label bdLogout;
    @FXML
    private Label bdViewManageMedicine;
    @FXML
    private Label bdCreateManageMedicine;
    @FXML
    private Label bdUpdateManageMedicine;
    @FXML
    private Label bdDeleteManageMedicine;
    @FXML
    private Label bdViewManageProfile;
    @FXML
    private Label bdCreateManageProfile;
    @FXML
    private Label bdUpdateManageProfile;
    @FXML
    private Label bdDeleteManageProfile;
    @FXML
    private Label bdUpdateMyProfile;
    @FXML
    private HBox cssViewProduct;
    @FXML
    private HBox cssCreateProduct;
    @FXML
    private HBox cssUpdateProduct;
    @FXML
    private HBox cssImportProduct;
    @FXML
    private HBox cssDeleteProduct;
    @FXML
    private HBox cssStatistic;
    @FXML
    private HBox cssViewMem;
    @FXML
    private HBox cssCreateMem;
    @FXML
    private HBox cssUpdateMem;
    @FXML
    private HBox cssDeleteMem;
    @FXML
    private HBox cssUpdateProfile;
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
        hideSupMyProfileAdmin(false);

        setLanguageItem();

    }

    private void changeLanguage(String language, String country) {

        Locale locale = new Locale(language, country);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/home_manager/Bundle", locale);

        bdDashboard.setText(resourceBundle.getString("bdDashboard"));
        bdManageMedicine.setText(resourceBundle.getString("bdManageMedicine"));
        bdImport.setText(resourceBundle.getString("bdImport"));
        bdStatistic.setText(resourceBundle.getString("bdStatistic"));
        bdManageProfile.setText(resourceBundle.getString("bdManageProfile"));
        bdMyProfile.setText(resourceBundle.getString("bdMyProfile"));
        bdChangePassword.setText(resourceBundle.getString("bdChangePassword"));
        bdSetting.setText(resourceBundle.getString("bdSetting"));
        bdLogout.setText(resourceBundle.getString("bdLogout"));

        bdViewManageMedicine.setText(resourceBundle.getString("bdViewManageMedicine"));
        bdCreateManageMedicine.setText(resourceBundle.getString("bdCreateManageMedicine"));
        bdUpdateManageMedicine.setText(resourceBundle.getString("bdUpdateManageMedicine"));
        bdDeleteManageMedicine.setText(resourceBundle.getString("bdDeleteManageMedicine"));
        bdViewManageProfile.setText(resourceBundle.getString("bdViewManageProfile"));
        bdCreateManageProfile.setText(resourceBundle.getString("bdCreateManageProfile"));
        bdUpdateManageProfile.setText(resourceBundle.getString("bdUpdateManageProfile"));
        bdDeleteManageProfile.setText(resourceBundle.getString("bdDeleteManageProfile"));
        bdUpdateMyProfile.setText(resourceBundle.getString("bdUpdateMyProfile"));
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

//    set info user from UI_Login
    public void setInfoUser(String fullname, String position, int id, String role) {
        fullnameLabel.setText(fullname);
        positionLabel.setText(position);
        gUserId = id;
        gRole = role;
    }

//    set center of layout
    public void setCenterHomeBox(String view) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/views/" + view + ".fxml"));
        homeBox.setCenter(root);

    }

    private void hideSupMedicine(boolean value) {
        supMedicine.setVisible(value);
        supMedicine.managedProperty().bind(supMedicine.visibleProperty());
        cssViewProduct.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssCreateProduct.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssUpdateProduct.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssImportProduct.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssDeleteProduct.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssStatistic.setStyle("-fx-cursor: hand; -fx-background-color: null");
    }

    private void hideSupMyProfile(boolean value) {
        supMyProfile.setVisible(value);
        supMyProfile.managedProperty().bind(supMyProfile.visibleProperty());
        cssViewMem.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssCreateMem.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssUpdateMem.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssDeleteMem.setStyle("-fx-cursor: hand; -fx-background-color: null");
    }

    private void hideSupSettings(boolean value) {
        supSettings.setVisible(value);
        supSettings.managedProperty().bind(supSettings.visibleProperty());

    }

    private void hideSupMyProfileAdmin(boolean value) {
        supMyProfileAdmin.setVisible(value);
        supMyProfileAdmin.managedProperty().bind(supMyProfileAdmin.visibleProperty());
        cssUpdateProfile.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssChangePassword.setStyle("-fx-cursor: hand; -fx-background-color: null");
    }

    @FXML
    private void dashboardClicked(MouseEvent event) throws IOException {
//        event clicked to label dashboard
        hideSupMedicine(false);
        hideSupMyProfile(false);
        hideSupSettings(false);
        hideSupMyProfileAdmin(false);

        setCenterHomeBox("Notice");
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
        gRole = null;
//        gLanguage = null;
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
        cssViewProduct.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssCreateProduct.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssUpdateProduct.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssImportProduct.setStyle("-fx-cursor: hand; -fx-background-color:" + color);
        cssDeleteProduct.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssStatistic.setStyle("-fx-cursor: hand; -fx-background-color: null");
    }

    @FXML
    private void createProductClicked(MouseEvent event) throws IOException {
        setCenterHomeBox("AddProduct");
        cssViewProduct.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssCreateProduct.setStyle("-fx-cursor: hand; -fx-background-color:" + color);
        cssUpdateProduct.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssImportProduct.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssDeleteProduct.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssStatistic.setStyle("-fx-cursor: hand; -fx-background-color: null");
    }

    @FXML
    private void updateClicked(MouseEvent event) throws IOException {
        setCenterHomeBox("UpdateProduct");
        cssViewProduct.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssCreateProduct.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssUpdateProduct.setStyle("-fx-cursor: hand; -fx-background-color:" + color);
        cssImportProduct.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssDeleteProduct.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssStatistic.setStyle("-fx-cursor: hand; -fx-background-color: null");

    }

    @FXML
    private void viewClicked(MouseEvent event) throws IOException {
        setCenterHomeBox("ViewProduct");
        cssViewProduct.setStyle("-fx-cursor: hand; -fx-background-color:" + color);
        cssCreateProduct.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssUpdateProduct.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssImportProduct.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssDeleteProduct.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssStatistic.setStyle("-fx-cursor: hand; -fx-background-color: null");

    }

    @FXML
    private void deleteClicked(MouseEvent event) throws IOException {
        setCenterHomeBox("DeleteProduct");
        cssViewProduct.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssCreateProduct.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssUpdateProduct.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssImportProduct.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssDeleteProduct.setStyle("-fx-cursor: hand; -fx-background-color:" + color);
        cssStatistic.setStyle("-fx-cursor: hand; -fx-background-color: null");
    }

    @FXML
    private void statisticClicked(MouseEvent event) throws IOException {
        setCenterHomeBox("StatisticManager");
        cssViewProduct.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssCreateProduct.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssUpdateProduct.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssImportProduct.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssDeleteProduct.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssStatistic.setStyle("-fx-cursor: hand; -fx-background-color:" + color);
    }

    @FXML
    private void updateProfileAdminClicked(MouseEvent event) throws IOException {
        setCenterHomeBox("UpdateProfileAdmin");
        cssUpdateProfile.setStyle("-fx-cursor: hand; -fx-background-color:" + color);
        cssChangePassword.setStyle("-fx-cursor: hand; -fx-background-color: null");
    }

    @FXML
    private void changePasswordProfileAdminClicked(MouseEvent event) throws IOException {
        setCenterHomeBox("ChangePasswordAdmin");
        cssUpdateProfile.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssChangePassword.setStyle("-fx-cursor: hand; -fx-background-color:" + color);
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
    private void viewMemberClicked(MouseEvent event) throws IOException {
        setCenterHomeBox("ViewUsers");
        cssViewMem.setStyle("-fx-cursor: hand; -fx-background-color:" + color);
        cssCreateMem.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssUpdateMem.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssDeleteMem.setStyle("-fx-cursor: hand; -fx-background-color: null");

    }

    @FXML
    private void createMemberClicked(MouseEvent event) throws IOException {
        setCenterHomeBox("CreateUsers");
        cssViewMem.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssCreateMem.setStyle("-fx-cursor: hand; -fx-background-color:" + color);
        cssUpdateMem.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssDeleteMem.setStyle("-fx-cursor: hand; -fx-background-color: null");

    }

    @FXML
    private void updateMemberClicked(MouseEvent event) throws IOException {
        setCenterHomeBox("UpdateUsers");
        cssViewMem.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssCreateMem.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssUpdateMem.setStyle("-fx-cursor: hand; -fx-background-color:" + color);
        cssDeleteMem.setStyle("-fx-cursor: hand; -fx-background-color: null");

    }

    @FXML
    private void deleteMemberClicked(MouseEvent event) throws IOException {
        setCenterHomeBox("DeleteUser");
        cssViewMem.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssCreateMem.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssUpdateMem.setStyle("-fx-cursor: hand; -fx-background-color: null");
        cssDeleteMem.setStyle("-fx-cursor: hand; -fx-background-color:" + color);

    }

}
