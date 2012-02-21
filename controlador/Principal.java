

package controlador;


import controlador.reportes.ReporteAutomatico;
import controlador.helpcenter.CorreoSTMP;
import controlador.helpcenter.CorreosGridFrameController;
import controlador.licencia.Equipo;
import controlador.util.DefaultFileChooser;
import internationalization.Spanish;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.tree.DefaultTreeModel;
import logger.LoggerUtil;
import modelo.Dominios;
import org.openswing.swing.mdi.client.ClientFacade;
import org.openswing.swing.mdi.client.MDIController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.util.client.ClientSettings;
import modelo.HibernateUtil;
import modelo.entidades.Empresa;
import modelo.entidades.Licencia;
import modelo.entidades.Usuario;
import modelo.entidades.defaultData.DefaultData;
import modelo.entidades.defaultData.DefaultPersona;
import modelo.entidades.defaultData.DefaultPoliza;
import modelo.entidades.defaultData.DefaultRecibo;
import modelo.entidades.defaultData.DefaultSiniestro;
import modelo.entidades.defaultData.DefaultVehiculo;
import modelo.entidades.helpcenter.maestra.Mensaje;
import modelo.entidades.sms.SMSPreEscrito;
import oracle.help.CSHManager;
import oracle.help.library.helpset.HelpSetParseException;
import org.hibernate.classic.Session;
import org.openswing.swing.client.OptionPane;
import org.openswing.swing.internationalization.java.Language;
import org.openswing.swing.logger.server.Log4JLogger;
import org.openswing.swing.logger.server.Logger;
import org.openswing.swing.lookup.client.LookupController;
import org.openswing.swing.mdi.client.Clock;
import org.openswing.swing.mdi.client.GenericStatusPanel;
import org.openswing.swing.permissions.client.LoginController;
import org.openswing.swing.table.profiles.client.FileGridProfileManager;
import org.openswing.swing.util.java.Consts;
import vista.login.LoginDialog2;
import vista.splash.Splash;
import vista.tip.cont;
import vista.tip.xxxtip;


/**
 *
 * @author Orlando Becerra
 * @author Nelson Moncada
 */
public class Principal implements MDIController, LoginController {

    static {
        General.version = "Version: 1.9.20 - 20/09/2010";
        General.edition = "jSipol Express Edition";
        General.splashLine2 = "Pólizas de Seguros";        
    }

    public static CSHManager helpManager = null;
    public static DefaultData defaultData = new DefaultData();
    public static Splash splah;
    private MenuPrincipal menuPrincipal;
    private Properties lenguajes = new Properties();

    public Principal() {
//        Properties p = System.getProperties();
//        p.list(System.out);

        splah = new Splash();
        splah.setValue("Inicializando log4j", 5);
        Logger.init(new Log4JLogger("APP"), Logger.LOG_ALL);

        splah.setValue("Conectando con Base de Datos", 20);
        validarLicencia();
        //getEmpresaLicencia();

//        System.out.println("--");
//        System.out.println(General.empresa.getSmsUsuario());
//        System.out.println(General.empresa.getSmsClave());
//        System.out.println("--");

//        splah.setValue("Inicializando Look And Feel", 15);
//        LookAndFeelInfo[] lfs = javax.swing.UIManager.getInstalledLookAndFeels();
//        for (LookAndFeelInfo lookAndFeelInfo : lfs) {
//            System.out.println(lookAndFeelInfo.getName());
//            System.out.println(lookAndFeelInfo.getClassName());
//        }
        //ClientSettings.LOOK_AND_FEEL_CLASS_NAME = javax.swing.UIManager.getSystemLookAndFeelClassName();
        //ClientSettings.LOOK_AND_FEEL_CLASS_NAME = "de.muntjak.tinylookandfeel.TinyLookAndFeel";
        //com.jgoodies.looks.plastic.PlasticXPLookAndFeel.setMyCurrentTheme(new com.jgoodies.looks.plastic.theme.ExperienceGreen());
        //com.jgoodies.looks.plastic.PlasticXPLookAndFeel.setMyCurrentTheme(new com.jgoodies.looks.plastic.theme.DesertBlue());
        //ClientSettings.LOOK_AND_FEEL_CLASS_NAME = "com.jgoodies.looks.plastic.PlasticXPLookAndFeel";
//        ClientSettings.LOOK_AND_FEEL_CLASS_NAME = javax.swing.UIManager.getCrossPlatformLookAndFeelClassName();
        //com.lipstikLF.LipstikLookAndFeel.installTheme(new com.lipstikLF.theme.KlearlooksTheme());
        //ClientSettings.LOOK_AND_FEEL_CLASS_NAME = "com.lipstikLF.LipstikLookAndFeel";
        //ClientSettings.LOOK_AND_FEEL_CLASS_NAME = "oracle.bali.ewt.olaf.OracleLookAndFeel";
        splah.setValue("Cargando Datos por Defecto", 30);
        try {
            defaultData.persona = (DefaultPersona) HibernateUtil.
                    getSessionFactory().openSession().createQuery("FROM " + DefaultPersona.class.
                    getName()).uniqueResult();
            General.defaultPersona = defaultData.persona;
            defaultData.vehiculo = (DefaultVehiculo) HibernateUtil.
                    getSessionFactory().openSession().createQuery("FROM " + DefaultVehiculo.class.
                    getName()).uniqueResult();
            defaultData.poliza = (DefaultPoliza) HibernateUtil.
                    getSessionFactory().openSession().createQuery("FROM " + DefaultPoliza.class.
                    getName()).uniqueResult();
            defaultData.recibo = (DefaultRecibo) HibernateUtil.
                    getSessionFactory().openSession().createQuery("FROM " + DefaultRecibo.class.
                    getName()).uniqueResult();
            defaultData.siniestro = (DefaultSiniestro) HibernateUtil.
                    getSessionFactory().openSession().createQuery("FROM " + DefaultSiniestro.class.
                    getName()).uniqueResult();
            defaultData.smsBienvenidaTodos = (ArrayList<SMSPreEscrito>) HibernateUtil.
                    getSessionFactory().openSession().createQuery("FROM " + SMSPreEscrito.class.
                    getName() + " WHERE UPPER(motivo) like :motivo").
                    setString("motivo", "%BIENVENIDA%").list();
            defaultData.smsBienvenida = defaultData.smsBienvenidaTodos.
                    get(0).getTexto();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(MDIFrame.getInstance(), "Error Cargando Datos por Defecto.\n" + ex.
                    getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            LoggerUtil.error(Principal.class, "new", ex);
            return;
        }

        splah.setValue("Cargando Propiedades", 50);
        //dominios
        Properties props = new Properties();
        try {
            props.load(getClass().getResourceAsStream(
                    "/Spanish.properties"));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(MDIFrame.getInstance(), "Error Cargando Propiedades.\n" + ex.
                    getMessage(), "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
            LoggerUtil.error(Principal.class, "new", ex);
        }
        splah.setValue("Estableciendo Propiedades", 70);

        new ClientSettings(
                new Spanish("Bs", props, true),
                Dominios.getDominios());

        splah.setValue("Configurando Aplicacion", 80);
        //ClientSettings.BACKGROUND = "background3.png";
        //ClientSettings.TREE_BACK = "background_tree.jpg";
        //ClientSettings.TREE_BACK = "blanco.jpg";
        //ClientSettings.BACKGROUND = "blanco.jpg";
        //ClientSettings.BACKGROUND = "companyIcon.png";
        ClientSettings.TREE_BACK="verde.jpg";
        ClientSettings.PROGRESS_BAR_COLOR= Color.BLUE;
        ClientSettings.BACK_IMAGE_DISPOSITION = Consts.BACK_IMAGE_STRETCHED;
        ClientSettings.DISABLED_INPUT_CONTROLS_FOCUSABLE = true;
        ClientSettings.FILL_FIND_FUNCTION_FIELD = true;
        ClientSettings.FILTER_SYMBOL = "filter2.png";
        ClientSettings.GRID_REQUIRED_CELL_BORDER = Color.RED;
        ClientSettings.INSERT_ROWS_ON_TOP = false;
        //ClientSettings.MENU_WIDTH=250;
        //ClientSettings.PERC_TREE_FOLDER
        //ClientSettings.SELECT_DATA_IN_EDITABLE_FORM=true;
        //ClientSettings.SELECT_DATA_IN_EDITABLE_GRID=false;
        ClientSettings.SHOW_SORTING_ORDER = true;
        ClientSettings.SHOW_TOOLTIP_IN_MENUBAR = false;
        ClientSettings.SHOW_TOOLTIP_IN_TREEMENU = false;
        ClientSettings.SHOW_FOCUS_BORDER_ON_FORM = false;
        ClientSettings.SHOW_FILTER_SYMBOL = true;
        ClientSettings.SHOW_SCROLLBARS_IN_MDI = true;
        ClientSettings.VIEW_BACKGROUND_SEL_COLOR = true;
        ClientSettings.VIEW_MANDATORY_SYMBOL = true;
        ClientSettings.ALLOW_OR_OPERATOR = false;
        ClientSettings.INCLUDE_IN_OPERATOR = false;
        ClientSettings.ICON_FILENAME = "icon.png";
        ClientSettings.BUTTON_RELOAD_IMAGE_NAME = "reload.png";
        ClientSettings.BUTTON_INSERT_IMAGE_NAME = "insert.png";
        ClientSettings.BUTTON_DELETE_IMAGE_NAME = "delete.png";
        ClientSettings.BUTTON_EXPORT_IMAGE_NAME = "printer.png";
        ClientSettings.BUTTON_FILTER_IMAGE_NAME = "filter2.png";
        //ClientSettings.MENU_WIDTH=ClientSettings.MENU_WIDTH-50;
        //ClientSettings.FILTER_PANEL_ON_GRID_POLICY=Consts.FILTER_PANEL_ON_GRID_USE_CLOSE_BUTTON;

        if (General.empresa.getFileGridProfileManager()) {
            ClientSettings.GRID_PROFILE_MANAGER =
                    new FileGridProfileManager();
        }
        ClientSettings.LOOKUP_FRAME_CONTENT =
                LookupController.GRID_AND_FILTER_FRAME;
        lenguajes.setProperty("ES", "Español");

        splah.setValue("Configurando Menu Principal", 50);
        menuPrincipal = new MenuPrincipal();

        splah.setValue("Contenido de Ayuda", 50);
        try {
            Class htmlBrowserClass = Class.forName(
                    "oracle.help.htmlBrowser.ICEBrowser");
            oracle.help.Help help = new oracle.help.Help(
                    htmlBrowserClass, false, false, false);
            oracle.help.library.helpset.HelpSet book = new oracle.help.library.helpset.HelpSet(
                    Principal.class, "jSipolHelp.hs");
            help.addBook(book);
            helpManager = new CSHManager(help);
            help.enableFavoritesNavigator(new File("helpFavorites").
                    toURI().toURL());
        } catch (MalformedURLException ex) {
            LoggerUtil.error(this.getClass(), "new", ex);
            JOptionPane.showMessageDialog(MDIFrame.getInstance(), "Error Cargando Ayuda (Favoritos).\n" + ex.
                    getMessage(), "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } catch (ClassNotFoundException ex) {
            helpManager = null;
            LoggerUtil.error(this.getClass(), "new", ex);
            JOptionPane.showMessageDialog(MDIFrame.getInstance(), "Error Cargando Ayuda.\n" + ex.
                    getMessage(), "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        } catch (HelpSetParseException ex) {
            helpManager = null;
            LoggerUtil.error(this.getClass(), "new", ex);
            JOptionPane.showMessageDialog(MDIFrame.getInstance(), "Error Cargando Ayuda.\n" + ex.
                    getMessage(), "Advertencia",
                    JOptionPane.WARNING_MESSAGE);
        }
        new DefaultFileChooser();
        splah.setValue("Abriendo Aplicacion", 100);

        if (true) {
            splah.dispose();
            LoginDialog2 login = new LoginDialog2(null, false, this,
                    null, null/*CryptUtils.getInstance*/, lenguajes,
                    "ES");
            ClientSettings.LOOK_AND_FEEL_CLASS_NAME = login.getLnF();
        } else {
            new MDIFrame(this);
            splah.dispose();
        }

        new ReporteAutomatico();

    }

    //MDIController
    public void afterMDIcreation(MDIFrame frame) {
        javax.swing.JMenuItem helpItem = new javax.swing.JMenuItem(
                "Contenido");
        frame.getMenuHelp().add(helpItem, 0);
        helpItem.setAccelerator(KeyStroke.getKeyStroke(
                java.awt.event.KeyEvent.VK_F1, 0));
        helpItem.setIcon(new ImageIcon(this.getClass().getResource(
                "/images/help.png")));
        helpItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (helpManager != null) {
                    helpManager.showNavigatorWindow();
                }
            }
        });

        javax.swing.JMenuItem soporteItem = new javax.swing.JMenuItem(
                "Soporte Ténico");
        frame.getMenuHelp().add(soporteItem, 1);
        //soporteItem.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        soporteItem.setIcon(new ImageIcon(this.getClass().getResource(
                "/images/helpcenter.png")));
        soporteItem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                new CorreosGridFrameController(Mensaje.class.getName());
            }
        });

        frame.getMenuHelp().add(new javax.swing.JSeparator(), 2);
        GenericStatusPanel companyPanel = new GenericStatusPanel();
        companyPanel.setColumns(30);
        companyPanel.setText(General.empresa.getNombre());
        MDIFrame.addStatusComponent(companyPanel);
        GenericStatusPanel userPanel = new GenericStatusPanel();
        userPanel.setColumns(12);
        MDIFrame.addStatusComponent(userPanel);
        userPanel.setText(General.usuario.getUserName());
        MDIFrame.addStatusComponent(new Clock());
        //new SplashScreen(frame, "kblackbox.png", getMDIFrameTitle(), 4);
        showTipFrame();
    }

    private void showTipFrame() {
        if (General.licencia.isActivoTipDelDia()) {
            xxxtip tipFrame1 = new xxxtip(new cont());
            MDIFrame.add(tipFrame1);
        }
    }

    public String getAboutImage() {
        //return "kblackbox.png";
        return null;
    }

    public String getAboutText() {
        return "jSipol Express Edition\n"
                + "\n"
                + "Sistema Integrado de Pólizas para Intermediarios de Seguros"
                + "\n"
                + "\n"
                + "Copyright 2009 Orlando Becerra, Nelson Moncada - Todos los Derechos Reservados\n"
                + "Telefonos: 0416-6762389; 0414-3765465; 0424-7040614 - Email: jsipolee@gmail.com\n"
                + "Version: 1.2 - 15/12/2009";
    }

    public DefaultTreeModel getApplicationFunctions() {
        return menuPrincipal.getApplicationFunctions();
    }

    public ClientFacade getClientFacade() {
        return menuPrincipal;
    }

    public int getExtendedState() {
        return JFrame.MAXIMIZED_BOTH;
    }

    public ArrayList getLanguages() {
        ArrayList list = new ArrayList();
        list.add(new Language("ES", "Español"));
        return list;
    }

    public String getMDIFrameTitle() {
        return General.edition;
    }

    public void stopApplication() {
        System.exit(0);
    }

    public boolean viewChangeLanguageInMenuBar() {
        return false;
    }

    public boolean viewFunctionsInMenuBar() {
        return false;
    }

    public boolean viewFunctionsInTreePanel() {
        return true;
    }

    public JDialog viewLoginDialog(JFrame parentFrame) {
        return new LoginDialog2(
                parentFrame,
                true,
                this,
                null,
                null,
                lenguajes,
                ClientSettings.getInstance().getResources().
                getLanguageId());
    }

    public boolean viewLoginInMenuBar() {
        return false;
    }

    public boolean viewOpenedWindowIcons() {
        return true;
    }

    public boolean authenticateUser(Map loginInfo) {
        String username = loginInfo.get("username").toString();
        String password = loginInfo.get("password").toString();
        byte[] encpassword = Equipo.encodeText(password);
        Session s;
        s = HibernateUtil.getSessionFactory().openSession();
        General.usuario = (Usuario) s.createQuery("FROM " + Usuario.class.
                getName()
                + " WHERE username=?").setString(0, username).
                uniqueResult();
        s.close();
        if (General.usuario == null || !Arrays.equals(encpassword, General.usuario.
                getPassword2())) {
            OptionPane.showMessageDialog(null, "user.pass.bad",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!General.usuario.getAuditoria().getActivo()) {
            OptionPane.showMessageDialog(null, "user.inactivo",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public int getMaxAttempts() {
        return 3;
    }

    public void loginSuccessful(Map loginInfo) {
        new MDIFrame(this);
    }

    public final void validarLicencia() {
        String validos = System.getProperty("valido");
        boolean valido = (validos != null && validos.equals("si"));
        if (!valido) {
            LoggerUtil.warn(this.getClass(), "validarLicencia",
                    "No ejecuto desde .EXE");
            CorreoSTMP.enviarCorreo("jsipolee@jsipol.com",
                    "jsipolee@gmail.com",
                    Equipo.getSerial()
                    + " No ejecuto desde .EXE jSipol Express Edition ", new Date().
                    toString());
            JOptionPane.showMessageDialog(null,
                    "Distribucion no autorizada de jSipol Express Edition.\n"
                    + "Contacte al proveedor para mayor informacion.\n"
                    + "jsipolee@gmail.com 0416-6762389 0414-3765465 0424-7040614",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        byte[] serial2 = Equipo.encodeText(Equipo.getSerial());
        String nombreEquipo = System.getProperty("COMPUTERNAME").
                toUpperCase();
        getEmpresaLicencia(serial2, nombreEquipo);

        if (General.licencia == null) {
            LoggerUtil.warn(this.getClass(), "validarLicencia",
                    "Copia no autorizada");
            CorreoSTMP.enviarCorreo("jsipolee@jsipol.com",
                    "jsipolee@gmail.com",
                    Equipo.getSerial()
                    + " Copia no autorizada de jSipol Express Edition", new Date().
                    toString());
            JOptionPane.showMessageDialog(null,
                    "Copia no autorizada de jSipol Express Edition.\n"
                    + "Contacte al proveedor si desea una copia legal.\n"
                    + "jsipolee@gmail.com 0416-6762389 0414-3765465 0424-7040614",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        } else if (!General.licencia.isActivo()) {
            LoggerUtil.warn(this.getClass(), "validarLicencia",
                    "Copia inactiva");
            CorreoSTMP.enviarCorreo("jsipolee@jsipol.com",
                    "jsipolee@gmail.com",
                    Equipo.getSerial()
                    + " jSipol Express Edition inactivo", new Date().
                    toString());
            JOptionPane.showMessageDialog(null,
                    "jSipol Express Edition inactivo.\n"
                    + "Contacte al proveedor porfavor.\n"
                    + "jsipolee@gmail.com 0416-6762389 0414-3765465 0424-7040614",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        } else if (!(General.licencia.getFechaDesde().before(
                new Date()) && General.licencia.getFechaHasta().after(
                new Date()))) {
            LoggerUtil.warn(this.getClass(), "validarLicencia",
                    "Copia caduco");
            CorreoSTMP.enviarCorreo("jsipolee@jsipol.com",
                    "jsipolee@gmail.com",
                    Equipo.getSerial()
                    + " jSipol Express Edition caduco", new Date().
                    toString());
            JOptionPane.showMessageDialog(null,
                    "jSipol Express Edition caduco.\n"
                    + "Contacte al proveedor porfavor.\n"
                    + "jsipolee@gmail.com 0416-6762389 0414-3765465 0424-7040614",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    public void getEmpresaLicencia(byte[] serial2, String nombreEquipo) {
        try {
            Session s =
                    HibernateUtil.getSessionFactory().openSession();
            General.licencia = (Licencia) s.createQuery(
                    "FROM " + Licencia.class.getName()
                    + " WHERE serialEquipo=? AND upper(nombreEquipo)=?").
                    setBinary(0, serial2).setString(1, nombreEquipo).
                    uniqueResult();
            General.empresa = (Empresa) s.createQuery("FROM " + Empresa.class.
                    getName()).uniqueResult();
            s.close();
        } catch (Exception ex) {
            LoggerUtil.warn(Principal.class, "validarLicencia",
                    "Error en Conexion con Data Base");
            OptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "Error en Conexion con Data Base",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    public void getEmpresaLicencia() {
        try {
            Session s =
                    HibernateUtil.getSessionFactory().openSession();
            General.licencia = (Licencia) s.createQuery("FROM " + Licencia.class.
                    getName()).setMaxResults(1).uniqueResult();
            General.empresa = (Empresa) s.createQuery("FROM " + Empresa.class.
                    getName()).uniqueResult();
            s.close();
        } catch (Exception ex) {
            LoggerUtil.warn(Principal.class, "validarLicencia",
                    "Error en Conexion con Data Base");
            OptionPane.showMessageDialog(
                    null,
                    ex.getMessage(),
                    "Error en Conexion con Data Base",
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }

    public boolean viewFileMenu() {
        return false;
    }

    public static void addComponent2HelpManager(Component component,
            String topicId) {
        if (helpManager != null) {
            helpManager.addComponent(component, topicId, true, true);
        }
    }

}
