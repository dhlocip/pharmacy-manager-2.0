/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodifier;

import connection.UseDataBase;
import data.AllInfoProduct;
import java.sql.*;
import java.util.*;
import javafx.collections.*;

/**
 *
 * @author sa
 */
public class AllInfoProductModifier extends UseDataBase {

    public ObservableList<AllInfoProduct> findProduct(String productName) throws SQLException {
        ObservableList<AllInfoProduct> listProducts = FXCollections.observableArrayList();

        int productId = 0;
        String sql;
        PreparedStatement preStatement;

        try {
            productId = Integer.parseInt(productName);
            sql = "select * from allInforProduct "
                    + "where productId like '%" + productName + "%'";
            preStatement = connect().prepareStatement(sql);
            preStatement.execute();
        } catch (NumberFormatException ex) {
            sql = "select * from allInforProduct "
                    + "where productName like '%" + productName + "%'";
            preStatement = connect().prepareStatement(sql);
            preStatement.execute();
        }

        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            listProducts.add(new AllInfoProduct(result.getInt("productTypeId"), result.getInt("productId"),
                    result.getString("productName"), result.getString("unit"), result.getInt("quantity"),
                    result.getDouble("price"), result.getString("mfgDate"), result.getString("expDate")));
        }
        return listProducts;
    }

    public ObservableList<AllInfoProduct> viewProduct() throws SQLException {
        ObservableList<AllInfoProduct> listProducts = FXCollections.observableArrayList();
        String sql = "select * from allInforProduct";

        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.execute();

        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            listProducts.add(new AllInfoProduct(result.getInt("productTypeId"), result.getInt("productId"),
                    result.getString("productName"), result.getString("unit"), result.getInt("quantity"),
                    result.getDouble("price"), result.getString("mfgDate"), result.getString("expDate")));
        }
        return listProducts;
    }

}
