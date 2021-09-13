/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.*;

/**
 *
 * @author sa
 */
public abstract class UseDataBase {
    
    public static Connection connect() throws SQLException{
        return DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=pharmacydb", "sa", "uZ92hUQE");
    }
    
}
