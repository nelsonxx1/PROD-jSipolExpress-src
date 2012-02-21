package controlador.polizas;

import java.util.ArrayList;
import java.util.Map;
import logger.LoggerUtil;
import modelo.HibernateUtil;
import modelo.entidades.polizas.maestra.Poliza;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;
import org.openswing.swing.util.server.HibernateUtils;

/**
 *
 * @author orlandobcrra
 */
public class PolizasGridInternalController5 extends GridController implements GridDataLocator {

    private Long contratanteId;
    private GridControl miGrid;

    public PolizasGridInternalController5(Long contratanteId) {
        this.contratanteId = contratanteId;
    }

    public Response loadData(int action,
            int startIndex,
            Map filteredColumns,
            ArrayList currentSortedColumns,
            ArrayList currentSortedVersusColumns,
            Class valueObjectType, Map otherGridParams) {
        Session s = null;
        try {
            String sql = "FROM " + Poliza.class.getName() + " C WHERE C.contratante.id=?";
            SessionFactory sf = HibernateUtil.getSessionFactory();
            s = sf.openSession();
            Response res = HibernateUtils.getAllFromQuery(
                    filteredColumns,
                    currentSortedColumns,
                    currentSortedVersusColumns,
                    valueObjectType,
                    sql,
                    new Object[]{contratanteId},
                    new Type[]{new LongType()},
                    "C",
                    sf,
                    s);
            return res;
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "loadData", ex);
            return new ErrorResponse(ex.getMessage());
        } finally {
            s.close();
        }
    }

    protected void reloadData() {
        if (miGrid != null) {
            miGrid.getReloadButton().doClick();
        }
    }
}
