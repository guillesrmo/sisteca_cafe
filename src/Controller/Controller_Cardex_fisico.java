package Controller;

import Conexion.Conexion;
import Config_Class.Render;
import Entity.Entity_Almacen;
import Entity.Entity_Compra;
import Entity.Entity_Compra_Producto;
import Entity.Entity_Documento;
import Entity.Entity_Moneda;
import Entity.Entity_Persona;
import Entity.Entity_Producto;
import Entity.Entity_Producto_Presentacion;
import Entity.Entity_Tipo_Comprobante;
import Entity.Entity_Tipo_afectacion;
import Entity.Entity_Venta;
import Entity.Entity_Venta_Producto;
import Function.Function_Component;
import Function.Function_Key_Event;
import Function.Function_ShowMessageDialog;
import View.View_Escritorio;
import View.View_Salida_producto;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Caret;
import javax.swing.text.MaskFormatter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author memo
 */
public class Controller_Cardex_fisico {

    private Conexion cxn;
    
    public Controller_Cardex_fisico() {

        cxn = new Conexion();
    }

   

    //calcular el número de veces que se repite un carácter en un String
    
   
    public void tabla_producto(JTable producto,String buscar ) {
        try {
            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String sSQL1 = "select producto_id,producto_cod,producto_descrip from producto  where producto_cod like '%"+buscar+"%' order by producto_id asc";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"","ID", "COD_BARRA", "DESCRIPCIÒN"});
            int contador=0;
            while (rs.next()) {
            contador++;
                model.addRow(new Object[]{contador,rs.getString(1), rs.getString(2), rs.getString(3)});
            }
            cxn.desconectar();
            producto.setModel(model);
            Function_Component.JTable(producto);
            producto.getColumnModel().getColumn(0).setCellRenderer(producto.getTableHeader().getDefaultRenderer());
            producto.getColumnModel().getColumn(0).setMaxWidth(20);
            producto.getColumnModel().getColumn(0).setMinWidth(20);
            producto.getColumnModel().getColumn(0).setPreferredWidth(20);
            /*tabla_igv.getColumnModel().getColumn(1).setMaxWidth(0);
            tabla_igv.getColumnModel().getColumn(1).setMinWidth(0);
            tabla_igv.getColumnModel().getColumn(1).setPreferredWidth(0);
            tabla_igv.getColumnModel().getColumn(3).setMaxWidth(0);
            tabla_igv.getColumnModel().getColumn(3).setMinWidth(0);
            tabla_igv.getColumnModel().getColumn(3).setPreferredWidth(0);*/

            producto.setDragEnabled(false);
            producto.getTableHeader().setReorderingAllowed(false);
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }

    public String table_lote(JTable tabla_lote, String id_producto) {
        String respuesta = "";
        try {
            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String sSQL1 = "select(select top 1 det_com_pro_id_compra from detalle_compra_producto where det_com_pro_id_producto='" + id_producto + "') id_compra,det_com.det_com_pro_fecha_venc,\n"
                    + "det_com.det_com_pro_cod_lote,\n"
                    + "((ISNULL((select  sum(dt_com.det_com_pro_cantidad*pre.present_cantidad) from producto pro\n"
                    + "inner join  producto_presentacion pre\n"
                    + "on pre.present_cod_producto=pro.producto_id\n"
                    + "inner join detalle_compra_producto dt_com\n"
                    + "on dt_com.det_com_pro_id_producto=pre.present_id\n"
                    + "where pro.producto_id=(select top 1 present_cod_producto from producto_presentacion  where present_id='" + id_producto + "')  and dt_com.det_com_pro_cod_lote=det_com.det_com_pro_cod_lote\n"
                    + "group by dt_com.det_com_pro_cod_lote),0))-(ISNULL((select  sum(dt_ven.det_ven_pro_cantidad*pre.present_cantidad) from producto pro\n"
                    + "inner join  producto_presentacion pre\n"
                    + "on pre.present_cod_producto=pro.producto_id\n"
                    + "inner join detalle_venta_producto dt_ven\n"
                    + "on dt_ven.det_ven_pro_id_producto=pre.present_id\n"
                    + "where pro.producto_id=(select top 1 present_cod_producto from producto_presentacion  where present_id='" + id_producto + "')  and dt_ven.det_ven_pro_cod_lote=det_com.det_com_pro_cod_lote\n"
                    + "group by dt_ven.det_ven_pro_cod_lote),0)))as STOCK_LOTE, sum(present.present_prec_venta) from\n"
                    + "producto produc\n"
                    + "right JOIN producto_presentacion present\n"
                    + "on present.present_cod_producto =produc.producto_id\n"
                    + "right join detalle_compra_producto det_com\n"
                    + "on det_com.det_com_pro_id_producto=present.present_id\n"
                    + "where produc.producto_id=(select top 1 present_cod_producto from producto_presentacion  where present_id='" + id_producto + "') \n"
                    + "group by produc.producto_id,det_com.det_com_pro_cod_lote,det_com.det_com_pro_fecha_venc";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"ID", "FECHA", "COD_LOTE", "CANTIDAD"});
            /* while (rs.next()) {

                   System.out.println("Controller.Controller_Venta.table_lote()"+rs.getString(1));
                }*/
            // if (rs.next()) {

            while (rs.next()) {

                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)});
            }

            /* } else {
                respuesta = "ERROR";
            }*/
            cxn.desconectar();
            tabla_lote.setModel(model);
            Function_Component.JTable(tabla_lote);
            tabla_lote.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla_lote.getColumnModel().getColumn(0).setMinWidth(0);
            tabla_lote.getColumnModel().getColumn(0).setPreferredWidth(0);
            /*tabla_igv.getColumnModel().getColumn(1).setMaxWidth(0);
            tabla_igv.getColumnModel().getColumn(1).setMinWidth(0);
            tabla_igv.getColumnModel().getColumn(1).setPreferredWidth(0);
            tabla_igv.getColumnModel().getColumn(3).setMaxWidth(0);
            tabla_igv.getColumnModel().getColumn(3).setMinWidth(0);
            tabla_igv.getColumnModel().getColumn(3).setPreferredWidth(0);*/

            tabla_lote.setDragEnabled(false);
            tabla_lote.getTableHeader().setReorderingAllowed(false);
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        return respuesta;
    }

    
}
