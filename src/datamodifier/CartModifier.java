/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamodifier;

import connection.UseDataBase;
import data.Cart;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author sa
 */
public class CartModifier extends UseDataBase {

    public ObservableList<Cart> viewProduct(int billId) throws SQLException {
        ObservableList<Cart> listCart = FXCollections.observableArrayList();
        String sql = "select * from goToCart where billId =?";

        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setInt(1, billId);
        preStatement.execute();

        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            listCart.add(new Cart(result.getInt("billId"), result.getInt("productId"),
                    result.getString("productName"), result.getInt("quantity"), result.getString("unit"),
                    result.getDouble("price"), result.getDouble("amount")));
        }
        return listCart;
    }

    public Cart getCartInfo(int billId, int productId) throws SQLException {
        Cart cart = new Cart();
        String sql = "select * from goToCart "
                + "where billId = ? and productId = ?";
        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setInt(1, billId);
        preStatement.setInt(2, productId);
        preStatement.execute();
        ResultSet result = preStatement.getResultSet();
        while (result.next()) {
            cart.setBillId(result.getInt("billId"));
            cart.setProductId(result.getInt("productId"));
            cart.setProductName(result.getString("productName"));
            cart.setQuantity(result.getInt("quantity"));
            cart.setUnit(result.getString("unit"));
            cart.setPrice(result.getDouble("price"));
            cart.setAmount(result.getDouble("amount"));
        }
        return cart;
    }

    public int getNumberProduct(int billId) throws SQLException {
        String sql = "select count(*) as number "
                + "from goToCart where billId =?";

        PreparedStatement preStatement = connect().prepareStatement(sql);
        preStatement.setInt(1, billId);
        preStatement.execute();

        ResultSet result = preStatement.getResultSet();

        while (result.next()) {
            return result.getInt("number");
        }
        return 0;
    }

    public boolean exportCartInfo(int billId, int userId) throws SQLException, FileNotFoundException, IOException {
        String sql = "select * from goToCart where billId = ?";
        PreparedStatement statement = connect().prepareStatement(sql);
        statement.setInt(1, billId);
        statement.execute();
        ResultSet result = statement.getResultSet();

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet(billId + "");

        XSSFFont fontTitle = workbook.createFont();
        fontTitle.setBold(true);
        fontTitle.setFontHeight(10);

        XSSFFont fontRow1 = workbook.createFont();
        fontRow1.setFontHeight(10);
        fontRow1.setBold(true);

        XSSFFont fontMain = workbook.createFont();
        fontMain.setBold(true);
        fontMain.setFontHeight(14);
        fontMain.setColor(IndexedColors.RED.getIndex());

        XSSFCellStyle styleTitle = workbook.createCellStyle();
        styleTitle.setFont(fontTitle);
        styleTitle.setAlignment(HorizontalAlignment.CENTER);

        XSSFCellStyle styleRow2 = workbook.createCellStyle();
        styleRow2.setBorderLeft(BorderStyle.THIN);
        styleRow2.setBorderRight(BorderStyle.THIN);
        styleRow2.setBorderTop(BorderStyle.THIN);
        styleRow2.setBorderBottom(BorderStyle.THIN);

        XSSFCellStyle styleRow1 = workbook.createCellStyle();
        styleRow1.setFont(fontRow1);
        styleRow1.setBorderLeft(BorderStyle.THIN);
        styleRow1.setBorderRight(BorderStyle.THIN);
        styleRow1.setBorderTop(BorderStyle.THIN);
        styleRow1.setBorderBottom(BorderStyle.THIN);
        styleRow1.setAlignment(HorizontalAlignment.CENTER);

        XSSFCellStyle styleMain = workbook.createCellStyle();
        styleMain.setFont(fontMain);
        styleMain.setAlignment(HorizontalAlignment.CENTER);
//        styleMain.setBorderBottom(BorderStyle.THIN);
//        styleMain.setBorderLeft(BorderStyle.THIN);
//        styleMain.setBorderRight(BorderStyle.THIN);
//        styleMain.setBorderTop(BorderStyle.THIN);
        styleMain.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());

        XSSFRow row = spreadsheet.createRow(1);
        XSSFCell cell = row.createCell(1);
        cell.setCellValue("Invoice Details");
        cell.setCellStyle(styleMain);

        row = spreadsheet.createRow(3);
        cell = row.createCell(1);
        cell.setCellValue("100 QL1A, Ninh Kieu, Can Tho. Hotline: 0948 00 0949");
        cell.setCellStyle(styleTitle);

        //MEARGING CELLS 
        //this statement for merging cells
        spreadsheet.addMergedRegion(
                new CellRangeAddress(
                        1, //first row (0-based)
                        1, //last row (0-based)
                        1, //first column (0-based)
                        6 //last column (0-based)
                )
        );

        spreadsheet.addMergedRegion(
                new CellRangeAddress(
                        3, //first row (0-based)
                        3, //last row (0-based)
                        1, //first column (0-based)
                        6 //last column (0-based)
                )
        );
        
        spreadsheet.addMergedRegion(
                new CellRangeAddress(
                        5, //first row (0-based)
                        5, //last row (0-based)
                        1, //first column (0-based)
                        2 //last column (0-based)
                )
        );
        
        spreadsheet.addMergedRegion(
                new CellRangeAddress(
                        6, //first row (0-based)
                        6, //last row (0-based)
                        1, //first column (0-based)
                        2 //last column (0-based)
                )
        );
        
        spreadsheet.addMergedRegion(
                new CellRangeAddress(
                        7, //first row (0-based)
                        7, //last row (0-based)
                        1, //first column (0-based)
                        2 //last column (0-based)
                )
        );
        
        LocalDateTime local = LocalDateTime.now();
        DateTimeFormatter fDate = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        
        row = spreadsheet.createRow(5);
        cell = row.createCell(1);
        cell.setCellValue("Invoice No: " + billId);
        
        row = spreadsheet.createRow(6);
        cell = row.createCell(1);
        cell.setCellValue("Invoice Date: " + fDate.format(local));
        
        row = spreadsheet.createRow(7);
        cell = row.createCell(1);
        cell.setCellValue("Memeber No: " + userId);

//        XSSFRow row = spreadsheet.createRow(1);
//        XSSFCell cell;
        row = spreadsheet.createRow(9);
        cell = row.createCell(1);
        cell.setCellValue("PRODUCT ID");
        cell.setCellStyle(styleRow1);

        cell = row.createCell(2);
        cell.setCellValue("PRODUCT NAME");
        cell.setCellStyle(styleRow1);

        cell = row.createCell(3);
        cell.setCellValue("UNIT");
        cell.setCellStyle(styleRow1);

        cell = row.createCell(4);
        cell.setCellValue("QUANTITY");
        cell.setCellStyle(styleRow1);

        cell = row.createCell(5);
        cell.setCellValue("PRICE");
        cell.setCellStyle(styleRow1);

        cell = row.createCell(6);
        cell.setCellValue("AMOUNT");
        cell.setCellStyle(styleRow1);

        int i = 10;
        int max = 0;
        
        while (result.next()) {
            row = spreadsheet.createRow(i);
            cell = row.createCell(1);
            cell.setCellValue(result.getInt("productId"));
            cell.setCellStyle(styleRow2);

            cell = row.createCell(2);
            cell.setCellValue(result.getString("productName"));
            cell.setCellStyle(styleRow2);

            cell = row.createCell(3);
            cell.setCellValue(result.getString("unit"));
            cell.setCellStyle(styleRow2);

            cell = row.createCell(4);
            cell.setCellValue(result.getInt("quantity"));
            cell.setCellStyle(styleRow2);

            cell = row.createCell(5);
            cell.setCellValue(result.getDouble("price"));
            cell.setCellStyle(styleRow2);

            cell = row.createCell(6);
            cell.setCellValue(result.getDouble("amount"));
            cell.setCellStyle(styleRow2);

            i++;
            max += result.getDouble("amount");
        }
        
        row = spreadsheet.createRow(i + 1);
        cell = row.createCell(5);
        cell.setCellValue("TOTAL");
        cell.setCellStyle(styleRow1);
        cell = row.createCell(6);
        cell.setCellValue(max);
        cell.setCellStyle(styleRow1);

        FileOutputStream out = new FileOutputStream(new File("/home/sa/Desktop/OrderDetail.xlsx"));
        workbook.write(out);
        out.close();
        return true;
    }

//    public static void main(String[] args) throws SQLException {
////        ObservableList<Cart> list = new CartModifier().viewProduct(50055);
////        list.forEach((t) -> {
////            System.out.println(t.getProductName());
////        });
//        
//        System.out.println(new CartModifier().getNumberProduct(333));
//    }
}
