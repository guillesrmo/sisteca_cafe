/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Conexion.Conexion;
import Entity.Entity_Almacen;
import Entity.Entity_Documento;
import Entity.Entity_Empresa;
import Entity.Entity_Sucursal;
import Function.Function_ShowMessageDialog;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;

/**
 *
 * @author pro
 */
public class Controller_Escritorio {
    private Conexion cxn;


    /*private generador_codigo generador_id;
    Function_ShowMessageDialog mensaje = new Function_ShowMessageDialog();*/
    public Controller_Escritorio() {
        cxn = new Conexion();
    }
    
    public void cbx_empresa(JComboBox<Entity_Empresa> empresa) {
        String sent = "SELECT * FROM empresa order by empresa_id  asc";
        try {
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sent);
            while (rs.next()) {
                empresa.addItem(new Entity_Empresa(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
                         rs.getString(7), rs.getBoolean(8)));
            }
            cxn.desconectar();
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }
    public void cbx_sucursal(JComboBox<Entity_Sucursal> sucursal) {
        String sent = "SELECT * FROM [sucursal] order by sucursal_id  asc";
        try {
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sent);
            while (rs.next()) {
                sucursal.addItem(new Entity_Sucursal(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
                         rs.getString(7), rs.getString(8)));
            }
            cxn.desconectar();
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }
    public void cbx_almacen(JComboBox<Entity_Almacen> almacen) {
        String sent = "SELECT * FROM almacen order by almacen_id  asc";
        try {
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sent);
            while (rs.next()) {
                almacen.addItem(new Entity_Almacen(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
            cxn.desconectar();
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }
    
}
