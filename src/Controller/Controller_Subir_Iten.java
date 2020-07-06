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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.TransferHandler;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
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
public class Controller_Subir_Iten {

    private Conexion cxn;
    private int getselection = 0;
    private int getselection_almacen = 0;
    private int contador_columna = 17;
    private boolean[] editable = {false, true, true, true, true, true, true, true, true};
    private boolean[] editable_almacen = {false, false, true, true, false, true};
    DefaultTableModel model;
    DefaultTableModel model_almacen;

    /*private generador_codigo generador_id;
    Function_ShowMessageDialog mensaje = new Function_ShowMessageDialog();*/
    public Controller_Subir_Iten() {
        cxn = new Conexion();
        //generador_id = new generador_codigo();
    }

    public void reset_variable() {
        getselection = 0;
    }

    public int variable() {
        return getselection;
    }

    public void reset_variable_almacen() {
        getselection_almacen = 0;
    }

    public int variable_almacen() {
        return getselection_almacen;
    }

    public int variable_contador() {
        return contador_columna;
    }

    public void add_subir_iten(JTable tabla, String dato) {
        JButton btnEliminar = new JButton("");
        btnEliminar.setName("btneliminar");
        btnEliminar.setIcon(new ImageIcon("src\\Icon\\icon_eliminar.png"));
        tabla.setDefaultRenderer(Object.class, new Render());
        /*  model = new DefaultTableModel(new Object[]{"", "ID_SUC", "ID_EMPRE", "COD_EMPRESA"}, 0) {

        };*/
        model = (DefaultTableModel) tabla.getModel();
        model.addColumn(dato);
        // model.addRow(new Object[]{btnEliminar, "", "", "", "", "", "", "", true});
        contador_columna++;
        tabla.setModel(model);
        Function_Key_Event.Validar_numeros_jtable(tabla);
        Function_Component.JTable(tabla);
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
        tabla.getColumnModel().getColumn(4).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(8).setMaxWidth(100);
        tabla.getColumnModel().getColumn(8).setMinWidth(100);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(100);

        settext_cod_empresa(tabla, tabla.getColumnModel().getColumn(3));
        settext_direccion(tabla, tabla.getColumnModel().getColumn(4));
        settext_descripcion(tabla, tabla.getColumnModel().getColumn(5));
        settext_url(tabla, tabla.getColumnModel().getColumn(6));
        settext_p_token(tabla, tabla.getColumnModel().getColumn(7));*/
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            settext_cod_empresa(tabla, tabla.getColumnModel().getColumn(i));
        }
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.setDragEnabled(false);
        tabla.getTableHeader().setReorderingAllowed(false);

    }

    public void add_subir_iten_anexo(JTable tabla, String dato) {
        JButton btnEliminar = new JButton("");
        btnEliminar.setName("btneliminar");
        btnEliminar.setIcon(new ImageIcon("src\\Icon\\icon_eliminar.png"));
        tabla.setDefaultRenderer(Object.class, new Render());
        /*  model = new DefaultTableModel(new Object[]{"", "ID_SUC", "ID_EMPRE", "COD_EMPRESA"}, 0) {

        };*/
        model = (DefaultTableModel) tabla.getModel();
        model.addColumn(dato);

        // model.addRow(new Object[]{btnEliminar, "", "", "", "", "", "", "", true});
        contador_columna++;
        tabla.setModel(model);
        Function_Key_Event.Validar_numeros_jtable(tabla);
        Function_Component.JTable(tabla);
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
        tabla.getColumnModel().getColumn(4).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(8).setMaxWidth(100);
        tabla.getColumnModel().getColumn(8).setMinWidth(100);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(100);

        settext_cod_empresa(tabla, tabla.getColumnModel().getColumn(3));
        settext_direccion(tabla, tabla.getColumnModel().getColumn(4));
        settext_descripcion(tabla, tabla.getColumnModel().getColumn(5));
        settext_url(tabla, tabla.getColumnModel().getColumn(6));
        settext_p_token(tabla, tabla.getColumnModel().getColumn(7));*/
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            settext_cod_empresa(tabla, tabla.getColumnModel().getColumn(i));
        }
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.setDragEnabled(false);
        tabla.getTableHeader().setReorderingAllowed(false);

    }

    public void table_iten(JTable tabla, String buscar) {
        JButton btnEliminar = new JButton("");
        btnEliminar.setName("btneliminar");
        btnEliminar.setIcon(new ImageIcon("src\\Icon\\icon_eliminar.png"));
        tabla.setDefaultRenderer(Object.class, new Render());
        model = new DefaultTableModel(new Object[]{"", "Nº ORDEN", "RUTA", "CODIGO MODULAR", "COLEGIO", "FECHA EMISION DE GUIA",
            "MOTIVO", "TIPO TRAMSPOR", "FECHA INICIO DE TRASLADO", "RUC TRANSPORTISTA", "PLACA", "DNI CODUCTOR", "UBIGEO", "DIRECCION PARTIDA",
            "UBIGEO", "DIRECCION LLEGADA", "MOTIVO SALIDA", "ALMACEN"}, 0) {

        };
        try {
            String sSQL1 = "SELECT institucion_orden,institucion_ruta \n"
                    + "         ,[institucion_codigo_modular]\n"
                    + "         ,[institucion_nombre],\n"
                    + "		 institucion_ubigeo,\n"
                    + "		 institucion_direccion\n"
                    + "  FROM [dbo].[institucion] where (cast(institucion_iten as NVARCHAR(max)))='" + buscar + "' and institucion_estado='true' order by institucion_orden asc";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);
            model.addRow(new Object[]{" ", " ", " ", " ", " ", " ", "01 = VENTA", "01 = TRANSPORTE PÚBLICO", " ", " ", " ", " ", " ", " ", " ", " ", " ", "1 = ALMACEN YMEN"});
            model.addRow(new Object[]{" ", " ", " ", " ", " ", " ", "02 = COMPRA", "02 = TRANSPORTE PRIVADO", " ", " ", " ", " ", " ", " ", " ", " ", " ", "2 = ALMACEN CIASA "});
            for (int i = 0; i < 4; i++) {
                model.addRow(new Object[]{" ", "", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "});
            }
            while (rs.next()) {
                model.addRow(new Object[]{" ", rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), " ", "01", "02", " ", "20490786849", " ", " ", "170101", " AV. ANDRES AVELINO CACERES 4041, KM 7.5 - TAMBOPATA-TAMBOPATA-MADRE DE DIOS", rs.getString(5), rs.getString(6), " ", " "});

            }
            cxn.desconectar();
        } catch (Exception e) {

        }
        tabla.setModel(model);
        Function_Key_Event.Validar_numeros_jtable(tabla);
        Function_Component.JTable(tabla);

        /*tabla.getColumnModel().getColumn(0).setMaxWidth(50);
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
        tabla.getColumnModel().getColumn(4).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(8).setMaxWidth(100);
        tabla.getColumnModel().getColumn(8).setMinWidth(100);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(100);

        settext_cod_empresa(tabla, tabla.getColumnModel().getColumn(3));
        settext_direccion(tabla, tabla.getColumnModel().getColumn(4));
        settext_descripcion(tabla, tabla.getColumnModel().getColumn(5));
        settext_url(tabla, tabla.getColumnModel().getColumn(6));
        settext_p_token(tabla, tabla.getColumnModel().getColumn(7));*/
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            settext_cod_empresa(tabla, tabla.getColumnModel().getColumn(i));
        }
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.setDragEnabled(true);
        tabla.getTableHeader().setReorderingAllowed(false);
        /*tabla.setTransferHandler(new TransferHandler("testProperty"));
       tabla
        .getSelectionModel()
        .addListSelectionListener(
            new ListSelectionListener() {
              public void valueChanged(ListSelectionEvent evt) {
                ///selectionChanged();
                  System.out.println(".valueChanged()");
              }

           
            });

    tabla.addMouseListener(
        new MouseAdapter() {
          public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2) {
                System.out.println(".mouseClicked()");
            }
          }
        });
         */
        //  tabla.setDropMode(DropMode.INSERT_ROWS);

        /* TransferHandler tableTransfer = new TransferHandler() {
            @Override
            public boolean canImport(JComponent comp,
                    DataFlavor[] transferFlavors) {
                return true;
            }
        };*/
        //   tabla.setTransferHandler(tableTransfer);
    }

    public void settext_cod_empresa(JTable tabla, TableColumn columna) {
        try {

            JTextField txt_cod_empresa = new JTextField();
            Caret seleccion = txt_cod_empresa.getCaret();
            Function_Component.JTextField(txt_cod_empresa);
            Function_Key_Event.Validar_Mayuscula(txt_cod_empresa);
            columna.setCellEditor(new DefaultCellEditor(txt_cod_empresa));
            DefaultTableCellRenderer render = new DefaultTableCellRenderer();
            txt_cod_empresa.addKeyListener(new KeyListener() {
                @Override
                public void keyPressed(KeyEvent evt) {
                    try {

                        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

                            if (txt_cod_empresa.getText().length() != 0) {

                                tabla.editCellAt(tabla.getSelectedRow() + getselection, 4);
                                Component aComp = tabla.getEditorComponent();
                                aComp.requestFocus();

                            }
                            evt.consume();
                        }

                    } catch (Exception e) {
                        System.out.println(".()" + e);
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

    public void settext_direccion(JTable tabla, TableColumn columna) {

        JTextField txt_direccion = new JTextField();
        Function_Component.JTextField(txt_direccion);
        Function_Key_Event.Validar_Mayuscula(txt_direccion);
        columna.setCellEditor(new DefaultCellEditor(txt_direccion));
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        txt_direccion.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent evt) {

                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (txt_direccion.getText().length() != 0) {
                        tabla.editCellAt(tabla.getSelectedRow() + getselection, 5);
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

    public void settext_descripcion(JTable tabla, TableColumn columna) {
        try {

            JTextField txt_descripcion = new JTextField();
            Function_Component.JTextField(txt_descripcion);
            Function_Key_Event.Validar_Mayuscula(txt_descripcion);
            columna.setCellEditor(new DefaultCellEditor(txt_descripcion));
            DefaultTableCellRenderer render = new DefaultTableCellRenderer();
            txt_descripcion.addKeyListener(new KeyListener() {
                @Override
                public void keyPressed(KeyEvent evt) {

                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                        if (txt_descripcion.getText().length() != 0) {
                            tabla.editCellAt(tabla.getSelectedRow() + getselection, 6);
                            Component aComp = tabla.getEditorComponent();
                            aComp.requestFocus();

                        }
                        evt.consume();

                    }
                    if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                        try {

                            if (tabla.getSelectedRow() + getselection == tabla.getRowCount() - 1) {
                                getselection = getselection + 1;

                                View.View_Empresa.btn_agregar_fila.doClick();
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

    public void settext_url(JTable tabla, TableColumn columna) {

        JTextField txt_url = new JTextField();
        Function_Component.JTextField(txt_url);
        columna.setCellEditor(new DefaultCellEditor(txt_url));
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        txt_url.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent evt) {

                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

                    tabla.editCellAt(tabla.getSelectedRow() + getselection, 7);
                    Component aComp = tabla.getEditorComponent();
                    aComp.requestFocus();
                    evt.consume();

                }
                if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                    try {

                        if (tabla.getSelectedRow() + getselection == tabla.getRowCount() - 1) {
                            getselection = getselection + 1;

                            View.View_Empresa.btn_agregar_fila.doClick();
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

    public void settext_p_token(JTable tabla, TableColumn columna) {

        JTextField txt_p_tokem = new JTextField();
        Function_Component.JTextField(txt_p_tokem);
        columna.setCellEditor(new DefaultCellEditor(txt_p_tokem));
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        txt_p_tokem.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent evt) {

                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

                    if (tabla.getSelectedRow() + getselection == tabla.getRowCount() - 1) {
                        getselection = getselection + 1;

                        View.View_Empresa.btn_agregar_fila.doClick();
                        tabla.editCellAt(tabla.getSelectedRow() + getselection, 3);
                        Component aComp = tabla.getEditorComponent();
                        aComp.requestFocus();
                    } else {
                        getselection = getselection + 1;
                        tabla.editCellAt(tabla.getSelectedRow() + getselection, 3);
                        Component aComp = tabla.getEditorComponent();
                        aComp.requestFocus();
                    }

                    evt.consume();
                }
                if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                    try {

                        if (tabla.getSelectedRow() + getselection == tabla.getRowCount() - 1) {
                            getselection = getselection + 1;

                            View.View_Empresa.btn_agregar_fila.doClick();
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

    public void tabla_producto(JTable producto) {
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
                    + "where pre.present_cod_unidad='1' and  pre.present_estado='true' \n"
                    + "group by com.det_com_pro_cod_lote,com.det_com_pro_fecha_venc,pro.producto_id";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"", "ID", "DESCRIPCIÒN", "MARCA", "COD LOTE", "FECHA VENC", "FECHA PROD", "PESO", "TOTAL"});
            int contador = 0;
            while (rs.next()) {
                contador++;
                model.addRow(new Object[]{contador, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)});
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
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }

    public void cbxItem(JComboBox iten) {
        String sent = "SELECT  (cast(institucion_iten as NVARCHAR(max))) \n"
                + "  FROM institucion\n"
                + "  group by (cast(institucion_iten as NVARCHAR(max)))";
        try {
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sent);
            while (rs.next()) {
                iten.addItem(rs.getString(1));
            }
            cxn.desconectar();
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }

    public void cbxItem_anexo(JComboBox anexo, String entrega) {
        String sent = "select anexo_cod from anexo_9\n"
                + "where cast(anexo_entrega as varchar(max))='" + entrega + "'\n"
                + "group by anexo_cod";
        try {
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sent);
            while (rs.next()) {
                anexo.addItem(rs.getString(1));
            }
            cxn.desconectar();
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }

    public String codigo_modular_orden(String codigo_modular) {
        String respuesta = "";
        try {
            String sSQL1 = "SELECT TOP 1\n"
                    + "      [institucion_orden]\n"
                    + "  FROM [dbo].[institucion] where institucion_codigo_modular like'%" + codigo_modular + "%'";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);
            rs.next();
            respuesta = rs.getString(1);

            cxn.desconectar();
        } catch (SQLException ex) {
            respuesta = "0";
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        return respuesta;
    }

    public String codigo_modular_guia(String codigo_modular) {
        String respuesta = "";
        try {
            String sSQL1 = "select \n"
                    + "ven.venta_serie,ven.venta_numero\n"
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
                    + "where ven.venta_concepto=4 and ven.venta_entrega='3'  and ins.institucion_codigo_modular='" + codigo_modular + "'";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);
            rs.next();
            respuesta = rs.getString(1) + "-" + rs.getString(2);

            cxn.desconectar();
        } catch (SQLException ex) {
            respuesta = "0";
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        return respuesta;
    }

    public String codigo_modular_institucion(String codigo_modular) {
        String respuesta = "";
        try {
            String sSQL1 = "select \n"
                    + "ins.institucion_nombre\n"
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
                    + "where ven.venta_concepto=4 and ven.venta_entrega='2'  and ins.institucion_codigo_modular='" + codigo_modular + "'";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);
            rs.next();
            respuesta = "ENTREGA DE ALIMENTOS A LA II.EE:" + rs.getString(1);

            cxn.desconectar();
        } catch (SQLException ex) {
            respuesta = "0";
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        return respuesta;
    }

    public String validar_dni(String codigo_modular) {
        String respuesta = "";
        try {
            String sSQL1 = "SELECT top 1\n"
                    + "      [padron_apellidos_nombres]\n"
                    + "  FROM [dbo].[padron] where [padron_dni]='" + codigo_modular + "'";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);
            rs.next();
            respuesta = rs.getString(1);

            cxn.desconectar();
        } catch (SQLException ex) {
            respuesta = "0";
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        return respuesta;
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

    public String Add_equipo_pedido(Entity_Equipo_colegio equipo) {
        String respuesta = "";

        String sent = "EXEC	[dbo].[InsertaTablaEquipo]\n"
                + "		@equi_col_id = N'" + equipo.getEqui_col_id() + "',\n"
                + "		@equi_col_colegio = N'" + equipo.getEqui_col_colegio() + "',\n"
                + "		@equi_col_entrega = N'" + equipo.getEqui_col_entrega() + "',\n"
                + "		@equi_col_equipo = N'" + equipo.getEqui_col_equipo() + "',\n"
                + "		@equi_col_cantida = N'" + equipo.getEqui_col_cantida() + "'";
        try {
            cxn.conectardb().createStatement().executeUpdate(sent);
            cxn.desconectar();
            respuesta = "exito";

        } catch (SQLException ex) {
            respuesta = "fallo";

            // Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        return respuesta;
    }

    public void table_bolsa(JTable equipo,String item,String entrega) {
        try {
            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String sSQL1 = "SELECT  equi_col_colegio,max(cast(ins.institucion_nombre as varchar(max))),max(cast(ins.institucion_ruta as varchar(max))),\n"
                    + "(select top 1 equi_col_cantida from equipo_colegio where equi_col_equipo='3' and equi_col_colegio=eq.equi_col_colegio) ,\n"
                    + "(select top 1 equi_col_cantida from equipo_colegio where equi_col_equipo='4' and equi_col_colegio=eq.equi_col_colegio),\n"
                    + "(select top 1 equi_col_cantida from equipo_colegio where equi_col_equipo='5' and equi_col_colegio=eq.equi_col_colegio),\n"
                    + "(select top 1 equi_col_cantida from equipo_colegio where equi_col_equipo='6' and equi_col_colegio=eq.equi_col_colegio),\n"
                    + "(select top 1 equi_col_cantida from equipo_colegio where equi_col_equipo='7' and equi_col_colegio=eq.equi_col_colegio),\n"
                    + "(select top 1 equi_col_cantida from equipo_colegio where equi_col_equipo='8' and equi_col_colegio=eq.equi_col_colegio),max(cast(equi_col_cantida as varchar(max)))\n"
                    + "     /* ,[equi_col_entrega]\n"
                    + "      ,[equi_col_equipo]\n"
                    + "      ,[equi_col_cantida]*/\n"
                    + "  FROM equipo_colegio eq\n"
                    + "  inner join institucion ins\n"
                    + "  on ins.institucion_codigo_modular=eq.equi_col_colegio\n"
                    + "  where cast(ins.institucion_iten as varchar(max))='"+item+"' AND equi_col_entrega='"+entrega+"'\n"
                    + "	group by equi_col_colegio	\n"
                    + "	order by max(cast(ins.institucion_ruta as varchar(max))) asc	";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"COD MODULAR", "NOMBRE", "RUTA", "BOLSA VERDE 25", "BOLSA VERDE 50", "BOLSA VERDE 180", "BOLSA NEGRO 25", "BOLSA NEGRO 50", "BOLSA NEGRO 25"});
            int contador = 0;
            while (rs.next()) {
                contador++;
                model.addRow(new Object[]{ rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),rs.getString(9)});
            }
            cxn.desconectar();
            equipo.setModel(model);
            Function_Component.JTable(equipo);
            /*equipo.getColumnModel().getColumn(0).setCellRenderer(equipo.getTableHeader().getDefaultRenderer());
            equipo.getColumnModel().getColumn(0).setMaxWidth(50);
            equipo.getColumnModel().getColumn(0).setMinWidth(50);
            equipo.getColumnModel().getColumn(0).setPreferredWidth(50)
            /*tabla_igv.getColumnModel().getColumn(1).setMaxWidth(0);
            tabla_igv.getColumnModel().getColumn(1).setMinWidth(0);
            tabla_igv.getColumnModel().getColumn(1).setPreferredWidth(0);
            tabla_igv.getColumnModel().getColumn(3).setMaxWidth(0);
            tabla_igv.getColumnModel().getColumn(3).setMinWidth(0);
            tabla_igv.getColumnModel().getColumn(3).setPreferredWidth(0);*/

            equipo.setDragEnabled(false);
            equipo.getTableHeader().setReorderingAllowed(false);
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }

    }

    public String codigo_modular(String codigo_modular) {
        String respuesta = "";
        try {
            String sSQL1 = "SELECT top 1\n"
                    + "     institucion_codigo_modular\n"
                    + "  FROM [dbo].[institucion] where institucion_codigo_modular like '%" + codigo_modular + "%'";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);
            rs.next();
            respuesta = rs.getString(1);

            cxn.desconectar();
        } catch (SQLException ex) {
            respuesta = "0";
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

    public String Add_guia(Entity_Guia guia) {
        String respuesta = "";
        String sent = "EXEC	[dbo].[InsertaTablaguia]\n"
                + "		@guia_id = N'" + guia.getGuia_id() + "',\n"
                + "		@guia_venta = N'" + guia.getGuia_venta() + "',\n"
                + "		@guia_fecha_emision = N'" + guia.getGuia_fecha_emision() + "',\n"
                + "		@guia_observaciones = N'" + guia.getGuia_observaciones() + "',\n"
                + "		@guia_motivo = N'" + guia.getGuia_motivo() + "',\n"
                + "		@guia_peso_bruto = N'" + guia.getGuia_peso_bruto() + "',\n"
                + "		@guia_bulto = N'" + guia.getGuia_bulto() + "',\n"
                + "		@guia_tipo_transportista = N'" + guia.getGuia_tipo_transportista() + "',\n"
                + "		@guia_fecha_inicio_traslado =N'" + guia.getGuia_fecha_inicio() + "',\n"
                + "		@guia_transportista = N'" + guia.getGuia_transportista() + "',\n"
                + "		@guia_placa = N'" + guia.getGuia_placa() + "',\n"
                + "		@guia_conductor = N'" + guia.getGuia_conductor() + "',\n"
                + "		@guia_ubigeo_partida = N'" + guia.getGuia_ubigeo_partida() + "',\n"
                + "		@guia_ubigeo_partida_direccion = N'" + guia.getGuia_ubigeo_partida_direccion() + "',\n"
                + "		@guia_ubigeo_llegada = N'" + guia.getGuia_ubigeo_llegada() + "',\n"
                + "		@guia_ubigeo_llegada_direccion = N'" + guia.getGuia_ubigeo_llegada_direccion() + "',\n"
                + "		@guia_url = N'" + guia.getGuia_url() + "'";
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

    public String generar_dni(String dni) {
        String valor = "";
        String sent = "SELECT [persona_id]FROM [persona] where persona_institucion='" + dni + "'";
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

    public String generar_numero_guia(String serie, String almacen, String comprobante) {
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

    public void tabla_anexo(JTable tabla, String entrega, String anexo) {
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
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.setDragEnabled(false);
        tabla.getTableHeader().setReorderingAllowed(false);

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
    public String busar_conductor(String codigo) {
        String respueta = "";

        String sent = "select  ruta_conductor  from plan_ruta where ruta_codigo='" + codigo + "'";
        try {
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sent);
            rs.next();
            // System.out.println("Controller.Controller_Institucion.editar()"+sent);
            respueta = rs.getString(1);
            cxn.desconectar();
            // respueta = "exito";
        } catch (SQLException ex) {
            respueta = "fallo";
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        return respueta;
    }

    public String buscarplaca(String codigo) {
        String respueta = "";

        String sent = "select  ruta_placa  from plan_ruta where ruta_codigo='" + codigo + "'";
        try {
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sent);
            rs.next();
            // System.out.println("Controller.Controller_Institucion.editar()"+sent);
            respueta = rs.getString(1);
            cxn.desconectar();
            // respueta = "exito";
        } catch (SQLException ex) {
            respueta = "fallo";
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        return respueta;
    }
}
