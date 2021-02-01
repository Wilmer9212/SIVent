/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author Elliot
 */
public class ConexionTest {
    public static void main(String[] args) {
        EntityManagerFactory emf=AbstractFacade.conexion();
        EntityManager em=emf.createEntityManager();
        Query query=em.createNativeQuery("SELECT nombre from cliente limit 1");
        System.out.println("Nombre:"+query.getSingleResult());
        emf.close();
    }
}
