package modelo.entidades.defaultData;

import java.util.ArrayList;
import modelo.entidades.sms.SMSPreEscrito;

/**
 *
 * @author bc
 */
public class DefaultData {

    public DefaultPersona persona;
    public DefaultVehiculo vehiculo;
    public DefaultPoliza poliza;
    public DefaultRecibo recibo;
    public DefaultSiniestro siniestro;
    public String smsBienvenida;
    public ArrayList<SMSPreEscrito> smsBienvenidaTodos;

    public DefaultData() {
        persona = new DefaultPersona();
        vehiculo = new DefaultVehiculo();
        poliza = new DefaultPoliza();
        recibo = new DefaultRecibo();
        siniestro = new DefaultSiniestro();
        smsBienvenida = "";
        smsBienvenidaTodos = new ArrayList<SMSPreEscrito>(0);
    }
}
