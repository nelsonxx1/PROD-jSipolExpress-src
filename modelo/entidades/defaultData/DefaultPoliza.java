package modelo.entidades.defaultData;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import modelo.entidades.auditoria.Auditable;
import modelo.entidades.auditoria.AuditoriaBasica;
import modelo.entidades.personas.maestra.Persona;
import modelo.entidades.polizas.dominio.GrupoPoliza;
import modelo.entidades.vehiculos.maestra.Vehiculo;
import modelo.util.bean.BeanVO;
import modelo.util.ehts.BusinessKey;
import modelo.util.ehts.Method;

/**
 *
 * @author bc
 */
@Entity
public class DefaultPoliza extends BeanVO implements Serializable, Auditable {

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
    private GrupoPoliza grupoPoliza;
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
    @ManyToOne()
    @BusinessKey
    private Vehiculo vehiculo;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Persona getProductor() {
        return productor;
    }

    public void setProductor(Persona productor) {
        this.productor = productor;
    }

    public GrupoPoliza getGrupoPoliza() {
        return grupoPoliza;
    }

    public void setGrupoPoliza(GrupoPoliza grupoPoliza) {
        this.grupoPoliza = grupoPoliza;
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

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
}
