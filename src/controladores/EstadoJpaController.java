/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Conexion.AbstractFacade;
import controladores.exceptions.NonexistentEntityException;
import controladores.exceptions.PreexistingEntityException;
import entidades.Estado;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Anonymous
 */
public class EstadoJpaController implements Serializable {
Icon icono = new ImageIcon(getClass().getResource("/Imagenes/oki.png"));
    public EstadoJpaController() {
        this.emf =AbstractFacade.conexion();// Persistence.createEntityManagerFactory("ventasPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estado estado) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(estado);
            em.getTransaction().commit();
        JOptionPane.showMessageDialog(null, "¡¡¡::EXITO::!!!","",JOptionPane.INFORMATION_MESSAGE,icono);
        } catch (Exception ex) {
            if (findEstado(estado.getIdestado()) != null) {
                JOptionPane.showMessageDialog(null,"EL CODIGO DE ESTADO YA ESTA REGISTRADO","",JOptionPane.WARNING_MESSAGE);
                throw new PreexistingEntityException("Municipio " +estado + " already exists.", ex);
            }
        } 
         finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estado estado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            estado = em.merge(estado);
            Integer id=estado.getIdestado();
            if(findEstado(id)==null){
                JOptionPane.showMessageDialog(null,"NO EXISTEN DATOS","",JOptionPane.ERROR_MESSAGE);
            }else{
            estado = em.merge(estado);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "¡¡¡::EXITO::!!!","",JOptionPane.INFORMATION_MESSAGE,icono);
            }
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estado.getIdestado();
                if (findEstado(id) == null) {
                    throw new NonexistentEntityException("The estado with id " + id + " no longer exists.");
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
            Estado estado;
            try {
                estado = em.getReference(Estado.class, id);
                estado.getIdestado();
            } catch (EntityNotFoundException enfe) {
                JOptionPane.showMessageDialog(null,"NO EXISTEN DATOS","",JOptionPane.ERROR_MESSAGE);
                throw new NonexistentEntityException("The municipio with id " + id + " no longer exists.", enfe);
            }
            em.remove(estado);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null,"¡¡¡::EXITO::!!!","",JOptionPane.PLAIN_MESSAGE,icono);
          } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estado> findEstadoEntities() {
        return findEstadoEntities(true, -1, -1);
    }

    public List<Estado> findEstadoEntities(int maxResults, int firstResult) {
        return findEstadoEntities(false, maxResults, firstResult);
    }

    private List<Estado> findEstadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estado.class));
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

    public Estado findEstado(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estado> rt = cq.from(Estado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
