

package modelo.entidades.facturacion;

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
import javax.persistence.OrderBy;
import modelo.entidades.Documento;
import modelo.entidades.Observacion;
import modelo.entidades.caja.dominio.TipoDocumentoPago;
import modelo.entidades.personas.maestra.Persona;

@Entity
public class Factura extends BeanVO implements Serializable, Auditable {

    /**
     * Pk autogenerado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     * Quien emite la factura
     */
    @ManyToOne()
    @BusinessKey
    private Persona vendedor;
    /**
     * Quien paga la factura
     */
    @ManyToOne()
    @BusinessKey
    private Persona comprador;
    /**
     */
    @Column
    @BusinessKey
    private String numeroFactura;
    /**
     * 
     */
    @Column
    @BusinessKey
    private String numeroControl;
    /**
     * Fecha de emision
     */
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    @Past
    @BusinessKey
    private Date fechaEmision;
    /**
     * Efectivo, Cheque, TarjetaDebito, TarjetaCredito, OtrosExplique
     */
    @ManyToOne()
    @BusinessKey
    private TipoDocumentoPago documentoPago;
    /**
     * forma de pago otro!
     */
    @Column
    @BusinessKey
    private String otroExplique;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double montoFactura;
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
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private Set<DetalleFactura> detalle = new HashSet<DetalleFactura>(0);
    /**
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private Set<Movimiento> movimientos = new HashSet<Movimiento>(0);
    /**
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private Set<Documento> documentos = new HashSet<Documento>(0);
    /**
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    @OrderBy(value = "id")
    private List<Observacion> observaciones = new ArrayList<Observacion>(0);

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

    public Double getMontoFactura() {
        return montoFactura;
    }

    public void setMontoFactura(Double montoFactura) {
        this.montoFactura = montoFactura;
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

    public Set<DetalleFactura> getDetalle() {
        return detalle;
    }

    public void setDetalle(Set<DetalleFactura> detalle) {
        this.detalle = detalle;
    }

    public Set<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(Set<Documento> documentos) {
        this.documentos = documentos;
    }

    public String getOtroExplique() {
        return otroExplique;
    }

    public void setOtroExplique(String otroExplique) {
        this.otroExplique = otroExplique;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public TipoDocumentoPago getDocumentoPago() {
        return documentoPago;
    }

    public void setDocumentoPago(TipoDocumentoPago documentoPago) {
        this.documentoPago = documentoPago;
    }

    public List<Observacion> getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(List<Observacion> observaciones) {
        this.observaciones = observaciones;
    }

    public Persona getComprador() {
        return comprador;
    }

    public void setComprador(Persona comprador) {
        this.comprador = comprador;
    }

    public Persona getVendedor() {
        return vendedor;
    }

    public void setVendedor(Persona vendedor) {
        this.vendedor = vendedor;
    }

    public Set<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(Set<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }
}
