<%@page import="com.emergentes.entidades.Usuario"%>
<%@page import="java.util.List"%>
<%@page import="com.emergentes.entidades.Solicitud"%>
<%
    Solicitud soli = (Solicitud)request.getAttribute("solicitud");
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
       <h1>Solicitud formulario</h1><!<!-- recoger datos -->
        <form action="SolicitudServlet" method="post">
            
             <input type="hidden" name="id" value="<%=soli.getId()%>" >
            <table>
                <tr>
                    <td>Titulo</td>
                    <td><input type="text" name="titulo" value="<%=soli.getTitulo()%>" ></td>
                </tr>
                
                <tr>
                    <td>Descripcion</td>
                    <td><input type="text" name="descripcion" value="<%=soli.getDescripcion() %>" ></td>
                </tr>
                              
                <tr>
                    <td>Usuario</td>
                    <td>
                        <select name="idUsuarioo">
                            <%
                                for (Usuario item :usuarios){
                            %>
                            <option value="<%=item.getId()%>"
                                    <%=(item.getId()== soli.getIdUsuarioo().getId())? "selected":""%>
                                    ><%=item.getNombre() %>
                            </option>
                            <%
                                }
                            %>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" ></td>
                </tr>
            </table>
        </form>
    </body>
</html>
