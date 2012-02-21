/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.siniestros;

import controlador.util.DefaultGridControllerWhitSQL;
import java.awt.Color;
import modelo.Dominios.EstadoSiniestro;
import modelo.util.bean.BeanVO;
import org.hibernate.type.Type;
import org.openswing.swing.message.receive.java.ValueObject;
import vista.siniestros.SiniestroDetailFrame;

/**
 *
 * @author PAPA
 */
public class SiniestroGridControllerWhitSQL extends DefaultGridControllerWhitSQL {

    public SiniestroGridControllerWhitSQL(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo, String sql, Object[] values, Type[] valueType) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo, sql, values, valueType);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        new SiniestroDetailController(SiniestroDetailFrame.class.getName(), null, (BeanVO) persistentObject, true);
    }
    
    @Override
    public Color getBackgroundColor(int row, String attributeName, Object value) {        
        if (attributeName.equalsIgnoreCase("estado")) {
            System.out.println(value);
            if (value != null) {
                switch (((EstadoSiniestro) value)) {
                    case ABIERTO:
                        return Color.GREEN;
                    case CERRADO:
                        return Color.RED;
                }
            }

        }
        return super.getBackgroundColor(row, attributeName, value);
    }
}
