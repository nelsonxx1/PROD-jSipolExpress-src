/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.entidades.sms;

import controlador.General;
import controlador.sms.EnviarSMS;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import modelo.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.openswing.swing.mdi.client.MDIFrame;

/**
 *
 * @author adrian
 */
public class SMSEntradaActualizar {

    public void Actualizar() {
        boolean hay = EnviarSMS.hayInternetHost2();
        if (!hay) {
            JOptionPane.showMessageDialog(MDIFrame.getInstance(), "No hay Internet", "Mensaje", JOptionPane.WARNING_MESSAGE);
            return;
        }
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            String usuario = General.empresa.getNombre();
            URL url = new URL("http://desolinfor.comze.com//smsbox/index.php?usuario=" + usuario + "&inbox=1");
            System.out.println("read url");
            BufferedReader b = new BufferedReader(new InputStreamReader(url.openStream()));
            String l = b.readLine().trim();
            if (l.indexOf("<br /><br />") != -1) {
                String datos[] = l.split("<br /><br />");
                Transaction tx = s.beginTransaction();
                for (int i = 0; i < datos.length; i++) {
                    String[] campo = datos[i].split("<br />");
                    SMSEntrada en = new SMSEntrada();
                    en.setPara(campo[0]);
                    en.setDe(campo[1]);
                    en.setTexto(campo[2]);
                    en.setTelefono(campo[3]);
                    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String strFecha = campo[4];
                    try {
                        en.setFecha(formatoDelTexto.parse(strFecha));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    s.save(en);
                }
                tx.commit();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            s.close();
        }
    }
}
