package controlador.sms.masa;

import controlador.General;
import controlador.sms.EnviarSMS;
import controlador.util.DefaultDetailFrameController;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.Map;
import javax.swing.JOptionPane;
import logger.LoggerUtil;
import modelo.Dominios.TipoSMS;
import modelo.HibernateUtil;
import modelo.entidades.auditoria.Auditable;
import modelo.entidades.auditoria.AuditoriaBasica;
import modelo.entidades.sms.SMSMasa;
import modelo.entidades.sms.SMSPreEscrito;
import modelo.util.bean.BeanVO;
import org.hibernate.Hibernate;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.java.Consts;
import vista.reportes.EsperaDialog;
import vista.sms.DialogEnviarMensajes;
import vista.sms.SMSMasaDetailFrame;
import vista.util.DefaultDetailFrame;

/**
 *
 * @author orlandobcrra
 */
public class MasaDetailController extends DefaultDetailFrameController {

    private TipoSMS tipoSMS;
    private String sql;
    private Map filteredColumns;
    private DialogEnviarMensajes enviarSMSs;
    private EsperaDialog es;
    private Thread hilo;
    private ValueObject newPersistentObject;
    
    public MasaDetailController(String sql, Map filteredColumns, TipoSMS tipoSMS, String titulo, String detailFramePath, GridControl gridControl, BeanVO beanVO, boolean aplicarLogicaNegocio) {
        this(tipoSMS, titulo, detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
        this.sql = sql;
        this.filteredColumns = filteredColumns;
        this.es= new EsperaDialog("Ejemplo", "Holaaa", null, false);
    }

    public MasaDetailController(TipoSMS tipoSMS, String titulo, String detailFramePath, GridControl gridControl, BeanVO beanVO, boolean aplicarLogicaNegocio) {
        this.gridControl = gridControl;
        this.beanVO = beanVO;
        this.aplicarLogicaNegocio = aplicarLogicaNegocio;
        this.tipoSMS = tipoSMS;
        
        try {
            Class<DefaultDetailFrame> t = (Class<vista.util.DefaultDetailFrame>) Class.forName(detailFramePath);
            vista = t.newInstance();
            vista.inicializar(this, true);

        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "new", ex);
        }
        vista.setTitle(titulo);
        if (beanVO != null) {
            vista.getMainPanel().reload();
            vista.getMainPanel().setMode(Consts.READONLY);
        } else {
            vista.getMainPanel().setMode(Consts.INSERT);
        }
        if (tipoSMS != null && tipoSMS == TipoSMS.TODOS_SMS) {
            ((SMSMasaDetailFrame) vista).ocultarFechas();
        }

        this.es= new EsperaDialog("Ejemplo", "Holaaa", null, false);
        this.hilo= new Thread(){

            @Override
            public void run() {
                es.setVisible(true);
                while(true){
                    int i=0;
                    i++;
                }
            }

            @Override
            public void interrupt() {
                es.setVisible(false);
                super.interrupt();
            }                                    
        };        
    }

    @Override
    public boolean validateControl(String attributeName, Object oldValue, Object newValue) {
        if (attributeName.equals("pre")) {
            SMSPreEscrito pre = (SMSPreEscrito) newValue;
            if (pre != null && pre.getId() != null) {
                vista.getMainPanel().getVOModel().setValue("motivo", pre.getMotivo());
                vista.getMainPanel().pull("motivo");
                vista.getMainPanel().getVOModel().setValue("texto", pre.getTexto());
                vista.getMainPanel().pull("texto");
            }
        }
        return true;
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        if (((SMSMasa) beanVO).getId() != null) {
            Session s = HibernateUtil.getSessionFactory().openSession();
            SMSMasa p = (SMSMasa) s.get(SMSMasa.class, ((SMSMasa) beanVO).getId());
            Hibernate.initialize(p.getSms());
            s.close();
            beanVO = p;
            //((SMSMasaDetailFrame) vista).setDia(((SMSMasa) beanVO).getDia());
        }
        return new VOResponse(beanVO);
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        this.newPersistentObject=newPersistentObject;
        
        Session s = null;
        try {
            AuditoriaBasica ab = null;
            s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            if (newPersistentObject instanceof Auditable) {
                ab = new AuditoriaBasica(new Date(), General.usuario.getUserName(), true);
                ((Auditable) newPersistentObject).setAuditoria(ab);
            }
            //*----------
            SMSMasa smsMasa = (SMSMasa) newPersistentObject;
            if (tipoSMS != null) {
                smsMasa.setTipo(tipoSMS);
            }
            if (((SMSMasaDetailFrame) vista).isDia()) {
                smsMasa.setDia(true);
                switch (smsMasa.getTipo()) {
                    case CUMPLEAYO_SMS: {                        
                        smsMasa.setSms(new CrearSMSMarsivos(((SMSMasaDetailFrame)vista).getGrid()).getCumpleayeros(smsMasa.getFecha(),
                                smsMasa.getTexto(), smsMasa.getMotivo(), "Productor", ab));
                        break;
                    }
                    case DOCUMENTO_SMS: {
                        smsMasa.setSms(new CrearSMSMarsivos(((SMSMasaDetailFrame)vista).getGrid()).getDocumentosVencidos(smsMasa.getFecha(),
                                smsMasa.getTexto(), smsMasa.getMotivo(), "Productor", ab));
                        break;
                    }
                    case GIRO_SMS: {
                        hilo.start();
                        smsMasa.setSms(new CrearSMSMarsivos(((SMSMasaDetailFrame)vista).getGrid()).getGirosVencidos(smsMasa.getFecha(),
                                smsMasa.getTexto(), smsMasa.getMotivo(), "Productor", ab));
                        break;
                    }
                    case RENOVACION_SMS: {
                        smsMasa.setSms(new CrearSMSMarsivos(((SMSMasaDetailFrame)vista).getGrid()).getRenovacion(smsMasa.getFecha(),
                                smsMasa.getTexto(), smsMasa.getMotivo(), "Productor", ab));
                        break;
                    }
                    case TODOS_SMS: {
                        if (sql == null || sql.isEmpty()) {                                                        
                            smsMasa.setSms(new CrearSMSMarsivos(((SMSMasaDetailFrame)vista).getGrid()).getTodos(
                                    smsMasa.getTexto(), smsMasa.getMotivo(), "Productor", ab));                            
                        } else {
                            smsMasa.setSms(new CrearSMSMarsivos(((SMSMasaDetailFrame)vista).getGrid()).getFiltrados(sql, filteredColumns,
                                    smsMasa.getTexto(), smsMasa.getMotivo(), "Productor", ab));
                        }
                        break;
                    }
                }
            } else {
                smsMasa.setDia(false);
                switch (smsMasa.getTipo()) {
                    case CUMPLEAYO_SMS: {
                        smsMasa.setSms(new CrearSMSMarsivos(((SMSMasaDetailFrame)vista).getGrid()).getCumpleayeros(
                                smsMasa.getDesde(), smsMasa.getHasta(), smsMasa.getTexto(), smsMasa.getMotivo(), "Productor", ab));
                        break;
                    }
                    case DOCUMENTO_SMS: {
                        smsMasa.setSms(new CrearSMSMarsivos(((SMSMasaDetailFrame)vista).getGrid()).getDocumentosVencidos(
                                smsMasa.getDesde(), smsMasa.getHasta(), smsMasa.getTexto(), smsMasa.getMotivo(), "Productor", ab));
                        break;
                    }
                    case GIRO_SMS: {
                        smsMasa.setSms(new CrearSMSMarsivos(((SMSMasaDetailFrame)vista).getGrid()).getGirosVencidos(
                                smsMasa.getDesde(), smsMasa.getHasta(), smsMasa.getTexto(), smsMasa.getMotivo(), "Productor", ab));
                        break;
                    }
                    case RENOVACION_SMS: {
                        smsMasa.setSms(new CrearSMSMarsivos(((SMSMasaDetailFrame)vista).getGrid()).getRenovacion(
                                smsMasa.getDesde(), smsMasa.getHasta(), smsMasa.getTexto(), smsMasa.getMotivo(), "Productor", ab));
                        break;
                    }
                }
            }
            //*-------------
            newPersistentObject = smsMasa;
            s.save(newPersistentObject);
            t.commit();
            vista.setOwnerVO((BeanVO) newPersistentObject);
            beanVO = (BeanVO) newPersistentObject;
            if (gridControl != null) {
                gridControl.reloadData();
            }
            ((SMSMasaDetailFrame) vista).reloadGridsData();
            return new VOResponse(newPersistentObject);
        } catch (Exception ex) {
            ex.printStackTrace();
            //return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "insertRecord", ex));
        } finally {
            s.close();
            es.dispose();
        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (EnviarSMS.hayInternet()) {
         enviarSMSs= new DialogEnviarMensajes(((SMSMasa) beanVO));         
         new Thread(){

                @Override
                public void run() {
                    enviarSMSs.enviarMensajes();
                    vista.reloadGridsData();
                }             
         }.start();
//            Session s = null;
//            try {
//                s = HibernateUtil.getSessionFactory().openSession();
//                Transaction t = s.beginTransaction();
//                SMSMasa masa = ((SMSMasa) beanVO);
//                
//                DialogEnviarMensajes enviar= new DialogEnviarMensajes(masa);
//                for (SMS sms : masa.getSms()) {
//                    if (sms.getEstatus() != EstatusSMS.SIN_NUMERO && sms.getEstatus() != EstatusSMS.ENVIADO && sms.getEstatus() != EstatusSMS.MUY_LARGO) {
//                        EstatusSMS estatus = EnviarSMS.enviarSMS(sms);
//                        sms.setEstatus(estatus);
//                        s.update(sms);
//                    }
//                }
//                t.commit();
//                vista.reloadGridsData();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            } finally {
//                s.close();
//            }
        } else {
            JOptionPane.showMessageDialog(MDIFrame.getInstance(), "No hay Internet...", "Mensaje", JOptionPane.WARNING_MESSAGE);
        }
    }
}
