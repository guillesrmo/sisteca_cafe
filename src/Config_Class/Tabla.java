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
import java.awt.Rectangle;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class Tabla extends JTable {
 
 public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
        if(columnIndex==0){
         super.changeSelection(rowIndex, columnIndex+1, toggle, extend);
        }else{
         super.changeSelection(rowIndex, columnIndex, toggle, extend);
        }
    }
}