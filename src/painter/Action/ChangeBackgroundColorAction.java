/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package painter.Action;

import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JColorChooser;
import painter.Shape.Shape;

/**
 *
 * @author liteh
 */
public class ChangeBackgroundColorAction extends DisposableAction {

    public ChangeBackgroundColorAction(Shape selectedShape) {
        super(selectedShape);
    }

    @Override
    public void perform() {
        Color _selectedColor = JColorChooser.showDialog(null, "Select Color", _selectedShape.getBackgroundColor());
        if (_selectedColor != null)
            _selectedShape.setBackgroundColor(_selectedColor);
    }

    @Override
    public Cursor getFirstCursor() {
        return Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
    }
    
}
