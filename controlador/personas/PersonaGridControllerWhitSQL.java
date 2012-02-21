/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador.personas;

import controlador.util.DefaultGridControllerWhitSQL;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import logger.LoggerUtil;
import modelo.HibernateUtil;
import modelo.entidades.auditoria.Auditable;
import modelo.entidades.auditoria.AuditoriaBasica;
import modelo.util.bean.BeanVO;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.type.Type;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author PAPA
 */
public class PersonaGridControllerWhitSQL extends DefaultGridControllerWhitSQL implements ActionListener{

    public PersonaGridControllerWhitSQL(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo, String sql, Object[] values, Type[] valueType) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo, sql, values, valueType);
    }

    @Override
    public Response deleteRecords(ArrayList persistentObjects) throws Exception {
        Session s = null;
        try {
            boolean allOk = true;
            s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            for (Object o : persistentObjects) {
                if (o instanceof Auditable) {
                    AuditoriaBasica a = ((Auditable) o).getAuditoria();
                    if (a != null && a.getBorrable() != null && !a.getBorrable()) {
                        return new ErrorResponse("El registro no es borrable!!!");
                    } else {
                        s.delete(o);
                    }
                } else {
                    s.delete(o);
                }
            }
            try {
                t.commit();
                System.out.println("Borrraaaaaa");
            }
            catch (ConstraintViolationException ex){
                if(ex.getSQLException().toString().indexOf("table: POLIZA")!=-1)
                {
                    JOptionPane.showMessageDialog(MDIFrame.getInstance(), "Tiene una o varias Polizas asociadas a este registro.\n"
                            + "Elimine la o las Polizas antes de intentar eliminar la Persona", "Poliza(s) Asociada(s) a la Persona",JOptionPane.WARNING_MESSAGE);
                }
                LoggerUtil.error(this.getClass(), "deleteRecords", ex);
                t.rollback();
                allOk = false;
            }
            catch (Exception ex) {
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

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        new PersonasDetailController(null, (BeanVO) persistentObject, null);
    }

    public void actionPerformed(ActionEvent e) {
        
    }

}
