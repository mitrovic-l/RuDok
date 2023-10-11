package view.dialogs;

import com.sun.tools.javac.Main;
import model.myNode.RuNode;
import model.myNode.RuNodeComposite;
import model.myNode.myNodeModels.RuPresentation;
import model.myNode.myNodeModels.RuProject;
import view.MainFrame;
import view.tree.model.MyTreeNode;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShareDialog extends JDialog {
    RuPresentation presentation;
    private JPanel projectPanel;
    private JButton btnOk;
    private JButton btnCancel;
    private String selProject;

    public ShareDialog(RuPresentation presentation){
        this.presentation = presentation;
        this.setLayout(new BorderLayout());
        this.setTitle("Deljenje " +presentation.getName());
        projectPanel = new JPanel();
        for (int i = 0; i<MainFrame.getInstance().getTree().getModel().getChildCount(MainFrame.getInstance().getTree().getModel().getRoot());i++){
            MyTreeNode node = (MyTreeNode) MainFrame.getInstance().getTree().getModel().getChild(MainFrame.getInstance().getTree().getModel().getRoot(), i);
            RuNode ruNode = node.getMyNode();
            RuNode presentationParent = this.presentation.getParent();
            if (!(ruNode.equals(presentationParent))){
                JButton projectButton = new JButton(ruNode.getName());
                projectButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selProject = projectButton.getText();
                        System.out.println(selProject);
                       // System.out.println(projectButton.getText());
                        for (int k = 0; k<MainFrame.getInstance().getTree().getModel().getChildCount(MainFrame.getInstance().getTree().getModel().getRoot());k++){
                            MyTreeNode m = (MyTreeNode) MainFrame.getInstance().getTree().getModel().getChild(MainFrame.getInstance().getTree().getModel().getRoot(), k);
                            RuNode pr = m.getMyNode();
                            if (pr.getName().equals(selProject)){
                                ((RuNodeComposite)pr).addChild(presentation);
                                presentation.setShared(true);
                            }
                        }

                        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());
                    }
                });
                //this.add(projectButton, BorderLayout.CENTER);
                projectPanel.add(projectButton);
                System.out.println("Added project button to panel");
            }

        }
        this.add(projectPanel, BorderLayout.CENTER);
        btnCancel = new JButton("Done");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (presentation.isShared()){
                    presentation.setName("*" +presentation.getName());
                }
                dispose();
            }
        });
        this.add(btnCancel, BorderLayout.SOUTH);
        this.setLocationRelativeTo(MainFrame.getInstance().getSplitPane());
        this.pack();
        this.setVisible(true);


    }

    public RuPresentation getPresentation() {
        return presentation;
    }

    public void setPresentation(RuPresentation presentation) {
        this.presentation = presentation;
    }
}
