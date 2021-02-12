/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package painter.Action;

import java.awt.Cursor;
import painter.Shape.Shape;

/**
 *
 * @author liteh
 */
public abstract class DisposableAction extends Action {
    public DisposableAction(Shape selectedShape) {
        super(selectedShape);
    }
    public abstract void perform();
}
