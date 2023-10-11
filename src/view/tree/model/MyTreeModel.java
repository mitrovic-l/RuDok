package view.tree.model;


import model.myNode.myNodeModels.RuProject;
import model.myNode.myNodeModels.RuWorkspace;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

public class MyTreeModel extends DefaultTreeModel {

    public MyTreeModel(TreeNode root){
        super(root);

    }
    public void addProject(RuProject project){
        MyTreeNode myTreeNode = (MyTreeNode) getRoot();
        RuWorkspace w = (RuWorkspace) myTreeNode.getMyNode();
        w.addChild(project);
        project.setParent(w);
    }
}
