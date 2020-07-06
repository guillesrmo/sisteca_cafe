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
public class Entity_Item {
    private String item_id;
    private String item_cod;
    private String item_descr;

    public Entity_Item(String item_id, String item_cod, String item_descr) {
        this.item_id = item_id;
        this.item_cod = item_cod;
        this.item_descr = item_descr;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_cod() {
        return item_cod;
    }

    public void setItem_cod(String item_cod) {
        this.item_cod = item_cod;
    }

    public String getItem_descr() {
        return item_descr;
    }

    public void setItem_descr(String item_descr) {
        this.item_descr = item_descr;
    }

    @Override
    public String toString() {
        return  item_cod ;
    }
    
}
