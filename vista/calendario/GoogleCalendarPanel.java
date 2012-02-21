

package vista.calendario;


/**
 *
 * @author orlandobcrra
 */
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

/**
 * @author Christopher Deckers
 */
public class GoogleCalendarPanel extends JPanel {

    public GoogleCalendarPanel() {
        super(new BorderLayout());

        NativeInterface.open();
        
        final JWebBrowser webBrowser = new JWebBrowser();

        String calendario = "7ar1maeri7bkocmpof66vhkcm4%40group.calendar.google.com&amp;";

        webBrowser.setHTMLContent(""
                + "<html>"
                + "<iframe src=\"https://www.google.com/calendar/embed?height=600&amp;wkst=1&amp;bgcolor=%23FFFFFF&amp;"
                + "src=" + calendario
                + "color=%23182C57&amp;ctz=America%2FCaracas\" style=\" border-width:0 \" width=\"600\" height=\"600\" frameborder=\"0\" scrolling=\"no\">"
                + "</iframe>"
                + "</html>"
                + "");
        webBrowser.setMenuBarVisible(false);
        webBrowser.setBarsVisible(false);
        webBrowser.setButtonBarVisible(false);
        webBrowser.setLocationBarVisible(false);
        webBrowser.setStatusBarVisible(true);
        add(webBrowser, BorderLayout.CENTER);
    }

    /* Standard main method to try that test as a standalone application. */
    public static void main(String[] args) {
        //UIUtils.setPreferredLookAndFeel();
        //NativeInterface.open();
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                JFrame frame = new JFrame("Google Calendar");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new GoogleCalendarPanel(), BorderLayout.CENTER);
                frame.setSize(700, 700);
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
            }
        });
        //NativeInterface.runEventPump();
    }
}
