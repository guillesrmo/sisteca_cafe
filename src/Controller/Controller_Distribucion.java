package Controller;

import Comprobantes_E_SUNAT.CE_RENIEC;
import Conexion.Conexion;
import Config_Class.Render;
import Config_Class.MyCellRenderer;
import Entity.Entity_Almacen;
import Entity.Entity_Compra;
import Entity.Entity_Compra_Producto;
import Entity.Entity_Documento;
import Entity.Entity_Entrega;
import Entity.Entity_Entrega_venta;
import Entity.Entity_Moneda;
import Entity.Entity_Persona;
import Entity.Entity_Producto;
import Entity.Entity_Producto_Presentacion;
import Entity.Entity_Tipo_Comprobante;
import Entity.Entity_Tipo_afectacion;
import Entity.Entity_Venta;
import Entity.Entity_Venta_Producto;
import Function.FixedColumnTable;
import Function.Function_Component;
import Function.Function_Key_Event;
import Function.Function_ShowMessageDialog;
import View.View_Distribuciones;
import static View.View_Distribuciones.cbx_entrega;
import View.View_Escritorio;
import View.View_Salida_producto;
import static Web_Service.Service_RUC_DNI.peticionHttpGet;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.RoundingMode;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.Caret;
import javax.swing.text.MaskFormatter;
import org.json.JSONArray;
import org.json.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author memo
 */
public class Controller_Distribucion {

    private boolean[] editable = {false, false, false, false, false, false, false, false, false, false,
        false, false, false, false, true, true, false, false, false, false,
        false, false, false, true, true, true, false, true, true, false,
        true, true, false, false, true, true, true, true, true, true, true};

    private boolean[] editable_fecha = {false, true, true, false, true, true, false, false, false};
    private Boolean estado = true;
    private Conexion cxn;
    DefaultTableModel model;

    public Controller_Distribucion() {

        cxn = new Conexion();
    }

    //calcular el número de veces que se repite un carácter en un String
    public void table_venta(JTable venta, String entrega, String iten,JScrollPane scrollbar) {
        
        JButton btnEliminar = new JButton("");
        btnEliminar.setName("btneliminar");
        btnEliminar.setIcon(new ImageIcon("src\\Icon\\icon_eliminar.png"));

        JButton btnCAE = new JButton("CAE");
        btnCAE.setIcon(new ImageIcon("src\\Icon\\icon_ver.png"));
        btnCAE.setName("btnCAE");

        JButton btnViewTablaCAE = new JButton("CAE");
        btnViewTablaCAE.setIcon(new ImageIcon("src\\Icon\\icon_tabla.png"));
        btnViewTablaCAE.setName("btnViewTablaCAE");

        JButton btnImprmirCAE = new JButton("CAE");
        btnImprmirCAE.setIcon(new ImageIcon("src\\Icon\\icon_imprimir.png"));
        btnImprmirCAE.setName("btnImprmirCAE");

        JButton btnPDFCAE = new JButton("CAE");
        btnPDFCAE.setName("btnPDFCAE");
        btnPDFCAE.setIcon(new ImageIcon("src\\Icon\\icon_pdf.png"));

        JButton btnGuiaCE = new JButton("GUIA E");
        btnGuiaCE.setIcon(new ImageIcon("src\\Icon\\icon_guia.png"));
        btnGuiaCE.setName("btnGuiaCE");

        JButton btnGuiaPDF = new JButton("GUIA");
        btnGuiaPDF.setIcon(new ImageIcon("src\\Icon\\icon_pdf.png"));
        btnGuiaPDF.setName("btnGuiaPDF");

        JButton btnImprmirGIA = new JButton("GUIA");
        btnImprmirGIA.setIcon(new ImageIcon("src\\Icon\\icon_imprimir.png"));
        btnImprmirGIA.setName("btnImprmirGIA");

        venta.setDefaultRenderer(Object.class, new MyCellRenderer());
        // venta.setDefaultRenderer(Object.class, new Render_Celda());
        try {
            DefaultTableModel model = new DefaultTableModel() {
                Class[] types = new Class[]{
                    java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                    java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class,
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class,
                    java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                    java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class,
                    java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                    java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                    java.lang.Object.class
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
            String sSQL1 = "select ven.venta_id,\n"
                    + "ti_com.comprobante_descrip,\n"
                    + "ti_do.documento_descrip,\n"
                    + "ins.institucion_codigo_modular,per.persona_razon_social,\n"
                    + "ven.venta_serie,\n"
                    + "ven.venta_numero,\n"
                    + " FORMAT(CONVERT(date,ven.venta_fecha_emision), N'dd-MM-yyyy '),\n"
                    + "al.almacen_descrip,\n"
                    + "guia_id,\n"
                    + "''+cast(guia_url as varchar(max)),ins.institucion_orden,ins.institucion_ruta,"
                    + "ISNULL((SELECT entrega_estado FROM entrega_venta WHERE entrega_venta=ven.venta_id),'PENDIENTE'),\n"
                    + "ISNULL((SELECT entrega_observacion FROM entrega_venta WHERE entrega_venta=ven.venta_id),''),"
                    + "ISNULL((SELECT entrega_encargado FROM entrega_venta WHERE entrega_venta=ven.venta_id),'00000000'),"
                    + "ISNULL(( SELECT resp.responsable_nombre FROM entrega_venta entre\n"
                    + "inner join persona_responsable resp\n"
                    + "on resp.responsable_dni=entre.entrega_encargado WHERE entrega_venta=ven.venta_id),''),"
                    + "ISNULL((SELECT entrega_fecha FROM entrega_venta WHERE entrega_venta=ven.venta_id),(FORMAT(CONVERT(date, GETDATE()), N'yyyy-MM-dd'))),"
                    + "ISNULL((SELECT entrega_hora FROM entrega_venta WHERE entrega_venta=ven.venta_id),''),"
                    + "ISNULL((SELECT entrega_acta FROM entrega_venta WHERE entrega_venta=ven.venta_id),''),"
                    + "ISNULL((SELECT entrega_dni_acta FROM entrega_venta WHERE entrega_venta=ven.venta_id),''),"
                    + "ISNULL((SELECT entrega_nombre_acta FROM entrega_venta WHERE entrega_venta=ven.venta_id),''),"
                    + "ISNULL((SELECT entrega_observacion_acta FROM entrega_venta WHERE entrega_venta=ven.venta_id),'')"
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
                    + "where ven.venta_concepto=4 and ven.venta_entrega='" + entrega + "' and cast(ins.institucion_iten as varchar(max)) ='" + iten + "'\n"
                    + "order  by ins.institucion_ruta asc ";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"ELI", "", "COMPROBANTE", "DOCUMENTO", "CODIGO MODULAR", "COLEGIO",
                "SERIE", "NUMERO", "FECHA", "ALMACEN", "GUIA_ID",
                "GUIA URL", "ORDEN", "RUTA", "CAE", "GUI",
                "CAE", "", "", "", "",
                "", "", "ESTADO", "OBSERVACION", "DNI", "NOMBRE", "FECHA", "HORA", "DJT", "NRO DE ACTA", "BUSCAR DNI", "NOMBRE DE CAE", "VALIDAR"});
            while (rs.next()) {
                //    System.out.println("Controller.Controller_Distribucion.table_venta()"+rs.getString(13));
                String hora = "";
                try {
                    LocalTime ingreso = LocalTime.parse("07:00");
                    LocalTime salida = LocalTime.parse(rs.getString(19));
                    int minutes = (int) ChronoUnit.MINUTES.between(ingreso, salida);
                    if (minutes > (60 * 7)) {
                        hora = ("DJT");
                    } else {
                        hora = ("NO DJT");
                    }
                } catch (Exception e) {
                }
                model.addRow(new Object[]{btnEliminar, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                    rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
                    rs.getString(13), false, false, btnCAE, btnViewTablaCAE, btnImprmirCAE, btnPDFCAE, btnGuiaCE, btnGuiaPDF, btnImprmirGIA,
                    rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18), rs.getString(19), hora, rs.getString(20), rs.getString(21), rs.getString(22), rs.getString(23)});
            }
            cxn.desconectar();
            venta.setModel(model);
            Function_Component.JTable(venta);
            venta.getColumnModel().getColumn(1).setMaxWidth(0);
            venta.getColumnModel().getColumn(1).setMinWidth(0);
            venta.getColumnModel().getColumn(1).setPreferredWidth(0);
            venta.getColumnModel().getColumn(2).setMaxWidth(0);
            venta.getColumnModel().getColumn(2).setMinWidth(0);
            venta.getColumnModel().getColumn(2).setPreferredWidth(0);
            venta.getColumnModel().getColumn(3).setMaxWidth(0);
            venta.getColumnModel().getColumn(3).setMinWidth(0);
            venta.getColumnModel().getColumn(3).setPreferredWidth(0);
            venta.getColumnModel().getColumn(5).setPreferredWidth(150);
            venta.getColumnModel().getColumn(10).setMaxWidth(0);
            venta.getColumnModel().getColumn(10).setMinWidth(0);
            venta.getColumnModel().getColumn(10).setPreferredWidth(0);
            venta.getColumnModel().getColumn(23).setMaxWidth(100);
            venta.getColumnModel().getColumn(23).setMinWidth(100);
            venta.getColumnModel().getColumn(23).setPreferredWidth(100);
            venta.getColumnModel().getColumn(24).setPreferredWidth(350);
            venta.getColumnModel().getColumn(25).setMaxWidth(100);
            venta.getColumnModel().getColumn(25).setMinWidth(100);
            venta.getColumnModel().getColumn(25).setPreferredWidth(100);
            venta.getColumnModel().getColumn(26).setPreferredWidth(250);
            venta.getColumnModel().getColumn(27).setPreferredWidth(100);
            
            venta.getColumnModel().getColumn(30).setMaxWidth(100);
            venta.getColumnModel().getColumn(30).setMinWidth(100);
            venta.getColumnModel().getColumn(30).setPreferredWidth(100);
            venta.getColumnModel().getColumn(31).setMaxWidth(100);
            venta.getColumnModel().getColumn(31).setMinWidth(100);
            venta.getColumnModel().getColumn(31).setPreferredWidth(100);
            venta.getColumnModel().getColumn(32).setPreferredWidth(350);
            venta.getColumnModel().getColumn(33).setPreferredWidth(150);

            setcbx_impresora(venta, venta.getColumnModel().getColumn(23));
            settext_descripcion(venta, venta.getColumnModel().getColumn(24));
            settext_dni(venta, venta.getColumnModel().getColumn(25));
            settext_fecha(venta, venta.getColumnModel().getColumn(27));
            settext_hora(venta, venta.getColumnModel().getColumn(28));
            settext_acta(venta, venta.getColumnModel().getColumn(30));
            settext_dni_reniec(venta, venta.getColumnModel().getColumn(31));
            venta.setDragEnabled(false);
            venta.getTableHeader().setReorderingAllowed(false);
            venta.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
           
         
         /*   FixedColumnTable fct = new FixedColumnTable(6, scrollbar);
               venta.setAutoCreateRowSorter(true);
venta.setRowSorter(venta.getRowSorter());
venta.setUpdateSelectionOnSort(true);
venta.setUpdateSelectionOnSort(false);*/
           

            /*DefaultTableCellRenderer render = (DefaultTableCellRenderer) venta.getTableHeader().getDefaultRenderer();
            render.setHorizontalAlignment(JLabel.CENTER);
            jtableHeader.setDefaultRenderer(render);*/
            ((DefaultTableCellRenderer) venta.getTableHeader().getDefaultRenderer())
                    .setHorizontalAlignment(JLabel.CENTER);
            venta.getTableHeader().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 1) {
                        int col = venta.columnAtPoint(e.getPoint());
                        if (col == 14) {
                            for (int i = 0; i < venta.getRowCount(); i++) {
                                venta.setValueAt(true, i, 14);

                            }
                        }
                    } else if (e.getClickCount() == 2) {
                        int col = venta.columnAtPoint(e.getPoint());
                        if (col == 14) {
                            for (int i = 0; i < venta.getRowCount(); i++) {
                                venta.setValueAt(false, i, 14);

                            }
                        }
                    }

                    if (e.getClickCount() == 1) {
                        int col = venta.columnAtPoint(e.getPoint());
                        if (col == 15) {
                            for (int i = 0; i < venta.getRowCount(); i++) {
                                venta.setValueAt(true, i, 15);

                            }
                        }
                    } else if (e.getClickCount() == 2) {
                        int col = venta.columnAtPoint(e.getPoint());
                        if (col == 15) {
                            for (int i = 0; i < venta.getRowCount(); i++) {
                                venta.setValueAt(false, i, 15);

                            }
                        }
                    }

                }
            });

            /*   DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        venta.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
       .setCellRenderer(centerRenderer);*/
            //venta.getColumnModel().getColumn(8).setCellRenderer(new MyCellRenderer());
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }

    public void setcbx_impresora(JTable tabla, TableColumn columna) {

        JComboBox lista_impresora = new JComboBox();

        lista_impresora.addItem("PENDIENTE");
        lista_impresora.addItem("ENTREGADO");
        lista_impresora.addItem("OBSERVADO");
        lista_impresora.addItem("SINCRONIZADO");

        Function_Component.JComboBox(lista_impresora);
        columna.setCellEditor(new DefaultCellEditor(lista_impresora));
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        lista_impresora.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent evt) {

                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

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

    public void table_carga(JTable venta, String iten, String entrega, String ruta) {
        try {
            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String sSQL1 = "select\n"
                    + "max(cast( vent.venta_id as varchar(max))) AS venta_id,\n"
                    + "max(cast( pro_p.present_descripcion as varchar(max))) AS present_descripcion,\n"
                    + "max(cast( pro_p.present_peso as varchar(max))) AS present_peso,\n"
                    + "sum(det_venta.det_ven_pro_cantidad*pro_p.present_cantidad/*pro_p.present_peso*/) AS TOTAL,\n"
                    + "(SELECT fa.familia_descrip  FROM producto pro\n"
                    + "inner join familia fa\n"
                    + "on fa.familia_id=pro.producto_familia\n"
                    + "inner join producto_presentacion pre\n"
                    + "on pre.present_cod_producto=pro.producto_id where pre.present_id=max(cast(pro_p.present_id as varchar(max)))) AS MARCA,\n"
                    + "det_venta.det_ven_pro_cod_lote,\n"
                    + "det_venta.det_ven_pro_fecha,\n"
                    + "((sum(det_venta.det_ven_pro_cantidad*pro_p.present_cantidad)-((CONVERT(int,SUM((det_venta.det_ven_pro_cantidad)))%CONVERT(int,max(cast( pro.producto_cantidad as INT))))))/max(cast( pro.producto_cantidad as varchar(max))))  as embalaje,max(cast( ti_po.embajale_descr as varchar(max))) AS embajale_descr,\n"
                    + "max(cast( ti_uni.unidad_cod as varchar(max))) AS unidad_cod,\n"
                    + "((CONVERT(int,SUM((det_venta.det_ven_pro_cantidad)))%CONVERT(int,max(cast( pro.producto_cantidad as INT)))))as unidad,\n"
                    + "max(cast( pro.producto_cantidad as INT)) AS producto_cantidad\n"
                    + "from detalle_venta_producto det_venta\n"
                    + "inner join venta vent\n"
                    + "on vent.venta_id=det_venta.det_ven_pro_id_venta\n"
                    + "inner join producto_presentacion pro_p\n"
                    + "on pro_p.present_id=det_venta.det_ven_pro_id_producto\n"
                    + "inner join producto pro\n"
                    + "on pro.producto_id=pro_p.present_cod_producto\n"
                    + "inner join tipo_afectacion ti_af\n"
                    + "on ti_af.afectacion_id=det_venta.det_ven_pro_id_igv\n"
                    + "inner join tipo_unidad ti_uni\n"
                    + "on ti_uni.unidad_id=pro_p.present_cod_unidad\n"
                    + "INNER join persona pers\n"
                    + "on  pers.persona_id=vent.venta_cliente\n"
                    + "inner join institucion ins\n"
                    + "on ins.institucion_codigo_modular=pers.persona_institucion\n"
                    + "INNER JOIN tipo_embalaje ti_po\n"
                    + "on ti_po.embalaje_id=pro.producto_embalaje\n"
                    + "where cast(ins.institucion_ruta as varchar(max))  ='" + ruta + "' and cast(ins.institucion_iten as varchar(max)) ='" + iten + "'  and vent.venta_entrega='" + entrega + "'\n"
                    + "group by det_venta.det_ven_pro_fecha,det_venta.det_ven_pro_cod_lote,pro.producto_id";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"", "DESCR.", "PESO", "TOTAL", "REPOSICION", "MARCA", "LOTE", "FECHA VEN.", "EMBALAJE", "TOTAL UND", "TOTAL ENVASE"});
            while (rs.next()) {

                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), "", rs.getString(5),
                    rs.getString(6), rs.getString(7), rs.getString(8) + " " + rs.getString(9), rs.getString(11) + " UND", rs.getString(12)});
            }
            cxn.desconectar();
            venta.setModel(model);
            Function_Component.JTable(venta);
            venta.getColumnModel().getColumn(0).setMaxWidth(0);
            venta.getColumnModel().getColumn(0).setMinWidth(0);
            venta.getColumnModel().getColumn(0).setPreferredWidth(0);
            venta.setDragEnabled(false);
            venta.getTableHeader().setReorderingAllowed(false);
            // venta.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }

    public void table_carga_zona(JTable venta, String entrega, String ruta) {
        try {
            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String sSQL1 = "select\n"
                    + "max(cast( vent.venta_id as varchar(max))) AS venta_id,\n"
                    + "max(cast( pro_p.present_descripcion as varchar(max))) AS present_descripcion,\n"
                    + "max(cast( pro_p.present_peso as varchar(max))) AS present_peso,\n"
                    + "sum(det_venta.det_ven_pro_cantidad*pro_p.present_cantidad/*pro_p.present_peso*/) AS TOTAL,\n"
                    + "(SELECT fa.familia_descrip  FROM producto pro\n"
                    + "inner join familia fa\n"
                    + "on fa.familia_id=pro.producto_familia\n"
                    + "inner join producto_presentacion pre\n"
                    + "on pre.present_cod_producto=pro.producto_id where pre.present_id=max(cast(pro_p.present_id as varchar(max)))) AS MARCA,\n"
                    + "det_venta.det_ven_pro_cod_lote,\n"
                    + "det_venta.det_ven_pro_fecha,\n"
                    + "((sum(det_venta.det_ven_pro_cantidad*pro_p.present_cantidad)-((CONVERT(int,SUM((det_venta.det_ven_pro_cantidad)))%CONVERT(int,max(cast( pro.producto_cantidad as INT))))))/max(cast( pro.producto_cantidad as varchar(max))))  as embalaje,max(cast( ti_po.embajale_descr as varchar(max))) AS embajale_descr,\n"
                    + "max(cast( ti_uni.unidad_cod as varchar(max))) AS unidad_cod,\n"
                    + "((CONVERT(int,SUM((det_venta.det_ven_pro_cantidad)))%CONVERT(int,max(cast( pro.producto_cantidad as INT)))))as unidad,\n"
                    + "max(cast( pro.producto_cantidad as INT)) AS producto_cantidad\n"
                    + "from detalle_venta_producto det_venta\n"
                    + "inner join venta vent\n"
                    + "on vent.venta_id=det_venta.det_ven_pro_id_venta\n"
                    + "inner join producto_presentacion pro_p\n"
                    + "on pro_p.present_id=det_venta.det_ven_pro_id_producto\n"
                    + "inner join producto pro\n"
                    + "on pro.producto_id=pro_p.present_cod_producto\n"
                    + "inner join tipo_afectacion ti_af\n"
                    + "on ti_af.afectacion_id=det_venta.det_ven_pro_id_igv\n"
                    + "inner join tipo_unidad ti_uni\n"
                    + "on ti_uni.unidad_id=pro_p.present_cod_unidad\n"
                    + "INNER join persona pers\n"
                    + "on  pers.persona_id=vent.venta_cliente\n"
                    + "inner join institucion ins\n"
                    + "on ins.institucion_codigo_modular=pers.persona_institucion\n"
                    + "INNER JOIN tipo_embalaje ti_po\n"
                    + "on ti_po.embalaje_id=pro.producto_embalaje\n"
                    + "where CAST(institucion_comunidad_indi AS VARCHAR(MAX)) ='" + ruta + "'   and vent.venta_entrega='" + entrega + "'\n"
                    + "group by det_venta.det_ven_pro_fecha,det_venta.det_ven_pro_cod_lote,pro.producto_id";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"", "DESCR.", "PESO", "TOTAL", "REPOSICION", "MARCA", "LOTE", "FECHA VEN.", "EMBALAJE", "TOTAL UND", "TOTAL ENVASE"});
            while (rs.next()) {

                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), "", rs.getString(5),
                    rs.getString(6), rs.getString(7), rs.getString(8) + " " + rs.getString(9), rs.getString(11) + " UND", rs.getString(12)});
            }
            cxn.desconectar();
            venta.setModel(model);
            Function_Component.JTable(venta);
            venta.getColumnModel().getColumn(0).setMaxWidth(0);
            venta.getColumnModel().getColumn(0).setMinWidth(0);
            venta.getColumnModel().getColumn(0).setPreferredWidth(0);
            venta.setDragEnabled(false);
            venta.getTableHeader().setReorderingAllowed(false);
            // venta.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }

    public void table_venta_fecha(JTable venta, String iten, String entrega, String ruta) {
        DefaultTableModel model = new DefaultTableModel() {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class,
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return editable_fecha[column];
            }
        };
        try {
            String sSQL1 = "SELECT  ven.venta_id,ven.venta_fecha_emision,ven.venta_glosa,\n"
                    + "guia.guia_id,guia.guia_fecha_emision,guia.guia_fecha_inicio_traslado,ins.institucion_codigo_modular,ins.institucion_nombre,ins.institucion_ruta  FROM venta ven\n"
                    + "inner join persona per\n"
                    + "on per.persona_id=ven.venta_cliente\n"
                    + "inner join institucion ins\n"
                    + "on ins.institucion_codigo_modular=per.persona_institucion\n"
                    + "inner join guia_remision guia\n"
                    + "on guia.guia_venta=ven.venta_id\n"
                    + "where institucion_ruta='" + ruta + "' and ven.venta_entrega='" + entrega + "' and cast(ins.institucion_iten as varchar (max))='" + iten + "'";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"VENTA_ID", "VENTA_FECHA EMISION", "VENTA_GLOSA", "GUIA", "FECHA_EMISION GUIA", "FECHA INICIO TRASLADO", "CODIGO MODULAR", "INSTITUCION",
                "RUTA"});
            while (rs.next()) {

                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                    rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)});
            }
            cxn.desconectar();
            venta.setModel(model);
            Function_Component.JTable(venta);
            /*venta.getColumnModel().getColumn(0).setMaxWidth(0);
            venta.getColumnModel().getColumn(0).setMinWidth(0);
            venta.getColumnModel().getColumn(0).setPreferredWidth(0);*/
            venta.getTableHeader().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent evt) {
                    /*if (evt.getClickCount() == 2) {
                        String resultado = JOptionPane.showInputDialog("Introduzca DATO");
                        if (venta.columnAtPoint(evt.getPoint()) == 1) {
                            for (int i = 0; i < venta.getRowCount(); i++) {

                                venta.setValueAt(resultado, i, 1);

                            }
                        }
                        if (venta.columnAtPoint(evt.getPoint()) == 2) {

                            for (int i = 0; i < venta.getRowCount(); i++) {
                                venta.setValueAt(resultado, i, 2);

                            }
                        }
                    }*/
                }
            });

            venta.setDragEnabled(false);
            for (int i = 0; i < venta.getColumnCount(); i++) {
                settext_descripcion(venta, venta.getColumnModel().getColumn(i));

            }
            venta.getTableHeader().setReorderingAllowed(false);

            // venta.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }
    
     public void table_venta_fecha_todo(JTable venta, String iten, String entrega) {
        DefaultTableModel model = new DefaultTableModel() {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class,
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return editable_fecha[column];
            }
        };
        try {
            String sSQL1 = "SELECT  ven.venta_id,ven.venta_fecha_emision,ven.venta_glosa,\n"
                    + "guia.guia_id,guia.guia_fecha_emision,guia.guia_fecha_inicio_traslado,ins.institucion_codigo_modular,ins.institucion_nombre,ins.institucion_ruta  FROM venta ven\n"
                    + "inner join persona per\n"
                    + "on per.persona_id=ven.venta_cliente\n"
                    + "inner join institucion ins\n"
                    + "on ins.institucion_codigo_modular=per.persona_institucion\n"
                    + "inner join guia_remision guia\n"
                    + "on guia.guia_venta=ven.venta_id\n"
                    + "where  ven.venta_entrega='" + entrega + "' and cast(ins.institucion_iten as varchar (max))='" + iten + "'";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"VENTA_ID", "VENTA_FECHA EMISION", "VENTA_GLOSA", "GUIA", "FECHA_EMISION GUIA", "FECHA INICIO TRASLADO", "CODIGO MODULAR", "INSTITUCION",
                "RUTA"});
            while (rs.next()) {

                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                    rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)});
            }
            cxn.desconectar();
            venta.setModel(model);
            Function_Component.JTable(venta);
            /*venta.getColumnModel().getColumn(0).setMaxWidth(0);
            venta.getColumnModel().getColumn(0).setMinWidth(0);
            venta.getColumnModel().getColumn(0).setPreferredWidth(0);*/
            venta.getTableHeader().addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent evt) {
                    if (evt.getClickCount() == 2) {
                        String resultado = JOptionPane.showInputDialog("Introduzca DATO");
                        if (venta.columnAtPoint(evt.getPoint()) == 1) {
                            for (int i = 0; i < venta.getRowCount(); i++) {

                                venta.setValueAt(resultado, i, 1);

                            }
                        }
                        if (venta.columnAtPoint(evt.getPoint()) == 4) {

                            for (int i = 0; i < venta.getRowCount(); i++) {
                                venta.setValueAt(resultado, i, 4);

                            }
                        }
                        if (venta.columnAtPoint(evt.getPoint()) == 5) {

                            for (int i = 0; i < venta.getRowCount(); i++) {
                                venta.setValueAt(resultado, i, 5);

                            }
                        }
                    }
                }
            });

            venta.setDragEnabled(false);
            for (int i = 0; i < venta.getColumnCount(); i++) {
                settext_descripcion(venta, venta.getColumnModel().getColumn(i));

            }
            venta.getTableHeader().setReorderingAllowed(false);

            // venta.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }

    public void table_pequin(JTable venta, String iten, String entrega) {
        try {
            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String sSQL1 = "select ven.venta_id,\n"
                    + "ins.institucion_codigo_modular,\n"
                    + "ins.institucion_nombre,\n"
                    + "ins.institucion_usuario,\n"
                    + "ins.institucion_direccion,\n"
                    + "ins.institucion_centro_poblado,\n"
                    + "ins.institucion_distrito,\n"
                    + "guia.guia_peso_bruto,\n"
                    + "cast(ven.venta_serie  as varchar(max))+' - '+cast( ven.venta_numero  as varchar(max)),\n"
                    + "cast(car.carro_nombre  as varchar(max))+' - '+cast( car.carro_placa  as varchar(max))  ,\n"
                    + "cond.coductor_nombre,cond.coductor_lincencia,\n"
                    + "trans.transportista_dni_ruc,\n"
                    + "ins.institucion_ruta,ins.institucion_nivel from venta ven\n"
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
                    + "inner join carro car\n"
                    + "on car.carro_placa=guia.guia_placa\n"
                    + "inner join conductor cond\n"
                    + "on cond.coductor_dni_ruc=guia_conductor\n"
                    + "inner join transportista trans\n"
                    + "on trans.transportista_dni_ruc=guia_transportista\n"
                    + "where ven.venta_concepto=4 and ven.venta_entrega='" + entrega + "' and cast(ins.institucion_iten as varchar(max)) ='" + iten + "' /*ven.venta_id='+id_Venta+'*/ \n"
                    + "order by ins.institucion_ruta asc ";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);
            DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
            separadoresPersonalizados.setDecimalSeparator('.');
            DecimalFormat formato2 = new DecimalFormat("################.###", separadoresPersonalizados);
            model.setColumnIdentifiers(new Object[]{"", "VENTA_ID", "CODIGO_MODULAR", "INSTI.", "USUARIOS", "DIRECCION",
                "CENTRO POBLADO", "DISTRITO", "PESO", "GUIA", "CARRO + PLACA", "CONDUCTOR", "LICENCIA", "RUC TRAMSPORTISTA",
                "FECHA ENTREGA", "FECHA DESPACHO", "RUTA", "EMBALADO", "DISTRIBUIDOR LIDER", ""});

            for (int i = 0; i < 7; i++) {
                model.addRow(new Object[]{"", "", "", "", "", "",
                    "", "", "", "", "", "", "", "", "", "",
                    "", "", "", ""});
            }
            int contador = 1;
            while (rs.next()) {

                model.addRow(new Object[]{contador, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                    rs.getString(6), rs.getString(7), formato2.format(rs.getDouble(8)), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), "", "",
                    rs.getString(14), "", "", rs.getString(15)});
                contador++;
            }
            cxn.desconectar();
            venta.setModel(model);
            Function_Component.JTable(venta);
            venta.getColumnModel().getColumn(0).setMaxWidth(0);
            venta.getColumnModel().getColumn(0).setMinWidth(0);
            venta.getColumnModel().getColumn(0).setPreferredWidth(0);
            venta.setDragEnabled(false);
            venta.getTableHeader().setReorderingAllowed(false);
            // venta.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }

    public String table_lote(JTable tabla_lote, String id_producto) {
        String respuesta = "";
        try {
            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String sSQL1 = "select(select top 1 det_com_pro_id_compra from detalle_compra_producto where det_com_pro_id_producto='" + id_producto + "') id_compra,det_com.det_com_pro_fecha_venc,\n"
                    + "det_com.det_com_pro_cod_lote,\n"
                    + "((ISNULL((select  sum(dt_com.det_com_pro_cantidad*pre.present_cantidad) from producto pro\n"
                    + "inner join  producto_presentacion pre\n"
                    + "on pre.present_cod_producto=pro.producto_id\n"
                    + "inner join detalle_compra_producto dt_com\n"
                    + "on dt_com.det_com_pro_id_producto=pre.present_id\n"
                    + "where pro.producto_id=(select top 1 present_cod_producto from producto_presentacion  where present_id='" + id_producto + "')  and dt_com.det_com_pro_cod_lote=det_com.det_com_pro_cod_lote\n"
                    + "group by dt_com.det_com_pro_cod_lote),0))-(ISNULL((select  sum(dt_ven.det_ven_pro_cantidad*pre.present_cantidad) from producto pro\n"
                    + "inner join  producto_presentacion pre\n"
                    + "on pre.present_cod_producto=pro.producto_id\n"
                    + "inner join detalle_venta_producto dt_ven\n"
                    + "on dt_ven.det_ven_pro_id_producto=pre.present_id\n"
                    + "where pro.producto_id=(select top 1 present_cod_producto from producto_presentacion  where present_id='" + id_producto + "')  and dt_ven.det_ven_pro_cod_lote=det_com.det_com_pro_cod_lote\n"
                    + "group by dt_ven.det_ven_pro_cod_lote),0)))as STOCK_LOTE, sum(present.present_prec_venta) from\n"
                    + "producto produc\n"
                    + "right JOIN producto_presentacion present\n"
                    + "on present.present_cod_producto =produc.producto_id\n"
                    + "right join detalle_compra_producto det_com\n"
                    + "on det_com.det_com_pro_id_producto=present.present_id\n"
                    + "where produc.producto_id=(select top 1 present_cod_producto from producto_presentacion  where present_id='" + id_producto + "') \n"
                    + "group by produc.producto_id,det_com.det_com_pro_cod_lote,det_com.det_com_pro_fecha_venc";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"ID", "FECHA", "COD_LOTE", "CANTIDAD"});
            /* while (rs.next()) {

                   System.out.println("Controller.Controller_Venta.table_lote()"+rs.getString(1));
                }*/
            // if (rs.next()) {

            while (rs.next()) {

                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)});
            }

            /* } else {
                respuesta = "ERROR";
            }*/
            cxn.desconectar();
            tabla_lote.setModel(model);
            Function_Component.JTable(tabla_lote);
            tabla_lote.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla_lote.getColumnModel().getColumn(0).setMinWidth(0);
            tabla_lote.getColumnModel().getColumn(0).setPreferredWidth(0);
            /*tabla_igv.getColumnModel().getColumn(1).setMaxWidth(0);
            tabla_igv.getColumnModel().getColumn(1).setMinWidth(0);
            tabla_igv.getColumnModel().getColumn(1).setPreferredWidth(0);
            tabla_igv.getColumnModel().getColumn(3).setMaxWidth(0);
            tabla_igv.getColumnModel().getColumn(3).setMinWidth(0);
            tabla_igv.getColumnModel().getColumn(3).setPreferredWidth(0);*/

            tabla_lote.setDragEnabled(false);
            tabla_lote.getTableHeader().setReorderingAllowed(false);
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        return respuesta;
    }

    public void delete(String id_Venta) {

        try {
            String sSQL1 = "EXEC	[dbo].[delete_distribucion]\n"
                    + "		@id_producto = N'" + id_Venta + "'";
            cxn.conectardb().createStatement().executeUpdate(sSQL1);
            cxn.desconectar();
        } catch (Exception e) {
            System.out.println("" + e);

        }

    }

    public void Entrega_venta(Entity_Entrega_venta entrega) {

        try {
            String sSQL1 = "EXEC	[dbo].[InsertaTablaentrega]\n"
                    + "		@entrega_venta = N'" + entrega.getEntrega_venta() + "',\n"
                    + "		@entrega_estado = N'" + entrega.getEntrega_estado() + "',\n"
                    + "		@entrega_observacion = N'" + entrega.getEntrega_observacion() + "',\n"
                    + "		@entrega_encargado = N'" + entrega.getEntrega_encargado() + "',\n"
                    + "		@entrega_hora = N'" + entrega.getEntrega_hora() + "',\n"
                    + "		@entrega_fecha = N'" + entrega.getEntrega_fecha() + "',\n"
                    + "		@entrega_dni_acta = N'" + entrega.getEntrega_dni_acta() + "',\n"
                    + "		@entrega_nombre_acta = N'" + entrega.getEntrega_nombre_acta() + "',\n"
                    + "		@entrega_acta = N'" + entrega.getEntrega_acta() + "',\n"
                    + "		@entrega_observacion_acta = N'" + entrega.getEntrega_observacion_acta() + "'";
            //   System.out.println("Controller.Controller_Distribucion.Entrega_venta()"+sSQL1);
            cxn.conectardb().createStatement().executeUpdate(sSQL1);
            cxn.desconectar();
        } catch (Exception e) {
            System.out.println("" + e);

        }

    }

    public void tabla_producto(JTable producto, String buscar) {
        try {
            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String sSQL1 = "select det_venta.det_ven_pro_id,\n"
                    + "pro_p.present_id,pro_p.present_cod_barra,pro_p.present_descripcion,det_venta.det_ven_pro_cantidad,\n"
                    + "pro_p.present_peso,ti_uni.unidad_cod,det_venta.det_ven_pro_fecha,(SELECT fa.familia_descrip  FROM producto pro\n"
                    + "inner join familia fa\n"
                    + "on fa.familia_id=pro.producto_familia \n"
                    + "inner join producto_presentacion pre\n"
                    + "on pre.present_cod_producto=pro.producto_id where pre.present_id=pro_p.present_id),det_venta.det_ven_pro_cod_lote,(det_venta.det_ven_pro_cantidad*pro_p.present_cantidad*pro_p.present_peso)\n"
                    + "from detalle_venta_producto det_venta\n"
                    + "inner join venta vent\n"
                    + "on vent.venta_id=det_venta.det_ven_pro_id_venta\n"
                    + "inner join producto_presentacion pro_p\n"
                    + "on pro_p.present_id=det_venta.det_ven_pro_id_producto\n"
                    + "inner join tipo_afectacion ti_af\n"
                    + "on ti_af.afectacion_id=det_venta.det_ven_pro_id_igv\n"
                    + "inner join tipo_unidad ti_uni\n"
                    + "on ti_uni.unidad_id=pro_p.present_cod_unidad\n"
                    + "where det_venta.det_ven_pro_id_venta='" + buscar + "'\n"
                    + "order by det_venta.det_ven_pro_id asc";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"", "ID_PRO", "ID_PRESENT", "COD_BARRA", "DESCRIPCIÒN", "CANTIDAD", "PESO", "UNI", "FECHA", "MARCA", "COD_LOTE", "TOTAL"});
            int contador = 0;
            while (rs.next()) {
                contador++;
                model.addRow(new Object[]{contador, rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
                    rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11)});
            }
            cxn.desconectar();
            producto.setModel(model);
            Function_Component.JTable(producto);
            producto.getColumnModel().getColumn(0).setCellRenderer(producto.getTableHeader().getDefaultRenderer());
            producto.getColumnModel().getColumn(0).setMaxWidth(40);
            producto.getColumnModel().getColumn(0).setMinWidth(40);
            producto.getColumnModel().getColumn(0).setPreferredWidth(40);
            producto.getColumnModel().getColumn(1).setMaxWidth(0);
            producto.getColumnModel().getColumn(1).setMinWidth(0);
            producto.getColumnModel().getColumn(1).setPreferredWidth(0);
            producto.getColumnModel().getColumn(2).setMaxWidth(0);
            producto.getColumnModel().getColumn(2).setMinWidth(0);
            producto.getColumnModel().getColumn(2).setPreferredWidth(0);
            producto.getColumnModel().getColumn(3).setMaxWidth(0);
            producto.getColumnModel().getColumn(3).setMinWidth(0);
            producto.getColumnModel().getColumn(3).setPreferredWidth(0);
            producto.getColumnModel().getColumn(4).setPreferredWidth(600);
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

    public String Add_sucursal(String url, String id_guia) {
        String respueta = "";
        String sent = "update guia_remision set guia_url='" + url + "' where guia_id='" + id_guia + "'";

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

    public void direccion_pdf(String buscar) throws IOException, DocumentException {
        List<InputStream> list = new ArrayList<InputStream>();
        String ruta = buscar;
        String ext = "pdf";
        Path dir = Paths.get(ruta);
        if (Files.exists(dir, LinkOption.NOFOLLOW_LINKS) && Files.isDirectory(dir, LinkOption.NOFOLLOW_LINKS)) {
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, String.format("*.%s", ext))) {
                ArrayList<Path> files = new ArrayList<>();
                stream.forEach(files::add);
                //utilize un natural order primero para ordenar por nombre
                files.sort(Comparator.naturalOrder());
                //utilize nuestro ordernado por Numero.
                String pdf = "";
                files.sort(Comparator.comparing(file -> getnumeric(file), Comparator.nullsLast(Comparator.naturalOrder())));

                // files.forEach(  file -> pdf=(file.getFileName().toString()));
                for (int i = 0; i < files.size(); i++) {
                    //System.out.println(files.get(i).toString());
                    list.add(new FileInputStream(new File(files.get(i).toString())));
                }

            }
        }
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de pdf", "pdf");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("DONDE DESEA GUARDAR ARCHIVO CREADO");
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String directorio = chooser.getSelectedFile().toString().concat(".pdf");
            OutputStream out = new FileOutputStream(new File(directorio));
            doMerge(list, out);

        }

    }

    public void add_subir_iten_anexo(JTable tabla, String dato) {

        tabla.setDefaultRenderer(Object.class, new Render());
        /*  model = new DefaultTableModel(new Object[]{"", "ID_SUC", "ID_EMPRE", "COD_EMPRESA"}, 0) {

        };*/
        model = (DefaultTableModel) tabla.getModel();
        model.addColumn(dato);

        // model.addRow(new Object[]{btnEliminar, "", "", "", "", "", "", "", true});
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

        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.setDragEnabled(false);
        tabla.getTableHeader().setReorderingAllowed(false);

    }

    public void tabla_anexo(JTable tabla, String entrega, String anexo) {
        JButton btnEliminar = new JButton("");
        btnEliminar.setName("btneliminar");
        btnEliminar.setIcon(new ImageIcon("src\\Icon\\icon_eliminar.png"));
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel model_tabla = new DefaultTableModel(new Object[]{"ID_PRODUCT", "CANTIDAD", "PRODUCTO", "PRESENTACION",
            "NUMERO LOTE", "MARCA", "FECHA"}, 0) {

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
            String sSQL1 = "select pre.present_id,pro.producto_cantidad, pro.producto_descrip,pre.present_peso,anexo_lote,\n"
                    + "fa.familia_descrip,anexo_fecha_vencimiento  from anexo_9 anex\n"
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
                model_tabla.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)});

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

    public int buscar_pequin(String producto, String venta, String lote, String fecha_venc) {
        int resultado = 0;

        try {
            String sSQL1 = "select det_ven_pro_cantidad from detalle_venta_producto where det_ven_pro_id_producto='" + producto + "' and det_ven_pro_id_venta='" + venta + "' and det_ven_pro_cod_lote='" + lote + "' and det_ven_pro_fecha='" + fecha_venc + "'";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);
            rs.next();
            resultado = rs.getInt(1);
            cxn.desconectar();
        } catch (SQLException e) {
            resultado = 0;
            //  Function_ShowMessageDialog.ShowMessageDialogwarning(null, e);
        }
        return resultado;
    }

    private static Integer getnumeric(Path file) {
        try {
            return Integer.parseInt(file.getFileName().toString().substring(0, file.getFileName().toString().indexOf('.')));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static void doMerge(List<InputStream> list, OutputStream outputStream)
            throws DocumentException, IOException {
        //crear un nuevo documento PDF
        Document document = new Document();
        //crear un escritor del PDF
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);
        document.open();
        PdfContentByte cb = writer.getDirectContent();
        //para cada PDF en la lista
        //leer su contenido por página e ir agregando
        //cada página en el PDF de la variable document
        for (InputStream in : list) {
            PdfReader reader = new PdfReader(in);
            for (int i = 1; i <= reader.getNumberOfPages(); i++) {
                document.newPage();
                //import the page from source pdf
                PdfImportedPage page = writer.getImportedPage(reader, i);
                //add the page to the destination pdf
                cb.addTemplate(page, 0, 0);
            }
        }
        //cerrar streams para liberar recursos
        //y cualquier bloqueo de archivo
        outputStream.flush();
        document.close();
        outputStream.close();
    }

    public void update_venta_guia(String venta_id, String guia_id, String fecha_venta, String venta_glosa, String guia_fecha_Emision, String guia_fecha_T) {
        String sent = "update venta set venta_fecha_emision ='" + fecha_venta + "' ,venta_fecha_vencimiento='" + fecha_venta + "' , venta_glosa='" + venta_glosa + "' where venta_id='" + venta_id + "';\n"
                + "update guia_remision set guia_fecha_emision='" + guia_fecha_Emision + "' ,guia_fecha_inicio_traslado='" + guia_fecha_T + "' where guia_id='" + guia_id + "';";
        try {
            //  System.out.println(sent);
            cxn.conectardb().createStatement().executeUpdate(sent);
            cxn.desconectar();
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }

    public void settext_descripcion(JTable tabla, TableColumn columna) {

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

                    //aComp.selectAll();
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

    public void settext_fecha(JTable tabla, TableColumn columna) {
        try {

            MaskFormatter mascara = new MaskFormatter("####-##-##");
            // mascara.setPlaceholderCharacter("AAAA-MM-DD");
            JFormattedTextField txt_descripcion = new JFormattedTextField(mascara);

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

                        //aComp.selectAll();
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

    public void settext_hora(JTable tabla, TableColumn columna) {
        try {

            MaskFormatter mascara = new MaskFormatter("##:##");
            // mascara.setPlaceholderCharacter("AAAA-MM-DD");
            JFormattedTextField txt_descripcion = new JFormattedTextField(mascara);

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

                        //aComp.selectAll();
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

    public void settext_dni(JTable tabla, TableColumn columna) {

        JTextField txt_descripcion = new JTextField();

        Function_Component.JTextField(txt_descripcion);
        Function_Key_Event.Validar_Mayuscula(txt_descripcion);
        txt_descripcion.selectAll();
        txt_descripcion.setHorizontalAlignment(JTextField.CENTER);
        columna.setCellEditor(new DefaultCellEditor(txt_descripcion));
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        txt_descripcion.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent evt) {
                char cteclap = evt.getKeyChar();
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    new Controller_Distribucion().table_persona_responsable(View_Distribuciones.tabla_persona);
                    Dimension desktopSize = View_Escritorio.desktopPane.getSize();
                    Dimension FrameSize = View_Distribuciones.Jdialog_Responsable.getSize();
                    View_Distribuciones.Jdialog_Responsable.setLocation((desktopSize.width - FrameSize.width) / 2, (desktopSize.height - FrameSize.height) / 2);
                    View_Distribuciones.Jdialog_Responsable.setVisible(true);

                    //aComp.selectAll();
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

    public void settext_acta(JTable tabla, TableColumn columna) {
        try {

            //MaskFormatter mascara = new MaskFormatter("########");
            // mascara.setPlaceholderCharacter("AAAA-MM-DD");
            JTextField txt_descripcion = new JTextField();

            Function_Component.JTextField(txt_descripcion);
            Function_Key_Event.Validar_numeros(txt_descripcion);
            txt_descripcion.selectAll();
            columna.setCellEditor(new DefaultCellEditor(txt_descripcion));
            DefaultTableCellRenderer render = new DefaultTableCellRenderer();
            txt_descripcion.addKeyListener(new KeyListener() {
                @Override
                public void keyPressed(KeyEvent evt) {
                    char cteclap = evt.getKeyChar();
                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

                        //aComp.selectAll();
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

    public void settext_dni_reniec(JTable tabla, TableColumn columna) {
        try {

            //MaskFormatter mascara = new MaskFormatter("########");
            // mascara.setPlaceholderCharacter("AAAA-MM-DD");
            // JFormattedTextField txt_descripcion = new JFormattedTextField(mascara);
            JTextField txt_descripcion = new JTextField();
            Function_Key_Event.Validar_numeros(txt_descripcion);
            columna.setCellEditor(new DefaultCellEditor(txt_descripcion));
            DefaultTableCellRenderer render = new DefaultTableCellRenderer();
            txt_descripcion.addKeyListener(new KeyListener() {
                @Override
                public void keyPressed(KeyEvent evt) {
                    char cteclap = evt.getKeyChar();
                    if (evt.isControlDown() && evt.getKeyCode() == KeyEvent.VK_R) {
                        String respuesta = "";
                        try {
                            respuesta = peticionHttpGet("https://apiperu.dev/api/dni/" + txt_descripcion.getText());
                            JSONArray jsonarray = new JSONArray("[" + respuesta + "]");

                            for (int i = 0; i < jsonarray.length(); i++) {
                                JSONObject jsonobject = jsonarray.getJSONObject(i);
                                if (jsonobject.get("success").equals(true)) {
                                    tabla.setValueAt(jsonobject.getJSONObject("data").getString("nombre_completo"), tabla.getSelectedRow(), 32);
                                } else if (jsonobject.get("success").equals("ERROR NO ENCONTRO EL SERVIDOR")) {
                                    CE_RENIEC cp = new CE_RENIEC();
                                    tabla.setValueAt(cp.apiConsume(txt_descripcion.getText()), tabla.getSelectedRow(), 32);
                                    //tabla.setValueAt(consulta, tabla.getSelectedRow(), 32);
                                } else {
                                    tabla.setValueAt(jsonobject.get("success"), tabla.getSelectedRow(), 32);
                                    System.out.println("" + jsonobject.get("success"));
                                }
                            }
                        } catch (Exception e) {
                            // Manejar excepción
                            e.printStackTrace();
                        }
                    }
                    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                        //System.out.println(".keyPressed()"+txt_descripcion.getText());
                        String consulta = new Controller_Distribucion().consultas_padron(txt_descripcion.getText(), tabla.getValueAt(tabla.getSelectedRow(), 4).toString(), ((Entity_Entrega) View_Distribuciones.cbx_entrega.getSelectedItem()).getEntrega_id());
                        //System.out.println(".keyPressed()"+consulta);
                        if (consulta.equals("NO ES CAE")) {
                            tabla.setValueAt(consulta, tabla.getSelectedRow(), 33);
                        tabla.setValueAt(consulta, tabla.getSelectedRow(), 32);
                        } else {
                            tabla.setValueAt("ES CAE", tabla.getSelectedRow(), 33);
                        tabla.setValueAt(consulta, tabla.getSelectedRow(), 32);
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

    public void table_persona_responsable(JTable persona) {
        String respueta = "";
        try {
            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String sSQL1 = "select * from persona_responsable";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"DNI", "NOMBRE"});
            while (rs.next()) {

                model.addRow(new Object[]{rs.getString(1), rs.getString(2),});
            }
            cxn.desconectar();
            persona.setModel(model);
            Function_Component.JTable(persona);
            persona.getColumnModel().getColumn(0).setMaxWidth(150);
            persona.getColumnModel().getColumn(0).setMinWidth(150);
            persona.getColumnModel().getColumn(0).setPreferredWidth(150);
            persona.setDragEnabled(false);
            persona.getTableHeader().setReorderingAllowed(false);
            //compra.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        //return respueta;
    }

    public String consultas_padron(String dni, String codigo, String entrega) {
        String respueta = "";
        try {
            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String sSQL1 = "SELECT padron_apellidos_nombres   FROM padron where padron_dni='" + dni + "' and entrega='" + entrega + "' and padron_codigo_modular ='" + codigo + "'";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);
            rs.next();
            respueta = rs.getString(1);
            System.out.println("Controller.Controller_Distribucion.consultas_padron()" + sSQL1);
            cxn.desconectar();

            //compra.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        } catch (SQLException ex) {
            //Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
            respueta = "NO ES CAE";
        }
        return respueta;
    }

    public static void main(String[] args) {
        String a = new Controller_Distribucion().consultas_padron("04816612", "1129600", "2");
        //System.out.println("Controller.Controller_Distribucion.main()" + a);
    }
}
