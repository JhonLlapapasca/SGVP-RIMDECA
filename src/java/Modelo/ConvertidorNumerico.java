/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Llapapasca Montes
 */
public class ConvertidorNumerico {

    // Método para convertir un double a palabras
    public static String convertirADecimal(double cantidad) {
        String[] unidades = {"", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve"};
        String[] decenas = {"", "diez", "veinte", "treinta", "cuarenta", "cincuenta", "sesenta", "setenta", "ochenta", "noventa"};
        String[] especiales = {"diez", "once", "doce", "trece", "catorce", "quince", "dieciséis", "diecisiete", "dieciocho", "diecinueve"};
        String[] centenas = {"", "ciento", "doscientos", "trescientos", "cuatrocientos", "quinientos", "seiscientos", "setecientos", "ochocientos", "novecientos"};
        String[] miles = {"", "mil", "millón", "mil millones", "billón", "mil billones"};
        
        // Dividir la cantidad en parte entera y parte decimal
        long parteEntera = (long) cantidad;
        int parteDecimal = (int) ((cantidad - parteEntera) * 100);

        // Convertir la parte entera a palabras
        StringBuilder resultado = new StringBuilder();
        if (parteEntera == 0) {
            resultado.append("Cero");
        } else {
            int grupo = 0;
            while (parteEntera > 0) {
                int grupoActual = (int) (parteEntera % 1000);
                if (grupoActual != 0) {
                    String palabrasGrupo = convertirGrupo(grupoActual, unidades, decenas, especiales, centenas);
                    if (grupo == 0 && grupoActual == 1) {
                        resultado.insert(0, "Cien" + " ");
                    } else {
                        resultado.insert(0, palabrasGrupo + " " + miles[grupo] + " ");
                    }
                }
                grupo++;
                parteEntera /= 1000;
            }
        }

        // Convertir la parte decimal a palabras
        if (parteDecimal > 0) {
            resultado.append("con " + parteDecimal + "/100");
        } else {
            resultado.append("con 00/100");
        }

        // Capitalizar la primera letra
        resultado.setCharAt(0, Character.toUpperCase(resultado.charAt(0)));
        
        return resultado.toString().trim();
    }

    // Método auxiliar para convertir un grupo de tres dígitos a palabras
    private static String convertirGrupo(int grupo, String[] unidades, String[] decenas, String[] especiales, String[] centenas) {
        StringBuilder resultado = new StringBuilder();
        
        // Obtener las unidades
        int unidadesGrupo = grupo % 10;
        int decenasGrupo = (grupo % 100) / 10;
        int centenasGrupo = grupo / 100;

        // Convertir las centenas
        if (centenasGrupo > 0) {
            resultado.append(centenas[centenasGrupo] + " ");
        }

        // Convertir las decenas
        if (decenasGrupo > 0) {
            if (decenasGrupo == 1 && unidadesGrupo > 0) {
                resultado.append(especiales[unidadesGrupo] + " ");
            } else {
                resultado.append(decenas[decenasGrupo] + " ");
            }
        }

        // Convertir las unidades
        if (unidadesGrupo > 0 && decenasGrupo != 1) {
            resultado.append(unidades[unidadesGrupo] + " ");
        }

        return resultado.toString().trim();
    }
}
