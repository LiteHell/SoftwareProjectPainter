/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package painter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import painter.Action.Action;
import painter.Action.ChangeBackgroundColorAction;
import painter.Action.ChangeBorderColorAction;
import painter.Action.DisposableAction;
import painter.Action.DraggableAction;
import painter.Action.MoveAction;
import painter.Action.ResizeAction;
import painter.Action.RotateAction;
import painter.Shape.Circle;
import painter.Shape.Diamond;
import painter.Shape.Line;
import painter.Shape.Square;
import painter.Shape.Triangle;

/**
 *
 * @author liteh
 */
public class MouseEventProcessing implements ActionListener, MouseListener, MouseMotionListener {
    private final PainterController controller;
    private String _filename;
    private Boolean firstDrag = false;
    
    public MouseEventProcessing(PainterController controller) {
        this.controller = controller;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch(actionCommand) {
            case "Circle":
                controller.addShape(new Circle());
                break;
            case "Diamond":
                controller.addShape(new Diamond());
                break;
            case "Line":
                controller.addShape(new Line());
                break;
            case "Square":
                controller.addShape(new Square());
                break;
            case "Triangle":
                controller.addShape(new Triangle());
                break;
            case "Delete":
                if (controller.getSelectedShape() == null) {
                    JOptionPane.showMessageDialog(null, "Select a shape first by clikcing it");
                } else {
                    controller.removeShape(controller.getSelectedShape());
                    controller.selectShape(null);
                }
                break;
            case "Resize":
                if (controller.getSelectedShape() == null) {
                    JOptionPane.showMessageDialog(null, "Select a shape first by clicking it");
                } else {
                    controller.setAction(new ResizeAction(controller.getSelectedShape()));
                }
                break;
            case "Rotate":
                if (controller.getSelectedShape() == null) {
                    JOptionPane.showMessageDialog(null, "Select a shape first by clicking it");
                } else {
                    controller.setAction(new RotateAction(controller.getSelectedShape()));
                }
                break;
            case "Change background color":
                if (controller.getSelectedShape() != null) {
                    controller.setAction(new ChangeBackgroundColorAction(controller.getSelectedShape()));
                }
                break;
            case "Change border color":
                if (controller.getSelectedShape() != null) {
                    controller.setAction(new ChangeBorderColorAction(controller.getSelectedShape()));
                }
                break;
        }
        
        if (actionCommand != "Save" && actionCommand != "Save as" && actionCommand != "Load" && actionCommand != "Export" && actionCommand != "Close" && actionCommand != "About")
            return;
        
        int result;
            String path = null;
            FileNameExtensionFilter caupaintFilter = new FileNameExtensionFilter("Paint file", "caupaint");
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.addChoosableFileFilter(caupaintFilter);
            fileChooser.setMultiSelectionEnabled(false);
            
        switch(e.getActionCommand()) {
            case "Save":
                path = _filename;
            case "Save as":
                if (path == null) {
                    result = fileChooser.showSaveDialog(null);
                    if (result == fileChooser.APPROVE_OPTION) {
                        path = fileChooser.getSelectedFile().getPath();
                        if (!path.endsWith(".caupaint"))
                            path = path + ".caupaint";
                    }
                }
                if (path != null) {
                    try {
                        controller.getCanvas().writeFile(path);
                        _filename = path;
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "파일 저장 실패");
                    }
                }
                break;
            case "Load":
                result = fileChooser.showOpenDialog(null);
                if (result == fileChooser.APPROVE_OPTION) {
                    try {
                        Canvas newCanvas = Canvas.readFile(fileChooser.getSelectedFile().getPath());
                        if (newCanvas == null)
                            JOptionPane.showMessageDialog(null, "파일 열기 실패");
                        else {
                            //_canvas.width = newCanvas.width;
                            //_canvas.height = newCanvas.height;
                            controller.setCanvas(newCanvas);
                            _filename = fileChooser.getSelectedFile().getPath();
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "파일 열기 실패");
                    }
                }
                break;
            case "Export":
                fileChooser.removeChoosableFileFilter(caupaintFilter);
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JPEG", "jpg", "jpeg"));
                fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PNG", "png"));
                result = fileChooser.showSaveDialog(null);
                if (result == fileChooser.APPROVE_OPTION) {
                    path = fileChooser.getSelectedFile().getPath();
                    String ext = path.substring(path.lastIndexOf(".") + 1);
                    if (ext != "jpg" || ext != "jpeg" || ext != "png") {
                        path = path + ".png";
                        ext = "png";
                    }
                    try {
                        controller.getCanvas().exportFile(ext, path);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null, "내보내기 실패");
                    }
                }
                break;
            case "Close":
                System.exit(0);
                break;
            case "About":
                JOptionPane.showMessageDialog(null, "Painter\nCopyright (c) 2020 Yeonjin Shin\n\nDeveloped for assignment of \"SOFTWARE PROJECT\" class, held on Chung-Ang University\n\nIcons\nRotate Icon : By Freepik from www.flaticons.com\nReszie Icon : By Smartline from www.flaticons.com");
                break;
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() != e.BUTTON1)
            return;

        if (controller.getSelectedShape() == null) {
            controller.selectShape(controller.getShapeByPoint(e.getPoint()));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Action action = controller.getAction();
        if (action == null) {
            controller.selectShape(null);
        } else {
            controller.setAction(null);
        }
        firstDrag = true;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Action action = controller.getAction();
        if (action instanceof DraggableAction) {
            ((DraggableAction)action).handleMouseDragged(e);
        } else if (controller.getSelectedShape() != null) {
            controller.setAction(new MoveAction(controller.getSelectedShape()));
        } else if (firstDrag) {
            controller.selectShape(controller.getShapeByPoint(e.getPoint()));
        }
        if (firstDrag) {
            firstDrag = false;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        return;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        return;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        return;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }
}
