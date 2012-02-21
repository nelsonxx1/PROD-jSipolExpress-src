package modelo.entidades.ordenes.transac;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import modelo.entidades.auditoria.Auditable;
import modelo.entidades.auditoria.AuditoriaBasica;
import modelo.entidades.caja.dominio.TipoDocumentoPago;
import modelo.util.bean.BeanVO;
import modelo.util.ehts.BusinessKey;
import modelo.util.ehts.Method;

/**
 *
 */
@Entity
public class OrdenDePagoDetalle extends BeanVO implements Serializable, Auditable {

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
    @Column
    @BusinessKey
    private Boolean automaticoOmanual;
    /**
     */
    @Column
    @BusinessKey
    private String detalle;
    /**
     *
     */
    @ManyToOne()
    @BusinessKey
    private TipoDocumentoPago tipoDocumentoPago;
    /**
     */
    @Column
    @BusinessKey
    private String numeroReferencia;
    /**
     */
    @Column
    @BusinessKey
    private String cuenta;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double debito;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double credito;
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

    public OrdenDePagoDetalle() {
    }

    public Double getCredito() {
        return credito;
    }

    public void setCredito(Double credito) {
        this.credito = credito;
    }

    public Double getDebito() {
        return debito;
    }

    public void setDebito(Double debito) {
        this.debito = debito;
    }

    public Integer getOptLock() {
        return optLock;
    }

    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    public Long getId() {
        return id;
    }

    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    public Boolean getAutomaticoOmanual() {
        return automaticoOmanual;
    }

    public void setAutomaticoOmanual(Boolean automaticoOmanual) {
        this.automaticoOmanual = automaticoOmanual;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public String getNumeroReferencia() {
        return numeroReferencia;
    }

    public void setNumeroReferencia(String numeroReferencia) {
        this.numeroReferencia = numeroReferencia;
    }

    public TipoDocumentoPago getTipoDocumentoPago() {
        return tipoDocumentoPago;
    }

    public void setTipoDocumentoPago(TipoDocumentoPago tipoDocumentoPago) {
        this.tipoDocumentoPago = tipoDocumentoPago;
    }
}

