package controlador.polizas;

import controlador.util.DefaultLookupController;
import controlador.util.DefaultLookupDataLocator;
import controlador.util.DefaultLookupGridController;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import logger.LoggerUtil;
import modelo.HibernateUtil;
import modelo.entidades.polizas.maestra.Poliza;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.type.Type;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.send.java.FilterWhereClause;
import org.openswing.swing.util.server.HibernateUtils;

/**
 *
 * @author bc
 */
public class PolizaLookupController extends DefaultLookupController {

    public PolizaLookupController() {
        setLookupGridController(new DefaultLookupGridController());
        setLookupDataLocator(new PolizaDataLocator(Poliza.class.getName()));
        setFramePreferedSize(new Dimension(690, 360));
        setCodeSelectionWindow(GRID_FRAME);
        setOnInvalidCode(ON_INVALID_CODE_RESTORE_FOCUS);
        setLookupValueObjectClassName(Poliza.class.getName());
        addLookup2ParentLink("poliza");
        setMaxSortedColumns(2);
        setAllColumnVisible(false);
        setVisibleColumn("compania.nombreCorto", true);
        setVisibleColumn("ramoPoliza.nombreCorto", true);
        setVisibleColumn("numero", true);
        setVisibleColumn("contratante.rif.cedulaCompleta", true);
        setVisibleColumn("contratante.nombreLargo", true);
        setVisibleColumn("grupoPoliza.nombre", true);
        setPreferredWidthColumn("compania.nombreCorto", 150);
        setPreferredWidthColumn("contratante.nombreLargo", 150);
        setFilterableColumn("numero", true);
        setFilterableColumn("compania.nombreCorto", true);
        setFilterableColumn("contratante.rif.cedulaCompleta", true);
        setFilterableColumn("contratante.nombreLargo", true);
        setFilterableColumn("compania.nombreCorto", true);
        setFilterableColumn("grupoPoliza.nombre", true);
        setSortableColumn("contratante.rif.cedulaCompleta", true);
        setSortableColumn("contratante.nombreLargo", true);
        setSortableColumn("compania.nombreCorto", true);
        setSortableColumn("numero", true);
    }

    class PolizaDataLocator extends DefaultLookupDataLocator {

        public PolizaDataLocator(String classFullName) {
            super(classFullName);
        }

        @Override
        public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType) {
            Session s = null;
            try {
                String sql = "FROM " + classFullName + " C ";
                filteredColumns.put(
                        "auditoria.activo",
                        new FilterWhereClause[]{
                            new FilterWhereClause("auditoria.activo", "=", true),
                            null
                        });
                filteredColumns.put(
                        "anulado",
                        new FilterWhereClause[]{
                            new FilterWhereClause("anulado", "=", false),
                            null
                        });
                SessionFactory sf = HibernateUtil.getSessionFactory();
                s = sf.openSession();
                Response res = HibernateUtils.getAllFromQuery(
                        filteredColumns,
                        currentSortedColumns,
                        currentSortedVersusColumns,
                        valueObjectType,
                        sql,
                        new Object[0],
                        new Type[0],
                        "C",
                        sf,
                        s);
                return res;
            } catch (Exception ex) {
                LoggerUtil.error(this.getClass(), "loadData", ex);
                return new ErrorResponse(ex.getMessage());
            } finally {
                s.close();
            }
        }

        @Override
        public Response validateCode(String code) {
            Session s = null;
            try {
                String sql = "FROM " + Poliza.class.getName() + " C " +
                        "WHERE C.auditoria.activo=? AND C.anulado=? AND upper(C.numero)=?";
                s = HibernateUtil.getSessionFactory().openSession();
                Transaction t = s.beginTransaction();
                List list = s.createQuery(sql).
                        setBoolean(0, true).
                        setBoolean(1, false).
                        setString(2, code.toUpperCase()).list();
                t.commit();
                return new VOListResponse(list, false, list.size());
            } catch (Exception ex) {
                LoggerUtil.error(this.getClass(), "validateCode", ex);
                System.out.println("clonando");
                return new ErrorResponse(ex.getMessage());
            } finally {
                s.close();
            }
        }
    }
}
