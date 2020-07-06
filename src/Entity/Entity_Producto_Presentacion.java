/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author memo
 */
public class Entity_Producto_Presentacion {
    private String present_id;
    private String present_cod_producto;
    private String present_cod_barra;
    private String present_descripcion;
    private String present_descripcion_corta;
    private String present_cod_unidad;
    private String present_cantidad;
    private String present_prec_compra;
    private String present_prec_venta;
    private boolean present_estado;
    private String present_peso;
    
    private String afectacion_id,afectacion_descr,afectacion_valor,afectacion_cod_valor;
    
    private String stock;

    public Entity_Producto_Presentacion(String present_id, String present_cod_barra, String present_descripcion, String present_cod_unidad, String present_cantidad, String present_prec_venta, String afectacion_id, String afectacion_descr, String afectacion_valor, String afectacion_cod_valor,String nulo) {
        this.present_id = present_id;
        this.present_cod_barra = present_cod_barra;
        this.present_descripcion = present_descripcion;
        this.present_cod_unidad = present_cod_unidad;
        this.present_cantidad = present_cantidad;
        this.present_prec_venta = present_prec_venta;
        this.afectacion_id = afectacion_id;
        this.afectacion_descr = afectacion_descr;
        this.afectacion_valor = afectacion_valor;
        this.afectacion_cod_valor = afectacion_cod_valor;
    }
    
    public Entity_Producto_Presentacion(String present_id, String present_cod_barra, String present_descripcion, String present_cod_unidad, String present_cantidad, String present_prec_venta, String afectacion_id, String afectacion_descr, String afectacion_valor, String afectacion_cod_valor,String nulo,String stock) {
        this.present_id = present_id;
        this.present_cod_barra = present_cod_barra;
        this.present_descripcion = present_descripcion;
        this.present_cod_unidad = present_cod_unidad;
        this.present_cantidad = present_cantidad;
        this.present_prec_venta = present_prec_venta;
        this.afectacion_id = afectacion_id;
        this.afectacion_descr = afectacion_descr;
        this.afectacion_valor = afectacion_valor;
        this.afectacion_cod_valor = afectacion_cod_valor;
        this.stock = stock;
    }
    
    

    public Entity_Producto_Presentacion(String present_id, String present_cod_barra, String present_descripcion, String present_cod_unidad, String present_cantidad, String present_prec_compra) {
        this.present_id = present_id;
        this.present_cod_barra = present_cod_barra;
        this.present_descripcion = present_descripcion;
        this.present_cod_unidad = present_cod_unidad;
        this.present_cantidad = present_cantidad;
        this.present_prec_compra = present_prec_compra;
    }
    
    public Entity_Producto_Presentacion(String present_id, String present_cod_barra, String present_descripcion, String present_cod_unidad, String present_cantidad, String present_prec_compra,String stock) {
        this.present_id = present_id;
        this.present_cod_barra = present_cod_barra;
        this.present_descripcion = present_descripcion;
        this.present_cod_unidad = present_cod_unidad;
        this.present_cantidad = present_cantidad;
        this.present_prec_compra = present_prec_compra;
        this.stock = stock;
    }
    

    public Entity_Producto_Presentacion(String present_id, String present_cod_producto, String present_cod_barra, String present_descripcion, String present_descripcion_corta, String present_cod_unidad, String present_cantidad, String present_prec_compra, String present_prec_venta, boolean present_estado,String present_peso) {
        this.present_id = present_id;
        this.present_cod_producto = present_cod_producto;
        this.present_cod_barra = present_cod_barra;
        this.present_descripcion = present_descripcion;
        this.present_descripcion_corta = present_descripcion_corta;
        this.present_cod_unidad = present_cod_unidad;
        this.present_cantidad = present_cantidad;
        this.present_prec_compra = present_prec_compra;
        this.present_prec_venta = present_prec_venta;
        this.present_estado = present_estado;
        this.present_peso=present_peso;
    }

    public Entity_Producto_Presentacion() {
    }
    
    
    

    public String getPresent_id() {
        return present_id;
    }

    public void setPresent_id(String present_id) {
        this.present_id = present_id;
    }

    public String getPresent_cod_producto() {
        return present_cod_producto;
    }

    public void setPresent_cod_producto(String present_cod_producto) {
        this.present_cod_producto = present_cod_producto;
    }

    public String getPresent_cod_barra() {
        return present_cod_barra;
    }

    public void setPresent_cod_barra(String present_cod_barra) {
        this.present_cod_barra = present_cod_barra;
    }

    public String getPresent_descripcion() {
        return present_descripcion;
    }

    public void setPresent_descripcion(String present_descripcion) {
        this.present_descripcion = present_descripcion;
    }

    public String getPresent_descripcion_corta() {
        return present_descripcion_corta;
    }

    public void setPresent_descripcion_corta(String present_descripcion_corta) {
        this.present_descripcion_corta = present_descripcion_corta;
    }

    public String getPresent_cod_unidad() {
        return present_cod_unidad;
    }

    public void setPresent_cod_unidad(String present_cod_unidad) {
        this.present_cod_unidad = present_cod_unidad;
    }

    public String getPresent_cantidad() {
        return present_cantidad;
    }

    public void setPresent_cantidad(String present_cantidad) {
        this.present_cantidad = present_cantidad;
    }

    public String getPresent_prec_compra() {
        return present_prec_compra;
    }

    public void setPresent_prec_compra(String present_prec_compra) {
        this.present_prec_compra = present_prec_compra;
    }

    public String getPresent_prec_venta() {
        return present_prec_venta;
    }

    public void setPresent_prec_venta(String present_prec_venta) {
        this.present_prec_venta = present_prec_venta;
    }

    public boolean getPresent_estado() {
        return present_estado;
    }

    public void setPresent_estado(boolean present_estado) {
        this.present_estado = present_estado;
    }

    public String getAfectacion_id() {
        return afectacion_id;
    }

    public void setAfectacion_id(String afectacion_id) {
        this.afectacion_id = afectacion_id;
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

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getPresent_peso() {
        return present_peso;
    }

    public void setPresent_peso(String present_peso) {
        this.present_peso = present_peso;
    }
    
    
    
}
