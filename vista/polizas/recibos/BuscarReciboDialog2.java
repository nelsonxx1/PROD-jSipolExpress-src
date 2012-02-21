package vista.polizas.recibos;

import controlador.personas.PersonaLookupControllerPorNombre;
import controlador.polizas.recibos.ReciboDetailController;
import controlador.polizas.recibos.ReciboGridControllerWhitSQL;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.HibernateUtil;
import modelo.entidades.personas.maestra.PersonaNatural;
import modelo.entidades.personas.maestra.Rif;
import modelo.entidades.polizas.maestra.Poliza;
import modelo.entidades.polizas.recibos.maestra.Recibo;
import modelo.entidades.vehiculos.maestra.Vehiculo;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.Type;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.InternalFrame;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.util.java.Consts;

/**
 *
 * @author bc
 */
public class BuscarReciboDialog2 extends InternalFrame {

    private Recibo recibo;

    public BuscarReciboDialog2(Component owner) {
        initComponents();

        this.getRootPane().setDefaultButton(jButton1);

        PersonaLookupControllerPorNombre lookupCompania = new PersonaLookupControllerPorNombre("SEG");
        lookupCompania.addLookup2ParentLink("poliza.compania");
        lookupCompania.addLookup2ParentLink("nombreCorto", "poliza.compania.nombreCorto");

//        PersonaLookupController lookupCompania = new PersonaLookupController("SEG");
//        lookupCompania.addLookup2ParentLink("poliza.compania");
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

        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        form1 = new org.openswing.swing.form.client.Form();
        textControl1 = new org.openswing.swing.client.TextControl();
        labelControl2 = new org.openswing.swing.client.LabelControl();
        labelControl3 = new org.openswing.swing.client.LabelControl();
        textControl3 = new org.openswing.swing.client.TextControl();
        labelControl4 = new org.openswing.swing.client.LabelControl();
        textControl4 = new org.openswing.swing.client.TextControl();
        textControl5 = new org.openswing.swing.client.TextControl();
        labelControl5 = new org.openswing.swing.client.LabelControl();
        textControl6 = new org.openswing.swing.client.TextControl();
        labelControl6 = new org.openswing.swing.client.LabelControl();
        textControl7 = new org.openswing.swing.client.TextControl();
        codLookupControl2 = new org.openswing.swing.client.CodLookupControl();
        labelControl7 = new org.openswing.swing.client.LabelControl();
        textControl8 = new org.openswing.swing.client.TextControl();
        labelControl8 = new org.openswing.swing.client.LabelControl();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Buscar Recibo");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel2.png"))); // NOI18N
        jButton2.setPreferredSize(new java.awt.Dimension(33, 33));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check1.png"))); // NOI18N
        jButton1.setPreferredSize(new java.awt.Dimension(33, 33));

        form1.setVOClassName(Recibo.class.getName());

        textControl1.setAttributeName("poliza.numero");
        textControl1.setLinkLabel(labelControl2);
        textControl1.setTrimText(true);
        textControl1.setUpperCase(true);

        labelControl2.setLabel("numeroPoliza");

        labelControl3.setLabel("numeroRecibo");

        textControl3.setAttributeName("numero");
        textControl3.setLinkLabel(labelControl2);
        textControl3.setTrimText(true);
        textControl3.setUpperCase(true);

        labelControl4.setLabel("placa");

        textControl4.setAttributeName("vehiculo.placa");
        textControl4.setTrimText(true);
        textControl4.setUpperCase(true);

        textControl5.setAttributeName("poliza.contratante.rif.rif");
        textControl5.setTrimText(true);
        textControl5.setUpperCase(true);

        labelControl5.setLabel("cedulaRIF");

        textControl6.setAttributeName("poliza.contratante.nombreLargo");
        textControl6.setTrimText(true);
        textControl6.setUpperCase(true);

        labelControl6.setLabel("contratanteAsegurado");

        textControl7.setAttributeName("poliza.compania.nombreLargo");
        textControl7.setEnabledOnEdit(false);
        textControl7.setEnabledOnInsert(false);
        textControl7.setTrimText(true);
        textControl7.setUpperCase(true);

        codLookupControl2.setAttributeName("poliza.compania.nombreCorto");
        codLookupControl2.setAutoCompletitionWaitTime(1000L);
        codLookupControl2.setMaxCharacters(10);

        labelControl7.setLabel("compania");

        textControl8.setAttributeName("certificado");
        textControl8.setLinkLabel(labelControl2);
        textControl8.setTrimText(true);
        textControl8.setUpperCase(true);

        labelControl8.setLabel("recibo.certificado");

        javax.swing.GroupLayout form1Layout = new javax.swing.GroupLayout(form1);
        form1.setLayout(form1Layout);
        form1Layout.setHorizontalGroup(
            form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(form1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(form1Layout.createSequentialGroup()
                        .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelControl7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textControl7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                            .addComponent(codLookupControl2, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                            .addComponent(textControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)))
                    .addGroup(form1Layout.createSequentialGroup()
                        .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelControl8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelControl3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textControl8, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                            .addComponent(textControl3, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)))
                    .addGroup(form1Layout.createSequentialGroup()
                        .addComponent(labelControl4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textControl4, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
                    .addGroup(form1Layout.createSequentialGroup()
                        .addComponent(labelControl5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textControl5, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
                    .addGroup(form1Layout.createSequentialGroup()
                        .addComponent(labelControl6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textControl6, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)))
                .addContainerGap())
        );

        form1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {labelControl2, labelControl3, labelControl4, labelControl5, labelControl6, labelControl7, labelControl8});

        form1Layout.setVerticalGroup(
            form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(form1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(codLookupControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelControl7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textControl7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textControl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textControl8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelControl8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textControl4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textControl5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelControl5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textControl6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelControl6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        form1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {codLookupControl2, labelControl2, labelControl3, labelControl4, labelControl5, labelControl6, labelControl7, labelControl8, textControl1, textControl3, textControl4, textControl5, textControl6, textControl7, textControl8});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 239, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
}//GEN-LAST:event_jButton2ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.client.CodLookupControl codLookupControl2;
    private org.openswing.swing.form.client.Form form1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private org.openswing.swing.client.LabelControl labelControl2;
    private org.openswing.swing.client.LabelControl labelControl3;
    private org.openswing.swing.client.LabelControl labelControl4;
    private org.openswing.swing.client.LabelControl labelControl5;
    private org.openswing.swing.client.LabelControl labelControl6;
    private org.openswing.swing.client.LabelControl labelControl7;
    private org.openswing.swing.client.LabelControl labelControl8;
    private org.openswing.swing.client.TextControl textControl1;
    private org.openswing.swing.client.TextControl textControl3;
    private org.openswing.swing.client.TextControl textControl4;
    private org.openswing.swing.client.TextControl textControl5;
    private org.openswing.swing.client.TextControl textControl6;
    private org.openswing.swing.client.TextControl textControl7;
    private org.openswing.swing.client.TextControl textControl8;
    // End of variables declaration//GEN-END:variables

    class Buscar extends FormController implements ActionListener {

        @Override
        public Response loadData(Class valueObjectClass) {
            return new VOResponse(null);
        }

        public void actionPerformed(ActionEvent e) {
            recibo = ((Recibo) BuscarReciboDialog2.this.form1.getVOModel().getValueObject());

            if (textControl3.getText() != null && textControl3.getText().isEmpty()) {
                recibo.setNumero(textControl3.getText());
            }

            if (recibo.getPoliza() == null) {
                recibo.setPoliza(new Poliza());
            }
            if (recibo.getAsegurado() == null) {
                recibo.setAsegurado(new PersonaNatural());
            }

            if (textControl1.getText() != null && !textControl1.getText().isEmpty()) {
                recibo.getPoliza().setNumero(textControl1.getText());
            }

            //recibo.setVehiculo(new Vehiculo());

            if (textControl4.getText() != null && !textControl4.getText().isEmpty()) {
                recibo.setVehiculo(new Vehiculo());
                recibo.getVehiculo().setPlaca(textControl4.getText());
            }

            if (recibo.getPoliza().getContratante() == null) {
                recibo.getPoliza().setContratante(new PersonaNatural());
            }

            if (textControl5.getText() != null && !textControl5.getText().isEmpty()) {
                recibo.getPoliza().getContratante().setRif(new Rif());
                recibo.getPoliza().getContratante().getRif().setRif(textControl5.getText());
            }

            if (textControl6.getText() != null && !textControl6.getText().isEmpty()) {
                recibo.getPoliza().getContratante().setNombreLargo(textControl6.getText());
            }

            if (textControl8.getText() != null && !textControl8.getText().isEmpty()) {
                recibo.setCertificado(textControl8.getText());
            }

            ArrayList<Object> values = new ArrayList<Object>(0);
            ArrayList<Type> valueTypes = new ArrayList<Type>(0);

            Session s = null;
            try {
                String sqlCount = "SELECT count(P) FROM " + Recibo.class.getName() + " as P WHERE ";
                String sqlRec = "FROM " + Recibo.class.getName() + " as P WHERE ";
                String where = "";
                String where2 = "";
                String napa = "";
                Query q = null;
                boolean nR = false, nP = false, nPlaca = false, C = false, rif = false, nombre = false, cer = false;
                s = HibernateUtil.getSessionFactory().openSession();

                if (recibo.getNumero() != null && !recibo.getNumero().isEmpty()) {
                    where += " P.numero=:numRec ";
                    where2 += " P.numero=? ";
                    napa = " AND ";
                    nR = true;
                }
                if (recibo.getCertificado() != null && !recibo.getCertificado().isEmpty()) {
                    where += " P.certificado=:cer ";
                    where2 += " P.certificado=? ";
                    napa = " AND ";
                    cer = true;
                }
                if (recibo.getPoliza() != null && recibo.getPoliza().getNumero() != null && !recibo.getPoliza().getNumero().isEmpty()) {
                    where += napa + " P.poliza.numero=:numPol ";
                    where2 += napa + " P.poliza.numero=? ";
                    napa = " AND ";
                    nP = true;
                }
                if (recibo.getPoliza() != null && recibo.getPoliza().getCompania() != null && recibo.getPoliza().getCompania().getId() != null) {
                    where += napa + " P.poliza.compania.id=:cia ";
                    where2 += napa + " P.poliza.compania.id=? ";
                    napa = " AND ";
                    C = true;
                }
                if (recibo.getVehiculo() != null && recibo.getVehiculo().getPlaca() != null && !recibo.getVehiculo().getPlaca().isEmpty()) {
                    where += napa + " P.vehiculo.placa=:nPlaca ";
                    where2 += napa + " P.vehiculo.placa=? ";
                    napa = " AND ";
                    nPlaca = true;
                }
                if (recibo.getPoliza() != null && recibo.getPoliza().getContratante() != null && recibo.getPoliza().getContratante().getRif() != null && recibo.getPoliza().getContratante().getRif().getRif() != null && !recibo.getPoliza().getContratante().getRif().getRif().isEmpty()) {
                    where += napa + " P.poliza.contratante.rif.rif like :rif ";
                    where2 += napa + " (P.poliza.contratante.rif.rif like ? ";
                    napa = " OR ";
                    where += napa + " P.asegurado.rif.rif like :rif ";
                    where2 += napa + " P.asegurado.rif.rif like ?) ";
                    napa = " AND ";
                    rif = true;
                }
                if (recibo.getPoliza() != null && recibo.getPoliza().getContratante() != null && recibo.getPoliza().getContratante().getNombreLargo() != null && !recibo.getPoliza().getContratante().getNombreLargo().isEmpty()) {
                    where += napa + " P.poliza.contratante.nombreLargo like :nombre ";
                    where2 += napa + " (P.poliza.contratante.nombreLargo like ? ";
                    napa = " OR ";
                    where += napa + " P.asegurado.nombreLargo like :nombre ";
                    where2 += napa + " P.asegurado.nombreLargo like ?) ";
                    napa += " AND ";
                    nombre = true;
                }



                if (!nR && !nP && !C && !nPlaca && !rif && !nombre && !cer) {
                    JOptionPane.showMessageDialog(BuscarReciboDialog2.this, "Faltan Datos!!!", "Mensaje", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                q = s.createQuery(sqlCount + where);
                if (nR) {
                    q.setString("numRec", recibo.getNumero());
                }
                if (cer) {
                    q.setString("cer", recibo.getCertificado());
                }
                if (nP) {
                    q.setString("numPol", recibo.getPoliza().getNumero());
                }
                if (C) {
                    q.setLong("cia", recibo.getPoliza().getCompania().getId());
                }
                if (nPlaca) {
                    q.setString("nPlaca", recibo.getVehiculo().getPlaca());
                }
                if (rif) {
                    q.setString("rif", "%" + recibo.getPoliza().getContratante().getRif().getRif() + "%");
                }
                if (nombre) {
                    q.setString("nombre", "%" + recibo.getPoliza().getContratante().getNombreLargo() + "%");
                }

                Long cant = (Long) q.list().get(0);
                if (cant == 0) {
                    JOptionPane.showMessageDialog(BuscarReciboDialog2.this, "No existe recibo", "Mensaje", JOptionPane.ERROR_MESSAGE);
                } else if (cant == 1) {
                    q = s.createQuery(sqlRec + where);
                    if (nR) {
                        q.setString("numRec", recibo.getNumero());
                    }
                    if (cer) {
                        q.setString("cer", recibo.getCertificado());
                    }
                    if (nP) {
                        q.setString("numPol", recibo.getPoliza().getNumero());
                    }
                    if (C) {
                        q.setLong("cia", recibo.getPoliza().getCompania().getId());
                    }
                    if (nPlaca) {
                        q.setString("nPlaca", recibo.getVehiculo().getPlaca());
                    }
                    if (rif) {
                        q.setString("rif", "%" + recibo.getPoliza().getContratante().getRif().getRif() + "%");
                    }
                    if (nombre) {
                        q.setString("nombre", "%" + recibo.getPoliza().getContratante().getNombreLargo() + "%");
                    }
                    recibo = (Recibo) q.uniqueResult();
                    new ReciboDetailController(ReciboDetailFrame.class.getName(), null, recibo, true, null, false);
                } else {
                    if (nR) {
                        values.add(recibo.getNumero());
                        valueTypes.add(new StringType());
                    }
                    if (cer) {
                        values.add(recibo.getCertificado());
                        valueTypes.add(new StringType());
                    }
                    if (nP) {
                        values.add(recibo.getPoliza().getNumero());
                        valueTypes.add(new StringType());
                    }
                    if (C) {
                        values.add(recibo.getPoliza().getCompania().getId());
                        valueTypes.add(new LongType());
                    }
                    if (nPlaca) {
                        values.add(recibo.getVehiculo().getPlaca());
                        valueTypes.add(new StringType());
                    }
                    if (rif) {
                        values.add("%" + recibo.getPoliza().getContratante().getRif().getRif() + "%");
                        valueTypes.add(new StringType());
                        values.add("%" + recibo.getPoliza().getContratante().getRif().getRif() + "%");
                        valueTypes.add(new StringType());
                    }
                    if (nombre) {
                        values.add("%" + recibo.getPoliza().getContratante().getNombreLargo() + "%");
                        valueTypes.add(new StringType());
                        values.add("%" + recibo.getPoliza().getContratante().getNombreLargo() + "%");
                        valueTypes.add(new StringType());
                    }

                    Type[] tt = new Type[valueTypes.size()];
                    for (int i = 0; i < valueTypes.size(); i++) {
                        tt[i] = valueTypes.get(i);
                    }
                    new ReciboGridControllerWhitSQL(RecibosGridFrame.class.getName(), ReciboDetailFrame.class.getName(), Recibo.class.getName(), null, sqlRec + where2, values.toArray(), tt);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(BuscarReciboDialog2.this, ex.getMessage(), "Mensaje", JOptionPane.ERROR_MESSAGE);
            } finally {
                s.close();
            }
        }
    }
}
