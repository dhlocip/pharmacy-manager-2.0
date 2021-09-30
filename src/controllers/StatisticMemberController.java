/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import data.Cart;
import datamodifier.StatisticModifier;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class StatisticMemberController implements Initializable {

    int lUserId;
    String lStartDate, lEndDate;

    @FXML
    private BorderPane homeBox;
    @FXML
    private Label startDate;
    @FXML
    private Label endDate;
    @FXML
    private Label tTotal;
    @FXML
    private TableView<Cart> tableViewByBillId;
    @FXML
    private TableColumn<Cart, Integer> bBillId;
    @FXML
    private TableColumn<Cart, Double> bTotal;
    @FXML
    private TableColumn<Cart, String> bDate;
    @FXML
    private TableView<Cart> tableViewByDate;
    @FXML
    private TableColumn<Cart, Double> dTotal;
    @FXML
    private TableColumn<Cart, String> dDate;
    @FXML
    private LineChart<String, Number> lineChart;
    @FXML
    private Label errorOfStartDate;
    @FXML
    private Label errorOfEndDate;
    @FXML
    private DatePicker dpStartDate;
    @FXML
    private DatePicker dpEndDate;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private CategoryAxis xAxis;
    @FXML
    private Label bdStartDate;
    @FXML
    private Label bdEndDate;
    @FXML
    private Label bdExeTop;
    @FXML
    private Label bdTitleDetail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        hideErrorOfStartDate(false);
        hideErrorOfStartBill(false);

        lUserId = new HomeMemberController().gUserId;
        setLanguage();
    }
    
    private void setLanguage() {
        String langMember = HomeMemberController.gLanguage;
        if (langMember.equalsIgnoreCase("english")) {
            changeLanguage("en", "EN");
        } else {
            changeLanguage("vi", "VN");
        }
    }

    private void changeLanguage(String language, String country) {

        Locale locale = new Locale(language, country);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n/statistic_manager/Bundle", locale);

        bdStartDate.setText(resourceBundle.getString("bdStartDate"));
        bdEndDate.setText(resourceBundle.getString("bdEndDate"));
        bdExeTop.setText(resourceBundle.getString("bdExeTop"));
        bdTitleDetail.setText(resourceBundle.getString("bdTitleDetail"));
        
    }

    private void getLineChart() throws SQLException {
        ObservableList<XYChart.Data<String, Number>> dList1 = new StatisticModifier().getLineChartSaleMember(lUserId, lStartDate, lEndDate);

        XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
        series1.getData().addAll(dList1);

        lineChart.getData().clear();
        lineChart.getData().add(series1);
        
        lineChart.setTitle("Sales by day");
        series1.setName("Your sales");
    }

    private void getTableTotalSaleByDate() throws SQLException {
        ObservableList<Cart> cList = new StatisticModifier().getTotalSaleMemberByDate(lUserId, lStartDate, lEndDate);

        dTotal.setCellValueFactory(new PropertyValueFactory<>("amount"));
        dDate.setCellValueFactory(new PropertyValueFactory<>("transactionTime"));

        tableViewByDate.setItems(cList);
    }

    private void getTableTotalSaleByBill() throws SQLException {
        ObservableList<Cart> cList = new StatisticModifier().getTotalSaleMemberByBill(lUserId, lStartDate, lEndDate);

        bBillId.setCellValueFactory(new PropertyValueFactory<>("billId"));
        bTotal.setCellValueFactory(new PropertyValueFactory<>("amount"));
        bDate.setCellValueFactory(new PropertyValueFactory<>("transactionTime"));

        tableViewByBillId.setItems(cList);
    }

    private void hideErrorOfStartBill(boolean value) {
        errorOfEndDate.setVisible(value);
        errorOfEndDate.managedProperty().bind(errorOfEndDate.visibleProperty());
    }

    private void hideErrorOfStartDate(boolean value) {
        errorOfStartDate.setVisible(value);
        errorOfStartDate.managedProperty().bind(errorOfStartDate.visibleProperty());
    }

    @FXML
    private void startDateAction(ActionEvent event) {
        LocalDate local = dpStartDate.getValue();
        lStartDate = String.valueOf(local.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
    }

    @FXML
    private void endDateAction(ActionEvent event) {
        LocalDate local = dpEndDate.getValue();
        lEndDate = String.valueOf(local.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
    }

    @FXML
    private void executeClicked(MouseEvent event) throws SQLException {
        getLineChart();
        getTableTotalSaleByDate();
        getTableTotalSaleByBill();
    }

}
