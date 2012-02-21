package controlador.sms.masa;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import modelo.Dominios.EstatusSMS;
import modelo.HibernateUtil;
import modelo.entidades.Documento;
import modelo.entidades.auditoria.AuditoriaBasica;
import modelo.entidades.personas.maestra.Persona;
import modelo.entidades.personas.maestra.PersonaNatural;
import modelo.entidades.personas.transac.TelefonoPersona;
import modelo.entidades.polizas.financiamiento.Financiamiento;
import modelo.entidades.polizas.financiamiento.Giro;
import modelo.entidades.polizas.recibos.maestra.Recibo;
import modelo.entidades.sms.SMS;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.type.Type;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.util.client.ClientUtils;
import org.openswing.swing.util.server.HibernateUtils;
import vista.reportes.EsperaDialog;

/**
 *
 * @author Nelson
 */
public class CrearSMSMarsivos {

    private String sql;
    private Map filteredColumns;
    private String msj;
    private String motivo;
    private String nombreProductor;
    private AuditoriaBasica ab;
    private EsperaDialog es;
    private Set<SMS> lista = null;
    private Date fecha;
    private Date hasta;
    private Date desde;
    private GridControl grid;

    public CrearSMSMarsivos(GridControl grid) {
        this.grid = grid;
    }

    public Set<SMS> getFiltrados(String sql2, Map filteredColumns2, String mensj, String motivo2, String nombreProductor2, AuditoriaBasica ab2) {
        this.sql = sql2;
        this.filteredColumns = filteredColumns2;
        this.msj = mensj;
        this.motivo = motivo2;
        this.nombreProductor = nombreProductor2;
        this.ab = ab2;
        this.lista = null;

//        es = new EsperaDialog("Mensajes Filtrados", "Creando Mensajes Filtrados", null, false);
//
//        ClientUtils.centerDialog(MDIFrame.getInstance(), es);
//        new Thread() {
//
//            @Override
//            public void run() {
//                es.setVisible(true);

                Session s = null;
                try {
                    SessionFactory sf = HibernateUtil.getSessionFactory();
                    s = sf.openSession();
                    Response res = HibernateUtils.getAllFromQuery(
                            filteredColumns,
                            new ArrayList(0),
                            new ArrayList(0),
                            Persona.class,
                            sql,
                            new Object[0],
                            new Type[0],
                            "C",
                            sf,
                            s);
                    Set<SMS> lista2 = new HashSet<SMS>(0);
                    for (Object o : ((VOListResponse) res).getRows()) {
                        String msj2 = msj;
                        Persona p = ((Persona) o);
                        msj2 = msj2.replaceAll(":nombreLargo", p.getNombreLargo());
                        msj2 = msj2.replaceAll(":nombre", p.getNombreCorto());
                        crearLista(lista2, p, nombreProductor, motivo, msj2, new Date(), ab);
                    }
                    lista = lista2;
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    s.close();
                }

//                es.dispose();
//            }
//        }.start();
//        es.dispose();

        return lista;
    }

    public Set<SMS> getTodos(String mesj, String motivo2, String nombreProductor2, AuditoriaBasica ab2) {
        this.msj = mesj;
        this.motivo = motivo2;
        this.nombreProductor = nombreProductor2;
        this.ab = ab2;
        this.lista = null;

//        es = new EsperaDialog("Mensajes Global", "Creando Mensajes Para Todas las Personas.", null, false);
//
//        ClientUtils.centerDialog(MDIFrame.getInstance(), es);
//        new Thread() {
//
//            @Override
//            public void run() {
//                es.setVisible(true);

        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            Set<SMS> lista2 = new HashSet<SMS>(0);
            String sql = "FROM " + Persona.class.getName();
            Query q = s.createQuery(sql);
            List l = q.list();
            for (Object o : l) {
                String msj2 = msj;
                Persona p = ((Persona) o);
                msj2 = msj2.replaceAll(":nombreLargo", p.getNombreLargo());
                msj2 = msj2.replaceAll(":nombre", p.getNombreCorto());
                crearLista(lista2, p, nombreProductor, motivo, msj2, new Date(), ab);
            }
            lista = lista2;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            s.close();
        }

//                grid.reloadData();                
//                //es.dispose();
//            }
//        }.start();
////        es.dispose();

        return lista;

    }

    public Set<SMS> getCumpleayeros(Date fecha2, String mesj, String motivo2, String nombreProductor2, AuditoriaBasica ab2) {

        this.fecha = fecha2;
        this.msj = mesj;
        this.motivo = motivo2;
        this.nombreProductor = nombreProductor2;
        this.ab = ab2;
        this.lista = null;

//        es = new EsperaDialog("Mensajes Cumpleañeros", "Creando Mensajes Para Cumpleañeros.", null, false);
//
//        ClientUtils.centerDialog(MDIFrame.getInstance(), es);
//        new Thread() {
//
//            @Override
//            public void run() {
//                es.setVisible(true);

                Session s = null;
                try {
                    s = HibernateUtil.getSessionFactory().openSession();
                    Set<SMS> lista2 = new HashSet<SMS>(0);
                    String sql = "FROM " + PersonaNatural.class.getName() + " WHERE ";
                    Query q = null;
                    sql += "day(fechaNacimiento)=day(:fecha) AND month(fechaNacimiento)=month(:fecha)";
                    q = s.createQuery(sql).setDate("fecha", fecha);
                    List l = q.list();
                    for (Object o : l) {
                        PersonaNatural p = ((PersonaNatural) o);
                        String msj2 = msj;
                        msj2 = msj2.replaceAll(":nombreLargo", p.getNombreLargo());
                        msj2 = msj2.replaceAll(":nombre", p.getNombreCorto());
                        crearLista(lista2, p, nombreProductor, motivo, msj2, fecha, ab);
                    }
                    lista = lista2;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    s.close();
                }
//
//                es.dispose();
//            }
//        }.start();
//        es.dispose();

        return lista;
    }

    public Set<SMS> getCumpleayeros(Date desde2, Date hasta2, String mesj, String motivo2, String nombreProductor2, AuditoriaBasica ab2) {

        this.desde = desde2;
        this.hasta = hasta2;
        this.msj = mesj;
        this.motivo = motivo2;
        this.nombreProductor = nombreProductor2;
        this.ab = ab2;
        this.lista = null;

//        es = new EsperaDialog("Mensajes Cumpleañeros", "Creando Mensajes Para Cumpleañeros.", null, false);
//
//        ClientUtils.centerDialog(MDIFrame.getInstance(), es);
//        new Thread() {
//
//            @Override
//            public void run() {
//                es.setVisible(true);

                Session s = null;
                try {
                    s = HibernateUtil.getSessionFactory().openSession();

                    Set<SMS> lista2 = new HashSet<SMS>(0);
                    String sql = "FROM " + PersonaNatural.class.getName() + " WHERE ";
                    Query q = null;

                    sql += "day(fechaNacimiento) BETWEEN day(:desde) AND day(:hasta) AND month(fechaNacimiento) BETWEEN month(:desde) AND month(:hasta)";
                    q = s.createQuery(sql).setDate("desde", desde).setDate("hasta", hasta);

                    List l = q.list();
                    for (Object o : l) {
                        String msj2 = msj;
                        PersonaNatural p = ((PersonaNatural) o);
                        msj2 = msj2.replaceAll(":nombreLargo", p.getNombreLargo());
                        msj2 = msj2.replaceAll(":nombre", p.getNombreCorto());

                        crearLista(lista2, p, nombreProductor, motivo, msj2, p.getFechaNacimiento(), ab);
                    }

                    lista = lista2;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    s.close();
                }

//                es.dispose();
//            }
//        }.start();
//        es.dispose();

        return lista;
    }

    public Set<SMS> getDocumentosVencidos(Date fecha2, String mesj, String motivo2, String nombreProductor2, AuditoriaBasica ab2) {
        this.fecha = fecha2;
        this.msj = mesj;
        this.motivo = motivo2;
        this.nombreProductor = nombreProductor2;
        this.ab = ab2;
        this.lista = null;
//
//        es = new EsperaDialog("Mensajes Documentos Vencidos", "Creando Mensajes Para Documentos Vencidos de Personas.", null, false);
//
//        ClientUtils.centerDialog(MDIFrame.getInstance(), es);
//        new Thread() {
//
//            @Override
//            public void run() {
//                es.setVisible(true);

                Session s = null;
                try {
                    s = HibernateUtil.getSessionFactory().openSession();

                    Set<SMS> lista2 = new HashSet<SMS>(0);
                    //String sql = "FROM " + PersonaNatural.class.getName() + " WHERE ";
                    String sql = "SELECT p, d FROM " + Persona.class.getName() + " as p join p.documentos d WHERE d.fechaVencimiento";
                    Query q = null;

                    sql += "=:fecha";
                    q = s.createQuery(sql).setDate("fecha", fecha);

                    List l = q.list();
                    for (Object o : l) {
                        String msj2 = msj;
                        Object object[] = (Object[]) o;
                        Persona p = (Persona) object[0];
                        Documento d = ((Documento) object[1]);
                        msj2 = msj2.replaceAll(":nombreLargo", p.getNombreLargo());
                        msj2 = msj2.replaceAll(":nombre", p.getNombreCorto());
                        msj2 = msj2.replaceAll(":tipoDocumento", p.getNombreLargo());
                        Calendar c = Calendar.getInstance();
                        c.setTime(d.getFechaVencimiento());
                        msj2 = msj2.replaceAll(":fecha", c.get(Calendar.DATE) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR));
                        crearLista(lista2, p, nombreProductor, motivo, msj2, fecha, ab);
                    }

                    lista = lista2;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    s.close();
                }
//
//                es.dispose();
//            }
//        }.start();
//        es.dispose();

        return lista;
    }

    public Set<SMS> getDocumentosVencidos(Date desde2, Date hasta2, String mesj, String motivo2, String nombreProductor2, AuditoriaBasica ab2) {
        this.desde = desde2;
        this.hasta = hasta2;
        this.msj = mesj;
        this.motivo = motivo2;
        this.nombreProductor = nombreProductor2;
        this.ab = ab2;
        this.lista = null;
//
//        es = new EsperaDialog("Mensajes Documentos Vencidos", "Creando Mensajes Para Documentos Vencidos de Personas.", null, false);
//
//        ClientUtils.centerDialog(MDIFrame.getInstance(), es);
//        new Thread() {
//
//            @Override
//            public void run() {
//                es.setVisible(true);

                Session s = null;
                try {
                    s = HibernateUtil.getSessionFactory().openSession();

                    Set<SMS> lista2 = new HashSet<SMS>(0);
                    String sql = "SELECT p, d FROM " + Persona.class.getName() + " as p join p.documentos d WHERE d.fechaVencimiento ";
                    Query q = null;

                    sql += " BETWEEN :desde AND :hasta";
                    q = s.createQuery(sql).setDate("desde", desde).setDate("hasta", hasta);

                    List l = q.list();
                    for (Object o : l) {
                        String msj2 = msj;
                        Object object[] = (Object[]) o;
                        Persona p = (Persona) object[0];
                        Documento d = ((Documento) object[1]);
                        msj2 = msj2.replaceAll(":nombreLargo", p.getNombreLargo());
                        msj2 = msj2.replaceAll(":nombre", p.getNombreCorto());
                        msj2 = msj2.replaceAll(":tipoDocumento", p.getNombreLargo());
                        Calendar c = Calendar.getInstance();
                        c.setTime(d.getFechaVencimiento());
                        msj2 = msj2.replaceAll(":fecha", c.get(Calendar.DATE) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR));
                        crearLista(lista2, p, nombreProductor, motivo, msj2, d.getFechaVencimiento(), ab);
                    }
                    lista = lista2;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    s.close();
                }

//                es.dispose();
//            }
//        }.start();
//        es.dispose();

        return lista;

    }

    public Set<SMS> getGirosVencidos(Date fecha2, String mesj, String motivo2, String nombreProductor2, AuditoriaBasica ab2) {
        this.fecha = fecha2;
        this.msj = mesj;
        this.motivo = motivo2;
        this.nombreProductor = nombreProductor2;
        this.ab = ab2;
        this.lista = lista;
//
//        es = new EsperaDialog("Mensajes Giros Vencidos", "Creando Mensajes Para Giros Vencidos.", null, false);
//
//        ClientUtils.centerDialog(MDIFrame.getInstance(), es);
//        new Thread() {
//
//            @Override
//            public void run() {
//                es.setVisible(true);

                Session s = null;
                try {
                    s = HibernateUtil.getSessionFactory().openSession();

                    Set<SMS> lista2 = new HashSet<SMS>(0);
                    //String sql = "FROM " + PersonaNatural.class.getName() + " WHERE ";
                    String sql = "SELECT f.pagador, g FROM " + Financiamiento.class.getName()
                            + " as f join f.giros g WHERE g.estatus=:estatus AND g.fechaVencimiento";
                    Query q = null;

                    sql += "=:fecha";
                    q = s.createQuery(sql).setDate("fecha", fecha).setString("estatus", "PENDIENTE");

                    List l = q.list();
                    for (Object o : l) {
                        String msj2 = msj;
                        Object object[] = (Object[]) o;
                        Persona p = (Persona) object[0];
                        Giro g = (Giro) object[1];
                        msj2 = msj2.replaceAll(":nombreLargo", p.getNombreLargo());
                        msj2 = msj2.replaceAll(":nombre", p.getNombreCorto());
                        msj2 = msj2.replaceAll(":numero", "" + g.getNumero());
                        msj2 = msj2.replaceAll(":monto", new DecimalFormat("#.00").format(g.getMontoGiro()));
                        Calendar c = Calendar.getInstance();
                        c.setTime(g.getFechaVencimiento());
                        msj2 = msj2.replaceAll(":fecha", c.get(Calendar.DATE) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR));
                        crearLista(lista2, p, nombreProductor, motivo, msj2, fecha, ab);
                    }
                    lista = lista2;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    s.close();
                }
//
//                es.dispose();
//            }
//        }.start();
//        es.dispose();

        return lista;
    }

    public Set<SMS> getGirosVencidos(Date desde2, Date hasta2, String mesj, String motivo2, String nombreProductor2, AuditoriaBasica ab2) {
        this.desde = desde2;
        this.hasta = hasta2;
        this.msj = mesj;
        this.motivo = motivo2;
        this.nombreProductor = nombreProductor2;
        this.ab = ab2;
        this.lista = null;

//        es = new EsperaDialog("Mensajes Giros Vencidos", "Creando Mensajes Para Giros Vencidos.", null, false);
//
//        ClientUtils.centerDialog(MDIFrame.getInstance(), es);
//        new Thread() {
//
//            @Override
//            public void run() {
//                es.setVisible(true);

                Session s = null;
                try {
                    s = HibernateUtil.getSessionFactory().openSession();
                    Set<SMS> lista2 = new HashSet<SMS>(0);
                    String sql = "SELECT f.pagador, g FROM " + Financiamiento.class.getName()
                            + " as f join f.giros g WHERE g.estatus=:estatus AND g.fechaVencimiento";
                    Query q = null;
                    sql += " BETWEEN :desde AND :hasta";
                    q = s.createQuery(sql).setDate("desde", desde).setDate("hasta", hasta);
                    q.setString("estatus", "PENDIENTE");
                    List l = q.list();
                    for (Object o : l) {
                        String msj2 = msj;
                        Object object[] = (Object[]) o;
                        Persona p = (Persona) object[0];
                        Giro g = (Giro) object[1];
                        //String msj = smsMasa.getPre().getTexto();
                        msj2 = msj2.replaceAll(":nombreLargo", p.getNombreLargo());
                        msj2 = msj2.replaceAll(":nombre", p.getNombreCorto());
                        msj2 = msj2.replaceAll(":numero", "" + g.getNumero());
                        msj2 = msj2.replaceAll(":monto", new DecimalFormat("#.00").format(g.getMontoGiro()));
                        Calendar c = Calendar.getInstance();
                        c.setTime(g.getFechaVencimiento());
                        msj2 = msj2.replaceAll(":fecha", c.get(Calendar.DATE) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR));
                        crearLista(lista2, p, nombreProductor, motivo, msj2, g.getFechaVencimiento(), ab);
                    }
                    lista = lista2;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    s.close();
                }
//
//                es.dispose();
//            }
//        }.start();
//        es.dispose();

        return lista;

    }

    public Set<SMS> getRenovacion(Date fecha2, String mesj, String motivo2, String nombreProductor2, AuditoriaBasica ab2) {
        this.fecha = fecha2;
        this.motivo = motivo2;
        this.nombreProductor = nombreProductor2;
        this.ab = ab2;
        this.lista = null;

//        es = new EsperaDialog("Mensajes Renovacion", "Creando Mensajes Para Renovaciones.", null, false);
//
//        ClientUtils.centerDialog(MDIFrame.getInstance(), es);
//        new Thread() {
//
//            @Override
//            public void run() {
//                es.setVisible(true);

                Session s = null;
                try {
                    s = HibernateUtil.getSessionFactory().openSession();
                    Set<SMS> lista2 = new HashSet<SMS>(0);
                    String sql = "FROM " + Recibo.class.getName() + " WHERE fechaCobro is not null AND vigenciaHasta";
                    Query q = null;
                    sql += "=:fecha";
                    q = s.createQuery(sql).setDate("fecha", fecha);
                    List l = q.list();
                    for (Object o : l) {
                        String msj2 = msj;
                        Recibo r = (Recibo) o;
                        Persona p = r.getAsegurado();
                        msj2 = msj2.replaceAll(":nombreLargo", p.getNombreLargo());
                        msj2 = msj2.replaceAll(":nombre", p.getNombreCorto());
                        Calendar c = Calendar.getInstance();
                        c.setTime(r.getVigenciaHasta());
                        msj2 = msj2.replaceAll(":fecha", c.get(Calendar.DATE) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR));
                        crearLista(lista2, p, nombreProductor, motivo, msj2, fecha, ab);
                    }
                    lista = lista2;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    s.close();
                }

//                es.dispose();
//            }
//        }.start();
//        es.dispose();

        return lista;
    }

    public Set<SMS> getRenovacion(Date desde2, Date hasta2, String mesj, String motivo2, String nombreProductor2, AuditoriaBasica ab2) {
        this.desde = desde2;
        this.hasta = hasta2;
        this.msj = mesj;
        this.motivo = motivo2;
        this.nombreProductor = nombreProductor2;
        this.ab = ab2;
        this.lista = null;

//        es = new EsperaDialog("Mensajes Renovacion", "Creando Mensajes Para Renovaciones.", null, false);
//
//        ClientUtils.centerDialog(MDIFrame.getInstance(), es);
//        new Thread() {
//
//            @Override
//            public void run() {
//                es.setVisible(true);

                Session s = null;
                try {
                    s = HibernateUtil.getSessionFactory().openSession();
                    Set<SMS> lista2 = new HashSet<SMS>(0);
                    String sql = "FROM " + Recibo.class.getName() + " WHERE fechaCobro is not null AND vigenciaHasta";
                    Query q = null;

                    sql += " BETWEEN :desde AND :hasta";
                    q = s.createQuery(sql).setDate("desde", desde).setDate("hasta", hasta);

                    List l = q.list();
                    for (Object o : l) {
                        String msj2 = msj;
                        Recibo r = (Recibo) o;
                        Persona p = r.getAsegurado();
                        msj2 = msj2.replaceAll(":nombreLargo", p.getNombreLargo());
                        msj2 = msj2.replaceAll(":nombre", p.getNombreCorto());
                        Calendar c = Calendar.getInstance();
                        c.setTime(r.getVigenciaHasta());
                        msj2 = msj2.replaceAll(":fecha", c.get(Calendar.DATE) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR));
                        crearLista(lista2, p, nombreProductor, motivo, msj2, r.getVigenciaHasta(), ab);
                    }
                    lista = lista2;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    s.close();
                }

//                es.dispose();
//            }
//        }.start();
//        es.dispose();

        return lista;
    }

    private void crearLista(Set<SMS> lista, Persona p, String nombreProductor, String motivo, String msj, Date fecha, AuditoriaBasica ab) {
        boolean marco = false;
        if (!p.getTelefonos().isEmpty()) {
            for (TelefonoPersona tel : p.getTelefonos()) {
                if (tel.isNotificar()) {
                    marco = true;
                    lista.add(new SMS(p.getNombreLargo(), nombreProductor, "0" + tel.getNumeroCompleto(), motivo, msj, fecha, EstatusSMS.PENDIENTE, ab,p));
                }
            }
        }
        if (!marco) {
            lista.add(new SMS(p.getNombreLargo(), nombreProductor, "S/N", motivo, msj, fecha, EstatusSMS.SIN_NUMERO, ab,p));
        }
    }
}
