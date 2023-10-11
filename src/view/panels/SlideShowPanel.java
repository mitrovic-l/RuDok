package view.panels;

import model.myNode.EditState;
import model.myNode.myNodeModels.RuPresentation;
import model.myNode.myNodeModels.RuSlide;
import view.rightPanels.PresentationView;
import view.rightPanels.SlideView;
import javax.swing.*;
import java.awt.*;

public class SlideShowPanel extends JPanel {
    private PresentationView presentationView;
    private JPanel cardPanel = new JPanel();
    private JPanel buttonPanel = new JPanel(new FlowLayout());
    private JButton btnPrev = new JButton("<<Previous");
    private JButton btnNext = new JButton("Next>>");
    private JButton btnClose = new JButton("Close");
    private JLabel lblPrez = new JLabel();
    private int currentCard;
    private int maxCard;


    public SlideShowPanel(RuPresentation presentation) {
        this.lblPrez.setText(presentation.getName());
        this.setLayout(new BorderLayout());
        this.add(lblPrez, BorderLayout.NORTH);
        cardPanel.setLayout(new CardLayout());
        presentationView = new PresentationView(presentation);
        this.currentCard = 1;
        this.maxCard = presentation.getChildren().size();

        for (Component c : presentationView.getPnl().getComponents()) {
            if (c instanceof SlideView) {
                cardPanel.add(c);
            }
        }

        this.add(new JPanel(), BorderLayout.CENTER);
        this.add(cardPanel, BorderLayout.CENTER);
        btnClose.addActionListener(e -> {
          //  presentation.setPresentationState(new EditState());
            presentationView.setEditState();
            for(Component s : cardPanel.getComponents()){
                if(s instanceof SlideView){
                    SlideView sw = (SlideView) s;
                    RuSlide slide = sw.getSlide();
                    slide.removeSubscriber(sw);
                   //System.out.println("Skinuo subsribera za " + slide.getName());
                }
            }
            presentation.removeSubscriber(presentationView);


        });
        btnPrev.setEnabled(false);
        btnPrev.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardLayout.previous(cardPanel);
            currentCard--;
            cardPanel.revalidate();
            cardPanel.repaint();
            System.out.println("Trenutni slajd: "+currentCard);
            System.out.println("Broj slajdova: "+maxCard);
            if (currentCard == 1) {
                btnPrev.setEnabled(false);
            }
            if (currentCard <= maxCard) {
                btnNext.setEnabled(true);
            }
        });
        btnNext.addActionListener(e -> {
            CardLayout cardLayout = (CardLayout) cardPanel.getLayout();
            cardLayout.next(cardPanel);
            currentCard++;
            cardPanel.revalidate();
            cardPanel.repaint();
            System.out.println("Trenutni slajd: "+currentCard);
            System.out.println("Broj slajdova: "+maxCard);
            if (currentCard >= maxCard) {
                btnNext.setEnabled(false);
            }
            if (currentCard >= 1) {
                btnPrev.setEnabled(true);
            }
        });
        if (maxCard == 1){
            btnPrev.setEnabled(false);
            btnNext.setEnabled(false);
        }
        buttonPanel.add(btnPrev);
        buttonPanel.add(btnNext);
        buttonPanel.add(btnClose);
        this.add(buttonPanel, BorderLayout.SOUTH);


    }

    public PresentationView getPresentationView() {
        return presentationView;
    }

    public void setPresentationView(PresentationView presentationView) {
        this.presentationView = presentationView;
    }

    public JPanel getCardPanel() {
        return cardPanel;
    }

    public void setCardPanel(JPanel cardPanel) {
        this.cardPanel = cardPanel;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public void setButtonPanel(JPanel buttonPanel) {
        this.buttonPanel = buttonPanel;
    }

    public JButton getBtnPrev() {
        return btnPrev;
    }

    public void setBtnPrev(JButton btnPrev) {
        this.btnPrev = btnPrev;
    }

    public JButton getBtnNext() {
        return btnNext;
    }

    public void setBtnNext(JButton btnNext) {
        this.btnNext = btnNext;
    }

    public JButton getBtnClose() {
        return btnClose;
    }

    public void setBtnClose(JButton btnClose) {
        this.btnClose = btnClose;
    }

    public JLabel getLblPrez() {
        return lblPrez;
    }

    public void setLblPrez(JLabel lblPrez) {
        this.lblPrez = lblPrez;
    }

    public int getCurrentCard() {
        return currentCard;
    }

    public void setCurrentCard(int currentCard) {
        this.currentCard = currentCard;
    }

    public int getMaxCard() {
        return maxCard;
    }

    public void setMaxCard(int maxCard) {
        this.maxCard = maxCard;
    }
}
