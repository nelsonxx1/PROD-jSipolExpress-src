package vista.vehiculos;

import controlador.vehiculos.MarcaModeloLookupController;
import controlador.vehiculos.VehiculoDetailController;
import controlador.vehiculos.VehiculoGridControllerWhitSQL;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.HibernateUtil;
import modelo.entidades.vehiculos.dominio.MarcaVehiculo;
import modelo.entidades.vehiculos.dominio.ModeloVehiculo;
import modelo.entidades.vehiculos.maestra.Vehiculo;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.hibernate.type.IntegerType;
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
public class BuscarVehiculoDialog extends InternalFrame {

    private Vehiculo vehiculo;

    public BuscarVehiculoDialog(Component owner) {
        initComponents();

        this.getRootPane().setDefaultButton(jButton1);

        MarcaModeloLookupController lookupMM = new MarcaModeloLookupController();
        lookupMM.addLookup2ParentLink("marcaModelo");
        codLookupControl1.setLookupController(lookupMM);

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
        textControl2 = new org.openswing.swing.client.TextControl();
        codLookupControl1 = new org.openswing.swing.client.CodLookupControl();
        labelControl1 = new org.openswing.swing.client.LabelControl();
        labelControl3 = new org.openswing.swing.client.LabelControl();
        numericControl2 = new org.openswing.swing.client.NumericControl();

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

        form1.setVOClassName(Vehiculo.class.getName());

        textControl1.setAttributeName("placa");
        textControl1.setLinkLabel(labelControl2);
        textControl1.setToolTipText("Numero de la Poliza");
        textControl1.setTrimText(true);
        textControl1.setUpperCase(true);

        labelControl2.setLabel("vehiculo.placa");

        textControl2.setAttributeName("marcaModelo.marca.nombre");
        textControl2.setEnabledOnEdit(false);
        textControl2.setEnabledOnInsert(false);

        codLookupControl1.setAttributeName("marcaModelo.nombre");
        codLookupControl1.setLinkLabel(labelControl1);
        codLookupControl1.setMaxCharacters(10);
        codLookupControl1.setToolTipText("RIF de la Compañia Aseguradora");

        labelControl1.setLabel("marcaModelo.marca.nombre");

        labelControl3.setLabel("vehiculo.ayo");

        numericControl2.setAttributeName("ayo");
        numericControl2.setMaxCharacters(4);
        numericControl2.setMaxValue(2050.0);
        numericControl2.setMinValue(1900.0);

        javax.swing.GroupLayout form1Layout = new javax.swing.GroupLayout(form1);
        form1.setLayout(form1Layout);
        form1Layout.setHorizontalGroup(
            form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(form1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(labelControl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(numericControl2, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addComponent(textControl2, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addComponent(codLookupControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addComponent(textControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
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
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(form1Layout.createSequentialGroup()
                        .addComponent(textControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelControl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numericControl2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        form1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {codLookupControl1, labelControl1, labelControl2, labelControl3, numericControl2, textControl1, textControl2});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(form1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 239, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(form1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
}//GEN-LAST:event_jButton2ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.client.CodLookupControl codLookupControl1;
    private org.openswing.swing.form.client.Form form1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private org.openswing.swing.client.LabelControl labelControl1;
    private org.openswing.swing.client.LabelControl labelControl2;
    private org.openswing.swing.client.LabelControl labelControl3;
    private org.openswing.swing.client.NumericControl numericControl2;
    private org.openswing.swing.client.TextControl textControl1;
    private org.openswing.swing.client.TextControl textControl2;
    // End of variables declaration//GEN-END:variables

    class Buscar extends FormController implements ActionListener {

        @Override
        public Response loadData(Class valueObjectClass) {
            return new VOResponse(null);
        }

        public void actionPerformed(ActionEvent e) {
            vehiculo = ((Vehiculo) BuscarVehiculoDialog.this.form1.getVOModel().getValueObject());



            if (textControl1.getText() != null && !textControl1.getText().isEmpty()) {
                vehiculo.setPlaca(textControl1.getText());
            }

            if (vehiculo.getMarcaModelo() == null) {
                vehiculo.setMarcaModelo(new ModeloVehiculo());
                vehiculo.getMarcaModelo().setMarca(new MarcaVehiculo());
            }

            if (numericControl2.getText() != null && !numericControl2.getText().isEmpty()) {
                vehiculo.setAyo(((Number) numericControl2.getValue()).intValue());
            }

            Session s = null;
            try {
                String sqlCount = "SELECT count(P) FROM " + Vehiculo.class.getName() + " as P WHERE ";
                String sqlRec = "FROM " + Vehiculo.class.getName() + " as P WHERE ";
                String where = "";
                String where2 = "";
                String napa = "";
                Query q = null;
                boolean MM = false, ayo = false, placa = false;

                ArrayList<Object> values = new ArrayList<Object>(0);
                ArrayList<Type> valueTypes = new ArrayList<Type>(0);

                s = HibernateUtil.getSessionFactory().openSession();

                // Numero de Poliza

                if (vehiculo.getMarcaModelo() != null && vehiculo.getMarcaModelo().getId() != null ) {
                    where += " P.marcaModelo.id=:modelo AND P.marcaModelo.marca.id=:marca ";
                    where2 += " P.marcaModelo.id=? AND P.marcaModelo.marca.id=? ";
                    napa = " AND ";
                    MM = true;
                }

                if (vehiculo.getAyo() != null) {
                    where += napa + " P.ayo=:ayo ";
                    where2 += napa + " P.ayo=? ";
                    napa = " AND ";
                    ayo = true;
                }

                if (vehiculo.getPlaca() != null && !vehiculo.getPlaca().isEmpty()) {
                    where += napa + " P.placa like :plac";
                    where2 += napa + " P.placa like ?";
                    napa = " AND ";
                    placa = true;
                }

                if (!MM && !placa && !ayo) {
                    JOptionPane.showMessageDialog(BuscarVehiculoDialog.this, "Faltan Datos!!!", "Mensaje", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                q = s.createQuery(sqlCount + where);

                if (MM) {
                    q.setLong("modelo", vehiculo.getMarcaModelo().getId());
                    q.setLong("marca", vehiculo.getMarcaModelo().getMarca().getId());
                }
                if (placa) {
                    q.setString("plac", "%" + vehiculo.getPlaca() + "%");
                }
                if (ayo) {
                    q.setInteger("ayo", vehiculo.getAyo());
                }

                Long cant = (Long) q.list().get(0);
                if (cant == 0) {
                    JOptionPane.showMessageDialog(BuscarVehiculoDialog.this, "No existe poliza", "Mensaje", JOptionPane.ERROR_MESSAGE);
                } else if (cant == 1) {
                    q = s.createQuery(sqlRec + where);
                    if (MM) {
                        q.setLong("modelo", vehiculo.getMarcaModelo().getId());
                        q.setLong("marca", vehiculo.getMarcaModelo().getMarca().getId());
                    }
                    if (placa) {
                        q.setString("plac", "%" + vehiculo.getPlaca() + "%");
                    }
                    if (ayo) {
                        q.setInteger("ayo", vehiculo.getAyo());
                    }
                    vehiculo = (Vehiculo) q.uniqueResult();

                    new VehiculoDetailController(VehiculosDetailFrame.class.getName(), null, vehiculo, false);
                } else {

                    if (MM) {
                        values.add(vehiculo.getMarcaModelo().getId());
                        valueTypes.add(new LongType());
                        values.add(vehiculo.getMarcaModelo().getMarca().getId());
                        valueTypes.add(new LongType());
                    }

                    if (placa) {
                        values.add("%" + vehiculo.getPlaca() + "%");
                        valueTypes.add(new StringType());
                    }
                    if (ayo) {
                        values.add(vehiculo.getAyo());
                        valueTypes.add(new IntegerType());
                    }
                    
                    Type[] tt = new Type[valueTypes.size()];
                    for (int i = 0; i < valueTypes.size(); i++) {
                        tt[i] = valueTypes.get(i);
                    }
                    new VehiculoGridControllerWhitSQL(VehiculosGridFrame.class.getName(), VehiculosDetailFrame.class.getName(), Vehiculo.class.getName(), null, sqlRec + where2, values.toArray(), tt);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(BuscarVehiculoDialog.this, ex.getMessage(), "Mensaje", JOptionPane.ERROR_MESSAGE);
            } finally {
                s.close();
            }
        }
    }
}
