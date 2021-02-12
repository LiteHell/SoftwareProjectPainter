/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package painter;

import painter.Shape.*;
import java.awt.Color;
import java.awt.Cursor;
import painter.Shape.Shape;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import painter.Action.*;

/**
 *
 * @author liteh
 */
public class PainterController {
    private final Canvas _canvas;
    private PainterView _view;
    private Action _action = null;
    
    public void refreshView(){
        _view.setShapes(_canvas.shapes);
    }
    
    public PainterController(PainterView view, Canvas canvas) {
        _view = view;
        _canvas = canvas;
    }
    
    public Action getAction() {
        return _action;
    }
    
    public void selectShape(Shape shape) {
        _canvas.selectedShape = shape;
        _view.setSelectedShape(shape);
        refreshView();
    }
    
    public Shape getSelectedShape() {
        return _canvas.selectedShape;
    }

    public Shape getShapeByPoint(Point point) {
        for (int i = _canvas.shapes.size() - 1; i >= 0; i--) {
            Shape shape = _canvas.shapes.get(i); // Reverse order
            if (shape.isPointIn(point.x, point.y)) {
                return shape;
            }
        }
        return null;
    }
    
    public Canvas getCanvas() {
        return _canvas;
    }
    public void setCanvas(Canvas canvas) {
        _canvas.selectedShape = canvas.selectedShape;
        _canvas.shapes = canvas.shapes;
        refreshView();
    }
    
    public void setAction(Action action) {
        this._action = action;
        if (action instanceof DisposableAction)
            ((DisposableAction)action).perform();
        if (action == null)
            _view.setCursor(null, null);
        else
            _view.setCursor(action.getFirstCursor(), action);
        refreshView();
    }
    
    public void addShape(Shape shape) {
        this._canvas.shapes.add(shape);
        refreshView();
    }
    
    public void removeShape(Shape shape) {
        this._canvas.shapes.remove(shape);
        refreshView();
    }
    
    public void Start() {
        MouseEventProcessing mouseEventProcessing = new MouseEventProcessing(this);
        
        _view.SetMenubarListener(mouseEventProcessing);
        _view.SetShapeButtonListener(mouseEventProcessing);
        _view.SetActionButtonListener(mouseEventProcessing);
        _view.Inititate();
        _view.addMouseListenerOfPaintArea(mouseEventProcessing);
        _view.addMouseMotionListenerOfPaintArea(mouseEventProcessing);
        
        _view.Show();
    }
}
