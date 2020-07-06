package Controller;

import Conexion.Conexion;
import Config_Class.Render;
import Entity.Entity_Almacen;
import Entity.Entity_Compra;
import Entity.Entity_Compra_Producto;
import Entity.Entity_Concepto;
import Entity.Entity_Documento;
import Entity.Entity_Moneda;
import Entity.Entity_Persona;
import Entity.Entity_Producto;
import Entity.Entity_Producto_Presentacion;
import Entity.Entity_Tipo_Comprobante;
import Entity.Entity_Tipo_afectacion;
import Entity.Entity_Venta;
import Function.Function_Component;
import Function.Function_Key_Event;
import Function.Function_ShowMessageDialog;
import View.View_Escritorio;
import View.View_Compra;
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
public class Controller_compra {

    private Conexion cxn;
    private int getselection = 0;
    private boolean[] editable = {false, false, false, true, false, false, false, true, false, false, true, true, false, true, true, true, true, true, false};
    DefaultTableModel model;

    /*private generador_codigo generador_id;
    Function_ShowMessageDialog mensaje = new Function_ShowMessageDialog();*/
    public Controller_compra() {
        cxn = new Conexion();
        //generador_id = new generador_codigo();
    }

    public void reset_variable() {
        getselection = 0;
    }

    public int variable() {
        return getselection;
    }

    public String table_produto_busqueda(JTable clasificacion_producto, String busqueda) {
        String respuesta = "";
        try {

            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String sSQL1 = "sELECT				pre.present_id      \n"
                    + "                        ,pre.present_cod_barra\n"
                    + "                        ,pre.present_descripcion\n"
                    + "                  	  ,uni.unidad_cod\n"
                    + "                        ,pre.present_cantidad\n"
                    + "                        ,pre.present_prec_compra\n"
                    + "                    FROM producto_presentacion pre \n"
                    + "                    INNER JOIN tipo_unidad uni\n"
                    + "                    on uni.unidad_id=pre.present_cod_unidad\n"
                    + "					inner join producto pro\n"
                    + "					on pro.producto_id=pre.present_cod_producto\n"
                    + "                    where pre.present_estado='true' and pre.present_descripcion like '%" + busqueda + "%'\n"
                    + "					 order by pro.producto_id asc";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"", "", "COD_BARRA", "DESCRIPCIÒN", "PRESENTACION", "CANT", "PRECIO_COMPR"});
            int contador = 0;
            while (rs.next()) {
                contador++;
                model.addRow(new Object[]{contador, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)});
            }

            /**
             * if (rs.next()) {
             *
             *
             *
             * } else { respuesta = "ERROR"; }
             */
            cxn.desconectar();
            clasificacion_producto.setModel(model);
            Function_Component.JTable(clasificacion_producto);
            clasificacion_producto.getColumnModel().getColumn(0).setCellRenderer(clasificacion_producto.getTableHeader().getDefaultRenderer());
            clasificacion_producto.getColumnModel().getColumn(0).setMaxWidth(50);
            clasificacion_producto.getColumnModel().getColumn(0).setMinWidth(50);
            clasificacion_producto.getColumnModel().getColumn(0).setPreferredWidth(50);
            clasificacion_producto.getColumnModel().getColumn(1).setMaxWidth(0);
            clasificacion_producto.getColumnModel().getColumn(1).setMinWidth(0);
            clasificacion_producto.getColumnModel().getColumn(1).setPreferredWidth(0);
            clasificacion_producto.getColumnModel().getColumn(2).setMaxWidth(150);
            clasificacion_producto.getColumnModel().getColumn(2).setMinWidth(150);
            clasificacion_producto.getColumnModel().getColumn(2).setPreferredWidth(150);

            clasificacion_producto.getColumnModel().getColumn(4).setMaxWidth(80);
            clasificacion_producto.getColumnModel().getColumn(4).setMinWidth(80);
            clasificacion_producto.getColumnModel().getColumn(4).setPreferredWidth(80);
            clasificacion_producto.getColumnModel().getColumn(5).setMaxWidth(80);
            clasificacion_producto.getColumnModel().getColumn(5).setMinWidth(80);
            clasificacion_producto.getColumnModel().getColumn(5).setPreferredWidth(80);
            clasificacion_producto.getColumnModel().getColumn(6).setMaxWidth(80);
            clasificacion_producto.getColumnModel().getColumn(6).setMinWidth(80);
            clasificacion_producto.getColumnModel().getColumn(6).setPreferredWidth(80);

            clasificacion_producto.setDragEnabled(false);
            clasificacion_producto.getTableHeader().setReorderingAllowed(false);
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }

        return respuesta;
    }

    public String table_produto_busqueda_presentacion(JTable clasificacion_producto, String busqueda) {
        String respuesta = "";
        try {

            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String sSQL1 = "sELECT				pre.present_id      \n"
                    + "                        ,pre.present_cod_barra\n"
                    + "                        ,pre.present_descripcion\n"
                    + "                  	  ,uni.unidad_cod\n"
                    + "                        ,pre.present_cantidad\n"
                    + "                        ,pre.present_prec_compra\n"
                    + "                    FROM producto_presentacion pre \n"
                    + "                    INNER JOIN tipo_unidad uni\n"
                    + "                    on uni.unidad_id=pre.present_cod_unidad\n"
                    + "					inner join producto pro\n"
                    + "					on pro.producto_id=pre.present_cod_producto\n"
                    + "                    where pre.present_estado='true' and pro.producto_id=(select present_cod_producto from producto_presentacion where present_cod_barra='" + busqueda + "')\n"
                    + "					 order by pro.producto_id asc";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"", "", "COD_BARRA", "DESCRIPCIÒN", "PRESENTACION", "CANT", "PRECIO_COMPR"});
            int contador = 0;
            while (rs.next()) {
                contador++;
                model.addRow(new Object[]{contador, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)});
            }

            /**
             * if (rs.next()) {
             *
             *
             *
             * } else { respuesta = "ERROR"; }
             */
            cxn.desconectar();
            clasificacion_producto.setModel(model);
            Function_Component.JTable(clasificacion_producto);
            clasificacion_producto.getColumnModel().getColumn(0).setCellRenderer(clasificacion_producto.getTableHeader().getDefaultRenderer());
            clasificacion_producto.getColumnModel().getColumn(0).setMaxWidth(50);
            clasificacion_producto.getColumnModel().getColumn(0).setMinWidth(50);
            clasificacion_producto.getColumnModel().getColumn(0).setPreferredWidth(50);
            clasificacion_producto.getColumnModel().getColumn(1).setMaxWidth(0);
            clasificacion_producto.getColumnModel().getColumn(1).setMinWidth(0);
            clasificacion_producto.getColumnModel().getColumn(1).setPreferredWidth(0);
            clasificacion_producto.getColumnModel().getColumn(2).setMaxWidth(150);
            clasificacion_producto.getColumnModel().getColumn(2).setMinWidth(150);
            clasificacion_producto.getColumnModel().getColumn(2).setPreferredWidth(150);

            clasificacion_producto.getColumnModel().getColumn(4).setMaxWidth(80);
            clasificacion_producto.getColumnModel().getColumn(4).setMinWidth(80);
            clasificacion_producto.getColumnModel().getColumn(4).setPreferredWidth(80);
            clasificacion_producto.getColumnModel().getColumn(5).setMaxWidth(80);
            clasificacion_producto.getColumnModel().getColumn(5).setMinWidth(80);
            clasificacion_producto.getColumnModel().getColumn(5).setPreferredWidth(80);
            clasificacion_producto.getColumnModel().getColumn(6).setMaxWidth(80);
            clasificacion_producto.getColumnModel().getColumn(6).setMinWidth(80);
            clasificacion_producto.getColumnModel().getColumn(6).setPreferredWidth(80);

            clasificacion_producto.setDragEnabled(false);
            clasificacion_producto.getTableHeader().setReorderingAllowed(false);
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }

        return respuesta;
    }

    public void table_tipo_afectacion(JTable tabla_igv) {
        try {
            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String sSQL1 = "SELECT * FROM [dbo].[tipo_afectacion] order by afectacion_id  asc";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"", "ID", "DESCRIPCIÒN", "DESCRIPCIÒN", "VALOR"});
            while (rs.next()) {

                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
            }
            cxn.desconectar();
            tabla_igv.setModel(model);
            Function_Component.JTable(tabla_igv);
            tabla_igv.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla_igv.getColumnModel().getColumn(0).setMinWidth(0);
            tabla_igv.getColumnModel().getColumn(0).setPreferredWidth(0);
            tabla_igv.getColumnModel().getColumn(1).setMaxWidth(0);
            tabla_igv.getColumnModel().getColumn(1).setMinWidth(0);
            tabla_igv.getColumnModel().getColumn(1).setPreferredWidth(0);
            tabla_igv.getColumnModel().getColumn(3).setMaxWidth(0);
            tabla_igv.getColumnModel().getColumn(3).setMinWidth(0);
            tabla_igv.getColumnModel().getColumn(3).setPreferredWidth(0);

            tabla_igv.setDragEnabled(false);
            tabla_igv.getTableHeader().setReorderingAllowed(false);
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }

    public void table_compra(JTable compra) {
        try {
            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String sSQL1 = "select compr.compra_id,tipo_compr.comprobante_descrip,compra_serie,compra_numero,tip_mon.moneda_descrip,compra_fecha_emis,\n"
                    + "per.persona_razon_social,compra_total,alm.almacen_descrip from compra compr\n"
                    + "inner join tipo_comprobante tipo_compr\n"
                    + "on tipo_compr.comprobante_id=compr.compra_comprobante\n"
                    + "inner join tipo_moneda tip_mon\n"
                    + "on tip_mon.moneda_id=compr.compra_moneda\n"
                    + "inner join persona per\n"
                    + "on per.persona_id=compr.compra_proveedor\n"
                    + "inner join almacen alm\n"
                    + "on alm.almacen_id=compr.compra_almacen order by compr.compra_id desc";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"", "COMPROBANTE", "SERIE", "NUMERO", "MONEDA", "FECHA", "DENOMINACION", "TOTAL", "ALMACEN"});
            while (rs.next()) {

                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                    rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)});
            }
            cxn.desconectar();
            compra.setModel(model);
            Function_Component.JTable(compra);
            compra.getColumnModel().getColumn(0).setMaxWidth(0);
            compra.getColumnModel().getColumn(0).setMinWidth(0);
            compra.getColumnModel().getColumn(0).setPreferredWidth(0);
            compra.setDragEnabled(false);
            compra.getTableHeader().setReorderingAllowed(false);
            compra.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }
    public void cbxConcepto(JComboBox<Entity_Concepto> concepto) {
        String sent = "SELECT * FROM [dbo].[concepto] order by concepto_id  asc";
        try {
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sent);
            while (rs.next()) {
                concepto.addItem(new Entity_Concepto(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
            cxn.desconectar();
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }

    public void cbxAfectacion(JComboBox<Entity_Tipo_afectacion> Marca) {
        String sent = "SELECT * FROM [dbo].[tipo_afectacion] order by afectacion_id  asc";
        try {
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sent);
            while (rs.next()) {
                Marca.addItem(new Entity_Tipo_afectacion(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            cxn.desconectar();
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }

    public void cbx_tipo_comprobante(JComboBox<Entity_Tipo_Comprobante> comprobante) {
        String sent = "SELECT * FROM tipo_comprobante order by comprobante_id  asc";
        try {
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sent);
            while (rs.next()) {
                comprobante.addItem(new Entity_Tipo_Comprobante(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
            cxn.desconectar();
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }

    public void cbx_tipo_moneda(JComboBox<Entity_Moneda> moneda) {
        String sent = "SELECT * FROM tipo_moneda order by moneda_id  asc";
        try {
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sent);
            while (rs.next()) {
                moneda.addItem(new Entity_Moneda(rs.getString(1), rs.getString(2), rs.getString(3)));
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

    public void cbx_tipo_documento(JComboBox<Entity_Documento> documento) {
        String sent = "SELECT * FROM tipo_documento order by documento_id  asc";
        try {
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sent);
            while (rs.next()) {
                documento.addItem(new Entity_Documento(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
            cxn.desconectar();
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }

    public String Add_proveedor(Entity_Persona persona) {
        String respueta = "";
        String sent = "DECLARE	@return_value int\n"
                + "SET IDENTITY_INSERT persona ON\n"
                + "EXEC	@return_value = [dbo].[Insertapersona]\n"
                + "		@persona_id = N'" + persona.getPersona_id() + "',\n"
                + "		@persona_tipo_documento = N'" + persona.getPersona_tipo_documento() + "',\n"
                + "		@persona_ruc = N'" + persona.getPersona_ruc() + "',\n"
                + "		@persona_dni = N'" + persona.getPersona_dni() + "',\n"
                + "		@persona_razon_social = N'" + persona.getPersona_razon_social() + "',\n"
                + "		@persona_razon_comercial = N'" + persona.getPersona_razon_comercial() + "',\n"
                + "		@persona_direccion = N'" + persona.getPersona_direccion() + "'";
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

    public String Add_compra(Entity_Compra compra) {
        String respuesta = "";

        String sent = "EXEC	 [dbo].[InsertaTablacompra]\n"
                + "		@compra_id = N'" + compra.getCompra_id() + "',\n"
                + "		@compra_comprobante = N'" + compra.getCompra_comprobante() + "',\n"
                + "		@compra_serie = N'" + compra.getCompra_serie() + "',\n"
                + "		@compra_numero = N'" + compra.getCompra_numero() + "',\n"
                + "		@compra_moneda = N'" + compra.getCompra_moneda() + "',\n"
                + "		@compra_afectacion = N'" + compra.getCompra_afectacion() + "',\n"
                + "		@compra_fecha_emis = N'" + compra.getCompra_fecha_emis() + "',\n"
                + "		@compra_fecha_ingreso_alm = N'" + compra.getCompra_fecha_ingreso_alm() + "',\n"
                + "		@compra_fecha_vencimienton = N'" + compra.getCompra_fecha_vencimiento() + "',\n"
                + "		@compra_fecha_contable = N'" + compra.getCompra_fecha_contable() + "',\n"
                + "		@compra_proveedor = N'" + compra.getCompra_proveedor() + "',\n"
                + "		@compra_glosa = N'" + compra.getCompra_glosa() + "',\n"
                + "		@compra_almacen = N'" + compra.getCompra_almacen() + "',\n"
                + "		@compra_persona = N'" + compra.getCompra_persona() + "',\n"
                + "		@compra_estado = N'" + compra.getCompra_estado() + "',\n"
                + "		@compra_estado_almacen = N'" + compra.getCompra_estado_almacen() + "',\n"
                + "             @compra_ingrese_descuento_global = N'" + compra.getCompra_ingrese_descuento_global() + "',\n"
                + "		@compra_descuento_global = N'" + compra.getCompra_descuento_global() + "',\n"
                + "		@compra_descuento_item = N'" + compra.getCompra_descuento_item() + "',\n"
                + "		@compra_gravada = N'" + compra.getCompra_gravada() + "',\n"
                + "		@compra_exonerada = N'" + compra.getCompra_exonerada() + "',\n"
                + "		@compra_inafecta = N'" + compra.getCompra_inafecta() + "',\n"
                + "		@compra_igv = N'" + compra.getCompra_igv() + "',\n"
                + "		@compra_gratuita = N'" + compra.getCompra_gratuita() + "',\n"
                + "		@compra_otros_cargos = N'" + compra.getCompra_otros_cargos() + "',\n"
                + "		@compra_total = N'" + compra.getCompra_total() + "',\n"
                + "		@compra_cambio =N'" + compra.getCompra_cambio() + "',\n"
                + "		@compra_tipo_documento =N'" + compra.getCompra_tipo_documento() + "',\n"
                + "		@compra_concepto =N'" + compra.getCompra_concepto()+ "'";

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

    public String Add_compra_producto_detalle(Entity_Compra_Producto detalle_compra) {
        String respuesta = "";
        String sent = "DECLARE	@return_value int\n"
                + "SET IDENTITY_INSERT detalle_compra_producto off\n"
                + "EXEC	@return_value = [dbo].[InsertaTabladetallecompraproducto]\n"
                + "		@det_com_pro_id = N'" + detalle_compra.getDet_com_pro_id() + "',\n"
                + "		@det_com_pro_id_compra = N'" + detalle_compra.getDet_com_pro_id_compra() + "',\n"
                + "		@det_com_pro_id_producto = N'" + detalle_compra.getDet_com_pro_id_producto() + "',\n"
                + "		@det_com_pro_id_igv = N'" + detalle_compra.getDet_com_pro_id_igv() + "',\n"
                + "		@det_com_pro_cantidad = N'" + detalle_compra.getDet_com_pro_cantidad() + "',\n"
                + "		@det_com_pro_precio = N'" + detalle_compra.getDet_com_pro_precio() + "',\n"
                + "		@det_com_pro_sub_total = N'" + detalle_compra.getDet_com_pro_sub_total() + "',\n"
                + "		@det_com_pro_total = N'" + detalle_compra.getDet_com_pro_total() + "',\n"
                + "		@det_com_pro_desc_pprint = N'" + detalle_compra.getDet_com_pro_desc_ppr() + "',\n"
                + "		@det_com_pro_des_Valor = N'" + detalle_compra.getDet_com_pro_des_Valor() + "',\n"
                + "		@det_com_pro_fecha_venc = N'" + detalle_compra.getDet_com_pro_fecha_venc() + "',\n"
                + "		@det_com_pro_cod_lote = N'" + detalle_compra.getDet_com_pro_cod_lote() + "'";

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

    public String delete_compra_producto(String compra) {
        String respuesta = "";
        String sent = "delete from detalle_compra_producto where det_com_pro_id='" + compra + "'";

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

    public String delete_compra(String compra) {
        String respuesta = "";
        String sent = "DECLARE	@return_value int\n"
                + "\n"
                + "EXEC	@return_value = [dbo].[delete_compra]\n"
                + "		@id_producto = N'" + compra + "'";

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

    public String update_cod_Productos(String cod_barra, String producto) {
        String respuesta = "";
        String sent = "UPDATE producto set producto_cod='" + cod_barra + "' where producto_id='" + producto + "'";

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

    public void add_presentacion(JTable tabla, JDialog dialog_producto, JTable tabla_producto, JDialog dialog_igv, JTable tabla_igv, String id_Afectacion, String afectacion, String valor_afectacion, String valor_cod_afectacion) {
        JButton btnEliminar = new JButton("");
        btnEliminar.setName("btneliminar");
        btnEliminar.setIcon(new ImageIcon("src\\Icon\\icon_eliminar.png"));
        tabla.setDefaultRenderer(Object.class, new Render());
        model = new DefaultTableModel(new Object[]{"ELI", "ID_PROD_COMPR", "ID_PROD", "COD BARRA", "DESCRIPCION", "MEDIDA", "ID_IGV",
            "IGV", "IGV_VALOR", "AFECTACION", "CANTIDAD", "PRECIO COMP", "SUB_TOTAL", "PRECIO TOTAL", "DESC %", "DESC VALOR", "FECHA VENCIMIENTO", "COD LOTE", "STOCK"}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
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
        model.addRow(new Object[]{btnEliminar, "", "", "", "", "", id_Afectacion, afectacion, valor_afectacion, valor_cod_afectacion, "", "", "", "", "0", "0", "", ""});

        tabla.setModel(model);
        Function_Key_Event.Validar_numeros_jtable(tabla);
        tabla.getColumnModel().getColumn(0).setMaxWidth(50);
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
        tabla.getColumnModel().getColumn(8).setMaxWidth(0);
        tabla.getColumnModel().getColumn(8).setMinWidth(0);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(9).setMaxWidth(0);
        tabla.getColumnModel().getColumn(9).setMinWidth(0);
        tabla.getColumnModel().getColumn(9).setPreferredWidth(0);
        settext_cod_barra(tabla, tabla.getColumnModel().getColumn(3), dialog_producto, tabla_producto);
        settext_igv(tabla, tabla.getColumnModel().getColumn(7), dialog_igv, tabla_igv);
        settext_cantidad(tabla, tabla.getColumnModel().getColumn(10), null);
        settext_p_compra(tabla, tabla.getColumnModel().getColumn(11), null);
        settext_p_sub_total(tabla, tabla.getColumnModel().getColumn(12), null);
        settext_p_desc_porcentaje(tabla, tabla.getColumnModel().getColumn(13), null);
        settext_p_total(tabla, tabla.getColumnModel().getColumn(13), null);
        settext_p_desc_porcentaje(tabla, tabla.getColumnModel().getColumn(14), null);
        settext_p_desc_valor(tabla, tabla.getColumnModel().getColumn(15), null);
        settext_p_fecha(tabla, tabla.getColumnModel().getColumn(16), null);
        settext_cod_lote(tabla, tabla.getColumnModel().getColumn(17), null);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.setDragEnabled(false);
        tabla.getTableHeader().setReorderingAllowed(false);

    }

    public void compra_detalle(JTable tabla, JDialog dialog_producto, JTable table_producto, JDialog dialog_igv, JTable table_igv, String cod_compra) {
        JButton btnEliminar = new JButton("");
        btnEliminar.setName("btneliminar");
        btnEliminar.setIcon(new ImageIcon("src\\Icon\\icon_eliminar.png"));
        tabla.setDefaultRenderer(Object.class, new Render());
        model = new DefaultTableModel(new Object[]{"ELI", "ID_PROD_COMPR", "ID_PROD", "COD BARRA", "DESCRIPCION", "MEDIDA", "ID_IGV",
            "IGV", "IGV_VALOR", "AFECTACION", "CANTIDAD", "PRECIO COMP", "SUB_TOTAL", "PRECIO TOTAL", "DESC %", "DESC VALOR", "FECHA VENCIMIENTO", "COD LOTE", "STOCK"}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
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
            String sSQL1 = " select \n"
                    + "det_compra.det_com_pro_id,\n"
                    + "pro_p.present_id,pro_p.present_cod_barra,pro_p.present_descripcion,ti_uni.unidad_cod,\n"
                    + "ti_af.afectacion_id,ti_af.afectacion_descr,ti_af.afectacion_valor,ti_af.afectacion_cod_valor,\n"
                    + "det_compra.det_com_pro_cantidad,det_compra.det_com_pro_precio,det_compra.det_com_pro_sub_total,det_compra.det_com_pro_total,\n"
                    + "det_compra.det_com_pro_desc_ppr,det_compra.det_com_pro_des_Valor,det_compra.det_com_pro_fecha_venc,det_compra.det_com_pro_cod_lote,					\n"
                    + "((ISNULL((select  sum(dt_com.det_com_pro_cantidad*pre.present_cantidad) from producto pro\n"
                    + "inner join  producto_presentacion pre\n"
                    + "on pre.present_cod_producto=pro.producto_id\n"
                    + "inner join detalle_compra_producto dt_com\n"
                    + "on dt_com.det_com_pro_id_producto=pre.present_id\n"
                    + "where pro.producto_id=pro_p.present_cod_producto\n"
                    + "group by pro.producto_id),0))-(ISNULL((select  sum(dt_ven.det_ven_pro_cantidad*pre.present_cantidad) from producto pro\n"
                    + "inner join  producto_presentacion pre\n"
                    + "on pre.present_cod_producto=pro.producto_id\n"
                    + "inner join detalle_venta_producto dt_ven\n"
                    + "on dt_ven.det_ven_pro_id_producto=pre.present_id\n"
                    + "where pro.producto_id=pro_p.present_cod_producto\n"
                    + "group by pro.producto_id),0)))AS TOTAL\n"
                    + "from detalle_compra_producto det_compra\n"
                    + "inner join compra comp\n"
                    + "on comp.compra_id=det_compra.det_com_pro_id_compra\n"
                    + "inner join producto_presentacion pro_p\n"
                    + "on pro_p.present_id=det_compra.det_com_pro_id_producto\n"
                    + "inner join tipo_afectacion ti_af\n"
                    + "on ti_af.afectacion_id=det_compra.det_com_pro_id_igv\n"
                    + "inner join tipo_unidad ti_uni\n"
                    + "on ti_uni.unidad_id=pro_p.present_cod_unidad\n"
                    + "where det_compra.det_com_pro_id_compra='" + cod_compra + "'\n"
                    + "order by det_compra.det_com_pro_id asc";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            while (rs.next()) {
                model.addRow(new Object[]{btnEliminar, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
                    rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
                    rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18)});

            }
            cxn.desconectar();
        } catch (Exception e) {

        }
        tabla.setModel(model);
        Function_Key_Event.Validar_numeros_jtable(tabla);
        /*for (int i = 0; i < tabla.getRowCount(); i++) {
            
            
        }*/
 /* int contador=0;
        int valor=0;
        
            
        
            for (int j = 0; j < tabla.getColumnCount(); j++) {
                for (int i = 0; i < tabla.getRowCount(); i++) {
                    if (tabla.getValueAt(i, j).toString().length()>=contador) {
                        valor=tabla.getValueAt(i, j).toString().length();
                    }
                    
                    
                }
                
                
                tabla.getColumnModel().getColumn(j).setMaxWidth(valor*20);
            }
        
         */

        tabla.getColumnModel().getColumn(0).setMaxWidth(50);
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
        tabla.getColumnModel().getColumn(8).setMaxWidth(0);
        tabla.getColumnModel().getColumn(8).setMinWidth(0);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(9).setMaxWidth(0);
        tabla.getColumnModel().getColumn(9).setMinWidth(0);
        tabla.getColumnModel().getColumn(9).setPreferredWidth(0);

        settext_cod_barra(tabla, tabla.getColumnModel().getColumn(3), dialog_producto, table_producto);
        settext_igv(tabla, tabla.getColumnModel().getColumn(7), dialog_igv, table_igv);

        settext_cantidad(tabla, tabla.getColumnModel().getColumn(10), null);
        settext_p_compra(tabla, tabla.getColumnModel().getColumn(11), null);
        settext_p_sub_total(tabla, tabla.getColumnModel().getColumn(12), null);
        settext_p_desc_porcentaje(tabla, tabla.getColumnModel().getColumn(13), null);
        settext_p_total(tabla, tabla.getColumnModel().getColumn(13), null);
        settext_p_desc_porcentaje(tabla, tabla.getColumnModel().getColumn(14), null);
        settext_p_desc_valor(tabla, tabla.getColumnModel().getColumn(15), null);
        settext_p_fecha(tabla, tabla.getColumnModel().getColumn(16), null);
        settext_cod_lote(tabla, tabla.getColumnModel().getColumn(17), null);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.setDragEnabled(false);
        tabla.getTableHeader().setReorderingAllowed(false);

    }

    public void settext_cod_barra(JTable tabla, TableColumn columna, JDialog dialogo, JTable producto) {
        try {

            JTextField txt_cod_barra = new JTextField();

            Caret seleccion = txt_cod_barra.getCaret();
            System.out.println("Controller.Controller_Ingreso_Almacen.settext_cod_barra()" + seleccion.getMark());
            Function_Component.JTextField(txt_cod_barra);
            columna.setCellEditor(new DefaultCellEditor(txt_cod_barra));
            DefaultTableCellRenderer render = new DefaultTableCellRenderer();
            txt_cod_barra.addKeyListener(new KeyListener() {
                @Override
                public void keyPressed(KeyEvent evt) {
                    try {
                        char cteclap = evt.getKeyChar();
                        if (evt.getKeyCode() == KeyEvent.VK_F1) {
                            Controller_compra almacen = new Controller_compra();
                            if (almacen.table_produto_busqueda(producto, txt_cod_barra.getText()).equals("ERROR")) {
                                Function_ShowMessageDialog.ShowMessageDialogvalidacion(null, "BUSQUEDA NO ENCONTRADA");

                            } else {
                                Dimension desktopSize = View_Escritorio.desktopPane.getSize();
                                Dimension FrameSize = dialogo.getSize();
                                dialogo.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
                                dialogo.setVisible(true);
                                tabla.editCellAt(tabla.getSelectedRow() + getselection, 7);
                                Component aComp = tabla.getEditorComponent();
                                aComp.requestFocus();
                                producto.requestFocus();
                                View_Compra.btn_calcular.doClick();

                            }
                            evt.consume();
                        }
                        if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_P) {
                            Controller_compra almacen = new Controller_compra();
                            if (almacen.table_produto_busqueda_presentacion(producto, txt_cod_barra.getText()).equals("ERROR")) {
                                Function_ShowMessageDialog.ShowMessageDialogvalidacion(null, "BUSQUEDA NO ENCONTRADA");

                            } else {
                                Dimension desktopSize = View_Escritorio.desktopPane.getSize();
                                Dimension FrameSize = dialogo.getSize();
                                dialogo.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
                                dialogo.setVisible(true);
                                tabla.editCellAt(tabla.getSelectedRow() + getselection, 7);
                                Component aComp = tabla.getEditorComponent();
                                aComp.requestFocus();
                                producto.requestFocus();
                                View_Compra.btn_calcular.doClick();

                            }
                            evt.consume();
                        }
                        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

                            List<Entity_Producto_Presentacion> persona = new Controller_compra().buscar_cod_barra_producto_presentacion(txt_cod_barra.getText());

                            if (persona.size() == 0) {
                                Controller_compra almacen = new Controller_compra();
                                if (almacen.table_produto_busqueda(producto, txt_cod_barra.getText()).equals("ERROR")) {
                                    Function_ShowMessageDialog.ShowMessageDialogvalidacion(null, "BUSQUEDA NO ENCONTRADA");

                                } else {
                                    Dimension desktopSize = View_Escritorio.desktopPane.getSize();
                                    Dimension FrameSize = dialogo.getSize();
                                    dialogo.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
                                    dialogo.setVisible(true);
                                    tabla.editCellAt(tabla.getSelectedRow() + getselection, 10);
                                    Component aComp = tabla.getEditorComponent();
                                    aComp.requestFocus();
                                    producto.requestFocus();

                                }

                            }
                            tabla.setValueAt(persona.get(0).getPresent_id(), tabla.getSelectedRow() + getselection, 2);
                            tabla.setValueAt(persona.get(0).getPresent_cod_barra(), tabla.getSelectedRow() + getselection, 3);
                            tabla.setValueAt(persona.get(0).getPresent_descripcion(), tabla.getSelectedRow() + getselection, 4);
                            tabla.setValueAt(persona.get(0).getPresent_cod_unidad(), tabla.getSelectedRow() + getselection, 5);
                            //tabla.setValueAt(persona.get(0).getPresent_id(), getselection);
                            tabla.setValueAt(persona.get(0).getPresent_prec_compra(), tabla.getSelectedRow() + getselection, 11);
                            tabla.setValueAt(persona.get(0).getStock(), tabla.getSelectedRow() + getselection, 18);

                            tabla.editCellAt(tabla.getSelectedRow() + getselection, 7);

                            Component aComp = tabla.getEditorComponent();
                            aComp.requestFocus();
                            View_Compra.btn_calcular.doClick();
                            evt.consume();

                        }

                    } catch (Exception e) {
                        System.out.println(".keyPressed()" + e);
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

    public void settext_igv(JTable tabla, TableColumn columna, JDialog dialogo, JTable tabla_igv) {

        JTextField txt_unidad_medida = new JTextField();
        Function_Component.JTextField(txt_unidad_medida);
        Function_Key_Event.Validar_Mayuscula(txt_unidad_medida);
        columna.setCellEditor(new DefaultCellEditor(txt_unidad_medida));
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        txt_unidad_medida.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent evt) {
                char cteclap = evt.getKeyChar();
                if (evt.getKeyCode() == KeyEvent.VK_F1) {
                    Controller_compra almacen = new Controller_compra();
                    almacen.table_tipo_afectacion(tabla_igv);

                    Dimension desktopSize = View_Escritorio.desktopPane.getSize();
                    Dimension FrameSize = dialogo.getSize();
                    dialogo.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
                    dialogo.setVisible(true);

                    tabla.editCellAt(tabla.getSelectedRow() + getselection, 10);
                    Component aComp = tabla.getEditorComponent();
                    aComp.requestFocus();
                    tabla_igv.requestFocus();
                    View_Compra.btn_calcular.doClick();

                }
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    tabla.editCellAt(tabla.getSelectedRow() + getselection, 10);
                    Component aComp = tabla.getEditorComponent();
                    aComp.requestFocus();
                    View_Compra.btn_calcular.doClick();

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
                            View_Compra.btn_calcular.doClick();

                        }
                        evt.consume();

                    }
                    if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                        try {

                            if (tabla.getSelectedRow() + getselection == tabla.getRowCount() - 1) {
                                getselection = getselection + 1;

                                View.View_Compra.btn_agregar_fila.doClick();
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

                }

                @Override
                public void keyTyped(KeyEvent e) {
                }

                @Override
                public void keyReleased(KeyEvent e) {
                    try {
                        if (txt_cantidad.getText().length() > 0) {
                            DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
                            separadoresPersonalizados.setDecimalSeparator('.');
                            DecimalFormat formato2 = new DecimalFormat("################.######", separadoresPersonalizados);

                            tabla.setValueAt(formato2.format((Double.parseDouble(txt_cantidad.getText()) * Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 11).toString()))),
                                    tabla.getSelectedRow() + getselection, 12);
                            tabla.setValueAt(formato2.format((((Double.parseDouble(txt_cantidad.getText()) * Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 11).toString()))
                                    * Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 8).toString()))
                                    + (Double.parseDouble(txt_cantidad.getText())
                                    * Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 11).toString())))),
                                    tabla.getSelectedRow() + getselection, 13);
                        }
                    } catch (Exception es) {

                    }

                }

            });

        } catch (Exception e) {
        }

    }

    public void settext_p_compra(JTable tabla, TableColumn columna, JDialog dialogo) {

        JTextField txt_p_compra = new JTextField();
        Function_Component.JTextField(txt_p_compra);
        Function_Key_Event.Validar_numeros(txt_p_compra);
        columna.setCellEditor(new DefaultCellEditor(txt_p_compra));
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        txt_p_compra.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent evt) {

                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (txt_p_compra.getText().length() != 0) {
                        tabla.editCellAt(tabla.getSelectedRow() + getselection, 13);
                        Component aComp = tabla.getEditorComponent();
                        aComp.requestFocus();
                        View_Compra.btn_calcular.doClick();

                    }
                    evt.consume();

                }
                if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                    try {

                        if (tabla.getSelectedRow() + getselection == tabla.getRowCount() - 1) {
                            getselection = getselection + 1;

                            View.View_Compra.btn_agregar_fila.doClick();
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

            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (txt_p_compra.getText().length() > 0) {
                    DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
                    separadoresPersonalizados.setDecimalSeparator('.');
                    DecimalFormat formato2 = new DecimalFormat("################.######", separadoresPersonalizados);

                    tabla.setValueAt(formato2.format((Double.parseDouble(txt_p_compra.getText()) * Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 10).toString()))),
                            tabla.getSelectedRow() + getselection, 12);

                    tabla.setValueAt(formato2.format((((Double.parseDouble(txt_p_compra.getText()) * Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 10).toString()))
                            * Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 8).toString()))
                            + (Double.parseDouble(txt_p_compra.getText())
                            * Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 10).toString())))),
                            tabla.getSelectedRow() + getselection, 13);
                }
            }

        });

    }

    public void settext_p_sub_total(JTable tabla, TableColumn columna, JDialog dialogo) {

        JTextField txt_p_sub_total = new JTextField();
        Function_Component.JTextField(txt_p_sub_total);
        Function_Key_Event.Validar_numeros(txt_p_sub_total);
        columna.setCellEditor(new DefaultCellEditor(txt_p_sub_total));
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        txt_p_sub_total.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent evt) {

                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (txt_p_sub_total.getText().length() != 0) {

                        tabla.editCellAt(tabla.getSelectedRow() + getselection, 13);
                        Component aComp = tabla.getEditorComponent();
                        aComp.requestFocus();
                        View_Compra.btn_calcular.doClick();

                    }
                    evt.consume();
                }
                if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                    try {

                        if (tabla.getSelectedRow() + getselection == tabla.getRowCount() - 1) {
                            getselection = getselection + 1;

                            View.View_Compra.btn_agregar_fila.doClick();
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
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        });

    }

    public void settext_p_total(JTable tabla, TableColumn columna, JDialog dialogo) {

        JTextField txt_p_venta = new JTextField();
        Function_Component.JTextField(txt_p_venta);
        Function_Key_Event.Validar_numeros(txt_p_venta);
        columna.setCellEditor(new DefaultCellEditor(txt_p_venta));
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        txt_p_venta.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent evt) {
                char cteclap = evt.getKeyChar();
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    //getselection = getselection + 1;

                    if (txt_p_venta.getText().length() != 0) {

                        tabla.editCellAt(tabla.getSelectedRow() + getselection, 14);
                        Component aComp = tabla.getEditorComponent();
                        aComp.requestFocus();
                        View_Compra.btn_calcular.doClick();

                    }
                    evt.consume();
                }
                if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                    try {

                        if (tabla.getSelectedRow() + getselection == tabla.getRowCount() - 1) {
                            getselection = getselection + 1;

                            View.View_Compra.btn_agregar_fila.doClick();
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
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (txt_p_venta.getText().length() > 0) {

                    DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
                    separadoresPersonalizados.setDecimalSeparator('.');
                    DecimalFormat formato2 = new DecimalFormat("################.######", separadoresPersonalizados);

                    tabla.setValueAt(formato2.format((((Double.parseDouble(txt_p_venta.getText())
                            / (Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 8).toString()) + 1))))),
                            tabla.getSelectedRow() + getselection, 12);

                    tabla.setValueAt(formato2.format((Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 12).toString()))
                            / (Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 10).toString()))),
                            tabla.getSelectedRow() + getselection, 11);
                }

            }

        });

    }

    public void settext_p_desc_porcentaje(JTable tabla, TableColumn columna, JDialog dialogo) {

        JTextField txt_p_desc_porcentaje = new JTextField();
        Function_Component.JTextField(txt_p_desc_porcentaje);
        Function_Key_Event.Validar_numeros(txt_p_desc_porcentaje);
        columna.setCellEditor(new DefaultCellEditor(txt_p_desc_porcentaje));
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        txt_p_desc_porcentaje.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent evt) {

                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (txt_p_desc_porcentaje.getText().length() != 0) {
                        DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
                        separadoresPersonalizados.setDecimalSeparator('.');
                        DecimalFormat formato2 = new DecimalFormat("################.######", separadoresPersonalizados);

                        tabla.setValueAt(formato2.format((Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 10).toString())
                                * Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 11).toString()))
                                - ((Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 10).toString())
                                * Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 11).toString()))
                                * Double.parseDouble(txt_p_desc_porcentaje.getText()))),
                                tabla.getSelectedRow() + getselection, 12);

                        tabla.setValueAt(formato2.format((Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 12).toString())) + ((Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 12).toString())) * (Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 8).toString())))),
                                tabla.getSelectedRow() + getselection, 13);
                        tabla.editCellAt(tabla.getSelectedRow() + getselection, 15);
                        Component aComp = tabla.getEditorComponent();
                        aComp.requestFocus();
                        View_Compra.btn_calcular.doClick();

                    }
                    evt.consume();
                }
                if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                    try {

                        if (tabla.getSelectedRow() + getselection == tabla.getRowCount() - 1) {
                            getselection = getselection + 1;

                            View.View_Compra.btn_agregar_fila.doClick();
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
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        });

    }

    public void settext_p_desc_valor(JTable tabla, TableColumn columna, JDialog dialogo) {

        JTextField txt_p_desc_valor = new JTextField();
        Function_Component.JTextField(txt_p_desc_valor);
        Function_Key_Event.Validar_numeros(txt_p_desc_valor);
        columna.setCellEditor(new DefaultCellEditor(txt_p_desc_valor));
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        txt_p_desc_valor.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent evt) {

                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (txt_p_desc_valor.getText().length() != 0) {
                        DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
                        separadoresPersonalizados.setDecimalSeparator('.');
                        DecimalFormat formato2 = new DecimalFormat("################.######", separadoresPersonalizados);

                        tabla.setValueAt(formato2.format((Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 10).toString()) * Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 11).toString())) - Double.parseDouble(txt_p_desc_valor.getText())),
                                tabla.getSelectedRow() + getselection, 12);
                        tabla.setValueAt(formato2.format(((Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 10).toString()) * Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 11).toString())) - Double.parseDouble(txt_p_desc_valor.getText())) + ((Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 10).toString()) * Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 11).toString())) - Double.parseDouble(txt_p_desc_valor.getText())) * (Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 8).toString()))),
                                tabla.getSelectedRow() + getselection, 13);

                        tabla.editCellAt(tabla.getSelectedRow() + getselection, 16);
                        Component aComp = tabla.getEditorComponent();
                        aComp.requestFocus();
                        View_Compra.btn_calcular.doClick();

                    }
                    evt.consume();
                }
                if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                    try {

                        if (tabla.getSelectedRow() + getselection == tabla.getRowCount() - 1) {
                            getselection = getselection + 1;

                            View.View_Compra.btn_agregar_fila.doClick();
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
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        });

    }

    public void settext_p_fecha(JTable tabla, TableColumn columna, JDialog dialogo) {

        try {

            MaskFormatter mascara = new MaskFormatter("####-##-##");
            // mascara.setPlaceholderCharacter("AAAA-MM-DD");
            JFormattedTextField txt_p_fecha = new JFormattedTextField(mascara);

            //JTextField txt_p_fecha = new JTextField();
            Function_Component.JTextField(txt_p_fecha);
            Function_Key_Event.Validar_numeros(txt_p_fecha);
            columna.setCellEditor(new DefaultCellEditor(txt_p_fecha));
            DefaultTableCellRenderer render = new DefaultTableCellRenderer();
            txt_p_fecha.addKeyListener(new KeyListener() {
                @Override
                public void keyPressed(KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                        //getselection = getselection + 1;
                        tabla.editCellAt(tabla.getSelectedRow() + getselection, 17);
                        Component aComp = tabla.getEditorComponent();
                        aComp.requestFocus();
                    }
                    if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                        try {

                            if (tabla.getSelectedRow() + getselection == tabla.getRowCount() - 1) {
                                getselection = getselection + 1;

                                View.View_Compra.btn_agregar_fila.doClick();
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

    public void settext_cod_lote(JTable tabla, TableColumn columna, JDialog dialogo) {

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

                            View.View_Compra.btn_agregar_fila.doClick();
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

                            View.View_Compra.btn_agregar_fila.doClick();
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
            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        });

    }

    public String generar_cod_compra() {
        String valor = "";
        String sent = "SELECT (CASE   WHEN (MAX(compra_id) > 0) THEN (MAX(compra_id) + 1)  ELSE 1    END) AS maximo FROM compra";
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
        String sent = "select present.present_id\n"
                + ",present.present_cod_barra\n"
                + ",present.present_descripcion\n"
                + ",uni.unidad_cod\n"
                + ",present.present_cantidad\n"
                + ",present.present_prec_compra\n"
                + ",present.present_cod_producto,\n"
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
                + "FROM producto_presentacion present \n"
                + "INNER JOIN tipo_unidad uni\n"
                + "on uni.unidad_id=present.present_cod_unidad\n"
                + "where present.present_estado='true' and present.present_cod_barra like '" + cod_barra + "'\n"
                + "order by present.present_cod_barra asc";
        try {
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sent);
            rs.next();
            persona.add(new Entity_Producto_Presentacion(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(8)));
            //System.out.println("Controller.Controller_Ingreso_Almacen.buscar_cod_barra_producto_presentacion()"+rs.getString(8));
            cxn.desconectar();
        } catch (SQLException ex) {
            // Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        return persona;
    }

    public List<Entity_Compra> buscar_compra(String compra) {
        List<Entity_Compra> persona = new ArrayList<Entity_Compra>();

        String sent = "select TOP 1 compr.compra_id,compr.compra_comprobante,compr.compra_serie,compr.compra_numero,\n" +
"compr.compra_moneda,compr.compra_afectacion,compr.compra_fecha_emis,compr.compra_fecha_ingreso_alm,\n" +
"compr.compra_fecha_vencimiento,compr.compra_fecha_contable,compr.compra_proveedor,compr.compra_glosa,\n" +
"compr.compra_almacen,compr.compra_persona,compr.compra_estado,compr.compra_estado_almacen,compr.compra_ingrese_descuento_global,\n" +
"compr.compra_descuento_global,compr.compra_descuento_item,compr.compra_gravada,compr.compra_exonerada,compr.compra_inafecta,\n" +
"compr.compra_igv,compr.compra_gratuita,compr.compra_otros_cargos,compr.compra_total,compr.compra_cambio,compr.compra_tipo_documento,\n" +
"tipo_compr.comprobante_id,tipo_compr.comprobante_cod,tipo_compr.comprobante_descrip,tip_mon.moneda_id,\n" +
"tip_mon.moneda_cod,tip_mon.moneda_descrip,tipo_afec.afectacion_id,tipo_afec.afectacion_cod,tipo_afec.afectacion_descr,\n" +
"tipo_afec.afectacion_valor,tipo_afec.afectacion_cod_valor,per.persona_id,per.persona_tipo_documento,per.persona_ruc,\n" +
"per.persona_dni,per.persona_razon_social,per.persona_razon_comercial,per.persona_direccion,alm.almacen_id,alm.almacen_cod,\n" +
"alm.almacen_descrip,tipo_doc.documento_id,tipo_doc.documento_cod,tipo_doc.documento_descrip,tipo_doc.documento_url,\n" +
"conc.concepto_id,conc.concepto_cod,conc.concepto_descripcion,ent.entrega_id,ent.entrega_descripcion,ent.entrega_año from compra compr\n" +
"inner join tipo_comprobante tipo_compr\n" +
"on tipo_compr.comprobante_id=compr.compra_comprobante\n" +
"inner join tipo_moneda tip_mon\n" +
"on tip_mon.moneda_id=compr.compra_moneda\n" +
"inner join tipo_afectacion tipo_afec\n" +
"on tipo_afec.afectacion_id=compr.compra_afectacion\n" +
"inner join persona per\n" +
"on per.persona_id=compr.compra_proveedor\n" +
"inner join almacen alm\n" +
"on alm.almacen_id=compr.compra_almacen\n" +
"inner join tipo_documento tipo_doc\n" +
"on tipo_doc.documento_id=per.persona_tipo_documento\n" +
"inner join concepto conc\n" +
"on conc.concepto_id=compr.compra_concepto \n" +
"inner join entrega ent\n" +
"on ent.entrega_id=compr.compra_entrega\n" +
"where compr.compra_id='"+compra+"'";
        try {
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sent);
            rs.next();
            persona.add(new Entity_Compra(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                    rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
                    rs.getString(13), rs.getString(14), rs.getBoolean(15), rs.getBoolean(16), rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20),
                    rs.getString(21), rs.getString(22), rs.getString(23), rs.getString(24), rs.getString(25), rs.getString(26), rs.getString(27),
                    rs.getString(28), rs.getString(29), rs.getString(30), rs.getString(31), rs.getString(32), rs.getString(33), rs.getString(34),
                    rs.getString(35), rs.getString(36), rs.getString(37), rs.getString(38), rs.getString(39), rs.getString(40), rs.getString(41), rs.getString(42),
                    rs.getString(43), rs.getString(44), rs.getString(45), rs.getString(46), rs.getString(47), rs.getString(48), rs.getString(49), rs.getString(50),
                    rs.getString(51), rs.getString(52), rs.getString(53),rs.getString(54),rs.getString(55),rs.getString(56),rs.getString(57),rs.getString(58),rs.getString(59)));
            cxn.desconectar();
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        return persona;
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
