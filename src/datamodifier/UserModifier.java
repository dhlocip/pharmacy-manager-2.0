/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodifier;

import connection.UseDataBase;
import data.Users;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author sa
 */
public class UserModifier extends UseDataBase {
    
    //    delete user by userId
    public boolean deleteByUserId(int userId) throws SQLException{
        String sql = "delete from users "
                + "where userId = ?";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setInt(1, userId);
        preStatement.execute();
        return true;
    }
    
    public boolean insertInto(Users user) throws SQLException{
        String sql = "insert into users "
                + "values(?,?,?,?,?,?,?,?,?)";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setString(1, user.getUserName());
        preStatement.setString(2, user.getPassword());
        preStatement.setString(3, user.getPhone());
        preStatement.setString(4, user.getFullName());
        preStatement.setString(5, user.getGender());
        preStatement.setString(6, user.getDateOfBirth());
        preStatement.setString(7, user.getAddress());
        preStatement.setString(8, user.getPosition());
        preStatement.setString(9, user.getEmail());
        preStatement.execute();
        return true;
    }
    
    public boolean updateUser(Users users) throws SQLException{
        String sql = "update users "
                + "set phone = ?, fullName = ?, gender = ?, dateOfBirth = ?, address = ?, position = ?, email = ? "
//                + "set phone = ?, fullName = ?, gender = ?, dateOfBirth = convert(varchar(10),?, 126), address = ?, position = ? "
                + "where userId =?";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setString(1, users.getPhone());
        preStatement.setString(2, users.getFullName());
        preStatement.setString(3, users.getGender());
        preStatement.setString(4, users.getDateOfBirth());
        preStatement.setString(5, users.getAddress());
        preStatement.setString(6, users.getPosition());
        preStatement.setString(7, users.getEmail());
        preStatement.setInt(8, users.getUserId());
        preStatement.executeUpdate();
        return true;
    }
    
    public boolean updatePassword(int userId, String password) throws SQLException{
        String sql = "update users "
                + "set password = ? "
                + "where userId =?";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setString(1, password);
        preStatement.setInt(2, userId);
        preStatement.executeUpdate();
        return true;
    }
    
    public ObservableList<Users> getInfo() throws SQLException{
        ObservableList<Users> oList = FXCollections.observableArrayList();
        String sql = "select * from users";
        
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.execute();
        
        ResultSet result = preStatement.getResultSet();
        while(result.next()){
            oList.add(new Users(result.getInt("userId"), result.getString("userName"), 
                        result.getString("password"), result.getString("phone"), result.getString("fullName"),
                        result.getString("gender"), result.getString("dateOfBirth"), result.getString("address"), 
                        result.getString("position"), result.getString("email")));
        }
        return oList;
    }
    
    public Users getUser(String userName, String pass) throws SQLException{
        String sql = "select * from users "
                + "where userName = ? and password = ?";
        
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setString(1, userName);
        preStatement.setString(2, pass);
        preStatement.execute();
        
        ResultSet result = preStatement.getResultSet();
        while(result.next()){
            return new Users(result.getInt("userId"), result.getString("userName"), 
                        result.getString("password"), result.getString("phone"), result.getString("fullName"),
                        result.getString("gender"), result.getString("dateOfBirth"), result.getString("address"), 
                        result.getString("position"), result.getString("email"));
        }
        return null;
    }
    
    public Users getUser(int userId) throws SQLException{
        String sql = "select * from users "
                + "where userId = ?";
        
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setInt(1, userId);
        preStatement.execute();
        
        ResultSet result = preStatement.getResultSet();
        while(result.next()){
            return new Users(result.getInt("userId"), result.getString("userName"), 
                        result.getString("password"), result.getString("phone"), result.getString("fullName"),
                        result.getString("gender"), result.getString("dateOfBirth"), result.getString("address"), 
                        result.getString("position"), result.getString("email"));
        }
        return null;
    }
    
    public boolean signIn(String userName, String pass) throws SQLException{
        String sql = "select * from users "
                + "where userName = ? and password = ?";
        
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setString(1, userName);
        preStatement.setString(2, pass);
        preStatement.execute();
        
        ResultSet result = preStatement.getResultSet();
        while(result.next()){
            return true;
        }
        return false;
    }
    
    public boolean matchUserName(String userName) throws SQLException{
        String sql = "select * from users "
                + "where userName = ?";
        
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setString(1, userName);
        preStatement.execute();
        
        ResultSet result = preStatement.getResultSet();
        while(result.next()){
            return true;
        }
        return false;
    }
    
    public ObservableList<Users> searchByName(String name) throws SQLException{
        ObservableList<Users> oList = FXCollections.observableArrayList();
        
        String sql = "select * from users "
                + "where userName like '%" + name + "%' or fullName like '%" + name + "%'";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.execute();
        
        ResultSet result = preStatement.getResultSet();
        while(result.next()){
            oList.add(new Users(result.getInt("userId"), result.getString("userName"), result.getString("password"),
                    result.getString("phone"), result.getString("fullName"), result.getString("gender"),
                    result.getString("dateOfBirth"), result.getString("address"), result.getString("position"), result.getString("email")));
        }
        return oList;
    }
    
//    public static void main(String[] args) throws SQLException {
////        System.out.println(signIn("dhlocc", "arootroot"));
////        Users user = new UserModifier().getUser("dhloc.se3", "rootroot");
////        System.out.println(user.getUserId());
////        System.out.println(user.getUserName());
////        System.out.println(user.getPassword());
////        System.out.println(user.getPhone());
////        System.out.println(user.getFullName());
////        System.out.println(user.getGender());
////        System.out.println(user.getDateOfBirth());
////        System.out.println(user.getAddress());
////        System.out.println(user.getPosition());
////        if(user.getPosition().equalsIgnoreCase("member")){
////            System.out.println("match");
////        }else{
////            System.out.println("not match");
////        }
//        
//    }
}
