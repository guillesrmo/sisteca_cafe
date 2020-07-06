/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author MEMO
 */
public class Conexion {

   public static Connection nn;
    private final String user = "sa";//
    private final String password = "root";
   // private final String url = "jdbc:sqlserver://DESKTOP-PJ60HNJ\\MSSQLSERVER:1433;databaseName=sistema_cafe";
    private final String url = "jdbc:sqlserver://192.168.0.20:1433;databaseName=sistema_cafe";
    public static final String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public Connection conectardb() {
        try {
            Class.forName(driver);
            nn = DriverManager.getConnection(url, user, password);
            nn.createStatement();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Driver no encontrado");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Fallo al recibir base de datos");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No hay resultado");
        } finally {
            return nn;
        }
    }

    public void desconectar() {
        try {
            nn.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
}
