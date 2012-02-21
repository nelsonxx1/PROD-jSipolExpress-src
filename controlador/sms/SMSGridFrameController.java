package controlador.sms;

import controlador.util.DefaultGridFrameController;
import java.awt.Color;
import modelo.Dominios.EstatusSMS;
import modelo.entidades.sms.SMS;
import modelo.util.bean.BeanVO;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author orlandobcrra
 */
public class SMSGridFrameController extends DefaultGridFrameController {

    public SMSGridFrameController(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo);
    }

    @Override
    public Color getBackgroundColor(int row, String attributeName, Object value) {
        if (attributeName.equalsIgnoreCase("estatus") || attributeName.equalsIgnoreCase("id")) {
            Color c = null;
            EstatusSMS est = ((SMS) gridFrame.getGridControl().getVOListTableModel().getObjectForRow(row)).getEstatus();
            if (est != null) {
                switch (est) {
                    case ENVIADO: {
                        c = Color.GREEN;
                        break;
                    }
                    case SIN_NUMERO: {
                        c = Color.YELLOW;
                        break;
                    }
                    case PENDIENTE: {
                        c = super.getBackgroundColor(row, attributeName, value);
                        break;
                    }
                    default: {
                        c = Color.RED;
                        break;
                    }
                }
            }
            if (c != null) {
                return c;
            }
        }
        return super.getBackgroundColor(row, attributeName, value);
    }

    //TODO para el grid de liquidaciones implementar este metodo propio
    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        if (detailFramePath != null) {
            new SMSDetailController(detailFramePath, gridFrame.getGridControl(), (BeanVO) persistentObject, false);
        }
    }
}
