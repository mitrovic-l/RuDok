package view.tree.controller;

import model.myNode.myNodeModels.RuPresentation;
import model.myNode.myNodeModels.RuProject;
import model.myNode.myNodeModels.RuSlide;
import model.myNode.myNodeModels.RuWorkspace;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class MyTreeSelectionListener implements TreeSelectionListener {
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();
        for (int i =0;i< path.getPathCount();i++) {
            if (path.getPathComponent(i) instanceof RuWorkspace) {
                RuWorkspace w = (RuWorkspace) path.getPathComponent(i);
                System.out.println("Selektovan ws");
            }

            if (path.getPathComponent(i) instanceof RuProject) {
                RuProject p = (RuProject) path.getPathComponent(i);
            }
            if (path.getPathComponent(i) instanceof RuPresentation) {
                RuPresentation p = (RuPresentation) path.getPathComponent(i);
            }
            if (path.getPathComponent(i) instanceof RuSlide) {
                RuSlide p = (RuSlide) path.getPathComponent(i);
            }
        }
    }

}
