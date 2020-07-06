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
public class Entity_Almacen {
    private String almacen_id;
    private String almacen_cod;
    private String almacen_descr;
    
    private String almacen_empresa;
    private String almacen_estado;

    public Entity_Almacen() {
    }

    public Entity_Almacen(String almacen_id, String almacen_cod, String almacen_descr) {
        this.almacen_id = almacen_id;
        this.almacen_cod = almacen_cod;
        this.almacen_descr = almacen_descr;
    }

    public Entity_Almacen(String almacen_id, String almacen_cod, String almacen_descr, String almacen_empresa, String almacen_estado) {
        this.almacen_id = almacen_id;
        this.almacen_cod = almacen_cod;
        this.almacen_descr = almacen_descr;
        this.almacen_empresa = almacen_empresa;
        this.almacen_estado = almacen_estado;
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

    public String getAlmacen_descr() {
        return almacen_descr;
    }

    public void setAlmacen_descr(String almacen_descr) {
        this.almacen_descr = almacen_descr;
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
        return almacen_descr;
    }
    
}
