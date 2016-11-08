/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario.Pesquisa;

import classes.Vendedores;
import erro.ErrorHandle;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import usuario.MainPage;
import utilitarios.LtpUtil;

/**
 *
 * @author jannsen
 */
public class PesqVendedor extends javax.swing.JFrame {

    private final Integer opcao;

    /**
     * Creates new form PesqVendedor
     * @param opcao
     */
    public PesqVendedor(Integer opcao) {
        initComponents();
        this.opcao = opcao;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Pesquisa de Vendedores");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        jLabel1.setText("Pesquisa de Vendedor");

        jLabel2.setText("jLabel2");

        jButton1.setText("Pesquisar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        if (opcao == 1)
        {
            // Pesquisa pelo cod
            jLabel2.setText("Informe o código do vendedor:");
        }
        else if(opcao == 2)
        {
            // Pesquisa pelo nome
            jLabel2.setText("Informe o nome do vendedor:");
        }
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.dispose();
        new MainPage().setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try
        {
            if(jTextField1.getText().length() < 1)
            {
                throw new ErrorHandle("Preencher o campo para realizar a pesquisa");
            }
            Vendedores ven = new Vendedores();
            if (opcao == 1)
            {
                // Pesquisa pelo cod
                ven.setCodVendedor(Integer.parseInt(jTextField1.getText()));
                ResultSet pesq = ven.PesquisaPeloCod();
                LtpUtil.loadFormatJTable(jTable1, pesq, true);
            }
            else if(opcao == 2)
            {
                // Pesquisa pelo nome
                ven.setNomeVendedor(jTextField1.getText());
                ResultSet pesq = ven.PesquisaPeloNome();
                LtpUtil.loadFormatJTable(jTable1, pesq, true);
            }
        }
        catch (NumberFormatException | ErrorHandle | SQLException | IOException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Falha na pesquisa", 0);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        try
        {
            if(evt.getKeyCode() == KeyEvent.VK_ENTER && jTextField1.getText().length() < 1)
            {
                throw new ErrorHandle("Preencher o campo para realizar a pesquisa");
            }
            else if(evt.getKeyCode() == KeyEvent.VK_ENTER && jTextField1.getText().length() > 1)
            {
                try
                {
                    if(jTextField1.getText().length() < 1)
                    {
                        throw new ErrorHandle("Preencher o campo para realizar a pesquisa");
                    }
                    Vendedores ven = new Vendedores();
                    if (opcao == 1)
                    {
                        // Pesquisa pelo cod
                        ven.setCodVendedor(Integer.parseInt(jTextField1.getText()));
                        ResultSet pesq = ven.PesquisaPeloCod();
                        LtpUtil.loadFormatJTable(jTable1, pesq, true);
                    }
                    else if(opcao == 2)
                    {
                        // Pesquisa pelo nome
                        ven.setNomeVendedor(jTextField1.getText());
                        ResultSet pesq = ven.PesquisaPeloNome();
                        LtpUtil.loadFormatJTable(jTable1, pesq, true);
                    }
                }
                catch (NumberFormatException | ErrorHandle | SQLException | IOException e)
                {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Falha na pesquisa", 0);
                }
            }
        }
        catch (ErrorHandle e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Falha na pesquisa", 0);
        }
    }//GEN-LAST:event_jTextField1KeyPressed

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
            java.util.logging.Logger.getLogger(PesqVendedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PesqVendedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PesqVendedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PesqVendedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PesqVendedor(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
