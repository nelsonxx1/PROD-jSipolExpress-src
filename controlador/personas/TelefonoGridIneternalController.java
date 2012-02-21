package controlador.personas;

import controlador.General;
import controlador.Principal;
import controlador.sms.EnviarSMS;
import controlador.util.DefaultGridInternalController;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import logger.LoggerUtil;
import modelo.Dominios.EstatusSMS;
import modelo.HibernateUtil;
import modelo.entidades.auditoria.Auditable;
import modelo.entidades.auditoria.AuditoriaBasica;
import modelo.entidades.personas.maestra.Persona;
import modelo.entidades.personas.transac.TelefonoPersona;
import modelo.entidades.sms.SMS;
import modelo.entidades.sms.SMSPreEscrito;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author bc
 */
public class TelefonoGridIneternalController extends DefaultGridInternalController {

    public TelefonoGridIneternalController(String classNameModelFullPath, String getMethodName, GridControl miGrid, ArrayList<DefaultGridInternalController> listSubGrids) {
        super(classNameModelFullPath, getMethodName, miGrid, listSubGrids);
    }

    @Override
    public void createValueObject(ValueObject valueObject) throws Exception {
        int rowToSel = miGrid.getVOListTableModel().getRowCount() - 1;
        miGrid.getVOListTableModel().setField(rowToSel, "tipoTelefono", General.defaultPersona.getTelefono());
    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
        Session s = null;
        if (getSet() != null) {
            try {
                s = HibernateUtil.getSessionFactory().openSession();
                Transaction t = s.beginTransaction();
                for (Object object : newValueObjects) {
                    ValueObject o = (ValueObject) object;
                    AuditoriaBasica ab = new AuditoriaBasica(new Date(), General.usuario.getUserName(), true);
                    if (o instanceof Auditable) {
                        ((Auditable) o).setAuditoria(ab);
                    }
                    String numero = "" + ((TelefonoPersona) o).getCodigoArea().getNumero() + ((TelefonoPersona) o).getNumeroS();
                    ((TelefonoPersona) o).setNumeroCompleto(numero);
                    if (((TelefonoPersona) o).getNotificar() && General.empresa.getSmsBienvenida()) {
                        String msj = Principal.defaultData.smsBienvenida;
                        String prod = "PRODUCTOR_B";
                        if (General.empresa.getSmsVariosProductores()) {
                            String productores[] = new String[Principal.defaultData.smsBienvenidaTodos.size()];
                            for (int i = 0; i < Principal.defaultData.smsBienvenidaTodos.size(); i++) {
                                SMSPreEscrito sms = Principal.defaultData.smsBienvenidaTodos.get(i);
                                productores[i] = sms.getMotivo().replaceAll("BIENVENIDA", "").trim().toUpperCase();
                            }
                            //JOptionPane.showInputDialog(MDIFrame.getInstance(), "mensaje", "titulo", JOptionPane.PLAIN_MESSAGE, null, productores, 0);
                            int r = JOptionPane.showOptionDialog(MDIFrame.getInstance(), "¿Productor que envia SMS de Bienvenida?", "SMS de Bienvenida", JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, productores, 0);
                            if (r != JOptionPane.CLOSED_OPTION) {
                                msj = Principal.defaultData.smsBienvenidaTodos.get(r).getTexto();
                                prod = productores[r];
                            }
                        }
                        msj = msj.replaceAll(":nombre", ((Persona) beanVO).getNombreCorto());
                        msj = msj.replaceAll(":nombreLargo", ((Persona) beanVO).getNombreLargo());
                        SMS sms = new SMS(((Persona) beanVO).getNombreCorto(), prod, "0" + numero, "BIENVENIDA", msj, new Date(), ab,(Persona) beanVO);
                        //((Persona) beanVO).getSmss().add(sms);
                        EstatusSMS estatus = EnviarSMS.enviarSMS(sms);
                        sms.setEstatus(estatus);
                        s.update(beanVO);
                        //s.save(sms);
                    }
                    getSet().add(o);
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
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            for (Object object : persistentObjects) {
                ValueObject o = (ValueObject) object;
                //ValueObject o = (ValueObject) persistentObjects.get(0);
                if (o instanceof Auditable) {
                    AuditoriaBasica ab = ((Auditable) o).getAuditoria();
                    ab.setFechaUpdate(new Date());
                    ab.setUsuarioUpdate(General.usuario.getUserName());
                }
                String n = "" + ((TelefonoPersona) o).getCodigoArea().getNumero() + ((TelefonoPersona) o).getNumeroS();
                ((TelefonoPersona) o).setNumeroCompleto(n);
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
