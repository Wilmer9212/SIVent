package Conexion;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Wilmer
 */
public class JPAUtil{
      String  usuario = "root";
      String pass = "root";
      String PU = "ventasPU";
    
    public EntityManagerFactory getEntityManagerFactory(String ip, String bd) {       
        try {               
        System.out.println("Lllego a jpa util");  
            System.out.println("Servidor:"+ip+",Base de datos:"+bd);
        Properties properties = new Properties();
        properties.put("javax.persistence.jdbc.driver","com.mysql.jdbc.Driver");
        properties.put("javax.persistence.jdbc.url","jdbc:mysql://"+ip+":3306/"+bd+"?serverTimezone=UTC");
        properties.put("javax.persistence.jdbc.user",usuario);
        properties.put("javax.persistence.jdbc.password",pass);
        properties.put("javax.persistence.schema-generation.database.action","create");
        EntityManagerFactory emf=Persistence.createEntityManagerFactory(PU,properties);    
        System.out.println("Listo");
         return emf;
        } catch (Throwable e) {
            System.err.println("Error al conectar a la persistencia" + e.getMessage());
       
        }
          return null;
    }

}


