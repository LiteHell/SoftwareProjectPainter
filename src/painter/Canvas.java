/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package painter;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import painter.Shape.Shape;

/**
 *
 * @author liteh
 */
public class Canvas implements Serializable {
    private static final long serialVersionUID = 1L;

    //public int width = 500;
    //public int height = 500;
    public ArrayList<Shape> shapes = new ArrayList<Shape>();
    public Shape selectedShape;
    
    public void writeTo(OutputStream outputStream) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        oos.writeObject(this);
        oos.flush();
        oos.close();
    }
    
    public void exportTo(String formatName, OutputStream outputStream) throws IOException {
        int height = 0, width = 0, padding = 10;
        for (Shape shape: shapes) {
            int maxShapeProp = Math.max(shape.getHeight(), shape.getWidth());
            height = Math.max(height, shape.getPosition().y + maxShapeProp + padding);
            width = Math.max(width, shape.getPosition().x + maxShapeProp + padding);
        }
        
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        for (Shape shape: shapes) {
            if (shape == selectedShape)
                shape.setSelectionBorderDisplay(false);
            shape.draw(g);
            if (shape == selectedShape)
                shape.setSelectionBorderDisplay(true);
        }
        
        ImageIO.write(image, formatName, outputStream);
    }
    
    public void exportFile(String formatName, String fileName) throws IOException {
        FileOutputStream fstr = new FileOutputStream(fileName);
        exportTo(formatName, fstr);
        fstr.close();
    }
    
    public void writeFile(String filename) throws IOException {
        FileOutputStream fstr = new FileOutputStream(filename);
        writeTo(fstr);
        fstr.close();
    }
    
    public static Canvas readFrom(InputStream inputStream) throws IOException {
        ObjectInputStream ois = new ObjectInputStream(inputStream);
        try {
            return (Canvas)ois.readObject();
        } catch (ClassNotFoundException ex) {
            return null;
        }
    }
    
    public static Canvas readFile(String filename) throws IOException {
        FileInputStream fstr = new FileInputStream(filename);
        Canvas result = readFrom(fstr);
        fstr.close();
        return result;
    }
}
