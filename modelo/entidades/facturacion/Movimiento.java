package modelo.entidades.facturacion;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.Version;
import modelo.entidades.auditoria.AuditoriaBasica;
import modelo.entidades.auditoria.Auditable;
import modelo.entidades.caja.dominio.TipoDocumentoPago;
import modelo.entidades.personas.transac.CuentaBancariaPersona;
import modelo.util.bean.BeanVO;
import modelo.util.ehts.BusinessKey;
import modelo.util.ehts.Method;
import javax.validation.constraints.Size;

/**
 * @author bc
 */
@Embeddable
public class Movimiento extends BeanVO implements Serializable {

//    /**
//     *  PK autoincremtado
//     */
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column
//    @BusinessKey(include = Method.TO_STRING)
//    private Long id;
    /**
     * Banco y numero de cuenta al que se va a hacer el pago, deposito o transferencia
     */
    @ManyToOne()
    @BusinessKey
    private CuentaBancariaPersona cuentaBancaria;
    /**
     * cheque, deposito, transferencia
     */
    @ManyToOne()
    @BusinessKey
    private TipoDocumentoPago documentoPago;
    /**
     * numero de deposito, cheque, transferencia
     */
    @Column
    @BusinessKey
    private String numeroReferencia;
    /**
     * descripcion del movimiento
     */
    @Column
    //@Size(min = 2, max = 120)
    @BusinessKey
    private String descripcion;
    /**
     * 
     */
    @Column
    @BusinessKey
    private Double ingreso;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double egreso;
    /**
     *
     */
    @Column
    @BusinessKey
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaEmision;
    /**
     *
     */
    @Column
    @BusinessKey
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaPago;
//    /**
//     */
//    @Version
//    @Column
//    private Integer optLock;
//    /**
//     */
//    @Embedded
//    @BusinessKey
//    private AuditoriaBasica auditoria;

    public Movimiento() {
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public AuditoriaBasica getAuditoria() {
//        return auditoria;
//    }
//
//    public void setAuditoria(AuditoriaBasica auditoria) {
//        this.auditoria = auditoria;
//    }
//    public Integer getOptLock() {
//        return optLock;
//    }
//
//    public void setOptLock(Integer optLock) {
//        this.optLock = optLock;
//    }
    public Double getEgreso() {
        return egreso;
    }

    public void setEgreso(Double egreso) {
        this.egreso = egreso;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Double getIngreso() {
        return ingreso;
    }

    public void setIngreso(Double ingreso) {
        this.ingreso = ingreso;
    }

    public CuentaBancariaPersona getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(CuentaBancariaPersona cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoDocumentoPago getDocumentoPago() {
        return documentoPago;
    }

    public void setDocumentoPago(TipoDocumentoPago documentoPago) {
        this.documentoPago = documentoPago;
    }

    public String getNumeroReferencia() {
        return numeroReferencia;
    }

    public void setNumeroReferencia(String numeroReferencia) {
        this.numeroReferencia = numeroReferencia;
    }
}
