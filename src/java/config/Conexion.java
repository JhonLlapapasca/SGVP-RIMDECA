/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Llapapasca Montes
 */
public class Conexion {
     Connection con;
    String baseDeDatos = "sgvp-rimdeca";
    String url = "jdbc:mysql://localhost:3306/"; 
    String user = "root";
    String pass = "";

    public Connection Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url+baseDeDatos, user, pass);
            System.out.println("Conexión exitosa");
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el driver de MySQL");
        } catch (SQLException e) {
            System.out.println("Error de conexión: " + e.getMessage());
        }
        return con;
    }
    
    public static void main(String[] args) {
        Conexion con = new Conexion();
        con.Conexion();
    }
    
}
