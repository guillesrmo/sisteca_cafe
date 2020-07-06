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
public class Entity_Concepto {
    private String concepto_id;
    private String concepto_cod;
    private String concepto_descripcion;

    public Entity_Concepto() {
    }

    public Entity_Concepto(String concepto_id, String concepto_cod, String concepto_descripcion) {
        this.concepto_id = concepto_id;
        this.concepto_cod = concepto_cod;
        this.concepto_descripcion = concepto_descripcion;
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

    @Override
    public String toString() {
        return concepto_descripcion ;
    }
    
    
}
