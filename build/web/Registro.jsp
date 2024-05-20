<%-- 
    Document   : Clientes
    Created on : 25 abr. 2024, 11:46:49
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
         <title>JSP Page</title>
        <%-- REFERENCIA A TABLA --%>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.1.3/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css">
    </head>
    <body>
        <div class="fw-bold">
            <h3>Registro de ventas</h3>
            <hr>  
        </div>
        <table  id="registros" class="table table-striped" style="width:100%">
            <thead>
                <tr>
                    <th>Numero Serie</th>
                    <th>Nombre</th>
                    <th># Documento</th>
                    <th>Dirección</th>
                    <th>I.G.V.</th>
                    <th>Fecha Emision</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="res" items="${registros}">
                    <tr>
                        <td>${res.getNumeroSerie()}</td>
                        <td>${res.getClienteNombre()}</td>
                        <td>${res.getNumeroDocumento()}</td>
                        <td>${res.getClienteDireccion()}</td>
                        <td>
                            <c:choose>
                                <c:when test="${res.getIgv() == 0}">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="flexCheckDisabled" disabled>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="flexCheckCheckedDisabled" checked disabled>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${res.getFechaEmision()}</td>
                    </tr>  
                </c:forEach>
            </tbody>
            <tfoot>
                <tr>
                    <th>Numero Serie</th>
                    <th>Nombre</th>
                    <th># Documento</th>
                    <th>Dirección</th>
                    <th>I.G.V.</th>
                    <th>Fecha Emision</th>
                </tr>
            </tfoot>
        </table>
    </body>
    <%-- REFERENCIA A TABLA --%>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap5.min.js"></script>
    <script>
        $(document).ready(function () {
            $('#registros').DataTable();
        });
    </script>
</body>
</html>
