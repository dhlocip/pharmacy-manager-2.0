/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import datamodifier.StatisticModifier;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;

/**
 * FXML Controller class
 *
 * @author sa
 */
public class AppController implements Initializable {

    @FXML
    private LineChart<String, Number> linechart;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList<XYChart.Series<String, Number>> sList = FXCollections.observableArrayList();
        try {
            
            
            ObservableList<Data<String, Number>> dList1 = new StatisticModifier().getLineChartSaleMember(10001, "09/01/2020", "09/30/2020");
            ObservableList<Data<String, Number>> dList2 = new StatisticModifier().getLineChartSaleMember(10002, "09/01/2020", "09/30/2020");
            ObservableList<Data<String, Number>> dList3 = new StatisticModifier().getLineChartSaleMember(10003, "09/01/2020", "09/30/2020");
            ObservableList<Data<String, Number>> dList4 = new StatisticModifier().getLineChartSaleMember(10004, "09/01/2020", "09/30/2020");
            
            XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
            series1.getData().addAll(dList1);
            
            XYChart.Series<String, Number> series2 = new XYChart.Series<String, Number>();
            series2.getData().addAll(dList2);
            
            XYChart.Series<String, Number> series3 = new XYChart.Series<String, Number>();
            series3.getData().addAll(dList3);
            
            XYChart.Series<String, Number> series4 = new XYChart.Series<String, Number>();
            series4.getData().addAll(dList4);
            
            linechart.setTitle("total ...");
            series1.setName("total sale of a");
            series2.setName("total sale of b");
            series3.setName("total sale of c");
            series4.setName("total sale of d");

            linechart.getData().add(series1);
            linechart.getData().add(series2);
            linechart.getData().add(series3);
            linechart.getData().add(series4);
//            linechart.getData().addAll(c);
        } catch (SQLException ex) {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
