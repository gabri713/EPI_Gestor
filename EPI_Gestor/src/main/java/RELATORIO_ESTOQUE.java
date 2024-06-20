
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;





/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author vcasotti
 */
public class RELATORIO_ESTOQUE extends javax.swing.JFrame {

    /**
     * Creates new form RELATORIO_ESTOQUE
     */
    public RELATORIO_ESTOQUE() {
        initComponents();
        carregarDadosDoArquivo();
        TXT_volta.setBackground (new java.awt.Color(0,0,0,0));
    }
   private void carregarDadosDoArquivo() {
        // Caminho da pasta da área de trabalho do usuário
        String desktopPath = System.getProperty("user.home") + "\\Desktop\\";
        
        // Procurar por arquivos .txt na área de trabalho
        File desktopFolder = new File(desktopPath);
        File[] files = desktopFolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));

        if (files != null && files.length > 0) {
            // Escolher o primeiro arquivo .txt encontrado (poderia ser mais elaborado para escolher o arquivo certo)
            File arquivoTxt = files[0];

            // Modelo da tabela
            DefaultTableModel model = new DefaultTableModel(
                new Object [][] {},
                new String [] {
                    "ID", "Nome EPI", "Tipo EPI", "Quantidade", "Fornecedor", "Data de Entrada", "Data de Validade", "Caminho do Arquivo"
                }
            ) {
                // Desabilitar edição de células da tabela
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
            
            // Aplicar o modelo à tabela
            tabelaE.setModel(model);

            // Limpar dados existentes da tabela (se houver)
            model.setRowCount(0);

            // Ler o arquivo e preencher a tabela
            try (BufferedReader br = new BufferedReader(new FileReader(arquivoTxt))) {
                String line;
                Object[] rowData = new Object[8]; // Vetor para armazenar os dados de cada linha

                while ((line = br.readLine()) != null) {
                    // Supondo que cada linha do arquivo tenha um formato específico
                    String[] data = line.split(": "); // Dividir a linha pelo separador ": "
                    
                    // Verificar o campo e preencher o vetor de dados correspondente
                    switch (data[0]) {
                        case "ID":
                            rowData[0] = data[1];
                            break;
                        case "Nome EPI":
                            rowData[1] = data[1];
                            break;
                        case "Tipo EPI":
                            rowData[2] = data[1];
                            break;
                        case "Quantidade":
                            rowData[3] = data[1];
                            break;
                        case "Fornecedor":
                            rowData[4] = data[1];
                            break;
                        case "Data de Entrada":
                            rowData[5] = data[1];
                            break;
                        case "Data de Validade":
                            rowData[6] = data[1];
                            break;
                        default:
                            // Ignorar campos desconhecidos ou não relevantes
                            break;
                    }
                }
                
                // Adicionar o caminho do arquivo na última coluna da tabela
                rowData[7] = arquivoTxt.getAbsolutePath();
                
                // Adicionar a linha na tabela após processar todas as colunas
                model.addRow(rowData);
                
                // Configurar o listener para abrir o arquivo quando clicar na célula correspondente
                tabelaE.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getClickCount() == 2) { // Verificar se é um clique duplo
                            int row = tabelaE.getSelectedRow();
                            int column = tabelaE.getSelectedColumn();

                            // Verificar se o clique foi na coluna correta (última coluna da tabela)
                            if (column == 7) { // Índice da última coluna (começa em 0)
                                try {
                                    String filePath = (String) tabelaE.getValueAt(row, column);
                                    File file = new File(filePath);
                                    if (file.exists()) {
                                        Desktop.getDesktop().open(file);
                                    } else {
                                        JOptionPane.showMessageDialog(RELATORIO_ESTOQUE.this, "Arquivo não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                                    }
                                } catch (IOException ex) {
                                    Logger.getLogger(RELATORIO_ESTOQUE.class.getName()).log(Level.SEVERE, null, ex);
                                    JOptionPane.showMessageDialog(RELATORIO_ESTOQUE.this, "Erro ao abrir o arquivo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        }
                    }
                });
                
            } catch (IOException ex) {
                Logger.getLogger(RELATORIO_ESTOQUE.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Erro ao ler o arquivo: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nenhum arquivo .txt encontrado na área de trabalho.", "Erro", JOptionPane.ERROR_MESSAGE);
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

        jPanel2 = new javax.swing.JPanel();
        TXT_volta = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaE = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TXT_volta.setToolTipText("");
        TXT_volta.setBorder(null);
        TXT_volta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXT_voltaActionPerformed(evt);
            }
        });
        jPanel2.add(TXT_volta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, 80));

        tabelaE.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome do EPI", "Tipo do EPI", "Quantidade", "Fornecedor", "Data de Entrada", "Data de Validade", "Title 8"
            }
        ));
        jScrollPane1.setViewportView(tabelaE);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 720, 340));

        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\vitor\\Desktop\\EPI_gestor\\EPI_Gestor\\EPI_Gestor\\src\\main\\java\\com\\telas\\epi_gestor\\telas\\RELATORIO EPI EM ESTOQUE.png")); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -30, 810, 560));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TXT_voltaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_voltaActionPerformed
MENU  VOLTA_INICIO = new MENU();
         VOLTA_INICIO.setVisible(true);        // TODO add your handling code here:

    }//GEN-LAST:event_TXT_voltaActionPerformed

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
            java.util.logging.Logger.getLogger(RELATORIO_ESTOQUE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RELATORIO_ESTOQUE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RELATORIO_ESTOQUE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RELATORIO_ESTOQUE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RELATORIO_ESTOQUE().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton TXT_volta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabelaE;
    // End of variables declaration//GEN-END:variables

}