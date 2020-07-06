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
public class Entity_Sunat_transaccion {
    private String transaccion_id;
    private String transaccion_cod;
    private String transaccion_descripcion;

    public Entity_Sunat_transaccion(String transaccion_id, String transaccion_cod, String transaccion_descripcion) {
        this.transaccion_id = transaccion_id;
        this.transaccion_cod = transaccion_cod;
        this.transaccion_descripcion = transaccion_descripcion;
    }

    public Entity_Sunat_transaccion() {
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

    @Override
    public String toString() {
        return  transaccion_descripcion ;
    }
    
    
    
    
}
