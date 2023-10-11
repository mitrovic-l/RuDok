package controller.actions;

import model.myNode.myNodeModels.RuProject;
import view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.*;

public class SaveProjectAction extends AbstractRudokAction{

    public SaveProjectAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("icons\\Save.png"));
        putValue(NAME, "Save project");
        putValue(SHORT_DESCRIPTION, "Save project");
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new ProjectFileFilter());

        RuProject project = MainFrame.getInstance().getTree().getCurrentProject();
        if (project == null){
            return;
        }
        File projectFile = project.getProjectFile();

        if (project.isChanged()){
            System.out.println("Not changed");
            return;
        }
        if (project.getProjectFile()==null){
            System.out.println("File is null");
            if (jFileChooser.showSaveDialog(MainFrame.getInstance())==JFileChooser.APPROVE_OPTION){
                System.out.println("Opening jFileChooser");
                projectFile = jFileChooser.getSelectedFile();
            } else {
                //System.out.println("ERROR");
                return;
            }
        } else {
            System.out.println("Ne ulazi u if");
        }
        ObjectOutputStream os;
        try {
            os = new ObjectOutputStream(new FileOutputStream(projectFile));
            os.writeObject(project);
            project.setProjectFile(projectFile);
            project.setChanged(false);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
