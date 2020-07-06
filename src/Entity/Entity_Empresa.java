/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.sql.Blob;

/**
 *
 * @author pro
 */
public class Entity_Empresa {

    private String empresa_id;
    private String empresa_cod;
    private String empresa_ruc;
    private String empresa_razon_social;
    private String empresa_comercial;
    private String empresa_direccion;
    private String empresa_decrip;
    private Boolean empresa_estado;
    private Blob empresa_imagen;
    
    private String almacen_id;
    private String almacen_cod;
    private String almacen_descrip;
    private String almacen_empresa;
    private String almacen_estado;

    public Entity_Empresa(String almacen_id, String almacen_cod, String almacen_descrip, String almacen_empresa, String almacen_estado) {
        this.almacen_id = almacen_id;
        this.almacen_cod = almacen_cod;
        this.almacen_descrip = almacen_descrip;
        this.almacen_empresa = almacen_empresa;
        this.almacen_estado = almacen_estado;
    }
    
    
    

    public Entity_Empresa(String empresa_id, String empresa_cod, String empresa_ruc, String empresa_razon_social, String empresa_comercial, String empresa_direccion, String empresa_decrip, Boolean empresa_estado, Blob empresa_imagen) {
        this.empresa_id = empresa_id;
        this.empresa_cod = empresa_cod;
        this.empresa_ruc = empresa_ruc;
        this.empresa_razon_social = empresa_razon_social;
        this.empresa_comercial = empresa_comercial;
        this.empresa_direccion = empresa_direccion;
        this.empresa_decrip = empresa_decrip;
        this.empresa_estado = empresa_estado;
        this.empresa_imagen = empresa_imagen;
    }
    
    public Entity_Empresa(String empresa_id, String empresa_cod, String empresa_ruc, String empresa_razon_social, String empresa_comercial, String empresa_direccion, String empresa_decrip, Boolean empresa_estado) {
        this.empresa_id = empresa_id;
        this.empresa_cod = empresa_cod;
        this.empresa_ruc = empresa_ruc;
        this.empresa_razon_social = empresa_razon_social;
        this.empresa_comercial = empresa_comercial;
        this.empresa_direccion = empresa_direccion;
        this.empresa_decrip = empresa_decrip;
        this.empresa_estado = empresa_estado;
    }

    public Entity_Empresa() {
    }

    public String getEmpresa_id() {
        return empresa_id;
    }

    public void setEmpresa_id(String empresa_id) {
        this.empresa_id = empresa_id;
    }

    public String getEmpresa_cod() {
        return empresa_cod;
    }

    public void setEmpresa_cod(String empresa_cod) {
        this.empresa_cod = empresa_cod;
    }

    public String getEmpresa_ruc() {
        return empresa_ruc;
    }

    public void setEmpresa_ruc(String empresa_ruc) {
        this.empresa_ruc = empresa_ruc;
    }

    public String getEmpresa_razon_social() {
        return empresa_razon_social;
    }

    public void setEmpresa_razon_social(String empresa_razon_social) {
        this.empresa_razon_social = empresa_razon_social;
    }

    public String getEmpresa_comercial() {
        return empresa_comercial;
    }

    public void setEmpresa_comercial(String empresa_comercial) {
        this.empresa_comercial = empresa_comercial;
    }

    public String getEmpresa_direccion() {
        return empresa_direccion;
    }

    public void setEmpresa_direccion(String empresa_direccion) {
        this.empresa_direccion = empresa_direccion;
    }

    public String getEmpresa_decrip() {
        return empresa_decrip;
    }

    public void setEmpresa_decrip(String empresa_decrip) {
        this.empresa_decrip = empresa_decrip;
    }

    public Boolean getEmpresa_estado() {
        return empresa_estado;
    }

    public void setEmpresa_estado(Boolean empresa_estado) {
        this.empresa_estado = empresa_estado;
    }

    public Blob getEmpresa_imagen() {
        return empresa_imagen;
    }

    public void setEmpresa_imagen(Blob empresa_imagen) {
        this.empresa_imagen = empresa_imagen;
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

    public String getAlmacen_empresa() {
        return almacen_empresa;
    }

    public void setAlmacen_empresa(String almacen_empresa) {
        this.almacen_empresa = almacen_empresa;
    }

    public String getAlmacen_estado() {
        return almacen_estado;
    }

    public void setAlmacen_estado(String almacen_estado) {
        this.almacen_estado = almacen_estado;
    }

    @Override
    public String toString() {
        return   empresa_razon_social ;
    }

    
    
    
    
    
    
}
