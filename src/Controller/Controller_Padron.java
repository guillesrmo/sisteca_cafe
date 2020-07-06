/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Conexion.Conexion;
import Config_Class.Render;
import Entity.Entity_Institucion;
import Entity.Entity_Padron;
import Entity.Entity_Producto;
import Entity.Entity_Venta_Producto;
import Function.Function_Component;
import Function.Function_Key_Event;
import Function.Function_ShowMessageDialog;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.*;

/**
 *
 * @author pro
 */
public class Controller_Padron {

    private Conexion cxn;

    public Controller_Padron() {
        cxn = new Conexion();
    }

    Workbook wb;

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

                                default:

                                    listaColumna[indiceColumna] = celda.getDateCellValue();
                                    break;
                            }
                            if (listaColumna[indiceColumna]==(null)) {
                              //  System.out.println("" +listaColumna[indiceColumna]);
                                listaColumna[indiceColumna]="";
                               //  System.out.println("" +listaColumna[indiceColumna]);
                            }
                            institucion.add((String) listaColumna[indiceColumna]);
                            // System.out.println(""+celda.getCellType());
                            // System.out.println("col"+indiceColumna+" valor: true - "+celda+".");
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

    public String Exportar(File archivo, JTable tablaD) {
        String respuesta = "No se realizo con exito la exportación.";
        int numFila = tablaD.getRowCount(), numColumna = tablaD.getColumnCount();
        if (archivo.getName().endsWith("xls")) {
            wb = new HSSFWorkbook();
        } else {
            wb = new XSSFWorkbook();
        }
        Sheet hoja = wb.createSheet("Pruebita");

        try {
            for (int i = -1; i < numFila; i++) {
                Row fila = hoja.createRow(i + 1);
                for (int j = 0; j < numColumna; j++) {
                    Cell celda = fila.createCell(j);
                    if (i == -1) {
                        celda.setCellValue(String.valueOf(tablaD.getColumnName(j)));
                    } else {
                        celda.setCellValue(String.valueOf(tablaD.getValueAt(i, j)));
                    }
                    wb.write(new FileOutputStream(archivo));
                }
            }
            respuesta = "Exportación exitosa.";
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return respuesta;
    }
    
    public void table_padron(JTable venta,String entrega) {
        JButton btnSelecionar = new JButton("");
        btnSelecionar.setName("btneliminar");
        btnSelecionar.setIcon(new ImageIcon("src\\Icon\\icon_agregar.png"));
        try {
            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String sSQL1 = "SELECT *   FROM padron where entrega ='"+entrega+"' ";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{ "NRO","CÓDIGO ANEXO","UNIDAD TERRITORIAL","DEPARTAMENTO","PROVINCIA","DISTRITO",
            "CENTRO POBLADO","CÓDIGO MODULAR","NOMBRE COLEGIO","NIVEL EDUCATIVO","CAMPAÑA","CONFORMACION","DNI","APELLIDOS Y NOMBRES","ROL","CARGO",
            "CORREO","CELULAR","TELEFONO","SEXO","ZONA","PUEBLO INDIGENA","FRONTERA","LENGUA MATERNA","DISCAPACIDAD","ESTADO",""});
            while (rs.next()) {

                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11),
                     rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20), rs.getString(21), rs.getString(22), rs.getString(23),
                      rs.getString(24), rs.getString(25), rs.getString(26),rs.getString(28)
                  });
            }
            cxn.desconectar();
            venta.setModel(model);
            Function_Component.JTable(venta);
            venta.getColumnModel().getColumn(1).setMaxWidth(0);
            venta.getColumnModel().getColumn(1).setMinWidth(0);
            venta.getColumnModel().getColumn(1).setPreferredWidth(0);
            venta.setDragEnabled(false);
            venta.getTableHeader().setReorderingAllowed(false);
            //venta.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }
    

    public void Subir_institucion(JTable tabla, File archivo) {
        List<String> a = new Controller_Padron().Importar(archivo);
        JButton btnEliminar = new JButton("");
        btnEliminar.setName("btneliminar");
        btnEliminar.setIcon(new ImageIcon("src\\Icon\\icon_eliminar.png"));
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel model = new DefaultTableModel(new Object[]{"NRO","CÓDIGO ANEXO","UNIDAD TERRITORIAL","DEPARTAMENTO","PROVINCIA","DISTRITO",
            "CENTRO POBLADO","CÓDIGO MODULAR","NOMBRE COLEGIO","NIVEL EDUCATIVO","CAMPAÑA","CONFORMACION","DNI","APELLIDOS Y NOMBRES","ROL","CARGO",
            "CORREO","CELULAR","TELEFONO","SEXO","ZONA","PUEBLO INDIGENA","FRONTERA","LENGUA MATERNA","DISCAPACIDAD","ESTADO"}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };
        int contador = 0;
        
        for (int i = 0; i < a.size() / 26; i++) {
            // System.out.println("Controller.Controller_Institucion.Subir_institucion()"+a.get(i));
            model.addRow(new Object[]{a.get(0 + contador), a.get(1 + contador), a.get(2 + contador), a.get(3 + contador), a.get(4 + contador),
                a.get(5 + contador), a.get(6 + contador), a.get(7 + contador), a.get(8 + contador), a.get(9 + contador), a.get(10 + contador), a.get(11 + contador),
                a.get(12 + contador), a.get(13 + contador), a.get(14 + contador), a.get(15 + contador), a.get(16 + contador), a.get(17 + contador), a.get(18 + contador),
                a.get(19 + contador), a.get(20 + contador), a.get(21 + contador), a.get(22 + contador), a.get(23 + contador), a.get(24 + contador), a.get(25 + contador)});
            contador = contador + 26;
        }


        /*for (int i = 0; i < 1; i++) {
            model.addRow(new Object[]{btnEliminar, "", "", "", "", "", "", "", "", "", "", "", "", "", ""});
        }*/
        tabla.setModel(model);
        Function_Component.JTable(tabla);
        Function_Key_Event.Validar_numeros_jtable(tabla);

        for (int i = 0; i < 26; i++) {
            settext_descripcion(tabla, tabla.getColumnModel().getColumn(i));

        }

        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.setDragEnabled(false);
        tabla.getTableHeader().setReorderingAllowed(false);

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

    public String Add_institutcion(Entity_Padron padron) {
        String respuesta = "";
        String sent = "EXEC	 [dbo].[InsertaTablapadron]\n" +
"		@padron_nro = N'"+padron.getPadron_nro()+"',\n" +
"		@padron_codigo_anexo = N'"+padron.getPadron_codigo_anexo()+"',\n" +
"		@padron_unidad_territorial = N'"+padron.getPadron_unidad_territorial()+"',\n" +
"		@padron_departamento = N'"+padron.getPadron_departamento()+"',\n" +
"		@padron_provincia = N'"+padron.getPadron_provincia()+"',\n" +
"		@padron_distrito = N'"+padron.getPadron_distrito()+"',\n" +
"		@padron_centro_poblado = N'"+padron.getPadron_centro_poblado()+"',\n" +
"		@padron_codigo_modular = N'"+padron.getPadron_codigo_modular()+"',\n" +
"		@padron_nombre_colegio = N'"+padron.getPadron_nombre_colegio()+"',\n" +
"		@padron_nivel_educativo = N'"+padron.getPadron_nivel_educativo()+"',\n" +
"		@padron_campaña = N'"+padron.getPadron_campaña()+"',\n" +
"		@padron_conformacion = N'"+padron.getPadron_conformacion()+"',\n" +
"		@padron_dni = N'"+padron.getPadron_dni()+"',\n" +
"		@padron_apellidos_nombres = N'"+padron.getPadron_apellidos_nombres()+"',\n" +
"		@padron_rol = N'"+padron.getPadron_rol()+"',\n" +
"		@padron_cargo = N'"+padron.getPadron_cargo()+"',\n" +
"		@padron_correo = N'"+padron.getPadron_correo()+"',\n" +
"		@padron_celular = N'"+padron.getPadron_celular()+"',\n" +
"		@padron_telefono = N'"+padron.getPadron_telefono()+"',\n" +
"		@padron_sexo = N'"+padron.getPadron_sexo()+"',\n" +
"		@padron_zona = N'"+padron.getPadron_zona()+"',\n" +
"		@padron_indigena = N'"+padron.getPadron_indigena()+"',\n" +
"		@padron_frontera = N'"+padron.getPadron_frontera()+"',\n" +
"		@padron_lengua_materna = N'"+padron.getPadron_lengua_materna()+"',\n" +
"		@padron_discapacidad = N'"+padron.getPadron_discapacidad()+"',\n" +
"		@padron_estado = N'"+padron.getPadron_estado()+"',\n" +
"		@entrega = N'"+padron.getEntrega()+"',\n" +
"		@id_padron = N''";

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
    
    public String delete_padron(String padron) {
        String respuesta = "";
        String sent = "delete from padron where id_padron='"+padron+"'";
       // System.out.println(""+sent);
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
