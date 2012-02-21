package controlador.vehiculos;

 import controlador.Principal;
import controlador.util.DefaultGridFrameController;
import java.awt.Color;
import java.beans.PropertyVetoException;
import logger.LoggerUtil;
import modelo.entidades.vehiculos.dominio.TipoColor;
import modelo.entidades.vehiculos.maestra.Vehiculo;
import modelo.util.bean.BeanVO;
import org.openswing.swing.message.receive.java.ValueObject;
import vista.vehiculos.VehiculosDetailFrame;

/**
 *
 * @author bc
 */
public class VehiculosGridController extends DefaultGridFrameController {

    @Override
    public Color getBackgroundColor(int row, String attributeName, Object value) {
        if (attributeName.equalsIgnoreCase("color.nombre") /*|| attributeName.equalsIgnoreCase("id")*/) {
            TipoColor tc = ((Vehiculo) gridFrame.getGridControl().getVOListTableModel().getObjectForRow(row)).getColor();
            if (tc != null && tc.getColor2() != null) {
                return tc.getColor2();
            }
        }
        return super.getBackgroundColor(row, attributeName, value);
    }

    @Override
    public void createValueObject(ValueObject valueObject) throws Exception {
        int rowToSel = gridFrame.getGridControl().getVOListTableModel().getRowCount() - 1;
        gridFrame.getGridControl().getVOListTableModel().setField(
                rowToSel, "marcaModelo", Principal.defaultData.vehiculo.getMarcaModelo());
        gridFrame.getGridControl().getVOListTableModel().setField(
                rowToSel, "color", Principal.defaultData.vehiculo.getColor());
        gridFrame.getGridControl().getVOListTableModel().setField(
                rowToSel, "clasificacion", Principal.defaultData.vehiculo.getClasificacionVehiculo());
    }

    public VehiculosGridController(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo);
        //((VehiculosGridFrame) gridFrame).getButtonColor().addActionListener(this);
        //((VehiculosGridFrame) gridFrame).getButtonColor().setOpaque(true);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        new VehiculoDetailController(VehiculosDetailFrame.class.getName(), null, (BeanVO) persistentObject, false);
    }

    /*public void actionPerformed(ActionEvent e) {
    Color c = JColorChooser.showDialog(MDIFrame.getInstance(), "Color", Color.RED);
    ((Vehiculo) gridFrame.getGridControl().getVOListTableModel().getObjectForRow(gridFrame.getGridControl().getSelectedRow())).setColor2(c);
    gridFrame.getGridControl().getVOListTableModel().updateObjectAt(gridFrame.getGridControl().getSelectedRow());
    }*/
}
