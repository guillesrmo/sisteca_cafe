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
public class Entity_Sucursal {
        private String sucursal_id; 
	private String sucursal_empresa;
	private String sucursal_cod ;
	private String sucursal_direccion;
	private String sucursal_descripcion ;
	private String sucursal_url ;
	private String sucursal_token ;
	private String sucursal_estado ;

    public Entity_Sucursal() {
    }

    public Entity_Sucursal(String sucursal_id, String sucursal_empresa, String sucursal_cod, String sucursal_direccion, String sucursal_descripcion, String sucursal_url, String sucursal_token, String sucursal_estado) {
        this.sucursal_id = sucursal_id;
        this.sucursal_empresa = sucursal_empresa;
        this.sucursal_cod = sucursal_cod;
        this.sucursal_direccion = sucursal_direccion;
        this.sucursal_descripcion = sucursal_descripcion;
        this.sucursal_url = sucursal_url;
        this.sucursal_token = sucursal_token;
        this.sucursal_estado = sucursal_estado;
    }

    public String getSucursal_id() {
        return sucursal_id;
    }

    public void setSucursal_id(String sucursal_id) {
        this.sucursal_id = sucursal_id;
    }

    public String getSucursal_empresa() {
        return sucursal_empresa;
    }

    public void setSucursal_empresa(String sucursal_empresa) {
        this.sucursal_empresa = sucursal_empresa;
    }

    public String getSucursal_cod() {
        return sucursal_cod;
    }

    public void setSucursal_cod(String sucursal_cod) {
        this.sucursal_cod = sucursal_cod;
    }

    public String getSucursal_direccion() {
        return sucursal_direccion;
    }

    public void setSucursal_direccion(String sucursal_direccion) {
        this.sucursal_direccion = sucursal_direccion;
    }

    public String getSucursal_descripcion() {
        return sucursal_descripcion;
    }

    public void setSucursal_descripcion(String sucursal_descripcion) {
        this.sucursal_descripcion = sucursal_descripcion;
    }

    public String getSucursal_url() {
        return sucursal_url;
    }

    public void setSucursal_url(String sucursal_url) {
        this.sucursal_url = sucursal_url;
    }

    public String getSucursal_token() {
        return sucursal_token;
    }

    public void setSucursal_token(String sucursal_token) {
        this.sucursal_token = sucursal_token;
    }

    public String getSucursal_estado() {
        return sucursal_estado;
    }

    public void setSucursal_estado(String sucursal_estado) {
        this.sucursal_estado = sucursal_estado;
    }

    @Override
    public String toString() {
        return  sucursal_cod ;
    }
    
        
    
}
