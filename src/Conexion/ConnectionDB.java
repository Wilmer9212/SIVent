/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */
public class ConnectionDB {


private static final String DB = "database_sofit";
private static final String DBuser = "root2";
private static final String DBpwd = "root";
private static final String DBurl = "jdbc:mysql://localhost:3306/" + DB;
Connection Conector;


public Connection ConnectionDB() {

    try {
        Class.forName("com.mysql.jdbc.Driver");
        Conector = DriverManager.getConnection(DBurl, DBuser, DBpwd);
        System.out.println("Conexion exitosa");

    } catch (ClassNotFoundException classNotFoundException) {
        JOptionPane.showMessageDialog(null, "Error con el driver JDBC");
    } catch (SQLException exception) {
        try {
            JOptionPane.showMessageDialog(null, "Error en la conexion");
            Conector.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
return Conector;
}
  /*  public static void main(String[] args) {
        ConnectionDB  cn=new ConnectionDB();
    }*/
}
