package com.emergentes.bean;

import com.emergentes.entidades.Publicacion;
import com.emergentes.jpa.PublicacionJpaController;
import com.emergentes.jpa.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BeanPublicacion {
    private EntityManagerFactory emf;
    private PublicacionJpaController jpaPublicacion;  //ayuda a interactuar con el controlador

    //constructor y asociar la unidad de persistencia al gestor de entidades
    public BeanPublicacion() {
        emf = Persistence.createEntityManagerFactory("trabajo120PU");
        jpaPublicacion = new PublicacionJpaController(emf);    //inicializado el controlador con persistencia
    }

    //crear metodos que vamos a utilizar en nuestra aplicacion
    public List<Publicacion> listarTodos() {      //recuperar todos los registros de la tabla
        return jpaPublicacion.findPublicacionEntities();
    }

    public void insertar(Publicacion p) {  //insertar registros de areas, crea NUEVO registro
        jpaPublicacion.create(p);
    }

    public void editar(Publicacion p) {  //editar un registro
        try {

            jpaPublicacion.edit(p);
        } catch (Exception ex) {
            Logger.getLogger(BeanPublicacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(Integer id) { //eliminar registro
        try {
            jpaPublicacion.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BeanPublicacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Publicacion buscar(Integer id) { //para buscar cualquier registro
        return jpaPublicacion.findPublicacion(id);
    }
}
