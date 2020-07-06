package Controller;

import Conexion.Conexion;
import Config_Class.Render;
import Entity.Entity_Almacen;
import Entity.Entity_Compra;
import Entity.Entity_Compra_Producto;
import Entity.Entity_Documento;
import Entity.Entity_Empresa;
import Entity.Entity_Moneda;
import Entity.Entity_Persona;
import Entity.Entity_Producto;
import Entity.Entity_Producto_Presentacion;
import Entity.Entity_Punto_operacion;
import Entity.Entity_Sucursal;
import Entity.Entity_Tipo_Comprobante;
import Entity.Entity_Tipo_afectacion;
import Entity.Entity_Venta;
import Function.Function_Component;
import Function.Function_Key_Event;
import Function.Function_ShowMessageDialog;
import View.View_Escritorio;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
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
public class Controller_Punto_operacion {

    private Conexion cxn;
    private int getselection = 0;
    private int getselection_usuario = 0;
    private boolean[] editable = {false, false, false, false, true, true, true, true, true, true, true};
    private boolean[] editable_usuario = {false, false, false, true,true, true};
    DefaultTableModel model;
    DefaultTableModel model_usuario;

    /*private generador_codigo generador_id;
    Function_ShowMessageDialog mensaje = new Function_ShowMessageDialog();*/
    public Controller_Punto_operacion() {
        cxn = new Conexion();
        //generador_id = new generador_codigo();
    }

    public void reset_variable() {
        getselection = 0;
    }

    public void reset_variable_usuario() {
        getselection_usuario = 0;
    }

    public int variable() {
        return getselection;
    }

    public int variable_usuario() {
        return getselection_usuario;
    }

    public void table_sucursal(JTable compra) {
        String respueta = "";
        try {
            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String sSQL1 = "select empre.empresa_ruc,empre.empresa_razon_social,sucur.sucursal_id,sucur.sucursal_cod from  empresa empre\n"
                    + "inner join sucursal sucur \n"
                    + "on sucur.sucursal_empresa = empre.empresa_id \n"
                    + "where empre.empresa_estado='true' and sucur.sucursal_estado='true' ";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"RUC", "RAZON SOCIAL", "", "COD_SUCUR"});
            while (rs.next()) {

                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)});
            }
            cxn.desconectar();
            compra.setModel(model);
            Function_Component.JTable(compra);
            /* compra.getColumnModel().getColumn(0).setMaxWidth(0);
            compra.getColumnModel().getColumn(0).setMinWidth(0);
            compra.getColumnModel().getColumn(0).setPreferredWidth(0);
            compra.getColumnModel().getColumn(1).setMaxWidth(150);
            compra.getColumnModel().getColumn(1).setMinWidth(150);
            compra.getColumnModel().getColumn(1).setPreferredWidth(150);
            compra.getColumnModel().getColumn(2).setMaxWidth(150);
            compra.getColumnModel().getColumn(2).setMinWidth(150);
            compra.getColumnModel().getColumn(2).setPreferredWidth(150);*/
            compra.setDragEnabled(false);
            compra.getTableHeader().setReorderingAllowed(false);
            //compra.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        //return respueta;
    }

    public void table_tipo_documento(JTable tipo_documento) {
        String respueta = "";
        try {
            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String sSQL1 = "SELECT  [comprobante_id]\n"
                    + "      ,[comprobante_cod]\n"
                    + "      ,[comprobante_descrip]\n"
                    + "  FROM [tipo_comprobante]";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"ID", "COD", "COMPROBANTE"});
            while (rs.next()) {

                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3)});
            }
            cxn.desconectar();
            tipo_documento.setModel(model);
            Function_Component.JTable(tipo_documento);
            /* compra.getColumnModel().getColumn(0).setMaxWidth(0);
            compra.getColumnModel().getColumn(0).setMinWidth(0);
            compra.getColumnModel().getColumn(0).setPreferredWidth(0);
            compra.getColumnModel().getColumn(1).setMaxWidth(150);
            compra.getColumnModel().getColumn(1).setMinWidth(150);
            compra.getColumnModel().getColumn(1).setPreferredWidth(150);
            compra.getColumnModel().getColumn(2).setMaxWidth(150);
            compra.getColumnModel().getColumn(2).setMinWidth(150);
            compra.getColumnModel().getColumn(2).setPreferredWidth(150);*/
            tipo_documento.setDragEnabled(false);
            tipo_documento.getTableHeader().setReorderingAllowed(false);
            //compra.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        //return respueta;
    }

    public void table_punta_operacion(JTable punto_operacion) {
        String respueta = "";
        try {
            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String sSQL1 = "SELECT  punto_op_id,punto_op_cod FROM punto_operacion order by punto_op_id asc";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"ID", "COD"});
            while (rs.next()) {

                model.addRow(new Object[]{rs.getString(1), rs.getString(2)});
            }
            cxn.desconectar();
            punto_operacion.setModel(model);
            Function_Component.JTable(punto_operacion);
            punto_operacion.getColumnModel().getColumn(0).setMaxWidth(0);
            punto_operacion.getColumnModel().getColumn(0).setMinWidth(0);
            punto_operacion.getColumnModel().getColumn(0).setPreferredWidth(0);
            /* compra.getColumnModel().getColumn(1).setMaxWidth(150);
            compra.getColumnModel().getColumn(1).setMinWidth(150);
            compra.getColumnModel().getColumn(1).setPreferredWidth(150);
            compra.getColumnModel().getColumn(2).setMaxWidth(150);
            compra.getColumnModel().getColumn(2).setMinWidth(150);
            compra.getColumnModel().getColumn(2).setPreferredWidth(150);*/
            punto_operacion.setDragEnabled(false);
            punto_operacion.getTableHeader().setReorderingAllowed(false);
            //compra.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        //return respueta;
    }

    public void table_usuario(JTable usuario) {
        String respueta = "";
        try {
            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String sSQL1 = "SELECT  [usuario_id]\n"
                    + "      ,[usuario_cod]\n"
                    + "      ,[usuario_user]\n"
                    + "  FROM [usuario] where usuario_estado='true'";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"ID", "COD", "USER"});
            while (rs.next()) {

                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3)});
            }
            cxn.desconectar();
            usuario.setModel(model);
            Function_Component.JTable(usuario);
            usuario.getColumnModel().getColumn(0).setMaxWidth(0);
            usuario.getColumnModel().getColumn(0).setMinWidth(0);
            usuario.getColumnModel().getColumn(0).setPreferredWidth(0);
            /* compra.getColumnModel().getColumn(1).setMaxWidth(150);
            compra.getColumnModel().getColumn(1).setMinWidth(150);
            compra.getColumnModel().getColumn(1).setPreferredWidth(150);
            compra.getColumnModel().getColumn(2).setMaxWidth(150);
            compra.getColumnModel().getColumn(2).setMinWidth(150);
            compra.getColumnModel().getColumn(2).setPreferredWidth(150);*/
            usuario.setDragEnabled(false);
            usuario.getTableHeader().setReorderingAllowed(false);
            //compra.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        //return respueta;
    }

    public String Add_Punto_operacion(Entity_Punto_operacion operacion) {
        String respueta = "";
        String sent = "EXEC	 [dbo].[InsertaTablapuntooperacion]\n"
                + "		@punto_op_id = N'" + operacion.getPunto_op_id() + "',\n"
                + "		@punto_op_cod = N'" + operacion.getPunto_op_cod() + "',\n"
                + "		@punto_op_sucursal = N'" + operacion.getPunto_op_sucursal() + "',\n"
                + "		@punto_op_estado = N'" + operacion.getPunto_op_estado() + "'";
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

    public String Add_detalle_punto_operacion(Entity_Punto_operacion operacion) {
        String respueta = "";
        String sent = "EXEC	[dbo].[InsertaTabladetalle_punto_operacion]\n"
                + "		@det_punto_op_id = N'" + operacion.getDet_punto_op_id() + "',\n"
                + "		@det_punto_op_punto_operacion = N'" + operacion.getDet_punto_op_punto_operacion() + "',\n"
                + "		@det_punto_op_tipo_documento = N'" + operacion.getDet_punto_op_tipo_documento() + "',\n"
                + "		@det_punto_op_serie = N'" + operacion.getDet_punto_op_serie() + "',\n"
                + "		@det_punto_op_numeracion = N'" + operacion.getDet_punto_op_numeracion() + "',\n"
                + "		@det_punto_op_formato = N'" + operacion.getDet_punto_op_formato() + "',\n"
                + "		@det_punto_op_impresora = N'" + operacion.getDet_punto_op_impresora() + "',\n"
                + "		@det_punto_op_control_stock = N'" + operacion.getDet_punto_op_control_stock() + "',\n"
                + "		@det_punto_op_control_estado = N'" + operacion.getDet_punto_op_control_estado() + "'";

        try {
            //System.out.println("Controller.Controller_Punto_operacion.Add_detalle_punto_operacion()"+sent);
            cxn.conectardb().createStatement().executeUpdate(sent);
            cxn.desconectar();
            respueta = "exito";
        } catch (SQLException ex) {
            respueta = "fallo";
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        return respueta;
    }

    public String Add_detalle_punto_operacion_usuario(Entity_Punto_operacion operacion) {
        String respueta = "";
        String sent = "EXEC	 [dbo].[InsertaTabladetalle_punto_operacion_usuario]\n"
                + "		@det_punto_op_user_id = N'"+operacion.getDet_punto_op_user_id()+"',\n"
                + "		@det_punto_op_user_punto_op = N'"+operacion.getDet_punto_op_user_punto_op()+"',\n"
                + "		@det_punto_op_user_usuario = N'"+operacion.getDet_punto_op_user_usuario()+"',\n"
                + "		@det_punto_op_user_estado = N'"+operacion.getDet_punto_op_user_estado()+"'";

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

    public void add_punto_operacion(JTable tabla, JDialog tipo_documento, JTable tipo_document) {
        JButton btnEliminar = new JButton("");
        btnEliminar.setName("btneliminar");
        btnEliminar.setIcon(new ImageIcon("src\\Icon\\icon_eliminar.png"));
        tabla.setDefaultRenderer(Object.class, new Render());
        model = new DefaultTableModel(new Object[]{"ELI", "ID_SUC", "ID_EMPRE", "COD_EMPRESA", "DIRECCION", "DESCRIPCION", "URL", "TOKEN", "ESTADO"}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class,
                java.lang.Boolean.class
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
        model.addRow(new Object[]{btnEliminar, "", "", "", "", "", "", "", "", true, true});

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
        tabla.getColumnModel().getColumn(9).setMaxWidth(100);
        tabla.getColumnModel().getColumn(9).setMinWidth(100);
        tabla.getColumnModel().getColumn(9).setPreferredWidth(100);*/
        settext_tipo_docuemnto(tabla, tabla.getColumnModel().getColumn(4), tipo_documento, tipo_document);
        settext_serie(tabla, tabla.getColumnModel().getColumn(5));
        settext_numero(tabla, tabla.getColumnModel().getColumn(6));
        setcbx_formato(tabla, tabla.getColumnModel().getColumn(7));
        setcbx_impresora(tabla, tabla.getColumnModel().getColumn(8));

        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.setDragEnabled(false);
        tabla.getTableHeader().setReorderingAllowed(false);

    }

    public void punto_operacion(JTable tabla, JDialog tipo_documento, JTable tipo_document, String codigo) {
        JButton btnEliminar = new JButton("");
        btnEliminar.setName("btneliminar");
        btnEliminar.setIcon(new ImageIcon("src\\Icon\\icon_eliminar.png"));
        tabla.setDefaultRenderer(Object.class, new Render());
        model = new DefaultTableModel(new Object[]{"ELI", "ID_DOCUMENTO", "ID_PUNTO_OPERACION", "ID_TIPO_DOCUMENTO", "TIPO_DOCUMENTO", "SERIE", "NUMERACION", "FORMATO", "IMPRESORA", "CONTROL_STOCK", "ESTADO"}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class,
                java.lang.Boolean.class
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
            String sSQL1 = "SELECT  det_pun.det_punto_op_id,det_pun.det_punto_op_punto_operacion,det_pun.det_punto_op_tipo_documento,ti_com.comprobante_descrip,\n"
                    + "det_pun.det_punto_op_serie,det_pun.det_punto_op_numeracion,det_pun.det_punto_op_formato,det_pun.det_punto_op_impresora,\n"
                    + "det_pun.det_punto_op_control_stock,det_pun.det_punto_op_control_estado\n"
                    + "  FROM detalle_punto_operacion det_pun\n"
                    + "  inner join tipo_comprobante ti_com\n"
                    + "  on ti_com.comprobante_id=det_pun.det_punto_op_tipo_documento where det_pun.det_punto_op_punto_operacion='" + codigo + "' order by det_pun.det_punto_op_id asc";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            while (rs.next()) {
                model.addRow(new Object[]{btnEliminar, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
                    rs.getString(7), rs.getString(8), rs.getBoolean(9), rs.getBoolean(10)});

            }
            cxn.desconectar();
        } catch (Exception e) {

        }

        tabla.setModel(model);
        Function_Key_Event.Validar_numeros_jtable(tabla);
        Function_Component.JTable(tabla);

        tabla.getColumnModel().getColumn(0).setMaxWidth(50);
        tabla.getColumnModel().getColumn(0).setMinWidth(50);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(1).setMaxWidth(0);
        tabla.getColumnModel().getColumn(1).setMinWidth(0);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(2).setMaxWidth(0);
        tabla.getColumnModel().getColumn(2).setMinWidth(0);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(3).setMaxWidth(0);
        tabla.getColumnModel().getColumn(3).setMinWidth(0);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(6).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(7).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(8).setMaxWidth(100);
        tabla.getColumnModel().getColumn(8).setMinWidth(100);
        tabla.getColumnModel().getColumn(8).setPreferredWidth(100);
        tabla.getColumnModel().getColumn(9).setMaxWidth(100);
        tabla.getColumnModel().getColumn(9).setMinWidth(100);
        tabla.getColumnModel().getColumn(9).setPreferredWidth(100);

        settext_tipo_docuemnto(tabla, tabla.getColumnModel().getColumn(4), tipo_documento, tipo_document);
        settext_serie(tabla, tabla.getColumnModel().getColumn(5));
        settext_numero(tabla, tabla.getColumnModel().getColumn(6));
        setcbx_formato(tabla, tabla.getColumnModel().getColumn(7));
        setcbx_impresora(tabla, tabla.getColumnModel().getColumn(8));

        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.setDragEnabled(false);
        tabla.getTableHeader().setReorderingAllowed(false);

    }

    public void settext_tipo_docuemnto(JTable tabla, TableColumn columna, JDialog tipo_documento, JTable tipo_document) {
        try {

            JTextField tipo_docuemnto = new JTextField();
            Caret seleccion = tipo_docuemnto.getCaret();
            Function_Component.JTextField(tipo_docuemnto);
            Function_Key_Event.Validar_Mayuscula(tipo_docuemnto);
            columna.setCellEditor(new DefaultCellEditor(tipo_docuemnto));
            DefaultTableCellRenderer render = new DefaultTableCellRenderer();
            tipo_docuemnto.addKeyListener(new KeyListener() {
                @Override
                public void keyPressed(KeyEvent evt) {
                    try {

                        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                            Controller_Punto_operacion punto_operacion = new Controller_Punto_operacion();

                            punto_operacion.table_tipo_documento(tipo_document);

                            Dimension desktopSize = View_Escritorio.desktopPane.getSize();
                            Dimension FrameSize = tipo_documento.getSize();
                            tipo_documento.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
                            tipo_documento.setVisible(true);

                            tabla.editCellAt(tabla.getSelectedRow() + getselection, 5);
                            Component aComp = tabla.getEditorComponent();
                            aComp.requestFocus();
                            tipo_document.requestFocus();
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

    public void settext_serie(JTable tabla, TableColumn columna) {

        JTextField text_serie = new JTextField();
        Function_Component.JTextField(text_serie);
        Function_Key_Event.Validar_Mayuscula(text_serie);
        columna.setCellEditor(new DefaultCellEditor(text_serie));
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        text_serie.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent evt) {

                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (text_serie.getText().length() != 0) {
                        tabla.editCellAt(tabla.getSelectedRow() + getselection, 6);
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

    public void settext_numero(JTable tabla, TableColumn columna) {
        try {

            JTextField text_numero = new JTextField();
            Function_Component.JTextField(text_numero);
            Function_Key_Event.Validar_numeros(text_numero);
            columna.setCellEditor(new DefaultCellEditor(text_numero));
            DefaultTableCellRenderer render = new DefaultTableCellRenderer();
            text_numero.addKeyListener(new KeyListener() {
                @Override
                public void keyPressed(KeyEvent evt) {

                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                        if (text_numero.getText().length() != 0) {
                            tabla.editCellAt(tabla.getSelectedRow() + getselection, 7);
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

    public void setcbx_formato(JTable tabla, TableColumn columna) {

        JComboBox formato = new JComboBox();
        String sent = "SELECT *\n"
                + "  FROM formato";
        try {
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sent);
            while (rs.next()) {
                formato.addItem(rs.getString(1));
            }

            cxn.desconectar();
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        Function_Component.JComboBox(formato);
        columna.setCellEditor(new DefaultCellEditor(formato));
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        formato.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent evt) {

                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

                    tabla.editCellAt(tabla.getSelectedRow() + getselection, 8);
                    Component aComp = tabla.getEditorComponent();
                    aComp.requestFocus();
                    evt.consume();

                }
                /*if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
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

                }*/

            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

        });

    }

    public void setcbx_impresora(JTable tabla, TableColumn columna) {

        JComboBox lista_impresora = new JComboBox();

        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);

        for (PrintService printService : printServices) {
            lista_impresora.addItem(printService.getName());
        }
        Function_Component.JComboBox(lista_impresora);
        columna.setCellEditor(new DefaultCellEditor(lista_impresora));
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        lista_impresora.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent evt) {

                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

                    if (tabla.getSelectedRow() + getselection == tabla.getRowCount() - 1) {
                        getselection = getselection + 1;

                        View.View_Punto_operacion.btn_argegar_fila.doClick();
                        tabla.editCellAt(tabla.getSelectedRow() + getselection, 4);
                        Component aComp = tabla.getEditorComponent();
                        aComp.requestFocus();
                    } else {
                        getselection = getselection + 1;
                        tabla.editCellAt(tabla.getSelectedRow() + getselection, 4);
                        Component aComp = tabla.getEditorComponent();
                        aComp.requestFocus();
                    }

                    evt.consume();
                }
                /*if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
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

                }*/

            }

            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }

        });

    }

    public void punto_operacion_usuario(JTable tabla, JDialog d_usuario, JTable t_usuario,String codigo) {
        JButton btnEliminar = new JButton("");
        btnEliminar.setName("btneliminar");
        btnEliminar.setIcon(new ImageIcon("src\\Icon\\icon_eliminar.png"));
        tabla.setDefaultRenderer(Object.class, new Render());
        model_usuario = new DefaultTableModel(new Object[]{"ELI", "ID_USUARIO", "ID_PUNTO_OPE", "USUARIO","", "ESTADO"}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class,java.lang.Boolean.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return editable_usuario[column];
            }
        };
        try {
            String sSQL1 = " select p_user.det_punto_op_user_id,p_user.det_punto_op_user_usuario, usu.usuario_user,p_user.det_punto_op_user_estado\n" +
"  from detalle_punto_operacion_usaurio p_user\n" +
"  inner join usuario usu\n" +
"  on usu.usuario_id=p_user.det_punto_op_user_usuario where p_user.det_punto_op_user_punto_op='"+codigo+"' order by  p_user.det_punto_op_user_id asc";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            while (rs.next()) {
                model_usuario.addRow(new Object[]{btnEliminar, rs.getString(1), rs.getString(2), rs.getString(3), "", rs.getBoolean(4)});

            }
            cxn.desconectar();
        } catch (Exception e) {

        }
        

        tabla.setModel(model_usuario);
        Function_Key_Event.Validar_numeros_jtable(tabla);
        Function_Component.JTable(tabla);

         tabla.getColumnModel().getColumn(0).setMaxWidth(50);
        tabla.getColumnModel().getColumn(0).setMinWidth(50);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(1).setMaxWidth(0);
        tabla.getColumnModel().getColumn(1).setMinWidth(0);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(2).setMaxWidth(0);
        tabla.getColumnModel().getColumn(2).setMinWidth(0);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(4).setMaxWidth(0);
        tabla.getColumnModel().getColumn(4).setMinWidth(0);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(300);
        settext_usuario(tabla, tabla.getColumnModel().getColumn(3), d_usuario, t_usuario);
        settxt_vacio(tabla, tabla.getColumnModel().getColumn(4));
        /*settext_tipo_docuemnto(tabla, tabla.getColumnModel().getColumn(4), tipo_documento, tipo_document);
        settext_serie(tabla, tabla.getColumnModel().getColumn(5));
        settext_numero(tabla, tabla.getColumnModel().getColumn(6));
        setcbx_formato(tabla, tabla.getColumnModel().getColumn(7));
        setcbx_impresora(tabla, tabla.getColumnModel().getColumn(8));*/

        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.setDragEnabled(false);
        tabla.getTableHeader().setReorderingAllowed(false);

    }
    
    public void add_punto_operacion_usuario(JTable tabla, JDialog d_usuario, JTable t_usuario) {
        JButton btnEliminar = new JButton("");
        btnEliminar.setName("btneliminar");
        btnEliminar.setIcon(new ImageIcon("src\\Icon\\icon_eliminar.png"));
        tabla.setDefaultRenderer(Object.class, new Render());
        model_usuario = new DefaultTableModel(new Object[]{"ELI", "ID_USUARIO", "ID_PUNTO_OPE", "USUARIO","", "ESTADO"}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class,java.lang.Boolean.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return editable_usuario[column];
            }
        };
        model_usuario = (DefaultTableModel) tabla.getModel();
        model_usuario.addRow(new Object[]{btnEliminar, "", "", "","", true});

        tabla.setModel(model_usuario);
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
        tabla.getColumnModel().getColumn(3).setMaxWidth(0);
        tabla.getColumnModel().getColumn(3).setMinWidth(0);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(300);*/
        settext_usuario(tabla, tabla.getColumnModel().getColumn(3), d_usuario, t_usuario);
        settxt_vacio(tabla, tabla.getColumnModel().getColumn(4));
        /*settext_tipo_docuemnto(tabla, tabla.getColumnModel().getColumn(4), tipo_documento, tipo_document);
        settext_serie(tabla, tabla.getColumnModel().getColumn(5));
        settext_numero(tabla, tabla.getColumnModel().getColumn(6));
        setcbx_formato(tabla, tabla.getColumnModel().getColumn(7));
        setcbx_impresora(tabla, tabla.getColumnModel().getColumn(8));*/

        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.setDragEnabled(false);
        tabla.getTableHeader().setReorderingAllowed(false);

    }

    public void settext_usuario(JTable tabla, TableColumn columna, JDialog j_usuario, JTable t_usuario) {
        try {

            JTextField tipo_docuemnto = new JTextField();
            Caret seleccion = tipo_docuemnto.getCaret();
            Function_Component.JTextField(tipo_docuemnto);
            Function_Key_Event.Validar_Mayuscula(tipo_docuemnto);
            columna.setCellEditor(new DefaultCellEditor(tipo_docuemnto));
            DefaultTableCellRenderer render = new DefaultTableCellRenderer();
            tipo_docuemnto.addKeyListener(new KeyListener() {
                @Override
                public void keyPressed(KeyEvent evt) {
                    try {

                        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                            Controller_Punto_operacion punto_operacion = new Controller_Punto_operacion();

                            punto_operacion.table_usuario(t_usuario);

                            Dimension desktopSize = View_Escritorio.desktopPane.getSize();
                            Dimension FrameSize = j_usuario.getSize();
                            j_usuario.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
                            j_usuario.setVisible(true);

                            tabla.editCellAt(tabla.getSelectedRow() + getselection_usuario, 4);
                            Component aComp = tabla.getEditorComponent();
                            aComp.requestFocus();
                            t_usuario.requestFocus();

                            /* if (tabla.getSelectedRow() + getselection_usuario == tabla.getRowCount() - 1) {
                                getselection_usuario = getselection_usuario + 1;

                                //View.View_Punto_operacion.btn_argegar_fila.doClick();
                                tabla.editCellAt(tabla.getSelectedRow() + getselection_usuario, 3);
                                Component aComp1 = tabla.getEditorComponent();
                                aComp1.requestFocus();
                            } else {
                                getselection_usuario = getselection_usuario + 1;
                                tabla.editCellAt(tabla.getSelectedRow() + getselection_usuario, 3);
                                Component aComp1 = tabla.getEditorComponent();
                                aComp1.requestFocus();
                            }
                             */
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

    public void settxt_vacio(JTable tabla, TableColumn columna) {

        JTextField tipo_docuemnto = new JTextField();
        Caret seleccion = tipo_docuemnto.getCaret();
        Function_Component.JTextField(tipo_docuemnto);
        Function_Key_Event.Validar_Mayuscula(tipo_docuemnto);
        columna.setCellEditor(new DefaultCellEditor(tipo_docuemnto));
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        tipo_docuemnto.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent evt) {

                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

                    if (tabla.getSelectedRow() + getselection_usuario == tabla.getRowCount() - 1) {
                        getselection_usuario = getselection_usuario + 1;

                        View.View_Punto_operacion.btn_argegar_fila_usuario.doClick();
                        tabla.editCellAt(tabla.getSelectedRow() + getselection_usuario, 3);
                        Component aComp = tabla.getEditorComponent();
                        aComp.requestFocus();
                    } else {
                        getselection_usuario = getselection_usuario + 1;
                        tabla.editCellAt(tabla.getSelectedRow() + getselection_usuario, 3);
                        Component aComp = tabla.getEditorComponent();
                        aComp.requestFocus();
                    }

                    evt.consume();
                }
                if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
                    try {

                        if (tabla.getSelectedRow() + getselection_usuario == tabla.getRowCount() - 1) {
                            getselection_usuario = getselection_usuario + 1;

                            View.View_Punto_operacion.btn_argegar_fila_usuario.doClick();
                            tabla.editCellAt(tabla.getSelectedRow() + getselection_usuario, 3);
                            Component aComp = tabla.getEditorComponent();
                            aComp.requestFocus();
                        } else {
                            getselection_usuario = getselection_usuario + 1;
                            tabla.editCellAt(tabla.getSelectedRow() + getselection_usuario, 3);
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

    /*buscar txt producto por cod compra*/
    public List<Entity_Punto_operacion> buscar_punto_operacion(String buscar) {
        List<Entity_Punto_operacion> punto_operacion = new ArrayList<Entity_Punto_operacion>();
        String sent = "select po.punto_op_id,po.punto_op_cod,po.punto_op_sucursal,po.punto_op_estado,\n"
                + "empre.empresa_ruc,empre.empresa_razon_social,sucur.sucursal_cod from  empresa empre\n"
                + "                    inner join sucursal sucur\n"
                + "                    on sucur.sucursal_empresa = empre.empresa_id\n"
                + "					inner join punto_operacion po\n"
                + "					on po.punto_op_sucursal=sucur.sucursal_id\n"
                + "                    where empre.empresa_estado='true' and sucur.sucursal_estado='true' and po.punto_op_cod ='" + buscar + "'";
        try {
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sent);
            rs.next();
            punto_operacion.add(new Entity_Punto_operacion(rs.getString(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getString(5), rs.getString(6), rs.getString(7)));
            cxn.desconectar();
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        return punto_operacion;
    }

    public String Id_Punto_operacion(String punto_operacion) {
        String respueta = "";
        String sent = "DECLARE	@return_value int,\n"
                + "		@Resultado varchar(255)\n"
                + "\n"
                + "SELECT	@Resultado = N'" + punto_operacion + "'\n"
                + "\n"
                + "EXEC	@return_value = [dbo].[increment_punto_operacion]\n"
                + "		@Id_Solicitud = N'12',\n"
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

    public String Edit_cod_empresa(String cod, String empresa) {
        String respueta = "";
        String sent = "update empresa set empresa_cod ='" + cod + "' where empresa_id='" + empresa + "'";

        try {
            cxn.conectardb().createStatement().executeUpdate(sent);
            cxn.desconectar();

        } catch (SQLException ex) {
            respueta = "fallo";
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        return respueta;
    }

    public String delete_punto_operacion(String sucursal) {
        String respuesta = "";
        String sent = "delete from detalle_punto_operacion where det_punto_op_id='" + sucursal + "'";

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
    public String delete_punto_operacion_usuario(String sucursal) {
        String respuesta = "";
        String sent = "delete from detalle_punto_operacion_usaurio where det_punto_op_user_id='" + sucursal + "'";

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

}
