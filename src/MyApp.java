
import model.myNode.myNodeModels.RuPresentation;
import observer.ErrorFactory;
import view.MainFrame;
import view.panels.SlideShowPanel;
import view.rightPanels.PresentationView;

import javax.swing.*;

public class MyApp {
    public static void main(String[] args) {
        MainFrame mf = MainFrame.getInstance();
        mf.setVisible(true);
        ErrorFactory ef = ErrorFactory.getInstance();
        ef.addSubscriber(mf);

    }
}
