package modelo.entidades.sms;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
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
import javax.validation.constraints.Size;

@Entity
public class SMSEntrada extends BeanVO implements Serializable {

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
    private String para;
    /**
     * Nombre de quien lo envia 
     */
    @Column
    @BusinessKey
    private String de;

    /**
     *
     */
    @Column
    @Size(min = 1, max = 160)
    @BusinessKey
    private String texto;
    /**
     *  
     */
    @Column
    @Size(min = 1, max = 60)
    @BusinessKey
    private String telefono;
    /**
     *
     */
    @Column
    @BusinessKey
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fecha;
    /**
     *
     */
    @Version
    @Column
    private Integer optLock;

    public SMSEntrada() {
    }

    public SMSEntrada(String nombre, String telefono, String texto, Date fecha) {
        this.para = nombre;
        this.telefono = telefono;
        this.texto = texto;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        
        this.fecha = fecha;
    }

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

}
