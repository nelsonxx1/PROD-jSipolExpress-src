/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.polizas;

import controlador.util.DefaultGridControllerWhitSQL;
import modelo.util.bean.BeanVO;
import org.hibernate.type.Type;
import org.openswing.swing.message.receive.java.ValueObject;
import vista.polizas.PolizaDetailFrame;

/**
 *
 * @author PAPA
 */
public class PolizaGridControllerWhitSQL extends DefaultGridControllerWhitSQL {

    public PolizaGridControllerWhitSQL(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo, String sql, Object[] values, Type[] valueType) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo, sql, values, valueType);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        new PolizaDetailController(PolizaDetailFrame.class.getName(), null, (BeanVO) persistentObject, true, false);
    }
}
