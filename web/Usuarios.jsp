<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelos.Usuario"%>
<%@page import="modelos.Usuarios"%>
<%@page import="dao.DaoUsuario"%>

<%!
    DaoUsuario dao = new DaoUsuario();
    Usuario edit = null;
%>

<%
    edit = null;

    if (request.getAttribute("edit") != null) {
        edit = (Usuario) request.getAttribute("edit");
    }

    Usuarios lista = dao.listar();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Usuarios - BD CON SERVLETS</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="estilos.css">

</head>

<body>

<div id="form_registro">

    <div style="text-align:right;max-width:1100px;margin:0 auto 10px auto;">
        <a href="UsuarioServlet?accion=Logout">Cerrar sesión</a>
    </div>

    <h2><%= (edit != null) ? "Modificar usuario" : "Registro de usuario" %></h2>

    <form method="post" action="UsuarioServlet">

        <input type="hidden" name="accion" id="accion" value="<%= (edit != null) ? "Modificar" : "Registrar" %>">

        <input type="hidden" name="tfId" id="tfId" value="<%= (edit != null) ? edit.getIdUsuario() : "" %>">

        <input type="text" name="tfNombre" id="tfNombre" value="<%= (edit != null) ? edit.getNombre() : "" %>" placeholder="Nombre" required>

        <input type="text" name="tfPaterno" id="tfPaterno" value="<%= (edit != null) ? edit.getApPaterno() : "" %>" placeholder="Apellido Paterno" required>

        <input type="text" name="tfMaterno" id="tfMaterno" value="<%= (edit != null) ? edit.getApMaterno() : "" %>" placeholder="Apellido Materno" required>

        <input type="email" name="tfCorreo" id="tfCorreo" value="<%= (edit != null) ? edit.getCorreo() : "" %>" placeholder="Correo (usuario)" required>

        <% if (edit == null) { %>
        <input type="password" name="tfPassword" id="tfPassword" placeholder="Contraseña" required>
        <% } %>

        <input type="submit" id="btnAccion" value="<%= (edit != null) ? "Modificar" : "Registrar" %>">

    </form>

</div>

<h2>Lista de Usuarios</h2>

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
