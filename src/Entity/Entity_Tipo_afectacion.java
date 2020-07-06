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
public class Entity_Tipo_afectacion {
    private String id;
    private String cod;
    private String descr;
    private String afectacion_valor;
    private String afectacion_cod_valor;

    public Entity_Tipo_afectacion() {
    }

    public Entity_Tipo_afectacion(String id, String cod, String descr, String afectacion_valor, String afectacion_cod_valor) {
        this.id = id;
        this.cod = cod;
        this.descr = descr;
        this.afectacion_valor = afectacion_valor;
        this.afectacion_cod_valor = afectacion_cod_valor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
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

    

    
    @Override
    public String toString() {
        return descr;
    }
    
    
    
}
