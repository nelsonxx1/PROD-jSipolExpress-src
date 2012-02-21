package modelo.entidades.polizas.recibos.devolucion.maestra;

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
import javax.persistence.Temporal;
import javax.persistence.Version;
import modelo.entidades.auditoria.Auditable;
import modelo.entidades.auditoria.AuditoriaBasica;
import modelo.util.bean.BeanVO;
import modelo.util.ehts.BusinessKey;
import modelo.util.ehts.Method;
import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import modelo.Dominios.TipoDevolucion;

@Entity
public class Devolucion extends BeanVO implements Serializable, Auditable {

    /**
     * Pk autogenerado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     */
    @Column
    @BusinessKey
    private String numero;
    /**
     * Asegurado/Cliente, Aseguradora, Asegurador
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private TipoDevolucion tipo;
    /**
     * Total de la prima a devolver
     */
    @Column
    @BusinessKey
    private Double primaDevuelta;
    /**
     * parte de la prima. talvez menos la comision del productor
     */
    @Column
    @Min(1)
    @BusinessKey
    private Double montoDevuelto;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double extornoComision;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double extornoCobrador;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double extornoBono1;
    /**
     *
     */
    @Column
    @BusinessKey
    private Double extornoBono2;
    /**
     *
     */
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    @Past
    @BusinessKey
    private Date fecha;
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

    public Devolucion() {
    }

    public AuditoriaBasica getAuditoria() {
        return auditoria;
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Double getExtornoCobrador() {
        return extornoCobrador;
    }

    public void setExtornoCobrador(Double extornoCobrador) {
        this.extornoCobrador = extornoCobrador;
    }

    public Double getExtornoComision() {
        return extornoComision;
    }

    public void setExtornoComision(Double extornoComision) {
        this.extornoComision = extornoComision;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getMontoDevuelto() {
        return montoDevuelto;
    }

    public void setMontoDevuelto(Double montoDevuelto) {
        this.montoDevuelto = montoDevuelto;
    }

    public Double getPrimaDevuelta() {
        return primaDevuelta;
    }

    public void setPrimaDevuelta(Double primaDevuelta) {
        this.primaDevuelta = primaDevuelta;
    }

    public TipoDevolucion getTipo() {
        return tipo;
    }

    public void setTipo(TipoDevolucion tipo) {
        this.tipo = tipo;
    }


    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    public Double getExtornoBono1() {
        return extornoBono1;
    }

    public void setExtornoBono1(Double extornoBono1) {
        this.extornoBono1 = extornoBono1;
    }

    public Double getExtornoBono2() {
        return extornoBono2;
    }

    public void setExtornoBono2(Double extornoBono2) {
        this.extornoBono2 = extornoBono2;
    }
    
}
