

package controlador;

import controlador.administracion.EgresoExtraordinarioGridController;
import controlador.administracion.FacturaCompraDetailController;
import controlador.administracion.FacturasGridController;
import controlador.comunicados.ComunicadosGridController;
import controlador.facturacion.LiquidacionDetailFrameController;
import controlador.helpcenter.CorreosGridFrameController;
import controlador.ordenes.OrdenPagoDetailController;
import controlador.personas.PersonasGridController;
import controlador.personas.mant.TipoPersonaGridFrameController;
import controlador.polizas.PolizasGridController;
import controlador.polizas.financiamiento.FinanciamientoDetailController;
import controlador.polizas.financiamiento.FinanciamientoGridController;
import controlador.polizas.recibos.ReciboDetailController;
import controlador.polizas.recibos.RecibosGridController;
import controlador.polizas.recibos.TipoDistribucionGridController;
import controlador.reportes.ReportesGridController;
import controlador.siniestros.SiniestroDetailController;
import controlador.siniestros.SiniestrosGridController;
import controlador.sistema.menu.RolGritFrameController;
import controlador.sistema.usuarios.UsuarioDetailController;
import controlador.sistema.usuarios.UsuariosGridController;
import controlador.sms.masa.MasaGridFrameController;
import controlador.sms.SMSDetailController;
import controlador.sms.SMSEntradaGridFrameController;
import controlador.sms.SMSGridFrameController;
import controlador.sms.masa.MasaDetailController;
import controlador.util.DefaultAllGridFrameController;
import controlador.util.DefaultDetailFrameController;
import controlador.util.DefaultGridFrameController;
import controlador.vehiculos.VehiculosGridController;
import controlador.vehiculos.mant.TipoColorGridFrameController;
import de.muntjak.tinylookandfeel.TinyLookAndFeel;
import de.muntjak.tinylookandfeel.controlpanel.ControlPanel;
import java.io.File;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import modelo.Dominios.TipoSMS;
import modelo.HibernateUtil;
import modelo.entidades.Encabezado;
import modelo.entidades.TipoDocumento;
import modelo.entidades.administracion.Factura2;
import modelo.entidades.administracion.Movimiento;
import modelo.entidades.facturacion.LiquidacionCompania;
import modelo.entidades.helpcenter.maestra.Mensaje;
import modelo.entidades.menu.Item;
import modelo.entidades.menu.MenuByRol;
import modelo.entidades.menu.Rol;
import modelo.entidades.ordenes.dominio.TipoOrden;
import modelo.entidades.ordenes.maestra.OrdenDePago;
import modelo.entidades.personas.dominio.TipoActividadEconomica;
import modelo.entidades.personas.dominio.TipoCapacidadEconomica;
import modelo.entidades.personas.dominio.TipoCodigoArea;
import modelo.entidades.personas.dominio.TipoCuentaBancaria;
import modelo.entidades.personas.dominio.TipoDireccion;
import modelo.entidades.personas.dominio.TipoPersona;
import modelo.entidades.personas.dominio.TipoTelefono2;
import modelo.entidades.personas.maestra.Persona;
import modelo.entidades.polizas.dominio.GrupoPoliza;
import modelo.entidades.polizas.dominio.RamoPoliza;
import modelo.entidades.polizas.financiamiento.Financiamiento;
import modelo.entidades.polizas.maestra.Poliza;
import modelo.entidades.polizas.recibos.dominio.TipoDistribucion;
import modelo.entidades.polizas.recibos.dominio.TipoZona;
import modelo.entidades.polizas.recibos.maestra.Distribucion;
import modelo.entidades.polizas.recibos.maestra.Recibo;
import modelo.entidades.siniestros.dominio.CausaSiniestro;
import modelo.entidades.siniestros.dominio.DañoVehiculo;
import modelo.entidades.siniestros.maestra.Siniestro;
import modelo.entidades.sms.SMS;
import modelo.entidades.sms.SMSAutomatico;
import modelo.entidades.sms.SMSEntrada;
import modelo.entidades.sms.SMSMasa;
import modelo.entidades.sms.SMSPreEscrito;
import modelo.entidades.vehiculos.dominio.ClasificacionVehiculo;
import modelo.entidades.vehiculos.dominio.MarcaVehiculo;
import modelo.entidades.vehiculos.dominio.TipoColor;
import modelo.entidades.vehiculos.maestra.Vehiculo;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.openswing.swing.client.OptionPane;
import org.openswing.swing.mdi.client.*;
import org.openswing.swing.mdi.java.ApplicationFunction;
import org.openswing.swing.tree.java.OpenSwingTreeNode;
import modelo.entidades.sms.SMSEntradaActualizar;
import vista.administracion.EgresosExtraordinarioGridFrame;
import vista.administracion.EgresosGridFrame;
import vista.administracion.FacturaCompraDetailFrame;
import vista.administracion.FacturasCompraGridFrame;
import vista.calendario.CalendarioDetailFrame;
import vista.facturacion.LiquidacionCompaniaGridFrame;
import vista.facturacion.LiquidacionDetailFrame;
import vista.historial.BuscarHistorialPersonaDialog;
import vista.historial.BuscarHistorialVehiculoDialog;
import vista.mant.DefaultMantenimientoGridFrame;
import vista.mant.EncabezadoGridFrame;
import vista.mant.TipoDocumentoGridFrame;
import vista.ordenes.OrdenPagoDetailFrame;
import vista.personas.BuscarPersonaDialog;
import vista.personas.Personas2GridFrame;
import vista.personas.PersonaDetailFrame;
import vista.personas.RifDialog;
import vista.personas.mant.CodigoAreaGridFrame;
import vista.personas.mant.TipoPersonaGridFrame;
import vista.polizas.BuscarPolizaDialog2;
import vista.polizas.NumeroPolizaDialog;
import vista.polizas.PolizasGridFrame;
import vista.polizas.financiamiento.BuscarFinanciamientoDialog2;
import vista.polizas.financiamiento.FinanciamientoDetailFrame;
import vista.polizas.financiamiento.FinanciamientosGridFrame;
import vista.polizas.financiamiento.NumeroFinanciamientoDialog;
import vista.polizas.mant.RamoGridFrame;
import vista.polizas.recibos.BuscarReciboDialog2;
import vista.polizas.recibos.NumeroReciboDialog;
import vista.polizas.recibos.ReciboDetailFrame;
import vista.polizas.recibos.RecibosGridFrame;
import vista.polizas.recibos.cobertura.mant.RamosCoberturasGridFram;
import vista.polizas.recibos.distribucion.DistribucionGridFrame;
import vista.polizas.recibos.distribucion.TipoDistribucionDetailFrame;
import vista.siniestros.BuscarSiniestroDialog2;
import vista.siniestros.SiniestroDetailFrame;
import vista.siniestros.SiniestrosGridFrame;
import vista.siniestros.mant.CausaGridFrame;
import vista.siniestros.mant.DañoVehiculoGridFrame;
import vista.sistema.CambiarPassDialog;
import vista.sistema.menu.RolOptionFrame;
import vista.sms.SMSAutomaticoGridFrame;
import vista.sms.SMSMasaDetailFrame;
import vista.sms.SMSMasaGridFrame;
import vista.sms.SMSDetailFrame;
import vista.sms.SMSEntradaDetailFrame;
import vista.sms.SMSEntradaGridFrame;
import vista.sms.SMSGridFrame;
import vista.sms.SMSPreEscritoDetailFrame;
import vista.sms.SMSPreEscritosGridFrame;
import vista.vehiculos.BuscarVehiculoDialog;
import vista.vehiculos.NuevoVehiculoDialog;
import vista.vehiculos.VehiculosGridFrame;
import vista.vehiculos.mant.MarcasModelosGridFram;
import vista.vehiculos.mant.TipoColorGridFrame;

/**
 * @author Orlando Becerra
 * @author Enrique Becerra
 */
public class MenuPrincipal implements ClientFacade {

    private boolean tinyCP1 = true;
    private ControlPanel cp = null;

    private MenuByRol addFuntion(DefaultMutableTreeNode padre, Item funcion) {

        String hql = "FROM " + modelo.entidades.menu.MenuByRol.class.getName()
                + " WHERE itemId=" + funcion.getId() + " AND "
                + "rolId=" + General.usuario.getRol().getId();
        Query query = s.createQuery(hql);
        modelo.entidades.menu.MenuByRol mbr = (MenuByRol) query.list().get(0);
        boolean sw = (mbr).isEnable();

        if (sw) {
            ApplicationFunction hijo = null;
            if (funcion.getMetodo() != null) {
                hijo = new ApplicationFunction(funcion.getNombre(),
                        funcion.getFuncionId(), funcion.getIcono(),
                        funcion.getMetodo());
            } else {
                hijo = new ApplicationFunction(funcion.getNombre(),
                        funcion.getIcono());

            }
            padre.add(hijo);
            for (Item it : funcion.getItems()) {
                addFuntion(hijo, it);
            }
        }
        return mbr;
    }
    Session s = null;

    public DefaultTreeModel getApplicationFunctions() {
        DefaultMutableTreeNode root = new OpenSwingTreeNode("Menu");

        DefaultTreeModel model = new DefaultTreeModel(root);
        {


            try {
                s = HibernateUtil.getSessionFactory().openSession();
                Transaction t = s.beginTransaction();
                {
                    String hql = "FROM " + modelo.entidades.menu.Item.class.getName() + " WHERE nombre='root'";
                    Query query = s.createQuery(hql);
                    List mensajes = query.list();
                    modelo.entidades.menu.Item rootItem = (Item) mensajes.get(0);
                    for (Item it : rootItem.getItems()) {
                        //Collections.sort(it.getItems());
                        MenuByRol mbr = addFuntion(root, it);
                        General.permisologiaModulo.put(it.getNombre(), mbr);
                    }

                }
                t.commit();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                s.close();
            }

        }

        return model;
    }


// <editor-fold defaultstate="collapsed" desc="Personas">
    public void getPersonas() {
        new PersonasGridController(Personas2GridFrame.class.getName(), PersonaDetailFrame.class.getName(), Persona.class.getName(), null);
    }

    public void getPersonaNueva() {
        new RifDialog();
    }

    public void getTipoPersona() {
        new TipoPersonaGridFrameController(TipoPersonaGridFrame.class.getName(), null, TipoPersona.class.getName(), "Tipos de Persona");
    }

    public void getTipoActividadEconomica() {
        new DefaultAllGridFrameController(DefaultMantenimientoGridFrame.class.getName(), null, TipoActividadEconomica.class.getName(), "Tipo Actividad Economica");
    }

    public void getTipoCapacidadEconomica() {
        new DefaultAllGridFrameController(DefaultMantenimientoGridFrame.class.getName(), null, TipoCapacidadEconomica.class.getName(), "Tipo Capacidad Economica");
    }

    public void getTipoTelefono() {
        new DefaultAllGridFrameController(DefaultMantenimientoGridFrame.class.getName(), null, TipoTelefono2.class.getName(), "Tipo Telefono");
    }

    public void getTipoDireccion() {
        new DefaultAllGridFrameController(DefaultMantenimientoGridFrame.class.getName(), null, TipoDireccion.class.getName(), "Tipo Direccion");
    }

    public void getTipoCuentaBancaria() {
        new DefaultAllGridFrameController(DefaultMantenimientoGridFrame.class.getName(), null, TipoCuentaBancaria.class.getName(), "Tipo Cuenta Bancaria");
    }

    public void getCodigoArea() {
        new DefaultAllGridFrameController(CodigoAreaGridFrame.class.getName(), null, TipoCodigoArea.class.getName(), null);
    }

    public void getBuscarPersona() {
        new BuscarPersonaDialog(null).setVisible(true);
    }

    public void getHistorialAsegurado() {
        new BuscarHistorialPersonaDialog(null, null, null).setVisible(true);
    }

    // </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Vehiculos">
    public void getVehiculoNuevo() {
        new NuevoVehiculoDialog();
    }

    public void getVehiculos() {
        new VehiculosGridController(VehiculosGridFrame.class.getName(), null, Vehiculo.class.getName(), null);
    }

    public void getMarcasModelos() {
        new DefaultAllGridFrameController(MarcasModelosGridFram.class.getName(), null, MarcaVehiculo.class.getName(), null);
    }

    public void getTipoColores() {
        new TipoColorGridFrameController(TipoColorGridFrame.class.getName(), null, TipoColor.class.getName(), null);
    }

    public void getClasificacionVehiculo() {
        new DefaultAllGridFrameController(DefaultMantenimientoGridFrame.class.getName(), null, ClasificacionVehiculo.class.getName(), "Clasificacion de Vehiculos");
    }

    public void getRamosCoberturas() {
        new DefaultAllGridFrameController(RamosCoberturasGridFram.class.getName(), null, RamoPoliza.class.getName(), null);
    }

    public void getBuscarVehiculo() {
        new BuscarVehiculoDialog(null).setVisible(true);
    }

    public void getHistorialVehiculo() {
        new BuscarHistorialVehiculoDialog(null).setVisible(true);
    }

    // </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Polizas">
    public void getPolizaNuevo() {
        NumeroPolizaDialog numPol = new NumeroPolizaDialog(null);
        numPol.setVisible(true);
//        if (numPol.isOk()) {
//            new PolizaDetailController(PolizaDetailFrame.class.getName(), null, numPol.getPoliza(), true, numPol.isNuevo());
//        }
    }

    public void getPolizas() {
        new PolizasGridController(PolizasGridFrame.class.getName(), null, Poliza.class.getName(), null);
    }

    public void getGrupoPoliza() {
        new DefaultAllGridFrameController(DefaultMantenimientoGridFrame.class.getName(), null, GrupoPoliza.class.getName(), "Grupos de Poliza");
    }

    public void getRamoPoliza() {
        new DefaultAllGridFrameController(RamoGridFrame.class.getName(), null, RamoPoliza.class.getName(), null);
    }

    public void getBuscarPoliza() {
        new BuscarPolizaDialog2(null).setVisible(true);
    }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Recibos">
    public void getReciboNuevo() {
        // new ReciboDetailController(ReciboDetailFrame.class.getName(), null, null, true, null);
        NumeroReciboDialog numPol = new NumeroReciboDialog(null, null);
        numPol.setVisible(true);
        if (numPol.isOk()) {
            new ReciboDetailController(ReciboDetailFrame.class.getName(), null, numPol.getRecibo(), true, null, numPol.isNuevo());
        }
    }

    public void getRecibos() {
        new RecibosGridController(RecibosGridFrame.class.getName(), null, Recibo.class.getName(), null);
    }

    public void getTipoDist() {
        new TipoDistribucionGridController(DefaultMantenimientoGridFrame.class.getName(), TipoDistribucionDetailFrame.class.getName(), TipoDistribucion.class.getName(), "Porcentaje en Distribucion de Comisiones");
    }

    public void getTipoZona() {
        new DefaultAllGridFrameController(DefaultMantenimientoGridFrame.class.getName(), null, TipoZona.class.getName(), "Tipo Zona");
    }

    public void getBuscarRecibo() {
        new BuscarReciboDialog2(null).setVisible(true);
    }

    public void getDist() {
        new DefaultGridFrameController(DistribucionGridFrame.class.getName(), null, Distribucion.class.getName(), null);
    }

    // </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Financiamiento">
    public void getFinanciamientoNuevo() {
        NumeroFinanciamientoDialog numPol = new NumeroFinanciamientoDialog(null);
        numPol.setVisible(true);
        if (numPol.isOk()) {
            new FinanciamientoDetailController(FinanciamientoDetailFrame.class.getName(), null, numPol.getFinanciamiento(), true, null, numPol.isNuevo());
        }
    }

    public void getFinanciamientos() {
        new FinanciamientoGridController(FinanciamientosGridFrame.class.getName(), FinanciamientoDetailFrame.class.getName(), Financiamiento.class.getName(), null);
    }

    public void getBuscarFinanciamiento() {
        new BuscarFinanciamientoDialog2(null).setVisible(true);
    }

// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Siniestros">
    public void getSiniestroNuevo() {
        new SiniestroDetailController(SiniestroDetailFrame.class.getName(), null, null, false);
    }

    public void getSiniestrosGrid() {
        new SiniestrosGridController(SiniestrosGridFrame.class.getName(), null, Siniestro.class.getName(), null);
    }

    public void getCausaSiniestro() {
        new DefaultAllGridFrameController(CausaGridFrame.class.getName(), null, CausaSiniestro.class.getName(), null);
    }

    public void getDañoVehiculo() {
        new DefaultAllGridFrameController(DañoVehiculoGridFrame.class.getName(), null, DañoVehiculo.class.getName(), null);
    }    
    
    public void getBuscarSiniestro() {
        new BuscarSiniestroDialog2(null).setVisible(true);
    }
    // </editor-fold>

// <editor-fold defaultstate="collapsed" desc="Administracion">
    public void getFacturaCompraNueva() {
        new FacturaCompraDetailController(FacturaCompraDetailFrame.class.getName(), null, null, false);
    }

    public void getFacturasCompras() {
        new FacturasGridController(FacturasCompraGridFrame.class.getName(), null, Factura2.class.getName(), null);
    }

    public void getTodosEgresos() {
        new DefaultGridFrameController(EgresosGridFrame.class.getName(), null, Movimiento.class.getName(), null);
    }

    public void getEgresosExtraordinarios() {
        new EgresoExtraordinarioGridController(EgresosExtraordinarioGridFrame.class.getName(), null, Movimiento.class.getName(), null);
    }
    // </editor-fold>

// <editor-fold defaultstate="collapsed" desc="SMS">
    public void getSMSSolo() {
        new SMSDetailController(SMSDetailFrame.class.getName(), null, null, false);
    }

    public void getSMSPreEscrito() {
        new DefaultDetailFrameController(SMSPreEscritoDetailFrame.class.getName(), null, null, false);
    }

    public void getSMSPreEscritos() {
        new DefaultGridFrameController(SMSPreEscritosGridFrame.class.getName(), SMSPreEscritoDetailFrame.class.getName(), SMSPreEscrito.class.getName(), null);
    }

    public void getSMSEnviados() {
        new SMSGridFrameController(SMSGridFrame.class.getName(), SMSDetailFrame.class.getName(), SMS.class.getName(), null);
    }

    public void getSMSMasa() {
        new MasaGridFrameController(SMSMasaGridFrame.class.getName(), SMSMasaDetailFrame.class.getName(), SMSMasa.class.getName(), null);
    }

    public void getSMSCumpleayeros() {
        new MasaDetailController(TipoSMS.CUMPLEAYO_SMS, "SMS a Cumpleañeros", SMSMasaDetailFrame.class.getName(), null, null, false);
    }

    public void getSMSGiros() {
        new MasaDetailController(TipoSMS.GIRO_SMS, "SMS por Fecha de Vencimiento de Giros", SMSMasaDetailFrame.class.getName(), null, null, false);
    }

    public void getSMSDocumentos() {
        new MasaDetailController(TipoSMS.DOCUMENTO_SMS, "SMS por Fecha de Vencimiento de Documentos Anexos ", SMSMasaDetailFrame.class.getName(), null, null, false);
    }

    public void getSMSRecibos() {
        new MasaDetailController(TipoSMS.RENOVACION_SMS, "SMS por Fecha de Vencimiento de Recibo/Poliza", SMSMasaDetailFrame.class.getName(), null, null, false);
    }

    public void getSMSRTodos() {
        new MasaDetailController(TipoSMS.TODOS_SMS, "SMS a todos los contactos", SMSMasaDetailFrame.class.getName(), null, null, false);
    }

    public void getSMSRecibidos() {
        new SMSEntradaActualizar().Actualizar();
        new SMSEntradaGridFrameController(SMSEntradaGridFrame.class.getName(), SMSEntradaDetailFrame.class.getName(), SMSEntrada.class.getName(), null);
    }
// </editor-fold>

    public void getNuevaLiquidacion() {
        new LiquidacionDetailFrameController(LiquidacionDetailFrame.class.getName(), null, null, true);
    }

    public void getLiquidaciones() {
        new DefaultGridFrameController(LiquidacionCompaniaGridFrame.class.getName(), LiquidacionDetailFrame.class.getName(), LiquidacionCompania.class.getName(), null);
    }

    public void getEncabezado(String name) {
        new DefaultAllGridFrameController(EncabezadoGridFrame.class.getName(), null, Encabezado.class.getName(), "Encabezados de Reporte");
    }

    public void getTipoDocAnex(String name) {
        new DefaultAllGridFrameController(TipoDocumentoGridFrame.class.getName(), null, TipoDocumento.class.getName(), "Tipo Docuemtnos Anexos");
    }

    public void getTipoDocAnex() {
        new DefaultAllGridFrameController(TipoDocumentoGridFrame.class.getName(), null, TipoDocumento.class.getName(), "Tipo Docuemtnos Anexos");
    }

    public void getUsuarios(String name) {
        if (General.usuario.getAdministraUsuarios()) {
            new UsuariosGridController(vista.sistema.UsuariosGridFrame.class.getName(), vista.sistema.UsuarioDetailFrame.class.getName(), modelo.entidades.Usuario.class.getName(), null);
        } else {
            new UsuarioDetailController(vista.sistema.UsuarioDetailFrame.class.getName(), null, General.usuario, false);
        }
    }

    public void getEmpresa(String name) {
        new DefaultDetailFrameController(vista.sistema.EmpresaDetailFrame.class.getName(), null, General.empresa, false);
    }

    public void getConfiguracion(String name) {
        new DefaultDetailFrameController(vista.sistema.ConfiguracionDetailFrame.class.getName(), null, General.empresa, false);
    }

    public void getLicencias(String name) {
        new DefaultAllGridFrameController(vista.sistema.LicenciasGridFrame.class.getName(), null, modelo.entidades.Licencia.class.getName(), "Licencias");
    }

    public void getComunicadosGrid(String name) {
        new ComunicadosGridController();
    }

    public void getReporteH(String name) {
        new ReportesGridController(vista.reportes.ReportesGridFrame.class.getName(), null, modelo.entidades.Reporte.class.getName(), null);
    }

    public void getHelpCenter() {
        new CorreosGridFrameController(Mensaje.class.getName());
    }

    public void getExit() {
        MDIFrame.getInstance().menuFileExit_actionPerformed(null);
    }

    public void getHelp() {
        if (Principal.helpManager != null) {
            Principal.helpManager.showNavigatorWindow();
        }
    }

    public void getTipoOrden() {
        new DefaultAllGridFrameController(DefaultMantenimientoGridFrame.class.getName(), null, TipoOrden.class.getName(), "Tipo Orden de Pago");
    }

    public void getConfigLnF() {
        if (tinyCP1) {
            TinyLookAndFeel.controlPanelInstantiated = true;
            System.setProperty("swing.aatext", "true");
            try {
                UIManager.setLookAndFeel("de.muntjak.tinylookandfeel.TinyLookAndFeel");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            cp = new ControlPanel();
            cp.openTheme2(new File(System.getProperty("user.dir") + "/Default.theme"));
            tinyCP1 = false;
        } else if (cp != null) {
            cp.theFrame.setVisible(true);
        }
    }

    public void AUN_NO_FUN() {
        OptionPane.showMessageDialog(
                MDIFrame.getInstance(),
                "build",
                "warning",
                JOptionPane.WARNING_MESSAGE);
    }

    public void getSMSAutomaticos() {
        new DefaultGridFrameController(SMSAutomaticoGridFrame.class.getName(), null, SMSAutomatico.class.getName(), null);
    }

    public void getOrdenComision() {
        new OrdenPagoDetailController(OrdenPagoDetailFrame.class.getName(), null, new OrdenDePago(), true, true);
    }

// <editor-fold defaultstate="collapsed" desc="Roles">
    public void getRoles() {
        new controlador.sistema.menu.RolOptionFrameController(RolOptionFrame.class.getName(), Rol.class.getName(), "Roles");
    }

    public void getNuevoRol() {

        if (General.usuario.getAdministraUsuarios()) {
            new RolGritFrameController(DefaultMantenimientoGridFrame.class.getName(), null, Rol.class.getName(), "Nuevo Rol");
        } else {
            JOptionPane.showMessageDialog(MDIFrame.getInstance(), "Notiene permisos para crear Nuevos Roles", "Error de Validacion de Roles", JOptionPane.ERROR_MESSAGE);
        }
    }
// </editor-fold>

    public void getSincronizar() {
        JFileChooser fjch = new JFileChooser();
        switch (fjch.showOpenDialog(MDIFrame.getInstance())) {
            case JFileChooser.CANCEL_OPTION:

                break;
            case JFileChooser.APPROVE_OPTION:

                break;
            case JFileChooser.ERROR_OPTION:

                break;
        }
    }

    public void changePass() {
        new CambiarPassDialog().showDialog();
    }

    public void getCalendario() {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                new DefaultDetailFrameController(CalendarioDetailFrame.class.getName(), null, null, false);
            }
        });
    }
}
