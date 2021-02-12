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
public class Circle extends Shape {

    @Override
    public void drawShape(Graphics g) {
        g.drawOval(_x, _y, _width, _height);
    }

    @Override
    public void fillShape(Graphics g) {
        g.fillOval(_x, _y, _width, _height);
    }
}
