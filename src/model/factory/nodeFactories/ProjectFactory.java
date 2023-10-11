package model.factory.nodeFactories;

import model.factory.AbstractNodeFactory;
import model.myNode.RuNode;
import model.myNode.myNodeModels.RuProject;
import model.myNode.myNodeModels.RuWorkspace;

public class ProjectFactory extends AbstractNodeFactory {

    @Override
    public RuNode createRuNode(RuNode nodeParent) {
       // System.out.println("Dodajem projekat");
        return new RuProject("Projekat "+(((RuWorkspace)nodeParent).getChildren().size()+1), nodeParent);
    }
}
