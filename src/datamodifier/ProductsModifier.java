/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodifier;

import connection.UseDataBase;
import data.Products;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.*;

/**
 *
 * @author sa
 */
public class ProductsModifier extends UseDataBase {

    public ObservableList<Products> getInfo() throws SQLException {
        ObservableList<Products> oList = FXCollections.observableArrayList();

        String sql = "select * from products";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.execute();

        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(new Products(result.getInt("productId"), result.getInt("productTypeId"),
                    result.getString("productName"), result.getString("unit"), result.getDouble("price")));
        }
        return oList;
    }

    public ObservableList<Products> getInfoByIdOrName(String productName) throws SQLException {
        ObservableList<Products> oList = FXCollections.observableArrayList();

        int productId = 0;
        String sql;
        PreparedStatement preStatement;

        try {
            productId = Integer.parseInt(productName);
            sql = "select * from products "
                    + "where productId = ?";
            preStatement = connect().prepareStatement(sql);
            preStatement.setInt(1, productId);
            preStatement.execute();
        } catch (NumberFormatException ex) {
            sql = "select * from products "
                + "where productName like '%" + productName + "%'";
            preStatement = connect().prepareStatement(sql);
            preStatement.execute();
        }

        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(new Products(result.getInt("productId"), result.getInt("productTypeId"),
                    result.getString("productName"), result.getString("unit"), result.getDouble("price")));
        }
        return oList;
    }

    public boolean updateProduct(Products product) throws SQLException {
        String sql = "update products "
                + "set productName =?, productTypeId = ?, unit = ?, price = ? "
                + "where productId = ?";
        PreparedStatement preStatement = connect().prepareStatement(sql);

        preStatement.setString(1, product.getProductName());
        preStatement.setInt(2, product.getProductTypeId());
        preStatement.setString(3, product.getUnit());
        preStatement.setDouble(4, product.getPrice());
        preStatement.setInt(5, product.getProductId());

        preStatement.executeUpdate();
        return true;
    }
    
    //    delete product by productId
    public boolean deleteByProductId(int productId) throws SQLException{
        String sql = "delete from products "
                + "where productId = ?";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setInt(1, productId);
        preStatement.execute();
        return true;
    }

    public static void main(String[] args) throws SQLException {
//        Products pro = new Products(30000, 20002, "Medicine F", "box", 50000);
        ObservableList<Products> l = new ProductsModifier().getInfoByIdOrName("r");
        l.forEach((t) -> {
            System.out.println(t.getProductName());
        });
//        int a = 0;
//        String tmp = null;
//        String b = "3b";
//        boolean boo = false;
//        try {
//            a = Integer.parseInt(b);
//            boo = false;
//        } catch (NumberFormatException ex) {
//            tmp = b;
//            boo = true;
//        }
//
//        System.out.println(a);
//        System.out.println(tmp);
//        System.out.println(boo);
    }
}
