package model.factory.nodeFactories;

import model.factory.AbstractNodeFactory;
import model.myNode.RuNode;
import model.myNode.RuNodeComposite;
import model.myNode.myNodeModels.RuPresentation;
import model.myNode.myNodeModels.RuProject;
import view.dialogs.newNode.AddNewPresentationDialog;

public class PresentationFactory extends AbstractNodeFactory {
    @Override
    public RuNode createRuNode(RuNode nodeParent) {
        return new RuPresentation("Prezentacija", (RuNodeComposite) nodeParent, "src/view/slideBackgrounds/background1.jpg", "Luka Mitrovic");
    }
}
