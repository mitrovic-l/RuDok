package controller.actions;

import model.myNode.ViewState;
import model.myNode.myNodeModels.RuPresentation;
import view.MainFrame;
import view.rightPanels.PresentationView;
import view.rightPanels.ProjectView;
import view.rightPanels.SlideView;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SlideShowAction extends AbstractRudokAction {
    public SlideShowAction() {
        putValue(NAME, "SlideShow");
        putValue(SHORT_DESCRIPTION, "SlideShow");
        putValue(SMALL_ICON, loadIcon("icons\\slideshow.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (MainFrame.getInstance().getSplitPane().getRightComponent() instanceof ProjectView) {
            ProjectView projectView = (ProjectView) MainFrame.getInstance().getSplitPane().getRightComponent();
            JTabbedPane jTabbedPane = projectView.getTabbedPane();
            if (jTabbedPane.getTabCount() > 0) {
                PresentationView presentationView = (PresentationView) jTabbedPane.getSelectedComponent();
               // RuPresentation presentation = presentationView.getPresentation();
              //  presentation.setPresentationState(new ViewState());
                presentationView.setViewState();
            }
        }


    }


}
