
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultCellEditor;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author vcasotti
 */
public class LISTA_FUNCIONARIOS extends javax.swing.JFrame {

       private final String URL = "jdbc:mysql://localhost:/sistema";
        private final String USER = "root";
        private final String PASSWORD = "";
        
        public Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);
}
    
    /**
     * Creates new form LISTA_FUNCIONARIOS
     */
    public LISTA_FUNCIONARIOS() {
        initComponents();
        adicionarPesquisaListener();
        preencherTabela();
        TXT_pesquisa2.setBackground (new java.awt.Color(0,0,0,1)); 
        VOLTA_INICIO.setBackground (new java.awt.Color(0,0,0,0)); 
BTN_SALVAR .setBackground (new java.awt.Color(0,0,0,0)); 
 
jButton1.setBackground (new java.awt.Color(0,0,0,0)); 
    }

    public void preencherTabela() {
        try {
            // Conectar ao banco de dados
            Connection con = getConnection();
            
            // Consulta SQL para selecionar todos os funcionários
            String sql = "SELECT * FROM Funcionarios";
            
            // Criar um objeto Statement para executar a consulta
            Statement stmt = con.createStatement();
            
            // Executar a consulta e obter o resultado
            ResultSet rs = stmt.executeQuery(sql);
            
            // Criar um modelo de tabela padrão
            DefaultTableModel model = (DefaultTableModel) tb_listaF.getModel();
            
            // Limpar todas as linhas existentes na tabela
            model.setRowCount(0);
            
            // Percorrer o resultado e adicionar cada funcionário à tabela
            while (rs.next()) {
                String nome = rs.getString("nome");
                String dataNascimento = rs.getString("data_nascimento");
                String endereco = rs.getString("endereco");
                String cargo = rs.getString("cargo");
                String cpf = rs.getString("cpf");
                String sexo = rs.getString("sexo");
                String estadoCivil = rs.getString("estado_civil");
                String carteiraTrabalho = rs.getString("carteira_trabalho");
                
                // Adicionar os dados do funcionário como uma nova linha na tabela
                model.addRow(new Object[]{nome, dataNascimento, endereco, cargo, cpf, sexo, estadoCivil, carteiraTrabalho});
            }
            
            // Fechar as conexões e recursos
            rs.close();
            stmt.close();
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao preencher tabela: " + ex.getMessage());
        }
    }
    
    private void adicionarPesquisaListener() {
    TXT_pesquisa2.getDocument().addDocumentListener(new DocumentListener() {
        public void insertUpdate(DocumentEvent e) {
            filtrarTabela();
        }

        public void removeUpdate(DocumentEvent e) {
            filtrarTabela();
        }

        public void changedUpdate(DocumentEvent e) {
            filtrarTabela();
        }
    });
}

private void filtrarTabela() {
    DefaultTableModel model = (DefaultTableModel) tb_listaF.getModel();
    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
    tb_listaF.setRowSorter(sorter);

    String textoPesquisa = TXT_pesquisa2.getText().toLowerCase();
    if (textoPesquisa.trim().length() == 0) {
        sorter.setRowFilter(null);
    } else {
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + textoPesquisa));
    }
}

public void carregarDadosFuncionarios() {
    DefaultTableModel model = (DefaultTableModel) tb_listaF.getModel();
    
    
    // Exemplo de conexão com o banco de dados e consulta para obter os dados dos funcionários cadastrados
    try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:/sistema", "root", "");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Funcionarios");
        
        while (rs.next()) {
            model.addRow(new Object[]{rs.getString("nome"), rs.getString("data_nascimento"), rs.getString("endereco"), rs.getString("cargo")});
        }
        
        con.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
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
        VOLTA_INICIO = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_listaF = new javax.swing.JTable();
        BTN_SALVAR = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        TXT_pesquisa2 = new javax.swing.JTextField();
        BTN_CANCELAR = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        VOLTA_INICIO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VOLTA_INICIOActionPerformed(evt);
            }
        });
        jPanel1.add(VOLTA_INICIO, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, 70));

        tb_listaF.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Data Nascimento", "Endereço", "Cargo"
            }
        ));
        tb_listaF.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tb_listaFAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jScrollPane2.setViewportView(tb_listaF);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 760, 270));

        BTN_SALVAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_SALVARActionPerformed(evt);
            }
        });
        jPanel1.add(BTN_SALVAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 450, 160, 30));

        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 440, 150, 40));

        TXT_pesquisa2.setBorder(null);
        TXT_pesquisa2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXT_pesquisa2ActionPerformed(evt);
            }
        });
        jPanel1.add(TXT_pesquisa2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 500, 40));

        BTN_CANCELAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/telas/epi_gestor/telas/TTela funcionarios cadastrados.png"))); // NOI18N
        BTN_CANCELAR.setText("jLabel1");
        BTN_CANCELAR.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                BTN_CANCELARAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jPanel1.add(BTN_CANCELAR, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 808, 509));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void VOLTA_INICIOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VOLTA_INICIOActionPerformed
        // TODO add your handling code here:
         MENU  VOLTA_INICIO = new MENU();
         VOLTA_INICIO.setVisible(true);

    }//GEN-LAST:event_VOLTA_INICIOActionPerformed

    private void BTN_SALVARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_SALVARActionPerformed
       
        // Percorrer cada linha da tabela
    for (int i = 0; i < tb_listaF.getRowCount(); i++) {
        // Recuperar os valores editados
        String nome = tb_listaF.getValueAt(i, 0).toString();
        String dataNascimento = tb_listaF.getValueAt(i, 1).toString();
        String endereco = tb_listaF.getValueAt(i, 2).toString();
        String cargo = tb_listaF.getValueAt(i, 3).toString();

        // Atualizar os registros correspondentes no banco de dados
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:/sistema", "root", "");
            String sql = "UPDATE Funcionarios SET nome = ?, data_nascimento = ?, endereco = ?, cargo = ? WHERE id = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, dataNascimento);
            stmt.setString(3, endereco);
            stmt.setString(4, cargo);
            stmt.setInt(5, i + 1); // Supondo que o id do funcionário corresponda à posição na tabela (começando de 1)
            stmt.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar alterações: " + ex.getMessage());
        }
    }
    JOptionPane.showMessageDialog(this, "Alterações salvas com sucesso!");
    }//GEN-LAST:event_BTN_SALVARActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
 MENU  btn_cancelar = new MENU();
         btn_cancelar.setVisible(true);           
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tb_listaFAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tb_listaFAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tb_listaFAncestorAdded

    private void BTN_CANCELARAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_BTN_CANCELARAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_BTN_CANCELARAncestorAdded

    private void TXT_pesquisa2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_pesquisa2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TXT_pesquisa2ActionPerformed

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
            java.util.logging.Logger.getLogger(LISTA_FUNCIONARIOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LISTA_FUNCIONARIOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LISTA_FUNCIONARIOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LISTA_FUNCIONARIOS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LISTA_FUNCIONARIOS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel BTN_CANCELAR;
    private javax.swing.JButton BTN_SALVAR;
    private javax.swing.JTextField TXT_pesquisa2;
    private javax.swing.JButton VOLTA_INICIO;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tb_listaF;
    // End of variables declaration//GEN-END:variables

}

   
