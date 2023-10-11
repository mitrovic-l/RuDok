package controller.actions;

import model.myNode.myNodeModels.RuProject;
import view.MainFrame;
import view.rightPanels.ProjectView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.*;

public class OpenProjectAction extends AbstractRudokAction{

    public OpenProjectAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("icons\\Open.png"));
        putValue(NAME, "Open project");
        putValue(SHORT_DESCRIPTION, "Open project");
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new ProjectFileFilter());
        if (jFileChooser.showOpenDialog(MainFrame.getInstance())==JFileChooser.APPROVE_OPTION){
            try {
                ObjectInputStream os = new ObjectInputStream(new FileInputStream(jFileChooser.getSelectedFile()));
                RuProject project = null;
                try {
                    project = (RuProject) os.readObject();
                } catch (ClassNotFoundException e1){
                    e1.printStackTrace();
                }
                MainFrame.getInstance().getTree().addProject(project);
             //   ProjectView p = new ProjectView(project);
               // MainFrame.getInstance().getSplitPane().setRightComponent(p);

            } catch (FileNotFoundException e1){
                e1.printStackTrace();
            } catch (IOException e2){
                e2.printStackTrace();
            }
        }
    }
}
