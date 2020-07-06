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
public class Entity_Punto_operacion {

    private String punto_op_id;
    private String punto_op_cod;
    private String punto_op_sucursal;
    private Boolean punto_op_estado;
    private String empresa_ruc;
    private String empresa_razon_social;
    private String sucursal_cod;
    
    
    private String det_punto_op_id;
    private String det_punto_op_punto_operacion;
    private String det_punto_op_tipo_documento;
    private String det_punto_op_serie;
    private String det_punto_op_numeracion;
    private String det_punto_op_formato;
    private String det_punto_op_impresora;
    private String det_punto_op_control_stock;
    private String det_punto_op_control_estado;
    
    
    private String det_punto_op_user_id;
    private String det_punto_op_user_punto_op;
    private String det_punto_op_user_usuario;
    private String det_punto_op_user_estado;
    

    public Entity_Punto_operacion() {
    }

    
    public Entity_Punto_operacion(String punto_op_id, String punto_op_cod, String punto_op_sucursal, Boolean punto_op_estado) {
        this.punto_op_id = punto_op_id;
        this.punto_op_cod = punto_op_cod;
        this.punto_op_sucursal = punto_op_sucursal;
        this.punto_op_estado = punto_op_estado;
    }

    public Entity_Punto_operacion(String punto_op_id, String punto_op_cod, String punto_op_sucursal, Boolean punto_op_estado, String empresa_ruc, String empresa_razon_social, String sucursal_cod) {
        this.punto_op_id = punto_op_id;
        this.punto_op_cod = punto_op_cod;
        this.punto_op_sucursal = punto_op_sucursal;
        this.punto_op_estado = punto_op_estado;
        this.empresa_ruc = empresa_ruc;
        this.empresa_razon_social = empresa_razon_social;
        this.sucursal_cod = sucursal_cod;
    }

    public Entity_Punto_operacion(String det_punto_op_id, String det_punto_op_punto_operacion, String det_punto_op_tipo_documento, String det_punto_op_serie, String det_punto_op_numeracion, String det_punto_op_formato, String det_punto_op_impresora, String det_punto_op_control_stock, String det_punto_op_control_estado) {
        this.det_punto_op_id = det_punto_op_id;
        this.det_punto_op_punto_operacion = det_punto_op_punto_operacion;
        this.det_punto_op_tipo_documento = det_punto_op_tipo_documento;
        this.det_punto_op_serie = det_punto_op_serie;
        this.det_punto_op_numeracion = det_punto_op_numeracion;
        this.det_punto_op_formato = det_punto_op_formato;
        this.det_punto_op_impresora = det_punto_op_impresora;
        this.det_punto_op_control_stock = det_punto_op_control_stock;
        this.det_punto_op_control_estado = det_punto_op_control_estado;
    }

    public Entity_Punto_operacion(String det_punto_op_user_id, String det_punto_op_user_punto_op, String det_punto_op_user_usuario, String det_punto_op_user_estado) {
        this.det_punto_op_user_id = det_punto_op_user_id;
        this.det_punto_op_user_punto_op = det_punto_op_user_punto_op;
        this.det_punto_op_user_usuario = det_punto_op_user_usuario;
        this.det_punto_op_user_estado = det_punto_op_user_estado;
    }
    
    
    
    

    public String getPunto_op_id() {
        return punto_op_id;
    }

    public void setPunto_op_id(String punto_op_id) {
        this.punto_op_id = punto_op_id;
    }

    public String getPunto_op_cod() {
        return punto_op_cod;
    }

    public void setPunto_op_cod(String punto_op_cod) {
        this.punto_op_cod = punto_op_cod;
    }

    public String getPunto_op_sucursal() {
        return punto_op_sucursal;
    }

    public void setPunto_op_sucursal(String punto_op_sucursal) {
        this.punto_op_sucursal = punto_op_sucursal;
    }

    public Boolean getPunto_op_estado() {
        return punto_op_estado;
    }

    public void setPunto_op_estado(Boolean punto_op_estado) {
        this.punto_op_estado = punto_op_estado;
    }

    public String getEmpresa_ruc() {
        return empresa_ruc;
    }

    public void setEmpresa_ruc(String empresa_ruc) {
        this.empresa_ruc = empresa_ruc;
    }

    public String getEmpresa_razon_social() {
        return empresa_razon_social;
    }

    public void setEmpresa_razon_social(String empresa_razon_social) {
        this.empresa_razon_social = empresa_razon_social;
    }

    public String getSucursal_cod() {
        return sucursal_cod;
    }

    public void setSucursal_cod(String sucursal_cod) {
        this.sucursal_cod = sucursal_cod;
    }

    public String getDet_punto_op_id() {
        return det_punto_op_id;
    }

    public void setDet_punto_op_id(String det_punto_op_id) {
        this.det_punto_op_id = det_punto_op_id;
    }

    public String getDet_punto_op_punto_operacion() {
        return det_punto_op_punto_operacion;
    }

    public void setDet_punto_op_punto_operacion(String det_punto_op_punto_operacion) {
        this.det_punto_op_punto_operacion = det_punto_op_punto_operacion;
    }

    public String getDet_punto_op_tipo_documento() {
        return det_punto_op_tipo_documento;
    }

    public void setDet_punto_op_tipo_documento(String det_punto_op_tipo_documento) {
        this.det_punto_op_tipo_documento = det_punto_op_tipo_documento;
    }

    public String getDet_punto_op_serie() {
        return det_punto_op_serie;
    }

    public void setDet_punto_op_serie(String det_punto_op_serie) {
        this.det_punto_op_serie = det_punto_op_serie;
    }

    public String getDet_punto_op_numeracion() {
        return det_punto_op_numeracion;
    }

    public void setDet_punto_op_numeracion(String det_punto_op_numeracion) {
        this.det_punto_op_numeracion = det_punto_op_numeracion;
    }

    public String getDet_punto_op_formato() {
        return det_punto_op_formato;
    }

    public void setDet_punto_op_formato(String det_punto_op_formato) {
        this.det_punto_op_formato = det_punto_op_formato;
    }

    public String getDet_punto_op_impresora() {
        return det_punto_op_impresora;
    }

    public void setDet_punto_op_impresora(String det_punto_op_impresora) {
        this.det_punto_op_impresora = det_punto_op_impresora;
    }

    public String getDet_punto_op_control_stock() {
        return det_punto_op_control_stock;
    }

    public void setDet_punto_op_control_stock(String det_punto_op_control_stock) {
        this.det_punto_op_control_stock = det_punto_op_control_stock;
    }

    public String getDet_punto_op_control_estado() {
        return det_punto_op_control_estado;
    }

    public void setDet_punto_op_control_estado(String det_punto_op_control_estado) {
        this.det_punto_op_control_estado = det_punto_op_control_estado;
    }

    public String getDet_punto_op_user_id() {
        return det_punto_op_user_id;
    }

    public void setDet_punto_op_user_id(String det_punto_op_user_id) {
        this.det_punto_op_user_id = det_punto_op_user_id;
    }

    public String getDet_punto_op_user_punto_op() {
        return det_punto_op_user_punto_op;
    }

    public void setDet_punto_op_user_punto_op(String det_punto_op_user_punto_op) {
        this.det_punto_op_user_punto_op = det_punto_op_user_punto_op;
    }

    public String getDet_punto_op_user_usuario() {
        return det_punto_op_user_usuario;
    }

    public void setDet_punto_op_user_usuario(String det_punto_op_user_usuario) {
        this.det_punto_op_user_usuario = det_punto_op_user_usuario;
    }

    public String getDet_punto_op_user_estado() {
        return det_punto_op_user_estado;
    }

    public void setDet_punto_op_user_estado(String det_punto_op_user_estado) {
        this.det_punto_op_user_estado = det_punto_op_user_estado;
    }
    
    
    
    
    

}
