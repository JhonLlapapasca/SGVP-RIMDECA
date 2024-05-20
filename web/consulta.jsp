<%-- 
    Document   : consulta
    Created on : 4 may. 2024, 19:04:39
    Author     : Llapapasca Montes
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%-- Tu archivo JSP --%>
<html>
    <head>
        <title>Formulario</title>
        <script>

            function enviarDNI() {
                var dni = document.getElementById("dni").value;
                fetch("https://apiperu.dev/api/dni?dni=" + dni, {method: "POST", headers: {Authorization: "Bearer f791765c531c124fdc7095d4f3ee7d21b674460b757668d3cfa66a4c9be15bd5"}})
                        .then((response) => response.json())
                        .then(({ data }) => {
                            var resHTML = "<div>Dni: " + data.numero + "</div>" +
                                    "<div>Nombre Completo: " + data.nombre_completo + "</div>";
                            ;
                            document.getElementById('res').innerHTML = resHTML;
                        })
                        .catch((error) => console.log(error));
            }
            
             function enviarRUC() {
                var dni = document.getElementById("ruc").value;
                fetch("https://apiperu.dev/api/ruc?ruc=" + dni, {method: "POST", headers: {Authorization: "Bearer f791765c531c124fdc7095d4f3ee7d21b674460b757668d3cfa66a4c9be15bd5"}})
                        .then((response) => response.json())
                        .then(({ data }) => {
                            var resHTML = "<div>Direccion: " + data.direccion + "</div>" +
                                    "<div>Nombre: " + data.nombre_o_razon_social + "</div>";
                            ;
                            document.getElementById('res').innerHTML = resHTML;
                        })
                        .catch((error) => console.log(error));
            }
        </script>
    </head>
    <body>
        <%-- Aquí está tu formulario HTML --%>
        <form id="formDni" onsubmit="enviarDNI(); return false;">
            <input type="text" placeholder="Ingrese DNI" id="dni" />
            <button type="submit">Enviar</button>
        </form>
        <form id="formRuc" onsubmit="enviarRUC(); return false;">
            <input type="text" placeholder="ingrese RUC" id="ruc" />
            <button type="submit">Enviar</button>
        </form>
        <div>
            <h2>Resultados</h2>
            <div id="res"></div>
        </div>
    </body>
</html>


