package vista.polizas;

import controlador.personas.PersonaLookupControllerPorNombre;
import controlador.polizas.PolizaDetailController;
import controlador.polizas.PolizaGridControllerWhitSQL;
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
public class BuscarPolizaDialog2 extends InternalFrame {

    private Poliza poliza;

    public BuscarPolizaDialog2(Component owner) {
        initComponents();

        this.getRootPane().setDefaultButton(jButton1);

        PersonaLookupControllerPorNombre lookupCompania = new PersonaLookupControllerPorNombre("SEG");
        lookupCompania.addLookup2ParentLink("compania");
        lookupCompania.addLookup2ParentLink("nombreCorto","compania.nombreCorto");

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
        textControl3 = new org.openswing.swing.client.TextControl();
        labelControl3 = new org.openswing.swing.client.LabelControl();
        textControl4 = new org.openswing.swing.client.TextControl();
        labelControl4 = new org.openswing.swing.client.LabelControl();
        textControl5 = new org.openswing.swing.client.TextControl();
        codLookupControl2 = new org.openswing.swing.client.CodLookupControl();
        labelControl5 = new org.openswing.swing.client.LabelControl();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        setTitle("Buscar Poliza");

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel2.png"))); // NOI18N
        jButton2.setPreferredSize(new java.awt.Dimension(33, 33));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check1.png"))); // NOI18N
        jButton1.setPreferredSize(new java.awt.Dimension(33, 33));

        form1.setVOClassName(Poliza.class.getName());

        textControl1.setAttributeName("numero");
        textControl1.setLinkLabel(labelControl2);
        textControl1.setToolTipText("Numero de la Poliza");
        textControl1.setTrimText(true);
        textControl1.setUpperCase(true);

        labelControl2.setLabel("numeroPoliza");

        textControl3.setAttributeName("contratante.rif.rif");
        textControl3.setToolTipText("Cedula  del Contratante de la Poliza");
        textControl3.setTrimText(true);
        textControl3.setUpperCase(true);

        labelControl3.setLabel("contratante.rif.cedulaCompleta");

        textControl4.setAttributeName("contratante.nombreLargo");
        textControl4.setToolTipText("Nombre del Contratante de la Poliza");
        textControl4.setTrimText(true);
        textControl4.setUpperCase(true);

        labelControl4.setLabel("contratante.nombreLargo");

        textControl5.setAttributeName("compania.nombreLargo");
        textControl5.setEnabledOnEdit(false);
        textControl5.setEnabledOnInsert(false);
        textControl5.setTrimText(true);
        textControl5.setUpperCase(true);

        codLookupControl2.setAttributeName("compania.nombreCorto");
        codLookupControl2.setAutoCompletitionWaitTime(500L);
        codLookupControl2.setMaxCharacters(10);

        labelControl5.setLabel("compania");

        javax.swing.GroupLayout form1Layout = new javax.swing.GroupLayout(form1);
        form1.setLayout(form1Layout);
        form1Layout.setHorizontalGroup(
            form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(form1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(form1Layout.createSequentialGroup()
                        .addComponent(labelControl5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(codLookupControl2, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
                    .addGroup(form1Layout.createSequentialGroup()
                        .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelControl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelControl4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textControl3, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                            .addComponent(textControl4, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)))
                    .addGroup(form1Layout.createSequentialGroup()
                        .addComponent(labelControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textControl5, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                            .addComponent(textControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))))
                .addContainerGap())
        );

        form1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {labelControl2, labelControl3, labelControl4, labelControl5});

        form1Layout.setVerticalGroup(
            form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(form1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(codLookupControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textControl5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textControl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelControl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelControl4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textControl4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        form1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {codLookupControl2, labelControl2, labelControl3, labelControl4, labelControl5, textControl1, textControl3, textControl4, textControl5});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 256, Short.MAX_VALUE)
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
    private org.openswing.swing.client.TextControl textControl1;
    private org.openswing.swing.client.TextControl textControl3;
    private org.openswing.swing.client.TextControl textControl4;
    private org.openswing.swing.client.TextControl textControl5;
    // End of variables declaration//GEN-END:variables

    class Buscar extends FormController implements ActionListener {

        @Override
        public Response loadData(Class valueObjectClass) {
            return new VOResponse(null);
        }

        public void actionPerformed(ActionEvent e) {
            poliza = ((Poliza) BuscarPolizaDialog2.this.form1.getVOModel().getValueObject());
            if (textControl1.getText() != null && !textControl1.getText().isEmpty()) {
                poliza.setNumero(textControl1.getText());
            }
            if (poliza.getContratante() == null) {
                poliza.setContratante(new PersonaNatural());
            }

            if (textControl3.getText() != null && !textControl3.getText().isEmpty()) {
                poliza.getContratante().setRif(new Rif());
                poliza.getContratante().getRif().setRif(textControl3.getText());
            }

            if (textControl4.getText() != null && !textControl4.getText().isEmpty()) {
                poliza.getContratante().setNombreLargo(textControl4.getText());
            }

            Session s = null;
            try {
                String sqlCount = "SELECT count(P) FROM " + Poliza.class.getName() + " as P WHERE ";
                String sqlRec = "FROM " + Poliza.class.getName() + " as P WHERE ";
                String where = "";
                String where2 = "";
                String napa = "";
                Query q = null;
                boolean nP = false, C = false, numCI = false, nombre = false;

                ArrayList<Object> values = new ArrayList<Object>(0);
                ArrayList<Type> valueTypes = new ArrayList<Type>(0);

                s = HibernateUtil.getSessionFactory().openSession();

                // Numero de Poliza
                if (poliza.getNumero() != null && !poliza.getNumero().isEmpty()) {
                    napa = " AND ";
                    where += " P.numero=:numPol ";
                    where2 += " P.numero=? ";
                    nP = true;
                }


                if (poliza.getCompania() != null && poliza.getCompania().getId() != null) {
                    where += napa + " P.compania.id=:cia ";
                    where2 += napa + " P.compania.id=? ";
                    napa = " AND ";
                    C = true;
                }

                if (poliza.getContratante() != null && poliza.getContratante().getRif() != null && !poliza.getContratante().getRif().getRif().isEmpty()) {
                    where += napa + " P.contratante.rif.rif like :rif ";
                    where2 += napa + " P.contratante.rif.rif like ? ";
                    napa = " AND ";
                    numCI = true;

                }

                if (poliza.getContratante() != null && poliza.getContratante().getNombreLargo() != null && !poliza.getContratante().getNombreLargo().isEmpty()) {
                    where += napa + " P.contratante.nombreLargo like :nombre";
                    where2 += napa + " P.contratante.nombreLargo like ?";
                    napa = " AND ";
                    nombre = true;
                }

                if (!nP && !C && !nombre && !numCI) {
                    JOptionPane.showMessageDialog(BuscarPolizaDialog2.this, "Faltan Datos!!!", "Mensaje", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                q = s.createQuery(sqlCount + where);
                if (nP) {
                    q.setString("numPol", poliza.getNumero());
                }
                if (C) {
                    q.setLong("cia", poliza.getCompania().getId());
                }
                if (nombre) {
                    q.setString("nombre", "%" + poliza.getContratante().getNombreLargo() + "%");
                }
                if (numCI) {
                    q.setString("rif", "%" + poliza.getContratante().getRif().getRif() + "%");
                }

                Long cant = (Long) q.list().get(0);
                if (cant == 0) {
                    JOptionPane.showMessageDialog(BuscarPolizaDialog2.this, "No existe poliza", "Mensaje", JOptionPane.ERROR_MESSAGE);
                } else if (cant == 1) {
                    q = s.createQuery(sqlRec + where);
                    if (nP) {
                        q.setString("numPol", poliza.getNumero());
                    }
                    if (C) {
                        q.setLong("cia", poliza.getCompania().getId());
                    }
                    if (nombre) {
                        q.setString("nombre", "%" + poliza.getContratante().getNombreLargo() + "%");
                    }
                    if (numCI) {
                        q.setString("rif", "%" + poliza.getContratante().getRif().getRif() + "%");
                    }
                    poliza = (Poliza) q.uniqueResult();
                    new PolizaDetailController(PolizaDetailFrame.class.getName(), null, poliza, true, false);
                } else {
                    
                    if (nP) {
                        values.add(poliza.getNumero());
                        valueTypes.add(new StringType());
                    }
                    if (C) {
                        values.add(poliza.getCompania().getId());
                        valueTypes.add(new LongType());
                    }
                    if (nombre) {
                        values.add("%" + poliza.getContratante().getNombreLargo() + "%");
                        valueTypes.add(new StringType());
                    }
                    if (numCI) {
                        values.add("%" + poliza.getContratante().getRif().getRif() + "%");
                        valueTypes.add(new StringType());
                    }
//                    List l = q.list();
//                    polizas = new VOListResponse(l, false, l.size());
                    Type []tt= new Type[valueTypes.size()];
                    for (int i = 0; i < valueTypes.size(); i++) {
                        tt[i]=valueTypes.get(i);
                    }
                    new PolizaGridControllerWhitSQL(PolizasGridFrame.class.getName(), PolizaDetailFrame.class.getName(), Poliza.class.getName(), null,sqlRec + where2, values.toArray(),tt);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(BuscarPolizaDialog2.this, ex.getMessage(), "Mensaje", JOptionPane.ERROR_MESSAGE);
            } finally {
                s.close();
            }
        }
    }
}
