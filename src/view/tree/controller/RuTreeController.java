package view.tree.controller;

import model.myNode.RuNode;
import model.myNode.RuNodeComposite;
import view.MainFrame;
import view.tree.model.MyTreeNode;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

public class RuTreeController implements TreeSelectionListener {

    private MyTreeNode selectedNode;
    private RuNodeComposite sel;
    @Override
    public void valueChanged(TreeSelectionEvent e1) {
        try {
            this.selectedNode = (MyTreeNode) MainFrame.getInstance().getTree().getLastSelectedPathComponent();
            this.sel = (RuNodeComposite) selectedNode.getMyNode();
            if (selectedNode != null){
                System.out.println("Cvor: " +sel.getName());
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }

    public MyTreeNode getSelectedNode() {
        return selectedNode;
    }

    public void setSelectedNode(MyTreeNode selectedNode) {
        this.selectedNode = selectedNode;
    }

    public RuNode getSel() {
        return sel;
    }

    public void setSel(RuNodeComposite sel) {
        this.sel = sel;
    }
}
