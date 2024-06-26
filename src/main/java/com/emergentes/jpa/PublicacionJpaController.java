/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.emergentes.jpa;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.emergentes.entidades.Area;
import com.emergentes.entidades.Categoria;
import com.emergentes.entidades.Empresa;
import com.emergentes.entidades.Publicacion;
import com.emergentes.entidades.Usuario;
import com.emergentes.jpa.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author HP VICTUS
 */
public class PublicacionJpaController implements Serializable {

    public PublicacionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Publicacion publicacion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Area idArea = publicacion.getIdArea();
            if (idArea != null) {
                idArea = em.getReference(idArea.getClass(), idArea.getId());
                publicacion.setIdArea(idArea);
            }
            Categoria idCategoria = publicacion.getIdCategoria();
            if (idCategoria != null) {
                idCategoria = em.getReference(idCategoria.getClass(), idCategoria.getId());
                publicacion.setIdCategoria(idCategoria);
            }
            Empresa idEmpresa = publicacion.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa = em.getReference(idEmpresa.getClass(), idEmpresa.getId());
                publicacion.setIdEmpresa(idEmpresa);
            }
            Usuario idUsuario = publicacion.getIdUsuario();
            if (idUsuario != null) {
                idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getId());
                publicacion.setIdUsuario(idUsuario);
            }
            em.persist(publicacion);
            if (idArea != null) {
                idArea.getPublicacionList().add(publicacion);
                idArea = em.merge(idArea);
            }
            if (idCategoria != null) {
                idCategoria.getPublicacionList().add(publicacion);
                idCategoria = em.merge(idCategoria);
            }
            if (idEmpresa != null) {
                idEmpresa.getPublicacionList().add(publicacion);
                idEmpresa = em.merge(idEmpresa);
            }
            if (idUsuario != null) {
                idUsuario.getPublicacionList().add(publicacion);
                idUsuario = em.merge(idUsuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Publicacion publicacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Publicacion persistentPublicacion = em.find(Publicacion.class, publicacion.getId());
            Area idAreaOld = persistentPublicacion.getIdArea();
            Area idAreaNew = publicacion.getIdArea();
            Categoria idCategoriaOld = persistentPublicacion.getIdCategoria();
            Categoria idCategoriaNew = publicacion.getIdCategoria();
            Empresa idEmpresaOld = persistentPublicacion.getIdEmpresa();
            Empresa idEmpresaNew = publicacion.getIdEmpresa();
            Usuario idUsuarioOld = persistentPublicacion.getIdUsuario();
            Usuario idUsuarioNew = publicacion.getIdUsuario();
            if (idAreaNew != null) {
                idAreaNew = em.getReference(idAreaNew.getClass(), idAreaNew.getId());
                publicacion.setIdArea(idAreaNew);
            }
            if (idCategoriaNew != null) {
                idCategoriaNew = em.getReference(idCategoriaNew.getClass(), idCategoriaNew.getId());
                publicacion.setIdCategoria(idCategoriaNew);
            }
            if (idEmpresaNew != null) {
                idEmpresaNew = em.getReference(idEmpresaNew.getClass(), idEmpresaNew.getId());
                publicacion.setIdEmpresa(idEmpresaNew);
            }
            if (idUsuarioNew != null) {
                idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getId());
                publicacion.setIdUsuario(idUsuarioNew);
            }
            publicacion = em.merge(publicacion);
            if (idAreaOld != null && !idAreaOld.equals(idAreaNew)) {
                idAreaOld.getPublicacionList().remove(publicacion);
                idAreaOld = em.merge(idAreaOld);
            }
            if (idAreaNew != null && !idAreaNew.equals(idAreaOld)) {
                idAreaNew.getPublicacionList().add(publicacion);
                idAreaNew = em.merge(idAreaNew);
            }
            if (idCategoriaOld != null && !idCategoriaOld.equals(idCategoriaNew)) {
                idCategoriaOld.getPublicacionList().remove(publicacion);
                idCategoriaOld = em.merge(idCategoriaOld);
            }
            if (idCategoriaNew != null && !idCategoriaNew.equals(idCategoriaOld)) {
                idCategoriaNew.getPublicacionList().add(publicacion);
                idCategoriaNew = em.merge(idCategoriaNew);
            }
            if (idEmpresaOld != null && !idEmpresaOld.equals(idEmpresaNew)) {
                idEmpresaOld.getPublicacionList().remove(publicacion);
                idEmpresaOld = em.merge(idEmpresaOld);
            }
            if (idEmpresaNew != null && !idEmpresaNew.equals(idEmpresaOld)) {
                idEmpresaNew.getPublicacionList().add(publicacion);
                idEmpresaNew = em.merge(idEmpresaNew);
            }
            if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
                idUsuarioOld.getPublicacionList().remove(publicacion);
                idUsuarioOld = em.merge(idUsuarioOld);
            }
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                idUsuarioNew.getPublicacionList().add(publicacion);
                idUsuarioNew = em.merge(idUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = publicacion.getId();
                if (findPublicacion(id) == null) {
                    throw new NonexistentEntityException("The publicacion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Publicacion publicacion;
            try {
                publicacion = em.getReference(Publicacion.class, id);
                publicacion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The publicacion with id " + id + " no longer exists.", enfe);
            }
            Area idArea = publicacion.getIdArea();
            if (idArea != null) {
                idArea.getPublicacionList().remove(publicacion);
                idArea = em.merge(idArea);
            }
            Categoria idCategoria = publicacion.getIdCategoria();
            if (idCategoria != null) {
                idCategoria.getPublicacionList().remove(publicacion);
                idCategoria = em.merge(idCategoria);
            }
            Empresa idEmpresa = publicacion.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa.getPublicacionList().remove(publicacion);
                idEmpresa = em.merge(idEmpresa);
            }
            Usuario idUsuario = publicacion.getIdUsuario();
            if (idUsuario != null) {
                idUsuario.getPublicacionList().remove(publicacion);
                idUsuario = em.merge(idUsuario);
            }
            em.remove(publicacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Publicacion> findPublicacionEntities() {
        return findPublicacionEntities(true, -1, -1);
    }

    public List<Publicacion> findPublicacionEntities(int maxResults, int firstResult) {
        return findPublicacionEntities(false, maxResults, firstResult);
    }

    private List<Publicacion> findPublicacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Publicacion.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Publicacion findPublicacion(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Publicacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getPublicacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Publicacion> rt = cq.from(Publicacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
