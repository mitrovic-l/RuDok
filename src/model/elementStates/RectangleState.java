package model.elementStates;

import controller.StrokeSerializationAdapter;
import model.elements.RectangleElement;
import model.myNode.myNodeModels.RuSlide;
import view.MainFrame;
import view.rightPanels.PresentationView;
import view.rightPanels.ProjectView;
import view.rightPanels.SlideView;
import view.rightPanels.slotView.SlotView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.Serializable;

public class RectangleState implements ElementState, Serializable {
    private Color color = new Color(0,222,111);
    private StrokeSerializationAdapter stroke = new StrokeSerializationAdapter(new BasicStroke(2f));


    @Override
    public void mousePressed(SlideView slideView, MouseEvent e) {
        if (slideView.getParent().getParent().getParent().getParent() instanceof ProjectView)
            return;
        //System.out.println("3" + slideView.getParent().getParent().getParent().getParent().getParent());
        ProjectView projectView = (ProjectView)slideView.getParent().getParent().getParent().getParent().getParent();
        PresentationView otvorena = (PresentationView)projectView.getTabbedPane().getSelectedComponent();
        Point position = e.getPoint();
        int width = 50;
        int height = 30;
       // Stroke stroke = new BasicStroke(2f);
        RuSlide slide = slideView.getSlide();
        RectangleElement rectangleElement = new RectangleElement(position, width, height, this.getColor(), this.getStroke());
        slide.getSlots().add(rectangleElement);
        SlotView slotView = new SlotView(rectangleElement);

        slideView.getSlotViews().add(slotView);
        ProjectView pw = (ProjectView) slideView.getParent().getParent().getParent().getParent().getParent();
        for (Component c : pw.getTest().getPnl().getComponents()) {
            if (c instanceof SlideView) {
                SlideView s = (SlideView) c;
                if (s.getSlide().getName().equals(slide.getName())) {
                    if (!s.getSlotViews().contains(slotView)) {
                        s.getSlotViews().add(slotView);
                        s.repaint();
                    }

                }

            }
        }
        slideView.repaint();
    }

    @Override
    public void mouseReleased(SlideView slideView, MouseEvent e) {

    }

    @Override
    public void mouseDragged(SlideView slideView, MouseEvent e) {

    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public StrokeSerializationAdapter getStroke() {
        return stroke;
    }

    public void setStroke(StrokeSerializationAdapter stroke) {
        this.stroke = stroke;
    }
}
