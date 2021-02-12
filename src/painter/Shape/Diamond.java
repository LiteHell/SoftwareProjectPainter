/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package painter.Shape;

import java.awt.Graphics;
import painter.Shape.Shape;

/**
 *
 * @author liteh
 */
public class Diamond extends Shape {

    @Override
    public void drawShape(Graphics g) {
        g.drawLine(_x, _y + (_height / 2), _x + (_width / 2), _y);
        g.drawLine(_x + (_width / 2), _y, _x + _width, _y + (_height / 2));
        g.drawLine(_x + _width, _y + (_height / 2), _x + (_width / 2),  _y + _height);
        g.drawLine(_x + (_width / 2),  _y + _height, _x, _y + (_height / 2));
    }

    @Override
    public void fillShape(Graphics g) {
        int xPoints[] = {_x, _x + (_width / 2), _x + _width, _x + (_width / 2)};
        int yPoints[] = {_y + (_height / 2), _y, _y + (_height / 2), _y + _height};
        g.fillPolygon(xPoints, yPoints, 4);
    }
}
