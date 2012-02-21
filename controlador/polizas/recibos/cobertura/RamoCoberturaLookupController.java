package controlador.polizas.recibos.cobertura;

import controlador.util.DefaultLookupController;
import controlador.util.DefaultLookupDataLocator;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import logger.LoggerUtil;
import modelo.HibernateUtil;
import modelo.entidades.polizas.dominio.RamoPoliza;
import modelo.entidades.polizas.recibos.cobertura.Cobertura;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.type.Type;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.send.java.FilterWhereClause;
import org.openswing.swing.tree.java.OpenSwingTreeNode;
import org.openswing.swing.util.java.Consts;
import org.openswing.swing.util.server.HibernateUtils;

/**
 * @author bc
 */
public class RamoCoberturaLookupController extends DefaultLookupController {

    public RamoCoberturaLookupController() {
        this.setLookupDataLocator(new RamoCoberturaLookupDataLocator(Cobertura.class.getName()));
        this.setFramePreferedSize(new Dimension(500, 500));
        this.setCodeSelectionWindow(RamoCoberturaLookupController.TREE_GRID_FRAME);
        this.getLookupDataLocator().setNodeNameAttribute("nombre");
        this.setLookupValueObjectClassName(Cobertura.class.getName());
        this.addLookup2ParentLink("ramoCobertura");
        this.setAllColumnVisible(false);
        this.setVisibleColumn("id", true);
        //this.setVisibleColumn("marca.nombre", true);
        this.setVisibleColumn("nombre", true);
        this.setPreferredWidthColumn("id", 50);
        this.setPreferredWidthColumn("nombre", 200);
        //this.setPreferredWidthColumn("marca.nombre", 150);
        this.setFilterableColumn("nombre", true);
        //this.setFilterableColumn("marca.nombre", true);
        //this.setSortableColumn("marca.nombre", true);
        this.setSortableColumn("nombre", true);
        this.setSortedColumn("nombre", Consts.ASC_SORTED);
       
    }

    class RamoCoberturaLookupDataLocator extends DefaultLookupDataLocator {

        public RamoCoberturaLookupDataLocator(String classFullName) {
            super(classFullName);
        }

        @Override
        public Response loadData(int action,
                int startIndex,
                Map filteredColumns,
                ArrayList currentSortedColumns,
                ArrayList currentSortedVersusColumns,
                Class valueObjectType) {
            
            try {
                Map map = getLookupFrameParams();
                if (map.get(Consts.TREE_FILTER) != null) {
                    Cobertura cobertura = (Cobertura) map.get(Consts.TREE_FILTER);
                    if (cobertura.getId() != -1) {
                        filteredColumns.put(
                                "ramo.id",
                                new FilterWhereClause[]{
                                    new FilterWhereClause("ramo.id", "=", cobertura.getId()),
                                    null
                                });
                        //return new VOListResponse(new ArrayList(marca.getModelos()), false, marca.getModelos().size());
                        Session s = null;
                        try {
                            String sql = "FROM " + Cobertura.class.getName() + " C ";
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
                            filteredColumns = new HashMap(0);
                            return res;
                        } catch (Exception ex) {
                            LoggerUtil.error(this.getClass(), "loadData", ex);
                            return new ErrorResponse(ex.getMessage());
                        } finally {
                            s.close();
                        }
                    } else {
                        return new VOListResponse(new ArrayList(0), false, 0);
                    }
                }
                return new VOListResponse(new ArrayList(0), false, 0);
            } catch (Exception ex) {
                LoggerUtil.error(this.getClass(), "loadData", ex);
                return new ErrorResponse(ex.getMessage());
            }

        }

        @Override
        public Response getTreeModel(JTree tree) {
            Session s = null;
            try {
                RamoPoliza vo = null;
                vo = new RamoPoliza();
                vo.setId(new Long(-1));
                vo.setNombre("Ramos");
                DefaultMutableTreeNode root = new OpenSwingTreeNode(vo);
                s = HibernateUtil.getSessionFactory().openSession();
                List ramos = s.createQuery("FROM " + RamoPoliza.class.getName()+" M ORDER BY M.nombre")
                        //.setCacheable(true)
                        .list();
                for (Object marca : ramos) {
                    root.add(new OpenSwingTreeNode(marca));
                }
                return new VOResponse(new DefaultTreeModel(root));
            } catch (Exception ex) {
                LoggerUtil.error(this.getClass(), "getTreeModel", ex);
                return new VOResponse(new DefaultTreeModel(new OpenSwingTreeNode()));
            } finally {
                s.close();
            }
        }
    }
}
