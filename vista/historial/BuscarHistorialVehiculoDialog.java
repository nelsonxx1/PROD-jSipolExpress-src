package vista.historial;

import controlador.historial.HistorialVehiculoDetailController;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.HibernateUtil;
import modelo.entidades.vehiculos.maestra.Vehiculo;
import modelo.utilitario.HistorialVehiculo;
import org.hibernate.classic.Session;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.InternalFrame;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.util.java.Consts;

/**
 *
 * @author bc
 */
public class BuscarHistorialVehiculoDialog extends InternalFrame {

    private HistorialVehiculo vehiculo;
    private boolean ok;
    private boolean nuevo;

    public BuscarHistorialVehiculoDialog(Component owner) {
        initComponents();
        jButton1.addActionListener(new VehiculoDialogController());
        form1.setFormController(new VehiculoDialogController());
        form1.setMode(Consts.INSERT);
        this.getRootPane().setDefaultButton(jButton1);
        if (owner == null) {
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            setLocation(((int) d.getWidth() - this.getWidth()) / 2, ((int) d.getHeight() - this.getHeight()) / 2);
        } else {
            owner.getSize();
            setLocation((owner.getWidth() - this.getWidth()) / 2 + (int) owner.getLocationOnScreen().getX(), (owner.getHeight() - this.getHeight()) / 2 + (int) owner.getLocationOnScreen().getY());
        }
        pack();
        MDIFrame.add(this);
    }

    public HistorialVehiculo getVehiculo() {
        return vehiculo;
    }

    public boolean isOk() {
        return ok;
    }

    public boolean isNuevo() {
        return nuevo;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        form1 = new org.openswing.swing.form.client.Form();
        textControl1 = new org.openswing.swing.client.TextControl();
        labelControl2 = new org.openswing.swing.client.LabelControl();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Buscar Historial de Vehiculo");
        setResizable(false);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel2.png"))); // NOI18N
        jButton2.setPreferredSize(new java.awt.Dimension(33, 33));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check1.png"))); // NOI18N
        jButton1.setPreferredSize(new java.awt.Dimension(33, 33));

        form1.setVOClassName(HistorialVehiculo.class.getName());

        textControl1.setAttributeName("vehiculo.placa");
        textControl1.setLinkLabel(labelControl2);
        textControl1.setRequired(true);
        textControl1.setTrimText(true);
        textControl1.setUpperCase(true);

        labelControl2.setLabel("placa");
        labelControl2.setToolTipText("Numero de Placa del Vehiculo");

        javax.swing.GroupLayout form1Layout = new javax.swing.GroupLayout(form1);
        form1.setLayout(form1Layout);
        form1Layout.setHorizontalGroup(
            form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, form1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
                .addContainerGap())
        );
        form1Layout.setVerticalGroup(
            form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(form1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        form1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {labelControl2, textControl1});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(form1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 192, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(form1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
}//GEN-LAST:event_jButton2ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.form.client.Form form1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private org.openswing.swing.client.LabelControl labelControl2;
    private org.openswing.swing.client.TextControl textControl1;
    // End of variables declaration//GEN-END:variables

    class VehiculoDialogController extends FormController implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            vehiculo = (HistorialVehiculo) BuscarHistorialVehiculoDialog.this.form1.getVOModel().getValueObject();
            Session s = null;
            try {
                s = HibernateUtil.getSessionFactory().openSession();
                Long cant = (Long) s.createQuery("SELECT count(P) FROM " + Vehiculo.class.getName() + " as P WHERE P.placa=?").
                        setString(0, vehiculo.getVehiculo().getPlaca()).
                        list().get(0);
                if (cant == 0) {
                    vehiculo = (HistorialVehiculo) BuscarHistorialVehiculoDialog.this.form1.getVOModel().getValueObject();
                    JOptionPane.showMessageDialog(null, "Esta vehiculo no esta registrado");
                    form1.setMode(Consts.INSERT);
                } else if (cant == 1) {
                    ok = true;
                    nuevo = false;
                    //BuscarHistorialVehiculoDialog.this.dispose();
                    new HistorialVehiculoDetailController(HistorialVehiculoDetailFrame.class.getName(), null, new HistorialVehiculo(), null, vehiculo.getVehiculo().getPlaca());
                } else {
                    ok = false;
                    JOptionPane.showMessageDialog(null, "Existen " + cant + " Vehiculos con el mismo numero de placa.\nRevise la información.");
                }
            } catch (Exception ex) {
                ok = false;
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } finally {
                s.close();
            }
        }
    }
}
