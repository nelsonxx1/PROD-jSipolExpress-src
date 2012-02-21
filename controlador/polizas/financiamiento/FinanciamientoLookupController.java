package controlador.polizas.financiamiento;

import controlador.util.DefaultLookupController;
import controlador.util.DefaultLookupDataLocator;
import controlador.util.DefaultLookupGridController;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import logger.LoggerUtil;
import modelo.HibernateUtil;
import modelo.entidades.polizas.financiamiento.Financiamiento;
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
public class FinanciamientoLookupController extends DefaultLookupController {

    public FinanciamientoLookupController() {
        setLookupGridController(new DefaultLookupGridController());
        setLookupDataLocator(new FinanciamientoDataLocator(Financiamiento.class.getName()));
        setFramePreferedSize(new Dimension(690, 360));
        setCodeSelectionWindow(GRID_FRAME);
        setOnInvalidCode(ON_INVALID_CODE_RESTORE_FOCUS);
        setLookupValueObjectClassName(Financiamiento.class.getName());
        addLookup2ParentLink("financiamiento");
        setMaxSortedColumns(2);
        setAllColumnVisible(false);
        setVisibleColumn("financiadora.nombreLargo", true);
        setVisibleColumn("numeroFF", true);
        setVisibleColumn("pagador.rif.cedulaCompleta", true);
        setVisibleColumn("pagador.nombreLargo", true);
        setVisibleColumn("fechaPagoInicial", true);
        setPreferredWidthColumn("financiadora.nombreLargo", 150);
        setPreferredWidthColumn("pagador.nombreLargo", 150);
        setFilterableColumn("numeroFF", true);
        setFilterableColumn("financiadora.nombreLargo", true);
        setFilterableColumn("pagador.rif.cedulaCompleta", true);
        setFilterableColumn("pagador.nombreLargo", true);
        setSortableColumn("financiadora.nombreLargo", true);
        setSortableColumn("pagador.rif.cedulaCompleta", true);
        setSortableColumn("pagador.nombreLargo", true);
        setSortableColumn("numeroFF", true);
    }

    class FinanciamientoDataLocator extends DefaultLookupDataLocator {

        public FinanciamientoDataLocator(String classFullName) {
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
                        "anuladoF",
                        new FilterWhereClause[]{
                            new FilterWhereClause("anuladoF", "=", false),
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
                String sql = "FROM " + Financiamiento.class.getName() + " C " +
                        "WHERE C.auditoria.activo=? AND C.anuladoF=? AND upper(C.numeroFF)=?";
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
                return new ErrorResponse(ex.getMessage());
            } finally {
                s.close();
            }
        }
    }
}
