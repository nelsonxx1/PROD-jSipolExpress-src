package modelo.entidades.caja.transac;

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
import modelo.entidades.caja.dominio.TipoMovimiento;
import modelo.entidades.caja.dominio.TipoTarjetaCredito;
import modelo.util.bean.BeanVO;
import modelo.entidades.personas.maestra.Persona;
import modelo.util.ehts.BusinessKey;
import modelo.util.ehts.Method;

/**

 */
@Entity
public class CajaDetalle extends BeanVO implements Serializable, Auditable {

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
    @ManyToOne()
    @BusinessKey
    private TipoMovimiento tipoMovimiento;
    /**
     *
     */
    @ManyToOne()
    @BusinessKey
    private TipoDocumentoPago tipoDocumento;
    /**
     *
     */
    @ManyToOne()
    @BusinessKey
    private TipoTarjetaCredito tipoTarjetaCredito;
    /**
     *
     */
    @ManyToOne()
    @BusinessKey
    private Persona banco;
    /**
     * Estatus: Nro. Cheque/Deposito Etc.
     */
    @Column
    @BusinessKey
    private String numeroReferencia;
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

    public CajaDetalle() {
    }

    public CajaDetalle(TipoMovimiento tipoMovimiento, TipoDocumentoPago tipoDocumento, TipoTarjetaCredito tipoTarjetaCredito, Persona banco, String numeroReferencia, Double debito, Double credito, AuditoriaBasica auditoria) {
        this.tipoMovimiento = tipoMovimiento;
        this.tipoDocumento = tipoDocumento;
        this.tipoTarjetaCredito = tipoTarjetaCredito;
        this.banco = banco;
        this.numeroReferencia = numeroReferencia;
        this.debito = debito;
        this.credito = credito;
        this.auditoria = auditoria;
    }

    public Persona getBanco() {
        return banco;
    }

    public void setBanco(Persona banco) {
        this.banco = banco;
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

    public String getNumeroReferencia() {
        return numeroReferencia;
    }

    public void setNumeroReferencia(String numeroReferencia) {
        this.numeroReferencia = numeroReferencia;
    }

    public TipoDocumentoPago getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumentoPago tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public TipoTarjetaCredito getTipoTarjetaCredito() {
        return tipoTarjetaCredito;
    }

    public void setTipoTarjetaCredito(TipoTarjetaCredito tipoTarjetaCredito) {
        this.tipoTarjetaCredito = tipoTarjetaCredito;
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
}

