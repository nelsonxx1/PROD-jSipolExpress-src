package controlador.reportes;

import modelo.entidades.Reporte;
import vista.personas.Personas2GridFrame;
import vista.polizas.financiamiento.FinanciamientosGridFrame;
import vista.polizas.recibos.RecibosGridFrame;
import vista.polizas.recibos.distribucion.DistribucionGridFrame;
import vista.reportes.FiltroDistribucionDialog;
import vista.reportes.FiltroRecibosDialog;
import vista.reportes.FiltroRenovacionesDialog;
import vista.reportes.FiltroSiniestroDialog;
import vista.util.DefaultGridFrame;

/**
 *
 * @author orlandobcrra
 */
public class Filtros {

    public DefaultGridFrame mostrarFiltro(Reporte reporte, boolean sencillo) {
        if (!sencillo) {
            switch (reporte.getCategoria()) {

                case PERSONAS: {
                    return new Personas2GridFrame();
                }

                case COMISIONES: {
                    return new RecibosGridFrame();
                }
                case RECIBOS: {
                    return new RecibosGridFrame();
                }
                case DISTRIBUCION: {
                    return new DistribucionGridFrame();
                }
                case RENOVACIONES: {
                    return new RecibosGridFrame();
                }
                case FINANCIAMIENTOS: {
                    return new FinanciamientosGridFrame();
                }
                case SUPERINTENDENDENCIA: {
                    return new RecibosGridFrame();
                }
                default: {
                    System.out.println("Opcion invalida");
                    throw new UnsupportedOperationException("Not yet implemented");
                }
            }
        } else {
            switch (reporte.getCategoria()) {
                case RENOVACIONES: {
                    new FiltroRenovacionesDialog(reporte, "Estilo3.jrtx").setVisible(true);
                    break;
                }
                case COMISIONES: {
                    new FiltroRecibosDialog(reporte, "Estilo3.jrtx").setVisible(true);
                    break;
                }
                case RECIBOS: {
                    new FiltroRecibosDialog(reporte, "Estilo3.jrtx").setVisible(true);
                    break;
                }
                case DEVOLUCIONES:
                    new FiltroRecibosDialog(reporte, "Estilo3.jrtx").setVisible(true);
                    break;
                case SUPERINTENDENDENCIA:
                    new FiltroRecibosDialog(reporte, "Estilo3.jrtx").setVisible(true);
                    break;
                case DISTRIBUCION:
                    new FiltroDistribucionDialog(reporte, "Estilo3.jrtx").setVisible(true);
                    break;
                case SINIESTROS:
                    new FiltroSiniestroDialog(reporte, "Estilo3.jrtx").setVisible(true);
                    break;
                default: {
                    throw new UnsupportedOperationException("Not yet implemented");

                }
            }
        }
        return null;
    }
}
