

package modelo.entidades.polizas.maestra;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.Version;
import modelo.entidades.Documento;
import modelo.entidades.Observacion;
import modelo.entidades.auditoria.Auditable;
import modelo.entidades.auditoria.AuditoriaBasica;
import modelo.entidades.personas.maestra.Persona;
import modelo.entidades.polizas.dominio.GrupoPoliza;
import modelo.entidades.polizas.dominio.RamoPoliza;
import modelo.entidades.polizas.recibos.maestra.Recibo;
import modelo.util.bean.BeanVO;
import modelo.util.ehts.BusinessKey;
import modelo.util.ehts.Method;
import org.hibernate.annotations.Index;
import javax.validation.constraints.Size;

@Entity
public class Poliza extends BeanVO implements Serializable, Auditable {

    /**
     * Pk autogenerado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     * Codigo en Archivo
     */
    @Column
    @BusinessKey
    private String codigoArchivo;
    /**
     *
     */
    @ManyToOne()
    @BusinessKey
    private Persona compania;
    /**
     *
     */
    @ManyToOne()
    @BusinessKey
    private Persona contratante;
    /**
     *
     */
    @ManyToOne()
    @BusinessKey
    private Persona productor;
    /**
     *
     */
    @ManyToOne()
    @BusinessKey
    private Persona cobrador;
    /**
     *
     */
    @Column
    @Size(min = 2, max = 50)
    @Index(name = "numeroPolizaIndex")
    @BusinessKey
    private String numero;
    /**
     *
     */
    @Column
    @BusinessKey
    private String referencia;
    /**
     *
     */
    @ManyToOne()
    @BusinessKey
    private RamoPoliza ramoPoliza;
    /**
     *
     */
    @ManyToOne()
    @BusinessKey
    private GrupoPoliza grupoPoliza;
    /**
     *
     */
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    //@Past
    @BusinessKey
    private Date fechaComienzo;
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
    @OneToMany(mappedBy = "poliza", fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private Set<Recibo> recibos = new HashSet<Recibo>(0);
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
    @OrderBy(value = "auditoria.fechaInsert")
    private List<Observacion> observaciones = new ArrayList<Observacion>(0);

    public Poliza() {
    }

    public Boolean getAnulado() {
        return anulado;
    }

    public void setAnulado(Boolean anulado) {
        this.anulado = anulado;
    }

    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    public Persona getCobrador() {
        return cobrador;
    }

    public void setCobrador(Persona cobrador) {
        this.cobrador = cobrador;
    }

    public Persona getCompania() {
        return compania;
    }

    public void setCompania(Persona compania) {
        this.compania = compania;
    }

    public Persona getContratante() {
        return contratante;
    }

    public void setContratante(Persona contratante) {
        this.contratante = contratante;
    }

    public Set<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(Set<Documento> documentos) {
        this.documentos = documentos;
    }

    public Date getFechaAnulacion() {
        return fechaAnulacion;
    }

    public void setFechaAnulacion(Date fechaAnulacion) {
        this.fechaAnulacion = fechaAnulacion;
    }

    public Date getFechaComienzo() {
        return fechaComienzo;
    }

    public void setFechaComienzo(Date fechaComienzo) {
        this.fechaComienzo = fechaComienzo;
    }

    public GrupoPoliza getGrupoPoliza() {
        return grupoPoliza;
    }

    public void setGrupoPoliza(GrupoPoliza grupoPoliza) {
        this.grupoPoliza = grupoPoliza;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public List<Observacion> getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(List<Observacion> observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getOptLock() {
        return optLock;
    }

    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    public Persona getProductor() {
        return productor;
    }

    public void setProductor(Persona productor) {
        this.productor = productor;
    }

    public RamoPoliza getRamoPoliza() {
        return ramoPoliza;
    }

    public void setRamoPoliza(RamoPoliza ramoPoliza) {
        this.ramoPoliza = ramoPoliza;
    }

    public Set<Recibo> getRecibos() {
        return recibos;
    }

    public void setRecibos(Set<Recibo> recibos) {
        this.recibos = recibos;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getCodigoArchivo() {
        return codigoArchivo;
    }

    public void setCodigoArchivo(String codigoArchivo) {
        this.codigoArchivo = codigoArchivo;
    }
}
