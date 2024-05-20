/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Llapapasca Montes
 */
public class EmpleadoDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    public Empleado validar(String user, String pass, String rol) {
        Empleado em = new Empleado();
        String sql = "select * from empleado where rol=? and correo=? and contraseña=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, rol);
            ps.setString(2, user);
            ps.setString(3, pass);
            rs = ps.executeQuery();
            while (rs.next()) {
                em.setIdEmpleado(rs.getInt("idEmpleado"));
                em.setNombres(rs.getString("nombres"));
                em.setApellidos(rs.getString("apellidos"));
                em.setDni(rs.getString("dni"));
                em.setTelefono(rs.getString("telefono"));
                em.setFechaIngreso(rs.getString("fecha_de_ingreso"));
                em.setRol(rs.getString("rol"));
                em.setCorreo(rs.getString("correo"));
                em.setContrasena(rs.getString("contraseña"));

            }
        } catch (Exception e) {
        }
        return em;
    }

    //Operacciones
    public List Listar() {
        String sql = "select * from empleado";
        List<Empleado> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Empleado em = new Empleado();
                em.setIdEmpleado(rs.getInt(1));
                em.setNombres(rs.getString(2));
                em.setApellidos(rs.getString(3));
                em.setDni(rs.getString(4));
                em.setTelefono(rs.getString(5));
                em.setFechaIngreso(rs.getString(6));
                em.setRol(rs.getString(7));
                em.setCorreo(rs.getString(8));
                em.setContrasena(rs.getString(9));

                lista.add(em);

            }
        } catch (Exception e) {
        }
        return lista;
    }

    //Agregar nuevo Empleado
    public int agregar(Empleado em) {
        Conexion cn = new Conexion(); // Suponiendo que tengas una clase Conexion para establecer la conexión
        Connection con = null;
        PreparedStatement ps = null;
        int filasInsertadas = 0;

        String sql = "INSERT INTO empleado (nombres, apellidos, dni, telefono, fecha_de_ingreso, rol, correo, contraseña) VALUES (?,?,?,?,?,?,?,?)";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, em.getNombres());
            ps.setString(2, em.getApellidos());
            ps.setString(3, em.getDni());
            ps.setString(4, em.getTelefono());
            ps.setString(5, em.getFechaIngreso());
            ps.setString(6, em.getRol());
            ps.setString(7, em.getCorreo());
            ps.setString(8, em.getContrasena());

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

    public Empleado ListarId(int id) {
        Empleado empleado = new Empleado();
        String sql = "SELECT * FROM empleado WHERE idEmpleado =" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                empleado.setIdEmpleado(rs.getInt(1));
                empleado.setNombres(rs.getString(2));
                empleado.setApellidos(rs.getString(3));
                empleado.setDni(rs.getString(4));
                empleado.setTelefono(rs.getString(5));
                empleado.setFechaIngreso(rs.getString(6));
                empleado.setRol(rs.getString(7));
                empleado.setCorreo(rs.getString(8));
                empleado.setContrasena(rs.getString(9));
            }
        } catch (Exception e) {
        }
        return empleado;
    }

    //Actualizar Empleado
    public int actualizar(Empleado em) {
        Conexion cn = new Conexion(); // Suponiendo que tengas una clase Conexion para establecer la conexión
        Connection con = null;
        PreparedStatement ps = null;
        int filasActualizadas = 0;

        String sql = "UPDATE empleado\n"
                + "SET nombres = ?,\n"
                + "    apellidos = ?,\n"
                + "    dni = ?,\n"
                + "    telefono = ?,\n"
                + "    rol = ?,\n"
                + "    correo = ?\n"
                + "WHERE idEmpleado = ?";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, em.getNombres());
            ps.setString(2, em.getApellidos());
            ps.setString(3, em.getDni());
            ps.setString(4, em.getTelefono());
            ps.setString(5, em.getRol());
            ps.setString(6, em.getCorreo());
            ps.setInt(7, em.getIdEmpleado());

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
    
    //Actualizar Empleado
    public int actualizarEmpleado(Empleado em) {
        int filasActualizadas = 0;
        String sql = "UPDATE empleado\n"
                + "SET nombres = ?,\n"
                + "    apellidos = ?,\n"
                + "    dni = ?,\n"
                + "    telefono = ?,\n"
                + "    rol = ?,\n"
                + "    correo = ?,\n"
                + "    contraseña = ?\n"
                + "WHERE idEmpleado = ?";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, em.getNombres());
            ps.setString(2, em.getApellidos());
            ps.setString(3, em.getDni());
            ps.setString(4, em.getTelefono());
            ps.setString(5, em.getRol());
            ps.setString(6, em.getCorreo());
            ps.setString(7, em.getContrasena());
            ps.setInt(8, em.getIdEmpleado());

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
    

    public static void main(String[] args) {
        Empleado empleadoActualizado = new Empleado();
        empleadoActualizado.setIdEmpleado(6); // ID del empleado que se desea actualizar
        empleadoActualizado.setNombres("Nuevo Nombre");
        empleadoActualizado.setApellidos("Nuevo Apellido");
        empleadoActualizado.setDni("12345678");
        empleadoActualizado.setTelefono("987654321");
        empleadoActualizado.setRol("Nuevo Rol");
        empleadoActualizado.setCorreo("nuevo@correo.com");
        empleadoActualizado.setContrasena("nueva");

        // Crear una instancia de EmpleadoDAO
        EmpleadoDAO empleadoDAO = new EmpleadoDAO();

        // Llamar al método actualizar para actualizar el empleado en la base de datos
        int filasActualizadas = empleadoDAO.actualizar(empleadoActualizado);

        // Verificar si la actualización fue exitosa
        if (filasActualizadas > 0) {
            System.out.println("Empleado actualizado exitosamente.");
        } else {
            System.out.println("No se pudo actualizar el empleado.");
        }
    }

}
