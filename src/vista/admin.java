/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import conexion.Conexion;
import conexion.conectar;
import controlador.CustomImageIcon;
import controlador.habitacionCRUD;
import controlador.usuarioCRUD;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Perfil;
import modelo.habitacion;
import modelo.sector;
import modelo.sesion;
import modelo.tipo_habitacion;
import modelo.usuario;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Fox
 */
public class admin extends javax.swing.JFrame {
    
    private FileInputStream fis;
    private FileInputStream fisds;
    /**
     * Creates new form admin
     */
    public admin() {
        initComponents();
        setLocationRelativeTo(null);
        //DEJAR INIVISIBLE LOS LABEL
        lbrutmod.setVisible(false);
        lberroringrut.setVisible(false);
        lberroringnombre.setVisible(false);
        lberroringapellidop.setVisible(false);
        lberroringapellidom.setVisible(false);
        lberroringcontraseña.setVisible(false);
        lberroringperfil.setVisible(false);
        cboperfilmod.setEnabled(false);
        txtmodnombre.setEnabled(false);
        txtmodapellidop.setEnabled(false);
        txtmodapellidom.setEnabled(false);
        txtmodpass.setEnabled(false);
        btmodificar.setEnabled(false);
        
        lberrormodcontraseña.setVisible(false);
        lberrormodperfil.setVisible(false);
        lberrormodgnombre.setVisible(false);
        lberrormodapellidom.setVisible(false);
        lberrormodapellidop.setVisible(false);
        
        lbrutfiltro.setVisible(false);
        txtrutfiltro.setVisible(false);
        lbstring.setVisible(false);
        txtstring.setVisible(false);

        //ETIQUIETAS HABITACION
        lbingnumhabitacion.setVisible(false);
        lbingtipohabitacion.setVisible(false);
        lbingvalorhabitacion.setVisible(false);

        //FIN ETIQUIETAS HABITACION
        //CARGAR LOS COMBOBOX DESDE LA BD
        Conexion c = new Conexion();
        
        c.conectar();
        String sql = "SELECT descripcion FROM perfil order by id_perfil";
        
        try {
            PreparedStatement st = c.getConector().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Perfil p = new Perfil();
                p.setDescripcion(rs.getString(1));
                cboperfil.addItem(p);
                
                cboperfilmod.addItem(p);
            }
        } catch (SQLException ex) {
        } finally {
            c.desconectar();
        }
        //HABITACION
        
        c.conectar();
        String sqldos = "SELECT descripcion FROM sector order by idsector";
        
        try {
            PreparedStatement st = c.getConector().prepareStatement(sqldos);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                sector p = new sector();
                p.setDescripcion(rs.getString(1));
                cboingsectorhabitacion.addItem(p);
                
            }
        } catch (SQLException ex) {
        } finally {
            c.desconectar();
        }
        
        c.conectar();
        String sqltres = "SELECT descripcion FROM tipo_habitacion order by idtipo_habitacion";
        try {
            PreparedStatement st = c.getConector().prepareStatement(sqltres);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                tipo_habitacion p = new tipo_habitacion();
                p.setDescripcion(rs.getString(1));
                cboingtipohabitacion.addItem(p);
                
            }
        } catch (SQLException ex) {
        } finally {
            c.desconectar();
        }
        
        c.conectar();
        String sqlcuatro = "SELECT descripcion FROM estado order by id_estado";
        try {
            PreparedStatement st = c.getConector().prepareStatement(sqlcuatro);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                tipo_habitacion p = new tipo_habitacion();
                p.setDescripcion(rs.getString(1));
                cboingestadohabitacion.addItem(p);
                
            }
        } catch (SQLException ex) {
        } finally {
            c.desconectar();
        }
        //HABITACION
        usuarioCRUD crud = new usuarioCRUD();
        
        ArrayList lista = crud.consultarTodos();
        //obtener el modelo de la tabla con getmodel
        DefaultTableModel modelox = (DefaultTableModel) tablabuscar.getModel();
        //limpiar la tabla
        modelox.getDataVector().clear();
        for (int i = 0; i < lista.size(); i++) {
            usuario b = (usuario) lista.get(i);
            Object fila[] = {b.getRut_user(), b.getNombre(), b.getApellidop(), b.getApellidom(), b.getPerfil()};
            modelox.addRow(fila);
        }
        tablabuscar.setModel(modelox);
        
        ArrayList listados = crud.consultarTodasesiones();
        //obtener el modelo de la tabla con getmodel
        DefaultTableModel modelo = (DefaultTableModel) tablasesion.getModel();
        //limpiar la tabla
        modelo.getDataVector().clear();
        for (int i = 0; i < listados.size(); i++) {
//  
            
            usuario b = (usuario) listados.get(i);
            
            Object fila[] = {b.getRut_user(), b.getNombre(), b.getApellidop(), b.getApellidom(), b.getPerfil(), b.getHora(), b.getFecha()};
            modelo.addRow(fila);
        }
        tablasesion.setModel(modelo);
        
    }
    
    public void cargartablilla(ArrayList lis) {
        ArrayList<usuario> lista = lis;

        //obtener el modelo de la tabla
        DefaultTableModel modelo = (DefaultTableModel) tablabuscar.getModel();
        modelo.getDataVector().clear();
        for (usuario b : lista) {
            
            String[] fila = {b.getRut_user(), b.getNombre(), b.getApellidop(), b.getApellidom(), b.getPerfil()};
            modelo.addRow(fila);
        }
        tablabuscar.setModel(modelo);
    }

    //FIN PARA CARGAR LOS COMBOBOX DESDE LA BD
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabbedSelector21 = new org.edisoncor.gui.tabbedPane.TabbedSelector2();
        tabbedPaneVertical5 = new org.edisoncor.gui.tabbedPane.TabbedPaneVertical();
        jPanel1 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jComboBox2 = new javax.swing.JComboBox();
        jTextField7 = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        tabbedPaneVertical3 = new org.edisoncor.gui.tabbedPane.TabbedPaneVertical();
        jPanel6 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        lblFoto2 = new javax.swing.JLabel();
        btfoto1 = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        cboingsectorhabitacion = new javax.swing.JComboBox();
        txtingnumerohabitacion = new javax.swing.JTextField();
        cboingtipohabitacion = new javax.swing.JComboBox();
        txtingvalorhabitacion = new javax.swing.JTextField();
        cboingestadohabitacion = new javax.swing.JComboBox();
        btingresarhabitacion = new javax.swing.JButton();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        lbingnumhabitacion = new javax.swing.JLabel();
        lbingtipohabitacion = new javax.swing.JLabel();
        lbingvalorhabitacion = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        tabbedPaneVertical1 = new org.edisoncor.gui.tabbedPane.TabbedPaneVertical();
        jPanel5 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        tabbedPaneVertical4 = new org.edisoncor.gui.tabbedPane.TabbedPaneVertical();
        jPanel15 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        tabbedPaneVertical6 = new org.edisoncor.gui.tabbedPane.TabbedPaneVertical();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        tabbedPaneVertical7 = new org.edisoncor.gui.tabbedPane.TabbedPaneVertical();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jPanel27 = new javax.swing.JPanel();
        tabbedPaneVertical8 = new org.edisoncor.gui.tabbedPane.TabbedPaneVertical();
        tabbedPaneVertical2 = new org.edisoncor.gui.tabbedPane.TabbedPaneVertical();
        jPanel11 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblFoto = new javax.swing.JLabel();
        btfoto = new javax.swing.JButton();
        txtrut = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtver = new javax.swing.JTextField();
        txtnombre = new javax.swing.JTextField();
        txtapellidop = new javax.swing.JTextField();
        txtapellidom = new javax.swing.JTextField();
        cboperfil = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtpass = new javax.swing.JPasswordField();
        lberroringrut = new javax.swing.JLabel();
        lberroringnombre = new javax.swing.JLabel();
        lberroringapellidop = new javax.swing.JLabel();
        lberroringapellidom = new javax.swing.JLabel();
        lberroringperfil = new javax.swing.JLabel();
        lberroringcontraseña = new javax.swing.JLabel();
        btreporte = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablabuscar = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        txtrutfiltro = new javax.swing.JTextField();
        lbrutfiltro = new javax.swing.JLabel();
        lbstring = new javax.swing.JLabel();
        txtstring = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablasesion = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        lblFoto1 = new javax.swing.JLabel();
        btmostrar = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtrutbuscar = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtverificarbuscar = new javax.swing.JTextField();
        txtmodapellidom = new javax.swing.JTextField();
        txtmodnombre = new javax.swing.JTextField();
        txtmodapellidop = new javax.swing.JTextField();
        cboperfilmod = new javax.swing.JComboBox();
        txtmodpass = new javax.swing.JPasswordField();
        lbrutmod = new javax.swing.JLabel();
        btmodificar = new javax.swing.JButton();
        lberrormodgnombre = new javax.swing.JLabel();
        lberrormodapellidop = new javax.swing.JLabel();
        lberrormodapellidom = new javax.swing.JLabel();
        lberrormodperfil = new javax.swing.JLabel();
        lberrormodcontraseña = new javax.swing.JLabel();
        btcerrarsesion = new org.edisoncor.gui.button.ButtonAero();
        labelMetric1 = new org.edisoncor.gui.label.LabelMetric();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Administrador");

        jLabel18.setText("Rut Cliente");

        jLabel19.setText("Email");

        jLabel20.setText("Nombre Cliente");

        jLabel21.setText("Apellido Cliente");

        jLabel22.setText("Celular Cliente");

        jLabel23.setText("Email Cliente");

        jLabel24.setText("Fecha Ingreso");

        jLabel25.setText("Fecha Salida");

        jLabel26.setText("Habitacion Nº");

        jLabel27.setText("Pago");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel46.setText("$");

        jLabel47.setText("-");

        jButton4.setText("Ingresar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel26))
                                .addGap(39, 39, 39))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel46)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                                    .addComponent(jTextField2)
                                    .addComponent(jTextField3)
                                    .addComponent(jTextField4)
                                    .addComponent(jTextField5)
                                    .addComponent(jTextField6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel47)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTextField7, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addComponent(jButton4)))
                .addContainerGap(601, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel46))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        tabbedPaneVertical5.addTab("Ingresar", jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 956, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 469, Short.MAX_VALUE)
        );

        tabbedPaneVertical5.addTab("Modificar", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 956, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 469, Short.MAX_VALUE)
        );

        tabbedPaneVertical5.addTab("Eliminar", jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 956, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 469, Short.MAX_VALUE)
        );

        tabbedPaneVertical5.addTab("Reporte", jPanel4);

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 956, Short.MAX_VALUE)
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 469, Short.MAX_VALUE)
        );

        tabbedPaneVertical5.addTab("Reservas para hoy", jPanel28);

        tabbedSelector21.addTab("Reservas", tabbedPaneVertical5);

        jLabel28.setText("Sector Habitacion");

        jLabel29.setText("Numero");

        jLabel30.setText("Tipo Habitacion");

        jLabel31.setText("Valor");

        jLabel32.setText("Estado");

        lblFoto2.setBackground(new java.awt.Color(102, 102, 102));
        lblFoto2.setForeground(new java.awt.Color(51, 51, 51));

        btfoto1.setText("Seleccionar");
        btfoto1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btfoto1ActionPerformed(evt);
            }
        });

        jLabel33.setText("Foto");

        cboingtipohabitacion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));

        btingresarhabitacion.setText("Ingresar");
        btingresarhabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btingresarhabitacionActionPerformed(evt);
            }
        });

        jLabel43.setText("*");

        jLabel44.setText("*");

        jLabel45.setText("*");

        lbingnumhabitacion.setForeground(new java.awt.Color(255, 0, 0));
        lbingnumhabitacion.setText("Debe ingresar el Numero");

        lbingtipohabitacion.setForeground(new java.awt.Color(255, 0, 0));
        lbingtipohabitacion.setText("Debe ingresar el tipo");

        lbingvalorhabitacion.setForeground(new java.awt.Color(255, 0, 0));
        lbingvalorhabitacion.setText("Debe Ingresar el valor");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel43)
                    .addComponent(jLabel44)
                    .addComponent(jLabel45))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addComponent(jLabel32)
                    .addComponent(jLabel31)
                    .addComponent(jLabel30)
                    .addComponent(jLabel29))
                .addGap(36, 36, 36)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cboingsectorhabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboingtipohabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btingresarhabitacion)
                    .addComponent(txtingnumerohabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtingvalorhabitacion, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(cboingestadohabitacion, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbingnumhabitacion)
                    .addComponent(lbingtipohabitacion)
                    .addComponent(lbingvalorhabitacion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 346, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(btfoto1)
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel33)
                        .addGap(46, 46, 46))
                    .addComponent(lblFoto2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(cboingsectorhabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29)
                            .addComponent(txtingnumerohabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43)
                            .addComponent(lbingnumhabitacion))
                        .addGap(14, 14, 14)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(cboingtipohabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel44)
                            .addComponent(lbingtipohabitacion))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(txtingvalorhabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45)
                            .addComponent(lbingvalorhabitacion))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(cboingestadohabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addComponent(btingresarhabitacion))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblFoto2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel33)
                        .addGap(12, 12, 12)
                        .addComponent(btfoto1)))
                .addContainerGap(157, Short.MAX_VALUE))
        );

        tabbedPaneVertical3.addTab("Ingresar", jPanel6);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 956, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 469, Short.MAX_VALUE)
        );

        tabbedPaneVertical3.addTab("Modficar", jPanel7);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 956, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 469, Short.MAX_VALUE)
        );

        tabbedPaneVertical3.addTab("Eliminar", jPanel8);

        tabbedSelector21.addTab("Habitacion", tabbedPaneVertical3);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 956, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 469, Short.MAX_VALUE)
        );

        tabbedPaneVertical1.addTab("Crear", jPanel5);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 956, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 469, Short.MAX_VALUE)
        );

        tabbedPaneVertical1.addTab("Modificar", jPanel9);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 956, Short.MAX_VALUE)
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 469, Short.MAX_VALUE)
        );

        tabbedPaneVertical1.addTab("Eliminar", jPanel10);

        tabbedSelector21.addTab("Promocion", tabbedPaneVertical1);

        jLabel34.setText("Rut Trabajador");

        jLabel35.setText("Nombre");

        jLabel36.setText("Apellido Paterno");

        jLabel37.setText("Apellido Materno");

        jLabel38.setText("Fecha Ingreso");

        jLabel39.setText("Dia de pago");

        jLabel40.setText("Valor Dia");

        jLabel41.setText("Numero de dias Trabajados");

        jLabel42.setText("fecha de despido");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel42)
                    .addComponent(jLabel41)
                    .addComponent(jLabel40)
                    .addComponent(jLabel39)
                    .addComponent(jLabel38)
                    .addComponent(jLabel37)
                    .addComponent(jLabel36)
                    .addComponent(jLabel35)
                    .addComponent(jLabel34))
                .addContainerGap(744, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel34)
                .addGap(18, 18, 18)
                .addComponent(jLabel35)
                .addGap(18, 18, 18)
                .addComponent(jLabel36)
                .addGap(18, 18, 18)
                .addComponent(jLabel37)
                .addGap(18, 18, 18)
                .addComponent(jLabel38)
                .addGap(18, 18, 18)
                .addComponent(jLabel39)
                .addGap(18, 18, 18)
                .addComponent(jLabel40)
                .addGap(18, 18, 18)
                .addComponent(jLabel41)
                .addGap(28, 28, 28)
                .addComponent(jLabel42)
                .addContainerGap(122, Short.MAX_VALUE))
        );

        tabbedPaneVertical4.addTab("Ingresar", jPanel15);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 956, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 469, Short.MAX_VALUE)
        );

        tabbedPaneVertical4.addTab("Modificar", jPanel16);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 956, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 469, Short.MAX_VALUE)
        );

        tabbedPaneVertical4.addTab("Eliminar", jPanel17);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 956, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 469, Short.MAX_VALUE)
        );

        tabbedPaneVertical4.addTab("Contrato", jPanel18);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 956, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 469, Short.MAX_VALUE)
        );

        tabbedPaneVertical4.addTab("Pagos", jPanel19);

        tabbedSelector21.addTab("Trabajadores", tabbedPaneVertical4);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 956, Short.MAX_VALUE)
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 469, Short.MAX_VALUE)
        );

        tabbedPaneVertical6.addTab("Ingresar Producto Nuevo", jPanel20);

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 956, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 469, Short.MAX_VALUE)
        );

        tabbedPaneVertical6.addTab("Realizar venta", jPanel21);

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 956, Short.MAX_VALUE)
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 469, Short.MAX_VALUE)
        );

        tabbedPaneVertical6.addTab("Agregar Stock", jPanel22);

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 956, Short.MAX_VALUE)
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 469, Short.MAX_VALUE)
        );

        tabbedPaneVertical6.addTab("Stock Critico", jPanel26);

        tabbedSelector21.addTab("Bodega Bar", tabbedPaneVertical6);

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 956, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 469, Short.MAX_VALUE)
        );

        tabbedPaneVertical7.addTab("Ingresar Nuevo Producto", jPanel23);

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 956, Short.MAX_VALUE)
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 469, Short.MAX_VALUE)
        );

        tabbedPaneVertical7.addTab("Realizar Venta", jPanel24);

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 956, Short.MAX_VALUE)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 469, Short.MAX_VALUE)
        );

        tabbedPaneVertical7.addTab("Agregar Stock", jPanel25);

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 956, Short.MAX_VALUE)
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 469, Short.MAX_VALUE)
        );

        tabbedPaneVertical7.addTab("Stock Critico", jPanel27);

        tabbedSelector21.addTab("Bodega Cocina", tabbedPaneVertical7);
        tabbedSelector21.addTab("Pagar $", tabbedPaneVertical8);

        jLabel1.setText("Rut");

        jLabel2.setText("Nombre");

        jLabel3.setText("Apellido Paterno");

        jLabel4.setText("Apellido Materno");

        jLabel5.setText("Perfil");

        jLabel6.setText("Foto");

        lblFoto.setBackground(new java.awt.Color(102, 102, 102));
        lblFoto.setForeground(new java.awt.Color(51, 51, 51));

        btfoto.setText("Seleccionar");
        btfoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btfotoActionPerformed(evt);
            }
        });

        jLabel7.setText("-");

        cboperfil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));

        jButton1.setText("Ingresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel8.setText("Contraseña");

        lberroringrut.setForeground(new java.awt.Color(255, 0, 0));
        lberroringrut.setText("Debe ingresar el rut");

        lberroringnombre.setForeground(new java.awt.Color(255, 0, 0));
        lberroringnombre.setText("Debe ingresar el nombre");

        lberroringapellidop.setForeground(new java.awt.Color(255, 0, 0));
        lberroringapellidop.setText("Debe ingresar el apellido");

        lberroringapellidom.setForeground(new java.awt.Color(255, 0, 0));
        lberroringapellidom.setText("Debe ingresar el apellido");

        lberroringperfil.setForeground(new java.awt.Color(255, 0, 0));
        lberroringperfil.setText("Debe seleccionar perfil");

        lberroringcontraseña.setForeground(new java.awt.Color(255, 0, 0));
        lberroringcontraseña.setText("Debe ingrear contraseña");

        btreporte.setText("Reporte Usuario");
        btreporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btreporteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtrut, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel2))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtapellidop, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtver, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lberroringrut))
                                            .addComponent(lberroringnombre))
                                        .addGap(0, 74, Short.MAX_VALUE))
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(lberroringapellidop)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(148, 148, 148)
                                        .addComponent(btreporte))
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel8))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addComponent(cboperfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lberroringperfil))
                                            .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addGap(6, 6, 6)
                                                .addComponent(txtpass, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lberroringcontraseña))
                                            .addGroup(jPanel11Layout.createSequentialGroup()
                                                .addComponent(txtapellidom, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(lberroringapellidom)))))
                                .addGap(80, 80, 80))))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(btfoto)
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(67, 67, 67))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtrut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtver, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lberroringrut))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lberroringnombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtapellidop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lberroringapellidop))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtapellidom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lberroringapellidom))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jLabel5))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboperfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lberroringperfil))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtpass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lberroringcontraseña)))
                .addGap(35, 35, 35)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addComponent(btreporte)
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addGap(12, 12, 12)
                .addComponent(btfoto)
                .addGap(119, 119, 119))
        );

        tabbedPaneVertical2.addTab("Ingresar", jPanel11);

        tablabuscar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Rut", "Nombres", "Apellido paterno", "Apellido materno", "Perfil"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablabuscar);
        if (tablabuscar.getColumnModel().getColumnCount() > 0) {
            tablabuscar.getColumnModel().getColumn(0).setResizable(false);
            tablabuscar.getColumnModel().getColumn(1).setResizable(false);
            tablabuscar.getColumnModel().getColumn(2).setResizable(false);
            tablabuscar.getColumnModel().getColumn(3).setResizable(false);
            tablabuscar.getColumnModel().getColumn(4).setResizable(false);
        }

        jButton2.setText("Eliminar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel17.setText("Buscar");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "Rut", "Nombre", "Apellido Paterno", "Apellido Materno", "Perfil" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        txtrutfiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtrutfiltroKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtrutfiltroKeyPressed(evt);
            }
        });

        lbrutfiltro.setText("Rut");

        lbstring.setText("lbstring");

        txtstring.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtstringActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 914, Short.MAX_VALUE)
                .addGap(27, 27, 27))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(50, 50, 50))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                .addComponent(lbrutfiltro)
                                .addGap(22, 22, 22))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                                .addComponent(lbstring)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))))
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtstring, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtrutfiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtrutfiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbrutfiltro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbstring)
                    .addComponent(txtstring, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jButton2)
                .addContainerGap(152, Short.MAX_VALUE))
        );

        tabbedPaneVertical2.addTab("Eliminar", jPanel13);

        tablasesion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Rut", "Nombres", "Apellido paterno", "Apellido materno", "Perfil", "hora", "fecha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tablasesion);
        if (tablasesion.getColumnModel().getColumnCount() > 0) {
            tablasesion.getColumnModel().getColumn(0).setResizable(false);
            tablasesion.getColumnModel().getColumn(1).setResizable(false);
            tablasesion.getColumnModel().getColumn(2).setResizable(false);
            tablasesion.getColumnModel().getColumn(3).setResizable(false);
            tablasesion.getColumnModel().getColumn(4).setResizable(false);
            tablasesion.getColumnModel().getColumn(5).setResizable(false);
            tablasesion.getColumnModel().getColumn(6).setResizable(false);
        }

        jButton3.setText("Reporte");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(696, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(168, 168, 168))
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addGap(21, 21, 21)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 914, Short.MAX_VALUE)
                    .addGap(21, 21, 21)))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(347, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(93, 93, 93))
            .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel14Layout.createSequentialGroup()
                    .addGap(143, 143, 143)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(206, Short.MAX_VALUE)))
        );

        tabbedPaneVertical2.addTab("Sesiones", jPanel14);

        lblFoto1.setBackground(new java.awt.Color(102, 102, 102));
        lblFoto1.setForeground(new java.awt.Color(51, 51, 51));

        btmostrar.setText("Buscar");
        btmostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmostrarActionPerformed(evt);
            }
        });

        jLabel9.setText("Rut");

        jLabel10.setText("Nombre");

        jLabel11.setText("Apellido Paterno");

        jLabel12.setText("Apellido Materno");

        jLabel13.setText("Perfil");

        jLabel14.setText("Contraseña");

        jLabel15.setText("Rut");

        jLabel16.setText("-");

        cboperfilmod.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));

        lbrutmod.setText("lbrut");

        btmodificar.setText("Modificar");
        btmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btmodificarActionPerformed(evt);
            }
        });

        lberrormodgnombre.setForeground(new java.awt.Color(255, 0, 0));
        lberrormodgnombre.setText("Debe ingresar el nombre");

        lberrormodapellidop.setForeground(new java.awt.Color(255, 0, 0));
        lberrormodapellidop.setText("Debe ingresar el apellido");

        lberrormodapellidom.setForeground(new java.awt.Color(255, 0, 0));
        lberrormodapellidom.setText("Debe ingresar el apellido");

        lberrormodperfil.setForeground(new java.awt.Color(255, 0, 0));
        lberrormodperfil.setText("Debe seleccionar perfil");

        lberrormodcontraseña.setForeground(new java.awt.Color(255, 0, 0));
        lberrormodcontraseña.setText("Debe ingrear contraseña");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(125, 125, 125)
                                .addComponent(txtrutbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btmostrar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtverificarbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel14))
                                .addGap(86, 86, 86)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboperfilmod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtmodapellidom, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtmodpass, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btmodificar)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11))
                                .addGap(90, 90, 90)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtmodapellidop, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtmodnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbrutmod))))
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addComponent(lberrormodcontraseña))
                                    .addGroup(jPanel12Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(lberrormodperfil)))
                                .addGap(2, 2, 2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lberrormodapellidom, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lberrormodapellidop, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lberrormodgnombre, javax.swing.GroupLayout.Alignment.TRAILING))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblFoto1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(308, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtrutbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16)
                            .addComponent(txtverificarbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btmostrar)
                        .addGap(27, 27, 27)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbrutmod)
                            .addComponent(jLabel15)))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel9)))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFoto1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtmodnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lberrormodgnombre))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtmodapellidop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lberrormodapellidop))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtmodapellidom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lberrormodapellidom))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(cboperfilmod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lberrormodperfil))
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jLabel14))
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtmodpass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lberrormodcontraseña))))))
                .addGap(18, 18, 18)
                .addComponent(btmodificar)
                .addContainerGap(108, Short.MAX_VALUE))
        );

        tabbedPaneVertical2.addTab("Modificar", jPanel12);

        tabbedSelector21.addTab("Usuario", tabbedPaneVertical2);

        btcerrarsesion.setBackground(new java.awt.Color(255, 255, 255));
        btcerrarsesion.setText("Cerrar sesion");
        btcerrarsesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcerrarsesionActionPerformed(evt);
            }
        });

        labelMetric1.setText("Administrador");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(180, 180, 180)
                .addComponent(labelMetric1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btcerrarsesion, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103))
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabbedSelector21, javax.swing.GroupLayout.PREFERRED_SIZE, 1107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btcerrarsesion, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelMetric1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(tabbedSelector21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btcerrarsesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcerrarsesionActionPerformed
        login l = new login();
        this.setVisible(false);
        l.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btcerrarsesionActionPerformed

    private void btfotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btfotoActionPerformed
        
        String caminhoArquivo = "";
        
        JFileChooser arquivo = new JFileChooser();
        arquivo.setDialogTitle("Seleccione una Foto");
        arquivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
        File file = new File("user.dir");
        
        int option = arquivo.showOpenDialog(this);
        
        if (option == JFileChooser.APPROVE_OPTION) {
            
            caminhoArquivo = arquivo.getSelectedFile().getAbsolutePath();
            file = arquivo.getSelectedFile();
            ImageIcon image = new ImageIcon(file.getPath());
            
            int height = image.getIconHeight();
            int width = image.getIconWidth();
            lblFoto.setSize(width, height);
            lblFoto.setIcon(image);
            lblFoto.repaint();
            
            try {
                
                fis = new FileInputStream(arquivo.getSelectedFile());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningun archivo!", "Error!", JOptionPane.ERROR_MESSAGE);
            fis = null;
        }

// TODO add your handling code here:
    }//GEN-LAST:event_btfotoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        int c = 0;
        if (txtrut.getText().isEmpty() || txtver.getText().isEmpty()) {
            lberroringrut.setVisible(true);
            c = c - 1;
        } else {
            lberroringrut.setVisible(false);
            c = c + 1;
        }
        
        if (txtnombre.getText().isEmpty()) {
            lberroringnombre.setVisible(true);
            c = c - 1;
        } else {
            lberroringnombre.setVisible(false);
            c = c + 1;
        }
        
        if (txtapellidop.getText().isEmpty()) {
            lberroringapellidop.setVisible(true);
            c = c - 1;
        } else {
            lberroringapellidop.setVisible(false);
            c = c + 1;
        }
        
        if (txtapellidom.getText().isEmpty()) {
            lberroringapellidom.setVisible(true);
            c = c - 1;
        } else {
            lberroringapellidom.setVisible(false);
            c = c + 1;
        }
        
        if (cboperfil.getSelectedItem().toString().equals("Seleccionar")) {
            lberroringperfil.setVisible(true);
            c = c - 1;
        } else {
            lberroringperfil.setVisible(false);
            c = c + 1;
        }
        
        if (txtpass.getText().isEmpty()) {
            lberroringcontraseña.setVisible(true);
            c = c - 1;
        } else {
            lberroringcontraseña.setVisible(false);
            c = c + 1;
        }
        
        if (c == 6) {
            usuarioCRUD uc = new usuarioCRUD();
            usuario u = new usuario();
            String rut = txtrut.getText() + "-" + txtver.getText();
            u.setRut_user(rut);
            u.setContraseña(txtpass.getText());
            u.setNombre(txtnombre.getText());
            u.setApellidop(txtapellidop.getText());
            u.setApellidom(txtapellidom.getText());
            u.setFoto(fis);
            
            if (cboperfil.getSelectedItem().equals("Administrador")) {
                u.setId_perfil(1);
            } else {
                u.setId_perfil(2);
            }
            uc.insertar(u);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void btmostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmostrarActionPerformed
        usuarioCRUD uc = new usuarioCRUD();
        
        String strrut;
        strrut = txtrutbuscar.getText() + "-" + txtverificarbuscar.getText();
        
        if (txtrutbuscar.getText().isEmpty() || txtverificarbuscar.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe Ingresar un rut para Buscar");
            txtmodnombre.setEnabled(false);
            txtmodapellidop.setEnabled(false);
            txtmodapellidom.setEnabled(false);
            cboperfilmod.setEnabled(false);
            txtmodpass.setEnabled(false);
            txtmodnombre.setText("");
            txtmodapellidop.setText("");
            txtmodapellidom.setText("");
            cboperfilmod.setSelectedItem(0);
            txtmodpass.setText("");
            
            lbrutmod.setVisible(false);
            btmodificar.setEnabled(false);
        } else {
            
            uc.consultarPorrut(strrut);
            String rutm = usuarioCRUD.strut;
            lbrutmod.setText(rutm);
            System.out.println("" + rutm);
            txtmodnombre.setEnabled(true);
            txtmodapellidop.setEnabled(true);
            txtmodapellidom.setEnabled(true);
            txtmodpass.setEnabled(true);
            cboperfilmod.setEnabled(true);
            lbrutmod.setVisible(true);
            btmodificar.setEnabled(true);
            
            txtmodnombre.setText(usuarioCRUD.nombres);
            txtmodapellidop.setText(usuarioCRUD.apellidop);
            txtmodapellidom.setText(usuarioCRUD.apellidom);
            txtmodpass.setText(usuarioCRUD.contraseña);
            if (usuarioCRUD.id == 1) {
                cboperfilmod.setSelectedIndex(1);
            } else if (usuarioCRUD.id == 0) {
                cboperfilmod.setSelectedIndex(0);
                
                txtmodnombre.setEnabled(false);
                txtmodapellidop.setEnabled(false);
                txtmodapellidom.setEnabled(false);
                cboperfilmod.setEnabled(false);
                txtmodpass.setEnabled(false);
                lbrutmod.setVisible(false);
                btmodificar.setEnabled(false);
                
                lberrormodcontraseña.setVisible(false);
                lberrormodperfil.setVisible(false);
                lberrormodgnombre.setVisible(false);
                lberrormodapellidom.setVisible(false);
                lberrormodapellidop.setVisible(false);
            } else if (usuarioCRUD.id == 2) {
                cboperfilmod.setSelectedIndex(2);
            }
            
            CustomImageIcon foto = usuarioCRUD.getFoto(strrut);
            if (foto != null) {
                lblFoto1.setIcon(foto);
            } else {
                lblFoto1.setIcon(null);
            }
            
            lblFoto1.setIcon((Icon) foto);
        }

// TODO add your handling code here:
    }//GEN-LAST:event_btmostrarActionPerformed

    private void btmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btmodificarActionPerformed
        int c = 0;
        if (txtmodnombre.getText().isEmpty()) {
            lberrormodgnombre.setVisible(true);
            c = c - 1;
        } else {
            lberrormodgnombre.setVisible(false);
            
            c = c + 1;
        }
        
        if (txtmodapellidop.getText().isEmpty()) {
            lberrormodapellidop.setVisible(true);
            c = c - 1;
        } else {
            lberrormodapellidop.setVisible(false);
            c = c + 1;
        }
        
        if (txtmodapellidom.getText().isEmpty()) {
            lberrormodapellidom.setVisible(true);
            
            c = c - 1;
        } else {
            lberrormodapellidom.setVisible(false);
            
            c = c + 1;
        }
        
        if (cboperfilmod.getSelectedItem().toString().equals("Seleccionar")) {
            lberrormodperfil.setVisible(true);
            c = c - 1;
        } else {
            lberrormodperfil.setVisible(false);
            
            c = c + 1;
        }
        
        if (txtmodpass.getText().isEmpty()) {
            lberrormodcontraseña.setVisible(true);
            c = c - 1;
        } else {
            lberrormodcontraseña.setVisible(false);
            
            c = c + 1;
        }
        usuarioCRUD uc = new usuarioCRUD();
        String strrut;
        strrut = txtrutbuscar.getText() + "-" + txtverificarbuscar.getText();
        uc.consultarPorrut(strrut);
        int igual = 0;
        if (txtmodnombre.getText().equals(usuarioCRUD.nombres)) {
            igual = igual + 1;
        } else {
            igual = igual - 1;
        }
        
        if (txtmodapellidop.getText().equals(usuarioCRUD.apellidop)) {
            igual = igual + 1;
        } else {
            igual = igual - 1;
        }
        
        if (txtmodapellidom.getText().equals(usuarioCRUD.apellidom)) {
            igual = igual + 1;
        } else {
            igual = igual - 1;
        }
        
        if (cboperfilmod.getSelectedIndex() == usuarioCRUD.id) {
            igual = igual + 1;
        } else {
            igual = igual - 1;
        }
        
        if (txtmodpass.getText().equals(usuarioCRUD.contraseña)) {
            igual = igual + 1;
        } else {
            igual = igual - 1;
        }
        
        if (igual == 5 && c == 5) {
            int pregunta = JOptionPane.showConfirmDialog(null, "No a realizado modificacion desea hacer una");
            if (pregunta == JOptionPane.YES_OPTION) {
                
            } else {
                txtmodnombre.setEnabled(false);
                txtmodapellidop.setEnabled(false);
                txtmodapellidom.setEnabled(false);
                cboperfilmod.setEnabled(false);
                txtmodpass.setEnabled(false);
                lbrutmod.setVisible(false);
                btmodificar.setEnabled(false);
                lblFoto1.setIcon(null);
                lberrormodcontraseña.setVisible(false);
                lberrormodperfil.setVisible(false);
                lberrormodgnombre.setVisible(false);
                lberrormodapellidom.setVisible(false);
                lberrormodapellidop.setVisible(false);
                
                txtmodnombre.setText("");
                txtmodapellidop.setText("");
                txtmodapellidom.setText("");
                cboperfilmod.setSelectedItem(0);
                txtmodpass.setText("");
                
                lbrutmod.setVisible(false);
            }
            
        } else if (c == 5) {
            usuario u = new usuario();
            usuarioCRUD crud = new usuarioCRUD();
            String strrute = txtrutbuscar.getText() + "-" + txtverificarbuscar.getText();
            
            u.setRut_user(strrute);
            
            u.setNombre(txtmodnombre.getText());
            u.setApellidop(txtmodapellidop.getText());
            u.setApellidom(txtmodapellidom.getText());
            u.setId_perfil(cboperfilmod.getSelectedIndex());
            u.setContraseña(txtmodpass.getText());
            crud.modificaruntipodeclase(u);
        } else {
            
        }
    }//GEN-LAST:event_btmodificarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        int LinhaSelecionada = this.tablabuscar.getSelectedRow();
        String CodigoPaciente = this.tablabuscar.getValueAt(LinhaSelecionada, 0).toString();
        usuarioCRUD crud = new usuarioCRUD();
        try {
            //Busca no banco de dados o código do paciente selecionado para ser excluido
            crud.consultarPorrut(CodigoPaciente);

            //Mensagem de confirmação
            String MsgConfirmacao = "Dese realmente borrar el paciente: " + usuarioCRUD.nombres + " ?";
            int OpcaoEscolhida = JOptionPane.showConfirmDialog(null, MsgConfirmacao, "Excluir", JOptionPane.YES_NO_OPTION);
            
            if (OpcaoEscolhida == JOptionPane.YES_OPTION) {
                //Excluindo o usuário após a confirmação positiva
                usuario u = new usuario();
                u.setRut_user(CodigoPaciente);
                crud.eliminarJugador(u);
                
                ArrayList lista = crud.consultarTodos();
                cargartablilla(lista);
            } else {
                //Atualiza jTable
                ArrayList lista = crud.consultarTodos();
                cargartablilla(lista);
            }
        } catch (Exception erroSQL) {
            JOptionPane.showMessageDialog(null, "Error al borrar", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        
        if (jComboBox1.getSelectedIndex() == 0) {
            lbrutfiltro.setVisible(false);
            txtrutfiltro.setVisible(false);
            lbstring.setVisible(false);
            txtstring.setVisible(false);
        } else if (jComboBox1.getSelectedIndex() == 1) {
            lbrutfiltro.setVisible(true);
            txtrutfiltro.setVisible(true);
            lbstring.setVisible(false);
            txtstring.setVisible(false);
        } else if (jComboBox1.getSelectedIndex() != 1 && jComboBox1.getSelectedIndex() != 0) {
            lbrutfiltro.setVisible(false);
            txtrutfiltro.setVisible(false);
            lbstring.setVisible(true);
            lbstring.setText(jComboBox1.getSelectedItem().toString());
            txtstring.setVisible(true);
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void txtrutfiltroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrutfiltroKeyPressed
        if (evt.isControlDown() || evt.isShiftDown()) {
            JOptionPane.showMessageDialog(null, "porfavor escriba su rut");
            evt.consume();
        }        
        usuarioCRUD crud = new usuarioCRUD();
        ArrayList lista = crud.seleccionarporrut(txtrutfiltro.getText());
        cargartablilla(lista);
// TODO add your handling code here:
    }//GEN-LAST:event_txtrutfiltroKeyPressed

    private void txtrutfiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrutfiltroKeyTyped
        char caracter = evt.getKeyChar();
        if (!(Character.isDigit(caracter)) && (caracter != KeyEvent.VK_BACK_SPACE)) {
            JOptionPane.showMessageDialog(null, "solo numeros");
            evt.consume();
        }
        
        if (txtrutfiltro.getText().length() >= 8) {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            
            txtrutfiltro.requestFocus();
        }

// TODO add your handling code here:
    }//GEN-LAST:event_txtrutfiltroKeyTyped

    private void txtstringActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtstringActionPerformed
        usuarioCRUD crud = new usuarioCRUD();

//Perfil
        if (lbstring.getText().equals("Nombre")) {
            
            ArrayList lista = crud.seleccionarpornombre(txtstring.getText());
            cargartablilla(lista);
        } else if (lbstring.getText().equals("Apellido Paterno")) {
            ArrayList lista = crud.seleccionarporapellidop(txtstring.getText());
            cargartablilla(lista);
        } else if (lbstring.getText().equals("Apellido Materno")) {
            ArrayList lista = crud.seleccionarporapellidom(txtstring.getText());
            cargartablilla(lista);
        }

// TODO add your handling code here:
    }//GEN-LAST:event_txtstringActionPerformed

    private void btreporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btreporteActionPerformed
        
        try {
            //String ubicacion = System.getProperty("user.dir"+"/src/reportes/ejemplo.jrmx");
            conectar c = new conectar();
            //JasperReport reporte = (JasperReport) JRLoader.loadObject(ubicacion);
            JasperReport reporte = JasperCompileManager.compileReport("ejemplo.jrxml");
            JasperPrint print = JasperFillManager.fillReport(reporte, null, c.conexion());
            JasperViewer ver = new JasperViewer(print, false);
            ver.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        

    }//GEN-LAST:event_btreporteActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            String ubicacion = System.getProperty("user.dir" + "/src/reportes/ejemplo.jrmx");
            conectar c = new conectar();
            //JasperReport reporte = (JasperReport) JRLoader.loadObject(ubicacion);
            JasperReport reporte = JasperCompileManager.compileReport("src/reportes/sesion.jrxml");
            JasperPrint print = JasperFillManager.fillReport(reporte, null, c.conexion());
            JasperViewer ver = new JasperViewer(print, false);
            ver.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btfoto1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btfoto1ActionPerformed
          
        String caminhoArquivo = "";
        
        JFileChooser arquivo = new JFileChooser();
        arquivo.setDialogTitle("Seleccione una Foto");
        arquivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
        File file = new File("user.dir");
        
        int option = arquivo.showOpenDialog(this);
        
        if (option == JFileChooser.APPROVE_OPTION) {
            
            caminhoArquivo = arquivo.getSelectedFile().getAbsolutePath();
            file = arquivo.getSelectedFile();
            ImageIcon image = new ImageIcon(file.getPath());
            
            int height = image.getIconHeight();
            int width = image.getIconWidth();
            lblFoto2.setSize(width, height);
            lblFoto2.setIcon(image);
            lblFoto2.repaint();
            
            try {
                
                fisds = new FileInputStream(arquivo.getSelectedFile());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningun archivo!", "Error!", JOptionPane.ERROR_MESSAGE);
            fisds = null;
        }
    }//GEN-LAST:event_btfoto1ActionPerformed

    private void btingresarhabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btingresarhabitacionActionPerformed
  int c = 0;
      
        
        if (txtingnumerohabitacion.getText().isEmpty()) {
            lbingnumhabitacion.setVisible(true);
            c = c - 1;
        } else {
            lbingnumhabitacion.setVisible(false);
            c = c + 1;
        }
      
        
        if (cboingtipohabitacion.getSelectedItem().toString().equals("Seleccionar")) {
            lbingtipohabitacion.setVisible(true);
            c = c - 1;
        } else {
            lbingtipohabitacion.setVisible(false);
            c = c + 1;
        }
        
          if (txtingvalorhabitacion.getText().isEmpty()) {
            lbingvalorhabitacion.setVisible(true);
            c = c - 1;
        } else {
            lbingvalorhabitacion.setVisible(false);
            c = c + 1;
        }
     
        
        if (c == 3) {
            habitacionCRUD uc = new habitacionCRUD();
            habitacion u = new habitacion();
            String strnumero = txtingnumerohabitacion.getText();
            String strvalor = txtingvalorhabitacion.getText();
            int numero = Integer.parseInt(strnumero);
            int valor = Integer.parseInt(strvalor);
           u.setNumero(numero);
           u.setId_tipo_habitacion(cboingtipohabitacion.getSelectedIndex() +1);
           u.setId_estado(cboingestadohabitacion.getSelectedIndex() +1);
            u.setValor(valor);
            
            u.setFoto(fisds);
            
            if (cboingsectorhabitacion.getSelectedItem().equals("NO TIENE")) {
                u.setIdsector(0);
            } else {
                u.setIdsector(cboingsectorhabitacion.getSelectedIndex());
            }
            uc.insertar(u);
        }    
        

    }//GEN-LAST:event_btingresarhabitacionActionPerformed

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
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(admin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new admin().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonAero btcerrarsesion;
    private javax.swing.JButton btfoto;
    private javax.swing.JButton btfoto1;
    private javax.swing.JButton btingresarhabitacion;
    private javax.swing.JButton btmodificar;
    private javax.swing.JButton btmostrar;
    private javax.swing.JButton btreporte;
    private javax.swing.JComboBox cboingestadohabitacion;
    private javax.swing.JComboBox cboingsectorhabitacion;
    private javax.swing.JComboBox cboingtipohabitacion;
    private javax.swing.JComboBox cboperfil;
    private javax.swing.JComboBox cboperfilmod;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private org.edisoncor.gui.label.LabelMetric labelMetric1;
    private javax.swing.JLabel lberroringapellidom;
    private javax.swing.JLabel lberroringapellidop;
    private javax.swing.JLabel lberroringcontraseña;
    private javax.swing.JLabel lberroringnombre;
    private javax.swing.JLabel lberroringperfil;
    private javax.swing.JLabel lberroringrut;
    private javax.swing.JLabel lberrormodapellidom;
    private javax.swing.JLabel lberrormodapellidop;
    private javax.swing.JLabel lberrormodcontraseña;
    private javax.swing.JLabel lberrormodgnombre;
    private javax.swing.JLabel lberrormodperfil;
    private javax.swing.JLabel lbingnumhabitacion;
    private javax.swing.JLabel lbingtipohabitacion;
    private javax.swing.JLabel lbingvalorhabitacion;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JLabel lblFoto1;
    private javax.swing.JLabel lblFoto2;
    private javax.swing.JLabel lbrutfiltro;
    private javax.swing.JLabel lbrutmod;
    private javax.swing.JLabel lbstring;
    private org.edisoncor.gui.tabbedPane.TabbedPaneVertical tabbedPaneVertical1;
    private org.edisoncor.gui.tabbedPane.TabbedPaneVertical tabbedPaneVertical2;
    private org.edisoncor.gui.tabbedPane.TabbedPaneVertical tabbedPaneVertical3;
    private org.edisoncor.gui.tabbedPane.TabbedPaneVertical tabbedPaneVertical4;
    private org.edisoncor.gui.tabbedPane.TabbedPaneVertical tabbedPaneVertical5;
    private org.edisoncor.gui.tabbedPane.TabbedPaneVertical tabbedPaneVertical6;
    private org.edisoncor.gui.tabbedPane.TabbedPaneVertical tabbedPaneVertical7;
    private org.edisoncor.gui.tabbedPane.TabbedPaneVertical tabbedPaneVertical8;
    private org.edisoncor.gui.tabbedPane.TabbedSelector2 tabbedSelector21;
    private javax.swing.JTable tablabuscar;
    private javax.swing.JTable tablasesion;
    private javax.swing.JTextField txtapellidom;
    private javax.swing.JTextField txtapellidop;
    private javax.swing.JTextField txtingnumerohabitacion;
    private javax.swing.JTextField txtingvalorhabitacion;
    private javax.swing.JTextField txtmodapellidom;
    private javax.swing.JTextField txtmodapellidop;
    private javax.swing.JTextField txtmodnombre;
    private javax.swing.JPasswordField txtmodpass;
    private javax.swing.JTextField txtnombre;
    private javax.swing.JPasswordField txtpass;
    private javax.swing.JTextField txtrut;
    private javax.swing.JTextField txtrutbuscar;
    private javax.swing.JTextField txtrutfiltro;
    private javax.swing.JTextField txtstring;
    private javax.swing.JTextField txtver;
    private javax.swing.JTextField txtverificarbuscar;
    // End of variables declaration//GEN-END:variables
}
