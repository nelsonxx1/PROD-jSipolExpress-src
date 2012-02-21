package controlador.polizas.recibos;

import controlador.General;
import controlador.util.DefaultGridInternalController;
import java.util.ArrayList;
import java.util.Date;
import logger.LoggerUtil;
import modelo.HibernateUtil;
import modelo.entidades.auditoria.Auditable;
import modelo.entidades.auditoria.AuditoriaBasica;
import modelo.entidades.polizas.recibos.devolucion.maestra.Devolucion;
import modelo.entidades.polizas.recibos.maestra.Recibo;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author bc
 */
public class DevolucionesGridInternalController extends DefaultGridInternalController {

    public DevolucionesGridInternalController(String classNameModelFullPath, String getMethodName, GridControl miGrid, ArrayList<DefaultGridInternalController> listSubGrids) {
        super(classNameModelFullPath, getMethodName, miGrid, listSubGrids);
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            for (Object o : persistentObjects) {
                if (getSet() != null) {
                    getSet().remove(o);
                }
            }
            if (getSet().size() == 0) {
                ((Recibo) beanVO).setDevolucion(false);
            }
            s.update(beanVO);
            t.commit();
            return new VOResponse(true);
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "deleteRecords", ex);
            return new ErrorResponse(ex.getMessage());
        } finally {
            s.close();
        }
    }

    private Response validarInsert(ArrayList newValueObjects) {
        double primaD = 0, comisionD = 0;
        String m = "";
        for (Object object : newValueObjects) {
            primaD += ((Devolucion) object).getPrimaDevuelta();
            if (((Devolucion) object).getPrimaDevuelta() < ((Devolucion) object).getMontoDevuelto()) {
                m += ("El monto a devolver al cliente no puede ser mayor al la prima devuelata.\n");
            }
        }
        for (Object object : getSet()) {
            primaD += ((Devolucion) object).getPrimaDevuelta();
        }
        if (primaD > ((Recibo) beanVO).getPrimaRecibo()) {
            m += ("El monto de las devoluciones no puede ser mayor al total de la prima.\n");
        }
        for (Object object : newValueObjects) {
            comisionD += ((Devolucion) object).getExtornoComision();
            if (((Devolucion) object).getExtornoComision() < ((Devolucion) object).getExtornoCobrador()) {
                m += ("El monto de comision devuelta por el cobrador no puede ser mayor al extorno de la devolucion.\n");
            }
        }
        for (Object object : getSet()) {
            comisionD += ((Devolucion) object).getExtornoComision();
        }
        if (comisionD > ((Recibo) beanVO).getComision()) {
            m += ("El extorno de las devoluciones no puede ser mayor al total de la comision.\n");
        }
        if (m.length() > 0) {
            return new ErrorResponse(m);
        } else {
            return null;
        }
    }

    private Response validarUpdate(ArrayList newValueObjects) {
        double primaD = 0, comisionD = 0;
        String m = "";
        for (Object object : newValueObjects) {
            primaD += ((Devolucion) object).getPrimaDevuelta();
            if (((Devolucion) object).getPrimaDevuelta() < ((Devolucion) object).getMontoDevuelto()) {
                m += ("El monto a devolver al cliente no puede ser mayor al la prima devuelata.\n");
            }
        }
        for (Object object : getSet()) {
            if (newValueObjects.size() > 0 && ((Devolucion) object).getId() != ((Devolucion) newValueObjects.get(0)).getId()) {
                primaD += ((Devolucion) object).getPrimaDevuelta();
            }
        }
        if (primaD > ((Recibo) beanVO).getPrimaRecibo()) {
            m += ("El monto de las devoluciones no puede ser mayor al total de la prima.\n");
        }
        for (Object object : newValueObjects) {
            comisionD += ((Devolucion) object).getExtornoComision();
            if (((Devolucion) object).getExtornoComision() < ((Devolucion) object).getExtornoCobrador()) {
                m += ("El monto de comision devuelta por el cobrador no puede ser mayor al extorno de la devolucion.\n");
            }
        }
        for (Object object : getSet()) {
            if (newValueObjects.size() > 0 && ((Devolucion) object).getId() != ((Devolucion) newValueObjects.get(0)).getId()) {
                comisionD += ((Devolucion) object).getExtornoComision();
            }
        }
        if (comisionD > ((Recibo) beanVO).getComision()) {
            m += ("El extorno de las devoluciones no puede ser mayor al total de la comision.\n");
        }
        if (m.length() > 0) {
            return new ErrorResponse(m);
        } else {
            return null;
        }
    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
        System.out.println("insert");
        Session s = null;
        if (getSet() != null) {
            Response r = validarInsert(newValueObjects);
            if (r != null) {
                return r;
            }
            for (Object object : newValueObjects) {
                ValueObject o = (ValueObject) object;
                AuditoriaBasica ab = new AuditoriaBasica(new Date(), General.usuario.getUserName(), true);
                if (o instanceof Auditable) {
                    ((Auditable) o).setAuditoria(ab);
                }
                getSet().add(o);
            }
            try {
                s = HibernateUtil.getSessionFactory().openSession();
                Transaction t = s.beginTransaction();
                ((Recibo) beanVO).setDevolucion(true);
                if (!((Recibo) beanVO).getFinanciado()) {
                    ((Recibo) beanVO).setFinanciamiento(null);
                }
                s.update(beanVO);
                selectedCell(0, 0, null, (ValueObject) newValueObjects.get(0));
                t.commit();
                return new VOListResponse(newValueObjects, false, newValueObjects.size());
            } catch (Exception ex) {
                for (Object o : newValueObjects) {
                    getSet().remove(o);
                }
                return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "insertRecords", ex));
            } finally {
                s.close();
            }
        } else {
            return new ErrorResponse("Primero tienes que guardar el Registro Principal");
        }
    }

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects, ArrayList persistentObjects) throws Exception {
        Response r = validarUpdate(persistentObjects);
        if (r != null) {
            return r;
        }
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            for (Object object : persistentObjects) {
                ValueObject o = (ValueObject) object;
                if (o instanceof Auditable) {
                    AuditoriaBasica ab = ((Auditable) o).getAuditoria();
                    ab.setFechaUpdate(new Date());
                    ab.setUsuarioUpdate(General.usuario.getUserName());
                }
                s.update(o);
            }
            t.commit();
            return new VOListResponse(persistentObjects, false, persistentObjects.size());
        } catch (Exception ex) {
            return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "insertRecords", ex));
        } finally {
            s.close();
        }
    }
}
