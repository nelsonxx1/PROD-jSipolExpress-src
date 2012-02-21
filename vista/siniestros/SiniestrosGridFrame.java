package vista.siniestros;

import controlador.siniestros.SiniestroDetailController;
import java.beans.PropertyVetoException;
import logger.LoggerUtil;
import modelo.Dominios;
import modelo.entidades.siniestros.maestra.Siniestro;
import modelo.util.bean.BeanVO;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;
import vista.util.DefaultGridFrame;

/**
 *
 * @author bc
 */
public class SiniestrosGridFrame extends DefaultGridFrame {

    public SiniestrosGridFrame() {
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        insertButton1 = new org.openswing.swing.client.InsertButton();
        deleteButton1 = new org.openswing.swing.client.DeleteButton();
        reloadButton1 = new org.openswing.swing.client.ReloadButton();
        exportButton1 = new org.openswing.swing.client.ExportButton();
        navigatorBar1 = new org.openswing.swing.client.NavigatorBar();
        filterButton1 = new org.openswing.swing.client.FilterButton();
        saveButton1 = new org.openswing.swing.client.SaveButton();
        gridData = new org.openswing.swing.client.GridControl();
        integerColumn1 = new org.openswing.swing.table.columns.client.IntegerColumn();
        comboColumn1 = new org.openswing.swing.table.columns.client.ComboColumn();
        textColumn5 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn12 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn11 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn10 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn7 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn6 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn8 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn9 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn1 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn13 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn2 = new org.openswing.swing.table.columns.client.TextColumn();
        dateTimeColumn1 = new org.openswing.swing.table.columns.client.DateTimeColumn();
        dateTimeColumn2 = new org.openswing.swing.table.columns.client.DateTimeColumn();
        textColumn3 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn4 = new org.openswing.swing.table.columns.client.TextColumn();
        dateTimeColumn3 = new org.openswing.swing.table.columns.client.DateTimeColumn();
        dateTimeColumn4 = new org.openswing.swing.table.columns.client.DateTimeColumn();
        textColumn14 = new org.openswing.swing.table.columns.client.TextColumn();
        dateTimeColumn5 = new org.openswing.swing.table.columns.client.DateTimeColumn();
        textColumn15 = new org.openswing.swing.table.columns.client.TextColumn();
        dateTimeColumn6 = new org.openswing.swing.table.columns.client.DateTimeColumn();

        setTitle("Siniestros");

        insertButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(insertButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(saveButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reloadButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exportButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(navigatorBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(insertButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(filterButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(navigatorBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(saveButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(exportButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(reloadButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(deleteButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        gridData.setDeleteButton(deleteButton1);
        gridData.setExportButton(exportButton1);
        gridData.setFilterButton(filterButton1);
        gridData.setFunctionId("SiniestrosGrid");
        gridData.setMaxSortedColumns(2);
        gridData.setNavBar(navigatorBar1);
        gridData.setReloadButton(reloadButton1);
        gridData.setSaveButton(saveButton1);
        gridData.setValueObjectClassName(Siniestro.class.getName());

        integerColumn1.setColumnName("id");
        integerColumn1.setPreferredWidth(50);
        gridData.getColumnContainer().add(integerColumn1);

        comboColumn1.setColumnFilterable(true);
        comboColumn1.setColumnName("estado");
        comboColumn1.setColumnSortable(true);
        comboColumn1.setDomainId(Dominios.EstadoSiniestro().getDomainId());
        comboColumn1.setHeaderColumnName("siniestro.estado");
        gridData.getColumnContainer().add(comboColumn1);

        textColumn5.setColumnFilterable(true);
        textColumn5.setColumnName("recibo.poliza.compania.nombreLargo");
        textColumn5.setColumnSortable(true);
        textColumn5.setHeaderColumnName("compania");
        textColumn5.setPreferredWidth(150);
        gridData.getColumnContainer().add(textColumn5);

        textColumn12.setColumnFilterable(true);
        textColumn12.setColumnName("recibo.poliza.ramoPoliza.nombre");
        textColumn12.setColumnSortable(true);
        textColumn12.setHeaderColumnName("poliza.ramo");
        gridData.getColumnContainer().add(textColumn12);

        textColumn11.setColumnFilterable(true);
        textColumn11.setColumnName("recibo.poliza.contratante.rif.cedulaCompleta");
        textColumn11.setColumnSortable(true);
        textColumn11.setHeaderColumnName("contratante.rif");
        gridData.getColumnContainer().add(textColumn11);

        textColumn10.setColumnFilterable(true);
        textColumn10.setColumnName("recibo.poliza.contratante.nombreLargo");
        textColumn10.setColumnSortable(true);
        textColumn10.setHeaderColumnName("contratante.nombre");
        gridData.getColumnContainer().add(textColumn10);

        textColumn7.setColumnFilterable(true);
        textColumn7.setColumnName("recibo.asegurado.rif.cedulaCompleta");
        textColumn7.setColumnSortable(true);
        textColumn7.setHeaderColumnName("asegurado.rif.cedulaCompleta");
        gridData.getColumnContainer().add(textColumn7);

        textColumn6.setColumnFilterable(true);
        textColumn6.setColumnName("recibo.asegurado.nombreLargo");
        textColumn6.setColumnSortable(true);
        textColumn6.setHeaderColumnName("asegurado.nombreLargo");
        textColumn6.setPreferredWidth(150);
        gridData.getColumnContainer().add(textColumn6);

        textColumn8.setColumnFilterable(true);
        textColumn8.setColumnName("recibo.poliza.numero");
        textColumn8.setColumnSortable(true);
        textColumn8.setHeaderColumnName("poliza.numero");
        gridData.getColumnContainer().add(textColumn8);

        textColumn9.setColumnFilterable(true);
        textColumn9.setColumnName("recibo.numero");
        textColumn9.setColumnSortable(true);
        textColumn9.setHeaderColumnName("recibo.numero");
        gridData.getColumnContainer().add(textColumn9);

        textColumn1.setColumnFilterable(true);
        textColumn1.setColumnName("numero");
        textColumn1.setColumnSortable(true);
        textColumn1.setHeaderColumnName("siniestro.numero");
        gridData.getColumnContainer().add(textColumn1);
        textColumn1.getAccessibleContext().setAccessibleName("siniestro.numero");

        textColumn13.setColumnFilterable(true);
        textColumn13.setColumnName("numeroNotificacion");
        textColumn13.setColumnSortable(true);
        textColumn13.setHeaderColumnName("siniestro.numeroNotificacion");
        gridData.getColumnContainer().add(textColumn13);

        textColumn2.setColumnFilterable(true);
        textColumn2.setColumnName("estatus");
        textColumn2.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn2);

        dateTimeColumn1.setColumnFilterable(true);
        dateTimeColumn1.setColumnName("fechaEstatus");
        dateTimeColumn1.setColumnSortable(true);
        gridData.getColumnContainer().add(dateTimeColumn1);

        dateTimeColumn2.setColumnFilterable(true);
        dateTimeColumn2.setColumnName("fechaOcurrencia");
        dateTimeColumn2.setColumnSortable(true);
        gridData.getColumnContainer().add(dateTimeColumn2);

        textColumn3.setColumnFilterable(true);
        textColumn3.setColumnName("detalleCausa.nombreCompleto");
        textColumn3.setColumnSortable(true);
        textColumn3.setHeaderColumnName("causa");
        gridData.getColumnContainer().add(textColumn3);

        textColumn4.setColumnFilterable(true);
        textColumn4.setColumnName("tipoPerdida");
        textColumn4.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn4);

        dateTimeColumn3.setColumnFilterable(true);
        dateTimeColumn3.setColumnName("fechaNotificacion");
        dateTimeColumn3.setColumnSortable(true);
        gridData.getColumnContainer().add(dateTimeColumn3);

        dateTimeColumn4.setColumnFilterable(true);
        dateTimeColumn4.setColumnName("fechaNotificacionCompania");
        dateTimeColumn4.setColumnSortable(true);
        gridData.getColumnContainer().add(dateTimeColumn4);

        textColumn14.setColumnFilterable(true);
        textColumn14.setColumnName("auditoria.usuarioInsert");
        textColumn14.setColumnRequired(false);
        textColumn14.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn14);

        dateTimeColumn5.setColumnFilterable(true);
        dateTimeColumn5.setColumnName("auditoria.fechaInsert");
        dateTimeColumn5.setColumnRequired(false);
        dateTimeColumn5.setColumnSortable(true);
        gridData.getColumnContainer().add(dateTimeColumn5);

        textColumn15.setColumnFilterable(true);
        textColumn15.setColumnName("auditoria.usuarioUpdate");
        textColumn15.setColumnRequired(false);
        textColumn15.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn15);

        dateTimeColumn6.setColumnFilterable(true);
        dateTimeColumn6.setColumnName("auditoria.fechaUpdate");
        dateTimeColumn6.setColumnRequired(false);
        dateTimeColumn6.setColumnSortable(true);
        gridData.getColumnContainer().add(dateTimeColumn6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(gridData, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gridData, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void insertButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertButton1ActionPerformed
        new SiniestroDetailController(SiniestroDetailFrame.class.getName(), null, null, false);
        try {
            this.closeFrame();
        } catch (PropertyVetoException ex) {
            LoggerUtil.error(this.getClass(), "insertButton1ActionPerformed", ex);
        }
    }//GEN-LAST:event_insertButton1ActionPerformed

    @Override
    public void inicializar(GridDataLocator gridDataLocator, GridController gridController, String valueObjectClassName, String titulo, boolean addToMDIFrame) {
        initComponents();
        gridData.setGridDataLocator(gridDataLocator);
        gridData.setController(gridController);

        if (addToMDIFrame) {
            pack();
        } else {
            gridData.setAutoLoadData(false);
            setBounds(0, 0, 0, 0);
        }
        MDIFrame.add(this);
    }

    @Override
    public void reloadGridsData() {
    }

    @Override
    public void clearGridsData() {
    }

    @Override
    public void setOwnerVO(BeanVO beanVO) {
    }

    @Override
    public GridControl getGridControl() {
        return gridData;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn1;
    private org.openswing.swing.table.columns.client.DateTimeColumn dateTimeColumn1;
    private org.openswing.swing.table.columns.client.DateTimeColumn dateTimeColumn2;
    private org.openswing.swing.table.columns.client.DateTimeColumn dateTimeColumn3;
    private org.openswing.swing.table.columns.client.DateTimeColumn dateTimeColumn4;
    private org.openswing.swing.table.columns.client.DateTimeColumn dateTimeColumn5;
    private org.openswing.swing.table.columns.client.DateTimeColumn dateTimeColumn6;
    private org.openswing.swing.client.DeleteButton deleteButton1;
    private org.openswing.swing.client.ExportButton exportButton1;
    private org.openswing.swing.client.FilterButton filterButton1;
    private org.openswing.swing.client.GridControl gridData;
    private org.openswing.swing.client.InsertButton insertButton1;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn1;
    private javax.swing.JPanel jPanel1;
    private org.openswing.swing.client.NavigatorBar navigatorBar1;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.table.columns.client.TextColumn textColumn1;
    private org.openswing.swing.table.columns.client.TextColumn textColumn10;
    private org.openswing.swing.table.columns.client.TextColumn textColumn11;
    private org.openswing.swing.table.columns.client.TextColumn textColumn12;
    private org.openswing.swing.table.columns.client.TextColumn textColumn13;
    private org.openswing.swing.table.columns.client.TextColumn textColumn14;
    private org.openswing.swing.table.columns.client.TextColumn textColumn15;
    private org.openswing.swing.table.columns.client.TextColumn textColumn2;
    private org.openswing.swing.table.columns.client.TextColumn textColumn3;
    private org.openswing.swing.table.columns.client.TextColumn textColumn4;
    private org.openswing.swing.table.columns.client.TextColumn textColumn5;
    private org.openswing.swing.table.columns.client.TextColumn textColumn6;
    private org.openswing.swing.table.columns.client.TextColumn textColumn7;
    private org.openswing.swing.table.columns.client.TextColumn textColumn8;
    private org.openswing.swing.table.columns.client.TextColumn textColumn9;
    // End of variables declaration//GEN-END:variables
}
