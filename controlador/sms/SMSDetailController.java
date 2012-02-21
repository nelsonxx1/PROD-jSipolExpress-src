package controlador.sms;

import controlador.General;
import controlador.util.DefaultDetailFrameController;
import java.util.Date;
import java.util.Set;
import javax.swing.JOptionPane;
import logger.LoggerUtil;
import modelo.Dominios.EstatusSMS;
import modelo.HibernateUtil;
import modelo.entidades.auditoria.Auditable;
import modelo.entidades.auditoria.AuditoriaBasica;
import modelo.entidades.personas.maestra.Persona;
import modelo.entidades.sms.SMS;
import modelo.util.bean.BeanVO;
import org.hibernate.FlushMode;
import org.hibernate.Hibernate;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.java.Consts;
import vista.util.DefaultDetailFrame;

public class SMSDetailController extends DefaultDetailFrameController {

    private Persona persona = null;

    public SMSDetailController(String detailFramePath, GridControl gridControl, BeanVO beanVO, boolean aplicarLogicaNegocio) {
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
        if (beanVO == null) {
            vista.getMainPanel().setMode(Consts.INSERT);
        } else {
            if (((SMS) beanVO).getId() == null) {
                vista.getMainPanel().setMode(Consts.INSERT);
                vista.getMainPanel().getVOModel().setValue("nombre", ((SMS) beanVO).getNombre());
                vista.getMainPanel().pull("nombre");
                vista.getMainPanel().getVOModel().setValue("numero", ((SMS) beanVO).getNumero());
                vista.getMainPanel().pull("numero");
            } else {
                vista.getMainPanel().reload();
                vista.getMainPanel().setMode(Consts.READONLY);
            }
        }
    }

    public SMSDetailController(String detailFramePath, GridControl gridControl, BeanVO beanVO, boolean aplicarLogicaNegocio, Persona persona) {
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
        if (beanVO == null) {
            vista.getMainPanel().setMode(Consts.INSERT);
        } else {
            if (((SMS) beanVO).getId() == null) {
                vista.getMainPanel().setMode(Consts.INSERT);
                vista.getMainPanel().getVOModel().setValue("nombre", ((SMS) beanVO).getNombre());
                vista.getMainPanel().pull("nombre");
                vista.getMainPanel().getVOModel().setValue("numero", ((SMS) beanVO).getNumero());
                vista.getMainPanel().pull("numero");
            } else {
                vista.getMainPanel().reload();
                vista.getMainPanel().setMode(Consts.READONLY);
            }
        }

        this.persona = persona;
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        return new VOResponse(beanVO);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        if (((SMS) newPersistentObject).getNumero().length() < 11) {
            return new ErrorResponse("Numero muy corto");
        }
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            
            if (newPersistentObject instanceof Auditable) {
                AuditoriaBasica ab = new AuditoriaBasica(new Date(), General.usuario.getUserName(), true);
                ((Auditable) newPersistentObject).setAuditoria(ab);
            }
            if (aplicarLogicaNegocio) {
                Response response = logicaNegocioConCambioEnVista(newPersistentObject, false);
                if (response.isError()) {
                    return response;
                }
                newPersistentObject = (ValueObject) ((VOResponse) response).getVo();
            }
            ((SMS) newPersistentObject).setCaracteres(((SMS) newPersistentObject).getTexto().length());
            EstatusSMS estaus = EnviarSMS.enviarSMS((SMS) newPersistentObject);
            JOptionPane.showMessageDialog(MDIFrame.getInstance(), estaus.name());
            ((SMS) newPersistentObject).setEstatus(estaus);
            SMS sms = ((SMS) newPersistentObject);
            s.save(sms);
            if (persona != null) {
                s.refresh(persona);
                Hibernate.initialize(persona.getSmss());
                persona.getSmss().add(sms);
                s.update(persona);
            } else if (((SMS) newPersistentObject).getPersona() != null) {
                Persona p = ((SMS) newPersistentObject).getPersona();
                s.refresh(p);
                Hibernate.initialize(p.getSmss());
                p.getSmss().add(sms);
                s.update(p);
            } else {
                Set<Persona> set = EnviarSMS.getPersonas(((SMS) newPersistentObject).getNumero());
                if (set != null && set.size() > 0) {
                    for (Persona persona1 : set) {
                        s.refresh(persona1);
                        Hibernate.initialize(persona1.getSmss());
                        //s.save(newPersistentObject);
                        persona1.getSmss().add(sms);
                        s.update(persona1);
                        //s.flush();
                    }
                }
//                else {
//                    s.save(newPersistentObject);
//                }
            }
            ///s.save(newPersistentObject);
            t.commit();
            vista.setOwnerVO((BeanVO) newPersistentObject);
            beanVO = (BeanVO) newPersistentObject;
            if (gridControl != null) {
                gridControl.reloadData();
            }
            return new VOResponse(newPersistentObject);
        } catch (ConstraintViolationException ex) {
            System.out.println(ex.getConstraintName());
            return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "insertRecord", ex));
        } catch (Exception ex) {
            return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "insertRecord", ex));
        } finally {
            s.close();
        }
    }
}
