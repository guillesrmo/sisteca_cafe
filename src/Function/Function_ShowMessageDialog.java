/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Function;

import Config_Class.ErroresdeConexion;
import java.awt.Component;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author memo
 */
public class Function_ShowMessageDialog {
    
    public static void ShowMessageDialogadd(Component componente){
        ImageIcon icon = new ImageIcon("src\\Icon\\icon_ok.png");
        JOptionPane.showMessageDialog(componente, "SE REGISTRO EXITOSAMENTE","MENSAJE", JOptionPane.PLAIN_MESSAGE,icon);
    }
    public static void ShowMessageDialogupdate(Component componente){
        ImageIcon icon = new ImageIcon("src\\Icon\\icon_update.png");
        JOptionPane.showMessageDialog(componente, "SE EDITO EXITOSAMENTE","MENSAJE", JOptionPane.PLAIN_MESSAGE,icon);
    }
    public static void ShowMessageDialogdelete(Component componente){
        ImageIcon icon = new ImageIcon("src\\Icon\\icon_delete.png");
        JOptionPane.showMessageDialog(componente, "SE ELIMINO EXITOSAMENTE","MENSAJE", JOptionPane.PLAIN_MESSAGE,icon);
    }
    public static int ShowMessageDialogdeleteconfirmacion(Component componente){
        ImageIcon icon = new ImageIcon("src\\Icon\\icon_delete.png");
        int confirmacion = JOptionPane.showConfirmDialog(componente, "ESTA SEGURO DE ELIMINAR?", "ALERTA", 2,JOptionPane.PLAIN_MESSAGE, icon);
        
        return confirmacion;
    }
    public static  void ShowMessageDialogvalidacion(Component componente,String mensaje){
        ImageIcon icon = new ImageIcon("src\\Icon\\icon_warning.png");
        JOptionPane.showMessageDialog(componente, mensaje, "ALERTA", JOptionPane.PLAIN_MESSAGE, icon);
        
    }
    public static void ShowMessageDialogwarning(Component componente,SQLException ex){
        
            ImageIcon icon = new ImageIcon("src\\Icon\\icon_warning.png");
            JOptionPane.showMessageDialog(componente, ErroresdeConexion.MessageError(ex.getErrorCode(),ex.getMessage()),"ALERTA", JOptionPane.PLAIN_MESSAGE,icon);
        
        
        
    }
    
    public static void txtvalidacionnumero(java.awt.event.KeyEvent evt) {                                         
        char car = evt.getKeyChar();
        if((car<'0' || car>'9') ) evt.consume();
        
    }
    
}
