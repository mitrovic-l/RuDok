package view.bars;

import view.MainFrame;

import javax.swing.*;
import java.awt.*;

public class PaletteBar extends JToolBar {
    public PaletteBar(){
        super(VERTICAL);
        setFloatable(false);
        add(MainFrame.getInstance().getActionManager().getAddSlotAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getDeleteSlotAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getSelectSlotAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getSlotColorAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getSlotStrokeAction());
        addSeparator();
    }
}
