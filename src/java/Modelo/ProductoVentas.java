/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Llapapasca Montes
 */
public class ProductoVentas {
    String nombre;
    int cantidadVendida;

    public ProductoVentas() {
    }

    public ProductoVentas(String nombre, int cantidadVendida) {
        this.nombre = nombre;
        this.cantidadVendida = cantidadVendida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(int cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }
    
    
}
