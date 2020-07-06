package Controller;

import Conexion.Conexion;
import Config_Class.MyCellRenderer;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
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
public class Controller_Cardex_fisico_Lote {

    private Conexion cxn;
    private boolean[] editable = {false, false, false, false, false, false, false, false, false, false,
        false, false, false, false, true, true, false, false, false, false,
        false, false, false, true, true, true, false, true, true, false,
        true, true, false, false, true, true, true, true, true, true, true};

    public Controller_Cardex_fisico_Lote() {

        cxn = new Conexion();
    }

    //calcular el número de veces que se repite un carácter en un String
    public void tabla_producto(JTable producto, String buscar) {
        try {
            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String sSQL1 = "SELECT MAX(pro.producto_id) as id_producto,com.det_com_pro_cod_lote,max(cast(pro.producto_descrip as varchar(max))) +' X '+max(cast(pre.present_peso as varchar(max)))+' KG',\n"
                    + "com.det_com_pro_fecha_venc,max(cast(fa.familia_descrip as varchar(max))) as familia_descrip\n"
                    + "	FROM  producto pro\n"
                    + "	inner join producto_presentacion pre\n"
                    + "	on pre.present_cod_producto=pro.producto_id\n"
                    + "	inner join detalle_compra_producto com\n"
                    + "	on com.det_com_pro_id_producto=pre.present_id\n"
                    + "	inner join familia fa\n"
                    + "	on fa.familia_id=pro.producto_familia\n"
                    + "	where det_com_pro_cod_lote like '%" + buscar + "%' \n"
                    + "	group by com.det_com_pro_cod_lote,pre.present_cod_producto,com.det_com_pro_fecha_venc\n"
                    + "	order by max(pro.producto_id) asc";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"", "ID", "COD LOTE", "DESCRIPCIÒN", "FECHA VEN", "MARCA"});
            int contador = 0;
            while (rs.next()) {
                contador++;
                model.addRow(new Object[]{contador, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
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
            producto.getColumnModel().getColumn(3).setPreferredWidth(350);
            /*tabla_igv.getColumnModel().getColumn(1).setMaxWidth(0);
            tabla_igv.getColumnModel().getColumn(1).setMinWidth(0);
            tabla_igv.getColumnModel().getColumn(1).setPreferredWidth(0);
            tabla_igv.getColumnModel().getColumn(3).setMaxWidth(0);
            tabla_igv.getColumnModel().getColumn(3).setMinWidth(0);
            tabla_igv.getColumnModel().getColumn(3).setPreferredWidth(0);*/
            // producto.
            producto.setAutoCreateRowSorter(true);
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

    public void tabla_lote(JTable tabla, String lote, String fecha1, String fecha2, String fecha3, String almacen) {
        JButton btnEliminar = new JButton("");
        btnEliminar.setName("btneliminar");
        btnEliminar.setIcon(new ImageIcon("src\\Icon\\icon_eliminar.png"));
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel model = new DefaultTableModel(new Object[]{"FECHA", "ENTRADA", "SALIDA", "SALDO", "MOTIVO", "RESPONSABLE", "OBSERVACION", ""}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
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
            String sSQL1 = "EXEC [dbo].[tabla_temporal]\n"
                    + "		@lote = N'" + lote + "',\n"
                    + "		@fecha1 = N'" + fecha1 + "',\n"
                    + "		@fecha2 = N'" + fecha2 + "',\n"
                    + "               @fecha3 = N'" + fecha3 + "',\n"
                    + "		@almacen = N'" + almacen + "'";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);
            String sql = "truncate table reporte_cadex_lote";
            cxn.conectardb().createStatement().executeUpdate(sql);
            cxn.desconectar();

            while (rs.next()) {

                String sql_set = "insert into reporte_cadex_lote(reporte_fecha,reporte_entrada,reporte_salida,reporte_saldo,reporte_motivo,reporte_responsable,reporte_observacion,reporte_lote)values('" + rs.getString(1) + "'," + rs.getDouble(2) + "," + rs.getDouble(3) + "," + rs.getDouble(4) + ",'" + rs.getString(5) + "','" + rs.getString(6) + "',\n"
                        + "                    '" + rs.getString(7) + "','" + lote + "')";

                cxn.conectardb().createStatement().executeUpdate(sql_set);
                cxn.desconectar();

            }
            String sql_final = "select * from reporte_cadex_lote\n"
                    + "           order by  reporte_fecha asc";
            ResultSet rs1 = cxn.conectardb().createStatement().executeQuery(sql_final);
            double saldo = 0;
            while (rs1.next()) {
                saldo = rs1.getDouble(2) - rs1.getDouble(3) + saldo;
                model.addRow(new Object[]{rs1.getString(1), rs1.getDouble(2), rs1.getDouble(3), saldo, rs1.getString(5), rs1.getString(6),
                    rs1.getString(7), rs1.getString(8)});
            }
            cxn.desconectar();
        } catch (Exception e) {
            System.out.println("puebas.Controller_Empresa.PRUEBA()" + e);

        }
        tabla.setModel(model);
        Function_Key_Event.Validar_numeros_jtable(tabla);
        Function_Component.JTable(tabla);

         tabla.getColumnModel().getColumn(0).setMaxWidth(150);
        tabla.getColumnModel().getColumn(0).setMinWidth(150);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(1).setMaxWidth(150);
        tabla.getColumnModel().getColumn(1).setMinWidth(150);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(2).setMaxWidth(150);
        tabla.getColumnModel().getColumn(2).setMinWidth(0);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(3).setMaxWidth(150);
        tabla.getColumnModel().getColumn(3).setMinWidth(150);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(400);
        tabla.getColumnModel().getColumn(7).setMaxWidth(0);
        tabla.getColumnModel().getColumn(7).setMinWidth(0);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(8).setMaxWidth(0);
        tabla.getColumnModel().getColumn(8).setMinWidth(0);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(9).setMaxWidth(50);
        tabla.getColumnModel().getColumn(9).setMinWidth(50);
        tabla.getColumnModel().getColumn(9).setPreferredWidth(50);
        //tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.setDragEnabled(false);
        tabla.getTableHeader().setReorderingAllowed(false);

    }

    public void tabla_lote_todo(JTable tabla, String lote, String fecha1, String fecha2, String fecha3, String fecha_ven, String producto) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        JButton btnbuscar = new JButton("");
        btnbuscar.setName("btnbuscar");
        btnbuscar.setIcon(new ImageIcon("src\\Icon\\icon_busqueda.png"));
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel model = new DefaultTableModel(new Object[]{"FECHA", "ENTRADA", "SALIDA", "SALDO", "MOTIVO", "RESPONSABLE", "OBSERVACION", "", "CONCEPTO", ""}, 0) {

            Class[] types = new Class[]{
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
            String sSQL1 = "EXEC [dbo].[tabla_temporal_solido]\n"
                    + "		@lote = N'" + lote + "',\n"
                    + "		@fecha1 = N'" + fecha1 + "',\n"
                    + "		@fecha2 = N'" + fecha2 + "',\n"
                    + "         @fecha3 = N'" + fecha3 + "',\n"
                    + "         @fechavencimiento = N'" + fecha_ven + "',\n"
                    + "         @producto = N'" + producto + "'";

            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);
            String sql = "truncate table reporte_cadex_lote";
            cxn.conectardb().createStatement().executeUpdate(sql);
            cxn.desconectar();

            while (rs.next()) {

                String sql_set = "insert into reporte_cadex_lote(reporte_fecha,reporte_entrada,reporte_salida,reporte_saldo,reporte_motivo,reporte_responsable,reporte_observacion,reporte_lote,reporte_concepto)values('" + rs.getString(1) + "'," + rs.getDouble(2) + "," + rs.getDouble(3) + "," + rs.getDouble(4) + ",'" + rs.getString(5) + "','" + rs.getString(6) + "',\n"
                        + "                    '" + rs.getString(7) + "','" + lote + "', '" + rs.getString(8) + "')";

                cxn.conectardb().createStatement().executeUpdate(sql_set);
                cxn.desconectar();

            }
            String sql_final = "select * from reporte_cadex_lote\n"
                    + "           order by  reporte_fecha ASC ,[reporte_entrada] DESC,[reporte_salida] DESC";
            ResultSet rs1 = cxn.conectardb().createStatement().executeQuery(sql_final);
            double saldo = 0;
            while (rs1.next()) {
                saldo = rs1.getDouble(2) - rs1.getDouble(3) + saldo;
                model.addRow(new Object[]{rs1.getDate(1), rs1.getDouble(2), rs1.getDouble(3), saldo, rs1.getString(5), rs1.getString(6),
                    rs1.getString(7), rs1.getString(8), rs1.getString(9), btnbuscar});
            }
            cxn.desconectar();
        } catch (Exception e) {
            System.out.println("puebas.Controller_Empresa.PRUEBA()" + e);

        }
        tabla.setModel(model);
        Function_Key_Event.Validar_numeros_jtable(tabla);
        Function_Component.JTable(tabla);

         tabla.getColumnModel().getColumn(0).setMaxWidth(150);
        tabla.getColumnModel().getColumn(0).setMinWidth(150);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(1).setMaxWidth(150);
        tabla.getColumnModel().getColumn(1).setMinWidth(150);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(2).setMaxWidth(150);
        tabla.getColumnModel().getColumn(2).setMinWidth(0);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(3).setMaxWidth(150);
        tabla.getColumnModel().getColumn(3).setMinWidth(150);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(400);
        tabla.getColumnModel().getColumn(7).setMaxWidth(0);
        tabla.getColumnModel().getColumn(7).setMinWidth(0);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(8).setMaxWidth(0);
        tabla.getColumnModel().getColumn(8).setMinWidth(0);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(9).setMaxWidth(50);
        tabla.getColumnModel().getColumn(9).setMinWidth(50);
        tabla.getColumnModel().getColumn(9).setPreferredWidth(50);
        //tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.setDragEnabled(false);
        tabla.getTableHeader().setReorderingAllowed(false);

    }

    public void tabla_lote_todo_empresa(JTable tabla, String lote, String fecha1, String fecha2, String fecha3, String fecha_ven, String producto, String empresa) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        JButton btnbuscar = new JButton("");
        btnbuscar.setName("btnbuscar");
        btnbuscar.setIcon(new ImageIcon("src\\Icon\\icon_busqueda.png"));
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel model = new DefaultTableModel(new Object[]{"FECHA", "ENTRADA", "SALIDA", "SALDO", "MOTIVO", "RESPONSABLE", "OBSERVACION", "", "CONCEPTO", "BUSCAR"}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

        };
        try {
            String sSQL1 = "EXEC [dbo].[tabla_temporal_solido_empresa]\n"
                    + "		@lote = N'" + lote + "',\n"
                    + "		@fecha1 = N'" + fecha1 + "',\n"
                    + "		@fecha2 = N'" + fecha2 + "',\n"
                    + "         @fecha3 = N'" + fecha3 + "',\n"
                    + "         @fechavencimiento = N'" + fecha_ven + "',\n"
                    + "         @producto = N'" + producto + "',\n"
                    + "         @almacen = N'" + empresa + "'";

            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);
            String sql = "truncate table reporte_cadex_lote";
            cxn.conectardb().createStatement().executeUpdate(sql);
            cxn.desconectar();

            while (rs.next()) {

                String sql_set = "insert into reporte_cadex_lote(reporte_fecha,reporte_entrada,reporte_salida,reporte_saldo,reporte_motivo,reporte_responsable,reporte_observacion,reporte_lote,reporte_concepto)values('" + rs.getString(1) + "'," + rs.getDouble(2) + "," + rs.getDouble(3) + "," + rs.getDouble(4) + ",'" + rs.getString(5) + "','" + rs.getString(6) + "',\n"
                        + "'" + rs.getString(7) + "','" + lote + "','" + rs.getString(8) + "')";

                cxn.conectardb().createStatement().executeUpdate(sql_set);
                cxn.desconectar();

            }
            String sql_final = "select * from reporte_cadex_lote\n"
                    + "           order by  reporte_fecha ASC ,[reporte_entrada] DESC,[reporte_salida] DESC";
            ResultSet rs1 = cxn.conectardb().createStatement().executeQuery(sql_final);
            double saldo = 0;
            while (rs1.next()) {
                saldo = rs1.getDouble(2) - rs1.getDouble(3) + saldo;
                model.addRow(new Object[]{rs1.getDate(1), rs1.getDouble(2), rs1.getDouble(3), saldo, rs1.getString(5), rs1.getString(6),
                    rs1.getString(7), rs1.getString(8), rs1.getString(9), btnbuscar});
            }
            cxn.desconectar();
        } catch (Exception e) {
            System.out.println("puebas.Controller_Empresa.PRUEBA()" + e);

        }
        tabla.setModel(model);
        Function_Key_Event.Validar_numeros_jtable(tabla);
        Function_Component.JTable(tabla);

         tabla.getColumnModel().getColumn(0).setMaxWidth(150);
        tabla.getColumnModel().getColumn(0).setMinWidth(150);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(1).setMaxWidth(150);
        tabla.getColumnModel().getColumn(1).setMinWidth(150);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(2).setMaxWidth(150);
        tabla.getColumnModel().getColumn(2).setMinWidth(0);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(3).setMaxWidth(150);
        tabla.getColumnModel().getColumn(3).setMinWidth(150);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(400);
        tabla.getColumnModel().getColumn(7).setMaxWidth(0);
        tabla.getColumnModel().getColumn(7).setMinWidth(0);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(8).setMaxWidth(0);
        tabla.getColumnModel().getColumn(8).setMinWidth(0);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(9).setMaxWidth(50);
        tabla.getColumnModel().getColumn(9).setMinWidth(50);
        tabla.getColumnModel().getColumn(9).setPreferredWidth(50);
        //tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.setDragEnabled(false);
        tabla.getTableHeader().setReorderingAllowed(false);

    }

    public void table_venta_distribucion(JTable venta, String fecha, String lote, String fechaven, String producto, String concepto) {
        Object[][] data = null;

        Object[] column;
        JButton btnEliminar = new JButton("");
        btnEliminar.setName("btneliminar");
        btnEliminar.setIcon(new ImageIcon("src\\Icon\\icon_eliminar.png"));

        JButton btnCAE = new JButton("CAE");
        btnCAE.setIcon(new ImageIcon("src\\Icon\\icon_ver.png"));
        btnCAE.setName("btnCAE");

        JButton btnViewTablaCAE = new JButton("CAE");
        btnViewTablaCAE.setIcon(new ImageIcon("src\\Icon\\icon_tabla.png"));
        btnViewTablaCAE.setName("btnViewTablaCAE");

        JButton btnImprmirCAE = new JButton("CAE");
        btnImprmirCAE.setIcon(new ImageIcon("src\\Icon\\icon_imprimir.png"));
        btnImprmirCAE.setName("btnImprmirCAE");

        JButton btnPDFCAE = new JButton("CAE");
        btnPDFCAE.setName("btnPDFCAE");
        btnPDFCAE.setIcon(new ImageIcon("src\\Icon\\icon_pdf.png"));

        JButton btnGuiaCE = new JButton("GUIA E");
        btnGuiaCE.setIcon(new ImageIcon("src\\Icon\\icon_guia.png"));
        btnGuiaCE.setName("btnGuiaCE");

        JButton btnGuiaPDF = new JButton("GUIA");
        btnGuiaPDF.setIcon(new ImageIcon("src\\Icon\\icon_pdf.png"));
        btnGuiaPDF.setName("btnGuiaPDF");

        JButton btnImprmirGIA = new JButton("GUIA");
        btnImprmirGIA.setIcon(new ImageIcon("src\\Icon\\icon_imprimir.png"));
        btnImprmirGIA.setName("btnImprmirGIA");

        venta.setDefaultRenderer(Object.class, new MyCellRenderer());
        // venta.setDefaultRenderer(Object.class, new Render_Celda());
        try {
            DefaultTableModel model = new DefaultTableModel() {
                Class[] types = new Class[]{
                    java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                    java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class,
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class,
                    java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                    java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class,
                    java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                    java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                    java.lang.Object.class
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
            String sSQL1 = "select ven.venta_id,\n"
                    + "ti_com.comprobante_descrip,\n"
                    + "ins.institucion_codigo_modular,per.persona_razon_social,\n"
                    + "ven.venta_serie,\n"
                    + "ven.venta_numero,\n"
                    + " FORMAT(CONVERT(date,ven.venta_fecha_emision), N'dd-MM-yyyy '),\n"
                    + "al.almacen_descrip\n"
                    + " from venta ven\n"
                    + "inner join tipo_comprobante ti_com\n"
                    + "on ti_com.comprobante_id=ven.venta_tipo_comprobante\n"
                    + "inner join tipo_documento ti_do\n"
                    + "on ti_do.documento_id=ven.venta_tipo_documento\n"
                    + "inner join persona per\n"
                    + "on per.persona_id=ven.venta_cliente\n"
                    + "inner join tipo_moneda ti_mo\n"
                    + "on ti_mo.moneda_id=ven.venta_id_moneda\n"
                    + "inner join tipo_afectacion ti_af\n"
                    + "on ti_af.afectacion_id=ven.venta_id_afectacion\n"
                    + "inner join almacen al\n"
                    + "on al.almacen_id =ven.venta_empresa \n"
                    + "inner join institucion ins\n"
                    + "on ins.institucion_codigo_modular=per.persona_institucion\n"
                    + "inner join guia_remision guia\n"
                    + "on ven.venta_id=guia.guia_venta\n"
                    + "inner join detalle_venta_producto det\n"
                    + "on ven.venta_id=det.det_ven_pro_id_venta\n"
                    + "inner join producto_presentacion pre\n"
                    + "on pre.present_id=det.det_ven_pro_id_producto\n"
                    + "where ven.venta_fecha_emision='"+fecha+"' and det.det_ven_pro_cod_lote='"+lote+"' and det.det_ven_pro_fecha='"+fechaven+"' and pre.present_cod_producto='"+producto+"' and ven.venta_concepto='"+concepto+"' \n"
                    + "order  by ins.institucion_ruta asc";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"", "COMPROBANTE", "CODIGO MODULAR", "COLEGIO",
                "SERIE", "NUMERO", "FECHA", "ALMACEN"});
            while (rs.next()) {
                //    System.out.println("Controller.Controller_Distribucion.table_venta()"+rs.getString(13));

                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                    rs.getString(6), rs.getString(7), rs.getString(8)});
            }
            cxn.desconectar();
            venta.setModel(model);
            Function_Component.JTable(venta);
            
            venta.getColumnModel().getColumn(1).setPreferredWidth(150);
            venta.getColumnModel().getColumn(2).setPreferredWidth(150);
            venta.getColumnModel().getColumn(3).setPreferredWidth(150);
            venta.getColumnModel().getColumn(5).setPreferredWidth(150);
            venta.setDragEnabled(false);
            venta.getTableHeader().setReorderingAllowed(false);
            venta.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            venta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            venta.setAutoCreateRowSorter(true);
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }

    public void table_venta(JTable venta, String fecha, String lote, String fechaven, String producto, String concepto) {
        Object[][] data = null;

        Object[] column;
        JButton btnEliminar = new JButton("");
        btnEliminar.setName("btneliminar");
        btnEliminar.setIcon(new ImageIcon("src\\Icon\\icon_eliminar.png"));

        JButton btnCAE = new JButton("CAE");
        btnCAE.setIcon(new ImageIcon("src\\Icon\\icon_ver.png"));
        btnCAE.setName("btnCAE");

        JButton btnViewTablaCAE = new JButton("CAE");
        btnViewTablaCAE.setIcon(new ImageIcon("src\\Icon\\icon_tabla.png"));
        btnViewTablaCAE.setName("btnViewTablaCAE");

        JButton btnImprmirCAE = new JButton("CAE");
        btnImprmirCAE.setIcon(new ImageIcon("src\\Icon\\icon_imprimir.png"));
        btnImprmirCAE.setName("btnImprmirCAE");

        JButton btnPDFCAE = new JButton("CAE");
        btnPDFCAE.setName("btnPDFCAE");
        btnPDFCAE.setIcon(new ImageIcon("src\\Icon\\icon_pdf.png"));

        JButton btnGuiaCE = new JButton("GUIA E");
        btnGuiaCE.setIcon(new ImageIcon("src\\Icon\\icon_guia.png"));
        btnGuiaCE.setName("btnGuiaCE");

        JButton btnGuiaPDF = new JButton("GUIA");
        btnGuiaPDF.setIcon(new ImageIcon("src\\Icon\\icon_pdf.png"));
        btnGuiaPDF.setName("btnGuiaPDF");

        JButton btnImprmirGIA = new JButton("GUIA");
        btnImprmirGIA.setIcon(new ImageIcon("src\\Icon\\icon_imprimir.png"));
        btnImprmirGIA.setName("btnImprmirGIA");

        venta.setDefaultRenderer(Object.class, new MyCellRenderer());
        // venta.setDefaultRenderer(Object.class, new Render_Celda());
        try {
            DefaultTableModel model = new DefaultTableModel() {
                Class[] types = new Class[]{
                    java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                    java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class,
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class,
                    java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                    java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class,
                    java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                    java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                    java.lang.Object.class
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
            String sSQL1 = "select ven.venta_id,ven.venta_glosa,ven.venta_serie,ven.venta_numero\n"
                    + "from venta ven \n"
                    + "inner join detalle_venta_producto det\n"
                    + "on ven.venta_id=det.det_ven_pro_id_venta\n"
                    + "inner join producto_presentacion pre\n"
                    + "on pre.present_id=det.det_ven_pro_id_producto\n"
                    + "where ven.venta_fecha_emision='"+fecha+"' and det.det_ven_pro_cod_lote='"+lote+"' and det.det_ven_pro_fecha='"+fechaven+"' and pre.present_cod_producto='"+producto+"' and ven.venta_concepto='"+concepto+"' ";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"", "GLOSA", "SERIE", "NUMERO"});
            while (rs.next()) {
                //    System.out.println("Controller.Controller_Distribucion.table_venta()"+rs.getString(13));

                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)});
            }
            cxn.desconectar();
            venta.setModel(model);
            Function_Component.JTable(venta);
            /*venta.getColumnModel().getColumn(1).setMaxWidth(0);
            venta.getColumnModel().getColumn(1).setMinWidth(0);
            venta.getColumnModel().getColumn(1).setPreferredWidth(0);
            venta.getColumnModel().getColumn(2).setMaxWidth(0);
            venta.getColumnModel().getColumn(2).setMinWidth(0);
            venta.getColumnModel().getColumn(2).setPreferredWidth(0);
            venta.getColumnModel().getColumn(3).setMaxWidth(0);
            venta.getColumnModel().getColumn(3).setMinWidth(0);
            venta.getColumnModel().getColumn(3).setPreferredWidth(0);*/
            venta.getColumnModel().getColumn(1).setPreferredWidth(300);
            venta.setDragEnabled(false);
            venta.getTableHeader().setReorderingAllowed(false);
            venta.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            venta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            venta.setAutoCreateRowSorter(true);
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }

    public void table_venta_compra(JTable venta, String fecha, String lote, String fechaven, String producto, String concepto) {
        Object[][] data = null;

        Object[] column;
        JButton btnEliminar = new JButton("");
        btnEliminar.setName("btneliminar");
        btnEliminar.setIcon(new ImageIcon("src\\Icon\\icon_eliminar.png"));

        JButton btnCAE = new JButton("CAE");
        btnCAE.setIcon(new ImageIcon("src\\Icon\\icon_ver.png"));
        btnCAE.setName("btnCAE");

        JButton btnViewTablaCAE = new JButton("CAE");
        btnViewTablaCAE.setIcon(new ImageIcon("src\\Icon\\icon_tabla.png"));
        btnViewTablaCAE.setName("btnViewTablaCAE");

        JButton btnImprmirCAE = new JButton("CAE");
        btnImprmirCAE.setIcon(new ImageIcon("src\\Icon\\icon_imprimir.png"));
        btnImprmirCAE.setName("btnImprmirCAE");

        JButton btnPDFCAE = new JButton("CAE");
        btnPDFCAE.setName("btnPDFCAE");
        btnPDFCAE.setIcon(new ImageIcon("src\\Icon\\icon_pdf.png"));

        JButton btnGuiaCE = new JButton("GUIA E");
        btnGuiaCE.setIcon(new ImageIcon("src\\Icon\\icon_guia.png"));
        btnGuiaCE.setName("btnGuiaCE");

        JButton btnGuiaPDF = new JButton("GUIA");
        btnGuiaPDF.setIcon(new ImageIcon("src\\Icon\\icon_pdf.png"));
        btnGuiaPDF.setName("btnGuiaPDF");

        JButton btnImprmirGIA = new JButton("GUIA");
        btnImprmirGIA.setIcon(new ImageIcon("src\\Icon\\icon_imprimir.png"));
        btnImprmirGIA.setName("btnImprmirGIA");

        venta.setDefaultRenderer(Object.class, new MyCellRenderer());
        // venta.setDefaultRenderer(Object.class, new Render_Celda());
        try {
            DefaultTableModel model = new DefaultTableModel() {
                Class[] types = new Class[]{
                    java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                    java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class,
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class,
                    java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                    java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class,
                    java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                    java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                    java.lang.Object.class
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
            String sSQL1 = "select comp.compra_id,comp.compra_glosa,comp.compra_serie,comp.compra_numero\n"
                    + "from compra comp \n"
                    + "inner join detalle_compra_producto det\n"
                    + "on comp.compra_id=det.det_com_pro_id_compra\n"
                    + "inner join producto_presentacion pre\n"
                    + "on pre.present_id=det.det_com_pro_id_producto\n"
                    + "where comp.compra_fecha_ingreso_alm='"+fecha+"' and det.det_com_pro_cod_lote='"+lote+"' and det.det_com_pro_fecha_venc='"+fechaven+"' and pre.present_cod_producto='"+producto+"' and comp.compra_concepto='"+concepto+"' ";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"", "GLOSA", "SERIE", "NUMERO"});
            while (rs.next()) {
                //    System.out.println("Controller.Controller_Distribucion.table_venta()"+rs.getString(13));

                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)});
            }
            cxn.desconectar();
            venta.setModel(model);
            Function_Component.JTable(venta);
            /*venta.getColumnModel().getColumn(1).setMaxWidth(0);
            venta.getColumnModel().getColumn(1).setMinWidth(0);
            venta.getColumnModel().getColumn(1).setPreferredWidth(0);
            venta.getColumnModel().getColumn(2).setMaxWidth(0);
            venta.getColumnModel().getColumn(2).setMinWidth(0);
            venta.getColumnModel().getColumn(2).setPreferredWidth(0);
            venta.getColumnModel().getColumn(3).setMaxWidth(0);
            venta.getColumnModel().getColumn(3).setMinWidth(0);
            venta.getColumnModel().getColumn(3).setPreferredWidth(0);*/
            venta.getColumnModel().getColumn(1).setPreferredWidth(300);
            venta.setDragEnabled(false);
            venta.getTableHeader().setReorderingAllowed(false);
            venta.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            venta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            venta.setAutoCreateRowSorter(true);
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }
}
