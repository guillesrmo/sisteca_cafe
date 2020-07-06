/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Conexion.Conexion;
import Entity.Entity_Moneda;
import Entity.Entity_Producto;
import Entity.Entity_Usuario;
import Function.Function_Component;
import Function.Function_ShowMessageDialog;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author pro
 */
public class Controller_Usuario {

    private Conexion cxn;


    /*private generador_codigo generador_id;
    Function_ShowMessageDialog mensaje = new Function_ShowMessageDialog();*/
    public Controller_Usuario() {
        cxn = new Conexion();
    }

    public String Add_Usuario(Entity_Usuario usuario) {
        String respueta = "";
        String sent = "EXEC	 [dbo].[InsertaTablausuario]\n"
                + "		@usuario_id = N'" + usuario.getUsuario_id() + "',\n"
                + "		@usuario_cod = N'" + usuario.getUsuario_cod() + "',\n"
                + "		@usuario_user = N'" + usuario.getUsuario_user() + "',\n"
                + "		@usuario_contrasena = N'" + usuario.getUsuario_contrasena() + "',\n"
                + "		@usuario_estado = N'" + usuario.getUsuario_estado() + "'";

        try {
            cxn.conectardb().createStatement().executeUpdate(sent);
            cxn.desconectar();
            respueta = "exito";
        } catch (SQLException ex) {
            respueta = "fallo";
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        return respueta;
    }

    public String Id_Usuario(String usuario) {
        String respueta = "";
        String sent = "DECLARE	@return_value int,\n"
                + "		@Resultado varchar(255)\n"
                + "EXEC	@return_value = [dbo].[increment_usario]\n"
                + "		@Id_Solicitud = N'" + usuario + "',\n"
                + "		@Resultado = @Resultado OUTPUT\n"
                + "SELECT	@Resultado as N'@Resultado'";

        try {
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sent);
            rs.next();
            respueta = rs.getString(1);
            cxn.desconectar();

        } catch (SQLException ex) {
            respueta = "fallo";
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        return respueta;
    }

    public String Edit_cod_usuario( String cod,String usuario) {
        String respueta = "";
        String sent = "update usuario set usuario_cod ='" + cod + "' where usuario_id='" + usuario + "'";

        try {
            cxn.conectardb().createStatement().executeUpdate(sent);
            cxn.desconectar();

        } catch (SQLException ex) {
            respueta = "fallo";
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        return respueta;
    }
    public String delete_usuario( String cod) {
        String respueta = "";
        String sent = "delete from usuario where usuario_id='"+cod+"'";

        try {
            cxn.conectardb().createStatement().executeUpdate(sent);
            cxn.desconectar();

        } catch (SQLException ex) {
            respueta = "fallo";
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        return respueta;
    }

    public void tabla_usuario(JTable usuario) {
        try {
            DefaultTableModel model = new DefaultTableModel() {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String sSQL1 = "SELECT usuario_id,usuario_cod,usuario_user FROM usuario  order by usuario_id asc";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"", "COD", "USUARIO"});
            while (rs.next()) {

                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3)});
            }
            cxn.desconectar();

            usuario.setModel(model);
            Function_Component.JTable(usuario);
            usuario.getColumnModel().getColumn(0).setMaxWidth(0);
            usuario.getColumnModel().getColumn(0).setMinWidth(0);
            usuario.getColumnModel().getColumn(0).setPreferredWidth(0);
            usuario.getColumnModel().getColumn(1).setMaxWidth(150);
            usuario.getColumnModel().getColumn(1).setMinWidth(150);
            usuario.getColumnModel().getColumn(1).setPreferredWidth(150);
            usuario.setDragEnabled(false);
            usuario.getTableHeader().setReorderingAllowed(false);
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }

    public List<Entity_Usuario> buscar_cod_usuario(String cod_user) {
        List<Entity_Usuario> usuario = new ArrayList<Entity_Usuario>();
        String sent = "select usuario_id,	usuario_cod	,usuario_user	,usuario_contrasena	,usuario_estado from usuario where usuario_cod='" + cod_user + "'";
        try {
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sent);
            rs.next();
            usuario.add(new Entity_Usuario(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getBoolean(5)));
            cxn.desconectar();
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        return usuario;
    }

}
