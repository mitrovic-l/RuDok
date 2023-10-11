package observer;

import java.util.ArrayList;

public class ErrorFactory implements MyPublisher{
    private static ErrorFactory instance;
    private ArrayList<MySubscriber> subscribers = new ArrayList<>();
    private ErrorFactory() {
    }
    public static ErrorFactory getInstance(){
        if (instance == null){
            instance = new ErrorFactory();
        }
        return instance;
    }
    public void generateError(String title, String text, String solution, int type){
       Message m = new Message(title, text, type, solution);
       notifySubscribers(m);
    }

    @Override
    public void addSubscriber(MySubscriber subscriber) {
        this.subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(MySubscriber subscriber) {
        this.subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers(Object notification) {
        for(MySubscriber s: this.subscribers){
            s.update(notification);
        }
    }
}
