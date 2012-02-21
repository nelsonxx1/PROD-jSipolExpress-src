package controlador.polizas.recibos;

import controlador.General;
import controlador.Principal;
import controlador.helpcenter.CorreoDetailFrameController;
import controlador.polizas.financiamiento.FinanciamientoDetailController;
import controlador.sms.EnviarSMS;
import controlador.util.DefaultDetailFrameController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import logger.LoggerUtil;
import modelo.Dominios;
import modelo.HibernateUtil;
import modelo.entidades.auditoria.AuditLogInterceptor;
import modelo.entidades.auditoria.Auditable;
import modelo.entidades.auditoria.AuditoriaBasica;
import modelo.entidades.personas.maestra.Persona;
import modelo.entidades.polizas.financiamiento.Financiamiento;
import modelo.entidades.polizas.maestra.Poliza;
import modelo.entidades.polizas.recibos.dominio.DistribucionModelo;
import modelo.entidades.polizas.recibos.dominio.TipoDistribucion;
import modelo.entidades.polizas.recibos.maestra.Distribucion;
import modelo.entidades.polizas.recibos.maestra.LNRecibo;
import modelo.entidades.polizas.recibos.maestra.Recibo;
import modelo.util.bean.BeanVO;
import org.hibernate.Hibernate;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.java.Consts;
import vista.polizas.financiamiento.FinanciamientoDetailFrame;
import vista.polizas.recibos.ReciboDetailFrame;

/**
 *
 * @author bc
 */
public class ReciboDetailController extends DefaultDetailFrameController implements ActionListener {

    public ReciboDetailController(
            String detailFramePath,
            GridControl gridControl,
            BeanVO beanVO,
            Boolean aplicarLogicaNegocio,
            Poliza poliza,
            Boolean isNuevo) {
        this.gridControl = gridControl;
        this.beanVO = beanVO;
        this.aplicarLogicaNegocio = aplicarLogicaNegocio;
        try {
            //Class<DefaultDetailFrame> t = (Class<vista.util.DefaultDetailFrame>) Class.forName(detailFramePath);
            vista = new ReciboDetailFrame();//t.newInstance();
            vista.inicializar(this, true);
            vista.setTitle("Recibo: ");
            if (beanVO != null) {
                if (((Recibo) beanVO).getAsegurado() != null) {
                    vista.setTitle(vista.getTitle() + ((Recibo) beanVO).getAsegurado().getNombreLargo());
                }
            }
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "new", ex);
        }
        if (!isNuevo) {
            vista.getMainPanel().setMode(Consts.READONLY);
        } else {
            vista.getMainPanel().setMode(Consts.INSERT);
           // ((ReciboDetailFrame)vista).setVisiblePaneles(false);
        }
        if (!isNuevo && beanVO != null) {
            vista.getMainPanel().reload();
        }
        if (poliza != null) {
            vista.getMainPanel().getVOModel().setValue("poliza", poliza);
            vista.getMainPanel().pull("poliza");
            vista.getMainPanel().getVOModel().setValue("cobrador2", poliza.getProductor());
            vista.getMainPanel().pull("cobrador2");
        }
        if (poliza != null && poliza.getRamoPoliza().getTipoRamo().equals(Dominios.TipoRamo.INDIVIDUAL)) {
            vista.getMainPanel().getVOModel().setValue("asegurado", poliza.getContratante());
            vista.getMainPanel().pull("asegurado");
        }
        if (poliza != null) {
            boolean v2 = poliza.getRamoPoliza().getRamoContable().equals(Dominios.RamoContable.VEHICULO);
            ((ReciboDetailFrame) (vista)).setVisibleVehiculo(v2);
            boolean v3 = poliza.getRamoPoliza().getRamoContable().equals(Dominios.RamoContable.PERSONA);
            ((ReciboDetailFrame) (vista)).setVisibleBeneficiarios(v3);
        }
        if (beanVO != null) {
            Poliza p = ((Recibo) beanVO).getPoliza();
            boolean v2 = p.getRamoPoliza().getRamoContable().equals(Dominios.RamoContable.VEHICULO);
            ((ReciboDetailFrame) (vista)).setVisibleVehiculo(v2);
            boolean v3 = p.getRamoPoliza().getRamoContable().equals(Dominios.RamoContable.PERSONA);
            ((ReciboDetailFrame) (vista)).setVisibleBeneficiarios(v3);
        }
        if (isNuevo && beanVO != null) {
            vista.getMainPanel().getVOModel().setValue("numero", ((Recibo) beanVO).getNumero());
            vista.getMainPanel().pull("numero");
            vista.getMainPanel().getVOModel().setValue("poliza", ((Recibo) beanVO).getPoliza());
            vista.getMainPanel().pull("poliza");
        }

        //vista.getMainPanel().getVOModel().setValue("sumaAsegurada", new Double(0));
        //vista.getMainPanel().pull("sumaAsegurada");
    }

    @Override
    public boolean validateControl(String attributeName, Object oldValue, Object newValue) {
        if (attributeName.equals("poliza")) {
            if (newValue != null) {
                boolean v2 = ((Poliza) newValue).getRamoPoliza().getRamoContable().equals(Dominios.RamoContable.VEHICULO);
                ((ReciboDetailFrame) (vista)).setVisibleVehiculo(v2);
                boolean v3 = ((Poliza) newValue).getRamoPoliza().getRamoContable().equals(Dominios.RamoContable.PERSONA);
                ((ReciboDetailFrame) (vista)).setVisibleBeneficiarios(v3);
                Poliza poliza = ((Poliza) newValue);
                if (poliza.getRamoPoliza().getTipoRamo().equals(Dominios.TipoRamo.INDIVIDUAL)) {
                    vista.getMainPanel().getVOModel().setValue("asegurado", poliza.getContratante());
                    vista.getMainPanel().pull("asegurado");
                }
                vista.getMainPanel().getVOModel().setValue("cobrador2", poliza.getProductor());
                vista.getMainPanel().pull("cobrador2");
            }
        } else if (attributeName.equals("tipoDistribucion")) {
            TipoDistribucion td2 = ((TipoDistribucion) newValue);
            if (td2 != null && td2.getId() != null) {
                Session s = HibernateUtil.getSessionFactory().openSession();
                Transaction t = s.beginTransaction();

                TipoDistribucion td = (TipoDistribucion) s.get(TipoDistribucion.class, td2.getId());
                Hibernate.initialize(td.getDistribuciones());

                vista.getMainPanel().getVOModel().setValue("tipoDistribucionNombre", td.getNombre());
                vista.getMainPanel().pull("tipoDistribucionNombre");
                beanVO = ((Recibo) vista.getMainPanel().getVOModel().getValueObject());
                s.update(beanVO);

                t.commit();
                s.close();

                vista.setOwnerVO(beanVO);

                Recibo recibo = (Recibo) beanVO;
                ArrayList<Distribucion> ds = new ArrayList<Distribucion>(0);

                for (DistribucionModelo d : td.getDistribuciones()) {
                    Distribucion dnew = new Distribucion(d.getCobrador(), d.getPorComision(), d.getPorComision2(), d.getPorBono1(), d.getPorBono2(), d.getPorBono3(), d.getPorBono4());
                    if (d.getTipoCobrador().equals(Dominios.TipoCobrador.PRODUCTOR)) {
                        dnew.setCobrador(recibo.getPoliza().getProductor());
                    }
                    if (d.getTipoCobrador().equals(Dominios.TipoCobrador.COBRADOR)) {
                        dnew.setCobrador(recibo.getCobrador2());
                    }
                    //s.save(dnew);
                    //((Recibo) vista.getMainPanel().getVOModel().getValueObject()).getDistribuciones().add(dnew);
                    ds.add(dnew);
                }


                Response r = null;
                try {
                    r = ((ReciboDetailFrame) vista).getGridDistribucionController().insertRecords(null, ds);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                if (r != null && r instanceof VOListResponse) {
                    for (Object object : ((VOListResponse) r).getRows()) {
                        ((Recibo) vista.getMainPanel().getVOModel().getValueObject()).getDistribuciones().add((Distribucion) object);
                    }
                }
                vista.reloadGridsData();
            }
        }
        return true;
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        if (((Recibo) beanVO).getId() != null) {
            Session s = HibernateUtil.getSessionFactory().openSession();
            Recibo p = (Recibo) s.get(Recibo.class, ((Recibo) beanVO).getId());
            Hibernate.initialize(p.getObservaciones());
            Hibernate.initialize(p.getDocumentos());
            Hibernate.initialize(p.getBeneficiarios2());
            Hibernate.initialize(p.getDevoluciones());
            Hibernate.initialize(p.getDistribuciones());
            s.close();
            beanVO = p;
        }
        return new VOResponse(beanVO);
    }

    @Override
    public void createPersistentObject(ValueObject PersistentObject) throws Exception {
        PersistentObject = new Recibo();
        ((Recibo) PersistentObject).setZona(Principal.defaultData.recibo.getZona());
        vista.getMainPanel().getVOModel().setValueObject(PersistentObject);
    }

    @Override
    public Response logicaNegocio(ValueObject persistentObject) {
        Recibo recibo = (Recibo) persistentObject;
        if (!recibo.getPoliza().getRamoPoliza().getRamoContable().equals(Dominios.RamoContable.VEHICULO)) {
            recibo.setVehiculo(Principal.defaultData.poliza.getVehiculo());
        }
        LNRecibo.actualizarEstatus(recibo);
        persistentObject = recibo;
        String errorMsj = LNRecibo.validar(recibo);
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
            Recibo rr = (Recibo) newPersistentObject;
            s = HibernateUtil.getSessionFactory().openSession();
            Long cant = (Long) s.createQuery("SELECT count(P) FROM " + Recibo.class.getName() + " as P WHERE P.numero=? AND P.poliza.numero=? AND P.poliza.compania.id=?").
                    setString(0, rr.getNumero()).
                    setString(1, rr.getPoliza().getNumero()).
                    setLong(2, rr.getPoliza().getCompania().getId()).
                    list().get(0);
            if (cant == 0) {
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
                //System.out.println(((Recibo) newPersistentObject).getFinanciamiento());
                if (((Recibo) newPersistentObject).getFinanciamiento() != null && ((Recibo) newPersistentObject).getFinanciamiento().getId() != null) {
                    ((Recibo) newPersistentObject).setFinanciado(true);
                } else {
                    ((Recibo) newPersistentObject).setFinanciado(false);
                    ((Recibo) newPersistentObject).setFinanciamiento(null);
                }
                s.save(newPersistentObject);                
                t.commit();
                vista.setOwnerVO((BeanVO) newPersistentObject);
                beanVO = (BeanVO) newPersistentObject;
                if (gridControl != null) {
                    gridControl.reloadData();
                }
                //((ReciboDetailFrame)vista).setVisiblePaneles(true);
                return new VOResponse(newPersistentObject);
            } else {
                throw new Exception("Número de poliza-recibo ya existe");
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
            Recibo r3 = (Recibo) oldPersistentObject;
            if ((!r3.getPoliza().getNumero().equalsIgnoreCase(r3.getPoliza().getNumero())) || (!r3.getNumero().equalsIgnoreCase(r3.getNumero()))) {
                Long cant = (Long) s.createQuery("SELECT count(P) FROM " + Recibo.class.getName() + " as P WHERE P.numero=? AND P.poliza.numero=? AND P.poliza.compania.id=?").
                        setString(0, ((Recibo) persistentObject).getNumero()).
                        setString(1, ((Recibo) persistentObject).getPoliza().getNumero()).
                        setLong(2, ((Recibo) persistentObject).getPoliza().getCompania().getId()).
                        list().get(0);
                if (cant != 0) {
                    throw new Exception("Poliza-Recibo ya existe");
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
            if (((Recibo) persistentObject).getFinanciamiento() != null && ((Recibo) persistentObject).getFinanciamiento().getId() != null) {
                ((Recibo) persistentObject).setFinanciado(true);
            } else {
                ((Recibo) persistentObject).setFinanciado(false);
                ((Recibo) persistentObject).setFinanciamiento(null);
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
        Recibo r = ((Recibo) vista.getMainPanel().getVOModel().getValueObject());
        if (e.getSource() == ((ReciboDetailFrame) vista).getFinanciamientoButton()) {
            Financiamiento f = r.getFinanciamiento();
            if (f != null && f.getId() != null) {
                new FinanciamientoDetailController(FinanciamientoDetailFrame.class.getName(), null, f, true, null, false);
            } else {
                new FinanciamientoDetailController(FinanciamientoDetailFrame.class.getName(), null, null, true, (Recibo) vista.getMainPanel().getVOModel().getValueObject(), true);
            }
        } else if (e.getSource() == ((ReciboDetailFrame) vista).getSMSButton()) {
            if (r != null && r.getAsegurado() != null) {
                Long idP = r.getAsegurado().getId();
                Session s = null;
                try {
                    s = HibernateUtil.getSessionFactory().openSession();
                    Persona p = (Persona) s.load(Persona.class, idP);
                    EnviarSMS.crearSMS(p.getNombreLargo(), r.getPoliza().getProductor().getNombreCorto(), p.getTelefonos(),p);
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    s.close();
                }
            }
        } else if (e.getSource() == ((ReciboDetailFrame) vista).getEmailButton()) {
            String email = r.getAsegurado().getEmail();
            if (email != null && !email.isEmpty()) {
                new CorreoDetailFrameController(email + "," + General.empresa.getEmail(), General.empresa.getEmail(), "Email", null, false);
            } else {
                JOptionPane.showMessageDialog(MDIFrame.getInstance(), "El asegurado no tiene registrado correo electronico...", "Mensaje", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
