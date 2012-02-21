package controlador.administracion;

import controlador.util.DefaultGridFrameController;
import modelo.util.bean.BeanVO;
import org.openswing.swing.message.receive.java.ValueObject;
import vista.administracion.FacturaCompraDetailFrame;

/**
 *
 * @author orlandobcrra
 */
public class FacturasGridController extends DefaultGridFrameController {

    public FacturasGridController(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        new FacturaCompraDetailController(FacturaCompraDetailFrame.class.getName(), null, (BeanVO) persistentObject, true);
    }
}
