package vista.polizas.recibos;

import controlador.personas.PersonaLookupController;
import controlador.polizas.recibos.ReciboLookupController;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import modelo.HibernateUtil;
import modelo.entidades.polizas.recibos.maestra.Recibo;
import modelo.entidades.siniestros.maestra.Siniestro;
import org.hibernate.classic.Session;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.java.Consts;

/**
 *
 * @author bc
 */
public class BuscarReciboDialog extends javax.swing.JDialog {

    private Recibo recibo;
    private boolean ok;
    private boolean nuevo;

    public BuscarReciboDialog(Component owner) {
        super(MDIFrame.getInstance(), true);
        initComponents();

        saveButton1.setIcon(jButton1.getIcon());
        jButton1.setVisible(false);
        this.getRootPane().setDefaultButton(saveButton1);

        PersonaLookupController lookupCompania = new PersonaLookupController("SEG");
        lookupCompania.addLookup2ParentLink("recibo.poliza.compania");
        codLookupControl1.setLookupController(lookupCompania);

        ReciboLookupController reciboLookupController = new ReciboLookupController();
        reciboLookupController.addLookup2ParentLink("numero","recibo.numero");
        codLookupControl2.setLookupController(reciboLookupController);

        form1.setFormController(new PolizaDialogController());
        form1.setMode(Consts.INSERT);

        if (owner == null) {
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            setLocation(((int) d.getWidth() - this.getWidth()) / 2, ((int) d.getHeight() - this.getHeight()) / 2);
        } else {
            owner.getSize();
            setLocation((owner.getWidth() - this.getWidth()) / 2 + (int) owner.getLocationOnScreen().getX(), (owner.getHeight() - this.getHeight()) / 2 + (int) owner.getLocationOnScreen().getY());
        }
        pack();
    }

    public Recibo getRecibo() {
        return recibo;
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
        textControl2 = new org.openswing.swing.client.TextControl();
        codLookupControl1 = new org.openswing.swing.client.CodLookupControl();
        labelControl1 = new org.openswing.swing.client.LabelControl();
        labelControl3 = new org.openswing.swing.client.LabelControl();
        codLookupControl2 = new org.openswing.swing.client.CodLookupControl();
        saveButton1 = new org.openswing.swing.client.SaveButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Buscar Recibo");
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

        form1.setVOClassName(Siniestro.class.getName());
        form1.setSaveButton(saveButton1);

        textControl1.setAttributeName("recibo.poliza.numero");
        textControl1.setEnabledOnEdit(false);
        textControl1.setEnabledOnInsert(false);
        textControl1.setLinkLabel(labelControl2);
        textControl1.setRequired(true);
        textControl1.setTrimText(true);
        textControl1.setUpperCase(true);

        labelControl2.setLabel("numeroPoliza");

        textControl2.setAttributeName("recibo.poliza.compania.nombreLargo");
        textControl2.setEnabledOnEdit(false);
        textControl2.setEnabledOnInsert(false);

        codLookupControl1.setAttributeName("recibo.poliza.compania.rif.cedulaCompleta");
        codLookupControl1.setEnabledOnEdit(false);
        codLookupControl1.setEnabledOnInsert(false);
        codLookupControl1.setLinkLabel(labelControl1);
        codLookupControl1.setMaxCharacters(10);
        codLookupControl1.setRequired(true);

        labelControl1.setLabel("compania");

        labelControl3.setLabel("numeroRecibo");

        codLookupControl2.setAttributeName("recibo.numero");
        codLookupControl2.setLinkLabel(labelControl3);
        codLookupControl2.setLookupButtonVisible(false);
        codLookupControl2.setMaxCharacters(25);

        javax.swing.GroupLayout form1Layout = new javax.swing.GroupLayout(form1);
        form1.setLayout(form1Layout);
        form1Layout.setHorizontalGroup(
            form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(form1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(form1Layout.createSequentialGroup()
                        .addComponent(labelControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textControl2, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                            .addComponent(codLookupControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)))
                    .addGroup(form1Layout.createSequentialGroup()
                        .addComponent(labelControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
                    .addGroup(form1Layout.createSequentialGroup()
                        .addComponent(labelControl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(codLookupControl2, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)))
                .addContainerGap())
        );

        form1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {labelControl1, labelControl2, labelControl3});

        form1Layout.setVerticalGroup(
            form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(form1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codLookupControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codLookupControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        form1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {codLookupControl1, labelControl1, labelControl2, labelControl3, textControl1, textControl2});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 154, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(form1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(form1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
}//GEN-LAST:event_jButton2ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.client.CodLookupControl codLookupControl1;
    private org.openswing.swing.client.CodLookupControl codLookupControl2;
    private org.openswing.swing.form.client.Form form1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private org.openswing.swing.client.LabelControl labelControl1;
    private org.openswing.swing.client.LabelControl labelControl2;
    private org.openswing.swing.client.LabelControl labelControl3;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.client.TextControl textControl1;
    private org.openswing.swing.client.TextControl textControl2;
    // End of variables declaration//GEN-END:variables

    class PolizaDialogController extends FormController {

        @Override
        public Response loadData(Class valueObjectClass) {
            return new VOResponse(recibo);
        }

        @Override
        public Response insertRecord(ValueObject newPersistentObject) throws Exception {
            recibo = ((Siniestro) newPersistentObject).getRecibo();
            Session s = null;
            try {
                s = HibernateUtil.getSessionFactory().openSession();
                Long cant = (Long) s.createQuery("SELECT count(P) FROM "+Recibo.class.getName()+" as P WHERE P.numero=? AND P.poliza.numero=? AND P.poliza.compania.id=?").
                        setString(0, recibo.getNumero()).
                        setString(1, recibo.getPoliza().getNumero()).
                        setLong(2, recibo.getPoliza().getCompania().getId()).
                        list().get(0);
                if (cant == 0) {
                    return new ErrorResponse("No existe recibo con este identificador.");
                } else if (cant == 1) {
                    ok = true;
                    nuevo = false;
                    recibo = (Recibo) s.createQuery("SELECT P FROM "+Recibo.class.getName()+" as P WHERE P.numero=? AND P.poliza.numero=? AND P.poliza.compania.id=?").
                            setString(0, recibo.getNumero()).
                            setString(1, recibo.getPoliza().getNumero()).
                            setLong(2, recibo.getPoliza().getCompania().getId()).
                            uniqueResult();
                    BuscarReciboDialog.this.dispose();
                    return new VOResponse(newPersistentObject);
                } else {
                    ok = false;
                    return new ErrorResponse("Existen " + cant + " recibos con el mismo identificador.\nRevise la información.");
                }
            } catch (Exception ex) {
                ok = false;
                ex.printStackTrace();
                return new ErrorResponse(ex.getMessage());
            } finally {
                s.close();
            }
        }
    }
}
