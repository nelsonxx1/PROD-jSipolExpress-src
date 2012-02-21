/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo.utilitario;

import modelo.Dominios.EstatusFinanciamiento;
import modelo.entidades.personas.maestra.Persona;
import modelo.entidades.polizas.recibos.maestra.Recibo;
import modelo.util.bean.BeanVO;

/**
 *
 * @author PAPA
 */
public class BuscarFinanciamiento extends BeanVO{
    private Persona financiadora;
    private EstatusFinanciamiento estatus;
    private Persona pagador;
    private Recibo recibo;
    private String numero;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public BuscarFinanciamiento() {
    }    
    
    public Persona getFinanciadora() {
        return financiadora;
    }

    public void setFinanciadora(Persona financiadora) {
        this.financiadora = financiadora;
    }

    public Persona getPagador() {
        return pagador;
    }

    public void setPagador(Persona pagador) {
        this.pagador = pagador;
    }

    public Recibo getRecibo() {
        return recibo;
    }

    public void setRecibo(Recibo recibo) {
        this.recibo = recibo;
    }

    public EstatusFinanciamiento getEstatus() {
        return estatus;
    }

    public void setEstatus(EstatusFinanciamiento estatus) {
        this.estatus = estatus;
    }
    
}
