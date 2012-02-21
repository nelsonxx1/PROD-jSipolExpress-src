package controlador.polizas.financiamiento;

import controlador.General;
import controlador.helpcenter.CorreoDetailFrameController;
import controlador.sms.EnviarSMS;
import controlador.util.DefaultDetailFrameController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JOptionPane;
import logger.LoggerUtil;
import modelo.Dominios.EstatusGiro;
import modelo.HibernateUtil;
import modelo.entidades.auditoria.Auditable;
import modelo.entidades.auditoria.AuditoriaBasica;
import modelo.entidades.personas.dominio.TipoPersona;
import modelo.entidades.personas.maestra.Persona;
import modelo.entidades.polizas.financiamiento.Financiamiento;
import modelo.entidades.polizas.financiamiento.Giro;
import modelo.entidades.polizas.financiamiento.LNFinanciamiento;
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
import vista.polizas.financiamiento.FinanciamientoDetailFrame;
import vista.polizas.recibos.BuscarReciboDialog;
import vista.util.DefaultDetailFrame;

/**
 *
 * @author bc
 */
public class FinanciamientoDetailController extends DefaultDetailFrameController implements ActionListener {

    private Recibo r;

    public FinanciamientoDetailController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio,
            Recibo recibo, Boolean nuevo) {
        this.gridControl = gridControl;
        this.beanVO = beanVO;
        this.aplicarLogicaNegocio = aplicarLogicaNegocio;
        try {
            Class<DefaultDetailFrame> t = (Class<vista.util.DefaultDetailFrame>) Class.forName(detailFramePath);
            vista = t.newInstance();
            vista.inicializar(this, true);
            vista.setTitle("Financiamiento: ");
            if (beanVO != null) {
                if (((Financiamiento) beanVO).getPagador() != null) {
                    vista.setTitle(vista.getTitle() + ((Financiamiento) beanVO).getPagador().getNombreLargo());
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
            vista.getMainPanel().getVOModel().setValue("financiadora", ((Financiamiento) beanVO).getFinanciadora());
            vista.getMainPanel().pull("financiadora");
            vista.getMainPanel().getVOModel().setValue("numeroFF", ((Financiamiento) beanVO).getNumeroFF());
            vista.getMainPanel().pull("numeroFF");
        }
        if (recibo != null) {
            r = recibo;
            //System.out.println("tenga el recibo");
            //Set Rs = (Set) vista.getMainPanel().getVOModel().getValue("recibos");
            //Rs.add(recibo);
            //vista.getMainPanel().getVOModel().setValue("recibos", Rs);
            //vista.getMainPanel().pull("recibos");
            vista.getMainPanel().getVOModel().setValue("pagador", recibo.getAsegurado());
            //System.out.println(recibo.getAsegurado());
            vista.getMainPanel().pull("pagador");
        }
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        Session s = null;
        try {
            vista.saveGridsData();
            //s = HibernateUtil.getSessionFactory().openSession(AuditLogInterceptor.INSTANCE2);
            s = HibernateUtil.getSessionFactory().openSession();
            Long cant = (Long) s.createQuery("SELECT count(P) FROM " + Financiamiento.class.getName() + " as P WHERE P.numeroFF=? AND P.financiadora.id=?").
                    setString(0, ((Financiamiento) newPersistentObject).getNumeroFF()).
                    setLong(1, ((Financiamiento) newPersistentObject).getFinanciadora().getId()).
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

                Persona p = ((Financiamiento) newPersistentObject).getPagador();
                p = (Persona) s.load(Persona.class, p.getId());
                Hibernate.initialize(p.getTiposPersona());

                boolean pagador = false;
                for (TipoPersona tp : p.getTiposPersona()) {
                    if (tp.getNombre().toUpperCase().compareTo("PAGADOR") == 0) {
                        pagador = true;
                        break;
                    }
                }

                if (!pagador) {
                    TipoPersona tp = (TipoPersona) s.createQuery("FROM " + TipoPersona.class.getName() + " T WHERE T.idPropio=:idP").
                            setString("idP", "PAG").
                            uniqueResult();
                    p.getTiposPersona().add(tp);
                    s.update(p);
                }

                s.save(newPersistentObject);
                if (r != null) {
                    Financiamiento f = (Financiamiento) newPersistentObject;

                    LNFinanciamiento.actulizarMontoFinanciamiento(f);
                    
                    r.setFinanciamiento(f);
                    r.setFinanciado(true);
                    f.getRecibos().add(r);
                    s.update(r);
                    s.update(f);

                }
                t.commit();
                vista.setOwnerVO((BeanVO) newPersistentObject);
                beanVO = (BeanVO) newPersistentObject;
                vista.reloadGridsData();
                if (gridControl != null) {
                    gridControl.reloadData();
                }
                return new VOResponse(newPersistentObject);
            } else {
                throw new Exception("Número de financiamiento ya existe");
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
            if (!((Financiamiento) oldPersistentObject).getNumeroFF().equalsIgnoreCase(((Financiamiento) persistentObject).getNumeroFF())) {
                Long cant = (Long) s.createQuery("SELECT count(P) FROM " + Financiamiento.class.getName() + " as P WHERE P.numeroFF=? AND P.financiadora.id=?").
                        setString(0, ((Financiamiento) persistentObject).getNumeroFF()).
                        setLong(1, ((Financiamiento) persistentObject).getFinanciadora().getId()).
                        list().get(0);
                if (cant != 0) {
                    throw new Exception("Financiamiento ya existe");
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

            LNFinanciamiento.actulizarMontoFinanciamiento((Financiamiento) persistentObject);
            
            s.update((Financiamiento) persistentObject);
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
    public Response loadData(Class valueObjectClass) {
        if (((Financiamiento) beanVO).getId() != null) {
            Session s = HibernateUtil.getSessionFactory().openSession();
            Financiamiento p = (Financiamiento) s.get(Financiamiento.class, ((Financiamiento) beanVO).getId());
            Hibernate.initialize(p.getGiros());
            Hibernate.initialize(p.getObservaciones());
            Hibernate.initialize(p.getDocumentos());
            Hibernate.initialize(p.getRecibos());
            s.close();
            beanVO = p;
        }
        return new VOResponse(beanVO);
    }

    @Override
    public Response logicaNegocio(ValueObject persistentObject) {
        String errorMsj = "";
        Financiamiento f = (Financiamiento) persistentObject;
        LNFinanciamiento.validarEstatusGiros(f);
        LNFinanciamiento.validarEstatusFinanciamiento(f);
        Session s = null;
        boolean reciboFinanciado = !f.getAnuladoF();
        for (Recibo recibo : f.getRecibos()) {
            if (reciboFinanciado != recibo.getFinanciado()) {
                try {
                    recibo.setFinanciado(reciboFinanciado);
                    if (!reciboFinanciado) {
                        //recibo.setFinanciamiento(null);
                    }
                    s = HibernateUtil.getSessionFactory().openSession();
                    Transaction t = s.beginTransaction();
                    s.update(recibo);
                    t.commit();
                } catch (Exception ex) {
                    LoggerUtil.error(this.getClass(), "logicaNegocio", ex);
                } finally {
                    s.close();
                }
            }
        }
        if (errorMsj.length() > 0) {
            return new ErrorResponse(errorMsj);
        } else {
            return new VOResponse(persistentObject);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Financiamiento fi = (Financiamiento) beanVO;
        Financiamiento f = (Financiamiento) vista.getMainPanel().getVOModel().getValueObject();
        if (e.getSource() == ((FinanciamientoDetailFrame) vista).getReciboNuevoButton()) {
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
                    rr.setFinanciamiento(f);
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
        } else if (e.getSource() == ((FinanciamientoDetailFrame) vista).getSMSButton()) {
            if (f != null) {
                if (f.getRecibos() != null && !f.getRecibos().isEmpty()) {
                    Recibo rr = (Recibo) f.getRecibos().toArray()[0];
                    Long idP = rr.getAsegurado().getId();
                    Session s = null;
                    try {
                        s = HibernateUtil.getSessionFactory().openSession();
                        Persona p = (Persona) s.load(Persona.class, idP);
                        EnviarSMS.crearSMS(p.getNombreLargo(), rr.getPoliza().getProductor().getNombreCorto(), p.getTelefonos(),p);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    } finally {
                        s.close();
                    }
                } else {
                    if (f.getPagador() != null) {
                        Long idP = f.getPagador().getId();
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
        } else if (e.getSource() == ((FinanciamientoDetailFrame) vista).getEmailButton()) {
            String email = f.getPagador().getEmail();
            if (email != null && !email.isEmpty()) {
                new CorreoDetailFrameController(email + "," + General.empresa.getEmail(), General.empresa.getEmail(), "Email", null, false);
            } else {
                JOptionPane.showMessageDialog(MDIFrame.getInstance(), "El pagador no tiene registrado correo electronico...", "Mensaje", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
