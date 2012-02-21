/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Tree.java
 *
 * Created on 13/10/2011, 03:09:05 PM
 */
package vista.polizas;

import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.tree.client.TreeController;
import org.openswing.swing.tree.client.TreeDataLocator;
import org.openswing.swing.tree.client.TreeGridPanel;
import vista.util.DefaultTreeGridFrame;

/**
 *
 * @author NELSON
 */
public class Tree extends DefaultTreeGridFrame {

    /** Creates new form Tree */
    public Tree() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textColumn1 = new org.openswing.swing.table.columns.client.TextColumn();
        treeGridPanel1 = new org.openswing.swing.tree.client.TreeGridPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(treeGridPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(treeGridPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.table.columns.client.TextColumn textColumn1;
    private org.openswing.swing.tree.client.TreeGridPanel treeGridPanel1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void inicializar(TreeDataLocator treeDataLocator, TreeController treeController, String valueObjectClassName, String titulo, boolean addToMDIFrame) {
        treeGridPanel1.setTreeController(treeController);
        treeGridPanel1.setTreeDataLocator(treeDataLocator);

        treeGridPanel1.addGridColumn("Fecha", 100);
        
        
        if (addToMDIFrame) {
            MDIFrame.add(this);
        }
    }

    @Override
    public TreeGridPanel getTreeGridPanel() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}