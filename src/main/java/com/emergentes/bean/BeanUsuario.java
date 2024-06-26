//beans para gestionar el manejo de las diferentes operaciones crod
package com.emergentes.bean;

import com.emergentes.entidades.Usuario;
import com.emergentes.jpa.UsuarioJpaController;
import com.emergentes.jpa.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BeanUsuario {

    private EntityManagerFactory emf;
    private UsuarioJpaController jpaUsuario;  //ayuda a interactuar con el controlador

    //constructor y asociar la unidad de persistencia al gestor de entidades
    public BeanUsuario() {
        emf = Persistence.createEntityManagerFactory("trabajo120PU");
        jpaUsuario = new UsuarioJpaController(emf);    //inicializado el controlador con persistencia
    }

    //crear metodos que vamos a utilizar en nuestra aplicacion
    public List<Usuario> listarTodos() {      //recuperar todos los registros de la tabla
        return jpaUsuario.findUsuarioEntities();
    }

    public void insertar(Usuario u) {  //insertar registros de areas, crea NUEVO registro
        jpaUsuario.create(u);
    }

    public void editar(Usuario u) {  //editar un registro
        try {

            jpaUsuario.edit(u);
        } catch (Exception ex) {
            Logger.getLogger(BeanUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(Integer id) { //eliminar registro
        try {
            jpaUsuario.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BeanUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Usuario buscar(Integer id) { //para buscar cualquier registro
        return jpaUsuario.findUsuario(id);
    }
}
