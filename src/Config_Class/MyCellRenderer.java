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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class MyCellRenderer extends DefaultTableCellRenderer {

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {

        /*   String numero = (String) table.getValueAt(row, 23);
      /*  String numero = table.getValueAt(row, 23).toString();*/
 /*  System.out.println("a"+numero);
        if (numero=="PENDIENTE") {
            setBackground(new java.awt.Color(255, 102, 102));
            setForeground(Color.YELLOW);
        } else if (numero.equals(null)) {
            setBackground(Color.YELLOW);
            setForeground(Color.BLACK);
        } else if (numero.length() >= 2) {
            setBackground(new java.awt.Color(255, 102, 102));
            setForeground(Color.BLACK);
        } else {
            setBackground(Color.WHITE);
            setForeground(Color.BLACK);
        }*/
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        JComponent comp = (JComponent) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        Border border = BorderFactory.createMatteBorder(0, 0, 0,
                0, Color.BLACK);

        if (value instanceof JButton) {
            JButton btn = (JButton) value;
            if (isSelected) {
                btn.setForeground(new java.awt.Color(0, 0, 0));

                btn.setBackground(new java.awt.Color(255, 102, 102));
            } else {
                btn.setForeground(table.getForeground());
                btn.setBackground(UIManager.getColor("Button.background"));
                btn.setBorder(null);
            }

            return btn;
        } /*else if (value instanceof Integer) {
            Integer amount = (Integer) value;
            if (amount.intValue() < 0) {
                cell.setBackground(Color.RED);
                cell.setForeground(Color.WHITE);
                border = BorderFactory.createMatteBorder(0, 0, 0, 0, Color.yellow);
            } else {
                if (amount.intValue() > 7) {
                    cell.setBackground(Color.GREEN);
                     cell.setForeground(Color.WHITE);

                } else {
                    cell.setBackground(Color.WHITE);
                    cell.setForeground(Color.BLACK);

                }
            }

            return cell;

        } */ else if (value instanceof String) {

            String amount = (String) value;
            //System.out.println("Config_Class.MyCellRenderer.getTableCellRendererComponent()"+value);
            int consulta = amount.indexOf("https");

            if (amount == "") {
                cell.setBackground(Color.WHITE);
                cell.setForeground(Color.BLACK);
            } else {
                if (consulta == -1) {

                    cell.setBackground(Color.WHITE);
                    cell.setForeground(Color.BLACK);

                } else {
                    cell.setBackground(new java.awt.Color(255, 102, 102));
                    cell.setForeground(Color.WHITE);

                }
            }
            if (column == 13) {

                if (amount.equals("1")) {
                    cell.setBackground(new java.awt.Color(0, 102, 102));
                    cell.setForeground(Color.WHITE);

                }
                if (amount.equals("2")) {
                    cell.setBackground(new java.awt.Color(50, 102, 102));
                    cell.setForeground(Color.WHITE);

                }
                if (amount.equals("3")) {
                    cell.setBackground(new java.awt.Color(100, 102, 102));
                    cell.setForeground(Color.WHITE);

                }
                if (amount.equals("4")) {
                    cell.setBackground(new java.awt.Color(150, 102, 102));
                    cell.setForeground(Color.WHITE);

                }
                if (amount.equals("5")) {
                    cell.setBackground(new java.awt.Color(200, 102, 102));
                    cell.setForeground(Color.WHITE);

                }
                if (amount.equals("6")) {
                    cell.setBackground(new java.awt.Color(0, 0, 102));
                    cell.setForeground(Color.WHITE);

                }
                if (amount.equals("7")) {
                    cell.setBackground(new java.awt.Color(0, 50, 102));
                    cell.setForeground(Color.WHITE);

                }
                if (amount.equals("8")) {
                    cell.setBackground(new java.awt.Color(0, 100, 102));
                    cell.setForeground(Color.WHITE);

                }
                if (amount.equals("9")) {
                    cell.setBackground(new java.awt.Color(0, 150, 102));
                    cell.setForeground(Color.WHITE);

                }
                if (amount.equals("10")) {
                    cell.setBackground(new java.awt.Color(0, 200, 102));
                    cell.setForeground(Color.WHITE);

                }
                if (amount.equals("11")) {
                    cell.setBackground(new java.awt.Color(0, 0, 50));
                    cell.setForeground(Color.WHITE);

                }

            }
            if (amount.equals("OBSERVADO")) {
                setBackground(Color.RED);
                setForeground(Color.BLACK);

            }
            if (amount.equals("PENDIENTE")) {
                setBackground(Color.ORANGE);
                setForeground(Color.BLACK);

            }
            if (amount.equals("SINCRONIZADO")) {
                setBackground(new java.awt.Color(0, 255, 0));
                setForeground(Color.BLACK);

            }
            if (amount.equals("ENTREGADO")) {
                setBackground(new java.awt.Color(255, 255, 0));
                setForeground(Color.BLACK);

            }

            if (amount.equals("00000000")) {
                setBackground(Color.RED);
                setForeground(Color.BLACK);

            }
            
            if (amount.equals("DJT")) {
                setBackground(Color.RED);
                setForeground(Color.BLACK);

            }
            
            if (amount.equals("00:00")) {
                setBackground(Color.RED);
                setForeground(Color.BLACK);

            }
            
            if (amount.equals("NO ES CAE")) {
                setBackground(Color.RED);
                setForeground(Color.BLACK);

            }
           
            

            return cell;

        } else if (value instanceof JLabel) {

            JLabel lbl = (JLabel) value;
            System.out.println("" + lbl);
            // String amount = (String) value;

            /*   if (column == 13){
            
            if (amount.equals("1")) {
                cell.setBackground(new java.awt.Color(0, 102, 102));
                cell.setForeground(Color.WHITE);
                lbl.setOpaque(true);
                lbl.setBackground(new java.awt.Color(0, 102, 102));
                lbl.setForeground(Color.WHITE);

            }
            if (amount.equals("2")) {
                cell.setBackground(new java.awt.Color(50, 102, 102));
                cell.setForeground(Color.WHITE);

            }
            if (amount.equals("3")) {
                cell.setBackground(new java.awt.Color(100, 102, 102));
                cell.setForeground(Color.WHITE);

            }
            if (amount.equals("4")) {
                cell.setBackground(new java.awt.Color(150, 102, 102));
                cell.setForeground(Color.WHITE);

            }
            if (amount.equals("5")) {
                cell.setBackground(new java.awt.Color(200, 102, 102));
                cell.setForeground(Color.WHITE);

            }
            if (amount.equals("6")) {
                cell.setBackground(new java.awt.Color(0, 0, 102));
                cell.setForeground(Color.WHITE);

            }
            if (amount.equals("7")) {
                cell.setBackground(new java.awt.Color(0, 50, 102));
                cell.setForeground(Color.WHITE);

            }
            if (amount.equals("8")) {
                cell.setBackground(new java.awt.Color(0, 100, 102));
                cell.setForeground(Color.WHITE);

            }
            if (amount.equals("9")) {
                cell.setBackground(new java.awt.Color(0,150, 102));
                cell.setForeground(Color.WHITE);

            }
            if (amount.equals("10")) {
                cell.setBackground(new java.awt.Color(0, 200, 102));
                cell.setForeground(Color.WHITE);

            }
            if (amount.equals("11")) {
                cell.setBackground(new java.awt.Color(0, 0, 50));
                cell.setForeground(Color.WHITE);

            }}*/
            return lbl;

        } else if (hasFocus) {
            /* comp.setBorder(border);
         return comp;*/
        }/*else if (value instanceof String) {
            
            comp.setBorder(border);
         return comp;
        }*/


 /* if (column == 5 || column == 8) { //EL NUMERO DE LA COLUMNA QUE DESEAMOS PINTAR
            //setBackground(new Color(153, 255, 204));
            setForeground(Color.BLACK);

        } else {
            setBackground(new Color(255, 255, 255));//EL COLOR DEL RESTO DE LAS COLUMNAS
            setForeground(new Color(0, 0, 0));

        }*/
        if (column == 11) { //EL NUMERO DE LA COLUMNA QUE DESEAMOS PINTAR
            //setBackground(new Color(153, 255, 204));
            if (table.getValueAt(row, 11).equals("https://www.pse.pe/guia/b223baa9-3f00-4d18-aff1-4ab6b9fc3d27-42d23aa2-861d-4800-a7e9-852270333b62")) {
                cell.setBackground(Color.RED);
                cell.setForeground(Color.WHITE);
                setForeground(Color.BLACK);
            }

        } else {
            setBackground(new Color(255, 255, 255));//EL COLOR DEL RESTO DE LAS COLUMNAS
            setForeground(new Color(0, 0, 0));

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
