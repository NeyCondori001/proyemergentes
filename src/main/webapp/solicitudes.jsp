<%@page import="com.emergentes.entidades.Solicitud"%>
<%@page import="java.util.List"%>
<%
    List<Solicitud> sol = (List<Solicitud>)request.getAttribute("solicitudes");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Listado - Solicitudes</h1>
        <p>
            <a href="SolicitudServlet?action=add" >Nuevo</a>
        </p>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Titulo</th>
                <th>Descripcion</th>
                <th>Usuario</th>
                <th></th>
                <th></th>
                
            </tr>
            <%
                for(Solicitud item :sol){
            %>
            <tr>
                <td><%=item.getId() %></td>
                <td><%=item.getTitulo() %></td>
                <td><%=item.getDescripcion() %></td>
                <td><%=item.getIdUsuarioo().getNombre() %></td><!<!-- aqui eliges la opcion de la tabla cual campo quieres -->
                <td><a href="SolicitudServlet?action=edit&id=<%=item.getId()%>">Editar</a></td>
                <td><a href="SolicitudServlet?action=dele&id=<%=item.getId()%>" onclick="return(confirm('Esta seguro de eliminar'))">Borrar</a></td>
            </tr>
            <%
                }
            %>
        </table>
    </body>
</html>
