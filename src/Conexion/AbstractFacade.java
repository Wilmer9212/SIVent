/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Elliot
 */
public abstract class AbstractFacade<T> {
    private EntityManager em;
    private final Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }
    public static EntityManagerFactory conexion() {
        Fichero_Configuraciones  datos = new Fichero_Configuraciones();
        EntityManagerFactory emf=null;
        JPAUtil jpa=new JPAUtil();
        emf=jpa.getEntityManagerFactory(datos.getHost(),datos.getDatabase());
        return emf;
    }
    

    public int inserta(T entity) {
        EntityManagerFactory emf=conexion();
        em = emf.createEntityManager();  
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en inserta: " + e.getMessage());
            em.getTransaction().rollback();
            return 0;
        } finally {
            em.close();
        }
        return 1;
     }

    public int actualiza(T entity) {
        EntityManagerFactory emf=conexion();
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en actualiza : " + e.getMessage());
            em.getTransaction().rollback();
            return 0;
        } finally {
            em.close();
        }
        return 1;
    }

    public int elimina(T entity) {
        EntityManagerFactory emf=conexion();
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(em.merge(entity));
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Error en elimina : " + e.getMessage());
            em.getTransaction().rollback();
            return 0;
        } finally {
            em.close();
        }
        return 1;
    }

    public T find(Object id) {
        EntityManagerFactory emf=conexion();
        em = emf.createEntityManager();
        T find = em.find(entityClass, id);
        em.close();
        return find;
    }

    public List<T> findAll() {
       EntityManagerFactory emf=conexion();
       em = emf.createEntityManager();
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        List lista = em.createQuery(cq).getResultList();
        em.close();
        return lista;
    }

 

}

