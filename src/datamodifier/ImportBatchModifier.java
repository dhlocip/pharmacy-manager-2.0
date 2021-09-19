/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodifier;

import connection.UseDataBase;
import data.ImportBatch;
import javafx.collections.*;
import java.sql.*;

/**
 *
 * @author sa
 */
public class ImportBatchModifier extends UseDataBase {
    
//    get ImportBatch info by userId
    public ObservableList<ImportBatch> getImpBatchInfo(int userId) throws SQLException{
        ObservableList<ImportBatch> oList = FXCollections.observableArrayList();
        String sql = "select * from importBatch "
                + "where userId = ? "
                + "order by importBatchId desc";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setInt(1, userId);
        preStatement.execute();
        ResultSet result = preStatement.getResultSet();
        while(result.next()){
            oList.add(new ImportBatch(result.getInt("importBatchId"), result.getInt("userId"), result.getString("importDate")));
        }
        return oList;
    }
    
//    insert importBatch new
    public boolean addImpNewBatch(int userId) throws SQLException {
        String sql = "insert into importBatch(userId, importDate) "
                + "values(?, getdate())";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setInt(1, userId);
        preStatement.execute();
        return true;
    }
    
//    get importBatchId
    public int getImpId(int userId) throws SQLException {
        String sql = "select max(importBatchId) as importBatchId "
                + "from importBatch where userId =?";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setInt(1, userId);
        preStatement.execute();
        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            return result.getInt("importBatchId");
        }
        return 0;
    }
    
//    get max importBatchId
    public int getMaxImpBatchId(int userId) throws SQLException {
        String sql = "select max(importBatchId) as number "
                + "from importBatch where userId =?";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setInt(1, userId);
        preStatement.execute();
        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            return result.getInt("number");
        }
        return 0;
    }
    
    //    get list importId by userId
    public ObservableList<Integer> getListImpId(int userId) throws SQLException {
        ObservableList<Integer> oList = FXCollections.observableArrayList();
        String sql = "select * from importBatch "
                + "where userId =?";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setInt(1, userId);
        preStatement.execute();
        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            oList.add(result.getInt("importBatchId"));
        }
        return oList;
    }
    
//    public static void main(String[] args) throws SQLException {
////        ObservableList<ImportBatch> list = new ImportBatchModifier().getImpBatchInfo(10001);
////        list.forEach((t) -> {
////            System.out.println(t.getDate());
////        });
//
//        int tes = new ImportBatchModifier().getImpId(10000);
//        System.out.println(tes);
//    }
}
