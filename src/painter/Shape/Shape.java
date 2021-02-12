/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package painter.Shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.Serializable;

/**
 *
 * @author liteh
 */
public abstract class Shape implements Serializable {
    protected final int _selectionBorderSize = 4;
    private final int _borderSize = 1;
    private double _radians = 0;
    private boolean _displaySelectionBorder = false;
    private boolean _selectionBorderBlinkMode = false;
    protected int _x = 10;
    protected int _y = 10;
    protected int _width = 50;
    protected int _height = 50;
    protected Color _borderColor = Color.BLACK;
    protected Color _bgColor = Color.WHITE;
    
    public void setPosition(int x, int y) {
        _x = x;
        _y = y;
    }
    public Point getPosition() {
        return new Point(_x, _y);
    }
    public Point getCenter() {
        return new Point(_x + (_width / 2), _y + (_height / 2));
    }
    public void setBorderColor(Color color) {
        _borderColor = color;
    }
    public Color getBorderColor() {
        return _borderColor;
    }
    public void setBackgroundColor(Color color) {
        _bgColor = color;
    }
    public void setSelectionBorderDisplay(boolean doDisplay) {
        _displaySelectionBorder = doDisplay;
    }
    public Color getBackgroundColor() {
        return _bgColor;
    }
    public void setSize(int width, int height) {
        _width = width;
        _height = height;
    }
    public int getWidth () {
        return _width;
    }
    public int getHeight () {
        return _height;
    }
    public void setRadians(double radians) {
        _radians = radians;
    }
    public double getRadians() {
        return _radians;
    }
    public boolean isPointIn(int x, int y) {
        if (_radians != 0 && false) {
            double sin = Math.sin(_radians), cos = Math.cos(_radians);
            x -= this.getCenter().x;
            y -= this.getCenter().y;
            y *= -1;
            
            int x_new = (int)(x * cos - y * sin);
            int y_new = (int)(x * sin + y * cos);
            
            x = x_new + this.getCenter().x;
            y = y_new + this.getCenter().y;
        }
        return _x <= x && x <= _x + _width && _y <= y && y <= _y + _height;
    }
    protected Point getCenterOfGravity() {
        return getCenter();
    }
    public abstract void drawShape(Graphics g);
    public abstract void fillShape(Graphics g);
    public void draw(Graphics g) {
        Point gravityCenter = getCenter();//getCenterOfGravity();
        Graphics2D g2d = (Graphics2D)g.create();
        if (_radians % (Math.PI * 2) != 0.0) {
            g2d.rotate(_radians, gravityCenter.x, gravityCenter.y);
        }
        g2d.setColor(_bgColor);
        fillShape((Graphics)g2d);
        g2d.setColor(_borderColor);
        g2d.setStroke(new BasicStroke(_borderSize));
        drawShape((Graphics)g2d);
        if (_displaySelectionBorder) {
            _selectionBorderBlinkMode = !_selectionBorderBlinkMode;
            for (int i = 0; i < _width; i += _selectionBorderSize * 2) {
                int bstart = _selectionBorderBlinkMode ? _x + i : _x + i + _selectionBorderSize,
                    bend = Math.min(bstart + _selectionBorderSize, _x + _width),
                    wstart = _selectionBorderBlinkMode ? _x + i + _selectionBorderSize : _x + i,
                    wend = Math.min(wstart + _selectionBorderSize, _x + _width);
                
                g2d.setColor(Color.black);
                g2d.drawLine(bstart, _y, bend, _y);
                g2d.drawLine(bstart, _y + _height, bend, _y + _height);
                
                g2d.setColor(Color.white);
                g2d.drawLine(wstart, _y, wend, _y);
                g2d.drawLine(wstart, _y + _height, wend, _y + _height);
            }
            for (int i = 0; i < _height; i += _selectionBorderSize * 2) {
                int bstart = _selectionBorderBlinkMode ? _y + i : _y + i + _selectionBorderSize,
                    bend = Math.min(bstart + _selectionBorderSize, _y + _height),
                    wstart = _selectionBorderBlinkMode ? _y + i + _selectionBorderSize : _y + i,
                    wend = Math.min(wstart + _selectionBorderSize, _y + _height);
                
                g2d.setColor(Color.black);
                g2d.drawLine(_x, bstart, _x, bend);
                g2d.drawLine(_x + _width, bstart, _x + _width, bend);
                
                g2d.setColor(Color.white);
                g2d.drawLine(_x, wstart, _x, wend);
                g2d.drawLine(_x + _width, wstart, _x + _width, wend);
            }
        }
    }
}
