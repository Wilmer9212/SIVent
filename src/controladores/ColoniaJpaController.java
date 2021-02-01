/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Conexion.AbstractFacade;
import controladores.exceptions.NonexistentEntityException;
import controladores.exceptions.PreexistingEntityException;
import entidades.Colonia;
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
public class ColoniaJpaController implements Serializable {
Icon icono = new ImageIcon(getClass().getResource("/Imagenes/oki.png"));
    public ColoniaJpaController() {
        this.emf = AbstractFacade.conexion();//Persistence.createEntityManagerFactory("ventasPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Colonia colonia) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(colonia);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "¡¡¡::EXITO::!!!","",JOptionPane.INFORMATION_MESSAGE,icono);
        } catch (Exception ex) {
            if (findColonia(colonia.getIdcolonia()) != null) {
                JOptionPane.showMessageDialog(null,"EL CODIGO DE LOCALIDAD YA ESTA REGISTRADO","",JOptionPane.WARNING_MESSAGE);
                throw new PreexistingEntityException("Colonia " + colonia + " already exists.", ex);
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Colonia colonia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            colonia = em.merge(colonia);
            Integer id=colonia.getIdcolonia();
            if(findColonia(id)==null){
                JOptionPane.showMessageDialog(null,"NO EXISTEN DATOS","",JOptionPane.ERROR_MESSAGE);
            }else{
            colonia = em.merge(colonia);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "¡¡¡::EXITO::!!!","",JOptionPane.INFORMATION_MESSAGE,icono);
            }
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = colonia.getIdcolonia();
                if (findColonia(id) == null) {
                    throw new NonexistentEntityException("The colonia with id " + id + " no longer exists.");
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
            Colonia colonia;
               try {
                colonia = em.getReference(Colonia.class, id);
                colonia.getIdcolonia();
            } catch (EntityNotFoundException enfe) {
               JOptionPane.showMessageDialog(null,"NO EXISTEN DATOS","",JOptionPane.ERROR_MESSAGE);
                throw new NonexistentEntityException("The colonia with id " + id + " no longer exists.", enfe);
            }
            em.remove(colonia);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null,"¡¡¡::EXITO::!!!","",JOptionPane.PLAIN_MESSAGE,icono);
        
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Colonia> findColoniaEntities() {
        return findColoniaEntities(true, -1, -1);
    }

    public List<Colonia> findColoniaEntities(int maxResults, int firstResult) {
        return findColoniaEntities(false, maxResults, firstResult);
    }

    private List<Colonia> findColoniaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Colonia.class));
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

    public Colonia findColonia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Colonia.class, id);
        } finally {
            em.close();
        }
    }

    public int getColoniaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Colonia> rt = cq.from(Colonia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

   
    
}
