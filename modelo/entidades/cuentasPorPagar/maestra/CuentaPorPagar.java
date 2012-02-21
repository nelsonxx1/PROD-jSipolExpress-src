package modelo.entidades.cuentasPorPagar.maestra;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.Version;
import modelo.entidades.auditoria.Auditable;
import modelo.entidades.auditoria.AuditoriaBasica;
import modelo.util.bean.BeanVO;
import modelo.util.ehts.BusinessKey;
import modelo.util.ehts.Method;
import java.util.Date;
import modelo.entidades.cuentasPorPagar.dominio.TipoEnriquecimiento;
import modelo.entidades.personas.maestra.Persona;

@Entity
public class CuentaPorPagar extends BeanVO implements Serializable, Auditable {

    /**
     * Pk autogenerado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     */
    @ManyToOne()
    @BusinessKey
    private Persona beneficiario;
    /**
     * Honorarios, Comisiones, Intereses.....
     */
    //TODO para la factura
    @ManyToOne()
    @BusinessKey
    private TipoEnriquecimiento tipoEnriq;
    //TODO la ordenes
    /**
     *
     *//*
    @ManyToMAny
    @BusinessKey
    private Orden ordenDePago;*/
    /**
     * Pendiente, Seleccionado, Emitida, Anulado
     */
    @Column
    @BusinessKey
    private String estatus;
    /**
     */
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    //@Past
    @BusinessKey
    private Date fechaFactura;
    /**
     *
     */
    @Column
    @BusinessKey
    private String numeroFactura;
    /**
     */
    @Column
    @BusinessKey
    private String numeroControl;
    /**
     */
    @Column
    @BusinessKey
    private Double montoFacturado;
    /**
     */
    @Column
    @BusinessKey
    private Double montoPagado;
    /**
     */
    @Column
    @BusinessKey
    private Double montoBaseImponible;
    /**
     */
    @Column
    @BusinessKey
    private Double montoSinCreditoFiscal;
    /**
     */
    @Column
    @BusinessKey
    private Double montoIVA;
    /**
     */
    @Column
    @BusinessKey
    private Double montoISRL;
    /**
     */
    @Column
    @BusinessKey
    private Double porcentajeIVA;
    /**
     */
    @Column
    @BusinessKey
    private Double porcentajeISRL;
    /**
     */
    @Column
    @BusinessKey
    private Double ivaRetenido;
    /**
     */
    @Column
    @BusinessKey
    private String asientoIVA;
    /**
     */
    @Column
    @BusinessKey
    private String asientoISRL;
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

    public CuentaPorPagar() {
        montoPagado = 0.0;
    }

    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAsientoISRL() {
        return asientoISRL;
    }

    public void setAsientoISRL(String asientoISRL) {
        this.asientoISRL = asientoISRL;
    }

    public String getAsientoIVA() {
        return asientoIVA;
    }

    public void setAsientoIVA(String asientoIVA) {
        this.asientoIVA = asientoIVA;
    }

    public Persona getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Persona beneficiario) {
        this.beneficiario = beneficiario;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Date getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(Date fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public Double getIvaRetenido() {
        return ivaRetenido;
    }

    public void setIvaRetenido(Double ivaRetenido) {
        this.ivaRetenido = ivaRetenido;
    }

    public Double getMontoBaseImponible() {
        return montoBaseImponible;
    }

    public void setMontoBaseImponible(Double montoBaseImponible) {
        this.montoBaseImponible = montoBaseImponible;
    }

    public Double getMontoFacturado() {
        return montoFacturado;
    }

    public void setMontoFacturado(Double montoFacturado) {
        this.montoFacturado = montoFacturado;
    }

    public Double getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(Double montoPagado) {
        this.montoPagado = montoPagado;
    }

    public Double getMontoISRL() {
        return montoISRL;
    }

    public void setMontoISRL(Double montoISRL) {
        this.montoISRL = montoISRL;
    }

    public Double getMontoIVA() {
        return montoIVA;
    }

    public void setMontoIVA(Double montoIVA) {
        this.montoIVA = montoIVA;
    }

    public Double getMontoSinCreditoFiscal() {
        return montoSinCreditoFiscal;
    }

    public void setMontoSinCreditoFiscal(Double montoSinCreditoFiscal) {
        this.montoSinCreditoFiscal = montoSinCreditoFiscal;
    }

    public String getNumeroControl() {
        return numeroControl;
    }

    public void setNumeroControl(String numeroControl) {
        this.numeroControl = numeroControl;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Integer getOptLock() {
        return optLock;
    }

    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    public Double getPorcentajeISRL() {
        return porcentajeISRL;
    }

    public void setPorcentajeISRL(Double porcentajeISRL) {
        this.porcentajeISRL = porcentajeISRL;
    }

    public Double getPorcentajeIVA() {
        return porcentajeIVA;
    }

    public void setPorcentajeIVA(Double porcentajeIVA) {
        this.porcentajeIVA = porcentajeIVA;
    }

    public TipoEnriquecimiento getTipoEnriq() {
        return tipoEnriq;
    }

    public void setTipoEnriq(TipoEnriquecimiento tipoEnriq) {
        this.tipoEnriq = tipoEnriq;
    }
}
