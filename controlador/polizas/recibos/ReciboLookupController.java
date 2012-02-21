package controlador.polizas.recibos;

import controlador.util.DefaultLookupController;
import controlador.util.DefaultLookupDataLocator;
import controlador.util.DefaultLookupGridController;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import logger.LoggerUtil;
import modelo.HibernateUtil;
import modelo.entidades.polizas.recibos.maestra.Recibo;
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
public class ReciboLookupController extends DefaultLookupController {

    public ReciboLookupController() {
        setLookupGridController(new DefaultLookupGridController());
        setLookupDataLocator(new ReciboDataLocator(Recibo.class.getName()));
        setFramePreferedSize(new Dimension(690, 360));
        setCodeSelectionWindow(GRID_FRAME);
        setOnInvalidCode(ON_INVALID_CODE_RESTORE_FOCUS);
        setLookupValueObjectClassName(Recibo.class.getName());
        addLookup2ParentLink("recibo");
        setMaxSortedColumns(2);
        setAllColumnVisible(false);
        setVisibleColumn("poliza.compania.nombreCorto", true);
        setVisibleColumn("poliza.numero", true);
        setVisibleColumn("numero", true);
        setVisibleColumn("vigenciaDesde", true);
        setVisibleColumn("vigenciaHasta", true);
        setVisibleColumn("poliza.contratante.rif.cedulaCompleta", true);
        setVisibleColumn("poliza.contratante.nombreLargo", true);
        setVisibleColumn("asegurado.rif.cedulaCompleta", true);
        setVisibleColumn("asegurado.nombreLargo", true);
        setVisibleColumn("poliza.grupoPoliza.nombre", true);

        setHeaderColumnName("poliza.contratante.nombreLargo", "contratante");
        setHeaderColumnName("asegurado.nombreLargo", "asegurado");

        setPreferredWidthColumn("poliza.compania.nombreCorto", 150);
        setPreferredWidthColumn("poliza.contratante.nombreLargo", 150);
        setPreferredWidthColumn("asegurado.nombreLargo", 150);
        setFilterableColumn("poliza.numero", true);
        setFilterableColumn("poliza.compania.nombreCorto", true);
        setFilterableColumn("numero", true);
        setFilterableColumn("vigenciaDesde", true);
        setFilterableColumn("vigenciaHasta", true);
        setFilterableColumn("poliza.contratante.rif.cedulaCompleta", true);
        setFilterableColumn("poliza.contratante.nombreLargo", true);
        setFilterableColumn("asegurado.rif.cedulaCompleta", true);
        setFilterableColumn("asegurado.nombreLargo", true);
        setFilterableColumn("poliza.compania.nombreCorto", true);
        setFilterableColumn("poliza.grupoPoliza.nombre", true);
        setSortableColumn("poliza.contratante.rif.cedulaCompleta", true);
        setSortableColumn("poliza.contratante.nombreLargo", true);
        setSortableColumn("asegurado.rif.cedulaCompleta", true);
        setSortableColumn("asegurado.nombreLargo", true);
        setSortableColumn("poliza.compania.nombreCorto", true);
        setSortableColumn("numero", true);
        setSortableColumn("poliza.numero", true);
    }

    class ReciboDataLocator extends DefaultLookupDataLocator {

        public ReciboDataLocator(String classFullName) {
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
                String sql = "FROM " + Recibo.class.getName() + " C "
                        + "WHERE C.auditoria.activo=? AND C.anulado=? AND upper(C.numero)=?";
                s = HibernateUtil.getSessionFactory().openSession();
                Transaction t = s.beginTransaction();
                List list = s.createQuery(sql).
                        setBoolean(0, true).
                        setBoolean(1, false).
                        setString(2, code.toUpperCase().trim()).list();
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
