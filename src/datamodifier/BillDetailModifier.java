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
}
