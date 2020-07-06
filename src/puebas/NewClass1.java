/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puebas;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

/**
 *
 * @author User
 */
public class NewClass1 {
    public static void main(String[] args) {
        try {
            LocalTime ingreso = LocalTime.parse("07:00");
            LocalTime salida  = LocalTime.parse("23:10");
            
          //  LocalTime hora = LocalTime.parse("09:00");

            int minutes = (int) ChronoUnit.MINUTES.between(ingreso, salida);
        //    int minutes1 = (int) ChronoUnit.MINUTES.between(ingreso,hora);
       //     System.out.println(""+minutes1);
            if(minutes > (60 * 7)) {
                System.out.println("DJT");
            }else{
                System.out.println("NO DJT");
            }
        } catch (Exception e) {
        }
            
      
    }
}
