package model.myNode;

import observer.MyPublisher;
import observer.MySubscriber;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RuNode implements MyPublisher, Serializable {
    private String name;
    private RuNode parent;
    List<MySubscriber>subscribers;

    public RuNode(String name, RuNode parent) {
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.notifySubscribers(this);
    }

    public RuNode getParent() {
        return parent;
    }

    public void setParent(RuNode parent) {
        this.parent = parent;
    }


    @Override
    public void addSubscriber(MySubscriber subscriber) {
        if (subscriber == null)
            return;
        if (this.subscribers == null)
            this.subscribers = new ArrayList<>();
        if (this.subscribers.contains(subscriber))
            return;
        this.subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(MySubscriber subscriber) {
        if (subscriber == null || this.subscribers== null || !(this.subscribers.contains(subscriber)))
            return;
        this.subscribers.remove(subscriber);

    }

    @Override
    public void notifySubscribers(Object notification) {
        if (notification == null || this.subscribers == null || this.subscribers.isEmpty())
            return;
        for (int i=0;i<subscribers.size();i++){
            subscribers.get(i).update(notification);
        }

    }
}
