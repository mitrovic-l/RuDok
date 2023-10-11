package model.factory;

import model.myNode.RuNode;
import view.MainFrame;
import view.tree.model.MyTreeNode;
import view.tree.view.MyTree;

public abstract class AbstractNodeFactory {

    public RuNode getNodeFromTree(MyTreeNode myTreeNode){
        RuNode selected = myTreeNode.getMyNode();
        RuNode n = createRuNode(selected);
        n.setParent(selected);
        n.setName("Cvor");
        return n;
    }
    public abstract  RuNode createRuNode(RuNode nodeParent);
}
