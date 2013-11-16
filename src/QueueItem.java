
public class QueueItem<E> {

    private E data;
    private int priority;
    private QueueItem next;

    public QueueItem() {
        data = null;
        priority = 0;
        next = null;
    }

    public QueueItem(E _data) {
        data = _data;
        priority = 0;
        next = null;
    }

    public QueueItem(E _data, int _priority) {
        data = _data;
        priority = _priority;
        next = null;
    }

    public QueueItem(E _data, QueueItem _next) {
        data = _data;
        priority = 0;
        next = _next;
    }

    public QueueItem(E _data, int _priority, QueueItem _next) {
        data = _data;
        priority = _priority;
        next = _next;
    }

    public void setNext(QueueItem _next) {
        next = _next;
    }

    public QueueItem getNext() {
        return next;
    }

    public E getData() {
        return data;
    }

    public void setPriority(int _priority) {
        priority = _priority;
    }

    public int getPriority() {
        return priority;
    }

}
