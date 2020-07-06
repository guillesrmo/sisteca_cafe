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
public class Entity_Moneda {
    private String moneda_id;
    private String moneda_cod;
    private String moneda_descrip;

    public Entity_Moneda() {
    }

    public Entity_Moneda(String moneda_id, String moneda_cod, String moneda_descrip) {
        this.moneda_id = moneda_id;
        this.moneda_cod = moneda_cod;
        this.moneda_descrip = moneda_descrip;
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

    @Override
    public String toString() {
        return  moneda_descrip ;
    }
    
    
    
}
