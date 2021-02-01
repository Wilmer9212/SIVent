/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Conexion.AbstractFacade;
import controladores.exceptions.NonexistentEntityException;
import controladores.exceptions.PreexistingEntityException;
import entidades.Proveedor;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.*;
import javax.swing.ImageIcon;

/**
 *
 * @author Anonymous
 */
public class ProveedorJpaController implements Serializable {
    Icon icono = new ImageIcon(getClass().getResource("/Imagenes/oki.png"));
    public ProveedorJpaController() {
        this.emf = AbstractFacade.conexion();//Persistence.createEntityManagerFactory("ventasPU");
        
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Proveedor proveedor) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(proveedor);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "¡¡¡::EXITO::!!!","",JOptionPane.INFORMATION_MESSAGE,icono);
        } catch (Exception ex) {
            if (findProveedor(proveedor.getIdproveedor()) != null) {
                                JOptionPane.showMessageDialog(null,"EL CODIGO DE PROVEEDOR YA ESTA REGISTRADO","",JOptionPane.WARNING_MESSAGE);
                                throw new PreexistingEntityException("Proveedor " + proveedor + " already exists.", ex);
                
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Proveedor proveedor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            proveedor = em.merge(proveedor);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "¡¡¡::EXITO::!!!","",JOptionPane.INFORMATION_MESSAGE,icono);
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = proveedor.getIdproveedor();
                if (findProveedor(id) == null) {
               JOptionPane.showMessageDialog(null,"NO EXISTEN DATOS","",JOptionPane.ERROR_MESSAGE);
                    throw new NonexistentEntityException("The proveedor with id " + id + " no longer exists.");
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
            Proveedor proveedor;
            try {
                proveedor = em.getReference(Proveedor.class, id);
                proveedor.getIdproveedor();
            } catch (EntityNotFoundException enfe) {                
                JOptionPane.showMessageDialog(null,"NO EXISTEN DATOS","",JOptionPane.ERROR_MESSAGE);
                throw new NonexistentEntityException("The proveedor with id " + id + " no longer exists.", enfe);
            }
            em.remove(proveedor);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "¡¡¡::EXITO::!!!","",JOptionPane.INFORMATION_MESSAGE,icono);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Proveedor> findProveedorEntities() {
        return findProveedorEntities(true, -1, -1);
    }

    public List<Proveedor> findProveedorEntities(int maxResults, int firstResult) {
        return findProveedorEntities(false, maxResults, firstResult);
    }

    private List<Proveedor> findProveedorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Proveedor.class));
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

    public Proveedor findProveedor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Proveedor.class, id);
        } finally {
            em.close();
        }
    }

    public int getProveedorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Proveedor> rt = cq.from(Proveedor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
