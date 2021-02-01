/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Conexion.AbstractFacade;
import controladores.exceptions.NonexistentEntityException;
import controladores.exceptions.PreexistingEntityException;
import entidades.Tipov;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Anonymous
 */
public class TipovJpaController implements Serializable {

    public TipovJpaController() {
        this.emf = AbstractFacade.conexion();//Persistence.createEntityManagerFactory("ventasPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Tipov tipov) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(tipov);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findTipov(tipov.getIdtipov()) != null) {
                throw new PreexistingEntityException("Tipov " + tipov + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Tipov tipov) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            tipov = em.merge(tipov);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipov.getIdtipov();
                if (findTipov(id) == null) {
                    throw new NonexistentEntityException("The tipov with id " + id + " no longer exists.");
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
            Tipov tipov;
            try {
                tipov = em.getReference(Tipov.class, id);
                tipov.getIdtipov();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipov with id " + id + " no longer exists.", enfe);
            }
            em.remove(tipov);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Tipov> findTipovEntities() {
        return findTipovEntities(true, -1, -1);
    }

    public List<Tipov> findTipovEntities(int maxResults, int firstResult) {
        return findTipovEntities(false, maxResults, firstResult);
    }

    private List<Tipov> findTipovEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tipov.class));
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

    public Tipov findTipov(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tipov.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipovCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Tipov> rt = cq.from(Tipov.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public void cerrar(){
        emf.close();
    }
}
