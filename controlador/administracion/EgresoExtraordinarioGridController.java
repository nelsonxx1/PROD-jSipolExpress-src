package controlador.administracion;

import controlador.util.DefaultGridFrameController;
import java.util.ArrayList;
import java.util.Map;
import modelo.entidades.administracion.Movimiento;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.send.java.FilterWhereClause;

/**
 *
 * @author orlandobcrra
 */
public class EgresoExtraordinarioGridController extends DefaultGridFrameController {

    public EgresoExtraordinarioGridController(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo);
    }

    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        filteredColumns.put(
                "extraordinario",
                new FilterWhereClause[]{
                    new FilterWhereClause("extraordinario", "=", true),
                    null
                });
        return super.loadData(action, startIndex, filteredColumns, currentSortedColumns, currentSortedVersusColumns, valueObjectType, otherGridParams);
    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
        for (Object object : newValueObjects) {
            ((Movimiento)object).setContribuyente(((Movimiento)object).getCuenta().getPersona());
            System.out.println(((Movimiento)object).getCuenta().getPersona().getNombreCorto());
        }
        return super.insertRecords(rowNumbers, newValueObjects);
    }


}
