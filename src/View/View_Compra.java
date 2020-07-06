/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.Controller_compra;
import Controller.Controller_compra;
import Entity.Entity_Almacen;
import Entity.Entity_Compra;
import Entity.Entity_Compra_Producto;
import Entity.Entity_Concepto;
import Entity.Entity_Documento;
import Entity.Entity_Moneda;
import Entity.Entity_Persona;
import Entity.Entity_Producto_Presentacion;
import Entity.Entity_Tipo_Comprobante;
import Entity.Entity_Tipo_afectacion;
import Function.Function_Component;
import Function.Function_Key_Event;
import Function.Function_ShowMessageDialog;
import static Web_Service.Service_RUC_DNI.peticionHttpGet;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author memo
 */
public class View_Compra extends javax.swing.JInternalFrame {

    Entity_Compra compra;
    Entity_Persona persona;
    Entity_Compra_Producto detalle_compra;
    /**
     * Creates new form View_Ingreso_Productos
     */
    Controller_compra almacen = new Controller_compra();

    public View_Compra() {
        initComponents();
        Function_Component.JTable(table_productos_compra);
        almacen.compra_detalle(table_productos_compra, Dialog_producto, tabla_productos, Dialog_afectacion, tabla_afectacion, TITLE_PROPERTY);
        almacen.cbxAfectacion(cbx_tipoafectacion);
        almacen.cbx_tipo_comprobante(cbx_tipo_comprobante);
        almacen.cbx_tipo_moneda(cbx_moneda);
        almacen.cbx_almacen(cbx_almacen);
        almacen.cbx_tipo_documento(cbx_documento);
        almacen.cbx_tipo_documento(cbx_compra_documento);
        //almacen.cbxConcepto(cbx_concepto);
        Date date = new Date();
        date_fecha_contable.setDate(date);
        date_fecha_emision.setDate(date);
        date_fecha_ingreso_almacen.setDate(date);
        date_fecha_vencimiento.setDate(date);

        Function_Key_Event.JDateChooser(date_fecha_emision, date_fecha_ingreso_almacen);
        Function_Key_Event.JDateChooser(date_fecha_ingreso_almacen, date_fecha_vencimiento);
        Function_Key_Event.JDateChooser(date_fecha_vencimiento, date_fecha_contable);
        Function_Key_Event.JDateChooser_JComboBox(date_fecha_contable, cbx_compra_documento);
        // date_fecha_vencimiento.getDateFormatString();
        Function_Key_Event.Validar_Mayuscula(txt_busqueda_compra_cod);
        Function_Key_Event.Validar_Mayuscula(txt_busqueda_compra);
        Function_Key_Event.Validar_Mayuscula(txt_serie);
        Function_Key_Event.Validar_numeros(txt_numero);
        Function_Key_Event.Validar_numeros(txt_tipo_cambio);
        Function_Key_Event.Validar_numeros(txt_compra_DNI_RUC);
        Function_Key_Event.Validar_numeros(txt_busqueda);
        Function_Key_Event.Validar_numeros(txt_ruc);
        Function_Key_Event.Validar_numeros(txt_dni);
        Function_Key_Event.Validar_Mayuscula(txt_compra_denominacion);
        Function_Key_Event.Validar_Mayuscula(txt_compra_denominacion);
        Function_Key_Event.Validar_Mayuscula(txt_rason_social);
        Function_Key_Event.Validar_Mayuscula(txt_razon_comercial);
        Function_Key_Event.Validar_Mayuscula(txt_direccion);
        Function_Key_Event.Validar_Mayuscula(txt_compra_direccion);
        Function_Key_Event.Validar_Mayuscula(txt_glosa);

        Function_Component.JComboBox(cbx_tipo_comprobante);
        Function_Component.JComboBox(cbx_moneda);
        Function_Component.JComboBox(cbx_tipoafectacion);
        Function_Component.JComboBox(cbx_compra_documento);
        Function_Component.JComboBox(cbx_almacen);
        Function_Component.JComboBox(cbx_documento);
//        Function_Component.JComboBox(cbx_concepto);
        Function_Component.JCheckBox(check_estado_comprobante);
        Function_Component.JCheckBox(check_estado_almacen);

        Function_Component.JDateChooser(date_fecha_contable);
        Function_Component.JDateChooser(date_fecha_emision);
        Function_Component.JDateChooser(date_fecha_ingreso_almacen);
        Function_Component.JDateChooser(date_fecha_vencimiento);

        //new JScrollPane(table_productos_compra, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        Function_Component.JButton(btn_buscar_cod_compra);
        Function_Component.JButton(btn_registrar);
        Function_Component.JButton(btn_eliminar);
        Function_Component.JButton(btn_nuevo);
        Function_Component.JButton(btn_cancelar);

        /*Function_Key_Event.Validar_Mayuscula(txt_busqueda_compra);
        Function_Key_Event.Validar_Mayuscula(txt_busqueda_compra);
        Function_Key_Event.Validar_Mayuscula(txt_busqueda_compra);
        Function_Key_Event.Validar_Mayuscula(txt_busqueda_compra);
        Function_Key_Event.Validar_Mayuscula(txt_busqueda_compra);*/
        Function_Key_Event.Validar_numeros(txt_ingrese_desct_global);
        Function_Key_Event.Validar_numeros(txt_descuento_global);
        Function_Key_Event.Validar_numeros(txt_descuento_iten);
        Function_Key_Event.Validar_numeros(txt_gravada);
        Function_Key_Event.Validar_numeros(txt_exonerada);
        Function_Key_Event.Validar_numeros(txt_inafecta);
        Function_Key_Event.Validar_numeros(txt_igv);
        Function_Key_Event.Validar_numeros(txt_gratuito);
        Function_Key_Event.Validar_numeros(txt_otras_cargas);
        Function_Key_Event.Validar_numeros(txt_total);

        deshabilitar_compra();

        btn_registrar.setEnabled(false);
        btn_cancelar.setEnabled(false);
        btn_eliminar.setEnabled(false);
        btn_nuevo.setEnabled(true);

        txt_id_compra.setVisible(false);
        txt_id_proveedor.setVisible(false);
        txt_id_persona.setVisible(false);

        // date_fecha_vencimiento.set;
        //  date_fecha_vencimiento=new JDateChooser("dd/MM/yyyy", "##/##/#####", '_');
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Dialog_afectacion = new javax.swing.JDialog();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla_afectacion = new javax.swing.JTable();
        Dialog_producto = new javax.swing.JDialog();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        txt_busqueda_productos = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_productos = new javax.swing.JTable();
        Dialog_Proveedor = new javax.swing.JDialog();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        txt_busqueda = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        btn_buscar_ruc_dni = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        txt_rason_social = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txt_razon_comercial = new javax.swing.JTextField();
        txt_direccion = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txt_ruc = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txt_dni = new javax.swing.JTextField();
        cbx_documento = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        txt_id_persona = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        btn_registrar_proveedor = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        Dialog_compra = new javax.swing.JDialog();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabla_compra = new javax.swing.JTable();
        jPanel23 = new javax.swing.JPanel();
        txt_busqueda_compra = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_busqueda_compra_cod = new javax.swing.JTextField();
        btn_buscar_cod_compra = new javax.swing.JButton();
        txt_id_compra = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        cbx_tipo_comprobante = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txt_serie = new javax.swing.JTextField();
        txt_numero = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        date_fecha_emision = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        date_fecha_vencimiento = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        date_fecha_ingreso_almacen = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        date_fecha_contable = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        cbx_moneda = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        cbx_tipoafectacion = new javax.swing.JComboBox<>();
        txt_tipo_cambio = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        txt_compra_DNI_RUC = new javax.swing.JTextField();
        txt_id_proveedor = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_compra_denominacion = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txt_glosa = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        cbx_compra_documento = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        cbx_almacen = new javax.swing.JComboBox<>();
        check_estado_almacen = new javax.swing.JCheckBox();
        jLabel13 = new javax.swing.JLabel();
        check_estado_comprobante = new javax.swing.JCheckBox();
        jLabel30 = new javax.swing.JLabel();
        txt_compra_direccion = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_productos_compra = new javax.swing.JTable();
        btn_agregar_fila = new javax.swing.JButton();
        btn_calcular = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        btn_nuevo = new javax.swing.JButton();
        btn_registrar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        txt_gravada = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txt_exonerada = new javax.swing.JTextField();
        txt_inafecta = new javax.swing.JTextField();
        txt_total = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        GRAVADA = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txt_igv = new javax.swing.JTextField();
        GRAVADA1 = new javax.swing.JLabel();
        txt_ingrese_desct_global = new javax.swing.JTextField();
        GRAVADA2 = new javax.swing.JLabel();
        txt_descuento_iten = new javax.swing.JTextField();
        GRAVADA3 = new javax.swing.JLabel();
        txt_descuento_global = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txt_gratuito = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txt_otras_cargas = new javax.swing.JTextField();

        Dialog_afectacion.setAutoRequestFocus(false);
        Dialog_afectacion.setMinimumSize(new java.awt.Dimension(550, 360));
        Dialog_afectacion.setResizable(false);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        tabla_afectacion.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_afectacion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_afectacionMouseClicked(evt);
            }
        });
        tabla_afectacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_afectacionKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(tabla_afectacion);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout Dialog_afectacionLayout = new javax.swing.GroupLayout(Dialog_afectacion.getContentPane());
        Dialog_afectacion.getContentPane().setLayout(Dialog_afectacionLayout);
        Dialog_afectacionLayout.setHorizontalGroup(
            Dialog_afectacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dialog_afectacionLayout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        Dialog_afectacionLayout.setVerticalGroup(
            Dialog_afectacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dialog_afectacionLayout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        Dialog_producto.setAutoRequestFocus(false);
        Dialog_producto.setMinimumSize(new java.awt.Dimension(754, 446));
        Dialog_producto.setResizable(false);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        txt_busqueda_productos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_busqueda_productosKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txt_busqueda_productos)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(txt_busqueda_productos, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        tabla_productos.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_productos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_productosMouseClicked(evt);
            }
        });
        tabla_productos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_productosKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tabla_productos);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Dialog_productoLayout = new javax.swing.GroupLayout(Dialog_producto.getContentPane());
        Dialog_producto.getContentPane().setLayout(Dialog_productoLayout);
        Dialog_productoLayout.setHorizontalGroup(
            Dialog_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dialog_productoLayout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        Dialog_productoLayout.setVerticalGroup(
            Dialog_productoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dialog_productoLayout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        Dialog_Proveedor.setAutoRequestFocus(false);
        Dialog_Proveedor.setMinimumSize(new java.awt.Dimension(584, 530));
        Dialog_Proveedor.setModal(true);
        Dialog_Proveedor.setUndecorated(true);
        Dialog_Proveedor.setResizable(false);

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel15.setForeground(new java.awt.Color(255, 255, 255));

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder("Proveedor"));

        txt_busqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_busquedaKeyPressed(evt);
            }
        });

        jLabel15.setText("Ingrese RUC/DNI:");

        btn_buscar_ruc_dni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscar_ruc_dniActionPerformed(evt);
            }
        });

        jLabel16.setText("Razon social/nombre:");

        txt_rason_social.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_rason_socialKeyPressed(evt);
            }
        });

        jLabel17.setText("Razon comecial:");

        txt_razon_comercial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_razon_comercialKeyPressed(evt);
            }
        });

        txt_direccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_direccionKeyPressed(evt);
            }
        });

        jLabel18.setText("Direccion:");

        txt_ruc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_rucKeyPressed(evt);
            }
        });

        jLabel19.setText("RUC:");

        jLabel20.setText("DNI:");

        txt_dni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_dniKeyPressed(evt);
            }
        });

        jLabel21.setText("Tipo de documento:");

        txt_id_persona.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_id_personaKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_rason_social)
                            .addComponent(txt_razon_comercial)
                            .addComponent(txt_direccion)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel16Layout.createSequentialGroup()
                                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txt_busqueda)
                                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btn_buscar_ruc_dni)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txt_id_persona, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_ruc)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_dni)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(120, 120, 120))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbx_documento, javax.swing.GroupLayout.PREFERRED_SIZE, 268, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbx_documento, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_buscar_ruc_dni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_busqueda)
                    .addComponent(txt_id_persona, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                .addGap(13, 13, 13)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_ruc, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_dni)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_rason_social, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_razon_comercial, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txt_direccion, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        btn_registrar_proveedor.setBackground(new java.awt.Color(255, 255, 255));
        btn_registrar_proveedor.setText("REGISTRAR");
        btn_registrar_proveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrar_proveedorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(btn_registrar_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btn_registrar_proveedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        );

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));

        jButton8.setBackground(new java.awt.Color(255, 255, 255));
        jButton8.setText("X");
        jButton8.setOpaque(false);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout Dialog_ProveedorLayout = new javax.swing.GroupLayout(Dialog_Proveedor.getContentPane());
        Dialog_Proveedor.getContentPane().setLayout(Dialog_ProveedorLayout);
        Dialog_ProveedorLayout.setHorizontalGroup(
            Dialog_ProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Dialog_ProveedorLayout.setVerticalGroup(
            Dialog_ProveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dialog_ProveedorLayout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        Dialog_compra.setMinimumSize(new java.awt.Dimension(669, 386));
        Dialog_compra.setModal(true);

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));

        tabla_compra.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_compra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_compraMouseClicked(evt);
            }
        });
        tabla_compra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_compraKeyPressed(evt);
            }
        });
        jScrollPane4.setViewportView(tabla_compra);

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        txt_busqueda_compra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_busqueda_compraKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txt_busqueda_compra)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addComponent(txt_busqueda_compra, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout Dialog_compraLayout = new javax.swing.GroupLayout(Dialog_compra.getContentPane());
        Dialog_compra.getContentPane().setLayout(Dialog_compraLayout);
        Dialog_compraLayout.setHorizontalGroup(
            Dialog_compraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dialog_compraLayout.createSequentialGroup()
                .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        Dialog_compraLayout.setVerticalGroup(
            Dialog_compraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dialog_compraLayout.createSequentialGroup()
                .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("INGRESO DE PRODUCTO");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Comprobante"));

        jLabel1.setText("Ingrese nª de registro de documento :");

        txt_busqueda_compra_cod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_busqueda_compra_codKeyPressed(evt);
            }
        });

        btn_buscar_cod_compra.setText("BUSCAR");
        btn_buscar_cod_compra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscar_cod_compraActionPerformed(evt);
            }
        });

        txt_id_compra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_id_compraKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txt_busqueda_compra_cod, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_buscar_cod_compra)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_id_compra, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(txt_id_compra, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_busqueda_compra_cod)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btn_buscar_cod_compra, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(2, 2, 2))))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de comprobante"));

        cbx_tipo_comprobante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_tipo_comprobanteKeyPressed(evt);
            }
        });

        jLabel3.setText("Tipo de comprobante");

        txt_serie.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_serieKeyPressed(evt);
            }
        });

        txt_numero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_numeroKeyPressed(evt);
            }
        });

        jLabel4.setText("Serie");

        jLabel5.setText("Numero");

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Fecha de registro de compra"));

        jLabel2.setText("Fecha de emision");

        date_fecha_emision.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                date_fecha_emisionKeyPressed(evt);
            }
        });

        jLabel6.setText("fecha de vencimento");

        date_fecha_vencimiento.setAutoscrolls(true);

        jLabel7.setText("Fecha de ingreso a almacen");

        jLabel8.setText("Fecha contable");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel6))
                            .addGap(32, 32, 32))
                        .addComponent(date_fecha_emision, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(date_fecha_vencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(date_fecha_contable, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                    .addComponent(date_fecha_ingreso_almacen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(date_fecha_emision, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(date_fecha_ingreso_almacen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(date_fecha_vencimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(date_fecha_contable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jLabel9.setText("Moneda");

        cbx_moneda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_monedaKeyPressed(evt);
            }
        });

        jLabel14.setText("Tipo afectacion");

        cbx_tipoafectacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_tipoafectacionKeyPressed(evt);
            }
        });

        txt_tipo_cambio.setText("1");
        txt_tipo_cambio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_tipo_cambioKeyPressed(evt);
            }
        });

        jLabel29.setText("Tipo cambio");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbx_tipo_comprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                                    .addComponent(cbx_moneda, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_tipo_cambio, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_serie, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_numero, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16))
                    .addComponent(cbx_tipoafectacion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(3, 3, 3)
                        .addComponent(cbx_tipo_comprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_serie, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_numero, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel29))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbx_moneda, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                            .addComponent(txt_tipo_cambio))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel14)
                .addGap(0, 0, 0)
                .addComponent(cbx_tipoafectacion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos de proveedor"));

        txt_compra_DNI_RUC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_compra_DNI_RUCKeyPressed(evt);
            }
        });

        txt_id_proveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_id_proveedorKeyPressed(evt);
            }
        });

        jLabel10.setText("Ingrese DNI/RUC:");

        txt_compra_denominacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_compra_denominacionKeyPressed(evt);
            }
        });

        jLabel11.setText("Denominacion:");

        jLabel12.setText("Glosa:");

        txt_glosa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_glosaKeyPressed(evt);
            }
        });

        jButton6.setText("+");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        cbx_compra_documento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_compra_documentoKeyPressed(evt);
            }
        });

        jLabel22.setText("Tipo_documento");

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos almacen"));

        cbx_almacen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_almacenKeyPressed(evt);
            }
        });

        check_estado_almacen.setSelected(true);
        check_estado_almacen.setText("Ingresar a almacen ");
        check_estado_almacen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                check_estado_almacenKeyPressed(evt);
            }
        });

        jLabel13.setText("Almacen:");

        check_estado_comprobante.setSelected(true);
        check_estado_comprobante.setText("Estado de comprobante");
        check_estado_comprobante.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                check_estado_comprobanteKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(check_estado_comprobante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(check_estado_almacen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel19Layout.createSequentialGroup()
                                .addComponent(cbx_almacen, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbx_almacen, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(check_estado_comprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(check_estado_almacen, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jLabel30.setText("Direccion:");

        txt_compra_direccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_compra_direccionKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_compra_direccion)
                    .addComponent(txt_compra_denominacion)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(cbx_compra_documento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(txt_compra_DNI_RUC, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_id_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6))
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txt_glosa)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbx_compra_documento, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_compra_DNI_RUC, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_id_proveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(3, 3, 3)
                        .addComponent(jLabel11)
                        .addGap(0, 0, 0)
                        .addComponent(txt_compra_denominacion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel30)
                        .addGap(0, 0, 0)
                        .addComponent(txt_compra_direccion, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jLabel12)
                        .addGap(0, 0, 0)
                        .addComponent(txt_glosa, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(0, 2, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabla productos"));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        table_productos_compra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        table_productos_compra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                table_productos_compraMouseClicked(evt);
            }
        });
        table_productos_compra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                table_productos_compraKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(table_productos_compra);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
        );

        btn_agregar_fila.setText("+");
        btn_agregar_fila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregar_filaActionPerformed(evt);
            }
        });

        btn_calcular.setText("CAL");
        btn_calcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_calcularActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_agregar_fila, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_calcular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(53, 53, 53)))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btn_agregar_fila)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_calcular)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btn_nuevo.setText("NUEVO");
        btn_nuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevoActionPerformed(evt);
            }
        });

        btn_registrar.setText("REGISTRAR");
        btn_registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrarActionPerformed(evt);
            }
        });

        btn_eliminar.setText("ELIMINAR");
        btn_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminarActionPerformed(evt);
            }
        });

        btn_cancelar.setText("CANCELAR");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addComponent(btn_registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_registrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_nuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_eliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setBorder(javax.swing.BorderFactory.createTitledBorder("Monto total"));

        txt_gravada.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_gravada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_gravadaKeyPressed(evt);
            }
        });

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setText("TOTAL:");

        txt_exonerada.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_exonerada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_exoneradaKeyPressed(evt);
            }
        });

        txt_inafecta.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_inafecta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_inafectaActionPerformed(evt);
            }
        });
        txt_inafecta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_inafectaKeyPressed(evt);
            }
        });

        txt_total.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_total.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_totalKeyPressed(evt);
            }
        });

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("INAFECTA:");

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel25.setText("EXONERADA:");

        GRAVADA.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        GRAVADA.setText("GRAVADA:");

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel26.setText("IGV:");

        txt_igv.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_igv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_igvKeyPressed(evt);
            }
        });

        GRAVADA1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        GRAVADA1.setText("INGRESE DESC GLOBAL:");

        txt_ingrese_desct_global.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_ingrese_desct_global.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_ingrese_desct_globalKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_ingrese_desct_globalKeyReleased(evt);
            }
        });

        GRAVADA2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        GRAVADA2.setText("DESCUENTO ITEN:");

        txt_descuento_iten.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_descuento_iten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_descuento_itenActionPerformed(evt);
            }
        });
        txt_descuento_iten.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_descuento_itenKeyPressed(evt);
            }
        });

        GRAVADA3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        GRAVADA3.setText("DESCUENTO GLOBAL:");

        txt_descuento_global.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_descuento_global.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_descuento_globalActionPerformed(evt);
            }
        });
        txt_descuento_global.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_descuento_globalKeyPressed(evt);
            }
        });

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel27.setText("GRATUITA:");

        txt_gratuito.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_gratuito.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_gratuitoKeyPressed(evt);
            }
        });

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel28.setText("OTROS CARGA:");

        txt_otras_cargas.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txt_otras_cargas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_otras_cargasKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_otras_cargasKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)))
                        .addGap(0, 0, 0)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_total, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                            .addComponent(txt_otras_cargas)
                            .addComponent(txt_gratuito)
                            .addComponent(txt_igv)
                            .addComponent(txt_inafecta)
                            .addComponent(txt_exonerada, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(GRAVADA3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(GRAVADA2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(GRAVADA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(GRAVADA1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_descuento_iten, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_gravada, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txt_ingrese_desct_global, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_descuento_global, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(4, 4, 4))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_ingrese_desct_global)
                    .addComponent(GRAVADA1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_descuento_global)
                    .addComponent(GRAVADA3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_descuento_iten)
                    .addComponent(GRAVADA2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_gravada)
                    .addComponent(GRAVADA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_exonerada))
                .addGap(0, 0, 0)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_inafecta))
                .addGap(1, 1, 1)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_igv))
                .addGap(0, 0, 0)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_gratuito))
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_otras_cargas))
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txt_total)))
        );

        jLabel23.getAccessibleContext().setAccessibleName("50");
        jLabel24.getAccessibleContext().setAccessibleName("50");
        jLabel25.getAccessibleContext().setAccessibleName("50");
        GRAVADA.getAccessibleContext().setAccessibleName("50");
        jLabel26.getAccessibleContext().setAccessibleName("50");
        GRAVADA1.getAccessibleContext().setAccessibleName("50");
        GRAVADA2.getAccessibleContext().setAccessibleName("50");
        GRAVADA3.getAccessibleContext().setAccessibleName("50");
        jLabel27.getAccessibleContext().setAccessibleName("50");
        jLabel28.getAccessibleContext().setAccessibleName("50");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_busqueda_compra_codKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busqueda_compra_codKeyPressed
        // TODO add your handling code here:
        try {

            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

                List<Entity_Compra> compra = new Controller_compra().buscar_compra(txt_busqueda_compra_cod.getText());
                if (compra.size() == 0) {
                    deshabilitar_compra();
                    nuevo_compra();
                    almacen.compra_detalle(table_productos_compra, Dialog_producto, tabla_productos, Dialog_afectacion, tabla_afectacion, TITLE_PROPERTY);

                }

                Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(compra.get(0).getCompra_fecha_emis());
                Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(compra.get(0).getCompra_fecha_ingreso_alm());
                Date date3 = new SimpleDateFormat("yyyy-MM-dd").parse(compra.get(0).getCompra_fecha_vencimiento());
                Date date4 = new SimpleDateFormat("yyyy-MM-dd").parse(compra.get(0).getCompra_fecha_contable());
                txt_id_compra.setText(compra.get(0).getCompra_id());
                cbx_tipo_comprobante.getModel().setSelectedItem(new Entity_Tipo_Comprobante(compra.get(0).getComprobante_id(),
                        compra.get(0).getComprobante_cod(), compra.get(0).getComprobante_descrip()));
                txt_serie.setText(compra.get(0).getCompra_serie());
                txt_numero.setText(compra.get(0).getCompra_numero());
                cbx_moneda.getModel().setSelectedItem(new Entity_Moneda(compra.get(0).getMoneda_id(),
                        compra.get(0).getMoneda_cod(), compra.get(0).getMoneda_descrip()));
                cbx_tipoafectacion.getModel().setSelectedItem(new Entity_Tipo_afectacion(compra.get(0).getAfectacion_id(),
                        compra.get(0).getAfectacion_cod(), compra.get(0).getAfectacion_descr(), compra.get(0).getAfectacion_valor(), compra.get(0).getAfectacion_cod_valor()));

                date_fecha_emision.setDate(date1);
                date_fecha_ingreso_almacen.setDate(date2);
                date_fecha_vencimiento.setDate(date3);
                date_fecha_contable.setDate(date4);
                cbx_compra_documento.getModel().setSelectedItem(new Entity_Documento(compra.get(0).getDocumento_id(),
                        compra.get(0).getDocumento_cod(), compra.get(0).getDocumento_descrip(), compra.get(0).getDocumento_url()));
                txt_id_proveedor.setText(compra.get(0).getPersona_id());
                if (compra.get(0).getDocumento_cod().equals("6")) {
                    txt_compra_DNI_RUC.setText(compra.get(0).getPersona_ruc());
                } else {
                    txt_compra_DNI_RUC.setText(compra.get(0).getPersona_dni());
                }

                txt_compra_denominacion.setText(compra.get(0).getPersona_razon_social());
                txt_compra_direccion.setText(compra.get(0).getPersona_direccion());
                txt_glosa.setText(compra.get(0).getCompra_glosa());
                cbx_almacen.getModel().setSelectedItem(new Entity_Almacen(compra.get(0).getAlmacen_id(),
                        compra.get(0).getAlmacen_cod(), compra.get(0).getAlmacen_descrip()));
                check_estado_comprobante.setSelected(compra.get(0).getCompra_estado());
                check_estado_almacen.setSelected(compra.get(0).getCompra_estado_almacen());

                txt_ingrese_desct_global.setText(compra.get(0).getCompra_ingrese_descuento_global());
                txt_descuento_global.setText(compra.get(0).getCompra_descuento_global());
                txt_descuento_iten.setText(compra.get(0).getCompra_descuento_item());
                txt_gravada.setText(compra.get(0).getCompra_gravada());
                txt_exonerada.setText(compra.get(0).getCompra_exonerada());
                txt_inafecta.setText(compra.get(0).getCompra_inafecta());
                txt_igv.setText(compra.get(0).getCompra_igv());
                txt_gratuito.setText(compra.get(0).getCompra_gratuita());
                txt_otras_cargas.setText(compra.get(0).getCompra_otros_cargos());
                txt_total.setText(compra.get(0).getCompra_total());
                txt_tipo_cambio.setText(compra.get(0).getCompra_cambio());

                almacen.compra_detalle(table_productos_compra, Dialog_producto, tabla_productos, Dialog_afectacion, tabla_afectacion, txt_busqueda_compra_cod.getText());
                habilitar_compra();
                btn_registrar.setEnabled(true);
                btn_cancelar.setEnabled(true);
                btn_eliminar.setEnabled(true);
                btn_nuevo.setEnabled(true);
                /* cbx_compra_documento.getModel().setSelectedItem(new Entity_Documento(compra.get(0).getDocumento_id(),
                    compra.get(0).getDocumento_cod(), compra.get(0).getDocumento_descrip(), compra.get(0).getDocumento_url()));*/
            }
        } catch (Exception e) {
        }


    }//GEN-LAST:event_txt_busqueda_compra_codKeyPressed

    private void txt_serieKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_serieKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_numero.requestFocus();
        }
    }//GEN-LAST:event_txt_serieKeyPressed

    private void txt_numeroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_numeroKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cbx_moneda.requestFocus();
        }
    }//GEN-LAST:event_txt_numeroKeyPressed

    private void txt_compra_DNI_RUCKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_compra_DNI_RUCKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            List<Entity_Persona> persona = new Controller_compra().buscar_persona(txt_compra_DNI_RUC.getText());
            txt_id_proveedor.setText(persona.get(0).getPersona_id());
            txt_compra_denominacion.setText(persona.get(0).getPersona_razon_social());
            txt_compra_direccion.setText(persona.get(0).getPersona_direccion());
            cbx_compra_documento.getModel().setSelectedItem(new Entity_Documento(persona.get(0).getDocumento_id(),
                    persona.get(0).getDocumento_cod(), persona.get(0).getDocumento_descrip(), persona.get(0).getDocumento_url()));
            txt_glosa.requestFocus();
        }


    }//GEN-LAST:event_txt_compra_DNI_RUCKeyPressed

    private void txt_id_proveedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_id_proveedorKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_id_proveedorKeyPressed

    private void txt_compra_denominacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_compra_denominacionKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_compra_denominacionKeyPressed

    private void txt_glosaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_glosaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            cbx_almacen.requestFocus();
        }
    }//GEN-LAST:event_txt_glosaKeyPressed

    private void btn_agregar_filaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregar_filaActionPerformed
        // TODO add your handling code here:
        /*jTable1.editCellAt(2, 3, null);

        //Focus will be rerouted to the editor via this call:
        jTable1.requestFocus();*/
        almacen.add_presentacion(table_productos_compra, Dialog_producto, tabla_productos, Dialog_afectacion, tabla_afectacion,
                ((Entity_Tipo_afectacion) cbx_tipoafectacion.getSelectedItem()).getId(),
                ((Entity_Tipo_afectacion) cbx_tipoafectacion.getSelectedItem()).getDescr(),
                ((Entity_Tipo_afectacion) cbx_tipoafectacion.getSelectedItem()).getAfectacion_valor(),
                ((Entity_Tipo_afectacion) cbx_tipoafectacion.getSelectedItem()).getAfectacion_cod_valor());
    }//GEN-LAST:event_btn_agregar_filaActionPerformed

    private void table_productos_compraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_table_productos_compraMouseClicked
        // TODO add your handling code here:
        almacen.reset_variable();
        int fila = table_productos_compra.rowAtPoint(evt.getPoint());
        int column = table_productos_compra.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / table_productos_compra.getRowHeight();
        if (row < table_productos_compra.getRowCount() && row >= 0 && column < table_productos_compra.getColumnCount() && column >= 0) {
            Object value = table_productos_compra.getValueAt(row, column);
            if (value instanceof JButton) {
                ((JButton) value).doClick();
                JButton boton = (JButton) value;

                if (boton.getName().equals("btneliminar")) {

                    if (table_productos_compra.getSelectedRow() != -1) {
                        /*DefaultTableModel model = (DefaultTableModel) tabla_productos_presentacion.getModel();
                        model.removeRow(tabla_productos_presentacion.getSelectedRow());*/
                        if (table_productos_compra.getValueAt(fila, 1).toString().equals("")) {
                            DefaultTableModel model = (DefaultTableModel) table_productos_compra.getModel();
                            model.removeRow(table_productos_compra.getSelectedRow());
                        } else if (table_productos_compra.getValueAt(fila, 1).toString().equals(null)) {
                            System.out.println("1");
                        } else {
                            almacen.delete_compra_producto(table_productos_compra.getValueAt(table_productos_compra.getSelectedRow(), 1).toString());
                            DefaultTableModel model = (DefaultTableModel) table_productos_compra.getModel();
                            model.removeRow(table_productos_compra.getSelectedRow());
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
        btn_calcular.doClick();
    }//GEN-LAST:event_table_productos_compraMouseClicked

    private void tabla_afectacionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_afectacionMouseClicked
        // TODO add your handling code here:

        table_productos_compra.setValueAt(tabla_afectacion.getValueAt(tabla_afectacion.getSelectedRow(), 0), table_productos_compra.getSelectedRow() + almacen.variable(), 6);
        table_productos_compra.setValueAt(tabla_afectacion.getValueAt(tabla_afectacion.getSelectedRow(), 2), table_productos_compra.getSelectedRow() + almacen.variable(), 7);
        table_productos_compra.setValueAt(tabla_afectacion.getValueAt(tabla_afectacion.getSelectedRow(), 3), table_productos_compra.getSelectedRow() + almacen.variable(), 8);
        table_productos_compra.setValueAt(tabla_afectacion.getValueAt(tabla_afectacion.getSelectedRow(), 4), table_productos_compra.getSelectedRow() + almacen.variable(), 9);
        Dialog_afectacion.dispose();
    }//GEN-LAST:event_tabla_afectacionMouseClicked

    private void tabla_productosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_productosMouseClicked
        // TODO add your handling code here:
        table_productos_compra.setValueAt(tabla_productos.getValueAt(tabla_productos.getSelectedRow(), 1), table_productos_compra.getSelectedRow() + almacen.variable(), 2);
        table_productos_compra.setValueAt(tabla_productos.getValueAt(tabla_productos.getSelectedRow(), 2), table_productos_compra.getSelectedRow() + almacen.variable(), 3);
        table_productos_compra.setValueAt(tabla_productos.getValueAt(tabla_productos.getSelectedRow(), 3), table_productos_compra.getSelectedRow() + almacen.variable(), 4);
        table_productos_compra.setValueAt(tabla_productos.getValueAt(tabla_productos.getSelectedRow(), 4), table_productos_compra.getSelectedRow() + almacen.variable(), 5);
        table_productos_compra.setValueAt(tabla_productos.getValueAt(tabla_productos.getSelectedRow(), 6), table_productos_compra.getSelectedRow() + almacen.variable(), 11);
        Dialog_producto.dispose();
        txt_busqueda_productos.setText("");
    }//GEN-LAST:event_tabla_productosMouseClicked

    private void btn_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoActionPerformed
        // TODO add your handling code here:
        txt_id_compra.setText("");
        nuevo_compra();
        habilitar_compra();
        almacen.compra_detalle(table_productos_compra, Dialog_producto, tabla_productos, Dialog_afectacion, tabla_afectacion, TITLE_PROPERTY);
        cbx_tipo_comprobante.requestFocus();
        btn_registrar.setEnabled(true);
        btn_cancelar.setEnabled(true);
        btn_eliminar.setEnabled(true);
        btn_nuevo.setEnabled(true);
    }//GEN-LAST:event_btn_nuevoActionPerformed

    private void txt_id_compraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_id_compraKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_id_compraKeyPressed

    private void btn_registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrarActionPerformed
        // TODO add your handling code here:
        btn_calcular.doClick();
        if (txt_id_compra.getText().length() >= 1) {

        } else if (txt_id_compra.getText().length() == 0) {
            txt_id_compra.setText(almacen.generar_cod_compra());
        }
        if (txt_id_compra.getText().length() == 0) {
            Function_ShowMessageDialog.ShowMessageDialogvalidacion(rootPane, "CAMPO VACIO");
            return;
        }
        if (txt_serie.getText().length() == 0) {
            Function_ShowMessageDialog.ShowMessageDialogvalidacion(rootPane, "CAMPO VACIO");
            return;
        }
        if (txt_numero.getText().length() == 0) {
            Function_ShowMessageDialog.ShowMessageDialogvalidacion(rootPane, "CAMPO VACIO");
            return;
        }
        if (txt_tipo_cambio.getText().length() == 0) {
            Function_ShowMessageDialog.ShowMessageDialogvalidacion(rootPane, "CAMPO VACIO");
            return;
        }
        if (txt_id_proveedor.getText().length() == 0) {
            Function_ShowMessageDialog.ShowMessageDialogvalidacion(rootPane, "CAMPO VACIO");
            return;
        }
        if (txt_compra_DNI_RUC.getText().length() == 0) {
            Function_ShowMessageDialog.ShowMessageDialogvalidacion(rootPane, "CAMPO VACIO");
            return;
        }

        if (table_productos_compra.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "DEBE CONTENER MAS DE UNA FILA LA TABLA DE PRESENTACION");
            return;

        }

        int tabla = 0;
        table_productos_compra.editCellAt(0, 3);
        Component aComp = table_productos_compra.getEditorComponent();
        aComp.requestFocus();
        for (int j = 0; j < table_productos_compra.getRowCount(); j++) {
            try {

                for (int i = 2; i < table_productos_compra.getColumnCount() - 3; i++) {
                    if (table_productos_compra.getValueAt(j, i).toString() == ("")) {
                        JOptionPane.showMessageDialog(rootPane, "HAY CAMPO VACIO:" + table_productos_compra.getTableHeader().getColumnModel().getColumn(i).getHeaderValue());
                        tabla = tabla + 1;
                        table_productos_compra.editCellAt(j, i);
                        Component aComp1 = table_productos_compra.getEditorComponent();
                        aComp1.requestFocus();
                        break;

                    } else if (table_productos_compra.getValueAt(j, i).toString().length() == 0) {
                        JOptionPane.showMessageDialog(rootPane, "HAY CAMPO VACIO:" + table_productos_compra.getTableHeader().getColumnModel().getColumn(i).getHeaderValue());
                        tabla = tabla + 1;
                        table_productos_compra.editCellAt(j, i);
                        Component aComp1 = table_productos_compra.getEditorComponent();
                        aComp1.requestFocus();
                        break;

                    }

                }
                if (tabla > 0) {
                    break;

                }
            } catch (Exception e) {
            }
        }

        if (tabla > 0) {
            return;

        }

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

        compra = new Entity_Compra(txt_id_compra.getText(), ((Entity_Tipo_Comprobante) cbx_tipo_comprobante.getSelectedItem()).getComprobante_id(),
                txt_serie.getText(), txt_numero.getText(), ((Entity_Moneda) cbx_moneda.getSelectedItem()).getMoneda_id(),
                ((Entity_Tipo_afectacion) cbx_tipoafectacion.getSelectedItem()).getId(), fmt.format(date_fecha_emision.getDate()),
                fmt.format(date_fecha_ingreso_almacen.getDate()), fmt.format(date_fecha_vencimiento.getDate()), fmt.format(date_fecha_contable.getDate()),
                txt_id_proveedor.getText(), txt_glosa.getText(), ((Entity_Almacen) cbx_almacen.getSelectedItem()).getAlmacen_id(), "1", check_estado_comprobante.isSelected(), check_estado_almacen.isSelected(),
                txt_ingrese_desct_global.getText(), txt_descuento_global.getText(), txt_descuento_iten.getText(), txt_gravada.getText(), txt_exonerada.getText(), txt_inafecta.getText(), txt_igv.getText(),
                txt_gratuito.getText(), txt_otras_cargas.getText(), txt_total.getText(), txt_tipo_cambio.getText(), ((Entity_Documento) cbx_compra_documento.getSelectedItem()).getDocumento_id(),"1","");

        if (almacen.Add_compra(compra).equals("exito")) {

            for (int i = 0; i < table_productos_compra.getRowCount(); i++) {
                detalle_compra = new Entity_Compra_Producto(
                        table_productos_compra.getValueAt(i, 1).toString(), txt_id_compra.getText(),
                        table_productos_compra.getValueAt(i, 2).toString(), table_productos_compra.getValueAt(i, 6).toString(),
                        table_productos_compra.getValueAt(i, 10).toString(), table_productos_compra.getValueAt(i, 11).toString(),
                        table_productos_compra.getValueAt(i, 12).toString(), table_productos_compra.getValueAt(i, 13).toString(),
                        table_productos_compra.getValueAt(i, 14).toString(), table_productos_compra.getValueAt(i, 15).toString(),
                        table_productos_compra.getValueAt(i, 16).toString(), table_productos_compra.getValueAt(i, 17).toString(),"");
                almacen.Add_compra_producto_detalle(detalle_compra);
            }

            nuevo_compra();
            habilitar_compra();
            almacen.compra_detalle(table_productos_compra, Dialog_producto, tabla_productos, Dialog_afectacion, tabla_afectacion, TITLE_PROPERTY);
            cbx_tipo_comprobante.requestFocus();
            txt_id_compra.setText("");
            btn_registrar.setEnabled(false);
            btn_cancelar.setEnabled(false);
            btn_eliminar.setEnabled(false);
            btn_nuevo.setEnabled(true);
        } else {
            Function_ShowMessageDialog.ShowMessageDialogvalidacion(null, "FALLO REGISTRO");
        }

    }//GEN-LAST:event_btn_registrarActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        limpiar_proveedor();
        txt_id_persona.setText(almacen.generar_cod_proveedor());
        txt_busqueda.setText("");
        habilitar_proveedor();
        Dimension desktopSize = View_Escritorio.desktopPane.getSize();
        Dimension FrameSize = Dialog_Proveedor.getSize();
        Dialog_Proveedor.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        Dialog_Proveedor.setVisible(true);

    }//GEN-LAST:event_jButton6ActionPerformed

    private void btn_buscar_ruc_dniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscar_ruc_dniActionPerformed
        // TODO add your handling code here:
        limpiar_proveedor();
        String respuesta = "";
        try {
            respuesta = peticionHttpGet(((Entity_Documento) cbx_documento.getSelectedItem()).getDocumento_url() + txt_busqueda.getText());
            JSONArray jsonarray = new JSONArray("[" + respuesta + "]");

            for (int i = 0; i < jsonarray.length(); i++) {
                JSONObject jsonobject = jsonarray.getJSONObject(i);
                if (jsonobject.get("success").equals(true)) {
                    if (txt_busqueda.getText().length() == 8) {
                        txt_dni.setText(txt_busqueda.getText());
                        txt_rason_social.setText(jsonobject.getJSONObject("data").getString("name"));
                        /*System.out.println("" + jsonobject.getJSONObject("data").getString("number"));
                        System.out.println("" + jsonobject.getJSONObject("data").getString("name"));
                        System.out.println("" + jsonobject.getJSONObject("data").getString("names"));
                        System.out.println("" + jsonobject.getJSONObject("data").getString("first_name"));
                        System.out.println("" + jsonobject.getJSONObject("data").getString("last_name"));
                        System.out.println("" + jsonobject.getJSONObject("data").getString("date_of_birthday"));
                        System.out.println("" + jsonobject.getJSONObject("data").getString("sex"));*/
                    } else if (txt_busqueda.getText().length() == 11) {
                        txt_ruc.setText(txt_busqueda.getText());
                        txt_rason_social.setText(jsonobject.getJSONObject("data").getString("name"));
                        txt_direccion.setText(jsonobject.getJSONObject("data").getString("address"));
                        /*System.out.println("" +jsonobject.getJSONObject("data").getString("name") );
                        System.out.println("" + jsonobject.getJSONObject("data").getString("address"));*/

                    } else {

                    }
                } else {
                    System.out.println("" + jsonobject.get("success"));
                }
            }
            // System.out.println(respuesta);
        } catch (Exception e) {
            // Manejar excepción
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_buscar_ruc_dniActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        limpiar_proveedor();
        Dialog_Proveedor.dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void txt_busquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busquedaKeyPressed
        // TODO add your handling code here:

        char cteclap = evt.getKeyChar();
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btn_buscar_ruc_dni.doClick();

        }

    }//GEN-LAST:event_txt_busquedaKeyPressed

    private void txt_id_personaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_id_personaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_id_personaKeyPressed

    private void txt_rucKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_rucKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_dni.requestFocus();
        }
    }//GEN-LAST:event_txt_rucKeyPressed

    private void txt_dniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_dniKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_rason_social.requestFocus();
        }
    }//GEN-LAST:event_txt_dniKeyPressed

    private void txt_rason_socialKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_rason_socialKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_razon_comercial.requestFocus();
        }
    }//GEN-LAST:event_txt_rason_socialKeyPressed

    private void txt_direccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_direccionKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            btn_registrar_proveedor.requestFocus();
        }
    }//GEN-LAST:event_txt_direccionKeyPressed

    private void txt_razon_comercialKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_razon_comercialKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_direccion.requestFocus();
        }
    }//GEN-LAST:event_txt_razon_comercialKeyPressed

    private void btn_registrar_proveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrar_proveedorActionPerformed
        // TODO add your handling code here:
        persona = new Entity_Persona(txt_id_persona.getText(), ((Entity_Documento) cbx_documento.getSelectedItem()).getDocumento_id(), txt_ruc.getText(), txt_dni.getText(), txt_rason_social.getText(), txt_razon_comercial.getText(), txt_direccion.getText());
        almacen.Add_proveedor(persona);
        cbx_compra_documento.getModel().setSelectedItem(new Entity_Documento(((Entity_Documento) cbx_documento.getSelectedItem()).getDocumento_id(), ((Entity_Documento) cbx_documento.getSelectedItem()).getDocumento_cod(), ((Entity_Documento) cbx_documento.getSelectedItem()).getDocumento_descrip(), ((Entity_Documento) cbx_documento.getSelectedItem()).getDocumento_url()));
        txt_compra_DNI_RUC.setText(txt_busqueda.getText());
        txt_id_proveedor.setText(txt_id_persona.getText());
        txt_compra_denominacion.setText(txt_rason_social.getText());
        txt_compra_direccion.setText(txt_direccion.getText());
        Dialog_Proveedor.dispose();
    }//GEN-LAST:event_btn_registrar_proveedorActionPerformed

    private void txt_gravadaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_gravadaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_gravadaKeyPressed

    private void txt_exoneradaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_exoneradaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_exoneradaKeyPressed

    private void txt_inafectaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_inafectaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_inafectaKeyPressed

    private void txt_totalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_totalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_totalKeyPressed

    private void txt_igvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_igvKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_igvKeyPressed

    private void txt_ingrese_desct_globalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ingrese_desct_globalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ingrese_desct_globalKeyPressed

    private void txt_descuento_itenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_descuento_itenKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_descuento_itenKeyPressed

    private void txt_descuento_globalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_descuento_globalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_descuento_globalKeyPressed

    private void txt_gratuitoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_gratuitoKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_gratuitoKeyPressed

    private void txt_otras_cargasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_otras_cargasKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_otras_cargasKeyPressed

    private void txt_descuento_globalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_descuento_globalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_descuento_globalActionPerformed

    private void txt_descuento_itenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_descuento_itenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_descuento_itenActionPerformed

    private void txt_tipo_cambioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tipo_cambioKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tipo_cambioKeyPressed

    private void txt_compra_direccionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_compra_direccionKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_compra_direccionKeyPressed

    private void txt_inafectaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_inafectaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_inafectaActionPerformed

    private void btn_buscar_cod_compraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscar_cod_compraActionPerformed
        // TODO add your handling code here:
        btn_registrar.setEnabled(true);
        btn_cancelar.setEnabled(true);
        btn_eliminar.setEnabled(true);
        btn_nuevo.setEnabled(true);
        almacen.table_compra(tabla_compra);
        Dimension desktopSize = View_Escritorio.desktopPane.getSize();
        Dimension FrameSize = Dialog_compra.getSize();
        Dialog_compra.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        Dialog_compra.setVisible(true);

    }//GEN-LAST:event_btn_buscar_cod_compraActionPerformed

    private void btn_calcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_calcularActionPerformed
        // TODO add your handling code here:
        try {

            DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
            separadoresPersonalizados.setDecimalSeparator('.');
            DecimalFormat formato2 = new DecimalFormat("###############0.000000", separadoresPersonalizados);
            double gravada = 0;
            double exonerada = 0;
            double inafecta = 0;
            double descuento_iten = 0;
            double gratuita = 0;
            double igv = 0;
            double descuento = 0;
            for (int i = 0; i < table_productos_compra.getRowCount(); i++) {

                descuento_iten = descuento_iten + Double.parseDouble(table_productos_compra.getValueAt(i, 15).toString());

                if (table_productos_compra.getValueAt(i, 9).toString().equals("GRATUITA")) {
                    gratuita = gratuita + Double.parseDouble(table_productos_compra.getValueAt(i, 12).toString());
                }
                if (table_productos_compra.getValueAt(i, 8).toString().equals("0.18")) {
                    igv = igv + Double.parseDouble(table_productos_compra.getValueAt(i, 12).toString());
                }
                if (table_productos_compra.getValueAt(i, 9).toString().equals("GRAVADA")) {
                    gravada = gravada + Double.parseDouble(table_productos_compra.getValueAt(i, 12).toString());
                }
                if (table_productos_compra.getValueAt(i, 9).toString().equals("EXONERADO")) {
                    exonerada = exonerada + Double.parseDouble(table_productos_compra.getValueAt(i, 12).toString());
                }
                if (table_productos_compra.getValueAt(i, 9).toString().toString().equals("INAFECTO")) {
                    inafecta = inafecta + Double.parseDouble(table_productos_compra.getValueAt(i, 12).toString());
                }
                if (table_productos_compra.getValueAt(i, 9).toString().toString() != ("GRATUITA")) {
                    descuento = descuento + Double.parseDouble(table_productos_compra.getValueAt(i, 12).toString());
                }

            }

            txt_descuento_global.setText("" + formato2.format(descuento - Double.parseDouble(txt_ingrese_desct_global.getText())));
            txt_gravada.setText("" + formato2.format(gravada));
            txt_exonerada.setText("" + formato2.format(exonerada));
            txt_inafecta.setText("" + formato2.format(inafecta));
            txt_gratuito.setText("" + formato2.format(gratuita));
            txt_descuento_iten.setText("" + formato2.format(descuento_iten));
            txt_igv.setText("" + formato2.format(igv * 0.18));
            txt_total.setText("" + formato2.format((Double.parseDouble(txt_gravada.getText()) + Double.parseDouble(txt_exonerada.getText())
                    + Double.parseDouble(txt_inafecta.getText()) + Double.parseDouble(txt_igv.getText())
                    - Double.parseDouble(txt_ingrese_desct_global.getText()) + Double.parseDouble(txt_otras_cargas.getText()))));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_calcularActionPerformed

    private void txt_ingrese_desct_globalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_ingrese_desct_globalKeyReleased
        // TODO add your handling code here:
        btn_calcular.doClick();
    }//GEN-LAST:event_txt_ingrese_desct_globalKeyReleased

    private void txt_otras_cargasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_otras_cargasKeyReleased
        // TODO add your handling code here:
        btn_calcular.doClick();
    }//GEN-LAST:event_txt_otras_cargasKeyReleased

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        // TODO add your handling code here:
        if (Function_ShowMessageDialog.ShowMessageDialogdeleteconfirmacion(null) == 0) {
            almacen.delete_compra(txt_id_compra.getText());
            nuevo_compra();
            habilitar_compra();
            almacen.compra_detalle(table_productos_compra, Dialog_producto, tabla_productos, Dialog_afectacion, tabla_afectacion, TITLE_PROPERTY);
            cbx_tipo_comprobante.requestFocus();
            btn_registrar.setEnabled(false);
            btn_cancelar.setEnabled(false);
            btn_eliminar.setEnabled(false);
            btn_nuevo.setEnabled(true);
        }

    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void txt_busqueda_compraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busqueda_compraKeyReleased
        // TODO add your handling code here:
        filtro_tabla(txt_busqueda_compra.getText().toUpperCase(), tabla_compra);
    }//GEN-LAST:event_txt_busqueda_compraKeyReleased

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        // TODO add your handling code here:
        txt_id_compra.setText("");
        deshabilitar_compra();
        nuevo_compra();
        almacen.compra_detalle(table_productos_compra, Dialog_producto, tabla_productos, Dialog_afectacion, tabla_afectacion, TITLE_PROPERTY);
        cbx_tipo_comprobante.requestFocus();
        btn_registrar.setEnabled(false);
        btn_cancelar.setEnabled(false);
        btn_eliminar.setEnabled(false);
        btn_nuevo.setEnabled(true);
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void tabla_compraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_compraMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_tabla_compraMouseClicked

    private void tabla_compraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_compraKeyPressed
        // TODO add your handling code here:
        try {

            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

                List<Entity_Compra> compra = new Controller_compra().buscar_compra(tabla_compra.getValueAt(tabla_compra.getSelectedRow(), 0).toString());
                Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(compra.get(0).getCompra_fecha_emis());
                Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(compra.get(0).getCompra_fecha_ingreso_alm());
                Date date3 = new SimpleDateFormat("yyyy-MM-dd").parse(compra.get(0).getCompra_fecha_vencimiento());
                Date date4 = new SimpleDateFormat("yyyy-MM-dd").parse(compra.get(0).getCompra_fecha_contable());
                txt_id_compra.setText(compra.get(0).getCompra_id());
                cbx_tipo_comprobante.getModel().setSelectedItem(new Entity_Tipo_Comprobante(compra.get(0).getComprobante_id(),
                        compra.get(0).getComprobante_cod(), compra.get(0).getComprobante_descrip()));
                txt_serie.setText(compra.get(0).getCompra_serie());
                txt_numero.setText(compra.get(0).getCompra_numero());
                cbx_moneda.getModel().setSelectedItem(new Entity_Moneda(compra.get(0).getMoneda_id(),
                        compra.get(0).getMoneda_cod(), compra.get(0).getMoneda_descrip()));
                cbx_tipoafectacion.getModel().setSelectedItem(new Entity_Tipo_afectacion(compra.get(0).getAfectacion_id(),
                        compra.get(0).getAfectacion_cod(), compra.get(0).getAfectacion_descr(), compra.get(0).getAfectacion_valor(), compra.get(0).getAfectacion_cod_valor()));

                date_fecha_emision.setDate(date1);
                date_fecha_ingreso_almacen.setDate(date2);
                date_fecha_vencimiento.setDate(date3);
                date_fecha_contable.setDate(date4);
                cbx_compra_documento.getModel().setSelectedItem(new Entity_Documento(compra.get(0).getDocumento_id(),
                        compra.get(0).getDocumento_cod(), compra.get(0).getDocumento_descrip(), compra.get(0).getDocumento_url()));
                txt_id_proveedor.setText(compra.get(0).getPersona_id());
                if (compra.get(0).getDocumento_cod().equals("6")) {
                    txt_compra_DNI_RUC.setText(compra.get(0).getPersona_ruc());
                } else {
                    txt_compra_DNI_RUC.setText(compra.get(0).getPersona_dni());
                }

                txt_compra_denominacion.setText(compra.get(0).getPersona_razon_social());
                txt_compra_direccion.setText(compra.get(0).getPersona_direccion());
                txt_glosa.setText(compra.get(0).getCompra_glosa());
                cbx_almacen.getModel().setSelectedItem(new Entity_Almacen(compra.get(0).getAlmacen_id(),
                        compra.get(0).getAlmacen_cod(), compra.get(0).getAlmacen_descrip()));
                check_estado_comprobante.setSelected(compra.get(0).getCompra_estado());
                check_estado_almacen.setSelected(compra.get(0).getCompra_estado_almacen());

                txt_ingrese_desct_global.setText(compra.get(0).getCompra_ingrese_descuento_global());
                txt_descuento_global.setText(compra.get(0).getCompra_descuento_global());
                txt_descuento_iten.setText(compra.get(0).getCompra_descuento_item());
                txt_gravada.setText(compra.get(0).getCompra_gravada());
                txt_exonerada.setText(compra.get(0).getCompra_exonerada());
                txt_inafecta.setText(compra.get(0).getCompra_inafecta());
                txt_igv.setText(compra.get(0).getCompra_igv());
                txt_gratuito.setText(compra.get(0).getCompra_gratuita());
                txt_otras_cargas.setText(compra.get(0).getCompra_otros_cargos());
                txt_total.setText(compra.get(0).getCompra_total());
                txt_tipo_cambio.setText(compra.get(0).getCompra_cambio());

                almacen.compra_detalle(table_productos_compra, Dialog_producto, tabla_productos, Dialog_afectacion, tabla_afectacion, tabla_compra.getValueAt(tabla_compra.getSelectedRow(), 0).toString());

                /* cbx_compra_documento.getModel().setSelectedItem(new Entity_Documento(compra.get(0).getDocumento_id(),
                    compra.get(0).getDocumento_cod(), compra.get(0).getDocumento_descrip(), compra.get(0).getDocumento_url()));*/
                Dialog_compra.dispose();
                habilitar_compra();
                btn_registrar.setEnabled(true);
                btn_cancelar.setEnabled(true);
                btn_eliminar.setEnabled(true);
                btn_nuevo.setEnabled(true);

            }
        } catch (Exception e) {
        }


    }//GEN-LAST:event_tabla_compraKeyPressed

    private void cbx_tipo_comprobanteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_tipo_comprobanteKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_serie.requestFocus();
        }
    }//GEN-LAST:event_cbx_tipo_comprobanteKeyPressed

    private void cbx_tipoafectacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_tipoafectacionKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            date_fecha_emision.requestFocus();
            date_fecha_emision.getDateEditor().getUiComponent().requestFocusInWindow();
        }
    }//GEN-LAST:event_cbx_tipoafectacionKeyPressed

    private void cbx_monedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_monedaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cbx_tipoafectacion.requestFocus();
        }
    }//GEN-LAST:event_cbx_monedaKeyPressed

    private void date_fecha_emisionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_date_fecha_emisionKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            System.out.println("View.View_Ingreso_Productos.date_fecha_emisionKeyPressed()");

        }
    }//GEN-LAST:event_date_fecha_emisionKeyPressed

    private void cbx_compra_documentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_compra_documentoKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_compra_DNI_RUC.requestFocus();

        }
    }//GEN-LAST:event_cbx_compra_documentoKeyPressed

    private void cbx_almacenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_almacenKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            check_estado_comprobante.requestFocus();
        }
    }//GEN-LAST:event_cbx_almacenKeyPressed

    private void check_estado_comprobanteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_check_estado_comprobanteKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            check_estado_almacen.requestFocus();
        }
    }//GEN-LAST:event_check_estado_comprobanteKeyPressed

    private void check_estado_almacenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_check_estado_almacenKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            btn_agregar_fila.doClick();
        }
    }//GEN-LAST:event_check_estado_almacenKeyPressed

    private void txt_busqueda_productosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busqueda_productosKeyReleased
        // TODO add your handling code here:
        filtro_tabla(txt_busqueda_productos.getText().toUpperCase(), tabla_productos);
    }//GEN-LAST:event_txt_busqueda_productosKeyReleased

    private void tabla_productosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_productosKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            table_productos_compra.setValueAt(tabla_productos.getValueAt(tabla_productos.getSelectedRow(), 1), table_productos_compra.getSelectedRow() + almacen.variable(), 2);
            table_productos_compra.setValueAt(tabla_productos.getValueAt(tabla_productos.getSelectedRow(), 2), table_productos_compra.getSelectedRow() + almacen.variable(), 3);
            table_productos_compra.setValueAt(tabla_productos.getValueAt(tabla_productos.getSelectedRow(), 3), table_productos_compra.getSelectedRow() + almacen.variable(), 4);
            table_productos_compra.setValueAt(tabla_productos.getValueAt(tabla_productos.getSelectedRow(), 4), table_productos_compra.getSelectedRow() + almacen.variable(), 5);
            table_productos_compra.setValueAt(tabla_productos.getValueAt(tabla_productos.getSelectedRow(), 6), table_productos_compra.getSelectedRow() + almacen.variable(), 11);
            Dialog_producto.dispose();
            txt_busqueda_productos.setText("");
        }
    }//GEN-LAST:event_tabla_productosKeyPressed

    private void tabla_afectacionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_afectacionKeyPressed
        // TODO add your handling code here:

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            table_productos_compra.setValueAt(tabla_afectacion.getValueAt(tabla_afectacion.getSelectedRow(), 0), table_productos_compra.getSelectedRow() + almacen.variable(), 6);
            table_productos_compra.setValueAt(tabla_afectacion.getValueAt(tabla_afectacion.getSelectedRow(), 2), table_productos_compra.getSelectedRow() + almacen.variable(), 7);
            table_productos_compra.setValueAt(tabla_afectacion.getValueAt(tabla_afectacion.getSelectedRow(), 3), table_productos_compra.getSelectedRow() + almacen.variable(), 8);
            table_productos_compra.setValueAt(tabla_afectacion.getValueAt(tabla_afectacion.getSelectedRow(), 4), table_productos_compra.getSelectedRow() + almacen.variable(), 9);
            Dialog_afectacion.dispose();
        }
    }//GEN-LAST:event_tabla_afectacionKeyPressed

    private void table_productos_compraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_table_productos_compraKeyPressed
        // TODO add your handling code here:
        if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_P) {
                            Controller_compra almacen = new Controller_compra();
                            if (almacen.table_produto_busqueda_presentacion(tabla_productos, table_productos_compra.getValueAt(table_productos_compra.getSelectedRow()+almacen.variable(), 3).toString()).equals("ERROR")) {
                                Function_ShowMessageDialog.ShowMessageDialogvalidacion(null, "BUSQUEDA NO ENCONTRADA");

                            } else {
                                Dimension desktopSize = View_Escritorio.desktopPane.getSize();
                                Dimension FrameSize = Dialog_producto.getSize();
                                Dialog_producto.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
                                Dialog_producto.setVisible(true);
                                table_productos_compra.editCellAt(table_productos_compra.getSelectedRow() + almacen.variable(), 7);
                                Component aComp = table_productos_compra.getEditorComponent();
                                aComp.requestFocus();
                                tabla_productos.requestFocus();
                                View_Compra.btn_calcular.doClick();

                            }
                            evt.consume();
                        }
    }//GEN-LAST:event_table_productos_compraKeyPressed

    void habilitar_proveedor() {
        txt_id_persona.setEnabled(true);
        txt_busqueda.setEnabled(true);
        txt_ruc.setEnabled(true);
        txt_dni.setEnabled(true);
        txt_rason_social.setEnabled(true);
        txt_razon_comercial.setEnabled(true);
        txt_direccion.setEnabled(true);

    }

    void deshabilitar_proveedor() {
        txt_id_persona.setEnabled(false);
        txt_busqueda.setEnabled(false);
        txt_ruc.setEnabled(false);
        txt_dni.setEnabled(false);
        txt_rason_social.setEnabled(false);
        txt_razon_comercial.setEnabled(false);
        txt_direccion.setEnabled(false);

    }

    void limpiar_proveedor() {
        //txt_id_persona.setText("");
        //txt_busqueda.setText("");
        txt_ruc.setText("");
        txt_dni.setText("");
        txt_rason_social.setText("");
        txt_razon_comercial.setText("");
        txt_direccion.setText("");

    }

    void filtro_tabla(String consulta, JTable jtableBuscar) {
        DefaultTableModel dm = (DefaultTableModel) jtableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(dm);
        jtableBuscar.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(consulta));
    }

    void nuevo_compra() {
        txt_busqueda_compra_cod.setText("");
        txt_serie.setText("");
        txt_numero.setText("");
        txt_tipo_cambio.setText("1.000000");
        Date date = new Date();
        date_fecha_contable.setDate(date);
        date_fecha_emision.setDate(date);
        date_fecha_ingreso_almacen.setDate(date);
        date_fecha_vencimiento.setDate(date);
        txt_compra_DNI_RUC.setText("");
        txt_id_proveedor.setText("");
        txt_compra_denominacion.setText("");
        txt_compra_direccion.setText("");
        txt_glosa.setText("");
        check_estado_comprobante.setSelected(true);
        check_estado_almacen.setSelected(true);

        txt_ingrese_desct_global.setText("0.000000");
        txt_descuento_global.setText("0.000000");
        txt_descuento_iten.setText("0.000000");
        txt_gravada.setText("0.000000");
        txt_exonerada.setText("0.000000");
        txt_inafecta.setText("0.000000");
        txt_igv.setText("0.000000");
        txt_gratuito.setText("0.000000");
        txt_otras_cargas.setText("0.000000");
        txt_total.setText("0.000000");
    }

    void deshabilitar_compra() {
        //txt_busqueda_compra_cod.setEnabled(false);
        txt_serie.setEnabled(false);
        txt_numero.setEnabled(false);
        txt_tipo_cambio.setEnabled(false);
        date_fecha_contable.setEnabled(false);
        date_fecha_emision.setEnabled(false);
        date_fecha_ingreso_almacen.setEnabled(false);
        date_fecha_vencimiento.setEnabled(false);
        txt_compra_DNI_RUC.setEnabled(false);
        txt_id_proveedor.setEnabled(false);
        txt_compra_denominacion.setEnabled(false);
        txt_compra_direccion.setEnabled(false);
        txt_glosa.setEnabled(false);
        check_estado_comprobante.setEnabled(false);
        check_estado_almacen.setEnabled(false);

        txt_ingrese_desct_global.setEnabled(false);
        txt_descuento_global.setEnabled(false);
        txt_descuento_iten.setEnabled(false);
        txt_gravada.setEnabled(false);
        txt_exonerada.setEnabled(false);
        txt_inafecta.setEnabled(false);
        txt_igv.setEnabled(false);
        txt_gratuito.setEnabled(false);
        txt_otras_cargas.setEnabled(false);
        txt_total.setEnabled(false);

        cbx_tipo_comprobante.setEnabled(false);
        cbx_moneda.setEnabled(false);
        cbx_tipoafectacion.setEnabled(false);
        cbx_compra_documento.setEnabled(false);
        cbx_almacen.setEnabled(false);
    }

    void habilitar_compra() {
        txt_busqueda_compra_cod.setEnabled(true);
        txt_serie.setEnabled(true);
        txt_numero.setEnabled(true);
        txt_tipo_cambio.setEnabled(true);
        date_fecha_contable.setEnabled(true);
        date_fecha_emision.setEnabled(true);
        date_fecha_ingreso_almacen.setEnabled(true);
        date_fecha_vencimiento.setEnabled(true);
        txt_compra_DNI_RUC.setEnabled(true);
        txt_id_proveedor.setEnabled(true);
        txt_compra_denominacion.setEnabled(true);
        txt_compra_direccion.setEnabled(true);
        txt_glosa.setEnabled(true);
        check_estado_comprobante.setEnabled(true);
        check_estado_almacen.setEnabled(true);

        txt_ingrese_desct_global.setEnabled(true);
        txt_descuento_global.setEnabled(true);
        txt_descuento_iten.setEnabled(true);
        txt_gravada.setEnabled(true);
        txt_exonerada.setEnabled(true);
        txt_inafecta.setEnabled(true);
        txt_igv.setEnabled(true);
        txt_gratuito.setEnabled(true);
        txt_otras_cargas.setEnabled(true);
        txt_total.setEnabled(true);

        cbx_tipo_comprobante.setEnabled(true);
        cbx_moneda.setEnabled(true);
        cbx_tipoafectacion.setEnabled(true);
        cbx_compra_documento.setEnabled(true);
        cbx_almacen.setEnabled(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Dialog_Proveedor;
    private javax.swing.JDialog Dialog_afectacion;
    private javax.swing.JDialog Dialog_compra;
    private javax.swing.JDialog Dialog_producto;
    private javax.swing.JLabel GRAVADA;
    private javax.swing.JLabel GRAVADA1;
    private javax.swing.JLabel GRAVADA2;
    private javax.swing.JLabel GRAVADA3;
    public static javax.swing.JButton btn_agregar_fila;
    private javax.swing.JButton btn_buscar_cod_compra;
    private javax.swing.JButton btn_buscar_ruc_dni;
    public static javax.swing.JButton btn_calcular;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_nuevo;
    private javax.swing.JButton btn_registrar;
    private javax.swing.JButton btn_registrar_proveedor;
    private javax.swing.JComboBox<Entity_Almacen> cbx_almacen;
    private javax.swing.JComboBox<Entity_Documento> cbx_compra_documento;
    private javax.swing.JComboBox<Entity_Documento> cbx_documento;
    private javax.swing.JComboBox<Entity_Moneda> cbx_moneda;
    private javax.swing.JComboBox<Entity_Tipo_Comprobante> cbx_tipo_comprobante;
    private javax.swing.JComboBox<Entity_Tipo_afectacion> cbx_tipoafectacion;
    private javax.swing.JCheckBox check_estado_almacen;
    private javax.swing.JCheckBox check_estado_comprobante;
    private com.toedter.calendar.JDateChooser date_fecha_contable;
    private com.toedter.calendar.JDateChooser date_fecha_emision;
    private com.toedter.calendar.JDateChooser date_fecha_ingreso_almacen;
    private com.toedter.calendar.JDateChooser date_fecha_vencimiento;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
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
    private javax.swing.JTable tabla_afectacion;
    private javax.swing.JTable tabla_compra;
    private javax.swing.JTable tabla_productos;
    private javax.swing.JTable table_productos_compra;
    private javax.swing.JTextField txt_busqueda;
    private javax.swing.JTextField txt_busqueda_compra;
    private javax.swing.JTextField txt_busqueda_compra_cod;
    private javax.swing.JTextField txt_busqueda_productos;
    private javax.swing.JTextField txt_compra_DNI_RUC;
    private javax.swing.JTextField txt_compra_denominacion;
    private javax.swing.JTextField txt_compra_direccion;
    private javax.swing.JTextField txt_descuento_global;
    private javax.swing.JTextField txt_descuento_iten;
    private javax.swing.JTextField txt_direccion;
    private javax.swing.JTextField txt_dni;
    private javax.swing.JTextField txt_exonerada;
    private javax.swing.JTextField txt_glosa;
    private javax.swing.JTextField txt_gratuito;
    private javax.swing.JTextField txt_gravada;
    private javax.swing.JTextField txt_id_compra;
    private javax.swing.JTextField txt_id_persona;
    private javax.swing.JTextField txt_id_proveedor;
    private javax.swing.JTextField txt_igv;
    private javax.swing.JTextField txt_inafecta;
    private javax.swing.JTextField txt_ingrese_desct_global;
    private javax.swing.JTextField txt_numero;
    private javax.swing.JTextField txt_otras_cargas;
    private javax.swing.JTextField txt_rason_social;
    private javax.swing.JTextField txt_razon_comercial;
    private javax.swing.JTextField txt_ruc;
    private javax.swing.JTextField txt_serie;
    private javax.swing.JTextField txt_tipo_cambio;
    private javax.swing.JTextField txt_total;
    // End of variables declaration//GEN-END:variables
}
