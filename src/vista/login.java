/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vista;

import controlador.usuarioCRUD;
import controlador.validarrut;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import javax.swing.JOptionPane;
import modelo.sesion;
import modelo.usuario;

/**
 *
 * @author Hermann
 */
public class login extends javax.swing.JFrame {

    /**
     * Creates new form login
     */
    public login() {
        initComponents();
        lbrut.setVisible(false);
       jLabel6.setVisible(false);
       setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new org.edisoncor.gui.panel.Panel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtcontraseña = new javax.swing.JPasswordField();
        txtverlog = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtrutlogin = new javax.swing.JTextField();
        buttonSeven1 = new org.edisoncor.gui.button.ButtonSeven();
        jLabel4 = new javax.swing.JLabel();
        lbrut = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");

        panel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondoz.jpg"))); // NOI18N

        jLabel1.setText("Rut");

        jLabel2.setText("Contraseña");

        txtcontraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcontraseñaKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtcontraseñaKeyPressed(evt);
            }
        });

        txtverlog.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtverlogKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtverlogKeyPressed(evt);
            }
        });

        jLabel3.setText("-");

        txtrutlogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtrutloginKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtrutloginKeyPressed(evt);
            }
        });

        buttonSeven1.setText("Aceptar");
        buttonSeven1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSeven1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Login");

        lbrut.setForeground(new java.awt.Color(255, 0, 51));
        lbrut.setText("jLabel5");

        jLabel6.setForeground(new java.awt.Color(255, 0, 51));
        jLabel6.setText("jLabel6");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addGap(0, 193, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(192, 192, 192))
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addGap(28, 28, 28)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(buttonSeven1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtverlog, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(lbrut))
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtrutlogin, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                            .addComponent(txtcontraseña))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6)))))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel4)
                .addGap(42, 42, 42)
                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtverlog, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtrutlogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1)
                                .addComponent(jLabel3)
                                .addComponent(lbrut)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtcontraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonSeven1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(89, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonSeven1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSeven1ActionPerformed
        
        validarrut v = new validarrut();
        int c = 0;
        v.validarrut(txtrutlogin.getText(), txtverlog.getText());
        lbrut.setText(validarrut.msj);
        if(txtrutlogin.getText().isEmpty()){
            lbrut.setVisible(true);
            lbrut.setText("escriba correctamente el rut");
        }else{
            
            lbrut.setText("");
            c = c +1;
        }
        
        
        if(txtverlog.getText().isEmpty()){
            lbrut.setVisible(true);
            lbrut.setText("escriba correctamente el rut");
        }else{
            lbrut.setText("");
            c = c +1;
        }
        
        if(txtcontraseña.getText().isEmpty()){
            jLabel6.setText("Debes escribir la contraseña");
        }else{
            jLabel6.setText("");
            c = c + 1;
        }
        if(c == 3){
            
            String rut = txtrutlogin.getText()+"-"+txtverlog.getText();
            usuario u = new usuario();
            usuarioCRUD ux = new usuarioCRUD();
            u.setRut_user(rut);
            u.setContraseña(txtcontraseña.getText());
            String pass = txtcontraseña.getText();
           ux.Login(rut, pass);
            
           if(usuarioCRUD.l){
               this.setVisible(false);
               
             admin a = new admin();
             a.setVisible(true);
             usuarioCRUD.l = false;
             sesion s = new sesion();
               Calendar cal1 = Calendar.getInstance();
               String fecha;
               String hora;
               
               fecha = cal1.get(Calendar.DATE)+"/"+cal1.get(Calendar.MONTH) +"/"+cal1.get(Calendar.YEAR);
               hora = cal1.get(Calendar.HOUR)+":"+cal1.get(Calendar.MINUTE)+":"+cal1.get(Calendar.SECOND);
 
             s.setRut(rut);
             s.setHora(Time.valueOf(hora));
            s.setFecha(fecha);
             ux.insertarsesion(s);
           }else{
               txtcontraseña.setText("");
               txtrutlogin.setText("");
               txtverlog.setText("");
               JOptionPane.showMessageDialog(null, "error usuario no valido");
           }
        }
// TODO add your handling code here:
    }//GEN-LAST:event_buttonSeven1ActionPerformed

    private void txtrutloginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrutloginKeyPressed
  if (evt.isControlDown() || evt.isShiftDown()) {
            JOptionPane.showMessageDialog(null, "porfavor escriba su rut");
            evt.consume();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtrutloginKeyPressed

    private void txtrutloginKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrutloginKeyTyped
  char caracter = evt.getKeyChar();
        if (!(Character.isDigit(caracter)) && (caracter != KeyEvent.VK_BACK_SPACE)) {
            JOptionPane.showMessageDialog(null, "solo numeros");
            evt.consume();
        }

        if (txtrutlogin.getText().length() >= 8) {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
           
            
            txtrutlogin.requestFocus();
         }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtrutloginKeyTyped

    private void txtcontraseñaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcontraseñaKeyPressed
  if (evt.isControlDown() || evt.isShiftDown()) {
            JOptionPane.showMessageDialog(null, "porfavor escriba la contraseña");
            evt.consume();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcontraseñaKeyPressed

    private void txtcontraseñaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcontraseñaKeyTyped

   char caracter = evt.getKeyChar();
        
 if (txtcontraseña.getText().length() >= 6 ){
   
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
           
            
            txtcontraseña.requestFocus();
         } 
       if (!(caracter != KeyEvent.VK_SPACE)){
           JOptionPane.showMessageDialog(null, "no se permiten espacios");
            evt.consume();
        }        

// TODO add your handling code here:
    }//GEN-LAST:event_txtcontraseñaKeyTyped

    private void txtverlogKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtverlogKeyTyped

     
     int limite = 1;
        if(txtverlog.getText().length() == 1){
        evt.consume();
        }
         char caracter = evt.getKeyChar();
         if ((caracter < '0') || (caracter > '9') && (caracter != 'k')) {
            evt.consume();
        } 
        // TODO add your handling code here:
    }//GEN-LAST:event_txtverlogKeyTyped

    private void txtverlogKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtverlogKeyPressed

     if (evt.isControlDown() || evt.isShiftDown()) {
            JOptionPane.showMessageDialog(null, "porfavor escriba el digito verificador");
            evt.consume();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtverlogKeyPressed

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
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.button.ButtonSeven buttonSeven1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lbrut;
    private org.edisoncor.gui.panel.Panel panel1;
    private javax.swing.JPasswordField txtcontraseña;
    private javax.swing.JTextField txtrutlogin;
    private javax.swing.JTextField txtverlog;
    // End of variables declaration//GEN-END:variables
}
