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
public class Entity_Venta {

    private String venta_id;
    private String venta_tipo_comprobante;
    private String comprobante_id;
    private String comprobante_cod;
    private String comprobante_descrip;
    private String venta_tipo_documento,documento_id,documento_cod,documento_descrip,documento_url;
    private String venta_cliente,persona_tipo_documento,persona_ruc,persona_dni,persona_razon_social,persona_razon_comercial,persona_direccion;
    private String venta_id_moneda,moneda_cod,moneda_descrip;
    private String venta_id_afectacion,afectacion_id,afectacion_cod,afectacion_descr,afectacion_valor,afectacion_cod_valor;
    private String venta_serie;
    private String venta_numero;
    private String venta_tipo_cambio ;
    private String venta_fecha_emision ;
    private String venta_fecha_vencimiento ;
    private String venta_ing_desc_global ;
    private String venta_desc_global ;
    private String venta_desc_iten ;
    private String venta_grava ;
    private String venta_exonerada ;
    private String venta_inafecta ;
    private String venta_igv ;
    private String venta_gratuita ;
    private String venta_otros_cargo ;
    private String venta_total ;
    private String venta_estado ;
    private String venta_empresa ;
    private String venta_persona ;
    private String venta_glosa;
    private String venta_sunat_transaccion;
    private String venta_concepto;
    private String concepto_id,concepto_cod,concepto_descripcion;
    private String transaccion_id,transaccion_cod,transaccion_descripcion;
    private String almacen_id,almacen_cod,almacen_descrip;
    private String entrega_id,entrega_descr,entrega_año;
    

    public Entity_Venta() {
        
    }

    public Entity_Venta(String venta_id, String venta_tipo_comprobante, String comprobante_id, String comprobante_cod, String comprobante_descrip, String venta_tipo_documento, String documento_id, String documento_cod, String documento_descrip, String documento_url, String venta_cliente, String persona_tipo_documento, String persona_ruc, String persona_dni, String persona_razon_social, String persona_razon_comercial, String persona_direccion, String venta_id_moneda, String moneda_cod, String moneda_descrip, String venta_id_afectacion, String afectacion_id, String afectacion_cod, String afectacion_descr, String afectacion_valor, String afectacion_cod_valor, String venta_serie, String venta_numero, String venta_tipo_cambio, String venta_fecha_emision, String venta_fecha_vencimiento, String venta_ing_desc_global, String venta_desc_global, String venta_desc_iten, String venta_grava, String venta_exonerada, String venta_inafecta, String venta_igv, String venta_gratuita, String venta_otros_cargo, String venta_total, String venta_estado, String venta_empresa, String venta_persona, String venta_glosa,String concepto_id,String concepto_cod,String concepto_descripcion,String transaccion_id,String transaccion_cod,String transaccion_descripcion,
            String almacen_id,String almacen_cod,String almacen_descrip ,String entrega_id,String entrega_descr,String entrega_año) {
        this.venta_id = venta_id;
        this.venta_tipo_comprobante = venta_tipo_comprobante;
        this.comprobante_id = comprobante_id;
        this.comprobante_cod = comprobante_cod;
        this.comprobante_descrip = comprobante_descrip;
        this.venta_tipo_documento = venta_tipo_documento;
        this.documento_id = documento_id;
        this.documento_cod = documento_cod;
        this.documento_descrip = documento_descrip;
        this.documento_url = documento_url;
        this.venta_cliente = venta_cliente;
        this.persona_tipo_documento = persona_tipo_documento;
        this.persona_ruc = persona_ruc;
        this.persona_dni = persona_dni;
        this.persona_razon_social = persona_razon_social;
        this.persona_razon_comercial = persona_razon_comercial;
        this.persona_direccion = persona_direccion;
        this.venta_id_moneda = venta_id_moneda;
        this.moneda_cod = moneda_cod;
        this.moneda_descrip = moneda_descrip;
        this.venta_id_afectacion = venta_id_afectacion;
        this.afectacion_id = afectacion_id;
        this.afectacion_cod = afectacion_cod;
        this.afectacion_descr = afectacion_descr;
        this.afectacion_valor = afectacion_valor;
        this.afectacion_cod_valor = afectacion_cod_valor;
        this.venta_serie = venta_serie;
        this.venta_numero = venta_numero;
        this.venta_tipo_cambio = venta_tipo_cambio;
        this.venta_fecha_emision = venta_fecha_emision;
        this.venta_fecha_vencimiento = venta_fecha_vencimiento;
        this.venta_ing_desc_global = venta_ing_desc_global;
        this.venta_desc_global = venta_desc_global;
        this.venta_desc_iten = venta_desc_iten;
        this.venta_grava = venta_grava;
        this.venta_exonerada = venta_exonerada;
        this.venta_inafecta = venta_inafecta;
        this.venta_igv = venta_igv;
        this.venta_gratuita = venta_gratuita;
        this.venta_otros_cargo = venta_otros_cargo;
        this.venta_total = venta_total;
        this.venta_estado = venta_estado;
        this.venta_empresa = venta_empresa;
        this.venta_persona = venta_persona;
        this.venta_glosa = venta_glosa;
        this.concepto_id = concepto_id;
        this.concepto_cod = concepto_cod;
        this.concepto_descripcion = concepto_descripcion;
        this.transaccion_id = transaccion_id;
        this.transaccion_cod = transaccion_cod;
        this.transaccion_descripcion = transaccion_descripcion;
        this.almacen_id = almacen_id;
        this.almacen_cod = almacen_cod;
        this.almacen_descrip = almacen_descrip;
        this.entrega_id = entrega_id;
        this.entrega_descr = entrega_descr;
        this.entrega_año = entrega_año;
        
        
    }
    

    public Entity_Venta(String venta_id, String venta_tipo_comprobante, String venta_tipo_documento, String venta_cliente, String venta_id_moneda, String venta_id_afectacion, String venta_serie, String venta_numero, String venta_tipo_cambio, String venta_fecha_emision, String venta_fecha_vencimiento, String venta_ing_desc_global, String venta_desc_global, String venta_desc_iten, String venta_grava, String venta_exonerada, String venta_inafecta, String venta_igv, String venta_gratuita, String venta_otros_cargo, String venta_total, String venta_estado, String venta_empresa, String venta_persona, String venta_glosa,String venta_sunat_transaccion,String venta_concepto,String entrega_id) {
        this.venta_id = venta_id;
        this.venta_tipo_comprobante = venta_tipo_comprobante;
        this.venta_tipo_documento = venta_tipo_documento;
        this.venta_cliente = venta_cliente;
        this.venta_id_moneda = venta_id_moneda;
        this.venta_id_afectacion = venta_id_afectacion;
        this.venta_serie = venta_serie;
        this.venta_numero = venta_numero;
        this.venta_tipo_cambio = venta_tipo_cambio;
        this.venta_fecha_emision = venta_fecha_emision;
        this.venta_fecha_vencimiento = venta_fecha_vencimiento;
        this.venta_ing_desc_global = venta_ing_desc_global;
        this.venta_desc_global = venta_desc_global;
        this.venta_desc_iten = venta_desc_iten;
        this.venta_grava = venta_grava;
        this.venta_exonerada = venta_exonerada;
        this.venta_inafecta = venta_inafecta;
        this.venta_igv = venta_igv;
        this.venta_gratuita = venta_gratuita;
        this.venta_otros_cargo = venta_otros_cargo;
        this.venta_total = venta_total;
        this.venta_estado = venta_estado;
        this.venta_empresa = venta_empresa;
        this.venta_persona = venta_persona;
        this.venta_glosa = venta_glosa;
        this.venta_sunat_transaccion=venta_sunat_transaccion;
        this.venta_concepto=venta_concepto;
        this.entrega_id=entrega_id;
        
    }
    

    public String getVenta_id() {
        return venta_id;
    }

    public void setVenta_id(String venta_id) {
        this.venta_id = venta_id;
    }

    public String getVenta_tipo_comprobante() {
        return venta_tipo_comprobante;
    }

    public void setVenta_tipo_comprobante(String venta_tipo_comprobante) {
        this.venta_tipo_comprobante = venta_tipo_comprobante;
    }

    public String getVenta_tipo_documento() {
        return venta_tipo_documento;
    }

    public void setVenta_tipo_documento(String venta_tipo_documento) {
        this.venta_tipo_documento = venta_tipo_documento;
    }

    public String getVenta_cliente() {
        return venta_cliente;
    }

    public void setVenta_cliente(String venta_cliente) {
        this.venta_cliente = venta_cliente;
    }

    public String getVenta_id_moneda() {
        return venta_id_moneda;
    }

    public void setVenta_id_moneda(String venta_id_moneda) {
        this.venta_id_moneda = venta_id_moneda;
    }

    public String getVenta_id_afectacion() {
        return venta_id_afectacion;
    }

    public void setVenta_id_afectacion(String venta_id_afectacion) {
        this.venta_id_afectacion = venta_id_afectacion;
    }

    public String getVenta_serie() {
        return venta_serie;
    }

    public void setVenta_serie(String venta_serie) {
        this.venta_serie = venta_serie;
    }

    public String getVenta_numero() {
        return venta_numero;
    }

    public void setVenta_numero(String venta_numero) {
        this.venta_numero = venta_numero;
    }

    public String getVenta_tipo_cambio() {
        return venta_tipo_cambio;
    }

    public void setVenta_tipo_cambio(String venta_tipo_cambio) {
        this.venta_tipo_cambio = venta_tipo_cambio;
    }

    public String getVenta_fecha_emision() {
        return venta_fecha_emision;
    }

    public void setVenta_fecha_emision(String venta_fecha_emision) {
        this.venta_fecha_emision = venta_fecha_emision;
    }

    public String getVenta_fecha_vencimiento() {
        return venta_fecha_vencimiento;
    }

    public void setVenta_fecha_vencimiento(String venta_fecha_vencimiento) {
        this.venta_fecha_vencimiento = venta_fecha_vencimiento;
    }

    public String getVenta_ing_desc_global() {
        return venta_ing_desc_global;
    }

    public void setVenta_ing_desc_global(String venta_ing_desc_global) {
        this.venta_ing_desc_global = venta_ing_desc_global;
    }

    public String getVenta_desc_global() {
        return venta_desc_global;
    }

    public void setVenta_desc_global(String venta_desc_global) {
        this.venta_desc_global = venta_desc_global;
    }

    public String getVenta_desc_iten() {
        return venta_desc_iten;
    }

    public void setVenta_desc_iten(String venta_desc_iten) {
        this.venta_desc_iten = venta_desc_iten;
    }

    public String getVenta_grava() {
        return venta_grava;
    }

    public void setVenta_grava(String venta_grava) {
        this.venta_grava = venta_grava;
    }

    public String getVenta_exonerada() {
        return venta_exonerada;
    }

    public void setVenta_exonerada(String venta_exonerada) {
        this.venta_exonerada = venta_exonerada;
    }

    public String getVenta_inafecta() {
        return venta_inafecta;
    }

    public void setVenta_inafecta(String venta_inafecta) {
        this.venta_inafecta = venta_inafecta;
    }

    public String getVenta_igv() {
        return venta_igv;
    }

    public void setVenta_igv(String venta_igv) {
        this.venta_igv = venta_igv;
    }

    public String getVenta_gratuita() {
        return venta_gratuita;
    }

    public void setVenta_gratuita(String venta_gratuita) {
        this.venta_gratuita = venta_gratuita;
    }

    public String getVenta_otros_cargo() {
        return venta_otros_cargo;
    }

    public void setVenta_otros_cargo(String venta_otros_cargo) {
        this.venta_otros_cargo = venta_otros_cargo;
    }

    public String getVenta_total() {
        return venta_total;
    }

    public void setVenta_total(String venta_total) {
        this.venta_total = venta_total;
    }

    public String getVenta_estado() {
        return venta_estado;
    }

    public void setVenta_estado(String venta_estado) {
        this.venta_estado = venta_estado;
    }

    public String getVenta_empresa() {
        return venta_empresa;
    }

    public void setVenta_empresa(String venta_empresa) {
        this.venta_empresa = venta_empresa;
    }

    public String getVenta_persona() {
        return venta_persona;
    }

    public void setVenta_persona(String venta_persona) {
        this.venta_persona = venta_persona;
    }

    public String getVenta_glosa() {
        return venta_glosa;
    }

    public void setVenta_glosa(String venta_glosa) {
        this.venta_glosa = venta_glosa;
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

    public String getVenta_sunat_transaccion() {
        return venta_sunat_transaccion;
    }

    public void setVenta_sunat_transaccion(String venta_sunat_transaccion) {
        this.venta_sunat_transaccion = venta_sunat_transaccion;
    }

    public String getVenta_concepto() {
        return venta_concepto;
    }

    public void setVenta_concepto(String venta_concepto) {
        this.venta_concepto = venta_concepto;
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

    public String getConcepto_descripcion() {
        return concepto_descripcion;
    }

    public void setConcepto_descripcion(String concepto_descripcion) {
        this.concepto_descripcion = concepto_descripcion;
    }

    public String getTransaccion_id() {
        return transaccion_id;
    }

    public void setTransaccion_id(String transaccion_id) {
        this.transaccion_id = transaccion_id;
    }

    public String getTransaccion_cod() {
        return transaccion_cod;
    }

    public void setTransaccion_cod(String transaccion_cod) {
        this.transaccion_cod = transaccion_cod;
    }

    public String getTransaccion_descripcion() {
        return transaccion_descripcion;
    }

    public void setTransaccion_descripcion(String transaccion_descripcion) {
        this.transaccion_descripcion = transaccion_descripcion;
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

    public String getEntrega_id() {
        return entrega_id;
    }

    public void setEntrega_id(String entrega_id) {
        this.entrega_id = entrega_id;
    }

    public String getEntrega_descr() {
        return entrega_descr;
    }

    public void setEntrega_descr(String entrega_descr) {
        this.entrega_descr = entrega_descr;
    }

    public String getEntrega_año() {
        return entrega_año;
    }

    public void setEntrega_año(String entrega_año) {
        this.entrega_año = entrega_año;
    }
    
    
    
    
    
    
    
    
    
    
    
}
