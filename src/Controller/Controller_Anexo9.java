package Controller;

import Conexion.Conexion;
import Config_Class.Render;
import Entity.Entity_Almacen;
import Entity.Entity_Anexo9;
import Entity.Entity_Compra;
import Entity.Entity_Compra_Producto;
import Entity.Entity_Concepto;
import Entity.Entity_Documento;
import Entity.Entity_Entrega;
import Entity.Entity_Moneda;
import Entity.Entity_Persona;
import Entity.Entity_Producto;
import Entity.Entity_Producto_Presentacion;
import Entity.Entity_Sunat_transaccion;
import Entity.Entity_Tipo_Comprobante;
import Entity.Entity_Tipo_afectacion;
import Entity.Entity_Venta;
import Entity.Entity_Venta_Producto;
import Function.Function_Component;
import Function.Function_Key_Event;
import Function.Function_ShowMessageDialog;
import View.View_Escritorio;
import View.View_Salida_producto;
import View.View_Salida_producto;
import View.View_Salida_producto;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
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
public class Controller_Anexo9 {

    private Conexion cxn;
    private int getselection = 0;
    private boolean[] editable = {false, false, false, true, false, false, true, true, true, false, false, true, true, true, true, true, true, true, true, true};
    DefaultTableModel model;

    DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
    DecimalFormat formato2;

    /*private generador_codigo generador_id;
    Function_ShowMessageDialog mensaje = new Function_ShowMessageDialog();*/
    public Controller_Anexo9() {

        cxn = new Conexion();
        separadoresPersonalizados.setDecimalSeparator('.');
        formato2 = new DecimalFormat("###############0.000000", separadoresPersonalizados);

        //generador_id = new generador_codigo();
    }

    public void reset_variable() {
        getselection = 0;
    }

    public int variable() {
        return getselection;
    }

    public void table_venta(JTable venta) {
        JButton btnSelecionar = new JButton("");
        btnSelecionar.setName("btneliminar");
        btnSelecionar.setIcon(new ImageIcon("src\\Icon\\icon_agregar.png"));
        try {
            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String sSQL1 = "select anex.anexo_cod,max(cast(ent.entrega_id as varchar(max))),max(cast(ent.entrega_descripcion as varchar(max))),max(cast(ent.entrega_año as varchar(max))) from anexo_9 anex\n"
                    + "inner join entrega ent\n"
                    + "on ent.entrega_id =anex.anexo_entrega\n"
                    + "\n"
                    + "group by anex.anexo_cod \n"
                    + "order by max(cast(anex.anexo_id as  varchar(max))) ";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{ "COD", "IDEN", "DESCRIPCION", "AÑO"});
            while (rs.next()) {

                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                  });
            }
            cxn.desconectar();
            venta.setModel(model);
            Function_Component.JTable(venta);
            venta.getColumnModel().getColumn(1).setMaxWidth(0);
            venta.getColumnModel().getColumn(1).setMinWidth(0);
            venta.getColumnModel().getColumn(1).setPreferredWidth(0);
            venta.setDragEnabled(false);
            venta.getTableHeader().setReorderingAllowed(false);
            //venta.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }

    public void cbx_entrega(JComboBox<Entity_Entrega> entrega) {
        String sent = "SELECT * FROM entrega order by entrega_id  asc";
        try {
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sent);
            while (rs.next()) {
                entrega.addItem(new Entity_Entrega(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
            cxn.desconectar();
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }

    public String Add_anexo(Entity_Anexo9 anexo) {
        String respueta = "";
        String sent = "EXEC	 [dbo].[InsertaTablaanexo9]\n"
                + "		@anexo_id = N'" + anexo.getAnexo_id() + "',\n"
                + "		@anexo_cod = N'" + anexo.getAnexo_cod() + "',\n"
                + "		@anexo_id_producto = N'" + anexo.getAnexo_id_producto() + "',\n"
                + "		@anexo_entrega = N'" + anexo.getAnexo_entrega() + "',\n"
                + "		@anexo_cantidad = N'" + anexo.getAnexo_cantidad() + "',\n"
                + "		@anexo_reposicion = N'" + anexo.getAnexo_reposicion() + "',\n"
                + "		@anexo_cantidad_liberar = N'" + anexo.getAnexo_cantidad_liberar() + "',\n"
                + "		@anexo_lote = N'" + anexo.getAnexo_lote() + "',\n"
                + "		@anexo_fecha_vencimiento = N'" + anexo.getAnexo_fecha_vencimiento() + "',\n"
                + "		@anexo_hsccp = N'" + anexo.getAnexo_hsccp() + "',\n"
                + "		@anexo_registro_sanitario = N'" + anexo.getAnexo_registro_sanitario() + "',\n"
                + "		@anexo_certificado = N'" + anexo.getAnexo_certificado() + "',\n"
                + "		@anexo_ensayo = N'" + anexo.getAnexo_ensayo() + "',\n"
                + "		@anexo_sanitario = N'" + anexo.getAnexo_sanitario() + "',\n"
                + "		@anexo_regional = N'" + anexo.getAnexo_regional() + "',\n"
                + "		@fecha_vencimiento = N'" + anexo.getFecha_vencimiento() + "'";
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

    public String Add_Productos(Entity_Producto producto) {
        String respueta = "";
        String sent = "DECLARE	@return_value int\n"
                + "\n"
                + "EXEC	@return_value = [dbo].[InsertaTablaproducto]\n"
                + "		@producto_id = N'" + producto.getProducto_id() + "',\n"
                + "		@producto_cod = N'" + producto.getProducto_cod() + "',\n"
                + "		@producto_descrip = N'" + producto.getProducto_descrip() + "',\n"
                + "		@producto_descripcort = N'" + producto.getProducto_descripcort() + "',\n"
                + "		@producto_clasifi = N'" + producto.getProducto_clasifi() + "',\n"
                + "		@producto_moneda = N'" + producto.getProducto_moneda() + "',\n"
                + "		@producto_familia = N'" + producto.getProducto_familia() + "',\n"
                + "		@producto_medida = N'" + producto.getProducto_medida() + "',\n"
                + "		@producto_observacion = N'" + producto.getProducto_observacion() + "',\n"
                + "		@producto_tipo_afect = N'" + producto.getProducto_tipo_afect() + "',\n"
                + "		@producto_estado = N'" + producto.getProducto_estado() + "',\n"
                + "             @producto_valor =N'" + producto.getProducto_valor() + "'\n"
                + "\n";
        System.out.println("" + producto);
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

    public String Add_venta(Entity_Venta venta) {
        String respuesta = "";

        String sent = "EXEC	[dbo].[InsertaTablaventa]\n"
                + "		@venta_id = N'" + venta.getVenta_id() + "',\n"
                + "		@venta_tipo_comprobante = N'" + venta.getVenta_tipo_comprobante() + "',\n"
                + "		@venta_tipo_documento = N'" + venta.getVenta_tipo_documento() + "',\n"
                + "		@venta_cliente = N'" + venta.getVenta_cliente() + "',\n"
                + "		@venta_id_moneda = N'" + venta.getVenta_id_moneda() + "',\n"
                + "		@venta_id_afectacion = N'" + venta.getVenta_id_afectacion() + "',\n"
                + "		@venta_serie = N'" + venta.getVenta_serie() + "',\n"
                + "		@venta_numero = N'" + venta.getVenta_numero() + "',\n"
                + "		@venta_tipo_cambio = N'" + venta.getVenta_tipo_cambio() + "',\n"
                + "		@venta_fecha_emision = N'" + venta.getVenta_fecha_emision() + "',\n"
                + "		@venta_fecha_vencimiento = N'" + venta.getVenta_fecha_vencimiento() + "',\n"
                + "		@venta_ing_desc_global = N'" + venta.getVenta_ing_desc_global() + "',\n"
                + "		@venta_desc_global = N'" + venta.getVenta_desc_global() + "',\n"
                + "		@venta_desc_iten = N'" + venta.getVenta_desc_iten() + "',\n"
                + "		@venta_grava = N'" + venta.getVenta_grava() + "',\n"
                + "		@venta_exonerada = N'" + venta.getVenta_exonerada() + "',\n"
                + "		@venta_inafecta = N'" + venta.getVenta_inafecta() + "',\n"
                + "		@venta_igv = N'" + venta.getVenta_igv() + "',\n"
                + "		@venta_gratuita = N'" + venta.getVenta_gratuita() + "',\n"
                + "		@venta_otros_cargo = N'" + venta.getVenta_otros_cargo() + "',\n"
                + "		@venta_total = N'" + venta.getVenta_total() + "',\n"
                + "		@venta_estado = N'" + venta.getVenta_estado() + "',\n"
                + "		@venta_empresa = N'" + venta.getVenta_empresa() + "',\n"
                + "		@venta_persona = N'" + venta.getVenta_persona() + "',\n"
                + "		@venta_glosa = N'" + venta.getVenta_glosa() + "',\n"
                + "		@venta_sunat_transaccion = N'" + venta.getVenta_sunat_transaccion() + "',\n"
                + "		@venta_concepto = N'" + venta.getVenta_concepto() + "',\n"
                + "		@venta_entrega = N'" + venta.getEntrega_id() + "'";

        try {
            cxn.conectardb().createStatement().executeUpdate(sent);
            cxn.desconectar();
            respuesta = "exito";

        } catch (SQLException ex) {
            respuesta = "fallo";

            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        return respuesta;
    }

    public String Add_compra_producto_detalle(Entity_Venta_Producto detalle_compra) {
        String respuesta = "";
        String sent = "EXEC	 [dbo].[InsertaTabladetalleventaproducto]\n"
                + "		@det_ven_pro_id = N'" + detalle_compra.getDet_ven_pro_id() + "',\n"
                + "		@det_ven_pro_id_venta = N'" + detalle_compra.getDet_ven_pro_id_venta() + "',\n"
                + "		@det_ven_pro_id_producto = N'" + detalle_compra.getDet_ven_pro_id_producto() + "',\n"
                + "		@det_ven_pro_id_igv = N'" + detalle_compra.getDet_ven_pro_id_igv() + "',\n"
                + "		@det_ven_pro_cantidad = N'" + detalle_compra.getDet_ven_pro_cantidad() + "',\n"
                + "		@det_ven_pro_precio = N'" + detalle_compra.getDet_ven_pro_precio() + "',\n"
                + "		@det_ven_pro_sub_total = N'" + detalle_compra.getDet_ven_pro_sub_total() + "',\n"
                + "		@det_ven_pro_total = N'" + detalle_compra.getDet_ven_pro_total() + "',\n"
                + "		@det_ven_pro_desc_por = N'" + detalle_compra.getDet_ven_pro_desc_por() + "',\n"
                + "		@det_ven_pro_desc_valor = N'" + detalle_compra.getDet_ven_pro_desc_valor() + "',\n"
                + "		@det_ven_pro_fecha = N'" + detalle_compra.getDet_ven_pro_fecha() + "',\n"
                + "		@det_ven_pro_cod_lote = N'" + detalle_compra.getDet_ven_pro_cod_lote() + "',\n"
                + "		@det_ven_pro_id_compra = N'" + detalle_compra.getDet_ven_pro_id_compra() + "'";

        try {
            cxn.conectardb().createStatement().executeUpdate(sent);
            cxn.desconectar();
            respuesta = "exito";

        } catch (SQLException ex) {
            respuesta = "fallo";

            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        return respuesta;
    }

    public String delete_compra_producto(String venta_detalle) {
        String respuesta = "";
        String sent = "delete from detalle_venta_producto where det_ven_pro_id='" + venta_detalle + "'";

        try {
            cxn.conectardb().createStatement().executeUpdate(sent);
            cxn.desconectar();
            respuesta = "exito";

        } catch (SQLException ex) {
            respuesta = "fallo";

            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        return respuesta;
    }

    public String delete_compra(String venta) {
        String respuesta = "";
        String sent = "DECLARE	@return_value int\n"
                + "\n"
                + "EXEC	@return_value = [dbo].[delete_venta]\n"
                + "		@id_producto = N'" + venta + "'\n"
                + "\n"
                + "SELECT	'Return Value' = @return_value";

        try {
            cxn.conectardb().createStatement().executeUpdate(sent);
            cxn.desconectar();
            respuesta = "exito";

        } catch (SQLException ex) {
            respuesta = "fallo";

            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        return respuesta;
    }

    public String anexo_9(String anexo) {
        String respueta = "";
        String sent = "DECLARE	@return_value int,\n"
                + "		@Resultado varchar(255)\n"
                + "\n"
                + "EXEC	@return_value = [dbo].[anexo9]\n"
                + "		@Id_Solicitud = N'" + anexo + "',\n"
                + "		@Resultado = @Resultado OUTPUT\n"
                + "\n"
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

    public String tabla_producto(JTable producto, String buscar) {
        String respuesta = "";
        JButton btnSelecionar = new JButton("");
        btnSelecionar.setName("btnselecionar");
        btnSelecionar.setIcon(new ImageIcon("src\\Icon\\icon_agregar.png"));
        producto.setDefaultRenderer(Object.class, new Render());
        try {
            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String sSQL1 = "SELECT MAX(pre.present_id) as id_presnetacion,max(cast(pro.producto_descrip as varchar(max))) as pro_descr,max(cast(fa.familia_descrip as varchar(max))) as familia,com.det_com_pro_cod_lote,\n"
                    + "max(cast(com.det_com_pro_fecha_venc as varchar(max))) as fecha_ven,max(cast(com.det_com_pro_fecha_produc as varchar(max))) as fecha_pro,\n"
                    + "max(cast(pre.present_peso as varchar(max))) as present_peso,\n"
                    + "( ISNULL((SELECT\n"
                    + "sum(det_comp.det_com_pro_cantidad *\n"
                    + "present.present_cantidad)\n"
                    + "FROM\n"
                    + "producto_presentacion present INNER JOIN producto produt ON present.present_cod_producto = produt.producto_id\n"
                    + "INNER JOIN detalle_compra_producto det_comp ON present.present_id = det_comp.det_com_pro_id_producto\n"
                    + "inner join compra compr on compr.compra_id=det_comp.det_com_pro_id_compra\n"
                    + "WHERE\n"
                    + "det_comp.det_com_pro_cod_lote =com.det_com_pro_cod_lote AND det_comp.det_com_pro_fecha_venc=com.det_com_pro_fecha_venc and produt.producto_id=pro.producto_id) ,0)-\n"
                    + "isNULL(( SELECT sum(det_ven.det_ven_pro_cantidad*\n"
                    + "present.present_cantidad ) AS total\n"
                    + "FROM\n"
                    + "producto_presentacion present INNER JOIN producto produt ON present.present_cod_producto = produt.producto_id\n"
                    + "INNER JOIN detalle_venta_producto det_ven ON present.present_id = det_ven.det_ven_pro_id_producto\n"
                    + "INNER JOIN venta vent on vent.venta_id=det_ven.det_ven_pro_id_venta\n"
                    + "WHERE\n"
                    + "det_ven.det_ven_pro_cod_lote =com.det_com_pro_cod_lote  AND det_ven.det_ven_pro_fecha=com.det_com_pro_fecha_venc and produt.producto_id=pro.producto_id),0)  ) as total  FROM  producto pro\n"
                    + "inner join producto_presentacion pre\n"
                    + "on pre.present_cod_producto=pro.producto_id\n"
                    + "inner join detalle_compra_producto com\n"
                    + "on com.det_com_pro_id_producto=pre.present_id\n"
                    + "inner  join familia fa\n"
                    + "on fa.familia_id=pro.producto_familia\n"
                    + "where pre.present_cod_unidad='1' and  pre.present_estado='true' and cast(pro.producto_descrip as varchar(max)) like  '%" + buscar + "%'\n"
                    + "group by com.det_com_pro_cod_lote,com.det_com_pro_fecha_venc,pro.producto_id";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"", "ID", "DESCRIPCIÒN", "MARCA", "COD LOTE", "FECHA VENC", "FECHA PROD", "PESO", "TOTAL", ""});
            int contador = 0;
            while (rs.next()) {
                contador++;
                model.addRow(new Object[]{contador, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), btnSelecionar});
            }
            cxn.desconectar();
            producto.setModel(model);
            Function_Component.JTable(producto);
            producto.getColumnModel().getColumn(0).setCellRenderer(producto.getTableHeader().getDefaultRenderer());
            producto.getColumnModel().getColumn(0).setMaxWidth(50);
            producto.getColumnModel().getColumn(0).setMinWidth(50);
            producto.getColumnModel().getColumn(0).setPreferredWidth(50);
            /*tabla_igv.getColumnModel().getColumn(1).setMaxWidth(0);
            tabla_igv.getColumnModel().getColumn(1).setMinWidth(0);
            tabla_igv.getColumnModel().getColumn(1).setPreferredWidth(0);
            tabla_igv.getColumnModel().getColumn(3).setMaxWidth(0);
            tabla_igv.getColumnModel().getColumn(3).setMinWidth(0);
            tabla_igv.getColumnModel().getColumn(3).setPreferredWidth(0);*/

            producto.setDragEnabled(false);
            producto.getTableHeader().setReorderingAllowed(false);
            producto.getAutoCreateRowSorter();
        } catch (SQLException ex) {
            respuesta = "error";
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        return respuesta;
    }

    public String Eliminar_Anexo(String anexo) {
        String respuesta = "";
        String sent = "delete from anexo_9 where anexo_id='"+anexo+"'";

        try {
            cxn.conectardb().createStatement().executeUpdate(sent);
            cxn.desconectar();
            respuesta = "exito";

        } catch (SQLException ex) {
            respuesta = "fallo";

            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        return respuesta;
    }

    public void add_anexo9(JTable tabla, JDialog dialog_producto, JTable producto, String id_producto, String descripcion, String familia, String presentacion, String codigo_lote, String fecha_V, String fecha_production) {
        JButton btnEliminar = new JButton("");
        btnEliminar.setName("btneliminar");
        btnEliminar.setIcon(new ImageIcon("src\\Icon\\icon_eliminar.png"));

        tabla.setDefaultRenderer(Object.class, new Render());
        model = new DefaultTableModel(new Object[]{"ELI", "ID_ANEXO", "ID_PRODUCT", "PRODUCTO", "MARCA", "PRESENTACION", "CANTIDAD ENTREGAR",
            "REPOSICION", "CANTIDAD A LIBERAR", "NUMERO LOTE", "FECHA VENCIMIENTO", "VALIDACION HACCP", "REGISTRO SANITARIO", "CERTIFICADO",
            "INFORME DE ENSAYO", "AUTORIZACION SANITARIA", "FACTURA BOLETA", "GUIA DE REMISION", "PRODUCTO REGIONAL", "FECHA DE VENCIMIENTO"}, 0) {

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
        model = (DefaultTableModel) tabla.getModel();
        model.addRow(new Object[]{btnEliminar, "", id_producto, descripcion, familia, presentacion, "", "", "", codigo_lote, fecha_V, "", "", "", "", "", "", "", "", fecha_production});

        tabla.setModel(model);
        Function_Key_Event.Validar_numeros_jtable(tabla);
        /* tabla.getColumnModel().getColumn(0).setMaxWidth(50);
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


 /*  tabla.getColumnModel().getColumn(8).setMaxWidth(0);
        tabla.getColumnModel().getColumn(8).setMinWidth(0);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(9).setMaxWidth(0);
        tabla.getColumnModel().getColumn(9).setMinWidth(0);
        tabla.getColumnModel().getColumn(9).setPreferredWidth(0);*/
        settext_cod_barra(tabla, tabla.getColumnModel().getColumn(3), dialog_producto, producto);

        settext_cantidad(tabla, tabla.getColumnModel().getColumn(6), null);
        settext_cantidad(tabla, tabla.getColumnModel().getColumn(7), null);
        settext_cantidad(tabla, tabla.getColumnModel().getColumn(8), null);
        for (int i = 9; i < 20; i++) {
            settxt_text(tabla, tabla.getColumnModel().getColumn(i), null);
        }
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.setDragEnabled(false);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.getAutoCreateRowSorter();
     //   tabla.get

    }

    public void tabla_anexo(JTable tabla, JDialog dialog_producto, JTable producto, String anexo) {
        JButton btnEliminar = new JButton("");
        btnEliminar.setName("btneliminar");
        btnEliminar.setIcon(new ImageIcon("src\\Icon\\icon_eliminar.png"));
        tabla.setDefaultRenderer(Object.class, new Render());
        model = new DefaultTableModel(new Object[]{"ELI", "ID_ANEXO", "ID_PRODUCT", "PRODUCTO", "MARCA", "PRESENTACION", "CANTIDAD ENTREGAR",
            "REPOSICION", "CANTIDAD A LIBERAR", "NUMERO LOTE", "FECHA VENCIMIENTO", "VALIDACION HACCP", "REGISTRO SANITARIO", "CERTIFICADO",
            "INFORME DE ENSAYO", "AUTORIZACION SANITARIA", "FACTURA BOLETA", "GUIA DE REMISION", "PRODUCTO REGIONAL", "FECHA VENCIMIENTO"}, 0) {

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
                    + "fa.familia_descrip,pre.present_peso,anex.anexo_cantidad,anexo_reposicion,\n"
                    + "anexo_cantidad_liberar,anexo_lote,anex.anexo_fecha_vencimiento,\n"
                    + "anex.anexo_hsccp,anex.anexo_registro_sanitario,anex.anexo_certificado,\n"
                    + "anex.anexo_ensayo,anex.anexo_sanitario,anex.anexo_regional,anex.fecha_vencimiento from anexo_9 anex\n"
                    + "inner join producto_presentacion  pre\n"
                    + "on pre.present_id=anex.anexo_id_producto\n"
                    + "inner join producto pro\n"
                    + "on pro.producto_id=pre.present_cod_producto\n"
                    + "inner join familia fa\n"
                    + "on fa.familia_id=pro.producto_familia\n"
                    + "inner join entrega ent\n"
                    + "on ent.entrega_id=anex.anexo_entrega\n"
                    + "where anex.anexo_cod='" + anexo + "'";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            while (rs.next()) {
                model.addRow(new Object[]{btnEliminar, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
                    rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
                    rs.getString(13), rs.getString(14), rs.getString(15), "", "", rs.getString(16), rs.getString(17)});

            }
            cxn.desconectar();
        } catch (SQLException e) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, e);
            // System.out.println("Controller.Controller_Venta.venta_detalle()" + e);
        }


        /*for (int i = 0; i < 1; i++) {
            model.addRow(new Object[]{btnEliminar, "", "", "", "", "", "", "", "", "", "", "", "", "", ""});
        }*/
        tabla.setModel(model);
        Function_Key_Event.Validar_numeros_jtable(tabla);

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
        settext_cod_barra(tabla, tabla.getColumnModel().getColumn(3), dialog_producto, producto);

        settext_cantidad(tabla, tabla.getColumnModel().getColumn(6), null);
        settext_cantidad(tabla, tabla.getColumnModel().getColumn(7), null);
        settext_cantidad(tabla, tabla.getColumnModel().getColumn(8), null);
        for (int i = 9; i < 20; i++) {
            settxt_text(tabla, tabla.getColumnModel().getColumn(i), null);
        }
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.setDragEnabled(false);
        tabla.getTableHeader().setReorderingAllowed(false);

    }

    public void settext_cod_barra(JTable tabla, TableColumn columna, JDialog dialogo, JTable producto) {
        try {

            JTextField txt_cod_barra = new JTextField();
            Function_Component.JTextField(txt_cod_barra);
            columna.setCellEditor(new DefaultCellEditor(txt_cod_barra));
            DefaultTableCellRenderer render = new DefaultTableCellRenderer();
            txt_cod_barra.addKeyListener(new KeyListener() {
                @Override
                public void keyPressed(KeyEvent evt) {
                    try {

                        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                            Controller_Anexo9 almacen = new Controller_Anexo9();
                            if (almacen.tabla_producto(producto, txt_cod_barra.getText()).equals("ERROR")) {
                                Function_ShowMessageDialog.ShowMessageDialogvalidacion(null, "BUSQUEDA NO ENCONTRADA");

                            } else {
                                Dimension desktopSize = View_Escritorio.desktopPane.getSize();
                                Dimension FrameSize = dialogo.getSize();
                                dialogo.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
                                dialogo.setVisible(true);
                                tabla.editCellAt(tabla.getSelectedRow() + getselection, 6);
                                Component aComp = tabla.getEditorComponent();
                                aComp.requestFocus();
                                //   producto.requestFocus();

                            }

                            tabla.editCellAt(tabla.getSelectedRow() + getselection, 10);

                            Component aComp = tabla.getEditorComponent();
                            aComp.requestFocus();
                            evt.consume();

                        }

                    } catch (Exception e) {

                    }

                }

                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                }

            });

        } catch (Exception e) {
        }

    }

    public void settext_cantidad(JTable tabla, TableColumn columna, JDialog dialogo) {
        try {

            JTextField txt_cantidad = new JTextField();
            Function_Component.JTextField(txt_cantidad);
            Function_Key_Event.Validar_numeros(txt_cantidad);
            columna.setCellEditor(new DefaultCellEditor(txt_cantidad));
            DefaultTableCellRenderer render = new DefaultTableCellRenderer();
            txt_cantidad.addKeyListener(new KeyListener() {
                @Override
                public void keyPressed(KeyEvent evt) {

                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                        if (txt_cantidad.getText().length() != 0) {
                            tabla.editCellAt(tabla.getSelectedRow() + getselection, 11);
                            Component aComp = tabla.getEditorComponent();
                            aComp.requestFocus();
                            View_Salida_producto.btn_calcular.doClick();

                        }
                        evt.consume();

                    }
                    if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                        try {
                            if (txt_cantidad.getText().length() != 0) {
                                if (tabla.getSelectedRow() + getselection == tabla.getRowCount() - 1) {
                                    getselection = getselection + 1;

                                    View.View_Salida_producto.btn_agregar_fila.doClick();
                                    tabla.editCellAt(tabla.getSelectedRow() + getselection, 3);
                                    Component aComp = tabla.getEditorComponent();
                                    aComp.requestFocus();
                                } else {
                                    getselection = getselection + 1;
                                    tabla.editCellAt(tabla.getSelectedRow() + getselection, 3);
                                    Component aComp = tabla.getEditorComponent();
                                    aComp.requestFocus();
                                }
                            }
                        } catch (Exception e) {
                            tabla.editCellAt(0, 3);
                            Component aComp = tabla.getEditorComponent();
                            aComp.requestFocus();
                        }
                        evt.consume();
                    }

                }

                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    /*   try {
                        if (txt_cantidad.getText().length() > 0) {

                            tabla.setValueAt(formato2.format((Double.parseDouble(txt_cantidad.getText()) * Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 11).toString()))),
                                    tabla.getSelectedRow() + getselection, 12);
                            tabla.setValueAt(formato2.format((((Double.parseDouble(txt_cantidad.getText()) * Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 11).toString()))
                                    * Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 8).toString()))
                                    + (Double.parseDouble(txt_cantidad.getText())
                                    * Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 11).toString())))),
                                    tabla.getSelectedRow() + getselection, 13);
                        }
                    } catch (Exception es) {

                    }*/

                }

            });

        } catch (Exception e) {
        }

    }

    public void settxt_text(JTable tabla, TableColumn columna, JDialog dialogo) {

        JTextField txt_cod_lote = new JTextField();
        Function_Component.JTextField(txt_cod_lote);

        columna.setCellEditor(new DefaultCellEditor(txt_cod_lote));
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        txt_cod_lote.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {

                        if (tabla.getSelectedRow() + getselection == tabla.getRowCount() - 1) {
                            getselection = getselection + 1;

                            View.View_Salida_producto.btn_agregar_fila.doClick();
                            tabla.editCellAt(tabla.getSelectedRow() + getselection, 3);
                            Component aComp = tabla.getEditorComponent();
                            aComp.requestFocus();
                        } else {
                            getselection = getselection + 1;
                            tabla.editCellAt(tabla.getSelectedRow() + getselection, 3);
                            Component aComp = tabla.getEditorComponent();
                            aComp.requestFocus();
                        }
                    } catch (Exception e) {
                        tabla.editCellAt(0, 3);
                        Component aComp = tabla.getEditorComponent();
                        aComp.requestFocus();
                    }

                }

                if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                    try {

                        if (tabla.getSelectedRow() + getselection == tabla.getRowCount() - 1) {
                            getselection = getselection + 1;

                            View.View_Salida_producto.btn_agregar_fila.doClick();
                            tabla.editCellAt(tabla.getSelectedRow() + getselection, 3);
                            Component aComp = tabla.getEditorComponent();
                            aComp.requestFocus();
                        } else {
                            getselection = getselection + 1;
                            tabla.editCellAt(tabla.getSelectedRow() + getselection, 3);
                            Component aComp = tabla.getEditorComponent();
                            aComp.requestFocus();
                        }
                    } catch (Exception e) {
                        tabla.editCellAt(0, 3);
                        Component aComp = tabla.getEditorComponent();
                        aComp.requestFocus();
                    }

                }
                if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_L) {
                    try {
                        if (tabla.getValueAt(tabla.getSelectedRow() + getselection, 2).toString().length() != 0) {

                        }

                    } catch (Exception e) {
                        tabla.editCellAt(0, 3);
                        Component aComp = tabla.getEditorComponent();
                        aComp.requestFocus();
                    }
                    evt.consume();
                }
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        });

    }

    public String generar_cod_venta() {
        String valor = "";
        String sent = "SELECT (CASE   WHEN (MAX(venta_id) > 0) THEN (MAX(venta_id) + 1)  ELSE 1    END) AS maximo FROM venta";
        try {
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sent);
            rs.next();
            valor = rs.getString(1);
            cxn.desconectar();
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        return valor;
    }

    public String generar_cod_proveedor() {
        String valor = "";
        String sent = "SELECT (CASE   WHEN (MAX(persona_id) > 0) THEN (MAX(persona_id) + 1)  ELSE 1    END) AS maximo FROM persona";
        try {
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sent);
            rs.next();
            valor = rs.getString(1);
            cxn.desconectar();
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        return valor;
    }

    /*buscar txt producto por cod compra*/
    public List<Entity_Producto_Presentacion> buscar_cod_barra_producto_presentacion(String cod_barra) {
        List<Entity_Producto_Presentacion> persona = new ArrayList<Entity_Producto_Presentacion>();
        String sent = "SELECT present.present_id,\n"
                + "present.present_cod_barra,\n"
                + "present.present_descripcion,\n"
                + "uni.unidad_cod,\n"
                + "present.present_cantidad,\n"
                + "present.present_prec_venta,\n"
                + "afec.afectacion_id,afec.afectacion_descr,afec.afectacion_valor,afec.afectacion_cod_valor,\n"
                + "((ISNULL((select  sum(dt_com.det_com_pro_cantidad*pre.present_cantidad) from producto pro\n"
                + "inner join  producto_presentacion pre\n"
                + "on pre.present_cod_producto=pro.producto_id\n"
                + "inner join detalle_compra_producto dt_com\n"
                + "on dt_com.det_com_pro_id_producto=pre.present_id\n"
                + "where pro.producto_id=present.present_cod_producto\n"
                + "group by pro.producto_id),0))-(ISNULL((select  sum(dt_ven.det_ven_pro_cantidad*pre.present_cantidad)from producto pro\n"
                + "inner join  producto_presentacion pre\n"
                + "on pre.present_cod_producto=pro.producto_id\n"
                + "inner join detalle_venta_producto dt_ven\n"
                + "on dt_ven.det_ven_pro_id_producto=pre.present_id\n"
                + "where pro.producto_id=present.present_cod_producto\n"
                + "group by pro.producto_id),0)))AS TOTAL\n"
                + "FROM producto_presentacion present\n"
                + "INNER JOIN tipo_unidad uni\n"
                + "on uni.unidad_id=present.present_cod_unidad\n"
                + "inner join producto pro\n"
                + "on pro.producto_id=present.present_cod_producto\n"
                + "inner join tipo_afectacion afec\n"
                + "on afec.afectacion_id=pro.producto_tipo_afect\n"
                + "where present.present_estado='true' and present.present_cod_barra like '" + cod_barra + "'";
        try {
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sent);
            rs.next();
            persona.add(new Entity_Producto_Presentacion(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                    rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), "", rs.getString(11)));
            cxn.desconectar();
        } catch (SQLException ex) {
            //Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        return persona;
    }

    public List<Entity_Venta> buscar_compra(String buscar_venta) {
        List<Entity_Venta> venta = new ArrayList<Entity_Venta>();

        String sent = "select ven.venta_id,\n"
                + "ven.venta_tipo_comprobante, ti_com.comprobante_id,	ti_com.comprobante_cod,ti_com.comprobante_descrip,\n"
                + "ven.venta_tipo_documento,ti_do.documento_id,ti_do.documento_cod,ti_do.documento_descrip,ti_do.documento_url,\n"
                + "ven.venta_cliente,per.persona_tipo_documento,per.persona_ruc,per.persona_dni,per.persona_razon_social,per.persona_razon_comercial, per.persona_direccion,\n"
                + "ven.venta_id_moneda ,ti_mo.moneda_cod,ti_mo.moneda_descrip,\n"
                + "ven.venta_id_afectacion,ti_af.afectacion_id,ti_af.afectacion_cod,ti_af.afectacion_descr,ti_af.afectacion_valor,ti_af.afectacion_cod_valor,\n"
                + "ven.venta_serie,\n"
                + "ven.venta_numero,\n"
                + "ven.venta_tipo_cambio,\n"
                + "ven.venta_fecha_emision,\n"
                + "ven.venta_fecha_vencimiento,\n"
                + "ven.venta_ing_desc_global,\n"
                + "ven.venta_desc_global,\n"
                + "ven.venta_desc_iten,\n"
                + "ven.venta_grava,\n"
                + "ven.venta_exonerada,\n"
                + "ven.venta_inafecta,\n"
                + "ven.venta_igv,\n"
                + "ven.venta_gratuita,\n"
                + "ven.venta_otros_cargo,\n"
                + "ven.venta_total,\n"
                + "ven.venta_estado,\n"
                + "ven.venta_empresa,\n"
                + "ven.venta_persona,\n"
                + "ven.venta_glosa,\n"
                + "conc.concepto_id,conc.concepto_cod,conc.concepto_descripcion,\n"
                + "sunat.transaccion_id,sunat.transaccion_cod,sunat.transaccion_descripcion,\n"
                + "al.almacen_id,al.almacen_cod,al.almacen_descrip,\n"
                + " entre.entrega_id,entre.entrega_descripcion,entre.entrega_año\n"
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
                + "inner join concepto conc\n"
                + "on conc.concepto_id=ven.venta_concepto\n"
                + "inner join sunat_transaccion sunat\n"
                + "on sunat.transaccion_id=ven.venta_sunat_transaccion\n"
                + "inner join almacen al\n"
                + "on al.almacen_id=ven.venta_empresa\n"
                + "inner join entrega entre\n"
                + "on entre.entrega_id=ven.venta_entrega\n"
                + "where venta_id='" + buscar_venta + "'";
        try {
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sent);
            rs.next();
            venta.add(new Entity_Venta(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                    rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
                    rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20),
                    rs.getString(21), rs.getString(22), rs.getString(23), rs.getString(24), rs.getString(25), rs.getString(26), rs.getString(27),
                    rs.getString(28), rs.getString(29), rs.getString(30), rs.getString(31), rs.getString(32), rs.getString(33), rs.getString(34),
                    rs.getString(35), rs.getString(36), rs.getString(37), rs.getString(38), rs.getString(39), rs.getString(40), rs.getString(41), rs.getString(42),
                    rs.getString(43), rs.getString(44), rs.getString(45), rs.getString(46), rs.getString(47), rs.getString(48), rs.getString(49),
                    rs.getString(50), rs.getString(51), rs.getString(52), rs.getString(53), rs.getString(54), rs.getString(55), rs.getString(56), rs.getString(57)));
            cxn.desconectar();
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        return venta;
    }

    public List<Entity_Persona> buscar_persona(String buscar) {
        List<Entity_Persona> persona = new ArrayList<Entity_Persona>();
        String sent = "select * from persona per\n"
                + "inner join tipo_documento doc\n"
                + "on doc.documento_id=per.persona_tipo_documento\n"
                + "where per.persona_ruc='" + buscar + "' or per.persona_dni='" + buscar + "'";
        try {
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sent);
            rs.next();
            persona.add(new Entity_Persona(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11)));
            cxn.desconectar();
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        return persona;
    }

}
