package controlador.administracion;

import controlador.General;
import controlador.helpcenter.CorreoDetailFrameController;
import controlador.sms.EnviarSMS;
import controlador.util.DefaultDetailFrameController;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import modelo.Dominios.TipoFactura;
import modelo.HibernateUtil;
import modelo.entidades.administracion.Factura2;
import modelo.entidades.personas.maestra.Persona;
import modelo.util.bean.BeanVO;
import org.hibernate.Hibernate;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import vista.administracion.FacturaCompraDetailFrame;

/**
 *
 * @author orlandobcrra
 */
public class FacturaCompraDetailController extends DefaultDetailFrameController {

    public FacturaCompraDetailController(String detailFramePath, GridControl gridControl, BeanVO beanVO, boolean aplicarLogicaNegocio) {
        super(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
    }

    @Override
    public Response loadData(Class valueObjectClass) {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Factura2 p = (Factura2) s.get(Factura2.class, ((Factura2) beanVO).getId());
        Hibernate.initialize(p.getPagos());
        s.close();
        beanVO = p;
        return new VOResponse(beanVO);
    }

    @Override
    public boolean validateControl(String attributeName, Object oldValue, Object newValue) {
        Double exento = 0.0;
        Double base12 = 0.0;
        Double iva12 = 0.0;
        Double total = 0.0;
        if (attributeName.equals("totalCompraExenta") || attributeName.equals("base12") || attributeName.equals("iva12")) {
            if (attributeName.equals("totalCompraExenta")) {
                if (newValue != null) {
                    exento += (Double) newValue;
                }
            } else {
                exento = (Double) vista.getMainPanel().getVOModel().getValue("totalCompraExenta");
                exento = exento == null ? 0.0 : exento;
            }
            if (attributeName.equals("base12")) {
                if (newValue != null) {
                    base12 += (Double) newValue;
                }
            } else {
                base12 = (Double) vista.getMainPanel().getVOModel().getValue("base12");
                base12 = base12 == null ? 0.0 : base12;
            }
            if (attributeName.equals("iva12")) {
                if (newValue != null) {
                    iva12 += (Double) newValue;
                }
            } else {
                iva12 = (Double) vista.getMainPanel().getVOModel().getValue("iva12");
                iva12 = iva12 == null ? 0.0 : iva12;
            }
            total = exento + base12 + iva12;
            vista.getMainPanel().getVOModel().setValue("totalConIVA", total);
            vista.getMainPanel().pull("totalConIVA");

        }
        return true;
    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        ((Factura2) newPersistentObject).setTipoFactura(TipoFactura.COMPRA);
        return super.insertRecord(newPersistentObject);
    }

    @Override
    public Response logicaNegocio(ValueObject persistentObject) {
        Factura2 p = (Factura2) persistentObject;
        persistentObject = p;
        String errorMsj = "";
        if (errorMsj.length() > 0) {
            return new ErrorResponse(errorMsj);
        } else {
            return new VOResponse(persistentObject);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Factura2 po = (Factura2) beanVO;
        if (e.getSource() == ((FacturaCompraDetailFrame) vista).getSMSButton()) {
            if (po != null && po.getRazonSocial() != null) {
                Long idP = po.getRazonSocial().getId();
                Session s = null;
                try {
                    s = HibernateUtil.getSessionFactory().openSession();
                    Persona p = (Persona) s.load(Persona.class, idP);
                    EnviarSMS.crearSMS(p.getNombreLargo(), po.getContribuyente().getNombreCorto(), p.getTelefonos(),p);
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    s.close();
                }
            }
        } else if (e.getSource() == ((FacturaCompraDetailFrame) vista).getEmailButton()) {
            String email = po.getRazonSocial().getEmail();
            if (email != null && !email.isEmpty()) {
                new CorreoDetailFrameController(email + "," + General.empresa.getEmail(), General.empresa.getEmail(), "Email", null, false);
            } else {
                JOptionPane.showMessageDialog(MDIFrame.getInstance(), "El contratante no tiene registrado correo electronico...", "Mensaje", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
