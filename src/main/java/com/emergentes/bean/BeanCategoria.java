package com.emergentes.bean;

import com.emergentes.entidades.Categoria;
import com.emergentes.jpa.CategoriaJpaController;
import com.emergentes.jpa.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BeanCategoria {

    private EntityManagerFactory emf;
    private CategoriaJpaController jpaCategoria;  //ayuda a interactuar con el controlador

    //constructor y asociar la unidad de persistencia al gestor de entidades
    public BeanCategoria() {
        emf = Persistence.createEntityManagerFactory("trabajo120PU");
        jpaCategoria = new CategoriaJpaController(emf);    //inicializado el controlador con persistencia
    }

    //crear metodos que vamos a utilizar en nuestra aplicacion
    public List<Categoria> listarTodos() {      //recuperar todos los registros de la tabla
        return jpaCategoria.findCategoriaEntities();
    }

    public void insertar(Categoria c) {  //insertar registros de areas, crea NUEVO registro
        jpaCategoria.create(c);
    }

    public void editar(Categoria c) {  //editar un registro
        try {

            jpaCategoria.edit(c);
        } catch (Exception ex) {
            Logger.getLogger(BeanCategoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(Integer id) { //eliminar registro
        try {
            jpaCategoria.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BeanCategoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Categoria buscar(Integer id) { //para buscar cualquier registro
        return jpaCategoria.findCategoria(id);
    }
}
