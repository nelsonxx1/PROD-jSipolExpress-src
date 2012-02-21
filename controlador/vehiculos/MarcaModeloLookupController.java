package controlador.vehiculos;

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
import modelo.entidades.vehiculos.dominio.MarcaVehiculo;
import modelo.entidades.vehiculos.dominio.ModeloVehiculo;
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
public class MarcaModeloLookupController extends DefaultLookupController {

    public MarcaModeloLookupController() {
        this.setLookupDataLocator(new MarcaModeloLookupDataLocator(ModeloVehiculo.class.getName()));
        this.setFramePreferedSize(new Dimension(500, 500));
        this.setCodeSelectionWindow(MarcaModeloLookupController.TREE_GRID_FRAME);
        this.getLookupDataLocator().setNodeNameAttribute("nombre");
        this.setLookupValueObjectClassName(ModeloVehiculo.class.getName());
        this.addLookup2ParentLink("marcaModelo");
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

    class MarcaModeloLookupDataLocator extends DefaultLookupDataLocator {

        public MarcaModeloLookupDataLocator(String classFullName) {
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
                    MarcaVehiculo marca = (MarcaVehiculo) map.get(Consts.TREE_FILTER);
                    if (marca.getId() != -1) {
                        filteredColumns.put(
                                "marca.id",
                                new FilterWhereClause[]{
                                    new FilterWhereClause("marca.id", "=", marca.getId()),
                                    null
                                });
                        //return new VOListResponse(new ArrayList(marca.getModelos()), false, marca.getModelos().size());
                        Session s = null;
                        try {
                            String sql = "FROM " + ModeloVehiculo.class.getName() + " C ";
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
                MarcaVehiculo vo = null;
                vo = new MarcaVehiculo();
                vo.setId(new Long(-1));
                vo.setNombre("Marcas");
                DefaultMutableTreeNode root = new OpenSwingTreeNode(vo);
                s = HibernateUtil.getSessionFactory().openSession();
                List marcas = s.createQuery("FROM " + MarcaVehiculo.class.getName()+" M ORDER BY M.nombre")
                        //.setCacheable(true)
                        .list();
                for (Object marca : marcas) {
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
