/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Function;


import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

/**
 *
 * @author memo
 */
public class Function_Component {
    private static Font fuente = new Font("Consolas",1, 14);
    //Font fuente = new Font("Calibri",1, 14);
    public static final  void JTextField(JTextField JTextField){
        JTextField.setFont(fuente); 
        JTextField.setForeground(Color.BLACK);
        JTextField.setCaretColor(Color.RED);
        JTextField.setSelectionColor(Color.RED);
        
       
    
    }
    public static final  void JButton(JButton JButton){
        JButton.setFont(fuente); 
        JButton.setForeground(Color.BLACK);
        JButton.setBackground(Color.WHITE);
    
    }
    public static final  void JComboBox(JComboBox JComboBox){
        JComboBox.setFont(fuente); 
        JComboBox.setForeground(Color.BLACK);
        
    
    }
    public static final  void JLabel(JLabel texto){
        texto.setFont(fuente); 
        texto.setForeground(Color.BLACK);
    
    }
    public static final  void JTabbedPane(JTabbedPane texto){
        texto.setFont(fuente); 
        texto.setForeground(Color.BLACK);
    
    }
    public static final  void JToggleButton(JToggleButton JToggleButton){
        JToggleButton.setFont(fuente); 
        JToggleButton.setForeground(Color.BLACK);
    
    }
    
    public static final  void JTable(JTable JTable){
        JTable.getTableHeader().setFont(fuente);
        JTable.setFont(fuente); 
        JTable.setForeground(Color.BLACK);
        JTable.setRowHeight(35);
        
        
    
    }
    public static final  void JDialog(JDialog texto){
        texto.setFont(fuente); 
        texto.setForeground(Color.BLACK);
    
    }
    public static final  void JEditorPane(JEditorPane JTextField){
        JTextField.setFont(fuente); 
        JTextField.setForeground(Color.BLACK);
        JTextField.setCaretColor(Color.RED);
    
    }
    public static final  void JCheckBox(JCheckBox check){
        check.setFont(fuente); 
        check.setForeground(Color.BLACK);
        //check.setCaretColor(Color.RED);
    
    }
    public static final  void JDateChooser(JDateChooser JDateChooser){
        JDateChooser.setFont(fuente); 
        JDateChooser.setForeground(Color.BLACK);
        //check.setCaretColor(Color.RED);
    
    }
    
   /* public static final void JDateChooser(JDateChooser data){
        data=new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
    
    }*/
}
