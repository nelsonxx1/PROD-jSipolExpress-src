package modelo.entidades.facturacion;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import modelo.entidades.personas.maestra.Persona;
import modelo.util.ehts.BusinessKey;
import modelo.util.ehts.Method;

/**
 * @author bc
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class CuentaPorCobrar extends DetalleFactura implements Serializable {

    /**
     * Quien emite la CuentaPorCobrar
     */
    @ManyToOne()
    @BusinessKey
    private Persona productor;
    /**
     * Quien pagara la CuentaPorCobrar
     */
    @ManyToOne()
    @BusinessKey
    private Persona pagador;
    /**
     * Pendiente, Seleccionada, Ingresado, Anulado
     */
    @Column
    @BusinessKey
    private String estatus;
    /**
     * Se puede seleccionar para una factura?
     */
    @Column
    @BusinessKey
    private Boolean seleccionable;
    /**
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private Set<CuentaPorCobrar> desglose = new HashSet<CuentaPorCobrar>(0);

    public CuentaPorCobrar() {
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Boolean getSeleccionable() {
        return seleccionable;
    }

    public void setSeleccionable(Boolean seleccionable) {
        this.seleccionable = seleccionable;
    }

    public Persona getPagador() {
        return pagador;
    }

    public void setPagador(Persona pagador) {
        this.pagador = pagador;
    }

    public Persona getProductor() {
        return productor;
    }

    public void setProductor(Persona productor) {
        this.productor = productor;
    }

    public Set<CuentaPorCobrar> getDesglose() {
        return desglose;
    }

    public void setDesglose(Set<CuentaPorCobrar> desglose) {
        this.desglose = desglose;
    }
}
