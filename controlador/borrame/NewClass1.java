/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador.borrame;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.tree.client.TreeController;
import org.openswing.swing.tree.client.TreeDataLocator;

/**
 *
 * @author bc
 */
public class NewClass1 extends TreeDataLocator implements TreeController {

    
    @Override
    public Response getTreeModel(JTree tree) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void doubleClick(DefaultMutableTreeNode node) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void leftClick(DefaultMutableTreeNode node) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean rightClick(DefaultMutableTreeNode node) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
