package controlador.sms;

import controlador.util.DefaultGridFrameController;
import modelo.util.bean.BeanVO;
import org.openswing.swing.message.receive.java.ValueObject;


/**
 *
 * @author orlandobcrra
 * @author adrian.and1
 */
public class SMSEntradaGridFrameController extends DefaultGridFrameController {

    public SMSEntradaGridFrameController(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo);
    }


    //TODO crear el visor de mensaje como si fuera para mi
    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        if (detailFramePath != null) {
            new SMSEntradaDetailController(detailFramePath, gridFrame.getGridControl(), (BeanVO) persistentObject, false);
        }
    }


}
