package controller.actions;

import controller.actions.NewAction;

public class ActionManager {

    private NewAction newAction;
    private InfoAction infoAction;
    private  DeleteAction deleteAction;
    private EditAction editAction;
    private SlideShowAction slideShowAction;
    private AddSlotAction addSlotAction;
    private DeleteSlotAction deleteSlotAction;
    private SelectSlotAction selectSlotAction;
    private SlotColorAction slotColorAction;
    private SlotStrokeAction slotStrokeAction;
    private UndoAction undoAction;
    private RedoAction redoAction;
    private SaveProjectAction saveProjectAction;
    private OpenProjectAction openProjectAction;
    private SharePresentationAction sharePresentationAction;


    public ActionManager(){
        initialiseActions();
    }
    private void initialiseActions(){
        newAction = new NewAction();
        infoAction = new InfoAction();
        deleteAction = new DeleteAction();
        editAction = new EditAction();
        slideShowAction = new SlideShowAction();
        addSlotAction = new AddSlotAction();
        deleteSlotAction = new DeleteSlotAction();
        selectSlotAction = new SelectSlotAction();
        slotColorAction = new SlotColorAction();
        slotStrokeAction = new SlotStrokeAction();
        undoAction = new UndoAction();
        redoAction = new RedoAction();
        saveProjectAction = new SaveProjectAction();
        openProjectAction = new OpenProjectAction();
        sharePresentationAction = new SharePresentationAction();
    }

    public SlotStrokeAction getSlotStrokeAction() {
        return slotStrokeAction;
    }

    public void setSlotStrokeAction(SlotStrokeAction slotStrokeAction) {
        this.slotStrokeAction = slotStrokeAction;
    }

    public SlotColorAction getSlotColorAction() {
        return slotColorAction;
    }

    public void setSlotColorAction(SlotColorAction slotColorAction) {
        this.slotColorAction = slotColorAction;
    }

    public NewAction getNewAction() {
        return newAction;
    }

    public void setNewAction(NewAction newAction) {
        this.newAction = newAction;
    }

    public InfoAction getInfoAction() {
        return infoAction;
    }

    public void setInfoAction(InfoAction infoAction) {
        this.infoAction = infoAction;
    }

    public DeleteAction getDeleteAction() {
        return deleteAction;
    }

    public void setDeleteAction(DeleteAction deleteAction) {
        this.deleteAction = deleteAction;
    }

    public EditAction getEditAction() {
        return editAction;
    }

    public void setEditAction(EditAction editAction) {
        this.editAction = editAction;
    }

    public SlideShowAction getSlideShowAction() {
        return slideShowAction;
    }

    public void setSlideShowAction(SlideShowAction slideShowAction) {
        this.slideShowAction = slideShowAction;
    }

    public AddSlotAction getAddSlotAction() {
        return addSlotAction;
    }

    public void setAddSlotAction(AddSlotAction addSlotAction) {
        this.addSlotAction = addSlotAction;
    }

    public DeleteSlotAction getDeleteSlotAction() {
        return deleteSlotAction;
    }

    public void setDeleteSlotAction(DeleteSlotAction deleteSlotAction) {
        this.deleteSlotAction = deleteSlotAction;
    }

    public SelectSlotAction getSelectSlotAction() {
        return selectSlotAction;
    }

    public void setSelectSlotAction(SelectSlotAction selectSlotAction) {
        this.selectSlotAction = selectSlotAction;
    }

    public UndoAction getUndoAction() {
        return undoAction;
    }

    public void setUndoAction(UndoAction undoAction) {
        this.undoAction = undoAction;
    }

    public RedoAction getRedoAction() {
        return redoAction;
    }

    public void setRedoAction(RedoAction redoAction) {
        this.redoAction = redoAction;
    }

    public SaveProjectAction getSaveProjectAction() {
        return saveProjectAction;
    }

    public void setSaveProjectAction(SaveProjectAction saveProjectAction) {
        this.saveProjectAction = saveProjectAction;
    }

    public OpenProjectAction getOpenProjectAction() {
        return openProjectAction;
    }

    public void setOpenProjectAction(OpenProjectAction openProjectAction) {
        this.openProjectAction = openProjectAction;
    }

    public SharePresentationAction getSharePresentationAction() {
        return sharePresentationAction;
    }

    public void setSharePresentationAction(SharePresentationAction sharePresentationAction) {
        this.sharePresentationAction = sharePresentationAction;
    }
}
