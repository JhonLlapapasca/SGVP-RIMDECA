<%-- 
    Document   : Venta
    Created on : 26 abr. 2024, 13:53:51
    Author     : Llapapasca Montes
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="Bootstrap-links/bootstrap.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="fw-bold d-flex align-items-start">
            <h3>Venta</h3>
            <div class="px-2">
                <select class="form-select" id="tipoComprobante" aria-label="Default select example" onchange="cambiarTipoComprobante()">
                    <option selected disabled>Tipo de comprobante</option>
                    <option value="boleta">Boleta</option>
                    <option value="factura">Factura</option>
                </select>
            </div> 
        </div>
        <hr>

        <div style="height:100vh;">
            <iframe id="frameVenta" name="frameVenta" style="height: 100%; width: 100%; border: none;"></iframe>
        </div>
        <script>
            window.onload = function () {
                document.getElementById("tipoComprobante").value = "boleta";
                cambiarTipoComprobante();
            };
            function cambiarTipoComprobante() {
                var tipoComprobante = document.getElementById("tipoComprobante").value;
                var url = "";
                if (tipoComprobante === "boleta") {
                    url = "VentaBoleta.jsp";
                } else if (tipoComprobante === "factura") {
                    url = "VentaFactura.jsp";
                }
                document.getElementById("frameVenta").src = url;
            }
        </script>
    </body>
</html>
