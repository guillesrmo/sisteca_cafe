/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author pro
 */
public class Entity_Compra {

    private String compra_id;
    private String compra_comprobante;
    private String compra_serie;
    private String compra_numero;
    private String compra_moneda;
    private String compra_afectacion;
    private String compra_fecha_emis;
    private String compra_fecha_ingreso_alm;
    private String compra_fecha_vencimiento;
    private String compra_fecha_contable;
    private String compra_proveedor;
    private String compra_glosa;
    private String compra_almacen;
    private String compra_persona;
    private boolean compra_estado;
    private boolean compra_estado_almacen;
    private String compra_ingrese_descuento_global;
    private String compra_descuento_global;
    private String compra_descuento_item;
    private String compra_gravada;
    private String compra_exonerada;
    private String compra_inafecta;
    private String compra_igv;
    private String compra_gratuita;
    private String compra_otros_cargos;
    private String compra_total;
    private String compra_cambio;
    private String compra_tipo_documento;
    private String comprobante_id;
    private String comprobante_cod;
    private String comprobante_descrip;
    private String moneda_id;
    private String moneda_cod;
    private String moneda_descrip;
    private String afectacion_id;
    private String afectacion_cod;
    private String afectacion_descr;
    private String afectacion_valor;
    private String afectacion_cod_valor;
    private String persona_id;
    private String persona_tipo_documento;
    private String persona_ruc;
    private String persona_dni;
    private String persona_razon_social;
    private String persona_razon_comercial;
    private String persona_direccion;
    private String almacen_id;
    private String almacen_cod;
    private String almacen_descrip;
    private String documento_id;
    private String documento_cod;
    private String documento_descrip;
    private String documento_url;
    private String compra_concepto;
    
    private String concepto_id;
    private String concepto_cod;
    private String concepto_desc;
    
    private String entrega_id;
    private String entrega_desc;
    private String entrega_año;

    public Entity_Compra() {
    }

    public Entity_Compra(String compra_id, String compra_comprobante, String compra_serie, String compra_numero, String compra_moneda, String compra_afectacion, String compra_fecha_emis, String compra_fecha_ingreso_alm, String compra_fecha_vencimiento, String compra_fecha_contable, String compra_proveedor, String compra_glosa, String compra_almacen, String compra_persona, boolean compra_estado, boolean compra_estado_almacen, String compra_ingrese_descuento_global, String compra_descuento_global, String compra_descuento_item, String compra_gravada, String compra_exonerada, String compra_inafecta, String compra_igv, String compra_gratuita, String compra_otros_cargos, String compra_total, String compra_cambio,String compra_tipo_documento,String compra_concepto,String entrega_id) {
        this.compra_id = compra_id;
        this.compra_comprobante = compra_comprobante;
        this.compra_serie = compra_serie;
        this.compra_numero = compra_numero;
        this.compra_moneda = compra_moneda;
        this.compra_afectacion = compra_afectacion;
        this.compra_fecha_emis = compra_fecha_emis;
        this.compra_fecha_ingreso_alm = compra_fecha_ingreso_alm;
        this.compra_fecha_vencimiento = compra_fecha_vencimiento;
        this.compra_fecha_contable = compra_fecha_contable;
        this.compra_proveedor = compra_proveedor;
        this.compra_glosa = compra_glosa;
        this.compra_almacen = compra_almacen;
        this.compra_persona = compra_persona;
        this.compra_estado = compra_estado;
        this.compra_estado_almacen = compra_estado_almacen;
        this.compra_ingrese_descuento_global = compra_ingrese_descuento_global;
        this.compra_descuento_global = compra_descuento_global;
        this.compra_descuento_item = compra_descuento_item;
        this.compra_gravada = compra_gravada;
        this.compra_exonerada = compra_exonerada;
        this.compra_inafecta = compra_inafecta;
        this.compra_igv = compra_igv;
        this.compra_gratuita = compra_gratuita;
        this.compra_otros_cargos = compra_otros_cargos;
        this.compra_total = compra_total;
        this.compra_cambio = compra_cambio;
        this.compra_tipo_documento = compra_tipo_documento;
        this.compra_concepto = compra_concepto;
        this.entrega_id=entrega_id;
        
    }

    public Entity_Compra(String compra_id, String compra_comprobante, String compra_serie, String compra_numero, String compra_moneda, String compra_afectacion, String compra_fecha_emis, String compra_fecha_ingreso_alm, String compra_fecha_vencimiento, String compra_fecha_contable, String compra_proveedor, String compra_glosa, String compra_almacen, String compra_persona, boolean compra_estado, boolean compra_estado_almacen, String compra_ingrese_descuento_global, String compra_descuento_global, String compra_descuento_item, String compra_gravada, String compra_exonerada, String compra_inafecta, String compra_igv, String compra_gratuita, String compra_otros_cargos, String compra_total, String compra_cambio,String compra_tipo_documento, String comprobante_id, String comprobante_cod, String comprobante_descrip, String moneda_id, String moneda_cod, String moneda_descrip, String afectacion_id, String afectacion_cod, String afectacion_descr, String afectacion_valor,String afectacion_cod_valor, String persona_id, String persona_tipo_documento, String persona_ruc, String persona_dni, String persona_razon_social, String persona_razon_comercial, String persona_direccion, String almacen_id, String almacen_cod, String almacen_descrip, String documento_id, String documento_cod, String documento_descrip, String documento_url,String concepto_id,String concepto_cod,String concepto_desc,String entrega_id,String entrega_desc,String entrega_año) {
        this.compra_id = compra_id;
        this.compra_comprobante = compra_comprobante;
        this.compra_serie = compra_serie;
        this.compra_numero = compra_numero;
        this.compra_moneda = compra_moneda;
        this.compra_afectacion = compra_afectacion;
        this.compra_fecha_emis = compra_fecha_emis;
        this.compra_fecha_ingreso_alm = compra_fecha_ingreso_alm;
        this.compra_fecha_vencimiento = compra_fecha_vencimiento;
        this.compra_fecha_contable = compra_fecha_contable;
        this.compra_proveedor = compra_proveedor;
        this.compra_glosa = compra_glosa;
        this.compra_almacen = compra_almacen;
        this.compra_persona = compra_persona;
        this.compra_estado = compra_estado;
        this.compra_estado_almacen = compra_estado_almacen;
        this.compra_ingrese_descuento_global = compra_ingrese_descuento_global;
        this.compra_descuento_global = compra_descuento_global;
        this.compra_descuento_item = compra_descuento_item;
        this.compra_gravada = compra_gravada;
        this.compra_exonerada = compra_exonerada;
        this.compra_inafecta = compra_inafecta;
        this.compra_igv = compra_igv;
        this.compra_gratuita = compra_gratuita;
        this.compra_otros_cargos = compra_otros_cargos;
        this.compra_total = compra_total;
        this.compra_cambio = compra_cambio;
        this.compra_tipo_documento = compra_tipo_documento;
        this.comprobante_id = comprobante_id;
        this.comprobante_cod = comprobante_cod;
        this.comprobante_descrip = comprobante_descrip;
        this.moneda_id = moneda_id;
        this.moneda_cod = moneda_cod;
        this.moneda_descrip = moneda_descrip;
        this.afectacion_id = afectacion_id;
        this.afectacion_cod = afectacion_cod;
        this.afectacion_descr = afectacion_descr;
        this.afectacion_valor = afectacion_valor;
        this.afectacion_cod_valor = afectacion_cod_valor;
        this.persona_id = persona_id;
        this.persona_tipo_documento = persona_tipo_documento;
        this.persona_ruc = persona_ruc;
        this.persona_dni = persona_dni;
        this.persona_razon_social = persona_razon_social;
        this.persona_razon_comercial = persona_razon_comercial;
        this.persona_direccion = persona_direccion;
        this.almacen_id = almacen_id;
        this.almacen_cod = almacen_cod;
        this.almacen_descrip = almacen_descrip;
        this.documento_id = documento_id;
        this.documento_cod = documento_cod;
        this.documento_descrip = documento_descrip;
        this.documento_url = documento_url;
        this.concepto_id = concepto_id;
        this.concepto_cod = concepto_cod;
        this.concepto_desc = concepto_desc;
        this.entrega_id=entrega_id;
        this.entrega_desc=entrega_desc;
        this.entrega_año=entrega_año;
        
    }
    

    

    public String getCompra_id() {
        return compra_id;
    }

    public void setCompra_id(String compra_id) {
        this.compra_id = compra_id;
    }

    public String getCompra_comprobante() {
        return compra_comprobante;
    }

    public void setCompra_comprobante(String compra_comprobante) {
        this.compra_comprobante = compra_comprobante;
    }

    public String getCompra_serie() {
        return compra_serie;
    }

    public void setCompra_serie(String compra_serie) {
        this.compra_serie = compra_serie;
    }

    public String getCompra_numero() {
        return compra_numero;
    }

    public void setCompra_numero(String compra_numero) {
        this.compra_numero = compra_numero;
    }

    public String getCompra_moneda() {
        return compra_moneda;
    }

    public void setCompra_moneda(String compra_moneda) {
        this.compra_moneda = compra_moneda;
    }

    public String getCompra_afectacion() {
        return compra_afectacion;
    }

    public void setCompra_afectacion(String compra_afectacion) {
        this.compra_afectacion = compra_afectacion;
    }

    public String getCompra_fecha_emis() {
        return compra_fecha_emis;
    }

    public void setCompra_fecha_emis(String compra_fecha_emis) {
        this.compra_fecha_emis = compra_fecha_emis;
    }

    public String getCompra_fecha_ingreso_alm() {
        return compra_fecha_ingreso_alm;
    }

    public void setCompra_fecha_ingreso_alm(String compra_fecha_ingreso_alm) {
        this.compra_fecha_ingreso_alm = compra_fecha_ingreso_alm;
    }

    public String getCompra_fecha_vencimiento() {
        return compra_fecha_vencimiento;
    }

    public void setCompra_fecha_vencimiento(String compra_fecha_vencimiento) {
        this.compra_fecha_vencimiento = compra_fecha_vencimiento;
    }

    public String getCompra_fecha_contable() {
        return compra_fecha_contable;
    }

    public void setCompra_fecha_contable(String compra_fecha_contable) {
        this.compra_fecha_contable = compra_fecha_contable;
    }

    public String getCompra_proveedor() {
        return compra_proveedor;
    }

    public void setCompra_proveedor(String compra_proveedor) {
        this.compra_proveedor = compra_proveedor;
    }

    public String getCompra_glosa() {
        return compra_glosa;
    }

    public void setCompra_glosa(String compra_glosa) {
        this.compra_glosa = compra_glosa;
    }

    public String getCompra_almacen() {
        return compra_almacen;
    }

    public void setCompra_almacen(String compra_almacen) {
        this.compra_almacen = compra_almacen;
    }

    public String getCompra_persona() {
        return compra_persona;
    }

    public void setCompra_persona(String compra_persona) {
        this.compra_persona = compra_persona;
    }

    public boolean getCompra_estado() {
        return compra_estado;
    }

    public void setCompra_estado(boolean compra_estado) {
        this.compra_estado = compra_estado;
    }

    public boolean getCompra_estado_almacen() {
        return compra_estado_almacen;
    }

    public void setCompra_estado_almacen(boolean compra_estado_almacen) {
        this.compra_estado_almacen = compra_estado_almacen;
    }

    public String getCompra_ingrese_descuento_global() {
        return compra_ingrese_descuento_global;
    }

    public void setCompra_ingrese_descuento_global(String compra_ingrese_descuento_global) {
        this.compra_ingrese_descuento_global = compra_ingrese_descuento_global;
    }

    public String getCompra_descuento_global() {
        return compra_descuento_global;
    }

    public void setCompra_descuento_global(String compra_descuento_global) {
        this.compra_descuento_global = compra_descuento_global;
    }

    public String getCompra_descuento_item() {
        return compra_descuento_item;
    }

    public void setCompra_descuento_item(String compra_descuento_item) {
        this.compra_descuento_item = compra_descuento_item;
    }

    public String getCompra_gravada() {
        return compra_gravada;
    }

    public void setCompra_gravada(String compra_gravada) {
        this.compra_gravada = compra_gravada;
    }

    public String getCompra_exonerada() {
        return compra_exonerada;
    }

    public void setCompra_exonerada(String compra_exonerada) {
        this.compra_exonerada = compra_exonerada;
    }

    public String getCompra_inafecta() {
        return compra_inafecta;
    }

    public void setCompra_inafecta(String compra_inafecta) {
        this.compra_inafecta = compra_inafecta;
    }

    public String getCompra_igv() {
        return compra_igv;
    }

    public void setCompra_igv(String compra_igv) {
        this.compra_igv = compra_igv;
    }

    public String getCompra_gratuita() {
        return compra_gratuita;
    }

    public void setCompra_gratuita(String compra_gratuita) {
        this.compra_gratuita = compra_gratuita;
    }

    public String getCompra_otros_cargos() {
        return compra_otros_cargos;
    }

    public void setCompra_otros_cargos(String compra_otros_cargos) {
        this.compra_otros_cargos = compra_otros_cargos;
    }

    public String getCompra_total() {
        return compra_total;
    }

    public void setCompra_total(String compra_total) {
        this.compra_total = compra_total;
    }

    public String getCompra_cambio() {
        return compra_cambio;
    }

    public void setCompra_cambio(String compra_cambio) {
        this.compra_cambio = compra_cambio;
    }

    public String getCompra_tipo_documento() {
        return compra_tipo_documento;
    }

    public void setCompra_tipo_documento(String compra_tipo_documento) {
        this.compra_tipo_documento = compra_tipo_documento;
    }
    

    public String getComprobante_id() {
        return comprobante_id;
    }

    public void setComprobante_id(String comprobante_id) {
        this.comprobante_id = comprobante_id;
    }

    public String getComprobante_cod() {
        return comprobante_cod;
    }

    public void setComprobante_cod(String comprobante_cod) {
        this.comprobante_cod = comprobante_cod;
    }

    public String getComprobante_descrip() {
        return comprobante_descrip;
    }

    public void setComprobante_descrip(String comprobante_descrip) {
        this.comprobante_descrip = comprobante_descrip;
    }

    public String getMoneda_id() {
        return moneda_id;
    }

    public void setMoneda_id(String moneda_id) {
        this.moneda_id = moneda_id;
    }

    public String getMoneda_cod() {
        return moneda_cod;
    }

    public void setMoneda_cod(String moneda_cod) {
        this.moneda_cod = moneda_cod;
    }

    public String getMoneda_descrip() {
        return moneda_descrip;
    }

    public void setMoneda_descrip(String moneda_descrip) {
        this.moneda_descrip = moneda_descrip;
    }

    public String getAfectacion_id() {
        return afectacion_id;
    }

    public void setAfectacion_id(String afectacion_id) {
        this.afectacion_id = afectacion_id;
    }

    public String getAfectacion_cod() {
        return afectacion_cod;
    }

    public void setAfectacion_cod(String afectacion_cod) {
        this.afectacion_cod = afectacion_cod;
    }

    public String getAfectacion_descr() {
        return afectacion_descr;
    }

    public void setAfectacion_descr(String afectacion_descr) {
        this.afectacion_descr = afectacion_descr;
    }

    public String getAfectacion_valor() {
        return afectacion_valor;
    }

    public void setAfectacion_valor(String afectacion_valor) {
        this.afectacion_valor = afectacion_valor;
    }

    public String getAfectacion_cod_valor() {
        return afectacion_cod_valor;
    }

    public void setAfectacion_cod_valor(String afectacion_cod_valor) {
        this.afectacion_cod_valor = afectacion_cod_valor;
    }
    

    public String getPersona_id() {
        return persona_id;
    }

    public void setPersona_id(String persona_id) {
        this.persona_id = persona_id;
    }

    public String getPersona_tipo_documento() {
        return persona_tipo_documento;
    }

    public void setPersona_tipo_documento(String persona_tipo_documento) {
        this.persona_tipo_documento = persona_tipo_documento;
    }

    public String getPersona_ruc() {
        return persona_ruc;
    }

    public void setPersona_ruc(String persona_ruc) {
        this.persona_ruc = persona_ruc;
    }

    public String getPersona_dni() {
        return persona_dni;
    }

    public void setPersona_dni(String persona_dni) {
        this.persona_dni = persona_dni;
    }

    public String getPersona_razon_social() {
        return persona_razon_social;
    }

    public void setPersona_razon_social(String persona_razon_social) {
        this.persona_razon_social = persona_razon_social;
    }

    public String getPersona_razon_comercial() {
        return persona_razon_comercial;
    }

    public void setPersona_razon_comercial(String persona_razon_comercial) {
        this.persona_razon_comercial = persona_razon_comercial;
    }

    public String getPersona_direccion() {
        return persona_direccion;
    }

    public void setPersona_direccion(String persona_direccion) {
        this.persona_direccion = persona_direccion;
    }

    public String getAlmacen_id() {
        return almacen_id;
    }

    public void setAlmacen_id(String almacen_id) {
        this.almacen_id = almacen_id;
    }

    public String getAlmacen_cod() {
        return almacen_cod;
    }

    public void setAlmacen_cod(String almacen_cod) {
        this.almacen_cod = almacen_cod;
    }

    public String getAlmacen_descrip() {
        return almacen_descrip;
    }

    public void setAlmacen_descrip(String almacen_descrip) {
        this.almacen_descrip = almacen_descrip;
    }

    public String getDocumento_id() {
        return documento_id;
    }

    public void setDocumento_id(String documento_id) {
        this.documento_id = documento_id;
    }

    public String getDocumento_cod() {
        return documento_cod;
    }

    public void setDocumento_cod(String documento_cod) {
        this.documento_cod = documento_cod;
    }

    public String getDocumento_descrip() {
        return documento_descrip;
    }

    public void setDocumento_descrip(String documento_descrip) {
        this.documento_descrip = documento_descrip;
    }

    public String getDocumento_url() {
        return documento_url;
    }

    public void setDocumento_url(String documento_url) {
        this.documento_url = documento_url;
    }

    public String getCompra_concepto() {
        return compra_concepto;
    }

    public void setCompra_concepto(String compra_concepto) {
        this.compra_concepto = compra_concepto;
    }

    public String getConcepto_id() {
        return concepto_id;
    }

    public void setConcepto_id(String concepto_id) {
        this.concepto_id = concepto_id;
    }

    public String getConcepto_cod() {
        return concepto_cod;
    }

    public void setConcepto_cod(String concepto_cod) {
        this.concepto_cod = concepto_cod;
    }

    public String getConcepto_desc() {
        return concepto_desc;
    }

    public void setConcepto_desc(String concepto_desc) {
        this.concepto_desc = concepto_desc;
    }

    public String getEntrega_id() {
        return entrega_id;
    }

    public void setEntrega_id(String entrega_id) {
        this.entrega_id = entrega_id;
    }

    public String getEntrega_desc() {
        return entrega_desc;
    }

    public void setEntrega_desc(String entrega_desc) {
        this.entrega_desc = entrega_desc;
    }

    public String getEntrega_año() {
        return entrega_año;
    }

    public void setEntrega_año(String entrega_año) {
        this.entrega_año = entrega_año;
    }
    
    
    
    
    

}
