package Complements;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Conexion.ConnectionDB;
import controladores.ProductosJpaController;
import controladores.TipovJpaController;
import entidades.Tipov;
import entidades.Ventas;
import java.awt.event.WindowEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FVentas extends javax.swing.JFrame implements Runnable {
    String codCliente = "0000000";//variable q acumula el codigo del cliente cuando se selecciona y misma que nos sirve para validar nombre al insertar venta
    ConnectionDB conectar = new ConnectionDB();
    Connection connect = conectar.ConnectionDB();
    DefaultComboBoxModel dfm = new DefaultComboBoxModel();
    DefaultTableModel tbuni = new DefaultTableModel();

    DefaultTableModel tbuni2 = new DefaultTableModel();
    CallableStatement cts = null;
    ResultSet r = null;
    String varPub = "";
    public boolean ac = false;
    String part1 = "", part2 = "";

    String hora, minutos, segundos, ampm;
    Calendar calendario;
    Thread h1;

    public FVentas() {
        initComponents();        
        this.setLocationRelativeTo(null);
        this.jdFecha.setDate(Calendar.getInstance().getTime());
        dfm.addElement("SELECCIONE");
        tbuni.addColumn("ID");
        tbuni.addColumn("PRODUCTO");
        tbuni.addColumn("U.MEDIDA");
        tbuni.addColumn("PRECIO");
        tbuni.addColumn("CANTIDAD");
        tbuni.addColumn("SUBTOTAL");
        llenarCB();
        btnAceptar.setEnabled(false);
        txtCliente.setText("XXXXXXXXXXXXXXXXXXXXXXXXX");
        jPanel4.setVisible(false);
        btnep.setEnabled(false);
        obtenerf();
       

    }

    @Override
    public void run() {
        Thread ct = Thread.currentThread();

        while (ct == h1) {
            calcula();
            lblrelojito.setText(hora + ":" + minutos + ":" + segundos + " " + ampm);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }
    }

    private void calcula() {
        Calendar calendario = new GregorianCalendar();
        Date fechaHoraActual = new Date();
        calendario.setTime(fechaHoraActual);
        ampm = calendario.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
        if (ampm.equals("PM")) {
            int h = calendario.get(Calendar.HOUR_OF_DAY) - 12;
            hora = h > 9 ? "" + h : "0" + h;
        } else {
            hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY) : "0" + calendario.get(Calendar.HOUR_OF_DAY);
        }
        if (Integer.parseInt(hora) == 00) {
            hora = "12";
        }
        minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);

    }

    public void obtenerf() {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = new GregorianCalendar();
        String dia = Integer.toString(c2.get(Calendar.DATE));
        int mes = Integer.parseInt(Integer.toString(c2.get(Calendar.MONTH))) + 1;
        String annio = Integer.toString(c2.get(Calendar.YEAR));
        String mesr = String.valueOf(mes);
        if ((Integer.parseInt(dia) < 10)) {
            dia = "0" + Integer.toString(c2.get(Calendar.DATE));
        }
        if (mes < 10) {
            mesr = "0" + String.valueOf(mes);
        }

        lblfecha.setText(dia + "/" + mesr + "/" + annio);

    }

    /*-----------------------------Metodo para llenar combo de tipos de ventas-------------------*/
    public void llenarCB() {
    TipovJpaController DetallesV = new TipovJpaController();
        List<Tipov> listV = DetallesV.findTipovEntities();
        try {
          for (int i = 0; i < listV.size(); i++) {
            cbTipoVenta.addItem(listV.get(i).getDescr());
        }   
        } catch (Exception e) {
            DetallesV.cerrar();
            System.out.println("Error:"+e.getMessage());
           
        }finally{DetallesV.cerrar();}
       
    }

    public void cargarTBC(String valorB) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("CODIGO");
        modelo.addColumn("NOMBRES");
        modelo.addColumn("DIRECCION");
        modelo.addColumn("TELEFONO");

        this.tbClientes1.setModel(modelo);
        String sql = "";
        if (valorB.equals("")) {
            sql = "SELECT idcliente,nombre,direccion,telefono FROM cliente";
        } else {
            sql = "SELECT idcliente,nombre,direccion,telefono FROM cliente WHERE nombre LIKE'%" + valorB + "%'";
        }
        String datos[] = new String[5];
        Statement st;
        try {
            st = (Statement) connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                modelo.addRow(datos);
            }
            this.tbClientes1.setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR AL CARGAR DATOS CLIENTES", "", JOptionPane.ERROR_MESSAGE);

        }
    }

    public void cargarTBP(String valorB) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("CODIGO");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("U.MEDIDA");
        modelo.addColumn("P.PUBLICO");
        modelo.addColumn("P.CLIENTE");
        modelo.addColumn("EXISTENCIAS");

        this.tbProductos.setModel(modelo);
        String sql = "";
        if (valorB.equals("")) {
            sql = "SELECT idproducto,nombre,umedidap,preciop,precioc,stock FROM productos";
        } else {
            sql = "SELECT idproducto,nombre,umedidap,preciop,precioc,stock FROM productos WHERE nombre LIKE'%" + valorB + "%'";
        }
        String datos[] = new String[8];
        Statement st;
        try {
            st = (Statement) connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                modelo.addRow(datos);
            }
            this.tbProductos.setModel(modelo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR AL CARGAR DATOS PRODUCTOS", "", JOptionPane.ERROR_MESSAGE);

        }
    }

    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogClientes = new javax.swing.JDialog();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtclientesb = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbClientes1 = new javax.swing.JTable();
        btnclientes = new javax.swing.JButton();
        jDialogProducto = new javax.swing.JDialog();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtProductoB = new javax.swing.JTextField();
        btnsalir = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbProductos = new javax.swing.JTable();
        btnaddProd = new javax.swing.JButton();
        pnlDatos = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jdFecha = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        cbTipoVenta = new javax.swing.JComboBox();
        btnbprod = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        btnep = new javax.swing.JButton();
        txtCodProd2 = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnlDatos1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbticket = new javax.swing.JTable();
        txtSubTotal = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCambio = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtEfect = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        lblfecha = new javax.swing.JLabel();
        lblrelojito = new javax.swing.JLabel();
        lblfechar = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel1.setText("BUSCAR");

        txtclientesb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtclientesbKeyReleased(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/salir ventana.png"))); // NOI18N
        jButton2.setText("SALIR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(153, 0, 255));

        jLabel5.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("SELECCION DE CLIENTES");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(jLabel5)
                .addContainerGap(153, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addContainerGap())
        );

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        tbClientes1 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tbClientes1.setModel(new javax.swing.table.DefaultTableModel(
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
        tbClientes1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbClientes1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbClientes1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnclientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/gestiones-menu.png"))); // NOI18N
        btnclientes.setText("CLIENTES");
        btnclientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclientesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtclientesb, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnclientes)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtclientesb, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnclientes, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout jDialogClientesLayout = new javax.swing.GroupLayout(jDialogClientes.getContentPane());
        jDialogClientes.getContentPane().setLayout(jDialogClientesLayout);
        jDialogClientesLayout.setHorizontalGroup(
            jDialogClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
        );
        jDialogClientesLayout.setVerticalGroup(
            jDialogClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jLabel9.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel9.setText("BUSCAR");

        txtProductoB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProductoBKeyReleased(evt);
            }
        });

        btnsalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/salir ventana.png"))); // NOI18N
        btnsalir.setText("SALIR");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(153, 0, 255));

        jLabel10.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("SELECCION DE PRODUCTOS");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        tbProductos = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tbProductos.setModel(new javax.swing.table.DefaultTableModel(
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
        tbProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbProductosMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbProductos);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnaddProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/gestiones-menu.png"))); // NOI18N
        btnaddProd.setText("Productos");
        btnaddProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddProdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnsalir)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtProductoB, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnaddProd)))))
                .addGap(19, 19, 19))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProductoB, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnaddProd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnsalir, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout jDialogProductoLayout = new javax.swing.GroupLayout(jDialogProducto.getContentPane());
        jDialogProducto.getContentPane().setLayout(jDialogProductoLayout);
        jDialogProductoLayout.setHorizontalGroup(
            jDialogProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jDialogProductoLayout.setVerticalGroup(
            jDialogProductoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setForeground(new java.awt.Color(51, 0, 51));
        setResizable(false);

        pnlDatos.setBackground(new java.awt.Color(0, 153, 153));
        pnlDatos.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("CLIENTE");

        jLabel6.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("FECHA");

        jdFecha.setDate(Calendar.getInstance().getTime());
        jdFecha.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("PRODUCTO");

        txtCliente.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        txtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtClienteKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("TIPO VENTA");

        cbTipoVenta.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        cbTipoVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTipoVentaActionPerformed(evt);
            }
        });

        btnbprod.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Find.png"))); // NOI18N
        btnbprod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbprodActionPerformed(evt);
            }
        });

        btnep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/eliminar.png"))); // NOI18N
        btnep.setText("ELIMINAR REGISTRO");
        btnep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnepActionPerformed(evt);
            }
        });

        txtCodProd2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtCodProd2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodProd2KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodProd2KeyReleased(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(0, 102, 102));

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("AGROVETERINARIA EL CAMPO, MAYOREO Y MENUDEO");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel14)
                .addContainerGap(39, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addContainerGap())
        );

        javax.swing.GroupLayout pnlDatosLayout = new javax.swing.GroupLayout(pnlDatos);
        pnlDatos.setLayout(pnlDatosLayout);
        pnlDatosLayout.setHorizontalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatosLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbTipoVenta, 0, 300, Short.MAX_VALUE)
                    .addComponent(txtCliente))
                .addGap(36, 36, 36)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addComponent(txtCodProd2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnbprod, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jdFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addComponent(jLabel12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDatosLayout.createSequentialGroup()
                .addContainerGap(182, Short.MAX_VALUE)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDatosLayout.createSequentialGroup()
                        .addComponent(btnep)
                        .addGap(315, 315, 315))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDatosLayout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(131, 131, 131))))
        );
        pnlDatosLayout.setVerticalGroup(
            pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDatosLayout.createSequentialGroup()
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDatosLayout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(btnbprod))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDatosLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbTipoVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodProd2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23)
                .addComponent(btnep))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDatosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );

        btnAceptar.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Print.png"))); // NOI18N
        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/salir ventana.png"))); // NOI18N
        btnCancelar.setText("SALIR");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        pnlDatos1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        tbticket.setBackground(new java.awt.Color(255, 255, 204));
        tbticket.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        tbticket = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        tbticket.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tbticket.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbticketMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbticket);

        javax.swing.GroupLayout pnlDatos1Layout = new javax.swing.GroupLayout(pnlDatos1);
        pnlDatos1.setLayout(pnlDatos1Layout);
        pnlDatos1Layout.setHorizontalGroup(
            pnlDatos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatos1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        pnlDatos1Layout.setVerticalGroup(
            pnlDatos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatos1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                .addContainerGap())
        );

        txtSubTotal.setEditable(false);
        txtSubTotal.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        txtSubTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSubTotal.setText("0.0");

        jLabel7.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel7.setText("SUB-TOTAL");

        txtCambio.setEditable(false);
        txtCambio.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        txtCambio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCambio.setText("0.0");

        jLabel8.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel8.setText("EFECTIVO");

        jLabel11.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jLabel11.setText("CAMBIO");

        txtEfect.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtEfect.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtEfect.setText("0.0");
        txtEfect.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEfectKeyPressed(evt);
            }
        });

        lblfecha.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblfecha.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblfecha.setText("f");

        lblrelojito.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblrelojito.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblrelojito.setText("jLabel9");

        lblfechar.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblfechar.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        lblfechar.setText("Fecha :");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblrelojito, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(lblfechar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblfecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblfechar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblrelojito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 190, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(205, 205, 205)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel11)
                    .addComponent(jLabel8))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(btnAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSubTotal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEfect, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCambio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addComponent(pnlDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlDatos1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(pnlDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlDatos1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtEfect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCambio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelar)))
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void cbTipoVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTipoVentaActionPerformed
        if (this.cbTipoVenta.getSelectedItem().equals("CLIENTE")) {
            cargarTBC("");
            FVentas fv = new FVentas();
            try {
                fv.setAlwaysOnTop(true);
                JDialog dialogov = new JDialog(jDialogClientes, "PANEL DE CLIENTES", true);
                dialogov.add(jDialogClientes.getContentPane());
                dialogov.setSize(503, 400);
                dialogov.setLocationRelativeTo(null);
                dialogov.setVisible(true);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "ERROR AL CONECTAR A LA BASE DE DATOS", "", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            txtCliente.setEditable(true);
        }
    }//GEN-LAST:event_cbTipoVentaActionPerformed

    private void txtClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtClienteKeyReleased
        this.txtCliente.setText(this.txtCliente.getText().toUpperCase());
    }//GEN-LAST:event_txtClienteKeyReleased

    private void btnbprodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbprodActionPerformed
        cargarTBP("");
        FVentas fv = new FVentas();
        try {
            fv.setAlwaysOnTop(true);
            JDialog dialogov = new JDialog(jDialogProducto, "PANEL DE PRODUCTOS", true);
            dialogov.add(jDialogProducto.getContentPane());
            dialogov.setSize(556, 400);
            dialogov.setLocationRelativeTo(null);
            dialogov.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL CONECTAR A LA BASE DE DATOS", "", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnbprodActionPerformed
    void venta() {//Metodo para calcular el total de la venta
        double total = 0;
        double a;
        double b = 0, tigv = 0, t = 0;
        for (int i = 0; i < tbticket.getRowCount(); i++) {
            String Calculo = String.valueOf(tbticket.getValueAt(i, 5));
            a = Double.parseDouble(Calculo);
            b = b + a;

            if (i == tbticket.getRowCount() - 1) {
                tigv = 0.16 * b;
                t = tigv + b;
                txtSubTotal.setText("" + b);
                total = b;
            }
        }
    }
    public boolean activador = false;
    private void tbticketMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbticketMouseClicked
        int fila = tbticket.getSelectedRow();
        if (fila >= 0) {
            activador = true;
            this.txtCodProd2.setText(tbticket.getValueAt(fila, 0).toString() + "X" + tbticket.getValueAt(fila, 4).toString());

        }

    }//GEN-LAST:event_tbticketMouseClicked

    private void txtEfectKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEfectKeyPressed
        if (evt.getKeyCode() == evt.VK_ENTER) {
            valCambio();
        }

    }//GEN-LAST:event_txtEfectKeyPressed
    public void valCambio() {
        double E = 0.0, T = 0.0, C = 0.0;

        if (txtEfect.getText().equals("0.0")) {

        } else if (txtSubTotal.getText().equals("0.0")) {
            JOptionPane.showMessageDialog(null, "ERROR,NO SE PUEDE REALIZAR EL CALCULO", "", JOptionPane.ERROR_MESSAGE);
        } else {

            E = Double.parseDouble(txtEfect.getText());
            T = Double.parseDouble(txtSubTotal.getText());
            if (E > T) {
                C = E - T;

                txtCambio.setText("" + C);
            } else {
                JOptionPane.showMessageDialog(null, "EFECTIVO INTRODUCIDO ES MENOR QUE" + "\nEL MONTO A COBRAR");
            }

        }
    }
    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        if (txtSubTotal.getText().equals("0.0")) {
            JOptionPane.showMessageDialog(null, "!!! Calcule el total de la venta !!!");
        } else {
            int confirmar = JOptionPane.showConfirmDialog(null, "¿Realizar transacción?", "CONFIRMAR", JOptionPane.YES_NO_OPTION);
            if (confirmar == JOptionPane.YES_NO_OPTION) {
                int c = 0;
                int a = 0;
                for (int x = 0; x < tbticket.getRowCount(); x++) {
                    String CodigoItem = tbticket.getValueAt(x, 0).toString();

                    try {
                        Statement stm1 = connect.createStatement();
                        Statement stm2 = connect.createStatement();

                        ResultSet rs = stm1.executeQuery("SELECT stock FROM productos WHERE idproducto='" + CodigoItem + "'");
                        rs.next();
                        //this.totalVendido();
                        long Calcula = rs.getLong("stock") - Long.valueOf(tbuni.getValueAt(x, 4).toString().replace(",", ""));
                        stm2.execute("UPDATE productos SET stock='" + Calcula + "'WHERE idproducto='" + CodigoItem + "'");
                        c++;
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "!!!!Fatal Error!!!!");
                    }
                }
                a = a + c;
                if (a == tbticket.getRowCount()) {
                    JOptionPane.showMessageDialog(null, "Transacción realizada con exito !!!\n\n"
                            + "Fecha de venta: " + lblfecha.getText() + "\n"
                            + "Total de venta: " + txtSubTotal.getText() + "\n"
                            + "Cliente       : " + txtCliente.getText() + "\n"
                            + "Hora          : " + lblrelojito.getText());
                    HistorialV();
                    tbuni.setRowCount(0);
                }
                valCambio();
                cbTipoVenta.setEnabled(true);
                cbTipoVenta.setSelectedIndex(0);
                txtCliente.setText("XXXXXXXXXXXXXXXXXXXXXXXXX");
                txtSubTotal.setText("0.0");
                txtEfect.setText("0.0");
                btnep.setEnabled(false);

            }
        }

    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnepActionPerformed
        int filas = tbticket.getSelectedRow();
        int totf = tbticket.getRowCount();

        if (filas >= 0) {
            tbuni.removeRow(filas);
            txtCodProd2.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "SELECCIONA UN REGISTRO A ELIMINAR.", "", JOptionPane.ERROR_MESSAGE);
        }
        if (tbticket.getRowCount() == 0) {
            cbTipoVenta.setEnabled(true);
            btnep.setEnabled(false);
            limpiarControles();
        }
        calcTot();

    }//GEN-LAST:event_btnepActionPerformed

    private void btnclientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclientesActionPerformed
        FClientes fcli = new FClientes();
        try {
            JDialog dialogov = new JDialog(fcli, "PANEL PRINCIPAL DE CLIENTES", true);
            dialogov.add(fcli.getContentPane());
            dialogov.setSize(800, 575);
            dialogov.setLocationRelativeTo(null);
            dialogov.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL CONECTAR A LA BASE DE DATOS", "", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnclientesActionPerformed

    private void tbClientes1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbClientes1MouseClicked
        int fs = tbClientes1.getSelectedRow();
        if (fs >= 0) {
            txtCliente.setText(tbClientes1.getValueAt(fs, 1).toString());
            codCliente = tbClientes1.getValueAt(fs, 0).toString();
            cbTipoVenta.setEnabled(false);
            this.setEnabled(true);
            jDialogClientes.setVisible(false);
        }
    }//GEN-LAST:event_tbClientes1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ac = true;
        this.jDialogClientes.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtclientesbKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtclientesbKeyReleased
        this.txtclientesb.setText(this.txtclientesb.getText().toUpperCase());
        cargarTBC(txtclientesb.getText());
    }//GEN-LAST:event_txtclientesbKeyReleased

    private void txtProductoBKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProductoBKeyReleased
        txtProductoB.setText(this.txtProductoB.getText().toUpperCase());
        System.out.println("yes");
        cargarTBP(txtProductoB.getText());
    }//GEN-LAST:event_txtProductoBKeyReleased

    private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
        this.jDialogProducto.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_btnsalirActionPerformed

    private void tbProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProductosMouseClicked
        int fs = tbProductos.getSelectedRow();

        if (fs >= 0) {
            txtCodProd2.setText(tbProductos.getValueAt(fs, 0).toString());

            this.setEnabled(true);
            jDialogProducto.setVisible(false);
            cbTipoVenta.setEnabled(false);
        }
    }//GEN-LAST:event_tbProductosMouseClicked

    private void btnaddProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddProdActionPerformed
        FProducto fcli = new FProducto();
        try {
            JDialog dialogov = new JDialog(fcli, "PANEL PRINCIPAL DE PRODUCTOS", true);
            dialogov.add(fcli.getContentPane());
            dialogov.setSize(900, 650);
            dialogov.setLocationRelativeTo(null);
            dialogov.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL CONECTAR A LA BASE DE DATOS", "", JOptionPane.ERROR_MESSAGE);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnaddProdActionPerformed

    private void txtCodProd2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodProd2KeyPressed

        if (evt.getKeyCode() == evt.VK_ENTER) {
            llenarTicket();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodProd2KeyPressed

    private void txtCodProd2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodProd2KeyReleased
        txtCodProd2.setText(txtCodProd2.getText().toUpperCase());
        MetodosValidar.ValCod(txtCodProd2, 10);        // TODO add your handling code here:
    }//GEN-LAST:event_txtCodProd2KeyReleased

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    public boolean llenarTicket() {
        boolean p = false;
        boolean comp = false;//Variable que me determina tipo de venta elejido (Cliente o publico)
        boolean stockp = false;//Variable para comprobar que los productos existan y sean mayor a 0 en stock
        int cantidad = 0;//Variable que almacena la cantidad a llevar es decir la parte 2 cuando se particiona la cadena del txtCod
        int codigop = 0;//Variable que nos almacenara el codigo del producto a entero porque lo obenetemos como texto
        boolean consplit = false;//Variable que se activa cuando se encuentra una X en la cadena
        boolean sinsplit = false;//Variable que se activa cuando no se encuentra X en la cadena
        boolean bandera = false;//Variable para correr la tabla y saber si el codigo introducido ya existe
        boolean finald = false;//Variable paracomprobar datos finales
        int codo = 0;//variable que almacena el codigo que se obtiene de la tabla de ticket convertido a entero
        int cantO = 0;//Variable que me almacena la cantidad que se obtiene al recorrer la tabla de ticket y validar si el codigo ya existe
        int resultado = 0;//Variable que acumula la cantidad para un producto
        String cadena = "";//Variable que me almacena lo que se obtuvo dentro del txt para codigo de producto
        cadena = txtCodProd2.getText();
        String codigoval = "";//Variable que nos alamcenara el codigo repetido en la tabla
        String[] cadenap = null; //Cadenap m alacena mi cadena del txtcod particionando por una X      
        int con = 0;
        for (int i = 0; i < cadena.length(); i++) {//Validados lo que se introduce en el txt
            if (cadena.charAt(i) == 'X') {
                try {
                    cadenap = cadena.split("X");
                    part1 = cadenap[0];
                    if (Integer.parseInt(part1) > 0) {
                        try {
                            part2 = cadenap[1];
                        } catch (Exception e) {
                            System.exit(0);
                        }
                    }
                } catch (Exception e) {
                    System.exit(0);
                }
            }

            boolean ban = false;
            if (cadena.charAt(i) == 'X') {  //Busca dentro de la cadena si encuentra la X ´para particionar
                cadenap = txtCodProd2.getText().split("X");
                try {
                    part2 = cadenap[1];
                    ban = true;
                } catch (Exception e) {
                    ban = false;
                }
            }

            if (ban) {  //Busca dentro de la cadena si encuentra la X ´para particionar
                consplit = true;
            } else {
                sinsplit = true;
            }
        }
        if (cbTipoVenta.getSelectedItem().equals("CLIENTE")) {
            comp = true;
        }
        String sql = "";
        Statement datosp;//Variable que nos dara los datos que se obtienen del query sql
        String datose[] = new String[7];
        if (consplit == true && Integer.parseInt(part2) > 0) {//por si 
            part1 = cadenap[0];
            part2 = cadenap[1];
            codigop = Integer.parseInt(part1);
            cantidad = Integer.parseInt(part2);
            resultado = cantidad;

            try {
                sql = "SELECT idproducto,nombre,umedidap,preciop,precioc,stock FROM productos WHERE idproducto='" + part1 + "'";//Obtenemos los campos del producto que se tecleo su id      String sql2
                datosp = (Statement) connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rdatosp = datosp.executeQuery(sql);

                while (rdatosp.next()) {//Extraemos datos de lo que nos trajo el Query
                    con = 1;
                    for (int i = 0; i < tbuni.getRowCount(); i++) {//para no insertar columnas con datos repetidos
                        codo = Integer.parseInt(tbuni.getValueAt(i, 0).toString());
                        if (codo == codigop) {
                            codigoval = tbuni.getValueAt(i, 4).toString();//Obtengo el valor que tiene la fila en la posicion 4 que debe ser la cantidad
                            cantO = Integer.parseInt(codigoval);
                            resultado = resultado + cantO;
                            tbuni.removeRow(i);
                        }
                    }

                    if (comp == true) {
                        if (rdatosp.getInt(6) >= Integer.parseInt(part2)) {
                            datose[0] = rdatosp.getString(1);
                            datose[1] = rdatosp.getString(2);
                            datose[2] = rdatosp.getString(3);
                            datose[3] = rdatosp.getString(5);
                            datose[4] = "" + resultado;//La parte 2 de la cadena particionada
                            datose[5] = "" + ((Integer.parseInt(datose[4])) * (Double.parseDouble(datose[3])));
                            txtCodProd2.setText("");
                            finald = true;
                        } else {
                            JOptionPane.showMessageDialog(null, "SIN EXISTENCIAS PARA EL PRODUCTO: \n" + rdatosp.getString(1), "", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        if (rdatosp.getInt(6) >= Integer.parseInt(part2)) {
                            datose[0] = rdatosp.getString(1);
                            datose[1] = rdatosp.getString(2);
                            datose[2] = rdatosp.getString(3);
                            datose[3] = rdatosp.getString(4);
                            datose[4] = "" + resultado;//La parte 2 de la cadena particionada
                            datose[5] = "" + ((Integer.parseInt(datose[4])) * (Double.parseDouble(datose[3])));
                            txtCodProd2.setText("");
                            finald = true;
                        } else {
                            JOptionPane.showMessageDialog(null, "SIN EXISTENCIAS PARA EL PRODUCTO: \n" + rdatosp.getString(1), "", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    if (finald == true) {
                        tbuni.addRow(datose);
                        tbticket.setModel(tbuni);
                        calcTot();
                        btnep.setEnabled(true);
                    }

                }
            } catch (SQLException ex) {

            }

        } else if (sinsplit == true) {//Validamos si se inserto puro codigo de producto y tambien acepta la cadena de 10001x0 para mandar el error 
            String[] caden = null;
            String c = txtCodProd2.getText();
            String cantidads = "";
            boolean b1 = false;
            boolean b2 = false;
            boolean mib = false;
            for (int i = 0; i < c.length(); i++) {
                try {
                    if (c.charAt(i) == 'X') {//Si la cadena trae X y cantidad = 0, 10001x0 
                        caden = c.split("X");
                        cantidads = caden[1];
                        if (Integer.parseInt(cantidads) == 0) {
                            b1 = true;  //Para marcar el error si se puso un 0 en la cadena
                        }
                    }
                    mib = true;
                } catch (Exception e) {
                    mib = false;
                }
            }

            if (b1 == true && mib) {
                JOptionPane.showMessageDialog(null, "CANTIDAD NO DEBE SER= 0 ", "", JOptionPane.ERROR_MESSAGE);
            } else {
                codigop = Integer.parseInt(txtCodProd2.getText());
            }
            try {
                cantidad = 1;
                resultado = cantidad;
                Statement sdatos;
                sql = "SELECT idproducto,nombre,umedidap,preciop,precioc,stock FROM productos WHERE idproducto='" + codigop + "'";//Obtenemos los campos del producto que se tecleo su id      String sql20
                sdatos = (Statement) connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = sdatos.executeQuery(sql);
                while (rs.next()) {//Extraemos datos de lo que nos trajo el Query
                    con = 1;
                    for (int i = 0; i < tbuni.getRowCount(); i++) {//para no insertar columnas con datos repetidos
                        codo = Integer.parseInt(tbuni.getValueAt(i, 0).toString());
                        if (codo == codigop) {
                            codigoval = tbuni.getValueAt(i, 4).toString();//Obtengo el valor que tiene la fila en la posicion 4 que debe ser la cantidad
                            cantO = Integer.parseInt(codigoval);
                            resultado = resultado + cantO;
                            tbuni.removeRow(i);
                        }
                    }
                    if (comp == true) {
                        if (rs.getInt(6) > 0) {
                            datose[0] = rs.getString(1);
                            datose[1] = rs.getString(2);
                            datose[2] = rs.getString(3);
                            datose[3] = rs.getString(5);
                            datose[4] = "" + resultado;//La parte 2 de la cadena particionada
                            datose[5] = "" + ((Integer.parseInt(datose[4])) * (Double.parseDouble(datose[3])));
                            txtCodProd2.setText("");
                            finald = true;
                        }
                    } else {
                        if (rs.getInt(6) > 0) {
                            datose[0] = rs.getString(1);
                            datose[1] = rs.getString(2);
                            datose[2] = rs.getString(3);
                            datose[3] = rs.getString(4);
                            datose[4] = "" + resultado;//La parte 2 de la cadena particionada
                            datose[5] = "" + ((Integer.parseInt(datose[4])) * (Double.parseDouble(datose[3])));
                            txtCodProd2.setText("");
                            finald = true;
                        } else {
                            JOptionPane.showMessageDialog(null, "SIN EXISTENCIAS PARA EL PRODUCTO: \n" + rs.getString(1), "", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    if (finald == true) {
                        tbuni.addRow(datose);
                        tbticket.setModel(tbuni);
                        btnep.setEnabled(true);
                        calcTot();
                    }
                }
                rs.close();

            } catch (SQLException ex) {

            }

        }
        if (Double.parseDouble(txtSubTotal.getText()) > 0) {
            btnAceptar.setEnabled(true);
        }
        if (con == 0) {
            JOptionPane.showMessageDialog(null, "PRODUCTO NO EXISTE", "", JOptionPane.ERROR_MESSAGE);
        } else {
            p = true;
        }
        return p;
    }

    public void calcTot() {
        double total = 0;
        //double IVA = 0;

        double a;
        double b = 0;
        for (int i = 0; i < tbuni.getRowCount(); i++) {
            String Calculo = String.valueOf(tbuni.getValueAt(i, 5));
            a = Double.parseDouble(Calculo);
            b = b + a;
            if (i == tbuni.getRowCount() - 1) {
                txtSubTotal.setText("" + b);
                //IVA = b * 1.18;
                total = b;
            }
        }
    }

    public void HistorialV() {

        for (int i = 0; i < tbticket.getRowCount(); i++) {
            try {
                PreparedStatement st;
                Statement st1;
                String insertv = "INSERT INTO ventas(fechav,total,idcliente,idtipov,idproducto,hora,carticulos) values(?,?,?,?,?,?,?)";//Obtenemos los campos del producto que se tecleo su id      String sql2
                st = connect.prepareStatement(insertv);
                st.setString(1, lblfecha.getText());
                st.setDouble(2, Double.parseDouble(tbticket.getValueAt(i, 5).toString()));
                st.setInt(3, Integer.parseInt(codCliente));

                if (cbTipoVenta.getSelectedItem().equals("PUBLICO")) {
                    st.setInt(4, 1);
                } else {
                    st.setInt(4, 2);
                }

                st.setInt(5, Integer.parseInt(tbticket.getValueAt(i, 0).toString()));
                st.setString(6, lblrelojito.getText());
                st.setInt(7, Integer.parseInt(String.valueOf(tbticket.getValueAt(i, 4).toString())));
                st.executeUpdate();
            } catch (Exception ex) {

            }
        }
    }

    private void limpiarControles() {
        this.txtCodProd2.setText("");
        this.txtCliente.setText("");
        this.txtSubTotal.setText("0");
        btnAceptar.setEnabled(false);
        txtCambio.setText("");
        txtCliente.setText("");
        cbTipoVenta.setSelectedIndex(0);
        this.txtCodProd2.requestFocusInWindow();

    }

    private boolean validarEntradas() {
        JComponent aux = null;
        String mensaje = "";
        boolean estado = true;

        if (this.txtCodProd2.getText().isEmpty() == true) {
            mensaje += "INGRESE SERIE DE FACTURA \n";
            estado = false;
            aux = this.txtCodProd2;
        } else {
            if (Double.parseDouble(this.txtCodProd2.getText()) == 0) {
                mensaje += "NUMERO DE SERIE INCORRECTO \n";
                estado = false;
                aux = this.txtCodProd2;
            }
        }
        if (this.cbTipoVenta.getSelectedIndex() == 1) {
            mensaje += "SELECCIONE TIPO DE VENTA \n";
            estado = false;
            aux = this.txtCodProd2;
        }

        if (this.txtCliente.getText().isEmpty() == true) {
            mensaje += "INGRESE CLIENTE \n";
            estado = false;
            aux = this.txtCliente;
        }
        if (this.jdFecha.getDate() == null) {
            mensaje += "SELECCIONE FECHA \n";
            estado = false;
            aux = this.jdFecha;
        }

        if (mensaje.length() >= 1) {
            //      MetodosValidar.mensajeAdvertencia(mensaje, Ferreteria.mensajeTitulo);
            aux.requestFocusInWindow();
        }

        return estado;
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
            java.util.logging.Logger.getLogger(FVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FVentas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FVentas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnaddProd;
    private javax.swing.JButton btnbprod;
    private javax.swing.JButton btnclientes;
    private javax.swing.JButton btnep;
    private javax.swing.JButton btnsalir;
    private javax.swing.JComboBox cbTipoVenta;
    private javax.swing.JButton jButton2;
    private javax.swing.JDialog jDialogClientes;
    private javax.swing.JDialog jDialogProducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private com.toedter.calendar.JDateChooser jdFecha;
    private javax.swing.JLabel lblfecha;
    private javax.swing.JLabel lblfechar;
    private javax.swing.JLabel lblrelojito;
    private javax.swing.JPanel pnlDatos;
    private javax.swing.JPanel pnlDatos1;
    private javax.swing.JTable tbClientes1;
    private javax.swing.JTable tbProductos;
    private javax.swing.JTable tbticket;
    private javax.swing.JTextField txtCambio;
    public javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtCodProd2;
    private javax.swing.JTextField txtEfect;
    private javax.swing.JTextField txtProductoB;
    private javax.swing.JTextField txtSubTotal;
    private javax.swing.JTextField txtclientesb;
    // End of variables declaration//GEN-END:variables
}
