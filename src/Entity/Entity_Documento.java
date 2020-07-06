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
public class Entity_Documento {
    private String documento_id;
    private String documento_cod;
    private String documento_descrip;
    private String documento_url;

    public Entity_Documento() {
    }

    public Entity_Documento(String documento_id, String documento_cod, String documento_descrip, String documento_url) {
        this.documento_id = documento_id;
        this.documento_cod = documento_cod;
        this.documento_descrip = documento_descrip;
        this.documento_url = documento_url;
    }

    public String getDocumento_id() {
        return documento_id;
    }

    public void setDocumento_id(String documento_id) {
        this.documento_id = documento_id;
    }

    public String getDocumento_cod() {
        return documento_cod;
    }

    public void setDocumento_cod(String documento_cod) {
        this.documento_cod = documento_cod;
    }

    public String getDocumento_descrip() {
        return documento_descrip;
    }

    public void setDocumento_descrip(String documento_descrip) {
        this.documento_descrip = documento_descrip;
    }

    public String getDocumento_url() {
        return documento_url;
    }

    public void setDocumento_url(String documento_url) {
        this.documento_url = documento_url;
    }

    @Override
    public String toString() {
        return documento_descrip ;
    }
    
    
    
}
