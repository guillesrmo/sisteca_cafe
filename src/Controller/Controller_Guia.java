package Controller;

import Conexion.Conexion;
import Config_Class.Render;
import Entity.Entity_Almacen;
import Entity.Entity_Compra;
import Entity.Entity_Compra_Producto;
import Entity.Entity_Documento;
import Entity.Entity_Moneda;
import Entity.Entity_Persona;
import Entity.Entity_Producto;
import Entity.Entity_Producto_Presentacion;
import Entity.Entity_Tipo_Comprobante;
import Entity.Entity_Tipo_afectacion;
import Entity.Entity_Venta;
import Entity.Entity_Venta_Producto;
import Function.Function_Component;
import Function.Function_Key_Event;
import Function.Function_ShowMessageDialog;
import View.View_Escritorio;
import View.View_Salida_producto;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
public class Controller_Guia {

    private Conexion cxn;

    public Controller_Guia() {

        cxn = new Conexion();
    }

    //calcular el número de veces que se repite un carácter en un String
    public void tabla_producto(JTable producto, String buscar) {
        try {
            DefaultTableModel model = new DefaultTableModel() {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            String sSQL1 = "select det_venta.det_ven_pro_id,\n" +
"pro_p.present_id,pro_p.present_cod_barra,pro_p.present_descripcion,det_venta.det_ven_pro_cantidad,\n" +
"pro_p.present_peso,ti_uni.unidad_cod,det_venta.det_ven_pro_fecha,(SELECT fa.familia_descrip  FROM producto pro\n" +
"inner join familia fa\n" +
"on fa.familia_id=pro.producto_familia \n" +
"inner join producto_presentacion pre\n" +
"on pre.present_cod_producto=pro.producto_id where pre.present_id=pro_p.present_id),det_venta.det_ven_pro_cod_lote,(det_venta.det_ven_pro_cantidad*pro_p.present_cantidad*pro_p.present_peso)\n" +
"from detalle_venta_producto det_venta\n" +
"inner join venta vent\n" +
"on vent.venta_id=det_venta.det_ven_pro_id_venta\n" +
"inner join producto_presentacion pro_p\n" +
"on pro_p.present_id=det_venta.det_ven_pro_id_producto\n" +
"inner join tipo_afectacion ti_af\n" +
"on ti_af.afectacion_id=det_venta.det_ven_pro_id_igv\n" +
"inner join tipo_unidad ti_uni\n" +
"on ti_uni.unidad_id=pro_p.present_cod_unidad\n" +
"where det_venta.det_ven_pro_id_venta='"+buscar+"'\n" +
"order by det_venta.det_ven_pro_id asc";
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sSQL1);

            model.setColumnIdentifiers(new Object[]{"","ID_PRO", "ID_PRESENT", "COD_BARRA", "DESCRIPCIÒN","CANTIDAD","PESO","UNI","FECHA","MARCA","COD_LOTE","TOTAL"});
            int contador = 0;
            while (rs.next()) {
                contador++;
                model.addRow(new Object[]{contador, rs.getString(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),
                rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11)});
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
    
    public ArrayList<String> institucion(String buscar){
        ArrayList inst=new ArrayList();
        String sent=" select (select per.persona_dni from venta ven\n" +
" inner join persona per\n" +
" on per.persona_id=ven.venta_cliente where ven.venta_id=gui.guia_venta),(\n" +
"  select per.persona_razon_social from venta ven\n" +
" inner join persona per\n" +
" on per.persona_id=ven.venta_cliente where ven.venta_id=gui.guia_venta),car.carro_placa,car.carro_nombre,con.coductor_dni_ruc,con.coductor_lincencia,con.coductor_nombre,\n" +
" (select sum(det_venta.det_ven_pro_cantidad*pro_p.present_cantidad*pro_p.present_peso)\n" +
"from detalle_venta_producto det_venta\n" +
"inner join venta vent\n" +
"on vent.venta_id=det_venta.det_ven_pro_id_venta\n" +
"inner join producto_presentacion pro_p\n" +
"on pro_p.present_id=det_venta.det_ven_pro_id_producto\n" +
"inner join tipo_afectacion ti_af\n" +
"on ti_af.afectacion_id=det_venta.det_ven_pro_id_igv\n" +
"inner join tipo_unidad ti_uni\n" +
"on ti_uni.unidad_id=pro_p.present_cod_unidad\n" +
"where det_venta.det_ven_pro_id_venta= gui.guia_venta) from guia gui\n" +
" inner join carro car\n" +
" on car.carro_placa=gui.guia_carro\n" +
" inner join  transportista tras \n" +
" on tras.transportista_dni_ruc=gui.guia_transportista\n" +
" inner join conductor con \n" +
" on con.coductor_dni_ruc =gui.guia_conductor where gui.guia_venta='"+buscar+"'";
        try {
            ResultSet rs = cxn.conectardb().createStatement().executeQuery(sent);

            while (rs.next()) {
                inst.add(rs.getString(1));
                inst.add(rs.getString(2));
                inst.add(rs.getString(3));
                inst.add(rs.getString(4));
                inst.add(rs.getString(5));
                inst.add(rs.getString(6));
                inst.add(rs.getString(7));
                inst.add(rs.getString(8));
                
            }
            cxn.desconectar();
        } catch (Exception e) {
        }
        
        return inst;
    }
    
  
}
