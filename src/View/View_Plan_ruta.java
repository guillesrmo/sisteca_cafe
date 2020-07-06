/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Conexion.Conexion;
import Controller.Controller_Plan_Ruta;
import Controller.Controller_Empresa;
import Controller.Controller_Ingreso_Almacen;
import Controller.Controller_Institucion;
import Controller.Controller_Subir_Iten;
import Entity.Entity_Almacen;
import Entity.Entity_Anexo9;
import Entity.Entity_Compra;
import Entity.Entity_Compra_Producto;
import Entity.Entity_Concepto;
import Entity.Entity_Documento;
import Entity.Entity_Entrega;
import Entity.Entity_Moneda;
import Entity.Entity_Persona;
import Entity.Entity_Plan_ruta;
import Entity.Entity_Producto_Presentacion;
import Entity.Entity_Sucursal;
import Entity.Entity_Sunat_transaccion;
import Entity.Entity_Tipo_Comprobante;
import Entity.Entity_Tipo_afectacion;
import Entity.Entity_Venta;
import Entity.Entity_Venta_Producto;
import Function.Function_Component;
import Function.Function_Key_Event;
import Function.Function_ShowMessageDialog;
import static Web_Service.Service_RUC_DNI.peticionHttpGet;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import static javax.swing.JInternalFrame.TITLE_PROPERTY;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.MaskFormatter;
import javax.swing.text.html.parser.Entity;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author memo
 */
public class View_Plan_ruta extends javax.swing.JInternalFrame {

    Entity_Plan_ruta entity_ruta;
    JFileChooser selecArchivo = new JFileChooser();
    File archivo;
    //Entity_Venta detalle_compra;
    /**
     * Creates new form View_Ingreso_Productos
     */
    Controller_Plan_Ruta anexo = new Controller_Plan_Ruta();

    public View_Plan_ruta() {
        initComponents();
          selecArchivo.setFileFilter(new FileNameExtensionFilter("Excel (*.xls)", "xls"));
        Function_Component.JTable(tabla_plan_ruta);
        anexo.tabla_plan_ruta(tabla_plan_ruta, TITLE_PROPERTY);
        anexo.cbx_entrega(cbx_entrega);
        new Controller_Subir_Iten().cbxItem(cbx_iten);
        new Controller_Subir_Iten().cbxItem(cbx_item_ruta);
       // filtro_tabla("",tabla_plan_ruta);
     /*  DefaultTableModel dm = (DefaultTableModel) tabla_plan_ruta.getModel();
TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(dm);*/
        /*Function_Key_Event.JDateChooser(date_fecha_emision, date_fecha_ingreso_almacen);
        Function_Key_Event.JDateChooser(date_fecha_ingreso_almacen, date_fecha_vencimiento);
        Function_Key_Event.JDateChooser(date_fecha_vencimiento, date_fecha_contable);
        Function_Key_Event.JDateChooser_JComboBox(date_fecha_contable, cbx_compra_documento);*/
        // date_fecha_vencimiento.getDateFormatString();
        Function_Key_Event.Validar_Mayuscula(txt_busqueda_plan_ruta);
        Function_Key_Event.Validar_Mayuscula(txt_busqueda_compra);
        //Function_Key_Event.Validar_Mayuscula(txt_serie);
        Function_Component.JComboBox(cbx_entrega);

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
        deshabilitar_compra();

        btn_registrar.setEnabled(false);
        btn_cancelar.setEnabled(false);
        btn_eliminar.setEnabled(false);
        btn_nuevo.setEnabled(true);

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

        Dialog_institucion = new javax.swing.JDialog();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        txt_busqueda_producto = new javax.swing.JTextField();
        cbx_iten = new javax.swing.JComboBox<>();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_insittucion = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        Dialog_venta = new javax.swing.JDialog();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabla_compra = new javax.swing.JTable();
        jPanel23 = new javax.swing.JPanel();
        txt_busqueda_compra = new javax.swing.JTextField();
        Dialog_carro = new javax.swing.JDialog();
        jPanel18 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tabla_carro = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        Dialog_conductor = new javax.swing.JDialog();
        jPanel19 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tabla_conductor = new javax.swing.JTable();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        cbx_entrega = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txt_busqueda_plan_ruta = new javax.swing.JTextField();
        btn_buscar_cod_compra = new javax.swing.JButton();
        btn_buscar_cod_compra2 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_plan_ruta = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        txt_busqueda = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        cbx_item_ruta = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        btn_nuevo = new javax.swing.JButton();
        btn_registrar = new javax.swing.JButton();
        btn_eliminar = new javax.swing.JButton();
        btn_cancelar = new javax.swing.JButton();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jPanel3 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        cbx_entrega1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txt_busqueda_anexo1 = new javax.swing.JTextField();
        btn_buscar_cod_compra1 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla_anexo1 = new javax.swing.JTable();
        btn_agregar_fila1 = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        txt_busqueda1 = new javax.swing.JTextField();
        jPanel17 = new javax.swing.JPanel();
        btn_nuevo1 = new javax.swing.JButton();
        btn_registrar1 = new javax.swing.JButton();
        btn_eliminar1 = new javax.swing.JButton();
        btn_cancelar1 = new javax.swing.JButton();

        Dialog_institucion.setTitle("INSTITUCION");
        Dialog_institucion.setAutoRequestFocus(false);
        Dialog_institucion.setMinimumSize(new java.awt.Dimension(1191, 340));
        Dialog_institucion.setResizable(false);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        txt_busqueda_producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_busqueda_productoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_busqueda_productoKeyReleased(evt);
            }
        });

        cbx_iten.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbx_itenItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addComponent(cbx_iten, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txt_busqueda_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 856, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_busqueda_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbx_iten, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        tabla_insittucion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabla_insittucion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_insittucionMouseClicked(evt);
            }
        });
        tabla_insittucion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_insittucionKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tabla_insittucion);

        jButton2.setText("ENVIAR");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1151, Short.MAX_VALUE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
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

        javax.swing.GroupLayout Dialog_institucionLayout = new javax.swing.GroupLayout(Dialog_institucion.getContentPane());
        Dialog_institucion.getContentPane().setLayout(Dialog_institucionLayout);
        Dialog_institucionLayout.setHorizontalGroup(
            Dialog_institucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dialog_institucionLayout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        Dialog_institucionLayout.setVerticalGroup(
            Dialog_institucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dialog_institucionLayout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        Dialog_venta.setTitle("VENTA");
        Dialog_venta.setMinimumSize(new java.awt.Dimension(669, 386));
        Dialog_venta.setModal(true);
        Dialog_venta.setResizable(false);

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

        javax.swing.GroupLayout Dialog_ventaLayout = new javax.swing.GroupLayout(Dialog_venta.getContentPane());
        Dialog_venta.getContentPane().setLayout(Dialog_ventaLayout);
        Dialog_ventaLayout.setHorizontalGroup(
            Dialog_ventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dialog_ventaLayout.createSequentialGroup()
                .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        Dialog_ventaLayout.setVerticalGroup(
            Dialog_ventaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dialog_ventaLayout.createSequentialGroup()
                .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        Dialog_carro.setTitle("INSTITUCION");
        Dialog_carro.setAutoRequestFocus(false);
        Dialog_carro.setMinimumSize(new java.awt.Dimension(436, 204));
        Dialog_carro.setResizable(false);

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));

        tabla_carro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabla_carro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_carroMouseClicked(evt);
            }
        });
        tabla_carro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_carroKeyPressed(evt);
            }
        });
        jScrollPane5.setViewportView(tabla_carro);

        jTextField1.setText("0");

        jTextField2.setText("0");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Dialog_carroLayout = new javax.swing.GroupLayout(Dialog_carro.getContentPane());
        Dialog_carro.getContentPane().setLayout(Dialog_carroLayout);
        Dialog_carroLayout.setHorizontalGroup(
            Dialog_carroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dialog_carroLayout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        Dialog_carroLayout.setVerticalGroup(
            Dialog_carroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dialog_carroLayout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        Dialog_conductor.setTitle("INSTITUCION");
        Dialog_conductor.setAutoRequestFocus(false);
        Dialog_conductor.setMinimumSize(new java.awt.Dimension(436, 204));
        Dialog_conductor.setResizable(false);

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));

        tabla_conductor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabla_conductor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_conductorMouseClicked(evt);
            }
        });
        tabla_conductor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_conductorKeyPressed(evt);
            }
        });
        jScrollPane6.setViewportView(tabla_conductor);

        jTextField3.setText("0");

        jTextField4.setText("0");

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Dialog_conductorLayout = new javax.swing.GroupLayout(Dialog_conductor.getContentPane());
        Dialog_conductor.getContentPane().setLayout(Dialog_conductorLayout);
        Dialog_conductorLayout.setHorizontalGroup(
            Dialog_conductorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dialog_conductorLayout.createSequentialGroup()
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        Dialog_conductorLayout.setVerticalGroup(
            Dialog_conductorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dialog_conductorLayout.createSequentialGroup()
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("PLAN DE RUTA");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel29.setBackground(new java.awt.Color(255, 255, 255));
        jPanel29.setBorder(javax.swing.BorderFactory.createTitledBorder("Anexo"));

        cbx_entrega.setAlignmentX(39.0F);
        cbx_entrega.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_entregaKeyPressed(evt);
            }
        });

        jLabel1.setText("Codigo de anexo 9");

        txt_busqueda_plan_ruta.setAlignmentX(39.0F);
        txt_busqueda_plan_ruta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_busqueda_plan_rutaActionPerformed(evt);
            }
        });
        txt_busqueda_plan_ruta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_busqueda_plan_rutaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_busqueda_plan_rutaKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_busqueda_plan_rutaKeyTyped(evt);
            }
        });

        btn_buscar_cod_compra.setText("BUSCAR");
        btn_buscar_cod_compra.setAlignmentX(39.0F);
        btn_buscar_cod_compra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscar_cod_compraActionPerformed(evt);
            }
        });

        btn_buscar_cod_compra2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/icon_pdf.png"))); // NOI18N
        btn_buscar_cod_compra2.setText("PDF");
        btn_buscar_cod_compra2.setAlignmentX(39.0F);
        btn_buscar_cod_compra2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscar_cod_compra2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap(277, Short.MAX_VALUE)
                .addComponent(cbx_entrega, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel29Layout.createSequentialGroup()
                        .addComponent(txt_busqueda_plan_ruta, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_buscar_cod_compra, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_buscar_cod_compra2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(277, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbx_entrega, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_busqueda_plan_ruta, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_buscar_cod_compra, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_buscar_cod_compra2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabla productos"));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        tabla_plan_ruta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla_plan_ruta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_plan_rutaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_plan_ruta);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1226, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel5.setForeground(new java.awt.Color(255, 255, 255));

        txt_busqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_busquedaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_busquedaKeyReleased(evt);
            }
        });

        jButton1.setText("INSTITUCION");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("GENERAR X ITEM");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("EXPORT");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("IMPORT");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbx_item_ruta, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbx_item_ruta, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel9Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 407, Short.MAX_VALUE))
            .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                    .addGap(49, 49, 49)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jInternalFrame1.setClosable(true);
        jInternalFrame1.setIconifiable(true);
        jInternalFrame1.setMaximizable(true);
        jInternalFrame1.setResizable(true);
        jInternalFrame1.setTitle("SALIDA PRODUCTOS");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jPanel30.setBackground(new java.awt.Color(255, 255, 255));
        jPanel30.setBorder(javax.swing.BorderFactory.createTitledBorder("Anexo"));

        cbx_entrega1.setAlignmentX(39.0F);
        cbx_entrega1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbx_entrega1KeyPressed(evt);
            }
        });

        jLabel2.setText("Codigo de anexo 9");

        txt_busqueda_anexo1.setAlignmentX(39.0F);
        txt_busqueda_anexo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_busqueda_anexo1ActionPerformed(evt);
            }
        });
        txt_busqueda_anexo1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_busqueda_anexo1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_busqueda_anexo1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_busqueda_anexo1KeyTyped(evt);
            }
        });

        btn_buscar_cod_compra1.setText("BUSCAR");
        btn_buscar_cod_compra1.setAlignmentX(39.0F);
        btn_buscar_cod_compra1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscar_cod_compra1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbx_entrega1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel30Layout.createSequentialGroup()
                        .addComponent(txt_busqueda_anexo1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_buscar_cod_compra1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbx_entrega1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_busqueda_anexo1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_buscar_cod_compra1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabla productos"));

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        tabla_anexo1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla_anexo1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_anexo1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tabla_anexo1);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1176, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE))
        );

        btn_agregar_fila1.setText("+");
        btn_agregar_fila1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregar_fila1ActionPerformed(evt);
            }
        });

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel16.setForeground(new java.awt.Color(255, 255, 255));

        txt_busqueda1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_busqueda1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_busqueda1, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(txt_busqueda1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btn_agregar_fila1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(60, 60, 60)))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_agregar_fila1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                    .addGap(49, 49, 49)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btn_nuevo1.setText("NUEVO");
        btn_nuevo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nuevo1ActionPerformed(evt);
            }
        });

        btn_registrar1.setText("REGISTRAR");
        btn_registrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_registrar1ActionPerformed(evt);
            }
        });

        btn_eliminar1.setText("ELIMINAR");
        btn_eliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_eliminar1ActionPerformed(evt);
            }
        });

        btn_cancelar1.setText("CANCELAR");
        btn_cancelar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelar1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addComponent(btn_registrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_eliminar1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(btn_cancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_nuevo1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_registrar1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_nuevo1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_cancelar1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_eliminar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabla_insittucionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_insittucionMouseClicked
        // TODO add your handling code here:
        int fila = tabla_insittucion.rowAtPoint(evt.getPoint());
        int column = tabla_insittucion.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / tabla_insittucion.getRowHeight();
        if (row < tabla_insittucion.getRowCount() && row >= 0 && column < tabla_insittucion.getColumnCount() && column >= 0) {
            Object value = tabla_insittucion.getValueAt(row, column);
            if (value instanceof JButton) {
                ((JButton) value).doClick();
                JButton boton = (JButton) value;

                if (boton.getName().equals("btnselecionar")) {

                    /* anexo.add_anexo9(tabla_plan_ruta, Dialog_institucion, tabla_insittucion,
                            tabla_insittucion.getValueAt(tabla_insittucion.getSelectedRow(), 1).toString(),
                            tabla_insittucion.getValueAt(tabla_insittucion.getSelectedRow(), 2).toString(),
                            tabla_insittucion.getValueAt(tabla_insittucion.getSelectedRow(), 3).toString(),
                            tabla_insittucion.getValueAt(tabla_insittucion.getSelectedRow(), 7).toString(),
                            tabla_insittucion.getValueAt(tabla_insittucion.getSelectedRow(), 4).toString(),
                            tabla_insittucion.getValueAt(tabla_insittucion.getSelectedRow(), 5).toString(),
                            tabla_insittucion.getValueAt(tabla_insittucion.getSelectedRow(), 6).toString());
                    txt_busqueda.requestFocus();
                    Dialog_institucion.dispose();*/
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
        /*  table_productos_compra.setValueAt(tabla_pruducto.getValueAt(tabla_pruducto.getSelectedRow(), 0), table_productos_compra.getSelectedRow() + anexo.variable(), 2);
        table_productos_compra.setValueAt(tabla_pruducto.getValueAt(tabla_pruducto.getSelectedRow(), 1), table_productos_compra.getSelectedRow() + anexo.variable(), 3);
        table_productos_compra.setValueAt(tabla_pruducto.getValueAt(tabla_pruducto.getSelectedRow(), 2), table_productos_compra.getSelectedRow() + anexo.variable(), 4);
        table_productos_compra.setValueAt(tabla_pruducto.getValueAt(tabla_pruducto.getSelectedRow(), 4), table_productos_compra.getSelectedRow() + anexo.variable(), 5);

        table_productos_compra.setValueAt(tabla_pruducto.getValueAt(tabla_pruducto.getSelectedRow(), 7), table_productos_compra.getSelectedRow() + anexo.variable(), 6);
        table_productos_compra.setValueAt(tabla_pruducto.getValueAt(tabla_pruducto.getSelectedRow(), 8), table_productos_compra.getSelectedRow() + anexo.variable(), 7);
        table_productos_compra.setValueAt(tabla_pruducto.getValueAt(tabla_pruducto.getSelectedRow(), 9), table_productos_compra.getSelectedRow() + anexo.variable(), 8);
        table_productos_compra.setValueAt(tabla_pruducto.getValueAt(tabla_pruducto.getSelectedRow(), 10), table_productos_compra.getSelectedRow() + anexo.variable(), 9);
        table_productos_compra.setValueAt(tabla_pruducto.getValueAt(tabla_pruducto.getSelectedRow(), 6), table_productos_compra.getSelectedRow() + anexo.variable(), 11);
        Dialog_producto.dispose();
        txt_busqueda_producto.setText("");*/
    }//GEN-LAST:event_tabla_insittucionMouseClicked

    private void txt_busqueda_compraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busqueda_compraKeyReleased
        // TODO add your handling code here:
        filtro_tabla(txt_busqueda_compra.getText().toUpperCase(), tabla_compra);
    }//GEN-LAST:event_txt_busqueda_compraKeyReleased

    private void tabla_compraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_compraMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_tabla_compraMouseClicked

    private void tabla_compraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_compraKeyPressed
        // TODO add your handling code here:
        try {

            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

                /*  buscar(tabla_compra.getValueAt(tabla_compra.getSelectedRow(), 0).toString());
                anexo.venta_detalle(table_productos_compra, Dialog_producto, tabla_pruducto, Dialog_afectacion, tabla_afectacion, txt_id_compra.getText());*/
                txt_busqueda_plan_ruta.setText("" + tabla_compra.getValueAt(tabla_compra.getSelectedRow(), 0).toString());
                anexo.tabla_plan_ruta(tabla_plan_ruta,txt_busqueda_plan_ruta.getText());
                cbx_entrega.getModel().setSelectedItem(new Entity_Entrega(tabla_compra.getValueAt(tabla_compra.getSelectedRow(), 1).toString(), tabla_compra.getValueAt(tabla_compra.getSelectedRow(), 2).toString(), tabla_compra.getValueAt(tabla_compra.getSelectedRow(), 3).toString()));
                txt_busqueda_plan_ruta.setEnabled(false);
                cbx_entrega.setEnabled(false);

                Dialog_venta.dispose();
                btn_registrar.setEnabled(true);
                btn_cancelar.setEnabled(true);
                btn_eliminar.setEnabled(true);
                btn_nuevo.setEnabled(true);
                /* cbx_compra_documento.getModel().setSelectedItem(new Entity_Documento(compra.get(0).getDocumento_id(),
                    compra.get(0).getDocumento_cod(), compra.get(0).getDocumento_descrip(), compra.get(0).getDocumento_url()));*/
            }
        } catch (Exception e) {
        }


    }//GEN-LAST:event_tabla_compraKeyPressed

    private void tabla_insittucionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_insittucionKeyPressed
        // TODO add your handling code here:
        try {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

                /* anexo.add_anexo9(table_productos_compra, Dialog_producto, tabla_pruducto,"","","","","","" );*/
                tabla_plan_ruta.setValueAt(tabla_insittucion.getValueAt(tabla_insittucion.getSelectedRow(), 1), tabla_plan_ruta.getSelectedRow() + anexo.variable(), 2);
                tabla_plan_ruta.setValueAt(tabla_insittucion.getValueAt(tabla_insittucion.getSelectedRow(), 2), tabla_plan_ruta.getSelectedRow() + anexo.variable(), 3);
                tabla_plan_ruta.setValueAt(tabla_insittucion.getValueAt(tabla_insittucion.getSelectedRow(), 3), tabla_plan_ruta.getSelectedRow() + anexo.variable(), 4);
                tabla_plan_ruta.setValueAt(tabla_insittucion.getValueAt(tabla_insittucion.getSelectedRow(), 7), tabla_plan_ruta.getSelectedRow() + anexo.variable(), 5);
                tabla_plan_ruta.setValueAt(tabla_insittucion.getValueAt(tabla_insittucion.getSelectedRow(), 4), tabla_plan_ruta.getSelectedRow() + anexo.variable(), 9);
                tabla_plan_ruta.setValueAt(tabla_insittucion.getValueAt(tabla_insittucion.getSelectedRow(), 5), tabla_plan_ruta.getSelectedRow() + anexo.variable(), 10);
                tabla_plan_ruta.setValueAt(tabla_insittucion.getValueAt(tabla_insittucion.getSelectedRow(), 6), tabla_plan_ruta.getSelectedRow() + anexo.variable(), 19);

                Dialog_institucion.dispose();
                txt_busqueda_producto.setText("");
            }

        } catch (Exception e) {
        }

    }//GEN-LAST:event_tabla_insittucionKeyPressed

    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        // TODO add your handling code here:

        deshabilitar_compra();
        nuevo_compra();
          // anexo.tabla_plan_ruta(tabla_plan_ruta, txt_busqueda_plan_ruta.getText());
        anexo.tabla_plan_ruta(tabla_plan_ruta, TITLE_PROPERTY);
        btn_registrar.setEnabled(false);
        btn_cancelar.setEnabled(false);
        btn_eliminar.setEnabled(false);
        btn_nuevo.setEnabled(true);
    }//GEN-LAST:event_btn_cancelarActionPerformed

    private void btn_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminarActionPerformed
        // TODO add your handling code here:
        if (Function_ShowMessageDialog.ShowMessageDialogdeleteconfirmacion(null) == 0) {

            nuevo_compra();
            habilitar_compra();
            anexo.tabla_plan_ruta(tabla_plan_ruta, TITLE_PROPERTY);
            btn_registrar.setEnabled(false);
            btn_cancelar.setEnabled(false);
            btn_eliminar.setEnabled(false);
            btn_nuevo.setEnabled(true);
        }
    }//GEN-LAST:event_btn_eliminarActionPerformed

    private void btn_registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrarActionPerformed
        // TODO add your handling code here:

        if (tabla_plan_ruta.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "DEBE CONTENER MAS DE UNA FILA LA TABLA DE PRESENTACION");
            return;

        }

    
        tabla_plan_ruta.editCellAt(0, 8);
        Component aComp = tabla_plan_ruta.getEditorComponent();
        aComp.requestFocus();
        /*  for (int j = 0; j < table_productos_compra.getRowCount(); j++) {
            try {

                for (int i = 2; i < table_productos_compra.getColumnCount() - 4; i++) {
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

        }*/
        
        for (int i = 0; i < tabla_plan_ruta.getRowCount(); i++) {
            //try {
                entity_ruta = new Entity_Plan_ruta(tabla_plan_ruta.getValueAt(i, 2).toString(), txt_busqueda_plan_ruta.getText(),
                        tabla_plan_ruta.getValueAt(i, 3).toString(), tabla_plan_ruta.getValueAt(i, 6).toString(),
                        tabla_plan_ruta.getValueAt(i, 8).toString(), tabla_plan_ruta.getValueAt(i, 9).toString(),
                        tabla_plan_ruta.getValueAt(i, 10).toString(), tabla_plan_ruta.getValueAt(i, 16).toString(),
                        tabla_plan_ruta.getValueAt(i, 17).toString(), tabla_plan_ruta.getValueAt(i, 18).toString(),
                        tabla_plan_ruta.getValueAt(i, 19).toString(), tabla_plan_ruta.getValueAt(i, 21).toString(),
                        tabla_plan_ruta.getValueAt(i, 20).toString(),((Entity_Entrega) cbx_entrega.getSelectedItem()).getEntrega_id());
           
                anexo.Add_Plam_ruta(entity_ruta);
         /*   } catch (Exception e) {
                System.out.println("View.View_Anexo_9.btn_registrarActionPerformed()" + e);
               // Function_ShowMessageDialog.ShowMessageDialogvalidacion(null, "FALLO REGISTRO - CAMPO VACIO");
            }
*/
        }
        nuevo_compra();
        habilitar_compra();
        // anexo.venta_detalle(table_productos_compra, Dialog_producto, tabla_pruducto, Dialog_afectacion, tabla_afectacion, "");
        anexo.tabla_plan_ruta(tabla_plan_ruta,TITLE_PROPERTY);
        Function_ShowMessageDialog.ShowMessageDialogadd(null);
        /*btn_registrar.setEnabled(false);
            btn_cancelar.setEnabled(false);
            btn_eliminar.setEnabled(false);
            btn_nuevo.setEnabled(true);*/

    }//GEN-LAST:event_btn_registrarActionPerformed

    private void btn_nuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevoActionPerformed
        // TODO add your handling code here:
        nuevo_compra();
        habilitar_compra();
        anexo.tabla_plan_ruta(tabla_plan_ruta, TITLE_PROPERTY);
        //   anexo.venta_detalle(table_productos_compra, Dialog_producto, tabla_pruducto, Dialog_afectacion, tabla_afectacion, "");
        btn_registrar.setEnabled(true);
        btn_cancelar.setEnabled(true);
        btn_eliminar.setEnabled(true);
        btn_nuevo.setEnabled(true);
    }//GEN-LAST:event_btn_nuevoActionPerformed

    private void tabla_plan_rutaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_plan_rutaMouseClicked
        // TODO add your handling code here:
       
        if (evt.getClickCount()==2) {
            if (tabla_plan_ruta.getSelectedColumn() == 4|| tabla_plan_ruta.getSelectedColumn() == 3||tabla_plan_ruta.getSelectedColumn() == 5) {
            anexo.table_carro(tabla_carro);
            Dimension desktopSize = View_Escritorio.desktopPane.getSize();
            Dimension FrameSize = Dialog_carro.getSize();
            Dialog_carro.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
            Dialog_carro.setVisible(true);

        }
        if (tabla_plan_ruta.getSelectedColumn() == 6||tabla_plan_ruta.getSelectedColumn() == 7) {
            anexo.table_conductor(tabla_conductor);
            Dimension desktopSize = View_Escritorio.desktopPane.getSize();
            Dimension FrameSize = Dialog_conductor.getSize();
            Dialog_conductor.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
            Dialog_conductor.setVisible(true);

        }
        }
        
        int fila = tabla_plan_ruta.rowAtPoint(evt.getPoint());
        int column = tabla_plan_ruta.getColumnModel().getColumnIndexAtX(evt.getX());
        int row = evt.getY() / tabla_plan_ruta.getRowHeight();
        if (row < tabla_plan_ruta.getRowCount() && row >= 0 && column < tabla_plan_ruta.getColumnCount() && column >= 0) {
            Object value = tabla_plan_ruta.getValueAt(row, column);
            if (value instanceof JButton) {
                ((JButton) value).doClick();
                JButton boton = (JButton) value;

                if (boton.getName().equals("btneliminar")) {

                    if (tabla_plan_ruta.getSelectedRow() != -1) {
                        /*DefaultTableModel model = (DefaultTableModel) tabla_productos_presentacion.getModel();
                        model.removeRow(tabla_productos_presentacion.getSelectedRow());*/
                        if (tabla_plan_ruta.getValueAt(fila, 1).toString().equals("")) {
                            DefaultTableModel model = (DefaultTableModel) tabla_plan_ruta.getModel();
                            model.removeRow(tabla_plan_ruta.getSelectedRow());
                        } else if (tabla_plan_ruta.getValueAt(fila, 1).toString().equals(null)) {
                            System.out.println("1");
                        } else {
                            /*  if (anexo.Eliminar_Anexo(tabla_plan_ruta.getValueAt(tabla_plan_ruta.getSelectedRow(), 1).toString()).equals("exito")) {
                                DefaultTableModel model = (DefaultTableModel) tabla_plan_ruta.getModel();
                            model.removeRow(tabla_plan_ruta.getSelectedRow());
                            }else{
                             Function_ShowMessageDialog.ShowMessageDialogdelete(null);
                            
                            }*/

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

    }//GEN-LAST:event_tabla_plan_rutaMouseClicked

    private void cbx_entregaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_entregaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_entregaKeyPressed

    private void btn_buscar_cod_compraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscar_cod_compraActionPerformed
        // TODO add your handling code here:
        anexo.table_venta(tabla_compra);
        Dimension desktopSize = View_Escritorio.desktopPane.getSize();
        Dimension FrameSize = Dialog_venta.getSize();
        Dialog_venta.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        Dialog_venta.setVisible(true);
    }//GEN-LAST:event_btn_buscar_cod_compraActionPerformed

    private void txt_busqueda_plan_rutaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busqueda_plan_rutaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            String id_usuario = new Controller_Plan_Ruta().plan_ruta(txt_busqueda_plan_ruta.getText());

            if (id_usuario.equals("0")) {

                System.out.println("View.View_Anexo_9.txt_busqueda_anexoKeyPressed()3" + id_usuario);
                anexo.tabla_plan_ruta(tabla_plan_ruta,txt_busqueda_plan_ruta.getText());
                txt_busqueda_plan_ruta.setEnabled(false);
                cbx_entrega.setEnabled(false);

            } else if (id_usuario.equals("fallo")) {
                System.out.println("View.View_Anexo_9.txt_busqueda_anexoKeyPressed()2" + id_usuario);

            } else if (id_usuario != "0") {
                txt_busqueda_plan_ruta.setEnabled(false);
                cbx_entrega.setEnabled(false);
                anexo.tabla_plan_ruta(tabla_plan_ruta, txt_busqueda_plan_ruta.getText());

            } else {
                System.out.println("View.View_Anexo_9.txt_busqueda_anexoKeyPressed()4" + id_usuario);

            }
            txt_busqueda.requestFocus();
            btn_registrar.setEnabled(true);
            btn_cancelar.setEnabled(true);
            btn_eliminar.setEnabled(true);
            btn_nuevo.setEnabled(true);
            /* txtcod_empresa.setEnabled(false);
            txt_ruc.requestFocus();*/
            evt.consume();
        }


    }//GEN-LAST:event_txt_busqueda_plan_rutaKeyPressed

    private void txt_busqueda_plan_rutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_busqueda_plan_rutaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_busqueda_plan_rutaActionPerformed

    private void txt_busquedaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busquedaKeyPressed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_txt_busquedaKeyPressed

    private void txt_busqueda_plan_rutaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busqueda_plan_rutaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_busqueda_plan_rutaKeyReleased

    private void txt_busqueda_plan_rutaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busqueda_plan_rutaKeyTyped
        // TODO add your handling code here:
        /*  Character letra = evt.getKeyChar();
        if(Character.isLetter(letra) || (evt.getKeyChar() == KeyEvent.VK_SPACE) || 
          (evt.getKeyChar() == KeyEvent.VK_BACK_SPACE) || (evt.getKeyChar() == KeyEvent.VK_MINUS)){
            evt.setKeyChar(Character.toUpperCase(letra));
        }else{
           evt.consume();
        }

       String texto = txt_busqueda_anexo.getText();
              if( texto.length() > 0)
              //eliminar primer caracter si no es una letra
              if(!Character.isLetter(texto.charAt(0))){
                texto = texto.length() > 1 ? texto.substring(1) : "";
              }

              // reemplazar guiones duplicados por un guion
              texto = texto.replaceAll("[-]+", "-");
              // reemplazar espacios duplicados por un espacio
              texto = texto.replaceAll("[ ]+", "");



              txt_busqueda_anexo.setText(texto);

        if(txt_busqueda_anexo.getText().length()>49){
            evt.consume(); //hace que esa pulsación de tecla se rechace.
            Toolkit.getDefaultToolkit().beep(); //sonido de no aceptar más caracteres.
        }*/
    }//GEN-LAST:event_txt_busqueda_plan_rutaKeyTyped

    private void cbx_entrega1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbx_entrega1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_entrega1KeyPressed

    private void txt_busqueda_anexo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_busqueda_anexo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_busqueda_anexo1ActionPerformed

    private void txt_busqueda_anexo1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busqueda_anexo1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_busqueda_anexo1KeyPressed

    private void txt_busqueda_anexo1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busqueda_anexo1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_busqueda_anexo1KeyReleased

    private void txt_busqueda_anexo1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busqueda_anexo1KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_busqueda_anexo1KeyTyped

    private void btn_buscar_cod_compra1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscar_cod_compra1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_buscar_cod_compra1ActionPerformed

    private void tabla_anexo1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_anexo1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_anexo1MouseClicked

    private void btn_agregar_fila1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregar_fila1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_agregar_fila1ActionPerformed

    private void txt_busqueda1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busqueda1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_busqueda1KeyPressed

    private void btn_nuevo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nuevo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_nuevo1ActionPerformed

    private void btn_registrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_registrar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_registrar1ActionPerformed

    private void btn_eliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_eliminar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_eliminar1ActionPerformed

    private void btn_cancelar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_cancelar1ActionPerformed

    private void btn_buscar_cod_compra2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscar_cod_compra2ActionPerformed
        // TODO add your handling code here:
        Conexion cxn = new Conexion();
        Map p = new HashMap();
        p.put("cod_anexo", txt_busqueda_plan_ruta.getText());

        //sub report ""src\\Reportes\\Jaspersoft_Cardex_Compra.jasper""
        JasperReport report;
        JasperPrint print;

        try {
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                    + "/src/Reportes/Jaspersoft_anexo9.jrxml");
            print = JasperFillManager.fillReport(report, p, cxn.conectardb());
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("ANEXO 9");

            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de excel", "xls");
            chooser.setFileFilter(filter);
            chooser.setDialogTitle("Guardar archivo");
            chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                String ruta = chooser.getSelectedFile().toString().concat(".xls");

                JRXlsExporter xlsExporter = new JRXlsExporter();

                xlsExporter.setExporterInput(new SimpleExporterInput(print));
                xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(ruta));
                SimpleXlsReportConfiguration xlsReportConfiguration = new SimpleXlsReportConfiguration();
                xlsReportConfiguration.setOnePagePerSheet(false);
                xlsReportConfiguration.setRemoveEmptySpaceBetweenRows(true);
                xlsReportConfiguration.setDetectCellType(false);
                xlsReportConfiguration.setWhitePageBackground(false);
                xlsExporter.setConfiguration(xlsReportConfiguration);

                xlsExporter.exportReport();
            }
            view.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btn_buscar_cod_compra2ActionPerformed

    private void txt_busqueda_productoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busqueda_productoKeyReleased
        // TODO add your handling code here:
        filtro_tabla(txt_busqueda_producto.getText().toUpperCase(), tabla_insittucion);
    }//GEN-LAST:event_txt_busqueda_productoKeyReleased

    private void txt_busqueda_productoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busqueda_productoKeyPressed
        // TODO add your handling code here:

    }//GEN-LAST:event_txt_busqueda_productoKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        Dimension desktopSize = View_Escritorio.desktopPane.getSize();
        Dimension FrameSize = Dialog_institucion.getSize();
        Dialog_institucion.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        Dialog_institucion.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbx_itenItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbx_itenItemStateChanged
        // TODO add your handling code here:
        anexo.tabla_institucion(tabla_insittucion, cbx_iten.getSelectedItem().toString());
    }//GEN-LAST:event_cbx_itenItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        anexo.add_plan_ruta(tabla_plan_ruta, cbx_item_ruta.getSelectedItem().toString());
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tabla_carroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_carroMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_carroMouseClicked

    private void tabla_carroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_carroKeyPressed
        // TODO add your handling code here:
        for (int i = Integer.parseInt(jTextField1.getText()); i <= Integer.parseInt(jTextField2.getText()); i++) {
            tabla_plan_ruta.setValueAt(tabla_carro.getValueAt(tabla_carro.getSelectedRow(), 0), i, 3);
            tabla_plan_ruta.setValueAt(tabla_carro.getValueAt(tabla_carro.getSelectedRow(), 1), i, 4);
            tabla_plan_ruta.setValueAt(tabla_carro.getValueAt(tabla_carro.getSelectedRow(), 1), i, 5);
        }
    }//GEN-LAST:event_tabla_carroKeyPressed

    private void tabla_conductorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_conductorMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabla_conductorMouseClicked

    private void tabla_conductorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_conductorKeyPressed
        // TODO add your handling code here:
        for (int i = Integer.parseInt(jTextField3.getText()); i <= Integer.parseInt(jTextField4.getText()); i++) {
            tabla_plan_ruta.setValueAt(tabla_conductor.getValueAt(tabla_conductor.getSelectedRow(), 0), i, 6);
            tabla_plan_ruta.setValueAt(tabla_conductor.getValueAt(tabla_conductor.getSelectedRow(), 1), i, 7);
        }
    }//GEN-LAST:event_tabla_conductorKeyPressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
         try {
            // TODO add your handling code here:

           new Controller_Institucion().exportarExcel_iten(tabla_plan_ruta);
        } catch (IOException ex) {

        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if (selecArchivo.showDialog(null, "Seleccionar archivo") == JFileChooser.APPROVE_OPTION) {
            archivo = selecArchivo.getSelectedFile();
            if (archivo.getName().endsWith("xls") || archivo.getName().endsWith("xlsx")) {
                //modeloE.Importar(archivo); 
                anexo.Subir_plan_ruta(tabla_plan_ruta, archivo);
                /* JOptionPane.showMessageDialog(null,
                       
                    modeloE.Importar(archivo) + "\n Formato ." + archivo.getName().substring(archivo.getName().lastIndexOf(".") + 1),
                    "IMPORTAR EXCEL", JOptionPane.INFORMATION_MESSAGE);*/
            } else {
                JOptionPane.showMessageDialog(null, "Elija un formato valido.");
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txt_busquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busquedaKeyReleased
        // TODO add your handling code here:
      //  filtro_tabla(txt_busqueda.getText(), tabla_plan_ruta);
    }//GEN-LAST:event_txt_busquedaKeyReleased

    void habilitar_proveedor() {

    }

    void deshabilitar_proveedor() {

    }

    void limpiar_proveedor() {
        //txt_id_persona.setText("");
        //txt_busqueda.setText("");

    }

    void filtro_tabla(String consulta, JTable jtableBuscar) {
        DefaultTableModel dm = (DefaultTableModel) jtableBuscar.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(dm);
        jtableBuscar.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(consulta));
    }

    void nuevo_compra() {
        txt_busqueda_plan_ruta.setText("");
        //txt_serie.setText("");

    }

    void deshabilitar_compra() {
        //txt_busqueda_compra_cod.setEnabled(false);

    }

    void habilitar_compra() {
        txt_busqueda_plan_ruta.setEnabled(true);

        cbx_entrega.setEnabled(true);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Dialog_carro;
    private javax.swing.JDialog Dialog_conductor;
    private javax.swing.JDialog Dialog_institucion;
    private javax.swing.JDialog Dialog_venta;
    public static javax.swing.JButton btn_agregar_fila1;
    private javax.swing.JButton btn_buscar_cod_compra;
    private javax.swing.JButton btn_buscar_cod_compra1;
    private javax.swing.JButton btn_buscar_cod_compra2;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_cancelar1;
    private javax.swing.JButton btn_eliminar;
    private javax.swing.JButton btn_eliminar1;
    private javax.swing.JButton btn_nuevo;
    private javax.swing.JButton btn_nuevo1;
    private javax.swing.JButton btn_registrar;
    private javax.swing.JButton btn_registrar1;
    private javax.swing.JComboBox<Entity_Entrega> cbx_entrega;
    private javax.swing.JComboBox<Entity_Entrega> cbx_entrega1;
    private javax.swing.JComboBox<String> cbx_item_ruta;
    private javax.swing.JComboBox<String> cbx_iten;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
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
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTable tabla_anexo1;
    private javax.swing.JTable tabla_carro;
    private javax.swing.JTable tabla_compra;
    private javax.swing.JTable tabla_conductor;
    private javax.swing.JTable tabla_insittucion;
    private javax.swing.JTable tabla_plan_ruta;
    private javax.swing.JTextField txt_busqueda;
    private javax.swing.JTextField txt_busqueda1;
    private javax.swing.JTextField txt_busqueda_anexo1;
    private javax.swing.JTextField txt_busqueda_compra;
    private javax.swing.JTextField txt_busqueda_plan_ruta;
    private javax.swing.JTextField txt_busqueda_producto;
    // End of variables declaration//GEN-END:variables
}
