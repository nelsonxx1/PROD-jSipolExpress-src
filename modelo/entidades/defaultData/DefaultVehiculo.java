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
import modelo.entidades.vehiculos.dominio.ClasificacionVehiculo;
import modelo.entidades.vehiculos.dominio.ModeloVehiculo;
import modelo.entidades.vehiculos.dominio.TipoColor;
import modelo.util.bean.BeanVO;
import modelo.util.ehts.BusinessKey;
import modelo.util.ehts.Method;

/**
 *
 * @author bc
 */
@Entity
public class DefaultVehiculo extends BeanVO implements Serializable, Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    @ManyToOne()
    @BusinessKey
    private TipoColor color;
    @ManyToOne()
    @BusinessKey
    private ModeloVehiculo marcaModelo;
    @ManyToOne()
    @BusinessKey
    private ClasificacionVehiculo clasificacionVehiculo;
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

    public ClasificacionVehiculo getClasificacionVehiculo() {
        return clasificacionVehiculo;
    }

    public void setClasificacionVehiculo(ClasificacionVehiculo clasificacionVehiculo) {
        this.clasificacionVehiculo = clasificacionVehiculo;
    }

    public TipoColor getColor() {
        return color;
    }

    public void setColor(TipoColor color) {
        this.color = color;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ModeloVehiculo getMarcaModelo() {
        return marcaModelo;
    }

    public void setMarcaModelo(ModeloVehiculo marcaModelo) {
        this.marcaModelo = marcaModelo;
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
}
