package vista.borrame;

import controlador.borrame.FileGridFrameController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JOptionPane;
import logger.LoggerUtil;
import modelo.borrame.FileVO;
import modelo.util.bean.BeanVO;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.client.OptionPane;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;
import vista.util.DefaultGridFrame;

/**
 *
 * @author bc
 */
public class FileGridFrame extends DefaultGridFrame {

    public FileGridFrame(FileGridFrameController c) {
        initComponents();

        /*z.org.openswing.swing.table.columns.client.PictureCaptureColumn pcc = new z.org.openswing.swing.table.columns.client.PictureCaptureColumn();
        pcc.setColumnName("file");
        pcc.setEditableOnEdit(true);
        pcc.setEditableOnInsert(true);
        pcc.setFileNameAttributeName("fileName");
        pcc.setPreferredWidth(300);
        gridControl1.getColumnContainer().add(pcc);*/

        buttonColumn1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String fileName = (String) getGridControl().getVOListTableModel().getField(getGridControl().getSelectedRow(), "fileName");
//                String filePath = new File(fileName).getAbsolutePath();
//                try {
//                    //TODO windows only
//                    //Runtime.getRuntime().exec("cmd /C " + filePath);
//                    java.awt.Desktop.getDesktop().open(new File(filePath));
//                } catch (Exception ex) {
//                    LoggerUtil.error(this.getClass(), "actionPerformed", ex);
//                }
                String filePath = new File( fileName).getAbsolutePath();

                try {
                    //TODO windows only
                    if (System.getProperty("os.name").startsWith("Windows")) {
                        Runtime.getRuntime().exec("cmd /C " + filePath);
                    } else {
                        OptionPane.showMessageDialog(
                                MDIFrame.getInstance(),
                                "Disponible solo para Windows. \n" +
                                "Contacte con el proveedor.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    LoggerUtil.error(this.getClass(), "doubleClick", ex);
                }

                getGridControl().requestFocus();
            }
        });
        gridControl1.setController(c);
        gridControl1.setGridDataLocator(c);
        pack();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gridControl1 = new org.openswing.swing.client.GridControl();
        buttonColumn1 = new org.openswing.swing.table.columns.client.ButtonColumn();
        fileColumn1 = new org.openswing.swing.table.columns.client.FileColumn();
        dateTimeColumn1 = new org.openswing.swing.table.columns.client.DateTimeColumn();
        insertButton1 = new org.openswing.swing.client.InsertButton();
        editButton1 = new org.openswing.swing.client.EditButton();
        saveButton1 = new org.openswing.swing.client.SaveButton();
        exportButton1 = new org.openswing.swing.client.ExportButton();
        reloadButton1 = new org.openswing.swing.client.ReloadButton();
        deleteButton1 = new org.openswing.swing.client.DeleteButton();

        gridControl1.setDeleteButton(deleteButton1);
        gridControl1.setEditButton(editButton1);
        gridControl1.setExportButton(exportButton1);
        gridControl1.setFunctionId("xxxxxxxxx2");
        gridControl1.setInsertButton(insertButton1);
        gridControl1.setReloadButton(reloadButton1);
        gridControl1.setSaveButton(saveButton1);
        gridControl1.setValueObjectClassName(FileVO.class.getName());

        buttonColumn1.setColumnName("button");
        buttonColumn1.setEditableOnEdit(true);
        buttonColumn1.setEditableOnInsert(true);
        buttonColumn1.setEnableInReadOnlyMode(true);
        buttonColumn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/view.png"))); // NOI18N
        buttonColumn1.setPreferredWidth(20);
        buttonColumn1.setText("");
        gridControl1.getColumnContainer().add(buttonColumn1);

        fileColumn1.setColumnName("file");
        fileColumn1.setEditableOnEdit(true);
        fileColumn1.setEditableOnInsert(true);
        fileColumn1.setFileNameAttributeName("fileName");
        fileColumn1.setPreferredWidth(300);
        gridControl1.getColumnContainer().add(fileColumn1);

        dateTimeColumn1.setColumnName("uploadDate");
        gridControl1.getColumnContainer().add(dateTimeColumn1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(insertButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(saveButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exportButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reloadButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(258, Short.MAX_VALUE))
            .addComponent(gridControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(insertButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(exportButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(reloadButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deleteButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(gridControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void inicializar(GridDataLocator gridDataLocator, GridController gridController, String valueObjectClassName, String titulo, boolean addToMDIFrame) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void reloadGridsData() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clearGridsData() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setOwnerVO(BeanVO beanVO) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public GridControl getGridControl() {
        return gridControl1;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.table.columns.client.ButtonColumn buttonColumn1;
    private org.openswing.swing.table.columns.client.DateTimeColumn dateTimeColumn1;
    private org.openswing.swing.client.DeleteButton deleteButton1;
    private org.openswing.swing.client.EditButton editButton1;
    private org.openswing.swing.client.ExportButton exportButton1;
    private org.openswing.swing.table.columns.client.FileColumn fileColumn1;
    private org.openswing.swing.client.GridControl gridControl1;
    private org.openswing.swing.client.InsertButton insertButton1;
    private org.openswing.swing.client.ReloadButton reloadButton1;
    private org.openswing.swing.client.SaveButton saveButton1;
    // End of variables declaration//GEN-END:variables
}
