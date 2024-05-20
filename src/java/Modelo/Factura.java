/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Llapapasca Montes
 */
public class Factura {

    int idBoleta;
    int idVenta;
    String nombreComercial;
    String ruc;
    String direccion;
    String numeroSerie;
    Double igv;

    public Factura() {
    }

    public Factura(int idBoleta, int idVenta, String nombreComercial, String ruc, String direccion, String numeroSerie, Double igv) {
        this.idBoleta = idBoleta;
        this.idVenta = idVenta;
        this.nombreComercial = nombreComercial;
        this.ruc = ruc;
        this.direccion = direccion;
        this.numeroSerie = numeroSerie;
        this.igv = igv;
    }

    public int getIdBoleta() {
        return idBoleta;
    }

    public void setIdBoleta(int idBoleta) {
        this.idBoleta = idBoleta;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public Double getIgv() {
        return igv;
    }

    public void setIgv(Double igv) {
        this.igv = igv;
    }
    
    
    
}
