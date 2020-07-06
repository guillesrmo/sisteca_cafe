/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Conexion.Conexion;
import Controller.Controller_Cardex_fisico;
import Entity.Entity_Almacen;
import Entity.Entity_Sunat_transaccion;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.FocusManager;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author pro
 */
public class View_Cardex_Fisico extends javax.swing.JInternalFrame {
    Controller_Cardex_fisico cardex=new Controller_Cardex_fisico();

    /**
     * Creates new form View_Cardex_Fisico
     */
    public View_Cardex_Fisico() {
        initComponents();
        Date date = new Date();
        datefecha1.setDate(date);
        datefecha2.setDate(date);
        txt_id_producto1.setEditable(false);
        txt_id_producto2.setEditable(false);
        txt_descripcion1.setEditable(false);
        txt_descripcion2.setEditable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Dialog_producto = new javax.swing.JDialog();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        txt_busqueda_producto = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla_pruducto = new javax.swing.JTable();
        Dialog_producto1 = new javax.swing.JDialog();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        txt_busqueda_producto1 = new javax.swing.JTextField();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabla_pruducto1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txt_cod_barra1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        txt_descripcion1 = new javax.swing.JTextField();
        txt_descripcion2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        txt_cod_barra2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_id_producto1 = new javax.swing.JTextField();
        txt_id_producto2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        datefecha1 = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        datefecha2 = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();

        Dialog_producto.setTitle("PRODUCTO");
        Dialog_producto.setAutoRequestFocus(false);
        Dialog_producto.setMinimumSize(new java.awt.Dimension(724, 417));
        Dialog_producto.setModal(true);
        Dialog_producto.setResizable(false);

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));

        txt_busqueda_producto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_busqueda_productoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txt_busqueda_producto)
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(txt_busqueda_producto, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        tabla_pruducto.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_pruducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_pruductoMouseClicked(evt);
            }
        });
        tabla_pruducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_pruductoKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tabla_pruducto);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
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
                .addContainerGap())
        );

        Dialog_producto1.setTitle("PRODUCTO");
        Dialog_producto1.setAutoRequestFocus(false);
        Dialog_producto1.setMinimumSize(new java.awt.Dimension(724, 417));
        Dialog_producto1.setModal(true);
        Dialog_producto1.setResizable(false);

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        txt_busqueda_producto1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_busqueda_producto1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txt_busqueda_producto1)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(txt_busqueda_producto1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));

        tabla_pruducto1.setModel(new javax.swing.table.DefaultTableModel(
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
        tabla_pruducto1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_pruducto1MouseClicked(evt);
            }
        });
        tabla_pruducto1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tabla_pruducto1KeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(tabla_pruducto1);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Dialog_producto1Layout = new javax.swing.GroupLayout(Dialog_producto1.getContentPane());
        Dialog_producto1.getContentPane().setLayout(Dialog_producto1Layout);
        Dialog_producto1Layout.setHorizontalGroup(
            Dialog_producto1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dialog_producto1Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        Dialog_producto1Layout.setVerticalGroup(
            Dialog_producto1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Dialog_producto1Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("CARDEX FISICO");
        setToolTipText("");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setText("+");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("+");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("INICIO DE PRODUCTO");

        jLabel2.setText("HASTA");

        jLabel3.setText("Fecha de inicio");

        datefecha1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                datefecha1KeyPressed(evt);
            }
        });

        jLabel7.setText("Fecha de fin");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_id_producto1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_id_producto2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt_cod_barra1)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_descripcion1))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txt_cod_barra2)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_descripcion2))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(datefecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(datefecha2, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txt_cod_barra1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_descripcion1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_id_producto1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(3, 3, 3)
                .addComponent(jLabel2)
                .addGap(1, 1, 1)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_cod_barra2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_descripcion2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_id_producto2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(datefecha1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(datefecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jButton3.setText("VISUALIZAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("DESING");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 38, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Conexion cxn=new Conexion();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = f.format(datefecha1.getDate());
        String fecha1 = f.format(datefecha2.getDate());
        Map p = new HashMap();
        p.put("PRODUCTO1", txt_id_producto1.getText());
        p.put("PRODUCTO2", txt_id_producto2.getText());
        p.put("FECHA1", fecha);
        p.put("FECHA2", fecha1);
        p.put("almacen_nombre",((Entity_Almacen) View_Escritorio.cbx_almacen.getSelectedItem()).getAlmacen_descr());
        p.put("almacen_id", ((Entity_Almacen) View_Escritorio.cbx_almacen.getSelectedItem()).getAlmacen_id());
        //sub report ""src\\Reportes\\Jaspersoft_Cardex_Compra.jasper""
        
        JasperReport report;
        JasperPrint print;

        try {
            report = JasperCompileManager.compileReport(new File("").getAbsolutePath()
                    + "/src/Reportes/Jaspersoft_Cardex.jrxml");
            print = JasperFillManager.fillReport(report, p, cxn.conectardb());
            JasperViewer view = new JasperViewer(print, false);
            view.setTitle("STOCK");
            view.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void datefecha1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_datefecha1KeyPressed
        // TODO add your handling code here:
       /* if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            System.out.println("View.View_Ingreso_Productos.date_fecha_emisionKeyPressed()");

        }*/
    }//GEN-LAST:event_datefecha1KeyPressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        try        
    {
        Process p = Runtime.getRuntime().exec("C:\\Program Files (x86)\\Jaspersoft\\iReport-5.6.0\\bin\\ireport.exe -C:\\Users\\memo\\Documents\\NetBeansProjects\\ERP_SC_Almacen\\src\\Reportes\\Jaspersoft_Cardex.jasper");  
       // Process p2 = Runtime.getRuntime().exec("C:\\Users\\memo\\Documents\\NetBeansProjects\\ERP_SC_Almacen\\src\\Reportes\\Jaspersoft_Cardex.jasper"); 
        p.waitFor();  
       //  p2.waitFor(); 
    }        
    catch ( IOException ioe )       
    {            
        ioe.printStackTrace();
    }   catch (InterruptedException ex) {         
            Logger.getLogger(View_Cardex_Fisico.class.getName()).log(Level.SEVERE, null, ex);
        }         
    }//GEN-LAST:event_jButton4ActionPerformed

    private void txt_busqueda_productoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busqueda_productoKeyReleased
        // TODO add your handling code here:
       // filtro_tabla(txt_busqueda_producto.getText().toUpperCase(), tabla_pruducto);
    }//GEN-LAST:event_txt_busqueda_productoKeyReleased

    private void tabla_pruductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_pruductoMouseClicked
        // TODO add your handling code here:
        txt_id_producto1.setText(""+tabla_pruducto.getValueAt(tabla_pruducto.getSelectedRow(), 1));
        txt_cod_barra1.setText(""+tabla_pruducto.getValueAt(tabla_pruducto.getSelectedRow(), 2));
        txt_descripcion1.setText(""+tabla_pruducto.getValueAt(tabla_pruducto.getSelectedRow(), 3));
        
        
        /*table_productos_compra.setValueAt(tabla_pruducto.getValueAt(tabla_pruducto.getSelectedRow(), 6), table_productos_compra.getSelectedRow() + salida.variable(), 6);
        table_productos_compra.setValueAt(tabla_pruducto.getValueAt(tabla_pruducto.getSelectedRow(), 7), table_productos_compra.getSelectedRow() + salida.variable(), 7);
        table_productos_compra.setValueAt(tabla_pruducto.getValueAt(tabla_pruducto.getSelectedRow(), 8), table_productos_compra.getSelectedRow() + salida.variable(), 8);
        table_productos_compra.setValueAt(tabla_pruducto.getValueAt(tabla_pruducto.getSelectedRow(), 9), table_productos_compra.getSelectedRow() + salida.variable(), 9);
        table_productos_compra.setValueAt(tabla_pruducto.getValueAt(tabla_pruducto.getSelectedRow(), 5), table_productos_compra.getSelectedRow() + salida.variable(), 11);
        Dialog_producto.dispose();
        txt_busqueda_producto.setText("");*/
    }//GEN-LAST:event_tabla_pruductoMouseClicked

    private void tabla_pruductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_pruductoKeyPressed
        // TODO add your handling code here:
     
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_id_producto1.setText(""+tabla_pruducto.getValueAt(tabla_pruducto.getSelectedRow(), 1));
            txt_cod_barra1.setText(""+tabla_pruducto.getValueAt(tabla_pruducto.getSelectedRow(), 2));
            txt_descripcion1.setText(""+tabla_pruducto.getValueAt(tabla_pruducto.getSelectedRow(), 3));
            Dialog_producto.dispose();
            txt_busqueda_producto.setText("");
        }
    }//GEN-LAST:event_tabla_pruductoKeyPressed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
       
        
        cardex.tabla_producto(tabla_pruducto,txt_cod_barra1.getText());
        Dimension desktopSize = View_Escritorio.desktopPane.getSize();
        Dimension FrameSize = Dialog_producto.getSize();
        Dialog_producto.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        Dialog_producto.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_busqueda_producto1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_busqueda_producto1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_busqueda_producto1KeyReleased

    private void tabla_pruducto1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_pruducto1MouseClicked
        // TODO add your handling code here:
        txt_id_producto2.setText(""+tabla_pruducto1.getValueAt(tabla_pruducto1.getSelectedRow(), 1));
        txt_cod_barra2.setText(""+tabla_pruducto1.getValueAt(tabla_pruducto1.getSelectedRow(), 2));
        txt_descripcion2.setText(""+tabla_pruducto1.getValueAt(tabla_pruducto1.getSelectedRow(), 3));
    }//GEN-LAST:event_tabla_pruducto1MouseClicked

    private void tabla_pruducto1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabla_pruducto1KeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            txt_id_producto2.setText(""+tabla_pruducto1.getValueAt(tabla_pruducto1.getSelectedRow(), 1));
            txt_cod_barra2.setText(""+tabla_pruducto1.getValueAt(tabla_pruducto1.getSelectedRow(), 2));
            txt_descripcion2.setText(""+tabla_pruducto1.getValueAt(tabla_pruducto1.getSelectedRow(), 3));
            Dialog_producto1.dispose();
            txt_busqueda_producto1.setText("");
        }
    }//GEN-LAST:event_tabla_pruducto1KeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        cardex.tabla_producto(tabla_pruducto1,txt_cod_barra2.getText());
        Dimension desktopSize = View_Escritorio.desktopPane.getSize();
        Dimension FrameSize = Dialog_producto1.getSize();
        Dialog_producto1.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
        Dialog_producto1.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog Dialog_producto;
    private javax.swing.JDialog Dialog_producto1;
    private com.toedter.calendar.JDateChooser datefecha1;
    private com.toedter.calendar.JDateChooser datefecha2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tabla_pruducto;
    private javax.swing.JTable tabla_pruducto1;
    private javax.swing.JTextField txt_busqueda_producto;
    private javax.swing.JTextField txt_busqueda_producto1;
    private javax.swing.JTextField txt_cod_barra1;
    private javax.swing.JTextField txt_cod_barra2;
    private javax.swing.JTextField txt_descripcion1;
    private javax.swing.JTextField txt_descripcion2;
    private javax.swing.JTextField txt_id_producto1;
    private javax.swing.JTextField txt_id_producto2;
    // End of variables declaration//GEN-END:variables
}