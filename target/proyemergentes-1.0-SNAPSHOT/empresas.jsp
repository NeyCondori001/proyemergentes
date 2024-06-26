<%@page import="com.emergentes.entidades.Empresa"%>
<%@page import="java.util.List"%>
<%
List<Empresa> empresas = (List<Empresa>)request.getAttribute("empresas");
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
        <p><a href="EmpresaServlet?action=add">Nuevo</a></p>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Nombre</th>
                <td></td>
                <td></td>
            </tr>
            <%
            for(Empresa item: empresas){
            %>
            <tr>
                <td><%=item.getId() %></td>
                <td><%=item.getNombre() %></td>
                <td><a href="EmpresaServlet?action=edit&id=<%=item.getId()%>">Editar</a></td>
                <td><a href="EmpresaServlet?action=dele&id=<%=item.getId()%>" onclick="return(confirm('Esta seguro de eliminar'))">Borrar</a></td>
                
            </tr>
            <%
                }
            %>
            
        </table>
    </body>
</html>
