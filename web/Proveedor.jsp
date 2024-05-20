<%-- 
    Document   : Proveedor
    Created on : 13 may. 2024, 15:41:59
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
        <%-- REFERENCIA A TABLA --%>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.1.3/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css">
    </head>
    <body>

        <c:if test="${not empty mensaje}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                ${mensaje}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>

        <div class="">
            <div class="row">
                <div class="col-md-4 fw-bold">
                    <h3>Proveedor</h3>
                </div>
                <div class="col-md-1 ms-auto">
                    <c:if test="${empleadoSesion.rol == 'Administrador'}">
                        <div class="">
                            <!-- Inicio Modal -->
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                                + Proveedor
                            </button>
                            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="exampleModalLabel">Producto</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <form action="Controlador?menu=Proveedor" method="POST">
                                                <div class="mb-3">
                                                    <label class="form-label">Nombre Comercial</label>
                                                    <input type="text" name="txtnombreComercial" class="form-control">
                                                </div>
                                                <div class="mb-3">
                                                    <label class="form-label">Ruc</label>
                                                    <input type="text" name="txtruc" class="form-control">
                                                </div>
                                                <div class="mb-3">
                                                    <label class="form-label">Telefono</label>
                                                    <input type="text" name="txttelefono" class="form-control">
                                                </div>
                                                <div class="mb-3">
                                                    <label class="form-label">Dirección</label>
                                                    <input type="text" class="form-control" name="txtdireccion" style="height: 100px">
                                                </div>


                                                <div>
                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                                                    <input type="submit" name="accion" value="Agregar" class="btn btn-primary">
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- Fin Modal -->
                        </div>
                    </c:if>
                </div>
            </div>
            <hr>
        </div>


        <div class="row">
            <c:if test="${empleadoSesion.rol == 'Administrador'}">
                <div class="col-3">

                    <div class="card">
                        <div class="card-body p-2">
                            <h5 class="card-title">Editar</h5>
                            <form action="Controlador?menu=Proveedor" method="POST">
                                <div class="mb-3">
                                    <label class="form-label">Nombre Comercial</label>
                                    <input type="text" value="${pr.getNombre()}" name="txtnombreComercialAC" class="form-control">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Ruc</label>
                                    <input type="text" value="${pr.getRuc()}" name="txtrucAC" class="form-control">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Telefono</label>
                                    <input type="text" value="${pr.getTelefono()}" name="txttelefonoAC" class="form-control">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Dirección</label>
                                    <input type="text" value="${pr.getDireccion()}" class="form-control" name="txtdireccionAC" style="height: 100px">
                                </div>
                                <div>
                                    <input type="submit" name="accion" value="Actualizar" class="btn btn-primary">
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${empleadoSesion.rol == 'Administrador'}">
                <div class="col-9">
                </c:if>
                <div class="col-12">
                    <table   id="proveedor" class="table table-striped" >
                        <thead>
                            <tr>
                                <th>Nombre</th>
                                <th>Ruc</th>
                                <th>Direccion</th>
                                <th>Telefono</th>
                                    <c:if test="${empleadoSesion.rol == 'Administrador'}">
                                    <th>Acciones</th>
                                    </c:if>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="pro" items="${proveedores}">
                                <tr>
                                    <td>${pro.getNombre()}</td>
                                    <td>${pro.getRuc()}</td>
                                    <td>${pro.getDireccion()}</td>
                                    <td>${pro.getTelefono()}</td>
                                    <c:if test="${empleadoSesion.rol == 'Administrador'}">
                                        <td>
                                            <a class="btn btn-success" href="Controlador?menu=Proveedor&accion=Editar&id=${pro.getIdproveedor()}"><i class="bi bi-pencil-square"></i></a>
                                            <a class="btn btn-danger" href="Controlador?menu=Proveedor&accion=Eliminar&id=${pro.getIdproveedor()}"><i class="bi bi-trash"></i></a>      
                                        </td>
                                    </c:if>
                                </tr>  
                            </c:forEach>
                        </tbody>
                        <tfoot>
                            <tr>
                                <th>Nombre</th>
                                <th>Ruc</th>
                                <th>Direccion</th>
                                <th>Telefono</th>
                                    <c:if test="${empleadoSesion.rol == 'Administrador'}">
                                    <th>Acciones</th>
                                    </c:if>
                            </tr>
                        </tfoot>
                    </table>
                </div>

            </div>



    </body>
    <%-- REFERENCIA A TABLA --%>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap5.min.js"></script>
    <script>


        $(document).ready(function () {
            $('#proveedor').DataTable();
        });
    </script>
</html>

