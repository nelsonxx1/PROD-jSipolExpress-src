package modelo.entidades.caja.dominio;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import modelo.entidades.auditoria.AuditoriaBasica;
import modelo.entidades.auditoria.Auditable;
import modelo.util.bean.BeanVO;
import modelo.util.ehts.BusinessKey;
import modelo.util.ehts.Method;
import javax.validation.constraints.Size;

/**
 * Clase Dominio para los tipos de actividad
 * economica que podria tener una persona
 * @version 1.0 22/05/2009
 * @since JDK 1.5
 * @author Orlando Becerra
 * @author Nelson Moncada
 */
@Entity
public class TipoMovimiento extends BeanVO implements Serializable, Auditable {

    /**
     *  PK autoincremtado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     * Nombre del tipo de Liquidación
     */
    @Column
    @Size(min = 4, max = 14)
    @BusinessKey
    private String idPropio;
    /**
     * Nombre Largo
     */
    @Column
    @Size(min = 4, max = 50)
    @BusinessKey
    private String nombre;
    /**
     * Si True=Debito False=Credito
     */
    @Column
    @BusinessKey
    private Boolean debitoOcredito;
    /**
     * Si True=Suma False=Resta
     */
    @Column
    @BusinessKey
    private Boolean sumaOresta;
    /**
     * Si True=Visible False=No Visible
     */
    @Column
    @BusinessKey
    private Boolean visible;
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

    public TipoMovimiento() {
    }

    public TipoMovimiento(String idPropio, String nombre, Boolean debitoOcredito, Boolean sumaOresta, Boolean visible, AuditoriaBasica auditoria) {
        this.idPropio = idPropio;
        this.nombre = nombre;
        this.debitoOcredito = debitoOcredito;
        this.sumaOresta = sumaOresta;
        this.visible = visible;
        this.auditoria = auditoria;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdPropio() {
        return idPropio;
    }

    public void setIdPropio(String idPropio) {
        this.idPropio = idPropio;
    }

    public Boolean getDebitoOcredito() {
        return debitoOcredito;
    }

    public void setDebitoOcredito(Boolean debitoOcredito) {
        this.debitoOcredito = debitoOcredito;
    }

    public Boolean getSumaOresta() {
        return sumaOresta;
    }

    public void setSumaOresta(Boolean sumaOresta) {
        this.sumaOresta = sumaOresta;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
}
