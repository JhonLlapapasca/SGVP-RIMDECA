<%-- 
    Document   : Reporte
    Created on : 18 may. 2024, 12:39:03
    Author     : Llapapasca Montes
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.io.*"%>
<%@page import="org.jfree.chart.*"%>
<%@page import="org.jfree.chart.plot.*"%>
<%@page import="org.jfree.data.general.*"%>
<%@include file="Bootstrap-links/bootstrap.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
</head>
<body>
    <div class="fw-bold">
        <h3>Reporte</h3>
        <hr>
    </div>
    <div class="row">
        <div class="col-6">
            <form action="Controlador" method="GET">
                <input type="hidden" name="menu" value="Reporte">
                <div class="form-group">
                    <label for="tipoGrafico"><h5 class="fw-bold">Seleccione el tipo:</h5></label>
                    <div class="row row-cols-lg-auto">
                        <div class="col-12">
                            <select name="tipoGrafico" id="tipoGrafico" class="form-select">
                                <option value="barras" <c:if test="${param.tipoGrafico == 'barras'}">selected</c:if>>Gráfico de Barras</option>
                                <option value="torta" <c:if test="${param.tipoGrafico == 'torta'}">selected</c:if>>Gráfico de Torta</option>
                                </select>
                            </div>
                            <div class="col-12">
                                <button type="submit" class="btn btn-primary"><i class="bi bi-check-lg"></i> Generar</button>
                            </div>
                        </div>
                    </div>

                </form>

            <c:if test="${not empty chartFilename}">
                <img src="<%= request.getContextPath()%>/temp/${chartFilename}" class="img-fluid" alt="Productos Más Vendidos" />
            </c:if>
        </div>
        <div class="col-6">
            <h4 class="fw-bold">Monto Total:</h4>
            <div class="row">
                <div class="col-4 p-2">
                    <div class="card bg-info-subtle text-info-emphasis">
                        <div class="card-body">
                            <h5 class="card-title">Diario:</h5>
                            <div class="text-center">
                                <h4>S/. ${ventadia}</h4>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-4 p-2">
                    <div class="card bg-primary-subtle text-info-emphasis">
                        <div class="card-body">
                            <h5 class="card-title">Semanal:</h5>
                            <div class="text-center">
                                <h4>S/. ${ventasemanal}</h4>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-4 p-2">
                    <div class="card bg-danger-subtle text-danger-emphasis">
                        <div class="card-body">
                            <h5 class="card-title">Mensual:</h5>
                            <div class="text-center">
                                <h4>S/. ${ventames}</h4>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-4 p-2">
                    <div class="card bg-secondary-subtle text-info-emphasis">
                        <div class="card-body">
                            <h5 class="card-title">Anual:</h5>
                            <div class="text-center">
                                <h4>S/. ${ventaaño}</h4>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <h4 class="fw-bold">Productos vendidos:</h4>
            <table class="table table-bordered border-primary">
                <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Cantidad</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="pro" items="${productos}">
                        <tr>
                            <td>${pro.getNombreproducto()}</td>
                            <td>${pro.getCantidad()}</td>                          
                        </tr>  
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
