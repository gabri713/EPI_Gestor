/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author vcasotti
 */
public class VISAO_GERAL_ESTOQUE extends javax.swing.JFrame {

    /**
     * Creates new form VISAO_GERAL_ESTOQUE
     */
    public VISAO_GERAL_ESTOQUE() {
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

        VOLTA_INICIO = new javax.swing.JButton();
        btn_MN_cadastro_F = new javax.swing.JButton();
        cb_estoque = new javax.swing.JComboBox<>();
        cb_relatorios = new javax.swing.JComboBox<>();
        listas = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        VOLTA_INICIO.setText("jButton1");
        VOLTA_INICIO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VOLTA_INICIOActionPerformed(evt);
            }
        });
        getContentPane().add(VOLTA_INICIO, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, -1, 40));

        btn_MN_cadastro_F.setText("jButton2");
        btn_MN_cadastro_F.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_MN_cadastro_FActionPerformed(evt);
            }
        });
        getContentPane().add(btn_MN_cadastro_F, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, -1, 50));

        cb_estoque.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Visão Geral", "Entrada de epis", "Epi para funcionarios", " " }));
        cb_estoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_estoqueActionPerformed(evt);
            }
        });
        getContentPane().add(cb_estoque, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, -1, 60));

        cb_relatorios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Relatorio do Estoque", "Relatorio de  Entrega" }));
        cb_relatorios.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                cb_relatoriosAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        cb_relatorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_relatoriosActionPerformed(evt);
            }
        });
        getContentPane().add(cb_relatorios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 280, -1, 70));

        listas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "EPI em Estoque", "Funcionarios  Cadastrados", "EPI Entregue", " " }));
        listas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listasActionPerformed(evt);
            }
        });
        getContentPane().add(listas, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, -1, 60));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\vitor\\Desktop\\EPI_gestor\\EPI_Gestor\\EPI_Gestor\\src\\main\\java\\com\\telas\\epi_gestor\\telas\\VISAO_GERAL.png")); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 502));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void VOLTA_INICIOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VOLTA_INICIOActionPerformed
        // TODO add your handling code here:
         MENU  VOLTA_INICIO = new MENU();
         VOLTA_INICIO.setVisible(true);

    }//GEN-LAST:event_VOLTA_INICIOActionPerformed

    private void btn_MN_cadastro_FActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_MN_cadastro_FActionPerformed
        // TODO add your handling code here:
        VISAO_GERAL_ESTOQUE.this.dispose();
        CADAStro_FUNCIONARIOS btn_MN_cadastro_F = new CADAStro_FUNCIONARIOS();
        btn_MN_cadastro_F.setVisible(true);
    }//GEN-LAST:event_btn_MN_cadastro_FActionPerformed

    private void cb_estoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_estoqueActionPerformed
        String selectedOption = (String) cb_estoque.getSelectedItem();

        
        if ("Entrada de epis".equals(selectedOption)) {
            VISAO_GERAL_ESTOQUE.this.dispose();
            ENTRADA_pei_estoque EntradaEpiEstoque = new ENTRADA_pei_estoque();
            EntradaEpiEstoque.setVisible(true);
        } else if ("Epi para funcionarios".equals(selectedOption)) {
            VISAO_GERAL_ESTOQUE.this.dispose();
            ENTRADA_epi_funcionarios SaidaParaFuncionarios = new ENTRADA_epi_funcionarios();
            SaidaParaFuncionarios.setVisible(true);
        }
    }//GEN-LAST:event_cb_estoqueActionPerformed

    private void cb_relatoriosAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_cb_relatoriosAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_cb_relatoriosAncestorAdded

    private void cb_relatoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_relatoriosActionPerformed
        // TODO add your handling code here:
        String selectedOption = (String)  cb_relatorios.getSelectedItem();

        if ("Relatorio do Estoque".equals(selectedOption)) {
            VISAO_GERAL_ESTOQUE.this.dispose();
            RELATORIO_ESTOQUE relatorioEstoque = new RELATORIO_ESTOQUE();
            relatorioEstoque.setVisible(true);
        } else if ("Relatorio de  Entrega".equals(selectedOption)) {
            VISAO_GERAL_ESTOQUE.this.dispose();
            RELATORIO_ENTREGA_FUNCIONARIO EntregaEpiEstoque = new RELATORIO_ENTREGA_FUNCIONARIO();
            EntregaEpiEstoque.setVisible(true);
        }
    }//GEN-LAST:event_cb_relatoriosActionPerformed

    private void listasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listasActionPerformed
        // TODO add your handling code here:
        String selectedOption = (String)listas.getSelectedItem();

        if ("EPI em Estoque".equals(selectedOption)) {
            VISAO_GERAL_ESTOQUE.this.dispose();
            LISTA_EPI_EMESTOQUE listaEstoque = new LISTA_EPI_EMESTOQUE();
            listaEstoque.setVisible(true);
        } else if ("Funcionarios  Cadastrados".equals(selectedOption)) {
            VISAO_GERAL_ESTOQUE.this.dispose();
            LISTA_FUNCIONARIOS lisEpiEntrgue = new LISTA_FUNCIONARIOS();
            lisEpiEntrgue.setVisible(true);
        } else if ("EPI Entregue".equals(selectedOption)) {
            VISAO_GERAL_ESTOQUE.this.dispose();
            EPI_ENTREGUE lisEpiEntrgue = new EPI_ENTREGUE();
            lisEpiEntrgue.setVisible(true);
        }
    }//GEN-LAST:event_listasActionPerformed

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
            java.util.logging.Logger.getLogger(VISAO_GERAL_ESTOQUE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VISAO_GERAL_ESTOQUE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VISAO_GERAL_ESTOQUE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VISAO_GERAL_ESTOQUE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VISAO_GERAL_ESTOQUE().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton VOLTA_INICIO;
    private javax.swing.JButton btn_MN_cadastro_F;
    private javax.swing.JComboBox<String> cb_estoque;
    private javax.swing.JComboBox<String> cb_relatorios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JComboBox<String> listas;
    // End of variables declaration//GEN-END:variables
}