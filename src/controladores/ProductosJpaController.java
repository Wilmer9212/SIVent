/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Conexion.AbstractFacade;
import controladores.exceptions.NonexistentEntityException;
import controladores.exceptions.PreexistingEntityException;
import entidades.Productos;
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
public class ProductosJpaController implements Serializable {
    Icon icono = new ImageIcon(getClass().getResource("/Imagenes/oki.png"));
    public ProductosJpaController() {
        this.emf = AbstractFacade.conexion();//Persistence.createEntityManagerFactory("ventasPU");
       
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Productos productos) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(productos);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "¡¡¡::EXITO::!!!","",JOptionPane.INFORMATION_MESSAGE,icono);
        } catch (Exception ex) {
            if (findProductos(productos.getIdproducto()) != null) {
                JOptionPane.showMessageDialog(null,"EL CODIGO DE PRODUCTO YA ESTA REGISTRADO","",JOptionPane.WARNING_MESSAGE);
                throw new PreexistingEntityException("Productos " + productos + " already exists.", ex);
            }
            throw ex;
        } finally {
            
        }
    }

    public void edit(Productos productos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Integer id=productos.getIdproducto();
            if(findProductos(id)==null){
                JOptionPane.showMessageDialog(null,"NO EXISTEN DATOS","",JOptionPane.ERROR_MESSAGE);
            }else{
            productos = em.merge(productos);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "¡¡¡::EXITO::!!!","",JOptionPane.INFORMATION_MESSAGE,icono);
            }
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = productos.getIdproducto();
                if (findProductos(id) == null) {
                    throw new NonexistentEntityException("The productos with id " + id + " no longer exists.");
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
            Productos productos;
            try {
                productos = em.getReference(Productos.class, id);
                productos.getIdproducto();
            } catch (EntityNotFoundException enfe) {
               JOptionPane.showMessageDialog(null,"NO EXISTEN DATOS","",JOptionPane.ERROR_MESSAGE);
                throw new NonexistentEntityException("The productos with id " + id + " no longer exists.", enfe);
            }
            em.remove(productos);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null,"¡¡¡::EXITO::!!!","",JOptionPane.PLAIN_MESSAGE,icono);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Productos> findProductosEntities() {
        return findProductosEntities(true, -1, -1);
    }

    public List<Productos> findProductosEntities(int maxResults, int firstResult) {
        return findProductosEntities(false, maxResults, firstResult);
    }

    private List<Productos> findProductosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Productos.class));
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

    public Productos findProductos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Productos.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Productos> rt = cq.from(Productos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
