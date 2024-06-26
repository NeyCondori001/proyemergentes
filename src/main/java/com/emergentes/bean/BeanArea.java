/*
administra los controladores, donde vamos a hacer la implementacion de las principales operaciones de la aplicacion
 */
package com.emergentes.bean;

import com.emergentes.entidades.Area;
import com.emergentes.jpa.AreaJpaController;
import com.emergentes.jpa.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BeanArea {

    private EntityManagerFactory emf;
    private AreaJpaController jpaArea;  //ayuda a interactuar con el controlador

    //constructor y asociar la unidad de persistencia al gestor de entidades
    public BeanArea() {
        emf = Persistence.createEntityManagerFactory("trabajo120PU");
        jpaArea = new AreaJpaController(emf);    //inicializado el controlador con persistencia
    }

    //crear metodos que vamos a utilizar en nuestra aplicacion
    public List<Area> listarTodos() {      //recuperar todos los registros de la tabla
        return jpaArea.findAreaEntities();
    }

    public void insertar(Area a) {  //insertar registros de areas, crea NUEVO registro
        jpaArea.create(a);
    }

    public void editar(Area a) {  //editar un registro
        try {

            jpaArea.edit(a);
        } catch (Exception ex) {
            Logger.getLogger(BeanArea.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(Integer id) { //eliminar registro
        try {
            jpaArea.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BeanArea.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Area buscar(Integer id) { //para buscar cualquier registro
        return jpaArea.findArea(id);
    }

}
