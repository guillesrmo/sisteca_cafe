/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Conexion.Conexion;
import Config_Class.Render;
import Entity.Entity_Actas_conformes;
import Entity.Entity_Entrega;
import Entity.Entity_Institucion;
import Entity.Entity_Item;
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
public class Controller_acta_conformes {

    private Conexion cxn;
    private boolean[] editable = {true, true, true, true, true, true, true, true, true, true,
        true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
    private boolean[] editable_institucion = {true, true, true, true, true, true, true, true, true, true,
        true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};

    public Controller_acta_conformes() {
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
                            //  System.out.println("col"+indiceColumna+" valor: true - "+celda+".");

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

        String sent = "select  ruta_condicion  from plan_ruta where ruta_codigo='" + codigo + "'";
        try {
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sent);
            rs.next();
            // System.out.println("Controller.Controller_Institucion.editar()"+sent);
            respueta = rs.getString(1);
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
        List<String> a = new Controller_acta_conformes().Importar(archivo);
        JButton btnEliminar = new JButton("");
        btnEliminar.setName("btneliminar");
        btnEliminar.setIcon(new ImageIcon("src\\Icon\\icon_eliminar.png"));
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel model = new DefaultTableModel(new Object[]{"", "CODIGO MODULAR", "NOMBRE", "USUARIOS",
            "ENTREGA", "ITEM", "NIVEL EDUCATIVO", "RACION", "N DE DIAS", "OBSERVACION", "ESTADO"}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Boolean.class,};

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

        for (int i = 0; i < a.size() / 11; i++) {
            //   System.out.println("Controller.Controller_Institucion.Subir_institucion()"+a.get(i));
            model.addRow(new Object[]{a.get(0 + contador), a.get(1 + contador), a.get(2 + contador), a.get(3 + contador), a.get(4 + contador),
                a.get(5 + contador), a.get(6 + contador), a.get(7 + contador), a.get(8 + contador), a.get(9 + contador), Boolean.parseBoolean(a.get(10 + contador))});
            contador = contador + 11;
        }


        /*for (int i = 0; i < 1; i++) {
            model.addRow(new Object[]{btnEliminar, "", "", "", "", "", "", "", "", "", "", "", "", "", ""});
        }*/
        tabla.setModel(model);
        Function_Component.JTable(tabla);
        Function_Key_Event.Validar_numeros_jtable(tabla);

        for (int i = 0; i < 7; i++) {
            settext_descripcion(tabla, tabla.getColumnModel().getColumn(i));

        }

        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabla.setDragEnabled(false);
        tabla.getTableHeader().setReorderingAllowed(false);

    }

    public void tabla_actas(JTable tabla, String entrega,String item) {
        JButton btnEliminar = new JButton("");
        btnEliminar.setName("btneliminar");
        btnEliminar.setIcon(new ImageIcon("src\\Icon\\icon_eliminar.png"));
        tabla.setDefaultRenderer(Object.class, new Render());
        DefaultTableModel model = new DefaultTableModel(new Object[]{"", "CODIGO MODULAR", "NOMBRE", "NIVEL",
            "DIRECCION", "OBSERVACION", "ESTADO"}, 0) {

            Class[] types = new Class[]{
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Object.class,    java.lang.Boolean.class,};

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
            String sSQL1 = "SELECT inst_entre.inst_entrega_id,inst_entre.inst_entrega_cod_modular, inst.institucion_nombre,niv.nivel_descripcion,inst.institucion_direccion,inst_entre.inst_entrega_observacion,inst_entre.inst_entrega_estado FROM institucion_entrega inst_entre\n"
                    + "inner join  institucion inst\n"
                    + "on inst.institucion_codigo_modular=inst_entre.inst_entrega_cod_modular\n"
                    + "inner join nivel niv\n"
                    + "on niv.nivel_id=inst_entre.inst_entrega_nivel\n"
                    + "where inst_entre.inst_entrega_item='"+item+"' and inst_entre.inst_entrega_entrega='"+entrega+"'\n"
                    + "order by inst.institucion_orden asc";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            while (rs.next()) {
                model.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                    rs.getString(6), rs.getBoolean(7)});

            }
            cxn.desconectar();
        } catch (Exception e) {

        }
        tabla.setModel(model);
        Function_Component.JTable(tabla);
        Function_Key_Event.Validar_numeros_jtable(tabla);

        for (int i = 0; i < 6; i++) {
            settext_descripcion(tabla, tabla.getColumnModel().getColumn(i));

        }

        //tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
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

    public String Add_actas_conforme(Entity_Actas_conformes actas_conforme) {
        String respuesta = "";
        String sent = "EXEC	[InsertaTablainstitucion_entrega]\n"
                + "		@inst_entrega_id = N'" + actas_conforme.getInst_entrega_id() + "',\n"
                + "		@inst_entrega_cod_modular = N'" + actas_conforme.getInst_entrega_cod_modular() + "',\n"
                + "		@inst_entrega_usuario =N'" + actas_conforme.getInst_entrega_usuario() + "',\n"
                + "		@inst_entrega_entrega = N'" + actas_conforme.getInst_entrega_entrega() + "',\n"
                + "		@inst_entrega_item = N'" + actas_conforme.getInst_entrega_item() + "',\n"
                + "		@inst_entrega_nivel = N'" + actas_conforme.getInst_entrega_nivel() + "',\n"
                + "		@inst_entrega_racion = N'" + actas_conforme.getInst_entrega_racion() + "',\n"
                + "		@inst_entrega_atencion = N'" + actas_conforme.getInst_entrega_atencion() + "',\n"
                + "		@inst_entrega_observacion = N'" + actas_conforme.getInst_entrega_observacion() + "',\n"
                + "		@inst_entrega_estado = N'" + actas_conforme.getInst_entrega_estado() + "'";
        // System.out.println("Controller.Controller_acta_conformes.Add_actas_conforme()"+sent);
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

    public String delete_institutcion(String institucion, String codigo_modular) {
        String respuesta = "";
        String sent = "EXEC	[dbo].[delete_institucion]\n"
                + "		@institucion = N'" + institucion + "',\n"
                + "		@codigo_modular = N'" + codigo_modular + "'";

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

    public void cbxItem(JComboBox<Entity_Item> item) {
        String sent = "SELECT * FROM [dbo].[item] order by item_id  asc";
        try {
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sent);
            while (rs.next()) {
                item.addItem(new Entity_Item(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
            cxn.desconectar();
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }

   /* public void cbxEntrega(JComboBox<Entity_Entrega> item) {
        String sent = "SELECT * FROM [dbo].[entrega] order by entrega_id  asc";
        try {
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sent);
            while (rs.next()) {
                item.addItem(new Entity_Entrega(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
            cxn.desconectar();
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }*/
    
    public void cbx_entrega(JComboBox<Entity_Entrega> entrega) {
        String sent = "SELECT * FROM entrega order by entrega_id  asc";
        try {
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sent);
            while (rs.next()) {
                entrega.addItem(new Entity_Entrega(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
            cxn.desconectar();
        } catch (SQLException ex) {
            Function_ShowMessageDialog.ShowMessageDialogwarning(null, ex);
        }
    }

}
