package model.myNode;

import com.sun.tools.javac.Main;
import model.myNode.myNodeModels.RuPresentation;
import view.MainFrame;
import view.panels.SlideShowPanel;
import view.rightPanels.PresentationView;
import view.rightPanels.ProjectView;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class ViewState implements PresentationState, Serializable {
    @Override
    public void changeState(RuPresentation presentation) {


        SlideShowPanel sp = new SlideShowPanel(presentation);

        System.out.println("Menjam state");

        MainFrame.getInstance().remove(MainFrame.getInstance().getToolBar());
        MainFrame.getInstance().setJMenuBar(null);
        JTabbedPane tabbedPane = ((ProjectView) MainFrame.getInstance().getSplitPane().getRightComponent()).getTabbedPane();
        ((ProjectView) MainFrame.getInstance().getSplitPane().getRightComponent()).remove(((ProjectView) MainFrame.getInstance().getSplitPane().getRightComponent()).getPaletteBar());
        ((ProjectView) MainFrame.getInstance().getSplitPane().getRightComponent()).remove(((ProjectView) MainFrame.getInstance().getSplitPane().getRightComponent()).getTest());
        int selectedIndex = tabbedPane.getSelectedIndex();
        tabbedPane.setComponentAt(selectedIndex, sp);


        MainFrame.getInstance().getGlavni().revalidate();
        MainFrame.getInstance().getGlavni().repaint();
        sp.revalidate();
        sp.repaint();
        MainFrame.getInstance().revalidate();
        MainFrame.getInstance().repaint();
       // System.out.println(MainFrame.getInstance().getSplitPane().getRightComponent().getSize());
    }
}
