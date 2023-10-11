package observer;

public interface MyPublisher {
    void addSubscriber(MySubscriber subscriber);
    void removeSubscriber(MySubscriber subscriber);
    void notifySubscribers(Object notification);
}
