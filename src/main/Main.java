/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import data.Cart;
import datamodifier.StatisticModifier;
import java.sql.SQLException;
import java.util.List;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author sa
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        launch(args);

//        List<Cart> cList = new StatisticModifier().getTest("09/01/2020", "09/30/2020");
//
//        
//        
//        for (int i = 1; i < cList.size(); i++) {
//            if(cList.get(i-1).getUserId() == cList.get(i).getUserId()){
//                System.out.println(cList.get(i-1).getUserId() + " = " + cList.get(i).getUserId());
//            }else{
//                System.out.println(cList.get(i-1).getUserId() + " != " + cList.get(i).getUserId());
//            }
//            
//        }
//        System.out.println(cList.size());
//        System.out.println("end");
    }
}
