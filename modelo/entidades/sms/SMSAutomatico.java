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
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.Version;
import modelo.entidades.auditoria.Auditable;
import modelo.entidades.auditoria.AuditoriaBasica;
import modelo.util.bean.BeanVO;
import modelo.util.ehts.BusinessKey;
import modelo.util.ehts.Method;
import modelo.Dominios.EstatusSMS;
import modelo.Dominios.TipoSMS;
import modelo.entidades.personas.maestra.Persona;

@Entity
public class SMSAutomatico extends BeanVO implements Serializable, Auditable {

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
    @Column
    @BusinessKey
    @Enumerated(EnumType.STRING)
    private TipoSMS tipo;
    /**
     *
     */
    @ManyToOne
    @BusinessKey
    private SMSPreEscrito mensaje;
    /**
     *
     */
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    @BusinessKey
    private Date fechaEstatus;
    /**
     *
     */
    @Column
    @BusinessKey
    private Integer diasAnticipo;
    /**
     *
     */
    @Column
    @BusinessKey
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date hora;
    /**
     *
     */
    @Column
    @BusinessKey
    @Enumerated(EnumType.STRING)
    private EstatusSMS estatus;
    /**
     *
     */
    @ManyToOne
    @BusinessKey(exclude = Method.ALL)
    private Persona productor;
    /**
     *
     */
    @ManyToOne
    //@NotNull
    @BusinessKey(exclude = Method.ALL)
    private SMSMasa masa;
    /**
     *
     */
    @Version
    @Column
    private Integer optLock;
    /**
     */
    @Embedded
    @BusinessKey
    private AuditoriaBasica auditoria;

    public SMSAutomatico() {
        fechaEstatus = new Date();
        estatus = EstatusSMS.PENDIENTE;
    }

    public Date getFechaEstatus() {
        return fechaEstatus;
    }

    public void setFechaEstatus(Date fechaEstatus) {
        this.fechaEstatus = fechaEstatus;
    }

    public Integer getOptLock() {
        return optLock;
    }

    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SMSMasa getMasa() {
        return masa;
    }

    public void setMasa(SMSMasa masa) {
        this.masa = masa;
    }

    public Integer getDiasAnticipo() {
        return diasAnticipo;
    }

    public void setDiasAnticipo(Integer diasAnticipo) {
        this.diasAnticipo = diasAnticipo;
    }

    public EstatusSMS getEstatus() {
        return estatus;
    }

    public void setEstatus(EstatusSMS estatus) {
        this.estatus = estatus;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public SMSPreEscrito getMensaje() {
        return mensaje;
    }

    public void setMensaje(SMSPreEscrito mensaje) {
        this.mensaje = mensaje;
    }

    public Persona getProductor() {
        return productor;
    }

    public void setProductor(Persona productor) {
        this.productor = productor;
    }

    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    public TipoSMS getTipo() {
        return tipo;
    }

    public void setTipo(TipoSMS tipo) {
        this.tipo = tipo;
    }
}
