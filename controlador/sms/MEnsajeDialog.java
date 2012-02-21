/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MEnsajeDialog.java
 *
 * Created on 23/12/2011, 12:43:57 PM
 */
package controlador.sms;

import com.sun.awt.AWTUtilities;
import java.awt.Dimension;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 *
 * @author NELSON
 */
public class MEnsajeDialog extends javax.swing.JFrame {

    private Timer timer;
    private Timer timerClose;
    private JFrame yo;
    private int timeClose;
    private int X;
    private int Y;
    private PosicionPantalla posicion = PosicionPantalla.EZQUINA_INFERIOR_DERECHA;
    private TipoCerrado formaCerrado = TipoCerrado.TRASLUCIDA;

    /** Creates new form MEnsajeDialog */
    public MEnsajeDialog(int timeClose, TipoCerrado formaCerradoVent, int X, int Y) {
        initComponents();
        this.yo = this;
        this.formaCerrado = formaCerradoVent;
        this.timeClose = timeClose;
        this.X = X;
        this.Y = Y;
        this.posicion = PosicionPantalla.PERSONALIZADO;

        this.setLocation(X, Y);
        InicializarTimer();
    }

    public MEnsajeDialog(int timeClose, int X, int Y) {
        initComponents();
        this.yo = this;
        this.timeClose = timeClose;
        this.X = X;
        this.Y = Y;
        this.posicion = PosicionPantalla.PERSONALIZADO;

        this.setLocation(X, Y);
        InicializarTimer();
    }

    public MEnsajeDialog(int timeClose, TipoCerrado formaCerradoVent, PosicionPantalla posicion) {
        initComponents();
        this.yo = this;
        this.timeClose = timeClose;
        this.posicion = posicion;
        this.formaCerrado = formaCerradoVent;
        EstablecerLocalizacion();
        InicializarTimer();
    }

    public MEnsajeDialog(int timeClose, PosicionPantalla posicion) {
        initComponents();
        this.yo = this;
        this.timeClose = timeClose;
        this.posicion = posicion;
        EstablecerLocalizacion();
        InicializarTimer();
    }

    public MEnsajeDialog(int timeClose, TipoCerrado formaCerradoVent) {
        initComponents();
        this.yo = this;
        this.timeClose = timeClose;
        this.formaCerrado = formaCerradoVent;

        EstablecerLocalizacion();
        InicializarTimer();
    }

    public MEnsajeDialog(int timeClose) {
        initComponents();
        this.yo = this;
        this.timeClose = timeClose;

        EstablecerLocalizacion();
        InicializarTimer();
    }

    public TipoCerrado getFormaCerrado() {
        return formaCerrado;
    }

    public void setFormaCerrado(TipoCerrado formaCerrado) {
        this.formaCerrado = formaCerrado;
    }

    public int getPosX() {
        return this.X;
    }

    public void setPosX(int X) {
        this.X = X;
    }

    public int getPosY() {
        return this.Y;
    }

    public void setPosY(int Y) {
        this.Y = Y;
    }

    public PosicionPantalla getPosicion() {
        return posicion;
    }

    public void setPosicion(PosicionPantalla posicion) {
        this.posicion = posicion;
    }

    private void InicializarTimer() {
        timer = new Timer(1, new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                switch (formaCerrado) {
                    case TRASLUCIDA:
                        //System.out.println(AWTUtilities.getWindowOpacity(yo));
                        if (AWTUtilities.getWindowOpacity(yo) - 0.005f > 0.0f) {
                            AWTUtilities.setWindowOpacity(yo, AWTUtilities.getWindowOpacity(yo) - 0.005f);
                        } else {
                            timer.stop();
                            yo.dispose();
                        }
                        break;
                    case TRASLUCIDA_CIRCULO:
                        //System.out.println(AWTUtilities.getWindowOpacity(yo));
                        Shape shape = AWTUtilities.getWindowShape(yo);

                        if (yo.getWidth() - 10 > 0) {
                            yo.setBounds(yo.getX() + 5, yo.getY(), yo.getWidth() - 10, yo.getHeight());
                            if (AWTUtilities.getWindowOpacity(yo) - 0.005f > 0.0f) {
                                AWTUtilities.setWindowOpacity(yo, AWTUtilities.getWindowOpacity(yo) - 0.005f);
                            }
                        }

                        break;
                    case BARRIDO_ARRIBA:
                        if (yo.getHeight() - 10 > 0) {
                            yo.setBounds(yo.getX(), yo.getY(), yo.getWidth(), yo.getHeight() - 10);
                        } else {
                            timer.stop();
                            yo.dispose();
                        }
                        break;
                    case BARRIDO_ABAJO:
                        if (yo.getHeight() - 10 > 0) {
                            yo.setBounds(yo.getX(), yo.getY() + 10, yo.getWidth(), yo.getHeight() - 10);
                        } else {
                            timer.stop();
                            yo.dispose();
                        }
                        break;
                    case BARRIDO_DERECHA:
                        if (yo.getWidth() - 10 > 0) {
                            yo.setBounds(yo.getX() + 10, yo.getY(), yo.getWidth() - 10, yo.getHeight());
                        } else {
                            timer.stop();
                            yo.dispose();
                        }
                        break;
                    case BARRIDO_IZQUIERDA:
                        if (yo.getWidth() - 10 > 0) {
                            yo.setBounds(yo.getX(), yo.getY(), yo.getWidth() - 10, yo.getHeight());
                        } else {
                            timer.stop();
                            yo.dispose();
                        }
                        break;
                    case BARRIDO_TOPES_CENTRO:
                        if (yo.getHeight() - 10 > 0) {
                            yo.setBounds(yo.getX(), yo.getY() + 5, yo.getWidth(), yo.getHeight() - 10);
                        } else {
                            timer.stop();
                            yo.dispose();
                        }
                        break;
                    case BARRIDO_LADOS_CENTRO:
                        if (yo.getWidth() - 10 > 0) {
                            yo.setBounds(yo.getX() + 5, yo.getY(), yo.getWidth() - 10, yo.getHeight());
                        } else {
                            timer.stop();
                            yo.dispose();
                        }
                        break;
                }
            }
        });

        timerClose = new Timer(timeClose, new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                timer.start();
                timerClose.stop();
            }
        });
    }

    public void Mostrar() {
        setVisible(true);
        timerClose.start();
    }

    private void EstablecerLocalizacion() {
        Dimension tamañoVentana = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        switch (posicion) {
            case EZQUINA_INFERIOR_DERECHA:
                this.setLocation(tamañoVentana.width - this.getWidth(), tamañoVentana.height - this.getHeight());
                break;
            case EZQUINA_INFERIOR_IZQUIERDA:
                this.setLocation(0, tamañoVentana.height - this.getHeight());
                break;
            case EZQUINA_SUPERIOR_DERECHA:
                this.setLocation(0, 0);
                break;
            case EZQUINA_SUPERIOR_IZQUIERDA:
                this.setLocation(tamañoVentana.width - this.getWidth(), 0);
                break;
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jToggleButton1 = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setUndecorated(true);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                formMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                formMouseEntered(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setPreferredSize(new java.awt.Dimension(32, 32));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 78, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 87, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Modern No. 20", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 255));
        jLabel2.setText("Sutitulo");

        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jToggleButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cerrar.png"))); // NOI18N
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseEntered
        timerClose.stop();
    }//GEN-LAST:event_formMouseEntered

    private void formMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseExited
        timerClose.start();
    }//GEN-LAST:event_formMouseExited

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        timer.stop();
        timerClose.stop();
        this.dispose();
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MEnsajeDialog(5000).Mostrar();
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JToggleButton jToggleButton1;
    // End of variables declaration//GEN-END:variables

    public enum TipoCerrado {

        TRASLUCIDA,
        TRASLUCIDA_CIRCULO,
        BARRIDO_ARRIBA,
        BARRIDO_ABAJO,
        BARRIDO_DERECHA,
        BARRIDO_IZQUIERDA,
        BARRIDO_TOPES_CENTRO,
        BARRIDO_LADOS_CENTRO,}

    public enum PosicionPantalla {

        EZQUINA_SUPERIOR_DERECHA,
        EZQUINA_SUPERIOR_IZQUIERDA,
        EZQUINA_INFERIOR_DERECHA,
        EZQUINA_INFERIOR_IZQUIERDA,
        PERSONALIZADO
    }
}
