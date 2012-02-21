/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.vehiculos;

import controlador.util.DefaultGridControllerWhitSQL;
import modelo.entidades.vehiculos.maestra.Vehiculo;
import modelo.util.bean.BeanVO;
import org.hibernate.type.Type;
import org.openswing.swing.message.receive.java.ValueObject;
import vista.vehiculos.VehiculosDetailFrame;

/**
 *
 * @author PAPA
 */
public class VehiculoGridControllerWhitSQL extends DefaultGridControllerWhitSQL {

    public VehiculoGridControllerWhitSQL(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo, String sql, Object[] values, Type[] valueType) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo, sql, values, valueType);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        new VehiculoDetailController(VehiculosDetailFrame.class.getName(), null, (BeanVO) persistentObject, false);
    }
}
