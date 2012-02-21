package controlador.sms;

import controlador.General;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import modelo.Dominios.EstatusSMS;
import modelo.HibernateUtil;
import modelo.entidades.personas.maestra.Persona;
import modelo.entidades.personas.transac.TelefonoPersona;
import modelo.entidades.sms.SMS;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import vista.sms.SMSDetailFrame;

/**
 *
 * @author orlandobcrra
 */
public class EnviarSMS {

    public static Set<Persona> getPersonas(String numeros) {
        Set<Persona> lista = null;
        Session s = null;

        String arrayNumeros[] = numeros.split(",");
        for (int i = 0; i < arrayNumeros.length; i++) {
            arrayNumeros[i] = arrayNumeros[i].trim();
            if(arrayNumeros[i].startsWith("0"))
            {
                arrayNumeros[i]=arrayNumeros[i].substring(1, arrayNumeros[i].length()-1);
            }
            try {
                s = HibernateUtil.getSessionFactory().openSession();
                lista = new HashSet<Persona>();

                String sql = "SELECT DISTINCT P FROM " + Persona.class.getName() + " P LEFT JOIN P.telefonos T WHERE T.numeroCompleto like :numero";
                Query consulta=s.createQuery(sql);
                consulta.setString("numero", "%"+arrayNumeros[i]+"%");
                lista.addAll(consulta.list());

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                s.close();
            }
        }

        return lista;
    }

    public static void crearSMS(String nombre, String prod, Set<TelefonoPersona> telefonos) {
        SMS sms = new SMS();
        sms.setNombre(nombre);
        String numero = "";
        for (TelefonoPersona telefonoPersona : telefonos) {
            if (telefonoPersona.getNotificar()) {
                numero += "0" + telefonoPersona.getNumeroCompleto() + ",";
            }
        }
        if (numero.length() > 0) {
            numero = numero.substring(0, numero.length() - 1);
        } else {
            numero = "S/N";
        }
        sms.setNumero(numero);
        new SMSDetailController(SMSDetailFrame.class.getName(), null, sms, false);
    }

    public static void crearSMS(String nombre, String prod, Set<TelefonoPersona> telefonos, Persona persona) {
        SMS sms = new SMS(persona);
        sms.setNombre(nombre);
        String numero = "";
        for (TelefonoPersona telefonoPersona : telefonos) {
            if (telefonoPersona.getNotificar()) {
                numero += "0" + telefonoPersona.getNumeroCompleto() + ",";
            }
        }
        if (numero.length() > 0) {
            numero = numero.substring(0, numero.length() - 1);
        } else {
            numero = "S/N";
        }
        sms.setNumero(numero);
        new SMSDetailController(SMSDetailFrame.class.getName(), null, sms, false);
    }

    public static void crearSMS(String nombre, String prod, Persona persona) {
        SMS sms = new SMS(persona);
        sms.setNombre(nombre);
        String numero = "";
        for (TelefonoPersona telefonoPersona : persona.getTelefonos()) {
            if (telefonoPersona.getNotificar()) {
                numero += "0" + telefonoPersona.getNumeroCompleto() + ",";
            }
        }
        if (numero.length() > 0) {
            numero = numero.substring(0, numero.length() - 1);
        } else {
            numero = "S/N";
        }
        sms.setNumero(numero);
        new SMSDetailController(SMSDetailFrame.class.getName(), null, sms, false);
    }

    public static EstatusSMS enviarSMS(SMS sms) throws UnsupportedEncodingException {
        if (sms.getTexto().length() > 160) {
            return EstatusSMS.MUY_LARGO;
        }
        if ((sms.getNumero().length() < 11)) {
            return EstatusSMS.SIN_NUMERO;
        }
        String host = "http://www.interconectados.net/api2/?";
        String usuario = General.empresa.getSmsUsuario();
        String clave = General.empresa.getSmsClave();
        String telf = sms.getNumero();
        String text = java.net.URLEncoder.encode(sms.getTexto(), "ISO-8859-1");


        URL url;
        try {
            url = new URL(host + "phonenumber=" + telf + "&Text=" + text + "&user=" + usuario
                    + "&password=" + clave);
            BufferedReader b = new BufferedReader(new InputStreamReader(url.openStream()));
            String respuesta = b.readLine();
            b.close();
            if (respuesta.startsWith("200")) {
                saveSMS(sms);
                return EstatusSMS.ENVIADO;
            }
            if (respuesta.equals("401")) {
                return EstatusSMS.ATENTICACION_INVALIDA;
            }
            if (respuesta.equals("402")) {
                return EstatusSMS.CREDITOS_INSUFICIENTES;
            }
            if (respuesta.equals("403")) {
                return EstatusSMS.CUENTA_INVALIDA;
            }
            if (respuesta.equals("501")) {
                return EstatusSMS.TIPOCUENTA_INVALIDA;
            }
            if (respuesta.equals("502")) {
                return EstatusSMS.PETICION_SOBRECARGADA;
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
            System.err.println(ex.getMessage());
            System.out.println("sin internet");
            return EstatusSMS.NO_ENVIADO2;
        }
        return EstatusSMS.NO_ENVIADO;
    }

    public static boolean enviarSMSs(Set<SMS> smss) {
        if (hayInternet()) {
            Session s = null;
            try {
                s = HibernateUtil.getSessionFactory().openSession();
                for (SMS sms : smss) {
                    EstatusSMS estatus =
                            enviarSMS(sms);
                    sms.setEstatus(estatus);
                    if (sms.getPersona() != null) {
                        Persona p = sms.getPersona();
                        s.refresh(p);
                        Hibernate.initialize(p.getSmss());
                        p.getSmss().add(sms);
                        s.update(p);
                    } else {
                        s.update(sms);
                    }
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            } finally {
                s.close();
            }
        } else {
            return false;
        }
    }

    public static boolean hayInternet() {
        try {
            URL url = new URL("http://www.interconectados.net");
            BufferedReader b = new BufferedReader(new InputStreamReader(url.openStream()));
            String respuesta = b.readLine();
            System.out.println(respuesta);
            b.close();
            return true;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }

    public static boolean saveSMS(SMS sms) {
        try {
            String telf = sms.getNumero(), texto = sms.getTexto().replaceAll(" ", "%20");
            texto = texto.replaceAll("&", "%26");
            texto = texto.replaceAll("\r", "\n");
            texto = texto.replaceAll("\n", "%0A");

            String usuario = General.empresa.getNombre();
            String para = sms.getNombre();
            
            String telf = java.net.URLEncoder.encode(sms.getNumero(), "ISO-8859-1");
            String texto = java.net.URLEncoder.encode(sms.getTexto(), "ISO-8859-1");
            String usuario = java.net.URLEncoder.encode(General.empresa.getNombre(), "ISO-8859-1");
            String para = java.net.URLEncoder.encode(sms.getNombre(), "ISO-8859-1");
            URL url = new URL("http://desolinfor.comze.com//smsbox/index.php?telf=" + telf + "&texto=" + texto + "&usuario=" + usuario + "&para=" + para);
            BufferedReader b = new BufferedReader(new InputStreamReader(url.openStream()));
            String respuesta = b.readLine();
            System.out.println(respuesta);
            b.close();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean hayInternetHost2() {
        try {
            URL url = new URL("http://desolinfor.comze.com//smsbox/index.php?");
            BufferedReader b = new BufferedReader(new InputStreamReader(url.openStream()));
            String respuesta = b.readLine();
            System.out.println(respuesta);
            b.close();
            return true;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }
}
