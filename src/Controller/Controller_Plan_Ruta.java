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
import Entity.Entity_Institucion;
import Entity.Entity_Moneda;
import Entity.Entity_Persona;
import Entity.Entity_Plan_ruta;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Caret;
import javax.swing.text.MaskFormatter;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author memo
 */
public class Controller_Plan_Ruta {

    private Conexion cxn;
    private int getselection = 0;
    private boolean[] editable = {false, false, false, false, false,
        false, false, false, true, true,
        true, false, false, false, false,
        false, true, true, true, true,
        true, true, true, true};
    Workbook wb;
    DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
    DecimalFormat formato2;

    /*private generador_codigo generador_id;
    Function_ShowMessageDialog mensaje = new Function_ShowMessageDialog();*/
    public Controller_Plan_Ruta() {

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
            String sSQL1 = "select plan_r.ruta_cod,max(cast(ent.entrega_id as varchar(max))),max(cast(ent.entrega_descripcion as varchar(max))),max(cast(ent.entrega_año as varchar(max)))  FROM plan_ruta plan_r\n"
                    + "inner join entrega ent\n"
                    + "on ent.entrega_id=plan_r.ruta_entrega\n"
                    + "  group by plan_r.ruta_cod  ";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"COD", "IDEN", "DESCRIPCION", "AÑO"});
            while (rs.next()) {

                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),});
            }
            cxn.desconectar();
            venta.setModel(model);
            Function_Component.JTable(venta);
             venta.getColumnModel().getColumn(1).setMaxWidth(0);
            venta.getColumnModel().getColumn(1).setMinWidth(0);
            venta.getColumnModel().getColumn(1).setPreferredWidth(0);
            venta.setDragEnabled(false);
            venta.getTableHeader().setReorderingAllowed(false);
            venta.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
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

    public String Add_Plam_ruta(Entity_Plan_ruta ruta) {
        String respuesta = "";

        String sent = "EXEC	 [dbo].[InsertaTablaplanruta]\n"
                + "		@ruta_id =N'" + ruta.getRuta_id() + "',\n"
                + "		@ruta_cod =N'" + ruta.getRuta_cod() + "',\n"
                + "		@ruta_placa =N'" + ruta.getRuta_placa() + "',\n"
                + "		@ruta_conductor =N'" + ruta.getRuta_conductor() + "',\n"
                + "		@ruta_punto_partida =N'" + ruta.getRuta_punto_partida() + "',\n"
                + "		@ruta_condicion =N'" + ruta.getRuta_condicion() + "',\n"
                + "		@ruta_codigo =N'" + ruta.getRuta_codigo() + "',\n"
                + "		@ruta_peso_total =N'" + ruta.getRuta_peso_total() + "',\n"
                + "		@ruta_dia =N'" + ruta.getRuta_dia() + "',\n"
                + "		@ruta_fecha_estiva = N'" + ruta.getRuta_fecha_estiva() + "',\n"
                + "		@ruta_estiva_hora =N'" + ruta.getRuta_estiva_hora() + "',\n"
                + "		@ruta_observacion =N'" + ruta.getRuta_observacion() + "',\n"
                + "		@ruta_contrato =N'" + ruta.getRuta_contrato() + "',\n"
                + "		@ruta_entrega = N'" + ruta.getRuta_entrega() + "'";

        try {
            cxn.conectardb().createStatement().executeUpdate(sent);
            cxn.desconectar();
            respuesta = "exito";

        } catch (SQLException ex) {
            respuesta = "fallo";
            System.out.println("Controller.Controller_Plan_Ruta.Add_Plam_ruta()" + ruta.getRuta_codigo());
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

    public String plan_ruta(String anexo) {
        String respueta = "";
        String sent = "DECLARE	@return_value int,\n"
                + "		@Resultado varchar(255)\n"
                + "\n"
                + "EXEC	@return_value = [dbo].[plan_rutas]\n"
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
        } catch (SQLException ex) {
            respuesta = "error";
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        return respuesta;
    }

    public void table_carro(JTable carro) {
        try {
            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String sSQL1 = "SELECT * FROM [dbo].[carro]  ";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"PLACA", "CARRO", "TARJETA PROPIEDAD"});
            while (rs.next()) {

                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3)});
            }
            cxn.desconectar();
            carro.setModel(model);
            Function_Component.JTable(carro);

            carro.setDragEnabled(false);
            carro.getTableHeader().setReorderingAllowed(false);
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }

    public void table_conductor(JTable conductor) {
        try {
            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String sSQL1 = "SELECT [coductor_dni_ruc],[coductor_nombre] FROM [dbo].[conductor]  ";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"DNI", "NOMBRE"});
            while (rs.next()) {

                model.addRow(new Object[]{rs.getString(1), rs.getString(2)});
            }
            cxn.desconectar();
            conductor.setModel(model);
            Function_Component.JTable(conductor);

            conductor.setDragEnabled(false);
            conductor.getTableHeader().setReorderingAllowed(false);
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }

    public void tabla_institucion(JTable tabla, String buscar) {
        JButton btnEliminar = new JButton("");
        btnEliminar.setName("btneliminar");
        btnEliminar.setIcon(new ImageIcon("src\\Icon\\icon_eliminar.png"));
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel model = new DefaultTableModel(new Object[]{"DEPARTAMENTO",
            "PROVINCIA", "DISTRITO", "CENTROPOBLADO", "CODIGO MODULAR", "NOMBRE", "DIRECCION	", "NIVEL", "USUARIOS",
            "ORDEN"}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
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
            String sSQL1 = "SELECT \n"
                    + "      [institucion_departamento]\n"
                    + "      ,[institucion_provincia]\n"
                    + "      ,[institucion_distrito]\n"
                    + "      ,[institucion_centro_poblado]\n"
                    + "      ,[institucion_codigo_modular]\n"
                    + "      ,[institucion_nombre]\n"
                    + "      ,[institucion_direccion]\n"
                    + "      ,[institucion_nivel]\n"
                    + "      ,[institucion_usuario]\n"
                    + "      ,[institucion_orden]\n"
                    + "  FROM [institucion]  where institucion_estado='true' AND(cast(institucion_iten as NVARCHAR(max))) like '%" + buscar + "%' ORDER BY institucion_orden  ASC";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            while (rs.next()) {
                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                    rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getInt(10)});

            }
            cxn.desconectar();
        } catch (Exception e) {

        }
        tabla.setModel(model);
        Function_Component.JTable(tabla);
        Function_Key_Event.Validar_numeros_jtable(tabla);

        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.setDragEnabled(false);
        tabla.getTableHeader().setReorderingAllowed(false);

    }

    public void add_plan_ruta(JTable tabla, String buscar) {
        JButton btnEliminar = new JButton("");
        btnEliminar.setName("btneliminar");
        btnEliminar.setIcon(new ImageIcon("src\\Icon\\icon_eliminar.png"));

        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel model = new DefaultTableModel(new Object[]{"ELI", "", "ID_RUTA", "PLACA", "CARRO", "TARJETA PROPIEDAD", "DNI CONDUCTOR",
            "NOMBRE", "PUNTO PARTIDA", "RUTA CONDICION", "CODIGO MODULAR", "INSTITUCION", "DIRECCION", "DISTRITO", "CENTRO POBLADO", "ITEM", "PESO",
            "DIA", "FECHA ESTIVA", "HORA ESTIVA", "CONTRATO", "OBSERVACION"}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
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

            //   model = (DefaultTableModel) tabla.getModel();
            String sSQL1 = "SELECT institucion_codigo_modular,"
                    + "            institucion_nombre \n"
                    + "         ,[institucion_direccion]\n"
                    + "         ,[institucion_distrito],\n"
                    + "		 institucion_centro_poblado,institucion_iten\n"
                    + "  FROM [dbo].[institucion] where (cast(institucion_iten as NVARCHAR(max)))='" + buscar + "' and institucion_estado='true' order by institucion_orden asc";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);
            int suma = 0;
            while (rs.next()) {
                model.addRow(new Object[]{btnEliminar, suma, "", "", "", "", "",
                    "", "", "", rs.getString(1), rs.getString(2), rs.getString(3),
                    rs.getString(4), rs.getString(5), rs.getString(6), "", "", "", "", "", ""});
                suma++;

            }
            cxn.desconectar();
        } catch (Exception e) {
            System.out.println("Controller.Controller_Plan_Ruta.add_plan_ruta()" + e);
        }

        tabla.setModel(model);
        Function_Key_Event.Validar_numeros_jtable(tabla);
        tabla.getColumnModel().getColumn(1).setCellRenderer(tabla.getTableHeader().getDefaultRenderer());
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
 /*  settext_cod_barra(tabla, tabla.getColumnModel().getColumn(3), dialog_producto, producto);

        settext_cantidad(tabla, tabla.getColumnModel().getColumn(6), null);
        settext_cantidad(tabla, tabla.getColumnModel().getColumn(7), null);
        settext_cantidad(tabla, tabla.getColumnModel().getColumn(8), null);*/
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            settxt_text(tabla, tabla.getColumnModel().getColumn(i), null);
        }

        //El método valueChange se debe sobreescribir obligatoriamente.
        //Se ejecuta automáticamente cada vez que se hace una nueva selección.
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.setDragEnabled(false);
        tabla.getTableHeader().setReorderingAllowed(false);

    }

    public void tabla_plan_ruta(JTable tabla, String cod) {
        JButton btnEliminar = new JButton("");
        btnEliminar.setName("btneliminar");
        btnEliminar.setIcon(new ImageIcon("src\\Icon\\icon_eliminar.png"));
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel model = new DefaultTableModel(new Object[]{"ELI", "", "ID_RUTA", "PLACA", "CARRO", "TARJETA PROPIEDAD", "DNI CONDUCTOR",
            "NOMBRE", "PUNTO PARTIDA", "RUTA CONDICION", "CODIGO MODULAR", "INSTITUCION", "DIRECCION", "DISTRITO", "CENTRO POBLADO", "ITEM", "PESO",
            "DIA", "FECHA ESTIVA", "HORA ESTIVA", "CONTRATO", "OBSERVACION"}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
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
            String sSQL1 = "select \n"
                    + "rut.ruta_id,\n"
                    + "rut.ruta_placa,\n"
                    + "car.carro_nombre,\n"
                    + "car.tarjeta_propiedad,\n"
                    + "rut.ruta_conductor,\n"
                    + "con.coductor_nombre,\n"
                    + "rut.ruta_punto_partida,\n"
                    + "rut.ruta_condicion,\n"
                    + "ins.institucion_codigo_modular,\n"
                    + "ins.institucion_nombre,\n"
                    + "ins.institucion_direccion,\n"
                    + "ins.institucion_distrito,\n"
                    + "ins.institucion_centro_poblado,\n"
                    + "ins.institucion_iten,\n"
                    + "rut.ruta_peso_total,\n"
                    + "rut.ruta_dia,\n"
                    + "rut.ruta_fecha_estiva,\n"
                    + "rut.ruta_estiva_hora,\n"
                    + "rut.ruta_contrato,\n"
                    + "rut.ruta_observacion from plan_ruta rut\n"
                    + "inner join carro car\n"
                    + "on car.carro_placa =rut.ruta_placa\n"
                    + "inner join institucion ins\n"
                    + "on ins.institucion_codigo_modular=rut.ruta_codigo\n"
                    + "inner join conductor con\n"
                    + "on con.coductor_dni_ruc=rut.ruta_conductor where ruta_cod='" + cod + "'";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);
            int suma = 0;
            while (rs.next()) {
                model.addRow(new Object[]{btnEliminar, suma, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
                    rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
                    rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20)});
                suma++;
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
        tabla.getColumnModel().getColumn(1).setCellRenderer(tabla.getTableHeader().getDefaultRenderer());
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
        //  settext_cod_barra(tabla, tabla.getColumnModel().getColumn(3), dialog_producto, producto);
        settext_cantidad(tabla, tabla.getColumnModel().getColumn(6), null);
        settext_cantidad(tabla, tabla.getColumnModel().getColumn(7), null);
        settext_cantidad(tabla, tabla.getColumnModel().getColumn(8), null);
        for (int i = 0; i < tabla.getColumnCount(); i++) {
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
                            Controller_Plan_Ruta almacen = new Controller_Plan_Ruta();
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

    /*buscar txt producto por cod compra*/
    public void Subir_plan_ruta(JTable tabla, File archivo) {
        List<String> a = new Controller_Plan_Ruta().Importar(archivo);

        JButton btnEliminar = new JButton("");
        btnEliminar.setName("btneliminar");
        btnEliminar.setIcon(new ImageIcon("src\\Icon\\icon_eliminar.png"));
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel model = new DefaultTableModel(new Object[]{"ELI", "", "ID_RUTA", "PLACA", "CARRO", "TARJETA PROPIEDAD", "DNI CONDUCTOR",
            "NOMBRE", "PUNTO PARTIDA", "RUTA CONDICION", "CODIGO MODULAR", "INSTITUCION", "DIRECCION", "DISTRITO", "CENTRO POBLADO", "ITEM", "PESO",
            "DIA", "FECHA ESTIVA", "HORA ESTIVA", "CONTRATO", "OBSERVACION"}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Boolean.class
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
        int contador = 0;
        int suma = 0;

        for (int i = 0; i < a.size() / 20; i++) {
            model.addRow(new Object[]{btnEliminar, suma, a.get(0 + contador), a.get(1 + contador), a.get(2 + contador), a.get(3 + contador), a.get(4 + contador),
                a.get(5 + contador), a.get(6 + contador), a.get(7 + contador), a.get(8 + contador), a.get(9 + contador), a.get(10 + contador), a.get(11 + contador),
                a.get(12 + contador), a.get(13 + contador), a.get(14 + contador), a.get(15 + contador), a.get(16 + contador), a.get(17 + contador), a.get(18 + contador),
                a.get(19 + contador)});
            contador = contador + 20;
        }


        /*for (int i = 0; i < 1; i++) {
            model.addRow(new Object[]{btnEliminar, "", "", "", "", "", "", "", "", "", "", "", "", "", ""});
        }*/
        tabla.setModel(model);
        Function_Component.JTable(tabla);
        Function_Key_Event.Validar_numeros_jtable(tabla);

        tabla.getColumnModel().getColumn(1).setCellRenderer(tabla.getTableHeader().getDefaultRenderer());

        for (int i = 0; i < tabla.getColumnCount(); i++) {
            settxt_text(tabla, tabla.getColumnModel().getColumn(i), null);
        }

        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.setDragEnabled(false);
        tabla.getTableHeader().setReorderingAllowed(false);

    }

    public List<String> Importar(File archivo) {
        List<String> institucion = new ArrayList<String>();
        String respuesta = "No se pudo realizar la importación.";
        DefaultTableModel modeloT = new DefaultTableModel();
        /*tablaD.setModel(modeloT);
        tablaD.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);*/
        Object value = null;
        DecimalFormatSymbols simbolos = new DecimalFormatSymbols();
        simbolos.setDecimalSeparator('.');
//DecimalFormat formateador = new DecimalFormat("####.####",simbolos);
        DecimalFormat df = new DecimalFormat("0");  //格式化number String字符
        DecimalFormat df2 = new DecimalFormat("0.000", simbolos);  //格式化数字

        try {
            wb = WorkbookFactory.create(new FileInputStream(archivo));
            Sheet hoja = wb.getSheetAt(0);
            Iterator filaIterator = hoja.rowIterator();
            int indiceFila = -1;
            while (filaIterator.hasNext()) {
                indiceFila++;
                Row fila = (Row) filaIterator.next();
                Iterator columnaIterator = fila.cellIterator();
                Object[] listaColumna = new Object[1000];
                int indiceColumna = -1;
                while (columnaIterator.hasNext()) {
                    indiceColumna++;
                    Cell celda = (Cell) columnaIterator.next();
                    if (indiceFila == 0) {
                        modeloT.addColumn(celda.getStringCellValue());
                    } else {
                        if (celda != null) {
                            //  System.out.println("" +listaColumna[indiceColumna]);
                            switch (celda.getCellType()) {

                                case Cell.CELL_TYPE_NUMERIC:
                                    //listaColumna[indiceColumna]= (int)Math.round(celda.getNumericCellValue());
                                    //  System.out.println("Modelo.ModeloExcel.Importar()"+celda.getCellStyle().getDataFormat());

                                    //listaColumna[indiceColumna]= String.valueOf(celda.getNumericCellValue());
                                    if ("General".equals(celda.getCellStyle().getDataFormatString())) {
                                        listaColumna[indiceColumna] = df.format(celda.getNumericCellValue());
                                    } else {
                                        listaColumna[indiceColumna] = df2.format(celda.getNumericCellValue());
                                    }
                                    break;
                                case Cell.CELL_TYPE_STRING:

                                    listaColumna[indiceColumna] = celda.getStringCellValue();
                                    break;
                                case Cell.CELL_TYPE_BOOLEAN:

                                    listaColumna[indiceColumna] = celda.getBooleanCellValue();
                                    break;
                                case Cell.CELL_TYPE_FORMULA:

                                    if ("General".equals(celda.getCellStyle().getDataFormatString())) {
                                        listaColumna[indiceColumna] = df.format(celda.getNumericCellValue());
                                    } else {
                                        listaColumna[indiceColumna] = df2.format(celda.getNumericCellValue());
                                    }
                                    break;
                                case Cell.CELL_TYPE_BLANK:

                                    listaColumna[indiceColumna] = celda.getStringCellValue();

                                    /* System.out.println("sss" +listaColumna[indiceColumna]);
  System.out.println("col"+indiceColumna+" valor: true - "+celda+".");*/
                                    System.out.println("" + celda.getCellType());
                                    break;

                                default:
                                    listaColumna[indiceColumna] = celda.getDateCellValue();
                                    break;
                            }
                            //System.out.println("col"+indiceColumna+" valor: true - "+celda+".");

                            if (listaColumna[indiceColumna] == (null)) {
                                System.out.println("" + listaColumna[indiceColumna]);
                                listaColumna[indiceColumna] = "";
                                System.out.println("" + listaColumna[indiceColumna]);
                            } else if (listaColumna[indiceColumna] == "") {
                                System.out.println("" + listaColumna[indiceColumna]);

                            }

                            /*  if (celda == null || celda.getCellType() == Cell.CELL_TYPE_BLANK) {
    // This cell is emptySystem.out.print(celda.getStringCellValue() + "\t\t"
 }*/
                            institucion.add((String) listaColumna[indiceColumna]);
                            //        System.out.println(""+(String) listaColumna[indiceColumna]);
                            //   System.out.println("col"+indiceColumna+" valor: true - "+celda+".");
                        }
                    }
                }
                if (indiceFila != 0) {
                    modeloT.addRow(listaColumna);
                }
            }
            respuesta = "Importación exitosa";
        } catch (IOException | InvalidFormatException | EncryptedDocumentException e) {
            System.err.println(e.getMessage());
        }
        return institucion;
    }

}
