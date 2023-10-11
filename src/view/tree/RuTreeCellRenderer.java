package view.tree;

import model.myNode.RuNode;
import model.myNode.myNodeModels.RuPresentation;
import model.myNode.myNodeModels.RuProject;
import model.myNode.myNodeModels.RuSlide;
import model.myNode.myNodeModels.RuWorkspace;
import view.tree.model.MyTreeNode;
import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

public class RuTreeCellRenderer extends DefaultTreeCellRenderer {
    public RuTreeCellRenderer() {

    }


    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

       if(value instanceof MyTreeNode) {
            MyTreeNode mtn = (MyTreeNode)value;
            RuNode mn = mtn.getMyNode();

           if (mn instanceof RuProject) {
               setIcon(loadIcon("treeIcons/projekat.png"));
           } else if (mn instanceof RuPresentation) {
               setIcon(loadIcon("treeIcons/prezentacija.png"));
           } else if (mn instanceof RuSlide) {
               setIcon(loadIcon("treeIcons/slajd.png"));
           } else if (mn instanceof RuWorkspace) {
               setIcon(loadIcon("treeIcons/workspace.png"));
           }
       }
        return this;
    }

    public Icon loadIcon(String fileName){

        URL imageURL = getClass().getResource(fileName);
        Icon icon = null;

        if (imageURL != null){
            icon = new ImageIcon(imageURL);
        }
        else
        {
            System.err.println("Resource not found: "+fileName);
        }
        return icon;
    }
}
