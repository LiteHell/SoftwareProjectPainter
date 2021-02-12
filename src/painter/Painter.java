/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package painter;

import java.util.EventListener;
import javax.swing.JFrame;

/**
 *
 * @author liteh
 */
public class Painter {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PainterView view = new PainterView();
        Canvas canvas = new Canvas();
        PainterController controller = new PainterController(view, canvas);
        
        controller.Start();
    }
    
}
