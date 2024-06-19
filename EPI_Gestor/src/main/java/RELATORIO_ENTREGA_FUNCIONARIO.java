
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import static java.sql.DriverManager.getConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;




/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author vcasotti
 */
public class RELATORIO_ENTREGA_FUNCIONARIO extends javax.swing.JFrame {

    /**
     * Creates new form RELATORIO_ENTREGA_FUNCIONARIO
     */
    public RELATORIO_ENTREGA_FUNCIONARIO() {
        initComponents();
      preencherTabelaComDadosDoRelatorio();
        configurarEventoAbrirArquivo();
        configurarEventoAbrirArquivoDoubleClick();
    }
 private void preencherTabelaComDadosDoRelatorio() {
        String desktopPath = System.getProperty("user.home") + "/Desktop";
        File dir = new File(desktopPath);
        File[] arquivosRelatorio = dir.listFiles((dir1, nome) -> nome.equals("relatorio EPI entregue aos funcionarios.txt"));

        DefaultTableModel model = (DefaultTableModel) jTablerelatorioF.getModel();
        model.setRowCount(0); // Limpa a tabela antes de adicionar os novos dados

        if (arquivosRelatorio == null || arquivosRelatorio.length == 0) {
            JOptionPane.showMessageDialog(this, "Nenhum arquivo de relatório encontrado no Desktop com o nome 'relatorio EPI entregue aos funcionarios.txt'", "Informação", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        for (File arquivoRelatorio : arquivosRelatorio) {
            try (BufferedReader br = new BufferedReader(new FileReader(arquivoRelatorio))) {
                String linha;
                String nomeFuncionario = "";
                String nomeArquivo = "";
                String data = "";
                String caminhoArquivo = arquivoRelatorio.getAbsolutePath();

                while ((linha = br.readLine()) != null) {
                    if (linha.startsWith("Nome Funcionário: ")) {
                        nomeFuncionario = linha.substring("Nome Funcionário: ".length());
                    } else if (linha.startsWith("Nome EPI: ")) {
                        nomeArquivo = linha.substring("Nome EPI: ".length());
                    } else if (linha.startsWith("Data e Hora de Geração: ")) {
                        data = linha.substring("Data e Hora de Geração: ".length());
                    }

                    if (!nomeFuncionario.isEmpty() && !nomeArquivo.isEmpty() && !data.isEmpty()) {
                        model.addRow(new Object[]{nomeFuncionario, nomeArquivo, data, caminhoArquivo});
                        nomeFuncionario = "";
                        nomeArquivo = "";
                        data = "";
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Erro ao ler o arquivo de relatório: " + arquivoRelatorio.getName() + "\n" + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    private void configurarEventoAbrirArquivo() {
        jTablerelatorioF.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int linhaSelecionada = jTablerelatorioF.getSelectedRow();
                    if (linhaSelecionada != -1) {
                        String caminhoArquivo = (String) jTablerelatorioF.getValueAt(linhaSelecionada, 3);
                        abrirArquivo(caminhoArquivo);
                    }
                }
            }
        });

        jTablerelatorioF.setDefaultEditor(Object.class, null); // Desabilitar edição direta das células na tabela
    }

    private void configurarEventoAbrirArquivoDoubleClick() {
        jTablerelatorioF.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    int linhaSelecionada = jTablerelatorioF.getSelectedRow();
                    if (linhaSelecionada != -1) {
                        String caminhoArquivo = (String) jTablerelatorioF.getValueAt(linhaSelecionada, 3);
                        abrirArquivo(caminhoArquivo);
                    }
                }
            }
        });
    }

    private void abrirArquivo(String caminhoArquivo) {
        try {
            File arquivo = new File(caminhoArquivo);
            Desktop desktop = Desktop.getDesktop();
            if (arquivo.exists()) {
                desktop.open(arquivo);
            } else {
                JOptionPane.showMessageDialog(this, "O arquivo não existe: " + caminhoArquivo, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao abrir o arquivo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
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
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablerelatorioF = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTablerelatorioF.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nome Funcionário", "Nome do Arquivo", "Data", "Arquivo"
            }
        ));
        jScrollPane1.setViewportView(jTablerelatorioF);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 740, 330));

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, 60));

        jButton2.setText("jButton2");
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 470, -1, -1));

        jButton3.setText("jButton3");
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 460, 150, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon("D:\\Users\\vcasotti\\Desktop\\Nova pasta\\EPI_Gestor\\EPI_Gestor\\src\\main\\java\\com\\telas\\epi_gestor\\telas\\relatorio entrega F.png")); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 740, 506));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(RELATORIO_ENTREGA_FUNCIONARIO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RELATORIO_ENTREGA_FUNCIONARIO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RELATORIO_ENTREGA_FUNCIONARIO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RELATORIO_ENTREGA_FUNCIONARIO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RELATORIO_ENTREGA_FUNCIONARIO().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablerelatorioF;
    // End of variables declaration//GEN-END:variables

    void carregarRelatoriosDoDesktop() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
