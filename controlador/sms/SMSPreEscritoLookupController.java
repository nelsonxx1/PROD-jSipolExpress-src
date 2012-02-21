package controlador.sms;

import controlador.util.DefaultLookupDataLocator;
import controlador.util.DefaultLookupGridController;
import java.awt.Dimension;
import org.openswing.swing.lookup.client.LookupController;

/**
 *
 * @author Orlando Becerra
 */
public class SMSPreEscritoLookupController extends LookupController {

    public SMSPreEscritoLookupController() {
    }

    public SMSPreEscritoLookupController(
            String classFullName) {
        this.setLookupDataLocator(new DefaultLookupDataLocator(classFullName));
        this.setLookupGridController(new DefaultLookupGridController());
        setLookupValueObjectClassName(classFullName);
        setCodeSelectionWindow(GRID_FRAME);
        setOnInvalidCode(ON_INVALID_CODE_RESTORE_LAST_VALID_CODE);
        setAllColumnVisible(false);
        setVisibleColumn("motivo", true);
        setVisibleColumn("texto", true);
        setPreferredWidthColumn("motivo", 100);
        setPreferredWidthColumn("texto", 300);
        setFilterableColumn("motivo", true);
        setFilterableColumn("texto", true);
        setSortableColumn("motivo", true);
        setSortableColumn("texto", true);
        setFramePreferedSize(new Dimension(300, 330));
    }
}
