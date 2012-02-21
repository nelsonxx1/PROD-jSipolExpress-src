/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.historial;

import controlador.util.DefaultDetailFrameController;
import java.util.List;
import modelo.Dominios.TipoRifEnum;
import modelo.HibernateUtil;
import modelo.entidades.personas.maestra.Persona;
import modelo.entidades.personas.maestra.PersonaJuridica;
import modelo.entidades.personas.maestra.PersonaNatural;
import modelo.entidades.polizas.recibos.maestra.Beneficiario;
import modelo.entidades.polizas.recibos.maestra.Recibo;
import modelo.entidades.siniestros.maestra.Siniestro;
import modelo.util.bean.BeanVO;
import modelo.utilitario.HistorialPersona;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.util.java.Consts;
import vista.historial.HistorialPersonaDetailFrame;

/**
 *
 * @author PAPA
 */
public class HistorialPersonaDetailController extends DefaultDetailFrameController {

    private TipoRifEnum tipoRif;
    private String rif;

    public HistorialPersonaDetailController() {
    }

    public HistorialPersonaDetailController(
            String detailFramePath,
            GridControl gridControl,
            BeanVO beanVO,
            TipoRifEnum tipoRif,
            String rif) {
        this.gridControl = gridControl;
        this.beanVO = beanVO;
        this.tipoRif = tipoRif;
        this.rif = rif;
        vista = new HistorialPersonaDetailFrame();
        vista.inicializar(this, true);
        ((HistorialPersonaDetailFrame) vista).setTipoPersonas(tipoRif);
        vista.getMainPanel().reload();
        vista.getMainPanel().setMode(Consts.READONLY);
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        String sql = "";
        Query q = null;
        if (rif != null) {
            Session s = HibernateUtil.getSessionFactory().openSession();

            boolean si = false, re = false;

            sql = "FROM " + Siniestro.class.getName() + " as S WHERE ";
            sql += " S.recibo.asegurado.rif.rif=:ci";

            q = s.createQuery(sql);
            q.setString("ci", rif);
            List<Siniestro> siniestros = q.list();
            if (siniestros.size() != 0) {
                si = true;
                ((HistorialPersona) beanVO).setSiniestros(siniestros);
                if (siniestros.get(0).getRecibo().getAsegurado().getRif().getTipoCedula().getTipoPersona() == TipoRifEnum.JURIDICO) {
                    ((HistorialPersona) beanVO).setPersonaJuridica((PersonaJuridica) siniestros.get(0).getRecibo().getAsegurado());
                } else {
                    ((HistorialPersona) beanVO).setPersonaNatural((PersonaNatural) siniestros.get(0).getRecibo().getAsegurado());
                }
            }
            sql = "SELECT DISTINCT R FROM " + Recibo.class.getName() + " R LEFT JOIN R.beneficiarios2 B WHERE ";
            sql += " R.asegurado.rif.rif=:ci OR B.beneficiario.rif.rif=:ci";

            q = s.createQuery(sql);
            q.setString("ci", rif);

            List<Recibo> recibos = q.list();
            if (recibos.size() != 0) {
                re = true;
                ((HistorialPersona) beanVO).setRecibos(recibos);
                if (!si) {
                    if (recibos.get(0).getAsegurado().getRif().getTipoCedula().getTipoPersona() == TipoRifEnum.JURIDICO) {
                        ((HistorialPersona) beanVO).setPersonaJuridica((PersonaJuridica) recibos.get(0).getAsegurado());
                    } else {
                        ((HistorialPersona) beanVO).setPersonaNatural((PersonaNatural) recibos.get(0).getAsegurado());
                    }
                }
            }
            if (!re && !si) {
                sql = "FROM " + Persona.class.getName() + " as P WHERE ";
                sql += " P.rif.rif=:ci";

                q = s.createQuery(sql);
                q.setString("ci", rif);

                List<Persona> persona = q.list();
                System.out.println("5");
                if (persona.size() != 0) {
                    if (persona.get(0).getRif().getTipoCedula().getTipoPersona() == TipoRifEnum.JURIDICO) {
                        ((HistorialPersona) beanVO).setPersonaJuridica((PersonaJuridica) persona.get(0));
                    } else {
                        ((HistorialPersona) beanVO).setPersonaNatural((PersonaNatural) persona.get(0));
                    }
                }
            }
        }
        System.out.println("llego ak");        
        return new VOResponse(beanVO);
    }
}
