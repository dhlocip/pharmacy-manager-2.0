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
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;

/**
 *
 * @author sa
 */
public class StatisticModifier extends UserModifier {
    
    
    public ObservableList<Cart> getTableSale(String startDate, String endDate) throws SQLException{
        ObservableList<Cart> dList = FXCollections.observableArrayList();
        String sql = "select userId, "
                + "sum(total) as total "
                + "from vStatisticByDate "
                + "where transactionTime between ? and ? "
                + "group by userId";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, startDate);
        preS.setString(2, endDate);
        preS.execute();
        ResultSet result = preS.getResultSet();
        while (result.next()) {
            dList.add(new Cart(result.getInt("userId"), result.getDouble("total")));
        }
        return dList;
        
    }
        
    public ObservableList<Data<String, Number>> getBarChart(String startDate, String endDate) throws SQLException{
        ObservableList<Data<String, Number>> dList = FXCollections.observableArrayList();
        String sql = "select userId, "
                + "sum(total) as total "
                + "from vStatisticByDate "
                + "where transactionTime between ? and ? "
                + "group by userId";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setString(1, startDate);
        preS.setString(2, endDate);
        preS.execute();
        ResultSet result = preS.getResultSet();
        while (result.next()) {
            dList.add(new XYChart.Data((result.getString("userId")), result.getDouble("total")));
        }
        return dList;
        
    }

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
                + "where transactionTime between ? and ? order by userId";
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

}
