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
import view.rightPanels.SlideView;
import view.rightPanels.slotView.SlotView;
import view.tree.model.MyTreeNode;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RemoveNodeCommand extends AbstractCommand{

    private MyTreeNode selektovani;
    private RuNode ruNode;
    private RuNode parent;
    private ArrayList<Slot>slots = new ArrayList<>();
    private ArrayList<RuProject>sharedPresParents = new ArrayList<>();

    public RemoveNodeCommand(MyTreeNode selektovani, RuNode ruNode) {
        this.selektovani = selektovani;
        this.ruNode = ruNode;
        this.parent = ruNode.getParent();
        if (ruNode instanceof RuSlide){
            for (Slot s : ((RuSlide) ruNode).getSlots()){
                System.out.println("Punim slotove");
                slots.add(s);
            }
        }
        if (ruNode instanceof RuPresentation){
            for (int i =0;i<MainFrame.getInstance().getTree().getModel().getChildCount(MainFrame.getInstance().getTree().getModel().getRoot());i++){
                MyTreeNode p = (MyTreeNode) MainFrame.getInstance().getTree().getModel().getChild(MainFrame.getInstance().getTree().getModel().getRoot(), i);
                RuNodeComposite ruProject = (RuNodeComposite) p.getMyNode();
                if (ruProject.getChildren().contains(ruNode) && !(ruProject.equals(parent))){
                    this.sharedPresParents.add((RuProject) ruProject);
                    System.out.println(ruProject.getName());
                }
            }
        }
    }

    @Override
    public void doCommand() {


        if (ruNode instanceof RuProject){
            System.out.println("Obrisan projekat");
            MainFrame.getInstance().getSplitPane().setRightComponent(new JPanel());
            MainFrame.getInstance().setJMenuBar(MainFrame.getInstance().getMyMenuBar());
            MainFrame.getInstance().add(MainFrame.getInstance().getToolBar(), BorderLayout.NORTH);
            MainFrame.getInstance().revalidate();
            MainFrame.getInstance().repaint();
        } else if (ruNode instanceof RuPresentation){
            System.out.println("Asd");
            if (((RuNodeComposite) ruNode.getParent()).getChildren().size()==1){
                System.out.println("Qwerty");
                MainFrame.getInstance().setJMenuBar(MainFrame.getInstance().getMyMenuBar());
                MainFrame.getInstance().add(MainFrame.getInstance().getToolBar(), BorderLayout.NORTH);
                MainFrame.getInstance().revalidate();
                MainFrame.getInstance().repaint();
            }
            if (((RuPresentation) ruNode).isShared()){
                for (int i =0;i<MainFrame.getInstance().getTree().getModel().getChildCount(MainFrame.getInstance().getTree().getModel().getRoot());i++){
                    MyTreeNode m = (MyTreeNode) MainFrame.getInstance().getTree().getModel().getChild(MainFrame.getInstance().getTree().getModel().getRoot(), i);
                    RuNodeComposite rn = (RuNodeComposite) m.getMyNode();
                    for (int j = 0;j<rn.getChildren().size();j++){
                        RuNode n = rn.getChildAt(j);
                        if (n.getName().equals(ruNode.getName())){

                            rn.getChildren().remove(j);
                           // n.setParent(null);
                        }
                    }
                }
            }
        }
        ((RuNodeComposite)ruNode.getParent()).removeChild(ruNode);
       // ((RuNodeComposite)parent).removeChild(ruNode);
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());
    }

    @Override
    public void undoCommand() {
       // System.out.println(fg.returnNodeFactory(parent.getClass().getSimpleName());
       // MyTreeNode dete = new MyTreeNode(f.createRuNode(parent));
        // ((RuNodeComposite)f.getNodeFromTree(selektovani)).addChild(dete.getMyNode());
        if (ruNode instanceof RuSlide){
            ((RuSlide)ruNode).getSlots().addAll(this.slots);
            System.out.println("Vracam slotove na slajd");
        }
        if (ruNode instanceof RuPresentation){
            for (int i = 0;i<this.sharedPresParents.size();i++){
                sharedPresParents.get(i).getChildren().add(ruNode);
            }
        }
        ((RuNodeComposite) parent).addChild(ruNode);
        ruNode.setParent(parent);
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());
        System.out.println(((RuNodeComposite) parent).getChildren().size());

    }
}
