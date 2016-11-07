/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario.Modificacao;

import classes.Clientes;
import erro.ErrorHandle;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JOptionPane;
import usuario.MainPage;
import utilitarios.LtpUtil;

/**
 *
 * @author jannsen
 */
public class AltCliente extends javax.swing.JFrame
{
    private static HashMap<String, String> estados;

    /**
     * Creates new form AltCliente
     */
    public AltCliente() {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Alterações de Clientes");
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

            },
            new String [] {

            }
        ));
        jTable1.setToolTipText("");
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Habilitar Alteração");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Excluir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Fechar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addGap(160, 160, 160)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
        new MainPage().setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        fillTable();
    }//GEN-LAST:event_formWindowOpened

    private void fillTable()
    {
        try
        {
            estados = new HashMap<>();
            ResultSet res = Clientes.listaClientes();
            LtpUtil.loadFormatJTable(jTable1, res, true);
            while(res.next())
            {
                 estados.put(res.getString(2), res.getString(1));
            }
        }
        catch (ErrorHandle | SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Falha na pesquisa", 0);
            jButton1.setEnabled(false);
            jButton2.setEnabled(false);
        }
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(jButton1.getText().equals("Habilitar Alteração"))
        {
            jTable1.setEnabled(true);
            jButton1.setText("Alterar");
        }
        else if(jButton1.getText().equals("Alterar"))
        {
            try
            {
                Clientes cli = new Clientes();
                cli.setCodCliente(Integer.parseInt((String) jTable1.getValueAt(jTable1.getSelectedRow(), 0)));
                cli.setNome((String) jTable1.getValueAt(jTable1.getSelectedRow(), 1));
                cli.setBairro((String) jTable1.getValueAt(jTable1.getSelectedRow(), 3));
                cli.setCep((String) jTable1.getValueAt(jTable1.getSelectedRow(), 6));
                cli.setCidade((String) jTable1.getValueAt(jTable1.getSelectedRow(), 4));
                cli.setEmail((String) jTable1.getValueAt(jTable1.getSelectedRow(), 8));
                cli.setEndereco((String) jTable1.getValueAt(jTable1.getSelectedRow(), 2));
                cli.setTelefone((String) jTable1.getValueAt(jTable1.getSelectedRow(), 7));
                cli.setUf(estados.get((String) jTable1.getValueAt(jTable1.getSelectedRow(), 5)));
                cli.Alterar();
                JOptionPane.showMessageDialog(null, "Alteração do cliente realizada com sucesso.", "Alterar cliente com sucesso", 3);
                fillTable();
            }
            catch (NumberFormatException | ErrorHandle e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Falha ao alterar cliente", 0);
            }
            finally
            {
                jTable1.setEnabled(true);
                jButton1.setText("Habilitar Alteração");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Alguma coisa errada com o botão", "Falha ao alterar vendedor", 0);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(jTable1.getSelectedRow() >= 0)
        {
            //String codigo = (jTable1.getModel().getValueAt(row, 0).toString());
            if(JOptionPane.showConfirmDialog(null, "Deseja confirmar a exclusao ?", "Excluir Cliente", 0) == 0)
            {
                try
                {
                    Clientes c = new Clientes();
                    c.setCodCliente(Integer.parseInt(jTable1.getModel().getValueAt(jTable1.getSelectedRow(), 0).toString()));
                    c.Apagar();
                    JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso !", "Excluido com sucesso", 3);
                    fillTable();
                }
                catch (ErrorHandle | NumberFormatException e)
                {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Falha ao excluir", 0);
                }
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.dispose();
        new MainPage().setVisible(true);
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(AltCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AltCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AltCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AltCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AltCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
