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
public class Entity_Equipo_colegio {

    private String equi_col_id;
    private String equi_col_colegio;
    private String equi_col_entrega;
    private String equi_col_equipo;
    private String equi_col_cantida;

    public Entity_Equipo_colegio(String equi_col_id, String equi_col_colegio, String equi_col_entrega, String equi_col_equipo, String equi_col_cantida) {
        this.equi_col_id = equi_col_id;
        this.equi_col_colegio = equi_col_colegio;
        this.equi_col_entrega = equi_col_entrega;
        this.equi_col_equipo = equi_col_equipo;
        this.equi_col_cantida = equi_col_cantida;
    }

    public Entity_Equipo_colegio() {
    }

    public String getEqui_col_id() {
        return equi_col_id;
    }

    public void setEqui_col_id(String equi_col_id) {
        this.equi_col_id = equi_col_id;
    }

    public String getEqui_col_colegio() {
        return equi_col_colegio;
    }

    public void setEqui_col_colegio(String equi_col_colegio) {
        this.equi_col_colegio = equi_col_colegio;
    }

    public String getEqui_col_entrega() {
        return equi_col_entrega;
    }

    public void setEqui_col_entrega(String equi_col_entrega) {
        this.equi_col_entrega = equi_col_entrega;
    }

    public String getEqui_col_equipo() {
        return equi_col_equipo;
    }

    public void setEqui_col_equipo(String equi_col_equipo) {
        this.equi_col_equipo = equi_col_equipo;
    }

    public String getEqui_col_cantida() {
        return equi_col_cantida;
    }

    public void setEqui_col_cantida(String equi_col_cantida) {
        this.equi_col_cantida = equi_col_cantida;
    }

    @Override
    public String toString() {
        return "" + equi_col_id + ", " + equi_col_colegio + ", " + equi_col_entrega + ", " + equi_col_equipo + "," + equi_col_cantida ;
    }
    
    

}
