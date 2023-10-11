package model.myNode;

import observer.MyPublisher;

import java.io.Serializable;
import java.util.ArrayList;

public class RuNodeComposite extends RuNode implements MyPublisher, Serializable {

    private ArrayList<RuNode> children;

    public RuNodeComposite(String name, RuNode parent) {
        super(name, parent);
        this.children = new ArrayList<>();
    }


    public ArrayList<RuNode> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<RuNode> children) {
        this.children = children;
    }

    public void addChild(RuNode n) {
        this.children.add(n);
        this.notifySubscribers(this);
    }
    public void removeChild(RuNode n){
        RuNodeComposite parent = (RuNodeComposite) n.getParent();
        n.setParent(null);
        parent.getChildren().remove(n);
        n.notifySubscribers(n);
    }
    public RuNode getLastChild(){
        return this.getChildAt(this.getChildren().size()-1);

    }

    public RuNode getChildAt(int index){
        return this.getChildren().get(index);
    }

    @Override
    public String toString() {
        return "MyNodeComposite{ Name= " + super.getName()+
                " children= " + children +
                '}';
    }
}
