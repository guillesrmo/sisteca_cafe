/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config_Class;

/**
 *
 * @author pro
 */
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class ModeloTabla extends DefaultTableModel {
 
 public boolean isCellEditable(int row, int column) {
        if(column==0){
         return false;
        }else{
         return true;
        }
    }
}