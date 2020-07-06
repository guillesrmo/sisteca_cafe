/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puebas;

/**
 *
 * @author pro
 */
import static com.sun.javafx.fxml.expression.Expression.add;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class Principal extends JApplet {
    
    JProgressBar barraprogreso;
 int n=0;
 Timer t;
 
 public void init(){
  
  barraprogreso=new JProgressBar(0, 100);
  barraprogreso.setStringPainted(true);
  ActionListener accion=new ActionListener() {
   
   public void actionPerformed(ActionEvent e) {
    if(n<=100){
     barraprogreso.setValue(n);
     n=n+10;
    }else{
     t.stop();
     barraprogreso.setString("Termino");
    }
   }
  };
  t=new Timer(500, accion);
  JPanel p1=new JPanel();
  JButton iniciar=new JButton("Comenzar");
  iniciar.addActionListener(new ActionListener() {
   
   public void actionPerformed(ActionEvent arg0) {
    if(!t.isRunning()){
     t.start();
    }
   }
  });
  p1.add(iniciar);
  add(barraprogreso, BorderLayout.NORTH);
  add(p1, BorderLayout.CENTER);
  
 }
 
    public static void main(String[] args) {
        new Principal().init();
    }
   
}
