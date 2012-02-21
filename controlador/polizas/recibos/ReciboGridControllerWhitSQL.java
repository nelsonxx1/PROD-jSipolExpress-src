/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.polizas.recibos;

import controlador.util.DefaultGridControllerWhitSQL;
import java.awt.Color;
import modelo.util.bean.BeanVO;
import org.hibernate.type.Type;
import org.openswing.swing.message.receive.java.ValueObject;
import vista.polizas.recibos.ReciboDetailFrame;

/**
 *
 * @author PAPA
 */
public class ReciboGridControllerWhitSQL extends DefaultGridControllerWhitSQL {

    public ReciboGridControllerWhitSQL(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo, String sql, Object[] values, Type[] valueType) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo, sql, values, valueType);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        new ReciboDetailController(ReciboDetailFrame.class.getName(), null, (BeanVO) persistentObject, true, null, false);
    }
    
}
