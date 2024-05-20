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
public class BoletaDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;

    public int numeroSerie() {
        int numeroserie = 0;
        String sql = "select max(numero_de_serie) from boleta";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String numeroSerieStr = rs.getString(1);
                if (numeroSerieStr != null) {
                    numeroserie = Integer.parseInt(numeroSerieStr);
                }
            }
        } catch (Exception e) {
        }
        return numeroserie;
    }

    public int guardarCliente(Boleta boleta) {
        String sql = "insert into boleta (idVenta, nombre_completo, dni, direccion, numero_de_serie) values (?,?,?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, boleta.getIdVenta());
            ps.setString(2, boleta.getNombreCompleto());
            ps.setString(3, boleta.getDni());
            ps.setString(4, boleta.getDireccion());
            ps.setString(5, boleta.getNumeroSerie());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }

}
