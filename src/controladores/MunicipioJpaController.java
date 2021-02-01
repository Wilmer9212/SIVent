/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import Conexion.AbstractFacade;
import controladores.exceptions.NonexistentEntityException;
import controladores.exceptions.PreexistingEntityException;
import entidades.Municipio;
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
public class MunicipioJpaController implements Serializable {
Icon icono = new ImageIcon(getClass().getResource("/Imagenes/oki.png"));
    public MunicipioJpaController() {
        this.emf =AbstractFacade.conexion();// Persistence.createEntityManagerFactory("ventasPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Municipio municipio) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(municipio);
            em.getTransaction().commit();
        JOptionPane.showMessageDialog(null, "¡¡¡::EXITO::!!!","",JOptionPane.INFORMATION_MESSAGE,icono);
        } catch (Exception ex) {
            if (findMunicipio(municipio.getIdmunicipio()) != null) {
                JOptionPane.showMessageDialog(null,"EL CODIGO DE MUNICIPIO YA ESTA REGISTRADO","",JOptionPane.WARNING_MESSAGE);
                throw new PreexistingEntityException("Municipio " + municipio + " already exists.", ex);
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Municipio municipio) throws NonexistentEntityException, Exception {
        
        
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            municipio = em.merge(municipio);
            Integer id=municipio.getIdmunicipio();
            if(findMunicipio(id)==null){
                JOptionPane.showMessageDialog(null,"NO EXISTEN DATOS","",JOptionPane.ERROR_MESSAGE);
            }else{
            municipio = em.merge(municipio);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null, "¡¡¡::EXITO::!!!","",JOptionPane.INFORMATION_MESSAGE,icono);
            }
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = municipio.getIdmunicipio();
                if (findMunicipio(id) == null) {
                    throw new NonexistentEntityException("The municipio with id " + id + " no longer exists.");
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
            Municipio municipio;
            try {
                municipio = em.getReference(Municipio.class, id);
                municipio.getIdmunicipio();
            } catch (EntityNotFoundException enfe) {
                JOptionPane.showMessageDialog(null,"NO EXISTEN DATOS","",JOptionPane.ERROR_MESSAGE);
                throw new NonexistentEntityException("The municipio with id " + id + " no longer exists.", enfe);
            }
            em.remove(municipio);
            em.getTransaction().commit();
            JOptionPane.showMessageDialog(null,"¡¡¡::EXITO::!!!","",JOptionPane.PLAIN_MESSAGE,icono);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Municipio> findMunicipioEntities() {
        return findMunicipioEntities(true, -1, -1);
    }

    public List<Municipio> findMunicipioEntities(int maxResults, int firstResult) {
        return findMunicipioEntities(false, maxResults, firstResult);
    }

    private List<Municipio> findMunicipioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Municipio.class));
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

    public Municipio findMunicipio(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Municipio.class, id);
        } finally {
            em.close();
        }
    }

    public int getMunicipioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Municipio> rt = cq.from(Municipio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
