/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package painter.Action;

import java.awt.Point;
import java.awt.event.MouseEvent;
import painter.Shape.Shape;

/**
 *
 * @author liteh
 */
public abstract class DraggableAction extends Action {
    public DraggableAction(Shape selectedShape) {
        super(selectedShape);
    }
    public abstract void handleMouseDragged(MouseEvent e);
}
