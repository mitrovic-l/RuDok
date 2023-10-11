package model.commands;

import model.elements.Slot;
import model.factory.AbstractNodeFactory;
import model.factory.FactoryGenerator;
import model.myNode.RuNode;
import model.myNode.RuNodeComposite;
import model.myNode.myNodeModels.RuPresentation;
import model.myNode.myNodeModels.RuProject;
import model.myNode.myNodeModels.RuSlide;
import view.MainFrame;
import view.tree.model.MyTreeNode;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class NewNodeCommand extends AbstractCommand{

    private MyTreeNode selektovani;
    private RuNode ruNode;
    private MyTreeNode dete;
    private ArrayList<Slot>slots = new ArrayList<>();

    public NewNodeCommand(MyTreeNode selected, RuNode ruNode) {
        this.selektovani = selected;
        this.ruNode = ruNode;
    }

    @Override
    public void doCommand() {
        if (dete == null)
        { FactoryGenerator fg = new FactoryGenerator(selektovani.getMyNode());
        AbstractNodeFactory f = fg.returnNodeFactory(selektovani.getMyNode());
         dete = new MyTreeNode(f.createRuNode(selektovani.getMyNode()));
        // ((RuNodeComposite)f.getNodeFromTree(selektovani)).addChild(dete.getMyNode());
        if (dete.getMyNode() instanceof RuSlide){
            ((RuSlide)dete.getMyNode()).getSlots().addAll(this.slots);
            System.out.println("Vracam slotove na slajd");
        }
        ((RuNodeComposite) selektovani.getMyNode()).addChild(dete.getMyNode());
        dete.getMyNode().setParent(selektovani.getMyNode());
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());
        MainFrame.getInstance().getTree().expandPath(MainFrame.getInstance().getTree().getSelectionPath());}
        else{
            ((RuNodeComposite) selektovani.getMyNode()).addChild(dete.getMyNode());
            dete.getMyNode().setParent(selektovani.getMyNode());
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());
            MainFrame.getInstance().getTree().expandPath(MainFrame.getInstance().getTree().getSelectionPath());}

    }

    @Override
    public void undoCommand() {
        ((RuNodeComposite)ruNode).removeChild(dete.getMyNode());
        if (dete.getMyNode() instanceof RuSlide){
            for (Slot s : ((RuSlide) dete.getMyNode()).getSlots()){
                System.out.println("Punim slotove");
                slots.add(s);
            }
        }
        if (dete.getMyNode() instanceof RuPresentation){
            MainFrame.getInstance().setJMenuBar(MainFrame.getInstance().getMyMenuBar());
            MainFrame.getInstance().add(MainFrame.getInstance().getToolBar(), BorderLayout.NORTH);
            MainFrame.getInstance().revalidate();
            MainFrame.getInstance().repaint();
        }
      //  dete.getMyNode().setParent(null);
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());

    }
}
