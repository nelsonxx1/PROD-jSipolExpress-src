package modelo.entidades.polizas.financiamiento;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.Version;
import modelo.Dominios;
import modelo.Dominios.EstatusGiro;
import modelo.entidades.auditoria.Auditable;
import modelo.entidades.auditoria.AuditoriaBasica;
import modelo.util.bean.BeanVO;
import modelo.util.ehts.BusinessKey;
import modelo.util.ehts.Method;

/**
 *
 * @author bc
 */
@Entity
public class Giro extends BeanVO implements Serializable, Auditable, Comparable<Giro> {

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
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    @BusinessKey
    private Date fechaVencimiento;
    /**
     * 
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private EstatusGiro estatus;
    /**
     *
     */
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    @BusinessKey
    private Date fechaCobro;
    /**
     *
     */
    @Column
    @BusinessKey
    private String numeroIngreso;
    /**
     *
     */
    @Column
    //@NotNull
    @BusinessKey
    private Double montoGiro;
    /**
     */
    @Column
    @BusinessKey
    private Boolean domiciliado;
    /**
     */
    @Column
    @BusinessKey
    private String observacion;
    /**
     *
     */
    //@Column
    //@BusinessKey
    transient private Integer numero;
    /**
     */
    @Version
    @Column
    private Integer optLock;
    /**
     */
    @Embedded
    @BusinessKey
    private AuditoriaBasica auditoria;

    public Giro() {
        domiciliado = false;
        setEstatus(Dominios.EstatusGiro.PENDIENTE);
    }

    public Giro(Date fechaVencimiento, Double montoGiro, Boolean domiciliado, Integer numero) {
        this.numero = numero;
        this.fechaVencimiento = fechaVencimiento;
        this.montoGiro = montoGiro;
        this.domiciliado = domiciliado;
        setEstatus(Dominios.EstatusGiro.PENDIENTE);
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getMontoGiro() {
        return montoGiro;
    }

    public void setMontoGiro(Double montoGiro) {
        this.montoGiro = montoGiro;
    }

    public Integer getOptLock() {
        return optLock;
    }

    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    public EstatusGiro getEstatus() {
        return estatus;
    }

    public void setEstatus(EstatusGiro estatus) {
        this.estatus = estatus;
    }

    public Date getFechaCobro() {
        return fechaCobro;
    }

    public void setFechaCobro(Date fechaCobro) {
        this.fechaCobro = fechaCobro;
    }

    public Boolean getDomiciliado() {
        return domiciliado;
    }

    public void setDomiciliado(Boolean domiciliado) {
        this.domiciliado = domiciliado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public int compareTo(Giro o) {
        return fechaVencimiento.compareTo(o.getFechaVencimiento());
    }

    public String getNumeroIngreso() {
        return numeroIngreso;
    }

    public void setNumeroIngreso(String numeroIngreso) {
        this.numeroIngreso = numeroIngreso;
    }
    
}
