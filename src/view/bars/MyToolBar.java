package view.bars;


import view.MainFrame;
import javax.swing.*;
import java.awt.*;

public class MyToolBar extends JToolBar {
    public MyToolBar(){
        super(HORIZONTAL);
        setFloatable(false);

        add(MainFrame.getInstance().getActionManager().getNewAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getDeleteAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getEditAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getSharePresentationAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getInfoAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getUndoAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getRedoAction());
        addSeparator();
        add(Box.createHorizontalGlue());
        add(MainFrame.getInstance().getActionManager().getSlideShowAction());
        addSeparator();

    }
}
