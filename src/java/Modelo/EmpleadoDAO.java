/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Llapapasca Montes
 */
public class EmpleadoDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public Empleado validar(String user, String pass){
        Empleado em = new Empleado();
        String sql = "select * from empleados where correo=? and contrasena=?";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);
            rs=ps.executeQuery();
            while (rs.next()) {
                em.setCorreo(rs.getString("correo"));
                em.setContrasena(rs.getString("contrasena"));
            }
        } catch (Exception e) {
        }
        return em;
    }
}
