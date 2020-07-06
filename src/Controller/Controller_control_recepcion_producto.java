package Controller;

import Conexion.Conexion;
import Config_Class.Render;
import Entity.Entity_Almacen;
import Entity.Entity_Compra;
import Entity.Entity_Compra_Producto;
import Entity.Entity_Documento;
import Entity.Entity_Equipo_colegio;
import Entity.Entity_Guia;
import Entity.Entity_Moneda;
import Entity.Entity_Persona;
import Entity.Entity_Producto;
import Entity.Entity_Producto_Presentacion;
import Entity.Entity_Tipo_Comprobante;
import Entity.Entity_Tipo_afectacion;
import Entity.Entity_Venta;
import Entity.Entity_Venta_Producto;
import Function.FixedColumnTable;
import Function.Function_Component;
import Function.Function_Key_Event;
import Function.Function_ShowMessageDialog;
import View.View_Escritorio;
import View.View_Salida_producto;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.datatransfer.DataFlavor;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.DropMode;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.TransferHandler;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
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
public class Controller_control_recepcion_producto {

    private Conexion cxn;
    private int getselection = 0;
    private int getselection_almacen = 0;
    private int contador_columna = 17;
    private boolean[] editable = {false, true, true, true, true, true, true, true, true,false, true, true, true, true, true, true, true, true,false, true, true, true, true, true, true, true, true};
    private boolean[] editable_almacen = {false, false, true, true, false, true};
    DefaultTableModel model;
    DefaultTableModel model_almacen;
    /*private generador_codigo generador_id;
    Function_ShowMessageDialog mensaje = new Function_ShowMessageDialog();*/
    public Controller_control_recepcion_producto() {
        cxn = new Conexion();
        //generador_id = new generador_codigo();
    }

   

    public void tabla(JTable tabla,JScrollPane a) {
       //  JTable tabla = new JTable(20, 10);
       // JTable table = new JTable(20, 10);



       
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel model_tabla = new DefaultTableModel(new Object[]{"DESCRIPCION", "CANTIDAD", "MARCA", "PRESENTACION",
            "NUMERO LOTE", "FECHA VENCIMIENTO", "FECHA PRODUCCION", "FECHA CARDEX","FACTURA","CANTIDAD","PROVEEDOR","ALMACEN","GUIA"}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return editable[column];
            }
        };
        try {
            String sSQL1 = "select \n" +
"max(cast(pro.producto_descrip as varchar(max))),max(cast(pro.producto_cantidad as varchar(max))),max(cast(fa.familia_descrip as varchar(max))),\n" +
"max(cast(pro_preset.present_peso as varchar(max))),det_compr.det_com_pro_cod_lote,det_compr.det_com_pro_fecha_venc,max(cast(det_compr.det_com_pro_fecha_produc as varchar(max)))  ,\n" +
"max(cast(compr.compra_fecha_ingreso_alm as varchar(max))),STUFF(\n" +
"    (select ' , '+(com.compra_serie+'-'+cast(com.compra_numero as varchar(max))) from detalle_compra_producto  det\n" +
"		inner join compra com\n" +
"		on com.compra_id=det.det_com_pro_id_compra\n" +
"		inner join producto_presentacion per\n" +
"		on per.present_id=det.det_com_pro_id_producto\n" +
"		where det.det_com_pro_cod_lote=det_compr.det_com_pro_cod_lote and per.present_cod_producto=pro.producto_id and  com.compra_fecha_ingreso_alm =compr.compra_fecha_ingreso_alm and det.det_com_pro_fecha_venc=det_compr.det_com_pro_fecha_venc\n" +
"group by com.compra_serie,com.compra_numero\n" +
"    FOR XML PATH ('')),\n" +
"1,2, '') ,\n" +
"sum(det_compr.det_com_pro_cantidad*present_cantidad),max(cast(per.persona_razon_social as varchar(max))),max(cast(alm.almacen_descrip as varchar(max))),  STUFF(\n" +
"    (select ' , '+gui.guia_decr from detalle_compra_producto  det\n" +
"		inner join compra com\n" +
"		on com.compra_id=det.det_com_pro_id_compra\n" +
"		inner join guia_compra gui\n" +
"		on gui.guia_compra=com.compra_id\n" +
"		inner join producto_presentacion per\n" +
"		on per.present_id=det.det_com_pro_id_producto\n" +
"		where det.det_com_pro_cod_lote=det_compr.det_com_pro_cod_lote and per.present_cod_producto=pro.producto_id and  com.compra_fecha_ingreso_alm =compr.compra_fecha_ingreso_alm and det.det_com_pro_fecha_venc=det_compr.det_com_pro_fecha_venc\n" +
"group by gui.guia_decr\n" +
"    FOR XML PATH ('')),\n" +
"1,2, '')\n" +
"from producto pro\n" +
"inner join familia fa\n" +
"on fa.familia_id=pro.producto_familia\n" +
"inner join producto_presentacion pro_preset\n" +
"on pro_preset.present_cod_producto=pro.producto_id\n" +
"inner join detalle_compra_producto det_compr\n" +
"on det_compr.det_com_pro_id_producto=pro_preset.present_id\n" +
"inner join compra compr\n" +
"on compr.compra_id=det_compr.det_com_pro_id_compra\n" +
"inner join persona per\n" +
"on per.persona_id=compr.compra_proveedor\n" +
"inner join almacen alm\n" +
"on alm.almacen_id=compr.compra_almacen\n" +
"inner join guia_compra guia_c\n" +
"on guia_c.guia_compra=compr.compra_id\n" +
"group by pro.producto_id,det_compr.det_com_pro_cod_lote,det_compr.det_com_pro_fecha_venc,compr.compra_fecha_ingreso_alm\n" +
"order by max(cast(compra_fecha_ingreso_alm as varchar(max))) asc";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            while (rs.next()) {
                model_tabla.addRow(new Object[]{ rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                    rs.getString(8), rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13)});

            }
            cxn.desconectar();
        } catch (SQLException e) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, e);
            // System.out.println("Controller.Controller_Venta.venta_detalle()" + e);
        }
       //scrollPane =new JScrollPane( tabla );
//       scrollPane.setVisible(true);
tabla.setModel(model_tabla);
tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);


// tabla.getTableHeader (). setReorderingAllowed(false);
// tabla.getColumnModel().getColumn(0).setResizable(false);

//tabla.getColumnModel().getColumn(0).setWidth(tabla.getColumnModel().getColumn(0).getWidth());
       
      //  tabla.setAutoscrolls(true);
       


        //tabla.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
       /* Function_Key_Event.Validar_numeros_jtable(tabla);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(250);
tabla.getColumnModel().getColumn(1).setPreferredWidth(100);
//FixedColumnTable fct = new FixedColumnTable(2, scrollPane);
       // JScrollPane scrollPane = new JScrollPane();
  //  scrollPane.getViewport().add(tabla);
  
        System.out.println("Controller.Controller_control_recepcion_producto.tabla()");
  
    //tabla.doLayout();
   // add(scrollPane);
/*JScrollPane scrollPane= new JScrollPane( tabla );
        Function_Component.JTable(tabla);*/
     //  tabla.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
      /*  FixedColumnTable fct = new FixedColumnTable(2, scrollPane);
        
JTable fixed = fct.getFixedTable();
tabla.setAutoCreateRowSorter(true);
fixed.setRowSorter(tabla.getRowSorter());
tabla.setUpdateSelectionOnSort(true);
fixed.setUpdateSelectionOnSort(false);*/
    // tabla.scrollRectToVisible(tabla.getCellRect(5,5, true)); 

        /*    tabla.getColumnModel().getColumn(0).setMaxWidth(50);
        tabla.getColumnModel().getColumn(0).setMinWidth(50);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(1).setMaxWidth(0);
        tabla.getColumnModel().getColumn(1).setMinWidth(0);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(2).setMaxWidth(0);
        tabla.getColumnModel().getColumn(2).setMinWidth(0);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(3).setMaxWidth(150);
        tabla.getColumnModel().getColumn(3).setMinWidth(150);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(400);
        tabla.getColumnModel().getColumn(6).setMaxWidth(0);
        tabla.getColumnModel().getColumn(6).setMinWidth(0);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(0);/*
        tabla.getColumnModel().getColumn(7).setMaxWidth(1000);
        tabla.getColumnModel().getColumn(7).setMinWidth(100);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(100);*/

 /*   tabla.getColumnModel().getColumn(8).setMaxWidth(0);
        tabla.getColumnModel().getColumn(8).setMinWidth(0);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(9).setMaxWidth(0);
        tabla.getColumnModel().getColumn(9).setMinWidth(0);
        tabla.getColumnModel().getColumn(9).setPreferredWidth(0);
         */
       /* tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.setDragEnabled(false);
        tabla.getTableHeader().setReorderingAllowed(false);*/

    }

    /*  public void tabla_anexo(JTable tabla, String entrega, String anexo) {
        JButton btnEliminar = new JButton("");
        btnEliminar.setName("btneliminar");
        btnEliminar.setIcon(new ImageIcon("src\\Icon\\icon_eliminar.png"));
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel model_tabla = new DefaultTableModel(new Object[]{"ELI", "ID_PRODUCT", "PRODUCTO", "MARCA", "PRESENTACION",
            "NUMERO LOTE", "FECHA VENCIMIENTO", "FECHA VENCIMIENTO", "CANTIDAD ENTREGAR"}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return editable[column];
            }
        };
        try {
            String sSQL1 = "select anex.anexo_id,pre.present_id,pro.producto_descrip,\n"
                    + "fa.familia_descrip,pre.present_peso,anexo_lote,anex.anexo_fecha_vencimiento,\n"
                    + "anex.fecha_vencimiento,\n"
                    + "anex.anexo_cantidad from anexo_9 anex\n"
                    + "inner join producto_presentacion  pre\n"
                    + "on pre.present_id=anex.anexo_id_producto\n"
                    + "inner join producto pro\n"
                    + "on pro.producto_id=pre.present_cod_producto\n"
                    + "inner join familia fa\n"
                    + "on fa.familia_id=pro.producto_familia\n"
                    + "inner join entrega ent\n"
                    + "on ent.entrega_id=anex.anexo_entrega\n"
                    + "where anex.anexo_cod='" + anexo + "' and anex.anexo_entrega='" + entrega + "'";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            while (rs.next()) {
                model_tabla.addRow(new Object[]{btnEliminar, rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                    rs.getString(8), rs.getString(9)});

            }
            cxn.desconectar();
        } catch (SQLException e) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, e);
            // System.out.println("Controller.Controller_Venta.venta_detalle()" + e);
        }
        tabla.setModel(model_tabla);
        Function_Key_Event.Validar_numeros_jtable(tabla);
        Function_Component.JTable(tabla);

        /*    tabla.getColumnModel().getColumn(0).setMaxWidth(50);
        tabla.getColumnModel().getColumn(0).setMinWidth(50);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(1).setMaxWidth(0);
        tabla.getColumnModel().getColumn(1).setMinWidth(0);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(2).setMaxWidth(0);
        tabla.getColumnModel().getColumn(2).setMinWidth(0);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(3).setMaxWidth(150);
        tabla.getColumnModel().getColumn(3).setMinWidth(150);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(400);
        tabla.getColumnModel().getColumn(6).setMaxWidth(0);
        tabla.getColumnModel().getColumn(6).setMinWidth(0);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(0);/*
        tabla.getColumnModel().getColumn(7).setMaxWidth(1000);
        tabla.getColumnModel().getColumn(7).setMinWidth(100);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(100);*/

 /*   tabla.getColumnModel().getColumn(8).setMaxWidth(0);
        tabla.getColumnModel().getColumn(8).setMinWidth(0);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(9).setMaxWidth(0);
        tabla.getColumnModel().getColumn(9).setMinWidth(0);
        tabla.getColumnModel().getColumn(9).setPreferredWidth(0);
     */
 /*   tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.setDragEnabled(false);
        tabla.getTableHeader().setReorderingAllowed(false);

    }
     */
   

   
}
