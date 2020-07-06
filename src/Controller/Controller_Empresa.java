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
public class Controller_Empresa {

    private Conexion cxn;
    private int getselection = 0;
    private int getselection_almacen = 0;
    private boolean[] editable = {false, true, true, true, true, true, true, true, true};
    private boolean[] editable_almacen = {false, false, true, true, false, true};
    DefaultTableModel model;
    DefaultTableModel model_almacen;

    /*private generador_codigo generador_id;
    Function_ShowMessageDialog mensaje = new Function_ShowMessageDialog();*/
    public Controller_Empresa() {
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
        getselection_almacen= 0;
    }

    public int variable_almacen() {
        return getselection_almacen;
    }

    public void table_empresa(JTable compra) {
        String respueta = "";
        try {
            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String sSQL1 = "select empresa_id ,empresa_cod,empresa_ruc,empresa_razon_social  from empresa order by empresa_id asc";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"", "COD", "RUC", "RAZON SOCIAL"});
            while (rs.next()) {

                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)});
            }
            cxn.desconectar();
            compra.setModel(model);
            Function_Component.JTable(compra);
            compra.getColumnModel().getColumn(0).setMaxWidth(0);
            compra.getColumnModel().getColumn(0).setMinWidth(0);
            compra.getColumnModel().getColumn(0).setPreferredWidth(0);
            compra.getColumnModel().getColumn(1).setMaxWidth(150);
            compra.getColumnModel().getColumn(1).setMinWidth(150);
            compra.getColumnModel().getColumn(1).setPreferredWidth(150);
            compra.getColumnModel().getColumn(2).setMaxWidth(150);
            compra.getColumnModel().getColumn(2).setMinWidth(150);
            compra.getColumnModel().getColumn(2).setPreferredWidth(150);
            compra.setDragEnabled(false);
            compra.getTableHeader().setReorderingAllowed(false);
            //compra.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        //return respueta;
    }

    public String Add_Empresa(Entity_Empresa empresa) {
        String respueta = "";
        String sent = "EXEC	 [dbo].[InsertaTablaempresa]\n"
                + "		@empresa_id = N'" + empresa.getEmpresa_id() + "',\n"
                + "		@empresa_cod = N'" + empresa.getEmpresa_cod() + "',\n"
                + "		@empresa_ruc = N'" + empresa.getEmpresa_ruc() + "',\n"
                + "		@empresa_razon_social = N'" + empresa.getEmpresa_razon_social() + "',\n"
                + "		@empresa_comercial = N'" + empresa.getEmpresa_comercial() + "',\n"
                + "		@empresa_direccion = N'" + empresa.getEmpresa_direccion() + "',\n"
                + "		@empresa_decrip = N'" + empresa.getEmpresa_decrip() + "',\n"
                + "		@empresa_estado = N'" + empresa.getEmpresa_estado() + "'";
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

    public String guardarImagen(String ruta, String nombre) {
        String respueta = "";
        String insert = "update  empresa set empresa_imagen=? where empresa_id=?";
        FileInputStream fis = null;
        PreparedStatement ps = null;
        try {

            File file = new File(ruta);
            fis = new FileInputStream(file);
            ps = cxn.conectardb().prepareStatement(insert);
            ps.setBinaryStream(1, fis, (int) file.length());
            ps.setString(2, nombre);
            ps.executeUpdate();
            cxn.conectardb().commit();
            cxn.desconectar();
            respueta = "exito";
        } catch (Exception ex) {
            respueta = "fallo";
        }
        return respueta;
    }

    public String Add_sucursal(Entity_Sucursal sucursal) {
        String respueta = "";
        String sent = "EXEC [dbo].[InsertaTablasucursal]\n"
                + "		@sucursal_id = N'" + sucursal.getSucursal_id() + "',\n"
                + "		@sucursal_empresa = N'" + sucursal.getSucursal_empresa() + "',\n"
                + "		@sucursal_cod = N'" + sucursal.getSucursal_cod() + "',\n"
                + "		@sucursal_direccion = N'" + sucursal.getSucursal_direccion() + "',\n"
                + "		@sucursal_descripcion = N'" + sucursal.getSucursal_descripcion() + "',\n"
                + "		@sucursal_url = N'" + sucursal.getSucursal_url() + "',\n"
                + "		@sucursal_token = N'" + sucursal.getSucursal_token() + "',\n"
                + "		@sucursal_estado = N'" + sucursal.getSucursal_estado() + "'";

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
    public String Add_almacen(Entity_Empresa empresa) {
        String respueta = "";
        String sent = "EXEC	 [dbo].[InsertaTabladetalle_almacen]\n" +
"		@almacen_id = N'"+empresa.getAlmacen_id()+"',\n" +
"		@almacen_cod = N'"+empresa.getAlmacen_cod()+"',\n" +
"		@almacen_descrip = N'"+empresa.getAlmacen_descrip()+"',\n" +
"		@almacen_empresa = N'"+empresa.getAlmacen_empresa()+"',\n" +
"		@almacen_estado = N'"+empresa.getAlmacen_estado()+"'";
        //System.out.println("Controller.Controller_Empresa.Add_almacen()"+sent);
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

    public void add_sucursal(JTable tabla) {
        JButton btnEliminar = new JButton("");
        btnEliminar.setName("btneliminar");
        btnEliminar.setIcon(new ImageIcon("src\\Icon\\icon_eliminar.png"));
        tabla.setDefaultRenderer(Object.class, new Render());
        model = new DefaultTableModel(new Object[]{"ELI", "ID_SUC", "ID_EMPRE", "COD_EMPRESA", "DIRECCION", "DESCRIPCION", "URL", "TOKEN", "ESTADO"}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
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
        model.addRow(new Object[]{btnEliminar, "", "", "", "", "", "", "", true});

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
        settext_p_token(tabla, tabla.getColumnModel().getColumn(7));

        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.setDragEnabled(false);
        tabla.getTableHeader().setReorderingAllowed(false);

    }

    public void empresa(JTable tabla, String cod_sucursal) {
        JButton btnEliminar = new JButton("");
        btnEliminar.setName("btneliminar");
        btnEliminar.setIcon(new ImageIcon("src\\Icon\\icon_eliminar.png"));
        tabla.setDefaultRenderer(Object.class, new Render());
        model = new DefaultTableModel(new Object[]{"ELI", "ID_SUC", "ID_EMPRE", "COD_EMPRESA", "DIRECCION", "DESCRIPCION", "URL", "TOKEN", "ESTADO"}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
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
            String sSQL1 = "select sucursal_id,	sucursal_empresa,	sucursal_cod,	sucursal_direccion,	sucursal_descripcion,"
                    + "	sucursal_url,	sucursal_token,	sucursal_estado from sucursal where sucursal_empresa ='" + cod_sucursal + "'"
                    + " order by sucursal_id asc";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            while (rs.next()) {
                model.addRow(new Object[]{btnEliminar, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6),
                    rs.getString(7), rs.getBoolean(8)});

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
        settext_p_token(tabla, tabla.getColumnModel().getColumn(7));

        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.setDragEnabled(false);
        tabla.getTableHeader().setReorderingAllowed(false);

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

    /*buscar txt producto por cod compra*/
    public void Add_Almacen(JTable tabla, String codigo) {
        JButton btnEliminar = new JButton("");
        btnEliminar.setName("btneliminar");
        btnEliminar.setIcon(new ImageIcon("src\\Icon\\icon_eliminar.png"));
        tabla.setDefaultRenderer(Object.class, new Render());
        model_almacen = new DefaultTableModel(new Object[]{"ELI", "ID_ALMACEN", "COD_ALMACEN", "ALMACEN", "ALMACEN_EMPRE", "ESTADO"}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Boolean.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return editable_almacen[column];
            }
        };
        model_almacen = (DefaultTableModel) tabla.getModel();
        model_almacen.addRow(new Object[]{btnEliminar, "", "", "", "", true});
       

        tabla.setModel(model_almacen);
        Function_Key_Event.Validar_numeros_jtable(tabla);
        Function_Component.JTable(tabla);

        tabla.getColumnModel().getColumn(0).setMaxWidth(50);
        tabla.getColumnModel().getColumn(0).setMinWidth(50);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(1).setMaxWidth(0);
        tabla.getColumnModel().getColumn(1).setMinWidth(0);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(0);
        /*tabla.getColumnModel().getColumn(2).setMaxWidth(0);
        tabla.getColumnModel().getColumn(2).setMinWidth(0);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(0);*/
        tabla.getColumnModel().getColumn(4).setMaxWidth(0);
        tabla.getColumnModel().getColumn(4).setMinWidth(0);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(100);
        settext_cod_almacen(tabla, tabla.getColumnModel().getColumn(2));
        settext_Descrip(tabla, tabla.getColumnModel().getColumn(3));
        /*settext_tipo_docuemnto(tabla, tabla.getColumnModel().getColumn(4), tipo_documento, tipo_document);
        settext_serie(tabla, tabla.getColumnModel().getColumn(5));
        settext_numero(tabla, tabla.getColumnModel().getColumn(6));
        setcbx_formato(tabla, tabla.getColumnModel().getColumn(7));
        setcbx_impresora(tabla, tabla.getColumnModel().getColumn(8));*/

        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.setDragEnabled(false);
        tabla.getTableHeader().setReorderingAllowed(false);

    }
    public void Almacen(JTable tabla, String codigo) {
        JButton btnEliminar = new JButton("");
        btnEliminar.setName("btneliminar");
        btnEliminar.setIcon(new ImageIcon("src\\Icon\\icon_eliminar.png"));
        tabla.setDefaultRenderer(Object.class, new Render());
        model_almacen = new DefaultTableModel(new Object[]{"ELI", "ID_ALMACEN", "COD_ALMACEN", "ALMACEN", "ALMACEN_EMPRE", "ESTADO"}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Boolean.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return editable_almacen[column];
            }
        };
        try {
            String sSQL1 = "SELECT *\n"
                    + "  FROM  [almacen] where almacen_empresa='"+codigo+"' order by  almacen_id asc";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            while (rs.next()) {
                model_almacen.addRow(new Object[]{btnEliminar, rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getBoolean(5)});

            }
            cxn.desconectar();
        } catch (Exception e) {

        }

        tabla.setModel(model_almacen);
        Function_Key_Event.Validar_numeros_jtable(tabla);
        Function_Component.JTable(tabla);

        tabla.getColumnModel().getColumn(0).setMaxWidth(50);
        tabla.getColumnModel().getColumn(0).setMinWidth(50);
        tabla.getColumnModel().getColumn(0).setPreferredWidth(50);
        tabla.getColumnModel().getColumn(1).setMaxWidth(0);
        tabla.getColumnModel().getColumn(1).setMinWidth(0);
        tabla.getColumnModel().getColumn(1).setPreferredWidth(0);
        /*tabla.getColumnModel().getColumn(2).setMaxWidth(0);
        tabla.getColumnModel().getColumn(2).setMinWidth(0);
        tabla.getColumnModel().getColumn(2).setPreferredWidth(0);*/
        tabla.getColumnModel().getColumn(4).setMaxWidth(0);
        tabla.getColumnModel().getColumn(4).setMinWidth(0);
        tabla.getColumnModel().getColumn(4).setPreferredWidth(0);
        tabla.getColumnModel().getColumn(3).setPreferredWidth(300);
        tabla.getColumnModel().getColumn(5).setPreferredWidth(100);
        settext_cod_almacen(tabla, tabla.getColumnModel().getColumn(2));
        settext_Descrip(tabla, tabla.getColumnModel().getColumn(3));
        /*settext_tipo_docuemnto(tabla, tabla.getColumnModel().getColumn(4), tipo_documento, tipo_document);
        settext_serie(tabla, tabla.getColumnModel().getColumn(5));
        settext_numero(tabla, tabla.getColumnModel().getColumn(6));
        setcbx_formato(tabla, tabla.getColumnModel().getColumn(7));
        setcbx_impresora(tabla, tabla.getColumnModel().getColumn(8));*/

        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.setDragEnabled(false);
        tabla.getTableHeader().setReorderingAllowed(false);

    }
    
    public void settext_cod_almacen(JTable tabla, TableColumn columna) {
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

                                tabla.editCellAt(tabla.getSelectedRow() + getselection_almacen, 3);
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
    public void settext_Descrip(JTable tabla, TableColumn columna) {
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

                    if (tabla.getSelectedRow() + getselection_almacen == tabla.getRowCount() - 1) {
                        getselection_almacen = getselection_almacen + 1;

                        View.View_Empresa.btn_agregar_fila_almacen.doClick();
                        tabla.editCellAt(tabla.getSelectedRow() + getselection_almacen, 2);
                        Component aComp = tabla.getEditorComponent();
                        aComp.requestFocus();
                    } else {
                        getselection_almacen = getselection_almacen + 1;
                        tabla.editCellAt(tabla.getSelectedRow() + getselection_almacen, 2);
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

    public List<Entity_Empresa> buscar_empresa(String buscar) {
        List<Entity_Empresa> empresa = new ArrayList<Entity_Empresa>();
        String sent = "select empresa_id,empresa_cod,empresa_ruc,empresa_razon_social,empresa_comercial,empresa_direccion,	empresa_decrip,empresa_estado,empresa_imagen\n"
                + " from empresa where empresa_cod='" + buscar + "'";
        try {
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sent);
            rs.next();
            empresa.add(new Entity_Empresa(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getBoolean(8), rs.getBlob(9)));
            cxn.desconectar();
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        return empresa;
    }

    public String Id_Empresa(String empresa) {
        String respueta = "";
        String sent = "DECLARE	@return_value int,\n"
                + "		@Resultado varchar(255)\n"
                + "\n"
                + "EXEC	@return_value = [dbo].[increment_empresa]\n"
                + "		@Id_Solicitud = N'" + empresa + "',\n"
                + "		@Resultado = @Resultado OUTPUT\n"
                + "\n"
                + "SELECT	@Resultado as N'@Resultado'\n"
                + "\n"
                + "";

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

    public String delete_sucursal(String sucursal) {
        String respuesta = "";
        String sent = "delete from sucursal where sucursal_id='" + sucursal + "'";

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
    public String delete_almacen(String almacen) {
        String respuesta = "";
        String sent = "delete from almacen where almacen_id='" + almacen + "'";

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
