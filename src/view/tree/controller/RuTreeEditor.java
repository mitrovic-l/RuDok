package view.tree.controller;

import model.commands.NewNodeCommand;
import model.commands.RenameNodeCommand;
import model.myNode.EditState;
import model.myNode.RuNode;
import model.myNode.myNodeModels.RuPresentation;
import model.myNode.myNodeModels.RuProject;
import model.myNode.myNodeModels.RuSlide;
import model.myNode.myNodeModels.RuWorkspace;
import observer.ErrorFactory;
import view.MainFrame;
import view.panels.SlideShowPanel;
import view.rightPanels.PresentationView;
import view.rightPanels.ProjectView;
import view.tree.model.MyTreeNode;
import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class RuTreeEditor extends DefaultTreeCellEditor implements ActionListener {

    private Object obj = null;
    private JTextField edit = null;

    public RuTreeEditor(JTree tree, DefaultTreeCellRenderer renderer) {
        super(tree, renderer);
    }

    @Override
    public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row) {
        super.getTreeCellEditorComponent(tree, value, isSelected, expanded, leaf, row);
        obj = value;
        edit = new JTextField(value.toString());
        edit.addActionListener(this);
        return edit;
    }

    @Override
    public boolean isCellEditable(EventObject event) {
        if (event instanceof MouseEvent) {
            if (((MouseEvent) event).getClickCount() == 3) {
                return true;
            }
            return false;
        }
        return false;
    }

    //Promena imena je moguca TEK KADA SE PRITISNE ENTER!
    @Override
    public void actionPerformed(ActionEvent e) {
        MyTreeNode mtn = (MyTreeNode) obj;
        RuNode mn = (RuNode) mtn.getMyNode();
        if (mn instanceof RuProject){
            if (e.getActionCommand().trim().equals("")){
                ErrorFactory.getInstance().generateError("GRESKA PRI PROMENI NAZIVA PROJEKTA", "Niste uneli naziv projekta.", "Unesite naziv", 1);
                return;
            }
           // ((RuProject) mn).setName(e.getActionCommand().trim());
            MainFrame.getInstance().getCommandManager().addCommand(new RenameNodeCommand(mtn, mn, mn.getName(), e.getActionCommand()));
            this.cancelCellEditing();
        } else if (mn instanceof RuPresentation){
            ProjectView projectView = (ProjectView) MainFrame.getInstance().getSplitPane().getRightComponent();
            JTabbedPane jtb = null;
            for (Component c : projectView.getComponents()){
                if (c instanceof JTabbedPane){
                    jtb = (JTabbedPane) c;
                }
            }
            if (jtb.getSelectedComponent() instanceof PresentationView && mn.equals(((PresentationView) jtb.getSelectedComponent()).getPresentation()) ) {
                if (e.getActionCommand().trim().equals("")) {
                    ErrorFactory.getInstance().generateError("GRESKA PRI PROMENI NAZIVA PREZENTACIJE", "Niste uneli naziv prezentacije.", "Unesite naziv", 1);
                    return;
                }
            }else {
                this.cancelCellEditing();
                ErrorFactory.getInstance().generateError("Greska pri promeni naziva prezentacije", "Nije moguce promeniti naziv prezentacije.", "Morate otvoriti prezentaiju, njen state mora biti EditState.", 0);
                return;
            }

            //((RuPresentation) mn).setName(e.getActionCommand().trim());
            MainFrame.getInstance().getCommandManager().addCommand(new RenameNodeCommand(mtn, mn, mn.getName(), e.getActionCommand()));
            this.cancelCellEditing();
        } else if (mn instanceof RuWorkspace){
            ErrorFactory.getInstance().generateError("GRESKA PRI PROMENI NAZIVA WORKSPACE", "Nije moguce promeniti naziv za workspace.","Moguce je promeniti naziv projektu i prezentaciji.", 0);
            this.cancelCellEditing();
            return;
        } else if (mn instanceof RuSlide){
            ErrorFactory.getInstance().generateError("GRESKA PRI PROMENI NAZIVA SLAJDA", "Nije moguce promeniti naziv slajda.", "Moguce je promeniti naziv projektu i prezentaciji.", 0);
            this.cancelCellEditing();
            return;
        }
    }
}
