/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author sa
 */
public class testDate {
    public static void main(String[] args) {
        LocalDateTime local = LocalDateTime.now();
        DateTimeFormatter fDate = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        System.out.println(fDate.format(local));
    }
   
}
