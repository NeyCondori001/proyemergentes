<%@page import="com.emergentes.entidades.Empresa"%>
<%
    Empresa empre = (Empresa)request.getAttribute("empresa");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <h1>Empresa formulario</h1><!<!-- recoger datos -->
        <form action="EmpresaServlet" method="post">
            <label>Nombre:</label>
            <input type="hidden" name="id" value="<%= empre.getId()%>">
            <input type="text" name="nombre" value="<%= empre.getNombre() %>">

            <input type="submit">
        </form>
    </body>
</html>
