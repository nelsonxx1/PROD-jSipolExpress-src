package modelo.entidades.administracion;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import modelo.Dominios.TipoDocumentoPago;
import modelo.Dominios.TipoMovimientoCaja;
import modelo.entidades.Documento;
import modelo.entidades.auditoria.Auditable;
import modelo.entidades.auditoria.AuditoriaBasica;
import modelo.entidades.personas.maestra.Persona;
import modelo.entidades.personas.transac.CuentaBancariaPersona;
import modelo.util.bean.BeanVO;
import modelo.util.ehts.BusinessKey;
import modelo.util.ehts.Method;

/**
 *
 * @author orlandobcrra
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Movimiento extends BeanVO implements Auditable {

    /**
     * Pk autoincrementado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
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
    @ManyToOne
    @NotNull
    @BusinessKey(include = Method.TO_STRING)
    private CuentaBancariaPersona cuenta;
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
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private TipoMovimientoCaja tipoMovimiento;
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
    private Double neutro;
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
    @Column
    @BusinessKey
    private String descripcion;
    /**
     *
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private TipoDocumentoPago tipoDocumento;
    /**
     *
     */
    @Column
    @BusinessKey
    private String numero;
    /**
     *
     */
    @ManyToOne
    @BusinessKey(include = Method.TO_STRING)
    private Documento documento;
    /**
     * asociado a una factura o no
     */
    @Column
    @BusinessKey
    private Boolean extraordinario;
    /**
     */
    @Version
    @Column
    private Integer optLock;
    /**
     * Auditoria Basica de los Registros
     */
    @Embedded
    @BusinessKey
    private AuditoriaBasica auditoria;

    public Movimiento() {
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public Double getEgreso() {
        return egreso;
    }

    public void setEgreso(Double egreso) {
        this.egreso = egreso;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getIngreso() {
        return ingreso;
    }

    public void setIngreso(Double ingreso) {
        this.ingreso = ingreso;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoDocumentoPago getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumentoPago tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public TipoMovimientoCaja getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(TipoMovimientoCaja tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public Persona getContribuyente() {
        return contribuyente;
    }

    public void setContribuyente(Persona contribuyente) {
        this.contribuyente = contribuyente;
    }

    public CuentaBancariaPersona getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaBancariaPersona cuenta) {
        this.cuenta = cuenta;
    }

    public Boolean getExtraordinario() {
        return extraordinario;
    }

    public void setExtraordinario(Boolean extraordinario) {
        this.extraordinario = extraordinario;
    }

    public Double getNeutro() {
        return neutro;
    }

    public void setNeutro(Double neutro) {
        this.neutro = neutro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
}
