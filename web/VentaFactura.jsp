<%-- 
    Document   : VentaFactura
    Created on : 26 abr. 2024, 14:14:58
    Author     : Llapapasca Montes
--%>
<%-- 
    Document   : VentaBoleta
    Created on : 26 abr. 2024, 14:14:41
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

        <style>
            @media print{
                .noImprimir, btn{
                    display: none;
                }
            }
        </style>
    </head>
    <body>
        <div class="">
            <div class="row">
                <div class="col-sm-3 col-md-3 noImprimir">
                    <div class="card">
                        <form action="Controlador?menu=VentaFactura" method="POST">
                            <div class="card-body">
                                <p class="fw-bold mt-2">Producto</p>
                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" name="codigoproducto" placeholder="Id del Producto">
                                    <button class="btn btn-outline-secondary" name="accion" type="submit" value="BuscarProducto"><i class="bi bi-search"></i></button>
                                </div>
                                <div class="form-floating mt-2">
                                    <input type="text" class="form-control" value="${pro.getNombreproducto()}" name="productonombre" id="productonombre">
                                    <label for="productonombre">Nombre</label>
                                </div>
                                <div class="form-floating mt-2">
                                    <input type="text" class="form-control" value="${pro.getPrecioproducto()}" name="productoprecio" id="productoprecio">
                                    <label for="productoprecio">Precio</label>
                                </div>
                                <div class="form-floating mt-2">
                                    <input type="text" class="form-control" value="${pro.getStock()}" id="productostock">
                                    <label for="productonombre">Stock</label>
                                </div>
                                <div class="form-floating mt-2">
                                    <input type="number" class="form-control" min="1" value="1" name="productocantidad" id="productocantidad">
                                    <label for="productocantidad">Cantidad</label>
                                </div>

                                <div class="mt-2" style="text-align: center;">
                                    <input type="submit" name="accion" class="btn btn-outline-success"  value="Agregar">
                                </div>

                                <p class="fw-bold">Datos del Cliente</p>

                                <div class="input-group mb-3">
                                    <input type="text" class="form-control" name="ruc" placeholder="Ingresar RUC">
                                    <button class="btn btn-outline-secondary" name="accion" type="submit" value="BuscarClienteRUC"><i class="bi bi-search"></i></button>
                                </div>

                                <div class="form-floating mt-2">
                                    <input type="text" class="form-control" value="${nombreClienteruc}" id="nombrecomercial">
                                    <label for="nombrecomercial">Nombre Comercial</label>
                                </div>

                                <div class="form-floating mt-2">
                                    <input type="text" class="form-control" value="${ruccliente}" id="ruc">
                                    <label for="ruc">RUC</label>
                                </div>

                                <div class="form-floating mt-2">
                                    <textarea class="form-control" id="direccion" style="height: 100px">${direccionClienteruc}</textarea>
                                    <label for="direccion">Dirección</label>
                                </div>

                                <div class="mt-2" style="text-align: center;">
                                    <input type="submit" name="accion" class="btn btn-outline-success" value="Agregar_Cliente">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="col-sm-12 col-md-8 offset-md-0">
                    <div class="card">
                        <div class="card-body">
                            <div class="">
                                <div class="row align-items-start">
                                    <div class="col-3">
                                        <img src="img/logo.png" alt="Bootstrap" width="170" height="140">
                                    </div>
                                    <div class="col-5">
                                        Representaciones Industricales 
                                        y Mecanicas DECA E.I.R.L.
                                        <div>
                                            Ruc: 20480654901
                                        </div>
                                        <div>
                                            Cal. Pachacutec nro.110 P.J. 9 
                                            de Octubre Lambayeque -Chiclayo
                                        </div>

                                    </div>
                                    <div class="col-4">
                                        <div class="card text-center">
                                            <div class="card-body" style="margin-top: 20px;">
                                                <div>
                                                    FACTURA ELECTRONICA
                                                </div>
                                                <div>
                                                    Nro. F - ${numeroSerie}
                                                </div>
                                                <br>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <hr>
                            <div>
                                <div id="fechaEmision"></div>
                                <div>
                                    <div>Nombre Comercial: ${nombreCliente}</div>
                                    <div>RUC: ${dniCliente}</div>
                                    <div>Dirección del cliente: ${direccion}</div>
                                </div>
                            </div>
                            <hr>
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th scope="col">Cantidad</th>
                                        <th scope="col">Nombre</th>
                                        <th scope="col">Precio Unitario</th>
                                        <th scope="col">Importe</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="list" items="${lista}">
                                        <tr>
                                            <td>${list.getCantidad()}</td>
                                            <td>${list.getDescripcion()}</td>
                                            <td>S/. ${list.getPrecioUnitario()}</td>
                                            <td>S/. ${list.getPrecioImporte()}</td>
                                        </tr>
                                    </c:forEach>

                                </tbody>
                            </table>

                            <div class="row justify-content-end mt-1">
                                <div class="col-sm-2 col-md-2">
                                    SubTotal:
                                </div>

                                <div class="col-sm-4 col-md-2">
                                    S/. ${subtotal}
                                </div>
                            </div>
                            <div class="row justify-content-end mt-1">
                                <div class="col-sm-2 col-md-2">
                                    I.G.V (18%):
                                </div>

                                <div class="col-sm-4 col-md-2">
                                    S/. ${igv}
                                </div>
                            </div>
                            <div class="row justify-content-end mt-1">
                                <div class="col-sm-2 col-md-2">
                                    Total:
                                </div>

                                <div class="col-sm-4 col-md-2">
                                    S/. ${totalpagar}
                                </div>
                            </div>

                            <div class="row justify-content-end mt-2">
                                <div class="col-sm-6 col-md-4">
                                    <p>${totalpagarLetras}</p>
                                </div>
                            </div>

                            <div class="row justify-content-end mt-1">
                                <div class="col-sm-2 col-md-2">
                                    <a href="Controlador?menu=VentaFactura&accion=Cancelar" class="btn btn-danger noImprimir"><i class="bi bi-x"></i> Cancelar</a>
                                </div>

                                <div class="col-sm-2 col-md-2">
                                    <a href="Controlador?menu=VentaFactura&accion=GenerarVentaFactura" class="btn btn-success noImprimir" onclick="print()"><i class="bi bi-bag-check"></i> Generar Venta</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>


            //Fecha Actual o emisión
            function mostrarFechaActual() {
                var fechaActual = new Date();
                var dia = fechaActual.getDate();
                var mes = fechaActual.getMonth() + 1;
                var año = fechaActual.getFullYear();
                if (dia < 10) {
                    dia = '0' + dia;
                }
                if (mes < 10) {
                    mes = '0' + mes;
                }
                var fechaFormateada = dia + '/' + mes + '/' + año;
                document.getElementById("fechaEmision").innerHTML = "Fecha de emisión: " + fechaFormateada;
            }

            window.onload = mostrarFechaActual;
        </script>
    </body>
</html>

