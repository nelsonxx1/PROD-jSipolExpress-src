package modelo.entidades.cuentasPorPagar.dominio;

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

/**
 */
@Entity
public class TipoEnriquecimientoDetalle extends BeanVO implements Serializable, Auditable {

    /**
     * PK autoincrementado
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
    private Double montoBase;
    /**
     */
    @Column
    @BusinessKey
    private Double montoSustraendo;
    /**
     */
    @Column
    @BusinessKey
    private Double porcentaje;
    /**
     * Natural o Juridico
     */
    @Column
    @BusinessKey
    private String tipoPersona;
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

    public TipoEnriquecimientoDetalle() {
    }

    public TipoEnriquecimientoDetalle(Double montoBase, Double montoSustraendo, Double porcentaje, String tipoPersona, AuditoriaBasica auditoria) {
        this.montoBase = montoBase;
        this.montoSustraendo = montoSustraendo;
        this.porcentaje = porcentaje;
        this.tipoPersona = tipoPersona;
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

    public Integer getOptLock() {
        return optLock;
    }

    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    public Double getMontoBase() {
        return montoBase;
    }

    public void setMontoBase(Double montoBase) {
        this.montoBase = montoBase;
    }

    public Double getMontoSustraendo() {
        return montoSustraendo;
    }

    public void setMontoSustraendo(Double montoSustraendo) {
        this.montoSustraendo = montoSustraendo;
    }

    public Double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }
}
