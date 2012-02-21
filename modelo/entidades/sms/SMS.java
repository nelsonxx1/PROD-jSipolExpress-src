package modelo.entidades.sms;

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
import javax.persistence.Transient;
import javax.persistence.Version;
import modelo.entidades.auditoria.Auditable;
import modelo.entidades.auditoria.AuditoriaBasica;
import modelo.util.bean.BeanVO;
import modelo.util.ehts.BusinessKey;
import modelo.util.ehts.Method;
import javax.validation.constraints.Size;
import modelo.Dominios.EstatusSMS;
import modelo.entidades.personas.maestra.Persona;

@Entity
public class SMS extends BeanVO implements Serializable, Auditable {

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
    @BusinessKey
    private String nombre;
    /**
     *
     */
    @Column
    @Size(min = 1, max = 255)
    @BusinessKey
    private String productorNombre;
    /**
     *
     */
    @Column
    @Size(min = 1, max = 6000)
    @BusinessKey
    private String numero;
    /**
     *
     */
    @Column
    @BusinessKey
    private String motivo;
    /**
     *
     */
    @Column
    @BusinessKey
    @Enumerated(EnumType.STRING)
    private EstatusSMS estatus;
    /**
     * texto
     */
    @Column
    @Size(min = 1, max = 255)
    @BusinessKey
    private String texto;
    /**
     *
     */
    @Column
    @BusinessKey
    private Integer caracteres;
    /**
     *
     */
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    //@Past
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
    @Transient
    private Persona persona;

    public SMS() {
        estatus = EstatusSMS.PENDIENTE;
    }

    public SMS(Persona persona) {
        estatus = EstatusSMS.PENDIENTE;
        this.persona=persona;
    }

    public SMS(String nombre, String productorNombre, String numero, String motivo, String texto, Date fecha, AuditoriaBasica auditoria) {
        this.nombre = nombre;
        this.productorNombre = productorNombre;
        this.numero = numero;
        this.motivo = motivo;
        this.texto = texto;
        this.fecha = fecha;
        this.auditoria = auditoria;
        this.caracteres = texto.length();
    }

    public SMS(String nombre, String productorNombre, String numero, String motivo, String texto, Date fecha, AuditoriaBasica auditoria, Persona persona) {
        this.nombre = nombre;
        this.productorNombre = productorNombre;
        this.numero = numero;
        this.motivo = motivo;
        this.texto = texto;
        this.fecha = fecha;
        this.auditoria = auditoria;
        this.caracteres = texto.length();
        this.persona = persona;
    }

    public SMS(String nombre, String productorNombre, String numero, String motivo, String texto, Date fecha, EstatusSMS estatus, AuditoriaBasica auditoria) {
        this.nombre = nombre;
        this.productorNombre = productorNombre;
        this.numero = numero;
        this.motivo = motivo;
        this.estatus = estatus;
        this.texto = texto;
        this.fecha = fecha;
        this.auditoria = auditoria;
        this.caracteres = texto.length();
    }

    public SMS(String nombre, String productorNombre, String numero, String motivo, String texto, Date fecha, EstatusSMS estatus, AuditoriaBasica auditoria, Persona persona) {
        this.nombre = nombre;
        this.productorNombre = productorNombre;
        this.numero = numero;
        this.motivo = motivo;
        this.estatus = estatus;
        this.texto = texto;
        this.fecha = fecha;
        this.auditoria = auditoria;
        this.caracteres = texto.length();
        this.persona = persona;
    }

//    public SMS(String nombre, String numero, String motivo, String texto, Integer caracteres, Date fecha, AuditoriaBasica auditoria) {
//        this.nombre = nombre;
//        this.numero = numero;
//        this.motivo = motivo;
//        this.texto = texto;
//        this.caracteres = caracteres;
//        this.fecha = fecha;
//        this.auditoria = auditoria;
//        estatus = EstatusSMS.PENDIENTE;
//    }
//
//    public SMS(String nombre, String numero, String motivo, String texto, Integer caracteres, Date fecha, EstatusSMS estatus, AuditoriaBasica auditoria) {
//        this.nombre = nombre;
//        this.numero = numero;
//        this.motivo = motivo;
//        this.texto = texto;
//        this.caracteres = caracteres;
//        this.fecha = fecha;
//        this.auditoria = auditoria;
//        this.estatus = estatus;
//    }
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Integer getOptLock() {
        return optLock;
    }

    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    public Integer getCaracteres() {
        return caracteres;
    }

    public void setCaracteres(Integer caracteres) {
        this.caracteres = caracteres;
    }

    public EstatusSMS getEstatus() {
        return estatus;
    }

    public void setEstatus(EstatusSMS estatus) {
        this.estatus = estatus;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getProductorNombre() {
        return productorNombre;
    }

    public void setProductorNombre(String productorNombre) {
        this.productorNombre = productorNombre;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
