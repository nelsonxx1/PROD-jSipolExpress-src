package controlador.vehiculos;

import controlador.Principal;
import controlador.util.DefaultDetailFrameController;
import modelo.HibernateUtil;
import modelo.entidades.vehiculos.maestra.Vehiculo;
import modelo.util.bean.BeanVO;
import org.hibernate.Hibernate;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.form.client.Form;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.java.Consts;
import vista.vehiculos.VehiculosDetailFrame;

public class VehiculoDetailController extends DefaultDetailFrameController {

    public VehiculoDetailController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio) {
        this(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio, null, null);
    }

    public VehiculoDetailController(String detailFramePath, GridControl gridControl, BeanVO beanVO, Boolean aplicarLogicaNegocio, Form linkForm, String attributeName) {
        super(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
        vista.setTitle("Vehiculo: ");
        if (beanVO != null) {
            vista.setTitle(vista.getTitle() + ((Vehiculo)beanVO).getPlaca()+" "+((Vehiculo)beanVO).getAyo());
        }
        this.linkForm = linkForm;
        this.attributeName = attributeName;
    }

    public VehiculoDetailController(GridControl gridControl, BeanVO beanVO, String placa) {
        this(gridControl, beanVO, placa, null, null);
    }

    public VehiculoDetailController(GridControl gridControl, BeanVO beanVO, String placa, Form linkForm, String attributeName) {
        this.gridControl = gridControl;
        this.beanVO = beanVO;
        this.aplicarLogicaNegocio = false;
        this.linkForm = linkForm;
        this.attributeName = attributeName;
        vista = new VehiculosDetailFrame();
        vista.inicializar(this, true);
        vista.setTitle("Vehiculo: ");
        if (beanVO != null) {
            vista.setTitle(vista.getTitle() + ((Vehiculo)beanVO).getPlaca()+" "+((Vehiculo)beanVO).getAyo());
        }
        vista.getMainPanel().setMode(Consts.INSERT);
        vista.getMainPanel().getVOModel().setValue("placa", placa);
        vista.getMainPanel().pull("placa");
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Vehiculo p = (Vehiculo) s.get(Vehiculo.class, ((Vehiculo) beanVO).getId());
        Hibernate.initialize(p.getObservaciones());
        Hibernate.initialize(p.getDocumentos());
        s.close();
        beanVO = p;
        return new VOResponse(beanVO);
    }

    @Override
    public void createPersistentObject(ValueObject PersistentObject) throws Exception {
        PersistentObject = new Vehiculo();
        ((Vehiculo) PersistentObject).setColor(Principal.defaultData.vehiculo.getColor());
        ((Vehiculo) PersistentObject).setMarcaModelo(Principal.defaultData.vehiculo.getMarcaModelo());
        ((Vehiculo) PersistentObject).setClasificacion(Principal.defaultData.vehiculo.getClasificacionVehiculo());
        vista.getMainPanel().getVOModel().setValueObject(PersistentObject);
    }
}
