<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - BD CON SERVLETS</title>

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="estilos.css">

</head>

<body>

<div id="form_registro">

    <h2>Iniciar sesión</h2>

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

    <form method="post" action="UsuarioServlet">

        <input type="hidden" name="accion" value="Login">

        <input type="email" name="tfCorreo" placeholder="Correo" required>

        <input type="password" name="tfPassword" placeholder="Contraseña" required>

        <input type="submit" value="Entrar">

    </form>

    <p style="text-align:center;margin-top:12px;">
        ¿No tienes cuenta? <a href="UsuarioServlet?accion=Listar">Regístrate aquí</a>
    </p>

</div>

</body>
</html>
