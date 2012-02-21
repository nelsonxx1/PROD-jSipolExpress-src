package controlador.polizas.financiamiento;

import controlador.util.DefaultGridFrameController;
import java.awt.Color;
import java.util.ArrayList;
import logger.LoggerUtil;
import modelo.Dominios.EstatusFinanciamiento;
import modelo.HibernateUtil;
import modelo.entidades.polizas.financiamiento.Financiamiento;
import modelo.entidades.polizas.recibos.maestra.Recibo;
import modelo.util.bean.BeanVO;
import org.hibernate.Hibernate;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import vista.polizas.financiamiento.FinanciamientoDetailFrame;

/**
 *
 * @author bc
 */
public class FinanciamientoGridController extends DefaultGridFrameController {

    public FinanciamientoGridController() {
    }

    public FinanciamientoGridController(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {

        new FinanciamientoDetailController(FinanciamientoDetailFrame.class.getName(), null, (BeanVO) persistentObject, true, null, false);
//        try {
//            gridFrame.closeFrame();
//        } catch (PropertyVetoException ex) {
//            LoggerUtil.error(this.getClass(), "doubleClick", ex);
//        }}

    }

    @Override
    public Color getBackgroundColor(int row, String attributeName, Object value) {
        if (attributeName.equalsIgnoreCase("estatus")) {
//            System.out.println(value);
            if (value != null) {
                switch (((EstatusFinanciamiento) value)) {
                    case PAGADO:
                        return Color.GREEN;
                    case PENDIENTE:
                        return Color.RED;
                    case SINGIROS:
                        return Color.ORANGE;
                    case ANULADO:
                        return Color.BLUE;
                }
            }

        }
        return super.getBackgroundColor(row, attributeName, value);
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {
        Session s = null;
        try {
            boolean allOk = true;
            s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();

            for (Object o : persistentObjects) {
                Financiamiento f = ((Financiamiento) o);
                f = (Financiamiento) s.get(Financiamiento.class, f.getId());
                Hibernate.initialize(f.getRecibos());
                for (Recibo recibo : f.getRecibos()) {
                    recibo.setFinanciado(false);
                    recibo.setFinanciamiento(null);
                    s.update(recibo);
                }
                s.delete(f);
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
