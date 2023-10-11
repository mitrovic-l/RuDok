package controller.actions;

import model.elementStates.RectangleState;
import model.myNode.myNodeModels.RuPresentation;
import view.MainFrame;
import view.rightPanels.PresentationView;
import view.rightPanels.ProjectView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddSlotAction extends AbstractRudokAction{

    public AddSlotAction(){
        putValue(NAME, "Add slot");
        putValue(SHORT_DESCRIPTION, "Add slot");
        putValue(SMALL_ICON, loadIcon("icons\\addslot.png"));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ProjectView projectView = (ProjectView) MainFrame.getInstance().getSplitPane().getRightComponent();
        JTabbedPane tabbedPane = projectView.getTabbedPane();
        PresentationView presentationView = (PresentationView) tabbedPane.getSelectedComponent();
        RuPresentation ruPresentation = presentationView.getPresentation();
        ruPresentation.setElementState(new RectangleState());
    }
}
