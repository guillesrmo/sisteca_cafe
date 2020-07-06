package Controller;

import Conexion.Conexion;
import Config_Class.Render;
import Entity.Entity_Producto;
import Entity.Entity_Producto_Presentacion;
import Entity.Entity_Tipo_Embalaje;
import Entity.Entity_Tipo_afectacion;
import Function.Function_Component;
import Function.Function_Key_Event;
import Function.Function_ShowMessageDialog;
import View.View_Escritorio;
import View.View_Producto;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Caret;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author memo
 */
public class Controller_Producto {

    private Conexion cxn;
    private boolean[] editable = {false, false, false, true, true, true, false, true, true, true, true, true, true};
    DefaultTableModel model;
    private int getselection = 0;

    /*private generador_codigo generador_id;
    Function_ShowMessageDialog mensaje = new Function_ShowMessageDialog();*/
    public Controller_Producto() {
        cxn = new Conexion();
        //generador_id = new generador_codigo();
    }

    public void reset_variable() {
        getselection = 0;
    }

    public int variable() {
        return getselection;
    }

    public void table_clasificaicon_producto(JTable clasificacion_producto, String busqueda) {
        try {
            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String sSQL1 = "SELECT * FROM [dbo].[clasificacion_producto] WHERE producto_cod  LIKE '%" + busqueda + "%' order by producto_id asc";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"", "ID", "DESCRIPCIÒN"});
            while (rs.next()) {

                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3)});
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

            if (busqueda.isEmpty()) {
                clasificacion_producto.clearSelection();
            } else {
                for (int i = 0; i < clasificacion_producto.getRowCount(); i++) {
                    if (clasificacion_producto.getValueAt(i, 1).equals(busqueda)) {
                        clasificacion_producto.requestFocus();
                        clasificacion_producto.changeSelection(i, 1, false, false);
                    }
                }
            }
            clasificacion_producto.setDragEnabled(false);
            clasificacion_producto.getTableHeader().setReorderingAllowed(false);
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }

    public void table_tipo_moneda(JTable tipo_moneda, String busqueda) {
        try {
            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String sSQL1 = "SELECT * FROM [dbo].[tipo_moneda] WHERE moneda_cod  LIKE '%" + busqueda + "%' order by moneda_id asc";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"", "ID", "DESCRIPCIÒN"});
            while (rs.next()) {

                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3)});
            }
            cxn.desconectar();
            tipo_moneda.setModel(model);
            Function_Component.JTable(tipo_moneda);
            tipo_moneda.getColumnModel().getColumn(0).setMaxWidth(0);
            tipo_moneda.getColumnModel().getColumn(0).setMinWidth(0);
            tipo_moneda.getColumnModel().getColumn(0).setPreferredWidth(0);
            tipo_moneda.getColumnModel().getColumn(1).setMaxWidth(150);
            tipo_moneda.getColumnModel().getColumn(1).setMinWidth(150);
            tipo_moneda.getColumnModel().getColumn(1).setPreferredWidth(150);

            if (busqueda.isEmpty()) {
                tipo_moneda.clearSelection();
            } else {
                for (int i = 0; i < tipo_moneda.getRowCount(); i++) {
                    if (tipo_moneda.getValueAt(i, 1).equals(busqueda)) {
                        tipo_moneda.requestFocus();
                        tipo_moneda.changeSelection(i, 1, false, false);
                    }
                }
            }
            tipo_moneda.setDragEnabled(false);
            tipo_moneda.getTableHeader().setReorderingAllowed(false);
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }

    public void table_familia(JTable familia, String busqueda) {
        try {
            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String sSQL1 = "SELECT * FROM [dbo].[familia] WHERE familia_cod  LIKE '%" + busqueda + "%' order by familia_id asc";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"", "ID", "DESCRIPCIÒN"});
            while (rs.next()) {

                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3)});
            }
            cxn.desconectar();
            familia.setModel(model);
            Function_Component.JTable(familia);
            familia.getColumnModel().getColumn(0).setMaxWidth(0);
            familia.getColumnModel().getColumn(0).setMinWidth(0);
            familia.getColumnModel().getColumn(0).setPreferredWidth(0);
            familia.getColumnModel().getColumn(1).setMaxWidth(150);
            familia.getColumnModel().getColumn(1).setMinWidth(150);
            familia.getColumnModel().getColumn(1).setPreferredWidth(150);

            if (busqueda.isEmpty()) {
                familia.clearSelection();
            } else {
                for (int i = 0; i < familia.getRowCount(); i++) {
                    if (familia.getValueAt(i, 1).equals(busqueda)) {
                        familia.requestFocus();
                        familia.changeSelection(i, 1, false, false);
                    }
                }
            }
            familia.setDragEnabled(false);
            familia.getTableHeader().setReorderingAllowed(false);
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }

    public void table_tipo_unidad(JTable tipo_unidad, String busqueda) {
        try {
            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String sSQL1 = "SELECT * FROM [dbo].[tipo_unidad] WHERE unidad_cod  LIKE '%" + busqueda + "%' order by unidad_id asc";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"", "ID", "DESCRIPCIÒN"});
            while (rs.next()) {

                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3)});
            }
            cxn.desconectar();
            tipo_unidad.setModel(model);
            Function_Component.JTable(tipo_unidad);
            tipo_unidad.getColumnModel().getColumn(0).setMaxWidth(0);
            tipo_unidad.getColumnModel().getColumn(0).setMinWidth(0);
            tipo_unidad.getColumnModel().getColumn(0).setPreferredWidth(0);
            tipo_unidad.getColumnModel().getColumn(1).setMaxWidth(150);
            tipo_unidad.getColumnModel().getColumn(1).setMinWidth(150);
            tipo_unidad.getColumnModel().getColumn(1).setPreferredWidth(150);

            if (busqueda.isEmpty()) {
                tipo_unidad.clearSelection();
            } else {
                for (int i = 0; i < tipo_unidad.getRowCount(); i++) {
                    if (tipo_unidad.getValueAt(i, 1).equals(busqueda)) {
                        tipo_unidad.requestFocus();
                        tipo_unidad.changeSelection(i, 1, false, false);
                    }
                }
            }
            tipo_unidad.setDragEnabled(false);
            tipo_unidad.getTableHeader().setReorderingAllowed(false);
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }

    public void tabla_producto(JTable producto) {
        try {
            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String sSQL1 = "SELECT pro.producto_id,pro.producto_cod,pro.producto_descrip,fa.familia_descrip FROM producto pro\n"
                    + "inner join  familia fa\n"
                    + "on fa.familia_id=pro.producto_familia  order by [producto_id] asc";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"", "ID", "DESCRIPCIÒN", "FAMILIA"});
            while (rs.next()) {

                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)});
            }
            cxn.desconectar();

            producto.setModel(model);
            Function_Component.JTable(producto);
            producto.getColumnModel().getColumn(0).setMaxWidth(0);
            producto.getColumnModel().getColumn(0).setMinWidth(0);
            producto.getColumnModel().getColumn(0).setPreferredWidth(0);
            producto.getColumnModel().getColumn(1).setMaxWidth(150);
            producto.getColumnModel().getColumn(1).setMinWidth(150);
            producto.getColumnModel().getColumn(1).setPreferredWidth(150);
            producto.getColumnModel().getColumn(3).setMaxWidth(150);
            producto.getColumnModel().getColumn(3).setMinWidth(150);
            producto.getColumnModel().getColumn(3).setPreferredWidth(150);
            producto.setDragEnabled(false);
            producto.getTableHeader().setReorderingAllowed(false);
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }

    public void tabla_producto_suant(JTable producto_sunat) {
        try {
            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String sSQL1 = "SELECT * FROM [dbo].[codigo_producto_sunat]  order by [sunat_id] asc";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"", "ID", "DESCRIPCIÒN"});
            while (rs.next()) {

                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3)});
            }
            cxn.desconectar();

            producto_sunat.setModel(model);
            Function_Component.JTable(producto_sunat);
            producto_sunat.getColumnModel().getColumn(0).setMaxWidth(0);
            producto_sunat.getColumnModel().getColumn(0).setMinWidth(0);
            producto_sunat.getColumnModel().getColumn(0).setPreferredWidth(0);
            producto_sunat.getColumnModel().getColumn(1).setMaxWidth(150);
            producto_sunat.getColumnModel().getColumn(1).setMinWidth(150);
            producto_sunat.getColumnModel().getColumn(1).setPreferredWidth(150);
            producto_sunat.setDragEnabled(false);
            producto_sunat.getTableHeader().setReorderingAllowed(false);
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

    public void cbxTipoEmbalaje(JComboBox<Entity_Tipo_Embalaje> Embalaje) {
        String sent = "SELECT * FROM [dbo].[tipo_embalaje] order by embalaje_id  asc";
        try {
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sent);
            while (rs.next()) {
                Embalaje.addItem(new Entity_Tipo_Embalaje(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
            cxn.desconectar();
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
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
                + "             @producto_valor =N'" + producto.getProducto_valor() + "',\n"
                + "             @producto_cantidad =N'" + producto.getProducto_cantidad() + "',\n"
                + "             @producto_embalaje =N'" + producto.getEmbalaje_id() + "'\n"
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

    public String Add_Productos_Presentacion(Entity_Producto_Presentacion producto) {
        String respuesta = "";
        String sent = "DECLARE	@return_value int\n"
                + "             EXEC	@return_value = [dbo].[InsertaTablaproductopresentacion]\n"
                + "		@present_id = N'" + producto.getPresent_id() + "',\n"
                + "		@present_cod_producto = " + producto.getPresent_cod_producto() + ",\n"
                + "		@present_cod_barra = N'" + producto.getPresent_cod_barra() + "',\n"
                + "		@present_descripcion = N'" + producto.getPresent_descripcion() + "',\n"
                + "		@present_descripcion_corta = N'" + producto.getPresent_descripcion_corta() + "',\n"
                + "		@present_cod_unidad = " + producto.getPresent_cod_unidad() + ",\n"
                + "		@present_cantidad = " + producto.getPresent_cantidad() + ",\n"
                + "		@present_prec_compra = " + producto.getPresent_prec_compra() + ",\n"
                + "		@present_prec_venta = " + producto.getPresent_prec_venta() + ",\n"
                + "		@present_estado = N'" + producto.getPresent_estado() + "',\n"
                + "             @present_peso=N'" + producto.getPresent_peso() + "'\n"
                + "            ";

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

    public String Add_Productos_Sunat(String producto, String cod_sunat) {
        String respuesta = "";
        String sent = "DECLARE	@return_value int\n"
                + "\n"
                + "EXEC	@return_value = [dbo].[InsertaTabladetalleproductosunat]\n"
                + "		@detalle_sunat_id = N'',\n"
                + "		@detalle_sunat_producto = " + producto + ",\n"
                + "		@detalle_sunat_cod = " + cod_sunat + "";

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

    public String delete_Productos_Presentacion(String producto) {
        String respuesta = "";
        String sent = "DELETE FROM producto_presentacion WHERE present_id='" + producto + "'";

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

    public String delete_Productos(String producto) {
        String respuesta = "";
        String sent = "DECLARE	@return_value int\n"
                + "EXEC	@return_value = [dbo].[delete_producto]\n"
                + "		@id_producto = N'" + producto + "'";

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

    public void add_presentacion(JTable tabla, JDialog tipo_unidad, JTable unidad_table, String cod_producto, String descrip, String descrip_corta) {
        JButton btnEliminar = new JButton("");
        btnEliminar.setName("btneliminar");
        btnEliminar.setIcon(new ImageIcon("src\\Icon\\icon_eliminar.png"));
        tabla.setDefaultRenderer(Object.class, new Render());
        model = new DefaultTableModel(new Object[]{"ELI", "ID_PROD", "COD BARRA", "DESCRIPCION", "DESCR CORTA", "COD_MEDIDA", "MEDIDA", "CANTIDAD", "PRECIO COMP", "PRECIO VENT", "PESO KG", "ESTADO"}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
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
        model.addRow(new Object[]{btnEliminar, "", cod_producto, "", descrip, descrip_corta, "", "", "0", "0", "0", "0", true});

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
        tabla.getColumnModel().getColumn(3).setMaxWidth(1000);
        tabla.getColumnModel().getColumn(3).setMinWidth(150);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(6).setMaxWidth(0);
        tabla.getColumnModel().getColumn(6).setMinWidth(0);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(7).setMaxWidth(1000);
        tabla.getColumnModel().getColumn(7).setMinWidth(100);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(8).setMaxWidth(1000);
        tabla.getColumnModel().getColumn(8).setMinWidth(100);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(9).setMaxWidth(1000);
        tabla.getColumnModel().getColumn(9).setMinWidth(100);
        tabla.getColumnModel().getColumn(9).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(10).setMaxWidth(1000);
        tabla.getColumnModel().getColumn(10).setMinWidth(100);
        tabla.getColumnModel().getColumn(10).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(11).setMaxWidth(70);
        tabla.getColumnModel().getColumn(11).setMinWidth(70);
        tabla.getColumnModel().getColumn(11).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(12).setMaxWidth(70);
        tabla.getColumnModel().getColumn(12).setMinWidth(70);
        tabla.getColumnModel().getColumn(12).setPreferredWidth(70);
        settext_cod_barra(tabla, tabla.getColumnModel().getColumn(3), null);
        settext_descripcion(tabla, tabla.getColumnModel().getColumn(4), null);
        settext_descrip_corta(tabla, tabla.getColumnModel().getColumn(5), null);
        settext_unidad_medida(tabla, tabla.getColumnModel().getColumn(7), tipo_unidad, unidad_table);
        settext_cantidad(tabla, tabla.getColumnModel().getColumn(8), null);
        settext_p_compra(tabla, tabla.getColumnModel().getColumn(9), null);
        settext_p_venta(tabla, tabla.getColumnModel().getColumn(10), null);
        settext_p_peso(tabla, tabla.getColumnModel().getColumn(11), tipo_unidad);
        tabla.setDragEnabled(false);
        tabla.getTableHeader().setReorderingAllowed(false);

    }

    public void presentacion(JTable tabla, JDialog tipo_unidad, JTable unidad_table, String cod_barra) {
        JButton btnEliminar = new JButton("");
        btnEliminar.setName("btneliminar");
        btnEliminar.setIcon(new ImageIcon("src\\Icon\\icon_eliminar.png"));
        tabla.setDefaultRenderer(Object.class, new Render());
        model = new DefaultTableModel(new Object[]{"ELI", "ID_PROD", "PRO COD", "COD BARRA", "DESCRIPCION", "DESCR CORTA", "COD_MEDIDA", "MEDIDA", "CANTIDAD", "PRECIO COMP", "PRECIO VENT", "PESO KG", "ESTADO"}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
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
            String sSQL1 = "SELECT pro.present_id	,pro.present_cod_producto,	pro.present_cod_barra,	pro.present_descripcion	,pro.present_descripcion_corta,\n"
                    + "pro.present_cod_unidad,tip_uni.unidad_cod,	pro.present_cantidad,	pro.present_prec_compra	,pro.present_prec_venta	,pro.present_estado,pro.present_peso\n"
                    + "FROM producto_presentacion pro\n"
                    + "inner join tipo_unidad tip_uni\n"
                    + "on tip_uni.unidad_id=pro.present_cod_unidad\n"
                    + "where pro.present_cod_producto='" + cod_barra + "'  order by present_id asc";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            while (rs.next()) {
                model.addRow(new Object[]{btnEliminar, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                    rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(12), rs.getBoolean(11)});

            }
            cxn.desconectar();
        } catch (Exception e) {

        }

        /* for (int i = 0; i < 0; i++) {
            model.addRow(new Object[]{btnEliminar, "", "1", "8585959595", "sdadfd", "dsfdsfd", "1", "dsfdsfd", "5", "0.9", "1.0", true});
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
        tabla.getColumnModel().getColumn(3).setMaxWidth(1000);
        tabla.getColumnModel().getColumn(3).setMinWidth(150);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(150);
        tabla.getColumnModel().getColumn(6).setMaxWidth(0);
        tabla.getColumnModel().getColumn(6).setMinWidth(0);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(7).setMaxWidth(1000);
        tabla.getColumnModel().getColumn(7).setMinWidth(100);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(8).setMaxWidth(1000);
        tabla.getColumnModel().getColumn(8).setMinWidth(100);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(9).setMaxWidth(1000);
        tabla.getColumnModel().getColumn(9).setMinWidth(100);
        tabla.getColumnModel().getColumn(9).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(10).setMaxWidth(1000);
        tabla.getColumnModel().getColumn(10).setMinWidth(100);
        tabla.getColumnModel().getColumn(10).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(11).setMaxWidth(70);
        tabla.getColumnModel().getColumn(11).setMinWidth(70);
        tabla.getColumnModel().getColumn(11).setPreferredWidth(70);
        tabla.getColumnModel().getColumn(12).setMaxWidth(70);
        tabla.getColumnModel().getColumn(12).setMinWidth(70);
        tabla.getColumnModel().getColumn(12).setPreferredWidth(70);
        settext_cod_barra(tabla, tabla.getColumnModel().getColumn(3), null);
        settext_descripcion(tabla, tabla.getColumnModel().getColumn(4), null);
        settext_descrip_corta(tabla, tabla.getColumnModel().getColumn(5), null);
        settext_unidad_medida(tabla, tabla.getColumnModel().getColumn(7), tipo_unidad, unidad_table);
        settext_cantidad(tabla, tabla.getColumnModel().getColumn(8), null);
        settext_p_compra(tabla, tabla.getColumnModel().getColumn(9), null);
        settext_p_venta(tabla, tabla.getColumnModel().getColumn(10), null);
        settext_p_peso(tabla, tabla.getColumnModel().getColumn(11), tipo_unidad);

        tabla.setDragEnabled(false);
        tabla.getTableHeader().setReorderingAllowed(false);

    }

    public void settext_cod_barra(JTable tabla, TableColumn columna, JDialog dialogo) {

        JTextField txt_cod_barra = new JTextField();
        Function_Component.JTextField(txt_cod_barra);
        columna.setCellEditor(new DefaultCellEditor(txt_cod_barra));
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        txt_cod_barra.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent evt) {
                try {
                    char cteclap = evt.getKeyChar();
                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

                        if (txt_cod_barra.getText().length() > 0) {
                            Controller_Producto producto = new Controller_Producto();
                            if (producto.buscar_cod_barra_ALL(txt_cod_barra.getText()).equals("0")) {
                                Function_ShowMessageDialog.ShowMessageDialogvalidacion(null, "ERROR EXISTE COD. DE BARRA EN BASE DE DATOS");

                            }

                            for (int i = 1; i < tabla.getRowCount(); i++) {
                                if (i == tabla.getSelectedRow() + getselection) {

                                } else {
                                    if (txt_cod_barra.getText().equals(tabla.getValueAt(i, 3).toString())) {

                                        Function_ShowMessageDialog.ShowMessageDialogvalidacion(null, "ERROR EXISTE COD. DE BARRA EN ESTA TABLA");

                                        return;
                                    } else {
                                        tabla.editCellAt(tabla.getSelectedRow() + getselection, 4);
                                        Component aComp = tabla.getEditorComponent();
                                        aComp.requestFocus();
                                        break;
                                    }

                                }

                            }
                            tabla.editCellAt(tabla.getSelectedRow() + getselection, 4);
                            Component aComp = tabla.getEditorComponent();
                            aComp.requestFocus();

                        } else {
                            Function_ShowMessageDialog.ShowMessageDialogvalidacion(null, "CAMPO DEBE COMTENER MAS DE UN DIGITO");

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

    }

    public void settext_descripcion(JTable tabla, TableColumn columna, JDialog dialogo) {

        JTextField txt_descripcion = new JTextField();

        Function_Component.JTextField(txt_descripcion);
        Function_Key_Event.Validar_Mayuscula(txt_descripcion);
        txt_descripcion.selectAll();
        columna.setCellEditor(new DefaultCellEditor(txt_descripcion));
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        txt_descripcion.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent evt) {
                char cteclap = evt.getKeyChar();
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    tabla.editCellAt(tabla.getSelectedRow() + getselection, 5);
                    Component aComp = tabla.getEditorComponent();
                    aComp.requestFocus();
                    //aComp.selectAll();
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

    public void settext_descrip_corta(JTable tabla, TableColumn columna, JDialog dialogo) {

        JTextField txt_descrip_corta = new JTextField();
        Function_Component.JTextField(txt_descrip_corta);
        Function_Key_Event.Validar_Mayuscula(txt_descrip_corta);
        columna.setCellEditor(new DefaultCellEditor(txt_descrip_corta));
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        txt_descrip_corta.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent evt) {
                char cteclap = evt.getKeyChar();
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    tabla.editCellAt(tabla.getSelectedRow() + getselection, 7);
                    Component aComp = tabla.getEditorComponent();
                    aComp.requestFocus();
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

    public void settext_unidad_medida(JTable tabla, TableColumn columna, JDialog dialogo, JTable unidad_table) {

        JTextField txt_unidad_medida = new JTextField();
        Function_Component.JTextField(txt_unidad_medida);
        Function_Key_Event.Validar_Mayuscula(txt_unidad_medida);
        columna.setCellEditor(new DefaultCellEditor(txt_unidad_medida));
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        txt_unidad_medida.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent evt) {
                char cteclap = evt.getKeyChar();
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

                    tabla.editCellAt(tabla.getSelectedRow() + getselection, 8);
                    Component aComp = tabla.getEditorComponent();
                    aComp.requestFocus();
                    Dimension desktopSize = View_Escritorio.desktopPane.getSize();
                    Dimension FrameSize = dialogo.getSize();
                    dialogo.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
                    Controller_Producto producto = new Controller_Producto();
                    dialogo.setVisible(true);
                    producto.table_tipo_unidad(unidad_table, txt_unidad_medida.getText());
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

        JTextField txt_cantidad = new JTextField();
        Function_Component.JTextField(txt_cantidad);
        Function_Key_Event.Validar_numeros(txt_cantidad);
        columna.setCellEditor(new DefaultCellEditor(txt_cantidad));
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        txt_cantidad.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent evt) {
                char cteclap = evt.getKeyChar();
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    tabla.editCellAt(tabla.getSelectedRow() + getselection, 9);
                    Component aComp = tabla.getEditorComponent();
                    aComp.requestFocus();
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

    public void settext_p_compra(JTable tabla, TableColumn columna, JDialog dialogo) {

        JTextField txt_p_compra = new JTextField();
        Function_Component.JTextField(txt_p_compra);
        Function_Key_Event.Validar_numeros(txt_p_compra);
        columna.setCellEditor(new DefaultCellEditor(txt_p_compra));
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        txt_p_compra.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent evt) {
                char cteclap = evt.getKeyChar();
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    tabla.editCellAt(tabla.getSelectedRow() + getselection, 10);
                    Component aComp = tabla.getEditorComponent();
                    aComp.requestFocus();
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

    public void settext_p_venta(JTable tabla, TableColumn columna, JDialog dialogo) {

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
                    try {

                        if (tabla.getSelectedRow() + getselection == tabla.getRowCount() - 1) {
                            getselection = getselection + 1;
                            View_Producto.btn_agregar_fila.doClick();
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

    public void settext_p_peso(JTable tabla, TableColumn columna, JDialog dialogo) {

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
                    try {

                        if (tabla.getSelectedRow() + getselection == tabla.getRowCount() - 1) {
                            getselection = getselection + 1;
                            View_Producto.btn_agregar_fila.doClick();
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

    public String buscar_cod_barra(String cod_barra) {
        String valor = "";
        String sent = "DECLARE	@return_value varchar,\n"
                + "		@Resultado varchar(255)\n"
                + "EXEC	@return_value = [dbo].[productos]\n"
                + "		@Id_Solicitud = N'" + cod_barra + "',\n"
                + "		@Resultado = @Resultado OUTPUT\n"
                + "\n"
                + "SELECT	@Resultado as N'Resultado'";
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

    public String buscar_cod_barra_ALL(String cod_barra) {
        String valor = "";
        String sent = "DECLARE	@return_value int,\n"
                + "		@Resultado varchar(255)\n"
                + "\n"
                + "EXEC	@return_value = [dbo].[productos_presentacion_busqueda_cod]\n"
                + "		@Id_Solicitud = N'" + cod_barra + "',\n"
                + "		@Resultado = @Resultado OUTPUT\n"
                + "\n"
                + "SELECT	@Resultado as N'@Resultado'\n"
                + "\n"
                + "SELECT	'Return Value' = @return_value";
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

    public List<Entity_Producto> buscar_cod_barra_producto_presentacion(String cod_barra) {
        List<Entity_Producto> persona = new ArrayList<Entity_Producto>();
        String sent = "SELECT TOP 1 pro.producto_id,\n"
                + "pro.producto_cod,\n"
                + "pro.producto_descrip,\n"
                + "pro.producto_descripcort,\n"
                + "pro.producto_clasifi,cls_pro.producto_cod,cls_pro.producto_descrip,\n"
                + "pro.producto_moneda,tp_mon.moneda_cod,tp_mon.moneda_descrip,\n"
                + "pro.producto_familia,fam.familia_cod,fam.familia_descrip,\n"
                + "pro.producto_medida,ti_uni.unidad_cod,ti_uni.unidad_descrip,\n"
                + "pro.producto_observacion,\n"
                + "producto_tipo_afect,tip_afec.afectacion_cod,tip_afec.afectacion_descr,tip_afec.afectacion_valor,tip_afec.afectacion_cod_valor,\n"
                + "pro.producto_estado,pro.producto_valor,\n"
                + "(ISNULL((SELECT CONVERT(varchar(10), sunat_id) FROM codigo_producto_sunat where sunat_id= detalle.detalle_sunat_cod),''))as'sunat_id',\n"
                + "(ISNULL((select sunat_cod from codigo_producto_sunat where sunat_id= detalle.detalle_sunat_cod), ''))as'sunat_cod',\n"
                + "(ISNULL((select sunat_descrip from codigo_producto_sunat where sunat_id= detalle.detalle_sunat_cod), ''))as'sunat_descrip',\n"
                + "pro.producto_cantidad,\n"
                + "ti_em.embalaje_id,\n"
                + "ti_em.embalaje_cod,\n"
                + "ti_em.embajale_descr	\n"
                + " FROM producto pro\n"
                + "inner join clasificacion_producto cls_pro\n"
                + "on cls_pro.producto_id=pro.producto_clasifi\n"
                + "inner join tipo_moneda tp_mon\n"
                + "on tp_mon.moneda_id=pro.producto_moneda\n"
                + "inner join familia fam\n"
                + "on fam.familia_id=pro.producto_familia\n"
                + "inner join tipo_unidad ti_uni\n"
                + "on ti_uni.unidad_id=pro.producto_medida\n"
                + "inner join tipo_afectacion tip_afec\n"
                + "on tip_afec.afectacion_id=pro.producto_tipo_afect\n"
                + "left join detalle_producto_cod_sunat  detalle\n"
                + "on detalle.detalle_sunat_producto=pro.producto_id\n"
                + "inner join tipo_embalaje ti_em\n"
                + "on ti_em.embalaje_id=pro.producto_embalaje\n"
                + "where pro.producto_cod='" + cod_barra + "'";
        try {
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sent);
            rs.next();
            persona.add(new Entity_Producto(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
                    rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13),
                    rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20),
                    rs.getString(21), rs.getString(22), rs.getBoolean(23), rs.getString(24), rs.getString(25), rs.getString(26), rs.getString(27),
                     rs.getString(28), rs.getString(29), rs.getString(30), rs.getString(31)));
            cxn.desconectar();
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        return persona;
    }

}
