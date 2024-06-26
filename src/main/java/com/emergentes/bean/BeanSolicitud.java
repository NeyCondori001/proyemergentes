package com.emergentes.bean;

import com.emergentes.entidades.Solicitud;
import com.emergentes.jpa.SolicitudJpaController;
import com.emergentes.jpa.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BeanSolicitud {

    private EntityManagerFactory emf;
    private SolicitudJpaController jpaSolicitud;  //ayuda a interactuar con el controlador

    //constructor y asociar la unidad de persistencia al gestor de entidades
    public BeanSolicitud() {
        emf = Persistence.createEntityManagerFactory("trabajo120PU");
        jpaSolicitud = new SolicitudJpaController(emf);    //inicializado el controlador con persistencia
    }

    //crear metodos que vamos a utilizar en nuestra aplicacion
    public List<Solicitud> listarTodos() {      //recuperar todos los registros de la tabla
        return jpaSolicitud.findSolicitudEntities();
    }

    public void insertar(Solicitud s) {  //insertar registros de areas, crea NUEVO registro
        jpaSolicitud.create(s);
    }

    public void editar(Solicitud s) {  //editar un registro
        try {

            jpaSolicitud.edit(s);
        } catch (Exception ex) {
            Logger.getLogger(BeanSolicitud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(Integer id) { //eliminar registro
        try {
            jpaSolicitud.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BeanSolicitud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Solicitud buscar(Integer id) { //para buscar cualquier registro
        return jpaSolicitud.findSolicitud(id);
    }
}
