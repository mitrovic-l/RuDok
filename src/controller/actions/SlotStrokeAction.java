package controller.actions;

import view.MainFrame;
import view.dialogs.slotDialogs.SlotStrokeDialog;
import view.rightPanels.ProjectView;

import java.awt.*;
import java.awt.event.ActionEvent;

public class SlotStrokeAction extends AbstractRudokAction{
    public SlotStrokeAction() {
        putValue(NAME, "Stroke type");
        putValue(SHORT_DESCRIPTION, "Stroke type");
        putValue(SMALL_ICON, loadIcon("icons\\editstroke.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SlotStrokeDialog dialog = new SlotStrokeDialog((ProjectView) MainFrame.getInstance().getSplitPane().getRightComponent());
    }
}
