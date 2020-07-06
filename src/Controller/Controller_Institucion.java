/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Conexion.Conexion;
import Config_Class.Render;
import Entity.Entity_Institucion;
import Entity.Entity_Persona;
import Entity.Entity_Producto;
import Entity.Entity_Sucursal;
import Entity.Entity_Venta_Producto;
import Function.Function_Component;
import Function.Function_Key_Event;
import Function.Function_ShowMessageDialog;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
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
public class Controller_Institucion {

    private Conexion cxn;
    private boolean[] editable = {true, true, true, true, true, true, true, true, true, true,
        true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
    private boolean[] editable_institucion = {true, true, true, true, true, true, true, true, true, true,
        true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};

    public Controller_Institucion() {
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
                            //  System.out.println("" +listaColumna[indiceColumna]);
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
                                case Cell.CELL_TYPE_BLANK:

                                    listaColumna[indiceColumna] = celda.getStringCellValue();

                                    /* System.out.println("sss" +listaColumna[indiceColumna]);
  System.out.println("col"+indiceColumna+" valor: true - "+celda+".");*/
                                    System.out.println("" + celda.getCellType());
                                    break;

                                default:
                                    listaColumna[indiceColumna] = celda.getDateCellValue();
                                    break;
                            }
                            //System.out.println("col"+indiceColumna+" valor: true - "+celda+".");

                            if (listaColumna[indiceColumna] == (null)) {
                                System.out.println("" + listaColumna[indiceColumna]);
                                listaColumna[indiceColumna] = "";
                                System.out.println("" + listaColumna[indiceColumna]);
                            } else if (listaColumna[indiceColumna] == "") {
                                System.out.println("" + listaColumna[indiceColumna]);

                            }

                            /*  if (celda == null || celda.getCellType() == Cell.CELL_TYPE_BLANK) {
    // This cell is emptySystem.out.print(celda.getStringCellValue() + "\t\t"
 }*/
                            institucion.add((String) listaColumna[indiceColumna]);
                            //   System.out.println(""+celda.getCellType());
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

    public List<String> Importar_fill(File archivo) {
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
                                case Cell.CELL_TYPE_BLANK:

                                    listaColumna[indiceColumna] = celda.getBooleanCellValue();
                                    System.out.println("" + celda.getCellType());
                                    break;

                                default:

                                    listaColumna[indiceColumna] = celda.getDateCellValue();
                                    break;
                            }
                            //System.out.println("col"+indiceColumna+" valor: true - "+celda+".");

                            if (listaColumna[indiceColumna] == (null)) {
                                System.out.println("" + listaColumna[indiceColumna]);
                                listaColumna[indiceColumna] = "";
                                System.out.println("" + listaColumna[indiceColumna]);
                            } else if (listaColumna[indiceColumna] == "") {
                                System.out.println("" + listaColumna[indiceColumna]);

                            }

                            if (celda == null || celda.getCellType() == Cell.CELL_TYPE_BLANK) {
                                // This cell is emptySystem.out.print(celda.getStringCellValue() + "\t\t"
                            }
                            institucion.add((String) listaColumna[indiceColumna]);
                            //   System.out.println(""+celda.getCellType());
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
    
    public String editar(String codigo) {
        String respueta = "";
       
        String sent = "select  ruta_condicion  from plan_ruta where ruta_codigo='"+codigo+"'";
        try {
             ResultSet rs = cxn.conectardb().createStatement().executeQuery(sent);
             rs.next();
             // System.out.println("Controller.Controller_Institucion.editar()"+sent);
             respueta=rs.getString(1);
            cxn.desconectar();
           // respueta = "exito";
        } catch (SQLException ex) {
            respueta = "fallo";
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
        return respueta;
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

    public void exportarExcel1(JTable t) throws IOException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de excel", "xls");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Guardar archivo");
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String ruta = chooser.getSelectedFile().toString().concat(".xls");
            try {
                File archivoXLS = new File(ruta);
                if (archivoXLS.exists()) {
                    archivoXLS.delete();
                }
                archivoXLS.createNewFile();
                Workbook libro = new HSSFWorkbook();
                FileOutputStream archivo = new FileOutputStream(archivoXLS);
                Sheet hoja = libro.createSheet("HOJA 1");
                hoja.setDisplayGridlines(false);
                for (int f = 0; f < t.getRowCount(); f++) {
                    Row fila = hoja.createRow(f);
                    for (int c = 0; c < t.getColumnCount(); c++) {
                        Cell celda = fila.createCell(c);
                        if (f == 0) {
                            celda.setCellValue(t.getColumnName(c));
                        }
                    }
                }
                int filaInicio = 1;
                for (int f = 0; f < t.getRowCount(); f++) {
                    Row fila = hoja.createRow(filaInicio);
                    filaInicio++;
                    for (int c = 0; c < t.getColumnCount(); c++) {

                        Cell celda = fila.createCell(c);
                        if (t.getValueAt(f, c) instanceof Double) {
                            celda.setCellValue(Double.parseDouble(t.getValueAt(f, c).toString()));
                        } else if (t.getValueAt(f, c) instanceof Float) {
                            celda.setCellValue(Float.parseFloat((String) t.getValueAt(f, c)));

                        } else {

                            
                                    celda.setCellValue(String.valueOf(t.getValueAt(f, c)));
                               

                        }//17

                    }
                }
                libro.write(archivo);
                archivo.close();
                Desktop.getDesktop().open(archivoXLS);
            } catch (IOException | NumberFormatException e) {
                throw e;
            }
        }
    }
    
    public void exportarExcel_iten(JTable t) throws IOException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de excel", "xls");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Guardar archivo");
        chooser.setAcceptAllFileFilterUsed(false);
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String ruta = chooser.getSelectedFile().toString().concat(".xls");
            try {
                File archivoXLS = new File(ruta);
                if (archivoXLS.exists()) {
                    archivoXLS.delete();
                }
                archivoXLS.createNewFile();
                Workbook libro = new HSSFWorkbook();
                FileOutputStream archivo = new FileOutputStream(archivoXLS);
                Sheet hoja = libro.createSheet("HOJA 1");
                hoja.setDisplayGridlines(false);
                for (int f = 0; f < t.getRowCount(); f++) {
                    Row fila = hoja.createRow(f);
                    for (int c = 0; c < t.getColumnCount(); c++) {
                        Cell celda = fila.createCell(c);
                        if (f == 0) {
                            celda.setCellValue(t.getColumnName(c));
                        }
                    }
                }
                int filaInicio = 1;
                for (int f = 0; f < t.getRowCount(); f++) {
                    Row fila = hoja.createRow(filaInicio);
                    filaInicio++;
                    for (int c = 0; c < t.getColumnCount(); c++) {

                        Cell celda = fila.createCell(c);
                        if (t.getValueAt(f, c) instanceof Double) {
                            celda.setCellValue(Double.parseDouble(t.getValueAt(f, c).toString()));
                        } else if (t.getValueAt(f, c) instanceof Float) {
                            celda.setCellValue(Float.parseFloat((String) t.getValueAt(f, c)));

                        } else {

                            if (c > 17 && f > 5) {
                                if (isInteger(t.getValueAt(f, c).toString()) == true) {
                                    celda.setCellValue(Double.parseDouble(t.getValueAt(f, c).toString()));
                                } else {
                                    celda.setCellValue(String.valueOf(t.getValueAt(f, c)));
                                }
                            } else {
                                celda.setCellValue(String.valueOf(t.getValueAt(f, c)));
                            }

                        }//17

                    }
                }
                libro.write(archivo);
                archivo.close();
                Desktop.getDesktop().open(archivoXLS);
            } catch (IOException | NumberFormatException e) {
                throw e;
            }
        }
    }

    public static boolean isInteger(String source) {

        try {
            Double.parseDouble(source);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public void Subir_institucion(JTable tabla, File archivo) {
        List<String> a = new Controller_Institucion().Importar(archivo);
        JButton btnEliminar = new JButton("");
        btnEliminar.setName("btneliminar");
        btnEliminar.setIcon(new ImageIcon("src\\Icon\\icon_eliminar.png"));
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel model = new DefaultTableModel(new Object[]{"icodcolegio", "UNIDADTERRITORIAL", "DEPARTAMENTO",
            "PROVINCIA", "DISTRITO", "UBIGEO", "CENTROPOBLADO", "CODIGO MODULAR", "CODIGO LOCAL", "ANEXO", "NOMBRE", "DIRECCION	", "NIVEL", "USUARIOS",
            "COMITE", "ITEM", "MODALIDAD", "RACION", "TIPO_SERVICIO", "REGION_ALIMENTARIA", "Comunidad Indigena", "QUINTIL2013 PPob2017", "AREA.",
            "PROGRAMACIÓN", "RUTA", "ORDEN", "ESTADO"}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Boolean.class
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
        int contador = 0;

        for (int i = 0; i < a.size() / 26; i++) {
            // System.out.println("Controller.Controller_Institucion.Subir_institucion()"+a.get(i));
            model.addRow(new Object[]{a.get(0 + contador), a.get(1 + contador), a.get(2 + contador), a.get(3 + contador), a.get(4 + contador),
                a.get(5 + contador), a.get(6 + contador), a.get(7 + contador), a.get(8 + contador), a.get(9 + contador), a.get(10 + contador), a.get(11 + contador),
                a.get(12 + contador), a.get(13 + contador), a.get(14 + contador), a.get(15 + contador), a.get(16 + contador), a.get(17 + contador), a.get(18 + contador),
                a.get(19 + contador), a.get(20 + contador), a.get(21 + contador), a.get(22 + contador), a.get(23 + contador), a.get(24 + contador), a.get(25 + contador), true});
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

    public void tabla_institucion(JTable tabla,String buscar) {
        JButton btnEliminar = new JButton("");
        btnEliminar.setName("btneliminar");
        btnEliminar.setIcon(new ImageIcon("src\\Icon\\icon_eliminar.png"));
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel model = new DefaultTableModel(new Object[]{"icodcolegio", "UNIDADTERRITORIAL", "DEPARTAMENTO",
            "PROVINCIA", "DISTRITO", "UBIGEO", "CENTROPOBLADO", "CODIGO MODULAR", "CODIGO LOCAL", "ANEXO", "NOMBRE", "DIRECCION	", "NIVEL", "USUARIOS",
            "COMITE", "ITEM", "MODALIDAD", "RACION", "TIPO_SERVICIO", "REGION_ALIMENTARIA", "Comunidad Indigena", "QUINTIL2013 PPob2017", "AREA.",
            "PROGRAMACIÓN", "RUTA", "ORDEN", "PERSONA", "ESTADO"}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return editable_institucion[column];
            }
        };

        try {
            String sSQL1 = "SELECT  [institucion_icodcolegio]\n"
                    + "      ,[institucion_unidad_teritorial]\n"
                    + "      ,[institucion_departamento]\n"
                    + "      ,[institucion_provincia]\n"
                    + "      ,[institucion_distrito]\n"
                    + "      ,[institucion_ubigeo]\n"
                    + "      ,[institucion_centro_poblado]\n"
                    + "      ,[institucion_codigo_modular]\n"
                    + "      ,[institucion_codigo_local]\n"
                    + "      ,[institucion_anexo]\n"
                    + "      ,[institucion_nombre]\n"
                    + "      ,[institucion_direccion]\n"
                    + "      ,[institucion_nivel]\n"
                    + "      ,[institucion_usuario]\n"
                    + "      ,[institucion_comite]\n"
                    + "      ,[institucion_iten]\n"
                    + "      ,[institucion_modalidad]\n"
                    + "      ,[institucion_racion]\n"
                    + "      ,[institucion_tipo_servicio]\n"
                    + "      ,[institucion_region_alimentaria]\n"
                    + "      ,[institucion_comunidad_indi]\n"
                    + "      ,[institucion_quintil]\n"
                    + "      ,[institucion_area]\n"
                    + "      ,[institucion_programacion]\n"
                    + "      ,[institucion_ruta]\n"
                    + "      ,[institucion_orden]\n"
                    + "      ,[institucion_persona]\n"
                    + "      ,[institucion_estado]\n"
                    + "  FROM [institucion]  where (cast(institucion_iten as NVARCHAR(max))) = '" + buscar + "' ORDER BY institucion_orden  ASC";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            while (rs.next()) {
                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                    rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12),
                    rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18), rs.getString(19),
                    rs.getString(20), rs.getString(21), rs.getString(22), rs.getString(23), rs.getString(24), rs.getString(25), rs.getString(26),
                    rs.getString(27), rs.getBoolean(28)});

            }
            cxn.desconectar();
        } catch (Exception e) {

        }
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

    public String Add_institutcion(Entity_Institucion institucion) {
        String respuesta = "";
        String sent = "EXEC	[dbo].[InsertaTablaInstitucion]\n"
                + "		@institucion_icodcolegio = N'" + institucion.getInstitucion_icodcolegio() + "',\n"
                + "		@institucion_unidad_teritorial = N'" + institucion.getInstitucion_unidad_teritorial() + "',\n"
                + "		@institucion_departamento = N'" + institucion.getInstitucion_departamento() + "',\n"
                + "		@institucion_provincia = N'" + institucion.getInstitucion_provincia() + "',\n"
                + "		@institucion_distrito = N'" + institucion.getInstitucion_distrito() + "',\n"
                + "		@institucion_ubigeo = N'" + institucion.getInstitucion_ubigeo() + "',\n"
                + "		@institucion_centro_poblado = N'" + institucion.getInstitucion_centro_poblado() + "',\n"
                + "		@institucion_codigo_modular = N'" + institucion.getInstitucion_codigo_modular() + "',\n"
                + "		@institucion_codigo_local = N'" + institucion.getInstitucion_codigo_local() + "',\n"
                + "		@institucion_anexo = N'" + institucion.getInstitucion_anexo() + "',\n"
                + "		@institucion_nombre = N'" + institucion.getInstitucion_nombre() + "',\n"
                + "		@institucion_direccion = N'" + institucion.getInstitucion_direccion() + "',\n"
                + "		@institucion_nivel = N'" + institucion.getInstitucion_nivel() + "',\n"
                + "		@institucion_usuario = N'" + institucion.getInstitucion_usuario() + "',\n"
                + "		@institucion_comite = N'" + institucion.getInstitucion_comite() + "',\n"
                + "		@institucion_iten = N'" + institucion.getInstitucion_iten() + "',\n"
                + "		@institucion_modalidad = N'" + institucion.getInstitucion_modalidad() + "',\n"
                + "		@institucion_racion = N'" + institucion.getInstitucion_racion() + "',\n"
                + "		@institucion_tipo_servicio = N'" + institucion.getInstitucion_tipo_servicio() + "',\n"
                + "		@institucion_region_alimentaria = N'" + institucion.getInstitucion_region_alimentaria() + "',\n"
                + "		@institucion_comunidad_indi = N'" + institucion.getInstitucion_comunidad_indi() + "',\n"
                + "		@institucion_quintil = N'" + institucion.getInstitucion_quintil() + "',\n"
                + "		@institucion_area = N'" + institucion.getInstitucion_area() + "',\n"
                + "		@institucion_programacion = N'" + institucion.getInstitucion_programacion() + "',\n"
                + "		@institucion_ruta = N'" + institucion.getInstitucion_ruta() + "',\n"
                + "		@institucion_orden = N'" + institucion.getInstitucion_orden() + "',\n"
                + "		@institucion_persona = N'" + institucion.getInstitucion_persona() + "',\n"
                + "		@institucion_estado = N'" + institucion.getInstitucion_estado() + "'";

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
    
    public String delete_institutcion(String institucion,String codigo_modular) {
        String respuesta = "";
        String sent = "EXEC	[dbo].[delete_institucion]\n"
                + "		@institucion = N'" +institucion+ "',\n"
                + "		@codigo_modular = N'" + codigo_modular +"'";

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

    public String generar_cod_proveedor() {
        String valor = "";
        String sent = "SELECT (CASE   WHEN (MAX(persona_id) > 0) THEN (MAX(persona_id) + 1)  ELSE 1    END) AS maximo FROM persona";
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

    public String Add_proveedor(Entity_Persona persona) {
        String respueta = "";
        String sent = "DECLARE	@return_value int\n"
                + "SET IDENTITY_INSERT persona ON\n"
                + "EXEC	@return_value = [dbo].[Insertapersonainstitucion]\n"
                + "		@persona_id = N'" + persona.getPersona_id() + "',\n"
                + "		@persona_tipo_documento = N'" + persona.getPersona_tipo_documento() + "',\n"
                + "		@persona_ruc = N'" + persona.getPersona_ruc() + "',\n"
                + "		@persona_dni = N'" + persona.getPersona_dni() + "',\n"
                + "		@persona_razon_social = N'" + persona.getPersona_razon_social() + "',\n"
                + "		@persona_razon_comercial = N'" + persona.getPersona_razon_comercial() + "',\n"
                + "		@persona_direccion = N'" + persona.getPersona_direccion() + "',\n"
                + "		@persona_institucion = N'" + persona.getPersona_codigo_modular() + "'";
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

    public String Importar(File archivo, JTable tablaD) {
        String respuesta = "No se pudo realizar la importación.";
        DefaultTableModel modeloT = new DefaultTableModel();
        tablaD.setModel(modeloT);
        tablaD.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

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
            for (int i = 0; i < tablaD.getColumnCount(); i++) {
                Function_Component.JTable(tablaD);
                settext_descripcion(tablaD, tablaD.getColumnModel().getColumn(i));

            }
        } catch (IOException | InvalidFormatException | EncryptedDocumentException e) {
            System.err.println(e.getMessage());
        }
        return respuesta;
    }

}
