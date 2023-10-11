package model.myNode;

import java.io.Serializable;

public class PStateManager implements Serializable {
    private EditState editState;
    private ViewState viewState;
    private PresentationState currentState;

    public PStateManager() {
        initialiseStateManager();
    }

    public void initialiseStateManager() {
        editState = new EditState();
        viewState = new ViewState();
        currentState = editState;
    }

    public PresentationState getCurrentState() {
        return currentState;
    }

    public void setEditState() {
        currentState = editState;
    }

    public void setViewState() {
        currentState = viewState;
    }

}
