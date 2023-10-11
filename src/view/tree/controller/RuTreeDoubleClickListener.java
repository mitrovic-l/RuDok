package view.tree.controller;

import model.myNode.RuNode;
import model.myNode.myNodeModels.RuProject;
import observer.ErrorFactory;
import view.MainFrame;
import view.rightPanels.ProjectView;
import view.tree.model.MyTreeNode;
import view.tree.view.MyTree;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;


public class RuTreeDoubleClickListener extends MouseAdapter {
    private MyTree tree;
    private boolean singleClick = true;
    private int doubleClickDelay = 300;
    private Timer timer;

    public RuTreeDoubleClickListener(MyTree tree) {
        this.tree = tree;
        ActionListener actionListener = e -> {
            timer.stop();
            if (singleClick) {
                singleClickHandler(e);
            } else {
                try {
                    doubleClickHandler(e);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        };
        timer = new javax.swing.Timer(doubleClickDelay, actionListener);
        timer.setRepeats(false);
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 1) {
            singleClick = true;
            timer.start();
        } else {
            singleClick = false;
        }
    }

    private void singleClickHandler(ActionEvent e) {

    }

    private void doubleClickHandler(ActionEvent e) throws ParseException {
       MyTreeNode selektovani = (MyTreeNode) MainFrame.getInstance().getTree().getLastSelectedPathComponent();
       RuNode selected = (RuNode) selektovani.getMyNode();
        if (selected instanceof RuProject) {
            if (MainFrame.getInstance().getSplitPane().getRightComponent() instanceof ProjectView) {
                ProjectView stari = (ProjectView) MainFrame.getInstance().getSplitPane().getRightComponent();
                RuProject stariProject = (RuProject) stari.getProject();
            }
            if (((RuProject) selected).getChildren().size() > 0) {
                MainFrame.getInstance().getSplitPane().setRightComponent(new ProjectView((RuProject) selected));
            } else {
                ErrorFactory.getInstance().generateError("Prazan projekat", "Projekat koji ste pokusali da otvorite je prazan.", "Dodajte prezentacije u projekat.", 0);
            }
        }

    }
}
