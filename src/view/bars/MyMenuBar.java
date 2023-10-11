package view.bars;



import view.MainFrame;

import javax.swing.*;

public class MyMenuBar extends JMenuBar{
    public MyMenuBar(){



        JMenu mFile = new JMenu("File");
        mFile.add(MainFrame.getInstance().getActionManager().getNewAction());
        mFile.addSeparator();
        mFile.add(MainFrame.getInstance().getActionManager().getDeleteAction());
        mFile.addSeparator();
        mFile.add(MainFrame.getInstance().getActionManager().getSaveProjectAction());
        mFile.addSeparator();
        mFile.add(MainFrame.getInstance().getActionManager().getOpenProjectAction());
        JMenu mHelp = new JMenu("Help");
        mHelp.add(MainFrame.getInstance().getActionManager().getEditAction());
        add(mFile);
        add(mHelp);
        JMenu mAbout = new JMenu("About");
        mAbout.add(MainFrame.getInstance().getActionManager().getInfoAction());
        mAbout.addSeparator();
        add(mAbout);




    }


}
