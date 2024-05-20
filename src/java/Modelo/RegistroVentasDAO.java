package Modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase proporciona métodos para acceder y manipular datos de ventas en la
 * base de datos.
 */
public class RegistroVentasDAO {

    private final Conexion cn;
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;

    /**
     * Constructor de la clase RegistroVentasDAO.
     */
    public RegistroVentasDAO() {
        // Inicializar la conexión
        this.cn = new Conexion();
    }

    /**
     * Método para obtener una lista de ventas.
     *
     * @return Una lista de objetos RegistroVentas.
     */
    public List<RegistroVentas> listarVentas() {
        String sql = "SELECT "
                + "    COALESCE(CONCAT('B', b.numero_de_serie), CONCAT('F', f.numero_de_serie), '-') AS Numero_Serie, "
                + "    COALESCE(b.nombre_completo, f.nombre_comercial, '-') AS Cliente_Nombre, "
                + "    COALESCE(b.dni, f.ruc, '-') AS Numero_Documento, "
                + "    COALESCE(b.direccion, f.direccion, '-') AS Direccion, "
                + "    COALESCE(f.igv, 0) AS igv, "
                + "    v.fecha_de_emision AS Fecha_Venta "
                + "FROM venta v "
                + "LEFT JOIN boleta b ON v.idVenta = b.idVenta "
                + "LEFT JOIN factura f ON v.idVenta = f.idVenta "
                + "LIMIT 0, 500;";

        List<RegistroVentas> lista = new ArrayList<>();
        try {
            con = cn.Conexion(); // Establecer conexión
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                RegistroVentas registroventas = new RegistroVentas();
                registroventas.setNumeroSerie(rs.getString("Numero_Serie"));
                registroventas.setClienteNombre(rs.getString("Cliente_Nombre"));
                registroventas.setNumeroDocumento(rs.getString("Numero_Documento"));
                registroventas.setClienteDireccion(rs.getString("Direccion"));
                registroventas.setIgv(rs.getDouble("igv"));
                registroventas.setFechaEmision(rs.getDate("Fecha_Venta"));

                lista.add(registroventas);
            }
        } catch (Exception e) {
            // Manejo de excepciones
            System.err.println("Error al listar ventas: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerrar recursos
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
            } catch (Exception ex) {
                System.err.println("Error al cerrar recursos: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return lista;
    }
}
