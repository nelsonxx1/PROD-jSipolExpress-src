

package modelo.entidades.vehiculos.maestra;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.Version;
import modelo.entidades.Documento;
import modelo.entidades.Observacion;
import modelo.entidades.auditoria.Auditable;
import modelo.entidades.auditoria.AuditoriaBasica;
import modelo.entidades.vehiculos.dominio.ClasificacionVehiculo;
import modelo.entidades.vehiculos.dominio.ModeloVehiculo;
import modelo.entidades.vehiculos.dominio.TipoColor;
import modelo.util.bean.BeanVO;
import modelo.util.ehts.BusinessKey;
import modelo.util.ehts.Method;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import modelo.entidades.personas.maestra.Persona;
import org.hibernate.validator.constraints.Range;

@Entity
public class Vehiculo extends BeanVO implements Serializable, Auditable {

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
    private Persona banco;
    /**
     *
     */
    @Column(unique = true)
    @Size(min = 4, max = 10)
    @BusinessKey
    private String placa;
    /**
     *
     */
    @Column
    @Range(min = 1900, max = 2050)
    @BusinessKey
    private Integer ayo;
    /**
     */
    @Column
    private Integer numeroPuestos;
    /**
     *
     */
    @Column
    @Size(max = 40)
    @BusinessKey
    private String serialMotor;
    /**
     *
     */
    @Column
    @Size(max = 40)
    @BusinessKey
    private String serialCarroceria;
    /**
     *
     */
    @ManyToOne()
    @NotNull
    @BusinessKey
    private TipoColor color;
    /**
     *
     */
    @ManyToOne()
    @NotNull
    @BusinessKey
    private ModeloVehiculo marcaModelo;
    /**
     *
     */
    @Column
    @BusinessKey
    private String version;
    /**
     *
     */
    @ManyToOne()
    @NotNull
    @BusinessKey
    private ClasificacionVehiculo clasificacion;
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

    public Vehiculo() {
    }

    public Set<Documento> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(Set<Documento> documentos) {
        this.documentos = documentos;
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

    public List<Observacion> getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(List<Observacion> observaciones) {
        this.observaciones = observaciones;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getSerialCarroceria() {
        return serialCarroceria;
    }

    public void setSerialCarroceria(String serialCarroceria) {
        this.serialCarroceria = serialCarroceria;
    }

    public String getSerialMotor() {
        return serialMotor;
    }

    public void setSerialMotor(String serialMotor) {
        this.serialMotor = serialMotor;
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

    public Integer getAyo() {
        return ayo;
    }

    public void setAyo(Integer ayo) {
        this.ayo = ayo;
    }

    public TipoColor getColor() {
        return color;
    }

    public void setColor(TipoColor color) {
        this.color = color;
    }

    public ClasificacionVehiculo getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(ClasificacionVehiculo clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getNumeroPuestos() {
        return numeroPuestos;
    }

    public void setNumeroPuestos(Integer numeroPuestos) {
        this.numeroPuestos = numeroPuestos;
    }

    public Persona getBanco() {
        return banco;
    }

    public void setBanco(Persona banco) {
        this.banco = banco;
    }
}
