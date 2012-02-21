package controlador.ordenes;

import controlador.General;
import controlador.helpcenter.CorreoDetailFrameController;
import controlador.sms.EnviarSMS;
import controlador.util.DefaultDetailFrameController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import logger.LoggerUtil;
import modelo.HibernateUtil;
import modelo.entidades.auditoria.Auditable;
import modelo.entidades.auditoria.AuditoriaBasica;
import modelo.entidades.ordenes.maestra.OrdenDePago;
import modelo.entidades.personas.dominio.TipoPersona;
import modelo.entidades.personas.maestra.Persona;
import modelo.entidades.polizas.recibos.maestra.Recibo;
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
import vista.ordenes.OrdenPagoDetailFrame;
import vista.polizas.recibos.BuscarReciboDialog;
import vista.util.DefaultDetailFrame;

/**
 *
 * @author bc
 */
public class OrdenPagoDetailController extends DefaultDetailFrameController implements ActionListener {

    private Recibo r;

    public OrdenPagoDetailController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio,
            Boolean nuevo) {
        this.gridControl = gridControl;
        this.beanVO = beanVO;
        this.aplicarLogicaNegocio = aplicarLogicaNegocio;
        try {
            Class<DefaultDetailFrame> t = (Class<vista.util.DefaultDetailFrame>) Class.forName(detailFramePath);
            vista = t.newInstance();
            vista.inicializar(this, true);
            vista.setTitle("Orden de Pago: ");
            if (beanVO != null) {
                if (((OrdenDePago) beanVO).getBeneficiario() != null) {
                    vista.setTitle(vista.getTitle() + ((OrdenDePago) beanVO).getBeneficiario().getNombreLargo());
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
                Response response = logicaNegocioConCambioEnVista(newPersistentObject, false);
                if (response.isError()) {
                    return response;
                }
                newPersistentObject = (ValueObject) ((VOResponse) response).getVo();
            }

            Persona p = ((OrdenDePago) newPersistentObject).getBeneficiario();
            p = (Persona) s.load(Persona.class, p.getId());
            Hibernate.initialize(p.getTiposPersona());

            //actualizar tipo de persona
//            boolean pagador = false;
//            for (TipoPersona tp : p.getTiposPersona()) {
//                if (tp.getNombre().toUpperCase().compareTo("PAGADOR") == 0) {
//                    pagador = true;
//                    break;
//                }
//            }
//
//            if (!pagador) {
//                TipoPersona tp = (TipoPersona) s.createQuery("FROM " + TipoPersona.class.getName() + " T WHERE T.idPropio=:idP").
//                        setString("idP", "PAG").
//                        uniqueResult();
//                p.getTiposPersona().add(tp);
//                s.update(p);
//            }

            s.save(newPersistentObject);
            t.commit();
            vista.setOwnerVO((BeanVO) newPersistentObject);
            beanVO = (BeanVO) newPersistentObject;
            vista.reloadGridsData();
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
    public Response loadData(Class valueObjectClass) {
        if (((OrdenDePago) beanVO).getId() != null) {
            Session s = HibernateUtil.getSessionFactory().openSession();
            OrdenDePago p = (OrdenDePago) s.get(OrdenDePago.class, ((OrdenDePago) beanVO).getId());
            Hibernate.initialize(p.getDetalle());
            Hibernate.initialize(p.getObservaciones());
            Hibernate.initialize(p.getDocumentos());
            Hibernate.initialize(p.getRecibos());
            s.close();
            beanVO = p;
        }
        return new VOResponse(beanVO);
    }

    @Override
    public Response logicaNegocioDespuesSave(ValueObject persistentObject) {
        String errorMsj = "";
        OrdenDePago f = (OrdenDePago) persistentObject;
        Calendar c = Calendar.getInstance();
        f.setNumeroO("" + c.get(Calendar.YEAR) + "-" + f.getId());
        persistentObject = f;
        if (errorMsj.length() > 0) {
            return new ErrorResponse(errorMsj);
        } else {
            return new VOResponse(persistentObject);
        }
    }

    @Override
    public Response logicaNegocio(ValueObject persistentObject) {
        String errorMsj = "";
        OrdenDePago f = (OrdenDePago) persistentObject;
        Calendar c = Calendar.getInstance();

        f.setNumeroO("" + c.get(Calendar.YEAR) + "-");
//        Session s = null;
//        boolean reciboFinanciado = !f.getAnuladoF();
//        for (Recibo recibo : f.getRecibos()) {
//            if (reciboFinanciado != recibo.getFinanciado()) {
//                try {
//                    recibo.setFinanciado(reciboFinanciado);
//                    s = HibernateUtil.getSessionFactory().openSession();
//                    Transaction t = s.beginTransaction();
//                    s.update(recibo);
//                    t.commit();
//                } catch (Exception ex) {
//                    LoggerUtil.error(this.getClass(), "logicaNegocio", ex);
//                } finally {
//                    s.close();
//                }
//            }
//        }
        if (errorMsj.length() > 0) {
            return new ErrorResponse(errorMsj);
        } else {
            return new VOResponse(persistentObject);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Financiamiento fi = (Financiamiento) beanVO;
        OrdenDePago f = (OrdenDePago) vista.getMainPanel().getVOModel().getValueObject();
        if (e.getSource() == ((OrdenPagoDetailFrame) vista).getReciboNuevoButton()) {
            BuscarReciboDialog br = new BuscarReciboDialog(null);
            br.setVisible(true);
            Recibo rr = br.getRecibo();
            if (rr != null && rr.getId() != null) {
                if (f.getRecibos().contains(rr)) {
                    JOptionPane.showMessageDialog(MDIFrame.getInstance(), "Este recibo ya estaba incluido en este financiamiento", "Mensaje", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if (rr.getFinanciado()) {
                    int res = JOptionPane.showConfirmDialog(MDIFrame.getInstance(), "¿Este recibo esta registrado en otro financiamiento (Numero: " + rr.getFinanciamiento().getNumeroFF() + ") desea agregarlo a este financiamiento y removerlo del otro?", "Mensaje", JOptionPane.YES_NO_OPTION);
                    if (res == JOptionPane.NO_OPTION) {
                        return;
                    }
                }
                Session s = null;
                try {
                    s = HibernateUtil.getSessionFactory().openSession();
                    Transaction t = s.beginTransaction();

//                    if (rr.getFinanciamiento() != null) {
//                        rr.getFinanciamiento().getRecibos().remove(rr);
//                        s.update(rr.getFinanciamiento());
//                        rr.setFinanciamiento(null);
//                        s.update(rr);
//                    }

                    f.getRecibos().add(rr);
                    rr.setOrden(f);
                    rr.setFinanciado(true);

                    s.update(rr);
                    s.update(f);
                    vista.reloadGridsData();
                    t.commit();
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    s.close();
                }
            }
        } else if (e.getSource() == ((OrdenPagoDetailFrame) vista).getSMSButton()) {
            if (f != null) {
                if (f.getRecibos() != null && !f.getRecibos().isEmpty()) {
                    Long idP = f.getBeneficiario().getId();
                    Session s = null;
                    try {
                        s = HibernateUtil.getSessionFactory().openSession();
                        Persona p = (Persona) s.load(Persona.class, idP);
                        EnviarSMS.crearSMS(p.getNombreLargo(), "PRODUCTOR", p.getTelefonos(),p);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        s.close();
                    }
                } else {
                    if (f.getBeneficiario() != null) {
                        Long idP = f.getBeneficiario().getId();
                        Session s = null;
                        try {
                            s = HibernateUtil.getSessionFactory().openSession();
                            Persona p = (Persona) s.load(Persona.class, idP);
                            EnviarSMS.crearSMS(p.getNombreLargo(), "PRODUCTOR", p.getTelefonos(),p);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        } finally {
                            s.close();
                        }
                    }
                }
            }
        } else if (e.getSource() == ((OrdenPagoDetailFrame) vista).getEmailButton()) {
            String email = f.getBeneficiario().getEmail();
            if (email != null && !email.isEmpty()) {
                new CorreoDetailFrameController(email + "," + General.empresa.getEmail(), General.empresa.getEmail(), "Email", null, false);
            } else {
                JOptionPane.showMessageDialog(MDIFrame.getInstance(), "El beneficiario no tiene registrado correo electronico...", "Mensaje", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
