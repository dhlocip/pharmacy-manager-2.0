/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodifier;

import data.Cart;
import javafx.collections.ObservableList;
import java.sql.*;
import java.text.DecimalFormat;
import javafx.collections.FXCollections;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;

/**
 *
 * @author sa
 */
public class StatisticModifier extends UserModifier {

    public ObservableList<Data<String, Number>> getLineChartSaleMember(int userId, String startDate, String endDate) throws SQLException {
        ObservableList<Data<String, Number>> dList = FXCollections.observableArrayList();
        String sql = "select * from vStatisticByDate "
                + "where userId = ? and transactionTime between ? and ?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setInt(1, userId);
        preS.setString(2, startDate);
        preS.setString(3, endDate);
        preS.execute();
        ResultSet result = preS.getResultSet();
        while (result.next()) {
            dList.add(new XYChart.Data<String, Number>(result.getString("transactionTime"), result.getDouble("total")));
        }
        return dList;
    }

    public ObservableList<Cart> getTotalSaleMemberByBill(int userId, String startDate, String endDate) throws SQLException {
        ObservableList<Cart> cList = FXCollections.observableArrayList();
        String sql = "select * from vStatisticByBill "
                + "where userId = ? and transactionTime between ? and ?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setInt(1, userId);
        preS.setString(2, startDate);
        preS.setString(3, endDate);
        preS.execute();
        ResultSet result = preS.getResultSet();
        while (result.next()) {
            cList.add(new Cart(result.getInt("userId"), result.getInt("billId"),
                    result.getDouble("total"), result.getString("transactionTime")));
        }
        return cList;
    }

    public ObservableList<Cart> getTotalSaleMemberByBill(String startDate, String endDate) throws SQLException {
        ObservableList<Cart> cList = FXCollections.observableArrayList();
        String sql = "select * from vStatisticByBill "
                + "where transactionTime between ? and ?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, startDate);
        preS.setString(2, endDate);
        preS.execute();
        ResultSet result = preS.getResultSet();
        while (result.next()) {
            cList.add(new Cart(result.getInt("userId"), result.getInt("billId"),
                    result.getDouble("total"), result.getString("transactionTime")));
        }
        return cList;
    }

    public ObservableList<Cart> getTotalSaleMemberByDate(int userId, String startDate, String endDate) throws SQLException {
        ObservableList<Cart> cList = FXCollections.observableArrayList();
        String sql = "select * from vStatisticByDate "
                + "where userId = ? and transactionTime between ? and ?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setInt(1, userId);
        preS.setString(2, startDate);
        preS.setString(3, endDate);
        preS.execute();
        ResultSet result = preS.getResultSet();
        while (result.next()) {
            cList.add(new Cart(result.getInt("userId"),
                    result.getDouble("total"), result.getString("transactionTime")));
        }
        return cList;
    }

    public ObservableList<Cart> getTotalSaleMemberByDate(String startDate, String endDate) throws SQLException {
        ObservableList<Cart> cList = FXCollections.observableArrayList();
        String sql = "select * from vStatisticByDate "
                + "where transactionTime between ? and ?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, startDate);
        preS.setString(2, endDate);
        preS.execute();
        ResultSet result = preS.getResultSet();
        while (result.next()) {
            cList.add(new Cart(result.getInt("userId"),
                    result.getDouble("total"), result.getString("transactionTime")));
        }
        return cList;
    }

    public static void main(String[] args) throws SQLException {
//        ObservableList<Cart> cart = new StatisticModifier().getTotalSaleMemberByDate(10001, "09/01/2020", "09/30/2021");
//        cart.forEach((t) -> {
//            System.out.println(t.getAmount());
//        });

        double num = 2.5E7;
        System.out.println(num);
        DecimalFormat format = new DecimalFormat("#0,000.00");
        String formattedNumber = format.format(num);
        System.out.println(formattedNumber);
    }

}
