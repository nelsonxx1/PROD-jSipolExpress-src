package controlador.siniestros;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Map;
import logger.LoggerUtil;
import modelo.Dominios.EstadoSiniestro;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import vista.util.DefaultGridFrame;

/**
 *
 * @author orlandobcrra
 */
public class SiniestrosGridControllerWhitData extends SiniestrosGridController {

    private VOListResponse listResponse;

    public SiniestrosGridControllerWhitData(VOListResponse listResponse, String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo) {
        this.listResponse = listResponse;
        this.detailFramePath = detailFramePath;
        this.claseModeloFullPath = claseModeloFullPath;
        if (titulo == null) {
            titulo = claseModeloFullPath;
        }
        try {
            Class<DefaultGridFrame> t = (Class<vista.util.DefaultGridFrame>) Class.forName(gridFramePath);
            gridFrame = t.newInstance();
            gridFrame.inicializar(this, this, claseModeloFullPath, titulo, true);
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "new", ex);
        }
    }

    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        return listResponse;
    }

    @Override
    public Color getBackgroundColor(int row, String attributeName, Object value) {
        if (attributeName.equalsIgnoreCase("estado")) {
            System.out.println(value);
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
