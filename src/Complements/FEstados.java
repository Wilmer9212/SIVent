/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Complements;

import Conexion.ConnectionDB;
import controladores.EstadoJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.Estado;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Elliot
 */
public class FEstados extends javax.swing.JFrame {
   EstadoJpaController control1=new EstadoJpaController();
   ConnectionDB conexion=new ConnectionDB();
   Connection connect=conexion.ConnectionDB();
    /**
     * Creates new form FEstados
     */
    public FEstados() {
        initComponents();
         Generarnumeracion();
        desabilitar();
        this.setLocationRelativeTo(null);
        llenartb("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ttcodigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtdescripcion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnaceptar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        txtbuscar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnnuevo = new javax.swing.JButton();
        btnmodificar = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbestado = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("ESTADOS");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("CODIGO");

        txtdescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtdescripcionKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("DESCRIPCION");

        btnaceptar.setText("ACEPTAR");
        btnaceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaceptarActionPerformed(evt);
            }
        });

        btncancelar.setText("CANCELAR");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarKeyReleased(evt);
            }
        });

        jLabel4.setText("NOMBRE");

        btnnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/nuevo.png"))); // NOI18N
        btnnuevo.setText("NUEVO");
        btnnuevo.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btnnuevo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnnuevo.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnnuevo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

        btnmodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/modiicar.png"))); // NOI18N
        btnmodificar.setText("MODIFICAR");
        btnmodificar.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btnmodificar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnmodificar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodificarActionPerformed(evt);
            }
        });

        btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/eliminar.png"))); // NOI18N
        btneliminar.setText("ELIMINAR");
        btneliminar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btneliminar.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btneliminar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });

        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/salir ventana.png"))); // NOI18N
        btnsalir.setText("SALIR");
        btnsalir.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        btnsalir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnsalir.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btnsalir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        tbestado = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tbestado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbestado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbestadoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbestado);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnnuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnmodificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btneliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnsalir))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(27, 27, 27))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ttcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnaceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btncancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 4, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtbuscar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ttcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(btnaceptar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtdescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(btncancelar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnnuevo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnsalir, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btneliminar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnmodificar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtdescripcionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdescripcionKeyReleased
        txtdescripcion.setText(txtdescripcion.getText().toUpperCase());
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdescripcionKeyReleased

    private void btnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceptarActionPerformed
        Estado um =new Estado();
        um.setIdestado(Integer.parseInt(ttcodigo.getText()));
        um.setNombree(txtdescripcion.getText());
        if(valEntradas()==true){
            int preg=JOptionPane.showConfirmDialog(null,"¿DATOS CORRECTOS?","",JOptionPane.YES_NO_OPTION);
            if(preg==JOptionPane.YES_OPTION){
                try {
                    control1.create(um);
                    llenartb("");
                    limipiar();
                    desabilitar();

                } catch (Exception ex) {
                    Logger.getLogger(FUnidadesM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnaceptarActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        btnnuevo.setEnabled(true);
        desabilitar();
    }//GEN-LAST:event_btncancelarActionPerformed

    private void txtbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyReleased
        txtbuscar.setText(txtbuscar.getText().toUpperCase());
        llenartb(txtbuscar.getText());
    }//GEN-LAST:event_txtbuscarKeyReleased

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        habilitar();
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void btnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarActionPerformed
        Estado um=new Estado();
        List<Estado>lm=control1.findEstadoEntities();
        int ide = 0;
        if(valEntradas()==true){
            int preg=JOptionPane.showConfirmDialog(null,"¿MODIFICAR?","",JOptionPane.YES_NO_OPTION);
            if(preg==JOptionPane.YES_OPTION){
                try {
                    um.setIdestado(Integer.parseInt(ttcodigo.getText()));
                    um.setNombree(txtdescripcion.getText());
                    control1.edit(um);
                    Generarnumeracion();
                    desabilitar();
                    llenartb("");
                    limipiar();
                } catch (Exception ex) {
                    Logger.getLogger(FUnidadesM.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnmodificarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        int preg=JOptionPane.showConfirmDialog(null,"¿ELIMINAR?","",JOptionPane.YES_NO_OPTION);
        if(preg==JOptionPane.YES_OPTION){
            try {
                control1.destroy(Integer.parseInt(ttcodigo.getText()));
                llenartb("");
                Generarnumeracion();
                desabilitar();
                limipiar();

            } catch (NonexistentEntityException ex) {
                Logger.getLogger(FUnidadesM.class.getName()).log(Level.SEVERE, null, ex);

            }
        }
    }//GEN-LAST:event_btneliminarActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
       this.setVisible(false);
    }//GEN-LAST:event_btnsalirActionPerformed

    private void tbestadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbestadoMouseClicked
        MouseE();
        int fis=tbestado.getSelectedRow();
        List<Estado>lm=control1.findEstadoEntities();
        if(fis>=0){
            for(int i=0;i<lm.size();i++){
                ttcodigo.setText(tbestado.getValueAt(fis, 0).toString());
                txtdescripcion.setText(tbestado.getValueAt(fis,1).toString());
            }

        }
    }//GEN-LAST:event_tbestadoMouseClicked
  public void llenartb(String descripcion){
      String[] titulos = {"CODIGO","DESCRIPCION"};
      DefaultTableModel dtm = new DefaultTableModel(null, titulos);
      int ide=0;
     try{
        Object o[] = null;
         List<Estado>lista = buscarU(descripcion);
         for (int i = 0; i < lista.size(); i++) {  
                dtm.addRow(o);
                dtm.setValueAt(lista.get(i).getIdestado().toString(),i, 0); 
                dtm.setValueAt(lista.get(i).getNombree(),i, 1);
          }
         tbestado.setModel(dtm);
       
     }catch(Exception ex){
         JOptionPane.showMessageDialog(null,"NO SE RECONOCE LA TABLA ESTADOS","",JOptionPane.ERROR_MESSAGE);
     }                 
  }
    private List<Estado> buscarU(String nombree) {
        EntityManager em = control1.getEntityManager();
        Query query = em.createQuery("SELECT u FROM Estado u WHERE u.nombree LIKE :nombree");
        query.setParameter("nombree","%"+nombree+"%");
        List<Estado> lista = query.getResultList();
        return lista;
    }
 
    public void Generarnumeracion() {
        String SQL = "select max(idestado) from estado";

        int c = 0;
        int b = 0;
        try {
            Statement st = (Statement) connect.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while (rs.next()) {
                c = rs.getInt(1);
            }
            if (c == 0) {
                ttcodigo.setText("1");
            } else {
                ttcodigo.setText("" + (+c + 1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//MetodoGenerarNumEnId*/ 
    
    public boolean valEntradas() {
        String mensaje = "";
        boolean estado = true;
        if (ttcodigo.getText().isEmpty()) {
            mensaje += "CODIGO NO DEBE ESTAR VACIO \n";
            estado = false;
        }
        if (txtdescripcion.getText().isEmpty()) {
            mensaje += "NO SE INSERTO UNA DESCRIPCION \n";
            estado = false;
        }
          
          if (mensaje.length() >= 6) {
            JOptionPane.showMessageDialog(null, mensaje, "", JOptionPane.WARNING_MESSAGE);

        }
        return estado;
    }
      public void desabilitar(){
        ttcodigo.setEnabled(false);
        txtdescripcion.setEnabled(false);
        btnaceptar.setEnabled(false);
        btnmodificar.setEnabled(false);
        btneliminar.setEnabled(false);
        txtdescripcion.setText("");
        Generarnumeracion();
        btnnuevo.setEnabled(true);
        btncancelar.setEnabled(false);
    }
    public void habilitar(){
        ttcodigo.setEnabled(true);
        ttcodigo.setEditable(false);
        txtdescripcion.setEnabled(true);
        btnaceptar.setEnabled(true);        
        btnnuevo.setEnabled(false);
        btneliminar.setEnabled(false);
        btnmodificar.setEnabled(false);
        btncancelar.setEnabled(true);
    }
    public void MouseE(){
     btnnuevo.setEnabled(false);
     btneliminar.setEnabled(true);
     btnmodificar.setEnabled(true);
     btnaceptar.setEnabled(false);
     ttcodigo.setEnabled(true);
     ttcodigo.setEditable(false);
     txtdescripcion.setEnabled(true);
     btncancelar.setEnabled(true);
     
    }
    public void limipiar(){
        Generarnumeracion();
        txtdescripcion.setText("");
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FEstados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FEstados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FEstados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FEstados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FEstados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnaceptar;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnmodificar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JButton btnsalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbestado;
    private javax.swing.JTextField ttcodigo;
    private javax.swing.JTextField txtbuscar;
    private javax.swing.JTextField txtdescripcion;
    // End of variables declaration//GEN-END:variables
}