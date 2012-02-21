package modelo.entidades.polizas.recibos.maestra;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.Version;
import modelo.Dominios.Parentesco;
import modelo.entidades.auditoria.AuditoriaBasica;
import modelo.entidades.auditoria.Auditable;
import modelo.entidades.personas.maestra.PersonaNatural;
import modelo.util.bean.BeanVO;
import modelo.util.ehts.BusinessKey;
import modelo.util.ehts.Method;

/**
 * @author bc
 */
@Entity
public class Beneficiario extends BeanVO implements Serializable, Auditable {

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
    @ManyToOne()
    @BusinessKey
    private PersonaNatural beneficiario;
    /**
     *
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private Parentesco parentesco;
    /**
     *
     */
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    //@Past
    @BusinessKey
    private Date fechaInclusion;
    /**
     *
     */
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    //@Past
    @BusinessKey
    private Date fechaExclusion;
    /**
     *
     */
    @Column
    @BusinessKey
    private Boolean maternidad;
    /**
     *
     */
    @Column
    @BusinessKey
    private Boolean enPorcentaje;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double prima;
    /**
     *
     */
    @Column
    @BusinessKey
    private String observacion;
    /**
     * 
     */
    @Version
    @Column
    private Integer optLock;
    /**
     *
     */
    @Embedded
    @BusinessKey
    private AuditoriaBasica auditoria;

    public Beneficiario() {
        maternidad = false;
    }

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

    public PersonaNatural getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(PersonaNatural beneficiario) {
        this.beneficiario = beneficiario;
    }

    public Date getFechaExclusion() {
        return fechaExclusion;
    }

    public void setFechaExclusion(Date fechaExclusion) {
        this.fechaExclusion = fechaExclusion;
    }

    public Date getFechaInclusion() {
        return fechaInclusion;
    }

    public void setFechaInclusion(Date fechaInclusion) {
        this.fechaInclusion = fechaInclusion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Parentesco getParentesco() {
        return parentesco;
    }

    public void setParentesco(Parentesco parentesco) {
        this.parentesco = parentesco;
    }

    public Boolean getMaternidad() {
        return maternidad;
    }

    public void setMaternidad(Boolean maternidad) {
        this.maternidad = maternidad;
    }

    public Double getPrima() {
        return prima;
    }

    public void setPrima(Double prima) {
        this.prima = prima;
    }

    public Boolean getEnPorcentaje() {
        return enPorcentaje;
    }

    public void setEnPorcentaje(Boolean enPorcentaje) {
        this.enPorcentaje = enPorcentaje;
    }
    
}
