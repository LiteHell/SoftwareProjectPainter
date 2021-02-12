/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package painter;

import painter.Shape.Shape;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author liteh
 */
public class PaintablePanel extends JPanel implements ActionListener, CursorChangable {
    private final boolean printHitbox = false; // FOR DEBUG PURPOSE
    private Iterable<Shape> _shapes = null;
    private Timer _timer = new Timer(50, this);
    private Shape _selectedShape = null;
    
    public PaintablePanel() {
        setBackground(Color.white);
    }
    
    public void setShapes(Iterable<Shape> shapes) {
        _shapes = shapes;
        repaint();
    }
    
    public void setSeletcedShape(Shape shape) {
        _selectedShape = shape;
        if (shape == null)
            _timer.stop();
        else
            _timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // For debug Purpose
        if (printHitbox && _shapes != null) {
            Graphics2D g2d = (Graphics2D)g.create();
            g2d.setColor(Color.RED);
            for (int x = (int)g.getClipBounds().getX(); x < (int)g.getClipBounds().getWidth(); x++)
                for (int y = (int)g.getClipBounds().getY(); y < (int)g.getClipBounds().getHeight(); y++)
                    for (Shape shape : _shapes)
                        if (shape.isPointIn(x, y))
                            g2d.drawRect(x, y, 1, 1);
        }
        
        if (_shapes != null)
            for (Shape shape : _shapes) {
                Color color = shape.getBorderColor();
                
                shape.setSelectionBorderDisplay(shape == _selectedShape);
                
                g.setColor(color);
                shape.draw(g);
            }
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == _timer) {
            repaint();
        }
    }
}
