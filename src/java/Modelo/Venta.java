/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author Llapapasca Montes
 */
public class Venta {
    
    String numeroSerie;
    int cantidad;
    int idEmpleado;
    int idProducto;
    int idVenta;
    int idAlmacen;
    String descripcion;
    Double precioUnitario;
    Double precioImporte;
    Double subTotal;
    Double igv;
    Double total;
    String fechaEmision;

    public Venta() {
    }

    public Venta(String numeroSerie, int cantidad, int idEmpleado, int idProducto, int idVenta, int idAlmacen, String descripcion, Double precioUnitario, Double precioImporte, Double subTotal, Double igv, Double total, String fechaEmision) {
        this.numeroSerie = numeroSerie;
        this.cantidad = cantidad;
        this.idEmpleado = idEmpleado;
        this.idProducto = idProducto;
        this.idVenta = idVenta;
        this.idAlmacen = idAlmacen;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.precioImporte = precioImporte;
        this.subTotal = subTotal;
        this.igv = igv;
        this.total = total;
        this.fechaEmision = fechaEmision;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdAlmacen() {
        return idAlmacen;
    }

    public void setIdAlmacen(int idAlmacen) {
        this.idAlmacen = idAlmacen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getPrecioImporte() {
        return precioImporte;
    }

    public void setPrecioImporte(Double precioImporte) {
        this.precioImporte = precioImporte;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getIgv() {
        return igv;
    }

    public void setIgv(Double igv) {
        this.igv = igv;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

   
}
