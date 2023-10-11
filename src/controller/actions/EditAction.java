package controller.actions;

import model.myNode.RuNode;
import model.myNode.myNodeModels.RuPresentation;
import observer.ErrorFactory;
import view.MainFrame;
import view.dialogs.EditDialog;
import view.tree.model.MyTreeNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class EditAction extends AbstractRudokAction {

    public EditAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("icons\\edit.png"));
        putValue(NAME, "Edit");
        putValue(SHORT_DESCRIPTION, "Edit presentation");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MyTreeNode selektovani = (MyTreeNode) MainFrame.getInstance().getTree().getLastSelectedPathComponent();
        if (selektovani == null) {
            ErrorFactory.getInstance().generateError("OBAVESTENJE O EDITOVANJU", "Niste odabrali prezentaciju.", "Odaberite cvor koji je prezentacija.", 1);
            return;
        } else {
            RuNode selectedNode = selektovani.getMyNode();
            if (selectedNode instanceof RuPresentation) {
                EditDialog editDialog = new EditDialog();
            }
            else {
                ErrorFactory.getInstance().generateError("OBAVESTENJE O EDITOVANJU", "Niste odabrali prezentaciju.", "Odaberite cvor koji je prezentacija.", 1);
                return;
            }
        }
    }
}
