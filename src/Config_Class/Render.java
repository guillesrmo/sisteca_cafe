/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Config_Class;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;

public class Render extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
      

        if (value instanceof JButton) {
            JButton btn = (JButton) value;
            if (isSelected) {
                btn.setForeground(new java.awt.Color(0, 0, 0));

                btn.setBackground(new java.awt.Color(255, 0, 0));
            } else {
                btn.setForeground(table.getForeground());
                btn.setBackground(UIManager.getColor("Button.background"));
                btn.setBorder(null);
            }

            return btn;
        } 

       

       

        /*  if( value instanceof Integer )
        {
            Integer amount = (Integer) value;
            if( amount.intValue() < 0 )
            {
                cell.setBackground(Color.RED);
                cell.setForeground(Color.WHITE);
            }
            else
            {
             if(amount.intValue()>70000){
              cell.setBackground(Color.GREEN);
                    cell.setForeground(Color.WHITE);
             }
             else{
              cell.setBackground(Color.WHITE);
              cell.setForeground(Color.BLACK);
             }
            }
        }
        return cell;
         */
 /* int numero = (Integer) table.getValueAt(row, 7);

        if (numero >= 10) {
            setBackground(Color.GREEN);
            setForeground(Color.BLACK);
        } else if (numero >= 5 && numero < 10) {
            setBackground(Color.YELLOW);
            setForeground(Color.BLACK);
        } else {
                setBackground(Color.RED);
            setForeground(Color.BLACK);
        }*/
       /* String numero = table.getValueAt(row, 11).toString();

        if (numero.equals("")) {
            setBackground(Color.BLACK);
            setForeground(Color.BLACK);
        } else if (numero.equals(null)) {
            setBackground(Color.YELLOW);
            setForeground(Color.BLACK);
        } else if (numero.length() >= 2) {
            setBackground(Color.RED);
            setForeground(Color.BLACK);
        } else {
            setBackground(Color.WHITE);
            setForeground(Color.BLACK);
        }*/
        /* if ((table.getValueAt(row,7)).equals("9"))
        {
            this.setOpaque(true);
            this.setBackground(Color.ORANGE);
            this.setForeground(Color.BLACK);
        } else{
            if ((table.getValueAt(row,7)).equals("7"))
            {
                this.setOpaque(true);
                this.setBackground(Color.GREEN);
                this.setForeground(Color.BLACK);
            }
            }*/
 /*    if (column == 6 || column == 2){ //EL NUMERO DE LA COLUMNA QUE DESEAMOS PINTAR
    setBackground(new Color(9, 187, 232));
   }
   else{ 
    setBackground(new Color(255, 255, 255));//EL COLOR DEL RESTO DE LAS COLUMNAS
   }*/

        if (value instanceof JCheckBox) {
            JCheckBox ch = (JCheckBox) value;
            return ch;
        }

        // return cell;
        return super.getTableCellRendererComponent(table, value, isSelected,
                hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
    }
}
