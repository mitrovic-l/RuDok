package model.myNode.myNodeModels;

import model.myNode.RuNode;
import model.myNode.RuNodeComposite;

import java.io.Serializable;


public class RuWorkspace extends RuNodeComposite implements Serializable {
    public RuWorkspace(String name, RuNode parent) {
        super(name, parent);
    }
}
