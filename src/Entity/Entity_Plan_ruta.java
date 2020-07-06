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
public class Entity_Plan_ruta {
    

    private String ruta_id;
    private String ruta_cod;
    private String ruta_placa;
    private String ruta_conductor;
    private String ruta_punto_partida;
    private String ruta_condicion;
    private String ruta_codigo;
    private String ruta_peso_total;
    private String ruta_dia;
    private String ruta_fecha_estiva;
    private String ruta_estiva_hora;
    private String ruta_observacion;
    private String ruta_contrato;
    private String ruta_entrega;

    public Entity_Plan_ruta(String ruta_id, String ruta_cod, String ruta_placa, String ruta_conductor, String ruta_punto_partida, String ruta_condicion, String ruta_codigo, String ruta_peso_total, String ruta_dia, String ruta_fecha_estiva, String ruta_estiva_hora, String ruta_observacion, String ruta_contrato, String ruta_entrega) {
        this.ruta_id = ruta_id;
        this.ruta_cod = ruta_cod;
        this.ruta_placa = ruta_placa;
        this.ruta_conductor = ruta_conductor;
        this.ruta_punto_partida = ruta_punto_partida;
        this.ruta_condicion = ruta_condicion;
        this.ruta_codigo = ruta_codigo;
        this.ruta_peso_total = ruta_peso_total;
        this.ruta_dia = ruta_dia;
        this.ruta_fecha_estiva = ruta_fecha_estiva;
        this.ruta_estiva_hora = ruta_estiva_hora;
        this.ruta_observacion = ruta_observacion;
        this.ruta_contrato = ruta_contrato;
        this.ruta_entrega = ruta_entrega;
    }

    public String getRuta_id() {
        return ruta_id;
    }

    public void setRuta_id(String ruta_id) {
        this.ruta_id = ruta_id;
    }

    public String getRuta_cod() {
        return ruta_cod;
    }

    public void setRuta_cod(String ruta_cod) {
        this.ruta_cod = ruta_cod;
    }

    public String getRuta_placa() {
        return ruta_placa;
    }

    public void setRuta_placa(String ruta_placa) {
        this.ruta_placa = ruta_placa;
    }

    public String getRuta_conductor() {
        return ruta_conductor;
    }

    public void setRuta_conductor(String ruta_conductor) {
        this.ruta_conductor = ruta_conductor;
    }

    public String getRuta_punto_partida() {
        return ruta_punto_partida;
    }

    public void setRuta_punto_partida(String ruta_punto_partida) {
        this.ruta_punto_partida = ruta_punto_partida;
    }

    public String getRuta_condicion() {
        return ruta_condicion;
    }

    public void setRuta_condicion(String ruta_condicion) {
        this.ruta_condicion = ruta_condicion;
    }

    public String getRuta_codigo() {
        return ruta_codigo;
    }

    public void setRuta_codigo(String ruta_codigo) {
        this.ruta_codigo = ruta_codigo;
    }

    public String getRuta_peso_total() {
        return ruta_peso_total;
    }

    public void setRuta_peso_total(String ruta_peso_total) {
        this.ruta_peso_total = ruta_peso_total;
    }

    public String getRuta_dia() {
        return ruta_dia;
    }

    public void setRuta_dia(String ruta_dia) {
        this.ruta_dia = ruta_dia;
    }

    public String getRuta_fecha_estiva() {
        return ruta_fecha_estiva;
    }

    public void setRuta_fecha_estiva(String ruta_fecha_estiva) {
        this.ruta_fecha_estiva = ruta_fecha_estiva;
    }

    public String getRuta_estiva_hora() {
        return ruta_estiva_hora;
    }

    public void setRuta_estiva_hora(String ruta_estiva_hora) {
        this.ruta_estiva_hora = ruta_estiva_hora;
    }

    public String getRuta_observacion() {
        return ruta_observacion;
    }

    public void setRuta_observacion(String ruta_observacion) {
        this.ruta_observacion = ruta_observacion;
    }

    public String getRuta_contrato() {
        return ruta_contrato;
    }

    public void setRuta_contrato(String ruta_contrato) {
        this.ruta_contrato = ruta_contrato;
    }

    public String getRuta_entrega() {
        return ruta_entrega;
    }

    public void setRuta_entrega(String ruta_entrega) {
        this.ruta_entrega = ruta_entrega;
    }

    @Override
    public String toString() {
        return "Entity_Plan_ruta{" + "ruta_id=" + ruta_id + ", ruta_cod=" + ruta_cod + ", ruta_placa=" + ruta_placa + ", ruta_conductor=" + ruta_conductor + ", ruta_punto_partida=" + ruta_punto_partida + ", ruta_condicion=" + ruta_condicion + ", ruta_codigo=" + ruta_codigo + ", ruta_peso_total=" + ruta_peso_total + ", ruta_dia=" + ruta_dia + ", ruta_fecha_estiva=" + ruta_fecha_estiva + ", ruta_estiva_hora=" + ruta_estiva_hora + ", ruta_observacion=" + ruta_observacion + ", ruta_contrato=" + ruta_contrato + ", ruta_entrega=" + ruta_entrega + '}';
    }
    
    

}
