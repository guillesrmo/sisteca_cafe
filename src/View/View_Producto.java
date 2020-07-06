/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Controller_Producto;
import Entity.Entity_Producto;
import Entity.Entity_Producto_Presentacion;
import Entity.Entity_Tipo_Embalaje;
import Entity.Entity_Tipo_afectacion;
import Function.Function_Component;
import Function.Function_Key_Event;
import Function.Function_ShowMessageDialog;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import jdk.nashorn.internal.parser.TokenType;
//https://stackoverflow.com/questions/8327352/how-to-set-selected-index-jcombobox-by-value

/**
 *
 * @author memo
 */
public class View_Producto extends javax.swing.JInternalFrame {

    Controller_Producto producto = new Controller_Producto();
    Entity_Producto entity_producto = new Entity_Producto();
    Entity_Producto_Presentacion entity_presenacion = new Entity_Producto_Presentacion();

    /**
     * Creates new form view_Producto
     */
    public View_Producto() {
        initComponents();

        Function_Key_Event.Validar_Mayuscula(txt_idcladificacion_producto);
        Function_Key_Event.Validar_Mayuscula(txt_idmoneda);
        Function_Key_Event.Validar_Mayuscula(txt_idfamilia);
        Function_Key_Event.Validar_Mayuscula(txtidunidad);
        Function_Key_Event.Validar_Mayuscula_JEditorPane(txt_descripcion);
        Function_Key_Event.Validar_Mayuscula(txtdiscripcion_corta);
        Function_Key_Event.Validar_Mayuscula(txtobservacion);
        Function_Component.JComboBox(cbx_tipoafectacion);
        Function_Component.JComboBox(cbx_tipoembalaje);
        Function_Component.JTable(tabla_productos_presentacion);
        Function_Key_Event.Validar_Mayuscula(txt_cod_sunat);
        Function_Key_Event.Validar_Mayuscula(txt_descrip_sunat);
        Function_Key_Event.Validar_Mayuscula(txt_id_sunat);
        Function_Key_Event.Validar_numeros(txt_producto_valor);
        Function_Key_Event.Validar_numeros(txt_cantidad);
        Function_Key_Event.Validar_Mayuscula(txt_descr_clasificacion_producto);
        Function_Key_Event.Validar_Mayuscula(txt_descrmoenda);
        Function_Key_Event.Validar_Mayuscula(txt_descrfamilia);
        Function_Key_Event.Validar_Mayuscula(txt_descrunidad);
        Function_Key_Event.Validar_Mayuscula(txt_buscar_tabla_productos);
        Function_Key_Event.Validar_Mayuscula(txt_busqueda_cod_sunat);
        Function_Component.JButton(btn_guardar);
        Function_Component.JButton(btn_eliminar);
        Function_Component.JButton(jButton8);
        txt_id_cod_producto.setVisible(false);
        txt_id_clasificacion_producto.setVisible(false);
        txt_id_moneda.setVisible(false);
        txt_id_familia.setVisible(false);
        txt_id_medida.setVisible(false);
        txt_id_sunat.setVisible(false);
        txt_descr_clasificacion_producto.setEnabled(false);
        txt_descrmoenda.setEnabled(false);
        txt_descrfamilia.setEnabled(false);
        txt_descrunidad.setEnabled(false);

        producto.cbxAfectacion(cbx_tipoafectacion);
        producto.cbxTipoEmbalaje(cbx_tipoembalaje);
        producto.presentacion(tabla_productos_presentacion, Dialog_tipo_unidad_tabla, tabla_tipo_unidad_tabla, TITLE_PROPERTY);
        deshabilitar();

    }

    public void habilitar() {
        txt_id_cod_producto.setEnabled(true);
        txt_descripcion.setEnabled(true);
        txtdiscripcion_corta.setEnabled(true);
        txt_id_clasificacion_producto.setEnabled(true);
        txt_idcladificacion_producto.setEnabled(true);
        txt_id_moneda.setEnabled(true);
        txt_idmoneda.setEnabled(true);
        txt_id_familia.setEnabled(true);
        txt_idfamilia.setEnabled(true);
        txt_id_medida.setEnabled(true);
        txtidunidad.setEnabled(true);
        txtobservacion.setEnabled(true);
        txt_cantidad.setEnabled(true);
        box_estado.setEnabled(true);
        cbx_tipoafectacion.setEnabled(true);
        cbx_tipoembalaje.setEnabled(true);
        txt_producto_valor.setEnabled(true);
        txt_cod_sunat.setEnabled(true);
        txt_descrip_sunat.setEnabled(true);
        txt_id_sunat.setEnabled(true);
        btn_clasificacion_producto.setEnabled(true);
        btn_moneda.setEnabled(true);
        btn_familia.setEnabled(true);
        btn_medida.setEnabled(true);
        btn_agregar_fila.setEnabled(true);
        btn_producto_sunat.setEnabled(true);
        btn_guardar.setEnabled(true);
        btn_eliminar.setEnabled(true);
    }

    public void deshabilitar() {
        txt_id_cod_producto.setEnabled(true);
        txt_descripcion.setEnabled(false);
        txtdiscripcion_corta.setEnabled(false);
        txt_id_clasificacion_producto.setEnabled(false);
        txt_idcladificacion_producto.setEnabled(false);
        txt_descr_clasificacion_producto.setEnabled(false);
        txt_id_moneda.setEnabled(false);
        txt_idmoneda.setEnabled(false);
        txt_descrmoenda.setEnabled(false);
        txt_id_familia.setEnabled(false);
        txt_idfamilia.setEnabled(false);
        txt_descrfamilia.setEnabled(false);
        txt_id_medida.setEnabled(false);
        txtidunidad.setEnabled(false);
        txt_descrunidad.setEnabled(false);
        txtobservacion.setEnabled(false);
        txt_cantidad.setEnabled(false);
        box_estado.setEnabled(false);
        cbx_tipoafectacion.setEnabled(false);
        cbx_tipoembalaje.setEnabled(false);
        txt_producto_valor.setEnabled(false);
        txt_cod_sunat.setEnabled(false);
        txt_descrip_sunat.setEnabled(false);
        txt_id_sunat.setEnabled(false);
        btn_clasificacion_producto.setEnabled(false);
        btn_moneda.setEnabled(false);
        btn_familia.setEnabled(false);
        btn_medida.setEnabled(false);
        btn_agregar_fila.setEnabled(false);
        btn_producto_sunat.setEnabled(false);
        btn_guardar.setEnabled(false);
        btn_eliminar.setEnabled(false);

    }

    public void limpiar() {
        txt_id_cod_producto.setText("");
        txt_descripcion.setText("");
        txtdiscripcion_corta.setText("");
        txt_id_clasificacion_producto.setText("");
        txt_idcladificacion_producto.setText("");
        txt_descr_clasificacion_producto.setText("");
        txt_id_moneda.setText("");
        txt_idmoneda.setText("");
        txt_descrmoenda.setText("");
        txt_id_familia.setText("");
        txt_idfamilia.setText("");
        txt_descrfamilia.setText("");
        txt_id_medida.setText("");
        txtidunidad.setText("");
        txt_descrunidad.setText("");
        txtobservacion.setText("");
        txt_cantidad.setText("");
        box_estado.setSelected(true);
        txt_producto_valor.setText("");
        txt_cod_sunat.setText("");
        txt_descrip_sunat.setText("");
        txt_id_sunat.setText("");

        producto.presentacion(tabla_productos_presentacion, Dialog_tipo_unidad_tabla, tabla_tipo_unidad_tabla, TITLE_PROPERTY);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Dialog_clasificacion_producto = new javax.swing.JDialog();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_descripcion_producto = new javax.swing.JTable();
        Dialog_tipo_moneda = new javax.swing.JDialog();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabla_tipo_moneda = new javax.swing.JTable();
        Dialog_familia = new javax.swing.JDialog();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabla_familia = new javax.swing.JTable();
        Dialog_tipo_unidad = new javax.swing.JDialog();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabla_tipo_unidad = new javax.swing.JTable();
        Dialog_tipo_unidad_tabla = new javax.swing.JDialog();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tabla_tipo_unidad_tabla = new javax.swing.JTable();
        Dialog_tipo_producto_sunat = new javax.swing.JDialog();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tabla_producto_sunat = new javax.swing.JTable();
        jPanel20 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        txt_busqueda_cod_sunat = new javax.swing.JTextField();
        Dialog_producto = new javax.swing.JDialog();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tabla_producto = new javax.swing.JTable();
        jPanel23 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txt_buscar_tabla_productos = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtdiscripcion_corta = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_descripcion = new javax.swing.JEditorPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_idcladificacion_producto = new javax.swing.JTextField();
        btn_clasificacion_producto = new javax.swing.JButton();
        txt_descr_clasificacion_producto = new javax.swing.JTextField();
        txt_idmoneda = new javax.swing.JTextField();
        btn_moneda = new javax.swing.JButton();
        txt_descrmoenda = new javax.swing.JTextField();
        txt_idfamilia = new javax.swing.JTextField();
        btn_familia = new javax.swing.JButton();
        txt_descrfamilia = new javax.swing.JTextField();
        txtidunidad = new javax.swing.JTextField();
        btn_medida = new javax.swing.JButton();
        txt_descrunidad = new javax.swing.JTextField();
        txtobservacion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txt_id_clasificacion_producto = new javax.swing.JTextField();
        txt_id_moneda = new javax.swing.JTextField();
        txt_id_familia = new javax.swing.JTextField();
        txt_id_medida = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        box_estado = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        cbx_tipoafectacion = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        txt_producto_valor = new javax.swing.JTextField();
        jPanel25 = new javax.swing.JPanel();
        cbx_tipoembalaje = new javax.swing.JComboBox<>();
        txt_cantidad = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txt_cod_barra = new javax.swing.JTextField();
        txt_id_cod_producto = new javax.swing.JTextField();
        btn_buscar = new javax.swing.JButton();
        btn_buscar1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_productos_presentacion = new javax.swing.JTable();
        jPanel14 = new javax.swing.JPanel();
        btn_agregar_fila = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        txt_cod_sunat = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        btn_producto_sunat = new javax.swing.JButton();
        txt_descrip_sunat = new javax.swing.JTextField();
        txt_id_sunat = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        btn_guardar = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();

        Dialog_clasificacion_producto.setAutoRequestFocus(false);
        Dialog_clasificacion_producto.setMinimumSize(new java.awt.Dimension(400, 185));
        Dialog_clasificacion_producto.setUndecorated(true);

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane3.setBackground(new java.awt.Color(255, 255, 255));

        table_descripcion_producto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        table_descripcion_producto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_descripcion_productoMouseClicked(evt);
            }
        });
        table_descripcion_producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                table_descripcion_productoKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(table_descripcion_producto);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout Dialog_clasificacion_productoLayout = new javax.swing.GroupLayout(Dialog_clasificacion_producto.getContentPane());
        Dialog_clasificacion_producto.getContentPane().setLayout(Dialog_clasificacion_productoLayout);
        Dialog_clasificacion_productoLayout.setHorizontalGroup(
            Dialog_clasificacion_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Dialog_clasificacion_productoLayout.setVerticalGroup(
            Dialog_clasificacion_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Dialog_tipo_moneda.setAutoRequestFocus(false);
        Dialog_tipo_moneda.setMinimumSize(new java.awt.Dimension(400, 185));
        Dialog_tipo_moneda.setUndecorated(true);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane4.setBackground(new java.awt.Color(255, 255, 255));

        tabla_tipo_moneda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabla_tipo_moneda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_tipo_monedaMouseClicked(evt);
            }
        });
        tabla_tipo_moneda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_tipo_monedaKeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(tabla_tipo_moneda);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout Dialog_tipo_monedaLayout = new javax.swing.GroupLayout(Dialog_tipo_moneda.getContentPane());
        Dialog_tipo_moneda.getContentPane().setLayout(Dialog_tipo_monedaLayout);
        Dialog_tipo_monedaLayout.setHorizontalGroup(
            Dialog_tipo_monedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Dialog_tipo_monedaLayout.setVerticalGroup(
            Dialog_tipo_monedaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Dialog_familia.setAutoRequestFocus(false);
        Dialog_familia.setMinimumSize(new java.awt.Dimension(400, 185));
        Dialog_familia.setUndecorated(true);

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane5.setBackground(new java.awt.Color(255, 255, 255));

        tabla_familia.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabla_familia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_familiaMouseClicked(evt);
            }
        });
        tabla_familia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_familiaKeyPressed(evt);
            }
        });
        jScrollPane5.setViewportView(tabla_familia);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout Dialog_familiaLayout = new javax.swing.GroupLayout(Dialog_familia.getContentPane());
        Dialog_familia.getContentPane().setLayout(Dialog_familiaLayout);
        Dialog_familiaLayout.setHorizontalGroup(
            Dialog_familiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Dialog_familiaLayout.setVerticalGroup(
            Dialog_familiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Dialog_tipo_unidad.setAutoRequestFocus(false);
        Dialog_tipo_unidad.setMinimumSize(new java.awt.Dimension(400, 185));
        Dialog_tipo_unidad.setUndecorated(true);

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane6.setBackground(new java.awt.Color(255, 255, 255));

        tabla_tipo_unidad.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabla_tipo_unidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_tipo_unidadMouseClicked(evt);
            }
        });
        tabla_tipo_unidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_tipo_unidadKeyPressed(evt);
            }
        });
        jScrollPane6.setViewportView(tabla_tipo_unidad);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout Dialog_tipo_unidadLayout = new javax.swing.GroupLayout(Dialog_tipo_unidad.getContentPane());
        Dialog_tipo_unidad.getContentPane().setLayout(Dialog_tipo_unidadLayout);
        Dialog_tipo_unidadLayout.setHorizontalGroup(
            Dialog_tipo_unidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Dialog_tipo_unidadLayout.setVerticalGroup(
            Dialog_tipo_unidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Dialog_tipo_unidad_tabla.setAutoRequestFocus(false);
        Dialog_tipo_unidad_tabla.setMinimumSize(new java.awt.Dimension(400, 185));
        Dialog_tipo_unidad_tabla.setUndecorated(true);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane7.setBackground(new java.awt.Color(255, 255, 255));

        tabla_tipo_unidad_tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabla_tipo_unidad_tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_tipo_unidad_tablaMouseClicked(evt);
            }
        });
        tabla_tipo_unidad_tabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_tipo_unidad_tablaKeyPressed(evt);
            }
        });
        jScrollPane7.setViewportView(tabla_tipo_unidad_tabla);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 936, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout Dialog_tipo_unidad_tablaLayout = new javax.swing.GroupLayout(Dialog_tipo_unidad_tabla.getContentPane());
        Dialog_tipo_unidad_tabla.getContentPane().setLayout(Dialog_tipo_unidad_tablaLayout);
        Dialog_tipo_unidad_tablaLayout.setHorizontalGroup(
            Dialog_tipo_unidad_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Dialog_tipo_unidad_tablaLayout.setVerticalGroup(
            Dialog_tipo_unidad_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Dialog_tipo_producto_sunat.setTitle("LISTA DE CODIGOS DE PRODUCTOS SUNAT");
        Dialog_tipo_producto_sunat.setBackground(new java.awt.Color(255, 255, 255));
        Dialog_tipo_producto_sunat.setMinimumSize(new java.awt.Dimension(772, 478));
        Dialog_tipo_producto_sunat.setModal(true);

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));

        tabla_producto_sunat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabla_producto_sunat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_producto_sunatMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tabla_producto_sunat);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setText("Ingrese descripcion");

        txt_busqueda_cod_sunat.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        txt_busqueda_cod_sunat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_busqueda_cod_sunatKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_busqueda_cod_sunat)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addContainerGap())))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_busqueda_cod_sunat, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout Dialog_tipo_producto_sunatLayout = new javax.swing.GroupLayout(Dialog_tipo_producto_sunat.getContentPane());
        Dialog_tipo_producto_sunat.getContentPane().setLayout(Dialog_tipo_producto_sunatLayout);
        Dialog_tipo_producto_sunatLayout.setHorizontalGroup(
            Dialog_tipo_producto_sunatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dialog_tipo_producto_sunatLayout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        Dialog_tipo_producto_sunatLayout.setVerticalGroup(
            Dialog_tipo_producto_sunatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dialog_tipo_producto_sunatLayout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        Dialog_producto.setTitle("LISTA DE PRODUCTOS");
        Dialog_producto.setBackground(new java.awt.Color(255, 255, 255));
        Dialog_producto.setMinimumSize(new java.awt.Dimension(762, 467));
        Dialog_producto.setModal(true);
        Dialog_producto.setResizable(false);

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));

        tabla_producto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabla_producto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_productoMouseClicked(evt);
            }
        });
        tabla_producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_productoKeyPressed(evt);
            }
        });
        jScrollPane9.setViewportView(tabla_producto);

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 762, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
                .addGap(28, 28, 28))
        );

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setText("Ingrese descripcion");

        txt_buscar_tabla_productos.setFont(new java.awt.Font("Consolas", 0, 14)); // NOI18N
        txt_buscar_tabla_productos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_buscar_tabla_productosKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_buscar_tabla_productos)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addContainerGap())))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_buscar_tabla_productos, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout Dialog_productoLayout = new javax.swing.GroupLayout(Dialog_producto.getContentPane());
        Dialog_producto.getContentPane().setLayout(Dialog_productoLayout);
        Dialog_productoLayout.setHorizontalGroup(
            Dialog_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dialog_productoLayout.createSequentialGroup()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        Dialog_productoLayout.setVerticalGroup(
            Dialog_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dialog_productoLayout.createSequentialGroup()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("REGISTRO DE PRODUCTO");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Producto"));

        txtdiscripcion_corta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtdiscripcion_cortaKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdiscripcion_cortaKeyTyped(evt);
            }
        });

        txt_descripcion.setAutoscrolls(false);
        txt_descripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_descripcionKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(txt_descripcion);

        jLabel1.setText("Descripcion corta");

        jLabel2.setText("Descripcion");

        jLabel3.setText("Clasificacion producto");

        txt_idcladificacion_producto.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_idcladificacion_productoCaretUpdate(evt);
            }
        });
        txt_idcladificacion_producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_idcladificacion_productoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_idcladificacion_productoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_idcladificacion_productoKeyTyped(evt);
            }
        });

        btn_clasificacion_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_clasificacion_productoActionPerformed(evt);
            }
        });

        txt_idmoneda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_idmonedaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_idmonedaKeyReleased(evt);
            }
        });

        btn_moneda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_monedaActionPerformed(evt);
            }
        });

        txt_idfamilia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_idfamiliaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_idfamiliaKeyReleased(evt);
            }
        });

        btn_familia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_familiaActionPerformed(evt);
            }
        });

        txtidunidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtidunidadKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtidunidadKeyReleased(evt);
            }
        });

        btn_medida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_medidaActionPerformed(evt);
            }
        });

        txtobservacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtobservacionKeyPressed(evt);
            }
        });

        jLabel4.setText("Moneda");

        jLabel5.setText("Familia");

        jLabel6.setText("Medida");

        jLabel8.setText("Observacion");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtobservacion, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel3)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                        .addComponent(txtdiscripcion_corta)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(txtidunidad, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_medida)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_descrunidad))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_idfamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_familia)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_descrfamilia))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_idmoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_moneda)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_descrmoenda))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(txt_idcladificacion_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_clasificacion_producto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_descr_clasificacion_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(txt_id_moneda, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txt_id_clasificacion_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_id_familia, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_id_medida, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel2)
                .addGap(2, 2, 2)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addComponent(txtdiscripcion_corta, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_id_clasificacion_producto)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txt_idcladificacion_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_clasificacion_producto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_descr_clasificacion_producto))
                .addGap(4, 4, 4)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_idmoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_moneda, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_descrmoenda, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_id_moneda, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_idfamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_familia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_descrfamilia, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_id_familia, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtidunidad, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_medida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_descrunidad, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_id_medida, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(1, 1, 1)
                .addComponent(jLabel8)
                .addGap(3, 3, 3)
                .addComponent(txtobservacion, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Descripcion adicional"));

        box_estado.setSelected(true);
        box_estado.setText("Activo");
        box_estado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                box_estadoKeyPressed(evt);
            }
        });

        jLabel7.setText("Tipo afectacion");

        cbx_tipoafectacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbx_tipoafectacionItemStateChanged(evt);
            }
        });
        cbx_tipoafectacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_tipoafectacionKeyPressed(evt);
            }
        });

        jLabel9.setText("Estado");

        txt_producto_valor.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txt_producto_valorCaretUpdate(evt);
            }
        });
        txt_producto_valor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_producto_valorKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_producto_valorKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_producto_valorKeyTyped(evt);
            }
        });

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));
        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder("Tipo de embalaje"));

        cbx_tipoembalaje.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbx_tipoembalajeItemStateChanged(evt);
            }
        });
        cbx_tipoembalaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_tipoembalajeKeyPressed(evt);
            }
        });

        jLabel14.setText("Tipo de embalaje");

        jLabel15.setText("Cantidad");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txt_cantidad)
                    .addComponent(cbx_tipoembalaje, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel25Layout.createSequentialGroup()
                        .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbx_tipoembalaje, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbx_tipoafectacion, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(box_estado)
                            .addComponent(txt_producto_valor, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 60, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbx_tipoafectacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txt_producto_valor, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(box_estado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setBorder(javax.swing.BorderFactory.createTitledBorder("COD. PRODUCTO"));

        jLabel12.setText("Codigo de producto");

        txt_cod_barra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_cod_barraKeyPressed(evt);
            }
        });

        txt_id_cod_producto.setEditable(false);
        txt_id_cod_producto.setMinimumSize(new java.awt.Dimension(15, 20));
        txt_id_cod_producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_id_cod_productoActionPerformed(evt);
            }
        });

        btn_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarActionPerformed(evt);
            }
        });

        btn_buscar1.setText("EDITAR COD");
        btn_buscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel24Layout.createSequentialGroup()
                        .addComponent(txt_cod_barra, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txt_id_cod_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_buscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_buscar1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_buscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_id_cod_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_cod_barra, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_buscar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        tabla_productos_presentacion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla_productos_presentacion.setToolTipText("TABLA DE PRESENTACION");
        tabla_productos_presentacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_productos_presentacionMouseClicked(evt);
            }
        });
        tabla_productos_presentacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_productos_presentacionKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabla_productos_presentacionKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_productos_presentacion);

        btn_agregar_fila.setText("+");
        btn_agregar_fila.setToolTipText("AGREGAR MAS FILA");
        btn_agregar_fila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregar_filaActionPerformed(evt);
            }
        });
        btn_agregar_fila.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btn_agregar_filaKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_agregar_fila, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(btn_agregar_fila)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1190, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jTabbedPane1.addTab("PRESENTACIONES", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        txt_cod_sunat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_cod_sunatKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cod_sunatKeyReleased(evt);
            }
        });

        jLabel10.setText("Codigo producto sunat");

        btn_producto_sunat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_producto_sunatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txt_id_sunat, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel17Layout.createSequentialGroup()
                                .addComponent(txt_cod_sunat, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(btn_producto_sunat, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_descrip_sunat, javax.swing.GroupLayout.DEFAULT_SIZE, 991, Short.MAX_VALUE))
                            .addComponent(jLabel10))
                        .addContainerGap())))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_producto_sunat, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(txt_descrip_sunat, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(txt_cod_sunat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_id_sunat, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jTabbedPane1.addTab("SUNAT", jPanel7);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jTabbedPane1)
                .addGap(0, 0, 0))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanel15.setPreferredSize(new java.awt.Dimension(207, 10));

        btn_guardar.setText("GUARDAR");
        btn_guardar.setToolTipText("AGREGAR MAS FILA");
        btn_guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_guardarActionPerformed(evt);
            }
        });

        jButton8.setText("CANCELAR");
        jButton8.setToolTipText("AGREGAR MAS FILA");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        btn_eliminar.setText("ELIMINAR");
        btn_eliminar.setToolTipText("AGREGAR MAS FILA");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(btn_guardar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_guardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, 1272, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_clasificacion_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_clasificacion_productoActionPerformed
        // TODO add your handling code here:
        producto.table_clasificaicon_producto(table_descripcion_producto, txt_idcladificacion_producto.getText());
        Dimension desktopSize = jPanel1.getSize();
        Dimension FrameSize = Dialog_clasificacion_producto.getSize();
        Dialog_clasificacion_producto.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        Dialog_clasificacion_producto.setVisible(true);

    }//GEN-LAST:event_btn_clasificacion_productoActionPerformed

    private void txt_idcladificacion_productoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_idcladificacion_productoKeyPressed
        // TODO add your handling code here:

        if ((evt.getKeyCode() == KeyEvent.VK_DOWN)) {
            table_descripcion_producto.requestFocus();
        }
        if ((evt.getKeyCode() == KeyEvent.VK_ESCAPE)) {
            Dialog_clasificacion_producto.dispose();
        }
    }//GEN-LAST:event_txt_idcladificacion_productoKeyPressed

    private void txt_idcladificacion_productoCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_idcladificacion_productoCaretUpdate
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_idcladificacion_productoCaretUpdate

    private void txt_idcladificacion_productoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_idcladificacion_productoKeyTyped

    }//GEN-LAST:event_txt_idcladificacion_productoKeyTyped

    private void table_descripcion_productoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_table_descripcion_productoKeyPressed
        // TODO add your handling code here:
        if ((evt.getKeyCode() == KeyEvent.VK_ESCAPE)) {
            txt_idcladificacion_producto.requestFocus();
            Dialog_clasificacion_producto.dispose();
        }
        if ((evt.getKeyCode() == KeyEvent.VK_ENTER)) {
            txt_idmoneda.requestFocus();
            int fila = table_descripcion_producto.getSelectedRow();
            txt_id_clasificacion_producto.setText((table_descripcion_producto.getValueAt(fila, 0).toString()));
            txt_idcladificacion_producto.setText((table_descripcion_producto.getValueAt(fila, 1).toString()));
            txt_descr_clasificacion_producto.setText((table_descripcion_producto.getValueAt(fila, 2).toString()));
            Dialog_clasificacion_producto.dispose();

        }


    }//GEN-LAST:event_table_descripcion_productoKeyPressed

    private void txt_idcladificacion_productoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_idcladificacion_productoKeyReleased
        // TODO add your handling code here:

        producto.table_clasificaicon_producto(table_descripcion_producto, txt_idcladificacion_producto.getText());
        Dimension desktopSize = jPanel1.getSize();
        Dimension FrameSize = Dialog_clasificacion_producto.getSize();
        Dialog_clasificacion_producto.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        Dialog_clasificacion_producto.setVisible(true);

        //producto.table_clasificaicon_producto(table_descripcion_producto, txt_idcladificacion_producto.getText());
    }//GEN-LAST:event_txt_idcladificacion_productoKeyReleased

    private void table_descripcion_productoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_descripcion_productoMouseClicked
        // TODO add your handling code here:
        int fila = table_descripcion_producto.rowAtPoint(evt.getPoint());
        txt_idcladificacion_producto.setText((table_descripcion_producto.getValueAt(fila, 1).toString()));
        txt_descr_clasificacion_producto.setText((table_descripcion_producto.getValueAt(fila, 2).toString()));

    }//GEN-LAST:event_table_descripcion_productoMouseClicked

    private void txt_idmonedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_idmonedaKeyReleased
        // TODO add your handling code here:
        producto.table_tipo_moneda(tabla_tipo_moneda, txt_idmoneda.getText());
        Dimension desktopSize = jPanel1.getSize();
        Dimension FrameSize = Dialog_tipo_moneda.getSize();
        Dialog_tipo_moneda.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        Dialog_tipo_moneda.setVisible(true);

    }//GEN-LAST:event_txt_idmonedaKeyReleased

    private void tabla_tipo_monedaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_tipo_monedaMouseClicked
        // TODO add your handling code here:
        int fila = tabla_tipo_moneda.rowAtPoint(evt.getPoint());
        txt_id_moneda.setText((tabla_tipo_moneda.getValueAt(fila, 0).toString()));
        txt_idmoneda.setText((tabla_tipo_moneda.getValueAt(fila, 1).toString()));
        txt_descrunidad.setText((tabla_tipo_moneda.getValueAt(fila, 2).toString()));
    }//GEN-LAST:event_tabla_tipo_monedaMouseClicked

    private void tabla_tipo_monedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_tipo_monedaKeyPressed
        // TODO add your handling code here:
        if ((evt.getKeyCode() == KeyEvent.VK_ESCAPE)) {
            txt_idmoneda.requestFocus();
            Dialog_tipo_moneda.dispose();
        }
        if ((evt.getKeyCode() == KeyEvent.VK_ENTER)) {
            txt_idfamilia.requestFocus();
            int fila = tabla_tipo_moneda.getSelectedRow();
            txt_id_moneda.setText((tabla_tipo_moneda.getValueAt(fila, 0).toString()));
            txt_idmoneda.setText((tabla_tipo_moneda.getValueAt(fila, 1).toString()));
            txt_descrmoenda.setText((tabla_tipo_moneda.getValueAt(fila, 2).toString()));
            Dialog_tipo_moneda.dispose();
        }
    }//GEN-LAST:event_tabla_tipo_monedaKeyPressed

    private void tabla_familiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_familiaMouseClicked
        // TODO add your handling code here:
        int fila = tabla_familia.rowAtPoint(evt.getPoint());
        txt_id_familia.setText((tabla_familia.getValueAt(fila, 0).toString()));
        txt_idfamilia.setText((tabla_familia.getValueAt(fila, 1).toString()));
        txt_descrfamilia.setText((tabla_familia.getValueAt(fila, 2).toString()));
    }//GEN-LAST:event_tabla_familiaMouseClicked

    private void tabla_familiaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_familiaKeyPressed
        // TODO add your handling code here:
        if ((evt.getKeyCode() == KeyEvent.VK_ESCAPE)) {
            txt_idfamilia.requestFocus();
            Dialog_familia.dispose();
        }
        if ((evt.getKeyCode() == KeyEvent.VK_ENTER)) {
            txtidunidad.requestFocus();
            int fila = tabla_familia.getSelectedRow();
            txt_id_familia.setText((tabla_familia.getValueAt(fila, 0).toString()));
            txt_idfamilia.setText((tabla_familia.getValueAt(fila, 1).toString()));
            txt_descrfamilia.setText((tabla_familia.getValueAt(fila, 2).toString()));
            Dialog_familia.dispose();
        }
    }//GEN-LAST:event_tabla_familiaKeyPressed

    private void txt_idfamiliaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_idfamiliaKeyReleased
        // TODO add your handling code here:
        producto.table_familia(tabla_familia, txt_idfamilia.getText());
        Dimension desktopSize = View_Escritorio.desktopPane.getSize();
        Dimension FrameSize = Dialog_familia.getSize();
        Dialog_familia.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        Dialog_familia.setVisible(true);

    }//GEN-LAST:event_txt_idfamiliaKeyReleased

    private void tabla_tipo_unidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_tipo_unidadMouseClicked
        // TODO add your handling code here:
        int fila = tabla_tipo_unidad.rowAtPoint(evt.getPoint());
        txt_id_medida.setText((tabla_tipo_unidad.getValueAt(fila, 0).toString()));
        txtidunidad.setText((tabla_tipo_unidad.getValueAt(fila, 1).toString()));
        txt_descrunidad.setText((tabla_tipo_unidad.getValueAt(fila, 2).toString()));
    }//GEN-LAST:event_tabla_tipo_unidadMouseClicked

    private void tabla_tipo_unidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_tipo_unidadKeyPressed
        // TODO add your handling code here:
        if ((evt.getKeyCode() == KeyEvent.VK_ESCAPE)) {
            txtobservacion.requestFocus();
            Dialog_tipo_unidad.dispose();
        }
        if ((evt.getKeyCode() == KeyEvent.VK_ENTER)) {
            txtobservacion.requestFocus();
            int fila = tabla_tipo_unidad.getSelectedRow();
            txt_id_medida.setText((tabla_tipo_unidad.getValueAt(fila, 0).toString()));
            txtidunidad.setText((tabla_tipo_unidad.getValueAt(fila, 1).toString()));
            txt_descrunidad.setText((tabla_tipo_unidad.getValueAt(fila, 2).toString()));
            Dialog_tipo_unidad.dispose();
        }
    }//GEN-LAST:event_tabla_tipo_unidadKeyPressed

    private void txtidunidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidunidadKeyReleased
        // TODO add your handling code here:
        producto.table_tipo_unidad(tabla_tipo_unidad, txtidunidad.getText());
        Dimension desktopSize = jPanel1.getSize();
        Dimension FrameSize = Dialog_tipo_unidad.getSize();
        Dialog_tipo_unidad.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        Dialog_tipo_unidad.setVisible(true);

    }//GEN-LAST:event_txtidunidadKeyReleased

    private void txt_descripcionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_descripcionKeyPressed
        // TODO add your handling code here:
        if ((evt.getKeyCode() == KeyEvent.VK_ENTER)) {
            txtdiscripcion_corta.requestFocus();

        }
    }//GEN-LAST:event_txt_descripcionKeyPressed

    private void txtdiscripcion_cortaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdiscripcion_cortaKeyPressed
        // TODO add your handling code here:

        if ((evt.getKeyCode() == KeyEvent.VK_ENTER)) {
            txt_idcladificacion_producto.requestFocus();

        }

    }//GEN-LAST:event_txtdiscripcion_cortaKeyPressed

    private void txt_idmonedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_idmonedaKeyPressed
        // TODO add your handling code here:
        if ((evt.getKeyCode() == KeyEvent.VK_DOWN)) {
            tabla_tipo_moneda.requestFocus();
        }
        if ((evt.getKeyCode() == KeyEvent.VK_ESCAPE)) {
            Dialog_tipo_moneda.dispose();
        }
    }//GEN-LAST:event_txt_idmonedaKeyPressed

    private void txt_idfamiliaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_idfamiliaKeyPressed
        // TODO add your handling code here:
        if ((evt.getKeyCode() == KeyEvent.VK_DOWN)) {
            tabla_familia.requestFocus();
        }
        if ((evt.getKeyCode() == KeyEvent.VK_ESCAPE)) {
            Dialog_familia.dispose();
        }
    }//GEN-LAST:event_txt_idfamiliaKeyPressed

    private void txtidunidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtidunidadKeyPressed
        // TODO add your handling code here:
        if ((evt.getKeyCode() == KeyEvent.VK_DOWN)) {
            tabla_tipo_unidad.requestFocus();
        }
        if ((evt.getKeyCode() == KeyEvent.VK_ESCAPE)) {
            Dialog_tipo_unidad.dispose();
        }
    }//GEN-LAST:event_txtidunidadKeyPressed

    private void txtobservacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtobservacionKeyPressed
        // TODO add your handling code here:
        if ((evt.getKeyCode() == KeyEvent.VK_ENTER)) {
            cbx_tipoafectacion.requestFocus();
        }
    }//GEN-LAST:event_txtobservacionKeyPressed

    private void cbx_tipoafectacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_tipoafectacionKeyPressed
        // TODO add your handling code here:
        if ((evt.getKeyCode() == KeyEvent.VK_ENTER)) {
            txt_producto_valor.requestFocus();

        }
    }//GEN-LAST:event_cbx_tipoafectacionKeyPressed

    private void tabla_tipo_unidad_tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_tipo_unidad_tablaMouseClicked
        // TODO add your handling code here:
        int fila = tabla_tipo_unidad_tabla.rowAtPoint(evt.getPoint());
        tabla_productos_presentacion.setValueAt(tabla_tipo_unidad_tabla.getValueAt(fila, 0), tabla_productos_presentacion.getSelectedRow() + producto.variable(), 6);
        tabla_productos_presentacion.setValueAt(tabla_tipo_unidad_tabla.getValueAt(fila, 1), tabla_productos_presentacion.getSelectedRow() + producto.variable(), 7);
        this.Dialog_tipo_unidad_tabla.dispose();


    }//GEN-LAST:event_tabla_tipo_unidad_tablaMouseClicked

    private void tabla_tipo_unidad_tablaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_tipo_unidad_tablaKeyPressed
        // TODO add your handling code here:
        int fila = tabla_tipo_unidad_tabla.getSelectedRow();
        tabla_productos_presentacion.setValueAt("", tabla_productos_presentacion.getSelectedRow() + producto.variable(), 6);
        //tabla_productos_presentacion.setValueAt(tabla_tipo_unidad_tabla.getValueAt(fila, 1), tabla_productos_presentacion.getSelectedRow(), 7);
        if ((evt.getKeyCode() == KeyEvent.VK_ESCAPE)) {
            //txtobservacion.requestFocus();
            Dialog_tipo_unidad_tabla.dispose();
        }
        if ((evt.getKeyCode() == KeyEvent.VK_ENTER)) {
            System.out.println("View.View_Producto.tabla_tipo_unidad_tablaKeyPressed()" + producto.variable());
            tabla_productos_presentacion.setValueAt(tabla_tipo_unidad_tabla.getValueAt(fila, 0), tabla_productos_presentacion.getSelectedRow() + producto.variable(), 6);
            tabla_productos_presentacion.setValueAt(tabla_tipo_unidad_tabla.getValueAt(fila, 1), tabla_productos_presentacion.getSelectedRow() + producto.variable(), 7);
            this.Dialog_tipo_unidad_tabla.dispose();

        }

    }//GEN-LAST:event_tabla_tipo_unidad_tablaKeyPressed

    private void btn_agregar_filaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregar_filaActionPerformed
        // TODO add your handling code here:
        producto.add_presentacion(tabla_productos_presentacion, Dialog_tipo_unidad_tabla, tabla_tipo_unidad_tabla, txt_id_cod_producto.getText(), txt_descripcion.getText(), txtdiscripcion_corta.getText());
    }//GEN-LAST:event_btn_agregar_filaActionPerformed
    public void table() {
        btn_agregar_fila.doClick();
    }

    public JDialog dialog() {
        return Dialog_tipo_unidad_tabla;
    }

    public JTable table_unidad() {
        return tabla_tipo_unidad_tabla;
    }

    public String txt_id_cod_producto() {
        return txt_id_cod_producto.getText();
    }

    public String txt_descripcion() {
        return txt_descripcion.getText();
    }

    public String txtdiscripcion_corta() {
        return txtdiscripcion_corta.getText();
    }
    private void tabla_productos_presentacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_productos_presentacionMouseClicked
        // TODO add your handling code here:
        producto.reset_variable();
        int fila = tabla_productos_presentacion.rowAtPoint(evt.getPoint());
        int column = tabla_productos_presentacion.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / tabla_productos_presentacion.getRowHeight();
        if (row < tabla_productos_presentacion.getRowCount() && row >= 0 && column < tabla_productos_presentacion.getColumnCount() && column >= 0) {
            Object value = tabla_productos_presentacion.getValueAt(row, column);
            if (value instanceof JButton) {
                ((JButton) value).doClick();
                JButton boton = (JButton) value;

                if (boton.getName().equals("btneliminar")) {
                    if (tabla_productos_presentacion.getSelectedRow() != -1) {
                        /*DefaultTableModel model = (DefaultTableModel) tabla_productos_presentacion.getModel();
                        model.removeRow(tabla_productos_presentacion.getSelectedRow());*/
                        if (tabla_productos_presentacion.getValueAt(fila, 1).toString().equals("")) {
                            DefaultTableModel model = (DefaultTableModel) tabla_productos_presentacion.getModel();
                            model.removeRow(tabla_productos_presentacion.getSelectedRow());
                        } else if (tabla_productos_presentacion.getValueAt(fila, 1).toString().equals(null)) {
                            System.out.println("1");
                        } else {
                            producto.delete_Productos_Presentacion(tabla_productos_presentacion.getValueAt(fila, 1).toString());
                            DefaultTableModel model = (DefaultTableModel) tabla_productos_presentacion.getModel();
                            model.removeRow(tabla_productos_presentacion.getSelectedRow());
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "sss");
                    }

                }
            }
            if (value instanceof JCheckBox) {
                //((JCheckBox)value).doClick();
                JCheckBox ch = (JCheckBox) value;
                if (ch.isSelected() == true) {
                    ch.setSelected(false);
                }
                if (ch.isSelected() == false) {
                    ch.setSelected(true);
                }

            }
        }
    }//GEN-LAST:event_tabla_productos_presentacionMouseClicked

    private void btn_guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_guardarActionPerformed
        // TODO add your handling code here:

        if (txt_id_cod_producto.getText().length() == 0) {
            Function_ShowMessageDialog.ShowMessageDialogvalidacion(rootPane, "CAMPO VACIO");
            return;
        }
        if (txt_cod_barra.getText().length() == 0) {
            Function_ShowMessageDialog.ShowMessageDialogvalidacion(rootPane, "CAMPO VACIO");
            return;
        }
        if (txt_descripcion.getText().length() == 0) {
            Function_ShowMessageDialog.ShowMessageDialogvalidacion(rootPane, "CAMPO VACIO");
            return;
        }
        if (txtdiscripcion_corta.getText().length() == 0) {
            Function_ShowMessageDialog.ShowMessageDialogvalidacion(rootPane, "CAMPO VACIO");

            return;
        }
        if (txt_id_clasificacion_producto.getText().length() == 0) {
            Function_ShowMessageDialog.ShowMessageDialogvalidacion(rootPane, "CAMPO VACIO");
            return;
        }
        if (txt_id_moneda.getText().length() == 0) {
            Function_ShowMessageDialog.ShowMessageDialogvalidacion(rootPane, "CAMPO VACIO");
            return;
        }
        if (txt_id_familia.getText().length() == 0) {
            Function_ShowMessageDialog.ShowMessageDialogvalidacion(rootPane, "CAMPO VACIO");
            return;
        }
        if (txt_id_medida.getText().length() == 0) {
            Function_ShowMessageDialog.ShowMessageDialogvalidacion(rootPane, "CAMPO VACIO");
            return;
        }
        if (tabla_productos_presentacion.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "DEBE CONTENER MAS DE UNA FILA LA TABLA DE PRESENTACION");
            return;

        }
        int tabla = 0;
        tabla_productos_presentacion.editCellAt(0, 3);
        Component aComp = tabla_productos_presentacion.getEditorComponent();
        aComp.requestFocus();
        for (int j = 0; j < tabla_productos_presentacion.getRowCount(); j++) {
            for (int i = 2; i < tabla_productos_presentacion.getColumnCount(); i++) {
                try {
                    
                
                if (tabla_productos_presentacion.getValueAt(j, i).toString() == ("")) {
                    JOptionPane.showMessageDialog(rootPane, "HAY CAMPO VACIO:" + tabla_productos_presentacion.getTableHeader().getColumnModel().getColumn(i).getHeaderValue());
                    tabla = tabla + 1;
                    tabla_productos_presentacion.editCellAt(j, i);
                    Component aComp1 = tabla_productos_presentacion.getEditorComponent();
                    aComp1.requestFocus();
                    break;

                } else if (tabla_productos_presentacion.getValueAt(j, i).toString().length() == 0) {
                    JOptionPane.showMessageDialog(rootPane, "HAY CAMPO VACIO:" + tabla_productos_presentacion.getTableHeader().getColumnModel().getColumn(i).getHeaderValue());
                    tabla = tabla + 1;
                    tabla_productos_presentacion.editCellAt(j, i);
                    Component aComp1 = tabla_productos_presentacion.getEditorComponent();
                    aComp1.requestFocus();
                    break;

                }
                } catch (Exception e) {
                }

            }
            if (tabla > 0) {
                break;

            }
            
            

        }
        

        entity_producto = new Entity_Producto(txt_id_cod_producto.getText(), txt_cod_barra.getText(), txt_descripcion.getText(), txtdiscripcion_corta.getText(),
                txt_id_clasificacion_producto.getText(), txt_id_moneda.getText(), txt_id_familia.getText(), txt_id_medida.getText(), txtobservacion.getText(),
                ((Entity_Tipo_afectacion) cbx_tipoafectacion.getSelectedItem()).getId(), box_estado.isSelected(), txt_producto_valor.getText(),txt_cantidad.getText(),((Entity_Tipo_Embalaje) cbx_tipoembalaje.getSelectedItem()).getEnbalaje_id());
        
        if (producto.Add_Productos(entity_producto).equals("exito")) {

            for (int i = 0; i < tabla_productos_presentacion.getRowCount(); i++) {
                entity_presenacion = new Entity_Producto_Presentacion(tabla_productos_presentacion.getValueAt(i, 1).toString(), tabla_productos_presentacion.getValueAt(i, 2).toString(), tabla_productos_presentacion.getValueAt(i, 3).toString(), tabla_productos_presentacion.getValueAt(i, 4).toString(),
                        tabla_productos_presentacion.getValueAt(i, 5).toString(), tabla_productos_presentacion.getValueAt(i, 6).toString(), tabla_productos_presentacion.getValueAt(i, 8).toString(), tabla_productos_presentacion.getValueAt(i, 9).toString(),
                        tabla_productos_presentacion.getValueAt(i, 10).toString(), Boolean.valueOf(tabla_productos_presentacion.getValueAt(i, 12).toString()), tabla_productos_presentacion.getValueAt(i, 11).toString());

                
                if (producto.Add_Productos_Presentacion(entity_presenacion).equals("exito")) {
                    
                }else{
                        Function_ShowMessageDialog.ShowMessageDialogvalidacion(null, "ERROR DE REGISTRO:"+ tabla_productos_presentacion.getValueAt(i, 3).toString()+" YA EXISTE");
                        return; 
                       
                        
                }
                
            }
            if (txt_id_sunat.getText().length() > 0 && txt_descrip_sunat.getText().length() > 0 && txt_cod_sunat.getText().length() > 0) {
                producto.Add_Productos_Sunat(txt_id_cod_producto.getText(), txt_id_sunat.getText());
            }
            txt_cod_barra.setEnabled(true);
            txt_cod_barra.setText("");
            txt_cod_barra.requestFocus();
            limpiar();
            deshabilitar();
        } else {
            Function_ShowMessageDialog.ShowMessageDialogvalidacion(null, "FALLO REGISTRO");
        }


    }//GEN-LAST:event_btn_guardarActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        try {
            txt_cod_barra.setEnabled(true);
            txt_cod_barra.setText("");
            txt_cod_barra.requestFocus();
            limpiar();
            deshabilitar();
        } catch (Exception e) {
            System.out.println("View.View_Producto.jButton8ActionPerformed()" + e);
        }

    }//GEN-LAST:event_jButton8ActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        // TODO add your handling code here:
        if (txt_id_cod_producto.getText().length() == 0) {
            Function_ShowMessageDialog.ShowMessageDialogvalidacion(rootPane, "ERROR AL ELIMINAR");
            return;
        }
        if (producto.delete_Productos(txt_id_cod_producto.getText()).equals("exito")) {

        } else {
            Function_ShowMessageDialog.ShowMessageDialogvalidacion(rootPane, "ERROR AL ELIMINAR DE BASE DE DATOS");
        }
        txt_cod_barra.setEnabled(true);
        txt_cod_barra.setText("");
        txt_cod_barra.requestFocus();
        limpiar();
        deshabilitar();

    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void tabla_productos_presentacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_productos_presentacionKeyPressed
        // TODO add your handling code here:
        /* if ((evt.getKeyCode() == KeyEvent.VK_F4)) {
            producto.add_presentacion(jTable1, Dialog_tipo_unidad_tabla, tabla_tipo_unidad_tabla, txt_descripcion.getText(), txtdiscripcion_corta.getText());
        }*/
 /*  if (evt.getKeyCode()==KeyEvent.VK_DOWN||evt.getKeyCode()==KeyEvent.VK_UP) {
                int fila=this.tabla_producto.getSelectedRow(); 
            //this.txtiddetalles.setText(jtableproforma.getValueAt(fila, 1).toString());
           
                     //   tabla_producto.changeSelection();
                  
           // this.txtcolor.setText(jtableproforma.getValueAt(fila,5 ).toString());
    } */
    }//GEN-LAST:event_tabla_productos_presentacionKeyPressed

    private void txt_cod_sunatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cod_sunatKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cod_sunatKeyPressed

    private void txt_cod_sunatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cod_sunatKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cod_sunatKeyReleased

    private void btn_producto_sunatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_producto_sunatActionPerformed
        // TODO add your handling code here:
        producto.tabla_producto_suant(tabla_producto_sunat);
        Dimension desktopSize = jPanel1.getSize();
        Dimension FrameSize = Dialog_clasificacion_producto.getSize();
        Dialog_tipo_producto_sunat.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        Dialog_tipo_producto_sunat.setVisible(true);

    }//GEN-LAST:event_btn_producto_sunatActionPerformed

    private void tabla_producto_sunatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_producto_sunatMouseClicked
        // TODO add your handling code here:
        int fila = tabla_producto_sunat.getSelectedRow();
        txt_id_sunat.setText((tabla_producto_sunat.getValueAt(fila, 0).toString()));
        txt_cod_sunat.setText((tabla_producto_sunat.getValueAt(fila, 1).toString()));
        txt_descrip_sunat.setText((tabla_producto_sunat.getValueAt(fila, 2).toString()));
        Dialog_tipo_producto_sunat.dispose();
    }//GEN-LAST:event_tabla_producto_sunatMouseClicked

    private void btn_monedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_monedaActionPerformed
        // TODO add your handling code here:
        producto.table_tipo_moneda(tabla_tipo_moneda, txt_idmoneda.getText());
        Dimension desktopSize = jPanel1.getSize();
        Dimension FrameSize = Dialog_tipo_moneda.getSize();
        Dialog_tipo_moneda.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        Dialog_tipo_moneda.setVisible(true);
    }//GEN-LAST:event_btn_monedaActionPerformed

    private void btn_familiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_familiaActionPerformed
        // TODO add your handling code here:
        producto.table_familia(tabla_familia, txt_idfamilia.getText());
        Dimension desktopSize = View_Escritorio.desktopPane.getSize();
        Dimension FrameSize = Dialog_familia.getSize();
        Dialog_familia.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        Dialog_familia.setVisible(true);
    }//GEN-LAST:event_btn_familiaActionPerformed

    private void btn_medidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_medidaActionPerformed
        // TODO add your handling code here:
        producto.table_tipo_unidad(tabla_tipo_unidad, txtidunidad.getText());
        Dimension desktopSize = jPanel1.getSize();
        Dimension FrameSize = Dialog_tipo_unidad.getSize();
        Dialog_tipo_unidad.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        Dialog_tipo_unidad.setVisible(true);
    }//GEN-LAST:event_btn_medidaActionPerformed

    private void btn_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarActionPerformed
        // TODO add your handling code here:
        producto.tabla_producto(tabla_producto);
        Dimension desktopSize = jPanel1.getSize();
        Dimension FrameSize = Dialog_producto.getSize();
        Dialog_producto.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        Dialog_producto.setVisible(true);
    }//GEN-LAST:event_btn_buscarActionPerformed

    private void tabla_productoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_productoMouseClicked
        // TODO add your handling code here:
        /* int fila = tabla_producto.rowAtPoint(evt.getPoint());
        txt_cod_barra.setEnabled(false);
        habilitar();
        limpiar();
        txt_descripcion.requestFocus();
        List<Entity_Producto> persona = new Controller_Producto().buscar_cod_barra_producto_presentacion(tabla_producto.getValueAt(fila, 1).toString());
        txt_cod_barra.setText(tabla_producto.getValueAt(fila, 1).toString());
        txt_id_cod_producto.setText(persona.get(0).getProducto_id());
        txt_descripcion.setText(persona.get(0).getProducto_descrip());
        txtdiscripcion_corta.setText(persona.get(0).getProducto_descripcort());
        txt_id_clasificacion_producto.setText(persona.get(0).getProducto_clasifi());
        txt_idcladificacion_producto.setText(persona.get(0).getProducto_clasifi_cod());
        txt_descr_clasificacion_producto.setText(persona.get(0).getProducto_clasifi_desc());
        txt_id_moneda.setText(persona.get(0).getProducto_moneda());
        txt_idmoneda.setText(persona.get(0).getProducto_moneda_cod());
        txt_descrmoenda.setText(persona.get(0).getProducto_moneda_decs());
        txt_id_familia.setText(persona.get(0).getProducto_familia());
        txt_idfamilia.setText(persona.get(0).getProducto_familia_cod());
        txt_descrfamilia.setText(persona.get(0).getProducto_familia_desc());
        txt_id_medida.setText(persona.get(0).getProducto_medida());
        txtidunidad.setText(persona.get(0).getProducto_medida_cod());
        txt_descrunidad.setText(persona.get(0).getProducto_medida_desc());
        txtobservacion.setText(persona.get(0).getProducto_observacion());
        box_estado.setSelected(persona.get(0).getProducto_estado());
        txt_cod_sunat.setText(persona.get(0).getSunat_cod());
        txt_descrip_sunat.setText(persona.get(0).getSunat_descrip());
        txt_id_sunat.setText(persona.get(0).getSunat_id());
        cbx_tipoafectacion.getModel().setSelectedItem(new Entity_Tipo_afectacion(persona.get(0).getProducto_tipo_afect(), persona.get(0).getProducto_tipo_afect_cod(), persona.get(0).getProducto_tipo_afect_desc(), persona.get(0).getProducto_valor()));
        producto.presentacion(tabla_productos_presentacion, Dialog_tipo_unidad_tabla, tabla_tipo_unidad_tabla, txt_id_cod_producto.getText());*/
    }//GEN-LAST:event_tabla_productoMouseClicked

    private void txt_cod_barraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cod_barraKeyPressed
        // TODO add your handling code here:
        if ((evt.getKeyCode() == KeyEvent.VK_ENTER)) {
            txt_cod_barra.setEnabled(false);
            String valor = producto.buscar_cod_barra(txt_cod_barra.getText());
            //txt_cod_barra.selectAll();
            habilitar();
            limpiar();
            if (valor.equals("0")) {

                txt_descripcion.requestFocus();
                buscar(txt_cod_barra.getText());
                producto.presentacion(tabla_productos_presentacion, Dialog_tipo_unidad_tabla, tabla_tipo_unidad_tabla, txt_id_cod_producto.getText());

            } else {
                txt_id_cod_producto.setText(valor);
                producto.presentacion(tabla_productos_presentacion, Dialog_tipo_unidad_tabla, tabla_tipo_unidad_tabla, txt_id_cod_producto.getText());

            }
            txt_descripcion.requestFocus();
        }

    }//GEN-LAST:event_txt_cod_barraKeyPressed

    private void txt_producto_valorCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txt_producto_valorCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_producto_valorCaretUpdate

    private void txt_producto_valorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_producto_valorKeyPressed
        // TODO add your handling code here:
        if ((evt.getKeyCode() == KeyEvent.VK_ENTER)) {
            box_estado.requestFocus();
        }
    }//GEN-LAST:event_txt_producto_valorKeyPressed

    private void txt_producto_valorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_producto_valorKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_producto_valorKeyReleased

    private void txt_producto_valorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_producto_valorKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_producto_valorKeyTyped

    private void cbx_tipoafectacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbx_tipoafectacionItemStateChanged
        // TODO add your handling code here:
        txt_producto_valor.setText("" + ((Entity_Tipo_afectacion) cbx_tipoafectacion.getSelectedItem()).getAfectacion_valor());

    }//GEN-LAST:event_cbx_tipoafectacionItemStateChanged

    private void box_estadoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_box_estadoKeyPressed
        // TODO add your handling code here:
        if ((evt.getKeyCode() == KeyEvent.VK_ENTER)) {
            btn_agregar_fila.requestFocus();
        }
    }//GEN-LAST:event_box_estadoKeyPressed

    private void btn_agregar_filaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btn_agregar_filaKeyPressed
        // TODO add your handling code here:
        if ((evt.getKeyCode() == KeyEvent.VK_ENTER)) {
            btn_agregar_fila.doClick();
            tabla_productos_presentacion.editCellAt(0, 3);
            Component aComp1 = tabla_productos_presentacion.getEditorComponent();
            aComp1.requestFocus();
        }
    }//GEN-LAST:event_btn_agregar_filaKeyPressed

    private void btn_buscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscar1ActionPerformed
        // TODO add your handling code here:

        if (txt_id_cod_producto.getText().length() == 0) {
            Function_ShowMessageDialog.ShowMessageDialogvalidacion(rootPane, "SELECCIONE PRODUCTO");
            return;
        }
        String entradaUsuario = JOptionPane.showInputDialog("INTRODUZCA NUEVO CODIGO DE BARRA:");
        if (producto.update_cod_Productos(entradaUsuario, txt_id_cod_producto.getText()).equals("exito")) {
            txt_cod_barra.setText(entradaUsuario);
        } else {
            Function_ShowMessageDialog.ShowMessageDialogvalidacion(rootPane, "ERROR AL INGRESAR NUEVO CODIGO");
        }

    }//GEN-LAST:event_btn_buscar1ActionPerformed

    private void txt_buscar_tabla_productosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_buscar_tabla_productosKeyReleased
        // TODO add your handling code here:
        filtro_producto(txt_buscar_tabla_productos.getText().toUpperCase(), tabla_producto);
    }//GEN-LAST:event_txt_buscar_tabla_productosKeyReleased

    private void tabla_productoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_productoKeyPressed
        // TODO add your handling code here:
        if ((evt.getKeyCode() == KeyEvent.VK_ENTER)) {
            txt_cod_barra.setEnabled(false);
            
            habilitar();
            limpiar();
            buscar(tabla_producto.getValueAt(tabla_producto.getSelectedRow(), 1).toString());
            txt_descripcion.requestFocus();
            
            producto.presentacion(tabla_productos_presentacion, Dialog_tipo_unidad_tabla, tabla_tipo_unidad_tabla, txt_id_cod_producto.getText());
            Dialog_producto.dispose();
        }
    }//GEN-LAST:event_tabla_productoKeyPressed

    private void txtdiscripcion_cortaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdiscripcion_cortaKeyTyped
        // TODO add your handling code here:

        if (txtdiscripcion_corta.getText().length() >= 35) {
            evt.consume();
        }
        /* if ((evt.getKeyCode() == KeyEvent.VK_SPACE)) {
            txtdiscripcion_corta.setText(""+txtdiscripcion_corta.getText()+"-");
            System.out.println("View.View_Producto.txtdiscripcion_cortaKeyTyped()");
        }*/


    }//GEN-LAST:event_txtdiscripcion_cortaKeyTyped

    private void txt_busqueda_cod_sunatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busqueda_cod_sunatKeyReleased
        // TODO add your handling code here:
        filtro_producto(txt_busqueda_cod_sunat.getText().toUpperCase(), tabla_producto_sunat);
    }//GEN-LAST:event_txt_busqueda_cod_sunatKeyReleased

    private void tabla_productos_presentacionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_productos_presentacionKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_tabla_productos_presentacionKeyReleased

    private void txt_id_cod_productoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_id_cod_productoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_id_cod_productoActionPerformed

    private void cbx_tipoembalajeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbx_tipoembalajeItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_tipoembalajeItemStateChanged

    private void cbx_tipoembalajeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_tipoembalajeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_tipoembalajeKeyPressed
    public void buscarTexto() {

        //hilit.removeAllHighlights();
    }

   void buscar(String cod){
       List<Entity_Producto> persona = new Controller_Producto().buscar_cod_barra_producto_presentacion(cod);
                txt_id_cod_producto.setText(persona.get(0).getProducto_id());
                txt_cod_barra.setText(persona.get(0).getProducto_cod());
                txt_descripcion.setText(persona.get(0).getProducto_descrip());
                txtdiscripcion_corta.setText(persona.get(0).getProducto_descripcort());
                txt_id_clasificacion_producto.setText(persona.get(0).getProducto_clasifi());
                txt_idcladificacion_producto.setText(persona.get(0).getProducto_clasifi_cod());
                txt_descr_clasificacion_producto.setText(persona.get(0).getProducto_clasifi_desc());
                txt_id_moneda.setText(persona.get(0).getProducto_moneda());
                txt_idmoneda.setText(persona.get(0).getProducto_moneda_cod());
                txt_descrmoenda.setText(persona.get(0).getProducto_moneda_decs());
                txt_id_familia.setText(persona.get(0).getProducto_familia());
                txt_idfamilia.setText(persona.get(0).getProducto_familia_cod());
                txt_descrfamilia.setText(persona.get(0).getProducto_familia_desc());
                txt_id_medida.setText(persona.get(0).getProducto_medida());
                txtidunidad.setText(persona.get(0).getProducto_medida_cod());
                txt_descrunidad.setText(persona.get(0).getProducto_medida_desc());
                txtobservacion.setText(persona.get(0).getProducto_observacion());
                box_estado.setSelected(persona.get(0).getProducto_estado());
                txt_cod_sunat.setText(persona.get(0).getSunat_cod());
                txt_descrip_sunat.setText(persona.get(0).getSunat_descrip());
                txt_id_sunat.setText(persona.get(0).getSunat_id());
                txt_cantidad.setText(persona.get(0).getProducto_cantidad());
                cbx_tipoembalaje.getModel().setSelectedItem(new Entity_Tipo_Embalaje(persona.get(0).getEmbalaje_id(),
                        persona.get(0).getEmbalaje_cod(), persona.get(0).getEmbajale_descr()));
                cbx_tipoafectacion.getModel().setSelectedItem(new Entity_Tipo_afectacion(persona.get(0).getProducto_tipo_afect(), persona.get(0).getProducto_tipo_afect_cod(), persona.get(0).getProducto_tipo_afect_desc(), persona.get(0).getProducto_tipo_valor(),persona.get(0).getProducto_afectacion_cod_valor()));
       
   
   
   }

    private void filtro_producto(String consulta, JTable jtableBuscar) {
        DefaultTableModel dm = (DefaultTableModel) jtableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(dm);
        jtableBuscar.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(consulta));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Dialog_clasificacion_producto;
    private javax.swing.JDialog Dialog_familia;
    private javax.swing.JDialog Dialog_producto;
    private javax.swing.JDialog Dialog_tipo_moneda;
    private javax.swing.JDialog Dialog_tipo_producto_sunat;
    private javax.swing.JDialog Dialog_tipo_unidad;
    private javax.swing.JDialog Dialog_tipo_unidad_tabla;
    private javax.swing.JCheckBox box_estado;
    public static javax.swing.JButton btn_agregar_fila;
    private javax.swing.JButton btn_buscar;
    private javax.swing.JButton btn_buscar1;
    private javax.swing.JButton btn_clasificacion_producto;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_familia;
    private javax.swing.JButton btn_guardar;
    private javax.swing.JButton btn_medida;
    private javax.swing.JButton btn_moneda;
    private javax.swing.JButton btn_producto_sunat;
    private javax.swing.JComboBox<Entity_Tipo_afectacion> cbx_tipoafectacion;
    private javax.swing.JComboBox<Entity_Tipo_Embalaje> cbx_tipoembalaje;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tabla_familia;
    private javax.swing.JTable tabla_producto;
    private javax.swing.JTable tabla_producto_sunat;
    private javax.swing.JTable tabla_productos_presentacion;
    private javax.swing.JTable tabla_tipo_moneda;
    private javax.swing.JTable tabla_tipo_unidad;
    private javax.swing.JTable tabla_tipo_unidad_tabla;
    private javax.swing.JTable table_descripcion_producto;
    private javax.swing.JTextField txt_buscar_tabla_productos;
    private javax.swing.JTextField txt_busqueda_cod_sunat;
    private javax.swing.JTextField txt_cantidad;
    private javax.swing.JTextField txt_cod_barra;
    private javax.swing.JTextField txt_cod_sunat;
    private javax.swing.JTextField txt_descr_clasificacion_producto;
    private javax.swing.JTextField txt_descrfamilia;
    private javax.swing.JTextField txt_descrip_sunat;
    private javax.swing.JEditorPane txt_descripcion;
    private javax.swing.JTextField txt_descrmoenda;
    private javax.swing.JTextField txt_descrunidad;
    private javax.swing.JTextField txt_id_clasificacion_producto;
    private javax.swing.JTextField txt_id_cod_producto;
    private javax.swing.JTextField txt_id_familia;
    private javax.swing.JTextField txt_id_medida;
    private javax.swing.JTextField txt_id_moneda;
    private javax.swing.JTextField txt_id_sunat;
    private javax.swing.JTextField txt_idcladificacion_producto;
    private javax.swing.JTextField txt_idfamilia;
    private javax.swing.JTextField txt_idmoneda;
    private javax.swing.JTextField txt_producto_valor;
    private javax.swing.JTextField txtdiscripcion_corta;
    private javax.swing.JTextField txtidunidad;
    private javax.swing.JTextField txtobservacion;
    // End of variables declaration//GEN-END:variables
}
