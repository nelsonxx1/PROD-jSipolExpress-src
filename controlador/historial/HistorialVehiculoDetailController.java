/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.historial;

import controlador.util.DefaultDetailFrameController;
import java.util.List;
import logger.LoggerUtil;
import modelo.HibernateUtil;
import modelo.entidades.polizas.recibos.maestra.Recibo;
import modelo.entidades.siniestros.maestra.Siniestro;
import modelo.entidades.vehiculos.maestra.Vehiculo;
import modelo.util.bean.BeanVO;
import modelo.utilitario.HistorialVehiculo;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.util.java.Consts;
import vista.historial.HistorialVehiculoDetailFrame;

/**
 *
 * @author PAPA
 */
public class HistorialVehiculoDetailController extends DefaultDetailFrameController {

    private Vehiculo vehiculo;
    private String placa;

    public HistorialVehiculoDetailController() {
    }

    public HistorialVehiculoDetailController(
            String detailFramePath,
            GridControl gridControl,
            BeanVO beanVO,
            Vehiculo vehiculo,
            String placa) {
        this.gridControl = gridControl;
        this.beanVO = beanVO;
        this.vehiculo = vehiculo;
        this.placa = placa;
        try {
            vista = new HistorialVehiculoDetailFrame();
            vista.inicializar(this, true);
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "new", ex);
        }
        vista.getMainPanel().reload();
        vista.getMainPanel().setMode(Consts.READONLY);
    }

    @Override
    public Response loadData(Class valueObjectClass) {

//        String sqlCount = "";
        String sql = "";
        Query q = null;

        if (vehiculo != null) {
        }

        if (placa != null) {
            Session s = HibernateUtil.getSessionFactory().openSession();

            boolean si = false, re = false;

            sql = "FROM " + Siniestro.class.getName() + " as S WHERE ";
            sql += " S.recibo.vehiculo.placa=:plac";

            q = s.createQuery(sql);
            q.setString("plac", placa);

            List<Siniestro> siniestros = q.list();
            if (siniestros.size() != 0) {
                si = true;
                ((HistorialVehiculo) beanVO).setSiniestros(siniestros);
                ((HistorialVehiculo) beanVO).setVehiculo(siniestros.get(0).getRecibo().getVehiculo());
            }

            sql = "FROM " + Recibo.class.getName() + " as R WHERE ";
            sql += " R.vehiculo.placa=:plac";

            q = s.createQuery(sql);
            q.setString("plac", placa);

            List<Recibo> recibos = q.list();
            if (recibos.size() != 0) {
                re = true;
                ((HistorialVehiculo) beanVO).setRecibos(recibos);
                if (!si) {
                    ((HistorialVehiculo) beanVO).setVehiculo(recibos.get(0).getVehiculo());
                }
            }

            if (!re && !si) {
                sql = "FROM " + Vehiculo.class.getName() + " as V WHERE ";
                sql += " V.placa=:plac";

                q = s.createQuery(sql);
                q.setString("plac", placa);

                List<Vehiculo> vehiculos = q.list();

                if (vehiculos.size() != 0) {
                    ((HistorialVehiculo) beanVO).setVehiculo(vehiculos.get(0));
                }
            }
        }
        return new VOResponse(beanVO);
    }
}
