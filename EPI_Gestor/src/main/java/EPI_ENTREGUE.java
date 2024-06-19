
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
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
public class EPI_ENTREGUE extends javax.swing.JFrame {
    
     private static class DatabaseConnection {
        private static final String URL = "jdbc:mysql://localhost:/sistema";
        private static final String USER = "root";
        private static final String PASSWORD = "";

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
    }

    /**
     * Creates new form EPI_ENTREGUE
     */
    public EPI_ENTREGUE() {
         initComponents();
          adicionarPesquisaListener();
    }

     public void preencherTabela3() {
       
        try {
            // Conectar ao banco de dados
            Connection con = DatabaseConnection.getConnection();

            // Consulta SQL para selecionar todas as entradas de EPI para funcionários
            String sql = "SELECT id_epi, nome_funcionario, nome_epi, quantidade FROM epi_para_funcionarios";

            // Criar um objeto Statement para executar a consulta
            Statement stmt = con.createStatement();

            // Executar a consulta e obter o resultado
            ResultSet rs = stmt.executeQuery(sql);

            // Criar um modelo de tabela padrão
            DefaultTableModel model = (DefaultTableModel) tb_epiENtregue.getModel();

            // Limpar todas as linhas existentes na tabela
            model.setRowCount(0);

            // Percorrer o resultado e adicionar cada entrada de EPI para funcionários à tabela
            while (rs.next()) {
                int idEPI = rs.getInt("id_epi");
                String nomeFuncionario = rs.getString("nome_funcionario");
                String nomeEPI = rs.getString("nome_epi");
                int quantidade = rs.getInt("quantidade");

                // Adicionar os dados da entrada de EPI como uma nova linha na tabela
                model.addRow(new Object[]{idEPI, nomeFuncionario, nomeEPI, quantidade});
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
        txt_pesquisa3.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrarTabela();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrarTabela();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrarTabela();
            }
        });
    }

    private void filtrarTabela() {
        DefaultTableModel model = (DefaultTableModel) tb_epiENtregue.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        tb_epiENtregue.setRowSorter(sorter);

        String textoPesquisa = txt_pesquisa3.getText().toLowerCase();
        if (textoPesquisa.trim().length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + textoPesquisa));
        }
    }

    public static void gerarRelatorio2() {
     try {
            ENTRADA_epi_funcionarios lsf = new ENTRADA_epi_funcionarios();
            Connection con = lsf.getConnection();

            String sqlSelect = "SELECT * FROM epi_para_funcionarios";
            PreparedStatement stmt = con.prepareStatement(sqlSelect);
            ResultSet rs = stmt.executeQuery();

            StringBuilder relatorio = new StringBuilder();
            relatorio.append("Relatório de Dados de saída de EPIs para funcionários:\n\n");

            // Adicionar a data e hora de geração do relatório
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            String formattedNow = now.format(formatter);
            relatorio.append("Data e Hora de Geração: ").append(formattedNow).append("\n\n");

            int totalEPIs = 0;

            while (rs.next()) {
                relatorio.append("Nome Funcionário: ").append(rs.getString("nome_funcionario")).append("\n");
                relatorio.append("ID: ").append(rs.getInt("id_epi")).append("\n");
                relatorio.append("Nome EPI: ").append(rs.getString("nome_epi")).append("\n");
                relatorio.append("Tipo EPI: ").append(rs.getString("tipo_epi")).append("\n");
                relatorio.append("Quantidade: ").append(rs.getInt("quantidade")).append("\n");
                relatorio.append("Proteção: ").append(rs.getString("protecao")).append("\n");
                relatorio.append("___________________\n\n");

                totalEPIs += rs.getInt("quantidade");
            }

            con.close();

            relatorio.append("Total de EPIs cadastrados: ").append(totalEPIs).append("\n");

            String desktopPath = System.getProperty("user.home") + "/Desktop/relatorio EPI entregue aos funcionarios.txt";
            FileWriter writer = new FileWriter(desktopPath);
            writer.write(relatorio.toString());
            writer.close();

            JOptionPane.showMessageDialog(null, "Relatório gerado com sucesso no desktop!");

        } catch (SQLException | IOException e) {
            JOptionPane.showMessageDialog(null, "Erro ao gerar relatório: " + e.getMessage());
        }
    }

    public void carregarDadosEPIEntregue() {
        DefaultTableModel model = (DefaultTableModel) tb_epiENtregue.getModel();

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:/sistema", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM epi_para_funcionarios");

            while (rs.next()) {
                model.addRow(new Object[]{rs.getInt("id"), rs.getString("nome_funcionario"), rs.getString("nome_epi"), rs.getInt("quantidade")});
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

        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        VOLTA_INICIO = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_epiENtregue = new javax.swing.JTable();
        txt_pesquisa3 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        gerar_relatorio = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentHidden(java.awt.event.ComponentEvent evt) {
                formComponentHidden(evt);
            }
        });

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        VOLTA_INICIO.setText("jButton1");
        VOLTA_INICIO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VOLTA_INICIOActionPerformed(evt);
            }
        });
        jPanel2.add(VOLTA_INICIO, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 70, 70));

        tb_epiENtregue.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID DO EPI", "NOME DO FUNCIONARIO", "NOME DO EPI", "QUANTIDADE"
            }
        ));
        jScrollPane1.setViewportView(tb_epiENtregue);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 750, 270));

        txt_pesquisa3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_pesquisa3ActionPerformed(evt);
            }
        });
        jPanel2.add(txt_pesquisa3, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 122, 510, 30));

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 450, -1, -1));

        gerar_relatorio.setIcon(new javax.swing.ImageIcon("D:\\Users\\vcasotti\\Desktop\\Nova pasta\\EPI_Gestor\\EPI_Gestor\\src\\main\\java\\com\\telas\\epi_gestor\\telas\\epi_ENTRGUE FUNCIONARIO.png")); // NOI18N
        gerar_relatorio.setText("jLabel1");
        jPanel2.add(gerar_relatorio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 802, 480));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void VOLTA_INICIOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VOLTA_INICIOActionPerformed
 MENU  VOLTA_INICIO = new MENU();
         VOLTA_INICIO.setVisible(true);        // TODO add your handling code here:

    }//GEN-LAST:event_VOLTA_INICIOActionPerformed

    private void txt_pesquisa3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_pesquisa3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_pesquisa3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        gerarRelatorio2();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formComponentHidden(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentHidden
        // TODO add your handling code here:
    }//GEN-LAST:event_formComponentHidden

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
            java.util.logging.Logger.getLogger(EPI_ENTREGUE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EPI_ENTREGUE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EPI_ENTREGUE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EPI_ENTREGUE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EPI_ENTREGUE().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton VOLTA_INICIO;
    private javax.swing.JLabel gerar_relatorio;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tb_epiENtregue;
    private javax.swing.JTextField txt_pesquisa3;
    // End of variables declaration//GEN-END:variables
}
