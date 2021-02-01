/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Conexion.AbstractFacade;
import controladores.exceptions.NonexistentEntityException;
import controladores.exceptions.PreexistingEntityException;
import entidades.Empvendedor;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Anonymous
 */
public class EmpvendedorJpaController implements Serializable {

    public EmpvendedorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = AbstractFacade.conexion();//

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empvendedor empvendedor) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(empvendedor);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEmpvendedor(empvendedor.getIdvendedor()) != null) {
                throw new PreexistingEntityException("Empvendedor " + empvendedor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empvendedor empvendedor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            empvendedor = em.merge(empvendedor);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = empvendedor.getIdvendedor();
                if (findEmpvendedor(id) == null) {
                    throw new NonexistentEntityException("The empvendedor with id " + id + " no longer exists.");
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
            Empvendedor empvendedor;
            try {
                empvendedor = em.getReference(Empvendedor.class, id);
                empvendedor.getIdvendedor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empvendedor with id " + id + " no longer exists.", enfe);
            }
            em.remove(empvendedor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empvendedor> findEmpvendedorEntities() {
        return findEmpvendedorEntities(true, -1, -1);
    }

    public List<Empvendedor> findEmpvendedorEntities(int maxResults, int firstResult) {
        return findEmpvendedorEntities(false, maxResults, firstResult);
    }

    private List<Empvendedor> findEmpvendedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empvendedor.class));
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

    public Empvendedor findEmpvendedor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empvendedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpvendedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empvendedor> rt = cq.from(Empvendedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
