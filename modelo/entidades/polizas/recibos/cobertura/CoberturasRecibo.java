/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.entidades.polizas.recibos.cobertura;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.Version;
import javax.validation.constraints.Past;
import modelo.entidades.auditoria.Auditable;
import modelo.entidades.auditoria.AuditoriaBasica;
import modelo.entidades.polizas.recibos.maestra.Recibo;
import modelo.util.ehts.BusinessKey;
import modelo.util.ehts.Method;

/**
 *
 * @author PAPA
 */
@Entity
public class CoberturasRecibo implements Serializable, Auditable {

    /**
     * Pk autogenerado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;

    /**
     *
     */
    @Version
    @Column
    private Integer optLock;
    /**
     *
     */
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    @Past
    @BusinessKey
    private Date fechaDesda;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double sumaAsegurada;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double prima;
    /**
     *
     */
    @ManyToOne
    //@OnDelete(action=OnDeleteAction.CASCADE)
    //@BusinessKey
    private Recibo recibo;
    /**
     *
     */
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    @BusinessKey
    private Date fechaHasta;
    /**
     *
     */
    @Embedded
    @BusinessKey
    private AuditoriaBasica auditoria;
    /**
     *
     */
    @ManyToOne   
    private Cobertura cobertura;

    public Long getId() {
        return id;
    }

    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria=auditoria;
    }

    public Integer getOptLock() {
        return optLock;
    }

    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    public Cobertura getCobertura() {
        return cobertura;
    }

    public void setCobertura(Cobertura cobertura) {
        this.cobertura = cobertura;
    }

    public Date getFechaDesda() {
        return fechaDesda;
    }

    public void setFechaDesda(Date fechaDesda) {
        this.fechaDesda = fechaDesda;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public Double getPrima() {
        return prima;
    }

    public void setPrima(Double prima) {
        this.prima = prima;
    }

    public Double getSumaAsegurada() {
        return sumaAsegurada;
    }

    public void setSumaAsegurada(Double sumaAsegurada) {
        this.sumaAsegurada = sumaAsegurada;
    }

    public Recibo getRecibo() {
        return recibo;
    }

    public void setRecibo(Recibo recibo) {
        this.recibo = recibo;
    }
    
}
