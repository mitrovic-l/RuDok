package model.elementStates;

import view.rightPanels.ProjectView;
import view.rightPanels.SlideView;
import view.rightPanels.slotView.SlotView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
import java.io.Serializable;

public class SelectState implements ElementState, Serializable {

    @Override
    public void mousePressed(SlideView slideView, MouseEvent e) {
      /* slideView.getSelectedSlotsList().add(slideView.getSelectedSlot());
        for (SlotView sl : slideView.getSelectedSlotsList()){
            System.out.println(sl);
        }*/

    }

    @Override
    public void mouseReleased(SlideView slideView, MouseEvent e) {

    }

    @Override
    public void mouseDragged(SlideView slideView, MouseEvent e) {
      /*  if (slideView.getSelectedSlot() != null) {
            // System.out.println("Moved");
            Shape shape = new GeneralPath();
            ((GeneralPath) shape).moveTo(e.getPoint().getX(), e.getPoint().getY());

            ((GeneralPath) shape).lineTo(e.getPoint().getX() + slideView.getSelectedSlot().getSlot().getWidth(), e.getPoint().getY());

            ((GeneralPath) shape).lineTo(e.getPoint().getX() + slideView.getSelectedSlot().getSlot().getWidth(), e.getPoint().getY() + slideView.getSelectedSlot().getSlot().getHeight());

            ((GeneralPath) shape).lineTo(e.getPoint().getX(), e.getPoint().getY() + slideView.getSelectedSlot().getSlot().getHeight());

            ((GeneralPath) shape).closePath();
            slideView.getSelectedSlot().getSlot().setShape(shape);
            slideView.getSelectedSlot().getSlot().setPosition(e.getPoint());
            slideView.repaint();
            ProjectView pw = (ProjectView) slideView.getParent().getParent().getParent().getParent().getParent();
            for (Component c : pw.getTest().getPnl().getComponents()) {
                if (c instanceof SlideView) {
                    SlideView sl = (SlideView) c;
                    if (sl.getSlide().getName().equals(slideView.getSlide().getName())) {
                        sl.repaint();
                    }

                }
            }
        }*/
        for (SlotView slotView: slideView.getSelectedSlotsList()){
            this.metoda(slideView, e, slotView);
        }
    }
    public void metoda(SlideView slideView, MouseEvent e, SlotView slotView){
        if (slotView != null && slideView.getSelectedSlot() != null) {
            // System.out.println("Moved");
            Shape shape = new GeneralPath();
            int distance_x, distance_y;
            distance_x = e.getX() - slotView.getSlot().getPosition().x;
            distance_y  = e.getY() - slotView.getSlot().getPosition().y;
            ((GeneralPath) shape).moveTo(distance_x + e.getPoint().getX(), distance_y+ e.getPoint().getY());

            ((GeneralPath) shape).lineTo(distance_x +e.getPoint().getX() + slideView.getSelectedSlot().getSlot().getWidth(), distance_y+ e.getPoint().getY());

            ((GeneralPath) shape).lineTo(distance_x + e.getPoint().getX() + slideView.getSelectedSlot().getSlot().getWidth(), distance_y+e.getPoint().getY() + slideView.getSelectedSlot().getSlot().getHeight());

            ((GeneralPath) shape).lineTo(distance_x + e.getPoint().getX(), distance_y+e.getPoint().getY() + slideView.getSelectedSlot().getSlot().getHeight());

            ((GeneralPath) shape).closePath();
            //slideView.getSelectedSlot().getSlot().setShape(shape);
            slotView.getSlot().setShape(shape);
            //slideView.getSelectedSlot().getSlot().setPosition(e.getPoint());
            slotView.getSlot().setPosition(new Point(e.getPoint().x+distance_x, e.getPoint().y + distance_y));
            slideView.repaint();
            ProjectView pw = (ProjectView) slideView.getParent().getParent().getParent().getParent().getParent();
            for (Component c : pw.getTest().getPnl().getComponents()) {
                if (c instanceof SlideView) {
                    SlideView sl = (SlideView) c;
                    if (sl.getSlide().getName().equals(slideView.getSlide().getName())) {
                        sl.repaint();
                    }

                }
            }
        }
    }
}
