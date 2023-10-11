package view.rightPanels;

import model.myNode.EditState;
import model.myNode.PStateManager;
import model.myNode.RuNode;
import model.myNode.ViewState;
import model.myNode.myNodeModels.RuPresentation;
import model.myNode.myNodeModels.RuSlide;
import observer.MySubscriber;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PresentationView extends JScrollPane implements MySubscriber {

    private JLabel lblAutor;
    private RuPresentation presentation;
    private JPanel pnl;
    private JPanel pnlAutor;
    private List<RuNode> children;
    private String currentFile;
    private String presName;
    private PStateManager pStateManager;


    public PresentationView(RuPresentation presentation) {

        this.presentation = presentation;
        presentation.addSubscriber(this);
        this.currentFile = presentation.getFilePath();
        this.presName = presentation.getName();
        lblAutor = new JLabel(presentation.getAutor());
        this.pnlAutor = new JPanel();
        pnlAutor.add(lblAutor);
        pnl = new JPanel();
        pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
        pnl.add(pnlAutor);
        pnl.setVisible(true);
        children = new ArrayList<RuNode>();
        children.addAll(presentation.getChildren());
        pStateManager = new PStateManager();


        for (RuNode deca : children) {
            SlideView slideView = new SlideView((RuSlide) deca);
            pnl.add(slideView);
            slideView.adjustSize();
            pnl.add(Box.createRigidArea(new Dimension(0, 50)));
            pnl.revalidate();
            pnl.repaint();
        }
        this.setViewportView(pnl);
        this.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        this.setVisible(true);

    }

    public void reloadView(){
        if(this.getParent() instanceof ProjectView){
            for (Component deca : pnl.getComponents()) {
                deca.setMaximumSize(new Dimension(120, 80));
                deca.setSize(new Dimension(120, 80));
                deca.setMinimumSize(new Dimension(120, 80));
                deca.setPreferredSize(new Dimension(120, 80));
                deca.revalidate();
                deca.repaint();
                //System.out.println(deca.getSize());
            }
            this.revalidate();
            this.repaint();
        }

    }

    public PStateManager getpStateManager() {
        return pStateManager;
    }

    public void setpStateManager(PStateManager pStateManager) {
        this.pStateManager = pStateManager;
    }

    public void setEditState(){
        this.pStateManager.setEditState();
        funkcija();
    }
    public void setViewState(){
        this.pStateManager.setViewState();
        funkcija();
    }

    public void funkcija (){
        this.pStateManager.getCurrentState().changeState(getPresentation());
    }

    public String getCurrentFile() {
        return currentFile;
    }

    public void setCurrentFile(String currentFile) {
        this.currentFile = currentFile;
    }

    public JLabel getLblAutor() {
        return lblAutor;
    }

    public void setLblAutor(JLabel lblAutor) {
        this.lblAutor = lblAutor;
    }

    public RuPresentation getPresentation() {
        return presentation;
    }

    public void setPresentation(RuPresentation presentation) {
        //this.presentation = presentation;
    }

    public JPanel getPnl() {
        return pnl;
    }

    @Override
    public void update(Object notification) {

        System.out.println(this.presentation);
        RuPresentation myPresentation = (RuPresentation) notification;
        if (this.getpStateManager().getCurrentState() instanceof ViewState){
            return;
        }
        if (myPresentation.getParent() == null) {
            System.out.println("Brisanje prez");
            JTabbedPane pnlParent = (JTabbedPane) this.getParent();
            if (pnlParent == null){
                return;
            }
            pnlParent.remove(this);
            pnlParent.revalidate();
            pnlParent.repaint();
            return;
        }

        if (!(this.presName.equals(myPresentation.getName()))) {
            if (this.getParent() instanceof JTabbedPane)
            {
                JTabbedPane tabParent = (JTabbedPane) this.getParent();
            for (int i = 0; i < tabParent.getTabCount(); i++) {
                if (tabParent.getTitleAt(i).equals(this.presName)) {
                    tabParent.setTitleAt(i, myPresentation.getName());
                    tabParent.revalidate();
                    tabParent.repaint();
                }
            }
            //Izmenjen observer za promenu imena, sada radi
            this.presName = myPresentation.getName();
            return;
        }
        }
        if (!(this.currentFile.equals(myPresentation.getFilePath()))) {
            this.currentFile = myPresentation.getFilePath();
            for (int i = 0; i < pnl.getComponentCount(); i++) {
                if (pnl.getComponent(i) instanceof SlideView) {
                    ((SlideView) pnl.getComponent(i)).setSlika(this.currentFile);
                    ((SlideView) pnl.getComponent(i)).revalidate();
                    ((SlideView) pnl.getComponent(i)).repaint();
                }
            }
            pnl.revalidate();
            pnl.repaint();
            this.revalidate();
            this.repaint();
            return;
        }
        if (!(this.lblAutor.getText().equals(myPresentation.getAutor()))) {
            this.lblAutor.setText(myPresentation.getAutor());
            this.revalidate();
            this.repaint();
            return;
        }

        if (presentation.getChildren().size() > 0) {
            RuSlide mySlide = (RuSlide) presentation.getLastChild();
          //  System.out.println("Komponenti panela: " + pnl.getComponentCount());
           // System.out.println("Slajd koji dodajem: " + mySlide.getName());
            if (pnl.getComponentCount() == 1) {
                SlideView sw = new SlideView(mySlide);
                pnl.add(sw);
                sw.adjustSize();
                pnl.add(Box.createRigidArea(new Dimension(0, 50)));
                pnl.revalidate();
                pnl.repaint();
                this.revalidate();
                this.repaint();
                this.reloadView();

            }
        }
        if (presentation.getChildren().size()>0) {
            RuSlide mySlide = (RuSlide) presentation.getLastChild();
            SlideView lastSlideView = null;
            //if (((RuPresentation) notification).getElementState() instanceof EditState) {
            if (this.getpStateManager().getCurrentState() instanceof EditState) {

                if (pnl.getComponentCount() != 1)
                    lastSlideView = (SlideView) pnl.getComponent(pnl.getComponentCount() - 2);
                RuSlide lastSlide = (RuSlide) lastSlideView.getSlide();
                if (!(lastSlide.equals(mySlide))) {
                    SlideView sw = new SlideView(mySlide);
                    pnl.add(sw);
                    sw.adjustSize();
                    pnl.add(Box.createRigidArea(new Dimension(0, 50)));
                    pnl.revalidate();
                    pnl.repaint();
                    this.revalidate();
                    this.repaint();
                    this.reloadView();
                }
            }
        }




    }

    /**
     * @param component Za koju traazimo indeks
     * @return
     */
    public static final int getComponentIndex(Component component) {
        if (component != null && component.getParent() != null) {
            Container c = component.getParent();
            for (int i = 0; i < c.getComponentCount(); i++) {
                if (c.getComponent(i) == component)
                    return i;
            }
        }

        return -1;
    }

}

