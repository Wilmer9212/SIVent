/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Complements;

import controladores.ProveedorJpaController;
import entidades.Proveedor;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Elliot
 */
public class FProveedor extends javax.swing.JFrame {

    
   DefaultTableModel dtm = new DefaultTableModel();
   
    ProveedorJpaController CProv = new ProveedorJpaController();
    Proveedor prov = new Proveedor();
    

    public FProveedor() {
        initComponents();
        Desabilitar();
        carga_informacion_Prov("");
    }

    private void carga_informacion_Prov(String nombre) {
        String[] titulos = {"COD.PROV", "EMPRESA", "TELEFONO", "EMAIL",};
        dtm=new DefaultTableModel(null,titulos);
        try {
            Object o[] = null;
            List<Proveedor> listp =buscarProv(nombre);
            for (int i = 0; i < listp.size(); i++) {
                dtm.addRow(o);
                dtm.setValueAt(listp.get(i).getIdproveedor(), i, 0);
                dtm.setValueAt(listp.get(i).getNombre(), i, 1);
                dtm.setValueAt(listp.get(i).getTelefono(), i, 2);
                dtm.setValueAt(listp.get(i).getMail(), i, 3);

            }
            tbcontenido.setModel(dtm);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
    }
    private List<Proveedor> buscarProv(String nombre) {
        EntityManager em = CProv.getEntityManager();
        Query query=em.createQuery("SELECT p FROM Proveedor p WHERE p.nombre LIKE :nombre");
        query.setParameter("nombre","%"+nombre+"%");
        List<Proveedor> listas=query.getResultList();
        return listas;
    }

    public void Desabilitar() {
        txtcodigo.setEnabled(false);

        txttelefono.setEnabled(false);
        txtemail.setEnabled(false);
        empresa.setEnabled(false);
        btnaceptar.setEnabled(false);
        btncancelar.setEnabled(false);
        btneliminar.setEnabled(false);
        btnmodificar.setEnabled(false);
        btnnuevo.setEnabled(true);

    }

    public void habField() {
        txtcodigo.setEnabled(true);
        txttelefono.setEnabled(true);
        txtemail.setEnabled(true);
        empresa.setEnabled(true);
        btnaceptar.setEnabled(true);
        btncancelar.setEnabled(true);

    }
    public void limpiarC() {

        txttelefono.setText("");
        txtemail.setText("");
        empresa.setText("");
        txtcodigo.setText("");
    }

    public boolean ValEntradas() {
        String msj = "";
        boolean es = true;
        if (this.txtcodigo.getText().isEmpty() == true) {
            msj += "NO SE INSERTO CODIGO \n";
            es = false;
        }
        if (empresa.getText().isEmpty() == true) {
            msj += "NO SE INSERTO NOMBRE \n";
            es = false;
        }if(txtemail.getText().isEmpty()==false && valcorreo()==false){
               msj+="VERIFICA DIRECCION DE CORREO @";
            es = false;
        }
        if (msj.length() >= 6) {
             JOptionPane.showMessageDialog(null, msj, "", JOptionPane.WARNING_MESSAGE);

        }
       
        return es;
    }
     public boolean valcorreo(){
          boolean correo=false;
          for(int i=0;i<txtemail.getText().length();i++){
               if(txtemail.getText().charAt(i)=='@'){
                  correo=true;
               } 
         }
         return correo;
     }          
  

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlDatos = new javax.swing.JPanel();
        j1 = new javax.swing.JLabel();
        btnaceptar = new javax.swing.JButton();
        txtcodigo = new javax.swing.JTextField();
        btncancelar = new javax.swing.JButton();
        txtemail = new javax.swing.JTextField();
        j2 = new javax.swing.JLabel();
        txttelefono = new javax.swing.JTextField();
        j4 = new javax.swing.JLabel();
        empresa = new javax.swing.JTextField();
        j5 = new javax.swing.JLabel();
        pnlLista = new javax.swing.JPanel();
        btnnuevo = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        btnmodificar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtbuscar = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbcontenido = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        pnlDatos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        j1.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        j1.setText("Codigo");

        btnaceptar.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnaceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/aceptar.png"))); // NOI18N
        btnaceptar.setText("Aceptar");
        btnaceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaceptarActionPerformed(evt);
            }
        });

        txtcodigo.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtcodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcodigoKeyReleased(evt);
            }
        });

        btncancelar.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btncancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cancelar.png"))); // NOI18N
        btncancelar.setText("Cancelar");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        txtemail.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txtemail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtemailKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtemailKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtemailKeyTyped(evt);
            }
        });

        j2.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        j2.setText("Empresa");

        txttelefono.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        txttelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttelefonoKeyReleased(evt);
            }
        });

        j4.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        j4.setText("Telefono");

        empresa.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        empresa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                empresaKeyReleased(evt);
            }
        });

        j5.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        j5.setText("E-mail");

        javax.swing.GroupLayout pnlDatosLayout = new javax.swing.GroupLayout(pnlDatos);
        pnlDatos.setLayout(pnlDatosLayout);
        pnlDatosLayout.setHorizontalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(j2)
                    .addComponent(j1)
                    .addComponent(j4)
                    .addComponent(j5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(btnaceptar)
                        .addGap(265, 265, 265)
                        .addComponent(btncancelar)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDatosLayout.createSequentialGroup()
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txttelefono, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtcodigo, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtemail)
                            .addComponent(empresa, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(15, 15, 15))))
        );
        pnlDatosLayout.setVerticalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(j1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(empresa, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(j2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttelefono, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(j4))
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(j5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)))
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnaceptar)
                    .addComponent(btncancelar))
                .addGap(20, 20, 20))
        );

        pnlLista.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        btnnuevo.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/nuevo.png"))); // NOI18N
        btnnuevo.setText("Nuevo");
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

        btneliminar.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/eliminar.png"))); // NOI18N
        btneliminar.setText("Eliminar");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });

        btnmodificar.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnmodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/modiicar.png"))); // NOI18N
        btnmodificar.setText("Modificar");
        btnmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodificarActionPerformed(evt);
            }
        });

        btnsalir.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/salir ventana.png"))); // NOI18N
        btnsalir.setText("Salir");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel5.setText("BUSCAR");

        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarKeyReleased(evt);
            }
        });

        tbcontenido = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tbcontenido.setModel(new javax.swing.table.DefaultTableModel(
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
        tbcontenido.setEditingColumn(0);
        tbcontenido.setEditingRow(0);
        tbcontenido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbcontenidoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbcontenido);

        javax.swing.GroupLayout pnlListaLayout = new javax.swing.GroupLayout(pnlLista);
        pnlLista.setLayout(pnlListaLayout);
        pnlListaLayout.setHorizontalGroup(
            pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlListaLayout.createSequentialGroup()
                        .addComponent(btnnuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnmodificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btneliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnsalir))
                    .addGroup(pnlListaLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlListaLayout.setVerticalGroup(
            pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListaLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlListaLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btneliminar)
                            .addComponent(btnmodificar)
                            .addComponent(btnnuevo)))
                    .addGroup(pnlListaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnsalir)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlLista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlLista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceptarActionPerformed
        if (this.ValEntradas() == true) {
               try {
                List<Proveedor> listP = CProv.findProveedorEntities();
                prov.setIdproveedor(Integer.parseInt(txtcodigo.getText()));
                prov.setNombre(empresa.getText());
                prov.setTelefono(txttelefono.getText());
                prov.setMail(txtemail.getText());
                int confirmar = JOptionPane.showConfirmDialog(null, "¿DATOS CORRECTOS?", "", JOptionPane.YES_NO_OPTION);
                if (confirmar == JOptionPane.YES_NO_OPTION) {
                    CProv.create(prov);
                    carga_informacion_Prov("");
                    limpiarC();
                    Desabilitar();
                }
            } catch (Exception e) {
            }
            }
    }//GEN-LAST:event_btnaceptarActionPerformed


    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        Desabilitar();
        limpiarC();

    }//GEN-LAST:event_btncancelarActionPerformed

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        this.limpiarC();
        habField();
        this.btneliminar.setEnabled(false);
        this.btnmodificar.setEnabled(false);
        this.btnnuevo.setEnabled(false);


    }//GEN-LAST:event_btnnuevoActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        int fila = tbcontenido.getSelectedRow();
     
            try {
                int confirmar = JOptionPane.showConfirmDialog(null, "¿ELIMINAR DATOS?", "", JOptionPane.YES_NO_OPTION);
                if (confirmar == JOptionPane.YES_NO_OPTION) {
                    CProv.destroy(Integer.parseInt(txtcodigo.getText()));
                    Desabilitar();
                    limpiarC();
                    btnmodificar.setEnabled(false);
                    btneliminar.setEnabled(false);
                    carga_informacion_Prov("");
                }
            } catch (Exception ex) {
                
            }
    }//GEN-LAST:event_btneliminarActionPerformed
    private void btnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarActionPerformed
        if (ValEntradas() == true) {
            try {
                prov.setIdproveedor(Integer.parseInt(txtcodigo.getText()));
                prov.setNombre(empresa.getText());
                prov.setTelefono(txttelefono.getText());
                prov.setMail(txtemail.getText());
                int confirmar = JOptionPane.showConfirmDialog(null, "¿DATOS CORRECTOS?", "", JOptionPane.YES_NO_OPTION);
                if (confirmar == JOptionPane.YES_NO_OPTION) {
                    CProv.edit(prov);
                    carga_informacion_Prov("");
                    limpiarC();
                    Desabilitar();
                }
            } catch (Exception e) {

            }
        }

    }//GEN-LAST:event_btnmodificarActionPerformed


    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnsalirActionPerformed

    private void txttelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoKeyReleased
       MetodosValidar.soloNumeros(txttelefono,13);
    }//GEN-LAST:event_txttelefonoKeyReleased

    private void txtemailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtemailKeyPressed
//        MetodosValidar.soloNumeros(txttelefono, 14);
    }//GEN-LAST:event_txtemailKeyPressed

    private void empresaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_empresaKeyReleased
     empresa.setText(this.empresa.getText().toUpperCase());
    }//GEN-LAST:event_empresaKeyReleased
    
    private void txtbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyReleased
        carga_informacion_Prov(txtbuscar.getText());
        txtbuscar.setText(txtbuscar.getText().toUpperCase());
    }//GEN-LAST:event_txtbuscarKeyReleased

    private void tbcontenidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbcontenidoMouseClicked
       int filaS=tbcontenido.getSelectedRow();
       if(filaS>=0){
           habField();
           btneliminar.setEnabled(true);
           btnmodificar.setEnabled(true);
           btnnuevo.setEnabled(false);
           btnaceptar.setEnabled(false);
           txtcodigo.setText(tbcontenido.getValueAt(filaS,0).toString());
           empresa.setText(tbcontenido.getValueAt(filaS,1).toString());
           txttelefono.setText(tbcontenido.getValueAt(filaS,2).toString());
           txtemail.setText(tbcontenido.getValueAt(filaS,3).toString());
           
       }
    }//GEN-LAST:event_tbcontenidoMouseClicked

    private void txtcodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodigoKeyReleased
     MetodosValidar.soloNumeros(txtcodigo,6);
    }//GEN-LAST:event_txtcodigoKeyReleased

    private void txtemailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtemailKeyTyped
    char car= evt.getKeyChar();
    if((car <'0' || car > '9') && (car < 'A' || car> 'Z') && (car < 'a' || car> 'z') && (car < '@'|| car> '@') && (car < '_'  || car > '_') && (car < '.' || car > '.')){        
    evt.consume();
    }
    }//GEN-LAST:event_txtemailKeyTyped

    private void txtemailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtemailKeyReleased
  this.txtemail.setText(txtemail.getText().toLowerCase());       // TODO add your handling code here:
    }//GEN-LAST:event_txtemailKeyReleased

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
            java.util.logging.Logger.getLogger(FProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FProveedor().setVisible(true);
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
    private javax.swing.JTextField empresa;
    private javax.swing.JLabel j1;
    private javax.swing.JLabel j2;
    private javax.swing.JLabel j4;
    private javax.swing.JLabel j5;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnlDatos;
    private javax.swing.JPanel pnlLista;
    private javax.swing.JTable tbcontenido;
    private javax.swing.JTextField txtbuscar;
    private javax.swing.JTextField txtcodigo;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
}
