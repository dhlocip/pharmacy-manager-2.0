/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodifier;

import connection.UseDataBase;
import static connection.UseDataBase.connect;
import data.BillDetail;
import java.sql.*;
import javafx.collections.ObservableList;

/**
 *
 * @author sa
 */
public class BillDetailModifier extends UseDataBase {
    
    public boolean addBillDetail(BillDetail billDetail) throws SQLException {
        String sql = "insert into billDetail(billId, productId, saleQuantity, mfgDate, expDate) "
                + "values(?,?,?, convert(varchar(10),?, 126), convert(varchar(10),?, 126))";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setInt(1, billDetail.getBillId());
        preStatement.setInt(2, billDetail.getProductId());
        preStatement.setInt(3, billDetail.getSaleQuantity());
        preStatement.setString(4, billDetail.getMfgDate());
        preStatement.setString(5, billDetail.getExpDate());
        preStatement.execute();
        return true;
    }
    
    //    delete billDetail by productId
    public boolean deleteByProductId(int productId) throws SQLException{
        String sql = "delete from billDetail "
                + "where productId = ?";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setInt(1, productId);
        preStatement.execute();
        return true;
    }
    
    public boolean deleteOnCart(int productId, int billId) throws SQLException{
        String sql = "delete from billDetail "
                + "where productId = ? and billId = ?";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setInt(1, productId);
        preStatement.setInt(2, billId);
        preStatement.execute();
        return true;
    }
    
    public boolean isExists(int productId, int billId) throws SQLException{
        String sql = "select * from billDetail "
                + "where productId = ? and billId = ?";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setInt(1, productId);
        preStatement.setInt(2, billId);
        preStatement.execute();
        
        ResultSet result = preStatement.getResultSet();
        while(result.next()){
            return true;
        }
        return false;
    }
    
    public boolean updateQuantity(int quantity, int billId, int productId) throws SQLException{
        String sql = "update billDetail "
                + "set saleQuantity = ? "
                + "where billId = ? and productId = ?";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setInt(1, quantity);
        preStatement.setInt(2, billId);
        preStatement.setInt(3, productId);
        preStatement.executeUpdate();
        return true;
    }
    
}
