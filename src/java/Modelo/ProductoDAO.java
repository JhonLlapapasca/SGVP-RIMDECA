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
public class ProductoDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    int r;
    int newProducto;
    int cantidad;

    // Operaciones
    public List Listar() {
        String sql = "SELECT p.idProducto, p.nombre, p.precio, a.stock, a.idAlmacen \n"
                + "FROM producto p\n"
                + "INNER JOIN almacen a ON p.idProducto = a.idProducto";
        List<Producto> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt(1));
                producto.setNombreproducto(rs.getString(2));
                producto.setPrecioproducto(rs.getDouble(3));
                producto.setStock(rs.getInt(4));
                producto.setIdAlmacen(rs.getInt(5));

                lista.add(producto);
            }
        } catch (Exception e) {
        }
        return lista;
    }

    //Lista de productos vendidos (Todos)
    public List ListarProductosVendidos() {
        String sql = "SELECT \n"
                + "    p.nombre AS producto,\n"
                + "    SUM(dv.cantidad) AS cantidad_vendida\n"
                + "FROM \n"
                + "    detalle_venta dv\n"
                + "JOIN \n"
                + "    almacen a ON dv.idAlmacen = a.idAlmacen\n"
                + "JOIN \n"
                + "    producto p ON a.idProducto = p.idProducto\n"
                + "GROUP BY \n"
                + "    p.idProducto\n"
                + "ORDER BY \n"
                + "    cantidad_vendida DESC";
        List<Producto> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setNombreproducto(rs.getString(1));
                producto.setCantidad(rs.getInt(2));

                lista.add(producto);
            }
        } catch (Exception e) {
        }
        return lista;
    }

    public List<ProductoVentas> obtenerProductosMasVendidos() {
        String sql = "SELECT \n"
                + "    p.nombre AS producto,\n"
                + "    SUM(dv.cantidad) AS cantidad_vendida\n"
                + "FROM \n"
                + "    detalle_venta dv\n"
                + "JOIN \n"
                + "    almacen a ON dv.idAlmacen = a.idAlmacen\n"
                + "JOIN \n"
                + "    producto p ON a.idProducto = p.idProducto\n"
                + "GROUP BY \n"
                + "    p.idProducto\n"
                + "ORDER BY \n"
                + "    cantidad_vendida DESC\n"
                + "LIMIT 3";
        List<ProductoVentas> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                ProductoVentas pv = new ProductoVentas();
                pv.setNombre(rs.getString("producto"));
                pv.setCantidadVendida(rs.getInt("cantidad_vendida"));
                lista.add(pv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
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
        return lista;
    }

    public List ListarProductos() {
        String sql = "SELECT p.idProducto, p.nombre \n"
                + "FROM producto p\n"
                + "LEFT JOIN almacen a ON p.idProducto = a.idProducto\n"
                + "WHERE a.stock IS NULL OR a.stock = 0;";
        List<Producto> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt(1));
                producto.setNombreproducto(rs.getString(2));
                lista.add(producto);
            }
        } catch (Exception e) {
        }
        return lista;
    }

    public Producto ListarId(int id) {
        Producto product = new Producto();
        String sql = "SELECT \n"
                + "    p.idProducto, \n"
                + "    p.nombre AS nombre_producto, \n"
                + "    p.precio, \n"
                + "    a.stock, \n"
                + "    a.idAlmacen, \n"
                + "    p.idProveedor, \n"
                + "    pr.nombre AS nombre_proveedor, \n"
                + "    p.idCategoria, \n"
                + "    c.categoria AS nombre_categoria\n"
                + "FROM \n"
                + "    producto p\n"
                + "INNER JOIN \n"
                + "    almacen a ON p.idProducto = a.idProducto\n"
                + "INNER JOIN \n"
                + "    proveedor pr ON p.idProveedor = pr.idProveedor\n"
                + "INNER JOIN \n"
                + "    categoria c ON p.idCategoria = c.idCategoria\n"
                + "WHERE \n"
                + "    p.idProducto =" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                product.setIdProducto(rs.getInt(1));
                product.setNombreproducto(rs.getString(2));
                product.setPrecioproducto(rs.getDouble(3));
                product.setStock(rs.getInt(4));
                product.setIdAlmacen(rs.getInt(5));
                product.setIdProveedor(rs.getInt(6));
                product.setNombreproveedor(rs.getString(7));
                product.setIdCategoria(rs.getInt(8));
                product.setNombrecategoria(rs.getString(9));
            }
        } catch (Exception e) {
        }
        return product;
    }

    //Agregar nuevo Producto
    public int agregar(Producto pr) {
        Conexion cn = new Conexion(); // Suponiendo que tengas una clase Conexion para establecer la conexión
        Connection con = null;
        PreparedStatement ps = null;
        int filasInsertadas = 0;

        String sql = "INSERT INTO producto (idCategoria, idProveedor, nombre, precio) VALUES (?, ?, ?, ?)";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, pr.getIdCategoria());
            ps.setInt(2, pr.getIdProveedor());
            ps.setString(3, pr.getNombreproducto());
            ps.setDouble(4, pr.getPrecioproducto());

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

    //Agregar Categoria
    public int agregarCategoria(Producto prCategoria) {
        Conexion cn = new Conexion(); // Suponiendo que tengas una clase Conexion para establecer la conexión
        Connection con = null;
        PreparedStatement ps = null;
        int filasInsertadas = 0;

        String sql = "INSERT INTO categoria (categoria) VALUES (?)";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, prCategoria.getNombrecategoria());

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

    // Agregar nuevo Producto
    public int agregarAlmacen(Producto pr) {
        Conexion cn = new Conexion();
        Connection con = null;
        PreparedStatement ps = null;
        int filasInsertadas = 0;

        try {
            con = cn.Conexion();
            // Verificar si el producto ya existe en el almacen
            String verificarExistencia = "SELECT COUNT(*) AS existencia FROM almacen WHERE idProducto = ?";
            PreparedStatement psVerificacion = con.prepareStatement(verificarExistencia);
            psVerificacion.setInt(1, pr.getIdProducto());
            ResultSet rs = psVerificacion.executeQuery();
            rs.next();
            int existencia = rs.getInt("existencia");
            rs.close();
            psVerificacion.close();

            // Si el producto ya existe, actualizar el stock
            if (existencia > 0) {
                String actualizarStock = "UPDATE almacen SET stock = stock + ? WHERE idProducto = ?";
                ps = con.prepareStatement(actualizarStock);
                ps.setInt(1, pr.getCantidad());
                ps.setInt(2, pr.getIdProducto());
            } else { // Si el producto no existe, insertarlo
                String insertarProducto = "INSERT INTO almacen (idProducto, stock) VALUES (?, ?)";
                ps = con.prepareStatement(insertarProducto);
                ps.setInt(1, pr.getIdProducto());
                ps.setInt(2, pr.getCantidad());
            }

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

    //Actualizar Producto
    public int actualizar(Producto pr) {
        Conexion cn = new Conexion(); // Suponiendo que tengas una clase Conexion para establecer la conexión
        Connection con = null;
        PreparedStatement ps = null;
        int filasActualizadas = 0;

        String sql = "UPDATE producto AS p\n"
                + "JOIN proveedor AS pr ON p.idProveedor = pr.idProveedor\n"
                + "JOIN categoria AS c ON p.idCategoria = c.idCategoria\n"
                + "JOIN almacen AS a ON p.idProducto = a.idProducto\n"
                + "SET p.idProveedor = ?,\n"
                + "    p.idCategoria = ?,\n"
                + "    p.nombre = ?,\n"
                + "    p.precio = ?,\n"
                + "    a.stock = ?\n"
                + "WHERE p.idProducto = ?";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, pr.getIdCategoria());
            ps.setInt(2, pr.getIdProveedor());
            ps.setString(3, pr.getNombreproducto());
            ps.setDouble(4, pr.getPrecioproducto());
            ps.setInt(5, pr.getStock());
            ps.setInt(6, pr.getIdProducto());

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
    public void eliminar(int idProducto) {
        String eliminarProducto = "DELETE FROM producto WHERE idProducto =" + idProducto;
        String eliminarAlmacen = "DELETE FROM almacen WHERE idProducto =" + idProducto;

        try {

            con = cn.Conexion();
            ps = con.prepareStatement(eliminarProducto);
            ps = con.prepareStatement(eliminarAlmacen);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    //Actualizar Stock
    public Producto buscar(int id) {
        Producto p = new Producto();
        String sql = "SELECT * from almacen WHERE idAlmacen = " + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                p.setIdProducto(rs.getInt(2));
                p.setStock(rs.getInt(3));
            }
        } catch (Exception e) {
        }
        return p;
    }

    public int actualizarStock(int stock, int id) {
        int r = 0; // Variable para almacenar el resultado de la actualización

        String sql = "UPDATE almacen SET stock=? WHERE idAlmacen=?";
        try {
            con = cn.Conexion(); // Obtener la conexión a la base de datos
            ps = con.prepareStatement(sql);
            ps.setInt(1, stock);
            ps.setInt(2, id);
            r = ps.executeUpdate(); // Ejecutar la consulta y obtener el número de filas afectadas
            con.close(); // Cerrar la conexión después de usarla
        } catch (Exception e) {
            System.out.println("Error al ejecutar la consulta SQL: " + e.getMessage());
            e.printStackTrace(); // Imprimir la traza completa del error en la consola
        }
        return r;
    }

    public static void main(String[] args) {
        // Crear un objeto Producto con los datos que quieres actualizar
        Producto producto = new Producto();
        producto.setIdProducto(21); // Supongamos que quieres actualizar el producto con id = 1
        producto.setIdProveedor(2); // Nuevo id de proveedor
        producto.setIdCategoria(1); // Nuevo id de categoría
        producto.setNombreproducto("Nuevo Nombre 2"); // Nuevo nombre del producto
        producto.setPrecioproducto(10.99); // Nuevo precio del producto
        producto.setStock(100); // Nuevo stock del producto

        // Crear una instancia de la clase que contiene el método actualizar
        // Aquí asumimos que la clase se llama ProductoDAO
        ProductoDAO productoDAO = new ProductoDAO();

        // Llamar al método actualizar y obtener el número de filas actualizadas
        int filasActualizadas = productoDAO.actualizar(producto);

        // Verificar si la actualización fue exitosa
        if (filasActualizadas > 0) {
            System.out.println("Producto actualizado exitosamente.");
        } else {
            System.out.println("No se pudo actualizar el producto.");
        }
    }
}
