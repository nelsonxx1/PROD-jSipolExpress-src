package vista.sms;

import controlador.sms.EnviarSMS;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import modelo.Dominios.EstatusSMS;
import modelo.HibernateUtil;
import modelo.entidades.personas.maestra.Persona;
import modelo.entidades.sms.SMS;
import modelo.entidades.sms.SMSMasa;
import org.hibernate.Hibernate;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.util.client.ClientUtils;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NewJFrame.java
 *
 * Created on 22/06/2011, 05:34:00 PM
 */
/**
 *
 * @author NELSON
 */
public class DialogEnviarMensajes extends javax.swing.JDialog {

    private int cantMen = 0;
    private Timer timer = null;
    private SMSMasa masa = null;
    private int enviados = 0;
    private int noEnviados = 0;
    private int pun = 0;
    public boolean cancelado = false;

    public DialogEnviarMensajes(SMSMasa masa) {
        super(MDIFrame.getInstance(), false);
        initComponents();
        this.masa = masa;

        this.cantMen = masa.getSms().size();
        jLabel2.setText(0 + "/" + cantMen);
        jProgressBar1.setMaximum(cantMen);
        ClientUtils.centerDialog(MDIFrame.getInstance(), this);
    }

    /** Creates new form NewJFrame */
    public void enviarMensajes() {
        timer = new Timer(100, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                pun++;
                String aux = "";
                for (int i = 0; i < pun; i++) {
                    aux += ".";
                }
                for (int i = 0; i < 4 - pun; i++) {
                    aux += " ";
                }
                if (pun == 4) {
                    pun = 0;
                    aux = "   ";
                }
                jLabel1.setText("Enviando Mensajes. Porfavor Espere" + aux);
            }
        });
        timer.start();

        setVisible(true);
        org.openswing.swing.util.client.ClientUtils.centerDialog(this, this);

        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();            

            int cont=0;
            for (SMS sms : masa.getSms()) {
                Transaction t = s.beginTransaction();               
                
                if (cancelado) {
                    break;
                }
                
                if (sms.getEstatus() != EstatusSMS.SIN_NUMERO && sms.getEstatus() != EstatusSMS.ENVIADO && sms.getEstatus() != EstatusSMS.MUY_LARGO) {
                    cont++;
//                    System.out.print(sms.getId()+" Antes - "+sms.getEstatus().name());
                    EstatusSMS estatus = EnviarSMS.enviarSMS(sms);                    
                    if (estatus != EstatusSMS.ENVIADO) {
                        noEnviados++;
                    } else {
                        enviados++;
                    }                                        
                    
                    sms.setEstatus(estatus);                                       
                    
                    if (sms.getPersona() != null) {
                        Persona p = sms.getPersona();
                        s.refresh(p);
                        Hibernate.initialize(p.getSmss());
                        p.getSmss().add(sms);
                        s.update(p);
//                        System.out.print(" Despues - "+sms.getEstatus().name()+" "+cont+" "+"\n");
                    } else {
                        Set<Persona> set = EnviarSMS.getPersonas(sms.getNumero());
                        if (set.size() > 0) {
                            for (Persona persona : set) {
                                Persona p = sms.getPersona();
                                s.refresh(p);
                                Hibernate.initialize(p.getSmss());
                                p.getSmss().add(sms);
                                s.update(p);
//                                System.out.print(" Despues - "+sms.getEstatus().name()+" "+cont+" "+"\n");
                            }
                        } else {
                            s.update(sms);
                           // System.out.print(" Despues - "+sms.getEstatus().name()+" "+cont+" "+"\n");
                        }
                    }
                } else {
                    noEnviados++;
                }
                jProgressBar1.setValue(jProgressBar1.getValue() + 1);
                jLabel2.setText(jProgressBar1.getValue() + "/" + cantMen);
                jLabel3.setText("Enviados: " + enviados + " - " + "NO Enviados: " + noEnviados);       
                t.commit();
            }
            jButton1.setEnabled(true);            
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            s.close();
            timer.stop();
            jLabel1.setText("Envio de Mensajes finalizado.");
        }
    }

    public void cancelar() {
        this.cancelado = true;
    }

    public DialogEnviarMensajes(int cantMens) {
        this.cantMen = cantMens;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Enviando Mensajes");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Enviando Mensajes. Porfavor Espere");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText(" ");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Enviandos: 0 - NO Enviados: 0");

        jButton1.setText("Finalizar");
        jButton1.setEnabled(false);
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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(172, 172, 172)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                .addGap(155, 155, 155))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        if ((noEnviados + enviados) != cantMen) {
            switch (JOptionPane.showConfirmDialog(this, "Si cierra la ventana los mensajes dejaran de enviarse.\n¿Desea Cerrar la Ventana?", "Cancelar envio de mensajes", JOptionPane.YES_NO_OPTION)) {
                case JOptionPane.YES_OPTION:
                    cancelar();
                    dispose();
                    break;
            }
        }
    }//GEN-LAST:event_formWindowClosing

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}
