package controller.actions;

import model.commands.NewNodeCommand;
import model.factory.AbstractNodeFactory;
import model.factory.FactoryGenerator;
import model.myNode.RuNode;
import model.myNode.RuNodeComposite;
import model.myNode.ViewState;
import model.myNode.myNodeModels.RuPresentation;
import model.myNode.myNodeModels.RuProject;
import model.myNode.myNodeModels.RuSlide;
import model.myNode.myNodeModels.RuWorkspace;
import observer.ErrorFactory;
import view.MainFrame;
import view.dialogs.newNode.AddNewPresentationDialog;
import view.tree.model.MyTreeNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewAction extends AbstractRudokAction{

    public NewAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("icons\\new.png"));
        putValue(NAME, "New");
        putValue(SHORT_DESCRIPTION, "New");

    }

    @Override
    public void actionPerformed(ActionEvent e) {
       MyTreeNode  selektovani =(MyTreeNode) MainFrame.getInstance().getTree().getLastSelectedPathComponent();

       //Prethodni nacin pravljenja cvora stabla: ---->
       /*  if ( selektovani==null ){
            ErrorFactory.getInstance().generateError("GRESKA PRI DODAVANJU", "Niste izabrali cvor na koji dodajete novi.", "Izaberite cvor.", 0);
            return;

        }
        RuNode selectedNode =  selektovani.getMyNode();
        if (selectedNode instanceof RuSlide){
            ErrorFactory.getInstance().generateError("GRESKA PRI DODAVANJU", "Nije moguce dodavati na slajd.", "Izaberite cvor koji nije slajd.", 0);
            return;
        }
        if (selectedNode instanceof RuProject){
            AddNewPresentationDialog p = new AddNewPresentationDialog();
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());

        }
        else if (selectedNode instanceof RuPresentation){
            if (((RuPresentation) selectedNode).getPresentationState() instanceof ViewState){
                ErrorFactory.getInstance().generateError("Greska pri dodavanju", "Prezentacija je u slideShow rezimu.", "Promenite rezim na edit i pokusajte ponovo", 0);
                return;
            }
            RuSlide mySlide = new RuSlide("Slide "+(((RuPresentation)selectedNode).getChildren().size()+1), (RuNodeComposite) selectedNode);
            ((RuPresentation)selectedNode).addChild(mySlide);
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());
        } else if (selectedNode instanceof RuWorkspace){

            RuProject p = new RuProject("Projekat "+(((RuWorkspace)selectedNode).getChildren().size()+1), (RuWorkspace) selectedNode);
            ((RuWorkspace)selectedNode).addChild(p);
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());
        }*/

        //Novi nacin pravljenja cvora stabla, koristeci AbstractNodeFactory...
        if (selektovani == null || selektovani.getMyNode() instanceof RuSlide){
            ErrorFactory.getInstance().generateError("GRESKA PRI DODAVANJU", "Dodavanje nije moguce.", "Izaberite cvor koji nije slajd ili null.", 0);
            return;
        }
        /*
        FactoryGenerator fg = new FactoryGenerator(selektovani.getMyNode());
        System.out.println(fg.returnNodeFactory(selektovani.getMyNode()).getClass().getSimpleName());
        AbstractNodeFactory f = fg.returnNodeFactory(selektovani.getMyNode());
        MyTreeNode dete = new MyTreeNode(f.createRuNode(selektovani.getMyNode()));
        // ((RuNodeComposite)f.getNodeFromTree(selektovani)).addChild(dete.getMyNode());
        ((RuNodeComposite) selektovani.getMyNode()).addChild(dete.getMyNode());
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());*/
        MainFrame.getInstance().getCommandManager().addCommand(new NewNodeCommand(selektovani, selektovani.getMyNode()));
        System.out.println("Broj projekata na workspace-u: "+MainFrame.getInstance().getTree().getModel().getChildCount(MainFrame.getInstance().getTree().getModel().getRoot()));


    }

}
