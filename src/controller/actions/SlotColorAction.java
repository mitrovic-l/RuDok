package controller.actions;

import model.elementStates.RectangleState;
import model.myNode.myNodeModels.RuPresentation;
import view.MainFrame;
import view.rightPanels.PresentationView;
import view.rightPanels.ProjectView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SlotColorAction extends AbstractRudokAction{

    public SlotColorAction() {
        putValue(NAME, "Color");
        putValue(SHORT_DESCRIPTION, "Color");
        putValue(SMALL_ICON, loadIcon("icons\\slotcolor.png"));


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ProjectView projectView = (ProjectView) MainFrame.getInstance().getSplitPane().getRightComponent();
        JTabbedPane tabbedPane = projectView.getTabbedPane();
        PresentationView presentationView = (PresentationView) tabbedPane.getSelectedComponent();
        RuPresentation ruPresentation = presentationView.getPresentation();
        Color color = JColorChooser.showDialog(MainFrame.getInstance().getSplitPane().getRightComponent(), "Choose a color", Color.PINK);
        if (ruPresentation.getElementState() instanceof RectangleState){
            ((RectangleState) ruPresentation.getElementState()).setColor(color);
        }


    }
}
