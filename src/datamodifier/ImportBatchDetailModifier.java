/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodifier;

import connection.UseDataBase;
import static connection.UseDataBase.connect;
import data.ImportBatch;
import data.ImportBatchDetail;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author sa
 */
public class ImportBatchDetailModifier extends UseDataBase {

//    get total product inside importBatchDetail != 0?
    public int getNumberProduct(int importBatchId) throws SQLException {
        String sql = "select count(*) as number "
                + "from importBatchDetail where importBatchId =?";

        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setInt(1, importBatchId);
        preStatement.execute();

        ResultSet result = preStatement.getResultSet();

        while (result.next()) {
            return result.getInt("number");
        }
        return 0;
    }

//    add product to importBatchDetail
    public boolean addBatchDetail(ImportBatchDetail importBatchDetail) throws SQLException {
        String sql = "insert into importBatchDetail "
                + "values(?,?,?,?,?,?)";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setInt(1, importBatchDetail.getImpId());
        preStatement.setInt(2, importBatchDetail.getProId());
        preStatement.setDouble(3, importBatchDetail.getPrice());
        preStatement.setInt(4, importBatchDetail.getQuantity());
        preStatement.setString(5, importBatchDetail.getMfgDate());
        preStatement.setString(6, importBatchDetail.getExpDate());
        preStatement.execute();
        return true;
    }

//    get product info by userId
    public ObservableList<ImportBatchDetail> getImpBatchDetailInfo(int userId) throws SQLException {
        ObservableList<ImportBatchDetail> oList = FXCollections.observableArrayList();
        String sql = "select * from vImportBatchDetail "
                + "where userId = ? "
                + "order by importBatchId desc";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setInt(1, userId);
        preStatement.execute();
        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(new ImportBatchDetail(result.getInt("importBatchId"), result.getInt("productId"), result.getDouble("importPrice"),
                    result.getInt("importQuantity"), result.getString("mfgDate"), result.getString("expDate")));
        }
        return oList;
    }

}
