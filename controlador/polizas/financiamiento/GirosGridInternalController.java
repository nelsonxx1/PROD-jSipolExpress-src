package controlador.polizas.financiamiento;

import controlador.util.DefaultGridInternalController;
import modelo.entidades.polizas.financiamiento.Giro;

import controlador.General;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import logger.LoggerUtil;
import modelo.Dominios;
import modelo.HibernateUtil;
import modelo.entidades.auditoria.Auditable;
import modelo.entidades.auditoria.AuditoriaBasica;
import modelo.entidades.polizas.financiamiento.Financiamiento;
import modelo.entidades.polizas.financiamiento.LNFinanciamiento;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author bc
 */
public class GirosGridInternalController extends DefaultGridInternalController {

    public GirosGridInternalController(String classNameModelFullPath, String getMethodName, GridControl miGrid, ArrayList<DefaultGridInternalController> listSubGrids) {
        super(classNameModelFullPath, getMethodName, miGrid, listSubGrids);
    }

    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        ArrayList al;
        Collection s = getSet();
        if (s != null) {
            al = new ArrayList(s);
            for (int i = 0; i < al.size(); i++) {
                Object o = al.get(i);
                Giro g = (Giro) o;
                g.setNumero(i + 1);
            }
        } else {
            al = new ArrayList(0);
        }
        return new VOListResponse(al, false, al.size());
    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
        Session s = null;
        if (getSet() != null) {
            for (Object object : newValueObjects) {
                ValueObject o = (ValueObject) object;
                AuditoriaBasica ab = new AuditoriaBasica(new Date(), General.usuario.getUserName(), true);
                if (o instanceof Auditable) {
                    ((Auditable) o).setAuditoria(ab);
                }
                Giro g = ((Giro) object);
                if (g.getFechaCobro() != null) {
                    g.setEstatus(Dominios.EstatusGiro.COBRADO_GIRO);
                }else{
                    g.setEstatus(Dominios.EstatusGiro.PENDIENTE);
                }
                getSet().add(o);
            }

            LNFinanciamiento.validarEstatusFinanciamiento((Financiamiento)beanVO);
            LNFinanciamiento.actulizarMontoFinanciamiento((Financiamiento)beanVO);
            try {
                s = HibernateUtil.getSessionFactory().openSession();
                Transaction t = s.beginTransaction();
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
                Giro g = ((Giro) object);
                if (g.getFechaCobro() != null) {
                    g.setEstatus(Dominios.EstatusGiro.COBRADO_GIRO);                    
                }                
                s.update(o);
            }
            
            LNFinanciamiento.validarEstatusFinanciamiento((Financiamiento)beanVO);
            LNFinanciamiento.validarEstatusGiros((Financiamiento)beanVO);
            s.update(beanVO);
            t.commit();
            return new VOListResponse(persistentObjects, false, persistentObjects.size());
        } catch (Exception ex) {
            return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "insertRecords", ex));
        } finally {
            s.close();
        }
    }
}
