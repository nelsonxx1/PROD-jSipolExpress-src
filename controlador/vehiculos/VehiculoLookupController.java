package controlador.vehiculos;

import controlador.util.DefaultLookupController;
import controlador.util.DefaultLookupDataLocator;
import controlador.util.DefaultLookupGridController;
import java.awt.Dimension;
import java.util.List;
import logger.LoggerUtil;
import modelo.HibernateUtil;
import modelo.entidades.vehiculos.maestra.Vehiculo;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;

/**
 *
 * @author bc
 */
public class VehiculoLookupController extends DefaultLookupController {

    public VehiculoLookupController() {
        this.setLookupDataLocator(new VehiculoDataLocator(Vehiculo.class.getName()));
        this.setLookupGridController(new DefaultLookupGridController());
        setLookupValueObjectClassName(Vehiculo.class.getName());
        addLookup2ParentLink("vehiculo");
        setCodeSelectionWindow(GRID_FRAME);
        setOnInvalidCode(ON_INVALID_CODE_RESTORE_LAST_VALID_CODE);
        setAllColumnVisible(false);
        setVisibleColumn("placa", true);
        setVisibleColumn("ayo", true);
        setVisibleColumn("marcaModelo.nombreCompleto", true);
        setPreferredWidthColumn("placa", 80);
        setPreferredWidthColumn("ayo", 50);
        setPreferredWidthColumn("marcaModelo.nombreCompleto", 200);
        setFilterableColumn("placa", true);
        setFilterableColumn("ayo", true);
        setSortableColumn("placa", true);
        setSortableColumn("ayo", true);
        setSortableColumn("marcaModelo.nombreCompleto", true);
        setFramePreferedSize(new Dimension(400, 330));
    }

    class VehiculoDataLocator extends DefaultLookupDataLocator {

        public VehiculoDataLocator(String classFullName) {
            super(classFullName);
        }

        @Override
        public Response validateCode(String code) {
            Session s = null;
            try {
                String sql = "FROM " + classFullName + " C " + "" +
                        "WHERE C.auditoria.activo=? AND C.placa=?";
                s = HibernateUtil.getSessionFactory().openSession();
                Transaction t = s.beginTransaction();
                List list = s.createQuery(sql).
                        setBoolean(0, true).
                        setString(1, code).list();
                t.commit();
                return new VOListResponse(list, false, list.size());
            } catch (Exception ex) {
                LoggerUtil.error(this.getClass(), "validateCode", ex);
                return new ErrorResponse(ex.getMessage());
            } finally {
                s.close();
            }
        }
    }
}
