/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto_etec.view;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import projeto_etec.dao.FuncionarioDAO;
import projeto_etec.modelo.Funcionario;

/**
 *
 * @author lab
 */
public class DFrmConsFun extends javax.swing.JDialog {

    /**
     * Creates new form DFrmConsFun
     */
    public DFrmConsFun(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblfun = new javax.swing.JTable();
        btnpes = new javax.swing.JButton();
        btncad = new javax.swing.JButton();
        btnalterar = new javax.swing.JButton();
        btnexcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe Print", 0, 18)); // NOI18N
        jLabel1.setText("Consulta de Funcionários");

        tblfun.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Funcionário", "Setor"
            }
        ));
        jScrollPane1.setViewportView(tblfun);

        btnpes.setFont(new java.awt.Font("Segoe Print", 0, 11)); // NOI18N
        btnpes.setText("Consultar");
        btnpes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnpesActionPerformed(evt);
            }
        });

        btncad.setFont(new java.awt.Font("Segoe Print", 0, 11)); // NOI18N
        btncad.setText("Cadastrar");
        btncad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncadActionPerformed(evt);
            }
        });

        btnalterar.setFont(new java.awt.Font("Segoe Print", 0, 11)); // NOI18N
        btnalterar.setText("Alterar");
        btnalterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnalterarActionPerformed(evt);
            }
        });

        btnexcluir.setFont(new java.awt.Font("Segoe Print", 0, 11)); // NOI18N
        btnexcluir.setText("Excluir");
        btnexcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(btncad)
                        .addGap(50, 50, 50)
                        .addComponent(btnalterar)
                        .addGap(46, 46, 46)
                        .addComponent(btnexcluir))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(143, 143, 143)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(224, 224, 224)
                        .addComponent(btnpes)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 44, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addGap(41, 41, 41)
                .addComponent(btnpes)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btncad)
                    .addComponent(btnalterar)
                    .addComponent(btnexcluir))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnpesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnpesActionPerformed
        //Botão Consultar
        FuncionarioDAO fd = new FuncionarioDAO();
        Funcionario f = new Funcionario();
        List<Funcionario> funcionarios = fd.getList();
        DefaultTableModel model = (DefaultTableModel) tblfun.getModel();
        model.setNumRows(0);
        for (Funcionario fl : funcionarios) {
            model.addRow(new Object[]{
                fl.getId_func(),
                fl.getNome_func(),
                fl.getId_setor_fk().getNome_setor()
            });
        }
    }//GEN-LAST:event_btnpesActionPerformed

    private void btncadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncadActionPerformed
        //Chama o DFmCadFun
        DFrmCadFun nomeVariavel = new DFrmCadFun();
        nomeVariavel.setVisible(true);
    }//GEN-LAST:event_btncadActionPerformed

    private void btnalterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnalterarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnalterarActionPerformed

    private void btnexcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexcluirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnexcluirActionPerformed

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
            java.util.logging.Logger.getLogger(DFrmConsFun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DFrmConsFun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DFrmConsFun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DFrmConsFun.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DFrmConsFun dialog = new DFrmConsFun(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnalterar;
    private javax.swing.JButton btncad;
    private javax.swing.JButton btnexcluir;
    private javax.swing.JButton btnpes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblfun;
    // End of variables declaration//GEN-END:variables
}
