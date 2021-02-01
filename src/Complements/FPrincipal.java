package Complements;

import Reportes.RPVentasDiarias;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class FPrincipal extends javax.swing.JFrame implements Runnable{
  
    Calendar c2 = new GregorianCalendar();
    int d = c2.get(Calendar.DATE);
    int m = c2.get(Calendar.MONTH);
    int a = c2.get(Calendar.YEAR);
    int cg = m + 1;
    String hora,minutos,segundos,ampm;
    Calendar calendario;
    Thread h1;
    /**
     * Creates new form SistemaV
     */
    Connection con;
    CallableStatement cst;
    ResultSet r;
    JDialog jd=new JDialog();
    
    public FPrincipal() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setTitle("SIVent V.1.0" );
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/carbuy.png")).getImage());
        ServiciosITEM.setEnabled(false);
        RProductosITEM.setEnabled(false);
        RComprasITEM.setEnabled(false);
        obtenerf();
        h1=new Thread(this);
        h1.start();
        // this.lblUsuario.setText("Usuario: " + Ferreteria.us.getNombreUsuario());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogInformation = new javax.swing.JDialog();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        toolusuario = new javax.swing.JToolBar();
        lblUsuario = new javax.swing.JLabel();
        jToolBar2 = new javax.swing.JToolBar();
        ventanap = new javax.swing.JDesktopPane();
        toolhora = new javax.swing.JToolBar();
        jlabelhora = new javax.swing.JLabel();
        lblhora = new javax.swing.JLabel();
        toolfechar = new javax.swing.JToolBar();
        jlabelfecha = new javax.swing.JLabel();
        lblfecha = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        MIniciar = new javax.swing.JMenu();
        ContraseniaITEM = new javax.swing.JMenuItem();
        DesarrolladorITEM = new javax.swing.JMenuItem();
        CerrarITEM = new javax.swing.JMenuItem();
        JMRegistro = new javax.swing.JMenu();
        VenderITEM = new javax.swing.JMenuItem();
        ServiciosITEM = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        JMReporte = new javax.swing.JMenu();
        RProductosITEM = new javax.swing.JMenu();
        MSInfoProgramado5 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        RVentasITEM = new javax.swing.JMenuItem();
        RComprasITEM = new javax.swing.JMenuItem();
        JMTrabajador = new javax.swing.JMenu();
        InventarioITEM = new javax.swing.JMenu();
        ProductosITEM = new javax.swing.JMenuItem();
        ProveedoresITEM = new javax.swing.JMenuItem();
        clientesITEM = new javax.swing.JMenuItem();
        LocalidadesITEM = new javax.swing.JMenuItem();
        MunicipiosITEM = new javax.swing.JMenuItem();
        EstadosITEM = new javax.swing.JMenuItem();
        UnidadesITEM = new javax.swing.JMenuItem();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("GRACIAS POR UTILIZAR DE NUESTRO SOFTWARE SIVent 1.0 ");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/LOGO.jpg"))); // NOI18N
        jLabel2.setText("jLabel2");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("DATOS CONTACTO");

        jLabel4.setFont(new java.awt.Font("SimSun-ExtB", 1, 18)); // NOI18N
        jLabel4.setText("CORREO : jdalton201214@gmail.com");

        jLabel5.setFont(new java.awt.Font("SimSun-ExtB", 1, 18)); // NOI18N
        jLabel5.setText("FACEBOOK: https://www.facebook.com/will.mramirez.54");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logos.png"))); // NOI18N

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/brands-and-logotypes (1).png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("SimSun-ExtB", 1, 18)); // NOI18N
        jLabel8.setText("TELEFONO : 9631562456");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/face.png"))); // NOI18N

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/gmail.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(251, 251, 251)
                                .addComponent(jLabel9))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(190, 190, 190)
                                .addComponent(jLabel3)))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel10)))
                                .addGap(0, 22, Short.MAX_VALUE))))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(57, 57, 57))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel3)
                        .addGap(21, 21, 21)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addGap(11, 11, 11)
                        .addComponent(jLabel9)))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jDialogInformationLayout = new javax.swing.GroupLayout(jDialogInformation.getContentPane());
        jDialogInformation.getContentPane().setLayout(jDialogInformationLayout);
        jDialogInformationLayout.setHorizontalGroup(
            jDialogInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogInformationLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );
        jDialogInformationLayout.setVerticalGroup(
            jDialogInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("VidSoniaSoft");

        toolusuario.setFloatable(false);
        toolusuario.setRollover(true);

        lblUsuario.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        lblUsuario.setText("Usuario: Unico");
        toolusuario.add(lblUsuario);

        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);
        toolusuario.add(jToolBar2);

        ventanap.setBackground(new java.awt.Color(0, 51, 51));

        javax.swing.GroupLayout ventanapLayout = new javax.swing.GroupLayout(ventanap);
        ventanap.setLayout(ventanapLayout);
        ventanapLayout.setHorizontalGroup(
            ventanapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        ventanapLayout.setVerticalGroup(
            ventanapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 446, Short.MAX_VALUE)
        );

        toolhora.setFloatable(false);
        toolhora.setRollover(true);

        jlabelhora.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jlabelhora.setText("Hora:");
        toolhora.add(jlabelhora);

        lblhora.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        toolhora.add(lblhora);

        toolfechar.setFloatable(false);
        toolfechar.setRollover(true);

        jlabelfecha.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jlabelfecha.setText("Fecha:");
        toolfechar.add(jlabelfecha);

        lblfecha.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        toolfechar.add(lblfecha);

        menuBar.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N

        MIniciar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/producto.png"))); // NOI18N
        MIniciar.setMnemonic('f');
        MIniciar.setText("SISTEMA");
        MIniciar.setFont(new java.awt.Font("Verdana", 1, 15)); // NOI18N

        ContraseniaITEM.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        ContraseniaITEM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Blue key.png"))); // NOI18N
        ContraseniaITEM.setText("Cambio de Contraseña");
        ContraseniaITEM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContraseniaITEMActionPerformed(evt);
            }
        });
        MIniciar.add(ContraseniaITEM);

        DesarrolladorITEM.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        DesarrolladorITEM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Help symbol.png"))); // NOI18N
        DesarrolladorITEM.setText("Informacion Desarrollador");
        DesarrolladorITEM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DesarrolladorITEMActionPerformed(evt);
            }
        });
        MIniciar.add(DesarrolladorITEM);

        CerrarITEM.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        CerrarITEM.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        CerrarITEM.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Turn off.png"))); // NOI18N
        CerrarITEM.setText("Salir");
        CerrarITEM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CerrarITEMActionPerformed(evt);
            }
        });
        MIniciar.add(CerrarITEM);

        menuBar.add(MIniciar);

        JMRegistro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Load.png"))); // NOI18N
        JMRegistro.setText("PROCESOS");
        JMRegistro.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N

        VenderITEM.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        VenderITEM.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        VenderITEM.setText("Vender");
        VenderITEM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VenderITEMActionPerformed(evt);
            }
        });
        JMRegistro.add(VenderITEM);

        ServiciosITEM.setText("Pago de servicios");
        ServiciosITEM.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N

        jMenuItem3.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jMenuItem3.setText("Servicio VeTB");
        ServiciosITEM.add(jMenuItem3);

        jMenuItem5.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jMenuItem5.setText("Servicio SKY");
        ServiciosITEM.add(jMenuItem5);

        jMenuItem7.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jMenuItem7.setText("Servicio DISH");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        ServiciosITEM.add(jMenuItem7);

        jMenuItem6.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jMenuItem6.setText("Servicio CFE");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        ServiciosITEM.add(jMenuItem6);

        JMRegistro.add(ServiciosITEM);

        menuBar.add(JMRegistro);

        JMReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/3d bar chart.png"))); // NOI18N
        JMReporte.setText("REPORTES");
        JMReporte.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N

        RProductosITEM.setText("Productos");
        RProductosITEM.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N

        MSInfoProgramado5.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        MSInfoProgramado5.setText("Lista Productos");
        MSInfoProgramado5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MSInfoProgramado5ActionPerformed(evt);
            }
        });
        RProductosITEM.add(MSInfoProgramado5);

        jMenuItem9.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jMenuItem9.setText("Producto mas vendido");
        RProductosITEM.add(jMenuItem9);

        jMenuItem10.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        jMenuItem10.setText("Producto menos vendido");
        RProductosITEM.add(jMenuItem10);

        JMReporte.add(RProductosITEM);

        RVentasITEM.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        RVentasITEM.setText("Ventas");
        RVentasITEM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RVentasITEMActionPerformed(evt);
            }
        });
        JMReporte.add(RVentasITEM);

        RComprasITEM.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        RComprasITEM.setText("Compras");
        JMReporte.add(RComprasITEM);

        menuBar.add(JMReporte);

        JMTrabajador.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Hard disk.png"))); // NOI18N
        JMTrabajador.setText("MANTENIMIENTO");
        JMTrabajador.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N

        InventarioITEM.setText("Inventario");
        InventarioITEM.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N

        ProductosITEM.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        ProductosITEM.setText("Productos");
        ProductosITEM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProductosITEMActionPerformed(evt);
            }
        });
        InventarioITEM.add(ProductosITEM);

        JMTrabajador.add(InventarioITEM);

        ProveedoresITEM.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        ProveedoresITEM.setText("Gestion de Proveedores");
        ProveedoresITEM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProveedoresITEMActionPerformed(evt);
            }
        });
        JMTrabajador.add(ProveedoresITEM);

        clientesITEM.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        clientesITEM.setText("Gestion de Clientes");
        clientesITEM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientesITEMActionPerformed(evt);
            }
        });
        JMTrabajador.add(clientesITEM);

        LocalidadesITEM.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        LocalidadesITEM.setText("Gestion de Localidades");
        LocalidadesITEM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LocalidadesITEMActionPerformed(evt);
            }
        });
        JMTrabajador.add(LocalidadesITEM);

        MunicipiosITEM.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        MunicipiosITEM.setText("Gestion de Municipios");
        MunicipiosITEM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MunicipiosITEMActionPerformed(evt);
            }
        });
        JMTrabajador.add(MunicipiosITEM);

        EstadosITEM.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        EstadosITEM.setText("Gestion de Estados");
        EstadosITEM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EstadosITEMActionPerformed(evt);
            }
        });
        JMTrabajador.add(EstadosITEM);

        UnidadesITEM.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        UnidadesITEM.setText("Gestion de Unidades de Medida");
        UnidadesITEM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UnidadesITEMActionPerformed(evt);
            }
        });
        JMTrabajador.add(UnidadesITEM);

        menuBar.add(JMTrabajador);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ventanap)
            .addGroup(layout.createSequentialGroup()
                .addComponent(toolusuario, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 245, Short.MAX_VALUE)
                .addComponent(toolfechar, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(toolhora, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ventanap)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(toolusuario, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(toolhora, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(toolfechar, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

  
       @Override
     public void run() {
        Thread ct=Thread.currentThread();
       
       while(ct==h1){
           calcula();
          lblhora.setText(hora+":"+minutos+":"+segundos+" "+ampm);
           try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            
        } 
       }       
    }
      private void calcula(){
         Calendar calendario=new GregorianCalendar();
         Date fechaHoraActual=new Date();
         calendario.setTime(fechaHoraActual);
         ampm=calendario.get(Calendar.AM_PM)==Calendar.AM?"AM":"PM";
         if(ampm.equals("PM")){
         int h=calendario.get(Calendar.HOUR_OF_DAY)-12;
         hora=h>9?""+h:"0"+h;
         }else{
          hora=calendario.get(Calendar.HOUR_OF_DAY)>9?""+calendario.get(Calendar.HOUR_OF_DAY):"0"+calendario.get(Calendar.HOUR_OF_DAY);
         }
         if(Integer.parseInt(hora)==00){
            hora="12"; 
         }
       minutos=calendario.get(Calendar.MINUTE)>9?""+calendario.get(Calendar.MINUTE):"0"+calendario.get(Calendar.MINUTE);
       segundos=calendario.get(Calendar.SECOND)>9?""+calendario.get(Calendar.SECOND):"0"+calendario.get(Calendar.SECOND);

        }


    private void CerrarITEMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CerrarITEMActionPerformed
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_CerrarITEMActionPerformed

    private void MSInfoProgramado5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MSInfoProgramado5ActionPerformed
        /*try {
            JFReporteListaProductos r = new JFReporteListaProductos();
            r.setLocationRelativeTo(null);
            r.setVisible(true);
        } catch (Exception e) {
            MetodosValidar.mensajeError(Ferreteria.mensajeError + "/n" + e.getMessage(), Ferreteria.mensajeTitulo);
        }*/
    }//GEN-LAST:event_MSInfoProgramado5ActionPerformed
 
    private void VenderITEMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VenderITEMActionPerformed
        FVentas venta = new FVentas();
        FPrincipal fp=new FPrincipal();
        try {           
        fp.setAlwaysOnTop(true);
        JDialog dialogov = new JDialog(venta, "PANEL PRINCIPAL DE VENTAS", true);
        dialogov.add(venta.getContentPane());
        dialogov.setSize(900,650);
        dialogov.setLocationRelativeTo(null);
        dialogov.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL CONECTAR A LA BASE DE DATOS", "", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_VenderITEMActionPerformed

    private void ProductosITEMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProductosITEMActionPerformed
        FProducto prod = new FProducto();
        FPrincipal fp=new FPrincipal();
        try {           
        fp.setAlwaysOnTop(true);
        JDialog dialogov = new JDialog(prod, "PANEL PRINCIPAL DE PRODUCTOS", true);
        dialogov.add(prod.getContentPane());
        dialogov.setSize(900,650);
        dialogov.setLocationRelativeTo(null);
        dialogov.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL CONECTAR A LA BASE DE DATOS", "", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_ProductosITEMActionPerformed

    private void DesarrolladorITEMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DesarrolladorITEMActionPerformed
       FPrincipal fv = new FPrincipal();
        try {
            fv.setAlwaysOnTop(true);
            JDialog dialogov = new JDialog(jDialogInformation, "CONTACTO", true);
            dialogov.add(jDialogInformation.getContentPane());
            dialogov.setSize(765, 400);
            dialogov.setLocationRelativeTo(null);
            dialogov.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL CONECTAR A LA BASE DE DATOS", "", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_DesarrolladorITEMActionPerformed

    private void ProveedoresITEMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProveedoresITEMActionPerformed
        FProveedor prov = new FProveedor();
        FPrincipal fp=new FPrincipal();
        try {           
        fp.setAlwaysOnTop(true);
        JDialog dialogov = new JDialog(prov, "PANEL PRINCIPAL DE PROVEEDORES", true);
        dialogov.add(prov.getContentPane());
        dialogov.setSize(750,570);
        dialogov.setLocationRelativeTo(null);
        dialogov.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL CONECTAR A LA BASE DE DATOS", "", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_ProveedoresITEMActionPerformed

    private void buttonTask2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTask2ActionPerformed
        this.dispose();
        System.exit(0);// TODO add your handling code here:
    }//GEN-LAST:event_buttonTask2ActionPerformed

    private void ContraseniaITEMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContraseniaITEMActionPerformed
        /*  try {
            JFCambioContrase� c = new JFCambioContrase�();
            c.setLocationRelativeTo(null);
            c.setVisible(true);
        } catch (Exception e) {
            MetodosVal.mensajeError(Ferreteria.mensajeError + "\n" + e.getMessage(), Ferreteria.mensajeTitulo);
        }*/
    }//GEN-LAST:event_ContraseniaITEMActionPerformed

    private void clientesITEMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientesITEMActionPerformed
       FClientes fcli= new FClientes();
        FPrincipal fp=new FPrincipal();
        try {           
        fp.setAlwaysOnTop(true);
        JDialog dialogov = new JDialog(fcli, "PANEL PRINCIPAL DE CLIENTES", true);
        dialogov.add(fcli.getContentPane());
        dialogov.setSize(800,575);
        dialogov.setLocationRelativeTo(null);
        dialogov.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL CONECTAR A LA BASE DE DATOS", "", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_clientesITEMActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void RVentasITEMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RVentasITEMActionPerformed
        RPVentasDiarias rpv= new RPVentasDiarias();
        FPrincipal fp=new FPrincipal();
        try {           
        fp.setAlwaysOnTop(true);
        JDialog dialogov = new JDialog(rpv, "REPORTE DE VENTAS", true);
        dialogov.add(rpv.getContentPane());
        dialogov.setSize(720,540);
        dialogov.setLocationRelativeTo(null);
        dialogov.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL CONECTAR A LA BASE DE DATOS", "", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_RVentasITEMActionPerformed

    private void LocalidadesITEMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LocalidadesITEMActionPerformed
        FLocalidades rpv= new FLocalidades();
        FPrincipal fp=new FPrincipal();
        try {           
        fp.setAlwaysOnTop(true);
        JDialog dialogov = new JDialog(rpv,"ADMINISTRACION LOCALIDADES", true);
        dialogov.add(rpv.getContentPane());
        dialogov.setSize(430,440);
        dialogov.setLocationRelativeTo(null);
        dialogov.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL CONECTAR A LA BASE DE DATOS", "", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_LocalidadesITEMActionPerformed

    private void EstadosITEMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EstadosITEMActionPerformed
        FEstados rpv= new FEstados();
        FPrincipal fp=new FPrincipal();
        try {           
        fp.setAlwaysOnTop(true);
        JDialog dialogov = new JDialog(rpv,"ADMINISTRACION ESTADOS", true);
        dialogov.add(rpv.getContentPane());
        dialogov.setSize(430,400);
        dialogov.setLocationRelativeTo(null);
        dialogov.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL CONECTAR A LA BASE DE DATOS", "", JOptionPane.ERROR_MESSAGE);
        }           // TODO add your handling code here:
    }//GEN-LAST:event_EstadosITEMActionPerformed

    private void MunicipiosITEMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MunicipiosITEMActionPerformed
        FMunicipios rpv= new FMunicipios();
        FPrincipal fp=new FPrincipal();
        try {           
        fp.setAlwaysOnTop(true);
        JDialog dialogov = new JDialog(rpv,"ADMINISTRACION MUNICIPIOS", true);
        dialogov.add(rpv.getContentPane());
        dialogov.setSize(430,440);
        dialogov.setLocationRelativeTo(null);
        dialogov.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL CONECTAR A LA BASE DE DATOS", "", JOptionPane.ERROR_MESSAGE);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_MunicipiosITEMActionPerformed

    private void UnidadesITEMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UnidadesITEMActionPerformed
        FUnidadesM rpv= new FUnidadesM();
        FPrincipal fp=new FPrincipal();
        try {           
        fp.setAlwaysOnTop(true);
        JDialog dialogov = new JDialog(rpv,"ADMINISTRACION UNIDADES DE MEDIDA", true);
        dialogov.add(rpv.getContentPane());
        dialogov.setSize(430,400);
        dialogov.setLocationRelativeTo(null);
        dialogov.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR AL CONECTAR A LA BASE DE DATOS", "", JOptionPane.ERROR_MESSAGE);
        }            // TODO add your handling code here:
    }//GEN-LAST:event_UnidadesITEMActionPerformed
 public void obtenerf(){ 
    Calendar c1 = Calendar.getInstance();
    Calendar c2 = new GregorianCalendar();
    String dia = Integer.toString(c2.get(Calendar.DATE));
    int mes = Integer.parseInt(Integer.toString(c2.get(Calendar.MONTH))) + 1;
    String annio = Integer.toString(c2.get(Calendar.YEAR));
    String mesr=String.valueOf(mes);
     if((Integer.parseInt(dia)<10)){
        dia="0"+Integer.toString(c2.get(Calendar.DATE));
     }if(mes<10){
        mesr="0"+String.valueOf(mes);
     } 
     
      lblfecha.setText(dia+"/"+mesr+"/"+annio);   
     
    
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
            java.util.logging.Logger.getLogger(FPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new FPrincipal().setVisible(true);
                
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem CerrarITEM;
    private javax.swing.JMenuItem ContraseniaITEM;
    private javax.swing.JMenuItem DesarrolladorITEM;
    private javax.swing.JMenuItem EstadosITEM;
    private javax.swing.JMenu InventarioITEM;
    private javax.swing.JMenu JMRegistro;
    private javax.swing.JMenu JMReporte;
    private javax.swing.JMenu JMTrabajador;
    private javax.swing.JMenuItem LocalidadesITEM;
    private javax.swing.JMenu MIniciar;
    private javax.swing.JMenuItem MSInfoProgramado5;
    private javax.swing.JMenuItem MunicipiosITEM;
    private javax.swing.JMenuItem ProductosITEM;
    private javax.swing.JMenuItem ProveedoresITEM;
    private javax.swing.JMenuItem RComprasITEM;
    private javax.swing.JMenu RProductosITEM;
    private javax.swing.JMenuItem RVentasITEM;
    private javax.swing.JMenu ServiciosITEM;
    private javax.swing.JMenuItem UnidadesITEM;
    private javax.swing.JMenuItem VenderITEM;
    private javax.swing.JMenuItem clientesITEM;
    private javax.swing.JDialog jDialogInformation;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JLabel jlabelfecha;
    private javax.swing.JLabel jlabelhora;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lblfecha;
    private javax.swing.JLabel lblhora;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JToolBar toolfechar;
    private javax.swing.JToolBar toolhora;
    private javax.swing.JToolBar toolusuario;
    private javax.swing.JDesktopPane ventanap;
    // End of variables declaration//GEN-END:variables

}
