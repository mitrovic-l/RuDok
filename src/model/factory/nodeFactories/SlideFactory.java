package model.factory.nodeFactories;

import model.factory.AbstractNodeFactory;
import model.myNode.RuNode;
import model.myNode.RuNodeComposite;
import model.myNode.myNodeModels.RuPresentation;
import model.myNode.myNodeModels.RuSlide;

public class SlideFactory extends AbstractNodeFactory {
    @Override
    public RuNode createRuNode(RuNode nodeParent) {
        return new RuSlide("Slajd "+(((RuPresentation)nodeParent).getChildren().size()+1), (RuNodeComposite) nodeParent);
    }
}
