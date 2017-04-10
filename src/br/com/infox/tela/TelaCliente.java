
package br.com.infox.tela;
import java.sql.*;
import br.com.infox.dal.ModuloConexao;
import javax.swing.JOptionPane;
//a linh abaixo importa recursos da biblioteca rx2ml.jar
import net.proteanit.sql.DbUtils;
/**
 * @author Junio
 */
public class TelaCliente extends javax.swing.JInternalFrame {
Connection conexao=null;
PreparedStatement pst=null;
ResultSet rs=null;

    public TelaCliente() {
        initComponents();
        conexao=ModuloConexao.conector();
    }
    
    //------------------------------------------------------------------------------------//
    //aqui vamos adicionar algum elemento
    //metodo para adicionar usuarios
     private void adicionar() {
         String sql="insert into tbclientes(nomecli,endcli,fonecli,emailcli) values(?,?,?,?)";
         
         try {
             pst=conexao.prepareStatement(sql);
             
             pst.setString(1,txtCliNome.getText());
             pst.setString(2,txtCliEndereco.getText());
             pst.setString(3,txtCliFone.getText());
             pst.setString(4,txtCliEmail.getText());
             
             
             //validação dos campos obrigatórios
             if ((txtCliNome.getText().isEmpty())||(txtCliFone.getText().isEmpty())) {
             JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios"); 
             } else { 
             //a linha abaixo atualizaa tabela usuario com os dados do
             //formulario
             //a extrutura abaixo é usada para confirmar a execusão
             int adicionado = pst.executeUpdate();
              //A linha abaixo serve de apoio ao intendimento
             //System.out.println(adicionado);
            if(adicionado >0){
                 JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso");
                 txtCliNome.setText(null);
                 txtCliEndereco.setText(null);
                 txtCliFone.setText(null);
                 txtCliEmail.setText(null);
                 
                
             }
            }
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null,e);
        }         
     }
//----------------------------------------------------------------------------------------
// Aqui é um método para pesquisar com o filtro de forma simples
     private void pesquisar_cliente(){
         String sql="select * from tbclientes where nomecli like ?";
         try {
             pst=conexao.prepareStatement(sql);
             //passando o conteudo para a caixa de pesquisa
             //atenção ao "%" continuação da String sql
             pst.setString(1,txtCliPesquisar.getText() + "%");
             rs=pst.executeQuery();
             //A linha abaixo usa a biblioteca rx2ml.jar para 
             //preencher a tabela
             tblClientes.setModel(DbUtils.resultSetToTableModel(rs));
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null,e);
         }
     }
     
 //--------------------------------------------------------------------------------
     //Método para setar os campos do formulário com o conteudo da tabela
     public void setar_campos(){
        
         int setar = tblClientes.getSelectedRow();
          txtCliId.setText(tblClientes.getModel().getValueAt(setar, 0).toString());
         txtCliNome.setText(tblClientes.getModel().getValueAt(setar, 1).toString());
         txtCliEndereco.setText(tblClientes.getModel().getValueAt(setar, 2).toString());
         txtCliFone.setText(tblClientes.getModel().getValueAt(setar, 3).toString());
         txtCliEmail.setText(tblClientes.getModel().getValueAt(setar, 4).toString());

//A linha abaixo desabilita o botao adicionar
btnAdicionar.setEnabled(false);
     }
     //---------------------------------------------------------------------------------
     //Método fazer alteração dos Clientes
     
      // Update
//criando o metodo para auterar dados do usuario
     private void alterar(){
        String sql = "update tbclientes set nomecli=?,endcli=?,fonecli=?,emailcli=? where idcli=?";
        try {
            pst=conexao.prepareStatement(sql);
            pst.setString(1,txtCliNome.getText());
            pst.setString(2,txtCliEndereco.getText());
            pst.setString(3,txtCliFone.getText());
            pst.setString(4,txtCliEmail.getText());
             pst.setString(5,txtCliId.getText());
                        
            if ((txtCliNome.getText().isEmpty())||(txtCliFone.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios"); 
             } else { 
             //a linha abaixo atualiza tabela usuario com os dados do formulario             
             //a extrutura abaixo é usada para confirmar a alteração de um crud
             int adicionado = pst.executeUpdate();
              //A linha abaixo serve de apoio ao intendimento
             //System.out.println(adicionado);
            if(adicionado>0){
                 JOptionPane.showMessageDialog(null, "Dados do cliente alterado com sucesso");
                 txtCliNome.setText(null);
                 txtCliEndereco.setText(null);
                 txtCliFone.setText(null);
                 txtCliEmail.setText(null);
                 btnAdicionar.setEnabled(true);
                
             }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }
 //--------------------------------------------------------------------------
 // O método à seguir Remove um Cliente ----------------
     private void remover(){
         //a estrutura abaixo confirma a remoção
         int confirma=JOptionPane.showConfirmDialog(null,"Tem certeza que deseja remover este Cliente","Atenção",JOptionPane.YES_NO_OPTION);
         if(confirma==JOptionPane.YES_OPTION){
             String sql="delete from tbclientes where idcli=?";
             try {
                 pst=conexao.prepareStatement(sql);
                 pst.setString(1,txtCliId.getText());
                 int apagado = pst.executeUpdate();
                 if(apagado>0){
                     JOptionPane.showMessageDialog(null,"Cliente removido com sucesso");
                     
                     txtCliNome.setText(null);
                     txtCliEndereco.setText(null);
                     txtCliFone.setText(null);
                     txtCliEmail.setText(null);          
                     btnAdicionar.setEnabled(true);
                 }
             } catch (Exception e) {
                 JOptionPane.showMessageDialog(null,e);
             }
         }
     }
 ///--------------------------------------------------------------------------------------
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        txtCliPesquisar = new javax.swing.JTextField();
        txtCliEndereco = new javax.swing.JTextField();
        txtCliFone = new javax.swing.JTextField();
        txtCliEmail = new javax.swing.JTextField();
        lblCliNome = new javax.swing.JLabel();
        lblCliEndereco = new javax.swing.JLabel();
        lblCliFone = new javax.swing.JLabel();
        lblCliEmail = new javax.swing.JLabel();
        lblCliObrigatorio = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnAdicionar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnRemover = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtCliId = new javax.swing.JTextField();
        txtCliNome = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Clientes");
        setPreferredSize(new java.awt.Dimension(640, 480));
        try {
            setSelected(true);
        } catch (java.beans.PropertyVetoException e1) {
            e1.printStackTrace();
        }
        setVisible(true);

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblClientes);

        txtCliPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliPesquisarKeyReleased(evt);
            }
        });

        txtCliEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliEnderecoActionPerformed(evt);
            }
        });

        txtCliFone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliFoneActionPerformed(evt);
            }
        });

        txtCliEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliEmailActionPerformed(evt);
            }
        });

        lblCliNome.setText("*Nome:");

        lblCliEndereco.setText("Endereço:");

        lblCliFone.setText("*Telefone:");

        lblCliEmail.setText("Email:");

        lblCliObrigatorio.setText("*Campo Obrigatório");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/pesquisar.png"))); // NOI18N

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/create.png"))); // NOI18N
        btnAdicionar.setToolTipText("Adicionar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });

        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/update.png"))); // NOI18N
        btnAlterar.setToolTipText("Atualizar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/delete.png"))); // NOI18N
        btnRemover.setToolTipText("Deletar");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });

        jLabel1.setText("Id Cliente:");

        txtCliId.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtCliPesquisar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addGap(26, 26, 26)
                        .addComponent(lblCliObrigatorio)
                        .addGap(28, 28, 28))))
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(btnRemover, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(lblCliEmail)
                        .addGap(18, 18, 18)
                        .addComponent(txtCliEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblCliNome)
                                .addComponent(lblCliEndereco))
                            .addComponent(lblCliFone)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCliEndereco)
                                    .addComponent(txtCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtCliFone, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCliPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCliObrigatorio)))
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCliEndereco)
                    .addComponent(txtCliEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCliFone, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtCliFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCliEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCliEmail))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(btnAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnRemover, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(205, 205, 205))
        );

        setBounds(0, 0, 640, 480);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCliFoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliFoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliFoneActionPerformed

    private void txtCliEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliEmailActionPerformed

    private void txtCliEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliEnderecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliEnderecoActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        // Chamando o método Remover um Cliente:
        remover();
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        // Métodos para chamar clientes:
        adicionar();
    }//GEN-LAST:event_btnAdicionarActionPerformed
//O evento abaixo é do tipo "enquanto for digitado"
    private void txtCliPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliPesquisarKeyReleased
        //Chamar o metodo pesquisar cliente:
        pesquisar_cliente();
    }//GEN-LAST:event_txtCliPesquisarKeyReleased
//Evento que serar usado quando clicando com o botao esquerdo do mouser
    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        // Chamando o método para setar o campo:
        setar_campos();
    }//GEN-LAST:event_tblClientesMouseClicked
//chamando o método para alterar clientes
    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        // Chamando o metodo alterar:
        alterar();
    }//GEN-LAST:event_btnAlterarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionar;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnRemover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblCliEmail;
    private javax.swing.JLabel lblCliEndereco;
    private javax.swing.JLabel lblCliFone;
    private javax.swing.JLabel lblCliNome;
    private javax.swing.JLabel lblCliObrigatorio;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtCliEmail;
    private javax.swing.JTextField txtCliEndereco;
    private javax.swing.JTextField txtCliFone;
    private javax.swing.JTextField txtCliId;
    private javax.swing.JTextField txtCliNome;
    private javax.swing.JTextField txtCliPesquisar;
    // End of variables declaration//GEN-END:variables
}
