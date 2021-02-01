/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Conexion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 * 
 * @author Sammy Guergachi <sguergachi at gmail.com>
 */

public class OperacionesUsuario {

ConnectionDB Conecta =new ConnectionDB();
Connection cn=Conecta.ConnectionDB();

public void pc(){
    if(cn !=null){
        System.out.println("Existe conexion");
    }else{
        System.out.println("Conexion rechazada");
    }
}
public Object[][] AccedeUsuario(String login, String clave) {
 

    int registros = 0;
    int usu_id;

    try {
        String Query = "SELECT count(1) as cont" + " FROM usuarios";
        Statement St = cn.createStatement();
        /*
        PreparedStatement pstm = Conn.prepareStatement(Query);
        ResultSet Rs = pstm.executeQuery();
         */
        try (ResultSet Rs = St.executeQuery(Query)) {
            /*
            PreparedStatement pstm = Conn.prepareStatement(Query);
            ResultSet Rs = pstm.executeQuery();
             */
            Rs.next();
            registros = Rs.getInt("cont");
            Rs.close();
        }
    } catch (SQLException sqle) {
        JOptionPane.showMessageDialog(null, "Error en la consulta MySQL");
        Logger.getLogger(OperacionesUsuario.class.getName()).log(Level.SEVERE, null, sqle);
    }

    Object[][] data = new Object[registros][3];

    if (login.length() != 0 && clave.length() != 0) {

        String usu_login;
        String usu_password;

        try {
            String Query = "SELECT * FROM sil.usuarios WHERE login = '"
                    + login + "' AND clave = '" + clave + "'";
            Statement St = cn.createStatement();
            /*  PreparedStatement pstm = Conn.prepareStatement(Query);
            try (ResultSet Rs = pstm.executeQuery()) {
             */
            try (ResultSet Rs = St.executeQuery(Query)) {
                int i = 0;
                while (Rs.next()) {
                    usu_id = Rs.getInt("idUsuarios");
                    usu_login = Rs.getString("login");
                    usu_password = Rs.getString("clave");
                    data[i][0] = usu_id;
                    data[i][1] = usu_login;
                    data[i][2] = usu_password;
                    i++;

                }
            }
        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(null, "Error en la consulta MySQL");
            Logger.getLogger(OperacionesUsuario.class.getName()).log(Level.SEVERE, null, sqle);
        } finally {
            try {
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException sqle) {
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexion");
                Logger.getLogger(OperacionesUsuario.class.getName()).log(Level.SEVERE, null, sqle);
            }
        }
    }
    return data;
}

/*public void RegistrandoUsuario(String nombreUsuarios, String login, String clave,int id) {
     try {
        String Query = "INSERT INTO 'database_sofit'.'estados'('idestado','nombre') VALUES ("'"+id+","+nombre+"'")";
        try (Statement St = cn.createStatement()) {
            St.executeUpdate(Query);
            JOptionPane.showMessageDialog(null, "Datos ingresados correctamente!");
            St.close();
            cn.close();
        }
    } catch (SQLException SQLe) {
        JOptionPane.showMessageDialog(null, "Error en la consulta");
    }
    /*finally {
        if (Conector != null) {
            try {
                Conector.close();
            } catch (SQLException sqle) {
                JOptionPane.showMessageDialog(null, "Error al cerrar la conexion");
                Logger.getLogger(OperacionesLicorerias.class.getName()).log(Level.SEVERE, null, sqle);
            }
        }
    }*/
//}
public void leerd(){
               
        try {
        String Query = "SELECT  * from estado";
        Statement St = cn.createStatement();
        /*
        PreparedStatement pstm = Conn.prepareStatement(Query);
        ResultSet Rs = pstm.executeQuery();
         */
        String datoss[]=new String[3];
        try (ResultSet Rs = St.executeQuery(Query)) {
            /*
            PreparedStatement pstm = Conn.prepareStatement(Query);
            ResultSet Rs = pstm.executeQuery();
             */
                     
            
            while(Rs.next()){
              datoss[0]=Rs.getString(1);
                System.out.println(""+datoss[0]);
            }
            Rs.close();
        }
    } catch (SQLException sqle) {
        JOptionPane.showMessageDialog(null, "Error en la consulta MySQL");
        Logger.getLogger(OperacionesUsuario.class.getName()).log(Level.SEVERE, null, sqle);
    }
}
    public static void main(String[] args) {
        OperacionesUsuario op=new OperacionesUsuario();
        op.pc();
        op.leerd();
    }



}
