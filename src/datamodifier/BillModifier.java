/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodifier;

import connection.UseDataBase;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sa
 */
public class BillModifier extends UseDataBase {

    public boolean addBill(int userId) throws SQLException {
        String sql = "insert into bills(userId, transactionTime) "
                + "values(?, getdate())";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setInt(1, userId);
        preStatement.execute();
        return true;
    }

    public int getMaxBillId(int userId) throws SQLException {
        String sql = "select max(billId) as number "
                + "from bills where userId =?";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setInt(1, userId);
        preStatement.execute();
        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            return result.getInt("number");
        }
        return 0;
    }
    
    public List<Integer> getListBills(int userId) throws SQLException{
        List<Integer> list = new ArrayList<>();
        String sql = "select * from bills where userId = ?";
        PreparedStatement preS = connect().prepareStatement(sql);
        preS.setInt(1, userId);
        preS.execute();
        ResultSet result = preS.getResultSet();
        while(result.next()){
            list.add(result.getInt("billId"));
        }
        return list;
    }
    
    public boolean deleteByUserId(int userId) throws SQLException{
        String sql = "delete from bills "
                + "where userId = ?";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setInt(1, userId);
        preStatement.execute();
        return true;
    }

}
