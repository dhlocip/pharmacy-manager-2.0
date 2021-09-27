/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class NoticeController implements Initializable {

    String lLanguage;

    @FXML
    private Label bdNotice;
    @FXML
    private Label bdNoticeTitle;
    @FXML
    private Label bdNoticeSup1;
    @FXML
    private Label bdNoticeSup2;
    @FXML
    private Label bdWorkTime;
    @FXML
    private Label bdWorkTimeSup1;
    @FXML
    private Label bdWorkTimeSup2;
    @FXML
    private Label bdWorkTimeSup3;
    @FXML
    private Label bdWorkTimeSup4;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setLanguage();

    }

    private void setLanguage() {
        String role = HomeManagerController.gRole;
        String langManager = HomeManagerController.gLanguage;
        String langMeber = HomeMemberController.gLanguage;
        if (role != null) {
            if (langManager.equalsIgnoreCase("english")) {
                changeLanguage("en", "EN");
            } else {
                changeLanguage("vi", "VN");
            }
        } else {
            if (langMeber.equalsIgnoreCase("english")) {
                changeLanguage("en", "EN");
            } else {
                changeLanguage("vi", "VN");
            }
        }

    }

    private void changeLanguage(String language, String country) {

        Locale locale = new Locale(language, country);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/notice/Bundle", locale);

        bdNotice.setText(resourceBundle.getString("bdNotice"));
        bdNoticeTitle.setText(resourceBundle.getString("bdNotice"));
        bdNoticeSup1.setText(resourceBundle.getString("bdNoticeSup1"));
        bdNoticeSup2.setText(resourceBundle.getString("bdNoticeSup2"));
        bdWorkTimeSup1.setText(resourceBundle.getString("bdWorkTimeSup1"));
        bdWorkTimeSup2.setText(resourceBundle.getString("bdWorkTimeSup2"));
        bdWorkTimeSup3.setText(resourceBundle.getString("bdWorkTimeSup3"));
        bdWorkTimeSup4.setText(resourceBundle.getString("bdWorkTimeSup4"));
    }
}
