package controlador.vehiculos.mant;

import controlador.util.DefaultAllGridFrameController;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JColorChooser;
import modelo.entidades.vehiculos.dominio.TipoColor;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.util.java.Consts;
import vista.vehiculos.mant.TipoColorGridFrame;

/**
 *
 * @author bc
 */
public class TipoColorGridFrameController extends DefaultAllGridFrameController implements ActionListener {

    public TipoColorGridFrameController(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, "Colores");
        ((TipoColorGridFrame) gridFrame).getButtonColor().addActionListener(this);
        ((TipoColorGridFrame) gridFrame).getButtonColor().setOpaque(true);
    }

    @Override
    public boolean isCellEditable(GridControl grid, int row, String attributeName) {
        if ((Consts.EDIT == grid.getMode())
                && (attributeName.equals("color")
                || attributeName.equals("nombre")
                || attributeName.equals("bloqueado"))) {
            TipoColor tc = (TipoColor) grid.getVOListTableModel().getObjectForRow(row);
            if (tc.getId() != null) {
                return !tc.getBloqueado();
            }
        }
        return super.isCellEditable(grid, row, attributeName);
    }

    @Override
    public Color getBackgroundColor(int row, String attributeName, Object value) {
        if (/*attributeName.equalsIgnoreCase("nombre") ||*/attributeName.equalsIgnoreCase("color") || attributeName.equalsIgnoreCase("id")) {
            Color c = (((TipoColor) gridFrame.getGridControl().getVOListTableModel().getObjectForRow(row)).getColor2());
            if (c != null) {
                return c;
            }
        }
        return super.getBackgroundColor(row, attributeName, value);
    }

    public void actionPerformed(ActionEvent e) {
        Color c = JColorChooser.showDialog(MDIFrame.getInstance(), "Color", Color.RED);
        ((TipoColor) gridFrame.getGridControl().getVOListTableModel().getObjectForRow(gridFrame.getGridControl().getSelectedRow())).setColor2(c);
        gridFrame.getGridControl().getVOListTableModel().updateObjectAt(gridFrame.getGridControl().getSelectedRow());
    }
}
