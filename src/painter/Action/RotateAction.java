/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package painter.Action;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.io.IOException;
import painter.Shape.Shape;

/**
 *
 * @author liteh
 */
public class RotateAction extends DraggableAction {
    
    private double _initialDragRadians;
    private double _initialShapeRadians;
    private boolean _needInitialRadians = true;
    private Cursor _cursor;

    public RotateAction(Shape selectedShape) {
        super(selectedShape);
        try {
            _cursor = CustomCursor.getRotateCursor();
        } catch (IOException err) {
            _cursor = null;
        }
    }
    
    @Override
    public void handleMouseDragged(MouseEvent e) {
        Point shapeCenter = _selectedShape.getCenter();
        if (shapeCenter.x == e.getX())
            return;
        double tan = (double)(shapeCenter.y - e.getY()) / (double)(shapeCenter.x - e.getX());
        double dragRadians = Math.atan(tan);
        if (shapeCenter.x < e.getX())
            dragRadians += dragRadians < 0 ? -Math.PI : Math.PI;
        if (_needInitialRadians) {
            _initialDragRadians = dragRadians;
            _initialShapeRadians = _selectedShape.getRadians();
            _needInitialRadians = false;
        } else {
            double newRadians = _initialShapeRadians + (dragRadians - _initialDragRadians);
            if (newRadians < -Math.PI)
                newRadians += Math.PI * 2;
            else if (newRadians > Math.PI)
                newRadians -= Math.PI * 2;
            double degrees = Math.toDegrees(newRadians);
            _selectedShape.setRadians(newRadians);
        }
    }

    
    @Override
    public Cursor getFirstCursor() {
        return _cursor;
    }
    
    
}
