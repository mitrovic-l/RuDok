package model.elementStates;

import view.rightPanels.SlideView;

import java.awt.event.MouseEvent;

public interface ElementState{
    void mousePressed(SlideView slideView, MouseEvent e);
    void mouseReleased(SlideView slideView, MouseEvent e);
    void mouseDragged (SlideView slideView, MouseEvent e);
}
