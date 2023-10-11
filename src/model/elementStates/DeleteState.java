package model.elementStates;

import view.MainFrame;
import view.rightPanels.ProjectView;
import view.rightPanels.SlideView;
import view.rightPanels.slotView.SlotView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.Serializable;

public class DeleteState implements ElementState, Serializable {


    @Override
    public void mousePressed(SlideView slideView, MouseEvent e) {

    }

    @Override
    public void mouseReleased(SlideView slideView, MouseEvent e) {
        SlotView s = null;
        for (SlotView slotView : slideView.getSlotViews()) {
            if (slotView.getSlot().getShape().contains(e.getPoint())) {
                s = slotView;
                break;
            }
        }
        if (s != null) {
            slideView.getSlotViews().remove(s);
            slideView.getSlide().getSlots().remove(s.getSlot());
            ProjectView pw = (ProjectView) slideView.getParent().getParent().getParent().getParent().getParent();
            for (Component c : pw.getTest().getPnl().getComponents()) {
                if (c instanceof SlideView) {
                    SlideView sl = (SlideView) c;
                    if (sl.getSlide().getName().equals(slideView.getSlide().getName())) {
                        sl.getSlotViews().remove(s);
                        sl.repaint();
                    }

                }
            }
        }
        slideView.repaint();
    }

    @Override
    public void mouseDragged(SlideView slideView, MouseEvent e) {

    }
}
