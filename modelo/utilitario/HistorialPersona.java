/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.utilitario;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import modelo.entidades.personas.dominio.TipoPersona;
import modelo.entidades.personas.maestra.PersonaJuridica;
import modelo.entidades.personas.maestra.PersonaNatural;
import modelo.entidades.polizas.maestra.Poliza;
import modelo.entidades.polizas.recibos.maestra.Recibo;
import modelo.entidades.siniestros.maestra.Siniestro;
import modelo.util.bean.BeanVO;

/**
 *
 * @author PAPA
 */
public class HistorialPersona extends BeanVO{
    
    private PersonaNatural personaNatural;
    private PersonaJuridica personaJuridica;
    private Set <TipoPersona> tiposPersona= new HashSet<TipoPersona>(0);
    private List <Recibo> recibos= new ArrayList<Recibo>(0);
    private List <Poliza> polizas= new ArrayList<Poliza>(0);
    private List<Siniestro> siniestros = new ArrayList<Siniestro>(0);

    public List<Siniestro> getSiniestros() {
        return siniestros;
    }

    public void setSiniestros(List<Siniestro> siniestros) {
        this.siniestros = siniestros;
    }

    public Set<TipoPersona> getTiposPersona() {
        return tiposPersona;
    }

    public void setTiposPersona(Set<TipoPersona> tiposPersona) {
        this.tiposPersona = tiposPersona;
    }

    public List<Recibo> getRecibos() {
        return recibos;
    }

    public void setRecibos(List<Recibo> recibos) {
        this.recibos = recibos;
    }

    public PersonaJuridica getPersonaJuridica() {
        return personaJuridica;
    }

    public void setPersonaJuridica(PersonaJuridica personaJuridica) {
        this.personaJuridica = personaJuridica;
        setTiposPersona(personaJuridica.getTiposPersona());
    }

    public PersonaNatural getPersonaNatural() {
        return personaNatural;
    }

    public void setPersonaNatural(PersonaNatural personaNatural) {
        this.personaNatural = personaNatural;
        setTiposPersona(personaNatural.getTiposPersona());
    }

    public List<Poliza> getPolizas() {
        return polizas;
    }

    public void setPolizas(List<Poliza> polizas) {
        this.polizas = polizas;
    }
}
