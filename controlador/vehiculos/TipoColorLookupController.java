package controlador.vehiculos;

import controlador.util.DefaultLookupController;
import controlador.util.DefaultLookupDataLocator;
import controlador.util.DefaultLookupGridController;
import java.awt.Color;
import java.util.List;
import logger.LoggerUtil;
import modelo.HibernateUtil;
import modelo.entidades.vehiculos.dominio.TipoColor;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;

/**
 *
 * @author bc
 */
public class TipoColorLookupController extends DefaultLookupController {

    public TipoColorLookupController() {
        setLookupGridController(new TipoColorLookupGridController());
        setLookupDataLocator(new TipoColorLookupDataLocator(TipoColor.class.getName()));
        setCodeSelectionWindow(GRID_FRAME);
        setOnInvalidCode(ON_INVALID_CODE_RESTORE_FOCUS);
        setLookupValueObjectClassName(TipoColor.class.getName());
        addLookup2ParentLink("color");
        setAllColumnVisible(false);
        setVisibleColumn("id", true);
        setVisibleColumn("color", true);
        setVisibleColumn("nombre", true);
        setPreferredWidthColumn("id", 50);
        setPreferredWidthColumn("nombre", 200);
        setFilterableColumn("nombre", true);
        setSortableColumn("nombre", true);
    }

    class TipoColorLookupGridController extends DefaultLookupGridController {

        @Override
        public Color getBackgroundColor(int row, String attributeName, Object value) {
            TipoColor vo = (TipoColor) model.getObjectForRow(row);
            if (attributeName.equalsIgnoreCase("id")) {
                return vo.getColor2();
            }
            return super.getBackgroundColor(row, attributeName, value);
        }
    }

    class TipoColorLookupDataLocator extends DefaultLookupDataLocator {

        public TipoColorLookupDataLocator(String classFullName) {
            super(classFullName);
        }

        @Override
        public Response validateCode(String colorNombre) {
            Session s = null;
            try {
                String sql = "FROM " + TipoColor.class.getName() + " C " +
                        "WHERE C.auditoria.activo=? AND upper(C.nombre)=?";
                s = HibernateUtil.getSessionFactory().openSession();
                Transaction t = s.beginTransaction();
                List list = s.createQuery(sql).
                        setBoolean(0, true).
                        setString(1, colorNombre.toUpperCase()).list();
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
