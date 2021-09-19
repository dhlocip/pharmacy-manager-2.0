/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodifier;

import connection.UseDataBase;
import java.sql.*;

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

    

//    public int getNumberProduct(int id) throws SQLException {
//        String sql = "select count(*) as number "
//                + "from billDetail where billId =?";
//
//        PreparedStatement preStatement = connect().prepareStatement(sql);
//        preStatement.setInt(1, id);
//        preStatement.execute();
//
//        ResultSet result = preStatement.getResultSet();
//
//        while (result.next()) {
//            return result.getInt("number");
//        }
//        return 0;
//    }

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

//    public static void main(String[] args) throws SQLException {
////        BillModifier bill = new BillModifier();
//////        if (bill.addToBillDetail(50055, 30000, 2)) {
////            System.out.println("ok");
////        }
//    }
}
