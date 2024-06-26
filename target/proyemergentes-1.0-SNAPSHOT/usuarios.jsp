<%@page import="com.emergentes.entidades.Usuario"%>
<%@page import="java.util.List"%>
<%
List<Usuario> usuarios = (List<Usuario>)request.getAttribute("usuarios");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Listado de registros - USUARIOS</h1>
        <p><a href="UsuarioServlet?action=add">Nuevo</a></p>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Fecha Nacimiento</th>
                <th>Celular</th>
                <th>Sexo</th>
                <th>Email</th>
                <td></td>
                <td></td>
            </tr>
            <%
            for(Usuario item: usuarios){
            %>
            <tr>
                <td><%=item.getId() %></td>
                <td><%=item.getNombre() %></td>
                <td><%=item.getApellido() %></td>
                <td><%=item.getFechanac() %></td>
                <td><%=item.getCelular() %></td>
                <td><%=item.getSexo() %></td>
                <td><%=item.getEmail() %></td>
                <td><a href="UsuarioServlet?action=edit&id=<%=item.getId()%>">Editar</a></td>
                <td><a href="UsuarioServlet?action=dele&id=<%=item.getId()%>" onclick="return(confirm('Esta seguro de eliminar'))">Borrar</a></td>
                
            </tr>
            <%
                }
            %>
            
        </table>
    </body>
</html>
