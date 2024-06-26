<%@page import="java.util.List"%>
<%@page import="com.emergentes.entidades.Area"%>
<%
List<Area> areas = (List<Area>)request.getAttribute("areas");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Listado de registros</h1>
        <p><a href="AreaServlet?action=add">Nuevo</a></p>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Nombre</th>
                <th>Descripcion</th>
                <td></td>
                <td></td>
            </tr>
            <%
            for(Area item: areas){
            %>
            <tr>
                <td><%=item.getId() %></td>
                <td><%=item.getNombre() %></td>
                <td><%=item.getDescripcion() %></td>
                <td><a href="AreaServlet?action=edit&id=<%=item.getId()%>">Editar</a></td>
                <td><a href="AreaServlet?action=dele&id=<%=item.getId()%>" onclick="return(confirm('Esta seguro de eliminar'))">Borrar</a></td>
                
            </tr>
            <%
                }
            %>
            
        </table>
    </body>
</html>
