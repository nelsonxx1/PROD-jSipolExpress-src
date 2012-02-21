

package modelo.entidades.polizas.financiamiento;

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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
import modelo.entidades.polizas.recibos.maestra.Recibo;
import modelo.util.bean.BeanVO;
import modelo.util.ehts.BusinessKey;
import modelo.util.ehts.Method;
import javax.validation.constraints.Past;
import modelo.Dominios.EstatusFinanciamiento;

/**
 *
 * @author bc
 */
@Entity
public class Financiamiento extends BeanVO implements Serializable, Auditable {

    public Financiamiento() {
        inicialFinanciamiento = 0.0;
        montoFinanciamiento = 0.0;
        montoPagado = 0.0;

    }
    /**
     *  PK autoincremtado
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
    @BusinessKey
    private String numeroFF;
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
    @BusinessKey
    private Integer girosPagados;
    /**
     *
     */
    @ManyToOne()
    @BusinessKey
    private Persona financiadora;
    /*
     * 
     */
    @ManyToOne()
    @BusinessKey
    private Persona pagador;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double montoFinanciamiento;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double inicialFinanciamiento;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double montoPagado;
    /**
     *
     */
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    @Past
    @BusinessKey
    private Date fechaPagoInicial;
    /**
     *
     */
    @Column
    @BusinessKey
    private Boolean anuladoF;
    /**
     *
     */
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    @Past
    @BusinessKey
    private Date fechaAnulacionF;
    /**
     *
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private List<Giro> giros = new ArrayList<Giro>(0);
    /**
     * 
     */
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    @Past
    @BusinessKey
    private Date fechaPagoContrato;
    /**
     *
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private EstatusFinanciamiento estatus;
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
    @OneToMany(mappedBy = "financiamiento", fetch = FetchType.LAZY)
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getAnuladoF() {
        return anuladoF;
    }

    public void setAnuladoF(Boolean anuladoF) {
        this.anuladoF = anuladoF;
    }

    public Date getFechaAnulacionF() {
        return fechaAnulacionF;
    }

    public void setFechaAnulacionF(Date fechaAnulacionF) {
        this.fechaAnulacionF = fechaAnulacionF;
    }

    public Date getFechaPagoInicial() {
        return fechaPagoInicial;
    }

    public void setFechaPagoInicial(Date fechaPagoInicial) {
        this.fechaPagoInicial = fechaPagoInicial;
    }

    public Persona getFinanciadora() {
        return financiadora;
    }

    public void setFinanciadora(Persona financiadora) {
        this.financiadora = financiadora;
    }

    public List<Giro> getGiros() {
        //Collections.sort(giros);
        return giros;
    }

    public void setGiros(List<Giro> giros) {
        this.giros = giros;
    }

    public Double getInicialFinanciamiento() {
        return inicialFinanciamiento;
    }

    public void setInicialFinanciamiento(Double inicialFinanciamiento) {
        this.inicialFinanciamiento = inicialFinanciamiento;
    }

    public Set<Recibo> getRecibos() {
        return recibos;
    }

    public void setRecibos(Set<Recibo> recibos) {
        this.recibos = recibos;
    }

    public String getNumeroFF() {
        return numeroFF;
    }

    public void setNumeroFF(String numeroFF) {
        this.numeroFF = numeroFF;
    }

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

    public Persona getPagador() {
        return pagador;
    }

    public void setPagador(Persona pagador) {
        this.pagador = pagador;
    }

    public Date getFechaPagoContrato() {
        return fechaPagoContrato;
    }

    public void setFechaPagoContrato(Date fechaPagoContrato) {
        this.fechaPagoContrato = fechaPagoContrato;
    }

    public Integer getGirosPagados() {
        return girosPagados;
    }

    public void setGirosPagados(Integer girosPagados) {
        this.girosPagados = girosPagados;
    }

    public Double getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(Double montoPagado) {
        this.montoPagado = montoPagado;
    }

    public Double getMontoFinanciamiento() {
        return montoFinanciamiento;
    }

    public void setMontoFinanciamiento(Double montoFinanciamiento) {
        this.montoFinanciamiento = montoFinanciamiento;
    }

    public String getNumeroIngreso() {
        return numeroIngreso;
    }

    public void setNumeroIngreso(String numeroIngreso) {
        this.numeroIngreso = numeroIngreso;
    }

    public EstatusFinanciamiento getEstatus() {
        return estatus;
    }

    public void setEstatus(EstatusFinanciamiento estatus) {
        this.estatus = estatus;
    }
}
