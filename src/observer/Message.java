package observer;

public class Message {
    private String title;
    private String text;
    private int type;
    private String solution;

    public Message(String title, String text, int type, String solution) {
        this.title = title;
        this.text = text;
        this.type = type;
        this.solution = solution;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}
