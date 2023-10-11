package view.dialogs.slotDialogs;

import controller.StrokeSerializationAdapter;
import model.elementStates.RectangleState;
import observer.ErrorFactory;
import view.MainFrame;
import view.rightPanels.PresentationView;
import view.rightPanels.ProjectView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SlotStrokeDialog extends JDialog {
    private ProjectView projectView;
    private JLabel lblVrstaLinije = new JLabel("Vrsta linije");
    private JRadioButton rbtnIsprekidana = new JRadioButton("Isprekidana linija");
    private JRadioButton rbtnNeisprekidana = new JRadioButton("Neisprekidana linija");
    private JSpinner jsDebljina;
    private ButtonGroup bgOdabir = new ButtonGroup();
    private JPanel pnlOdabir = new JPanel();
    private JButton btnOk = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");
    private JPanel pnlButtons = new JPanel();
    private float debljina;
    private PresentationView otvorena;

    public SlotStrokeDialog(ProjectView projectView){
        System.out.println(projectView.getTabbedPane().getSelectedComponent().getClass().getSimpleName());
        this.otvorena = (PresentationView) projectView.getTabbedPane().getSelectedComponent();
        this.setTitle("Choose type of stroke");
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(400, 150));
        this.setModal(true);
        this.setModalityType(ModalityType.APPLICATION_MODAL);
        this.setLocationRelativeTo(projectView);
        this.setResizable(false);
        bgOdabir.add(rbtnNeisprekidana);
        bgOdabir.add(rbtnIsprekidana);
        SpinnerModel value = new SpinnerNumberModel(2,0,10,1);
        jsDebljina = new JSpinner(value);
        pnlOdabir.setLayout(new GridLayout());
        pnlOdabir.add(rbtnNeisprekidana);
        pnlOdabir.add(rbtnIsprekidana);
        pnlButtons.add(btnOk);
        pnlButtons.add(btnCancel);
        this.add(pnlOdabir, BorderLayout.WEST);
        this.add(jsDebljina, BorderLayout.EAST);
        this.add(pnlButtons, BorderLayout.SOUTH);
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                debljina = (int) jsDebljina.getValue();
                if (!(rbtnNeisprekidana.isSelected()) && (!(rbtnIsprekidana.isSelected()))){
                    ErrorFactory.getInstance().generateError("Greska pri odabiru linije", "Niste izabrali vrstu linije slota.", "Izaberite jednu od ponudjenih vrsta.", 0);
                    return;
                }
                if (otvorena.getPresentation().getElementState() instanceof RectangleState) {
                    if (rbtnNeisprekidana.isSelected()) {
                        ((RectangleState) otvorena.getPresentation().getElementState()).setStroke(new StrokeSerializationAdapter(new BasicStroke((float) debljina)));
                    } else {
                        ((RectangleState) otvorena.getPresentation().getElementState()).setStroke(new StrokeSerializationAdapter(new BasicStroke((float) debljina, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 2.0f, new float[]{3.0f, 2.0f}, 0.0f)));
                    }
                }
                dispose();

            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        this.getRootPane().setDefaultButton(btnOk);
        this.pack();
        this.setVisible(true);









    }
}
