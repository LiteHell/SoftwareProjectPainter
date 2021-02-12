/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package painter.Shape;

import painter.Shape.Shape;
import java.awt.Graphics;
import painter.Shape.Shape;

/**
 *
 * @author liteh
 */
public class Line extends Shape {

    @Override
    public void drawShape(Graphics g) {
        g.drawLine(_x, _y, _x + _width, _y + _height);
    }

    @Override
    public void fillShape(Graphics g) {
        return;
    }
}
