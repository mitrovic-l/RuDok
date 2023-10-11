package model.commands;

import model.myNode.RuNode;
import view.MainFrame;
import view.tree.model.MyTreeNode;

import javax.swing.*;

public class RenameNodeCommand extends AbstractCommand{
    private MyTreeNode myTreeNode;
    private RuNode ruNode;
    private String staroIme;
    private String novoIme;

    public RenameNodeCommand(MyTreeNode myTreeNode, RuNode ruNode, String staroIme, String novoIme) {
        this.myTreeNode = myTreeNode;
        this.ruNode = ruNode;
        this.staroIme = staroIme;
        this.novoIme = novoIme;
    }

    @Override
    public void doCommand() {
        ruNode.setName(novoIme);
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());
    }

    @Override
    public void undoCommand() {
        ruNode.setName(staroIme);
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());

    }
}
