/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Conexion.AbstractFacade;
import controladores.exceptions.NonexistentEntityException;
import controladores.exceptions.PreexistingEntityException;
import entidades.Unidadesm;
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
public class UnidadesmJpaController implements Serializable {
Icon icono = new ImageIcon(getClass().getResource("/Imagenes/oki.png"));
    public UnidadesmJpaController() {
        this.emf =  AbstractFacade.conexion();//Persistence.createEntityManagerFactory("ventasPU");;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Unidadesm unidadesm) throws PreexistingEntityException, Exception {
        EntityManager em = null;
          try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(unidadesm);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "¡¡¡::EXITO::!!!","",JOptionPane.INFORMATION_MESSAGE,icono);
        } catch (Exception ex) {
            if (findUnidadesm(unidadesm.getIdunidad()) != null) {
                JOptionPane.showMessageDialog(null,"EL CODIGO DE UNIDAD YA ESTA REGISTRADO","",JOptionPane.WARNING_MESSAGE);
                throw new PreexistingEntityException("Unidad " +unidadesm + " already exists.", ex);
            }
        
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Unidadesm unidadesm) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try{
         em = getEntityManager();
            em.getTransaction().begin();
            unidadesm = em.merge(unidadesm);
            Integer id=unidadesm.getIdunidad();
            if(findUnidadesm(id)==null){
                JOptionPane.showMessageDialog(null,"NO EXISTEN DATOS","",JOptionPane.ERROR_MESSAGE);
            }else{
            unidadesm = em.merge(unidadesm);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "¡¡¡::EXITO::!!!","",JOptionPane.INFORMATION_MESSAGE,icono);
            }
        }
       finally {
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
            Unidadesm unidadesm;
            try {
                unidadesm = em.getReference(Unidadesm.class, id);
                unidadesm.getIdunidad();
            } catch (EntityNotFoundException enfe) {
                JOptionPane.showMessageDialog(null,"NO EXISTEN DATOS","",JOptionPane.ERROR_MESSAGE);
                throw new NonexistentEntityException("The municipio with id " + id + " no longer exists.", enfe);
            }
            em.remove(unidadesm);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null,"¡¡¡::EXITO::!!!","",JOptionPane.PLAIN_MESSAGE,icono);
          } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Unidadesm> findUnidadesmEntities() {
        return findUnidadesmEntities(true, -1, -1);
    }

    public List<Unidadesm> findUnidadesmEntities(int maxResults, int firstResult) {
        return findUnidadesmEntities(false, maxResults, firstResult);
    }

    private List<Unidadesm> findUnidadesmEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Unidadesm.class));
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

    public Unidadesm findUnidadesm(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Unidadesm.class, id);
        } finally {
            em.close();
        }
    }

    public int getUnidadesmCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Unidadesm> rt = cq.from(Unidadesm.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
