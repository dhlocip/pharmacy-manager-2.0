/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodifier;

import connection.UseDataBase;
import static connection.UseDataBase.connect;
import data.AllInfoProduct;
import java.sql.*;
import java.util.*;
import javafx.collections.*;

/**
 *
 * @author sa
 */
public class AllInfoProductModifier {

    public ObservableList<AllInfoProduct> findProduct(String productName) throws SQLException {
        ObservableList<AllInfoProduct> listProducts = FXCollections.observableArrayList();
        String sql = "select * from allInforProduct "
                + "where productName like '" + productName + "%'";

        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.execute();

        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            listProducts.add(new AllInfoProduct(result.getInt("productTypeId"), result.getInt("productId"),
                    result.getString("productName"), result.getString("unit"), result.getInt("quantity"),
                    result.getString("mfgDate"), result.getString("expDate")));
        }
        return listProducts;
    }

    public static void main(String[] args) throws SQLException {
//        ObservableList<AllInfoProduct> list = new AllInfoProductModifier().findProduct("Medicine ");
//        System.out.println("size: " + list.size());
//        list.forEach((t) -> {
//            System.out.println(t.getProductName());
//        });
    }
}
