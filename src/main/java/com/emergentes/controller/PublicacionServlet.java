package com.emergentes.controller;

import com.emergentes.bean.BeanArea;
import com.emergentes.bean.BeanCategoria;
import com.emergentes.bean.BeanEmpresa;
import com.emergentes.bean.BeanPublicacion;
import com.emergentes.bean.BeanUsuario;
import com.emergentes.entidades.Area;
import com.emergentes.entidades.Categoria;
import com.emergentes.entidades.Empresa;
import com.emergentes.entidades.Usuario;
import com.emergentes.entidades.Publicacion;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PublicacionServlet", urlPatterns = {"/PublicacionServlet"})
public class PublicacionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id;
        BeanPublicacion daoPublicacion = new BeanPublicacion();

        //otras tablas
        BeanUsuario daoUsuario = new BeanUsuario();
        BeanEmpresa daoEmpresa = new BeanEmpresa();
        BeanArea daoArea = new BeanArea();
        BeanCategoria daoCategoria = new BeanCategoria();
        
        Publicacion s = new Publicacion();
        
        List<Area> listaarea;
        List<Categoria> listacate;
        
        List<Empresa> listaem;
        List<Usuario> lista;

        String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
        switch (action) {
            case "add":
                
                listaarea = daoArea.listarTodos();
                request.setAttribute("areas", listaarea);
                
                listacate = daoCategoria.listarTodos();
                request.setAttribute("categorias", listacate);
                
                listaem = daoEmpresa.listarTodos();
                request.setAttribute("empresas", listaem);
                
                lista = daoUsuario.listarTodos();
                request.setAttribute("usuarios", lista);

                request.setAttribute("publicacion", s);
                request.getRequestDispatcher("publicacion-edit.jsp").forward(request, response);//editar el solicitud
                break;
            case "edit":
                id = Integer.parseInt(request.getParameter("id"));
                s = daoPublicacion.buscar(id);
                listaarea = daoArea.listarTodos();
                request.setAttribute("areas", listaarea);
                
                listacate = daoCategoria.listarTodos();
                request.setAttribute("categorias", listacate);
     
                listaem = daoEmpresa.listarTodos();
                request.setAttribute("empresas", listaem);
                
                lista = daoUsuario.listarTodos();
                request.setAttribute("usuarios", lista);
                

                request.setAttribute("publicacion", s);
                request.getRequestDispatcher("publicacion-edit.jsp").forward(request, response);
                break;
                
            case "dele":
                id = Integer.parseInt(request.getParameter("id"));
                daoPublicacion.eliminar(id);
                response.sendRedirect("PublicacionServlet");
                break;
            case "view":
                //listado de todas las SOLICITUDES
                List<Publicacion> solislist = daoPublicacion.listarTodos();
                request.setAttribute("publicaciones", solislist);
                request.getRequestDispatcher("publicaciones.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BeanPublicacion daoPublicacion = new BeanPublicacion();  //permite hacer las operaciones
        BeanUsuario daoUsuario = new BeanUsuario();
        BeanEmpresa daoEmpresa = new BeanEmpresa();
        BeanArea daoArea = new BeanArea();
        BeanCategoria daoCategoria = new BeanCategoria();

        int id = Integer.parseInt(request.getParameter("id"));
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        int idArea = Integer.parseInt(request.getParameter("idArea"));
        int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
        int idEmpresa = Integer.parseInt(request.getParameter("idEmpresa"));
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));

        Area are = daoArea.buscar(idUsuario);
        Categoria cate = daoCategoria.buscar(idCategoria);
        Empresa em = daoEmpresa.buscar(idEmpresa);
        Usuario us = daoUsuario.buscar(idUsuario);
        Publicacion s = new Publicacion();
        
        s.setId(id);
        s.setTitulo(titulo);
        s.setDescripcion(descripcion);
        s.setIdArea(are);
        s.setIdCategoria(cate);
        s.setIdEmpresa(em);
        s.setIdUsuario(us);

        if (id > 0) {
            daoPublicacion.editar(s);
        } else {
            daoPublicacion.insertar(s);
        }
        response.sendRedirect("PublicacionServlet");
    }
}
