/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package painter.Action;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseEvent;
import painter.Shape.Shape;

/**
 *
 * @author liteh
 */
public class MoveAction extends DraggableAction {
    private boolean _needPointDifference = true;
    private int _xDiff;
    private int _yDiff;

    public MoveAction(Shape selectedShape) {
        super(selectedShape);
    }
    
    @Override
    public void handleMouseDragged(MouseEvent e) {
        if (_needPointDifference) {
            _xDiff = e.getX() - _selectedShape.getPosition().x;
            _yDiff = e.getY() - _selectedShape.getPosition().y;
            _needPointDifference = false;
        }
        _selectedShape.setPosition(e.getX() - _xDiff, e.getY() - _yDiff);
    }

    @Override
    public Cursor getFirstCursor() {
        return Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR);
    }
}
