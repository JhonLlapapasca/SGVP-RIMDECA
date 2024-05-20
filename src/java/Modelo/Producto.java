/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Llapapasca Montes
 */
public class Producto {

    int idProducto;
    int idCategoria;
    int idProveedor;
    String nombreproveedor;
    String nombrecategoria;
    int idAlmacen;
    String nombreproducto;
    double precioproducto;
    int stock;
    int cantidad;

    public Producto() {
    }

    public Producto(int idProducto, int idCategoria, int idProveedor, String nombreproveedor, String nombrecategoria, int idAlmacen, String nombreproducto, double precioproducto, int stock, int cantidad) {
        this.idProducto = idProducto;
        this.idCategoria = idCategoria;
        this.idProveedor = idProveedor;
        this.nombreproveedor = nombreproveedor;
        this.nombrecategoria = nombrecategoria;
        this.idAlmacen = idAlmacen;
        this.nombreproducto = nombreproducto;
        this.precioproducto = precioproducto;
        this.stock = stock;
        this.cantidad = cantidad;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombreproveedor() {
        return nombreproveedor;
    }

    public void setNombreproveedor(String nombreproveedor) {
        this.nombreproveedor = nombreproveedor;
    }

    public String getNombrecategoria() {
        return nombrecategoria;
    }

    public void setNombrecategoria(String nombrecategoria) {
        this.nombrecategoria = nombrecategoria;
    }

    public int getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(int idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }

    public double getPrecioproducto() {
        return precioproducto;
    }

    public void setPrecioproducto(double precioproducto) {
        this.precioproducto = precioproducto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    
   
}
