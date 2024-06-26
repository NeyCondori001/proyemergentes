package com.emergentes.controller;

import com.emergentes.bean.BeanUsuario;
import com.emergentes.entidades.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UsuarioServlet", urlPatterns = {"/UsuarioServlet"})
public class UsuarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BeanUsuario daoUsuario = new BeanUsuario();
        Usuario u = new Usuario();
        int id;
        String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
        switch (action) {
            case "add":
                request.setAttribute("usuario", u);
                request.getRequestDispatcher("usuario-edit.jsp").forward(request, response);
                break;
            case "edit":
                id = Integer.parseInt(request.getParameter("id"));
                u = daoUsuario.buscar(id);
                request.setAttribute("usuario", u);
                request.getRequestDispatcher("usuario-edit.jsp").forward(request, response);
                break;
            case "dele":
                id = Integer.parseInt(request.getParameter("id"));
                daoUsuario.eliminar(id);
                response.sendRedirect("UsuarioServlet");
                break;
            case "view":
                List<Usuario> lista = daoUsuario.listarTodos();
                request.setAttribute("usuarios", lista);
                request.getRequestDispatcher("/usuarios.jsp").forward(request, response);

                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BeanUsuario daoUsuario = new BeanUsuario();
        
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String fechanac = request.getParameter("fechanac");
        int celular = Integer.parseInt(request.getParameter("celular"));
        char sexo = request.getParameter("sexo").charAt(0);
        String email = request.getParameter("email");

        Usuario u = new Usuario();
        
        u.setId(id);
        u.setNombre(nombre);
        u.setApellido(apellido);
        u.setFechanac(fechanac);
        u.setCelular(celular);
        u.setSexo(sexo);
        u.setEmail(email);

        if (id > 0) {
            daoUsuario.editar(u);
        } else {
            daoUsuario.insertar(u);
        }
        
        response.sendRedirect("UsuarioServlet");

    }
}
