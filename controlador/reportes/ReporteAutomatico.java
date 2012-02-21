/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package controlador.reportes;

import controlador.reportes.ReporteController;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import modelo.Dominios.CategoriaReporte;
import modelo.HibernateUtil;
import modelo.entidades.Reporte;
import modelo.entidades.polizas.recibos.maestra.Recibo;
import org.hibernate.classic.Session;
import org.openswing.swing.message.send.java.FilterWhereClause;
import vista.reportes.EsperaDialog;

/**
 *
 * @author orlandobcrra
 */
public class ReporteAutomatico {

    public ReporteAutomatico() {
        if (Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == 1) {
            int r = JOptionPane.showConfirmDialog(null, "¿Mostar Reportes Automaticos?", "?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (r == JOptionPane.YES_OPTION) {
                renovaciones();
            }
        }
    }

    private void renovaciones() {
        Map filtros = new HashMap(0);

        FilterWhereClause parametros[] = new FilterWhereClause[2];

        Calendar desde2 = Calendar.getInstance();
        //desde2.setTime(new Date(2011-1900, 3, 1));

        Calendar hasta2 = Calendar.getInstance();
        //hasta2.setTime(new Date(2011-1900, 3, 1));
        hasta2.set(Calendar.MONTH, hasta2.get(Calendar.MONTH) + 1);

        //validacion si pasa de año
        if (hasta2.get(Calendar.MONTH) == 12) {
            hasta2.set(Calendar.MONTH, 1);
            hasta2.set(Calendar.YEAR, hasta2.get(Calendar.YEAR) + 1);
        }

        parametros[0] = new FilterWhereClause("vigenciaHasta", ">=", desde2.getTime());
        parametros[1] = new FilterWhereClause("vigenciaHasta", "<", hasta2.getTime());

        filtros.put("vigenciaHasta", parametros);


        //REC-D001

        Session s = HibernateUtil.getSessionFactory().openSession();
        Reporte r = (Reporte) s.createQuery("FROM " + Reporte.class.getName() + " WHERE categoria=:categoria AND file=:file").
                setString("categoria", CategoriaReporte.RENOVACIONES.name()).
                setString("file", "REC-D001").
                uniqueResult();
        s.close();

        mostrarReporte(r, "Estilo3.jrtx", filtros);
    }

    public void mostrarReporte(final Reporte reporte, final String estilo, final Map filtros) {
        //final EsperaDialog es = new EsperaDialog(null, false);
        //ClientUtils.centerDialog(MDIFrame.getInstance(), es);
        new Thread() {

            @Override
            public void run() {
                //es.setVisible(true);
                new ReporteController().loadData(reporte, estilo, 1, 0, filtros, new ArrayList(0), new ArrayList(0), Recibo.class, new HashMap(0));
                //es.dispose();
            }
        }.start();
        //es.dispose();
    }
}
