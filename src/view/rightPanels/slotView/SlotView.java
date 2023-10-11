package view.rightPanels.slotView;

import model.elements.Slot;

import java.awt.*;
import java.io.Serializable;

public class SlotView implements Serializable {
    private Slot slot;

    public SlotView(Slot slot) {
        this.slot = slot;
    }

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }


    public void paint(Graphics2D g){
        g.setColor(Color.RED);
        g.setPaint(Color.RED);
        g.setStroke(slot.getStroke());
        g.draw(slot.getShape());
        g.setPaint(slot.getColor());
        g.fill(slot.getShape());


    }
}
