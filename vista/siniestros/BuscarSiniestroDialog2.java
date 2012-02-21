package vista.siniestros;

import controlador.personas.PersonaLookupController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openswing.swing.mdi.client.InternalFrame;
import controlador.siniestros.SiniestroDetailController;
import controlador.siniestros.SiniestroGridControllerWhitSQL;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Dominios;
import modelo.Dominios.EstadoSiniestro;
import modelo.HibernateUtil;
import modelo.entidades.personas.maestra.Persona;
import modelo.entidades.personas.maestra.Rif;
import modelo.entidades.polizas.maestra.Poliza;
import modelo.entidades.polizas.recibos.maestra.Recibo;
import modelo.entidades.siniestros.maestra.Siniestro;
import modelo.entidades.vehiculos.maestra.Vehiculo;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.java.Consts;

/**
 *
 * @author orlandobcrra
 */
public class BuscarSiniestroDialog2 extends InternalFrame {

    private Siniestro siniestro;
    private VOListResponse siniestros;

    public BuscarSiniestroDialog2(Component owner) {
        initComponents();
        //initComponents();



        this.getRootPane().setDefaultButton(jButton1);

//        PersonaLookupControllerPorNombre lookupCompania = new PersonaLookupControllerPorNombre("SEG");
//        lookupCompania.addLookup2ParentLink("recibo.poliza.compania");
//        lookupCompania.addLookup2ParentLink("nombreCorto","recibo.poliza.compania.nombreCorto");

        PersonaLookupController lookupCompania = new PersonaLookupController("SEG");
        lookupCompania.addLookup2ParentLink("recibo.poliza.compania");
        codLookupControl2.setLookupController(lookupCompania);

        jButton1.addActionListener(new Buscar());
        form1.setFormController(new Buscar());
        form1.setMode(Consts.INSERT);

        if (owner == null) {
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            setLocation(((int) d.getWidth() - this.getWidth()) / 2, ((int) d.getHeight() - this.getHeight()) / 2);
        } else {
            owner.getSize();
            setLocation((owner.getWidth() - this.getWidth()) / 2 + (int) owner.getLocationOnScreen().getX(), (owner.getHeight() - this.getHeight()) / 2 + (int) owner.getLocationOnScreen().getY());
        }
        MDIFrame.add(this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        form1 = new org.openswing.swing.form.client.Form();
        textControl1 = new org.openswing.swing.client.TextControl();
        labelControl2 = new org.openswing.swing.client.LabelControl();
        labelControl3 = new org.openswing.swing.client.LabelControl();
        textControl3 = new org.openswing.swing.client.TextControl();
        labelControl4 = new org.openswing.swing.client.LabelControl();
        textControl4 = new org.openswing.swing.client.TextControl();
        labelControl5 = new org.openswing.swing.client.LabelControl();
        textControl5 = new org.openswing.swing.client.TextControl();
        textControl6 = new org.openswing.swing.client.TextControl();
        labelControl6 = new org.openswing.swing.client.LabelControl();
        textControl7 = new org.openswing.swing.client.TextControl();
        labelControl7 = new org.openswing.swing.client.LabelControl();
        textControl8 = new org.openswing.swing.client.TextControl();
        codLookupControl2 = new org.openswing.swing.client.CodLookupControl();
        labelControl8 = new org.openswing.swing.client.LabelControl();
        textControl9 = new org.openswing.swing.client.TextControl();
        labelControl9 = new org.openswing.swing.client.LabelControl();
        labelControl10 = new org.openswing.swing.client.LabelControl();
        comboBoxControl1 = new org.openswing.swing.client.ComboBoxControl();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Buscar Siniestro");

        form1.setVOClassName(Siniestro.class.getName());

        textControl1.setAttributeName("recibo.poliza.numero");
        textControl1.setTrimText(true);
        textControl1.setUpperCase(true);

        labelControl2.setLabel("numeroPoliza");

        labelControl3.setLabel("numeroRecibo");

        textControl3.setAttributeName("recibo.numero");
        textControl3.setTrimText(true);
        textControl3.setUpperCase(true);

        labelControl4.setLabel("placa");

        textControl4.setAttributeName("recibo.vehiculo.placa");
        textControl4.setTrimText(true);
        textControl4.setUpperCase(true);

        labelControl5.setLabel("numeroSiniestro");

        textControl5.setAttributeName("numero");
        textControl5.setTrimText(true);
        textControl5.setUpperCase(true);

        textControl6.setAttributeName("recibo.asegurado.rif.rif");
        textControl6.setTrimText(true);
        textControl6.setUpperCase(true);

        labelControl6.setLabel("cedulaRIF");

        textControl7.setAttributeName("recibo.asegurado.nombreLargo");
        textControl7.setTrimText(true);
        textControl7.setUpperCase(true);

        labelControl7.setLabel("contratanteAsegurado");

        textControl8.setAttributeName("recibo.poliza.compania.nombreLargo");
        textControl8.setEnabledOnEdit(false);
        textControl8.setEnabledOnInsert(false);
        textControl8.setTrimText(true);
        textControl8.setUpperCase(true);

        codLookupControl2.setAttributeName("recibo.poliza.compania.nombreCorto");
        codLookupControl2.setMaxCharacters(10);

        labelControl8.setLabel("compania");

        textControl9.setAttributeName("numeroNotificacion");
        textControl9.setTrimText(true);
        textControl9.setUpperCase(true);

        labelControl9.setLabel("siniestro.numeroNotificacion");

        labelControl10.setLabel("Estado Siniestro (s)");

        comboBoxControl1.setAttributeName("estado");
        comboBoxControl1.setDomainId(Dominios.EstadoSiniestroFind().getDomainId());

        javax.swing.GroupLayout form1Layout = new javax.swing.GroupLayout(form1);
        form1.setLayout(form1Layout);
        form1Layout.setHorizontalGroup(
            form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(form1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addComponent(labelControl4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(labelControl2, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                        .addComponent(labelControl8, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                        .addComponent(labelControl3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelControl5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelControl9, 0, 0, Short.MAX_VALUE))
                    .addComponent(labelControl6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addComponent(labelControl7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(codLookupControl2, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addComponent(textControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addComponent(textControl8, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addComponent(textControl5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addComponent(textControl3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addComponent(textControl9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addComponent(textControl6, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addComponent(textControl4, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addComponent(textControl7, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                    .addComponent(comboBoxControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE))
                .addContainerGap())
        );

        form1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {labelControl2, labelControl3, labelControl5, labelControl8});

        form1Layout.setVerticalGroup(
            form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(form1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(form1Layout.createSequentialGroup()
                        .addComponent(codLookupControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textControl8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelControl8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(form1Layout.createSequentialGroup()
                        .addComponent(labelControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelControl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelControl5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(form1Layout.createSequentialGroup()
                        .addComponent(textControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textControl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textControl5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelControl9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textControl9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(form1Layout.createSequentialGroup()
                        .addComponent(labelControl4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelControl6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelControl7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(form1Layout.createSequentialGroup()
                        .addComponent(textControl4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textControl6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textControl7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        form1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {codLookupControl2, labelControl10, labelControl2, labelControl3, labelControl4, labelControl5, labelControl6, labelControl7, labelControl8, labelControl9, textControl1, textControl3, textControl4, textControl5, textControl6, textControl7, textControl8});

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel2.png"))); // NOI18N
        jButton2.setPreferredSize(new java.awt.Dimension(33, 33));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check1.png"))); // NOI18N
        jButton1.setPreferredSize(new java.awt.Dimension(33, 33));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(form1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 274, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(form1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private org.openswing.swing.client.CodLookupControl codLookupControl2;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl1;
    private org.openswing.swing.form.client.Form form1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private org.openswing.swing.client.LabelControl labelControl10;
    private org.openswing.swing.client.LabelControl labelControl2;
    private org.openswing.swing.client.LabelControl labelControl3;
    private org.openswing.swing.client.LabelControl labelControl4;
    private org.openswing.swing.client.LabelControl labelControl5;
    private org.openswing.swing.client.LabelControl labelControl6;
    private org.openswing.swing.client.LabelControl labelControl7;
    private org.openswing.swing.client.LabelControl labelControl8;
    private org.openswing.swing.client.LabelControl labelControl9;
    private org.openswing.swing.client.TextControl textControl1;
    private org.openswing.swing.client.TextControl textControl3;
    private org.openswing.swing.client.TextControl textControl4;
    private org.openswing.swing.client.TextControl textControl5;
    private org.openswing.swing.client.TextControl textControl6;
    private org.openswing.swing.client.TextControl textControl7;
    private org.openswing.swing.client.TextControl textControl8;
    private org.openswing.swing.client.TextControl textControl9;
    // End of variables declaration//GEN-END:variables

    class Buscar extends FormController implements ActionListener {

        @Override
        public Response loadData(Class valueObjectClass) {
            return new VOResponse(null);
        }

        @Override
        public void createPersistentObject(ValueObject PersistentObject) throws Exception {
            super.createPersistentObject(PersistentObject);
            ((Siniestro)PersistentObject).setEstado(null);
        }
        
        

        public void actionPerformed(ActionEvent e) {
            siniestro = ((Siniestro) BuscarSiniestroDialog2.this.form1.getVOModel().getValueObject());

            if (siniestro.getRecibo() == null) {
                siniestro.setRecibo(new Recibo());
            }
            if (siniestro.getRecibo().getPoliza() == null) {
                siniestro.getRecibo().setPoliza(new Poliza());
            }

            if (siniestro.getRecibo().getVehiculo() == null) {
                siniestro.getRecibo().setVehiculo(new Vehiculo());
            }

            if (siniestro.getRecibo().getAsegurado() == null) {
                siniestro.getRecibo().setAsegurado(new Persona());
                siniestro.getRecibo().getAsegurado().setRif(new Rif());
            }

            if (textControl5.getText() != null && !textControl5.getText().isEmpty()) {
                siniestro.setNumero(textControl5.getText());
            }

            if (textControl3.getText() != null && !textControl3.getText().isEmpty()) {
                siniestro.getRecibo().setNumero(textControl3.getText());
            }

            if (textControl1.getText() != null && !textControl1.getText().isEmpty()) {
                siniestro.getRecibo().getPoliza().setNumero(textControl1.getText());
            }

            if (textControl4.getText() != null && !textControl4.getText().isEmpty()) {
                siniestro.getRecibo().getVehiculo().setPlaca(textControl4.getText());
            }

            if (textControl6.getText() != null && !textControl6.getText().isEmpty()) {
                siniestro.getRecibo().getAsegurado().getRif().setRif(textControl6.getText());
            }

            if (textControl7.getText() != null && !textControl7.getText().isEmpty()) {
                siniestro.getRecibo().getAsegurado().setNombreLargo(textControl7.getText());
            }

            if (textControl9.getText() != null && !textControl9.getText().isEmpty()) {
                siniestro.setNumeroNotificacion(textControl9.getText());
            }

            siniestro.setEstado(EstadoSiniestro.TODOS);

            Session s = null;
            try {
                String sqlCount = "SELECT count(P) FROM " + Siniestro.class.getName() + " as P WHERE ";
                String sqlRec = "FROM " + Siniestro.class.getName() + " as P WHERE ";
                String where = "";
                String where2 = "";
                String napa = "";
                Query q = null;
                boolean nR = false, nP = false, nPlaca = false, nS = false, C = false, rif = false, nombre = false, numNot = false, est = false;
                s = HibernateUtil.getSessionFactory().openSession();

                ArrayList<Object> values = new ArrayList<Object>(0);
                ArrayList<Type> valueTypes = new ArrayList<Type>(0);

                if (siniestro.getNumero() != null && !siniestro.getNumero().isEmpty()) {
                    where += " P.numero=:numSin ";
                    where2 += " P.numero=? ";
                    napa = " AND ";
                    nS = true;
                }

                if (siniestro.getRecibo().getNumero() != null && !siniestro.getRecibo().getNumero().isEmpty()) {
                    where += " P.recibo.numero=:numRec ";
                    where2 += " P.recibo.numero=? ";
                    napa = " AND ";
                    nR = true;
                }

                if (siniestro.getRecibo().getPoliza() != null && siniestro.getRecibo().getPoliza().getNumero() != null && !siniestro.getRecibo().getPoliza().getNumero().isEmpty()) {
                    where += napa + " P.recibo.poliza.numero=:numPol ";
                    where2 += napa + " P.recibo.poliza.numero=? ";
                    napa = " AND ";
                    nP = true;
                }

                if (siniestro.getRecibo().getPoliza() != null && siniestro.getRecibo().getPoliza().getCompania() != null && siniestro.getRecibo().getPoliza().getCompania().getId() != null) {
                    where += napa + " P.recibo.poliza.compania.id=:cia ";
                    where2 += napa + " P.recibo.poliza.compania.id=? ";
                    napa = " AND ";
                    C = true;
                }

                if (siniestro.getRecibo().getVehiculo() != null && siniestro.getRecibo().getVehiculo().getPlaca() != null && !siniestro.getRecibo().getVehiculo().getPlaca().isEmpty()) {
                    where += napa + " P.recibo.vehiculo.placa=:nPlaca ";
                    where2 += napa + " P.recibo.vehiculo.placa=? ";
                    napa = " AND ";
                    nPlaca = true;
                }

                if (siniestro.getRecibo() != null && siniestro.getRecibo().getAsegurado() != null && siniestro.getRecibo().getAsegurado().getRif() != null && siniestro.getRecibo().getAsegurado().getRif().getRif() != null && !siniestro.getRecibo().getAsegurado().getRif().getRif().isEmpty()) {
                    where += napa + " (P.recibo.poliza.contratante.rif.rif like :rif ";
                    where2 += napa + " (P.recibo.poliza.contratante.rif.rif like ? ";
                    napa = " OR ";
                    where += napa + " P.recibo.asegurado.rif.rif like :rif) ";
                    where2 += napa + " P.recibo.asegurado.rif.rif like ?) ";
                    napa = " AND ";
                    rif = true;
                }

                if (siniestro.getRecibo() != null && siniestro.getRecibo().getAsegurado() != null && siniestro.getRecibo().getAsegurado().getNombreLargo() != null && !siniestro.getRecibo().getAsegurado().getNombreLargo().isEmpty()) {
                    where += napa + " (P.recibo.asegurado.nombreLargo like :nombre ";
                    where2 += napa + " (P.recibo.asegurado.rif.rif like ? ";
                    napa = " OR ";
                    where += napa + " P.recibo.poliza.contratante.nombreLargo like :nombre) ";
                    where2 += napa + " P.recibo.poliza.contratante.nombreLargo like ?) ";
                    napa = " AND ";
                    nombre = true;
                }

                if (siniestro.getNumeroNotificacion() != null && !siniestro.getNumeroNotificacion().isEmpty()) {
                    where += napa + " P.numeroNotificacion like :numNot ";
                    where2 += napa + " P.numeroNotificacion like ? ";
                    napa = " AND ";
                    numNot = true;
                }

                if (siniestro.getEstado() != null) {
                    if (siniestro.getEstado() != EstadoSiniestro.TODOS) {
                        where += napa + " P.estado like :est";
                        where2 += napa + " P.estado like ? ";
                    }
                    else
                    {
                        where += napa + " P.estado != :est";
                        where2 += napa + " P.estado != ? ";
                        
                    }
                    napa = " AND ";
                    est = true;
                }

                if (!nR && !nP && !C && !nPlaca && !nS && !rif && !nombre && !numNot && !est) {
                    JOptionPane.showMessageDialog(BuscarSiniestroDialog2.this, "Faltan Datos!!!", "Mensaje", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                q = s.createQuery(sqlCount + where);
                if (nS) {
                    q.setString("numSin", siniestro.getNumero());
                }
                if (nR) {
                    q.setString("numRec", siniestro.getRecibo().getNumero());
                }
                if (nP) {
                    q.setString("numPol", siniestro.getRecibo().getPoliza().getNumero());
                }
                if (C) {
                    q.setLong("cia", siniestro.getRecibo().getPoliza().getCompania().getId());
                }
                if (nPlaca) {
                    q.setString("nPlaca", siniestro.getRecibo().getVehiculo().getPlaca());
                }
                if (rif) {
                    q.setString("rif", "%" + siniestro.getRecibo().getAsegurado().getRif().getRif() + "%");
                }
                if (nombre) {
                    q.setString("nombre", "%" + siniestro.getRecibo().getAsegurado().getNombreLargo() + "%");
                }
                if (numNot) {
                    q.setString("numNot", "%" + siniestro.getNumeroNotificacion() + "%");
                }
                if (est) {
                    q.setString("est", siniestro.getEstado().toString());
                }
                Long cant = (Long) q.list().get(0);
                if (cant == 0) {
                    JOptionPane.showMessageDialog(BuscarSiniestroDialog2.this, "No existe siniestro", "Mensaje", JOptionPane.ERROR_MESSAGE);
                } else if (cant == 1) {
                    q = s.createQuery(sqlRec + where);
                    if (nS) {

                        q.setString("numSin", siniestro.getNumero());
                    }
                    if (nR) {
                        q.setString("numRec", siniestro.getRecibo().getNumero());
                    }
                    if (nP) {
                        q.setString("numPol", siniestro.getRecibo().getPoliza().getNumero());
                    }
                    if (C) {
                        q.setLong("cia", siniestro.getRecibo().getPoliza().getCompania().getId());
                    }
                    if (nPlaca) {
                        q.setString("nPlaca", siniestro.getRecibo().getVehiculo().getPlaca());
                    }
                    if (rif) {
                        q.setString("rif", "%" + siniestro.getRecibo().getAsegurado().getRif().getRif() + "%");
                    }
                    if (nombre) {
                        q.setString("nombre", "%" + siniestro.getRecibo().getAsegurado().getNombreLargo() + "%");
                    }
                    if (numNot) {
                        q.setString("numNot", "%" + siniestro.getNumeroNotificacion() + "%");
                    }
                    if (est) {
                        q.setString("est", siniestro.getEstado().name());
                    }
                    siniestro = (Siniestro) q.uniqueResult();
                    new SiniestroDetailController(SiniestroDetailFrame.class.getName(), null, siniestro, true);
                } else { // Si son varios
                    q = s.createQuery(sqlRec + where2);
                    if (nS) {
                        values.add(siniestro.getNumero());
                        valueTypes.add(new StringType());
                    }
                    if (nR) {
                        values.add(siniestro.getRecibo().getNumero());
                        valueTypes.add(new StringType());
                    }
                    if (nP) {
                        values.add(siniestro.getRecibo().getPoliza().getNumero());
                        valueTypes.add(new StringType());
                    }
                    if (C) {
                        values.add(siniestro.getRecibo().getPoliza().getCompania().getId());
                        valueTypes.add(new LongType());
                    }
                    if (nPlaca) {
                        values.add(siniestro.getRecibo().getVehiculo().getPlaca());
                        valueTypes.add(new StringType());
                    }
                    if (rif) {
                        values.add("%" + siniestro.getRecibo().getAsegurado().getRif().getRif() + "%");
                        valueTypes.add(new StringType());
                        values.add("%" + siniestro.getRecibo().getAsegurado().getRif().getRif() + "%");
                        valueTypes.add(new StringType());
                    }
                    if (nombre) {
                        values.add("%" + siniestro.getRecibo().getAsegurado().getNombreLargo() + "%");
                        valueTypes.add(new StringType());
                        values.add("%" + siniestro.getRecibo().getAsegurado().getNombreLargo() + "%");
                        valueTypes.add(new StringType());
                    }
                    if (numNot) {
                        values.add("%" + siniestro.getNumeroNotificacion() + "%");
                        valueTypes.add(new StringType());
                    }
                    if (est) {
                        values.add(siniestro.getEstado().name());
                        valueTypes.add(new StringType());
                    }
                    Type[] tt = new Type[valueTypes.size()];
                    for (int i = 0; i < valueTypes.size(); i++) {
                        tt[i] = valueTypes.get(i);
                    }
                    new SiniestroGridControllerWhitSQL(SiniestrosGridFrame.class.getName(), SiniestroDetailFrame.class.getName(), Siniestro.class.getName(), null, sqlRec + where2, values.toArray(), tt);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(BuscarSiniestroDialog2.this, ex.getMessage(), "Mensaje", JOptionPane.ERROR_MESSAGE);
            } finally {
                s.close();
            }
        }
    }
}
