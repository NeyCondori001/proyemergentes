package com.emergentes.bean;

import com.emergentes.entidades.Empresa;
import com.emergentes.jpa.EmpresaJpaController;
import com.emergentes.jpa.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class BeanEmpresa {
 private EntityManagerFactory emf;
    private EmpresaJpaController jpaEmpresa;  //ayuda a interactuar con el controlador

    //constructor y asociar la unidad de persistencia al gestor de entidades
    public BeanEmpresa() {
        emf = Persistence.createEntityManagerFactory("trabajo120PU");
        jpaEmpresa = new EmpresaJpaController(emf);    //inicializado el controlador con persistencia
    }

    //crear metodos que vamos a utilizar en nuestra aplicacion
    public List<Empresa> listarTodos() {      //recuperar todos los registros de la tabla
        return jpaEmpresa.findEmpresaEntities();
    }

    public void insertar(Empresa e) {  //insertar registros de areas, crea NUEVO registro
        jpaEmpresa.create(e);
    }

    public void editar(Empresa e) {  //editar un registro
        try {

            jpaEmpresa.edit(e);
        } catch (Exception ex) {
            Logger.getLogger(BeanEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void eliminar(Integer id) { //eliminar registro
        try {
            jpaEmpresa.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(BeanEmpresa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Empresa buscar(Integer id) { //para buscar cualquier registro
        return jpaEmpresa.findEmpresa(id);
    }   
}
