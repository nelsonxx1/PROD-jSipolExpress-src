

package modelo.entidades.ordenes.maestra;

import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.Version;
import modelo.entidades.auditoria.Auditable;
import modelo.entidades.auditoria.AuditoriaBasica;
import modelo.util.bean.BeanVO;
import modelo.util.ehts.BusinessKey;
import modelo.util.ehts.Method;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OrderBy;
import modelo.Dominios.EstatusOrdenPago;
import modelo.Dominios.TipoDocumentoPago;
import modelo.entidades.Documento;
import modelo.entidades.Observacion;
import modelo.entidades.ordenes.dominio.TipoOrden;
import modelo.entidades.ordenes.transac.OrdenDePagoDetalle;
import modelo.entidades.personas.maestra.Persona;
import modelo.entidades.polizas.recibos.maestra.Recibo;

//TODO many to many con cuenta x pagar
@Entity
public class OrdenDePago extends BeanVO implements Serializable, Auditable {

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
    private TipoOrden tipo;
    /**
     */
    @Column
    @BusinessKey
    private String numeroO;
    /**
     *
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private EstatusOrdenPago estatus;
//    /**
//     *
//     */
//    @ManyToOne()
//    @BusinessKey
//    private Persona productor;
//    /**
//     *
//     */
//    @ManyToOne()
//    @BusinessKey
//    private CuentaBancariaPersona cuentaBancaria;
    /**
     *
     */
    @ManyToOne()
    @BusinessKey
    private Persona beneficiario;
    /**
     */
    @Column
    @BusinessKey
    private String nombreBeneficiario;
    /**
     */
    @Column
    @BusinessKey
    private String rifBeneficiario;
    /**
     *
     */
    @Column
    @BusinessKey
    private String observacion;
//    /**
//     */
//    @Column
//    @BusinessKey
//    private Boolean contabilizada;
//    /**
//     */
//    @Column
//    @BusinessKey
//    private String asientoContable;
    /**
     */
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    @Past
    @BusinessKey
    private Date fechaOrden;
    /**
     */
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    @Past
    @BusinessKey
    private Date fechaPago;
    /**
     *
     */
    @Column
    @BusinessKey
    private Boolean anuladoO;
    /**
     */
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    @Past
    @BusinessKey
    private Date fechaAnulacionO;
    /**
     *
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private TipoDocumentoPago tipoDocumentoPago;
    /**
     */
    @Column
    @BusinessKey
    private String numeroPago;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double montoPago;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double debitos;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double creditos;
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
    /**
     *
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private Set<OrdenDePagoDetalle> detalle = new HashSet<OrdenDePagoDetalle>(0);
    /**
     *
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private Set<Documento> documentos = new HashSet<Documento>(0);
    /**
     *
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    @OrderBy(value = "id")
    private List<Observacion> observaciones = new ArrayList<Observacion>(0);
    /**
     *
     */
    @OneToMany(mappedBy = "orden", fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private Set<Recibo> recibos = new HashSet<Recibo>(0);

    public OrdenDePago() {
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

    public Persona getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Persona beneficiario) {
        this.beneficiario = beneficiario;
    }

//    public Boolean getContabilizada() {
//        return contabilizada;
//    }
//
//    public void setContabilizada(Boolean contabilizada) {
//        this.contabilizada = contabilizada;
//    }
//
//        public String getAsientoContable() {
//        return asientoContable;
//    }
//
//    public void setAsientoContable(String asientoContable) {
//        this.asientoContable = asientoContable;
//    }
    public Double getCreditos() {
        return creditos;
    }

    public void setCreditos(Double creditos) {
        this.creditos = creditos;
    }

    public Double getDebitos() {
        return debitos;
    }

    public void setDebitos(Double debitos) {
        this.debitos = debitos;
    }

    public Set<OrdenDePagoDetalle> getDetalle() {
        return detalle;
    }

    public void setDetalle(Set<OrdenDePagoDetalle> detalle) {
        this.detalle = detalle;
    }

    public EstatusOrdenPago getEstatus() {
        return estatus;
    }

    public void setEstatus(EstatusOrdenPago estatus) {
        this.estatus = estatus;
    }

    public Boolean getAnuladoO() {
        return anuladoO;
    }

    public void setAnuladoO(Boolean anuladoO) {
        this.anuladoO = anuladoO;
    }

    public Date getFechaAnulacionO() {
        return fechaAnulacionO;
    }

    public void setFechaAnulacionO(Date fechaAnulacionO) {
        this.fechaAnulacionO = fechaAnulacionO;
    }

    public Date getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(Date fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public TipoDocumentoPago getTipoDocumentoPago() {
        return tipoDocumentoPago;
    }

    public void setTipoDocumentoPago(TipoDocumentoPago tipoDocumentoPago) {
        this.tipoDocumentoPago = tipoDocumentoPago;
    }

    public Double getMontoPago() {
        return montoPago;
    }

    public void setMontoPago(Double montoPago) {
        this.montoPago = montoPago;
    }

    public String getNombreBeneficiario() {
        return nombreBeneficiario;
    }

    public void setNombreBeneficiario(String nombreBeneficiario) {
        this.nombreBeneficiario = nombreBeneficiario;
    }

    public String getNumeroO() {
        return numeroO;
    }

    public void setNumeroO(String numeroO) {
        this.numeroO = numeroO;
    }

    public Integer getOptLock() {
        return optLock;
    }

    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    public String getNumeroPago() {
        return numeroPago;
    }

    public void setNumeroPago(String numeroPago) {
        this.numeroPago = numeroPago;
    }

    public String getRifBeneficiario() {
        return rifBeneficiario;
    }

    public void setRifBeneficiario(String rifBeneficiario) {
        this.rifBeneficiario = rifBeneficiario;
    }

    public TipoOrden getTipo() {
        return tipo;
    }

    public void setTipo(TipoOrden tipo) {
        this.tipo = tipo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

//    public Persona getProductor() {
//        return productor;
//    }
//
//    public void setProductor(Persona productor) {
//        this.productor = productor;
//    }
//    public CuentaBancariaPersona getCuentaBancaria() {
//        return cuentaBancaria;
//    }
//
//    public void setCuentaBancaria(CuentaBancariaPersona cuentaBancaria) {
//        this.cuentaBancaria = cuentaBancaria;
//    }
    public Set<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(Set<Documento> documentos) {
        this.documentos = documentos;
    }

    public List<Observacion> getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(List<Observacion> observaciones) {
        this.observaciones = observaciones;
    }

    public Set<Recibo> getRecibos() {
        return recibos;
    }

    public void setRecibos(Set<Recibo> recibos) {
        this.recibos = recibos;
    }
}
