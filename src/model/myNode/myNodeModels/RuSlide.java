package model.myNode.myNodeModels;

import model.elements.Slot;
import model.myNode.RuNode;
import model.myNode.RuNodeComposite;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class RuSlide extends RuNode implements Serializable {
    private List<Slot> slots;
    public RuSlide(String name, RuNodeComposite parent) {
        super(name, parent);
        slots = new ArrayList<>();
    }

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }
}
