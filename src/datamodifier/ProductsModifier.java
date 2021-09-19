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
    public ObservableList<Products> getInfo() throws SQLException{
        ObservableList<Products> oList = FXCollections.observableArrayList();
        
        String sql = "select * from products";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.execute();
        
        ResultSet result = preStatement.getResultSet();
        while(result.next()){
            oList.add(new Products(result.getInt("productId"), result.getInt("productTypeId"),
                    result.getString("productName"), result.getString("unit"), result.getDouble("price")));
        }
        return oList;
    }
}
