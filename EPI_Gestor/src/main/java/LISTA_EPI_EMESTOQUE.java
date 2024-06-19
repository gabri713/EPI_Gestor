
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
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
public class LISTA_EPI_EMESTOQUE extends javax.swing.JFrame {

    private final String URL = "jdbc:mysql://localhost:/sistema";
        private final String USER = "root";
        private final String PASSWORD = "";
        
        public Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    /**
     * Creates new form LISTA_EPI_EMESTOQUE
     */
    public LISTA_EPI_EMESTOQUE() {
        initComponents();
       adicionarPesquisaListener();
    
    }
    
     public void preencherTabela2() {
    
    
        try {
            // Conectar ao banco de dados
            Connection con = getConnection();

            // Consulta SQL para selecionar todas as entradas de EPI
            String sql = "SELECT fornecedor, nome_epi, id_epi, tipo_epi, quantidade, dt_entrada, validade FROM EntradaEPI";

            // Criar um objeto Statement para executar a consulta
            Statement stmt = con.createStatement();

            // Executar a consulta e obter o resultado
            ResultSet rs = stmt.executeQuery(sql);

            // Criar um modelo de tabela padrão
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

            // Limpar todas as linhas existentes na tabela
            model.setRowCount(0);

            // Percorrer o resultado e adicionar cada entrada de EPI à tabela
            while (rs.next()) {
                String fornecedor = rs.getString("fornecedor");
                String nomeEpi = rs.getString("nome_epi"); // Adicionando a obtenção do nome_epi
                int idEPI = rs.getInt("id_epi");
                String tipoEPI = rs.getString("tipo_epi");
                int quantidade = rs.getInt("quantidade");
                Date dt_entrada = rs.getDate("dt_entrada");
                Date validade = rs.getDate("validade");

                // Adicionando a linha ao modelo na ordem desejada
                model.addRow(new Object[]{fornecedor, nomeEpi, idEPI, tipoEPI, quantidade, dt_entrada, validade});
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
        txt_pesquisa.getDocument().addDocumentListener(new DocumentListener() {
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
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        jTable1.setRowSorter(sorter);

        String textoPesquisa = txt_pesquisa.getText().toLowerCase();
        if (textoPesquisa.trim().length() == 0) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + textoPesquisa));
        }
    }

    
    public static void gerarRelatorio2() {
      try {
        ENTRADA_pei_estoque entradaPeiEstoque = new ENTRADA_pei_estoque();
        Connection con = entradaPeiEstoque.getConnection();

        String sqlSelect = "SELECT * FROM EntradaEPI";
        PreparedStatement stmt = con.prepareStatement(sqlSelect);
        ResultSet rs = stmt.executeQuery();

        StringBuilder relatorio = new StringBuilder();
        relatorio.append("Relatório de Dados do Estoque:\n\n");

        int totalEPIs = 0; // Variável para armazenar o total de EPIs

        while (rs.next()) {
            relatorio.append("ID: ").append(rs.getInt("id_epi")).append("\n");
            relatorio.append("Nome EPI: ").append(rs.getString("nome_epi")).append("\n");
            relatorio.append("Tipo EPI: ").append(rs.getString("tipo_epi")).append("\n");
            relatorio.append("Quantidade: ").append(rs.getInt("quantidade")).append("\n");
            relatorio.append("Fornecedor: ").append(rs.getString("fornecedor")).append("\n");
            relatorio.append("Data de Entrada: ").append(rs.getDate("dt_entrada")).append("\n");
            relatorio.append("Data de Validade: ").append(rs.getDate("validade")).append("\n\n");
            relatorio.append("___________________\n\n"); // Adiciona separador entre os blocos

            totalEPIs += rs.getInt("quantidade"); // Adiciona a quantidade de EPIs ao total
        }

        con.close(); // Fechar a conexão com o banco de dados

        // Adicionar total de EPIs ao relatório
        relatorio.append("Total de EPIs cadastrados: ").append(totalEPIs).append("\n");

        // Salvar o relatório no desktop do usuário
        String desktopPath = System.getProperty("user.home") + "/Desktop/ vitor.txt";
        FileWriter writer = new FileWriter(desktopPath);
        writer.write(relatorio.toString());
        writer.close();

        JOptionPane.showMessageDialog(null, "Relatório  gerado com sucesso no desktop!");

    } catch (SQLException | IOException e) {
        JOptionPane.showMessageDialog(null, "Erro ao gerar relatório : " + e.getMessage());
    }
}
    
   
public void carregarDadosEPIEstoque() {
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
   
 
    // Exemplo de conexão com o banco de dados e consulta para obter os dados de EPI em estoque
    try {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:/sistema", "root", "");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM EntradaEPI");
        
        while (rs.next()) {
           model.addRow(new Object[]{rs.getString("fornecedor"), rs.getString("nome_epi"), rs.getString("id_epi"), rs.getString("tipo_epi"), rs.getInt("quantidade"), rs.getString("dt_entrada"), rs.getString("validade")});
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
        jTable1 = new javax.swing.JTable();
        txt_pesquisa = new javax.swing.JTextField();
        gerar_relatorio2 = new javax.swing.JButton();
        lista_pesquisa = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        VOLTA_INICIO.setText("jButton1");
        VOLTA_INICIO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VOLTA_INICIOActionPerformed(evt);
            }
        });
        jPanel1.add(VOLTA_INICIO, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, -1, 40));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Fornecedor", "Nome do EPI", "ID do EPI", "Tipo do EPI", "Quantidade", "Data de Entrada", "Validade"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 700, 270));

        txt_pesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_pesquisaActionPerformed(evt);
            }
        });
        jPanel1.add(txt_pesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 510, 30));

        gerar_relatorio2.setText("jButton1");
        gerar_relatorio2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gerar_relatorio2ActionPerformed(evt);
            }
        });
        jPanel1.add(gerar_relatorio2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 470, 140, 30));

        lista_pesquisa.setIcon(new javax.swing.ImageIcon("D:\\Users\\vcasotti\\Desktop\\Nova pasta\\EPI_Gestor\\EPI_Gestor\\src\\main\\java\\com\\telas\\epi_gestor\\telas\\LISTA_EPIS.png")); // NOI18N
        lista_pesquisa.setText("jLabel1");
        jPanel1.add(lista_pesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 760, 509));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 503));

        pack();
    }// </editor-fold>//GEN-END:initComponents

        
    
    private void VOLTA_INICIOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VOLTA_INICIOActionPerformed
        // TODO add your handling code here:
         MENU  VOLTA_INICIO = new MENU();
         VOLTA_INICIO.setVisible(true);

    }//GEN-LAST:event_VOLTA_INICIOActionPerformed

    private void txt_pesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_pesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_pesquisaActionPerformed

    private void gerar_relatorio2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gerar_relatorio2ActionPerformed
        // TODO add your handling code here:
        gerarRelatorio2();
    }//GEN-LAST:event_gerar_relatorio2ActionPerformed

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
            java.util.logging.Logger.getLogger(LISTA_EPI_EMESTOQUE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LISTA_EPI_EMESTOQUE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LISTA_EPI_EMESTOQUE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LISTA_EPI_EMESTOQUE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LISTA_EPI_EMESTOQUE().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton VOLTA_INICIO;
    private javax.swing.JButton gerar_relatorio2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lista_pesquisa;
    private javax.swing.JTextField txt_pesquisa;
    // End of variables declaration//GEN-END:variables
}
