package model.myNode;

import model.myNode.myNodeModels.RuPresentation;
import view.MainFrame;
import view.rightPanels.PresentationView;
import view.rightPanels.ProjectView;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.io.Serializable;

public class EditState implements PresentationState, Serializable {
    @Override
    public void changeState(RuPresentation presentation) {
        PresentationView presV = new PresentationView(presentation);
        JTabbedPane tabbedPane = ((ProjectView)MainFrame.getInstance().getSplitPane().getRightComponent()).getTabbedPane();
        int selectedIndex = tabbedPane.getSelectedIndex();
        tabbedPane.setComponentAt(selectedIndex, presV);
        ((ProjectView)MainFrame.getInstance().getSplitPane().getRightComponent()).add(((ProjectView)MainFrame.getInstance().getSplitPane().getRightComponent()).getPaletteBar(), BorderLayout.EAST);
        ((ProjectView)MainFrame.getInstance().getSplitPane().getRightComponent()).add(((ProjectView)MainFrame.getInstance().getSplitPane().getRightComponent()).getTest(), BorderLayout.WEST);
        ((ProjectView)MainFrame.getInstance().getSplitPane().getRightComponent()).changeNavigation(presV);
        MainFrame.getInstance().setJMenuBar(MainFrame.getInstance().getMyMenuBar());
        MainFrame.getInstance().add(MainFrame.getInstance().getToolBar(), BorderLayout.NORTH);
        MainFrame.getInstance().revalidate();
        MainFrame.getInstance().repaint();
       // System.out.println("State changed to: "+ presentation.getPresentationState());

    }
}
