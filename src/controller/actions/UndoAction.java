package controller.actions;

import view.MainFrame;

import java.awt.event.ActionEvent;

public class UndoAction extends AbstractRudokAction{
    public UndoAction() {
        putValue(NAME, "Undo");
        putValue(SHORT_DESCRIPTION, "Undo");
        putValue(SMALL_ICON, loadIcon("icons/Undo.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getCommandManager().undoCommand();
    }
}
