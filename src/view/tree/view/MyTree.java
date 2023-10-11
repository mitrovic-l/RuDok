package view.tree.view;

import model.myNode.RuNode;
import model.myNode.myNodeModels.RuProject;
import view.MainFrame;
import view.rightPanels.ProjectView;
import view.tree.controller.RuTreeDoubleClickListener;
import view.tree.RuTreeCellRenderer;
import view.tree.controller.RuTreeEditor;
import view.tree.controller.MyTreeSelectionListener;
import view.tree.model.MyTreeModel;
import view.tree.model.MyTreeNode;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

public class MyTree extends JTree {
    public MyTree() {
        setCellEditor(new RuTreeEditor(this, new DefaultTreeCellRenderer()));
        setEditable(true);
        setCellRenderer(new RuTreeCellRenderer());
        addTreeSelectionListener(new MyTreeSelectionListener());
        addMouseListener(new RuTreeDoubleClickListener(this));

    }
    public RuProject getCurrentProject() {
        MyTreeNode myTreeNode = (MyTreeNode) MainFrame.getInstance().getTree().getLastSelectedPathComponent();
        RuNode ruNode = myTreeNode.getMyNode();
        if (ruNode instanceof RuProject){
            return (RuProject) ruNode;
        }
        return null;
    }

    public void addProject(RuProject project){
        ((MyTreeModel)getModel()).addProject(project);
        SwingUtilities.updateComponentTreeUI(this);
    }
}
