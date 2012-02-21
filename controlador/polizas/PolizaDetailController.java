package controlador.polizas;

import controlador.General;
import controlador.Principal;
import controlador.helpcenter.CorreoDetailFrameController;
import controlador.sms.EnviarSMS;
import controlador.util.DefaultDetailFrameController;
import java.awt.event.ActionEvent;
import java.util.Date;
import javax.swing.JOptionPane;
import logger.LoggerUtil;
import modelo.HibernateUtil;
import modelo.entidades.auditoria.AuditLogInterceptor;
import modelo.entidades.auditoria.Auditable;
import modelo.entidades.auditoria.AuditoriaBasica;
import modelo.entidades.personas.maestra.Persona;
import modelo.entidades.polizas.maestra.Poliza;
import modelo.util.bean.BeanVO;
import org.hibernate.Hibernate;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.java.Consts;
import vista.polizas.PolizaDetailFrame;
import vista.util.DefaultDetailFrame;

/**
 *
 * @author bc
 */
public class PolizaDetailController extends DefaultDetailFrameController {

    public PolizaDetailController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio, Boolean nuevo) {
        this.gridControl = gridControl;
        this.beanVO = beanVO;
        this.aplicarLogicaNegocio = aplicarLogicaNegocio;
        try {
            Class<DefaultDetailFrame> t = (Class<vista.util.DefaultDetailFrame>) Class.forName(detailFramePath);
            vista = t.newInstance();
            vista.inicializar(this, true);
            vista.setTitle("Poliza: ");
            if (beanVO != null) {
                if (((Poliza) beanVO).getContratante() != null) {
                    vista.setTitle(vista.getTitle() + ((Poliza) beanVO).getContratante().getNombreLargo());
                }
            }
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "new", ex);
        }
        if (nuevo) {
            vista.getMainPanel().setMode(Consts.INSERT);
        } else {
            vista.getMainPanel().setMode(Consts.READONLY);
        }
        if (!nuevo && beanVO != null) {
            vista.getMainPanel().reload();
        }
        if (nuevo && beanVO != null) {
            vista.getMainPanel().getVOModel().setValue("compania", ((Poliza) beanVO).getCompania());
            vista.getMainPanel().pull("compania");
            vista.getMainPanel().getVOModel().setValue("numero", ((Poliza) beanVO).getNumero());
            vista.getMainPanel().pull("numero");
            vista.getMainPanel().getVOModel().setValue("ramoPoliza", ((Poliza) beanVO).getRamoPoliza());
            vista.getMainPanel().pull("ramoPoliza");
        }
    }

//    @Override
//    public boolean validateControl(String attributeName, Object oldValue, Object newValue) {
//        if (attributeName.equals("ramoPoliza")) {
//            if (newValue != null && (((RamoPoliza) newValue).getId() != null || ((RamoPoliza) newValue).getId() != 0)) {
//                //boolean v = !(!Dominios.RamoContable.valueOf(((RamoPoliza) newValue).getRamoContable()).equals(Dominios.RamoContable.VEHICULO) || Dominios.TipoRamo.valueOf(((RamoPoliza) newValue).getTipoRamo()).equals(Dominios.TipoRamo.COLECTIVO));
//                boolean v = !(!Dominios.RamoContable.valueOf(((RamoPoliza) newValue).getRamoContable()).equals(Dominios.RamoContable.VEHICULO));
//                ((PolizaDetailFrame) (vista)).setVisibleVehiculo(v);
//            }
//        }
//        return true;
//    }
    @Override
    public Response loadData(Class valueObjectClass) {
        if (((Poliza) beanVO).getId() != null) {
            Session s = HibernateUtil.getSessionFactory().openSession();
            Poliza p = (Poliza) s.get(Poliza.class, ((Poliza) beanVO).getId());
            Hibernate.initialize(p.getObservaciones());
            Hibernate.initialize(p.getDocumentos());
            Hibernate.initialize(p.getRecibos());
            s.close();
            beanVO = p;
        }
        return new VOResponse(beanVO);
    }

    @Override
    public void createPersistentObject(ValueObject PersistentObject) throws Exception {
        PersistentObject = new Poliza();
        //((Poliza) PersistentObject).setVehiculo(Principal.defaultData.poliza.getVehiculo());
        ((Poliza) PersistentObject).setGrupoPoliza((Principal.defaultData.poliza.getGrupoPoliza()));
//        ((Poliza) PersistentObject).setCompania(Principal.defaultData.poliza.getCompania());
//        ((Poliza) PersistentObject).setProductor(Principal.defaultData.poliza.getProductor());
//        ((Poliza) PersistentObject).setCobrador(Principal.defaultData.poliza.getCobrador());
        vista.getMainPanel().getVOModel().setValueObject(PersistentObject);
    }

    @Override
    public Response logicaNegocio(ValueObject persistentObject) {
        Poliza p = (Poliza) persistentObject;
        persistentObject = p;
        String errorMsj = "";
        if (errorMsj.length() > 0) {
            return new ErrorResponse(errorMsj);
        } else {
            return new VOResponse(persistentObject);
        }
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        Session s = null;
        try {
            vista.saveGridsData();
            //s = HibernateUtil.getSessionFactory().openSession(AuditLogInterceptor.INSTANCE2);
            Poliza p = (Poliza) newPersistentObject;
            s = HibernateUtil.getSessionFactory().openSession();
            Long cant = (Long) s.createQuery("SELECT count(P) FROM " + Poliza.class.getName() + " as P WHERE P.numero=? AND P.compania.id=? AND P.ramoPoliza.id=?").
                    setString(0, p.getNumero()).
                    setLong(1, p.getCompania().getId()).
                    setLong(2, p.getRamoPoliza().getId()).
                    list().get(0);
            if (cant == 0) {
                Transaction t = s.beginTransaction();
                if (newPersistentObject instanceof Auditable) {
                    AuditoriaBasica ab = new AuditoriaBasica(new Date(), General.usuario.getUserName(), true);
                    ((Auditable) newPersistentObject).setAuditoria(ab);
                }
                if (aplicarLogicaNegocio) {
                    Response response = super.logicaNegocioConCambioEnVista(newPersistentObject, false);
                    if (response.isError()) {
                        return response;
                    }
                    newPersistentObject = (ValueObject) ((VOResponse) response).getVo();
                }
                s.save(newPersistentObject);
                t.commit();
                vista.setOwnerVO((BeanVO) newPersistentObject);
                beanVO = (BeanVO) newPersistentObject;
                if (gridControl != null) {
                    gridControl.reloadData();
                }
                return new VOResponse(newPersistentObject);
            } else {
                throw new Exception("Número de póliza ya existe");
            }
        } catch (Exception ex) {
            return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "insertRecord", ex));
        } finally {
            s.close();
        }
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        Session s = null;
        try {
            vista.saveGridsData();
            //s = HibernateUtil.getSessionFactory().openSession(AuditLogInterceptor.INSTANCE2);
            s = HibernateUtil.getSessionFactory().openSession();
            AuditLogInterceptor.INSTANCE2.setSession(s);
            if (!((Poliza) oldPersistentObject).getNumero().equalsIgnoreCase((((Poliza) persistentObject).getNumero()))) {
                Long cant = (Long) s.createQuery("SELECT count(P) FROM " + Poliza.class.getName() + " as P WHERE P.numero=? AND P.compania.id=?").
                        setString(0, ((Poliza) persistentObject).getNumero()).
                        setLong(1, ((Poliza) persistentObject).getCompania().getId()).
                        list().get(0);
                if (cant != 0) {
                    throw new Exception("Póliza ya existe");
                }
            }
            Transaction t = s.beginTransaction();
            if (persistentObject instanceof Auditable) {
                AuditoriaBasica ab = ((Auditable) persistentObject).getAuditoria();
                ab.setFechaUpdate(new Date());
                ab.setUsuarioUpdate(General.usuario.getUserName());
            }
            if (aplicarLogicaNegocio) {
                Response response = logicaNegocioConCambioEnVista(persistentObject, false);

                if (response.isError()) {
                    return response;
                }
                persistentObject = (ValueObject) ((VOResponse) response).getVo();
            }
            s.update(persistentObject);
            t.commit();
            if (gridControl != null) {
                gridControl.reloadData();
            }
            return new VOResponse(persistentObject);
        } catch (Exception ex) {
            return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "updateRecord", ex));
        } finally {
            s.close();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //super.actionPerformed(e);
        Poliza po = (Poliza) beanVO;
        if (e.getSource() == ((PolizaDetailFrame) vista).getSMSButton()) {
            if (po != null && po.getContratante() != null) {
                Long idP = po.getContratante().getId();
                Session s = null;
                try {
                    s = HibernateUtil.getSessionFactory().openSession();
                    Persona p = (Persona) s.load(Persona.class, idP);
                    EnviarSMS.crearSMS(p.getNombreLargo(), po.getProductor().getNombreCorto(), p.getTelefonos(),p);
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    s.close();
                }
            }
        } else if (e.getSource() == ((PolizaDetailFrame) vista).getEmailButton()) {
            String email = po.getContratante().getEmail();
            if (email != null && !email.isEmpty()) {
                new CorreoDetailFrameController(email + "," + General.empresa.getEmail(), General.empresa.getEmail(), "Email", null, false);
            } else {
                JOptionPane.showMessageDialog(MDIFrame.getInstance(), "El contratante no tiene registrado correo electronico...", "Mensaje", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
