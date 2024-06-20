
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;




/**
 *
 * @author vitor
 */
public class ENTRADA_pei_estoque extends javax.swing.JFrame {
 private HashMap<String, Integer> primeiroIdPorNomeEpi = new HashMap<>();

     private final String URL = "jdbc:mysql://localhost:/sistema";
        
        public Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }private final String USER = "root";
        private final String PASSWORD = "";
        
public ENTRADA_pei_estoque() {
        initComponents();
         
TXT_tipoEPI.setBackground (new java.awt.Color(0,0,0,1));
txt_quantidade.setBackground (new java.awt.Color(0,0,0,1)); 
txt_ID_EPI .setBackground (new java.awt.Color(0,0,0,1));
txt_Fonercedor .setBackground (new java.awt.Color(0,0,0,1));
nome_epi .setBackground (new java.awt.Color(0,0,0,1));

VOLTA_INICIO .setBackground (new java.awt.Color(0,0,0,0)); 
btn_MN_cadastro_F .setBackground (new java.awt.Color(0,0,0,0)); 
btn_mostalistaestoq .setBackground (new java.awt.Color(0,0,0,0)); 
salva2 .setBackground (new java.awt.Color(0,0,0,0)); 
jButton1  .setBackground (new java.awt.Color(0,0,0,0));

cb_estoque.setBackground (new java.awt.Color(0,0,0,0)); 
cb_relatorios .setBackground (new java.awt.Color(0,0,0,0));  
listas .setBackground (new java.awt.Color(0,0,0,0)); 

jFormatted_data_entrada .setBackground (new java.awt.Color(0,0,0,1));
jFormatted_validade .setBackground (new java.awt.Color(0,0,0,1));
        
    try {
            // Máscara para a data de entrada e validade
            MaskFormatter maskdtentrada = new MaskFormatter("##/##/####");
            maskdtentrada.setPlaceholderCharacter('_');
            maskdtentrada.install((JFormattedTextField) jFormatted_data_entrada);

            MaskFormatter maskDataValidade = new MaskFormatter("##/##/####");
            maskDataValidade.setPlaceholderCharacter('_');
            maskDataValidade.install((JFormattedTextField) jFormatted_validade);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        // Adiciona um DocumentListener ao campo de texto do nome do EPI
        nome_epi.getDocument().addDocumentListener(new DocumentListener() {
            
            public void insertUpdate(DocumentEvent e) {
                updateEpiId();
            }

            
            public void removeUpdate(DocumentEvent e) {
                updateEpiId();
            }

            
            public void changedUpdate(DocumentEvent e) {
                updateEpiId();
            }

            private void updateEpiId() {
                String nomeEpi = nome_epi.getText();
                if (!nomeEpi.isEmpty()) {
                    try {
                        Connection con = getConnection();
                        String sqlSelect = "SELECT id_epi FROM EntradaEPI WHERE nome_epi = ? ORDER BY id_epi ASC LIMIT 1";
                        PreparedStatement stmt = con.prepareStatement(sqlSelect);
                        stmt.setString(1, nomeEpi);
                        ResultSet rs = stmt.executeQuery();
                        if (rs.next()) {
                            int idEPI = rs.getInt("id_epi");
                            txt_ID_EPI.setText(String.valueOf(idEPI));
                        } else {
                            txt_ID_EPI.setText(""); // Limpa o campo se o EPI não for encontrado
                        }
                        rs.close();
                        stmt.close();
                        con.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(ENTRADA_pei_estoque.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
}
public static void gerarRelatorio2() {
   try {
        ENTRADA_pei_estoque entradaPeiEstoque = new ENTRADA_pei_estoque();
        Connection con = entradaPeiEstoque.getConnection();

        String sqlSelect = "SELECT * FROM EntradaEPI";
        PreparedStatement stmt = con.prepareStatement(sqlSelect);
        ResultSet rs = stmt.executeQuery();

        StringBuilder relatorio = new StringBuilder();
        relatorio.append("Relatório de Dados Estoque:\n\n");

        while (rs.next()) {
            relatorio.append("ID: ").append(rs.getInt("id_epi")).append("\n");
            relatorio.append("Nome EPI: ").append(rs.getString("nome_epi")).append("\n");
            relatorio.append("Tipo EPI: ").append(rs.getString("tipo_epi")).append("\n");
            relatorio.append("Quantidade: ").append(rs.getInt("quantidade")).append("\n");
            relatorio.append("Fornecedor: ").append(rs.getString("fornecedor")).append("\n");
            relatorio.append("Data de Entrada: ").append(rs.getDate("dt_entrada")).append("\n");
            relatorio.append("Data de Validade: ").append(rs.getDate("validade")).append("\n\n");
            relatorio.append("___________________\n\n"); // Adiciona separador entre os blocos
        }

        con.close(); // Fechar a conexão com o banco de dados

        // Salvar o relatório no desktop do usuário
        String desktopPath = System.getProperty("user.home") + "/Desktop/relatorio2.txt";
        FileWriter writer = new FileWriter(desktopPath);
        writer.write(relatorio.toString());
        writer.close();

        JOptionPane.showMessageDialog(null, "Relatório  gerado com sucesso no desktop!");

    } catch (SQLException | IOException e) {
        JOptionPane.showMessageDialog(null, "Erro ao gerar relatório : " + e.getMessage());
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
        btn_mostalistaestoq = new javax.swing.JButton();
        VOLTA_INICIO = new javax.swing.JButton();
        btn_MN_cadastro_F = new javax.swing.JButton();
        cb_estoque = new javax.swing.JComboBox<>();
        cb_relatorios = new javax.swing.JComboBox<>();
        listas = new javax.swing.JComboBox<>();
        TXT_tipoEPI = new javax.swing.JTextField();
        txt_quantidade = new javax.swing.JTextField();
        txt_ID_EPI = new javax.swing.JTextField();
        txt_Fonercedor = new javax.swing.JTextField();
        jFormatted_validade = new javax.swing.JFormattedTextField();
        jFormatted_data_entrada = new javax.swing.JFormattedTextField();
        salva2 = new javax.swing.JButton();
        nome_epi = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        salvar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_mostalistaestoq.setBorder(null);
        btn_mostalistaestoq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_mostalistaestoqActionPerformed(evt);
            }
        });
        jPanel1.add(btn_mostalistaestoq, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 460, 180, 30));

        VOLTA_INICIO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VOLTA_INICIOActionPerformed(evt);
            }
        });
        jPanel1.add(VOLTA_INICIO, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 80, 70));

        btn_MN_cadastro_F.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_MN_cadastro_FActionPerformed(evt);
            }
        });
        jPanel1.add(btn_MN_cadastro_F, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 80, 80));

        cb_estoque.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { ".", "Visão Geral", "Entrada de epis", "Epi para funcionarios", " " }));
        cb_estoque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_estoqueActionPerformed(evt);
            }
        });
        jPanel1.add(cb_estoque, new org.netbeans.lib.awtextra.AbsoluteConstraints(-90, 180, 170, 90));

        cb_relatorios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { ".", "Relatorio do Estoque", "Relatorio de  Entrega" }));
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
        jPanel1.add(cb_relatorios, new org.netbeans.lib.awtextra.AbsoluteConstraints(-70, 290, 150, 80));

        listas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { ".", "EPI em Estoque", "Funcionarios  Cadastrados", "EPI Entregue", " " }));
        listas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listasActionPerformed(evt);
            }
        });
        jPanel1.add(listas, new org.netbeans.lib.awtextra.AbsoluteConstraints(-90, 390, -1, 90));

        TXT_tipoEPI.setBorder(null);
        TXT_tipoEPI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TXT_tipoEPIActionPerformed(evt);
            }
        });
        jPanel1.add(TXT_tipoEPI, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 160, 190, 30));

        txt_quantidade.setBorder(null);
        txt_quantidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_quantidadeActionPerformed(evt);
            }
        });
        jPanel1.add(txt_quantidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 300, 180, 30));

        txt_ID_EPI.setBorder(null);
        txt_ID_EPI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ID_EPIActionPerformed(evt);
            }
        });
        jPanel1.add(txt_ID_EPI, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 160, 180, 30));

        txt_Fonercedor.setBorder(null);
        txt_Fonercedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_FonercedorActionPerformed(evt);
            }
        });
        jPanel1.add(txt_Fonercedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, 180, 30));

        jFormatted_validade.setBorder(null);
        jFormatted_validade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormatted_validadeActionPerformed(evt);
            }
        });
        jPanel1.add(jFormatted_validade, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 390, 190, 30));

        jFormatted_data_entrada.setBorder(null);
        jFormatted_data_entrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormatted_data_entradaActionPerformed(evt);
            }
        });
        jPanel1.add(jFormatted_data_entrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 300, 190, 30));

        salva2.setBorder(null);
        salva2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salva2ActionPerformed(evt);
            }
        });
        jPanel1.add(salva2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 460, 150, 30));

        nome_epi.setBorder(null);
        nome_epi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nome_epiActionPerformed(evt);
            }
        });
        jPanel1.add(nome_epi, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 160, 180, 30));

        jButton1.setBorder(null);
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 460, 180, 30));

        salvar.setIcon(new javax.swing.ImageIcon("C:\\Users\\vitor\\Desktop\\EPI_gestor\\EPI_Gestor\\EPI_Gestor\\src\\main\\java\\com\\telas\\epi_gestor\\telas\\ENTRADA DE EPIS NO ESTOQUE.png")); // NOI18N
        salvar.setText("jLabel1");
        jPanel1.add(salvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 510));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_mostalistaestoqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_mostalistaestoqActionPerformed
           LISTA_EPI_EMESTOQUE listaemEStoque = new LISTA_EPI_EMESTOQUE();
        listaemEStoque.setVisible(true);
        listaemEStoque.preencherTabela2();
    }//GEN-LAST:event_btn_mostalistaestoqActionPerformed

    private void VOLTA_INICIOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VOLTA_INICIOActionPerformed
        ENTRADA_pei_estoque.this.dispose();
        MENU  VOLTA_INICIO = new MENU();
        VOLTA_INICIO.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_VOLTA_INICIOActionPerformed

    private void btn_MN_cadastro_FActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_MN_cadastro_FActionPerformed
        // TODO add your handling code here:
        ENTRADA_pei_estoque.this.dispose();
        CADAStro_FUNCIONARIOS btn_MN_cadastro_F = new CADAStro_FUNCIONARIOS();
        btn_MN_cadastro_F.setVisible(true);
    }//GEN-LAST:event_btn_MN_cadastro_FActionPerformed

    private void cb_estoqueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_estoqueActionPerformed
        String selectedOption = (String) cb_estoque.getSelectedItem();

       if ("Visão Geral".equals(selectedOption)) {
            ENTRADA_pei_estoque.this.dispose();
            VISAO_GERAL_ESTOQUE VisaoGeralEstoque = new VISAO_GERAL_ESTOQUE();
            VisaoGeralEstoque.setVisible(true);
        } else if ("Epi para funcionarios".equals(selectedOption)) {
            ENTRADA_pei_estoque.this.dispose();
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
            ENTRADA_pei_estoque.this.dispose();
            RELATORIO_ESTOQUE relatorioEstoque = new RELATORIO_ESTOQUE();
            relatorioEstoque.setVisible(true);
        } else if ("Relatorio de  Entrega".equals(selectedOption)) {
            ENTRADA_pei_estoque.this.dispose();
            RELATORIO_ENTREGA_FUNCIONARIO EntregaEpiEstoque = new RELATORIO_ENTREGA_FUNCIONARIO();
            EntregaEpiEstoque.setVisible(true);
        }
    }//GEN-LAST:event_cb_relatoriosActionPerformed

    private void listasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listasActionPerformed
        // TODO add your handling code here:
        String selectedOption = (String)listas.getSelectedItem();

        if ("EPI em Estoque".equals(selectedOption)) {
            ENTRADA_pei_estoque.this.dispose();
            LISTA_EPI_EMESTOQUE listaEstoque = new LISTA_EPI_EMESTOQUE();
            listaEstoque.setVisible(true);
        } else if ("Funcionarios  Cadastrados".equals(selectedOption)) {
            ENTRADA_pei_estoque.this.dispose();
            LISTA_FUNCIONARIOS lisEpiEntrgue = new LISTA_FUNCIONARIOS();
            lisEpiEntrgue.setVisible(true);
        } else if ("EPI Entregue".equals(selectedOption)) {
            ENTRADA_pei_estoque.this.dispose();
            EPI_ENTREGUE lisEpiEntrgue = new EPI_ENTREGUE();
            lisEpiEntrgue.setVisible(true);
        }
    }//GEN-LAST:event_listasActionPerformed

    private void TXT_tipoEPIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TXT_tipoEPIActionPerformed
     
    }//GEN-LAST:event_TXT_tipoEPIActionPerformed

    private void txt_quantidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_quantidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_quantidadeActionPerformed

    private void txt_ID_EPIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ID_EPIActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_ID_EPIActionPerformed

    private void txt_FonercedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_FonercedorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_FonercedorActionPerformed

    private void jFormatted_data_entradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormatted_data_entradaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormatted_data_entradaActionPerformed

    private void jFormatted_validadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormatted_validadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormatted_validadeActionPerformed

    private void salva2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salva2ActionPerformed
    
                                          
       String nomeEpi = nome_epi.getText();
        String tipoEPI = TXT_tipoEPI.getText();
        String quantidade = txt_quantidade.getText();
        String fornecedor = txt_Fonercedor.getText();
        String idEPIString = txt_ID_EPI.getText().trim(); // Captura o ID como string

        // Validação básica de campos vazios
        if (nomeEpi.isEmpty() || tipoEPI.isEmpty() || quantidade.isEmpty() || fornecedor.isEmpty() || idEPIString.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int idEPI;
        try {
            // Converte o ID para inteiro
            idEPI = Integer.parseInt(idEPIString);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "O ID do EPI deve ser um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Date dt_entrada = null;
        Date validade = null;
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

        try {
            dt_entrada = formatoData.parse(jFormatted_data_entrada.getText());
            validade = formatoData.parse(jFormatted_validade.getText());
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Por favor, insira datas válidas no formato dd/MM/yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int quantidadeInt = Integer.parseInt(quantidade);
            if (quantidadeInt <= 0) {
                JOptionPane.showMessageDialog(this, "A quantidade deve ser um número maior que zero.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "A quantidade deve ser um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (validade.before(dt_entrada)) {
            JOptionPane.showMessageDialog(this, "A data de validade deve ser posterior à data de entrada.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Insere o novo EPI
            Connection con = getConnection();

            String sqlInsert = "INSERT INTO EntradaEPI (id_epi, nome_epi, validade, tipo_epi, quantidade, fornecedor, dt_entrada) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = con.prepareStatement(sqlInsert);
            stmt.setInt(1, idEPI);
            stmt.setString(2, nomeEpi);
            stmt.setDate(3, new java.sql.Date(validade.getTime()));
            stmt.setString(4, tipoEPI);
            stmt.setInt(5, Integer.parseInt(quantidade));
            stmt.setString(6, fornecedor);
            stmt.setDate(7, new java.sql.Date(dt_entrada.getTime()));

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "EPI cadastrado com sucesso.");
                limparCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar EPI.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

            con.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao conectar ao banco de dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparCampos() {
        TXT_tipoEPI.setText("");
        txt_quantidade.setText("");
        txt_ID_EPI.setText("");
        txt_Fonercedor.setText("");
        jFormatted_data_entrada.setValue(null);
        jFormatted_validade.setValue(null);
    

    }//GEN-LAST:event_salva2ActionPerformed



    private void nome_epiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nome_epiActionPerformed
        // TODO add
    }//GEN-LAST:event_nome_epiActionPerformed

    
    
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
            java.util.logging.Logger.getLogger(ENTRADA_pei_estoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ENTRADA_pei_estoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ENTRADA_pei_estoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ENTRADA_pei_estoque.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ENTRADA_pei_estoque().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TXT_tipoEPI;
    private javax.swing.JButton VOLTA_INICIO;
    private javax.swing.JButton btn_MN_cadastro_F;
    private javax.swing.JButton btn_mostalistaestoq;
    private javax.swing.JComboBox<String> cb_estoque;
    private javax.swing.JComboBox<String> cb_relatorios;
    private javax.swing.JButton jButton1;
    private javax.swing.JFormattedTextField jFormatted_data_entrada;
    private javax.swing.JFormattedTextField jFormatted_validade;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> listas;
    private javax.swing.JTextField nome_epi;
    private javax.swing.JButton salva2;
    private javax.swing.JLabel salvar;
    private javax.swing.JTextField txt_Fonercedor;
    private javax.swing.JTextField txt_ID_EPI;
    private javax.swing.JTextField txt_quantidade;
    // End of variables declaration//GEN-END:variables

    
}
