package view.tree.model;

import model.myNode.RuNode;
import model.myNode.RuNodeComposite;
import javax.swing.tree.TreeNode;
import java.io.Serializable;
import java.util.Enumeration;

public class MyTreeNode implements TreeNode, Serializable {

    private RuNode ruNode;

    public MyTreeNode(RuNode ruNode) {
        this.ruNode = ruNode;
    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        if (ruNode instanceof RuNodeComposite){
            RuNode mn = ((RuNodeComposite) ruNode).getChildren().get(childIndex);
            return new MyTreeNode(mn);
        }
        return null;
    }

    @Override
    public int getChildCount() {
        if (ruNode instanceof RuNodeComposite){
            return ((RuNodeComposite) ruNode).getChildren().size();
        }
        return 0;
    }


    @Override
    public TreeNode getParent() {
        return (TreeNode) ruNode.getParent();
    }

    @Override
    public int getIndex(TreeNode node) {
        if (node instanceof RuNodeComposite){
            return ((RuNodeComposite)node).getChildren().indexOf(node);
        }
        else {
            return 0;
        }
    }

    @Override
    public boolean getAllowsChildren() {
        return ruNode instanceof RuNodeComposite;
    }

    @Override
    public boolean isLeaf() {
        return !(ruNode instanceof RuNodeComposite);
    }

    @Override
    public Enumeration<? extends TreeNode> children() {
        return null;
    }

    public RuNode getMyNode() {
        return ruNode;
    }

    public void setMyNode(RuNode ruNode) {
        this.ruNode = ruNode;
    }

    @Override
    public String toString() {
        return ruNode.getName();
    }


}
