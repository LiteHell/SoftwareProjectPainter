/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package painter.Action;

import java.awt.Cursor;
import painter.Shape.Shape;
import painter.CursorChangable;

/**
 *
 * @author liteh
 */
public abstract class Action {
    protected final Shape _selectedShape;
    public Action(Shape selectedShape) {
        _selectedShape = selectedShape;
    }
    
    private CursorChangable _cursorListener;
    public void setCursorListener(CursorChangable listener) {
        _cursorListener = listener;
    }
    protected void setCursor(Cursor cursor) {
        if (_cursorListener != null)
            _cursorListener.setCursor(cursor);
    }
    public abstract Cursor getFirstCursor();
}
