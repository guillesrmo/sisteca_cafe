/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author pro
 */
public class Entity_Venta_Producto {
    private String det_ven_pro_id;
    private String det_ven_pro_id_venta;
    private String det_ven_pro_id_producto;
    private String det_ven_pro_id_igv;
    private String det_ven_pro_cantidad;
    private String det_ven_pro_precio;
    private String det_ven_pro_sub_total;
    private String det_ven_pro_total;
    private String det_ven_pro_desc_por;
    private String det_ven_pro_desc_valor;
    private String det_ven_pro_fecha;
    private String det_ven_pro_cod_lote;
    private String det_ven_pro_id_compra;

    public Entity_Venta_Producto() {
    }

    
    public Entity_Venta_Producto(String det_ven_pro_id, String det_ven_pro_id_venta, String det_ven_pro_id_producto, String det_ven_pro_id_igv, String det_ven_pro_cantidad, String det_ven_pro_precio, String det_ven_pro_sub_total, String det_ven_pro_total, String det_ven_pro_desc_por, String det_ven_pro_desc_valor, String det_ven_pro_fecha, String det_ven_pro_cod_lote, String det_ven_pro_id_compra) {
        this.det_ven_pro_id = det_ven_pro_id;
        this.det_ven_pro_id_venta = det_ven_pro_id_venta;
        this.det_ven_pro_id_producto = det_ven_pro_id_producto;
        this.det_ven_pro_id_igv = det_ven_pro_id_igv;
        this.det_ven_pro_cantidad = det_ven_pro_cantidad;
        this.det_ven_pro_precio = det_ven_pro_precio;
        this.det_ven_pro_sub_total = det_ven_pro_sub_total;
        this.det_ven_pro_total = det_ven_pro_total;
        this.det_ven_pro_desc_por = det_ven_pro_desc_por;
        this.det_ven_pro_desc_valor = det_ven_pro_desc_valor;
        this.det_ven_pro_fecha = det_ven_pro_fecha;
        this.det_ven_pro_cod_lote = det_ven_pro_cod_lote;
        this.det_ven_pro_id_compra = det_ven_pro_id_compra;
    }

    public String getDet_ven_pro_id() {
        return det_ven_pro_id;
    }

    public void setDet_ven_pro_id(String det_ven_pro_id) {
        this.det_ven_pro_id = det_ven_pro_id;
    }

    public String getDet_ven_pro_id_venta() {
        return det_ven_pro_id_venta;
    }

    public void setDet_ven_pro_id_venta(String det_ven_pro_id_venta) {
        this.det_ven_pro_id_venta = det_ven_pro_id_venta;
    }

    public String getDet_ven_pro_id_producto() {
        return det_ven_pro_id_producto;
    }

    public void setDet_ven_pro_id_producto(String det_ven_pro_id_producto) {
        this.det_ven_pro_id_producto = det_ven_pro_id_producto;
    }

    public String getDet_ven_pro_id_igv() {
        return det_ven_pro_id_igv;
    }

    public void setDet_ven_pro_id_igv(String det_ven_pro_id_igv) {
        this.det_ven_pro_id_igv = det_ven_pro_id_igv;
    }

    public String getDet_ven_pro_cantidad() {
        return det_ven_pro_cantidad;
    }

    public void setDet_ven_pro_cantidad(String det_ven_pro_cantidad) {
        this.det_ven_pro_cantidad = det_ven_pro_cantidad;
    }

    public String getDet_ven_pro_precio() {
        return det_ven_pro_precio;
    }

    public void setDet_ven_pro_precio(String det_ven_pro_precio) {
        this.det_ven_pro_precio = det_ven_pro_precio;
    }

    public String getDet_ven_pro_sub_total() {
        return det_ven_pro_sub_total;
    }

    public void setDet_ven_pro_sub_total(String det_ven_pro_sub_total) {
        this.det_ven_pro_sub_total = det_ven_pro_sub_total;
    }

    public String getDet_ven_pro_total() {
        return det_ven_pro_total;
    }

    public void setDet_ven_pro_total(String det_ven_pro_total) {
        this.det_ven_pro_total = det_ven_pro_total;
    }

    public String getDet_ven_pro_desc_por() {
        return det_ven_pro_desc_por;
    }

    public void setDet_ven_pro_desc_por(String det_ven_pro_desc_por) {
        this.det_ven_pro_desc_por = det_ven_pro_desc_por;
    }

    public String getDet_ven_pro_desc_valor() {
        return det_ven_pro_desc_valor;
    }

    public void setDet_ven_pro_desc_valor(String det_ven_pro_desc_valor) {
        this.det_ven_pro_desc_valor = det_ven_pro_desc_valor;
    }

    public String getDet_ven_pro_fecha() {
        return det_ven_pro_fecha;
    }

    public void setDet_ven_pro_fecha(String det_ven_pro_fecha) {
        this.det_ven_pro_fecha = det_ven_pro_fecha;
    }

    public String getDet_ven_pro_cod_lote() {
        return det_ven_pro_cod_lote;
    }

    public void setDet_ven_pro_cod_lote(String det_ven_pro_cod_lote) {
        this.det_ven_pro_cod_lote = det_ven_pro_cod_lote;
    }

    public String getDet_ven_pro_id_compra() {
        return det_ven_pro_id_compra;
    }

    public void setDet_ven_pro_id_compra(String det_ven_pro_id_compra) {
        this.det_ven_pro_id_compra = det_ven_pro_id_compra;
    }

    @Override
    public String toString() {
        return "Entity_Venta_Producto{" + "det_ven_pro_id=" + det_ven_pro_id + ", det_ven_pro_id_venta=" + det_ven_pro_id_venta + ", det_ven_pro_id_producto=" + det_ven_pro_id_producto + ", det_ven_pro_id_igv=" + det_ven_pro_id_igv + ", det_ven_pro_cantidad=" + det_ven_pro_cantidad + ", det_ven_pro_precio=" + det_ven_pro_precio + ", det_ven_pro_sub_total=" + det_ven_pro_sub_total + ", det_ven_pro_total=" + det_ven_pro_total + ", det_ven_pro_desc_por=" + det_ven_pro_desc_por + ", det_ven_pro_desc_valor=" + det_ven_pro_desc_valor + ", det_ven_pro_fecha=" + det_ven_pro_fecha + ", det_ven_pro_cod_lote=" + det_ven_pro_cod_lote + ", det_ven_pro_id_compra=" + det_ven_pro_id_compra + '}';
    }
    

}
