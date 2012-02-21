/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.utilitario;

import java.util.ArrayList;
import java.util.List;
import modelo.entidades.polizas.recibos.maestra.Recibo;
import modelo.entidades.siniestros.maestra.Siniestro;
import modelo.entidades.vehiculos.maestra.Vehiculo;
import modelo.util.bean.BeanVO;

/**
 *
 * @author PAPA
 */
public class HistorialVehiculo extends BeanVO{
    private Vehiculo vehiculo;
    private List <Recibo> recibos= new ArrayList<Recibo>(0);
    private List<Siniestro> siniestros = new ArrayList<Siniestro>(0);

    public List<Recibo> getRecibos() {
        return recibos;
    }

    public void setRecibos(List<Recibo> recibos) {
        this.recibos = recibos;
    }

    public List<Siniestro> getSiniestros() {
        return siniestros;
    }

    public void setSiniestros(List<Siniestro> siniestros) {
        this.siniestros = siniestros;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
}
