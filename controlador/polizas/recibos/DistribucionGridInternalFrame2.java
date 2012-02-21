package controlador.polizas.recibos;

import controlador.util.DefaultGridInternalController;
import controlador.util.DefaultGridInternalController2;
import java.util.ArrayList;
import logger.LoggerUtil;
import modelo.HibernateUtil;
import modelo.entidades.polizas.recibos.maestra.Distribucion;
import modelo.entidades.polizas.recibos.maestra.Recibo;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;

/**
 *
 * @author orlandobcrra
 */
public class DistribucionGridInternalFrame2 extends DefaultGridInternalController2 {

    public DistribucionGridInternalFrame2(String classNameModelFullPath, String getMethodName, GridControl miGrid, ArrayList<DefaultGridInternalController> listSubGrids) {
        super(classNameModelFullPath, getMethodName, miGrid, listSubGrids);
    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
        if (beanVO instanceof Recibo) {
            System.out.println("tenga");
            for (Object o : newValueObjects) {
                ((Distribucion) o).setRecibo((Recibo) beanVO);
            }
        }
        return super.insertRecords(rowNumbers, newValueObjects);
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {
        Session s = null;
        try {
            boolean allOk = true;
            s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            for (Object o : persistentObjects) {
                if (getSet() != null) {
                    getSet().remove(o);
                }
                s.delete(o);
                //s.update(beanVO);
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
