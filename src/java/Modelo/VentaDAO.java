/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Llapapasca Montes
 */
public class VentaDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;

    public String idVentas() {
        String idventas = "";
        String sql = "select max(idVenta) from venta";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                idventas = rs.getString(1);
            }
        } catch (Exception e) {
        }
        return idventas;
    }

    public double montoTotalDelDia() {
        double montoTotal = 0.0;
        String sql = "SELECT SUM(monto_total) FROM venta WHERE fecha_de_emision = CURDATE()";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                montoTotal = rs.getDouble(1);
            }
        } catch (Exception e) {
        }
        return montoTotal;
    }

    public double montoTotalDelaSemana() {
        double montoTotal = 0.0;
        String sql = "SELECT SUM(monto_total)\n"
                + "FROM venta\n"
                + "WHERE YEARWEEK(fecha_de_emision, 1) = YEARWEEK(CURDATE(), 1)";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                montoTotal = rs.getDouble(1);
            }
        } catch (Exception e) {
        }
        return montoTotal;
    }

    public double montoTotalDelMes() {
        double montoTotal = 0.0;
        String sql = "SELECT SUM(monto_total)\n"
                + "FROM venta\n"
                + "WHERE YEAR(fecha_de_emision) = YEAR(CURDATE()) AND MONTH(fecha_de_emision) = MONTH(CURDATE())";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                montoTotal = rs.getDouble(1);
            }
        } catch (Exception e) {
        }
        return montoTotal;
    }

    public double montoTotalDelAño() {
        double montoTotal = 0.0;
        String sql = "SELECT SUM(monto_total)\n"
                + "FROM venta\n"
                + "WHERE YEAR(fecha_de_emision) = YEAR(CURDATE())";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                montoTotal = rs.getDouble(1);
            }
        } catch (Exception e) {
        }
        return montoTotal;
    }

    public int guardarVenta(Venta ve) {
        String sql = "insert into venta (idEmpleado, monto_total, fecha_de_emision) values (?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, ve.getIdEmpleado());
            ps.setDouble(2, ve.getTotal());
            ps.setString(3, ve.getFechaEmision());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }

    public int guardarDatalleVentas(Venta venta) {
        String sql = "insert into detalle_venta (idVenta, idAlmacen, cantidad) values (?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, venta.getIdVenta());
            ps.setInt(2, venta.getIdAlmacen());
            ps.setInt(3, venta.getCantidad());
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return r;
    }

    public static String obtenerFecha() {
        // Obtenemos la fecha actual
        Date fechaActual = new Date();
        // Formato fecha
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        return formato.format(fechaActual);
    }
}
