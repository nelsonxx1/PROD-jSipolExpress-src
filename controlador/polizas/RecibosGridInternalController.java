package controlador.polizas;

import controlador.polizas.recibos.ReciboDetailController;
import controlador.util.DefaultGridInternalController;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import logger.LoggerUtil;
import modelo.HibernateUtil;
import modelo.entidades.polizas.recibos.maestra.Recibo;
import modelo.util.bean.BeanVO;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;
import vista.polizas.recibos.ReciboDetailFrame;

/**
 *
 * @author bc
 */
public class RecibosGridInternalController extends DefaultGridInternalController {

    public RecibosGridInternalController(String classNameModelFullPath, String getMethodName, GridControl miGrid, ArrayList<DefaultGridInternalController> listSubGrids) {
        super(classNameModelFullPath, getMethodName, miGrid, listSubGrids);

        BottomGridController bottomGridController = new BottomGridController();
        miGrid.setBottomGridDataLocator(bottomGridController);
        miGrid.setBottomGridController(bottomGridController);
        miGrid.setLockedRowsOnBottom(1);
    }
    private Recibo reciboBottom;

    class BottomGridController extends GridController implements GridDataLocator {

        public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
            ArrayList<Recibo> al = new ArrayList<Recibo>(1);
            al.add(reciboBottom);
            return new VOListResponse(al, false, al.size());
        }

        @Override
        public Color getBackgroundColor(int row, String attributeName, Object value) {
            return Color.CYAN;
        }
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        new ReciboDetailController(ReciboDetailFrame.class.getName(), miGrid, (BeanVO) persistentObject, true, null, false);
    }

    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        System.out.println("normal22");
        ArrayList al;
        Collection s = getSet();
        if (s != null) {
            al = new ArrayList(s);
        } else {
            al = new ArrayList(0);
        }

        reciboBottom = new Recibo();
        reciboBottom.setPrimaRecibo(0.0d);
        reciboBottom.setPrimaTotal(0.0d);
        reciboBottom.setSumaAsegurada(0.0d);
        reciboBottom.setComision(0.0d);
        reciboBottom.setBono1(0.0d);
        reciboBottom.setBono2(0.0d);
        if(s!=null)
        for (Object object : s) {
            reciboBottom.setPrimaRecibo(reciboBottom.getPrimaRecibo() + ((Recibo) object).getPrimaRecibo());
            reciboBottom.setPrimaTotal(reciboBottom.getPrimaTotal() + ((Recibo) object).getPrimaTotal());
            if (((Recibo) object).getSumaAsegurada() != null) {
                reciboBottom.setSumaAsegurada(reciboBottom.getSumaAsegurada() + ((Recibo) object).getSumaAsegurada());
            }
            if (((Recibo) object).getComision() != null) {
                reciboBottom.setComision(reciboBottom.getComision() + ((Recibo) object).getComision());
            }
            reciboBottom.setBono1(reciboBottom.getBono1() + ((Recibo) object).getBono1());
            reciboBottom.setBono2(reciboBottom.getBono2() + ((Recibo) object).getBono2());
        }
        return new VOListResponse(al, false, al.size());
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
