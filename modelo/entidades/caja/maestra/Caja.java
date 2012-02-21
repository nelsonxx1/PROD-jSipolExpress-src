package modelo.entidades.caja.maestra;

import modelo.entidades.caja.transac.CajaDetalle;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.Temporal;
import javax.persistence.Version;
import modelo.entidades.auditoria.Auditable;
import modelo.entidades.auditoria.AuditoriaBasica;
import modelo.util.bean.BeanVO;
import modelo.entidades.personas.maestra.Persona;
import modelo.util.ehts.BusinessKey;
import modelo.util.ehts.Method;
import javax.validation.constraints.Past;

/**

 */
@Entity
public class Caja extends BeanVO implements Serializable, Auditable {

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
    private Persona compania;
    /**
     * Número Liquidación de Caja
     * Generado por Sistema:  AAMM-XXXXX
     */
    @Column
    @BusinessKey
    private String numero;
    /**
     * Fecha de Liquidación
     */
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    @Past
    @BusinessKey
    private Date fecha;
    /**
     * Estatus: Pendiente, Ingresada, Anulada
     */
    @Column
    @BusinessKey
    private String estatus;
    /**
     * Tipo de Liquidación
     * <p>
     * Ejemplo: Pólizas-Recibos/Contratos/Giros, Facturación, Pago Comisiones, Cheques Devueltos
     */
    @Column
    @BusinessKey
    private String tipo;
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
     *  Coleccion detalles Ingresos de Caja
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private Set<CajaDetalle> detalles = new HashSet<CajaDetalle>(0);
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

    public Caja() {
    }

    public Caja(Persona compania, String numero, Date fecha, String estatus, String tipo, Double debitos, Double creditos, AuditoriaBasica auditoria) {
        this.compania = compania;
        this.numero = numero;
        this.fecha = fecha;
        this.estatus = estatus;
        this.tipo = tipo;
        this.debitos = debitos;
        this.creditos = creditos;
        this.auditoria = auditoria;
    }

    public Persona getCompania() {
        return compania;
    }

    public void setCompania(Persona compania) {
        this.compania = compania;
    }

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

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getOptLock() {
        return optLock;
    }

    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public Set<CajaDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(Set<CajaDetalle> detalles) {
        this.detalles = detalles;
    }
}

