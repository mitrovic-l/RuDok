package view.rightPanels;

import model.myNode.RuNode;
import model.myNode.myNodeModels.RuPresentation;
import model.myNode.myNodeModels.RuProject;
import observer.MySubscriber;
import view.MainFrame;
import view.bars.PaletteBar;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ProjectView extends JPanel implements MySubscriber {

    private JLabel lblProjekat;
    private PresentationView presentationView;
    private JTabbedPane tabbedPane;
    private JPanel pnlAutor;
    private RuProject project;
    private PresentationView test;
    private JPanel navigationPanel = new JPanel();
    private PaletteBar paletteBar;


    public ProjectView(RuProject project) {
        this.project = project;
        this.project.addSubscriber(this);
        this.setLayout(new BorderLayout());
        this.lblProjekat = new JLabel(project.getName());
        this.pnlAutor = new JPanel();
        pnlAutor.add(lblProjekat);
        this.add(pnlAutor, BorderLayout.NORTH);
        this.tabbedPane = new JTabbedPane();
        this.paletteBar = new PaletteBar();
        tabbedPane.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                if (test != null) {
                    //Znaci da je prezentacija u edit reximu
                    if(tabbedPane.getSelectedComponent() instanceof PresentationView){
                        if(MainFrame.getInstance().getJMenuBar() == null)
                            MainFrame.getInstance().setJMenuBar(MainFrame.getInstance().getMyMenuBar());

                        MainFrame.getInstance().add(MainFrame.getInstance().getToolBar(), BorderLayout.NORTH);
                        MainFrame.getInstance().revalidate();
                        MainFrame.getInstance().repaint();
                        changeNavigation(((PresentationView) tabbedPane.getSelectedComponent()));
                        if (paletteBar.getParent() == null){
                            add(paletteBar, BorderLayout.EAST);
                            MainFrame.getInstance().revalidate();
                            MainFrame.getInstance().repaint();
                        }
                    }


                    else {
                        if(MainFrame.getInstance().getJMenuBar() != null) {
                            MainFrame.getInstance().setJMenuBar(null);
                            MainFrame.getInstance().remove(MainFrame.getInstance().getToolBar());
                            ProjectView p2 = (ProjectView)MainFrame.getInstance().getSplitPane().getRightComponent();
                            //Sklanjanje paletteBar-a iz prezentacije koja je u slideshow rezimu rada.
                            Component component = p2.getComponent(p2.getComponentCount()-1);
                            System.out.println(component.getClass().getSimpleName());
                            ((ProjectView) MainFrame.getInstance().getSplitPane().getRightComponent()).remove(component);
                            MainFrame.getInstance().revalidate();
                            MainFrame.getInstance().repaint();
                            remove(test);
                        }

                    }
                    System.out.println("Selected is " + test.getPresentation().getName());

                }
            }
        });
        for (RuNode pres : project.getChildren()) {
            PresentationView presV = new PresentationView((RuPresentation) pres);
            tabbedPane.addTab(pres.getName(), presV);
            presV.setPreferredSize(presV.getPreferredSize());
            //  presV.testiranje();

        }

        this.add(tabbedPane, BorderLayout.CENTER);
        this.add(paletteBar, BorderLayout.EAST);
        RuPresentation t = (RuPresentation) project.getChildAt(0);
        this.test = new PresentationView(t);
        revalidate();

        JPanel pnl = (JPanel) test.getViewport().getView();
        System.out.println(test.getViewport().getView());
        for (Component deca : pnl.getComponents()) {
            deca.setMaximumSize(new Dimension(120, 80));
            deca.setSize(new Dimension(120, 80));
            deca.setMinimumSize(new Dimension(120, 80));
            deca.setPreferredSize(new Dimension(120, 80));
            deca.revalidate();
            deca.repaint();
            //System.out.println(deca.getSize());
        }
        test.revalidate();
        test.repaint();
        this.add(test, BorderLayout.WEST);
       // this.setColorSlota(new Color(200,0,150));


        this.setVisible(true);
        test.getViewport().setViewSize(new Dimension(110, 20));
        revalidate();
        MainFrame.getInstance().setJMenuBar(MainFrame.getInstance().getMyMenuBar());
        if (!(MainFrame.getInstance().getComponent(0).equals(MainFrame.getInstance().getToolBar()))){
            MainFrame.getInstance().add(MainFrame.getInstance().getToolBar(), BorderLayout.NORTH);
        }
        MainFrame.getInstance().revalidate();
        MainFrame.getInstance().repaint();
    }


    public void changeNavigation(PresentationView presView){
        this.remove(test);
        if (presView == null){
            return;
        }
        PresentationView pw = new PresentationView(presView.getPresentation());

        this.add(pw, BorderLayout.WEST);
        this.setVisible(true);
        pw.getViewport().setViewSize(new Dimension(100, 20));
        JPanel pnl = (JPanel) pw.getViewport().getView();
        for (Component deca : pnl.getComponents()) {
            deca.setMaximumSize(new Dimension(120, 80));
            deca.setSize(new Dimension(120, 80));
            deca.setMinimumSize(new Dimension(120, 80));
            deca.setPreferredSize(new Dimension(120, 80));
            deca.revalidate();
            deca.repaint();
        }
        pw.revalidate();
        pw.repaint();
        revalidate();
        repaint();

        test = pw;
    }

    public PaletteBar getPaletteBar() {
        return paletteBar;
    }

    public void setPaletteBar(PaletteBar paletteBar) {
        this.paletteBar = paletteBar;
    }

    public PresentationView getTest() {
        return test;
    }

    public void setTest(PresentationView test) {
        this.test = test;
    }

    public JLabel getLblProjekat() {
        return lblProjekat;
    }

    public void setLblProjekat(JLabel lblProjekat) {
        this.lblProjekat = lblProjekat;
    }

    public PresentationView getPresentationView() {
        return presentationView;
    }

    public void setPresentationView(PresentationView presentationView) {
        this.presentationView = presentationView;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public void setTabbedPane(JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
    }


    public JPanel getPnlAutor() {
        return pnlAutor;
    }

    public void setPnlAutor(JPanel pnlAutor) {
        this.pnlAutor = pnlAutor;
    }

    public RuProject getProject() {
        return project;
    }

    public void setProject(RuProject projecty) {
        this.project = project;
    }

    @Override
    public void update(Object notification) {
        RuProject myProject = (RuProject) notification;
        System.out.println("Update Project view");
        if (myProject.getParent() == null) {
            MainFrame.getInstance().getSplitPane().setRightComponent(new JPanel());
            MainFrame.getInstance().revalidate();
            MainFrame.getInstance().repaint();
            return;
        }
        if (!(lblProjekat.getText().equals(myProject.getName()))) {
            System.out.println("Promena labele");
            lblProjekat.setText(myProject.getName());
            return;
        }
        //Dodavanje taba za prezentaicjuu
        //Nema decu
        if (myProject.getChildren().size() > 0) {
            System.out.println("Usao za dodavanj prezentation taba");
            if (tabbedPane.getTabCount() == 0) {
                RuPresentation myPresentation = (RuPresentation) myProject.getLastChild();
                PresentationView pw = new PresentationView(myPresentation);
                tabbedPane.addTab(myPresentation.getName(), pw);
                tabbedPane.revalidate();
                tabbedPane.repaint();
                return;
            }
        }
        //Ima decu
        if (myProject.getChildren().size() > tabbedPane.getTabCount()) {
            RuPresentation novaPrezentacija = (RuPresentation) project.getLastChild();
            tabbedPane.addTab(novaPrezentacija.getName(), new PresentationView(novaPrezentacija));
        }
    }

}
