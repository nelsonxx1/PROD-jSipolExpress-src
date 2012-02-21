package controlador.sms.masa;

import controlador.util.DefaultGridFrameController;
import java.beans.PropertyVetoException;
import logger.LoggerUtil;
import modelo.util.bean.BeanVO;
import org.openswing.swing.message.receive.java.ValueObject;
import vista.sms.SMSMasaDetailFrame;

/**
 *
 * @author orlandobcrra
 */
public class MasaGridFrameController extends DefaultGridFrameController {

    public MasaGridFrameController(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        new MasaDetailController(null, "SMS Masivo", SMSMasaDetailFrame.class.getName(), null, (BeanVO) persistentObject, false);
        try {
            gridFrame.closeFrame();
        } catch (PropertyVetoException ex) {
            LoggerUtil.error(this.getClass(), "doubleClick", ex);
        }
    }
}
