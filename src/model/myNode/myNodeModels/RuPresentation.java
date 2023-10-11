package model.myNode.myNodeModels;


import model.elementStates.ElementState;
import model.elementStates.RectangleState;
import model.elementStates.SelectState;
import model.myNode.EditState;
import model.myNode.PresentationState;
import model.myNode.RuNodeComposite;

import java.io.Serializable;


public class RuPresentation extends RuNodeComposite implements Serializable {
    private String filePath;
    private String autor;
    private ElementState elementState;
    private boolean shared;
    public RuPresentation(String name, RuNodeComposite parent, String filePath, String autor){
        super(name, parent);
        this.autor = autor;
        if (filePath == null)
            this.filePath ="";
        else
            this.filePath = filePath;
        this.elementState = new SelectState();
        this.shared = false;
    }

    public ElementState getElementState() {
        return elementState;
    }

    public void setElementState(ElementState elementState) {
        this.elementState = elementState;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
        System.out.println("Promenjena slika na prezentaciji.");
        super.notifySubscribers(this);
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
        System.out.println("Promenjen autor prezentacije.");
        super.notifySubscribers(this);
    }

    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }
}
