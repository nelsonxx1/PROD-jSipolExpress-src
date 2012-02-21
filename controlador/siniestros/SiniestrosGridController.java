package controlador.siniestros;

import controlador.util.DefaultGridFrameController;
import java.awt.Color;
import modelo.Dominios.EstadoSiniestro;
import modelo.util.bean.BeanVO;
import org.openswing.swing.message.receive.java.ValueObject;
import vista.siniestros.SiniestroDetailFrame;

/**
 *
 * @author bc
 */
public class SiniestrosGridController extends DefaultGridFrameController {

    public SiniestrosGridController() {
    }

    
    public SiniestrosGridController(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        new SiniestroDetailController(SiniestroDetailFrame.class.getName(), null, (BeanVO) persistentObject, true);
    }

    @Override
    public Color getBackgroundColor(int row, String attributeName, Object value) {
        if (attributeName.equalsIgnoreCase("estado")) {
            if (value != null) {
                switch (((EstadoSiniestro) value)) {
                    case ABIERTO:
                        return Color.GREEN;
                    case CERRADO:
                        return Color.RED;
                }
            }
        }
        return super.getBackgroundColor(row, attributeName, value);
    }
}
