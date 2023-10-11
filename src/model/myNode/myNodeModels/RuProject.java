package model.myNode.myNodeModels;

import model.myNode.RuNode;
import model.myNode.RuNodeComposite;
import view.MainFrame;

import javax.swing.*;
import java.io.File;
import java.io.Serializable;


public class RuProject extends RuNodeComposite implements Serializable {
    private File projectFile;
    private transient boolean changed;
    public RuProject(String name, RuNode parent) {

        super(name, parent);
        this.projectFile = null;
        this.changed = false;
    }

    public File getProjectFile() {
        return projectFile;
    }

    public void setProjectFile(File projectFile) {
        this.projectFile = projectFile;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        if (this.changed!=changed){
            this.changed=changed;
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());
        }
    }
}
