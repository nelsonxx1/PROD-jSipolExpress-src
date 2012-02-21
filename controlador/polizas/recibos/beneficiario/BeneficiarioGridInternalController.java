/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador.polizas.recibos.beneficiario;

import controlador.personas.PersonasDetailController;
import controlador.util.DefaultGridInternalController;
import java.util.ArrayList;
import modelo.entidades.personas.maestra.PersonaNatural;
import modelo.entidades.polizas.recibos.maestra.Beneficiario;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author PAPA
 */
public class BeneficiarioGridInternalController extends DefaultGridInternalController{

    public BeneficiarioGridInternalController(String classNameModelFullPath, String getMethodName, GridControl miGrid, ArrayList<DefaultGridInternalController> listSubGrids) {
        super(classNameModelFullPath, getMethodName, miGrid, listSubGrids);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        PersonaNatural p = ((Beneficiario)persistentObject).getBeneficiario();
        new PersonasDetailController(null, p, null);
    }

}
