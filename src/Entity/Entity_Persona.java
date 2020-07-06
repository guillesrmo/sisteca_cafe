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
public class Entity_Persona extends Entity_Documento {
    private String persona_id;
    private String persona_tipo_documento;
    private String persona_ruc,persona_dni;
    private String persona_razon_social;
    private String persona_razon_comercial;
    private String persona_direccion;
    private String persona_codigo_modular;

    public Entity_Persona() {
    }

    public Entity_Persona(String persona_id, String persona_tipo_documento, String persona_ruc, String persona_dni, String persona_razon_social, String persona_razon_comercial, String persona_direccion, String documento_id, String documento_cod, String documento_descrip, String documento_url) {
        super(documento_id, documento_cod, documento_descrip, documento_url);
        this.persona_id = persona_id;
        this.persona_tipo_documento = persona_tipo_documento;
        this.persona_ruc = persona_ruc;
        this.persona_dni = persona_dni;
        this.persona_razon_social = persona_razon_social;
        this.persona_razon_comercial = persona_razon_comercial;
        this.persona_direccion = persona_direccion;
    }
    

    public Entity_Persona(String persona_id, String persona_tipo_documento, String persona_ruc, String persona_dni, String persona_razon_social, String persona_razon_comercial, String persona_direccion) {
        this.persona_id = persona_id;
        this.persona_tipo_documento = persona_tipo_documento;
        this.persona_ruc = persona_ruc;
        this.persona_dni = persona_dni;
        this.persona_razon_social = persona_razon_social;
        this.persona_razon_comercial = persona_razon_comercial;
        this.persona_direccion = persona_direccion;
    }

    public Entity_Persona(String persona_id, String persona_tipo_documento, String persona_ruc, String persona_dni, String persona_razon_social, String persona_razon_comercial, String persona_direccion, String persona_codigo_modular) {
        this.persona_id = persona_id;
        this.persona_tipo_documento = persona_tipo_documento;
        this.persona_ruc = persona_ruc;
        this.persona_dni = persona_dni;
        this.persona_razon_social = persona_razon_social;
        this.persona_razon_comercial = persona_razon_comercial;
        this.persona_direccion = persona_direccion;
        this.persona_codigo_modular = persona_codigo_modular;
    }
    
    
    
    

    public String getPersona_id() {
        return persona_id;
    }

    public void setPersona_id(String persona_id) {
        this.persona_id = persona_id;
    }

    public String getPersona_tipo_documento() {
        return persona_tipo_documento;
    }

    public void setPersona_tipo_documento(String persona_tipo_documento) {
        this.persona_tipo_documento = persona_tipo_documento;
    }

    public String getPersona_ruc() {
        return persona_ruc;
    }

    public void setPersona_ruc(String persona_ruc) {
        this.persona_ruc = persona_ruc;
    }

    public String getPersona_dni() {
        return persona_dni;
    }

    public void setPersona_dni(String persona_dni) {
        this.persona_dni = persona_dni;
    }

    public String getPersona_razon_social() {
        return persona_razon_social;
    }

    public void setPersona_razon_social(String persona_razon_social) {
        this.persona_razon_social = persona_razon_social;
    }

    public String getPersona_razon_comercial() {
        return persona_razon_comercial;
    }

    public void setPersona_razon_comercial(String persona_razon_comercial) {
        this.persona_razon_comercial = persona_razon_comercial;
    }

    public String getPersona_direccion() {
        return persona_direccion;
    }

    public void setPersona_direccion(String persona_direccion) {
        this.persona_direccion = persona_direccion;
    }

    public String getPersona_codigo_modular() {
        return persona_codigo_modular;
    }

    public void setPersona_codigo_modular(String persona_codigo_modular) {
        this.persona_codigo_modular = persona_codigo_modular;
    }
    
    
    
    
    
}
