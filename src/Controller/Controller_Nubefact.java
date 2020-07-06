/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Conexion.Conexion;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class Controller_Nubefact {
     private Conexion cxn;
    public Controller_Nubefact() {
        cxn=new Conexion();
    }
    
    
    public List<String> Guia_persona(String id_Venta){
        Conexion a=new Conexion();
        List<String> guia = new ArrayList<String>();

        try {
            String sql = "select ven.venta_id,\n"
                    + "ven.venta_serie,\n"
                    + "ven.venta_numero,\n"
                    + "ti_com.comprobante_descrip,\n"
                    + "ti_do.documento_cod,\n"
                    + "per.persona_dni,per.persona_razon_social,per.persona_direccion,\n"
                    + "FORMAT(CONVERT(date,guia.guia_fecha_emision), N'dd-MM-yyyy ')/*ven.venta_fecha_emision*/,\n"
                    + "guia.guia_motivo,\n"
                    + "guia.guia_peso_bruto,\n"
                    + "guia.guia_tipo_transportista,\n"
                    + "guia.guia_fecha_inicio_traslado,\n"
                    + "(select documento_cod from tipo_documento where documento_id=trans.transportista_tipo_docuemnto) as tipo_transportista,trans.transportista_dni_ruc,trans.transportista_nombre,trans.transportista_direcc,\n"
                    + "car.carro_nombre,car.carro_placa,\n"
                    + "(select documento_cod from tipo_documento where documento_id=cond.coductor_tipo_documento) as tipo_conduictor,cond.coductor_dni_ruc,cond.coductor_lincencia,cond.coductor_nombre,\n"
                    + "guia.guia_ubigeo_partida,\n"
                    + "guia.guia_ubigeo_partida_direccion,\n"
                    + "guia.guia_ubigeo_llegada,\n"
                    + "guia.guia_ubigeo_llegada_direccion,\n"
                    + "guia_url from venta ven\n"
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
                    + "where /*ven.venta_concepto=4 and ven.venta_entrega='1' and cast(ins.institucion_iten as varchar(max)) ='inambari'*/ ven.venta_id='"+id_Venta+"'\n"
                    + "order by ins.institucion_orden desc ";
            String sql1="SELECT \n" +
"'LOTE: '+det_ven.det_ven_pro_cod_lote  as lote,\n" +
"cast(pro.producto_descrip as varchar(max)) +' | '+\n" +
"'MARCA: '+cast(fa.familia_descrip as varchar(max)) +' | '+\n" +
"'PRESENTACION: '+cast(present.present_peso as varchar(max))+' '+ti_uni.unidad_cod +' | '+\n" +
"'FV: '+FORMAT(CONVERT(date,det_ven.det_ven_pro_fecha), N'dd-MM-yyyy ')as descr,\n" +
"det_ven.det_ven_pro_cantidad\n" +
"  FROM detalle_venta_producto det_ven\n" +
"  inner join producto_presentacion present\n" +
"  on det_ven.det_ven_pro_id_producto=present.present_id\n" +
"  inner join producto pro\n" +
"  on pro.producto_id =present.present_cod_producto\n" +
"  inner join familia fa\n" +
"  on fa.familia_id=pro.producto_familia\n" +
"  inner join tipo_unidad ti_uni\n" +
"  on ti_uni.unidad_id=pro.producto_medida\n" +
"    where det_ven_pro_id_venta='"+id_Venta+"'";
                ResultSet rs = a.conectardb().createStatement().executeQuery(sql);
                ResultSet rs1 = a.conectardb().createStatement().executeQuery(sql1);
            while (rs.next()) {
                guia.add(rs.getString(1));
                guia.add(rs.getString(2));
                guia.add(rs.getString(3));
                guia.add(rs.getString(4));
                guia.add(rs.getString(5));
                guia.add(rs.getString(6));
                guia.add(rs.getString(7));
                guia.add(rs.getString(8));
                guia.add(rs.getString(9));
                guia.add(rs.getString(10));
                guia.add(rs.getString(11));
                guia.add(rs.getString(12));
                guia.add(rs.getString(13));
                guia.add(rs.getString(14));
                guia.add(rs.getString(15));
                guia.add(rs.getString(16));
                guia.add(rs.getString(17));
                guia.add(rs.getString(18));
                guia.add(rs.getString(19));
                guia.add(rs.getString(20));
                guia.add(rs.getString(21));
                guia.add(rs.getString(22));
                guia.add(rs.getString(23));
                guia.add(rs.getString(24));
                guia.add(rs.getString(25));
                guia.add(rs.getString(26));
                guia.add(rs.getString(27));
                
                
            }
            
            while (rs1.next()) {
                for (int i = 1; i <= 3; i++) {
                    guia.add(rs1.getString(i).replace("\n", ""));

                }
                
                
            }
           
            
            
        } catch (Exception e) {
        }
        return guia;
    }
    public static void main(String[] args) {
         List<String> guia = new Controller_Nubefact().Guia_persona("292");
          for (int i = 0; i < guia.size(); i++) {
                System.out.println(""+guia.get(i));
                
            }
    }
   
}
