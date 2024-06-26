package com.emergentes.controller;

import com.emergentes.bean.BeanSolicitud;
import com.emergentes.bean.BeanUsuario;
import com.emergentes.entidades.Solicitud;
import com.emergentes.entidades.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SolicitudServlet", urlPatterns = {"/SolicitudServlet"})
public class SolicitudServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id;
        BeanSolicitud daoSolicitud = new BeanSolicitud();

        BeanUsuario daoUsuario = new BeanUsuario();

        Solicitud s = new Solicitud();
        List<Usuario> lista;

        String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
        switch (action) {
            case "add":
                lista = daoUsuario.listarTodos();
                request.setAttribute("usuarios", lista);

                request.setAttribute("solicitud", s);
                request.getRequestDispatcher("solicitud-edit.jsp").forward(request, response);//editar el solicitud
                break;
            case "edit":
                id = Integer.parseInt(request.getParameter("id"));
                s = daoSolicitud.buscar(id);

                lista = daoUsuario.listarTodos();
                request.setAttribute("usuarios", lista);

                request.setAttribute("solicitud", s);
                request.getRequestDispatcher("solicitud-edit.jsp").forward(request, response);
                break;
            case "dele":
                id = Integer.parseInt(request.getParameter("id"));
                daoSolicitud.eliminar(id);
                response.sendRedirect("SolicitudServlet");
                break;
            case "view":
                //listado de todas las SOLICITUDES
                List<Solicitud> solislist = daoSolicitud.listarTodos();
                request.setAttribute("solicitudes", solislist);
                request.getRequestDispatcher("solicitudes.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BeanSolicitud daoSolicitud = new BeanSolicitud();  //permite hacer las operaciones
        BeanUsuario daoUsuario = new BeanUsuario();
               
        int id = Integer.parseInt(request.getParameter("id"));
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        int idUsuarioo = Integer.parseInt(request.getParameter("idUsuarioo"));
        
        Usuario usu = daoUsuario.buscar(idUsuarioo);

        Solicitud s = new Solicitud();
        s.setId(id);
        s.setTitulo(titulo);
        s.setDescripcion(descripcion);
        s.setIdUsuarioo(usu);
        
        if (id > 0) {
            daoSolicitud.editar(s);
        } else {
            daoSolicitud.insertar(s);
        }
        response.sendRedirect("SolicitudServlet");
    }

}
