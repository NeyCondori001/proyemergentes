<%@page import="com.emergentes.entidades.Usuario"%>
<%
    Usuario usu = (Usuario)request.getAttribute("usuario");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>USUARIO formulario</h1><!<!-- recoger datos -->
        <form action="UsuarioServlet" method="post">
            <label>Nombre:</label>
            <input type="hidden" name="id" value="<%= usu.getId()%>">
            <input type="text" name="nombre" value="<%= usu.getNombre() %>">

            <label>Apellidos</label>
            <input type="text" name="apellido" value="<%= usu.getApellido() %>">
            <label>Fecha Nacimiento</label>
            <input type="text" name="fechanac" value="<%= usu.getFechanac() %>">
            <label>Celular</label>
            <input type="number" name="celular" value="<%= usu.getCelular() %>">
            <label>Sexo</label>
            <input type="text" name="sexo" value="<%= usu.getSexo() %>">
            <label>Email</label>
            <input type="email" name="email" value="<%= usu.getEmail() %>">
            <input type="submit">
        </form>
    </body>
</html>
