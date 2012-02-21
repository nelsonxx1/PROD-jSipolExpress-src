package vista.polizas.recibos.distribucion;

import controlador.personas.PersonaLookupController;
import controlador.personas.PersonaLookupControllerPorNombre;
import controlador.polizas.recibos.DistribucionGridInternalFrame;
import controlador.util.DefaultGridInternalController;
import javax.swing.ListSelectionModel;
import modelo.Dominios;
import modelo.entidades.polizas.recibos.dominio.DistribucionModelo;
import modelo.entidades.polizas.recibos.dominio.TipoDistribucion;
import modelo.util.bean.BeanVO;
import org.openswing.swing.form.client.Form;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.util.java.Consts;
import vista.util.DefaultDetailFrame;

/**
 *
 * @author bc
 */
public class TipoDistribucionDetailFrame extends DefaultDetailFrame {

    private DefaultGridInternalController controllerDistribuciones;

    public TipoDistribucionDetailFrame() {
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel28 = new javax.swing.JPanel();
        gridControl2 = new org.openswing.swing.client.GridControl();
        decimalColumn8 = new org.openswing.swing.table.columns.client.DecimalColumn();
        comboColumn1 = new org.openswing.swing.table.columns.client.ComboColumn();
        codLookupColumn2 = new org.openswing.swing.table.columns.client.CodLookupColumn();
        textColumn6 = new org.openswing.swing.table.columns.client.TextColumn();
        percentageColumn1 = new org.openswing.swing.table.columns.client.PercentageColumn();
        percentageColumn2 = new org.openswing.swing.table.columns.client.PercentageColumn();
        percentageColumn3 = new org.openswing.swing.table.columns.client.PercentageColumn();
        jPanel29 = new javax.swing.JPanel();
        insertButton10 = new org.openswing.swing.client.InsertButton();
        editButton10 = new org.openswing.swing.client.EditButton();
        deleteButton10 = new org.openswing.swing.client.DeleteButton();
        saveButton10 = new org.openswing.swing.client.SaveButton();
        reloadButton10 = new org.openswing.swing.client.ReloadButton();
        saveButton1 = new org.openswing.swing.client.SaveButton();
        editButton1 = new org.openswing.swing.client.EditButton();
        form1 = new org.openswing.swing.form.client.Form();
        labelControl2 = new org.openswing.swing.client.LabelControl();
        textControl1 = new org.openswing.swing.client.TextControl();

        setTitle("Distribucion de Comisiones");

        gridControl2.setDeleteButton(deleteButton10);
        gridControl2.setEditButton(editButton10);
        gridControl2.setInsertButton(insertButton10);
        gridControl2.setMaxNumberOfRowsOnInsert(9);
        gridControl2.setReloadButton(reloadButton10);
        gridControl2.setSaveButton(saveButton10);
        gridControl2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        gridControl2.setValueObjectClassName(DistribucionModelo.class.getName());

        decimalColumn8.setColumnName("id");
        decimalColumn8.setColumnRequired(false);
        decimalColumn8.setPreferredWidth(40);
        gridControl2.getColumnContainer().add(decimalColumn8);

        comboColumn1.setColumnName("tipoCobrador");
        comboColumn1.setColumnRequired(false);
        comboColumn1.setDomainId(Dominios.TipoCobrador().getDomainId());
        comboColumn1.setEditableOnEdit(true);
        comboColumn1.setEditableOnInsert(true);
        comboColumn1.setHeaderColumnName("tipoCobrador");
        gridControl2.getColumnContainer().add(comboColumn1);

        codLookupColumn2.setAutoCompletitionWaitTime(1000L);
        codLookupColumn2.setColumnFilterable(true);
        codLookupColumn2.setColumnName("cobrador.nombreCorto");
        codLookupColumn2.setColumnRequired(false);
        codLookupColumn2.setColumnSortable(true);
        codLookupColumn2.setControllerMethodName("getPersonaNueva");
        codLookupColumn2.setEditableOnEdit(true);
        codLookupColumn2.setEditableOnInsert(true);
        codLookupColumn2.setHeaderColumnName("");
        codLookupColumn2.setPreferredWidth(160);
        gridControl2.getColumnContainer().add(codLookupColumn2);

        textColumn6.setColumnName("cobrador.nombreLargo");
        textColumn6.setColumnRequired(false);
        textColumn6.setHeaderColumnName("cobrador");
        textColumn6.setPreferredWidth(160);
        gridControl2.getColumnContainer().add(textColumn6);

        percentageColumn1.setColumnName("porComision");
        percentageColumn1.setDecimals(3);
        percentageColumn1.setEditableOnEdit(true);
        percentageColumn1.setEditableOnInsert(true);
        percentageColumn1.setPreferredWidth(80);
        gridControl2.getColumnContainer().add(percentageColumn1);

        percentageColumn2.setColumnName("porBono1");
        percentageColumn2.setDecimals(3);
        percentageColumn2.setEditableOnEdit(true);
        percentageColumn2.setEditableOnInsert(true);
        percentageColumn2.setPreferredWidth(80);
        gridControl2.getColumnContainer().add(percentageColumn2);

        percentageColumn3.setColumnName("porBono2");
        percentageColumn3.setDecimals(3);
        percentageColumn3.setEditableOnEdit(true);
        percentageColumn3.setEditableOnInsert(true);
        percentageColumn3.setPreferredWidth(80);
        gridControl2.getColumnContainer().add(percentageColumn3);

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addComponent(insertButton10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editButton10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteButton10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveButton10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reloadButton10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(insertButton10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(editButton10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(deleteButton10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(saveButton10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(reloadButton10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(414, Short.MAX_VALUE))
            .addComponent(gridControl2, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gridControl2, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                .addContainerGap())
        );

        form1.setVOClassName(TipoDistribucion.class.getName());

        labelControl2.setLabel("nombre");

        textControl1.setAttributeName("nombre");
        textControl1.setTrimText(true);
        textControl1.setUpperCase(true);

        javax.swing.GroupLayout form1Layout = new javax.swing.GroupLayout(form1);
        form1.setLayout(form1Layout);
        form1Layout.setHorizontalGroup(
            form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(form1Layout.createSequentialGroup()
                .addComponent(labelControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE))
        );
        form1Layout.setVerticalGroup(
            form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, form1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        form1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {labelControl2, textControl1});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel28, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(form1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(saveButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(saveButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(form1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void inicializar(FormController formController, boolean addToMDIFrame) {
        initComponents();
        controllerDistribuciones =
                new DistribucionGridInternalFrame(TipoDistribucion.class.getName(), "getDistribuciones", gridControl2, null);
        gridControl2.setGridDataLocator(controllerDistribuciones);
        gridControl2.setController(controllerDistribuciones);
        gridControl2.setCreateInnerVO(false);
        
        PersonaLookupControllerPorNombre cobradorDistrib = new PersonaLookupControllerPorNombre("COB");
        cobradorDistrib.addLookup2ParentLink("cobrador");
        cobradorDistrib.addLookup2ParentLink("nombreCorto", "cobrador.nombreCorto");
        codLookupColumn2.setLookupController(cobradorDistrib);

        form1.setFormController(formController);
        form1.setCreateInnerVO(false);
        if (addToMDIFrame) {
            pack();
        } else {
            setBounds(0, 0, 0, 0);
        }
        MDIFrame.add(this);
    }

    @Override
    public void saveGridsData() {
        gridControl2.getSaveButton().doClick();
    }

    @Override
    public void reloadGridsData() {
        gridControl2.reloadData();
    }

    @Override
    public void clearGridsData() {
        gridControl2.clearData();
    }

    @Override
    public void setOwnerVO(BeanVO beanVO) {
        controllerDistribuciones.setBeanVO(beanVO);
    }

    @Override
    public Form getMainPanel() {
        return form1;
    }

    @Override
    public void modeChanged(int currentMode) {
        if (currentMode == Consts.INSERT) {
            clearGridsData();
        }
        if (currentMode == Consts.INSERT) {
            setEnableGridInternalButtons(false);
        } else {
            setEnableGridInternalButtons(true);
        }
    }

    private void setEnableGridInternalButtons(boolean enabled) {
        jPanel29.setVisible(enabled);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.table.columns.client.CodLookupColumn codLookupColumn2;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn1;
    private org.openswing.swing.table.columns.client.DecimalColumn decimalColumn8;
    private org.openswing.swing.client.DeleteButton deleteButton10;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.client.EditButton editButton10;
    private org.openswing.swing.form.client.Form form1;
    private org.openswing.swing.client.GridControl gridControl2;
    private org.openswing.swing.client.InsertButton insertButton10;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private org.openswing.swing.client.LabelControl labelControl2;
    private org.openswing.swing.table.columns.client.PercentageColumn percentageColumn1;
    private org.openswing.swing.table.columns.client.PercentageColumn percentageColumn2;
    private org.openswing.swing.table.columns.client.PercentageColumn percentageColumn3;
    private org.openswing.swing.client.ReloadButton reloadButton10;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.client.SaveButton saveButton10;
    private org.openswing.swing.table.columns.client.TextColumn textColumn6;
    private org.openswing.swing.client.TextControl textControl1;
    // End of variables declaration//GEN-END:variables
}
