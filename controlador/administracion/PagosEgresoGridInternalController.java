package controlador.administracion;

import controlador.util.DefaultGridInternalController;
import java.util.ArrayList;
import modelo.Dominios.TipoMovimientoCaja;
import modelo.entidades.administracion.Factura2;
import modelo.entidades.administracion.Movimiento;
import modelo.entidades.caja.dominio.TipoMovimiento;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.Response;

/**
 *
 * @author orlandobcrra
 */
public class PagosEgresoGridInternalController extends DefaultGridInternalController {

    public PagosEgresoGridInternalController(String classNameModelFullPath, String getMethodName, GridControl miGrid, ArrayList<DefaultGridInternalController> listSubGrids) {
        super(classNameModelFullPath, getMethodName, miGrid, listSubGrids);
    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
        for (Object object : newValueObjects) {
            ((Movimiento) object).setContribuyente(((Factura2) beanVO).getContribuyente());
            ((Movimiento) object).setRazonSocial(((Factura2) beanVO).getRazonSocial());
            ((Movimiento) object).setRif(((Factura2) beanVO).getRazonSocial().getRif().getRif());
            ((Movimiento) object).setNombre(((Factura2) beanVO).getRazonSocial().getNombreLargo());
            ((Movimiento) object).setDescripcion(((Factura2) beanVO).getDescripcion());
            ((Movimiento) object).setTipoMovimiento(TipoMovimientoCaja.EGRESO);
            ((Movimiento) object).setExtraordinario(false);
        }
        return super.insertRecords(rowNumbers, newValueObjects);
    }
}
