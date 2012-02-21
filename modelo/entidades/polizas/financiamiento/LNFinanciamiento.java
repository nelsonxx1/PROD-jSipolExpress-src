package modelo.entidades.polizas.financiamiento;

import modelo.Dominios;

/**
 *
 * @author bc
 */
public class LNFinanciamiento {

    public static void validarEstatusGiros(Financiamiento f) {
        if (f.getAnuladoF()) {
            for (Object object : f.getGiros()) {
                Giro g = ((Giro) object);
                g.setEstatus(Dominios.EstatusGiro.ANULADO);              
            }
        }
        for (Object object : f.getGiros()) {
            Giro g = ((Giro) object);
            if (g.getFechaCobro() != null) {
                g.setEstatus(Dominios.EstatusGiro.COBRADO_GIRO);
            } else {
                g.setEstatus(Dominios.EstatusGiro.PENDIENTE);
            }
        }
    }

    public static void actulizarMontoFinanciamiento(Financiamiento f){
        f.setMontoFinanciamiento(f.getInicialFinanciamiento());
        f.setMontoPagado(f.getInicialFinanciamiento());
        int i=0;
        for (Giro giro : f.getGiros()) {
            f.setMontoFinanciamiento(f.getMontoFinanciamiento()+giro.getMontoGiro());
            if(giro.getEstatus()==Dominios.EstatusGiro.COBRADO_GIRO && giro.getEstatus()!=Dominios.EstatusGiro.ANULADO)
            {
                f.setMontoPagado(f.getMontoPagado()+giro.getMontoGiro());
                i++;
            }
        }
        f.setGirosPagados(i);
    }

    public static void validarEstatusFinanciamiento(Financiamiento f) {

        if(f.getAnuladoF().booleanValue())
        {
            f.setEstatus(Dominios.EstatusFinanciamiento.ANULADO);
            return;
        }
        
        if (f.getGiros() == null || f.getGiros().size() == 0) {
            f.setEstatus(Dominios.EstatusFinanciamiento.SINGIROS);
            return;
        }

        int i = 0;

        for (Object object : f.getGiros()) {
            Giro g = ((Giro) object);
            if (g.getEstatus().equals(Dominios.EstatusGiro.COBRADO_GIRO)) {
                i++;
            }
        }

        if (f.getGiros().size() == i) {
            f.setEstatus(Dominios.EstatusFinanciamiento.PAGADO);
        } else if (f.getGiros().size() > 0) {
            f.setEstatus(Dominios.EstatusFinanciamiento.PENDIENTE);
        } else {
            f.setEstatus(Dominios.EstatusFinanciamiento.SINGIROS);
        }

    }
}
