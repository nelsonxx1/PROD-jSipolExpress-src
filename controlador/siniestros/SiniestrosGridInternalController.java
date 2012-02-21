package controlador.siniestros;

import controlador.util.DefaultGridInternalController;
import java.util.ArrayList;
import logger.LoggerUtil;
import modelo.HibernateUtil;
import modelo.util.bean.BeanVO;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import vista.siniestros.SiniestroDetailFrame;

/**
 *
 * @author bc
 */
public class SiniestrosGridInternalController extends DefaultGridInternalController {

    public SiniestrosGridInternalController(String classNameModelFullPath, String getMethodName, GridControl miGrid, ArrayList<DefaultGridInternalController> listSubGrids) {
        super(classNameModelFullPath, getMethodName, miGrid, listSubGrids);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        new SiniestroDetailController(SiniestroDetailFrame.class.getName(), miGrid, (BeanVO) persistentObject, false);
//        new ReciboDetailController(ReciboDetailFrame.class.getName(), miGrid, (BeanVO) persistentObject, true, null, false);
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {
        Session s = null;
        try {
            boolean allOk = true;
            s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            for (Object o : persistentObjects) {
                s.delete(o);
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
