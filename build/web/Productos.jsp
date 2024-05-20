<%-- 
    Document   : Productos
    Created on : 25 abr. 2024, 11:20:45
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

        <c:if test="${not empty error}">
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                ${error}
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        </c:if>




        <div class="">
            <div class="row">

                <div class="col-md-4 fw-bold d-flex align-items-start">
                    <h3>Productos</h3>
                    <div class="px-2">
                        <form action="Controlador?menu=Producto" method="POST">
                            <input type="submit" name="accion" value="Descargar" class="btn btn-light">
                        </form>
                    </div> 
                </div>




                <div class="col-md-3 ms-auto d-flex">
                    <c:if test="${empleadoSesion.rol == 'Administrador'}">
                        <div class="">
                            <!-- Inicio Modal -->
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                                + Producto
                            </button>
                            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="exampleModalLabel">Producto</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <form action="Controlador?menu=Producto" method="POST">
                                                <div class="mb-3">
                                                    <label class="form-label">Nombre</label>
                                                    <input type="text" name="txtnombre" class="form-control">
                                                </div>
                                                <div class="mb-3">
                                                    <label class="form-label">Precio</label>
                                                    <input type="text" name="txtprecio" class="form-control">
                                                </div>

                                                <div class="mb-3">
                                                    <label class="form-label">Proveedor</label>
                                                    <select name="txtproveedor" class="form-select" aria-label="Default select example">
                                                        <option selected>Seleccionar</option>
                                                        <c:forEach var="proveedor" items="${proveedores}">
                                                            <option value="${proveedor.getIdproveedor()}">${proveedor.getNombre()}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>

                                                <div class="mb-3">
                                                    <label class="form-label">Categoria</label>
                                                    <select name="txtcategoria" class="form-select" aria-label="Default select example">
                                                        <option selected>Seleccionar</option>
                                                        <c:forEach var="categoria" items="${categorias}">
                                                            <option value="${categoria.getIdcategoria()}">${categoria.getCategoria()}</option>
                                                        </c:forEach>
                                                    </select>
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

                        <div class="mx-2">
                            <!-- Inicio Modal -->
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal2">
                                + Stock
                            </button>
                            <div class="modal fade" id="exampleModal2" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="exampleModalLabel">Producto</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <form action="Controlador?menu=Producto" method="POST">
                                                <div class="mb-3">
                                                    <label class="form-label">Producto</label>
                                                    <select name="txtidproducto" class="form-select" aria-label="Default select example">
                                                        <option selected>Seleccionar</option>
                                                        <c:forEach var="producto" items="${listaproductos}">
                                                            <option value="${producto.getIdProducto()}">${producto.getNombreproducto()}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                                <div class="mb-3">
                                                    <label class="form-label">Stock</label>
                                                    <input type="text" name="txtcantidad" class="form-control">
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

                        <div class="">
                            <!-- Inicio Modal -->
                            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModalCategoria">
                                + Categoria
                            </button>
                            <div class="modal fade" id="exampleModalCategoria" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h1 class="modal-title fs-5" id="exampleModalLabel">Categoria</h1>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <div class="modal-body">
                                            <form action="Controlador?menu=Producto" method="POST">
                                                <div class="mb-3">
                                                    <label class="form-label">Nombre de la Categoria</label>
                                                    <input type="text" name="txtcategoria" class="form-control">
                                                </div>
                                                <div>
                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                                                    <input type="submit" name="accion" value="Agregar_Categoria" class="btn btn-primary">
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
        <p class="text-end fw-bold text-danger">Solo se mostraran los productos que tengan stock</p>

        <div class="row">
            <c:if test="${empleadoSesion.rol == 'Administrador'}">
                <div class="col-3">

                    <div class="card">
                        <div class="card-body p-2">
                            <h5 class="card-title">Editar</h5>
                            <form action="Controlador?menu=Producto" method="POST">
                                <div class="mb-3">
                                    <label class="form-label">Nombre</label>
                                    <input type="text" value="${pr.getNombreproducto()}" name="nombreProducto" class="form-control">
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Precio</label>
                                    <input type="text" value="${pr.getPrecioproducto()}" name="precioProducto" class="form-control">
                                </div>

                                <div class="mb-3">
                                    <label class="form-label">Cantidad</label>
                                    <input type="text" value="${pr.getStock()}" name="stockProducto" class="form-control">
                                </div>

                                <div class="mb-3">
                                    <label class="form-label">Proveedor</label>
                                    <select name="proveedorProducto" class="form-select" aria-label="Default select example">
                                        <option value="${pr.getIdProveedor()}" selected>${pr.getNombreproveedor()}</option>
                                        <c:forEach var="proveedor" items="${proveedores}">
                                            <option value="${proveedor.getIdproveedor()}">${proveedor.getNombre()}</option>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="mb-3">
                                    <label class="form-label">Categoria</label>
                                    <select name="categoriaProducto" class="form-select" aria-label="Default select example">
                                        <option value="${pr.getIdCategoria()}" selected>${pr.getNombrecategoria()}</option>
                                        <c:forEach var="categoria" items="${categorias}">
                                            <option value="${categoria.getIdcategoria()}">${categoria.getCategoria()}</option>
                                        </c:forEach>
                                    </select>
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
                    <table  id="productos" class="table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nombre</th>
                                <th>Precio</th>
                                <th>Stock</th>
                                    <c:if test="${empleadoSesion.rol == 'Administrador'}">
                                    <th>Acciones</th>
                                    </c:if>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="pro" items="${productos}">
                                <tr>
                                    <td>${pro.getIdProducto()}</td>
                                    <td>${pro.getNombreproducto()}</td>
                                    <td>${pro.getPrecioproducto()}</td>
                                    <td>${pro.getStock()}</td>
                                    <c:if test="${empleadoSesion.rol == 'Administrador'}">
                                        <td>
                                            <a class="btn btn-success" href="Controlador?menu=Producto&accion=Editar&id=${pro.getIdProducto()}"><i class="bi bi-pencil-square"></i></a>
                                            <a class="btn btn-danger" href="Controlador?menu=Producto&accion=Eliminar&id=${pro.getIdProducto()}"><i class="bi bi-trash"></i></a>      
                                        </td>
                                    </c:if>
                                </tr>  
                            </c:forEach>
                        </tbody>
                        <tfoot>
                            <tr>
                                <th>ID</th>
                                <th>Nombre</th>
                                <th>Precio</th>
                                <th>Stock</th>
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
            $('#productos').DataTable();
        });
    </script>
</html>
