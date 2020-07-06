package Controller;

import Conexion.Conexion;
import Config_Class.Render;
import Entity.Entity_Almacen;
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
public class Controller_salida {

    private Conexion cxn;
    private int getselection = 0;
    private boolean[] editable = {false, false, false, true, false, false, false, true, false, false, true, true, false, true, true, true, true, true, true, false};
    DefaultTableModel model;

    DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
    DecimalFormat formato2;

    /*private generador_codigo generador_id;
    Function_ShowMessageDialog mensaje = new Function_ShowMessageDialog();*/
    public Controller_salida() {

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

    public String table_produto_busqueda(JTable clasificacion_producto, String busqueda) {
        String respuesta = "";
        try {

            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String sSQL1 = " SELECT pre.present_id,\n"
                    + "pre.present_cod_barra,\n"
                    + "pre.present_descripcion,\n"
                    + "fa.familia_descrip,\n"
                    + "uni.unidad_cod,\n"
                    + "pre.present_cantidad,\n"
                    + "pre.present_prec_venta,\n"
                    + "afec.afectacion_id,afec.afectacion_descr,afec.afectacion_valor,afec.afectacion_cod_valor\n"
                    + "FROM producto_presentacion pre\n"
                    + "INNER JOIN tipo_unidad uni\n"
                    + "on uni.unidad_id=pre.present_cod_unidad\n"
                    + "inner join producto pro\n"
                    + "on pro.producto_id=pre.present_cod_producto\n"
                    + "inner join tipo_afectacion afec\n"
                    + "on afec.afectacion_id=pro.producto_tipo_afect\n"
                    + "inner join familia fa \n"
                    + "on fa.familia_id=pro.producto_familia\n"
                    + "where pre.present_estado='true'  and pre.present_descripcion like '%"+busqueda+"%' "
                    + " order by pro.producto_id asc";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"", "COD_BARRA", "DESCRIPCIÒN", "MARCA","PRES", "CANT", "PRECIO_VENT", "", "", "", ""});

            while (rs.next()) {
                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
                    rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),rs.getString(11)});
            }

            cxn.desconectar();
            clasificacion_producto.setModel(model);
            Function_Component.JTable(clasificacion_producto);
            clasificacion_producto.getColumnModel().getColumn(0).setMaxWidth(0);
            clasificacion_producto.getColumnModel().getColumn(0).setMinWidth(0);
            clasificacion_producto.getColumnModel().getColumn(0).setPreferredWidth(0);
            clasificacion_producto.getColumnModel().getColumn(1).setMaxWidth(150);
            clasificacion_producto.getColumnModel().getColumn(1).setMinWidth(150);
            clasificacion_producto.getColumnModel().getColumn(1).setPreferredWidth(150);
            clasificacion_producto.getColumnModel().getColumn(3).setMaxWidth(110);
            clasificacion_producto.getColumnModel().getColumn(3).setMinWidth(110);
            clasificacion_producto.getColumnModel().getColumn(3).setPreferredWidth(110);
            clasificacion_producto.getColumnModel().getColumn(4).setMaxWidth(50);
            clasificacion_producto.getColumnModel().getColumn(4).setMinWidth(50);
            clasificacion_producto.getColumnModel().getColumn(4).setPreferredWidth(50);
            clasificacion_producto.getColumnModel().getColumn(5).setMaxWidth(70);
            clasificacion_producto.getColumnModel().getColumn(5).setMinWidth(70);
            clasificacion_producto.getColumnModel().getColumn(5).setPreferredWidth(70);
            clasificacion_producto.getColumnModel().getColumn(6).setMaxWidth(0);
            clasificacion_producto.getColumnModel().getColumn(6).setMinWidth(0);
            clasificacion_producto.getColumnModel().getColumn(6).setPreferredWidth(0);
            clasificacion_producto.getColumnModel().getColumn(7).setMaxWidth(0);
            clasificacion_producto.getColumnModel().getColumn(7).setMinWidth(0);
            clasificacion_producto.getColumnModel().getColumn(7).setPreferredWidth(0);
            clasificacion_producto.getColumnModel().getColumn(8).setMaxWidth(0);
            clasificacion_producto.getColumnModel().getColumn(8).setMinWidth(0);
            clasificacion_producto.getColumnModel().getColumn(8).setPreferredWidth(0);
            clasificacion_producto.getColumnModel().getColumn(9).setMaxWidth(0);
            clasificacion_producto.getColumnModel().getColumn(9).setMinWidth(0);
            clasificacion_producto.getColumnModel().getColumn(9).setPreferredWidth(0);
            clasificacion_producto.getColumnModel().getColumn(10).setMaxWidth(0);
            clasificacion_producto.getColumnModel().getColumn(10).setMinWidth(0);
            clasificacion_producto.getColumnModel().getColumn(10).setPreferredWidth(0);

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

    public String table_lote(JTable tabla_lote, String id_producto) {
        String respuesta = "";
        try {
            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String sSQL1 = "select max(cast(det_com_pro_fecha_produc as varchar(max)))  as det_com_pro_fecha_produc,det_com.det_com_pro_fecha_venc,\n"
                    + "det_com.det_com_pro_cod_lote,\n"
                    + "( ISNULL((SELECT\n"
                    + "sum(det_comp.det_com_pro_cantidad *\n"
                    + "present.present_cantidad)\n"
                    + "FROM\n"
                    + "producto_presentacion present INNER JOIN producto produt ON present.present_cod_producto = produt.producto_id\n"
                    + "INNER JOIN detalle_compra_producto det_comp ON present.present_id = det_comp.det_com_pro_id_producto\n"
                    + "inner join compra compr on compr.compra_id=det_comp.det_com_pro_id_compra\n"
                    + "WHERE\n"
                    + "det_comp.det_com_pro_cod_lote ='J860' AND   det_comp.det_com_pro_fecha_venc =det_com.det_com_pro_fecha_venc and present.present_cod_producto=(select top 1 present_cod_producto from producto_presentacion  where present_id='" + id_producto + "') \n"
                    + "  ) ,0)-isNULL(( SELECT sum(det_ven.det_ven_pro_cantidad*\n"
                    + "present.present_cantidad ) AS total\n"
                    + "FROM\n"
                    + "producto_presentacion present INNER JOIN producto produt ON present.present_cod_producto = produt.producto_id\n"
                    + "INNER JOIN detalle_venta_producto det_ven ON present.present_id = det_ven.det_ven_pro_id_producto\n"
                    + "INNER JOIN venta vent on vent.venta_id=det_ven.det_ven_pro_id_venta\n"
                    + "WHERE\n"
                    + "det_ven.det_ven_pro_cod_lote ='J860'  AND    det_ven.det_ven_pro_fecha =det_com.det_com_pro_fecha_venc and present.present_cod_producto=(select top 1 present_cod_producto from producto_presentacion  where present_id='" + id_producto + "') \n"
                    + "    ),0)  )  AS ENTRADA from\n"
                    + "producto produc\n"
                    + "right JOIN producto_presentacion present\n"
                    + "on present.present_cod_producto =produc.producto_id\n"
                    + "right join detalle_compra_producto det_com\n"
                    + "on det_com.det_com_pro_id_producto=present.present_id\n"
                    + "where produc.producto_id=(select top 1 present_cod_producto from producto_presentacion  where present_id='" + id_producto + "') \n"
                    + "group by produc.producto_id,det_com.det_com_pro_cod_lote,det_com.det_com_pro_fecha_venc";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"ID", "FECHA VEN", "COD_LOTE", "CANTIDAD"});

            while (rs.next()) {

                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)});
            }
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

    public void table_venta(JTable venta) {
        try {
            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String sSQL1 = "select ven.venta_id,\n"
                    + "ti_com.comprobante_descrip,\n"
                    + "ti_do.documento_descrip,\n"
                    + "per.persona_ruc,per.persona_dni,per.persona_razon_social,\n"
                    + "ven.venta_serie,\n"
                    + "ven.venta_numero,\n"
                    + "ven.venta_fecha_emision,\n"
                    + "ven.venta_total,\n"
                    + "al.almacen_descrip from venta ven\n"
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
                    + "on al.almacen_id =ven.venta_empresa\n"
                    + "order by ven.venta_id desc";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"", "COMPROBANTE", "DOCUMENTO", "RUC", "DNI", "RAZON SOCIAL", "SERIE", "NUMERO", "FECHA", "TOTAL", "ALMACEN"});
            while (rs.next()) {

                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                    rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11)});
            }
            cxn.desconectar();
            venta.setModel(model);
            Function_Component.JTable(venta);
            venta.getColumnModel().getColumn(0).setMaxWidth(0);
            venta.getColumnModel().getColumn(0).setMinWidth(0);
            venta.getColumnModel().getColumn(0).setPreferredWidth(0);
            venta.setDragEnabled(false);
            venta.getTableHeader().setReorderingAllowed(false);
            venta.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }

    public void cbxAfectacion(JComboBox<Entity_Tipo_afectacion> Marca) {
        String sent = "select * from tipo_afectacion \n"
                + "		    ORDER BY (CASE\n"
                + "		   WHEN afectacion_id = 8 THEN 0\n"
                + "	       WHEN afectacion_id = 2 THEN 1\n"
                + "	       WHEN afectacion_id = 3 THEN 2\n"
                + "	       WHEN afectacion_id = 4 THEN 3\n"
                + "		   WHEN afectacion_id = 5 THEN 4\n"
                + "		   WHEN afectacion_id = 6 THEN 5\n"
                + "		   WHEN afectacion_id = 7 THEN 6\n"
                + "		   WHEN afectacion_id = 1 THEN 7\n"
                + "		   WHEN afectacion_id = 9 THEN 8\n"
                + "		   WHEN afectacion_id = 10 THEN 9\n"
                + "		   WHEN afectacion_id = 11 THEN 10\n"
                + "           WHEN afectacion_id = 12 THEN 11\n"
                + "		   WHEN afectacion_id = 13 THEN 12\n"
                + "	       WHEN afectacion_id = 14 THEN 13\n"
                + "		   WHEN afectacion_id = 15  THEN  14 END)";
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

    public void cbx_sunat(JComboBox<Entity_Sunat_transaccion> sunat) {
        String sent = "SELECT * FROM sunat_transaccion order by transaccion_id  asc";
        try {
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sent);
            while (rs.next()) {
                sunat.addItem(new Entity_Sunat_transaccion(rs.getString(1), rs.getString(2), rs.getString(3)));
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

    public void cbx_serie(JComboBox serie, String comprobante, String sucursal) {
        String sent = "SELECT dpo.det_punto_op_serie\n"
                + "  FROM punto_operacion po\n"
                + "  inner join detalle_punto_operacion dpo\n"
                + "  on po.punto_op_id=dpo.det_punto_op_punto_operacion\n"
                + "   where po.punto_op_sucursal='" + sucursal + "' and dpo.det_punto_op_tipo_documento ='" + comprobante + "'";
        try {
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sent);
            while (rs.next()) {
                serie.addItem(rs.getString(1));
            }
            cxn.desconectar();
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }

    public String serie_numero(String serie, String almacen, String comprobante) {
        String numero = "";
        String sent = "SELECT (CASE   WHEN (MAX(venta_numero) > 0) THEN (MAX(venta_numero) + 1)  ELSE 1    END) AS maximo FROM venta ven\n"
                + "  inner join almacen al\n"
                + "  on al.almacen_id=ven.venta_empresa\n"
                + "  inner join tipo_comprobante tp\n"
                + "  on tp.comprobante_id=ven.venta_tipo_comprobante\n"
                + "  where   Cast(ven.venta_serie as varchar(max))='" + serie + "' and al.almacen_id='" + almacen + "' and tp.comprobante_id='" + comprobante + "'";
        try {
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sent);
            rs.next();
            numero = rs.getString(1);
            System.out.println("Controller.Controller_salida.serie_numero()" + numero);
            cxn.desconectar();
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        return numero;
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

    public void cbx_concepto(JComboBox<Entity_Concepto> concepto) {
        String sent = "SELECT * FROM concepto order by concepto_id  asc";
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
    public void cbx_zona(JComboBox zona) {
        String sent = "SELECT CAST(institucion_comunidad_indi AS VARCHAR(MAX)) FROM institucion  GROUP BY CAST(institucion_comunidad_indi AS VARCHAR(MAX))";
        try {
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sent);
            while (rs.next()) {
               zona.addItem(rs.getString(1));
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
                + "		@venta_entrega = N'" + venta.getEntrega_id()+ "'";

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
            "IGV", "IGV_VALOR", "AFECTACION", "CANTIDAD", "PRECIO VENTA", "SUB_TOTAL", "PRECIO TOTAL", "DESC %", "DESC VALOR", "FECHA VENCIMIENTO", "COD LOTE", "ID_LOTE_COMPRA", "STOCK"}, 0) {

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
        model.addRow(new Object[]{btnEliminar, "", "", "", "", "", id_Afectacion, afectacion, valor_afectacion, valor_cod_afectacion, "", "", "", "", "0", "0", "", "", ""});

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
        settext_p_fecha_produccion(tabla, tabla.getColumnModel().getColumn(18), null);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.setDragEnabled(false);
        tabla.getTableHeader().setReorderingAllowed(false);

    }
    public void venta_detalle_buscar(JTable tabla, JDialog dialog_producto, JTable table_producto, JDialog dialog_igv, JTable table_igv, String venta) {
        JButton btnEliminar = new JButton("");
        btnEliminar.setName("btneliminar");
        btnEliminar.setIcon(new ImageIcon("src\\Icon\\icon_eliminar.png"));
        tabla.setDefaultRenderer(Object.class, new Render());
        model = new DefaultTableModel(new Object[]{"ELI", "ID_PROD_COMPR", "ID_PROD", "COD BARRA", "DESCRIPCION", "MEDIDA", "ID_IGV",
            "IGV", "IGV_VALOR", "AFECTACION", "CANTIDAD", "PRECIO VENTA", "SUB_TOTAL", "PRECIO TOTAL", "DESC %", "DESC VALOR", "FECHA VENCIMIENTO", "COD LOTE", "ID_LOTE_COMPRA", "STOCK"}, 0) {

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
            String sSQL1 = "select det_venta.det_ven_pro_id,\n"
                    + "pro_p.present_id,pro_p.present_cod_barra,pro_p.present_descripcion,ti_uni.unidad_cod,\n"
                    + "ti_af.afectacion_id,ti_af.afectacion_descr,ti_af.afectacion_valor,ti_af.afectacion_cod_valor,\n"
                    + "det_venta.det_ven_pro_cantidad,det_venta.det_ven_pro_precio,det_venta.det_ven_pro_sub_total,det_venta.det_ven_pro_total,\n"
                    + "det_venta.det_ven_pro_desc_por,det_venta.det_ven_pro_desc_valor,det_venta.det_ven_pro_fecha,det_venta.det_ven_pro_cod_lote,det_venta.det_ven_pro_id_compra,\n"
                    + "((ISNULL((select  sum(dt_com.det_com_pro_cantidad*pre.present_cantidad) from producto pro\n"
                    + "inner join  producto_presentacion pre\n"
                    + "on pre.present_cod_producto=pro.producto_id\n"
                    + "inner join detalle_compra_producto dt_com\n"
                    + "on dt_com.det_com_pro_id_producto=pre.present_id\n"
                    + "where pro.producto_id=pro_p.present_cod_producto\n"
                    + "group by pro.producto_id),0))-(ISNULL((select  sum(dt_ven.det_ven_pro_cantidad*pre.present_cantidad)from producto pro\n"
                    + "inner join  producto_presentacion pre\n"
                    + "on pre.present_cod_producto=pro.producto_id\n"
                    + "inner join detalle_venta_producto dt_ven\n"
                    + "on dt_ven.det_ven_pro_id_producto=pre.present_id\n"
                    + "where pro.producto_id=pro_p.present_cod_producto\n"
                    + "group by pro.producto_id),0)))AS TOTAL\n"
                    + "from detalle_venta_producto det_venta\n"
                    + "inner join venta vent\n"
                    + "on vent.venta_id=det_venta.det_ven_pro_id_venta\n"
                    + "inner join producto_presentacion pro_p\n"
                    + "on pro_p.present_id=det_venta.det_ven_pro_id_producto\n"
                    + "inner join tipo_afectacion ti_af\n"
                    + "on ti_af.afectacion_id=det_venta.det_ven_pro_id_igv\n"
                    + "inner join tipo_unidad ti_uni\n"
                    + "on ti_uni.unidad_id=pro_p.present_cod_unidad\n"
                    + "where det_venta.det_ven_pro_id_venta='" + venta + "'\n"
                    + "order by det_venta.det_ven_pro_id asc";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            while (rs.next()) {
                model.addRow(new Object[]{btnEliminar, "", rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
                    rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
                    rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18), rs.getString(19)});

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
        settext_p_fecha_produccion(tabla, tabla.getColumnModel().getColumn(18), null);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.setDragEnabled(false);
        tabla.getTableHeader().setReorderingAllowed(false);

    }

    public void venta_detalle(JTable tabla, JDialog dialog_producto, JTable table_producto, JDialog dialog_igv, JTable table_igv, String venta) {
        JButton btnEliminar = new JButton("");
        btnEliminar.setName("btneliminar");
        btnEliminar.setIcon(new ImageIcon("src\\Icon\\icon_eliminar.png"));
        tabla.setDefaultRenderer(Object.class, new Render());
        model = new DefaultTableModel(new Object[]{"ELI", "ID_PROD_COMPR", "ID_PROD", "COD BARRA", "DESCRIPCION", "MEDIDA", "ID_IGV",
            "IGV", "IGV_VALOR", "AFECTACION", "CANTIDAD", "PRECIO VENTA", "SUB_TOTAL", "PRECIO TOTAL", "DESC %", "DESC VALOR", "FECHA VENCIMIENTO", "COD LOTE", "ID_LOTE_COMPRA", "STOCK"}, 0) {

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
            String sSQL1 = "select det_venta.det_ven_pro_id,\n"
                    + "pro_p.present_id,pro_p.present_cod_barra,pro_p.present_descripcion,ti_uni.unidad_cod,\n"
                    + "ti_af.afectacion_id,ti_af.afectacion_descr,ti_af.afectacion_valor,ti_af.afectacion_cod_valor,\n"
                    + "det_venta.det_ven_pro_cantidad,det_venta.det_ven_pro_precio,det_venta.det_ven_pro_sub_total,det_venta.det_ven_pro_total,\n"
                    + "det_venta.det_ven_pro_desc_por,det_venta.det_ven_pro_desc_valor,det_venta.det_ven_pro_fecha,det_venta.det_ven_pro_cod_lote,det_venta.det_ven_pro_id_compra,\n"
                    + "((ISNULL((select  sum(dt_com.det_com_pro_cantidad*pre.present_cantidad) from producto pro\n"
                    + "inner join  producto_presentacion pre\n"
                    + "on pre.present_cod_producto=pro.producto_id\n"
                    + "inner join detalle_compra_producto dt_com\n"
                    + "on dt_com.det_com_pro_id_producto=pre.present_id\n"
                    + "where pro.producto_id=pro_p.present_cod_producto\n"
                    + "group by pro.producto_id),0))-(ISNULL((select  sum(dt_ven.det_ven_pro_cantidad*pre.present_cantidad)from producto pro\n"
                    + "inner join  producto_presentacion pre\n"
                    + "on pre.present_cod_producto=pro.producto_id\n"
                    + "inner join detalle_venta_producto dt_ven\n"
                    + "on dt_ven.det_ven_pro_id_producto=pre.present_id\n"
                    + "where pro.producto_id=pro_p.present_cod_producto\n"
                    + "group by pro.producto_id),0)))AS TOTAL\n"
                    + "from detalle_venta_producto det_venta\n"
                    + "inner join venta vent\n"
                    + "on vent.venta_id=det_venta.det_ven_pro_id_venta\n"
                    + "inner join producto_presentacion pro_p\n"
                    + "on pro_p.present_id=det_venta.det_ven_pro_id_producto\n"
                    + "inner join tipo_afectacion ti_af\n"
                    + "on ti_af.afectacion_id=det_venta.det_ven_pro_id_igv\n"
                    + "inner join tipo_unidad ti_uni\n"
                    + "on ti_uni.unidad_id=pro_p.present_cod_unidad\n"
                    + "where det_venta.det_ven_pro_id_venta='" + venta + "'\n"
                    + "order by det_venta.det_ven_pro_id asc";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            while (rs.next()) {
                model.addRow(new Object[]{btnEliminar, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
                    rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
                    rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18), rs.getString(19)});

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
        settext_p_fecha_produccion(tabla, tabla.getColumnModel().getColumn(18), null);
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
                        if (evt.getKeyCode() == KeyEvent.VK_F1) {
                            Controller_salida almacen = new Controller_salida();
                            System.out.println(".keyPressed()" + txt_cod_barra.getText());
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
                            evt.consume();
                        }
                        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                            List<Entity_Producto_Presentacion> persona = new Controller_salida().buscar_cod_barra_producto_presentacion(txt_cod_barra.getText());

                            if (persona.size() == 0) {
                                Controller_salida almacen = new Controller_salida();
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

                            tabla.setValueAt(persona.get(0).getAfectacion_id(), tabla.getSelectedRow() + getselection, 6);
                            tabla.setValueAt(persona.get(0).getAfectacion_descr(), tabla.getSelectedRow() + getselection, 7);
                            tabla.setValueAt(persona.get(0).getAfectacion_valor(), tabla.getSelectedRow() + getselection, 8);
                            tabla.setValueAt(persona.get(0).getAfectacion_cod_valor(), tabla.getSelectedRow() + getselection, 9);
                            //tabla.setValueAt(persona.get(0).getPresent_id(), getselection);
                            tabla.setValueAt(persona.get(0).getPresent_prec_venta(), tabla.getSelectedRow() + getselection, 11);
                            tabla.setValueAt(persona.get(0).getStock(), tabla.getSelectedRow() + getselection, 19);

                            tabla.editCellAt(tabla.getSelectedRow() + getselection, 10);

                            Component aComp = tabla.getEditorComponent();
                            aComp.requestFocus();
                            evt.consume();

                        }
                        if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_L) {
                            try {
                                if (tabla.getValueAt(tabla.getSelectedRow() + getselection, 2).toString().length() != 0) {

                                    Controller_salida almacen = new Controller_salida();
                                    if (almacen.table_lote(View_Salida_producto.tabla_lote, tabla.getValueAt(tabla.getSelectedRow() + getselection, 2).toString()).equals("ERROR")) {
                                        Function_ShowMessageDialog.ShowMessageDialogvalidacion(null, "BUSQUEDA NO ENCONTRADA");

                                    } else {
                                        almacen.table_lote(View_Salida_producto.tabla_lote, tabla.getValueAt(tabla.getSelectedRow() + getselection, 2).toString());
                                        Dimension desktopSize = View_Escritorio.desktopPane.getSize();
                                        Dimension FrameSize = View_Salida_producto.Dialog_lote.getSize();
                                        View_Salida_producto.Dialog_lote.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
                                        View_Salida_producto.Dialog_lote.setVisible(true);
                                        tabla.editCellAt(tabla.getSelectedRow() + getselection, 10);
                                        Component aComp = tabla.getEditorComponent();
                                        aComp.requestFocus();
                                        View_Salida_producto.tabla_lote.requestFocus();

                                    }
                                }

                            } catch (Exception e) {
                                tabla.editCellAt(0, 3);
                                Component aComp = tabla.getEditorComponent();
                                aComp.requestFocus();
                            }
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
                    Controller_salida almacen = new Controller_salida();
                    almacen.table_tipo_afectacion(tabla_igv);

                    Dimension desktopSize = View_Escritorio.desktopPane.getSize();
                    Dimension FrameSize = dialogo.getSize();
                    dialogo.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
                    dialogo.setVisible(true);

                    tabla.editCellAt(tabla.getSelectedRow() + getselection, 10);
                    Component aComp = tabla.getEditorComponent();
                    aComp.requestFocus();
                    tabla_igv.requestFocus();
                    evt.consume();

                }
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    tabla.editCellAt(tabla.getSelectedRow() + getselection, 10);
                    Component aComp = tabla.getEditorComponent();
                    aComp.requestFocus();
                    evt.consume();

                }
                if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_L) {
                    try {
                        if (tabla.getValueAt(tabla.getSelectedRow() + getselection, 2).toString().length() != 0) {

                            Controller_salida almacen = new Controller_salida();
                            if (almacen.table_lote(View_Salida_producto.tabla_lote, tabla.getValueAt(tabla.getSelectedRow() + getselection, 2).toString()).equals("ERROR")) {
                                Function_ShowMessageDialog.ShowMessageDialogvalidacion(null, "BUSQUEDA NO ENCONTRADA");

                            } else {
                                almacen.table_lote(View_Salida_producto.tabla_lote, tabla.getValueAt(tabla.getSelectedRow() + getselection, 2).toString());
                                Dimension desktopSize = View_Escritorio.desktopPane.getSize();
                                Dimension FrameSize = View_Salida_producto.Dialog_lote.getSize();
                                View_Salida_producto.Dialog_lote.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
                                View_Salida_producto.Dialog_lote.setVisible(true);
                                tabla.editCellAt(tabla.getSelectedRow() + getselection, 10);
                                Component aComp = tabla.getEditorComponent();
                                aComp.requestFocus();
                                View_Salida_producto.tabla_lote.requestFocus();

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

                    if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_L) {
                        try {
                            if (tabla.getValueAt(tabla.getSelectedRow() + getselection, 2).toString().length() != 0) {

                                Controller_salida almacen = new Controller_salida();
                                if (almacen.table_lote(View_Salida_producto.tabla_lote, tabla.getValueAt(tabla.getSelectedRow() + getselection, 2).toString()).equals("ERROR")) {
                                    Function_ShowMessageDialog.ShowMessageDialogvalidacion(null, "BUSQUEDA NO ENCONTRADA");

                                } else {
                                    almacen.table_lote(View_Salida_producto.tabla_lote, tabla.getValueAt(tabla.getSelectedRow() + getselection, 2).toString());
                                    Dimension desktopSize = View_Escritorio.desktopPane.getSize();
                                    Dimension FrameSize = View_Salida_producto.Dialog_lote.getSize();
                                    View_Salida_producto.Dialog_lote.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
                                    View_Salida_producto.Dialog_lote.setVisible(true);
                                    tabla.editCellAt(tabla.getSelectedRow() + getselection, 10);
                                    Component aComp = tabla.getEditorComponent();
                                    aComp.requestFocus();
                                    View_Salida_producto.tabla_lote.requestFocus();

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
                    try {
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
                        View_Salida_producto.btn_calcular.doClick();

                    }
                    evt.consume();

                }
                if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                    try {
                        if (txt_p_compra.getText().length() != 0) {
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
                if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_L) {
                    try {
                        if (tabla.getValueAt(tabla.getSelectedRow() + getselection, 2).toString().length() != 0) {

                            Controller_salida almacen = new Controller_salida();
                            if (almacen.table_lote(View_Salida_producto.tabla_lote, tabla.getValueAt(tabla.getSelectedRow() + getselection, 2).toString()).equals("ERROR")) {
                                Function_ShowMessageDialog.ShowMessageDialogvalidacion(null, "BUSQUEDA NO ENCONTRADA");

                            } else {
                                almacen.table_lote(View_Salida_producto.tabla_lote, tabla.getValueAt(tabla.getSelectedRow() + getselection, 2).toString());
                                Dimension desktopSize = View_Escritorio.desktopPane.getSize();
                                Dimension FrameSize = View_Salida_producto.Dialog_lote.getSize();
                                View_Salida_producto.Dialog_lote.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
                                View_Salida_producto.Dialog_lote.setVisible(true);
                                tabla.editCellAt(tabla.getSelectedRow() + getselection, 10);
                                Component aComp = tabla.getEditorComponent();
                                aComp.requestFocus();
                                View_Salida_producto.tabla_lote.requestFocus();

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
                try {

                    if (txt_p_compra.getText().length() > 0) {

                        tabla.setValueAt(formato2.format((Double.parseDouble(txt_p_compra.getText()) * Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 10).toString()))),
                                tabla.getSelectedRow() + getselection, 12);

                        tabla.setValueAt(formato2.format((((Double.parseDouble(txt_p_compra.getText()) * Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 10).toString()))
                                * Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 8).toString()))
                                + (Double.parseDouble(txt_p_compra.getText())
                                * Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 10).toString())))),
                                tabla.getSelectedRow() + getselection, 13);
                    }
                } catch (Exception exx) {
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
                        View_Salida_producto.btn_calcular.doClick();

                    }
                    evt.consume();
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

                            Controller_salida almacen = new Controller_salida();
                            if (almacen.table_lote(View_Salida_producto.tabla_lote, tabla.getValueAt(tabla.getSelectedRow() + getselection, 2).toString()).equals("ERROR")) {
                                Function_ShowMessageDialog.ShowMessageDialogvalidacion(null, "BUSQUEDA NO ENCONTRADA");

                            } else {
                                almacen.table_lote(View_Salida_producto.tabla_lote, tabla.getValueAt(tabla.getSelectedRow() + getselection, 2).toString());
                                Dimension desktopSize = View_Escritorio.desktopPane.getSize();
                                Dimension FrameSize = View_Salida_producto.Dialog_lote.getSize();
                                View_Salida_producto.Dialog_lote.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
                                View_Salida_producto.Dialog_lote.setVisible(true);
                                tabla.editCellAt(tabla.getSelectedRow() + getselection, 10);
                                Component aComp = tabla.getEditorComponent();
                                aComp.requestFocus();
                                View_Salida_producto.tabla_lote.requestFocus();

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

                        /*tabla.editCellAt(tabla.getSelectedRow() + getselection, 14);
                        Component aComp = tabla.getEditorComponent();
                        aComp.requestFocus();
                        View_Salida_producto.btn_calcular.doClick();*/
                    }
                    evt.consume();
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

                            Controller_salida almacen = new Controller_salida();
                            if (almacen.table_lote(View_Salida_producto.tabla_lote, tabla.getValueAt(tabla.getSelectedRow() + getselection, 2).toString()).equals("ERROR")) {
                                Function_ShowMessageDialog.ShowMessageDialogvalidacion(null, "BUSQUEDA NO ENCONTRADA");

                            } else {
                                almacen.table_lote(View_Salida_producto.tabla_lote, tabla.getValueAt(tabla.getSelectedRow() + getselection, 2).toString());
                                Dimension desktopSize = View_Escritorio.desktopPane.getSize();
                                Dimension FrameSize = View_Salida_producto.Dialog_lote.getSize();
                                View_Salida_producto.Dialog_lote.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
                                View_Salida_producto.Dialog_lote.setVisible(true);
                                tabla.editCellAt(tabla.getSelectedRow() + getselection, 10);
                                Component aComp = tabla.getEditorComponent();
                                aComp.requestFocus();
                                View_Salida_producto.tabla_lote.requestFocus();

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
                if (txt_p_venta.getText().length() > 0) {

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
                        View_Salida_producto.btn_calcular.doClick();

                    }
                    evt.consume();
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

                            Controller_salida almacen = new Controller_salida();
                            if (almacen.table_lote(View_Salida_producto.tabla_lote, tabla.getValueAt(tabla.getSelectedRow() + getselection, 2).toString()).equals("ERROR")) {
                                Function_ShowMessageDialog.ShowMessageDialogvalidacion(null, "BUSQUEDA NO ENCONTRADA");

                            } else {
                                almacen.table_lote(View_Salida_producto.tabla_lote, tabla.getValueAt(tabla.getSelectedRow() + getselection, 2).toString());
                                Dimension desktopSize = View_Escritorio.desktopPane.getSize();
                                Dimension FrameSize = View_Salida_producto.Dialog_lote.getSize();
                                View_Salida_producto.Dialog_lote.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
                                View_Salida_producto.Dialog_lote.setVisible(true);
                                tabla.editCellAt(tabla.getSelectedRow() + getselection, 10);
                                Component aComp = tabla.getEditorComponent();
                                aComp.requestFocus();
                                View_Salida_producto.tabla_lote.requestFocus();

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

                        tabla.setValueAt(formato2.format((Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 10).toString()) * Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 11).toString())) - Double.parseDouble(txt_p_desc_valor.getText())),
                                tabla.getSelectedRow() + getselection, 12);
                        tabla.setValueAt(formato2.format(((Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 10).toString()) * Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 11).toString())) - Double.parseDouble(txt_p_desc_valor.getText())) + ((Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 10).toString()) * Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 11).toString())) - Double.parseDouble(txt_p_desc_valor.getText())) * (Double.parseDouble(tabla.getValueAt(tabla.getSelectedRow() + getselection, 8).toString()))),
                                tabla.getSelectedRow() + getselection, 13);

                        tabla.editCellAt(tabla.getSelectedRow() + getselection, 16);
                        Component aComp = tabla.getEditorComponent();
                        aComp.requestFocus();
                        View_Salida_producto.btn_calcular.doClick();

                    }
                    evt.consume();
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

                            Controller_salida almacen = new Controller_salida();
                            if (almacen.table_lote(View_Salida_producto.tabla_lote, tabla.getValueAt(tabla.getSelectedRow() + getselection, 2).toString()).equals("ERROR")) {
                                Function_ShowMessageDialog.ShowMessageDialogvalidacion(null, "BUSQUEDA NO ENCONTRADA");

                            } else {
                                almacen.table_lote(View_Salida_producto.tabla_lote, tabla.getValueAt(tabla.getSelectedRow() + getselection, 2).toString());
                                Dimension desktopSize = View_Escritorio.desktopPane.getSize();
                                Dimension FrameSize = View_Salida_producto.Dialog_lote.getSize();
                                View_Salida_producto.Dialog_lote.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
                                View_Salida_producto.Dialog_lote.setVisible(true);
                                tabla.editCellAt(tabla.getSelectedRow() + getselection, 10);
                                Component aComp = tabla.getEditorComponent();
                                aComp.requestFocus();
                                View_Salida_producto.tabla_lote.requestFocus();

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

                                Controller_salida almacen = new Controller_salida();
                                if (almacen.table_lote(View_Salida_producto.tabla_lote, tabla.getValueAt(tabla.getSelectedRow() + getselection, 2).toString()).equals("ERROR")) {
                                    Function_ShowMessageDialog.ShowMessageDialogvalidacion(null, "BUSQUEDA NO ENCONTRADA");

                                } else {
                                    almacen.table_lote(View_Salida_producto.tabla_lote, tabla.getValueAt(tabla.getSelectedRow() + getselection, 2).toString());
                                    Dimension desktopSize = View_Escritorio.desktopPane.getSize();
                                    Dimension FrameSize = View_Salida_producto.Dialog_lote.getSize();
                                    View_Salida_producto.Dialog_lote.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
                                    View_Salida_producto.Dialog_lote.setVisible(true);
                                    tabla.editCellAt(tabla.getSelectedRow() + getselection, 10);
                                    Component aComp = tabla.getEditorComponent();
                                    aComp.requestFocus();
                                    View_Salida_producto.tabla_lote.requestFocus();

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

                            Controller_salida almacen = new Controller_salida();
                            if (almacen.table_lote(View_Salida_producto.tabla_lote, tabla.getValueAt(tabla.getSelectedRow() + getselection, 2).toString()).equals("ERROR")) {
                                Function_ShowMessageDialog.ShowMessageDialogvalidacion(null, "BUSQUEDA NO ENCONTRADA");

                            } else {
                                almacen.table_lote(View_Salida_producto.tabla_lote, tabla.getValueAt(tabla.getSelectedRow() + getselection, 2).toString());
                                Dimension desktopSize = View_Escritorio.desktopPane.getSize();
                                Dimension FrameSize = View_Salida_producto.Dialog_lote.getSize();
                                View_Salida_producto.Dialog_lote.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
                                View_Salida_producto.Dialog_lote.setVisible(true);
                                tabla.editCellAt(tabla.getSelectedRow() + getselection, 10);
                                Component aComp = tabla.getEditorComponent();
                                aComp.requestFocus();
                                View_Salida_producto.tabla_lote.requestFocus();

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
            }

        });

    }

    public void settext_p_fecha_produccion(JTable tabla, TableColumn columna, JDialog dialogo) {

        try {

            /* MaskFormatter mascara = new MaskFormatter("####-##-##");
            // mascara.setPlaceholderCharacter("AAAA-MM-DD");
            JFormattedTextField txt_p_fecha = new JFormattedTextField(mascara);
             */
            JTextField txt_p_fecha = new JTextField();
            Function_Component.JTextField(txt_p_fecha);
            Function_Key_Event.Validar_numeros(txt_p_fecha);
            columna.setCellEditor(new DefaultCellEditor(txt_p_fecha));
            DefaultTableCellRenderer render = new DefaultTableCellRenderer();
            txt_p_fecha.addKeyListener(new KeyListener() {
                @Override
                public void keyPressed(KeyEvent evt) {
                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                        //getselection = getselection + 1;
                        tabla.editCellAt(tabla.getSelectedRow() + getselection, 18);
                        Component aComp = tabla.getEditorComponent();
                        aComp.requestFocus();
                    }
                    if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                        try {

                            if (tabla.getSelectedRow() + getselection == tabla.getRowCount() - 1) {
                                getselection = getselection + 1;

                                View.View_Ingreso_Productos.btn_agregar_fila.doClick();
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
                +" entre.entrega_id,entre.entrega_descripcion,entre.entrega_año\n"
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
