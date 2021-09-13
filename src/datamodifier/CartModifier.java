/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodifier;

import static connection.UseDataBase.connect;
import data.Cart;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author sa
 */
public class CartModifier {

    public ObservableList<Cart> viewProduct(int id) throws SQLException {
        ObservableList<Cart> listCart = FXCollections.observableArrayList();
        String sql = "select * from goToCart where billId =?";

        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setInt(1, id);
        preStatement.execute();

        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            listCart.add(new Cart(result.getInt("billId"), result.getInt("productId"),
                    result.getString("productName"), result.getInt("quantity"), result.getString("unit"),
                    result.getDouble("price"), result.getDouble("amount")));
        }
        return listCart;
    }
    
    public int getNumberProduct(int id) throws SQLException{
        String sql = "select count(*) as number "
                + "from goToCart where billId =?";

        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setInt(1, id);
        preStatement.execute();

        ResultSet result = preStatement.getResultSet();
        
        while(result.next()){
            return result.getInt("number");
        }
        return 0;
    }

    public static void main(String[] args) throws SQLException {
//        ObservableList<Cart> list = new CartModifier().viewProduct(50055);
//        list.forEach((t) -> {
//            System.out.println(t.getProductName());
//        });
        
        System.out.println(new CartModifier().getNumberProduct(333));
    }
}
