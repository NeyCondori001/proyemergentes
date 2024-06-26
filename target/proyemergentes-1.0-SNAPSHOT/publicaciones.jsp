<%@page import="com.emergentes.entidades.Publicacion"%>
<%@page import="java.util.List"%>
<%
    List<Publicacion> pub = (List<Publicacion>)request.getAttribute("publicaciones");
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
            <a href="PublicacionServlet?action=add" >Nuevo</a>
        </p>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Titulo</th>
                <th>Descripcion</th>
                <th>Area</th>
                <th>Categoria</th>
                <th>Empresa</th>
                <th>Usuario</th>
                <th></th>
                <th></th>
                
            </tr>
            <%
                for(Publicacion item :pub){
            %>
            <tr>
                <td><%=item.getId() %></td>
                <td><%=item.getTitulo() %></td>
                <td><%=item.getDescripcion() %></td>
                <td><%=item.getIdArea().getNombre() %></td>
                <td><%=item.getIdCategoria().getNombre() %></td>
                <td><%=item.getIdEmpresa().getNombre() %></td>
                <td><%=item.getIdUsuario().getNombre() %></td><!<!-- aqui eliges la opcion de la tabla cual campo quieres -->
                <td><a href="PublicacionServlet?action=edit&id=<%=item.getId()%>">Editar</a></td>
                <td><a href="PublicacionServlet?action=dele&id=<%=item.getId()%>" onclick="return(confirm('Esta seguro de eliminar'))">Borrar</a></td>
            </tr>
            <%
                }
            %>
        </table>
    </body>
</html>
