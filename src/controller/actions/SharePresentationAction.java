package controller.actions;

import model.myNode.RuNode;
import model.myNode.myNodeModels.RuPresentation;
import observer.ErrorFactory;
import view.MainFrame;
import view.dialogs.ShareDialog;
import view.tree.model.MyTreeNode;

import javax.sound.midi.Soundbank;
import java.awt.event.ActionEvent;

public class SharePresentationAction extends AbstractRudokAction{

    public SharePresentationAction() {
        putValue(NAME, "Share");
        putValue(SHORT_DESCRIPTION, "Share presentation");
        putValue(SMALL_ICON, loadIcon("icons\\share.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (MainFrame.getInstance().getTree().getLastSelectedPathComponent() instanceof MyTreeNode) {
            MyTreeNode m = (MyTreeNode)MainFrame.getInstance().getTree().getLastSelectedPathComponent();
            if (m.getMyNode() instanceof RuPresentation) {

                RuNode n = (RuNode) m.getMyNode();
                RuPresentation p = (RuPresentation) n;
                ShareDialog shareDialog = new ShareDialog(p);
            }else {
                ErrorFactory.getInstance().generateError("Greska pri deljenju", "Niste izabrali prezentaciju za deljenje.", "Izaberite prezentaciju koju zelite da podelite.", 1);
                return;
            }
        } else {
            ErrorFactory.getInstance().generateError("Greska pri deljenju", "Niste izabrali prezentaciju za deljenje.", "Izaberite prezentaciju koju zelite da podelite.", 1);
            return;
        }
    }
}
