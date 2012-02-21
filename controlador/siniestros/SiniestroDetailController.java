package controlador.siniestros;

import controlador.General;
import controlador.helpcenter.CorreoDetailFrameController;
import controlador.historial.HistorialPersonaDetailController;
import controlador.reportes.ReporteController;
import controlador.sms.EnviarSMS;
import controlador.util.DefaultDetailFrameController;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Dominios;
import modelo.HibernateUtil;
import modelo.entidades.Observacion;
import modelo.entidades.ParametroReporte;
import modelo.entidades.Reporte;
import modelo.entidades.personas.maestra.Persona;
import modelo.entidades.polizas.recibos.maestra.Recibo;
import modelo.entidades.siniestros.maestra.Siniestro;
import modelo.util.bean.BeanVO;
import modelo.utilitario.HistorialPersona;
import org.hibernate.Hibernate;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import vista.siniestros.SiniestroDetailFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.util.java.Consts;
import vista.historial.HistorialPersonaDetailFrame;
import vista.polizas.recibos.SuperusuarioLoginDialog;

/**
 *
 * @author bc
 */
public class SiniestroDetailController extends DefaultDetailFrameController {

    public SiniestroDetailController(String detailFramePath, GridControl gridControl, BeanVO beanVO, boolean aplicarLogicaNegocio) {
        super(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
        vista.setTitle("Siniestro: ");
        if (beanVO != null) {
            ((SiniestroDetailFrame) vista).validarEstatus(((Siniestro) beanVO).getEstado());
            if (((Siniestro) beanVO).getRecibo() != null) {
                vista.setTitle(vista.getTitle() + ((Siniestro) beanVO).getRecibo().getAsegurado().getNombreLargo());
            }
        }
    }

    @Override
    public boolean validateControl(String attributeName, Object oldValue, Object newValue) {
        if (attributeName.equals("recibo")) {
            if (newValue != null && (((Recibo) newValue).getId() != null || ((Recibo) newValue).getId() != 0)) {
                boolean v = ((Recibo) newValue).getPoliza().getRamoPoliza().getRamoContable().equals(Dominios.RamoContable.VEHICULO);
                ((SiniestroDetailFrame) (vista)).setVisibleVehiculo(v);
            }
        }
        return true;
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Siniestro p = (Siniestro) s.get(Siniestro.class, ((Siniestro) beanVO).getId());
        Hibernate.initialize(p.getObservaciones());
        Hibernate.initialize(p.getDocumentos());
        s.close();
        beanVO = p;
        boolean v = ((Recibo) p.getRecibo()).getPoliza().getRamoPoliza().getRamoContable().equals(Dominios.RamoContable.VEHICULO);
        ((SiniestroDetailFrame) (vista)).setVisibleVehiculo(v);
        return new VOResponse(beanVO);
    }

    @Override
    public void createPersistentObject(ValueObject PersistentObject) throws Exception {
        PersistentObject = new Siniestro();
        //        ((Siniestro) PersistentObject).setGrupoPoliza((Principal.defaultData.poliza.getGrupoPoliza()));
//        vista.getMainPanel().getVOModel().setValueObject(PersistentObject);
    }

    @Override
    public Response logicaNegocio(ValueObject persistentObject) {
        String errorMsj = "";
//        Poliza p = (Poliza) persistentObject;
//        p.setAseguraVehiculo(true);
//        if (Dominios.QueAsegura.values()[p.getRamoPoliza().getQueAsegura()].equals(Dominios.QueAsegura.VEHICULO) && Dominios.TipoRamo.values()[p.getRamoPoliza().getTipoRamo()].equals(Dominios.TipoRamo.INDIVIDUAL) && (p.getVehiculo().getId() == null || p.getVehiculo().getId() == 0 || p.getVehiculo().getId() == 1)) {
//            errorMsj = "Para el ramo \"" + p.getRamoPoliza().getNombre() + "\" es obligatorio registrar el vehiculo,\n" +
//                    "y asociarlo a la poliza, para poder guardar este registro.";
//        } else if(!Dominios.QueAsegura.values()[p.getRamoPoliza().getQueAsegura()].equals(Dominios.QueAsegura.VEHICULO) || Dominios.TipoRamo.values()[p.getRamoPoliza().getTipoRamo()].equals(Dominios.TipoRamo.COLECTIVO) ){
//            p.setAseguraVehiculo(false);
//            //if (p.getVehiculo() == null || p.getVehiculo().getId() == null || p.getVehiculo().getId() == 0) {
//            p.setVehiculo(Principal.defaultData.poliza.getVehiculo());
//            //}
//        }
//        persistentObject = p;
        if (errorMsj.length() > 0) {
            return new ErrorResponse(errorMsj);
        } else {
            return new VOResponse(persistentObject);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Siniestro ss = (Siniestro) beanVO;
        if (e.getSource() == ((SiniestroDetailFrame) vista).getSMSButton()) {
            if (ss != null && ss.getRecibo() != null) {
                Long idP = ss.getRecibo().getAsegurado().getId();
                Session s = null;
                try {
                    s = HibernateUtil.getSessionFactory().openSession();
                    Persona p = (Persona) s.load(Persona.class, idP);
                    EnviarSMS.crearSMS(p.getNombreLargo(), ss.getRecibo().getPoliza().getProductor().getNombreCorto(), p.getTelefonos(),p);
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    s.close();
                }
            }
        } else if (e.getSource() == ((SiniestroDetailFrame) vista).getEmailButton()) {
            String email = ss.getRecibo().getAsegurado().getEmail();
            if (email != null && !email.isEmpty()) {
                new CorreoDetailFrameController(email + "," + General.empresa.getEmail(), General.empresa.getEmail(), "Email", null, false);
            } else {
                JOptionPane.showMessageDialog(MDIFrame.getInstance(), "El contratante no tiene registrado correo electronico...", "Mensaje", JOptionPane.WARNING_MESSAGE);
            }
        } else if (e.getSource() == ((SiniestroDetailFrame) vista).getEstadoButton()) {
            if (vista.getMainPanel().getMode() == Consts.READONLY) {
                switch (ss.getEstado()) {
                    case ABIERTO:
                        int op = JOptionPane.showConfirmDialog(MDIFrame.getInstance(),
                                "Si Cierra el siniestro necesitara privilegios de Super usuario para poder abrirlo nuevamente. Desea Cerralo?",
                                "Cerrar Sinietro",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.INFORMATION_MESSAGE);
                        if (op == JOptionPane.YES_OPTION) {
                            ss.setEstado(Dominios.EstadoSiniestro.CERRADO);
                            ((SiniestroDetailFrame) vista).validarEstatus(ss.getEstado());
                            System.out.println("Siniestro cerrado");
                        }
                        break;
                    case CERRADO:
                        if (SuperusuarioLoginDialog.VerificarSuperusuario()) {
                            ss.setEstado(Dominios.EstadoSiniestro.ABIERTO);
                            ((SiniestroDetailFrame) vista).validarEstatus(ss.getEstado());
                            System.out.println("Siniestro Abierto");
                        } else {
                            return;
                        }
                        break;
                }

                Session s = null;
                try {
                    s = HibernateUtil.getSessionFactory().openSession();
                    Transaction t = s.beginTransaction();
                    s.update(ss);
                    t.commit();
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    s.close();
                }

                ((SiniestroDetailFrame) vista).validarEstado(ss);
            } else {
                JOptionPane.showMessageDialog(null, "Debes guardar primero el Registro");
            }
        } else if (e.getSource() == ((SiniestroDetailFrame) vista).getReporteButoon()) {
            Reporte r = new Reporte(Dominios.CategoriaReporte.SINIESTROS, 0, "SIN", "Detalle de Siniestro", "", "");

            List l = new ArrayList(0);
            Siniestro s = (Siniestro) (((SiniestroDetailFrame) vista).getVOModel().getValueObject());
            l.add(s);
            for (Observacion observacion : s.getObservaciones()) {
                System.out.println(observacion.getAuditoria().getFechaInsert());
            }
            if (s.getRecibo().getVehiculo().getPlaca().compareTo("0000") != 0) {
                new ReporteController().mostrarReporte(l, new ArrayList<ParametroReporte>(0), "SIN", "Detalle del Siniestro", "Estilo3.jrtx");
            } else {
                new ReporteController().mostrarReporte(l, new ArrayList<ParametroReporte>(0), "SIN2", "Detalle del Siniestro", "Estilo3.jrtx");
            }
        } else if (e.getSource() == ((SiniestroDetailFrame) vista).getHistorialButoon()) {
            Siniestro p;
            if ((p = (Siniestro) (((SiniestroDetailFrame) vista).getVOModel().getValueObject())) != null) {
                new HistorialPersonaDetailController(HistorialPersonaDetailFrame.class.getName(), null, new HistorialPersona(), p.getRecibo().getAsegurado().getRif().getTipoCedula().getTipoPersona(), p.getRecibo().getAsegurado().getRif().getRif());
            }
        }
    }
}
