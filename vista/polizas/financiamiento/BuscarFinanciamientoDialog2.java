package vista.polizas.financiamiento;

import controlador.personas.PersonaLookupControllerPorNombre;
import controlador.polizas.financiamiento.FinanciamientoDetailController;
import controlador.polizas.financiamiento.FinanciamientoGridControllerWhitSQL;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.Dominios;
import modelo.HibernateUtil;
import modelo.entidades.personas.maestra.Persona;
import modelo.entidades.personas.maestra.Rif;
import modelo.entidades.polizas.financiamiento.Financiamiento;
import modelo.entidades.polizas.maestra.Poliza;
import modelo.entidades.polizas.recibos.maestra.Recibo;
import modelo.entidades.vehiculos.maestra.Vehiculo;
import modelo.utilitario.BuscarFinanciamiento;
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
public class BuscarFinanciamientoDialog2 extends InternalFrame {

    private BuscarFinanciamiento buscarFinanciamiento;

    public BuscarFinanciamientoDialog2(Component owner) {
        initComponents();

        this.getRootPane().setDefaultButton(jButton1);

        PersonaLookupControllerPorNombre lookupCompania = new PersonaLookupControllerPorNombre("FIN");
        lookupCompania.addLookup2ParentLink("financiadora");
        lookupCompania.addLookup2ParentLink("nombreCorto", "financiadora.nombreCorto");

//        PersonaLookupController lookupCompania = new PersonaLookupController("FIN");
//        lookupCompania.addLookup2ParentLink("financiadora");
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
        pack();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        form1 = new org.openswing.swing.form.client.Form();
        textControl1 = new org.openswing.swing.client.TextControl();
        labelControl2 = new org.openswing.swing.client.LabelControl();
        labelControl1 = new org.openswing.swing.client.LabelControl();
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
        codLookupControl2 = new org.openswing.swing.client.CodLookupControl();
        labelControl8 = new org.openswing.swing.client.LabelControl();
        comboBoxControl1 = new org.openswing.swing.client.ComboBoxControl();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Buscar Financiamiento");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel2.png"))); // NOI18N
        jButton2.setPreferredSize(new java.awt.Dimension(33, 33));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check1.png"))); // NOI18N
        jButton1.setPreferredSize(new java.awt.Dimension(33, 33));

        form1.setVOClassName(BuscarFinanciamiento.class.getName());

        textControl1.setAttributeName("recibo.poliza.numero");
        textControl1.setLinkLabel(labelControl2);
        textControl1.setToolTipText("Numero de la Poliza");
        textControl1.setTrimText(true);
        textControl1.setUpperCase(true);

        labelControl2.setLabel("numeroPoliza");

        labelControl1.setLabel("financiadora");

        labelControl3.setLabel("numeroRecibo");

        textControl3.setAttributeName("recibo.numero");
        textControl3.setLinkLabel(labelControl2);
        textControl3.setToolTipText("Numero de Recibo");
        textControl3.setTrimText(true);
        textControl3.setUpperCase(true);

        labelControl4.setLabel("placa");

        textControl4.setAttributeName("recibo.vehiculo.placa");
        textControl4.setToolTipText("Numero de Placa del Vehiculo (Si el Bien Asegurado es un Vehiculo)");
        textControl4.setTrimText(true);
        textControl4.setUpperCase(true);

        labelControl5.setLabel("pagador.cedulaRIF");

        textControl5.setAttributeName("pagador.rif.rif");
        textControl5.setToolTipText("Cedula o RIF del Pagador");
        textControl5.setTrimText(true);
        textControl5.setUpperCase(true);

        textControl6.setAttributeName("pagador.nombreLargo");
        textControl6.setToolTipText("Nombre o Apellido del Pagador");
        textControl6.setTrimText(true);
        textControl6.setUpperCase(true);

        labelControl6.setLabel("pagador.nombre");

        textControl7.setAttributeName("numero");
        textControl7.setToolTipText("Numero del Financiamiento");
        textControl7.setTrimText(true);
        textControl7.setUpperCase(true);

        labelControl7.setLabel("financiamiento.numero");

        codLookupControl2.setAttributeName("financiadora.nombreCorto");
        codLookupControl2.setAutoCompletitionWaitTime(1000L);
        codLookupControl2.setMaxCharacters(10);

        labelControl8.setLabel("estatus");

        comboBoxControl1.setAttributeName("estatus");
        comboBoxControl1.setDomainId(Dominios.EstatusFinanciamiento().getDomainId());
        comboBoxControl1.setNullAsDefaultValue(true);

        javax.swing.GroupLayout form1Layout = new javax.swing.GroupLayout(form1);
        form1.setLayout(form1Layout);
        form1Layout.setHorizontalGroup(
            form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(form1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(form1Layout.createSequentialGroup()
                        .addComponent(labelControl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textControl3, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
                    .addGroup(form1Layout.createSequentialGroup()
                        .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelControl4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelControl7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textControl4, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                            .addComponent(textControl7, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)))
                    .addGroup(form1Layout.createSequentialGroup()
                        .addComponent(labelControl5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textControl5, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
                    .addGroup(form1Layout.createSequentialGroup()
                        .addComponent(labelControl6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textControl6, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
                    .addGroup(form1Layout.createSequentialGroup()
                        .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelControl2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelControl1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelControl8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                            .addComponent(codLookupControl2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                            .addComponent(comboBoxControl1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))))
                .addContainerGap())
        );

        form1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {labelControl1, labelControl2, labelControl3, labelControl4, labelControl5, labelControl6, labelControl7, labelControl8});

        form1Layout.setVerticalGroup(
            form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(form1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codLookupControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(form1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelControl8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(form1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(comboBoxControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textControl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textControl7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textControl4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textControl5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelControl6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textControl6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        form1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {codLookupControl2, comboBoxControl1, labelControl1, labelControl2, labelControl3, labelControl4, labelControl5, labelControl6, labelControl7, labelControl8, textControl1, textControl3, textControl4, textControl5, textControl6, textControl7});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 254, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(form1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(form1, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-356)/2, (screenSize.height-295)/2, 356, 295);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
}//GEN-LAST:event_jButton2ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.client.CodLookupControl codLookupControl2;
    private org.openswing.swing.client.ComboBoxControl comboBoxControl1;
    private org.openswing.swing.form.client.Form form1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private org.openswing.swing.client.LabelControl labelControl1;
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
    // End of variables declaration//GEN-END:variables

    class Buscar extends FormController implements ActionListener {

        @Override
        public Response loadData(Class valueObjectClass) {
            return new VOResponse(null);
        }

        public void actionPerformed(ActionEvent e) {
            buscarFinanciamiento = ((BuscarFinanciamiento) BuscarFinanciamientoDialog2.this.form1.getVOModel().getValueObject());

            // <editor-fold defaultstate="collapsed" desc="Intanciar Atributos">
            if (buscarFinanciamiento.getRecibo() == null) {
                buscarFinanciamiento.setRecibo(new Recibo());
            }
            if (buscarFinanciamiento.getRecibo().getPoliza() == null) {
                buscarFinanciamiento.getRecibo().setPoliza(new Poliza());
            }

            if (buscarFinanciamiento.getRecibo().getVehiculo() == null) {
                buscarFinanciamiento.getRecibo().setVehiculo(new Vehiculo());
            }

            if (buscarFinanciamiento.getPagador() == null) {
                buscarFinanciamiento.setPagador(new Persona());
            }

            if (buscarFinanciamiento.getPagador().getRif() == null) {
                buscarFinanciamiento.getPagador().setRif(new Rif());
            }

            if (buscarFinanciamiento.getFinanciadora() == null) {
                buscarFinanciamiento.setFinanciadora(new Persona());
            }
            // </editor-fold>

            // <editor-fold defaultstate="expanded" desc="Inicializar Atributos">
            if (textControl1.getText() != null && !textControl1.getText().isEmpty()) {
                buscarFinanciamiento.getRecibo().getPoliza().setNumero(textControl1.getText());
            }

            if (textControl3.getText() != null && !textControl3.getText().isEmpty()) {
                buscarFinanciamiento.getRecibo().setNumero(textControl3.getText());
            }

            if (textControl4.getText() != null && !textControl4.getText().isEmpty()) {
                buscarFinanciamiento.getRecibo().getVehiculo().setPlaca(textControl4.getText());
            }

            if (textControl5.getText() != null && !textControl5.getText().isEmpty()) {
                buscarFinanciamiento.getPagador().getRif().setRif(textControl5.getText());
            }

            if (textControl6.getText() != null && !textControl6.getText().isEmpty()) {
                buscarFinanciamiento.getPagador().setNombreLargo(textControl6.getText());
            }

            if (textControl7.getText() != null && !textControl7.getText().isEmpty()) {
                buscarFinanciamiento.setNumero(textControl7.getText());
            }
            // </editor-fold>

            Session s = null;
            try {
                String sqlCount = "SELECT count(F) FROM " + Financiamiento.class.getName() + " F WHERE ";
                String sqlRec = "SELECT DISTINCT F FROM " + Financiamiento.class.getName() + " F WHERE ";
                String where = "";
                String where2 = "";
                String napa = "";
                Query q = null;
                boolean nR = false, nP = false, nPlaca = false, nF = false, C = false, rif = false, nombre = false, esta = false;
                s = HibernateUtil.getSessionFactory().openSession();

                ArrayList<Object> values = new ArrayList<Object>(0);
                ArrayList<Type> valueTypes = new ArrayList<Type>(0);

                if (buscarFinanciamiento.getRecibo().getNumero() != null && !buscarFinanciamiento.getRecibo().getNumero().isEmpty()) {
                    sqlCount = "SELECT count(F) FROM " + Financiamiento.class.getName() + " F LEFT JOIN F.recibos R WHERE ";
                    sqlRec = "SELECT DISTINCT F FROM " + Financiamiento.class.getName() + " F LEFT JOIN F.recibos R WHERE ";
                    where += " R.numero=:numRec ";
                    where2 += " R.numero=? ";
                    napa = " AND ";
                    nR = true;
                }
                if (buscarFinanciamiento.getRecibo().getPoliza() != null && buscarFinanciamiento.getRecibo().getPoliza().getNumero() != null && !buscarFinanciamiento.getRecibo().getPoliza().getNumero().isEmpty()) {
                    sqlCount = "SELECT count(F) FROM " + Financiamiento.class.getName() + " F LEFT JOIN F.recibos R WHERE ";
                    sqlRec = "SELECT DISTINCT F FROM " + Financiamiento.class.getName() + " F LEFT JOIN F.recibos R WHERE ";
                    where += napa + " R.poliza.numero=:numPol ";
                    where2 += napa + " R.poliza.numero=? ";
                    napa = " AND ";
                    nP = true;
                }

                if (buscarFinanciamiento.getRecibo().getVehiculo() != null && buscarFinanciamiento.getRecibo().getVehiculo().getPlaca() != null && !buscarFinanciamiento.getRecibo().getVehiculo().getPlaca().isEmpty()) {
                    sqlCount = "SELECT count(F) FROM " + Financiamiento.class.getName() + " F LEFT JOIN F.recibos R WHERE ";
                    sqlRec = "SELECT DISTINCT F FROM " + Financiamiento.class.getName() + " F LEFT JOIN F.recibos R WHERE ";
                    where += napa + " R.vehiculo.placa=:nPlaca ";
                    where2 += napa + " R.vehiculo.placa=? ";
                    napa = " AND ";
                    nPlaca = true;
                }

                if (buscarFinanciamiento.getNumero() != null && !buscarFinanciamiento.getNumero().isEmpty()) {
                    where += " F.numeroFF=:numFin ";
                    where2 += " F.numeroFF=? ";
                    napa = " AND ";
                    nF = true;
                }

                if (buscarFinanciamiento.getFinanciadora() != null && buscarFinanciamiento.getFinanciadora().getId() != null) {
                    where += napa + " F.financiadora.id=:cia ";
                    where2 += napa + " F.financiadora.id=? ";
                    napa = " AND ";
                    C = true;
                }

                if (buscarFinanciamiento.getPagador() != null && buscarFinanciamiento.getPagador().getRif() != null && buscarFinanciamiento.getPagador().getRif().getRif() != null && !buscarFinanciamiento.getPagador().getRif().getRif().isEmpty()) {
                    where += napa + " F.pagador.rif.rif like :rif ";
                    where2 += napa + " F.pagador.rif.rif like ? ";
                    napa = " AND ";
                    rif = true;
                }
                if (buscarFinanciamiento.getPagador() != null && buscarFinanciamiento.getPagador().getNombreLargo() != null && !buscarFinanciamiento.getPagador().getNombreLargo().isEmpty()) {
                    where += napa + " F.pagador.nombreLargo like :nombre ";
                    where2 += napa + " F.pagador.nombreLargo like ? ";
                    napa = " AND ";
                    nombre = true;
                }

                if (buscarFinanciamiento.getEstatus() != null) {
                    where += napa + " F.estatus IN (:esta)";
                    where2 += napa + " F.estatus IN (?)";
                    napa = " AND ";
                    esta = true;
                }

                if (!nR && !nP && !C && !nPlaca && !nF && !rif && !nombre && !esta) {
                    JOptionPane.showMessageDialog(BuscarFinanciamientoDialog2.this, "Faltan Datos!!!", "Mensaje", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                q = s.createQuery(sqlCount + where);
                if (nR) {
                    q.setString("numRec", buscarFinanciamiento.getRecibo().getNumero());
                }
                if (nP) {
                    q.setString("numPol", buscarFinanciamiento.getRecibo().getPoliza().getNumero());
                }
                if (nPlaca) {
                    q.setString("nPlaca", buscarFinanciamiento.getRecibo().getVehiculo().getPlaca());
                }
                if (nF) {
                    q.setString("numFin", buscarFinanciamiento.getNumero());
                }
                if (C) {
                    q.setLong("cia", buscarFinanciamiento.getFinanciadora().getId());
                }
                if (rif) {
                    q.setString("rif", "%" + buscarFinanciamiento.getPagador().getRif().getRif() + "%");
                }
                if (nombre) {
                    q.setString("nombre", "%" + buscarFinanciamiento.getPagador().getNombreLargo() + "%");
                }
                if (esta) {
                    q.setString("esta", buscarFinanciamiento.getEstatus().name());
                }
                Long cant = (Long) q.uniqueResult();
                if (cant == 0) {
                    JOptionPane.showMessageDialog(BuscarFinanciamientoDialog2.this, "No existe financiamiento", "Mensaje", JOptionPane.ERROR_MESSAGE);
                } else if (cant == 1) {
                    q = s.createQuery(sqlRec + where);
                    if (nR) {
                        q.setString("numRec", buscarFinanciamiento.getRecibo().getNumero());
                    }
                    if (nP) {
                        q.setString("numPol", buscarFinanciamiento.getRecibo().getPoliza().getNumero());
                    }
                    if (nPlaca) {
                        q.setString("nPlaca", buscarFinanciamiento.getRecibo().getVehiculo().getPlaca());
                    }
                    if (nF) {
                        q.setString("numFin", buscarFinanciamiento.getNumero());
                    }
                    if (C) {
                        q.setLong("cia", buscarFinanciamiento.getFinanciadora().getId());
                    }
                    if (rif) {
                        q.setString("rif", "%" + buscarFinanciamiento.getPagador().getRif().getRif() + "%");
                    }
                    if (nombre) {
                        q.setString("nombre", "%" + buscarFinanciamiento.getPagador().getNombreLargo() + "%");
                    }
                    if (esta) {
                        q.setString("esta", buscarFinanciamiento.getEstatus().name());
                    }
                    Financiamiento financiamiento2 = (Financiamiento) q.uniqueResult();
                    new FinanciamientoDetailController(FinanciamientoDetailFrame.class.getName(), null, financiamiento2, true, null, false);
                } else {
                    q = s.createQuery(sqlRec + where);
                    if (nR) {
                        values.add(buscarFinanciamiento.getRecibo().getNumero());
                        valueTypes.add(new StringType());
                    }
                    if (nP) {
                        values.add(buscarFinanciamiento.getRecibo().getPoliza().getNumero());
                        valueTypes.add(new StringType());
                    }
                    if (nPlaca) {
                        values.add(buscarFinanciamiento.getRecibo().getVehiculo().getPlaca());
                        valueTypes.add(new StringType());
                    }
                    if (nF) {
                        values.add(buscarFinanciamiento.getNumero());
                        valueTypes.add(new StringType());
                    }
                    if (C) {
                        values.add(buscarFinanciamiento.getFinanciadora().getId());
                        valueTypes.add(new LongType());
                    }
                    if (rif) {
                        values.add("%" + buscarFinanciamiento.getPagador().getRif().getRif() + "%");
                        valueTypes.add(new StringType());
                    }
                    if (nombre) {
                        values.add("%" + buscarFinanciamiento.getPagador().getNombreLargo() + "%");
                        valueTypes.add(new StringType());
                    }
                    if (esta) {
                        values.add(buscarFinanciamiento.getEstatus().name());
                        valueTypes.add(new StringType());
                    }
                    Type[] tt = new Type[valueTypes.size()];
                    for (int i = 0; i < valueTypes.size(); i++) {
                        tt[i] = valueTypes.get(i);
                    }
                    new FinanciamientoGridControllerWhitSQL(FinanciamientosGridFrame.class.getName(), FinanciamientoDetailFrame.class.getName(), Financiamiento.class.getName(), null, sqlRec + where2, values.toArray(), tt);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(BuscarFinanciamientoDialog2.this, ex.getMessage(), "Mensaje", JOptionPane.ERROR_MESSAGE);
            } finally {
                s.close();
            }
        }
    }
}
