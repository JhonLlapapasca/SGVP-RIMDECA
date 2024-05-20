/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Llapapasca Montes
 */
public class ProveedorDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    //Operacciones
    public List Listar() {
        String sql = "select * from proveedor";
        List<Proveedor> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setIdproveedor(rs.getInt(1));
                proveedor.setNombre(rs.getString(2));
                proveedor.setRuc(rs.getString(3));
                proveedor.setDireccion(rs.getString(4));
                proveedor.setTelefono(rs.getString(5));

                lista.add(proveedor);

            }
        } catch (Exception e) {
        }
        return lista;
    }

    public Proveedor ListarId(int id) {
        Proveedor proveedor = new Proveedor();
        String sql = "SELECT * FROM proveedor WHERE idProveedor = " + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                proveedor.setIdproveedor(rs.getInt(1));
                proveedor.setNombre(rs.getString(2));
                proveedor.setRuc(rs.getString(3));
                proveedor.setDireccion(rs.getString(4));
                proveedor.setTelefono(rs.getString(5));
            }
        } catch (Exception e) {
        }
        return proveedor;
    }

    //Agregar nuevo Producto
    public int agregar(Proveedor pr) {
        Conexion cn = new Conexion(); // Suponiendo que tengas una clase Conexion para establecer la conexión
        Connection con = null;
        PreparedStatement ps = null;
        int filasInsertadas = 0;

        String sql = "INSERT INTO proveedor (nombre, ruc, direccion, telefono) VALUES (?, ?, ?, ?)";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, pr.getNombre());
            ps.setString(2, pr.getRuc());
            ps.setString(3, pr.getDireccion());
            ps.setString(4, pr.getTelefono());

            filasInsertadas = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return filasInsertadas;
    }

    //Actualizar Proveedor
    public int actualizar(Proveedor pr) {
        Conexion cn = new Conexion();
        Connection con = null;
        PreparedStatement ps = null;
        int filasActualizadas = 0;

        String sql = "UPDATE proveedor SET nombre = ?, ruc = ?, direccion = ?, telefono = ? WHERE idProveedor = ?";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, pr.getNombre());
            ps.setString(2, pr.getRuc());
            ps.setString(3, pr.getDireccion());
            ps.setString(4, pr.getTelefono());
            ps.setInt(5, pr.getIdproveedor());

            filasActualizadas = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return filasActualizadas;
    }
    
    //Eliminar
    public void eliminar(int id){
        String sql="Delete from proveedor where idProveedor="+id;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        // Crear una instancia de Proveedor
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre("Proveedor 4");
        proveedor.setRuc("123456789");
        proveedor.setDireccion("Calle Falsa 123");
        proveedor.setTelefono("987654321");
        proveedor.setIdproveedor(3);

        // Crear una instancia de la clase que contiene el método actualizar
        ProveedorDAO proveedorDAO = new ProveedorDAO();

        // Llamar al método actualizar
        int filasActualizadas = proveedorDAO.actualizar(proveedor);

        // Mostrar el resultado
        if (filasActualizadas > 0) {
            System.out.println("Proveedor actualizado correctamente.");
        } else {
            System.out.println("No se pudo actualizar el proveedor.");
        }
    }
}
