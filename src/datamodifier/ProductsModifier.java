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
                    + "where productId like '%" + productName + "%'";
            preStatement = connect().prepareStatement(sql);
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

    public boolean updateProducts(Products product) throws SQLException {
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
    
    public boolean insertIntoProducts(Products product) throws SQLException {
        String sql = "insert into products "
                + "values(?,?,?,?)";
        
        PreparedStatement preStatement = connect().prepareStatement(sql);

        preStatement.setString(1, product.getProductName());
        preStatement.setInt(2, product.getProductTypeId());
        preStatement.setString(3, product.getUnit());
        preStatement.setDouble(4, product.getPrice());

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

}
