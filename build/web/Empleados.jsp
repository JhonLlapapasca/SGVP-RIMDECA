<%-- 
    Document   : Empleados
    Created on : 27 abr. 2024, 14:16:43
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
        <style>
            .form-control {
                width: 100%;
                padding: 5px;
                border: 1px solid #ced4da;
                border-radius: 5px;

            }

            .square-input {
                display: flex;

            }

            .square-input input[type="text"] {
                width: calc(100% / 8 - 10px); /* Calcula el ancho de cada cuadrado */
                margin-right: 5px;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <c:if test="${not empty mensaje}">
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                ${mensaje}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>

        <div class="row">
            <div class="col-md-4 fw-bold">
                <h3>Empleados</h3>
            </div>
            <div class="col-md-1 ms-auto">
                <c:if test="${empleadoSesion.rol == 'Administrador'}">
                    <div class="">
                        <!-- Inicio Modal -->
                        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModalEmpleado">
                            + Empleado
                        </button>
                        <div class="modal fade" id="exampleModalEmpleado" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h1 class="modal-title fs-5" id="exampleModalLabel">Nuevo Empleado</h1>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <form action="Controlador?menu=Empleado" method="POST">
                                            <div class="mb-3">
                                                <label class="form-label">Nombres</label>
                                                <input type="text" name="txtnombres" class="form-control">
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Apellidos</label>
                                                <input type="text" name="txtapellidos" class="form-control">
                                            </div>
                                            <label class="form-label">DNI</label>
                                            <div class="mb-3 square-input">
                                                <% for (int i = 0; i < 8; i++) {%>
                                                <input type="text" name="txtdni<%= i%>"  maxlength="1" class="form-control" onkeydown="handleKeyDown(event)">
                                                <% }%>
                                            </div>
                                            <label class="form-label">Telefono</label>
                                            <div class="mb-3 square-input">
                                                <% for (int i = 0; i < 9; i++) {%>
                                                <input type="text" name="txttelefono<%= i%>" maxlength="1" class="form-control" onkeydown="handleKeyDown(event)">
                                                <% }%>
                                            </div>

                                            <div class="mb-3">
                                                <label class="form-label">Fecha de ingreso</label>
                                                <p id="fechaIngreso" class="form-control"></p>
                                            </div>

                                            <div class="mb-3">
                                                <label class="form-label">Rol</label>
                                                <select name="txtrol" class="form-select" aria-label="Default select example">
                                                    <option selected>Seleccionar</option>
                                                    <option value="Administrador">Administrador</option>
                                                    <option value="Empleado">Empleado</option>
                                                </select>
                                            </div>

                                            <div class="mb-3">
                                                <label class="form-label">Correo</label>
                                                <input type="text" name="txtcorreo" class="form-control">
                                            </div>
                                            <div class="mb-3">
                                                <label class="form-label">Contraseña</label>
                                                <p class="form-control">RIMDECA</p>
                                            </div>
                                            <div>
                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                                                <input type="submit" name="accion" value="Aceptar" class="btn btn-primary">
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

        <div class="row">
            <c:if test="${empleadoSesion.rol == 'Administrador'}">
                <div class="col-3">

                    <div class="card">
                        <div class="card-body p-2">
                            <h5 class="card-title">Editar</h5>
                            <form action="Controlador?menu=Empleado" method="POST">
                                <div class="mb-3">
                                    <label class="form-label">Nombres</label>
                                    <input value="${empleado.getNombres()}" type="text" name="txtnombresAC" class="form-control">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Apellidos</label>
                                    <input value="${empleado.getApellidos()}" type="text" name="txtapellidosAC" class="form-control">
                                </div>
                                <label class="form-label">DNI</label>
                                <div class="mb-3">
                                    <input value="${empleado.getDni()}" type="text" name="txtdniAC" class="form-control">
                                </div>
                                <label class="form-label">Telefono</label>
                                <div class="mb-3">
                                    <input value="${empleado.getTelefono()}" type="text" name="txttelefonoAC" class="form-control">
                                </div>

                                <div class="mb-3">
                                    <label class="form-label">Rol</label>
                                    <select name="txtrolAC" class="form-select" aria-label="Default select example">
                                        <option value="${empleado.getRol()}" selected>${empleado.getRol()}</option>
                                        <option value="Administrador">Administrador</option>
                                        <option value="Empleado">Empleado</option>
                                    </select>
                                </div>

                                <div class="mb-3">
                                    <label class="form-label">Correo</label>
                                    <input value="${empleado.getCorreo()}" type="text" name="txtcorreoAC" class="form-control">
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

                    <table  id="empleados" class="table table-striped" >
                        <thead>
                            <tr>
                                <th>Nombres</th>
                                <th>Apellidos</th>
                                <th>DNI</th>
                                <th>Telefono</th>
                                <th>Fecha de ingreso</th>
                                <th>Rol</th>
                                <th>Correo</th>  
                                    <c:if test="${empleadoSesion.rol == 'Administrador'}">
                                    <th>Acciones</th>
                                    </c:if>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="em" items="${empleados}">
                                <tr>
                                    <td>${em.getNombres()}</td>
                                    <td>${em.getApellidos()}</td>
                                    <td>${em.getDni()}</td>
                                    <td>${em.getTelefono()}</td>
                                    <td>${em.getFechaIngreso()}</td>
                                    <td>${em.getRol()}</td>
                                    <td>${em.getCorreo()}</td>
                                    <c:if test="${empleadoSesion.rol == 'Administrador'}">
                                        <td>
                                            <a class="btn btn-success" href="Controlador?menu=Empleado&accion=Editar&id=${em.getIdEmpleado()}"><i class="bi bi-pencil-square"></i></a>
                                            <a class="btn btn-danger" href="#"><i class="bi bi-trash"></i></a>      
                                        </td>
                                    </c:if>
                                </tr>  
                            </c:forEach>
                        </tbody>
                        <tfoot>
                            <tr>
                                <th>Nombres</th>
                                <th>Apellidos</th>
                                <th>DNI</th>
                                <th>Telefono</th>
                                <th>Fecha de ingreso</th>
                                <th>Rol</th>
                                <th>Correo</th>  
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
                                                    function handleKeyDown(event) {
                                                        var key = event.keyCode;
                                                        var target = event.target;

                                                        // Flecha izquierda (37) o flecha derecha (39)
                                                        if (key === 37 || key === 39) {
                                                            var nextIndex = key === 37 ? -1 : 1; // -1 para la flecha izquierda, 1 para la flecha derecha
                                                            var currentIndex = Array.from(target.parentNode.children).indexOf(target);
                                                            var nextInput = target.parentNode.children[currentIndex + nextIndex];

                                                            if (nextInput) {
                                                                nextInput.focus();
                                                            }
                                                        }
                                                    }

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
                                                        var fechaFormateada = año + '/' + mes + '/' + dia;
                                                        document.getElementById("fechaIngreso").innerHTML = fechaFormateada;
                                                    }

                                                    window.onload = mostrarFechaActual;

                                                    $(document).ready(function () {
                                                        $('#empleados').DataTable();
                                                    });
    </script>
</html>
