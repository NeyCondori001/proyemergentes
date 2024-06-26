package com.emergentes.controller;

import com.emergentes.bean.BeanEmpresa;
import com.emergentes.entidades.Empresa;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EmpresaServlet", urlPatterns = {"/EmpresaServlet"})
public class EmpresaServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id;
        BeanEmpresa daoEmpresa = new BeanEmpresa();
        Empresa e = new Empresa();

        String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
        switch (action) {
            case "add":
                request.setAttribute("empresa", e);
                request.getRequestDispatcher("empresa-edit.jsp").forward(request, response);//editar el empresa
                break;
            case "edit":
                id = Integer.parseInt(request.getParameter("id"));
                e = daoEmpresa.buscar(id);
                request.setAttribute("empresa", e);
                request.getRequestDispatcher("empresa-edit.jsp").forward(request, response);
                break;
            case "dele":
                id = Integer.parseInt(request.getParameter("id"));
                daoEmpresa.eliminar(id);
                response.sendRedirect("EmpresaServlet");
                break;
            case "view":
                //listado de todas las EMPRESAS
                List<Empresa> lista = daoEmpresa.listarTodos();
                request.setAttribute("empresas", lista);
                request.getRequestDispatcher("empresas.jsp").forward(request, response);
                break;
        }
    }
  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BeanEmpresa daoEmpresa = new BeanEmpresa();  //permite hacer las operaciones

        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");

        Empresa e = new Empresa();
        e.setId(id);
        e.setNombre(nombre);

        if (id > 0) {
            daoEmpresa.editar(e);
        } else {
            daoEmpresa.insertar(e);
        }
        response.sendRedirect("EmpresaServlet");
    }

}
