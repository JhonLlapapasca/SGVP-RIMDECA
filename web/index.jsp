<%-- 
    Document   : index
    Created on : 23 abr. 2024, 16:28:05
    Author     : Llapapasca Montes
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="Bootstrap-links/bootstrap.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RIMDECA E.I.R.L.</title>
    </head>
    <body>
        <div class="container">
            <div class="d-grid gap-2 d-md-flex justify-content-md-end pt-4">
                <!-- Inicio Modal -->
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                    Acceder
                </button>
                <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5" id="exampleModalLabel">INICIAR SESION</h1>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <form action="Validar" method="POST">
                                    <div class="mb-3">
                                        <label class="form-label">Correo</label>
                                        <input type="text" name="txtuser" class="form-control">
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Contraseña</label>
                                        <input type="password" name="txtpass" class="form-control">
                                    </div>
                                    <div class="mb-3">
                                        <label class="form-label">Rol</label>
                                        <select name="txtrol" class="form-select" aria-label="Default select example">
                                            <option selected>Seleccionar</option>
                                            <option value="Administrador">Administrador</option>
                                            <option value="Empleado">Empleado</option>
                                        </select>
                                    </div>
                                    <div>
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
                                        <input type="submit" name="accion" value="Ingresar" class="btn btn-primary">
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- Fin Modal -->
            </div>
            <div class="clearfix mt-5">
                <img src="img/logo.png" class="col-md-6 float-md-end mb-3 ms-md-3" alt="...">
                <div style="padding-top: 50px">
                    <p>
                        Somos una empresa con+15 años de trayectoria en el mercado, en RIMDECA E.I.R.L. nos especializamos en la satisfacción de las necesidades del sector industrial y mecánico. Hemos forjado una sólida reputación como proveedores de maquinaria y equipo al por mayor, así como de otros productos vinculados a la construcción, ferretería y fontanería.
                    </p>
                    <p>
                        Nuestra ubicación estratégica en la Calle Pachacutec #110, Interior 01, P.J. 9 de Octubre, en Chiclayo, Lambayeque, nos coloca en el corazón de la actividad comercial de la región. Desde aquí, operamos con eficiencia para atender las demandas de nuestros clientes y garantizar su plena satisfacción.
                    </p>  
                    <a href="https://maps.app.goo.gl/fAwpTRQauf77e2Rt8" target="_blank" class="btn btn-primary" tabindex="-1" role="button" aria-disabled="true"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-geo-alt-fill" viewBox="0 0 16 16">
                        <path d="M8 16s6-5.686 6-10A6 6 0 0 0 2 6c0 4.314 6 10 6 10m0-7a3 3 0 1 1 0-6 3 3 0 0 1 0 6"/>
                        </svg> Ubicación
                    </a>
                </div>
            </div>
            <br>
            <div>
                <div class="row">
                    <div class="col-sm-6 col-md-6 col-lg-6">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Misión</h5>
                                <p class="card-text">Somos una empresa de origen peruano encargada de suministrar con efectividad mangueras y conexiones para el sector minero, industrial y pesquero; generando una sana competitividad y aportando significativamente al desarrollo social, ambiental y a la industria peruana.
                                </p>
                            </div>
                        </div>
                        <br>
                    </div>
                    <div class="col-sm-6 col-md-6 col-lg-6">  
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Visión</h5>
                                <p class="card-text">Ser una empresa líder y competitiva a nivel nacional en el suministro de mangueras hidráulicas e industriales desarrollando máximo potencial y desarrollo económico de la nación peruana.
                                    <br>
                                    <br>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <hr>
            <!-- Pie de pagina (footer) -->
            <div class="row">
                <div class="col-sm-6 col-md-6 col-lg-6">
                    <p class="fw-bold">
                        Contacto
                    </p>
                    <p>Télefono: 01 123456789</p>
                    <p>Facebook: <a href="https://www.facebook.com/" target="_blank" class="text-decoration-none">RINDECA</a></p>
                    <p>WhatsApp: <a href="https://web.whatsapp.com/" target="_blank" class="text-decoration-none">+51 123456789</a></p>
                </div>
                <div class="col-sm-6 col-md-6 col-lg-6">  
                    <p class="fw-bold">
                        Horario de Atención
                    </p>
                    <p>Lunes - Viernes : 8am - 8pm</p>
                    <p>Sábado - Domingo: 8am - 1pm</p>
                </div>
            </div>
            <div class="text-end fw-bold fst-italic">
                <p>REPRESENTACIONES INDUSTRILAES Y MECANICAS DECA E.I.R.L.</p>
                <p>RUC: 20480654901</p>
            </div>
        </div>
    </body>
</html>
