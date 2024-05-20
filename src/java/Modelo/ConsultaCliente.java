/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Llapapasca Montes
 */
public class ConsultaCliente {

public static String[] consultarClienteDni(String dni) {
        try {
            URL url = new URL("http://localhost:8085/dni/" + dni);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parsear JSON
            String jsonString = response.toString();

            // Obtener el nombre completo
            int startIndexNombre = jsonString.indexOf("\"nombre_completo\":\"") + 19;
            int endIndexNombre = jsonString.indexOf("\"", startIndexNombre);
            String nombreCompleto = jsonString.substring(startIndexNombre, endIndexNombre);

            // Obtener el DNI
            int startIndexDni = jsonString.indexOf("\"dni\":\"") + 7;
            int endIndexDni = jsonString.indexOf("\"", startIndexDni);
            String dniD = jsonString.substring(startIndexDni, endIndexDni);

            return new String[]{nombreCompleto, dniD};

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return null; 
        }
    }
    public static String[] consultarClienteRuc(String ruc) {
        try {
            URL url = new URL("http://localhost:8085/ruc/" + ruc);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parsear JSON
            String jsonString = response.toString();

            // Obtener el RUC
            int startIndexRuc = jsonString.indexOf("\"ruc\":\"") + 7;
            int endIndexRuc = jsonString.indexOf("\"", startIndexRuc);
            String rucEmpresa = jsonString.substring(startIndexRuc, endIndexRuc);

            // Obtener el nombre comercial
            int startIndexNCom = jsonString.indexOf("\"nombre_comercial\":\"") + 20;
            int endIndexNCom = jsonString.indexOf("\"", startIndexNCom);
            String nombreComercial = jsonString.substring(startIndexNCom, endIndexNCom);

            // Obtener la dirección
            int startIndexDom = jsonString.indexOf("\"domicilio_fiscal\":\"") + 20;
            int endIndexDom = jsonString.indexOf("\"", startIndexDom);
            String direccionDom = jsonString.substring(startIndexDom, endIndexDom);

            direccionDom = decodeUnicode(direccionDom);

            // Convertir la dirección a mayúsculas
            direccionDom = direccionDom.toUpperCase();

            return new String[]{rucEmpresa, nombreComercial, direccionDom};

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }

    public static String decodeUnicode(String input) {
        Pattern pattern = Pattern.compile("\\\\u(\\p{XDigit}{4})");
        Matcher matcher = pattern.matcher(input);
        StringBuffer buffer = new StringBuffer(input.length());
        while (matcher.find()) {
            char ch = (char) Integer.parseInt(matcher.group(1), 16);
            matcher.appendReplacement(buffer, String.valueOf(ch));
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }

    public static void main(String[] args) {

        System.out.println("-----------------------------");

        String dni = "16711333"; // DNI de ejemplo
        String ruc = "20480654901"; // RUC de ejemplo

        String[] cliente = consultarClienteDni(dni);
        if (cliente != null) {
            System.out.println("Nombre: " + cliente[0]);
            System.out.println("DNI: " + cliente[1]);
        } else {
            System.out.println("No se pudo consultar el cliente.");
        }

        String[] empresa = consultarClienteRuc(ruc);
        if (empresa != null) {
            System.out.println("ruc: " + empresa[0]);
            System.out.println("nombre: " + empresa[1]);
            System.out.println("dirección: " + empresa[2]);
        } else {
            System.out.println("No se pudo consultar el cliente.");
        }
    }
}
