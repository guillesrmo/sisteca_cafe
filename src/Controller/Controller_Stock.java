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
public class Controller_Stock {

    private Conexion cxn;

    public Controller_Stock() {

        cxn = new Conexion();
    }

    //calcular el número de veces que se repite un carácter en un String
    public void tabla_producto(JTable producto, String buscar1, String buscar2, String buscar3) {
        try {
            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String sSQL1 = "select producto_id,producto_cod,producto_descrip, ISNULL((SELECT\n"
                    + "     sum(det_comp.det_com_pro_cantidad *\n"
                    + "     present.present_cantidad)\n"
                    + "FROM\n"
                    + "     producto_presentacion present INNER JOIN producto produt ON present.present_cod_producto = produt.producto_id\n"
                    + "     INNER JOIN detalle_compra_producto det_comp ON present.present_id = det_comp.det_com_pro_id_producto\n"
                    + "	 inner join compra compr on compr.compra_id=det_comp.det_com_pro_id_compra\n"
                    + "WHERE\n"
                    + "     produt.producto_id =producto.producto_id    " + buscar2 + "),0)as entrada,isNULL(( SELECT sum(det_ven.det_ven_pro_cantidad*\n"
                    + "     present.present_cantidad ) AS total\n"
                    + "	 FROM\n"
                    + "     producto_presentacion present INNER JOIN producto produt ON present.present_cod_producto = produt.producto_id\n"
                    + "     INNER JOIN detalle_venta_producto det_ven ON present.present_id = det_ven.det_ven_pro_id_producto\n"
                    + "	 INNER JOIN venta vent on vent.venta_id=det_ven.det_ven_pro_id_venta\n"
                    + "	 WHERE\n"
                    + "     produt.producto_id =producto.producto_id    " + buscar3 + "),0) as salida,\n"
                    + "	 ( ISNULL((SELECT\n"
                    + "     sum(det_comp.det_com_pro_cantidad *\n"
                    + "     present.present_cantidad)\n"
                    + "FROM\n"
                    + "     producto_presentacion present INNER JOIN producto produt ON present.present_cod_producto = produt.producto_id\n"
                    + "     INNER JOIN detalle_compra_producto det_comp ON present.present_id = det_comp.det_com_pro_id_producto\n"
                    + "	 inner join compra compr on compr.compra_id=det_comp.det_com_pro_id_compra\n"
                    + "WHERE\n"
                    + "     produt.producto_id =producto.producto_id   " + buscar2 + ") ,0)-isNULL(( SELECT sum(det_ven.det_ven_pro_cantidad*\n"
                    + "     present.present_cantidad ) AS total\n"
                    + "	 FROM\n"
                    + "     producto_presentacion present INNER JOIN producto produt ON present.present_cod_producto = produt.producto_id\n"
                    + "     INNER JOIN detalle_venta_producto det_ven ON present.present_id = det_ven.det_ven_pro_id_producto\n"
                    + "	 INNER JOIN venta vent on vent.venta_id=det_ven.det_ven_pro_id_venta\n"
                    + "	 WHERE\n"
                    + "     produt.producto_id =producto.producto_id      " + buscar3 + "),0)  ) as total from producto " + buscar1 + "\n"
                    + "	 order by producto.producto_id asc";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"", "ID", "COD_BARRA", "DESCRIPCIÒN", "ENTRADA", "SALIDA", "TOTAL"});
            int contador = 0;
            while (rs.next()) {
                contador++;
                model.addRow(new Object[]{contador, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)});
            }
            cxn.desconectar();
            producto.setModel(model);
            Function_Component.JTable(producto);
            producto.getColumnModel().getColumn(0).setCellRenderer(producto.getTableHeader().getDefaultRenderer());
            producto.getColumnModel().getColumn(0).setMaxWidth(50);
            producto.getColumnModel().getColumn(0).setMinWidth(50);
            producto.getColumnModel().getColumn(0).setPreferredWidth(50);
            producto.getColumnModel().getColumn(1).setMaxWidth(50);
            producto.getColumnModel().getColumn(1).setMinWidth(50);
            producto.getColumnModel().getColumn(1).setPreferredWidth(50);
            producto.getColumnModel().getColumn(2).setMaxWidth(150);
            producto.getColumnModel().getColumn(2).setMinWidth(150);
            producto.getColumnModel().getColumn(2).setPreferredWidth(150);
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

    public void tabla_LOTE(JTable producto, String buscar1, String almacen1, String almacen2) {
        try {
            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String sSQL1 = "SELECT \n"
                    + "MAX(pro.producto_id) as id_producto,com.det_com_pro_cod_lote,\n"
                    + "com.det_com_pro_fecha_venc,\n"
                    + "max(cast(pro.producto_descrip as varchar(max))) as pro_descr,\n"
                    + "max(cast(fa.familia_descrip as varchar(max))) familia_descrip,\n"
                    + "ISNULL((SELECT\n"
                    + "sum(det_comp.det_com_pro_cantidad *\n"
                    + "present.present_cantidad)\n"
                    + "FROM\n"
                    + "producto_presentacion present INNER JOIN producto produt ON present.present_cod_producto = produt.producto_id\n"
                    + "INNER JOIN detalle_compra_producto det_comp ON present.present_id = det_comp.det_com_pro_id_producto\n"
                    + "inner join compra compr on compr.compra_id=det_comp.det_com_pro_id_compra\n"
                    + "WHERE\n"
                    + "det_comp.det_com_pro_cod_lote =com.det_com_pro_cod_lote AND det_comp.det_com_pro_fecha_venc=com.det_com_pro_fecha_venc and produt.producto_id=pro.producto_id "+almacen1+" ),0)as entrada,isNULL(( SELECT sum(det_ven.det_ven_pro_cantidad*\n"
                    + "present.present_cantidad ) AS total\n"
                    + "FROM\n"
                    + "producto_presentacion present INNER JOIN producto produt ON present.present_cod_producto = produt.producto_id\n"
                    + "INNER JOIN detalle_venta_producto det_ven ON present.present_id = det_ven.det_ven_pro_id_producto\n"
                    + "INNER JOIN venta vent on vent.venta_id=det_ven.det_ven_pro_id_venta\n"
                    + "WHERE\n"
                    + "det_ven.det_ven_pro_cod_lote =com.det_com_pro_cod_lote AND det_ven.det_ven_pro_fecha=com.det_com_pro_fecha_venc and produt.producto_id=pro.producto_id "+almacen2+"),0) as salida,\n"
                    + "( ISNULL((SELECT\n"
                    + "sum(det_comp.det_com_pro_cantidad *\n"
                    + "present.present_cantidad)\n"
                    + "FROM\n"
                    + "producto_presentacion present INNER JOIN producto produt ON present.present_cod_producto = produt.producto_id\n"
                    + "INNER JOIN detalle_compra_producto det_comp ON present.present_id = det_comp.det_com_pro_id_producto\n"
                    + "inner join compra compr on compr.compra_id=det_comp.det_com_pro_id_compra\n"
                    + "WHERE\n"
                    + "det_comp.det_com_pro_cod_lote =com.det_com_pro_cod_lote AND det_comp.det_com_pro_fecha_venc=com.det_com_pro_fecha_venc and produt.producto_id=pro.producto_id "+almacen1+") ,0)-isNULL(( SELECT sum(det_ven.det_ven_pro_cantidad*\n"
                    + "present.present_cantidad ) AS total\n"
                    + "FROM\n"
                    + "producto_presentacion present INNER JOIN producto produt ON present.present_cod_producto = produt.producto_id\n"
                    + "INNER JOIN detalle_venta_producto det_ven ON present.present_id = det_ven.det_ven_pro_id_producto\n"
                    + "INNER JOIN venta vent on vent.venta_id=det_ven.det_ven_pro_id_venta\n"
                    + "WHERE\n"
                    + "det_ven.det_ven_pro_cod_lote =com.det_com_pro_cod_lote  AND det_ven.det_ven_pro_fecha=com.det_com_pro_fecha_venc and produt.producto_id=pro.producto_id  "+almacen2+"),0)  ) as total  FROM  producto pro\n"
                    + "inner join producto_presentacion pre\n"
                    + "on pre.present_cod_producto=pro.producto_id\n"
                    + "inner join detalle_compra_producto com\n"
                    + "on com.det_com_pro_id_producto=pre.present_id\n"
                    + "inner join familia fa\n"
                    + "on pro.producto_familia=fa.familia_id\n"
                    + "group by com.det_com_pro_cod_lote,com.det_com_pro_fecha_venc,pro.producto_id\n"
                    + "order by max(pro.producto_id) asc";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"", "ID", "COD_LOTE", "FECHA VEN.", "DESCRIPCIÒN", "MARCA", "ENTRADA", "SALIDA", "TOTAL"});
            int contador = 0;
            while (rs.next()) {
                contador++;
                model.addRow(new Object[]{contador, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6), rs.getDouble(7), rs.getDouble(8)});
            }
            cxn.desconectar();
            producto.setModel(model);
            Function_Component.JTable(producto);
            producto.getColumnModel().getColumn(0).setCellRenderer(producto.getTableHeader().getDefaultRenderer());
            producto.getColumnModel().getColumn(0).setMaxWidth(50);
            producto.getColumnModel().getColumn(0).setMinWidth(5);
            producto.getColumnModel().getColumn(0).setPreferredWidth(50);
            producto.getColumnModel().getColumn(1).setMaxWidth(50);
            producto.getColumnModel().getColumn(1).setMinWidth(5);
            producto.getColumnModel().getColumn(1).setPreferredWidth(50);
            producto.getColumnModel().getColumn(3).setMaxWidth(150);
            producto.getColumnModel().getColumn(3).setMinWidth(155);
            producto.getColumnModel().getColumn(3).setPreferredWidth(150);
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

}
