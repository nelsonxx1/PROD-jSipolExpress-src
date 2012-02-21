package controlador.sms;

import controlador.General;
import controlador.util.DefaultGridInternalController;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Map;
import logger.LoggerUtil;
import modelo.Dominios.EstatusSMS;
import modelo.HibernateUtil;
import modelo.entidades.personas.maestra.Persona;
import modelo.entidades.sms.SMS;
import modelo.util.bean.BeanVO;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.ValueObject;
import vista.sms.SMSDetailFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.util.server.HibernateUtils;
import org.hibernate.type.Type;
import org.openswing.swing.message.receive.java.ErrorResponse;

/**
 *
 * @author orlandobcrra
 */
public class SMSInternalGridController extends DefaultGridInternalController {

    public SMSInternalGridController(String classNameModelFullPath, String getMethodName, GridControl miGrid, ArrayList<DefaultGridInternalController> listSubGrids) {
        super(classNameModelFullPath, getMethodName, miGrid, listSubGrids);
    }

    @Override
    public Color getBackgroundColor(int row, String attributeName, Object value) {
        if (/*attributeName.equalsIgnoreCase("nombre") ||*/attributeName.equalsIgnoreCase("estatus")) {
            Color c = null;
            EstatusSMS est = ((EstatusSMS) value);
            if (est != null) {
                switch (est) {
                    case ENVIADO: {
                        c = Color.GREEN;
                        break;
                    }
                    case SIN_NUMERO: {
                        c = Color.YELLOW;
                        break;
                    }
                    case PENDIENTE: {
                        c = super.getBackgroundColor(row, attributeName, value);
                        break;
                    }
                    default: {
                        c = Color.RED;
                        break;
                    }
                }
            }
            if (c != null) {
                return c;
            }
        }
        return super.getBackgroundColor(row, attributeName, value);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        new SMSDetailController(SMSDetailFrame.class.getName(), miGrid, (BeanVO) persistentObject, false);
    }

//    public Response loadData(int action,
//            int startIndex,
//            Map filteredColumns,
//            ArrayList currentSortedColumns,
//            ArrayList currentSortedVersusColumns,
//            Class valueObjectType,
//            Map otherGridParams) {
//        ArrayList al;
//        Collection s = getSet();
//        if (s != null) {
//            al = new ArrayList(s);
//        } else {
//            al = new ArrayList(0);
//        }
//        return new VOListResponse(al, false, al.size());
//    }
    
    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns,
            ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        Session s = null;
        try {
            String sql = "SELECT DISTINCT S FROM " + Persona.class.getName() + " C "+"left join C.smss S WHERE C.id="+((Persona)beanVO).getId();
            SessionFactory sf = HibernateUtil.getSessionFactory();
            s = sf.openSession();
            Response res = HibernateUtils.getBlockFromQuery(
                    action,
                    startIndex,
                    General.licencia.getBlockSize(),
                    filteredColumns,
                    currentSortedColumns,
                    currentSortedVersusColumns,
                    valueObjectType,
                    sql,
                    new Object[0],
                    new Type[0],
                    "S",
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
}
