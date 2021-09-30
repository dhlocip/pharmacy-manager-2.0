/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import data.Cart;
import datamodifier.StatisticModifier;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class StatisticManagerController implements Initializable {

    int lUserId;
    String lStartDate, lEndDate;

    @FXML
    private BorderPane homeBox;
    @FXML
    private Label startDate;
    @FXML
    private Label endDate;
    @FXML
    private TableColumn<Cart, Double> tTotal;
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
    private TableColumn<Cart, Integer> bUserId;
    @FXML
    private TableColumn<Cart, Integer> dUserId;
    @FXML
    private HBox saleDetailBack;
    @FXML
    private HBox topSaleNext;
    @FXML
    private VBox detailSaleBox;
    @FXML
    private Label tTotal1;
    @FXML
    private VBox topSaleBox;
    @FXML
    private Label endDate1;
    @FXML
    private Label tTotal2;
    @FXML
    private TableView<Cart> tableViewTopSale;
    @FXML
    private TableColumn<Cart, Integer> tUserid;
    @FXML
    private HBox executeTopBox;
    @FXML
    private HBox executeDetailBox;
    @FXML
    private HBox lineChartBox;
    @FXML
    private HBox barChartBox;
    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private NumberAxis yAxisLine;
    @FXML
    private CategoryAxis xAxisLine;
    @FXML
    private NumberAxis yAxisBar;
    @FXML
    private CategoryAxis xAxisBar;
    @FXML
    private Label bdStartDate;
    @FXML
    private Label bdEndDate;
    @FXML
    private Label bdExeTop;
    @FXML
    private Label bdExeDetail;
    @FXML
    private Label bdSaleDetail;
    @FXML
    private Label bdTopSale;
    @FXML
    private Label bdTitleDetail;
    @FXML
    private Label bdTitleTop;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        hideErrorOfStartDate(false);
        hideErrorOfStartBill(false);
//        xAxis.s

        hideTopSale(false);
        hideSaleDetailBack(false);
        hideExecuteTop(false);
        hideBarChart(false);
        
        setLanguage();
    }
    
    private void setLanguage() {
        String langManager = HomeManagerController.gLanguage;
        if (langManager.equalsIgnoreCase("english")) {
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
        bdExeDetail.setText(resourceBundle.getString("bdExeDetail"));
        bdSaleDetail.setText(resourceBundle.getString("bdSaleDetail"));
        bdTopSale.setText(resourceBundle.getString("bdTopSale"));
        bdTitleDetail.setText(resourceBundle.getString("bdTitleDetail"));
        bdTitleTop.setText(resourceBundle.getString("bdTitleTop"));
        
    }

    private void getTableTopSale() throws SQLException {
        ObservableList<Cart> oList = new StatisticModifier().getTableSale(lStartDate, lEndDate);

        tUserid.setCellValueFactory(new PropertyValueFactory<>("userId"));
        tTotal.setCellValueFactory(new PropertyValueFactory<>("amount"));

        tableViewTopSale.setItems(oList);
    }

    private void getBarChart() throws SQLException {
        ObservableList<XYChart.Data<String, Number>> data = new StatisticModifier().getBarChart(lStartDate, lEndDate);

        XYChart.Series<String, Number> series = new XYChart.Series();
        series.getData().addAll(data);

        barChart.getData().clear();
        barChart.getData().add(series);
        barChart.setTitle("Top sales");
        xAxisBar.setLabel("User ID");
    }

    private void getLineChart() throws SQLException {
        ObservableList<XYChart.Series<String, Number>> series = FXCollections.observableArrayList();

        ObservableList<Cart> cList = new StatisticModifier().getTotalSaleMemberByDate(lStartDate, lEndDate);

        XYChart.Series<String, Number> tmpSeries = new XYChart.Series();

        for (int i = 1; i < cList.size(); i++) {
            tmpSeries.getData().add(new XYChart.Data(cList.get(i - 1).getTransactionTime(), cList.get(i - 1).getAmount()));

            if (cList.get(i - 1).getUserId() != cList.get(i).getUserId()) {
                series.add(tmpSeries);
                tmpSeries.setName("Employee No. " + cList.get(i - 1).getUserId());
                tmpSeries = new XYChart.Series();

                tmpSeries.getData().add(new XYChart.Data(cList.get(i).getTransactionTime(), cList.get(i).getAmount()));
            } else {
                tmpSeries.getData().add(new XYChart.Data(cList.get(i).getTransactionTime(), cList.get(i).getAmount()));
            }

            if (i == cList.size() - 1) {
                tmpSeries.setName("Employee No. " + cList.get(i).getUserId());
                series.add(tmpSeries);
            }

        }
        if (cList.size() == 1) {
            tmpSeries.getData().add(new XYChart.Data(cList.get(0).getTransactionTime(), cList.get(0).getAmount()));
            series.add(tmpSeries);
            tmpSeries.setName("Employee No. " + cList.get(0).getUserId());

        }

        lineChart.getData().clear();
        lineChart.getData().addAll(series);
        lineChart.setTitle("Sales by day");
    }

    private void getTableTotalSaleByDate() throws SQLException {
        ObservableList<Cart> cList = new StatisticModifier().getTotalSaleMemberByDate(lStartDate, lEndDate);

        dUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        dTotal.setCellValueFactory(new PropertyValueFactory<>("amount"));
        dDate.setCellValueFactory(new PropertyValueFactory<>("transactionTime"));

        tableViewByDate.setItems(cList);
    }

    private void getTableTotalSaleByBill() throws SQLException {
        ObservableList<Cart> cList = new StatisticModifier().getTotalSaleMemberByBill(lStartDate, lEndDate);

        bUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
        bBillId.setCellValueFactory(new PropertyValueFactory<>("billId"));
        bTotal.setCellValueFactory(new PropertyValueFactory<>("amount"));
        bDate.setCellValueFactory(new PropertyValueFactory<>("transactionTime"));

        tableViewByBillId.setItems(cList);
    }

    private void hideLineChart(boolean value) {
        lineChartBox.setVisible(value);
        lineChartBox.managedProperty().bind(lineChartBox.visibleProperty());
    }

    private void hideBarChart(boolean value) {
        barChartBox.setVisible(value);
        barChartBox.managedProperty().bind(barChartBox.visibleProperty());
    }

    private void hideExecuteTop(boolean value) {
        executeTopBox.setVisible(value);
        executeTopBox.managedProperty().bind(executeTopBox.visibleProperty());
    }

    private void hideExecuteDetail(boolean value) {
        executeDetailBox.setVisible(value);
        executeDetailBox.managedProperty().bind(executeDetailBox.visibleProperty());
    }

    private void hideSaleDetailBack(boolean value) {
        saleDetailBack.setVisible(value);
        saleDetailBack.managedProperty().bind(saleDetailBack.visibleProperty());
    }

    private void hideTopSaleNext(boolean value) {
        topSaleNext.setVisible(value);
        topSaleNext.managedProperty().bind(topSaleNext.visibleProperty());
    }

    private void hideSaleDetail(boolean value) {
        detailSaleBox.setVisible(value);
        detailSaleBox.managedProperty().bind(detailSaleBox.visibleProperty());
    }

    private void hideTopSale(boolean value) {
        topSaleBox.setVisible(value);
        topSaleBox.managedProperty().bind(topSaleBox.visibleProperty());
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
    private void topSaleClicked(MouseEvent event) throws IOException {
        hideSaleDetail(false);
        hideTopSaleNext(false);
        hideExecuteDetail(false);
        hideSaleDetailBack(true);
        hideTopSale(true);
        hideExecuteTop(true);
        hideLineChart(false);
        hideBarChart(true);
    }

    @FXML
    private void detailSaleClicked(MouseEvent event) {
        hideSaleDetail(true);
        hideTopSaleNext(true);
        hideExecuteDetail(true);
        hideExecuteTop(false);
        hideSaleDetailBack(false);
        hideTopSale(false);
        hideBarChart(false);
        hideLineChart(true);
    }

    @FXML
    private void executeTopClicked(MouseEvent event) throws SQLException {
        getBarChart();
        getTableTopSale();
    }

    @FXML
    private void executeDetailClicked(MouseEvent event) throws SQLException {
        getLineChart();
        getTableTotalSaleByDate();
        getTableTotalSaleByBill();
    }

}
