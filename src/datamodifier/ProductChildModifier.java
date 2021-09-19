/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodifier;

import connection.UseDataBase;
import data.ProductChild;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author sa
 */
public class ProductChildModifier extends UseDataBase {

//    get product child info
    public ObservableList<ProductChild> getInfo() throws SQLException {
        ObservableList<ProductChild> oList = FXCollections.observableArrayList();
        String sql = "select * from products";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.execute();
        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(new ProductChild(result.getInt("productId"), result.getString("productName")));
        }
        return oList;
    }
    
//    get list productId
    public ObservableList<Integer> getListProId() throws SQLException {
        ObservableList<Integer> oList = FXCollections.observableArrayList();
        String sql = "select * from products";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.execute();
        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(result.getInt("productId"));
        }
        return oList;
    }

//    public static void main(String[] args) throws SQLException {
//        ObservableList<ProductChild> oList = new ProductChildModifier().getInfo();
//        oList.forEach((t) -> {
//            System.out.println(t.getName());
//        });
//
//    }
}
