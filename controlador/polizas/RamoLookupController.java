package controlador.polizas;

import controlador.util.DefaultLookupController;
import controlador.util.DefaultLookupDataLocatorPorNombre;
import controlador.util.DefaultLookupGridController;
import java.awt.Dimension;
import modelo.entidades.polizas.dominio.RamoPoliza;

/**
 *
 * @author bc
 */
public class RamoLookupController extends DefaultLookupController {

    public RamoLookupController() {
        setLookupGridController(new DefaultLookupGridController());
        setLookupDataLocator(new RamoLookupDataLocator(RamoPoliza.class.getName()));

        addLookup2ParentLink("nombre", "ramoPoliza.nombre");
        addLookup2ParentLink("ramoPoliza");

        setCodeSelectionWindow(GRID_FRAME);
        setOnInvalidCode(ON_INVALID_CODE_CLEAR_CODE);
        setLookupValueObjectClassName(RamoPoliza.class.getName());

        setAllColumnVisible(false);
        setVisibleColumn("nombre", true);
        setVisibleColumn("nombreCorto", true);
        setVisibleColumn("ramoContable", true);
        setVisibleColumn("tipoRamo", true);
        setPreferredWidthColumn("nombre", 230);
        setFilterableColumn("nombre", true);
        setSortableColumn("nombre", true);
        setFramePreferedSize(new Dimension(570, 400));

    }

    class RamoLookupDataLocator extends DefaultLookupDataLocatorPorNombre {

        public RamoLookupDataLocator(String classFullName) {
            super(classFullName);
        }

//        @Override
//        public Response validateCode(String code) {
//            Session s = null;
//            try {
//                String sql = "FROM " + classFullName + " C " + ""
//                        + "WHERE C.auditoria.activo=? AND C.nombreCorto=?";
//                s = HibernateUtil.getSessionFactory().openSession();
//                Transaction t = s.beginTransaction();
//                List list = s.createQuery(sql).
//                        setBoolean(0, true).
//                        setString(1, code.toUpperCase().trim()).list();
//                t.commit();
//                return new VOListResponse(list, false, list.size());
//            } catch (Exception ex) {
//                LoggerUtil.error(this.getClass(), "validateCode", ex);
//                return new ErrorResponse(ex.getMessage());
//            } finally {
//                s.close();
//            }
//        }
    }
}
