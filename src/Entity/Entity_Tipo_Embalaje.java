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
public class Entity_Tipo_Embalaje {
    private String enbalaje_id;
    private String enbalaje_cod;
    private String enbalaje_descrip;

    public Entity_Tipo_Embalaje(String enbalaje_id, String enbalaje_cod, String enbalaje_descrip) {
        this.enbalaje_id = enbalaje_id;
        this.enbalaje_cod = enbalaje_cod;
        this.enbalaje_descrip = enbalaje_descrip;
    }

    public String getEnbalaje_id() {
        return enbalaje_id;
    }

    public void setEnbalaje_id(String enbalaje_id) {
        this.enbalaje_id = enbalaje_id;
    }

    public String getEnbalaje_cod() {
        return enbalaje_cod;
    }

    public void setEnbalaje_cod(String enbalaje_cod) {
        this.enbalaje_cod = enbalaje_cod;
    }

    public String getEnbalaje_descrip() {
        return enbalaje_descrip;
    }

    public void setEnbalaje_descrip(String enbalaje_descrip) {
        this.enbalaje_descrip = enbalaje_descrip;
    }

    @Override
    public String toString() {
        return  enbalaje_descrip ;
    }
    
    
    
}
