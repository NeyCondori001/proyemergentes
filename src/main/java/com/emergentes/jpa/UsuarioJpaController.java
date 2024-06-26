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
import com.emergentes.entidades.Solicitud;
import java.util.ArrayList;
import java.util.List;
import com.emergentes.entidades.Publicacion;
import com.emergentes.entidades.Usuario;
import com.emergentes.jpa.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author HP VICTUS
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getSolicitudList() == null) {
            usuario.setSolicitudList(new ArrayList<Solicitud>());
        }
        if (usuario.getPublicacionList() == null) {
            usuario.setPublicacionList(new ArrayList<Publicacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Solicitud> attachedSolicitudList = new ArrayList<Solicitud>();
            for (Solicitud solicitudListSolicitudToAttach : usuario.getSolicitudList()) {
                solicitudListSolicitudToAttach = em.getReference(solicitudListSolicitudToAttach.getClass(), solicitudListSolicitudToAttach.getId());
                attachedSolicitudList.add(solicitudListSolicitudToAttach);
            }
            usuario.setSolicitudList(attachedSolicitudList);
            List<Publicacion> attachedPublicacionList = new ArrayList<Publicacion>();
            for (Publicacion publicacionListPublicacionToAttach : usuario.getPublicacionList()) {
                publicacionListPublicacionToAttach = em.getReference(publicacionListPublicacionToAttach.getClass(), publicacionListPublicacionToAttach.getId());
                attachedPublicacionList.add(publicacionListPublicacionToAttach);
            }
            usuario.setPublicacionList(attachedPublicacionList);
            em.persist(usuario);
            for (Solicitud solicitudListSolicitud : usuario.getSolicitudList()) {
                Usuario oldIdUsuariooOfSolicitudListSolicitud = solicitudListSolicitud.getIdUsuarioo();
                solicitudListSolicitud.setIdUsuarioo(usuario);
                solicitudListSolicitud = em.merge(solicitudListSolicitud);
                if (oldIdUsuariooOfSolicitudListSolicitud != null) {
                    oldIdUsuariooOfSolicitudListSolicitud.getSolicitudList().remove(solicitudListSolicitud);
                    oldIdUsuariooOfSolicitudListSolicitud = em.merge(oldIdUsuariooOfSolicitudListSolicitud);
                }
            }
            for (Publicacion publicacionListPublicacion : usuario.getPublicacionList()) {
                Usuario oldIdUsuarioOfPublicacionListPublicacion = publicacionListPublicacion.getIdUsuario();
                publicacionListPublicacion.setIdUsuario(usuario);
                publicacionListPublicacion = em.merge(publicacionListPublicacion);
                if (oldIdUsuarioOfPublicacionListPublicacion != null) {
                    oldIdUsuarioOfPublicacionListPublicacion.getPublicacionList().remove(publicacionListPublicacion);
                    oldIdUsuarioOfPublicacionListPublicacion = em.merge(oldIdUsuarioOfPublicacionListPublicacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getId());
            List<Solicitud> solicitudListOld = persistentUsuario.getSolicitudList();
            List<Solicitud> solicitudListNew = usuario.getSolicitudList();
            List<Publicacion> publicacionListOld = persistentUsuario.getPublicacionList();
            List<Publicacion> publicacionListNew = usuario.getPublicacionList();
            List<Solicitud> attachedSolicitudListNew = new ArrayList<Solicitud>();
            for (Solicitud solicitudListNewSolicitudToAttach : solicitudListNew) {
                solicitudListNewSolicitudToAttach = em.getReference(solicitudListNewSolicitudToAttach.getClass(), solicitudListNewSolicitudToAttach.getId());
                attachedSolicitudListNew.add(solicitudListNewSolicitudToAttach);
            }
            solicitudListNew = attachedSolicitudListNew;
            usuario.setSolicitudList(solicitudListNew);
            List<Publicacion> attachedPublicacionListNew = new ArrayList<Publicacion>();
            for (Publicacion publicacionListNewPublicacionToAttach : publicacionListNew) {
                publicacionListNewPublicacionToAttach = em.getReference(publicacionListNewPublicacionToAttach.getClass(), publicacionListNewPublicacionToAttach.getId());
                attachedPublicacionListNew.add(publicacionListNewPublicacionToAttach);
            }
            publicacionListNew = attachedPublicacionListNew;
            usuario.setPublicacionList(publicacionListNew);
            usuario = em.merge(usuario);
            for (Solicitud solicitudListOldSolicitud : solicitudListOld) {
                if (!solicitudListNew.contains(solicitudListOldSolicitud)) {
                    solicitudListOldSolicitud.setIdUsuarioo(null);
                    solicitudListOldSolicitud = em.merge(solicitudListOldSolicitud);
                }
            }
            for (Solicitud solicitudListNewSolicitud : solicitudListNew) {
                if (!solicitudListOld.contains(solicitudListNewSolicitud)) {
                    Usuario oldIdUsuariooOfSolicitudListNewSolicitud = solicitudListNewSolicitud.getIdUsuarioo();
                    solicitudListNewSolicitud.setIdUsuarioo(usuario);
                    solicitudListNewSolicitud = em.merge(solicitudListNewSolicitud);
                    if (oldIdUsuariooOfSolicitudListNewSolicitud != null && !oldIdUsuariooOfSolicitudListNewSolicitud.equals(usuario)) {
                        oldIdUsuariooOfSolicitudListNewSolicitud.getSolicitudList().remove(solicitudListNewSolicitud);
                        oldIdUsuariooOfSolicitudListNewSolicitud = em.merge(oldIdUsuariooOfSolicitudListNewSolicitud);
                    }
                }
            }
            for (Publicacion publicacionListOldPublicacion : publicacionListOld) {
                if (!publicacionListNew.contains(publicacionListOldPublicacion)) {
                    publicacionListOldPublicacion.setIdUsuario(null);
                    publicacionListOldPublicacion = em.merge(publicacionListOldPublicacion);
                }
            }
            for (Publicacion publicacionListNewPublicacion : publicacionListNew) {
                if (!publicacionListOld.contains(publicacionListNewPublicacion)) {
                    Usuario oldIdUsuarioOfPublicacionListNewPublicacion = publicacionListNewPublicacion.getIdUsuario();
                    publicacionListNewPublicacion.setIdUsuario(usuario);
                    publicacionListNewPublicacion = em.merge(publicacionListNewPublicacion);
                    if (oldIdUsuarioOfPublicacionListNewPublicacion != null && !oldIdUsuarioOfPublicacionListNewPublicacion.equals(usuario)) {
                        oldIdUsuarioOfPublicacionListNewPublicacion.getPublicacionList().remove(publicacionListNewPublicacion);
                        oldIdUsuarioOfPublicacionListNewPublicacion = em.merge(oldIdUsuarioOfPublicacionListNewPublicacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getId();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<Solicitud> solicitudList = usuario.getSolicitudList();
            for (Solicitud solicitudListSolicitud : solicitudList) {
                solicitudListSolicitud.setIdUsuarioo(null);
                solicitudListSolicitud = em.merge(solicitudListSolicitud);
            }
            List<Publicacion> publicacionList = usuario.getPublicacionList();
            for (Publicacion publicacionListPublicacion : publicacionList) {
                publicacionListPublicacion.setIdUsuario(null);
                publicacionListPublicacion = em.merge(publicacionListPublicacion);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
