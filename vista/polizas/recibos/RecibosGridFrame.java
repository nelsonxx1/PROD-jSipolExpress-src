package vista.polizas.recibos;

import controlador.polizas.recibos.ReciboDetailController;
import java.beans.PropertyVetoException;
import logger.LoggerUtil;
import modelo.Dominios;
import modelo.entidades.polizas.recibos.maestra.Recibo;
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
public class RecibosGridFrame extends DefaultGridFrame {

    public RecibosGridFrame() {
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
        gridData = new org.openswing.swing.client.GridControl();
        integerColumn1 = new org.openswing.swing.table.columns.client.IntegerColumn();
        comboColumn1 = new org.openswing.swing.table.columns.client.ComboColumn();
        textColumn1 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn8 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn2 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn3 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn5 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn4 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn6 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn7 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn11 = new org.openswing.swing.table.columns.client.TextColumn();
        currencyColumn1 = new org.openswing.swing.table.columns.client.CurrencyColumn();
        dateColumn1 = new org.openswing.swing.table.columns.client.DateColumn();
        comboColumn2 = new org.openswing.swing.table.columns.client.ComboColumn();
        comboColumn3 = new org.openswing.swing.table.columns.client.ComboColumn();
        currencyColumn2 = new org.openswing.swing.table.columns.client.CurrencyColumn();
        currencyColumn3 = new org.openswing.swing.table.columns.client.CurrencyColumn();
        currencyColumn4 = new org.openswing.swing.table.columns.client.CurrencyColumn();
        dateColumn2 = new org.openswing.swing.table.columns.client.DateColumn();
        dateColumn3 = new org.openswing.swing.table.columns.client.DateColumn();
        dateColumn4 = new org.openswing.swing.table.columns.client.DateColumn();
        dateColumn5 = new org.openswing.swing.table.columns.client.DateColumn();
        textColumn13 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn14 = new org.openswing.swing.table.columns.client.TextColumn();
        checkBoxColumn1 = new org.openswing.swing.table.columns.client.CheckBoxColumn();
        textColumn15 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn9 = new org.openswing.swing.table.columns.client.TextColumn();
        dateTimeColumn1 = new org.openswing.swing.table.columns.client.DateTimeColumn();
        textColumn12 = new org.openswing.swing.table.columns.client.TextColumn();
        textColumn10 = new org.openswing.swing.table.columns.client.TextColumn();
        dateTimeColumn2 = new org.openswing.swing.table.columns.client.DateTimeColumn();
        checkBoxColumn2 = new org.openswing.swing.table.columns.client.CheckBoxColumn();
        checkBoxColumn3 = new org.openswing.swing.table.columns.client.CheckBoxColumn();

        setTitle("Recibos");
        setPreferredSize(new java.awt.Dimension(700, 540));

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reloadButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exportButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(navigatorBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(filterButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(292, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filterButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(navigatorBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exportButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reloadButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(insertButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        gridData.setDeleteButton(deleteButton1);
        gridData.setExportButton(exportButton1);
        gridData.setFilterButton(filterButton1);
        gridData.setFunctionId("RecibosGrid");
        gridData.setNavBar(navigatorBar1);
        gridData.setReloadButton(reloadButton1);
        gridData.setValueObjectClassName(Recibo.class.getName());

        integerColumn1.setColumnName("id");
        integerColumn1.setPreferredWidth(40);
        gridData.getColumnContainer().add(integerColumn1);

        comboColumn1.setColumnFilterable(true);
        comboColumn1.setColumnName("estatus");
        comboColumn1.setColumnSortable(true);
        comboColumn1.setDomainId(Dominios.EstatusRecibo().getDomainId());
        gridData.getColumnContainer().add(comboColumn1);

        textColumn1.setColumnFilterable(true);
        textColumn1.setColumnName("poliza.compania.nombreCorto");
        textColumn1.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn1);

        textColumn8.setColumnFilterable(true);
        textColumn8.setColumnName("poliza.ramoPoliza.nombreCorto");
        textColumn8.setColumnRequired(false);
        textColumn8.setColumnSortable(true);
        textColumn8.setHeaderColumnName("ramoPoliza");
        gridData.getColumnContainer().add(textColumn8);

        textColumn2.setColumnFilterable(true);
        textColumn2.setColumnName("poliza.numero");
        textColumn2.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn2);

        textColumn3.setColumnFilterable(true);
        textColumn3.setColumnName("numero");
        textColumn3.setColumnSortable(true);
        textColumn3.setHeaderColumnName("recibo.numero");
        gridData.getColumnContainer().add(textColumn3);

        textColumn5.setColumnFilterable(true);
        textColumn5.setColumnName("poliza.contratante.rif.cedulaCompleta");
        textColumn5.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn5);

        textColumn4.setColumnFilterable(true);
        textColumn4.setColumnName("poliza.contratante.nombreLargo");
        textColumn4.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn4);

        textColumn6.setColumnFilterable(true);
        textColumn6.setColumnName("asegurado.rif.cedulaCompleta");
        textColumn6.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn6);

        textColumn7.setColumnFilterable(true);
        textColumn7.setColumnName("asegurado.nombreLargo");
        textColumn7.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn7);

        textColumn11.setColumnFilterable(true);
        textColumn11.setColumnName("vehiculo.placa");
        textColumn11.setColumnSortable(true);
        textColumn11.setHeaderColumnName("placa");
        gridData.getColumnContainer().add(textColumn11);

        currencyColumn1.setColumnFilterable(true);
        currencyColumn1.setColumnName("comision");
        currencyColumn1.setColumnSortable(true);
        currencyColumn1.setDecimals(2);
        gridData.getColumnContainer().add(currencyColumn1);

        dateColumn1.setColumnFilterable(true);
        dateColumn1.setColumnName("fechaPagoComision");
        dateColumn1.setColumnSortable(true);
        gridData.getColumnContainer().add(dateColumn1);

        comboColumn2.setColumnFilterable(true);
        comboColumn2.setColumnName("tipoRecibo");
        comboColumn2.setColumnSortable(true);
        comboColumn2.setDomainId(Dominios.TipoRecibo().getDomainId());
        gridData.getColumnContainer().add(comboColumn2);

        comboColumn3.setColumnFilterable(true);
        comboColumn3.setColumnName("tipoVigencia");
        comboColumn3.setColumnSortable(true);
        comboColumn3.setDomainId(Dominios.TipoVigencia().getDomainId());
        comboColumn3.setHeaderColumnName("recibo.tipoVigencia");
        gridData.getColumnContainer().add(comboColumn3);

        currencyColumn2.setColumnFilterable(true);
        currencyColumn2.setColumnName("sumaAsegurada");
        currencyColumn2.setColumnSortable(true);
        currencyColumn2.setDecimals(2);
        gridData.getColumnContainer().add(currencyColumn2);

        currencyColumn3.setColumnFilterable(true);
        currencyColumn3.setColumnName("primaTotal");
        currencyColumn3.setColumnSortable(true);
        currencyColumn3.setDecimals(2);
        gridData.getColumnContainer().add(currencyColumn3);

        currencyColumn4.setColumnFilterable(true);
        currencyColumn4.setColumnName("primaRecibo");
        currencyColumn4.setColumnSortable(true);
        currencyColumn4.setDecimals(2);
        gridData.getColumnContainer().add(currencyColumn4);

        dateColumn2.setColumnFilterable(true);
        dateColumn2.setColumnName("fechaEmision");
        dateColumn2.setColumnSortable(true);
        gridData.getColumnContainer().add(dateColumn2);

        dateColumn3.setColumnFilterable(true);
        dateColumn3.setColumnName("vigenciaDesde");
        dateColumn3.setColumnSortable(true);
        gridData.getColumnContainer().add(dateColumn3);

        dateColumn4.setColumnFilterable(true);
        dateColumn4.setColumnName("vigenciaHasta");
        dateColumn4.setColumnSortable(true);
        gridData.getColumnContainer().add(dateColumn4);

        dateColumn5.setColumnFilterable(true);
        dateColumn5.setColumnName("fechaCobro");
        dateColumn5.setColumnSortable(true);
        gridData.getColumnContainer().add(dateColumn5);

        textColumn13.setColumnFilterable(true);
        textColumn13.setColumnName("cobrador2.nombreLargo");
        textColumn13.setColumnSortable(true);
        textColumn13.setHeaderColumnName("cobrador.nombreLargo");
        gridData.getColumnContainer().add(textColumn13);

        textColumn14.setColumnFilterable(true);
        textColumn14.setColumnName("poliza.productor.nombreLargo");
        textColumn14.setColumnSortable(true);
        textColumn14.setHeaderColumnName("productor.nombreLargo");
        gridData.getColumnContainer().add(textColumn14);

        checkBoxColumn1.setColumnFilterable(true);
        checkBoxColumn1.setColumnName("anulado");
        checkBoxColumn1.setColumnSortable(true);
        gridData.getColumnContainer().add(checkBoxColumn1);

        textColumn15.setColumnFilterable(true);
        textColumn15.setColumnName("tipoDistribucionNombre");
        textColumn15.setColumnRequired(false);
        textColumn15.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn15);

        textColumn9.setColumnFilterable(true);
        textColumn9.setColumnName("auditoria.usuarioInsert");
        textColumn9.setColumnRequired(false);
        textColumn9.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn9);

        dateTimeColumn1.setColumnFilterable(true);
        dateTimeColumn1.setColumnName("auditoria.fechaInsert");
        dateTimeColumn1.setColumnRequired(false);
        dateTimeColumn1.setColumnSortable(true);
        gridData.getColumnContainer().add(dateTimeColumn1);

        textColumn12.setColumnFilterable(true);
        textColumn12.setColumnName("poliza.codigoArchivo");
        textColumn12.setColumnSortable(true);
        textColumn12.setHeaderColumnName("poliza.codigoArchivo");
        gridData.getColumnContainer().add(textColumn12);

        textColumn10.setColumnFilterable(true);
        textColumn10.setColumnName("auditoria.usuarioUpdate");
        textColumn10.setColumnRequired(false);
        textColumn10.setColumnSortable(true);
        gridData.getColumnContainer().add(textColumn10);

        dateTimeColumn2.setColumnFilterable(true);
        dateTimeColumn2.setColumnName("auditoria.fechaUpdate");
        dateTimeColumn2.setColumnRequired(false);
        dateTimeColumn2.setColumnSortable(true);
        gridData.getColumnContainer().add(dateTimeColumn2);

        checkBoxColumn2.setColumnFilterable(true);
        checkBoxColumn2.setColumnName("devolucion");
        checkBoxColumn2.setColumnSortable(true);
        gridData.getColumnContainer().add(checkBoxColumn2);

        checkBoxColumn3.setColumnFilterable(true);
        checkBoxColumn3.setColumnName("financiado");
        checkBoxColumn3.setColumnSortable(true);
        gridData.getColumnContainer().add(checkBoxColumn3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(gridData, javax.swing.GroupLayout.DEFAULT_SIZE, 664, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gridData, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void insertButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertButton1ActionPerformed
        NumeroReciboDialog numPol = new NumeroReciboDialog(this, null);
        numPol.setVisible(true);
        if (numPol.isOk()) {
            new ReciboDetailController(ReciboDetailFrame.class.getName(), null, numPol.getRecibo(), true, null, numPol.isNuevo());
            try {
                this.closeFrame();
            } catch (PropertyVetoException ex) {
                LoggerUtil.error(this.getClass(), "insertButton1ActionPerformed", ex);
            }
        }
//        new ReciboDetailController(ReciboDetailFrame.class.getName(), null, null, true, null);
//        try {
//            this.closeFrame();
//        } catch (PropertyVetoException ex) {
//            LoggerUtil.error(this.getClass(), "insertButton1ActionPerformed", ex);
//        }
}//GEN-LAST:event_insertButton1ActionPerformed

    @Override
    public void inicializar(GridDataLocator gridDataLocator, GridController gridController, String valueObjectClassName, String titulo, boolean addToMDIFrame) {
        initComponents();
        
        gridData.setGridDataLocator(gridDataLocator);
        gridData.setController(gridController);

//        integerColumn1.setAdditionalHeaderColumnName("Poliza");
//        integerColumn1.setAdditionalHeaderColumnSpan(7);
//
//        textColumn3.setAdditionalHeaderColumnName("Recibo");
//        textColumn3.setAdditionalHeaderColumnSpan(13);
//
//        textColumn9.setAdditionalHeaderColumnName("Auditoria");
//        textColumn9.setAdditionalHeaderColumnSpan(4);

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
    private org.openswing.swing.table.columns.client.CheckBoxColumn checkBoxColumn1;
    private org.openswing.swing.table.columns.client.CheckBoxColumn checkBoxColumn2;
    private org.openswing.swing.table.columns.client.CheckBoxColumn checkBoxColumn3;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn1;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn2;
    private org.openswing.swing.table.columns.client.ComboColumn comboColumn3;
    private org.openswing.swing.table.columns.client.CurrencyColumn currencyColumn1;
    private org.openswing.swing.table.columns.client.CurrencyColumn currencyColumn2;
    private org.openswing.swing.table.columns.client.CurrencyColumn currencyColumn3;
    private org.openswing.swing.table.columns.client.CurrencyColumn currencyColumn4;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn1;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn2;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn3;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn4;
    private org.openswing.swing.table.columns.client.DateColumn dateColumn5;
    private org.openswing.swing.table.columns.client.DateTimeColumn dateTimeColumn1;
    private org.openswing.swing.table.columns.client.DateTimeColumn dateTimeColumn2;
    private org.openswing.swing.client.DeleteButton deleteButton1;
    private org.openswing.swing.client.ExportButton exportButton1;
    private org.openswing.swing.client.FilterButton filterButton1;
    private org.openswing.swing.client.GridControl gridData;
    private org.openswing.swing.client.InsertButton insertButton1;
    private org.openswing.swing.table.columns.client.IntegerColumn integerColumn1;
    private javax.swing.JPanel jPanel1;
    private org.openswing.swing.client.NavigatorBar navigatorBar1;
    private org.openswing.swing.client.ReloadButton reloadButton1;
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
