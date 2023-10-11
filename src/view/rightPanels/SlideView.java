package view.rightPanels;

import model.elementStates.SelectState;
import model.elements.Slot;
import model.myNode.myNodeModels.RuPresentation;
import model.myNode.myNodeModels.RuSlide;
import observer.MySubscriber;
import view.rightPanels.slotView.SlotView;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;


public class SlideView extends JPanel implements MySubscriber{

    private transient Image slika;
    private RuSlide slide;
    private ArrayList<SlotView> slotViews = new ArrayList<>();
    private SlotView selectedSlot = null;
    private ArrayList<SlotView>selectedSlotsList = new ArrayList<>();

    public SlideView(RuSlide slide) {
        this.slide = slide;
        slide.addSubscriber(this);
        //Povezivanje Observera sa Prezentacijom
        this.setSize(600, 400);
        this.setPreferredSize(new Dimension(600, 400));
        this.setMaximumSize(new Dimension(600, 400));
        try {
            this.slika = ImageIO.read(new File(((RuPresentation) slide.getParent()).getFilePath()));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Nije nadjena slika!");
        }
        this.setVisible(true);
        for (Slot slot : slide.getSlots()) {
            this.slotViews.add(new SlotView(slot));
        }
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                //if metodom onemoguceno dodavanje slotova preko navigatora.
                if (getParent().getParent().getParent().getParent() instanceof ProjectView) {
                    return;
                }
                super.mousePressed(e);
                PresentationView pw = (PresentationView) getParent().getParent().getParent();
                RuPresentation presentation = pw.getPresentation();
                if(presentation.getElementState() instanceof SelectState)
                {
                    for (SlotView slotView : getSlotViews()) {
                        if (slotView.getSlot().getShape().contains(e.getPoint())) {
                            selectedSlot = slotView;
                            if (selectedSlotsList.contains(selectedSlot)){
                                selectedSlotsList.remove(selectedSlot);
                            }
                            selectedSlotsList.add(selectedSlot);
                            break;
                        }
                    }
                }
                else {
                    presentation.getElementState().mousePressed((SlideView) e.getComponent(), e);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                selectedSlot = null;
                RuPresentation presentation = (RuPresentation) slide.getParent();
                presentation.getElementState().mouseReleased((SlideView) e.getComponent(), e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {

            }
        });
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                RuPresentation presentation = (RuPresentation) slide.getParent();
                presentation.getElementState().mouseDragged((SlideView) e.getComponent(), e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
            }

        });
    }


    public SlotView getSelectedSlot() {
        return selectedSlot;
    }

    public void setSelectedSlot(SlotView selectedSlot) {
        this.selectedSlot = selectedSlot;
    }

    public void setSlika(String filePath) {
        try {
            this.slika = ImageIO.read(new File(filePath));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Nije nadjena slika!");
        }
    }

    public void adjustSize() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(this.slika, 0, 0, this.getWidth(), this.getHeight(), null);
        //System.out.println("Painting picture");
        if (this.getParent().getLayout() instanceof CardLayout){
          //  System.err.println(this.getSize());
            g2d.scale((double) this.getHeight() / 400, (double) this.getWidth() / 600);
        }


        else if (this.getParent().getParent().getParent().getParent() instanceof ProjectView)
            g2d.scale(0.2, 0.2);

        for (SlotView s : this.slotViews)
            s.paint(g2d);

    }


    public ArrayList<SlotView> getSelectedSlotsList() {
        return selectedSlotsList;
    }

    public void setSelectedSlotsList(ArrayList<SlotView> selectedSlotsList) {
        this.selectedSlotsList = selectedSlotsList;
    }

    public Image getSlika() {
        return slika;
    }


    public RuSlide getSlide() {
        return slide;
    }

    public void setSlide(RuSlide slide) {
        this.slide = slide;
    }

    public ArrayList<SlotView> getSlotViews() {
        return slotViews;
    }

    public void setSlotViews(ArrayList<SlotView> slotViews) {
        this.slotViews = slotViews;
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

    @Override
    public void update(Object notification) {
        RuSlide mySlide = (RuSlide) notification;
        if (mySlide.getParent() == null) {
            int rigedAreaIndex = getComponentIndex(this);
            JPanel pnlParent = (JPanel) this.getParent();
            pnlParent.remove(this);
            pnlParent.remove(rigedAreaIndex);
            pnlParent.revalidate();
            pnlParent.repaint();
            return;
        }

    }
}
