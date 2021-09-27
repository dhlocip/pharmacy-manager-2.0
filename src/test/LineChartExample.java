/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage; 
import javafx.scene.chart.LineChart; 
import javafx.scene.chart.NumberAxis; 
import javafx.scene.chart.XYChart; 
         
public class LineChartExample extends Application { 
   @Override 
   public void start(Stage stage) {
      //Defining the x axis             
      NumberAxis xAxis = new NumberAxis(1960, 2020, 10); 
      xAxis.setLabel("Years"); 
        
      //Defining the y axis   
      NumberAxis yAxis = new NumberAxis   (0, 350, 50); 
      yAxis.setLabel("No.of schools"); 
        
      //Creating the line chart 
      LineChart linechart = new LineChart(xAxis, yAxis);  
        
      //Prepare XYChart.Series objects by setting data 
      XYChart.Series series = new XYChart.Series(); 
      series.setName("chart a"); 
        
      series.getData().add(new XYChart.Data(1970, 15)); 
      series.getData().add(new XYChart.Data(1980, 30)); 
      series.getData().add(new XYChart.Data(1990, 60)); 
      series.getData().add(new XYChart.Data(2000, 120)); 
      series.getData().add(new XYChart.Data(2013, 240)); 
      series.getData().add(new XYChart.Data(2014, 300)); 
            
      //Prepare XYChart.Series objects by setting data 
      XYChart.Series series1 = new XYChart.Series(); 
      series1.setName("chart b");
      
      series1.getData().add(new XYChart.Data(1970, 10)); 
      series1.getData().add(new XYChart.Data(1980, 40)); 
      series1.getData().add(new XYChart.Data(1990, 60)); 
      series1.getData().add(new XYChart.Data(2000, 200)); 
      series1.getData().add(new XYChart.Data(2013, 290)); 
      series1.getData().add(new XYChart.Data(2014, 350)); 
      
      //Setting the data to Line chart    
      linechart.getData().add(series);        
      linechart.getData().add(series1);        
        
      //Creating a Group object  
      Group root = new Group(linechart); 
         
      //Creating a scene object 
      Scene scene = new Scene(root, 600, 400);  
      
      //Setting title to the Stage 
      stage.setTitle("Line Chart"); 
         
      //Adding scene to the stage 
      stage.setScene(scene);
	   
      //Displaying the contents of the stage 
      stage.show();         
   } 
   public static void main(String args[]){ 
      launch(args); 
   } 
}