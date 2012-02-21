package modelo.entidades.polizas.recibos.maestra;

import modelo.Dominios;
import modelo.entidades.polizas.recibos.devolucion.maestra.Devolucion;

/**
 *
 * @author bc
 */
public class LNRecibo {

    public static void actualizarEstatus(Recibo r) {
        if (r.getFechaCobro() == null) {
            r.setEstatus(Dominios.EstatusRecibo.PENDIENTE_COBRO);
        }
        if (r.getFechaCobro() != null && r.getFechaPagoComision() == null) {
            r.setEstatus(Dominios.EstatusRecibo.COBRADO_PENDIENTE_COMISION);
        }
        if (r.getFechaPagoComision() != null) {
            r.setEstatus(Dominios.EstatusRecibo.COBRADO);
        }
        if (r.getFechaAnulacion() != null) {
            r.setEstatus(Dominios.EstatusRecibo.ANULADO);
        }
    }

    public static String validar(Recibo r) {
        StringBuilder errorMsj = new StringBuilder();
        if (r.getVigenciaDesde().after(r.getVigenciaHasta())) {
            errorMsj.append("Inconsistencia en vigencia.");
            errorMsj.append("\n");
        }
        if ((r.getFechaCobro() == null && r.getFechaPagoComision() != null) || (r.getFechaPagoComision() != null && r.getFechaCobro() != null && r.getFechaPagoComision().before(r.getFechaCobro()))) {
            errorMsj.append("La fecha de pago de comision no puede ser menor a la fecha de cobro.");
            errorMsj.append("\n");
        }
        double primaD=0, comisionD=0;
        for (Devolucion d : r.getDevoluciones()) {
            primaD+=d.getPrimaDevuelta();
            comisionD+=d.getExtornoComision();
        }
        if(primaD>r.getPrimaRecibo()){
            errorMsj.append("El monto de la prima no puede ser menor a la prima en devoluciones.");
            errorMsj.append("\n");
        }
        if(comisionD>r.getComision()){
            errorMsj.append("El monto de la comision no puede ser menor al extorno de comisiones.");
            errorMsj.append("\n");
        }
        return errorMsj.toString();
    }
}
