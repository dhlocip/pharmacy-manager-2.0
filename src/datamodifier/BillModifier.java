/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodifier;

import static connection.UseDataBase.connect;
import java.sql.*;

/**
 *
 * @author sa
 */
public class BillModifier {

    public boolean addToBill(int userId) throws SQLException {
        String sql = "insert into bills(userId, transactionTime) "
                + "values(?, getdate())";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setInt(1, userId);
        preStatement.execute();
        return true;
    }
    
    public boolean addToBillDetail(int billId, int productId, int quantity) throws SQLException {
        String sql = "insert into billDetail(billId, productId, saleQuantity) "
                + "values(?,?,?)";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setInt(1, billId);
        preStatement.setInt(2, productId);
        preStatement.setInt(3, quantity);
        preStatement.execute();
        return true;
    }
    
    public int getNumberProduct(int id) throws SQLException{
        String sql = "select count(*) as number "
                + "from billDetail where billId =?";

        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setInt(1, id);
        preStatement.execute();

        ResultSet result = preStatement.getResultSet();
        
        while(result.next()){
            return result.getInt("number");
        }
        return 0;
    }

    public int getBillId() throws SQLException {
        String sql = "select max(billId) as billId from bills";
        Statement statement = connect().createStatement();
        statement.execute(sql);
        ResultSet result = statement.getResultSet();
        while (result.next()) {
            return result.getInt("billId");
        }
        return 0;
    }

    public static void main(String[] args) throws SQLException {
        BillModifier bill = new BillModifier();
        if(bill.addToBillDetail(50055, 30000, 2)){
            System.out.println("ok");
        }
    }
}
