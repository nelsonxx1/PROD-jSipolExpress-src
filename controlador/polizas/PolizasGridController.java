package controlador.polizas;

import controlador.util.DefaultGridFrameController;
import modelo.util.bean.BeanVO;
import org.openswing.swing.message.receive.java.ValueObject;
import vista.polizas.PolizaDetailFrame;

/**
 *
 * @author bc
 */
public class PolizasGridController extends DefaultGridFrameController {

    public PolizasGridController() {
    }

    public PolizasGridController(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        new PolizaDetailController(PolizaDetailFrame.class.getName(), null, (BeanVO) persistentObject, true, false);
    }
}
