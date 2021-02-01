/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Complements;

import Conexion.ConnectionDB;
import controladores.ProductosJpaController;
import controladores.ProveedorJpaController;
import controladores.UnidadesmJpaController;
import controladores.exceptions.NonexistentEntityException;
import entidades.Productos;
import entidades.Proveedor;
import entidades.Unidadesm;
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
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JSpinner;

/**
 *
 * @author Sistemas
 */
public class FProducto extends javax.swing.JFrame {
    
    DefaultComboBoxModel dfmu = new DefaultComboBoxModel();
    DefaultComboBoxModel dfmp = new DefaultComboBoxModel();
    DefaultTableModel dtm = new DefaultTableModel();//Cree instancia de una tabla
    String varPub = "", msjbd = "DATOS NO ENCONTRADOS";
    int idp = 0;
    ProductosJpaController CProd = new ProductosJpaController();
     ConnectionDB conectar = new ConnectionDB();
    Connection connect = conectar.ConnectionDB();
    
    public FProducto() {
        initComponents();
        buscarProd("");
        ListarProductos("");
        llenarUnidades();
        llenarProveedores();
        desabPanCon();
        this.btnnuevo.requestFocusInWindow();
        this.btnmodificar.setEnabled(false);
        this.btneliminar.setEnabled(false);
        padquisicion.setVisible(false);
        this.setLocationRelativeTo(null);
             
    }
    
    
       public void llenarUnidades() {
       List<Unidadesm> lista;
       UnidadesmJpaController emu=new UnidadesmJpaController();
       lista =emu.findUnidadesmEntities();
        for (int i = 0; i < lista.size(); i++) {
            cbunidad.addItem(lista.get(i).getDescripcion());
         }
       }
       public void llenarProveedores() {
       List<Proveedor> lista2;
       ProveedorJpaController emu=new ProveedorJpaController();
       lista2 =emu.findProveedorEntities();
        for (int i = 0; i < lista2.size(); i++) {
            cbproveedor.addItem(lista2.get(i).getNombre());
        }
    }
    public void ListarProductos( String nombre) {       
        String[] titulos = {"COD.PROD", "NOMBRE", "U.MEDIDA", "P.PUBLICO", "P.CLIENTE", "PROVEEDOR", "EXISTENCIAS","P.ADQUIRIDO"};
        dtm = new DefaultTableModel(null, titulos);
               try {
            Object o[] = null;
            List<Productos> listp = buscarProd(nombre);
            
            for (int i = 0; i < listp.size(); i++) {    
                dtm.addRow(o);
                dtm.setValueAt(listp.get(i).getIdproducto(), i, 0);
                dtm.setValueAt(listp.get(i).getNombre(), i, 1);
                dtm.setValueAt(listp.get(i).getUmedidap(), i, 2);
                dtm.setValueAt(listp.get(i).getPrecioc(), i, 3);
                dtm.setValueAt(listp.get(i).getPreciop(), i, 4);
                dtm.setValueAt(listp.get(i).getProveedor(), i, 5);
                dtm.setValueAt(listp.get(i).getStock(), i, 6);
                dtm.setValueAt(listp.get(i).getPadquicision(), i, 7);
                
            }
            tbproductos.setModel(dtm);
            listp.clear();
       
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());

        }
               
    }
    
    private List<Productos> buscarProd(String nombre) {
        EntityManager em = CProd.getEntityManager();
        List<Productos>lista=null;
        Query query=em.createQuery("SELECT p FROM Productos p WHERE p.nombre LIKE :nombre");
        query.setParameter("nombre","%"+nombre+"%");
        lista=query.getResultList();
        return lista;
    }
    public void desabPanCon() {
        jLabel1.setEnabled(false);
        jLabel6.setEnabled(false);
        jLabel7.setEnabled(false);
        jLabel8.setEnabled(false);
        jLabel9.setEnabled(false);
        jLabel10.setEnabled(false);
        jLabel11.setEnabled(false);
        txtcod.setEnabled(false);
        txtnombre.setEnabled(false);
        cbunidad.setEnabled(false);
        sppcliente.setEnabled(false);
        spppublico.setEnabled(false);
        spcantidad.setEnabled(false);
        cbproveedor.setEnabled(false);
        btnaceptar.setEnabled(false);
        btncancelar.setEnabled(false);
    }

    public void habPanCon() {
        jLabel1.setEnabled(true);
        jLabel6.setEnabled(true);
        jLabel7.setEnabled(true);
        jLabel8.setEnabled(true);
        jLabel9.setEnabled(true);
        jLabel10.setEnabled(true);
        jLabel11.setEnabled(true);
        txtcod.setEnabled(true);
        txtnombre.setEnabled(true);
        cbunidad.setEnabled(true);
        sppcliente.setEnabled(true);
        spppublico.setEnabled(true);
        spcantidad.setEnabled(true);
        cbproveedor.setEnabled(true);
        btnaceptar.setEnabled(true);
        btncancelar.setEnabled(true);

    }

    public void hablitarUISP() {
        ((JSpinner.DefaultEditor) sppcliente.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) spppublico.getEditor()).getTextField().setEditable(false);
        ((JSpinner.DefaultEditor) spcantidad.getEditor()).getTextField().setEditable(false);
    }//MetodoParaHablitarEditorSpiners

    public boolean valEntradas() {

        String mensaje = "";
        boolean estado = true;
        Double pcliente = Double.parseDouble(sppcliente.getValue().toString());
        Double ppublico = Double.parseDouble(spppublico.getValue().toString());
        Double cantidad = Double.parseDouble(spcantidad.getValue().toString());
        if (this.txtcod.getText().isEmpty() == true) {
            mensaje += "NO SE GENERO CODIGO \n";
            estado = false;
        }
        if (txtnombre.getText().isEmpty() == true) {
            mensaje += "NO SE INSERTO NOMBRE \n";
            estado = false;
        }
        if (cbunidad.getSelectedIndex() == 0) {
            mensaje += "NO SE SELECCIONO UNIDAD DE MEDIDA \n";
            estado = false;
        }
        if (pcliente <= 0) {
            mensaje += "PRECIO CLIENTE DEBE SER MAYOR A CERO \n";
            sppcliente.setValue(0);
            estado = false;
        }
        if (ppublico <= 0) {
            mensaje += "PRECIO PUBLICO DEBE SER MAYOR A CERO \n";
            spppublico.setValue(0);
            estado = false;
        }
        if (cantidad <= 0) {
            mensaje += "CANTIDAD DEBE SER MAYOR A CERO \n";
            spcantidad.setValue(0);
            estado = false;
        }
        if (cbproveedor.getSelectedIndex() == 0) {
            mensaje += "NO SE SELECCIONO PROVEEDOR \n";
            estado = false;
        }

        if (mensaje.length() >= 4) {
            JOptionPane.showMessageDialog(null, mensaje, "", JOptionPane.WARNING_MESSAGE);

        }
        return estado;

    }//MetodoParaValidarCajasDeTexto

    public void limpiarC() {//MetodoLimpiarCajaasDeTexto
        txtnombre.setText("");
        cbunidad.setSelectedIndex(0);
        sppcliente.setValue(0);
        spppublico.setValue(0);
        spcantidad.setValue(0);
        cbproveedor.setSelectedIndex(0);
        this.btnmodificar.setEnabled(false);
        this.btneliminar.setEnabled(false);
    }

    public void EventoTbProductMC() {
        int fseleccionada = tbproductos.getSelectedRow();
        if (fseleccionada >= 0) {
            habPanCon();
            this.btnmodificar.setEnabled(true);
            this.btneliminar.setEnabled(true);
            txtcod.setText(tbproductos.getValueAt(fseleccionada, 0).toString());
            txtnombre.setText(tbproductos.getValueAt(fseleccionada, 1).toString());
            sppcliente.setValue(Double.parseDouble(tbproductos.getValueAt(fseleccionada, 3).toString()));
            spppublico.setValue(Double.parseDouble(tbproductos.getValueAt(fseleccionada, 4).toString()));
            spcantidad.setValue(Integer.parseInt(tbproductos.getValueAt(fseleccionada, 6).toString()));
            cbproveedor.setSelectedItem(tbproductos.getValueAt(fseleccionada, 5).toString());
            cbunidad.setSelectedItem(tbproductos.getValueAt(fseleccionada, 2).toString());
            padquisicion.setText(tbproductos.getValueAt(fseleccionada,7).toString());
            btnaceptar.setEnabled(false);
            btnnuevo.setEnabled(false);
        }

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlDatos = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnaceptar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        pcontenido = new javax.swing.JPanel();
        txtnombre = new javax.swing.JTextField();
        sppcliente = new javax.swing.JSpinner();
        cbunidad = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtcod = new javax.swing.JTextField();
        spppublico = new javax.swing.JSpinner();
        spcantidad = new javax.swing.JSpinner();
        cbproveedor = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        plista = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnnuevo = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        btnmodificar = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbproductos = new javax.swing.JTable();
        padquisicion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        pnlDatos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        btnaceptar.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnaceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/aceptar.png"))); // NOI18N
        btnaceptar.setText("Aceptar");
        btnaceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaceptarActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btncancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnaceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnaceptar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(btncancelar)
                .addContainerGap())
        );

        txtnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtnombreKeyReleased(evt);
            }
        });

        sppcliente.setModel(new javax.swing.SpinnerNumberModel(0.0d, null, null, 1.0d));

        cbunidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---" }));
        cbunidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbunidadActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel1.setText("Codigo");

        jLabel7.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel7.setText("Nombre");

        jLabel8.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel8.setText("Unidad Medida");

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel6.setText("Precio Cliente");

        txtcod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcodKeyReleased(evt);
            }
        });

        spppublico.setModel(new javax.swing.SpinnerNumberModel(0.0d, null, null, 1.0d));

        cbproveedor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---" }));

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel9.setText("Precio Publico");

        jLabel11.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel11.setText("Cantidad");

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel10.setText("Proveedor");

        javax.swing.GroupLayout pcontenidoLayout = new javax.swing.GroupLayout(pcontenido);
        pcontenido.setLayout(pcontenidoLayout);
        pcontenidoLayout.setHorizontalGroup(
            pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pcontenidoLayout.createSequentialGroup()
                .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pcontenidoLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel11)
                            .addGroup(pcontenidoLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(3, 3, 3))
                            .addComponent(jLabel10)))
                    .addGroup(pcontenidoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pcontenidoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9)
                        .addGap(3, 3, 3)))
                .addGap(30, 30, 30)
                .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbproveedor, 0, 435, Short.MAX_VALUE)
                    .addComponent(spcantidad)
                    .addComponent(spppublico)
                    .addComponent(sppcliente)
                    .addComponent(cbunidad, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtnombre)
                    .addComponent(txtcod))
                .addGap(21, 21, 21))
        );
        pcontenidoLayout.setVerticalGroup(
            pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pcontenidoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbunidad, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sppcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spppublico, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spcantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pcontenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbproveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlDatosLayout = new javax.swing.GroupLayout(pnlDatos);
        pnlDatos.setLayout(pnlDatosLayout);
        pnlDatosLayout.setHorizontalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDatosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pcontenido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        pnlDatosLayout.setVerticalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDatosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pcontenido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        plista.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        plista.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel3.setText("BUSCAR");

        btnnuevo.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        btnnuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/nuevo.png"))); // NOI18N
        btnnuevo.setText("NUEVO");
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
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

        btnsalir.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/salir ventana.png"))); // NOI18N
        btnsalir.setText("Salir");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        tbproductos.setBackground(new java.awt.Color(204, 204, 255));
        tbproductos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tbproductos = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tbproductos.setModel(new javax.swing.table.DefaultTableModel(
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
        tbproductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbproductosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbproductos);

        padquisicion.setText("jLabel2");

        javax.swing.GroupLayout plistaLayout = new javax.swing.GroupLayout(plista);
        plista.setLayout(plistaLayout);
        plistaLayout.setHorizontalGroup(
            plistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plistaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(plistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(plistaLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtBuscar))
                    .addComponent(jScrollPane2)
                    .addGroup(plistaLayout.createSequentialGroup()
                        .addComponent(btnnuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnmodificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btneliminar)
                        .addGap(31, 31, 31)
                        .addComponent(padquisicion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnsalir)))
                .addContainerGap())
        );
        plistaLayout.setVerticalGroup(
            plistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(plistaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(plistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(plistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsalir)
                    .addComponent(btneliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnmodificar)
                    .addComponent(btnnuevo)
                    .addComponent(padquisicion))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(plista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(plista, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceptarActionPerformed
        Productos pr = new Productos();
        Double pad=0.0;
        int confirmar2=JOptionPane.showConfirmDialog(null,"¿INGRESAR PRECIO DE AQUISICION?","",JOptionPane.YES_NO_OPTION);
        if(confirmar2==JOptionPane.YES_OPTION){
        pad=Double.parseDouble(JOptionPane.showInputDialog("PRECIO AQUIRIDO"));
        }else{
            pad=0.0;
        }
        
        try {
           if (valEntradas() == true) {
           int confirmar=JOptionPane.showConfirmDialog(null,"¿SUS DATOS SON CORRECTOS?","",JOptionPane.YES_NO_OPTION); 
                if(confirmar==JOptionPane.YES_OPTION){ 
                pr.setIdproducto(Integer.parseInt(txtcod.getText()));
                pr.setNombre(txtnombre.getText());
                pr.setUmedidap(cbunidad.getSelectedItem().toString());
                pr.setPreciop(Double.parseDouble(spppublico.getValue().toString()));
                pr.setPrecioc(Double.parseDouble(sppcliente.getValue().toString()));
                pr.setStock(Integer.parseInt(spcantidad.getValue().toString()));
                pr.setProveedor(cbproveedor.getSelectedItem().toString());
                if(pad>0){
                 pr.setPadquicision(pad);   
                }
                else{
                    pr.setPadquicision(0.0);
                }
                CProd.create(pr);  
               }
            }
            limpiarC();
            ListarProductos("");
        } catch (Exception e) {

        }


    }//GEN-LAST:event_btnaceptarActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
        desabPanCon();
        limpiarC();
        this.btnnuevo.setEnabled(true);
        this.btnmodificar.setEnabled(false);
        this.btneliminar.setEnabled(false);

    }//GEN-LAST:event_btncancelarActionPerformed

    private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
        habPanCon();
    }//GEN-LAST:event_btnnuevoActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        ListarProductos(txtBuscar.getText());
        txtBuscar.setText(this.txtBuscar.getText().toUpperCase());

    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmodificarActionPerformed
        Double pad = 0.0;
        int confirmar2=JOptionPane.showConfirmDialog(null,"¿MODIFICAR PRECIO DE AQUISICION?","",JOptionPane.YES_NO_OPTION);
        if(confirmar2==JOptionPane.YES_NO_OPTION){
               pad=Double.parseDouble(JOptionPane.showInputDialog("PRECIO AQUIRIDO"));
        }else{
           pad=0.0;
        }
        int confirmar=JOptionPane.showConfirmDialog(null,"¿MODIFICAR?","",JOptionPane.YES_NO_OPTION);
        if (valEntradas() == true) {
            try {
                if (valEntradas() == true) {
                    Productos pr = new Productos();
                    pr.setIdproducto(Integer.parseInt(txtcod.getText()));
                    pr.setNombre(txtnombre.getText());
                    pr.setUmedidap(cbunidad.getSelectedItem().toString());
                    pr.setPreciop(Double.parseDouble(spppublico.getValue().toString()));
                    pr.setPrecioc(Double.parseDouble(sppcliente.getValue().toString()));
                    pr.setStock(Integer.parseInt(spcantidad.getValue().toString()));
                    pr.setProveedor(cbproveedor.getSelectedItem().toString());
                    if(pad>0.0){
                         pr.setPadquicision(pad);
                    }else{
                        pr.setPadquicision(Double.parseDouble(padquisicion.getText()));
                    }
                   
                    if(confirmar==JOptionPane.YES_NO_OPTION){
                    CProd.edit(pr);
                    this.limpiarC();
                    this.desabPanCon();
                    this.btnmodificar.setEnabled(false);
                    this.btneliminar.setEnabled(false);
                    this.btnnuevo.setEnabled(true);
                    this.btnaceptar.setEnabled(false);
                    txtcod.setText("");
                    ListarProductos("");
                    }
                  }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR AL MODIFICAR LOS REGISTROS");
            }
    }//GEN-LAST:event_btnmodificarActionPerformed
    }
    private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
        int confirmar=JOptionPane.showConfirmDialog(null,"¿ELIMINAR?","",JOptionPane.YES_NO_OPTION);
        int fila = tbproductos.getSelectedRow();
        try {                 
               if (confirmar == JOptionPane.YES_NO_OPTION) {
                CProd.destroy(Integer.parseInt(txtcod.getText()));
                ListarProductos("");
                this.limpiarC();
                
                this.desabPanCon();
                this.btnmodificar.setEnabled(false);
                this.btneliminar.setEnabled(false);
                this.btnnuevo.setEnabled(true);
                this.btnaceptar.setEnabled(false);
                txtcod.setText("");
            }
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(FProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btneliminarActionPerformed

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
       dispose();
    }//GEN-LAST:event_btnsalirActionPerformed

    private void txtnombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombreKeyReleased
       
        this.txtnombre.setText(this.txtnombre.getText().toUpperCase());
    }//GEN-LAST:event_txtnombreKeyReleased

    private void tbproductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbproductosMouseClicked
        EventoTbProductMC();
    }//GEN-LAST:event_tbproductosMouseClicked

    private void cbunidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbunidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbunidadActionPerformed

    private void txtcodKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcodKeyReleased
   MetodosValidar.soloNumeros(txtcod,6);     
    }//GEN-LAST:event_txtcodKeyReleased
   

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
            java.util.logging.Logger.getLogger(FProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FProducto().setVisible(true);
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
    private javax.swing.JComboBox<String> cbproveedor;
    private javax.swing.JComboBox<String> cbunidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel padquisicion;
    private javax.swing.JPanel pcontenido;
    private javax.swing.JPanel plista;
    private javax.swing.JPanel pnlDatos;
    private javax.swing.JSpinner spcantidad;
    private javax.swing.JSpinner sppcliente;
    private javax.swing.JSpinner spppublico;
    public javax.swing.JTable tbproductos;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtcod;
    public javax.swing.JTextField txtnombre;
    // End of variables declaration//GEN-END:variables
}
