package model.factory;

import model.factory.nodeFactories.PresentationFactory;
import model.factory.nodeFactories.ProjectFactory;
import model.factory.nodeFactories.SlideFactory;
import model.myNode.RuNode;
import model.myNode.myNodeModels.RuPresentation;
import model.myNode.myNodeModels.RuProject;
import model.myNode.myNodeModels.RuWorkspace;


public class FactoryGenerator {
    private RuNode selected;
    private ProjectFactory projectFactory = new ProjectFactory();
    private PresentationFactory presentationFactory = new PresentationFactory();
    private SlideFactory slideFactory = new SlideFactory();

    public FactoryGenerator(RuNode selected) {
        this.selected = selected;
    }
    public AbstractNodeFactory returnNodeFactory(RuNode selected){
        if (selected instanceof RuWorkspace){
            return projectFactory;
        }
        if (selected instanceof RuProject){
             return presentationFactory;
        }
        if (selected instanceof RuPresentation){
             return slideFactory;
        }
        else {
            return null;
        }

    }
}
