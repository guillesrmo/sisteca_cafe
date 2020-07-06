/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author User
 */
public class Entity_Entrega {
    private String entrega_id;
    private String entrega_descrip;
    private String entrega_años;
    

    public Entity_Entrega(String entrega_id, String entrega_descrip, String entrega_años) {
        this.entrega_id = entrega_id;
        this.entrega_descrip = entrega_descrip;
        this.entrega_años = entrega_años;
    }

    public Entity_Entrega() {
    }
    

    
    public String getEntrega_id() {
        return entrega_id;
    }

    public void setEntrega_id(String entrega_id) {
        this.entrega_id = entrega_id;
    }

    public String getEntrega_descrip() {
        return entrega_descrip;
    }

    public void setEntrega_descrip(String entrega_descrip) {
        this.entrega_descrip = entrega_descrip;
    }

    public String getEntrega_años() {
        return entrega_años;
    }

    public void setEntrega_años(String entrega_años) {
        this.entrega_años = entrega_años;
    }

    @Override
    public String toString() {
        return  entrega_descrip + " " + entrega_años ;
    }
    
    
    
    
}
