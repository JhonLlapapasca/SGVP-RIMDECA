<%-- 
    Document   : Configuracion
    Created on : 18 may. 2024, 13:19:43
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
        <div class="fw-bold">
            <h3>Configuración</h3>
            <hr>
        </div>
        <div class="card container">
            <div class="card-body">
                <h5 class="card-title text-center fw-bold">Datos del Usuario - ID [${empleado.idEmpleado}]</h5>
                <form action="Controlador?menu=Configuracion" method="POST">
                    <div class="row">
                        <div class="col-6">
                            <h5 class="card-title">Personales</h5>
                            <div class="p-2">
                                <div class="form-floating mt-2">
                                    <input type="text" class="form-control" value="${empleado.nombres}" name="nombres" id="nombres">
                                    <label for="nombres">Nombres</label>
                                </div>
                                <div class="form-floating mt-2">
                                    <input type="text" class="form-control" value="${empleado.apellidos}" name="apellidos" id="apellidos">
                                    <label for="apellidos">Apellidos</label>
                                </div>
                                <div class="form-floating mt-2">
                                    <input type="text" class="form-control" value="${empleado.dni}" name="dni" id="dni">
                                    <label for="dni">DNI</label>
                                </div>
                                <div class="form-floating mt-2">
                                    <input type="text" class="form-control" value="${empleado.telefono}" name="telefono" id="telefono">
                                    <label for="telefono">Teléfono</label>
                                </div>
                            </div>
                        </div>
                        <div class="col-6">
                            <h5 class="card-title">Generales</h5>
                            <div class="p-2">
                                <div class="form-floating mt-2">
                                    <input type="text" class="form-control" value="${empleado.correo}" name="correo" id="correo">
                                    <label for="correo">Correo</label>
                                </div>
                                <div class="form-floating mt-2">
                                    <input type="text" class="form-control" value="${empleado.contrasena}" name="contrasena" id="contrasena">
                                    <label for="contrasena">Contraseña</label>
                                </div>
                                <div class="form-floating mt-2">
                                    <p class="form-control">${empleado.rol}</p>
                                    <label for="rol">Rol</label>
                                </div>
                                <div class="form-floating mt-2">
                                    <p class="form-control">${empleado.fechaIngreso}</p>
                                    <label for="fechaIngreso">Fecha de ingreso</label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <input type="submit" name="accion" value="Actualizar" class="btn btn-primary">
                    </div>
                </form>
                <c:if test="${not empty mensaje}">
                    <div class="alert alert-success mt-3" role="alert">
                        ${mensaje}
                    </div>
                </c:if>
            </div>   
        </div>
    </body>
</html>
