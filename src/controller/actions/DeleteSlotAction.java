package controller.actions;

import model.elementStates.DeleteState;
import model.myNode.myNodeModels.RuPresentation;
import view.MainFrame;
import view.rightPanels.PresentationView;
import view.rightPanels.ProjectView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class DeleteSlotAction extends AbstractRudokAction{

    public DeleteSlotAction(){
        putValue(NAME, "Delete slot");
        putValue(SHORT_DESCRIPTION, "Delete slot");
        putValue(SMALL_ICON, loadIcon("icons\\deleteslot.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ProjectView projectView = (ProjectView) MainFrame.getInstance().getSplitPane().getRightComponent();
        JTabbedPane tabbedPane = projectView.getTabbedPane();
        PresentationView presentationView = (PresentationView) tabbedPane.getSelectedComponent();
        RuPresentation ruPresentation = presentationView.getPresentation();
        ruPresentation.setElementState(new DeleteState());
    }
}
