/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.facturacion;

import controlador.General; import controlador.Principal;
import controlador.util.DefaultDetailFrameController;
import modelo.entidades.facturacion.DetalleLiquidacion;
import modelo.entidades.facturacion.LiquidacionCompania;
import org.hibernate.Hibernate;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.java.Consts;
import vista.util.DefaultDetailFrame;
import java.util.Date;
import logger.LoggerUtil;
import modelo.HibernateUtil;
import modelo.entidades.auditoria.AuditoriaBasica;
import modelo.entidades.auditoria.Auditable;
import modelo.util.bean.BeanVO;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

/**
 *
 * @author bc
 */
public class LiquidacionDetailFrameController extends DefaultDetailFrameController {

    public LiquidacionDetailFrameController(String detailFramePath, GridControl gridControl, BeanVO beanVO, boolean aplicarLogicaNegocio) {
        this.gridControl = gridControl;
        this.beanVO = beanVO;
        this.aplicarLogicaNegocio = aplicarLogicaNegocio;
        try {
            Class<DefaultDetailFrame> t = (Class<vista.util.DefaultDetailFrame>) Class.forName(detailFramePath);
            vista = t.newInstance();
            vista.inicializar(this, true);
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "new", ex);
        }
        if (beanVO != null) {
            vista.getMainPanel().reload();
            vista.getMainPanel().setMode(Consts.READONLY);
        } else {
            vista.getMainPanel().setMode(Consts.INSERT);
            LiquidacionCompania lc = new LiquidacionCompania();
            Session s = null;
            try {
                String sql = "FROM " + DetalleLiquidacion.class.getName() + " C ";
                SessionFactory sf = HibernateUtil.getSessionFactory();
                s = sf.openSession();
                for (Object object : s.createQuery(sql).list()) {
                    lc.getDetalle().add((DetalleLiquidacion) object);
                }
            } catch (Exception ex) {
                LoggerUtil.error(this.getClass(), "loadData", ex);
                ex.printStackTrace();
            } finally {
                s.close();
            }
            vista.getMainPanel().pull("detalle");
        }
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        System.out.println("load data liquidacion");
        if (((LiquidacionCompania) beanVO).getId() != null) {
            Session s = HibernateUtil.getSessionFactory().openSession();
            LiquidacionCompania p = (LiquidacionCompania) s.get(LiquidacionCompania.class, ((LiquidacionCompania) beanVO).getId());
            Hibernate.initialize(p.getObservaciones());
            Hibernate.initialize(p.getDocumentos());
            Hibernate.initialize(p.getDetalle());
            s.close();
            beanVO = p;
        }
        return new VOResponse(beanVO);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        Session s = null;
        try {
            vista.saveGridsData();
            //s = HibernateUtil.getSessionFactory().openSession(AuditLogInterceptor.INSTANCE2);
            s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            if (newPersistentObject instanceof Auditable) {
                AuditoriaBasica ab = new AuditoriaBasica(new Date(), General.usuario.getUserName(), true);
                ((Auditable) newPersistentObject).setAuditoria(ab);
            }
            if (aplicarLogicaNegocio) {
                Response response = logicaNegocioConCambioEnVista(newPersistentObject,false);
                if (response.isError()) {
                    return response;
                }
                newPersistentObject = (ValueObject) ((VOResponse) response).getVo();
            }
            LiquidacionCompania lc = (LiquidacionCompania) newPersistentObject;
            lc.getPago().setCuentaBancaria(null);
            lc.getPago().setDocumentoPago(null);
            s.save(newPersistentObject);
            t.commit();
            vista.setOwnerVO((BeanVO) newPersistentObject);
            beanVO = (BeanVO) newPersistentObject;
            if (gridControl != null) {
                gridControl.reloadData();
            }
            return new VOResponse(newPersistentObject);
        } catch (Exception ex) {
            return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "insertRecord", ex));
        } finally {
            s.close();
        }
    }

    @Override
    public Response logicaNegocio(ValueObject persistentObject) {
        String errorMsj = "";
        if (errorMsj.length() > 0) {
            return new ErrorResponse(errorMsj);
        } else {
            return new VOResponse(persistentObject);
        }
    }
}
