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
public class Entity_Usuario {

    private String usuario_id;
    private String usuario_cod;
    private String usuario_user;
    private String usuario_contrasena;
    private boolean usuario_estado;

    public Entity_Usuario() {
    }

    public Entity_Usuario(String usuario_id, String usuario_cod, String usuario_user, String usuario_contrasena, boolean usuario_estado) {
        this.usuario_id = usuario_id;
        this.usuario_cod = usuario_cod;
        this.usuario_user = usuario_user;
        this.usuario_contrasena = usuario_contrasena;
        this.usuario_estado = usuario_estado;
    }

    public String getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(String usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getUsuario_cod() {
        return usuario_cod;
    }

    public void setUsuario_cod(String usuario_cod) {
        this.usuario_cod = usuario_cod;
    }

    public String getUsuario_user() {
        return usuario_user;
    }

    public void setUsuario_user(String usuario_user) {
        this.usuario_user = usuario_user;
    }

    public String getUsuario_contrasena() {
        return usuario_contrasena;
    }

    public void setUsuario_contrasena(String usuario_contrasena) {
        this.usuario_contrasena = usuario_contrasena;
    }

    public boolean getUsuario_estado() {
        return usuario_estado;
    }

    public void setUsuario_estado(boolean usuario_estado) {
        this.usuario_estado = usuario_estado;
    }

    
    
}
