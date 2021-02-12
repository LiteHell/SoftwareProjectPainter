/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package painter;

import painter.Shape.Shape;
import painter.Action.Action;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author liteh
 */
public class PainterView {
    private final JFrame _frame;
    private ActionListener _menubarListener;
    private ActionListener _shapeButtonListener;
    private ActionListener _actionButtonListener;
    private JMenuBar _menubar;
    private PaintablePanel _paintablePanel;
    public PainterView() {
        // Frame 생성
        _frame = new JFrame();
        _frame.setSize(800, 800);
        _frame.setTitle("Painter");
    }
    public void SetMenubarListener(ActionListener listener) {
        _menubarListener = listener;
    }
    public void SetShapeButtonListener(ActionListener listener) {
        _shapeButtonListener = listener;
    }
    public void SetActionButtonListener(ActionListener listener) {
        _actionButtonListener = listener;
    }
    public void Inititate() {
        // Layout 생성
        // FlowLayout layout = new FlowLayout(FlowLayout.LEADING);
        BorderLayout layout = new BorderLayout();
        
        // Panel 생성
        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(layout);
        _paintablePanel = new PaintablePanel();
        JPanel shapePanel = createShapePanel();
        JPanel actionPanel = createActionPanel();
        
        rootPanel.add(actionPanel, "North");
        rootPanel.add(shapePanel, "South");
        rootPanel.add(_paintablePanel, "Center");
        
        // Menu 생성
        _menubar = createJMenuBar();
        
        // ContentPane 설정
        _frame.setJMenuBar(_menubar);
        _frame.setContentPane(rootPanel);
        _frame.setDefaultCloseOperation(_frame.EXIT_ON_CLOSE);
    }
    private JPanel createActionPanel() {
        JPanel panel = new JPanel();
        
        //JButton colorButton = new JButton("Pick Color");
        JButton deleteButton = new JButton("Delete");
        JButton resizeButton = new JButton("Resize");
        JButton rotateButton = new JButton("Rotate");
        JButton fillInsideButton = new JButton("Change background color");
        JButton fillBorderButton = new JButton("Change border color");
        
        //colorButton.addActionListener(_actionButtonListener);
        deleteButton.addActionListener(_actionButtonListener);
        resizeButton.addActionListener(_actionButtonListener);
        rotateButton.addActionListener(_actionButtonListener);
        fillInsideButton.addActionListener(_actionButtonListener);
        fillBorderButton.addActionListener(_actionButtonListener);
        
       // panel.add(colorButton);
        panel.add(deleteButton);
        panel.add(resizeButton);
        panel.add(rotateButton);
        panel.add(fillInsideButton);
        panel.add(fillBorderButton);
        return panel;
    }
    private JMenuBar createJMenuBar() {
        JMenuBar menubar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(new JMenuItem("Save"));
        fileMenu.add(new JMenuItem("Save as"));
        fileMenu.add(new JMenuItem("Load"));
        fileMenu.addSeparator();
        fileMenu.add(new JMenuItem("Export"));
        fileMenu.addSeparator();
        fileMenu.add(new JMenuItem("Close"));
        JMenu helpMenu = new JMenu("Help");
        helpMenu.add(new JMenuItem("About"));
        
        menubar.add(fileMenu);
        menubar.add(helpMenu);
        
        for (int i = 0; i < menubar.getMenuCount(); i++) {
            JMenu menuNow = menubar.getMenu(i);
            for (int j = 0; j < menuNow.getItemCount(); j++) {
                JMenuItem item = menuNow.getItem(j);
                if (item != null)
                    item.addActionListener(_menubarListener);
            }
        }
        
        return menubar;
    }
    private JPanel createShapePanel() {
        JPanel panel = new JPanel();
        
        JButton circleButton = new JButton("Circle");
        JButton diamondButton = new JButton("Diamond");
        JButton lineButton = new JButton("Line");
        JButton squareButton = new JButton("Square");
        JButton triangleButton = new JButton("Triangle");
        
        circleButton.addActionListener(_shapeButtonListener);
        diamondButton.addActionListener(_shapeButtonListener);
        lineButton.addActionListener(_shapeButtonListener);
        squareButton.addActionListener(_shapeButtonListener);
        triangleButton.addActionListener(_shapeButtonListener);
        
        panel.add(circleButton);
        panel.add(diamondButton);
        panel.add(lineButton);
        panel.add(squareButton);
        panel.add(triangleButton);
        
        return panel;
    }
    
    public void Show() {
        _frame.setVisible(true);
    }
    
    public void setShapes(ArrayList<Shape> shapes) {
        _paintablePanel.setShapes(shapes);
    }
    
    public void setSelectedShape(Shape shape) {
        _paintablePanel.setSeletcedShape(shape);
    }
    
    public void addMouseListenerOfPaintArea(MouseListener listener) {
        _paintablePanel.addMouseListener(listener);
    }
    
    public void addMouseMotionListenerOfPaintArea(MouseMotionListener listener) {
        _paintablePanel.addMouseMotionListener(listener);
    }
    
    public void setCursor(Cursor cursor, Action action) {
        if (cursor == null)
            cursor = Cursor.getDefaultCursor();
        _paintablePanel.setCursor(cursor);
        if (action != null)
            action.setCursorListener(this._paintablePanel);
    }
}