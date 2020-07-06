/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.logging.Logger;

/**
 *
 * @author memo
 */
public class Entity_Producto {
    private String producto_id;
    private String producto_cod;
    private String producto_descrip;
    private String producto_descripcort;
    private String producto_clasifi;
    private String producto_clasifi_cod;
    private String producto_clasifi_desc;
    private String producto_moneda;
    private String producto_moneda_cod;
    private String producto_moneda_decs;
    private String producto_familia;
    private String producto_familia_cod;
    private String producto_familia_desc;
    private String producto_medida;
    private String producto_medida_cod;
    private String producto_medida_desc;
    private String producto_observacion;
    private String producto_tipo_afect;
    private String producto_tipo_afect_cod;
    private String producto_tipo_afect_desc;
    private String producto_tipo_valor;
    private String producto_afectacion_cod_valor;
    private boolean producto_estado;
    private String producto_valor;
    private String sunat_id;
    private String sunat_cod;
    private String sunat_descrip;
    private String producto_cantidad;
    private String embalaje_id;
    private String embalaje_cod;
    private String embajale_descr;

    public Entity_Producto() {
    }
    
    

    public Entity_Producto(String producto_id, String producto_cod, String producto_descrip, String producto_descripcort, String producto_clasifi, String producto_moneda, String producto_familia, String producto_medida, String producto_observacion, String producto_tipo_afect, boolean producto_estado, String producto_valor,String producto_cantidad,String embalaje_id) {
        this.producto_id = producto_id;
        this.producto_cod = producto_cod;
        this.producto_descrip = producto_descrip;
        this.producto_descripcort = producto_descripcort;
        this.producto_clasifi = producto_clasifi;
        this.producto_moneda = producto_moneda;
        this.producto_familia = producto_familia;
        this.producto_medida = producto_medida;
        this.producto_observacion = producto_observacion;
        this.producto_tipo_afect = producto_tipo_afect;
        this.producto_estado = producto_estado;
        this.producto_valor = producto_valor;
        this.producto_cantidad = producto_cantidad;
        this.embalaje_id = embalaje_id;
    }

    public Entity_Producto(String producto_id, String producto_cod, String producto_descrip, String producto_descripcort, String producto_clasifi, String producto_clasifi_cod, String producto_clasifi_desc, String producto_moneda, String producto_moneda_cod, String producto_moneda_decs, String producto_familia, String producto_familia_cod, String producto_familia_desc, String producto_medida, String producto_medida_cod, String producto_medida_desc, String producto_observacion, String producto_tipo_afect, String producto_tipo_afect_cod, String producto_tipo_afect_desc, String producto_tipo_valor, String producto_afectacion_cod_valor, boolean producto_estado, String producto_valor, String sunat_id, String sunat_cod, String sunat_descrip,
            String producto_cantidad ,String embalaje_id ,String embalaje_cod,String embajale_descr) {
        this.producto_id = producto_id;
        this.producto_cod = producto_cod;
        this.producto_descrip = producto_descrip;
        this.producto_descripcort = producto_descripcort;
        this.producto_clasifi = producto_clasifi;
        this.producto_clasifi_cod = producto_clasifi_cod;
        this.producto_clasifi_desc = producto_clasifi_desc;
        this.producto_moneda = producto_moneda;
        this.producto_moneda_cod = producto_moneda_cod;
        this.producto_moneda_decs = producto_moneda_decs;
        this.producto_familia = producto_familia;
        this.producto_familia_cod = producto_familia_cod;
        this.producto_familia_desc = producto_familia_desc;
        this.producto_medida = producto_medida;
        this.producto_medida_cod = producto_medida_cod;
        this.producto_medida_desc = producto_medida_desc;
        this.producto_observacion = producto_observacion;
        this.producto_tipo_afect = producto_tipo_afect;
        this.producto_tipo_afect_cod = producto_tipo_afect_cod;
        this.producto_tipo_afect_desc = producto_tipo_afect_desc;
        this.producto_tipo_valor = producto_tipo_valor;
        this.producto_afectacion_cod_valor = producto_afectacion_cod_valor;
        this.producto_estado = producto_estado;
        this.producto_valor = producto_valor;
        this.sunat_id = sunat_id;
        this.sunat_cod = sunat_cod;
        this.sunat_descrip = sunat_descrip;
        this.producto_cantidad = producto_cantidad;
        this.embalaje_id = embalaje_id;
        this.embalaje_cod = embalaje_cod;
        this.embajale_descr = embajale_descr;
    }
    

    

    
    public String getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(String producto_id) {
        this.producto_id = producto_id;
    }

    public String getProducto_cod() {
        return producto_cod;
    }

    public void setProducto_cod(String producto_cod) {
        this.producto_cod = producto_cod;
    }

    public String getProducto_descrip() {
        return producto_descrip;
    }

    public void setProducto_descrip(String producto_descrip) {
        this.producto_descrip = producto_descrip;
    }

    public String getProducto_descripcort() {
        return producto_descripcort;
    }

    public void setProducto_descripcort(String producto_descripcort) {
        this.producto_descripcort = producto_descripcort;
    }

    public String getProducto_clasifi() {
        return producto_clasifi;
    }

    public void setProducto_clasifi(String producto_clasifi) {
        this.producto_clasifi = producto_clasifi;
    }

    public String getProducto_clasifi_cod() {
        return producto_clasifi_cod;
    }

    public void setProducto_clasifi_cod(String producto_clasifi_cod) {
        this.producto_clasifi_cod = producto_clasifi_cod;
    }

    public String getProducto_clasifi_desc() {
        return producto_clasifi_desc;
    }

    public void setProducto_clasifi_desc(String producto_clasifi_desc) {
        this.producto_clasifi_desc = producto_clasifi_desc;
    }

    public String getProducto_moneda() {
        return producto_moneda;
    }

    public void setProducto_moneda(String producto_moneda) {
        this.producto_moneda = producto_moneda;
    }

    public String getProducto_moneda_cod() {
        return producto_moneda_cod;
    }

    public void setProducto_moneda_cod(String producto_moneda_cod) {
        this.producto_moneda_cod = producto_moneda_cod;
    }

    public String getProducto_moneda_decs() {
        return producto_moneda_decs;
    }

    public void setProducto_moneda_decs(String producto_moneda_decs) {
        this.producto_moneda_decs = producto_moneda_decs;
    }

    public String getProducto_familia() {
        return producto_familia;
    }

    public void setProducto_familia(String producto_familia) {
        this.producto_familia = producto_familia;
    }

    public String getProducto_familia_cod() {
        return producto_familia_cod;
    }

    public void setProducto_familia_cod(String producto_familia_cod) {
        this.producto_familia_cod = producto_familia_cod;
    }

    public String getProducto_familia_desc() {
        return producto_familia_desc;
    }

    public void setProducto_familia_desc(String producto_familia_desc) {
        this.producto_familia_desc = producto_familia_desc;
    }

    public String getProducto_medida() {
        return producto_medida;
    }

    public void setProducto_medida(String producto_medida) {
        this.producto_medida = producto_medida;
    }

    public String getProducto_medida_cod() {
        return producto_medida_cod;
    }

    public void setProducto_medida_cod(String producto_medida_cod) {
        this.producto_medida_cod = producto_medida_cod;
    }

    public String getProducto_medida_desc() {
        return producto_medida_desc;
    }

    public void setProducto_medida_desc(String producto_medida_desc) {
        this.producto_medida_desc = producto_medida_desc;
    }

    public String getProducto_observacion() {
        return producto_observacion;
    }

    public void setProducto_observacion(String producto_observacion) {
        this.producto_observacion = producto_observacion;
    }

    public String getProducto_tipo_afect() {
        return producto_tipo_afect;
    }

    public void setProducto_tipo_afect(String producto_tipo_afect) {
        this.producto_tipo_afect = producto_tipo_afect;
    }

    public String getProducto_tipo_afect_cod() {
        return producto_tipo_afect_cod;
    }

    public void setProducto_tipo_afect_cod(String producto_tipo_afect_cod) {
        this.producto_tipo_afect_cod = producto_tipo_afect_cod;
    }

    public String getProducto_tipo_afect_desc() {
        return producto_tipo_afect_desc;
    }

    public void setProducto_tipo_afect_desc(String producto_tipo_afect_desc) {
        this.producto_tipo_afect_desc = producto_tipo_afect_desc;
    }

    public String getProducto_tipo_valor() {
        return producto_tipo_valor;
    }

    public void setProducto_tipo_valor(String producto_tipo_valor) {
        this.producto_tipo_valor = producto_tipo_valor;
    }

    public String getProducto_afectacion_cod_valor() {
        return producto_afectacion_cod_valor;
    }

    public void setProducto_afectacion_cod_valor(String producto_afectacion_cod_valor) {
        this.producto_afectacion_cod_valor = producto_afectacion_cod_valor;
    }
    

    public boolean getProducto_estado() {
        return producto_estado;
    }

    public void setProducto_estado(boolean producto_estado) {
        this.producto_estado = producto_estado;
    }

    public String getProducto_valor() {
        return producto_valor;
    }

    public void setProducto_valor(String producto_valor) {
        this.producto_valor = producto_valor;
    }

    public String getSunat_id() {
        return sunat_id;
    }

    public void setSunat_id(String sunat_id) {
        this.sunat_id = sunat_id;
    }

    public String getSunat_cod() {
        return sunat_cod;
    }

    public void setSunat_cod(String sunat_cod) {
        this.sunat_cod = sunat_cod;
    }

    public String getSunat_descrip() {
        return sunat_descrip;
    }

    public void setSunat_descrip(String sunat_descrip) {
        this.sunat_descrip = sunat_descrip;
    }

    public String getEmbalaje_id() {
        return embalaje_id;
    }

    public void setEmbalaje_id(String embalaje_id) {
        this.embalaje_id = embalaje_id;
    }

    public String getEmbalaje_cod() {
        return embalaje_cod;
    }

    public void setEmbalaje_cod(String embalaje_cod) {
        this.embalaje_cod = embalaje_cod;
    }

    public String getEmbajale_descr() {
        return embajale_descr;
    }

    public void setEmbajale_descr(String embajale_descr) {
        this.embajale_descr = embajale_descr;
    }

    public String getProducto_cantidad() {
        return producto_cantidad;
    }

    public void setProducto_cantidad(String producto_cantidad) {
        this.producto_cantidad = producto_cantidad;
    }
    
    
    

    
    @Override
    public String toString() {
        return "'" + producto_id + "','" + producto_cod + "','" + producto_descrip + "','" + producto_descripcort + "','" + producto_clasifi + "','" + producto_moneda + "','" + producto_familia + "','" + producto_medida + "','" + producto_observacion + "','" + producto_tipo_afect + "','" + producto_estado + "'";
    }
    private static final Logger LOG = Logger.getLogger(Entity_Producto.class.getName());
    
    
    
}
