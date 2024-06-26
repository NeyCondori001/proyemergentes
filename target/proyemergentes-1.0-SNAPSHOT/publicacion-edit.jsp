<%@page import="java.util.List"%>
<%@page import="com.emergentes.entidades.Empresa"%>
<%@page import="com.emergentes.entidades.Categoria"%>
<%@page import="com.emergentes.entidades.Area"%>
<%@page import="com.emergentes.entidades.Usuario"%>
<%@page import="com.emergentes.entidades.Publicacion"%>
<%
    Publicacion pub = (Publicacion)request.getAttribute("publicacion");
    List<Area> areas = (List<Area>)request.getAttribute("areas");
    List<Categoria> cate = (List<Categoria>)request.getAttribute("categorias");
    List<Empresa> emp = (List<Empresa>)request.getAttribute("empresas");
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
       <h1>Publicacion formulario</h1><!<!-- recoger datos -->
        <form action="PublicacionServlet" method="post">
            
             <input type="hidden" name="id" value="<%=pub.getId()%>" >
            <table>
                <tr>
                    <td>Titulo</td>
                    <td><input type="text" name="titulo" value="<%=pub.getTitulo()%>" ></td>
                </tr>
                
                <tr>
                    <td>Descripcion</td>
                    <td><input type="text" name="descripcion" value="<%=pub.getDescripcion() %>" ></td>
                </tr>
                <tr>
                    <td>Area</td>
                    <td>
                        <select name="idArea">
                            <%
                                for (Area item :areas){
                            %>
                            <option value="<%=item.getId()%>"
                                    <%=(item.getId()== pub.getIdArea().getId())? "selected":""%>
                                    ><%=item.getNombre() %>
                            </option>
                            <%
                                }
                            %>
                        </select>
                    </td>
                </tr>
                
                <tr>
                    <td>Categoria</td>
                    <td>
                        <select name="idCategoria">
                            <%
                                for (Categoria item :cate){
                            %>
                            <option value="<%=item.getId()%>"
                                    <%=(item.getId()== pub.getIdCategoria().getId())? "selected":""%>
                                    ><%=item.getNombre() %>
                            </option>
                            <%
                                }
                            %>
                        </select>
                    </td>
                </tr>
                
                 <tr>
                    <td>Empresa</td>
                    <td>
                        <select name="idEmpresa">
                            <%
                                for (Empresa item : emp){
                            %>
                            <option value="<%=item.getId()%>"
                                    <%=(item.getId()== pub.getIdEmpresa().getId())? "selected":""%>
                                    ><%=item.getNombre() %>
                            </option>
                            <%
                                }
                            %>
                        </select>
                    </td>
                </tr>
                              
                <tr>
                    <td>Usuario</td>
                    <td>
                        <select name="idUsuarioo">
                            <%
                                for (Usuario item :usuarios){
                            %>
                            <option value="<%=item.getId()%>"
                                    <%=(item.getId()== pub.getIdUsuario().getId())? "selected":""%>
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
