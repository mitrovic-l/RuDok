package controller.actions;

import model.commands.RemoveNodeCommand;
import model.myNode.EditState;
import model.myNode.RuNode;
import model.myNode.RuNodeComposite;
import model.myNode.myNodeModels.RuPresentation;
import model.myNode.myNodeModels.RuProject;
import model.myNode.myNodeModels.RuWorkspace;
import observer.ErrorFactory;
import view.MainFrame;
import view.tree.model.MyTreeNode;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteAction extends AbstractRudokAction {

    public DeleteAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("icons\\Delete.png"));
        putValue(NAME, "Delete");
        putValue(SHORT_DESCRIPTION, "Delete");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MyTreeNode mtn = (MyTreeNode) MainFrame.getInstance().getTree().getLastSelectedPathComponent();
        if (mtn == null) {
            ErrorFactory.getInstance().generateError("GRESKA PRI BRISANJU", "Niste izabrali cvor koji brisete.", "Izaberite cvor.", 0);
            return;
        }
        RuNode ruNode = mtn.getMyNode();
        RuNodeComposite myParent = (RuNodeComposite) ruNode.getParent();
        if (ruNode instanceof RuWorkspace) {
            ErrorFactory.getInstance().generateError("GRESKA PRI BRISANJU", "Nije moguce obrisati workspace", "Izaberite drugi cvor za brisanje.", 0);
            return;
        }

       // myParent.removeChild(ruNode);
        MainFrame.getInstance().getCommandManager().addCommand(new RemoveNodeCommand(mtn, mtn.getMyNode()));
      /*  if (myParent instanceof RuProject){
            MainFrame.getInstance().setJMenuBar(MainFrame.getInstance().getMyMenuBar());
            MainFrame.getInstance().add(MainFrame.getInstance().getToolBar(), BorderLayout.NORTH);
            MainFrame.getInstance().revalidate();
            MainFrame.getInstance().repaint();
        }*/
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());
        MainFrame.getInstance().getTree().clearSelection();
        MainFrame.getInstance().reloadTree();


    }
}
