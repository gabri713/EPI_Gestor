/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.telas.epi_gestor.telas;

import com.mycompany.epi_gestor.telas.administrador;
import javax.swing.JOptionPane;

/**
 *
 * @author vitor
 */
public class tela_login extends javax.swing.JFrame {

    administrador adm= new administrador("admin@gmail.com","admin");
    /**
     * Creates new form tela_login
     */
    public tela_login() {
        initComponents();
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
        btnlogin = new javax.swing.JButton();
        txtemail = new javax.swing.JTextField();
        txtsenha = new javax.swing.JPasswordField();
        JBTesqueceu2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnlogin.setBackground(new java.awt.Color(255, 0, 255));
        btnlogin.setForeground(new java.awt.Color(255, 255, 255));
        btnlogin.setText("LOGIN");
        btnlogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnloginActionPerformed(evt);
            }
        });
        jPanel1.add(btnlogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 280, 150, 40));

        txtemail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtemailActionPerformed(evt);
            }
        });
        jPanel1.add(txtemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 170, 230, 40));
        jPanel1.add(txtsenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 230, 230, 40));

        JBTesqueceu2.setBackground(new java.awt.Color(255, 0, 255));
        JBTesqueceu2.setForeground(new java.awt.Color(255, 255, 255));
        JBTesqueceu2.setText("ESQUECEU SUA SENHA");
        JBTesqueceu2.setMaximumSize(new java.awt.Dimension(72, 23));
        JBTesqueceu2.setMinimumSize(new java.awt.Dimension(72, 23));
        JBTesqueceu2.setPreferredSize(new java.awt.Dimension(72, 23));
        JBTesqueceu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBTesqueceu2ActionPerformed(evt);
            }
        });
        jPanel1.add(JBTesqueceu2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 330, 170, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Gabriel Resende\\Desktop\\tech tech\\EPI_Gestor\\EPI_Gestor\\src\\main\\java\\com\\telas\\sistema\\tela_login.png")); // NOI18N
        jLabel1.setText("jLabel1");
        jLabel1.setMaximumSize(new java.awt.Dimension(1001, 600));
        jLabel1.setPreferredSize(new java.awt.Dimension(1001, 600));
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -20, 750, 530));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnloginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnloginActionPerformed
        // TODO add your handling code here:
        String email = txtemail.getText();
        String senha = String.valueOf(txtsenha.getPassword());
        
        if (email .equals(adm.getEmail()) && senha.equals(adm.getSenha())) {
            Funcionarios f = new Funcionarios ();
            this.dispose();
            f.setVisible(true);
            JOptionPane.showMessageDialog(this,"Email ou senha incorreta!");
        }
        tela_login.this.dispose();
        tela_menu btnlogin = new tela_menu();
        btnlogin.setVisible(true);
        
        
        
    }//GEN-LAST:event_btnloginActionPerformed

    private void txtemailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtemailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtemailActionPerformed

    private void JBTesqueceu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBTesqueceu2ActionPerformed
        // TODO add your handling code here:
        tela_login.this.dispose();
        tela_redefinir_senha JBTesqueceu2 = new tela_redefinir_senha();
        JBTesqueceu2.setVisible(true);
    }//GEN-LAST:event_JBTesqueceu2ActionPerformed

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
            java.util.logging.Logger.getLogger(tela_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tela_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tela_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tela_login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tela_login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBTesqueceu2;
    private javax.swing.JButton btnlogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtemail;
    private javax.swing.JPasswordField txtsenha;
    // End of variables declaration//GEN-END:variables
}