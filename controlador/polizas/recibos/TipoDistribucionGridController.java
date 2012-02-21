package controlador.polizas.recibos;

import controlador.util.DefaultAllGridFrameController;
import java.util.ArrayList;
import logger.LoggerUtil;
import modelo.HibernateUtil;
import modelo.entidades.polizas.recibos.dominio.DistribucionModelo;
import modelo.entidades.polizas.recibos.dominio.TipoDistribucion;
import modelo.entidades.polizas.recibos.maestra.Distribucion;
import modelo.util.bean.BeanVO;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author orlandobcrra
 */
public class TipoDistribucionGridController extends DefaultAllGridFrameController {

    public TipoDistribucionGridController(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        if (detailFramePath != null) {
            new TipoDistribucionDetailController(detailFramePath, gridFrame.getGridControl(), (BeanVO) persistentObject, false);
        }
    }

        @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {
        Session s = null;
        try {
            boolean allOk = true;
            s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            for (Object o : persistentObjects) {
                TipoDistribucion td=(TipoDistribucion)s.get(TipoDistribucion.class, ((TipoDistribucion)o).getId());
                for (DistribucionModelo d : td.getDistribuciones()) {
                    s.delete(d);
                }
                s.delete(td);
            }
            try {
                t.commit();
            } catch (Exception ex) {
                LoggerUtil.error(this.getClass(), "deleteRecords", ex);
                t.rollback();
                allOk = false;
            }
            if (allOk) {
                return new VOResponse(true);
            } else {
                return new ErrorResponse("delete.constraint.violation");
            }
        } finally {
            s.close();
        }
    }
}
