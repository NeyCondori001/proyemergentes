/*
Controlador
 */
package com.emergentes.controller;

import com.emergentes.bean.BeanArea;
import com.emergentes.entidades.Area;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AreaServlet", urlPatterns = {"/AreaServlet"})
public class AreaServlet extends HttpServlet {

    //funcionalidad para las operaciones, adicion, edicion eliminar y mostrar
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id;
        BeanArea daoArea = new BeanArea();
        Area a = new Area();

        String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
        switch (action) {
            case "add":
                request.setAttribute("area", a);
                request.getRequestDispatcher("area-edit.jsp").forward(request, response);//editar el are
                break;
            case "edit":
                id = Integer.parseInt(request.getParameter("id"));
                a = daoArea.buscar(id);
                request.setAttribute("area", a);
                request.getRequestDispatcher("area-edit.jsp").forward(request, response);
                break;
            case "dele":
                id = Integer.parseInt(request.getParameter("id"));
                daoArea.eliminar(id);
                response.sendRedirect("AreaServlet");
                break;
            case "view":
                //listado de todas las AREAS
                List<Area> lista = daoArea.listarTodos();
                request.setAttribute("areas", lista);
                request.getRequestDispatcher("areas.jsp").forward(request, response);
                break;
        }
        //recorrido de la lista
        /*for( Area a : lista){
            System.out.println("id: "+a.getId());
            System.out.println("nomre: "+a.getNombre());
            System.out.println("descripcion: "+a.getDescripcion());
        }*/

    }

    //cuando ya tengamos todos los datos en el formulario, vamos a implementar
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BeanArea daoArea = new BeanArea();  //permite hacer las operaciones

        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");

        Area a = new Area();
        a.setId(id);
        a.setNombre(nombre);
        a.setDescripcion(descripcion);

        if (id > 0) {
            daoArea.editar(a);
        } else {
            daoArea.insertar(a);
        }
        response.sendRedirect("AreaServlet");
    }

}
