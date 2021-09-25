/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodifier;

import connection.UseDataBase;
import static connection.UseDataBase.connect;
import data.ProductChild;
import data.ProductType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author sa
 */
public class ProductTypeModifier extends UseDataBase {

    //    get product type info
    public ObservableList<ProductType> getInfo() throws SQLException {
        ObservableList<ProductType> oList = FXCollections.observableArrayList();
        String sql = "select * from productType";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.execute();
        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(new ProductType(result.getInt("productTypeId"), result.getString("productTypeName")));
        }
        return oList;
    }

////    get list productId
//    public ObservableList<Integer> getListProId() throws SQLException {
//        ObservableList<Integer> oList = FXCollections.observableArrayList();
//        String sql = "select * from productType";
//        PreparedStatement preStatement = connect().prepareStatement(sql);
//        preStatement.execute();
//        ResultSet result = preStatement.getResultSet();
//        while (result.next()) {
//            oList.add(result.getInt("productTypeId"));
//        }
//        return oList;
//    }
    //    insert product type new
    public boolean addProductTypeNew(String productTypeName) throws SQLException {
        String sql = "insert into productType "
                + "values(?)";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setString(1, productTypeName);
        preStatement.execute();
        return true;
    }

    //    get list productTypeId
    public ObservableList<Integer> getListProTypeId() throws SQLException {
        ObservableList<Integer> oList = FXCollections.observableArrayList();
        String sql = "select * from productType";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.execute();
        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(result.getInt("productTypeId"));
        }
        return oList;
    }

    public boolean matchProductType(String productTypeName) throws SQLException {
        String sql = "select *  from productType "
                + "where productTypeName = ?";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setString(1, productTypeName);
        preStatement.execute();

        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) throws SQLException {
        System.out.println(new ProductTypeModifier().matchProductType("type a"));
    }
}
