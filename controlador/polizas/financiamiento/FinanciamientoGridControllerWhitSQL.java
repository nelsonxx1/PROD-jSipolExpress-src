/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador.polizas.financiamiento;

import controlador.util.DefaultGridControllerWhitSQL;
import java.awt.Color;
import modelo.Dominios.EstatusFinanciamiento;
import modelo.util.bean.BeanVO;
import org.hibernate.type.Type;
import org.openswing.swing.message.receive.java.ValueObject;
import vista.polizas.financiamiento.FinanciamientoDetailFrame;

/**
 *
 * @author PAPA
 */
public class FinanciamientoGridControllerWhitSQL extends DefaultGridControllerWhitSQL{

    public FinanciamientoGridControllerWhitSQL(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo, String sql, Object[] values, Type[] valueType) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo, sql, values, valueType);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        new FinanciamientoDetailController(FinanciamientoDetailFrame.class.getName(), null, (BeanVO) persistentObject , true, null, false);
    }

    @Override
    public Color getBackgroundColor(int row, String attributeName, Object value) {
        if (attributeName.equalsIgnoreCase("estatus")) {
//            System.out.println(value);
            if (value != null) {
                switch (((EstatusFinanciamiento) value)) {
                    case PAGADO:
                        return Color.GREEN;
                    case PENDIENTE:
                        return Color.RED;
                    case SINGIROS:
                        return Color.ORANGE;
                }
            }

        }
        return super.getBackgroundColor(row, attributeName, value);
    }
}
