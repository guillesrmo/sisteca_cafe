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
public class Entity_Tipo_Comprobante {
    private String comprobante_id;
    private String comprobante_cod;
    private String comprobante_descrip;

    public Entity_Tipo_Comprobante() {
    }

   
    
    

    
    public Entity_Tipo_Comprobante(String comprobante_id, String comprobante_cod, String comprobante_descrip) {
        this.comprobante_id = comprobante_id;
        this.comprobante_cod = comprobante_cod;
        this.comprobante_descrip = comprobante_descrip;
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

    @Override
    public String toString() {
        return comprobante_descrip ;
    }
    
    
}
