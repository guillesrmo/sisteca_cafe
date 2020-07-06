package puebas;

import Controller.*;
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
        model.addColumn("s");
       // model.addRow(new Object[]{btnEliminar, "", "", "", "", "", "", "", true});

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

           
        };
        /*for (int i = 0; i < 10; i++) {
            model.addRow(new Object[]{btnEliminar, "", "", "", "", "", "",
                   "", true});
        }*/
        try {
            String sSQL1 = "select * from institucion";
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
  

}
