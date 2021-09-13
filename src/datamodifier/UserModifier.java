/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodifier;

import static connection.UseDataBase.connect;
import data.Users;
import java.sql.*;

/**
 *
 * @author sa
 */
public class UserModifier {
    
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
                        result.getString("gender"), result.getString("dateOfBirth"), result.getString("address"), result.getString("position"));
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
    
    
    public static void main(String[] args) throws SQLException {
//        System.out.println(signIn("dhlocc", "arootroot"));
        Users user = new UserModifier().getUser("dhloc.se3", "rootroot");
        System.out.println(user.getUserId());
        System.out.println(user.getUserName());
        System.out.println(user.getPassword());
        System.out.println(user.getPhone());
        System.out.println(user.getFullName());
        System.out.println(user.getGender());
        System.out.println(user.getDateOfBirth());
        System.out.println(user.getAddress());
        System.out.println(user.getPosition());
        if(user.getPosition().equalsIgnoreCase("member")){
            System.out.println("match");
        }else{
            System.out.println("not match");
        }
        
    }
}
