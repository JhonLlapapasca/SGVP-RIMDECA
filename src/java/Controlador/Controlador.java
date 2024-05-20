/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Boleta;
import Modelo.BoletaDAO;
import Modelo.Categoria;
import Modelo.CategoriaDAO;
import Modelo.ConvertidorNumerico;
import Modelo.ConsultaCliente;
import static Modelo.ConsultaCliente.consultarClienteDni;
import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import Modelo.Factura;
import Modelo.FacturaDAO;
import Modelo.Producto;
import Modelo.ProductoDAO;
import Modelo.ProductoVentas;
import Modelo.Proveedor;
import Modelo.ProveedorDAO;
import Modelo.RegistroVentas;
import Modelo.RegistroVentasDAO;
import Modelo.Venta;
import Modelo.VentaDAO;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Llapapasca Montes
 */
public class Controlador extends HttpServlet {

    Empleado em = new Empleado();
    EmpleadoDAO edao = new EmpleadoDAO();

    Producto producto = new Producto();
    ProductoDAO productoDAO = new ProductoDAO();

    Venta venta = new Venta();
    VentaDAO ventadao = new VentaDAO();

    Boleta boleta = new Boleta();
    BoletaDAO boletaDAO = new BoletaDAO();

    Factura factura = new Factura();
    FacturaDAO facturaDAO = new FacturaDAO();

    Categoria categoria = new Categoria();
    CategoriaDAO categoriaDAO = new CategoriaDAO();

    Proveedor proveedor = new Proveedor();
    ProveedorDAO proveedorDAO = new ProveedorDAO();

    RegistroVentas registroventas = new RegistroVentas();
    RegistroVentasDAO registroventasDAO = new RegistroVentasDAO();

    List<Venta> lista = new ArrayList<>();
    int cantidad;
    String descripcion;
    Double precioUnitario;
    Double precioImporte;
    Double subTotal;
    Double igv;
    Double total;

    String resultado;
    String dni;
    String nombreCliente;
    String dniCliente;

    String ruc;
    String nombreClienteruc;
    String ruccliente;
    String direccionClienteruc;

    String direccion;

    int nSerie;
    String nSerieBoleta;
    String nSerieFactura;
    String nSeriecadena;
    int id;

    List listacategoria;
    List listaproveedor;
    List listaProductosVendidos;

    ConvertidorNumerico lector = new ConvertidorNumerico();
    ConsultaCliente consultar = new ConsultaCliente();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");

        HttpSession session = request.getSession();
        Empleado empleadosesion = (Empleado) session.getAttribute("usuarioLogeado");

        if (menu.equals("Principal")) {

            if (empleadosesion != null) {
                request.setAttribute("empleadoSesion", empleadosesion);
                request.getRequestDispatcher("Principal.jsp").forward(request, response);
            }
        }
        if (menu.equals("Configuracion")) {

            if (empleadosesion != null) {
                request.setAttribute("empleado", empleadosesion);

                if ("Actualizar".equals(accion)) {
                    String nombres = request.getParameter("nombres");
                    String apellidos = request.getParameter("apellidos");
                    String correo = request.getParameter("correo");
                    String contrasena = request.getParameter("contrasena");
                    String dni = request.getParameter("dni");
                    String telefono = request.getParameter("telefono");

                    empleadosesion.setNombres(nombres);
                    empleadosesion.setApellidos(apellidos);
                    empleadosesion.setCorreo(correo);
                    empleadosesion.setContrasena(contrasena);
                    empleadosesion.setDni(dni);
                    empleadosesion.setTelefono(telefono);

                    EmpleadoDAO edao = new EmpleadoDAO();
                    edao.actualizarEmpleado(empleadosesion);

                    // Actualizar la información en la sesión
                    session.setAttribute("usuarioLogeado", empleadosesion);

                    request.setAttribute("mensaje", "Datos actualizados correctamente.");
                }

                request.getRequestDispatcher("Configuracion.jsp").forward(request, response);
            } else {
                response.sendRedirect("index.jsp");
            }

        }
        if (menu.equals("Reporte")) {
            try {
                if ("Listar".equals(accion)) {
                    listaProductosVendidos = productoDAO.ListarProductosVendidos();
                    request.setAttribute("productos", listaProductosVendidos);
                }

                String tipoGrafico = request.getParameter("tipoGrafico");
                ProductoDAO productoDAO = new ProductoDAO();
                List<ProductoVentas> productosMasVendidos = productoDAO.obtenerProductosMasVendidos();

                JFreeChart chart = null;

                if ("torta".equalsIgnoreCase(tipoGrafico)) {
                    DefaultPieDataset dataset = new DefaultPieDataset();
                    for (ProductoVentas pv : productosMasVendidos) {
                        dataset.setValue(pv.getNombre(), pv.getCantidadVendida());
                    }
                    chart = ChartFactory.createPieChart(
                            "Productos Más Vendidos",
                            dataset,
                            true, true, false);
                } else {
                    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
                    for (ProductoVentas pv : productosMasVendidos) {
                        dataset.addValue(pv.getCantidadVendida(), "Productos", pv.getNombre());
                    }
                    chart = ChartFactory.createBarChart(
                            "Productos Más Vendidos",
                            "Producto",
                            "Cantidad Vendida",
                            dataset,
                            PlotOrientation.VERTICAL,
                            true,
                            true,
                            false);
                }

                String filename = "productos_mas_vendidos.png";
                String filePath = getServletContext().getRealPath("/") + "temp/" + filename;
                File file = new File(filePath);
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                }
                ChartUtilities.saveChartAsPNG(file, chart, 800, 600);

                Double ventadia = ventadao.montoTotalDelDia();
                Double ventasemanal = ventadao.montoTotalDelaSemana();
                Double ventames = ventadao.montoTotalDelMes();
                Double ventaaño = ventadao.montoTotalDelAño();

                request.setAttribute("ventadia", ventadia);
                request.setAttribute("ventasemanal", ventasemanal);
                request.setAttribute("ventames", ventames);
                request.setAttribute("ventaaño", ventaaño);
                request.setAttribute("chartFilename", filename);
                request.setAttribute("productos", listaProductosVendidos);
                request.setAttribute("tipoGrafico", tipoGrafico);
                request.getRequestDispatcher("Reporte.jsp").forward(request, response);

            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "Ocurrió un error al generar el reporte: " + e.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
        if (menu.equals("Empleado")) {
            request.setAttribute("empleadoSesion", empleadosesion);

            switch (accion) {
                case "Listar":
                    List lista = edao.Listar();
                    request.setAttribute("empleados", lista);
                    break;
                case "Aceptar":
                    String nombres = request.getParameter("txtnombres");
                    String apellidos = request.getParameter("txtapellidos");

                    String rol = request.getParameter("txtrol");
                    String correo = request.getParameter("txtcorreo");
                    //String contrasena = request.getParameter("txtcontrasena");

                    String fecha = VentaDAO.obtenerFecha();

                    em.setNombres(nombres);
                    em.setApellidos(apellidos);

                    // Recuperar los valores del DNI
                    StringBuilder dniBuilder = new StringBuilder();
                    for (int i = 0; i < 8; i++) {
                        String fieldName = "txtdni" + i;
                        String fieldValue = request.getParameter(fieldName);
                        dniBuilder.append(fieldValue);
                    }
                    em.setDni(dniBuilder.toString());

                    // Recuperar los valores del teléfono
                    StringBuilder telefonoBuilder = new StringBuilder();
                    for (int i = 0; i < 9; i++) {
                        String fieldName = "txttelefono" + i;
                        String fieldValue = request.getParameter(fieldName);
                        telefonoBuilder.append(fieldValue);
                    }
                    em.setTelefono(telefonoBuilder.toString());

                    em.setFechaIngreso(fecha);
                    em.setRol(rol);
                    em.setCorreo(correo);
                    em.setContrasena("RIMDECA");

                    edao.agregar(em);
                    request.setAttribute("mensaje", "Empleado agregado con éxito");

                    break;
                case "Editar":
                    id = Integer.parseInt(request.getParameter("id"));
                    Empleado empleado = edao.ListarId(id);
                    request.setAttribute("empleado", empleado);
                    break;
                case "Actualizar":
                    String nombresActualizar = request.getParameter("txtnombresAC");
                    String apellidosActualizar = request.getParameter("txtapellidosAC");
                    String rolActualizar = request.getParameter("txtrolAC");
                    String correoActualizar = request.getParameter("txtcorreoAC");
                    String dniActualizar = request.getParameter("txtdniAC");
                    String telefonoActualizar = request.getParameter("txttelefonoAC");

                    em.setNombres(nombresActualizar);
                    em.setApellidos(apellidosActualizar);
                    em.setDni(dniActualizar);
                    em.setTelefono(telefonoActualizar);
                    em.setRol(rolActualizar);
                    em.setCorreo(correoActualizar);
                    em.setIdEmpleado(id);

                    edao.actualizar(em);
                    request.setAttribute("mensaje", "Empleado actualizado con éxito");
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Empleados.jsp").forward(request, response);
        }
        if (menu.equals("Producto")) {
            request.setAttribute("empleadoSesion", empleadosesion);
            switch (accion) {
                case "Descargar":
                    response.setContentType("application/pdf");
                    response.setHeader("Content-Disposition", "attachment; filename=Productos-Rimdeca.pdf");
                    try (OutputStream out = response.getOutputStream()) {
                        Document documento = new Document();
                        PdfWriter.getInstance(documento, out);
                        documento.open();

                        // Imagen pequeña en la parte superior izquierda
                        try {
                            Image imagen = Image.getInstance(request.getServletContext().getRealPath("/") + "img/logo.png");
                            imagen.scaleToFit(100, 100); // Ajustar tamaño
                            documento.add(imagen);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        // Título del documento
                        Paragraph par1 = new Paragraph();
                        Font fonttitulo = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLUE);
                        par1.add(new Phrase("Lista de Productos - RIMDECA E.I.R.L", fonttitulo));
                        par1.setAlignment(Element.ALIGN_CENTER);
                        par1.add(Chunk.NEWLINE);
                        par1.add(Chunk.NEWLINE);
                        documento.add(par1);

                        // Descripción
                        Paragraph par2 = new Paragraph();
                        Font fontdescripcion = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
                        par2.add(new Phrase("Productos disponibles.", fontdescripcion));
                        par2.setAlignment(Element.ALIGN_JUSTIFIED);
                        par2.add(Chunk.NEWLINE);
                        par2.add(Chunk.NEWLINE);
                        documento.add(par2);

                        // Tabla de productos
                        PdfPTable table = new PdfPTable(4); // 4 columnas: ID, Nombre, Precio, Stock
                        table.setWidthPercentage(100);
                        table.setSpacingBefore(10f);
                        table.setSpacingAfter(10f);

                        // Encabezados de la tabla
                        Font fontHeader = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);
                        PdfPCell cell;

                        cell = new PdfPCell(new Phrase("ID", fontHeader));
                        cell.setBackgroundColor(BaseColor.BLUE);
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase("Nombre", fontHeader));
                        cell.setBackgroundColor(BaseColor.BLUE);
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase("Precio (S/.)", fontHeader));
                        cell.setBackgroundColor(BaseColor.BLUE);
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase("Stock", fontHeader));
                        cell.setBackgroundColor(BaseColor.BLUE);
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);

                        // Filas de la tabla
                        Font fontCell = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
                        List<Producto> productos = productoDAO.Listar();
                        for (Producto producto : productos) {
                            table.addCell(new PdfPCell(new Phrase(String.valueOf(producto.getIdProducto()), fontCell)));
                            table.addCell(new PdfPCell(new Phrase(producto.getNombreproducto(), fontCell)));
                            table.addCell(new PdfPCell(new Phrase(String.valueOf(producto.getPrecioproducto()), fontCell)));
                            table.addCell(new PdfPCell(new Phrase(String.valueOf(producto.getStock()), fontCell)));
                        }

                        documento.add(table);

                        documento.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case "Listar":
                    List lista = productoDAO.Listar();
                    List lista2 = productoDAO.ListarProductos();
                    listacategoria = categoriaDAO.Listar();
                    listaproveedor = proveedorDAO.Listar();
                    request.setAttribute("productos", lista);
                    request.setAttribute("categorias", listacategoria);
                    request.setAttribute("proveedores", listaproveedor);
                    request.setAttribute("listaproductos", lista2);

                    break;
                case "Agregar_Categoria":
                    String nombrecategoria = request.getParameter("txtcategoria");
                    producto.setNombrecategoria(nombrecategoria);
                    productoDAO.agregarCategoria(producto);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Aceptar":

                    String nombre = request.getParameter("txtnombre");
                    String precioString = request.getParameter("txtprecio");
                    String categoria = request.getParameter("txtcategoria");
                    String proveedor = request.getParameter("txtproveedor");

                    double precio = Double.parseDouble(precioString);
                    int categoria2 = Integer.parseInt(categoria);
                    int proveedor2 = Integer.parseInt(proveedor);

                    producto.setNombreproducto(nombre);
                    producto.setPrecioproducto(precio);
                    producto.setIdCategoria(categoria2);
                    producto.setIdProveedor(proveedor2);
                    productoDAO.agregar(producto);

                    request.setAttribute("mensaje", "Producto agregado con éxito");

                    break;
                case "Agregar":
                    String idproducto = request.getParameter("txtidproducto");
                    String cantidad = request.getParameter("txtcantidad");
                    int cantidad2 = Integer.parseInt(cantidad);
                    int idproducto2 = Integer.parseInt(idproducto);

                    producto.setIdProducto(idproducto2);
                    producto.setCantidad(cantidad2);

                    productoDAO.agregarAlmacen(producto);

                    request.setAttribute("mensaje", "Cantidad agregado con éxito");
                    break;
                case "Editar":
                    id = Integer.parseInt(request.getParameter("id"));
                    Producto pr = productoDAO.ListarId(id);
                    request.setAttribute("pr", pr);
                    request.setAttribute("categorias", listacategoria);
                    request.setAttribute("proveedores", listaproveedor);
                    //request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":

                    String nombreProducto = request.getParameter("nombreProducto");
                    String precioString2 = request.getParameter("precioProducto");
                    int stockProducto = Integer.parseInt(request.getParameter("stockProducto"));
                    int proveedorProducto = Integer.parseInt(request.getParameter("proveedorProducto"));
                    int categoriaProducto = Integer.parseInt(request.getParameter("categoriaProducto"));

                    double precio2 = Double.parseDouble(precioString2);

                    producto.setNombreproducto(nombreProducto);
                    producto.setPrecioproducto(precio2);
                    producto.setStock(stockProducto);
                    producto.setIdProveedor(proveedorProducto);
                    producto.setIdCategoria(categoriaProducto);
                    producto.setIdProducto(id);

                    productoDAO.actualizar(producto);

                    request.setAttribute("mensaje", "Producto actualizado con éxito");
                    break;
                case "Eliminar":
                    id = Integer.parseInt(request.getParameter("id"));
                    productoDAO.eliminar(id);
                    //request.setAttribute("mensaje", "Producto eliminado con éxito");
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Productos.jsp").forward(request, response);
        }
        if (menu.equals("Registro")) {
            switch (accion) {
                case "Listar":
                    List lista = registroventasDAO.listarVentas();
                    request.setAttribute("registros", lista);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Registro.jsp").forward(request, response);
        }
        if (menu.equals("Proveedor")) {
            request.setAttribute("empleadoSesion", empleadosesion);
            switch (accion) {
                case "Listar":
                    List lista = proveedorDAO.Listar();
                    request.setAttribute("proveedores", lista);
                    break;
                case "Agregar":

                    String nombrecomercial = request.getParameter("txtnombreComercial");
                    String ruc = request.getParameter("txtruc");
                    String telefono = request.getParameter("txttelefono");
                    String direccion = request.getParameter("txtdireccion");

                    proveedor.setNombre(nombrecomercial);
                    proveedor.setRuc(ruc);
                    proveedor.setTelefono(telefono);
                    proveedor.setDireccion(direccion);

                    proveedorDAO.agregar(proveedor);
                    request.setAttribute("mensaje", "Proveedor agregado con éxito");
                    //request.getRequestDispatcher("Controlador?menu=Proveedor&accion=Listar").forward(request, response);

                    break;

                //FALTA EDITAR, ACTUALIZAR EN PROVEEDOR
                case "Editar":
                    id = Integer.parseInt(request.getParameter("id"));
                    Proveedor proveedo = proveedorDAO.ListarId(id);
                    request.setAttribute("pr", proveedo);

                    //request.getRequestDispatcher("Controlador?menu=Proveedor&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":

                    String nombrecomercialActualizar = request.getParameter("txtnombreComercialAC");
                    String rucActualizar = request.getParameter("txtrucAC");
                    String telefonoActualizar = request.getParameter("txttelefonoAC");
                    String direccionActualizar = request.getParameter("txtdireccionAC");

                    proveedor.setIdproveedor(id);
                    proveedor.setNombre(nombrecomercialActualizar);
                    proveedor.setRuc(rucActualizar);
                    proveedor.setTelefono(telefonoActualizar);
                    proveedor.setDireccion(direccionActualizar);

                    proveedorDAO.actualizar(proveedor);

                    request.setAttribute("mensaje", "Proveedor actualizado con éxito");
                    //request.getRequestDispatcher("Controlador?menu=Proveedor&accion=Listar").forward(request, response);

                    break;
                case "Eliminar":
                    id = Integer.parseInt(request.getParameter("id"));
                    proveedorDAO.eliminar(id);

                    request.setAttribute("mensaje", "Proveedor eliminado con éxito");
                    //request.getRequestDispatcher("Controlador?menu=Proveedor&accion=Listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Proveedor.jsp").forward(request, response);
        }
        if (menu.equals("Venta")) {
            request.getRequestDispatcher("Venta.jsp").forward(request, response);
        }
        if (menu.equals("VentaBoleta")) {
            switch (accion) {
                case "BuscarProducto":
                    int id = Integer.parseInt(request.getParameter("codigoproducto"));
                    producto = productoDAO.ListarId(id);

                    request.setAttribute("pro", producto);
                    request.setAttribute("numeroSerie", nSerieBoleta);
                    request.setAttribute("totalpagarLetras", resultado);
                    request.setAttribute("totalpagar", total);
                    request.setAttribute("lista", lista);

                    break;
                case "BuscarClienteDNI":
                    dni = request.getParameter("dni");
                    String[] clientedni = consultar.consultarClienteDni(dni);

                    nombreCliente = clientedni[0];
                    dniCliente = clientedni[1];

                    request.setAttribute("numeroSerie", nSerieBoleta);
                    request.setAttribute("nombreCliente", nombreCliente);
                    request.setAttribute("dniCliente", dniCliente);
                    request.setAttribute("direccion", direccion);
                    request.setAttribute("pro", producto);
                    request.setAttribute("totalpagarLetras", resultado);
                    request.setAttribute("totalpagar", total);
                    request.setAttribute("lista", lista);
                    break;
                case "Agregar":
                    total = 0.0;
                    cantidad = Integer.parseInt(request.getParameter("productocantidad"));
                    descripcion = request.getParameter("productonombre");
                    precioUnitario = Double.parseDouble(request.getParameter("productoprecio"));
                    precioImporte = precioUnitario * cantidad;

                    venta = new Venta();

                    venta.setCantidad(cantidad);
                    venta.setDescripcion(descripcion);
                    venta.setPrecioUnitario(precioUnitario);
                    venta.setPrecioImporte(precioImporte);

                    int idalmacen = producto.getIdAlmacen();
                    int idproducto = producto.getIdProducto();

                    venta.setIdAlmacen(idalmacen);
                    venta.setIdProducto(idproducto);

                    lista.add(venta);

                    for (int i = 0; i < lista.size(); i++) {
                        total = total + lista.get(i).getPrecioImporte();
                    }
                    resultado = lector.convertirADecimal(total);

                    nSerie = boletaDAO.numeroSerie() + 1;

                    nSeriecadena = String.valueOf(nSerie);

                    //Agregar ceros
                    if (nSeriecadena.length() == 1) {
                        nSerieBoleta = "00" + nSeriecadena;
                    }
                    if (nSeriecadena.length() == 2) {
                        nSerieBoleta = "0" + nSeriecadena;
                    }
                    if (nSeriecadena.length() == 3) {
                        nSerieBoleta = nSeriecadena;
                    }

                    request.setAttribute("numeroSerie", nSerieBoleta);
                    request.setAttribute("totalpagarLetras", resultado);
                    request.setAttribute("totalpagar", total);
                    request.setAttribute("lista", lista);
                    break;
                case "Agregar_Cliente":

                    direccion = request.getParameter("direccionCliente");

                    request.setAttribute("direccion", direccion);
                    request.setAttribute("numeroSerie", nSerieBoleta);
                    request.setAttribute("nombreCliente", nombreCliente);
                    request.setAttribute("dniCliente", dniCliente);
                    request.setAttribute("pro", producto);
                    request.setAttribute("totalpagarLetras", resultado);
                    request.setAttribute("totalpagar", total);
                    request.setAttribute("lista", lista);
                    break;
                case "GenerarVentaBoleta":

                    //Actualizar Stock
                    for (int i = 0; i < lista.size(); i++) {
                        Producto pr2 = new Producto();
                        int cant2 = lista.get(i).getCantidad();
                        int idalmacen2 = lista.get(i).getIdAlmacen();
                        ProductoDAO productDAO2 = new ProductoDAO();
                        pr2 = productDAO2.buscar(idalmacen2);
                        int newStock = pr2.getStock() - cant2;
                        productDAO2.actualizarStock(newStock, idalmacen2);
                    }

                    //Guardar Venta
                    String fecha = VentaDAO.obtenerFecha();
                    venta.setIdEmpleado(empleadosesion.getIdEmpleado());
                    venta.setTotal(total);
                    venta.setFechaEmision(fecha);
                    ventadao.guardarVenta(venta);

                    //Guardar Detalle Venta
                    int idv = Integer.parseInt(ventadao.idVentas());
                    for (int i = 0; i < lista.size(); i++) {
                        venta = new Venta();
                        venta.setIdVenta(idv);
                        venta.setIdAlmacen(lista.get(i).getIdAlmacen());
                        venta.setCantidad(lista.get(i).getCantidad());
                        ventadao.guardarDatalleVentas(venta);
                    }

                    //Guardar datos del Cliente (DNI)
                    boleta.setIdVenta(idv);
                    boleta.setNombreCompleto(nombreCliente);
                    boleta.setDni(dniCliente);
                    boleta.setDireccion(direccion);

                    //Agregar ceros
                    if (nSeriecadena.length() == 1) {
                        nSerieBoleta = "00" + nSeriecadena;
                    }
                    if (nSeriecadena.length() == 2) {
                        nSerieBoleta = "0" + nSeriecadena;
                    }
                    if (nSeriecadena.length() == 3) {
                        nSerieBoleta = nSeriecadena;
                    }

                    boleta.setNumeroSerie(nSerieBoleta);

                    boletaDAO.guardarCliente(boleta);

                    break;
                case "Cancelar":
                    lista.clear();
                    break;
                default:
                    request.getRequestDispatcher("Venta.jsp").forward(request, response);
            }
            request.getRequestDispatcher("VentaBoleta.jsp").forward(request, response);
        }

        if (menu.equals("VentaFactura")) {
            switch (accion) {
                case "BuscarProducto":
                    int id = Integer.parseInt(request.getParameter("codigoproducto"));
                    producto = productoDAO.ListarId(id);
                    request.setAttribute("pro", producto);
                    request.setAttribute("totalpagarLetras", resultado);
                    request.setAttribute("numeroSerie", nSerieFactura);
                    request.setAttribute("subtotal", subTotal);
                    request.setAttribute("igv", igv);
                    request.setAttribute("totalpagar", total);
                    request.setAttribute("lista", lista);
                    break;
                case "BuscarClienteRUC":
                    ruc = request.getParameter("ruc");
                    String[] clienteruc = consultar.consultarClienteRuc(ruc);

                    ruccliente = clienteruc[0];
                    nombreClienteruc = clienteruc[1];
                    direccionClienteruc = clienteruc[2];
                    request.setAttribute("nombreClienteruc", nombreClienteruc);
                    request.setAttribute("ruccliente", ruccliente);
                    request.setAttribute("direccionClienteruc", direccionClienteruc);
                    request.setAttribute("pro", producto);
                    request.setAttribute("totalpagarLetras", resultado);
                    request.setAttribute("numeroSerie", nSerieFactura);
                    request.setAttribute("subtotal", subTotal);
                    request.setAttribute("igv", igv);
                    request.setAttribute("totalpagar", total);
                    request.setAttribute("lista", lista);
                    break;
                case "Agregar":
                    total = 0.0;
                    subTotal = 0.0;
                    igv = 0.0;
                    cantidad = Integer.parseInt(request.getParameter("productocantidad"));
                    descripcion = request.getParameter("productonombre");
                    precioUnitario = Double.parseDouble(request.getParameter("productoprecio"));
                    precioImporte = precioUnitario * cantidad;

                    venta = new Venta();

                    venta.setCantidad(cantidad);
                    venta.setDescripcion(descripcion);
                    venta.setPrecioUnitario(precioUnitario);
                    venta.setPrecioImporte(precioImporte);

                    int idalmacen = producto.getIdAlmacen();
                    int idproducto = producto.getIdProducto();

                    venta.setIdAlmacen(idalmacen);
                    venta.setIdProducto(idproducto);

                    lista.add(venta);

                    for (int i = 0; i < lista.size(); i++) {
                        subTotal = subTotal + lista.get(i).getPrecioImporte();
                    }

                    //Calcular IGV
                    igv = 0.18 * subTotal;
                    //Total a pagar
                    total = subTotal + igv;

                    resultado = lector.convertirADecimal(total);

                    nSerie = facturaDAO.numeroSerie() + 1;

                    nSeriecadena = String.valueOf(nSerie);

                    //Agregar ceros
                    if (nSeriecadena.length() == 1) {
                        nSerieFactura = "00" + nSeriecadena;
                    }
                    if (nSeriecadena.length() == 2) {
                        nSerieFactura = "0" + nSeriecadena;
                    }
                    if (nSeriecadena.length() == 3) {
                        nSerieFactura = nSeriecadena;
                    }

                    request.setAttribute("numeroSerie", nSerieFactura);
                    request.setAttribute("totalpagarLetras", resultado);
                    request.setAttribute("subtotal", subTotal);
                    request.setAttribute("igv", igv);
                    request.setAttribute("totalpagar", total);
                    request.setAttribute("lista", lista);
                    break;
                case "Agregar_Cliente":

                    // direccion = request.getParameter("direccionCliente");
                    request.setAttribute("direccion", direccionClienteruc);
                    request.setAttribute("numeroSerie", nSerieFactura);
                    request.setAttribute("nombreCliente", nombreClienteruc);
                    request.setAttribute("dniCliente", ruccliente);
                    request.setAttribute("pro", producto);
                    request.setAttribute("totalpagarLetras", resultado);
                    request.setAttribute("subtotal", subTotal);
                    request.setAttribute("igv", igv);
                    request.setAttribute("totalpagar", total);
                    request.setAttribute("lista", lista);
                    break;
                case "GenerarVentaFactura":

                    //Actualizar Stock
                    for (int i = 0; i < lista.size(); i++) {
                        Producto pr2 = new Producto();
                        int cant2 = lista.get(i).getCantidad();
                        int idalmacen2 = lista.get(i).getIdAlmacen();
                        ProductoDAO productDAO2 = new ProductoDAO();
                        pr2 = productDAO2.buscar(idalmacen2);
                        int newStock = pr2.getStock() - cant2;
                        productDAO2.actualizarStock(newStock, idalmacen2);
                    }

                    //Guardar Venta
                    String fecha = VentaDAO.obtenerFecha();
                    venta.setIdEmpleado(empleadosesion.getIdEmpleado());
                    venta.setTotal(total);
                    venta.setFechaEmision(fecha);
                    ventadao.guardarVenta(venta);

                    //Guardar Detalle Venta
                    int idv = Integer.parseInt(ventadao.idVentas());
                    for (int i = 0; i < lista.size(); i++) {
                        venta = new Venta();
                        venta.setIdVenta(idv);
                        venta.setIdAlmacen(lista.get(i).getIdAlmacen());
                        venta.setCantidad(lista.get(i).getCantidad());
                        ventadao.guardarDatalleVentas(venta);
                    }

                    //Guardar datos del Cliente (DNI)
                    factura.setIdVenta(idv);
                    factura.setNombreComercial(nombreClienteruc);
                    factura.setRuc(ruccliente);
                    factura.setDireccion(direccionClienteruc);

                    //Agregar ceros
                    if (nSeriecadena.length() == 1) {
                        nSerieFactura = "00" + nSeriecadena;
                    }
                    if (nSeriecadena.length() == 2) {
                        nSerieFactura = "0" + nSeriecadena;
                    }
                    if (nSeriecadena.length() == 3) {
                        nSerieFactura = nSeriecadena;
                    }

                    factura.setNumeroSerie(nSerieFactura);
                    factura.setIgv(igv);

                    facturaDAO.guardarCliente(factura);

                    break;
                case "Cancelar":
                    lista.clear();
                    break;
                default:
                    request.getRequestDispatcher("Venta.jsp").forward(request, response);
            }
            request.getRequestDispatcher("VentaFactura.jsp").forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
