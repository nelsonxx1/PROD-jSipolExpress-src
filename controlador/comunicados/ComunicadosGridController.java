package controlador.comunicados;

import controlador.General;
import controlador.PictureCapture.PictureCaptureDialogController;
import controlador.util.DefaultGridFrameController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import javax.swing.JOptionPane;
import logger.LoggerUtil;
import modelo.HibernateUtil;
import modelo.entidades.auditoria.Auditable;
import modelo.entidades.auditoria.AuditoriaBasica;
import modelo.entidades.comunicados.Comunicado;
import modelo.interfaces.Documentable;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.type.Type;
import org.openswing.swing.client.OptionPane;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import vista.comunicados.ComunicadosGridFrame;
import org.openswing.swing.util.server.HibernateUtils;

/**
 *
 * @author bc
 */
public class ComunicadosGridController extends DefaultGridFrameController implements ActionListener {

    private String path = General.empresa.getRutaDocDigitales() + "/Comunicados";

    public ComunicadosGridController() {
        super.claseModeloFullPath = Comunicado.class.getName();
        //super(gridFramePath, detailFramePath, claseModeloFullPath, titulo);
        super.gridFrame = new ComunicadosGridFrame();
        ((ComunicadosGridFrame) super.gridFrame).inicializar(this, true);
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();
        }
        path += "/";
    }

    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        Session s = null;
        try {
            String sql = "FROM " + claseModeloFullPath + " C ";
            SessionFactory sf = HibernateUtil.getSessionFactory();
            s = sf.openSession();
//            Response res = HibernateUtils.getAllFromQuery(
//                    filteredColumns,
//                    currentSortedColumns,
//                    currentSortedVersusColumns,
//                    valueObjectType,
//                    sql,
//                    new Object[0],
//                    new Type[0],
//                    "C",
//                    sf,
//                    s);
            Response res = HibernateUtils.getBlockFromQuery(
                    action,
                    startIndex,
                    General.licencia.getBlockSize(),
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
            if (res instanceof VOListResponse) {
                for (Object object : ((VOListResponse) res).getRows()) {
                    Documentable vo = (Documentable) object;
                    try {
                        BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File(path + vo.getFileName())));
                        byte[] bb = new byte[vo.getFileLength()];
                        in.read(bb);
                        in.close();
                        vo.setFile(bb);
                    } catch (Exception ex) {
                        vo.setFileName("ERROR");
                        LoggerUtil.error(this.getClass(), "loadData", ex);
                    }
                }
            }
            return res;
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "loadData", ex);
            return new ErrorResponse(ex.getMessage());
        } finally {
            s.close();
        }
    }

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects, ArrayList persistentObjects) throws Exception {
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            for (Object o : persistentObjects) {
                if (o instanceof Auditable) {
                    AuditoriaBasica ab = ((Auditable) o).getAuditoria();
                    ab.setFechaUpdate(new Date());
                    ab.setUsuarioUpdate(General.usuario.getUserName());
                }
                Documentable vo = (Documentable) o;
                if (PictureCaptureDialogController.DEFAULT_FILE_NAME.equals(vo.getFileName())) {
                    vo.setFileName(new Date().getTime() / 1000 + ".jpg");
                }
                vo.setFileLength(vo.getFile().length);
                FileOutputStream out = new FileOutputStream(path + vo.getFileName());
                byte[] bb = vo.getFile();
                out.write(bb);
                out.close();
                s.update(o);
            }
            t.commit();
            return new VOListResponse(persistentObjects, false, persistentObjects.size());
        } catch (Exception ex) {
            return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "updateRecords", ex));
        } finally {
            s.close();
        }
    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            AuditoriaBasica ab = new AuditoriaBasica(new Date(), General.usuario.getUserName(), true);
            for (Object o : newValueObjects) {
                if (o instanceof Auditable) {
                    ((Auditable) o).setAuditoria(ab);
                }
                Documentable vo = (Documentable) o;
                if (PictureCaptureDialogController.DEFAULT_FILE_NAME.equals(vo.getFileName())) {
                    vo.setFileName(new Date().getTime() / 1000 + ".jpg");
                }
                vo.setFileLength(vo.getFile().length);
                FileOutputStream out = new FileOutputStream(path + vo.getFileName());
                byte[] bb = vo.getFile();
                out.write(bb);
                out.close();
                s.save(o);
            }
            t.commit();
            return new VOListResponse(newValueObjects, false, newValueObjects.size());
        } catch (Exception ex) {
            return new ErrorResponse(LoggerUtil.isInvalidStateException(this.getClass(), "insertRecords", ex));
        } finally {
            s.close();
        }
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {
        Session s = null;
        try {
            boolean allOk = true;
            s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();

            for (Object o : persistentObjects) {
                if (path != null) {
                    Documentable vo = (Documentable) o;
                    s.delete(vo);
                    new File(path + vo.getFileName()).delete();
                }
                s.delete(o);
            }
            try {
                t.commit();
            } catch (Exception ex) {
                LoggerUtil.error(this.getClass(), "deleteRecords", ex);
                t.rollback();
                allOk = false;
            }
            if (allOk) {
                return new VOResponse(true);
            } else {
                return new ErrorResponse("delete.constraint.violation");
            }
        } finally {
            s.close();
        }
    }

    public void showDocument() {
        //Ver el documento
        String fileName = (String) super.gridFrame.getGridControl().getVOListTableModel().getField(super.gridFrame.getGridControl().getSelectedRow(), "fileName");
        String filePath = new File(path + fileName).getAbsolutePath();
        boolean sw = false;
        try {
            java.awt.Desktop.getDesktop().open(new File(filePath));
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "showDocument", ex);
            sw = true;
        }
        if (sw) {
            try {
                //TODO windows only
                if (System.getProperty("os.name").startsWith("Windows")) {
                    Runtime.getRuntime().exec("cmd /C " + filePath);
                } else {
                    OptionPane.showMessageDialog(
                            MDIFrame.getInstance(),
                            "Disponible solo para Windows. \n"
                            + "Contacte con el proveedor.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                LoggerUtil.error(this.getClass(), "showDocument", ex);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        showDocument();
        super.gridFrame.getGridControl().requestFocus();
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        showDocument();
    }
}
