package controller.actions;

import model.elementStates.SelectState;
import model.myNode.myNodeModels.RuPresentation;
import view.MainFrame;
import view.rightPanels.PresentationView;
import view.rightPanels.ProjectView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SelectSlotAction extends AbstractRudokAction{

   public SelectSlotAction(){
       putValue(NAME, "Select slot");
       putValue(SHORT_DESCRIPTION, "Select slot");
       putValue(SMALL_ICON, loadIcon("icons\\selectslot.png"));

   }

    @Override
    public void actionPerformed(ActionEvent e) {
        ProjectView projectView = (ProjectView) MainFrame.getInstance().getSplitPane().getRightComponent();
        JTabbedPane tabbedPane = projectView.getTabbedPane();
        PresentationView presentationView = (PresentationView) tabbedPane.getSelectedComponent();
        RuPresentation ruPresentation = presentationView.getPresentation();
        ruPresentation.setElementState(new SelectState());
    }
}
