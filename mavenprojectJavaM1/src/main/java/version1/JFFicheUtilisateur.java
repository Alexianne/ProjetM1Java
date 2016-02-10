/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package version1;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author Alexandra
 */
public class JFFicheUtilisateur extends javax.swing.JFrame {
    private String nom;
    private String idrech;
    ClientConnect client;
    
    /**
     * Creates new form JFFicheUtilisateur
     */
    public JFFicheUtilisateur(String nom, Object c) {
        initComponents();
        this.nom=nom;
        this.client = (ClientConnect) c;
        String req = "GETINFO2 "+nom;
        System.out.println(req);
        try {
            String info = client.communiquer(req);
            String msg[] = info.split(" ");
            jnom.setText(msg[1]);
            jprenom.setText(msg[2]);
            jmail.setText(msg[3]);
            jtel.setText(msg[4]);
            jnais.setText(msg[5]);
            idrech = msg[6];
            String req2 = "GETINFOUSER "+idrech;
            String dipcompt = client.communiquer(req2);
            String msgg[] = dipcompt.split(" ");


            System.out.println(msgg[1]);
            System.out.println(msgg[2]);
            //System.out.println("err : "+msg[1]);
            DefaultListModel modeldip = new DefaultListModel();
            modeldip.addElement(msgg[1]+" "+msgg[2]);
            jLDip.setModel(modeldip);    
              
            DefaultListModel modelcom = new DefaultListModel();
            modelcom.addElement(msgg[3]+" "+msgg[4]+" "+msgg[5]);
            jLComp.setModel(modelcom);  

            
        } catch (IOException ex) {
            Logger.getLogger(JFApp.class.getName()).log(Level.SEVERE, null, ex);
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

        rech = new javax.swing.JLabel();
        jnom = new javax.swing.JLabel();
        jprenom = new javax.swing.JLabel();
        jmail = new javax.swing.JLabel();
        jtel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLComp = new javax.swing.JList<>();
        jcomp = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLDip = new javax.swing.JList<>();
        jdiplome = new javax.swing.JLabel();
        jnais = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        rech.setText("Utilisateur recherché");

        jnom.setText("Nom");

        jprenom.setText("Prénom");

        jmail.setText("Mail : ");

        jtel.setText("Téléphone : ");

        jScrollPane1.setViewportView(jLComp);

        jcomp.setText("Compétences : ");

        jScrollPane2.setViewportView(jLDip);

        jdiplome.setText("Diplômes : ");

        jnais.setText("Naissance :");

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(65, 65, 65)
                                .addComponent(rech))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jnom))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jprenom)
                                .addGap(37, 37, 37))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jcomp)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jdiplome)
                                .addGap(0, 133, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jmail)
                            .addComponent(jnais)
                            .addComponent(jtel))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(164, 164, 164)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rech)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jnom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jprenom)
                .addGap(18, 18, 18)
                .addComponent(jmail)
                .addGap(18, 18, 18)
                .addComponent(jtel)
                .addGap(16, 16, 16)
                .addComponent(jnais)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcomp)
                    .addComponent(jdiplome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JList<String> jLComp;
    private javax.swing.JList<String> jLDip;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel jcomp;
    private javax.swing.JLabel jdiplome;
    private javax.swing.JLabel jmail;
    private javax.swing.JLabel jnais;
    private javax.swing.JLabel jnom;
    private javax.swing.JLabel jprenom;
    private javax.swing.JLabel jtel;
    private javax.swing.JLabel rech;
    // End of variables declaration//GEN-END:variables
}
