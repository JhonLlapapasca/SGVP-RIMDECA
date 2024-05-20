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
public class FacturaDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;

    public int numeroSerie() {
        int numeroserie = 0;
        String sql = "select max(numero_de_serie) from factura";
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
    
     public int guardarCliente(Factura factura) {
        String sql = "insert into factura (idVenta, nombre_comercial, ruc, direccion, numero_de_serie, igv) values (?,?,?,?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, factura.getIdVenta());
            ps.setString(2, factura.getNombreComercial());
            ps.setString(3, factura.getRuc());
            ps.setString(4, factura.getDireccion());
            ps.setString(5, factura.getNumeroSerie());
            ps.setDouble(6, factura.getIgv());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }

}
