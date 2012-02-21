package controlador.polizas.recibos;

import controlador.util.DefaultDetailFrameController;
import modelo.HibernateUtil;
import modelo.entidades.polizas.recibos.dominio.TipoDistribucion;
import modelo.util.bean.BeanVO;
import org.hibernate.Hibernate;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;

/**
 *
 * @author orlandobcrra
 */
public class TipoDistribucionDetailController extends DefaultDetailFrameController {

    public TipoDistribucionDetailController(String detailFramePath, GridControl gridControl, BeanVO beanVO, boolean aplicarLogicaNegocio) {
        super(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);

    }

    @Override
    public Response loadData(Class valueObjectClass) {
        //System.out.println("ak");
        //System.out.println(((Recibo) beanVO).getId());
        if (((TipoDistribucion) beanVO).getId() != null) {
            Session s = HibernateUtil.getSessionFactory().openSession();
            TipoDistribucion p = (TipoDistribucion) s.get(TipoDistribucion.class, ((TipoDistribucion) beanVO).getId());
            Hibernate.initialize(p.getDistribuciones());
            s.close();
            beanVO = p;
        }
        return new VOResponse(beanVO);
    }
}
