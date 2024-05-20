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
public class RegistroVentas {
    String clienteNombre;
    String numeroDocumento;
    String clienteDireccion;
    String numeroSerie;
    Double igv;
    Date fechaEmision;

    public RegistroVentas() {
    }

    public RegistroVentas(String clienteNombre, String numeroDocumento, String clienteDireccion, String numeroSerie, Double igv, Date fechaEmision) {
        this.clienteNombre = clienteNombre;
        this.numeroDocumento = numeroDocumento;
        this.clienteDireccion = clienteDireccion;
        this.numeroSerie = numeroSerie;
        this.igv = igv;
        this.fechaEmision = fechaEmision;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getClienteDireccion() {
        return clienteDireccion;
    }

    public void setClienteDireccion(String clienteDireccion) {
        this.clienteDireccion = clienteDireccion;
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

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

   
   
}
