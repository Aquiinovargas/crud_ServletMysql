<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelos.Alumno"%>
<%@page import="modelos.Alumnos"%>
<%@page import="dao.DaoAlumno"%>

<%!
    DaoAlumno dao = new DaoAlumno();
    Alumno edit = null;
%>

<%
    edit = null;

    if (request.getAttribute("edit") != null) {
        edit = (Alumno) request.getAttribute("edit");
    }

    Alumnos lista = dao.listar();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>BD CON SERVLETS</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="estilos.css">

</head>

<body>

<div id="form_registro">

    <div style="text-align:right;max-width:1100px;margin:0 auto 10px auto;">
        <a href="UsuarioServlet?accion=Logout">Cerrar sesión</a>
    </div>

    <h2><%= (edit != null) ? "Modificar calificaciones" : "Registro de calificaciones" %></h2>

    <form method="post" action="AlumnoServlet">

        <input type="hidden" name="accion" id="accion" value="<%= (edit != null) ? "Modificar" : "Agregar" %>">

        <input type="hidden" name="tfNLold" id="tfNLold" value="<%= (edit != null) ? edit.getNL() : "" %>">

        <input type="text" name="tfNL" id="tfNL" value="<%= (edit != null) ? edit.getNL() : "" %>" placeholder="Numero de Lista" <%= (edit != null) ? "readonly" : "" %> required>

        <input type="text" name="tfNombre" id="tfNombre" value="<%= (edit != null) ? edit.getNombre() : "" %>" placeholder="Nombre" required>

        <input type="text" name="tfPaterno" id="tfPaterno" value="<%= (edit != null) ? edit.getApPaterno() : "" %>" placeholder="Apellido Paterno" required>

        <input type="text" name="tfMaterno" id="tfMaterno" value="<%= (edit != null) ? edit.getApMaterno() : "" %>" placeholder="Apellido Materno" required>

        <input type="number" step="0.01" min="0" max="10" name="tfDDI" id="tfDDI" value="<%= (edit != null) ? edit.getDDI() : "" %>" placeholder="Desarrollo de Dispositivos Inteligentes" required>

        <input type="number" step="0.01" min="0" max="10" name="tfDWI" id="tfDWI" value="<%= (edit != null) ? edit.getDWI() : "" %>" placeholder="Desarrollo Web Integral" required>

        <input type="number" step="0.01" min="0" max="10" name="tfECBD" id="tfECBD" value="<%= (edit != null) ? edit.getECBD() : "" %>" placeholder="Extraccion del Conocimiento de Bases de Datos" required>

        <input type="submit" id="btnAccion" value="<%= (edit != null) ? "Modificar" : "Agregar" %>">

    </form>

</div>

<h2>Lista de Alumnos</h2>

<%
    String error = (String) request.getAttribute("error");
    if (error != null) {
%>
<div style="max-width:1100px;margin:0 auto 18px auto;background:#fdecea;border:1px solid #f5c2c0;color:#b3261e;padding:14px 18px;border-radius:8px;font-size:13.5px;">
    <strong>Error:</strong> <%= error %>
</div>
<%
    }
%>

<%= lista.mostrar() %>

<script src="index.js"></script>

</body>
</html>