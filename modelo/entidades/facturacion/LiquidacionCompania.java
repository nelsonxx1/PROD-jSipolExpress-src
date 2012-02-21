

package modelo.entidades.facturacion;

import java.util.ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import modelo.util.ehts.BusinessKey;
import modelo.util.ehts.Method;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import modelo.entidades.Documento;
import modelo.entidades.Observacion;
import modelo.entidades.auditoria.Auditable;
import modelo.entidades.personas.maestra.Persona;
import modelo.util.bean.BeanVO;
import javax.persistence.Version;
import modelo.entidades.auditoria.AuditoriaBasica;
import modelo.entidades.polizas.recibos.maestra.Recibo;

@Entity
public class LiquidacionCompania extends BeanVO implements Auditable {

//    public LiquidacionCompania() {
//        setSeleccionable(true);
//    }
    /**
     *  PK autoincremtado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     * Quien emite la factura
     */
    @ManyToOne
    @BusinessKey
    private Persona compania;
    /**
     * Quien emite la factura
     */
    @Embedded
    @BusinessKey
    private Movimiento pago;
    /**
     */
    @Column
    //@NotNull
    @BusinessKey
    private String numeroOrden;
    /**
     */
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    @Past
    @BusinessKey
    private Date fechaEmision2;
    /**
     */
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    @BusinessKey
    private Date fechaDesde;
    /**
     */
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    @BusinessKey
    private Date fechaHasta;
    /**
     */
    @Column
    @BusinessKey
    private Integer cantidadRecibos;
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
    private Set<DetalleLiquidacion> detalle = new HashSet<DetalleLiquidacion>(0);
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
    /**
     *
     */
    @OneToMany//(mappedBy = "poliza", fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private Set<Recibo> recibos = new HashSet<Recibo>(0);

    public Integer getCantidadRecibos() {
        return cantidadRecibos;
    }

    public void setCantidadRecibos(Integer cantidadRecibos) {
        this.cantidadRecibos = cantidadRecibos;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public Date getFechaEmision2() {
        return fechaEmision2;
    }

    public void setFechaEmision2(Date fechaEmision2) {
        this.fechaEmision2 = fechaEmision2;
    }

    public Set<DetalleLiquidacion> getDetalle() {
        return detalle;
    }

    public void setDetalle(Set<DetalleLiquidacion> detalle) {
        this.detalle = detalle;
    }

    public Persona getCompania() {
        return compania;
    }

    public void setCompania(Persona compania) {
        this.compania = compania;
    }

    public Set<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(Set<Documento> documentos) {
        this.documentos = documentos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Observacion> getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(List<Observacion> observaciones) {
        this.observaciones = observaciones;
    }

    public Movimiento getPago() {
        return pago;
    }

    public void setPago(Movimiento pago) {
        this.pago = pago;
    }

    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    public Integer getOptLock() {
        return optLock;
    }

    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    public Set<Recibo> getRecibos() {
        return recibos;
    }

    public void setRecibos(Set<Recibo> recibos) {
        this.recibos = recibos;
    }
}
