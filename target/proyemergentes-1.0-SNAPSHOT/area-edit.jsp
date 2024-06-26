<%@page import="com.emergentes.entidades.Area"%>
<%
    Area are = (Area)request.getAttribute("area");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Area formulario</h1><!<!-- recoger datos -->
        <form action="AreaServlet" method="post">
            <label>Nombre:</label>
            <input type="hidden" name="id" value="<%= are.getId()%>">
            <input type="text" name="nombre" value="<%= are.getNombre() %>">

            <label>Descripcion</label>
            <input type="text" name="descripcion" value="<%= are.getDescripcion()%>">
            <input type="submit">
        </form>
    </body>
</html>
