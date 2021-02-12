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
public class ResizeAction extends DraggableAction {
    private Point _initialCenter = null, _initialPoint = null;
    private int _initialWidth, _initialHeight;
    private int _verticalDirection, _horizontalDirection;
    private Point _initialPosition;
    public ResizeAction(Shape selectedShape) {
        super(selectedShape);
    }
    
    private int getSign(int n) {
        if (n < 0) return -1;
        else if (n > 0) return 1;
        else return 0;
    }
    
    @Override
    public void handleMouseDragged(MouseEvent e) {
        if (_initialPoint == null) {
            _initialCenter = _selectedShape.getCenter();
            _initialPoint = e.getPoint();
            _verticalDirection = getSign(_initialPoint.y - _initialCenter.y);
            _horizontalDirection = getSign(_initialPoint.x - _initialCenter.x);
            _initialWidth = _selectedShape.getWidth();
            _initialHeight = _selectedShape.getHeight();
            _initialPosition = _selectedShape.getPosition();
        }
        int xDiff = e.getX() - _initialPoint.x, yDiff = e.getY() - _initialPoint.y;
        setCursor(getCursor());
            
        if (_verticalDirection < 0) {
            _selectedShape.setPosition(_selectedShape.getPosition().x, _initialPosition.y + yDiff);
            yDiff *= -1;
        }
        if (_horizontalDirection < 0) {
            _selectedShape.setPosition(_initialPosition.x + xDiff, _selectedShape.getPosition().y);
            xDiff *= -1;
        }
        
        if (_initialWidth <= -xDiff || _initialHeight <= -yDiff)
            return;
        
        _selectedShape.setSize(_initialWidth + xDiff, _initialHeight + yDiff);
    }

    private Cursor getCursor() {
        if (_initialPoint == null) {
            _verticalDirection = 0;
            _horizontalDirection = 0;
        }
        if (_verticalDirection < 0 && _horizontalDirection < 0) {
            return Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
        } else if (_verticalDirection < 0 && _horizontalDirection == 0) {
            return Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR);
        } else if (_verticalDirection < 0 && _horizontalDirection > 0) {
            return Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR);
        } else if (_verticalDirection == 0 && _horizontalDirection < 0) {
            return Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR);
        } else if (_verticalDirection == 0 && _horizontalDirection > 0) {
            return Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR);
        } else if (_verticalDirection > 0 && _horizontalDirection < 0) {
            return Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR);
        } else if (_verticalDirection > 0 && _horizontalDirection == 0) {
            return Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);
        } else if (_verticalDirection > 0 && _horizontalDirection > 0) {
            return Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR);
        } else {
            return null;
        }
    }

    @Override
    public Cursor getFirstCursor() {
        try {
            return CustomCursor.getResizeCursor();
        } catch (IOException ex) {
            return null;
        }
    }
}
