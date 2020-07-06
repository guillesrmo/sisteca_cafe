/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Function;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author memo
 */
public class Function_Key_Event {

    public static void Validar_Mayuscula(JTextField texto) {
        Function_Component.JTextField(texto);

        texto.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
            }

            ;
        @Override
            public void keyTyped(KeyEvent evt) {
                // if(Jtxtfield.getText().equals(""))
                char c = evt.getKeyChar();
                if (Character.isLowerCase(c)) {
                    String cad = ("" + c).toUpperCase();
                    c = cad.charAt(0);
                    evt.setKeyChar(c);
                }
            }

            @Override
            public void keyReleased(KeyEvent evt) {
                // if(Jtxtfield.getText().equals(""))
                char c = evt.getKeyChar();
                if (Character.isLowerCase(c)) {
                    String cad = ("" + c).toUpperCase();
                    c = cad.charAt(0);
                    evt.setKeyChar(c);
                }
            }
        });

    }

    public static void Validar_Mayuscula_JEditorPane(JEditorPane texto) {
        Function_Component.JEditorPane(texto);

        texto.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
            }

            ;
        @Override
            public void keyTyped(KeyEvent evt) {
                // if(Jtxtfield.getText().equals(""))
                char c = evt.getKeyChar();
                if (Character.isLowerCase(c)) {
                    String cad = ("" + c).toUpperCase();
                    c = cad.charAt(0);
                    evt.setKeyChar(c);
                }
            }

            @Override
            public void keyReleased(KeyEvent evt) {
                // if(Jtxtfield.getText().equals(""))
                char c = evt.getKeyChar();
                if (Character.isLowerCase(c)) {
                    String cad = ("" + c).toUpperCase();
                    c = cad.charAt(0);
                    evt.setKeyChar(c);
                }
            }
        });

    }

    public static void Validar_numeros(JTextField texto) {
        Function_Component.JTextField(texto);
        texto.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
            }

            ;
        @Override
            public void keyTyped(KeyEvent evt) {
                // if(Jtxtfield.getText().equals(""))
                char car = evt.getKeyChar();
                if ((car < '0' || car > '9') && (car < '.' || car > '.')) {
                    evt.consume();
                }

              

            }

            @Override
            public void keyReleased(KeyEvent evt) {
                // if(Jtxtfield.getText().equals(""))
                char car = evt.getKeyChar();
                if ((car < '0' || car > '9') && (car < '.' || car > '.')) {
                    evt.consume();
                }

            }
        });

    }

    public static void Validar_numeros_jtable(JTable texto) {
        texto.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent evt) {
                char car = evt.getKeyChar();
                if ((car < '.' || car > '.')) {
                    evt.consume();
                }
            }

            ;
        @Override
            public void keyTyped(KeyEvent evt) {
                // if(Jtxtfield.getText().equals(""))
                char car = evt.getKeyChar();
                if ((car < '.' || car > '.')) {
                    evt.consume();
                }

            }

            @Override
            public void keyReleased(KeyEvent evt) {
                // if(Jtxtfield.getText().equals(""))
                char car = evt.getKeyChar();
                if ((car < '.' || car > '.')) {
                    evt.consume();
                }

            }
        });

    }
    
    public static void JDateChooser (JDateChooser  JDateChooser ,JDateChooser  next_JDateChooser) {
        JDateChooser.getDateEditor().getUiComponent().addKeyListener(new java.awt.event.KeyListener() {
        @Override
        public void keyTyped(java.awt.event.KeyEvent e){
               
            try{
           
            if(Color.RED == JDateChooser.getDateEditor().getUiComponent().getForeground() ){
            System.out.println("No valido");
            }else{
            System.out.println("valido");
           
           
            }
         }catch(UnsupportedOperationException uop){
             System.out.println(uop.getMessage());
         }
       
            }
           
            @Override
            public void keyPressed(KeyEvent evt) {
                try{
                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            next_JDateChooser.getDateEditor().getUiComponent().requestFocusInWindow();
        }
                   
         }catch(UnsupportedOperationException uop){
             System.out.println(uop.getMessage());
         }
           
            }
            @Override
            public void keyReleased(KeyEvent e) {
                try{
                   
         }catch(UnsupportedOperationException uop){
             System.out.println(uop.getMessage());
         }
            }
        });

    }
    
    public static void JDateChooser_JComboBox (JDateChooser  JDateChooser ,JComboBox combo) {
        JDateChooser.getDateEditor().getUiComponent().addKeyListener(new java.awt.event.KeyListener() {
        @Override
        public void keyTyped(java.awt.event.KeyEvent e){
               
            try{
           
            if(Color.RED == JDateChooser.getDateEditor().getUiComponent().getForeground() ){
            System.out.println("No valido");
            }else{
            System.out.println("valido");
           
           
            }
         }catch(UnsupportedOperationException uop){
             System.out.println(uop.getMessage());
         }
       
            }
           
            @Override
            public void keyPressed(KeyEvent evt) {
                try{
                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                        combo.requestFocus();
        }
                   
         }catch(UnsupportedOperationException uop){
             System.out.println(uop.getMessage());
         }
           
            }
            @Override
            public void keyReleased(KeyEvent e) {
                try{
                   
         }catch(UnsupportedOperationException uop){
             System.out.println(uop.getMessage());
         }
            }
        });

    }

}
