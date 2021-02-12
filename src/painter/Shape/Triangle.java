/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package painter.Shape;

import painter.Shape.Shape;
import java.awt.Graphics;
import java.awt.Point;
import painter.Shape.Shape;

/**
 *
 * @author liteh
 */
public class Triangle extends Shape {

    @Override
    public void drawShape(Graphics g) {
        g.drawLine(_x, _y, _x + _width, _y);
        g.drawLine(_x, _y, _x + (_width / 2), _y + _height);
        g.drawLine(_x + _width, _y, _x + (_width / 2), _y + _height);
    }

    @Override
    public void fillShape(Graphics g) {
        int xPoints[] = {_x, _x + _width, _x + (_width / 2)};
        int yPoints[] = {_y, _y, _y + _height};
        g.fillPolygon(xPoints, yPoints, 3);
    }
    
    @Override
    protected Point getCenterOfGravity() {
        return new Point(_x + (_width / 2), _y + (_height / 3 * 2));
    }
}
