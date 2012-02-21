package modelo.entidades.administracion;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import modelo.Dominios.TipoFactura;
import modelo.entidades.auditoria.AuditoriaBasica;
import modelo.entidades.auditoria.Auditable;
import modelo.entidades.personas.maestra.Persona;
import modelo.util.bean.BeanVO;
import modelo.util.ehts.BusinessKey;
import modelo.util.ehts.Method;

/**
 *
 * @author orlandobcrra
 */
@Entity
public class Factura2 extends BeanVO implements Auditable {

    /**
     * PK autoincrementado
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
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private TipoFactura tipoFactura;
    /**
     *
     */
    @ManyToOne
    @NotNull
    @BusinessKey(include = Method.TO_STRING)
    private Persona contribuyente;
    /**
     *
     */
    @Column
    @BusinessKey
    private String descripcion;
    /**
     *
     */
    @Column
    @BusinessKey
    private Boolean incluirLibro;
    /**
     *
     */
    @Column
    @BusinessKey
    private Boolean cuentaX;
    /**
     *
     */
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    @BusinessKey
    private Date fecha;
    /**
     *
     */
    @ManyToOne
    @BusinessKey(include = Method.TO_STRING)
    private Persona razonSocial;
    /**
     *
     */
    @Column
    @BusinessKey
    private String rif;
    /**
     *
     */
    @Column
    @BusinessKey
    private String nombre;
    /**
     *
     */
    @Column
    @BusinessKey
    private String numeroControl;
    /**
     *
     */
    @Column
    @BusinessKey
    private String numeroFactura;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double totalConIVA;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double totalVentaNoGrabadas;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double totalCompraSinDerecho;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double totalCompraExenta;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double totalCompraNoSujeto;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double base12;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double iva12;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double base8;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double iva8;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double base22;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double iva22;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double ivaRetenido;
    /**
     *
     */
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    @BusinessKey
    private Date fechaRetencion;
    /**
     *
     */
    @Column
    @BusinessKey
    private String numeroRetencion;
    /**
     *
     */
    @Column
    @BusinessKey
    private Boolean anulado;
    /**
     *
     */
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    @BusinessKey
    private Date fechaAnulacion;
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
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private Set<Movimiento> pagos = new HashSet<Movimiento>(0);

    public Factura2() {
        incluirLibro=false;
        cuentaX=false;
        totalCompraExenta=0.0;
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

    public Integer getOptLock() {
        return optLock;
    }

    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    public Double getBase12() {
        return base12;
    }

    public void setBase12(Double base12) {
        this.base12 = base12;
    }

    public Double getBase22() {
        return base22;
    }

    public void setBase22(Double base22) {
        this.base22 = base22;
    }

    public Double getBase8() {
        return base8;
    }

    public void setBase8(Double base8) {
        this.base8 = base8;
    }

    public Persona getContribuyente() {
        return contribuyente;
    }

    public void setContribuyente(Persona contribuyente) {
        this.contribuyente = contribuyente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFechaRetencion() {
        return fechaRetencion;
    }

    public void setFechaRetencion(Date fechaRetencion) {
        this.fechaRetencion = fechaRetencion;
    }

    public Boolean getIncluirLibro() {
        return incluirLibro;
    }

    public void setIncluirLibro(Boolean incluirLibro) {
        this.incluirLibro = incluirLibro;
    }

    public Double getIva12() {
        return iva12;
    }

    public void setIva12(Double iva12) {
        this.iva12 = iva12;
    }

    public Double getIva22() {
        return iva22;
    }

    public void setIva22(Double iva22) {
        this.iva22 = iva22;
    }

    public Double getIva8() {
        return iva8;
    }

    public void setIva8(Double iva8) {
        this.iva8 = iva8;
    }

    public Double getIvaRetenido() {
        return ivaRetenido;
    }

    public void setIvaRetenido(Double ivaRetenido) {
        this.ivaRetenido = ivaRetenido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getNumeroRetencion() {
        return numeroRetencion;
    }

    public void setNumeroRetencion(String numeroRetencion) {
        this.numeroRetencion = numeroRetencion;
    }

    public Persona getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(Persona razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRif() {
        return rif;
    }

    public void setRif(String rif) {
        this.rif = rif;
    }

    public Double getTotalCompraExenta() {
        return totalCompraExenta;
    }

    public void setTotalCompraExenta(Double totalCompraExenta) {
        this.totalCompraExenta = totalCompraExenta;
    }

    public Double getTotalCompraNoSujeto() {
        return totalCompraNoSujeto;
    }

    public void setTotalCompraNoSujeto(Double totalCompraNoSujeto) {
        this.totalCompraNoSujeto = totalCompraNoSujeto;
    }

    public Double getTotalCompraSinDerecho() {
        return totalCompraSinDerecho;
    }

    public void setTotalCompraSinDerecho(Double totalCompraSinDerecho) {
        this.totalCompraSinDerecho = totalCompraSinDerecho;
    }

    public Double getTotalConIVA() {
        return totalConIVA;
    }

    public void setTotalConIVA(Double totalConIVA) {
        this.totalConIVA = totalConIVA;
    }

    public Double getTotalVentaNoGrabadas() {
        return totalVentaNoGrabadas;
    }

    public void setTotalVentaNoGrabadas(Double totalVentaNoGrabadas) {
        this.totalVentaNoGrabadas = totalVentaNoGrabadas;
    }

    public TipoFactura getTipoFactura() {
        return tipoFactura;
    }

    public void setTipoFactura(TipoFactura tipoFactura) {
        this.tipoFactura = tipoFactura;
    }

    public Boolean getCuentaX() {
        return cuentaX;
    }

    public void setCuentaX(Boolean cuentaX) {
        this.cuentaX = cuentaX;
    }

    public Boolean getAnulado() {
        return anulado;
    }

    public void setAnulado(Boolean anulado) {
        this.anulado = anulado;
    }

    public Date getFechaAnulacion() {
        return fechaAnulacion;
    }

    public void setFechaAnulacion(Date fechaAnulacion) {
        this.fechaAnulacion = fechaAnulacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Movimiento> getPagos() {
        return pagos;
    }

    public void setPagos(Set<Movimiento> pagos) {
        this.pagos = pagos;
    }
}
