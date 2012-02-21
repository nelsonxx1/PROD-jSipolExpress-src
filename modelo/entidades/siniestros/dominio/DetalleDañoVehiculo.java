package modelo.entidades.siniestros.dominio;

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
import modelo.util.bean.BeanVO;
import modelo.util.ehts.BusinessKey;
import modelo.util.ehts.Method;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

/**
 *
 * @author bc
 */
@Entity
public class DetalleDañoVehiculo extends BeanVO implements Serializable, Auditable {

    /**
     *  PK autoincremtado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     * Nombre
     */
    @Column
    @Size(min = 4, max = 120)
    @BusinessKey
    private String nombre;
    /**
     * Daño del Vehiculo
     */
    @ManyToOne
    @NotNull
    //TODO bussines key
    private DañoVehiculo dano;
    /**
     *
     */
    private transient String nombreCompleto;
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

    public DetalleDañoVehiculo() {
    }

    public DetalleDañoVehiculo(String nombre, DañoVehiculo dano, AuditoriaBasica auditoria) {
        this.nombre = nombre;
        this.dano = dano;
        this.auditoria = auditoria;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getOptLock() {
        return optLock;
    }

    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getNombreCompleto() {
        if (id != null) {
            return getDano().getNombre() + " - " + getNombre();
        } else {
            return nombreCompleto;
        }
    }

    /**
     * @return the dano
     */
    public DañoVehiculo getDano() {
        return dano;
    }

    /**
     * @param dano the dano to set
     */
    public void setDano(DañoVehiculo dano) {
        this.dano = dano;
    }    
    
}
