package controller.actions;

import view.MainFrame;

import javax.print.attribute.standard.MediaSize;
import java.awt.event.ActionEvent;

public class RedoAction extends AbstractRudokAction{
    public RedoAction() {
        putValue(NAME, "Redo");
        putValue(SHORT_DESCRIPTION, "Redo");
        putValue(SMALL_ICON, loadIcon("icons/Redo.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        MainFrame.getInstance().getCommandManager().doCommand();
    }
}
