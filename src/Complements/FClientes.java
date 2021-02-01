/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Complements;

import controladores.ClienteJpaController;
import controladores.ColoniaJpaController;
import controladores.EstadoJpaController;
import controladores.GeneroJpaController;
import controladores.MunicipioJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.Cliente;
import entidades.Colonia;
import entidades.Estado;
import entidades.Genero;
import entidades.Municipio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Sistemas
 */
public class FClientes extends javax.swing.JFrame {

    ClienteJpaController PClientes = new ClienteJpaController();
    ColoniaJpaController PColonia = new ColoniaJpaController();
    MunicipioJpaController PMunicipio = new MunicipioJpaController();
    EstadoJpaController PEstado = new EstadoJpaController();
    GeneroJpaController PGenero=new GeneroJpaController();
    DefaultTableModel dtm = new DefaultTableModel();

    Colonia colonias = new Colonia();

    public FClientes() {
        initComponents();
        ListarClientes("");
        desabilitarC();
        llenarC();
        llenarM();
        llenarE();
        llenarG();
        this.setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblcodigo = new javax.swing.JLabel();
        lblnombre = new javax.swing.JLabel();
        lbledad = new javax.swing.JLabel();
        lbldireccion = new javax.swing.JLabel();
        lbltelefono = new javax.swing.JLabel();
        txtcodigo = new javax.swing.JTextField();
        txtnombre = new javax.swing.JTextField();
        txtedad = new javax.swing.JTextField();
        txttelefono = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        btnaceptar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        txtemail = new javax.swing.JTextField();
        lblgenero = new javax.swing.JLabel();
        lblemail = new javax.swing.JLabel();
        cbcol = new javax.swing.JComboBox<>();
        cbmuni = new javax.swing.JComboBox<>();
        cbestado = new javax.swing.JComboBox<>();
        cbgenero = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        btnsalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbclientes = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtbuscar = new javax.swing.JTextField();
        btnnuevo = new javax.swing.JButton();
        btnmodificar = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ADMINSTRACION DE CLIENTES");
        setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        setForeground(java.awt.Color.green);
        setIconImages(null);
        setName("miframe"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        lblcodigo.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblcodigo.setText("CODIGO");

        lblnombre.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblnombre.setText("NOMBRE");

        lbledad.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lbledad.setText("EDAD");

        lbldireccion.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lbldireccion.setText("DIRECCION");

        lbltelefono.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lbltelefono.setText("TELEFONO");

        txtcodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcodigoKeyReleased(evt);
            }
        });

        txtnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnombreKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombreKeyTyped(evt);
            }
        });

        txtedad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtedadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtedadKeyTyped(evt);
            }
        });

        txttelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttelefonoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttelefonoKeyTyped(evt);
            }
        });

        btnaceptar.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btnaceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/aceptar.png"))); // NOI18N
        btnaceptar.setText("ACEPTAR");
        btnaceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaceptarActionPerformed(evt);
            }
        });

        btncancelar.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btncancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/cancelar.png"))); // NOI18N
        btncancelar.setText("CANCELAR");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btncancelar)
                    .addComponent(btnaceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnaceptar)
                .addGap(41, 41, 41)
                .addComponent(btncancelar)
                .addGap(53, 53, 53))
        );

        txtemail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtemailKeyTyped(evt);
            }
        });

        lblgenero.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblgenero.setText("GENERO");

        lblemail.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblemail.setText("EMAIL");

        cbcol.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---" }));
        cbcol.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbcolMouseClicked(evt);
            }
        });
        cbcol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbcolActionPerformed(evt);
            }
        });

        cbmuni.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---" }));

        cbestado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---" }));

        cbgenero.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONA..." }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblnombre)
                            .addComponent(lblcodigo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtnombre, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
                            .addComponent(txtcodigo)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbldireccion)
                            .addComponent(lbledad)
                            .addComponent(lbltelefono)
                            .addComponent(lblgenero)
                            .addComponent(lblemail))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtedad)
                            .addComponent(txttelefono)
                            .addComponent(txtemail)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cbcol, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbmuni, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbestado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(cbgenero, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(10, 10, 10)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblcodigo)
                            .addComponent(txtcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblnombre))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtedad, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbledad))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbcol, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(cbmuni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbestado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbldireccion))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txttelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbltelefono))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblgenero)
                            .addComponent(cbgenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblemail)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        btnsalir.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/salir ventana.png"))); // NOI18N
        btnsalir.setText("SALIR");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        tbclientes = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tbclientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbclientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbclientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbclientes);

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel8.setText("BUSCAR");

        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarKeyReleased(evt);
            }
        });

        btnnuevo.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btnnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/nuevo.png"))); // NOI18N
        btnnuevo.setText("NUEVO");
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

        btnmodificar.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btnmodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/modiicar.png"))); // NOI18N
        btnmodificar.setText("MODIFICAR");
        btnmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmodificarActionPerformed(evt);
            }
        });

        btneliminar.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btneliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/eliminar.png"))); // NOI18N
        btneliminar.setText("ELIMINAR");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnnuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnmodificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btneliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 262, Short.MAX_VALUE)
                        .addComponent(btnsalir))
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtbuscar)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsalir)
                    .addComponent(btnnuevo)
                    .addComponent(btnmodificar)
                    .addComponent(btneliminar))
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
     private void ListarClientes( String  nombre ) {
        String[] titulos = {"CV.CLIENTE", "NMOBRE", "EDAD", "DIRECCION", "TELEFONO", "GENERO", "E-MAIL"};
        dtm = new DefaultTableModel(null, titulos);
        try {
            Object o[] = null;
            List<Cliente> listC = buscarClie(nombre);
            for (int i = 0; i < listC.size(); i++) {
                dtm.addRow(o);
                dtm.setValueAt(listC.get(i).getIdcliente(), i, 0);
                dtm.setValueAt(listC.get(i).getNombre(),    i, 1);
                dtm.setValueAt(listC.get(i).getEdad(),      i, 2);
                dtm.setValueAt(listC.get(i).getDireccion(), i, 3);
                dtm.setValueAt(listC.get(i).getTelefono(),  i, 4);
                dtm.setValueAt(listC.get(i).getGenero(),    i, 5);
                dtm.setValueAt(listC.get(i).getMail(),      i, 6);
            }
            tbclientes.setModel(dtm);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    public void llenarC() {
        List<Colonia> listCo = PColonia.findColoniaEntities();
        for (int i = 0; i < listCo.size(); i++) {
            cbcol.addItem(listCo.get(i).getNombre());
        }
    }
    public int rgm;
    public void llenarM(){
        List<Municipio> listM = PMunicipio.findMunicipioEntities();
        for (int i = 0; i < listM.size(); i++) {
            cbmuni.addItem(listM.get(i).getNombrem());
            rgm = listM.get(i).getIdmunicipio();
        }
    }
    public void llenarE() {
        List<Estado> listE = PEstado.findEstadoEntities();
        for (int i = 0; i < listE.size(); i++) {
            cbestado.addItem(listE.get(i).getNombree());
        }
    }
     public void llenarG() {
        List<Genero> listG = PGenero.findGeneroEntities();
        for (int i = 0; i < listG.size(); i++) {
            cbgenero.addItem(listG.get(i).getDescripcion());
        }
    }
    private List<Cliente> buscarClie(String nombre) {
        EntityManager em = PClientes.getEntityManager();
        Query query = em.createQuery("SELECT c FROM Cliente c WHERE c.nombre LIKE :nombre");
        query.setParameter("nombre","%"+nombre+"%");
        List<Cliente> lista = query.getResultList();
        return lista;
    }

    public void llenarDir() {
        List<Colonia> ListC = PColonia.findColoniaEntities();
        List<Municipio> ListM = PMunicipio.findMunicipioEntities();
        List<Estado> ListE = PEstado.findEstadoEntities();
        int idc = 0, idm = 0, ide, idmm = 0;
        try {
            for (int i = 0; i < ListC.size(); i++) {
                 if(ListC.get(i).getNombre().equalsIgnoreCase(String.valueOf(cbcol.getSelectedItem()))){
                 idm=ListC.get(i).getIdmunicipio(); 
                 if(idm==0){
                  
                  cbestado.addItem("---");
                  
                 }
                     for (Municipio ListM1 : ListM) {
                         if(ListM1.getIdmunicipio().equals(idm)){
                             cbmuni.setSelectedItem(ListM1.getNombrem());
                             ide=ListM1.getIdestado();
                               for (Estado ListE1 : ListE) {
                                   if(ListE1.getIdestado().equals(ide)){
                                       cbestado.setSelectedItem(ListE1.getNombree());
                                   }
                               }
                         }
                     }         
                 }
            }
        } catch (Exception e) {
        }
      

    }

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        this.btnnuevo.setEnabled(false);
        habilitarC();
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        desabilitarC();
        limpiarC();
        this.btnnuevo.setEnabled(true);
    }//GEN-LAST:event_btncancelarActionPerformed

    private void btnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceptarActionPerformed
        if (valEntradas() == true) {
            Cliente cl = new Cliente();
            try {  
               
                int confirmar = JOptionPane.showConfirmDialog(null, "¿Datos correctos?", "", JOptionPane.YES_NO_OPTION);
                if (confirmar == JOptionPane.YES_OPTION) {
                
                cl.setIdcliente(Integer.parseInt(txtcodigo.getText()));
                cl.setNombre(txtnombre.getText());
                cl.setEdad(Integer.parseInt(txtedad.getText()));                 
                cl.setDireccion(cbcol.getSelectedItem()+","+cbmuni.getSelectedItem()+","+cbestado.getSelectedItem());
                if(!txttelefono.getText().equals("")){
                 cl.setTelefono(Integer.parseInt(txttelefono.getText())); 
                }
                cl.setGenero(cbgenero.getSelectedItem().toString());
                cl.setMail(txtemail.getText());
                List<Colonia>listc=PColonia.findColoniaEntities();
                int idcc=0;
                for(int i=0;i<listc.size();i++){
                    if(cbcol.getSelectedItem().equals(listc.get(i).getNombre())){
                      idcc=listc.get(i).getIdcolonia();
                       }
                 }
                cl.setIdcolonia(idcc);   
                PClientes.create(cl);
                ListarClientes("");
                limpiarC();
                desabilitarC(); 
                btnnuevo.setEnabled(true);
                
            }
               
        } catch (Exception e) {

        }
        }

    }//GEN-LAST:event_btnaceptarActionPerformed

    private void txtnombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyReleased
        this.txtnombre.setText(txtnombre.getText().toUpperCase());
    }//GEN-LAST:event_txtnombreKeyReleased

    private void txtedadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtedadKeyReleased
    }//GEN-LAST:event_txtedadKeyReleased

    private void txttelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoKeyReleased
     MetodosValidar.soloNumeros(txttelefono,13);    
    }//GEN-LAST:event_txttelefonoKeyReleased

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnsalirActionPerformed

    private void tbclientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbclientesMouseClicked
        EventMouseClick();
        particionarDom();
    }//GEN-LAST:event_tbclientesMouseClicked

    private void btnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarActionPerformed
         if (this.valEntradas() == true) {
              try {
                List<Colonia>listc=PColonia.findColoniaEntities();
                
                int idc=0,idcc=0;
                for(int i=0;i<listc.size();i++){
                    idc=listc.get(i).getIdcolonia();
                    if(cbcol.getSelectedItem().equals(listc.get(i).getNombre())){
                      idcc=listc.get(i).getIdcolonia();
                       }
                   
                }
               int confirmar=JOptionPane.showConfirmDialog(null,"¿MODIFICAR?","",JOptionPane.YES_NO_OPTION);
               if (confirmar == JOptionPane.YES_NO_OPTION) {
                Cliente cl = new Cliente();
                cl.setIdcliente(Integer.parseInt(txtcodigo.getText()));
                cl.setNombre(txtnombre.getText());
                cl.setEdad(Integer.parseInt(txtedad.getText()));
                cl.setDireccion(cbcol.getSelectedItem()+","+cbmuni.getSelectedItem()+","+cbestado.getSelectedItem());
                if(!txttelefono.getText().equals("")){
                cl.setTelefono(Integer.parseInt(txttelefono.getText()));    
                }                
                cl.setGenero(cbgenero.getSelectedItem().toString());
                cl.setMail(txtemail.getText());
                cl.setIdcolonia(idc);                 
                PClientes.edit(cl);
                ListarClientes("");
                limpiarC();
                desabilitarC();
                btnnuevo.setEnabled(true);
            }           
        } catch (Exception e) {

        }
        }

    }//GEN-LAST:event_btnmodificarActionPerformed

    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
   int fila =tbclientes.getSelectedRow();
        try {
            int confirmar = JOptionPane.showConfirmDialog(null, "¿ELIMINAR?", "", JOptionPane.YES_NO_OPTION);
            if (confirmar == JOptionPane.YES_NO_OPTION) {
                PClientes.destroy(Integer.parseInt(txtcodigo.getText()));
                ListarClientes("");
                this.limpiarC();
                desabilitarC();    
                btnnuevo.setEnabled(true);
          }
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(FProducto.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btneliminarActionPerformed

    private void txtnombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyTyped
       // MetodosValidar.soloLetrasNumeros(txtnombre, 100);
    }//GEN-LAST:event_txtnombreKeyTyped

    private void cbcolMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbcolMouseClicked

    }//GEN-LAST:event_cbcolMouseClicked

    private void cbcolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbcolActionPerformed
        llenarDir();
    }//GEN-LAST:event_cbcolActionPerformed

    private void txtbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyReleased
       txtbuscar.setText(txtbuscar.getText().toUpperCase());
        ListarClientes(txtbuscar.getText());

    }//GEN-LAST:event_txtbuscarKeyReleased

    private void txttelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelefonoKeyTyped
  char car= evt.getKeyChar();
    if((car <'0' || car > '9')){
    if(txttelefono.getText().length()<10){
       evt.consume();  
    }}       // TODO add your handling code here:
    }//GEN-LAST:event_txttelefonoKeyTyped

    private void txtcodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodigoKeyReleased
  MetodosValidar.soloNumeros(txtcodigo,6);
    }//GEN-LAST:event_txtcodigoKeyReleased

    private void txtemailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtemailKeyTyped
    char car= evt.getKeyChar();
    if((car <'0' || car > '9') && (car < 'A' || car> 'Z') && (car < 'a' || car> 'z') && (car < '@'|| car> '@') && (car < '_'  || car > '_') && (car < '.' || car > '.')){        
    evt.consume();
    }       // TODO add your handling code here:
    }//GEN-LAST:event_txtemailKeyTyped

    private void txtedadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtedadKeyTyped
    MetodosValidar.soloNumeros(txtedad,3);       // TODO add your handling code here:
    }//GEN-LAST:event_txtedadKeyTyped
    public void particionarDom(){
        String c,m,e;
        int filaS=tbclientes.getSelectedRow();
        String cadena =tbclientes.getValueAt(filaS,3).toString() ;   
        String[] Part = cadena.split(",");   
        for (int i = 0; i< Part.length; i++)    {     
            c=Part[0];
            m=Part[1];
            e=Part[2];
            cbcol.setSelectedItem(c);
            cbmuni.setSelectedItem(m);
            cbestado.setSelectedItem(e);
        } 
    
}
    public void EventMouseClick() {
        int filas = tbclientes.getSelectedRow();
        if (filas >= 0) {
           
            this.habilitarC();
            this.btnaceptar.setEnabled(false);
            this.btnmodificar.setEnabled(true);
            this.btneliminar.setEnabled(true);
            this.btnnuevo.setEnabled(false);
            this.txtcodigo.setText(tbclientes.getValueAt(filas, 0).toString());
            this.txtnombre.setText(tbclientes.getValueAt(filas, 1).toString());
            this.txtedad.setText(tbclientes.getValueAt(filas, 2).toString());
            if(tbclientes.getValueAt(filas,3).toString().equals("")){
                
            }else{
             llenarDir();   
            }            
            if(tbclientes.getValueAt(filas, 4).toString().equals("")){
                this.txttelefono.setText("ddd");
               
            }else{
               this.txttelefono.setText(tbclientes.getValueAt(filas, 4).toString());    
            }
            cbgenero.setSelectedItem(tbclientes.getValueAt(filas,5).toString());
            txtemail.setText(tbclientes.getValueAt(filas,6).toString());

        }
    }//EventoDeMouseClicked

   
    public void desabilitarC() {
        this.lblcodigo.setEnabled(false);
        this.lblnombre.setEnabled(false);
        this.lbledad.setEnabled(false);
        this.lbldireccion.setEnabled(false);
        this.lbltelefono.setEnabled(false);
        lblgenero.setEnabled(false);
        lblemail.setEnabled(false);
        this.btnaceptar.setEnabled(false);
        this.btncancelar.setEnabled(false);
        this.btnmodificar.setEnabled(false);
        this.btneliminar.setEnabled(false);
        this.txtcodigo.setEnabled(false);
        this.txtnombre.setEnabled(false);
        this.txtedad.setEnabled(false);
        this.txttelefono.setEnabled(false);
        cbgenero.setEnabled(false);
        txtemail.setEnabled(false);
        cbcol.setEnabled(false);
        cbmuni.setEnabled(false);
        cbestado.setEnabled(false);

    }

    public void habilitarC() {
        this.lblcodigo.setEnabled(true);
        this.lblnombre.setEnabled(true);
        this.lbledad.setEnabled(true);
        this.lbldireccion.setEnabled(true);
        this.lbltelefono.setEnabled(true);
        lblgenero.setEnabled(true);
        lblemail.setEnabled(true);
        this.btnaceptar.setEnabled(true);
        this.btncancelar.setEnabled(true);
        this.btnmodificar.setEnabled(false);
        this.btneliminar.setEnabled(false);
        this.txtcodigo.setEnabled(true);
        this.txtnombre.setEnabled(true);
        this.txtedad.setEnabled(true);
        this.txttelefono.setEnabled(true);
        cbgenero.setEnabled(true);
        txtemail.setEnabled(true);
        cbcol.setEnabled(true);
        cbmuni.setEnabled(true);
        cbestado.setEnabled(true);

    }

    public void limpiarC() {
//        this.Generarnumeracion();
        this.txtnombre.setText("");
        this.txtedad.setText("");
        this.txttelefono.setText("");
        txtcodigo.setText("");
        cbgenero.setSelectedIndex(0);
        txtemail.setText("");
        cbcol.setSelectedIndex(0);
        cbmuni.setSelectedIndex(0);
        cbestado.setSelectedIndex(0);
    }

    public boolean valEntradas() {
        String mensaje = "";
        boolean estado = true;
        if (txtcodigo.getText().isEmpty()) {
            mensaje += "ID NO DEBE ESTAR VACIO \n";
            estado = false;
        }
        if (txtnombre.getText().isEmpty()) {
            mensaje += "NO SE INSERTO UN NOMBRE VALIDO \n";
            estado = false;
        }
         if (txtedad.getText().isEmpty() || Integer.parseInt(txtedad.getText())>100 ||  Integer.parseInt(txtedad.getText())<15) {
            mensaje += "EDAD DEBE SER MAYOR A 15 Y MENOR A 100 \n";
            estado = false;
        }
         if (cbgenero.getSelectedIndex()==0) {
            mensaje += "NO SE SELECCIONO GENERO \n";
            estado = false;
        }
        if(txtemail.getText().isEmpty()==false && valcorreo()==false){
               mensaje+="VERIFICA DIRECCION DE CORREO @";
            estado = false;
        }
        if (mensaje.length() >= 6) {
             JOptionPane.showMessageDialog(null, mensaje, "", JOptionPane.WARNING_MESSAGE);

        }
       
        return estado;
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
            java.util.logging.Logger.getLogger(FClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FClientes().setVisible(true);
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
    private javax.swing.JComboBox<String> cbcol;
    private javax.swing.JComboBox<String> cbestado;
    private javax.swing.JComboBox<String> cbgenero;
    private javax.swing.JComboBox<String> cbmuni;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblcodigo;
    private javax.swing.JLabel lbldireccion;
    private javax.swing.JLabel lbledad;
    private javax.swing.JLabel lblemail;
    private javax.swing.JLabel lblgenero;
    private javax.swing.JLabel lblnombre;
    private javax.swing.JLabel lbltelefono;
    private javax.swing.JTable tbclientes;
    private javax.swing.JTextField txtbuscar;
    private javax.swing.JTextField txtcodigo;
    private javax.swing.JTextField txtedad;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JTextField txttelefono;
    // End of variables declaration//GEN-END:variables
}
