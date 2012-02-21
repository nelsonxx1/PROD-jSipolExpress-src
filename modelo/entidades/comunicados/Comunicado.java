package modelo.entidades.comunicados;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import modelo.Dominios.EstatusComunicado;
import modelo.entidades.Documento;
import modelo.entidades.personas.maestra.Persona;
import modelo.util.ehts.BusinessKey;
import javax.validation.constraints.Size;

/**
 * @author bc
 */
@Entity
public class Comunicado extends Documento {

    /**
     *
     */
    @ManyToOne()
    @BusinessKey
    private Persona compania;
    /**
     */
    @Column
    @Size(min = 2, max = 120)
    @BusinessKey
    private String departamento;
    /**
     */
    @Column
    @Size(min = 2, max = 120)
    @BusinessKey
    private String persona;
    /**
     */
    @Column
    @Size(min = 0, max = 120)
    @BusinessKey
    private String asunto;
    /**
     */
    @Column
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @BusinessKey
    private Date fechaEntrega;
    /**
     */
    @Column
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @BusinessKey
    private Date fechaRespuesta;
    /**
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private EstatusComunicado estatus;

    public Comunicado() {
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public Persona getCompania() {
        return compania;
    }

    public void setCompania(Persona compania) {
        this.compania = compania;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public EstatusComunicado getEstatus() {
        return estatus;
    }

    public void setEstatus(EstatusComunicado estatus) {
        this.estatus = estatus;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(Date fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }
}
