/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guia_remision;

import Comprobantes_E_SUNAT.CE_Guia;
import Comprobantes_E_SUNAT.CE_Guia1;
import Comprobantes_E_SUNAT.CE_RENIEC;
import Conexion.Conexion;
import Controller.Controller_Nubefact;
import java.sql.ResultSet;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author LLAMA.PE
 */
public class PruebaFE {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    /*    Conexion cxn=new Conexion();
        try {
            String sql="select [padron_telefono]\n" +
"  FROM [sistema_cafe].[dbo].[padron]";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sql);

            
            while (rs.next()) {
                if (rs.getString(1).length() >5) {
                    System.out.println(rs.getString(1));
                   
                    cp.apiConsume(rs.getString(1));
                }
                    
               
            }
        } catch (Exception e) {
        }*/
     
       /* CE_Guia1 cp = new CE_Guia1();
        cp.apiConsume();*/

         /* List<String> guia = new CE_Guia().apiConsume("166");
          for (int i = 0; i < guia.size(); i++) {
                System.out.println(""+guia.get(i));
                
            }*/

// TODO code application logic here
        CE_RENIEC cp = new CE_RENIEC();
        System.out.println("guia_remision.PruebaFE.main()"+ cp.apiConsume("04816065"));
    }
    
}
