<%-- 
    Document   : Principal
    Created on : 23 abr. 2024, 16:36:43
    Author     : Llapapasca Montes
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="Bootstrap-links/bootstrap.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RIMDECA E.I.R.L.</title>
    </head>
    <body>

        <div class="main-container d-flex">
            
            <div class="sidebar bg-light" id="side_nav">
                <div class="header-box px-2 pt-3 pb-4 d-flex justify-content-between">
                    <a class="navbar-brand mx-auto" href="#"><img src="img/logo.png" alt="Bootstrap" width="170" height="150">
                    </a>
                    <button class="btn d-md-none d-block close-btn px-1 py-0 btn btn-light"><i class="bi bi-list"></i></button>
                </div>

                <ul class="list-unstyled px-2">
                    <li class="active"><a href="#" id="inicioEnlace" class="text-decoration-none px-3 py-2 d-block btn btn-light"><i class="bi bi-house"></i> Inicio</a></li>
                    <li class=""><a href="Controlador?menu=Venta&accion=default" target="myFrame" class="text-decoration-none px-3 py-2 d-block btn btn-light"><i class="bi bi-cart"></i>
                            Venta</a></li>
                    <li class=""><a href="Controlador?menu=Producto&accion=Listar" target="myFrame"  class="text-decoration-none px-3 py-2 d-block btn btn-light">
                            <span><i class="bi bi-archive"></i> Productos</span>
                        </a>
                    </li>
                    <li class=""><a href="Controlador?menu=Registro&accion=Listar" target="myFrame"  class="text-decoration-none px-3 py-2 d-block btn btn-light">
                            <span><i class="bi bi-archive"></i> Registro</span>
                        </a>
                    </li>
                    <li class=""><a href="Controlador?menu=Proveedor&accion=Listar" target="myFrame"  class="text-decoration-none px-3 py-2 d-block btn btn-light">
                            <span><i class="bi bi-people"></i> Proveedor</span>
                        </a>
                    </li>
                    <li class=""><a href="Controlador?menu=Empleado&accion=Listar" target="myFrame" class="text-decoration-none px-3 py-2 d-block btn btn-light"><i class="bi bi-people"></i> Empleados</a></li>
                    <li class=""><a href="Controlador?menu=Reporte&accion=Listar" target="myFrame" class="text-decoration-none px-3 py-2 d-block btn btn-light"><i class="bi bi-bar-chart"></i>
                            Reportes</a></li>
                </ul>
                <hr class="h-color mx-2">

                <ul class="list-unstyled px-2">
                    <li class=""><a href="Controlador?menu=Configuracion" target="myFrame"  class="text-decoration-none px-3 py-2 d-block btn btn-light"><i class="bi bi-gear"></i>
                            Configuración</a></li>

                </ul>

                <div style="text-align: center;">
                    <form action="Validar" method="POST">
                        <button name="accion" value="Salir" class="btn btn-primary"><i class="bi bi-arrow-bar-left"></i> Salir</button>
                    </form>
                </div>

            </div>
            <div class="content">
                <nav class="navbar navbar-expand-md navbar-light">
                    <div class="container-fluid">
                        <div class="d-flex justify-content-between d-md-none d-block">
                            <button class="btn px-1 py-0 open-btn me-2 bg-dark rounded px-2 py-0 text-white">RINDECA</button>
                        </div>
                        <button class="navbar-toggler p-0 border-0" type="button" data-bs-toggle="collapse"
                                data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                                aria-expanded="false" aria-label="Toggle navigation">
                            <i class="fal fa-bars"></i>
                        </button>
                    </div>
                </nav>

                <div class=" px-2 pt-5" style="height:100vh;">
                    <div id="bienvenida" class="fw-bold">
                        <h3>Bienvenido  
                            <c:if test="${empleadoSesion.rol == 'Administrador'}">
                                Administrador
                            </c:if>
                            ${empleadoSesion.nombres} ${empleadoSesion.apellidos} 👋</h3>
                        <hr>

                        <div>
                            <div id="carouselExampleIndicators" class="carousel slide">
                                <div class="carousel-indicators">
                                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
                                </div>
                                <div class="carousel-inner">
                                    <div class="carousel-item active">
                                        <img src="img/carrusel01.png" class="d-block w-100" height="380">
                                    </div>
                                    <div class="carousel-item">
                                        <img src="img/carrusel02.jpg" class="d-block w-100" height="380">
                                    </div>          
                                </div>
                                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">Previous</span>
                                </button>
                                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">Next</span>
                                </button>
                            </div>
                        </div>

                    </div>

                    <iframe id="myFrame" name="myFrame" style="height: 100%; width: 100%;">
                    </iframe>
                </div>
            </div>
        </div>



        <style>


            #side_nav{

                min-width: 250px;
                max-width: 250px;
                transition: all 0.3s;
            }
            .content{
                height: 100%;
                width: 100%;
            }


            .sidebar li.active{

                border-radius: 8px;
            }


            @media(max-width: 767px){
                #side_nav{
                    margin-left: -250px;
                    position: absolute;
                    min-height: 100vh;
                    z-index: 1;

                }
                #side_nav.active{
                    margin-left: 0;
                }
            }

        </style>
        <script src="https://code.jquery.com/jquery-3.6.0.js"></script>

        <script  type="text/javascript">

            // INICIO DE OCULTAR Y VOLVER AL INICIO (IFRAME) 
            document.addEventListener("DOMContentLoaded", function () {
                const bienvenida = document.getElementById("bienvenida");
                const iframe = document.getElementById("myFrame");

                iframe.onload = function () {
                    if (iframe.contentDocument && iframe.contentDocument.body.children.length > 0) {
                        bienvenida.style.display = "none";
                        iframe.style.display = "block";
                    }
                };

                function volverInicio() {
                    bienvenida.style.display = "block";
                    iframe.style.display = "none";
                }

                document.getElementById("inicioEnlace").addEventListener("click", volverInicio);
            });

            //--------------------------------

            $(".sidebar ul li").on('click', function () {
                $(".sidebar ul li.active").removeClass('active');
                $(this).addClass('active');
            });

            $('.open-btn').on('click', function () {
                $('.sidebar').addClass('active');

            });


            $('.close-btn').on('click', function () {
                $('.sidebar').removeClass('active');

            })
        </script>

    </body>

</html>
