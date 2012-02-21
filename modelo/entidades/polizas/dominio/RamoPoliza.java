package modelo.entidades.polizas.dominio;

import java.io.Serializable;
import java.util.HashSet;
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
import javax.persistence.OneToMany;
import javax.persistence.Version;
import modelo.Dominios.RamoContable;
import modelo.Dominios.TipoRamo;
import modelo.entidades.auditoria.Auditable;
import modelo.entidades.auditoria.AuditoriaBasica;
import modelo.util.bean.BeanVO;
import modelo.util.ehts.BusinessKey;
import modelo.util.ehts.Method;
import javax.validation.constraints.Size;
import modelo.entidades.polizas.recibos.cobertura.Cobertura;

@Entity
public class RamoPoliza extends BeanVO implements Serializable, Auditable {

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
    @Column
    @Size(min = 2, max = 240)
    @BusinessKey
    private String nombre;
    /**
     *
     */
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private String nombreCorto;
    /**
     *
     */
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private String descripcion;
    /**
     * Describe si es individual o colectivo
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private TipoRamo tipoRamo;
    /**
     * PERSONA, VEHICULO, RAMOS_GENERALES, FIANZA
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private RamoContable ramoContable;
    /**
     *
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ramo")
    @BusinessKey(exclude = Method.ALL)
    private Set<Cobertura> coberturas = new HashSet<Cobertura>(0);
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

    public RamoPoliza() {
    }

    public RamoPoliza(String nombre, String nombreCorto, TipoRamo tipoRamo, RamoContable ramoContable, AuditoriaBasica auditoria) {
        this.nombre = nombre;
        this.nombreCorto = nombreCorto;
        this.tipoRamo = tipoRamo;
        this.ramoContable = ramoContable;
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

    public String getNombreCorto() {
        return nombreCorto;
    }

    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    public Integer getOptLock() {
        return optLock;
    }

    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    public TipoRamo getTipoRamo() {
        return tipoRamo;
    }

    public void setTipoRamo(TipoRamo tipoRamo) {
        this.tipoRamo = tipoRamo;
    }

    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    public RamoContable getRamoContable() {
        return ramoContable;
    }

    public void setRamoContable(RamoContable ramoContable) {
        this.ramoContable = ramoContable;
    }

    public Set<Cobertura> getCoberturas() {
        return coberturas;
    }

    public void setCoberturas(Set<Cobertura> coberturas) {
        this.coberturas = coberturas;
    }
    
}
